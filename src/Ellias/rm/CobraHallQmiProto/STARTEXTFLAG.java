package CobraHallQmiProto;

import java.io.Serializable;

public final class STARTEXTFLAG
  implements Serializable
{
  public static final STARTEXTFLAG STARTEXTFLAG_SCRMATCH;
  public static final int _STARTEXTFLAG_SCRMATCH = 1;
  private static STARTEXTFLAG[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!STARTEXTFLAG.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new STARTEXTFLAG[1];
      STARTEXTFLAG_SCRMATCH = new STARTEXTFLAG(0, 1, "STARTEXTFLAG_SCRMATCH");
      return;
    }
  }

  private STARTEXTFLAG(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static STARTEXTFLAG a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static STARTEXTFLAG a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.STARTEXTFLAG
 * JD-Core Version:    0.6.0
 */