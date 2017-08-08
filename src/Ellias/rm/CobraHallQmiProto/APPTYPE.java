package CobraHallQmiProto;

import java.io.Serializable;

public final class APPTYPE
  implements Serializable
{
  public static final APPTYPE APPTYPE_APP;
  public static final APPTYPE APPTYPE_GAME;
  public static final APPTYPE APPTYPE_PLUGIN;
  public static final int _APPTYPE_APP = 2;
  public static final int _APPTYPE_GAME = 1;
  public static final int _APPTYPE_PLUGIN = 4;
  private static APPTYPE[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!APPTYPE.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new APPTYPE[3];
      APPTYPE_GAME = new APPTYPE(0, 1, "APPTYPE_GAME");
      APPTYPE_APP = new APPTYPE(1, 2, "APPTYPE_APP");
      APPTYPE_PLUGIN = new APPTYPE(2, 4, "APPTYPE_PLUGIN");
      return;
    }
  }

  private APPTYPE(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static APPTYPE a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static APPTYPE a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.APPTYPE
 * JD-Core Version:    0.6.0
 */