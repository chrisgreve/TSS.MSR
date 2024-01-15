package tss;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.TreeMap;

public abstract class TpmEnum<T extends TpmEnum<T>> implements TpmMarshaller
{
    protected  int      Value;
    protected  Enum<?>  NameAsEnum;
    protected  String   Name;
    
    /** @return The size of the enumeration value used to marshal it to the TPM representation */
    protected abstract int wireSize();
    
    /** Map used for conversion from an int value to the corresponding TpmEnum derived type */
    @SuppressWarnings("serial")
    protected static class ValueMap<T extends TpmEnum<T>> extends TreeMap<Integer, T>
    {
        public ValueMap() {}
    }
    
    @SuppressWarnings("unchecked")
    protected TpmEnum (int value, Enum<?> nameAsEnum, ValueMap<T> values)
    {
        Value = value;
        if (nameAsEnum != null)
        {
            Name = nameAsEnum.toString();
            NameAsEnum = nameAsEnum;
            // Some values may need to be skipped to avoid conflicts caused by the values used by TPM 2.0 Spec  
            if (values != null)
                values.put(value, (T)this);
        }
    }
    
    protected TpmEnum (int value, ValueMap<T> values)
    {
        Value = value;
        if (values.containsKey(value))
        {
            T v = values.get(value);
            Name = v.Name;
            NameAsEnum = v.NameAsEnum;
        }
        else
            Name = Integer.toHexString(value);
    }
    
    protected static <T extends TpmEnum<T>>
    T fromInt (int value, ValueMap<T> values, Class<T> cls)
    {
        if (values.containsKey(value))
            return (T)values.get(value);
        Constructor<T> ctor = null;
        try {
            ctor = cls.getConstructor(int.class);
        } catch (NoSuchMethodException | SecurityException ignored ) {}
        if (ctor == null)
            return null;
        T newEnum = null;
        try {
            newEnum = ctor.newInstance(value);
        } catch (InstantiationException | IllegalAccessException |
                 IllegalArgumentException | InvocationTargetException ignored ) {}
        return newEnum;
    }

    protected static <T extends TpmEnum<T>>
    T fromTpm(byte[] buf, ValueMap<T> values, Class<T> cls)
    {
        int value = Helpers.netToHost(buf);
        return fromInt(value, values, cls);
    }
    
    protected static <T extends TpmEnum<T>>
    T fromTpm(TpmBuffer buf, ValueMap<T> values, Class<T> cls)
    {
        int value = (int)buf.readNum(values.firstEntry().getValue().wireSize());
        return fromInt(value, values, cls);
    }
    
    public int toInt()
    {
        return Value;
    }
    
    @Override
    public void toTpm(TpmBuffer buf) {
        buf.writeNum(Value, wireSize());
    }

    @Override
    public void initFromTpm(TpmBuffer buf)
    {
        Value = (int)buf.readNum(wireSize());
    }

    public byte[] toBytes()
    {
        TpmBuffer buf = new TpmBuffer(wireSize());
        buf.writeNum(Value, wireSize());
        return buf.trim();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) 
            return true;
        else if (obj == null) 
            return false;
        else if (getClass().isInstance(obj)) 
        {
            return Value == ((TpmEnum<?>)obj).Value;
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        return Value;
    }

    @Override
    public String toString()
    {
        return "{" + name() + "}";
    }
    
    /**
     * @return Short name of the enumeration member (no class qualifier and no numerical value)
     */
    public String name()
    {
        return Name;
    }

    /**
     * @return Complete string representation of the enumeration member (with class qualifier and numerical value in decimal and hex forms)
     */
    public String toStringVerbose()
    {
        return getClass().getSimpleName() + "." +  Name + 
                    " (" + Integer.toString(Value) + ", 0x" + Integer.toHexString(Value) + ")";
    }
}

