package com.tencent.component.plugin;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract class UninstallPluginListener$Stub extends Binder
  implements UninstallPluginListener
{
  static final int a = 1;
  static final int b = 2;
  private static final String c = "com.tencent.component.plugin.UninstallPluginListener";

  public UninstallPluginListener$Stub()
  {
    attachInterface(this, "com.tencent.component.plugin.UninstallPluginListener");
  }

  public static UninstallPluginListener a(IBinder paramIBinder)
  {
    if (paramIBinder == null)
      return null;
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.tencent.component.plugin.UninstallPluginListener");
    if ((localIInterface != null) && ((localIInterface instanceof UninstallPluginListener)))
      return (UninstallPluginListener)localIInterface;
    return new ao(paramIBinder);
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
      paramParcel2.writeString("com.tencent.component.plugin.UninstallPluginListener");
      return true;
    case 1:
      paramParcel1.enforceInterface("com.tencent.component.plugin.UninstallPluginListener");
      a();
      paramParcel2.writeNoException();
      return true;
    case 2:
    }
    paramParcel1.enforceInterface("com.tencent.component.plugin.UninstallPluginListener");
    a(paramParcel1.readString());
    paramParcel2.writeNoException();
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.UninstallPluginListener.Stub
 * JD-Core Version:    0.6.0
 */