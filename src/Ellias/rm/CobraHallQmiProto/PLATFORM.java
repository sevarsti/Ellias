package CobraHallQmiProto;

import java.io.Serializable;

public final class PLATFORM
  implements Serializable
{
  public static final PLATFORM PF_ANDROID;
  public static final PLATFORM PF_FLASH;
  public static final PLATFORM PF_HTML5;
  public static final PLATFORM PF_IMAC;
  public static final PLATFORM PF_IOS;
  public static final PLATFORM PF_KJAVA;
  public static final PLATFORM PF_MTK;
  public static final PLATFORM PF_SYMBIAN;
  public static final PLATFORM PF_WIN;
  public static final PLATFORM PF_WP;
  public static final int _PF_ANDROID = 5;
  public static final int _PF_FLASH = 7;
  public static final int _PF_HTML5 = 8;
  public static final int _PF_IMAC = 10;
  public static final int _PF_IOS = 3;
  public static final int _PF_KJAVA = 2;
  public static final int _PF_MTK = 4;
  public static final int _PF_SYMBIAN = 1;
  public static final int _PF_WIN = 9;
  public static final int _PF_WP = 6;
  private static PLATFORM[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!PLATFORM.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new PLATFORM[10];
      PF_SYMBIAN = new PLATFORM(0, 1, "PF_SYMBIAN");
      PF_KJAVA = new PLATFORM(1, 2, "PF_KJAVA");
      PF_IOS = new PLATFORM(2, 3, "PF_IOS");
      PF_MTK = new PLATFORM(3, 4, "PF_MTK");
      PF_ANDROID = new PLATFORM(4, 5, "PF_ANDROID");
      PF_WP = new PLATFORM(5, 6, "PF_WP");
      PF_FLASH = new PLATFORM(6, 7, "PF_FLASH");
      PF_HTML5 = new PLATFORM(7, 8, "PF_HTML5");
      PF_WIN = new PLATFORM(8, 9, "PF_WIN");
      PF_IMAC = new PLATFORM(9, 10, "PF_IMAC");
      return;
    }
  }

  private PLATFORM(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static PLATFORM a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static PLATFORM a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.PLATFORM
 * JD-Core Version:    0.6.0
 */