package CobraHallQmiProto;

import java.io.Serializable;

public final class AUTHTYPE
  implements Serializable
{
  public static final AUTHTYPE AUTHTYPE_A2SHAREKEY;
  public static final AUTHTYPE AUTHTYPE_MSF_A8;
  public static final AUTHTYPE AUTHTYPE_MSF_D3_IED;
  public static final AUTHTYPE AUTHTYPE_OPENTOKEN;
  public static final AUTHTYPE AUTHTYPE_PWDHASH;
  public static final AUTHTYPE AUTHTYPE_QQNO;
  public static final AUTHTYPE AUTHTYPE_SID;
  public static final int _AUTHTYPE_A2SHAREKEY = 64;
  public static final int _AUTHTYPE_MSF_A8 = 4;
  public static final int _AUTHTYPE_MSF_D3_IED = 2;
  public static final int _AUTHTYPE_OPENTOKEN = 16;
  public static final int _AUTHTYPE_PWDHASH = 1;
  public static final int _AUTHTYPE_QQNO = 32;
  public static final int _AUTHTYPE_SID = 8;
  private static AUTHTYPE[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!AUTHTYPE.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new AUTHTYPE[7];
      AUTHTYPE_PWDHASH = new AUTHTYPE(0, 1, "AUTHTYPE_PWDHASH");
      AUTHTYPE_MSF_D3_IED = new AUTHTYPE(1, 2, "AUTHTYPE_MSF_D3_IED");
      AUTHTYPE_MSF_A8 = new AUTHTYPE(2, 4, "AUTHTYPE_MSF_A8");
      AUTHTYPE_SID = new AUTHTYPE(3, 8, "AUTHTYPE_SID");
      AUTHTYPE_OPENTOKEN = new AUTHTYPE(4, 16, "AUTHTYPE_OPENTOKEN");
      AUTHTYPE_QQNO = new AUTHTYPE(5, 32, "AUTHTYPE_QQNO");
      AUTHTYPE_A2SHAREKEY = new AUTHTYPE(6, 64, "AUTHTYPE_A2SHAREKEY");
      return;
    }
  }

  private AUTHTYPE(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static AUTHTYPE a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static AUTHTYPE a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.AUTHTYPE
 * JD-Core Version:    0.6.0
 */