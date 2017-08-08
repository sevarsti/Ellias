package com.qq.taf.jce;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class JceDisplayer
{
  private int _level = 0;
  private StringBuilder sb;

  public JceDisplayer(StringBuilder paramStringBuilder)
  {
    this.sb = paramStringBuilder;
  }

  public JceDisplayer(StringBuilder paramStringBuilder, int paramInt)
  {
    this.sb = paramStringBuilder;
    this._level = paramInt;
  }

  private void ps(String paramString)
  {
    for (int i = 0; i < this._level; i++)
      this.sb.append('\t');
    if (paramString != null)
      this.sb.append(paramString).append(": ");
  }

  public JceDisplayer display(byte paramByte, String paramString)
  {
    ps(paramString);
    this.sb.append(paramByte).append('\n');
    return this;
  }

  public JceDisplayer display(char paramChar, String paramString)
  {
    ps(paramString);
    this.sb.append(paramChar).append('\n');
    return this;
  }

  public JceDisplayer display(double paramDouble, String paramString)
  {
    ps(paramString);
    this.sb.append(paramDouble).append('\n');
    return this;
  }

  public JceDisplayer display(float paramFloat, String paramString)
  {
    ps(paramString);
    this.sb.append(paramFloat).append('\n');
    return this;
  }

  public JceDisplayer display(int paramInt, String paramString)
  {
    ps(paramString);
    this.sb.append(paramInt).append('\n');
    return this;
  }

  public JceDisplayer display(long paramLong, String paramString)
  {
    ps(paramString);
    this.sb.append(paramLong).append('\n');
    return this;
  }

  public JceDisplayer display(JceStruct paramJceStruct, String paramString)
  {
    display('{', paramString);
    if (paramJceStruct == null)
      this.sb.append('\t').append("null");
    while (true)
    {
      display('}', null);
      return this;
      paramJceStruct.display(this.sb, 1 + this._level);
    }
  }

  public <T> JceDisplayer display(T paramT, String paramString)
  {
    if (paramT == null)
    {
      this.sb.append("null").append('\n');
      return this;
    }
    if ((paramT instanceof Byte))
    {
      display(((Byte)paramT).byteValue(), paramString);
      return this;
    }
    if ((paramT instanceof Boolean))
    {
      display(((Boolean)paramT).booleanValue(), paramString);
      return this;
    }
    if ((paramT instanceof Short))
    {
      display(((Short)paramT).shortValue(), paramString);
      return this;
    }
    if ((paramT instanceof Integer))
    {
      display(((Integer)paramT).intValue(), paramString);
      return this;
    }
    if ((paramT instanceof Long))
    {
      display(((Long)paramT).longValue(), paramString);
      return this;
    }
    if ((paramT instanceof Float))
    {
      display(((Float)paramT).floatValue(), paramString);
      return this;
    }
    if ((paramT instanceof Double))
    {
      display(((Double)paramT).doubleValue(), paramString);
      return this;
    }
    if ((paramT instanceof String))
    {
      display((String)paramT, paramString);
      return this;
    }
    if ((paramT instanceof Map))
    {
      display((Map)paramT, paramString);
      return this;
    }
    if ((paramT instanceof List))
    {
      display((List)paramT, paramString);
      return this;
    }
    if ((paramT instanceof JceStruct))
    {
      display((JceStruct)paramT, paramString);
      return this;
    }
    if ((paramT instanceof byte[]))
    {
      display((byte[])(byte[])paramT, paramString);
      return this;
    }
    if ((paramT instanceof boolean[]))
    {
      display((boolean[])(boolean[])paramT, paramString);
      return this;
    }
    if ((paramT instanceof short[]))
    {
      display((short[])(short[])paramT, paramString);
      return this;
    }
    if ((paramT instanceof int[]))
    {
      display((int[])(int[])paramT, paramString);
      return this;
    }
    if ((paramT instanceof long[]))
    {
      display((long[])(long[])paramT, paramString);
      return this;
    }
    if ((paramT instanceof float[]))
    {
      display((float[])(float[])paramT, paramString);
      return this;
    }
    if ((paramT instanceof double[]))
    {
      display((double[])(double[])paramT, paramString);
      return this;
    }
    if (paramT.getClass().isArray())
    {
      display((Object[])(Object[])paramT, paramString);
      return this;
    }
    throw new JceEncodeException("write object error: unsupport type.");
  }

  public JceDisplayer display(String paramString1, String paramString2)
  {
    ps(paramString2);
    if (paramString1 == null)
    {
      this.sb.append("null").append('\n');
      return this;
    }
    this.sb.append(paramString1).append('\n');
    return this;
  }

  public <T> JceDisplayer display(Collection<T> paramCollection, String paramString)
  {
    if (paramCollection == null)
    {
      ps(paramString);
      this.sb.append("null").append('\t');
      return this;
    }
    return display(paramCollection.toArray(), paramString);
  }

  public <K, V> JceDisplayer display(Map<K, V> paramMap, String paramString)
  {
    ps(paramString);
    if (paramMap == null)
    {
      this.sb.append("null").append('\n');
      return this;
    }
    if (paramMap.isEmpty())
    {
      this.sb.append(paramMap.size()).append(", {}").append('\n');
      return this;
    }
    this.sb.append(paramMap.size()).append(", {").append('\n');
    JceDisplayer localJceDisplayer1 = new JceDisplayer(this.sb, 1 + this._level);
    JceDisplayer localJceDisplayer2 = new JceDisplayer(this.sb, 2 + this._level);
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localJceDisplayer1.display('(', null);
      localJceDisplayer2.display(localEntry.getKey(), null);
      localJceDisplayer2.display(localEntry.getValue(), null);
      localJceDisplayer1.display(')', null);
    }
    display('}', null);
    return this;
  }

  public JceDisplayer display(short paramShort, String paramString)
  {
    ps(paramString);
    this.sb.append(paramShort).append('\n');
    return this;
  }

  public JceDisplayer display(boolean paramBoolean, String paramString)
  {
    ps(paramString);
    StringBuilder localStringBuilder = this.sb;
    if (paramBoolean);
    for (char c = 'T'; ; c = 'F')
    {
      localStringBuilder.append(c).append('\n');
      return this;
    }
  }

  public JceDisplayer display(byte[] paramArrayOfByte, String paramString)
  {
    ps(paramString);
    if (paramArrayOfByte == null)
    {
      this.sb.append("null").append('\n');
      return this;
    }
    if (paramArrayOfByte.length == 0)
    {
      this.sb.append(paramArrayOfByte.length).append(", []").append('\n');
      return this;
    }
    this.sb.append(paramArrayOfByte.length).append(", [").append('\n');
    JceDisplayer localJceDisplayer = new JceDisplayer(this.sb, 1 + this._level);
    int i = paramArrayOfByte.length;
    for (int j = 0; j < i; j++)
      localJceDisplayer.display(paramArrayOfByte[j], null);
    display(']', null);
    return this;
  }

  public JceDisplayer display(char[] paramArrayOfChar, String paramString)
  {
    ps(paramString);
    if (paramArrayOfChar == null)
    {
      this.sb.append("null").append('\n');
      return this;
    }
    if (paramArrayOfChar.length == 0)
    {
      this.sb.append(paramArrayOfChar.length).append(", []").append('\n');
      return this;
    }
    this.sb.append(paramArrayOfChar.length).append(", [").append('\n');
    JceDisplayer localJceDisplayer = new JceDisplayer(this.sb, 1 + this._level);
    int i = paramArrayOfChar.length;
    for (int j = 0; j < i; j++)
      localJceDisplayer.display(paramArrayOfChar[j], null);
    display(']', null);
    return this;
  }

  public JceDisplayer display(double[] paramArrayOfDouble, String paramString)
  {
    ps(paramString);
    if (paramArrayOfDouble == null)
    {
      this.sb.append("null").append('\n');
      return this;
    }
    if (paramArrayOfDouble.length == 0)
    {
      this.sb.append(paramArrayOfDouble.length).append(", []").append('\n');
      return this;
    }
    this.sb.append(paramArrayOfDouble.length).append(", [").append('\n');
    JceDisplayer localJceDisplayer = new JceDisplayer(this.sb, 1 + this._level);
    int i = paramArrayOfDouble.length;
    for (int j = 0; j < i; j++)
      localJceDisplayer.display(paramArrayOfDouble[j], null);
    display(']', null);
    return this;
  }

  public JceDisplayer display(float[] paramArrayOfFloat, String paramString)
  {
    ps(paramString);
    if (paramArrayOfFloat == null)
    {
      this.sb.append("null").append('\n');
      return this;
    }
    if (paramArrayOfFloat.length == 0)
    {
      this.sb.append(paramArrayOfFloat.length).append(", []").append('\n');
      return this;
    }
    this.sb.append(paramArrayOfFloat.length).append(", [").append('\n');
    JceDisplayer localJceDisplayer = new JceDisplayer(this.sb, 1 + this._level);
    int i = paramArrayOfFloat.length;
    for (int j = 0; j < i; j++)
      localJceDisplayer.display(paramArrayOfFloat[j], null);
    display(']', null);
    return this;
  }

  public JceDisplayer display(int[] paramArrayOfInt, String paramString)
  {
    ps(paramString);
    if (paramArrayOfInt == null)
    {
      this.sb.append("null").append('\n');
      return this;
    }
    if (paramArrayOfInt.length == 0)
    {
      this.sb.append(paramArrayOfInt.length).append(", []").append('\n');
      return this;
    }
    this.sb.append(paramArrayOfInt.length).append(", [").append('\n');
    JceDisplayer localJceDisplayer = new JceDisplayer(this.sb, 1 + this._level);
    int i = paramArrayOfInt.length;
    for (int j = 0; j < i; j++)
      localJceDisplayer.display(paramArrayOfInt[j], null);
    display(']', null);
    return this;
  }

  public JceDisplayer display(long[] paramArrayOfLong, String paramString)
  {
    ps(paramString);
    if (paramArrayOfLong == null)
    {
      this.sb.append("null").append('\n');
      return this;
    }
    if (paramArrayOfLong.length == 0)
    {
      this.sb.append(paramArrayOfLong.length).append(", []").append('\n');
      return this;
    }
    this.sb.append(paramArrayOfLong.length).append(", [").append('\n');
    JceDisplayer localJceDisplayer = new JceDisplayer(this.sb, 1 + this._level);
    int i = paramArrayOfLong.length;
    for (int j = 0; j < i; j++)
      localJceDisplayer.display(paramArrayOfLong[j], null);
    display(']', null);
    return this;
  }

  public <T> JceDisplayer display(T[] paramArrayOfT, String paramString)
  {
    ps(paramString);
    if (paramArrayOfT == null)
    {
      this.sb.append("null").append('\n');
      return this;
    }
    if (paramArrayOfT.length == 0)
    {
      this.sb.append(paramArrayOfT.length).append(", []").append('\n');
      return this;
    }
    this.sb.append(paramArrayOfT.length).append(", [").append('\n');
    JceDisplayer localJceDisplayer = new JceDisplayer(this.sb, 1 + this._level);
    int i = paramArrayOfT.length;
    for (int j = 0; j < i; j++)
      localJceDisplayer.display(paramArrayOfT[j], null);
    display(']', null);
    return this;
  }

  public JceDisplayer display(short[] paramArrayOfShort, String paramString)
  {
    ps(paramString);
    if (paramArrayOfShort == null)
    {
      this.sb.append("null").append('\n');
      return this;
    }
    if (paramArrayOfShort.length == 0)
    {
      this.sb.append(paramArrayOfShort.length).append(", []").append('\n');
      return this;
    }
    this.sb.append(paramArrayOfShort.length).append(", [").append('\n');
    JceDisplayer localJceDisplayer = new JceDisplayer(this.sb, 1 + this._level);
    int i = paramArrayOfShort.length;
    for (int j = 0; j < i; j++)
      localJceDisplayer.display(paramArrayOfShort[j], null);
    display(']', null);
    return this;
  }

  public JceDisplayer displaySimple(byte paramByte, boolean paramBoolean)
  {
    this.sb.append(paramByte);
    if (paramBoolean)
      this.sb.append("|");
    return this;
  }

  public JceDisplayer displaySimple(char paramChar, boolean paramBoolean)
  {
    this.sb.append(paramChar);
    if (paramBoolean)
      this.sb.append("|");
    return this;
  }

  public JceDisplayer displaySimple(double paramDouble, boolean paramBoolean)
  {
    this.sb.append(paramDouble);
    if (paramBoolean)
      this.sb.append("|");
    return this;
  }

  public JceDisplayer displaySimple(float paramFloat, boolean paramBoolean)
  {
    this.sb.append(paramFloat);
    if (paramBoolean)
      this.sb.append("|");
    return this;
  }

  public JceDisplayer displaySimple(int paramInt, boolean paramBoolean)
  {
    this.sb.append(paramInt);
    if (paramBoolean)
      this.sb.append("|");
    return this;
  }

  public JceDisplayer displaySimple(long paramLong, boolean paramBoolean)
  {
    this.sb.append(paramLong);
    if (paramBoolean)
      this.sb.append("|");
    return this;
  }

  public JceDisplayer displaySimple(JceStruct paramJceStruct, boolean paramBoolean)
  {
    this.sb.append("{");
    if (paramJceStruct == null)
      this.sb.append('\t').append("null");
    while (true)
    {
      this.sb.append("}");
      if (paramBoolean)
        this.sb.append("|");
      return this;
      paramJceStruct.displaySimple(this.sb, 1 + this._level);
    }
  }

  public <T> JceDisplayer displaySimple(T paramT, boolean paramBoolean)
  {
    if (paramT == null)
    {
      this.sb.append("null").append('\n');
      return this;
    }
    if ((paramT instanceof Byte))
    {
      displaySimple(((Byte)paramT).byteValue(), paramBoolean);
      return this;
    }
    if ((paramT instanceof Boolean))
    {
      displaySimple(((Boolean)paramT).booleanValue(), paramBoolean);
      return this;
    }
    if ((paramT instanceof Short))
    {
      displaySimple(((Short)paramT).shortValue(), paramBoolean);
      return this;
    }
    if ((paramT instanceof Integer))
    {
      displaySimple(((Integer)paramT).intValue(), paramBoolean);
      return this;
    }
    if ((paramT instanceof Long))
    {
      displaySimple(((Long)paramT).longValue(), paramBoolean);
      return this;
    }
    if ((paramT instanceof Float))
    {
      displaySimple(((Float)paramT).floatValue(), paramBoolean);
      return this;
    }
    if ((paramT instanceof Double))
    {
      displaySimple(((Double)paramT).doubleValue(), paramBoolean);
      return this;
    }
    if ((paramT instanceof String))
    {
      displaySimple((String)paramT, paramBoolean);
      return this;
    }
    if ((paramT instanceof Map))
    {
      displaySimple((Map)paramT, paramBoolean);
      return this;
    }
    if ((paramT instanceof List))
    {
      displaySimple((List)paramT, paramBoolean);
      return this;
    }
    if ((paramT instanceof JceStruct))
    {
      displaySimple((JceStruct)paramT, paramBoolean);
      return this;
    }
    if ((paramT instanceof byte[]))
    {
      displaySimple((byte[])(byte[])paramT, paramBoolean);
      return this;
    }
    if ((paramT instanceof boolean[]))
    {
      displaySimple((boolean[])(boolean[])paramT, paramBoolean);
      return this;
    }
    if ((paramT instanceof short[]))
    {
      displaySimple((short[])(short[])paramT, paramBoolean);
      return this;
    }
    if ((paramT instanceof int[]))
    {
      displaySimple((int[])(int[])paramT, paramBoolean);
      return this;
    }
    if ((paramT instanceof long[]))
    {
      displaySimple((long[])(long[])paramT, paramBoolean);
      return this;
    }
    if ((paramT instanceof float[]))
    {
      displaySimple((float[])(float[])paramT, paramBoolean);
      return this;
    }
    if ((paramT instanceof double[]))
    {
      displaySimple((double[])(double[])paramT, paramBoolean);
      return this;
    }
    if (paramT.getClass().isArray())
    {
      displaySimple((Object[])(Object[])paramT, paramBoolean);
      return this;
    }
    throw new JceEncodeException("write object error: unsupport type.");
  }

  public JceDisplayer displaySimple(String paramString, boolean paramBoolean)
  {
    if (paramString == null)
      this.sb.append("null");
    while (true)
    {
      if (paramBoolean)
        this.sb.append("|");
      return this;
      this.sb.append(paramString);
    }
  }

  public <T> JceDisplayer displaySimple(Collection<T> paramCollection, boolean paramBoolean)
  {
    if (paramCollection == null)
    {
      this.sb.append("[]");
      if (paramBoolean)
        this.sb.append("|");
      return this;
    }
    return displaySimple(paramCollection.toArray(), paramBoolean);
  }

  public <K, V> JceDisplayer displaySimple(Map<K, V> paramMap, boolean paramBoolean)
  {
    if ((paramMap == null) || (paramMap.isEmpty()))
    {
      this.sb.append("{}");
      if (paramBoolean)
        this.sb.append("|");
    }
    do
    {
      return this;
      this.sb.append("{");
      JceDisplayer localJceDisplayer = new JceDisplayer(this.sb, 2 + this._level);
      int i = 1;
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (i == 0)
          this.sb.append(",");
        localJceDisplayer.displaySimple(localEntry.getKey(), true);
        localJceDisplayer.displaySimple(localEntry.getValue(), false);
        i = 0;
      }
      this.sb.append("}");
    }
    while (!paramBoolean);
    this.sb.append("|");
    return this;
  }

  public JceDisplayer displaySimple(short paramShort, boolean paramBoolean)
  {
    this.sb.append(paramShort);
    if (paramBoolean)
      this.sb.append("|");
    return this;
  }

  public JceDisplayer displaySimple(boolean paramBoolean1, boolean paramBoolean2)
  {
    StringBuilder localStringBuilder = this.sb;
    if (paramBoolean1);
    for (char c = 'T'; ; c = 'F')
    {
      localStringBuilder.append(c);
      if (paramBoolean2)
        this.sb.append("|");
      return this;
    }
  }

  public JceDisplayer displaySimple(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
      if (paramBoolean)
        this.sb.append("|");
    do
    {
      return this;
      this.sb.append(HexUtil.bytes2HexStr(paramArrayOfByte));
    }
    while (!paramBoolean);
    this.sb.append("|");
    return this;
  }

  public JceDisplayer displaySimple(char[] paramArrayOfChar, boolean paramBoolean)
  {
    if ((paramArrayOfChar == null) || (paramArrayOfChar.length == 0))
      if (paramBoolean)
        this.sb.append("|");
    do
    {
      return this;
      this.sb.append(new String(paramArrayOfChar));
    }
    while (!paramBoolean);
    this.sb.append("|");
    return this;
  }

  public JceDisplayer displaySimple(double[] paramArrayOfDouble, boolean paramBoolean)
  {
    if ((paramArrayOfDouble == null) || (paramArrayOfDouble.length == 0))
    {
      this.sb.append("[]");
      if (paramBoolean)
        this.sb.append("|");
    }
    do
    {
      return this;
      this.sb.append("[");
      JceDisplayer localJceDisplayer = new JceDisplayer(this.sb, 1 + this._level);
      for (int i = 0; i < paramArrayOfDouble.length; i++)
      {
        double d = paramArrayOfDouble[i];
        if (i != 0)
          this.sb.append("|");
        localJceDisplayer.displaySimple(d, false);
      }
      this.sb.append("[");
    }
    while (!paramBoolean);
    this.sb.append("|");
    return this;
  }

  public JceDisplayer displaySimple(float[] paramArrayOfFloat, boolean paramBoolean)
  {
    if ((paramArrayOfFloat == null) || (paramArrayOfFloat.length == 0))
    {
      this.sb.append("[]");
      if (paramBoolean)
        this.sb.append("|");
    }
    do
    {
      return this;
      this.sb.append("[");
      JceDisplayer localJceDisplayer = new JceDisplayer(this.sb, 1 + this._level);
      for (int i = 0; i < paramArrayOfFloat.length; i++)
      {
        float f = paramArrayOfFloat[i];
        if (i != 0)
          this.sb.append("|");
        localJceDisplayer.displaySimple(f, false);
      }
      this.sb.append("]");
    }
    while (!paramBoolean);
    this.sb.append("|");
    return this;
  }

  public JceDisplayer displaySimple(int[] paramArrayOfInt, boolean paramBoolean)
  {
    if ((paramArrayOfInt == null) || (paramArrayOfInt.length == 0))
    {
      this.sb.append("[]");
      if (paramBoolean)
        this.sb.append("|");
    }
    do
    {
      return this;
      this.sb.append("[");
      JceDisplayer localJceDisplayer = new JceDisplayer(this.sb, 1 + this._level);
      for (int i = 0; i < paramArrayOfInt.length; i++)
      {
        int j = paramArrayOfInt[i];
        if (i != 0)
          this.sb.append("|");
        localJceDisplayer.displaySimple(j, false);
      }
      this.sb.append("]");
    }
    while (!paramBoolean);
    this.sb.append("|");
    return this;
  }

  public JceDisplayer displaySimple(long[] paramArrayOfLong, boolean paramBoolean)
  {
    if ((paramArrayOfLong == null) || (paramArrayOfLong.length == 0))
    {
      this.sb.append("[]");
      if (paramBoolean)
        this.sb.append("|");
    }
    do
    {
      return this;
      this.sb.append("[");
      JceDisplayer localJceDisplayer = new JceDisplayer(this.sb, 1 + this._level);
      for (int i = 0; i < paramArrayOfLong.length; i++)
      {
        long l = paramArrayOfLong[i];
        if (i != 0)
          this.sb.append("|");
        localJceDisplayer.displaySimple(l, false);
      }
      this.sb.append("]");
    }
    while (!paramBoolean);
    this.sb.append("|");
    return this;
  }

  public <T> JceDisplayer displaySimple(T[] paramArrayOfT, boolean paramBoolean)
  {
    if ((paramArrayOfT == null) || (paramArrayOfT.length == 0))
    {
      this.sb.append("[]");
      if (paramBoolean)
        this.sb.append("|");
    }
    do
    {
      return this;
      this.sb.append("[");
      JceDisplayer localJceDisplayer = new JceDisplayer(this.sb, 1 + this._level);
      for (int i = 0; i < paramArrayOfT.length; i++)
      {
        T ? = paramArrayOfT[i];
        if (i != 0)
          this.sb.append("|");
        localJceDisplayer.displaySimple(?, false);
      }
      this.sb.append("]");
    }
    while (!paramBoolean);
    this.sb.append("|");
    return this;
  }

  public JceDisplayer displaySimple(short[] paramArrayOfShort, boolean paramBoolean)
  {
    if ((paramArrayOfShort == null) || (paramArrayOfShort.length == 0))
    {
      this.sb.append("[]");
      if (paramBoolean)
        this.sb.append("|");
    }
    do
    {
      return this;
      this.sb.append("[");
      JceDisplayer localJceDisplayer = new JceDisplayer(this.sb, 1 + this._level);
      for (int i = 0; i < paramArrayOfShort.length; i++)
      {
        short s = paramArrayOfShort[i];
        if (i != 0)
          this.sb.append("|");
        localJceDisplayer.displaySimple(s, false);
      }
      this.sb.append("]");
    }
    while (!paramBoolean);
    this.sb.append("|");
    return this;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.qq.taf.jce.JceDisplayer
 * JD-Core Version:    0.6.0
 */