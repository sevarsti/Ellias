package CobraHallQmiProto;

import java.io.Serializable;

public final class GAMEACTIONTYPE
  implements Serializable
{
  public static final GAMEACTIONTYPE GAT_LEAVE;
  public static final GAMEACTIONTYPE GAT_PLAYING;
  public static final GAMEACTIONTYPE GAT_START;
  public static final int _GAT_LEAVE = 2;
  public static final int _GAT_PLAYING = 1;
  public static final int _GAT_START;
  private static GAMEACTIONTYPE[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!GAMEACTIONTYPE.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new GAMEACTIONTYPE[3];
      GAT_START = new GAMEACTIONTYPE(0, 0, "GAT_START");
      GAT_PLAYING = new GAMEACTIONTYPE(1, 1, "GAT_PLAYING");
      GAT_LEAVE = new GAMEACTIONTYPE(2, 2, "GAT_LEAVE");
      return;
    }
  }

  private GAMEACTIONTYPE(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static GAMEACTIONTYPE a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static GAMEACTIONTYPE a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.GAMEACTIONTYPE
 * JD-Core Version:    0.6.0
 */