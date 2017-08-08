package com.tencent.beacon.e;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class a
{
  private ByteBuffer a;
  private String b = "GBK";

  public a()
  {
  }

  public a(byte[] paramArrayOfByte)
  {
    this.a = ByteBuffer.wrap(paramArrayOfByte);
  }

  public a(byte[] paramArrayOfByte, int paramInt)
  {
    this.a = ByteBuffer.wrap(paramArrayOfByte);
    this.a.position(4);
  }

  private double a(double paramDouble, int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      switch (locala.a)
      {
      default:
        throw new RuntimeException("type mismatch.");
      case 12:
        paramDouble = 0.0D;
      case 4:
      case 5:
      }
    }
    do
    {
      return paramDouble;
      return this.a.getFloat();
      return this.a.getDouble();
    }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  private float a(float paramFloat, int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      switch (locala.a)
      {
      default:
        throw new RuntimeException("type mismatch.");
      case 12:
        paramFloat = 0.0F;
      case 4:
      }
    }
    do
    {
      return paramFloat;
      return this.a.getFloat();
    }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  private static int a(a parama, ByteBuffer paramByteBuffer)
  {
    int i = paramByteBuffer.get();
    parama.a = (byte)(i & 0xF);
    parama.b = ((i & 0xF0) >> 4);
    if (parama.b == 15)
    {
      parama.b = (0xFF & paramByteBuffer.get());
      return 2;
    }
    return 1;
  }

  private <K, V> Map<K, V> a(Map<K, V> paramMap1, Map<K, V> paramMap2, int paramInt, boolean paramBoolean)
  {
    if ((paramMap2 == null) || (paramMap2.isEmpty()))
      paramMap1 = new HashMap();
    do
      while (true)
      {
        return paramMap1;
        Map.Entry localEntry = (Map.Entry)paramMap2.entrySet().iterator().next();
        Object localObject1 = localEntry.getKey();
        Object localObject2 = localEntry.getValue();
        if (!a(paramInt))
          break;
        a locala = new a();
        a(locala, this.a);
        switch (locala.a)
        {
        default:
          throw new RuntimeException("type mismatch.");
        case 8:
        }
        int i = a(0, 0, true);
        if (i < 0)
          throw new RuntimeException("size invalid: " + i);
        for (int j = 0; j < i; j++)
          paramMap1.put(a(localObject1, 0, true), a(localObject2, 1, true));
      }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  private void a()
  {
    a locala = new a();
    do
    {
      a(locala, this.a);
      a(locala.a);
    }
    while (locala.a != 11);
  }

  private void a(byte paramByte)
  {
    int i = 0;
    switch (paramByte)
    {
    default:
      throw new RuntimeException("invalid type.");
    case 0:
      this.a.position(1 + this.a.position());
    case 11:
    case 12:
      return;
    case 1:
      this.a.position(2 + this.a.position());
      return;
    case 2:
      this.a.position(4 + this.a.position());
      return;
    case 3:
      this.a.position(8 + this.a.position());
      return;
    case 4:
      this.a.position(4 + this.a.position());
      return;
    case 5:
      this.a.position(8 + this.a.position());
      return;
    case 6:
      int i1 = this.a.get();
      if (i1 < 0)
        i1 += 256;
      this.a.position(i1 + this.a.position());
      return;
    case 7:
      int n = this.a.getInt();
      this.a.position(n + this.a.position());
      return;
    case 8:
      int m = a(0, 0, true);
      while (i < m << 1)
      {
        a locala3 = new a();
        a(locala3, this.a);
        a(locala3.a);
        i++;
      }
    case 9:
      int k = a(0, 0, true);
      while (i < k)
      {
        a locala2 = new a();
        a(locala2, this.a);
        a(locala2.a);
        i++;
      }
    case 13:
      a locala1 = new a();
      a(locala1, this.a);
      if (locala1.a != 0)
        throw new RuntimeException("skipField with invalid type, type value: " + paramByte + ", " + locala1.a);
      int j = a(0, 0, true);
      this.a.position(j + this.a.position());
      return;
    case 10:
    }
    a();
  }

  private boolean a(int paramInt)
  {
    try
    {
      a locala = new a();
      while (true)
      {
        int i = a(locala, this.a.duplicate());
        if (locala.a == 11)
          return false;
        if (paramInt <= locala.b)
        {
          if (paramInt != locala.b)
            break;
          return true;
        }
        this.a.position(i + this.a.position());
        a(locala.a);
      }
    }
    catch (BufferUnderflowException localBufferUnderflowException)
    {
      return false;
    }
    catch (RuntimeException localRuntimeException)
    {
    }
    return false;
  }

  private <T> T[] b(T paramT, int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      switch (locala.a)
      {
      default:
        throw new RuntimeException("type mismatch.");
      case 9:
      }
      int i = a(0, 0, true);
      if (i < 0)
        throw new RuntimeException("size invalid: " + i);
      Object[] arrayOfObject = (Object[])Array.newInstance(paramT.getClass(), i);
      for (int j = 0; ; j++)
      {
        if (j >= i)
          return arrayOfObject;
        arrayOfObject[j] = a(paramT, 0, true);
      }
    }
    if (paramBoolean)
      throw new RuntimeException("require field not exist.");
    return null;
  }

  private boolean[] d(int paramInt, boolean paramBoolean)
  {
    boolean[] arrayOfBoolean1;
    int j;
    boolean[] arrayOfBoolean2;
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      switch (locala.a)
      {
      default:
        throw new RuntimeException("type mismatch.");
      case 9:
      }
      int i = a(0, 0, true);
      if (i < 0)
        throw new RuntimeException("size invalid: " + i);
      arrayOfBoolean1 = new boolean[i];
      j = 0;
      if (j >= i)
        arrayOfBoolean2 = arrayOfBoolean1;
    }
    do
    {
      return arrayOfBoolean2;
      if (a(0, 0, true) != 0);
      for (int k = 1; ; k = 0)
      {
        arrayOfBoolean1[j] = k;
        j++;
        break;
      }
      arrayOfBoolean2 = null;
    }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  private short[] e(int paramInt, boolean paramBoolean)
  {
    short[] arrayOfShort;
    int j;
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      switch (locala.a)
      {
      default:
        throw new RuntimeException("type mismatch.");
      case 9:
      }
      int i = a(0, 0, true);
      if (i < 0)
        throw new RuntimeException("size invalid: " + i);
      arrayOfShort = new short[i];
      j = 0;
      if (j < i);
    }
    do
    {
      return arrayOfShort;
      arrayOfShort[j] = a(arrayOfShort[0], 0, true);
      j++;
      break;
      arrayOfShort = null;
    }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  private int[] f(int paramInt, boolean paramBoolean)
  {
    int[] arrayOfInt;
    int j;
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      switch (locala.a)
      {
      default:
        throw new RuntimeException("type mismatch.");
      case 9:
      }
      int i = a(0, 0, true);
      if (i < 0)
        throw new RuntimeException("size invalid: " + i);
      arrayOfInt = new int[i];
      j = 0;
      if (j < i);
    }
    do
    {
      return arrayOfInt;
      arrayOfInt[j] = a(arrayOfInt[0], 0, true);
      j++;
      break;
      arrayOfInt = null;
    }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  private long[] g(int paramInt, boolean paramBoolean)
  {
    long[] arrayOfLong;
    int j;
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      switch (locala.a)
      {
      default:
        throw new RuntimeException("type mismatch.");
      case 9:
      }
      int i = a(0, 0, true);
      if (i < 0)
        throw new RuntimeException("size invalid: " + i);
      arrayOfLong = new long[i];
      j = 0;
      if (j < i);
    }
    do
    {
      return arrayOfLong;
      arrayOfLong[j] = a(arrayOfLong[0], 0, true);
      j++;
      break;
      arrayOfLong = null;
    }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  private float[] h(int paramInt, boolean paramBoolean)
  {
    float[] arrayOfFloat;
    int j;
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      switch (locala.a)
      {
      default:
        throw new RuntimeException("type mismatch.");
      case 9:
      }
      int i = a(0, 0, true);
      if (i < 0)
        throw new RuntimeException("size invalid: " + i);
      arrayOfFloat = new float[i];
      j = 0;
      if (j < i);
    }
    do
    {
      return arrayOfFloat;
      arrayOfFloat[j] = a(arrayOfFloat[0], 0, true);
      j++;
      break;
      arrayOfFloat = null;
    }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  private double[] i(int paramInt, boolean paramBoolean)
  {
    double[] arrayOfDouble;
    int j;
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      switch (locala.a)
      {
      default:
        throw new RuntimeException("type mismatch.");
      case 9:
      }
      int i = a(0, 0, true);
      if (i < 0)
        throw new RuntimeException("size invalid: " + i);
      arrayOfDouble = new double[i];
      j = 0;
      if (j < i);
    }
    do
    {
      return arrayOfDouble;
      arrayOfDouble[j] = a(arrayOfDouble[0], 0, true);
      j++;
      break;
      arrayOfDouble = null;
    }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  public final byte a(byte paramByte, int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      switch (locala.a)
      {
      default:
        throw new RuntimeException("type mismatch.");
      case 12:
        paramByte = 0;
      case 0:
      }
    }
    do
    {
      return paramByte;
      return this.a.get();
    }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  public final int a(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (a(paramInt2))
    {
      a locala = new a();
      a(locala, this.a);
      switch (locala.a)
      {
      default:
        throw new RuntimeException("type mismatch.");
      case 12:
        paramInt1 = 0;
      case 0:
      case 1:
      case 2:
      }
    }
    do
    {
      return paramInt1;
      return this.a.get();
      return this.a.getShort();
      return this.a.getInt();
    }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  public final int a(String paramString)
  {
    this.b = paramString;
    return 0;
  }

  public final long a(long paramLong, int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      switch (locala.a)
      {
      default:
        throw new RuntimeException("type mismatch.");
      case 12:
        paramLong = 0L;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    do
    {
      return paramLong;
      return this.a.get();
      return this.a.getShort();
      return this.a.getInt();
      return this.a.getLong();
    }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  public final c a(c paramc, int paramInt, boolean paramBoolean)
  {
    c localc;
    if (a(paramInt))
    {
      try
      {
        localc = (c)paramc.getClass().newInstance();
        a locala = new a();
        a(locala, this.a);
        if (locala.a != 10)
          throw new RuntimeException("type mismatch.");
      }
      catch (Exception localException)
      {
        throw new RuntimeException(localException.getMessage());
      }
      localc.a(this);
      a();
    }
    do
    {
      return localc;
      localc = null;
    }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  public final <T> Object a(T paramT, int paramInt, boolean paramBoolean)
  {
    int i = 0;
    if ((paramT instanceof Byte))
      return Byte.valueOf(a(0, paramInt, paramBoolean));
    if ((paramT instanceof Boolean))
    {
      int j = a(0, paramInt, paramBoolean);
      boolean bool = false;
      if (j != 0)
        bool = true;
      return Boolean.valueOf(bool);
    }
    if ((paramT instanceof Short))
      return Short.valueOf(a(0, paramInt, paramBoolean));
    if ((paramT instanceof Integer))
      return Integer.valueOf(a(0, paramInt, paramBoolean));
    if ((paramT instanceof Long))
      return Long.valueOf(a(0L, paramInt, paramBoolean));
    if ((paramT instanceof Float))
      return Float.valueOf(a(0.0F, paramInt, paramBoolean));
    if ((paramT instanceof Double))
      return Double.valueOf(a(0.0D, paramInt, paramBoolean));
    if ((paramT instanceof String))
      return b(paramInt, paramBoolean);
    if ((paramT instanceof Map))
    {
      Map localMap = (Map)paramT;
      return (HashMap)a(new HashMap(), localMap, paramInt, paramBoolean);
    }
    if ((paramT instanceof List))
    {
      List localList = (List)paramT;
      if ((localList == null) || (localList.isEmpty()))
        return new ArrayList();
      Object[] arrayOfObject2 = b(localList.get(0), paramInt, paramBoolean);
      if (arrayOfObject2 == null)
        return null;
      ArrayList localArrayList = new ArrayList();
      while (true)
      {
        if (i >= arrayOfObject2.length)
          return localArrayList;
        localArrayList.add(arrayOfObject2[i]);
        i++;
      }
    }
    if ((paramT instanceof c))
      return a((c)paramT, paramInt, paramBoolean);
    if (paramT.getClass().isArray())
    {
      if (((paramT instanceof byte[])) || ((paramT instanceof Byte[])))
        return c(paramInt, paramBoolean);
      if ((paramT instanceof boolean[]))
        return d(paramInt, paramBoolean);
      if ((paramT instanceof short[]))
        return e(paramInt, paramBoolean);
      if ((paramT instanceof int[]))
        return f(paramInt, paramBoolean);
      if ((paramT instanceof long[]))
        return g(paramInt, paramBoolean);
      if ((paramT instanceof float[]))
        return h(paramInt, paramBoolean);
      if ((paramT instanceof double[]))
        return i(paramInt, paramBoolean);
      Object[] arrayOfObject1 = (Object[])paramT;
      if ((arrayOfObject1 == null) || (arrayOfObject1.length == 0))
        throw new RuntimeException("unable to get type of key and value.");
      return b(arrayOfObject1[0], paramInt, paramBoolean);
    }
    throw new RuntimeException("read object error: unsupport type.");
  }

  public final <K, V> HashMap<K, V> a(Map<K, V> paramMap, int paramInt, boolean paramBoolean)
  {
    return (HashMap)a(new HashMap(), paramMap, paramInt, paramBoolean);
  }

  public final short a(short paramShort, int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      switch (locala.a)
      {
      default:
        throw new RuntimeException("type mismatch.");
      case 12:
        paramShort = 0;
      case 0:
      case 1:
      }
    }
    do
    {
      return paramShort;
      return (short)this.a.get();
      return this.a.getShort();
    }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  public final void a(byte[] paramArrayOfByte)
  {
    this.a = ByteBuffer.wrap(paramArrayOfByte);
  }

  public final boolean a(int paramInt, boolean paramBoolean)
  {
    int i = a(0, paramInt, paramBoolean);
    int j = 0;
    if (i != 0)
      j = 1;
    return j;
  }

  public final String b(int paramInt, boolean paramBoolean)
  {
    byte[] arrayOfByte2;
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      switch (locala.a)
      {
      default:
        throw new RuntimeException("type mismatch.");
      case 6:
        int j = this.a.get();
        if (j < 0)
          j += 256;
        arrayOfByte2 = new byte[j];
        this.a.get(arrayOfByte2);
      case 7:
      }
    }
    do
    {
      try
      {
        str2 = new String(arrayOfByte2, this.b);
        return str2;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException2)
      {
        return new String(arrayOfByte2);
      }
      int i = this.a.getInt();
      if ((i > 104857600) || (i < 0) || (i > this.a.capacity()))
        throw new RuntimeException("String too long: " + i);
      byte[] arrayOfByte1 = new byte[i];
      this.a.get(arrayOfByte1);
      try
      {
        String str1 = new String(arrayOfByte1, this.b);
        return str1;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException1)
      {
        return new String(arrayOfByte1);
      }
      String str2 = null;
    }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  public final byte[] c(int paramInt, boolean paramBoolean)
  {
    byte[] arrayOfByte;
    if (a(paramInt))
    {
      a locala1 = new a();
      a(locala1, this.a);
      switch (locala1.a)
      {
      default:
        throw new RuntimeException("type mismatch.");
      case 13:
        a locala2 = new a();
        a(locala2, this.a);
        if (locala2.a != 0)
          throw new RuntimeException("type mismatch, tag: " + paramInt + ", type: " + locala1.a + ", " + locala2.a);
        int k = a(0, 0, true);
        if ((k < 0) || (k > this.a.capacity()))
          throw new RuntimeException("invalid size, tag: " + paramInt + ", type: " + locala1.a + ", " + locala2.a + ", size: " + k);
        arrayOfByte = new byte[k];
        this.a.get(arrayOfByte);
      case 9:
      }
    }
    do
    {
      while (true)
      {
        return arrayOfByte;
        int i = a(0, 0, true);
        if ((i < 0) || (i > this.a.capacity()))
          throw new RuntimeException("size invalid: " + i);
        arrayOfByte = new byte[i];
        for (int j = 0; j < i; j++)
          arrayOfByte[j] = a(arrayOfByte[0], 0, true);
      }
      arrayOfByte = null;
    }
    while (!paramBoolean);
    throw new RuntimeException("require field not exist.");
  }

  public static final class a
  {
    public byte a;
    public int b;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.e.a
 * JD-Core Version:    0.6.0
 */