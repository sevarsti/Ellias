package CobraHallQmiProto;

import java.io.Serializable;

public final class REPORTTYPE
  implements Serializable
{
  public static final REPORTTYPE REPORTTYPE_30000_GAME;
  public static final REPORTTYPE REPORTTYPE_30000_NEWS;
  public static final REPORTTYPE REPORTTYPE_30000_PAGE;
  public static final REPORTTYPE REPORTTYPE_30000_QMI;
  public static final REPORTTYPE REPORTTYPE_ERROR_REPORT;
  public static final REPORTTYPE REPORTTYPE_LOGIN;
  public static final int _REPORTTYPE_30000_GAME = 403;
  public static final int _REPORTTYPE_30000_NEWS = 402;
  public static final int _REPORTTYPE_30000_PAGE = 401;
  public static final int _REPORTTYPE_30000_QMI = 404;
  public static final int _REPORTTYPE_ERROR_REPORT = 600;
  public static final int _REPORTTYPE_LOGIN = 700;
  private static REPORTTYPE[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!REPORTTYPE.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new REPORTTYPE[6];
      REPORTTYPE_30000_PAGE = new REPORTTYPE(0, 401, "REPORTTYPE_30000_PAGE");
      REPORTTYPE_30000_NEWS = new REPORTTYPE(1, 402, "REPORTTYPE_30000_NEWS");
      REPORTTYPE_30000_GAME = new REPORTTYPE(2, 403, "REPORTTYPE_30000_GAME");
      REPORTTYPE_30000_QMI = new REPORTTYPE(3, 404, "REPORTTYPE_30000_QMI");
      REPORTTYPE_ERROR_REPORT = new REPORTTYPE(4, 600, "REPORTTYPE_ERROR_REPORT");
      REPORTTYPE_LOGIN = new REPORTTYPE(5, 700, "REPORTTYPE_LOGIN");
      return;
    }
  }

  private REPORTTYPE(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static REPORTTYPE a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static REPORTTYPE a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.REPORTTYPE
 * JD-Core Version:    0.6.0
 */