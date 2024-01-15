package tss;
import java.io.IOException;
import java.net.Socket;

public class TpmDeviceTcp extends TpmDevice 
{
    protected Socket CommandSocket = null;
    protected Socket SignalSocket = null;
    String hostName;
    int port;
    boolean linuxTrm;
    boolean oldTrm = true;
    
    boolean responsePending;
    int currentLocality;
    
    public TpmDeviceTcp(String hostName, int port, boolean linuxTrm)
    {
        init(hostName, port, linuxTrm);
    }

    public TpmDeviceTcp(String hostName, int port)
    {
        init(hostName, port, false);
    }
    

    void init(String hostName, int port, boolean linuxTrm)
    {
        this.hostName = hostName;
        this.port = port;
        this.linuxTrm = linuxTrm;
    }

    @Override
    public boolean connect()
    {
        try {
            CommandSocket = new Socket(hostName, port);
            if (!linuxTrm)
                SignalSocket = new Socket(hostName, port+1);
        } catch (Exception e) {
            if (CommandSocket != null)
                try { CommandSocket.close(); } catch (IOException ignored ) {}
            System.err.println("Failed to connect to the TPM at " + hostName + ":" + 
                               port + ": " +  e.getMessage());
            return false;
        }
        
        if (linuxTrm)
        {
            byte[] cmdGetRandom = new byte[] {
                    (byte)0x80, 0x01,       // TPM_ST_NO_SESSIONS
                    0, 0, 0, 0x0C,          // length
                    0, 0, 0x01, 0x7B,       // TPM_CC_GetRandom
                    0, 0x08                 // Command parameter - num random bytes to generate
            };

            byte[] resp = null;
            try {
                dispatchCommand(cmdGetRandom);
                resp = getResponse();
            }
            catch (Exception ignored ) {}
            if (resp == null || resp.length != 20)
            {
                try { CommandSocket.close(); } catch (IOException ignored ) {}
                CommandSocket = null;
                if (oldTrm) {
                    oldTrm = false;
                    connect(hostName, port);
                }
                else {
                    System.err.println("Unknown user mode TRM protocol version");
                    return false;
                }
            }
            //System.out.println("==>> Connected to " + (oldTrm ? "OLD TRM" : "NEW TRM"));
        }
        return true;
    }

    public void connect(String hostName, int port, boolean linuxTrm)
    {
        init(hostName, port, linuxTrm);
    }

    public void connect(String hostName, int port)
    {
        init(hostName, port, false);
    }

    @Override
    public void close()
    {
        if (CommandSocket != null) {
            writeInt(CommandSocket, TcpTpmCommands.SessionEnd.Val);
            try { CommandSocket.close(); } catch (IOException ignored ) {}
            CommandSocket = null;
        }
        if (SignalSocket != null) {
            writeInt(SignalSocket, TcpTpmCommands.SessionEnd.Val);
            try { SignalSocket.close(); } catch (IOException ignored ) {}
            SignalSocket = null;
        }
    }

    @Override
    public void dispatchCommand(byte[] commandBuffer) 
    {
        writeInt(CommandSocket, TcpTpmCommands.SendCommand.Val);
        writeBuf(CommandSocket, new byte[] {(byte) currentLocality});
        if (linuxTrm && oldTrm)
        {
            // Send 'debugMsgLevel'
            writeBuf(CommandSocket, new byte[]{0});
            // Send 'commandSent' status bit
            writeBuf(CommandSocket, new byte[]{1});
        }    
        writeInt(CommandSocket, commandBuffer.length);
        try {
            CommandSocket.getOutputStream().write(commandBuffer);
            responsePending = true;
        } catch (IOException e) {
            throw new TpmException("Error sending data to the TPM", e);
        }
    }
    
    @Override
    public byte[] getResponse()
    {
        if(!responsePending)
        {
            throw new TpmException("Cannot getResponse() without a prior dispatchCommand()");
        }
        responsePending = false;
        byte[] outBuf = readEncapsulated(CommandSocket);
        readInt(CommandSocket);
        return outBuf;
    }
    
