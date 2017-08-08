package CobraHallQmiProto;

import java.io.Serializable;

public final class PKGTYPE
  implements Serializable
{
  public static final PKGTYPE PKGTYPE_FLASH;
  public static final PKGTYPE PKGTYPE_FREEINSTALL;
  public static final PKGTYPE PKGTYPE_HTML5;
  public static final PKGTYPE PKGTYPE_INSTALL;
  public static final PKGTYPE PKGTYPE_KJAVA;
  public static final PKGTYPE PKGTYPE_PAGE;
  public static final PKGTYPE PKGTYPE_SYMBIAN_DLL;
  public static final PKGTYPE PKGTYPE_SYMBIAN_EXE;
  public static final PKGTYPE PKGTYPE_UNZIP;
  public static final int _PKGTYPE_FLASH = 7;
  public static final int _PKGTYPE_FREEINSTALL = 9;
  public static final int _PKGTYPE_HTML5 = 8;
  public static final int _PKGTYPE_INSTALL = 1;
  public static final int _PKGTYPE_KJAVA = 6;
  public static final int _PKGTYPE_PAGE = 2;
  public static final int _PKGTYPE_SYMBIAN_DLL = 4;
  public static final int _PKGTYPE_SYMBIAN_EXE = 5;
  public static final int _PKGTYPE_UNZIP = 3;
  private static PKGTYPE[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!PKGTYPE.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new PKGTYPE[9];
      PKGTYPE_INSTALL = new PKGTYPE(0, 1, "PKGTYPE_INSTALL");
      PKGTYPE_PAGE = new PKGTYPE(1, 2, "PKGTYPE_PAGE");
      PKGTYPE_UNZIP = new PKGTYPE(2, 3, "PKGTYPE_UNZIP");
      PKGTYPE_SYMBIAN_DLL = new PKGTYPE(3, 4, "PKGTYPE_SYMBIAN_DLL");
      PKGTYPE_SYMBIAN_EXE = new PKGTYPE(4, 5, "PKGTYPE_SYMBIAN_EXE");
      PKGTYPE_KJAVA = new PKGTYPE(5, 6, "PKGTYPE_KJAVA");
      PKGTYPE_FLASH = new PKGTYPE(6, 7, "PKGTYPE_FLASH");
      PKGTYPE_HTML5 = new PKGTYPE(7, 8, "PKGTYPE_HTML5");
      PKGTYPE_FREEINSTALL = new PKGTYPE(8, 9, "PKGTYPE_FREEINSTALL");
      return;
    }
  }

  private PKGTYPE(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static PKGTYPE a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static PKGTYPE a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.PKGTYPE
 * JD-Core Version:    0.6.0
 */