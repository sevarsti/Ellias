package CobraHallQmiProto;

import java.io.Serializable;

public final class TOKEN_TYPE
  implements Serializable
{
  public static final TOKEN_TYPE TOKEN_TYPE_QQ;
  public static final TOKEN_TYPE TOKEN_TYPE_WECHAT;
  public static final int _TOKEN_TYPE_QQ = 0;
  public static final int _TOKEN_TYPE_WECHAT = 1;
  private static TOKEN_TYPE[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!TOKEN_TYPE.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new TOKEN_TYPE[2];
      TOKEN_TYPE_QQ = new TOKEN_TYPE(0, 0, "TOKEN_TYPE_QQ");
      TOKEN_TYPE_WECHAT = new TOKEN_TYPE(1, 1, "TOKEN_TYPE_WECHAT");
      return;
    }
  }

  private TOKEN_TYPE(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static TOKEN_TYPE a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static TOKEN_TYPE a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.TOKEN_TYPE
 * JD-Core Version:    0.6.0
 */