package com.tencent.component.plugin;

import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public abstract class PluginManageHandler$Stub extends Binder
  implements PluginManageHandler
{
  static final int a = 1;
  private static final String b = "com.tencent.component.plugin.PluginManageHandler";

  public PluginManageHandler$Stub()
  {
    attachInterface(this, "com.tencent.component.plugin.PluginManageHandler");
  }

  public static PluginManageHandler a(IBinder paramIBinder)
  {
    if (paramIBinder == null)
      return null;
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.tencent.component.plugin.PluginManageHandler");
    if ((localIInterface != null) && ((localIInterface instanceof PluginManageHandler)))
      return (PluginManageHandler)localIInterface;
    return new n(paramIBinder);
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
      paramParcel2.writeString("com.tencent.component.plugin.PluginManageHandler");
      return true;
    case 1:
    }
    paramParcel1.enforceInterface("com.tencent.component.plugin.PluginManageHandler");
    String str = paramParcel1.readString();
    Uri localUri;
    if (paramParcel1.readInt() != 0)
    {
      localUri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
      Intent localIntent = a(str, localUri);
      paramParcel2.writeNoException();
      if (localIntent == null)
        break label118;
      paramParcel2.writeInt(1);
      localIntent.writeToParcel(paramParcel2, 1);
    }
    while (true)
    {
      return true;
      localUri = null;
      break;
      label118: paramParcel2.writeInt(0);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginManageHandler.Stub
 * JD-Core Version:    0.6.0
 */