    @Override
    public boolean responseReady()
    {
        if(!responsePending)
        {
            throw new TpmException("Cannot responseReady() without a prior dispatchCommand()");
        }
        int available;
        try {
            available = CommandSocket.getInputStream().available();
        } catch (IOException e) {
            throw new TpmException("Error getting data from the TPM", e);
        }
        return (available>0);
    }
    
    @Override
    public void powerCtl(boolean on)
    {
        sendCmdAndGetAck(SignalSocket, on ? TcpTpmCommands.SignalPowerOn : TcpTpmCommands.SignalPowerOff);
        sendCmdAndGetAck(SignalSocket, on ? TcpTpmCommands.SignalNvOn : TcpTpmCommands.SignalNvOff);
    }

    @Override
    public void assertPhysicalPresence(boolean on)
    {
        sendCmdAndGetAck(SignalSocket, on ? TcpTpmCommands.SignalPPOn : TcpTpmCommands.SignalPPOff);
    }

    @Override
    public void setLocality(int locality)  
    {
        currentLocality = locality;
    }
    
    public void sendCmdAndGetAck(Socket s, TcpTpmCommands comm) 
    {
        writeEncapsulated(s, Helpers.hostToNet(comm.getVal()));
        getAck(s);
    }
    private void getAck(Socket s) 
    {
        readInt(s);
    }
    private int readInt(Socket s) 
    {
        int val=-1;
        try {
            val = Helpers.netToHost(readBuf(s, 4));
        } catch (Exception e) {
            throw new TpmException("TPM IO error", e);
        }
        return val;
    }

    private void writeInt(Socket s, int val) 
    {
        writeBuf(s, Helpers.hostToNet(val));
    }
    
    private void writeBuf(Socket s, byte[] buffer) 
    {
        try 
        {
            s.getOutputStream().write(buffer, 0, buffer.length);
        } catch (IOException e) {
            throw new TpmException("TPM IO error", e);
        }
    }
    
    private byte[] readBuf(Socket s, int numBytes) 
    {
        byte[] buf = new byte[numBytes];
        int numRead = 0;
        while(numRead<numBytes)
        {
            int sz;
            try {
                sz = s.getInputStream().read(buf, numRead, numBytes-numRead);
            } catch (IOException e) {
                throw new TpmException("TPM IO error", e);
            }
            numRead+=sz;
        }
        return buf;
    }
    
    private void writeEncapsulated(Socket s, byte[] buf) 
    {
        writeBuf(s, Helpers.hostToNet(buf.length));
        writeBuf(s, buf);
    }

    private byte[] readEncapsulated(Socket s) 
    {
        byte[] t = readBuf(s, 4);
        int sz = Helpers.netToHost(t);
        return readBuf(s, sz);
    }
    
    /**
     * Commands of the Microsoft TPM simulator TCP protocol
     */
    enum TcpTpmCommands
    {
        SignalPowerOn (1),
        SignalPowerOff (2),
        SignalPPOn (3),
        SignalPPOff (4),
        SignalHashStart (5),
        SignalHashData (6),
        SignalHashEnd (7),
        SendCommand (8),
        SignalCancelOn (9),
        SignalCancelOff (10),
        SignalNvOn (11),
        SignalNvOff (12),
        SignalKeyCacheOn (13),
        SignalKeyCacheOff (14),
        RemoteHandshake (15),
        //SetAlternativeResult = 16,    // Not used since 1.38h
        SessionEnd (20),
        Stop (21),
        TestFailureMode (30);
        
        private int Val;
        TcpTpmCommands(int val)
        {
            setVal(val);
        }
        public int getVal() {
            return Val;
        }
        public void setVal(int val) {
            Val = val;
        }
    } // enum TcpTpmCommands
}
