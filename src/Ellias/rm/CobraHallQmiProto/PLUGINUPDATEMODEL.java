package CobraHallQmiProto;

import java.io.Serializable;

public final class PLUGINUPDATEMODEL
  implements Serializable
{
  public static final PLUGINUPDATEMODEL PLUGINUPDATEMODEL_AUTO;
  public static final PLUGINUPDATEMODEL PLUGINUPDATEMODEL_NORMAL;
  public static final int _PLUGINUPDATEMODEL_AUTO = 1;
  public static final int _PLUGINUPDATEMODEL_NORMAL;
  private static PLUGINUPDATEMODEL[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!PLUGINUPDATEMODEL.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new PLUGINUPDATEMODEL[2];
      PLUGINUPDATEMODEL_NORMAL = new PLUGINUPDATEMODEL(0, 0, "PLUGINUPDATEMODEL_NORMAL");
      PLUGINUPDATEMODEL_AUTO = new PLUGINUPDATEMODEL(1, 1, "PLUGINUPDATEMODEL_AUTO");
      return;
    }
  }

  private PLUGINUPDATEMODEL(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static PLUGINUPDATEMODEL a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static PLUGINUPDATEMODEL a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.PLUGINUPDATEMODEL
 * JD-Core Version:    0.6.0
 */