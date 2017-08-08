package com.tencent.beacon.e;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class b
{
  private ByteBuffer a;
  private String b = "GBK";

  static
  {
    new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
  }

  public b()
  {
    this(128);
  }

  public b(int paramInt)
  {
    this.a = ByteBuffer.allocate(paramInt);
  }

  private void a(int paramInt)
  {
    int i;
    if (this.a.remaining() < paramInt)
      i = paramInt + this.a.capacity() << 1;
    try
    {
      ByteBuffer localByteBuffer = ByteBuffer.allocate(i);
      localByteBuffer.put(this.a.array(), 0, this.a.position());
      this.a = localByteBuffer;
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
    throw localIllegalArgumentException;
  }

  private void b(byte paramByte, int paramInt)
  {
    if (paramInt < 15)
    {
      byte b2 = (byte)(paramByte | paramInt << 4);
      this.a.put(b2);
      return;
    }
    if (paramInt < 256)
    {
      byte b1 = (byte)(paramByte | 0xF0);
      this.a.put(b1);
      this.a.put((byte)paramInt);
      return;
    }
    throw new RuntimeException("tag is too large: " + paramInt);
  }

  public final int a(String paramString)
  {
    this.b = paramString;
    return 0;
  }

  public final ByteBuffer a()
  {
    return this.a;
  }

  public final void a(byte paramByte, int paramInt)
  {
    a(3);
    if (paramByte == 0)
    {
      b(12, paramInt);
      return;
    }
    b(0, paramInt);
    this.a.put(paramByte);
  }

  public final void a(int paramInt1, int paramInt2)
  {
    a(6);
    if ((paramInt1 >= -32768) && (paramInt1 <= 32767))
    {
      a((short)paramInt1, paramInt2);
      return;
    }
    b(2, paramInt2);
    this.a.putInt(paramInt1);
  }

  public final void a(long paramLong, int paramInt)
  {
    a(10);
    if ((paramLong >= -2147483648L) && (paramLong <= 2147483647L))
    {
      a((int)paramLong, paramInt);
      return;
    }
    b(3, paramInt);
    this.a.putLong(paramLong);
  }

  public final void a(c paramc, int paramInt)
  {
    a(2);
    b(10, paramInt);
    paramc.a(this);
    a(2);
    b(11, 0);
  }

  public final void a(Object paramObject, int paramInt)
  {
    int i = 1;
    if ((paramObject instanceof Byte))
      a(((Byte)paramObject).byteValue(), paramInt);
    while (true)
    {
      return;
      if ((paramObject instanceof Boolean))
      {
        if (((Boolean)paramObject).booleanValue());
        while (true)
        {
          a((byte)i, paramInt);
          return;
          i = 0;
        }
      }
      if ((paramObject instanceof Short))
      {
        a(((Short)paramObject).shortValue(), paramInt);
        return;
      }
      if ((paramObject instanceof Integer))
      {
        a(((Integer)paramObject).intValue(), paramInt);
        return;
      }
      if ((paramObject instanceof Long))
      {
        a(((Long)paramObject).longValue(), paramInt);
        return;
      }
      if ((paramObject instanceof Float))
      {
        float f2 = ((Float)paramObject).floatValue();
        a(6);
        b(4, paramInt);
        this.a.putFloat(f2);
        return;
      }
      if ((paramObject instanceof Double))
      {
        double d2 = ((Double)paramObject).doubleValue();
        a(10);
        b(5, paramInt);
        this.a.putDouble(d2);
        return;
      }
      if ((paramObject instanceof String))
      {
        a((String)paramObject, paramInt);
        return;
      }
      if ((paramObject instanceof Map))
      {
        a((Map)paramObject, paramInt);
        return;
      }
      if ((paramObject instanceof List))
      {
        a((List)paramObject, paramInt);
        return;
      }
      if ((paramObject instanceof c))
      {
        c localc = (c)paramObject;
        a(2);
        b(10, paramInt);
        localc.a(this);
        a(2);
        b(11, 0);
        return;
      }
      if ((paramObject instanceof byte[]))
      {
        a((byte[])paramObject, paramInt);
        return;
      }
      if ((paramObject instanceof boolean[]))
      {
        boolean[] arrayOfBoolean = (boolean[])paramObject;
        a(8);
        b(9, paramInt);
        a(arrayOfBoolean.length, 0);
        int i9 = arrayOfBoolean.length;
        int i10 = 0;
        label344: if (i10 < i9)
          if (arrayOfBoolean[i10] == 0)
            break label376;
        label376: for (int i11 = i; ; i11 = 0)
        {
          a((byte)i11, 0);
          i10++;
          break label344;
          break;
        }
      }
      if ((paramObject instanceof short[]))
      {
        short[] arrayOfShort = (short[])paramObject;
        a(8);
        b(9, paramInt);
        a(arrayOfShort.length, 0);
        int i7 = arrayOfShort.length;
        for (int i8 = 0; i8 < i7; i8++)
          a(arrayOfShort[i8], 0);
        continue;
      }
      if ((paramObject instanceof int[]))
      {
        int[] arrayOfInt = (int[])paramObject;
        a(8);
        b(9, paramInt);
        a(arrayOfInt.length, 0);
        int i5 = arrayOfInt.length;
        for (int i6 = 0; i6 < i5; i6++)
          a(arrayOfInt[i6], 0);
        continue;
      }
      if ((paramObject instanceof long[]))
      {
        long[] arrayOfLong = (long[])paramObject;
        a(8);
        b(9, paramInt);
        a(arrayOfLong.length, 0);
        int i3 = arrayOfLong.length;
        for (int i4 = 0; i4 < i3; i4++)
          a(arrayOfLong[i4], 0);
        continue;
      }
      if ((paramObject instanceof float[]))
      {
        float[] arrayOfFloat = (float[])paramObject;
        a(8);
        b(9, paramInt);
        a(arrayOfFloat.length, 0);
        int i1 = arrayOfFloat.length;
        for (int i2 = 0; i2 < i1; i2++)
        {
          float f1 = arrayOfFloat[i2];
          a(6);
          b(4, 0);
          this.a.putFloat(f1);
        }
        continue;
      }
      if ((paramObject instanceof double[]))
      {
        double[] arrayOfDouble = (double[])paramObject;
        a(8);
        b(9, paramInt);
        a(arrayOfDouble.length, 0);
        int m = arrayOfDouble.length;
        for (int n = 0; n < m; n++)
        {
          double d1 = arrayOfDouble[n];
          a(10);
          b(5, 0);
          this.a.putDouble(d1);
        }
        continue;
      }
      if (!paramObject.getClass().isArray())
        break;
      Object[] arrayOfObject = (Object[])paramObject;
      a(8);
      b(9, paramInt);
      a(arrayOfObject.length, 0);
      int j = arrayOfObject.length;
      for (int k = 0; k < j; k++)
        a(arrayOfObject[k], 0);
    }
    if ((paramObject instanceof Collection))
    {
      a((Collection)paramObject, paramInt);
      return;
    }
    throw new RuntimeException("write object error: unsupport type. " + paramObject.getClass());
  }

  public final void a(String paramString, int paramInt)
  {
    try
    {
      byte[] arrayOfByte2 = paramString.getBytes(this.b);
      arrayOfByte1 = arrayOfByte2;
      a(10 + arrayOfByte1.length);
      if (arrayOfByte1.length > 255)
      {
        b(7, paramInt);
        this.a.putInt(arrayOfByte1.length);
        this.a.put(arrayOfByte1);
        return;
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      byte[] arrayOfByte1;
      while (true)
        arrayOfByte1 = paramString.getBytes();
      b(6, paramInt);
      this.a.put((byte)arrayOfByte1.length);
      this.a.put(arrayOfByte1);
    }
  }

  public final <T> void a(Collection<T> paramCollection, int paramInt)
  {
    a(8);
    b(9, paramInt);
    int i;
    Iterator localIterator;
    if (paramCollection == null)
    {
      i = 0;
      a(i, 0);
      if (paramCollection != null)
        localIterator = paramCollection.iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return;
        i = paramCollection.size();
        break;
      }
      a((Object)localIterator.next(), 0);
    }
  }

  public final <K, V> void a(Map<K, V> paramMap, int paramInt)
  {
    a(8);
    b(8, paramInt);
    int i;
    Iterator localIterator;
    if (paramMap == null)
    {
      i = 0;
      a(i, 0);
      if (paramMap != null)
        localIterator = paramMap.entrySet().iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return;
        i = paramMap.size();
        break;
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      a(localEntry.getKey(), 0);
      a(localEntry.getValue(), 1);
    }
  }

  public final void a(short paramShort, int paramInt)
  {
    a(4);
    if ((paramShort >= -128) && (paramShort <= 127))
    {
      a((byte)paramShort, paramInt);
      return;
    }
    b(1, paramInt);
    this.a.putShort(paramShort);
  }

  public final void a(boolean paramBoolean, int paramInt)
  {
    if (paramBoolean);
    for (int i = 1; ; i = 0)
    {
      a((byte)i, paramInt);
      return;
    }
  }

  public final void a(byte[] paramArrayOfByte, int paramInt)
  {
    a(8 + paramArrayOfByte.length);
    b(13, paramInt);
    b(0, 0);
    a(paramArrayOfByte.length, 0);
    this.a.put(paramArrayOfByte);
  }

  public final byte[] b()
  {
    byte[] arrayOfByte = new byte[this.a.position()];
    System.arraycopy(this.a.array(), 0, arrayOfByte, 0, this.a.position());
    return arrayOfByte;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.e.b
 * JD-Core Version:    0.6.0
 */