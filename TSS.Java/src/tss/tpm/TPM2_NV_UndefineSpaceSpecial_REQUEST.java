package tss.tpm;

import tss.*;


// -----------This is an auto-generated file: do not edit

//>>>

/** This command allows removal of a platform-created NV Index that has
 *  TPMA_NV_POLICY_DELETE SET.
 */
public class TPM2_NV_UndefineSpaceSpecial_REQUEST extends ReqStructure
{
    /** Index to be deleted
     *  Auth Index: 1
     *  Auth Role: ADMIN
     */
    public final TPM_HANDLE nvIndex;

    /** TPM_RH_PLATFORM + {PP}
     *  Auth Index: 2
     *  Auth Role: USER
     */
    public final TPM_HANDLE platform;

    public TPM2_NV_UndefineSpaceSpecial_REQUEST()
    {
        nvIndex = new TPM_HANDLE();
        platform = new TPM_HANDLE();
    }

    /** @param _nvIndex Index to be deleted
     *         Auth Index: 1
     *         Auth Role: ADMIN
     *  @param _platform TPM_RH_PLATFORM + {PP}
     *         Auth Index: 2
     *         Auth Role: USER
     */
    public TPM2_NV_UndefineSpaceSpecial_REQUEST(TPM_HANDLE _nvIndex, TPM_HANDLE _platform)
    {
        nvIndex = _nvIndex;
        platform = _platform;
    }

    /** @deprecated Use {@link #toBytes()} instead
     *  @return Wire (marshaled) representation of this object
     */
    public byte[] toTpm () { return toBytes(); }

    /** Static marshaling helper
     *  @param byteBuf Wire representation of the object
     *  @return New object constructed from its wire representation
     */
    public static TPM2_NV_UndefineSpaceSpecial_REQUEST fromBytes (byte[] byteBuf) 
    {
        return new TpmBuffer(byteBuf).createObj(TPM2_NV_UndefineSpaceSpecial_REQUEST.class);
    }

    /** @deprecated Use {@link #fromBytes(byte[])} instead
     *  @param byteBuf Wire representation of the object
     *  @return New object constructed from its wire representation
     */
    public static TPM2_NV_UndefineSpaceSpecial_REQUEST fromTpm (byte[] byteBuf)  { return fromBytes(byteBuf); }

    /** Static marshaling helper
     *  @param buf Wire representation of the object
     *  @return New object constructed from its wire representation
     */
    public static TPM2_NV_UndefineSpaceSpecial_REQUEST fromTpm (TpmBuffer buf) 
    {
        return buf.createObj(TPM2_NV_UndefineSpaceSpecial_REQUEST.class);
    }

    @Override
    public String toString()
    {
        TpmStructurePrinter _p = new TpmStructurePrinter("TPM2_NV_UndefineSpaceSpecial_REQUEST");
        toStringInternal(_p, 1);
        _p.endStruct();
        return _p.toString();
    }

    @Override
    public void toStringInternal(TpmStructurePrinter _p, int d)
    {
        _p.add(d, "TPM_HANDLE", "nvIndex", nvIndex);
        _p.add(d, "TPM_HANDLE", "platform", platform);
    }

    @Override
    public int numHandles() { return 2; }

    @Override
    public int numAuthHandles() { return 2; }

    @Override
    public TPM_HANDLE[] getHandles() { return new TPM_HANDLE[] {nvIndex, platform}; }
}

//<<<
