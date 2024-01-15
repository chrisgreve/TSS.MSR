package tss.tpm;

import tss.*;


// -----------This is an auto-generated file: do not edit

//>>>

/** This command includes a secret-based authorization to a policy. The caller proves
 *  knowledge of the secret value using an authorization session using the authValue
 *  associated with authHandle. A password session, an HMAC session, or a policy session
 *  containing TPM2_PolicyAuthValue() or TPM2_PolicyPassword() will satisfy this requirement.
 */
public class TPM2_PolicySecret_REQUEST extends ReqStructure
{
    /** Handle for an entity providing the authorization
     *  Auth Index: 1
     *  Auth Role: USER
     */
    public final TPM_HANDLE authHandle;

    /** Handle for the policy session being extended
     *  Auth Index: None
     */
    public final TPM_HANDLE policySession;

    /** The policy nonce for the session
     *  This can be the Empty Buffer.
     */
    public byte[] nonceTPM;

    /** Digest of the command parameters to which this authorization is limited
     *  This not the cpHash for this command but the cpHash for the command to which this
     *  policy session will be applied. If it is not limited, the parameter will be the Empty Buffer.
     */
    public byte[] cpHashA;

    /** A reference to a policy relating to the authorization may be the Empty Buffer
     *  Size is limited to be no larger than the nonce size supported on the TPM.
     */
    public byte[] policyRef;

    /** Time when authorization will expire, measured in seconds from the time that nonceTPM
     *  was generated
     *  If expiration is non-negative, a NULL Ticket is returned. See 23.2.5.
     */
    public int expiration;

    public TPM2_PolicySecret_REQUEST()
    {
        authHandle = new TPM_HANDLE();
        policySession = new TPM_HANDLE();
    }

    /** @param _authHandle Handle for an entity providing the authorization
     *         Auth Index: 1
     *         Auth Role: USER
     *  @param _policySession Handle for the policy session being extended
     *         Auth Index: None
     *  @param _nonceTPM The policy nonce for the session
     *         This can be the Empty Buffer.
     *  @param _cpHashA Digest of the command parameters to which this authorization is limited
     *         This not the cpHash for this command but the cpHash for the command to which this
     *         policy session will be applied. If it is not limited, the parameter will be the
     *         Empty Buffer.
     *  @param _policyRef A reference to a policy relating to the authorization may be the
     *  Empty Buffer
     *         Size is limited to be no larger than the nonce size supported on the TPM.
     *  @param _expiration Time when authorization will expire, measured in seconds from the time
     *         that nonceTPM was generated
     *         If expiration is non-negative, a NULL Ticket is returned. See 23.2.5.
     */
    public TPM2_PolicySecret_REQUEST(TPM_HANDLE _authHandle, TPM_HANDLE _policySession, byte[] _nonceTPM, byte[] _cpHashA, byte[] _policyRef, int _expiration)
    {
        authHandle = _authHandle;
        policySession = _policySession;
        nonceTPM = _nonceTPM;
        cpHashA = _cpHashA;
        policyRef = _policyRef;
        expiration = _expiration;
    }

    /** TpmMarshaller method */
    @Override
    public void toTpm(TpmBuffer buf)
    {
        buf.writeSizedByteBuf(nonceTPM);
        buf.writeSizedByteBuf(cpHashA);
        buf.writeSizedByteBuf(policyRef);
        buf.writeInt(expiration);
    }

    /** TpmMarshaller method */
    @Override
    public void initFromTpm(TpmBuffer buf)
    {
        nonceTPM = buf.readSizedByteBuf();
        cpHashA = buf.readSizedByteBuf();
        policyRef = buf.readSizedByteBuf();
        expiration = buf.readInt();
    }

    /** @deprecated Use {@link #toBytes()} instead
     *  @return Wire (marshaled) representation of this object
     */
    public byte[] toTpm () { return toBytes(); }

    /** Static marshaling helper
     *  @param byteBuf Wire representation of the object
     *  @return New object constructed from its wire representation
     */
    public static TPM2_PolicySecret_REQUEST fromBytes (byte[] byteBuf) 
    {
        return new TpmBuffer(byteBuf).createObj(TPM2_PolicySecret_REQUEST.class);
    }

    /** @deprecated Use {@link #fromBytes(byte[])} instead
     *  @param byteBuf Wire representation of the object
     *  @return New object constructed from its wire representation
     */
    public static TPM2_PolicySecret_REQUEST fromTpm (byte[] byteBuf)  { return fromBytes(byteBuf); }

    /** Static marshaling helper
     *  @param buf Wire representation of the object
     *  @return New object constructed from its wire representation
     */
    public static TPM2_PolicySecret_REQUEST fromTpm (TpmBuffer buf) 
    {
        return buf.createObj(TPM2_PolicySecret_REQUEST.class);
    }

    @Override
    public String toString()
    {
        TpmStructurePrinter _p = new TpmStructurePrinter("TPM2_PolicySecret_REQUEST");
        toStringInternal(_p, 1);
        _p.endStruct();
        return _p.toString();
    }

    @Override
    public void toStringInternal(TpmStructurePrinter _p, int d)
    {
        _p.add(d, "TPM_HANDLE", "authHandle", authHandle);
        _p.add(d, "TPM_HANDLE", "policySession", policySession);
        _p.add(d, "byte[]", "nonceTPM", nonceTPM);
        _p.add(d, "byte[]", "cpHashA", cpHashA);
        _p.add(d, "byte[]", "policyRef", policyRef);
        _p.add(d, "int", "expiration", expiration);
    }

    @Override
    public int numHandles() { return 2; }

    @Override
    public int numAuthHandles() { return 1; }

    @Override
    public TPM_HANDLE[] getHandles() { return new TPM_HANDLE[] {authHandle, policySession}; }

    @Override
    public SessEncInfo sessEncInfo() { return new SessEncInfo(2, 1); }
}

//<<<
