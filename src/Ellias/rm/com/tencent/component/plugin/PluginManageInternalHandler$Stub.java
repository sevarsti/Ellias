package com.tencent.component.plugin;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract class PluginManageInternalHandler$Stub extends Binder
  implements PluginManageInternalHandler
{
  static final int a = 1;
  private static final String b = "com.tencent.component.plugin.PluginManageInternalHandler";

  public PluginManageInternalHandler$Stub()
  {
    attachInterface(this, "com.tencent.component.plugin.PluginManageInternalHandler");
  }

  public static PluginManageInternalHandler a(IBinder paramIBinder)
  {
    if (paramIBinder == null)
      return null;
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.tencent.component.plugin.PluginManageInternalHandler");
    if ((localIInterface != null) && ((localIInterface instanceof PluginManageInternalHandler)))
      return (PluginManageInternalHandler)localIInterface;
    return new o(paramIBinder);
  }

  public IBinder asBinder()
  {
    return this;
  }

  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
  {
    switch (paramInt1)
    {
    default:
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
    case 1598968902:
      paramParcel2.writeString("com.tencent.component.plugin.PluginManageInternalHandler");
      return true;
    case 1:
    }
    paramParcel1.enforceInterface("com.tencent.component.plugin.PluginManageInternalHandler");
    boolean bool = a(paramParcel1.readString());
    paramParcel2.writeNoException();
    if (bool);
    for (int i = 1; ; i = 0)
    {
      paramParcel2.writeInt(i);
      return true;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginManageInternalHandler.Stub
 * JD-Core Version:    0.6.0
 */