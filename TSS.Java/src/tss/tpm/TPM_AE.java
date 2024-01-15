package tss.tpm;

import tss.*;
import java.util.*;

// -----------This is an auto-generated file: do not edit

//>>>

/** These constants are the TCG-defined error values returned by an AC. */
public final class TPM_AE extends TpmEnum<TPM_AE>
{
    /** Values from enum _N are only intended to be used in case labels of a switch statement
     *  using the result of this.asEnum() method as the switch condition. However, their Java
     *  names are identical to those of the constants defined in this class further below, so
     *  for any other usage just prepend them with the TPM_AE. qualifier.
     */
    public enum _N {
        /** In a command, a non-specific request for AC information; in a response, indicates that
         *  outputData is not meaningful
         */
        NONE
    }

    private static final ValueMap<TPM_AE> _ValueMap = new ValueMap<>();

    /** These definitions provide mapping of the Java enum constants to their TPM integer values */
    public static final TPM_AE
        NONE = new TPM_AE(0x00000000, _N.NONE);

    public TPM_AE () { super(0, _ValueMap); }
    public TPM_AE (int value) { super(value, _ValueMap); }
    public static TPM_AE fromInt (int value) { return TpmEnum.fromInt(value, _ValueMap, TPM_AE.class); }
    public static TPM_AE fromTpm (byte[] buf) { return TpmEnum.fromTpm(buf, _ValueMap, TPM_AE.class); }
    public static TPM_AE fromTpm (TpmBuffer buf) { return TpmEnum.fromTpm(buf, _ValueMap, TPM_AE.class); }
    public TPM_AE._N asEnum() { return (TPM_AE._N)NameAsEnum; }
    public static Collection<TPM_AE> values() { return _ValueMap.values(); }
    private TPM_AE (int value, _N nameAsEnum) { super(value, nameAsEnum, _ValueMap); }
    private TPM_AE (int value, _N nameAsEnum, boolean noConvFromInt) { super(value, nameAsEnum, null); }

    @Override
    protected int wireSize() { return 4; }
}

//<<<
