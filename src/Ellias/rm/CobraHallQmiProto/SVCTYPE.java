package CobraHallQmiProto;

import java.io.Serializable;

public final class SVCTYPE
  implements Serializable
{
  public static final SVCTYPE SVCTYPE_FBGAME;
  public static final SVCTYPE SVCTYPE_MINIGAME;
  public static final SVCTYPE SVCTYPE_MSF_A8;
  public static final SVCTYPE SVCTYPE_MSF_D3_IED;
  public static final SVCTYPE SVCTYPE_WECHAT;
  public static final int _SVCTYPE_FBGAME = 2;
  public static final int _SVCTYPE_MINIGAME = 1;
  public static final int _SVCTYPE_MSF_A8 = 3;
  public static final int _SVCTYPE_MSF_D3_IED = 4;
  public static final int _SVCTYPE_WECHAT = 5;
  private static SVCTYPE[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!SVCTYPE.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new SVCTYPE[5];
      SVCTYPE_MINIGAME = new SVCTYPE(0, 1, "SVCTYPE_MINIGAME");
      SVCTYPE_FBGAME = new SVCTYPE(1, 2, "SVCTYPE_FBGAME");
      SVCTYPE_MSF_A8 = new SVCTYPE(2, 3, "SVCTYPE_MSF_A8");
      SVCTYPE_MSF_D3_IED = new SVCTYPE(3, 4, "SVCTYPE_MSF_D3_IED");
      SVCTYPE_WECHAT = new SVCTYPE(4, 5, "SVCTYPE_WECHAT");
      return;
    }
  }

  private SVCTYPE(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static SVCTYPE a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static SVCTYPE a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.SVCTYPE
 * JD-Core Version:    0.6.0
 */