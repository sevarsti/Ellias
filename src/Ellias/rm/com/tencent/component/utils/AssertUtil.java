package com.tencent.component.utils;

public class AssertUtil
{
  public static void a()
  {
    a(null);
  }

  public static void a(byte paramByte1, byte paramByte2)
  {
    a(null, paramByte1, paramByte2);
  }

  public static void a(char paramChar1, char paramChar2)
  {
    a(null, paramChar1, paramChar2);
  }

  public static void a(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    a(null, paramDouble1, paramDouble2, paramDouble3);
  }

  public static void a(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    a(null, paramFloat1, paramFloat2, paramFloat3);
  }

  public static void a(int paramInt1, int paramInt2)
  {
    a(null, paramInt1, paramInt2);
  }

  public static void a(long paramLong1, long paramLong2)
  {
    a(null, paramLong1, paramLong2);
  }

  public static void a(Object paramObject)
  {
    a(null, paramObject);
  }

  public static void a(Object paramObject1, Object paramObject2)
  {
    a(null, paramObject1, paramObject2);
  }

  public static void a(String paramString)
  {
    if (paramString == null)
      throw new AssertionError();
    throw new AssertionError(paramString);
  }

  public static void a(String paramString, byte paramByte1, byte paramByte2)
  {
    a(paramString, new Byte(paramByte1), new Byte(paramByte2));
  }

  public static void a(String paramString, char paramChar1, char paramChar2)
  {
    a(paramString, new Character(paramChar1), new Character(paramChar2));
  }

  public static void a(String paramString, double paramDouble1, double paramDouble2, double paramDouble3)
  {
    if (Double.compare(paramDouble1, paramDouble2) == 0);
    do
      return;
    while (Math.abs(paramDouble1 - paramDouble2) <= paramDouble3);
    e(paramString, new Double(paramDouble1), new Double(paramDouble2));
  }

  public static void a(String paramString, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (Float.compare(paramFloat1, paramFloat2) == 0);
    do
      return;
    while (Math.abs(paramFloat1 - paramFloat2) <= paramFloat3);
    e(paramString, new Float(paramFloat1), new Float(paramFloat2));
  }

  public static void a(String paramString, int paramInt1, int paramInt2)
  {
    a(paramString, new Integer(paramInt1), new Integer(paramInt2));
  }

  public static void a(String paramString, long paramLong1, long paramLong2)
  {
    a(paramString, new Long(paramLong1), new Long(paramLong2));
  }

  public static void a(String paramString, Object paramObject)
  {
    if (paramObject != null);
    for (boolean bool = true; ; bool = false)
    {
      a(paramString, bool);
      return;
    }
  }

  public static void a(String paramString, Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == null) && (paramObject2 == null));
    do
      return;
    while ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
    e(paramString, paramObject1, paramObject2);
  }

  public static void a(String paramString1, String paramString2)
  {
    a(null, paramString1, paramString2);
  }

  public static void a(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString2 == null) && (paramString3 == null));
    do
      return;
    while ((paramString2 != null) && (paramString2.equals(paramString3)));
    if (paramString1 == null)
      paramString1 = "";
    a(paramString1 + " (expected:" + paramString2 + ", actual:" + paramString3 + ")");
  }

  public static void a(String paramString, short paramShort1, short paramShort2)
  {
    a(paramString, new Short(paramShort1), new Short(paramShort2));
  }

  public static void a(String paramString, boolean paramBoolean)
  {
    if (!paramBoolean)
      a(paramString);
  }

  public static void a(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    a(paramString, Boolean.valueOf(paramBoolean1), Boolean.valueOf(paramBoolean2));
  }

  public static void a(short paramShort1, short paramShort2)
  {
    a(null, paramShort1, paramShort2);
  }

  public static void a(boolean paramBoolean)
  {
    a(null, paramBoolean);
  }

  public static void a(boolean paramBoolean, String paramString)
  {
    if (!paramBoolean)
      throw new AssertionError(paramString);
  }

  public static void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    a(null, paramBoolean1, paramBoolean2);
  }

  public static void b(Object paramObject)
  {
    if (paramObject != null)
      b("Expected: <null> but was: " + paramObject.toString(), paramObject);
  }

  public static void b(Object paramObject1, Object paramObject2)
  {
    b(null, paramObject1, paramObject2);
  }

  public static void b(String paramString)
  {
    if (paramString != null);
    for (String str = paramString + " "; ; str = "")
    {
      a(str + "expected not same");
      return;
    }
  }

  public static void b(String paramString, Object paramObject)
  {
    if (paramObject == null);
    for (boolean bool = true; ; bool = false)
    {
      a(paramString, bool);
      return;
    }
  }

  public static void b(String paramString, Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2)
      return;
    d(paramString, paramObject1, paramObject2);
  }

  public static void b(String paramString, boolean paramBoolean)
  {
    if (!paramBoolean);
    for (boolean bool = true; ; bool = false)
    {
      a(paramString, bool);
      return;
    }
  }

  public static void b(boolean paramBoolean)
  {
    b(null, paramBoolean);
  }

  public static void c(Object paramObject1, Object paramObject2)
  {
    c(null, paramObject1, paramObject2);
  }

  public static void c(String paramString, Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2)
      b(paramString);
  }

  public static void d(String paramString, Object paramObject1, Object paramObject2)
  {
    if (paramString != null);
    for (String str = paramString + " "; ; str = "")
    {
      a(str + "expected same:<" + paramObject1 + "> was not:<" + paramObject2 + ">");
      return;
    }
  }

  public static void e(String paramString, Object paramObject1, Object paramObject2)
  {
    a(f(paramString, paramObject1, paramObject2));
  }

  public static String f(String paramString, Object paramObject1, Object paramObject2)
  {
    String str = "";
    if ((paramString != null) && (paramString.length() > 0))
      str = paramString + " ";
    return str + "expected:<" + paramObject1 + "> but was:<" + paramObject2 + ">";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.AssertUtil
 * JD-Core Version:    0.6.0
 */