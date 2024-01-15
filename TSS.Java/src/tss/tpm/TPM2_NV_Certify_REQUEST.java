package tss.tpm;

import tss.*;


// -----------This is an auto-generated file: do not edit

//>>>

/** The purpose of this command is to certify the contents of an NV Index or portion of an
 *  NV Index.
 */
public class TPM2_NV_Certify_REQUEST extends ReqStructure
{
    /** Handle of the key used to sign the attestation structure
     *  Auth Index: 1
     *  Auth Role: USER
     */
    public final TPM_HANDLE signHandle;

    /** Handle indicating the source of the authorization value for the NV Index
     *  Auth Index: 2
     *  Auth Role: USER
     */
    public final TPM_HANDLE authHandle;

    /** Index for the area to be certified
     *  Auth Index: None
     */
    public final TPM_HANDLE nvIndex;

    /** User-provided qualifying data */
    public byte[] qualifyingData;

    /** Scheme selector */
    public TPM_ALG_ID inSchemeScheme() { return inScheme != null ? inScheme.GetUnionSelector() : TPM_ALG_ID.NULL; }

    /** Signing scheme to use if the scheme for signHandle is TPM_ALG_NULL
     *  One of: TPMS_SIG_SCHEME_RSASSA, TPMS_SIG_SCHEME_RSAPSS, TPMS_SIG_SCHEME_ECDSA,
     *  TPMS_SIG_SCHEME_ECDAA, TPMS_SIG_SCHEME_SM2, TPMS_SIG_SCHEME_ECSCHNORR,
     *  TPMS_SCHEME_HMAC, TPMS_SCHEME_HASH, TPMS_NULL_SIG_SCHEME.
     */
    public TPMU_SIG_SCHEME inScheme;

    /** Number of octets to certify */
    public int size;

    /** Octet offset into the NV area
     *  This value shall be less than or equal to the size of the nvIndex data.
     */
    public int offset;

    public TPM2_NV_Certify_REQUEST()
    {
        signHandle = new TPM_HANDLE();
        authHandle = new TPM_HANDLE();
        nvIndex = new TPM_HANDLE();
    }

    /** @param _signHandle Handle of the key used to sign the attestation structure
     *         Auth Index: 1
     *         Auth Role: USER
     *  @param _authHandle Handle indicating the source of the authorization value for the NV Index
     *         Auth Index: 2
     *         Auth Role: USER
     *  @param _nvIndex Index for the area to be certified
     *         Auth Index: None
     *  @param _qualifyingData User-provided qualifying data
     *  @param _inScheme Signing scheme to use if the scheme for signHandle is TPM_ALG_NULL
     *         One of: TPMS_SIG_SCHEME_RSASSA, TPMS_SIG_SCHEME_RSAPSS, TPMS_SIG_SCHEME_ECDSA,
     *         TPMS_SIG_SCHEME_ECDAA, TPMS_SIG_SCHEME_SM2, TPMS_SIG_SCHEME_ECSCHNORR,
     *         TPMS_SCHEME_HMAC, TPMS_SCHEME_HASH, TPMS_NULL_SIG_SCHEME.
     *  @param _size Number of octets to certify
     *  @param _offset Octet offset into the NV area
     *         This value shall be less than or equal to the size of the nvIndex data.
     */
    public TPM2_NV_Certify_REQUEST(TPM_HANDLE _signHandle, TPM_HANDLE _authHandle, TPM_HANDLE _nvIndex, byte[] _qualifyingData, TPMU_SIG_SCHEME _inScheme, int _size, int _offset)
    {
        signHandle = _signHandle;
        authHandle = _authHandle;
        nvIndex = _nvIndex;
        qualifyingData = _qualifyingData;
        inScheme = _inScheme;
        size = _size;
        offset = _offset;
    }

    /** TpmMarshaller method */
    @Override
    public void toTpm(TpmBuffer buf)
    {
        buf.writeSizedByteBuf(qualifyingData);
        buf.writeShort(inScheme.GetUnionSelector());
        inScheme.toTpm(buf);
        buf.writeShort(size);
        buf.writeShort(offset);
    }

    /** TpmMarshaller method */
    @Override
    public void initFromTpm(TpmBuffer buf)
    {
        qualifyingData = buf.readSizedByteBuf();
        TPM_ALG_ID inSchemeScheme = TPM_ALG_ID.fromTpm(buf);
        inScheme = UnionFactory.create("TPMU_SIG_SCHEME", inSchemeScheme);
        inScheme.initFromTpm(buf);
        size = buf.readShort();
        offset = buf.readShort();
    }

    /** @deprecated Use {@link #toBytes()} instead
     *  @return Wire (marshaled) representation of this object
     */
    public byte[] toTpm () { return toBytes(); }

    /** Static marshaling helper
     *  @param byteBuf Wire representation of the object
     *  @return New object constructed from its wire representation
     */
    public static TPM2_NV_Certify_REQUEST fromBytes (byte[] byteBuf) 
    {
        return new TpmBuffer(byteBuf).createObj(TPM2_NV_Certify_REQUEST.class);
    }

    /** @deprecated Use {@link #fromBytes(byte[])} instead
     *  @param byteBuf Wire representation of the object
     *  @return New object constructed from its wire representation
     */
    public static TPM2_NV_Certify_REQUEST fromTpm (byte[] byteBuf)  { return fromBytes(byteBuf); }

    /** Static marshaling helper
     *  @param buf Wire representation of the object
     *  @return New object constructed from its wire representation
     */
    public static TPM2_NV_Certify_REQUEST fromTpm (TpmBuffer buf) 
    {
        return buf.createObj(TPM2_NV_Certify_REQUEST.class);
    }

    @Override
    public String toString()
    {
        TpmStructurePrinter _p = new TpmStructurePrinter("TPM2_NV_Certify_REQUEST");
        toStringInternal(_p, 1);
        _p.endStruct();
        return _p.toString();
    }

    @Override
    public void toStringInternal(TpmStructurePrinter _p, int d)
    {
        _p.add(d, "TPM_HANDLE", "signHandle", signHandle);
        _p.add(d, "TPM_HANDLE", "authHandle", authHandle);
        _p.add(d, "TPM_HANDLE", "nvIndex", nvIndex);
        _p.add(d, "byte[]", "qualifyingData", qualifyingData);
        _p.add(d, "TPMU_SIG_SCHEME", "inScheme", inScheme);
        _p.add(d, "int", "size", size);
        _p.add(d, "int", "offset", offset);
    }

    @Override
    public int numHandles() { return 3; }

    @Override
    public int numAuthHandles() { return 2; }

    @Override
    public TPM_HANDLE[] getHandles() { return new TPM_HANDLE[] {signHandle, authHandle, nvIndex}; }

    @Override
    public SessEncInfo sessEncInfo() { return new SessEncInfo(2, 1); }
}

//<<<
