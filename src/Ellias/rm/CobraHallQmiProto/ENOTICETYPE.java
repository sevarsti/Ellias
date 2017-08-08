package CobraHallQmiProto;

import java.io.Serializable;

public final class ENOTICETYPE
  implements Serializable
{
  public static final ENOTICETYPE ENOTICETYPE_ESCAPE_COUNT;
  public static final ENOTICETYPE ENOTICETYPE_SHOW_COUNT;
  public static final int _ENOTICETYPE_ESCAPE_COUNT = 0;
  public static final int _ENOTICETYPE_SHOW_COUNT = 1;
  private static ENOTICETYPE[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!ENOTICETYPE.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new ENOTICETYPE[2];
      ENOTICETYPE_ESCAPE_COUNT = new ENOTICETYPE(0, 0, "ENOTICETYPE_ESCAPE_COUNT");
      ENOTICETYPE_SHOW_COUNT = new ENOTICETYPE(1, 1, "ENOTICETYPE_SHOW_COUNT");
      return;
    }
  }

  private ENOTICETYPE(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static ENOTICETYPE a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static ENOTICETYPE a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.ENOTICETYPE
 * JD-Core Version:    0.6.0
 */