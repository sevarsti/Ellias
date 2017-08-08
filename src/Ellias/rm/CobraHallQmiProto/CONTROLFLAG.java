package CobraHallQmiProto;

import java.io.Serializable;

public final class CONTROLFLAG
  implements Serializable
{
  public static final CONTROLFLAG CONTROLFLAG_REPORTLOC;
  public static final CONTROLFLAG CONTROLFLAG_SAVEPWDHASH;
  public static final int _CONTROLFLAG_REPORTLOC = 2;
  public static final int _CONTROLFLAG_SAVEPWDHASH = 1;
  private static CONTROLFLAG[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!CONTROLFLAG.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new CONTROLFLAG[2];
      CONTROLFLAG_SAVEPWDHASH = new CONTROLFLAG(0, 1, "CONTROLFLAG_SAVEPWDHASH");
      CONTROLFLAG_REPORTLOC = new CONTROLFLAG(1, 2, "CONTROLFLAG_REPORTLOC");
      return;
    }
  }

  private CONTROLFLAG(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static CONTROLFLAG a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static CONTROLFLAG a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.CONTROLFLAG
 * JD-Core Version:    0.6.0
 */