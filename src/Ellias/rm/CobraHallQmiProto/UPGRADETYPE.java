package CobraHallQmiProto;

import java.io.Serializable;

public final class UPGRADETYPE
  implements Serializable
{
  public static final UPGRADETYPE UPGRADETYPE_FORCE;
  public static final UPGRADETYPE UPGRADETYPE_NONE;
  public static final UPGRADETYPE UPGRADETYPE_PROMPT;
  public static final UPGRADETYPE UPGRADETYPE_WEAK;
  public static final int _UPGRADETYPE_FORCE = 2;
  public static final int _UPGRADETYPE_NONE = 0;
  public static final int _UPGRADETYPE_PROMPT = 1;
  public static final int _UPGRADETYPE_WEAK = 3;
  private static UPGRADETYPE[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!UPGRADETYPE.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new UPGRADETYPE[4];
      UPGRADETYPE_NONE = new UPGRADETYPE(0, 0, "UPGRADETYPE_NONE");
      UPGRADETYPE_PROMPT = new UPGRADETYPE(1, 1, "UPGRADETYPE_PROMPT");
      UPGRADETYPE_FORCE = new UPGRADETYPE(2, 2, "UPGRADETYPE_FORCE");
      UPGRADETYPE_WEAK = new UPGRADETYPE(3, 3, "UPGRADETYPE_WEAK");
      return;
    }
  }

  private UPGRADETYPE(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static UPGRADETYPE a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static UPGRADETYPE a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.UPGRADETYPE
 * JD-Core Version:    0.6.0
 */