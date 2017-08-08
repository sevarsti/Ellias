package CobraHallQmiProto;

import java.io.Serializable;

public final class PRODUCTFORM
  implements Serializable
{
  public static final PRODUCTFORM PRF_GAMEJOY;
  public static final PRODUCTFORM PRF_GAMEJOY_ASSISTANT;
  public static final PRODUCTFORM PRF_GAMEJOY_ASSISTANT_SDK;
  public static final int _PRF_GAMEJOY = 1;
  public static final int _PRF_GAMEJOY_ASSISTANT = 2;
  public static final int _PRF_GAMEJOY_ASSISTANT_SDK = 3;
  private static PRODUCTFORM[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!PRODUCTFORM.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new PRODUCTFORM[3];
      PRF_GAMEJOY = new PRODUCTFORM(0, 1, "PRF_GAMEJOY");
      PRF_GAMEJOY_ASSISTANT = new PRODUCTFORM(1, 2, "PRF_GAMEJOY_ASSISTANT");
      PRF_GAMEJOY_ASSISTANT_SDK = new PRODUCTFORM(2, 3, "PRF_GAMEJOY_ASSISTANT_SDK");
      return;
    }
  }

  private PRODUCTFORM(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static PRODUCTFORM a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static PRODUCTFORM a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.PRODUCTFORM
 * JD-Core Version:    0.6.0
 */