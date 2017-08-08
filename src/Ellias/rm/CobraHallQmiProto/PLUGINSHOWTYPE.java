package CobraHallQmiProto;

import java.io.Serializable;

public final class PLUGINSHOWTYPE
  implements Serializable
{
  public static final PLUGINSHOWTYPE PST_NORUN;
  public static final PLUGINSHOWTYPE PST_RUNNOSHOW;
  public static final PLUGINSHOWTYPE PST_SHOW;
  public static final int _PST_NORUN = 1;
  public static final int _PST_RUNNOSHOW = 2;
  public static final int _PST_SHOW = 3;
  private static PLUGINSHOWTYPE[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!PLUGINSHOWTYPE.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new PLUGINSHOWTYPE[3];
      PST_NORUN = new PLUGINSHOWTYPE(0, 1, "PST_NORUN");
      PST_RUNNOSHOW = new PLUGINSHOWTYPE(1, 2, "PST_RUNNOSHOW");
      PST_SHOW = new PLUGINSHOWTYPE(2, 3, "PST_SHOW");
      return;
    }
  }

  private PLUGINSHOWTYPE(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static PLUGINSHOWTYPE a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static PLUGINSHOWTYPE a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.PLUGINSHOWTYPE
 * JD-Core Version:    0.6.0
 */