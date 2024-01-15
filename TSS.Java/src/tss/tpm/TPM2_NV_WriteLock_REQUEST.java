package tss.tpm;

import tss.*;


// -----------This is an auto-generated file: do not edit

//>>>

/** If the TPMA_NV_WRITEDEFINE or TPMA_NV_WRITE_STCLEAR attributes of an NV location are
 *  SET, then this command may be used to inhibit further writes of the NV Index.
 */
public class TPM2_NV_WriteLock_REQUEST extends ReqStructure
{
    /** Handle indicating the source of the authorization value
     *  Auth Index: 1
     *  Auth Role: USER
     */
    public final TPM_HANDLE authHandle;

    /** The NV Index of the area to lock
     *  Auth Index: None
     */
    public final TPM_HANDLE nvIndex;

    public TPM2_NV_WriteLock_REQUEST()
    {
        authHandle = new TPM_HANDLE();
        nvIndex = new TPM_HANDLE();
    }

    /** @param _authHandle Handle indicating the source of the authorization value
     *         Auth Index: 1
     *         Auth Role: USER
     *  @param _nvIndex The NV Index of the area to lock
     *         Auth Index: None
     */
    public TPM2_NV_WriteLock_REQUEST(TPM_HANDLE _authHandle, TPM_HANDLE _nvIndex)
    {
        authHandle = _authHandle;
        nvIndex = _nvIndex;
    }

    /** @deprecated Use {@link #toBytes()} instead
     *  @return Wire (marshaled) representation of this object
     */
    public byte[] toTpm () { return toBytes(); }

    /** Static marshaling helper
     *  @param byteBuf Wire representation of the object
     *  @return New object constructed from its wire representation
     */
    public static TPM2_NV_WriteLock_REQUEST fromBytes (byte[] byteBuf) 
    {
        return new TpmBuffer(byteBuf).createObj(TPM2_NV_WriteLock_REQUEST.class);
    }

    /** @deprecated Use {@link #fromBytes(byte[])} instead
     *  @param byteBuf Wire representation of the object
     *  @return New object constructed from its wire representation
     */
    public static TPM2_NV_WriteLock_REQUEST fromTpm (byte[] byteBuf)  { return fromBytes(byteBuf); }

    /** Static marshaling helper
     *  @param buf Wire representation of the object
     *  @return New object constructed from its wire representation
     */
    public static TPM2_NV_WriteLock_REQUEST fromTpm (TpmBuffer buf) 
    {
        return buf.createObj(TPM2_NV_WriteLock_REQUEST.class);
    }

    @Override
    public String toString()
    {
        TpmStructurePrinter _p = new TpmStructurePrinter("TPM2_NV_WriteLock_REQUEST");
        toStringInternal(_p, 1);
        _p.endStruct();
        return _p.toString();
    }

    @Override
    public void toStringInternal(TpmStructurePrinter _p, int d)
    {
        _p.add(d, "TPM_HANDLE", "authHandle", authHandle);
        _p.add(d, "TPM_HANDLE", "nvIndex", nvIndex);
    }

    @Override
    public int numHandles() { return 2; }

    @Override
    public int numAuthHandles() { return 1; }

    @Override
    public TPM_HANDLE[] getHandles() { return new TPM_HANDLE[] {authHandle, nvIndex}; }
}

//<<<
