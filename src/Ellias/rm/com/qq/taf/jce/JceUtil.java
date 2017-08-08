package com.qq.taf.jce;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

public final class JceUtil
{
  private static final byte[] highDigits;
  private static final int iConstant = 37;
  private static final int iTotal = 17;
  private static final byte[] lowDigits;

  static
  {
    byte[] arrayOfByte1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
    byte[] arrayOfByte2 = new byte[256];
    byte[] arrayOfByte3 = new byte[256];
    for (int i = 0; i < 256; i++)
    {
      arrayOfByte2[i] = arrayOfByte1[(i >>> 4)];
      arrayOfByte3[i] = arrayOfByte1[(i & 0xF)];
    }
    highDigits = arrayOfByte2;
    lowDigits = arrayOfByte3;
  }

  public static int compareTo(byte paramByte1, byte paramByte2)
  {
    if (paramByte1 < paramByte2)
      return -1;
    if (paramByte1 > paramByte2)
      return 1;
    return 0;
  }

  public static int compareTo(char paramChar1, char paramChar2)
  {
    if (paramChar1 < paramChar2)
      return -1;
    if (paramChar1 > paramChar2)
      return 1;
    return 0;
  }

  public static int compareTo(double paramDouble1, double paramDouble2)
  {
    if (paramDouble1 < paramDouble2)
      return -1;
    if (paramDouble1 > paramDouble2)
      return 1;
    return 0;
  }

  public static int compareTo(float paramFloat1, float paramFloat2)
  {
    if (paramFloat1 < paramFloat2)
      return -1;
    if (paramFloat1 > paramFloat2)
      return 1;
    return 0;
  }

