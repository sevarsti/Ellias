package CobraHallQmiProto;

import java.io.Serializable;

public final class UPDATETYPE
  implements Serializable
{
  public static final UPDATETYPE UPDATETYPE_CLOSE;
  public static final UPDATETYPE UPDATETYPE_DELETE;
  public static final UPDATETYPE UPDATETYPE_GREY;
  public static final UPDATETYPE UPDATETYPE_MAINTAIN;
  public static final UPDATETYPE UPDATETYPE_UPDATE;
  public static final int _UPDATETYPE_CLOSE = 3;
  public static final int _UPDATETYPE_DELETE = 1;
  public static final int _UPDATETYPE_GREY = 4;
  public static final int _UPDATETYPE_MAINTAIN = 5;
  public static final int _UPDATETYPE_UPDATE = 2;
  private static UPDATETYPE[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!UPDATETYPE.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new UPDATETYPE[5];
      UPDATETYPE_DELETE = new UPDATETYPE(0, 1, "UPDATETYPE_DELETE");
      UPDATETYPE_UPDATE = new UPDATETYPE(1, 2, "UPDATETYPE_UPDATE");
      UPDATETYPE_CLOSE = new UPDATETYPE(2, 3, "UPDATETYPE_CLOSE");
      UPDATETYPE_GREY = new UPDATETYPE(3, 4, "UPDATETYPE_GREY");
      UPDATETYPE_MAINTAIN = new UPDATETYPE(4, 5, "UPDATETYPE_MAINTAIN");
      return;
    }
  }

  private UPDATETYPE(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static UPDATETYPE a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static UPDATETYPE a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.UPDATETYPE
 * JD-Core Version:    0.6.0
 */