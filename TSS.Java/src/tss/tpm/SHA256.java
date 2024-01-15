package tss.tpm;

import tss.*;
import java.util.*;

// -----------This is an auto-generated file: do not edit

//>>>

/** Table 14 Defines for SHA256 Hash Values */
public final class SHA256 extends TpmEnum<SHA256>
{
    /** Values from enum _N are only intended to be used in case labels of a switch statement
     *  using the result of this.asEnum() method as the switch condition. However, their Java
     *  names are identical to those of the constants defined in this class further below, so
     *  for any other usage just prepend them with the SHA256. qualifier.
     */
    public enum _N {
        /** Size of digest */
        DIGEST_SIZE,

        /** Size of hash block */
        BLOCK_SIZE
    }

    private static ValueMap<SHA256> _ValueMap = new ValueMap<>();

    /** These definitions provide mapping of the Java enum constants to their TPM integer values */
    public static final SHA256
        DIGEST_SIZE = new SHA256(32, _N.DIGEST_SIZE),
        BLOCK_SIZE = new SHA256(64, _N.BLOCK_SIZE);

    public SHA256 () { super(0, _ValueMap); }
    public SHA256 (int value) { super(value, _ValueMap); }
    public static SHA256 fromInt (int value) { return TpmEnum.fromInt(value, _ValueMap, SHA256.class); }
    public static SHA256 fromTpm (byte[] buf) { return TpmEnum.fromTpm(buf, _ValueMap, SHA256.class); }
    public static SHA256 fromTpm (TpmBuffer buf) { return TpmEnum.fromTpm(buf, _ValueMap, SHA256.class); }
    public SHA256._N asEnum() { return (SHA256._N)NameAsEnum; }
    public static Collection<SHA256> values() { return _ValueMap.values(); }
    private SHA256 (int value, _N nameAsEnum) { super(value, nameAsEnum, _ValueMap); }
    private SHA256 (int value, _N nameAsEnum, boolean noConvFromInt) { super(value, nameAsEnum, null); }

    @Override
    protected int wireSize() { return 4; }
}

//<<<