  public static int compareTo(int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2)
      return -1;
    if (paramInt1 > paramInt2)
      return 1;
    return 0;
  }

  public static int compareTo(long paramLong1, long paramLong2)
  {
    if (paramLong1 < paramLong2)
      return -1;
    if (paramLong1 > paramLong2)
      return 1;
    return 0;
  }

  public static <T extends Comparable<T>> int compareTo(T paramT1, T paramT2)
  {
    return paramT1.compareTo(paramT2);
  }

  public static <T extends Comparable<T>> int compareTo(List<T> paramList1, List<T> paramList2)
  {
    Iterator localIterator1 = paramList1.iterator();
    Iterator localIterator2 = paramList2.iterator();
    while ((localIterator1.hasNext()) && (localIterator2.hasNext()))
    {
      int i = ((Comparable)localIterator1.next()).compareTo(localIterator2.next());
      if (i != 0)
        return i;
    }
    return compareTo(localIterator1.hasNext(), localIterator2.hasNext());
  }

  public static int compareTo(short paramShort1, short paramShort2)
  {
    if (paramShort1 < paramShort2)
      return -1;
    if (paramShort1 > paramShort2)
      return 1;
    return 0;
  }

  public static int compareTo(boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = 1;
    int j;
    if (paramBoolean1)
    {
      j = i;
      if (!paramBoolean2)
        break label21;
    }
    while (true)
    {
      return j - i;
      j = 0;
      break;
      label21: i = 0;
    }
  }

  public static int compareTo(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int i = 0;
    for (int j = 0; (i < paramArrayOfByte1.length) && (j < paramArrayOfByte2.length); j++)
    {
      int k = compareTo(paramArrayOfByte1[i], paramArrayOfByte2[j]);
      if (k != 0)
        return k;
      i++;
    }
    return compareTo(paramArrayOfByte1.length, paramArrayOfByte2.length);
  }

  public static int compareTo(char[] paramArrayOfChar1, char[] paramArrayOfChar2)
  {
    int i = 0;
    for (int j = 0; (i < paramArrayOfChar1.length) && (j < paramArrayOfChar2.length); j++)
    {
      int k = compareTo(paramArrayOfChar1[i], paramArrayOfChar2[j]);
      if (k != 0)
        return k;
      i++;
    }
    return compareTo(paramArrayOfChar1.length, paramArrayOfChar2.length);
  }

  public static int compareTo(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    int i = 0;
    for (int j = 0; (i < paramArrayOfDouble1.length) && (j < paramArrayOfDouble2.length); j++)
    {
      int k = compareTo(paramArrayOfDouble1[i], paramArrayOfDouble2[j]);
      if (k != 0)
        return k;
      i++;
    }
    return compareTo(paramArrayOfDouble1.length, paramArrayOfDouble2.length);
  }

  public static int compareTo(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    int i = 0;
    for (int j = 0; (i < paramArrayOfFloat1.length) && (j < paramArrayOfFloat2.length); j++)
    {
      int k = compareTo(paramArrayOfFloat1[i], paramArrayOfFloat2[j]);
      if (k != 0)
        return k;
      i++;
    }
    return compareTo(paramArrayOfFloat1.length, paramArrayOfFloat2.length);
  }

  public static int compareTo(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    int i = 0;
    for (int j = 0; (i < paramArrayOfInt1.length) && (j < paramArrayOfInt2.length); j++)
    {
      int k = compareTo(paramArrayOfInt1[i], paramArrayOfInt2[j]);
      if (k != 0)
        return k;
      i++;
    }
    return compareTo(paramArrayOfInt1.length, paramArrayOfInt2.length);
  }

  public static int compareTo(long[] paramArrayOfLong1, long[] paramArrayOfLong2)
  {
    int i = 0;
    for (int j = 0; (i < paramArrayOfLong1.length) && (j < paramArrayOfLong2.length); j++)
    {
      int k = compareTo(paramArrayOfLong1[i], paramArrayOfLong2[j]);
      if (k != 0)
        return k;
      i++;
    }
    return compareTo(paramArrayOfLong1.length, paramArrayOfLong2.length);
  }

  public static <T extends Comparable<T>> int compareTo(T[] paramArrayOfT1, T[] paramArrayOfT2)
  {
    int i = 0;
    for (int j = 0; (i < paramArrayOfT1.length) && (j < paramArrayOfT2.length); j++)
    {
      int k = paramArrayOfT1[i].compareTo(paramArrayOfT2[j]);
      if (k != 0)
        return k;
      i++;
    }
    return compareTo(paramArrayOfT1.length, paramArrayOfT2.length);
  }

  public static int compareTo(short[] paramArrayOfShort1, short[] paramArrayOfShort2)
  {
    int i = 0;
    for (int j = 0; (i < paramArrayOfShort1.length) && (j < paramArrayOfShort2.length); j++)
    {
      int k = compareTo(paramArrayOfShort1[i], paramArrayOfShort2[j]);
      if (k != 0)
        return k;
      i++;
    }
    return compareTo(paramArrayOfShort1.length, paramArrayOfShort2.length);
  }

  public static int compareTo(boolean[] paramArrayOfBoolean1, boolean[] paramArrayOfBoolean2)
  {
    int i = 0;
    for (int j = 0; (i < paramArrayOfBoolean1.length) && (j < paramArrayOfBoolean2.length); j++)
    {
      int k = compareTo(paramArrayOfBoolean1[i], paramArrayOfBoolean2[j]);
      if (k != 0)
        return k;
      i++;
    }
    return compareTo(paramArrayOfBoolean1.length, paramArrayOfBoolean2.length);
  }

  public static boolean equals(byte paramByte1, byte paramByte2)
  {
    return paramByte1 == paramByte2;
  }

  public static boolean equals(char paramChar1, char paramChar2)
  {
    return paramChar1 == paramChar2;
  }

  public static boolean equals(double paramDouble1, double paramDouble2)
  {
    return paramDouble1 == paramDouble2;
  }

  public static boolean equals(float paramFloat1, float paramFloat2)
  {
    return paramFloat1 == paramFloat2;
  }

  public static boolean equals(int paramInt1, int paramInt2)
  {
    return paramInt1 == paramInt2;
  }

  public static boolean equals(long paramLong1, long paramLong2)
  {
    return paramLong1 == paramLong2;
  }

  public static boolean equals(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null)
      return paramObject1 == paramObject2;
    return paramObject1.equals(paramObject2);
  }

  public static boolean equals(short paramShort1, short paramShort2)
  {
    return paramShort1 == paramShort2;
  }

  public static boolean equals(boolean paramBoolean1, boolean paramBoolean2)
  {
    return paramBoolean1 == paramBoolean2;
  }

  public static String getHexdump(ByteBuffer paramByteBuffer)
  {
    int i = paramByteBuffer.remaining();
    if (i == 0)
      return "empty";
    StringBuffer localStringBuffer = new StringBuffer(-1 + 3 * paramByteBuffer.remaining());
    int j = paramByteBuffer.position();
    int k = 0xFF & paramByteBuffer.get();
    localStringBuffer.append((char)highDigits[k]);
    localStringBuffer.append((char)lowDigits[k]);
    for (int m = i - 1; m > 0; m--)
    {
      localStringBuffer.append(' ');
      int n = 0xFF & paramByteBuffer.get();
      localStringBuffer.append((char)highDigits[n]);
      localStringBuffer.append((char)lowDigits[n]);
    }
    paramByteBuffer.position(j);
    return localStringBuffer.toString();
  }

  public static String getHexdump(byte[] paramArrayOfByte)
  {
    return getHexdump(ByteBuffer.wrap(paramArrayOfByte));
  }

  public static byte[] getJceBufArray(ByteBuffer paramByteBuffer)
  {
    byte[] arrayOfByte = new byte[paramByteBuffer.position()];
    System.arraycopy(paramByteBuffer.array(), 0, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }

  public static int hashCode(byte paramByte)
  {
    return paramByte + 629;
  }

  public static int hashCode(char paramChar)
  {
    return paramChar + 'ɵ';
  }

  public static int hashCode(double paramDouble)
  {
    return hashCode(Double.doubleToLongBits(paramDouble));
  }

  public static int hashCode(float paramFloat)
  {
    return 629 + Float.floatToIntBits(paramFloat);
  }

  public static int hashCode(int paramInt)
  {
    return paramInt + 629;
  }

  public static int hashCode(long paramLong)
  {
    return 629 + (int)(paramLong ^ paramLong >> 32);
  }

  public static int hashCode(Object paramObject)
  {
    if (paramObject == null)
      return 629;
    if (paramObject.getClass().isArray())
    {
      if ((paramObject instanceof long[]))
        return hashCode((long[])(long[])paramObject);
      if ((paramObject instanceof int[]))
        return hashCode((int[])(int[])paramObject);
      if ((paramObject instanceof short[]))
        return hashCode((short[])(short[])paramObject);
      if ((paramObject instanceof char[]))
        return hashCode((char[])(char[])paramObject);
      if ((paramObject instanceof byte[]))
        return hashCode((byte[])(byte[])paramObject);
      if ((paramObject instanceof double[]))
        return hashCode((double[])(double[])paramObject);
      if ((paramObject instanceof float[]))
        return hashCode((float[])(float[])paramObject);
      if ((paramObject instanceof boolean[]))
        return hashCode((boolean[])(boolean[])paramObject);
      if ((paramObject instanceof JceStruct[]))
        return hashCode((JceStruct[])(JceStruct[])paramObject);
      return hashCode((Object[])(Object[])paramObject);
    }
    if ((paramObject instanceof JceStruct))
      return paramObject.hashCode();
    return 629 + paramObject.hashCode();
  }

  public static int hashCode(short paramShort)
  {
    return paramShort + 629;
  }

  public static int hashCode(boolean paramBoolean)
  {
    if (paramBoolean);
    for (int i = 0; ; i = 1)
      return i + 629;
  }

  public static int hashCode(byte[] paramArrayOfByte)
  {
    int i;
    if (paramArrayOfByte == null)
      i = 629;
    while (true)
    {
      return i;
      i = 17;
      for (int j = 0; j < paramArrayOfByte.length; j++)
        i = i * 37 + paramArrayOfByte[j];
    }
  }

  public static int hashCode(char[] paramArrayOfChar)
  {
    int i;
    if (paramArrayOfChar == null)
      i = 629;
    while (true)
    {
      return i;
      i = 17;
      for (int j = 0; j < paramArrayOfChar.length; j++)
        i = i * 37 + paramArrayOfChar[j];
    }
  }

  public static int hashCode(double[] paramArrayOfDouble)
  {
    int i;
    if (paramArrayOfDouble == null)
      i = 629;
    while (true)
    {
      return i;
      i = 17;
      for (int j = 0; j < paramArrayOfDouble.length; j++)
        i = i * 37 + (int)(Double.doubleToLongBits(paramArrayOfDouble[j]) ^ Double.doubleToLongBits(paramArrayOfDouble[j]) >> 32);
    }
  }

  public static int hashCode(float[] paramArrayOfFloat)
  {
    int i;
    if (paramArrayOfFloat == null)
      i = 629;
    while (true)
    {
      return i;
      i = 17;
      for (int j = 0; j < paramArrayOfFloat.length; j++)
        i = i * 37 + Float.floatToIntBits(paramArrayOfFloat[j]);
    }
  }

  public static int hashCode(int[] paramArrayOfInt)
  {
    int i;
    if (paramArrayOfInt == null)
      i = 629;
    while (true)
    {
      return i;
      i = 17;
      for (int j = 0; j < paramArrayOfInt.length; j++)
        i = i * 37 + paramArrayOfInt[j];
    }
  }

  public static int hashCode(long[] paramArrayOfLong)
  {
    int i;
    if (paramArrayOfLong == null)
      i = 629;
    while (true)
    {
      return i;
      i = 17;
      for (int j = 0; j < paramArrayOfLong.length; j++)
        i = i * 37 + (int)(paramArrayOfLong[j] ^ paramArrayOfLong[j] >> 32);
    }
  }

  public static int hashCode(JceStruct[] paramArrayOfJceStruct)
  {
    int i;
    if (paramArrayOfJceStruct == null)
      i = 629;
    while (true)
    {
      return i;
      i = 17;
      for (int j = 0; j < paramArrayOfJceStruct.length; j++)
        i = i * 37 + paramArrayOfJceStruct[j].hashCode();
    }
  }

  public static int hashCode(short[] paramArrayOfShort)
  {
    int i;
    if (paramArrayOfShort == null)
      i = 629;
    while (true)
    {
      return i;
      i = 17;
      for (int j = 0; j < paramArrayOfShort.length; j++)
        i = i * 37 + paramArrayOfShort[j];
    }
  }

  public static int hashCode(boolean[] paramArrayOfBoolean)
  {
    if (paramArrayOfBoolean == null)
    {
      i = 629;
      return i;
    }
    int i = 17;
    int j = 0;
    label15: int k;
    if (j < paramArrayOfBoolean.length)
    {
      k = i * 37;
      if (paramArrayOfBoolean[j] == 0)
        break label46;
    }
    label46: for (int m = 0; ; m = 1)
    {
      i = k + m;
      j++;
      break label15;
      break;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.qq.taf.jce.JceUtil
 * JD-Core Version:    0.6.0
 */