package CobraHallQmiProto;

import java.io.Serializable;

public final class EGENDER
  implements Serializable
{
  public static final EGENDER EG_FEMALE;
  public static final EGENDER EG_MALE;
  public static final int _EG_FEMALE = 1;
  public static final int _EG_MALE;
  private static EGENDER[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!EGENDER.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new EGENDER[2];
      EG_MALE = new EGENDER(0, 0, "EG_MALE");
      EG_FEMALE = new EGENDER(1, 1, "EG_FEMALE");
      return;
    }
  }

  private EGENDER(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static EGENDER a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static EGENDER a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.EGENDER
 * JD-Core Version:    0.6.0
 */