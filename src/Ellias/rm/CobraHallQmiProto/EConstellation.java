package CobraHallQmiProto;

import java.io.Serializable;

public final class EConstellation
  implements Serializable
{
  public static final EConstellation EC_AQUARIUS;
  public static final EConstellation EC_ARIES;
  public static final EConstellation EC_CANCER;
  public static final EConstellation EC_CAPRICORN;
  public static final EConstellation EC_GEMINI;
  public static final EConstellation EC_LIBRA;
  public static final EConstellation EC_LION;
  public static final EConstellation EC_PISCES;
  public static final EConstellation EC_SAGITTARIUS;
  public static final EConstellation EC_SCORPIO;
  public static final EConstellation EC_TAURUS;
  public static final EConstellation EC_VIRGO;
  public static final int _EC_AQUARIUS = 0;
  public static final int _EC_ARIES = 2;
  public static final int _EC_CANCER = 5;
  public static final int _EC_CAPRICORN = 11;
  public static final int _EC_GEMINI = 4;
  public static final int _EC_LIBRA = 8;
  public static final int _EC_LION = 6;
  public static final int _EC_PISCES = 1;
  public static final int _EC_SAGITTARIUS = 10;
  public static final int _EC_SCORPIO = 9;
  public static final int _EC_TAURUS = 3;
  public static final int _EC_VIRGO = 7;
  private static EConstellation[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!EConstellation.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new EConstellation[12];
      EC_AQUARIUS = new EConstellation(0, 0, "EC_AQUARIUS");
      EC_PISCES = new EConstellation(1, 1, "EC_PISCES");
      EC_ARIES = new EConstellation(2, 2, "EC_ARIES");
      EC_TAURUS = new EConstellation(3, 3, "EC_TAURUS");
      EC_GEMINI = new EConstellation(4, 4, "EC_GEMINI");
      EC_CANCER = new EConstellation(5, 5, "EC_CANCER");
      EC_LION = new EConstellation(6, 6, "EC_LION");
      EC_VIRGO = new EConstellation(7, 7, "EC_VIRGO");
      EC_LIBRA = new EConstellation(8, 8, "EC_LIBRA");
      EC_SCORPIO = new EConstellation(9, 9, "EC_SCORPIO");
      EC_SAGITTARIUS = new EConstellation(10, 10, "EC_SAGITTARIUS");
      EC_CAPRICORN = new EConstellation(11, 11, "EC_CAPRICORN");
      return;
    }
  }

  private EConstellation(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static EConstellation a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static EConstellation a(String paramString)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].toString().equals(paramString))
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public int a()
  {
    return this.__value;
  }

  public String toString()
  {
    return this.__T;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.EConstellation
 * JD-Core Version:    0.6.0
 */