package CobraHallQmiProto;

import java.io.Serializable;

public final class PLUGINSHOWMODEL
  implements Serializable
{
  public static final PLUGINSHOWMODEL PLUGINSHOWMODEL_FORCESHOW;
  public static final PLUGINSHOWMODEL PLUGINSHOWMODEL_NORMAL;
  public static final int _PLUGINSHOWMODEL_FORCESHOW = 1;
  public static final int _PLUGINSHOWMODEL_NORMAL;
  private static PLUGINSHOWMODEL[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!PLUGINSHOWMODEL.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new PLUGINSHOWMODEL[2];
      PLUGINSHOWMODEL_NORMAL = new PLUGINSHOWMODEL(0, 0, "PLUGINSHOWMODEL_NORMAL");
      PLUGINSHOWMODEL_FORCESHOW = new PLUGINSHOWMODEL(1, 1, "PLUGINSHOWMODEL_FORCESHOW");
      return;
    }
  }

  private PLUGINSHOWMODEL(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static PLUGINSHOWMODEL a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static PLUGINSHOWMODEL a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.PLUGINSHOWMODEL
 * JD-Core Version:    0.6.0
 */