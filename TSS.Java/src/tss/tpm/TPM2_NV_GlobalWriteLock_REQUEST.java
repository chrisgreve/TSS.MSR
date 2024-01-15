package tss.tpm;

import tss.*;


// -----------This is an auto-generated file: do not edit

//>>>

/** The command will SET TPMA_NV_WRITELOCKED for all indexes that have their
 *  TPMA_NV_GLOBALLOCK attribute SET.
 */
public class TPM2_NV_GlobalWriteLock_REQUEST extends ReqStructure
{
    /** TPM_RH_OWNER or TPM_RH_PLATFORM+{PP}
     *  Auth Index: 1
     *  Auth Role: USER
     */
    public final TPM_HANDLE authHandle;

    public TPM2_NV_GlobalWriteLock_REQUEST() { authHandle = new TPM_HANDLE(); }

    /** @param _authHandle TPM_RH_OWNER or TPM_RH_PLATFORM+{PP}
     *         Auth Index: 1
     *         Auth Role: USER
     */
    public TPM2_NV_GlobalWriteLock_REQUEST(TPM_HANDLE _authHandle) { authHandle = _authHandle; }

    /** @deprecated Use {@link #toBytes()} instead
     *  @return Wire (marshaled) representation of this object
     */
    public byte[] toTpm () { return toBytes(); }

    /** Static marshaling helper
     *  @param byteBuf Wire representation of the object
     *  @return New object constructed from its wire representation
     */
    public static TPM2_NV_GlobalWriteLock_REQUEST fromBytes (byte[] byteBuf) 
    {
        return new TpmBuffer(byteBuf).createObj(TPM2_NV_GlobalWriteLock_REQUEST.class);
    }

    /** @deprecated Use {@link #fromBytes(byte[])} instead
     *  @param byteBuf Wire representation of the object
     *  @return New object constructed from its wire representation
     */
    public static TPM2_NV_GlobalWriteLock_REQUEST fromTpm (byte[] byteBuf)  { return fromBytes(byteBuf); }

    /** Static marshaling helper
     *  @param buf Wire representation of the object
     *  @return New object constructed from its wire representation
     */
    public static TPM2_NV_GlobalWriteLock_REQUEST fromTpm (TpmBuffer buf) 
    {
        return buf.createObj(TPM2_NV_GlobalWriteLock_REQUEST.class);
    }

    @Override
    public String toString()
    {
        TpmStructurePrinter _p = new TpmStructurePrinter("TPM2_NV_GlobalWriteLock_REQUEST");
        toStringInternal(_p, 1);
        _p.endStruct();
        return _p.toString();
    }

    @Override
    public void toStringInternal(TpmStructurePrinter _p, int d)
    {
        _p.add(d, "TPM_HANDLE", "authHandle", authHandle);
    }

    @Override
    public int numHandles() { return 1; }

    @Override
    public int numAuthHandles() { return 1; }

    @Override
    public TPM_HANDLE[] getHandles() { return new TPM_HANDLE[] {authHandle}; }
}

//<<<
