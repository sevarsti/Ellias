package CobraHallQmiProto;

import java.io.Serializable;

public final class PICPROFILETYPE
  implements Serializable
{
  public static final PICPROFILETYPE PPT_BACKGROUND;
  public static final PICPROFILETYPE PPT_FACE;
  public static final int _PPT_BACKGROUND = 1;
  public static final int _PPT_FACE;
  private static PICPROFILETYPE[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!PICPROFILETYPE.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new PICPROFILETYPE[2];
      PPT_FACE = new PICPROFILETYPE(0, 0, "PPT_FACE");
      PPT_BACKGROUND = new PICPROFILETYPE(1, 1, "PPT_BACKGROUND");
      return;
    }
  }

  private PICPROFILETYPE(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static PICPROFILETYPE a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static PICPROFILETYPE a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.PICPROFILETYPE
 * JD-Core Version:    0.6.0
 */