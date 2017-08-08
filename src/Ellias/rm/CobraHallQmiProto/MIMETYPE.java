package CobraHallQmiProto;

import java.io.Serializable;

public final class MIMETYPE
  implements Serializable
{
  public static final MIMETYPE MIMETYPE_ANIMATION;
  public static final MIMETYPE MIMETYPE_MUSIC;
  public static final MIMETYPE MIMETYPE_PIC;
  public static final MIMETYPE MIMETYPE_VOICE;
  public static final int _MIMETYPE_ANIMATION = 3;
  public static final int _MIMETYPE_MUSIC = 2;
  public static final int _MIMETYPE_PIC = 1;
  public static final int _MIMETYPE_VOICE = 4;
  private static MIMETYPE[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!MIMETYPE.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new MIMETYPE[4];
      MIMETYPE_PIC = new MIMETYPE(0, 1, "MIMETYPE_PIC");
      MIMETYPE_MUSIC = new MIMETYPE(1, 2, "MIMETYPE_MUSIC");
      MIMETYPE_ANIMATION = new MIMETYPE(2, 3, "MIMETYPE_ANIMATION");
      MIMETYPE_VOICE = new MIMETYPE(3, 4, "MIMETYPE_VOICE");
      return;
    }
  }

  private MIMETYPE(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static MIMETYPE a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static MIMETYPE a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.MIMETYPE
 * JD-Core Version:    0.6.0
 */