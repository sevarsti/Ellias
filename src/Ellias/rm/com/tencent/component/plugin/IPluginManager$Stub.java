package com.tencent.component.plugin;

import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

public abstract class IPluginManager$Stub extends Binder
  implements IPluginManager
{
  static final int a = 1;
  static final int b = 2;
  static final int c = 3;
  static final int d = 4;
  static final int e = 5;
  static final int f = 6;
  static final int g = 7;
  static final int h = 8;
  static final int i = 9;
  static final int j = 10;
  static final int k = 11;
  static final int l = 12;
  static final int m = 13;
  static final int n = 14;
  static final int o = 15;
  private static final String p = "com.tencent.component.plugin.IPluginManager";

  public IPluginManager$Stub()
  {
    attachInterface(this, "com.tencent.component.plugin.IPluginManager");
  }

  public static IPluginManager a(IBinder paramIBinder)
  {
    if (paramIBinder == null)
      return null;
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.tencent.component.plugin.IPluginManager");
    if ((localIInterface != null) && ((localIInterface instanceof IPluginManager)))
      return (IPluginManager)localIInterface;
    return new a(paramIBinder);
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
      paramParcel2.writeString("com.tencent.component.plugin.IPluginManager");
      return true;
    case 1:
      paramParcel1.enforceInterface("com.tencent.component.plugin.IPluginManager");
      int i11 = paramParcel1.readInt();
      PluginPlatformConfig localPluginPlatformConfig = null;
      if (i11 != 0)
        localPluginPlatformConfig = (PluginPlatformConfig)PluginPlatformConfig.CREATOR.createFromParcel(paramParcel1);
      a(localPluginPlatformConfig);
      paramParcel2.writeNoException();
      return true;
    case 2:
      paramParcel1.enforceInterface("com.tencent.component.plugin.IPluginManager");
      String str6 = paramParcel1.readString();
      String str7 = paramParcel1.readString();
      int i9 = paramParcel1.readInt();
      PluginInfo localPluginInfo4 = null;
      if (i9 != 0)
        localPluginInfo4 = (PluginInfo)PluginInfo.CREATOR.createFromParcel(paramParcel1);
      boolean bool7 = a(str6, str7, localPluginInfo4);
      paramParcel2.writeNoException();
      if (bool7);
      for (int i10 = 1; ; i10 = 0)
      {
        paramParcel2.writeInt(i10);
        return true;
      }
    case 3:
      paramParcel1.enforceInterface("com.tencent.component.plugin.IPluginManager");
      boolean bool6 = a(paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      int i8 = 0;
      if (bool6)
        i8 = 1;
      paramParcel2.writeInt(i8);
      return true;
    case 4:
      paramParcel1.enforceInterface("com.tencent.component.plugin.IPluginManager");
      boolean bool5 = b(paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      int i7 = 0;
      if (bool5)
        i7 = 1;
      paramParcel2.writeInt(i7);
      return true;
    case 5:
      paramParcel1.enforceInterface("com.tencent.component.plugin.IPluginManager");
      boolean bool4 = c(paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      int i6 = 0;
      if (bool4)
        i6 = 1;
      paramParcel2.writeInt(i6);
      return true;
    case 6:
      paramParcel1.enforceInterface("com.tencent.component.plugin.IPluginManager");
      boolean bool3 = d(paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      int i5 = 0;
      if (bool3)
        i5 = 1;
      paramParcel2.writeInt(i5);
      return true;
    case 7:
      paramParcel1.enforceInterface("com.tencent.component.plugin.IPluginManager");
      boolean bool2 = e(paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      int i4 = 0;
      if (bool2)
        i4 = 1;
      paramParcel2.writeInt(i4);
      return true;
    case 8:
      paramParcel1.enforceInterface("com.tencent.component.plugin.IPluginManager");
      PluginInfo localPluginInfo3 = f(paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      if (localPluginInfo3 != null)
      {
        paramParcel2.writeInt(1);
        localPluginInfo3.writeToParcel(paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
      return true;
    case 9:
      paramParcel1.enforceInterface("com.tencent.component.plugin.IPluginManager");
      PluginInfo localPluginInfo2 = g(paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      if (localPluginInfo2 != null)
      {
        paramParcel2.writeInt(1);
        localPluginInfo2.writeToParcel(paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
      return true;
    case 10:
      paramParcel1.enforceInterface("com.tencent.component.plugin.IPluginManager");
      List localList = a(paramParcel1.readString());
      paramParcel2.writeNoException();
      paramParcel2.writeTypedList(localList);
      return true;
    case 11:
      paramParcel1.enforceInterface("com.tencent.component.plugin.IPluginManager");
      a(paramParcel1.readString(), PluginManageHandler.Stub.a(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    case 12:
      paramParcel1.enforceInterface("com.tencent.component.plugin.IPluginManager");
      String str4 = paramParcel1.readString();
      String str5 = paramParcel1.readString();
      int i3 = paramParcel1.readInt();
      Uri localUri = null;
      if (i3 != 0)
        localUri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
      Intent localIntent = a(str4, str5, localUri);
      paramParcel2.writeNoException();
      if (localIntent != null)
      {
        paramParcel2.writeInt(1);
        localIntent.writeToParcel(paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
      return true;
    case 13:
      paramParcel1.enforceInterface("com.tencent.component.plugin.IPluginManager");
      a(paramParcel1.readString(), paramParcel1.readString(), InstallPluginListener.Stub.a(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    case 14:
      paramParcel1.enforceInterface("com.tencent.component.plugin.IPluginManager");
      String str3 = paramParcel1.readString();
      int i2 = paramParcel1.readInt();
      PluginInfo localPluginInfo1 = null;
      if (i2 != 0)
        localPluginInfo1 = (PluginInfo)PluginInfo.CREATOR.createFromParcel(paramParcel1);
      a(str3, localPluginInfo1, UninstallPluginListener.Stub.a(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    case 15:
    }
    paramParcel1.enforceInterface("com.tencent.component.plugin.IPluginManager");
    String str1 = paramParcel1.readString();
    String str2 = paramParcel1.readString();
    int i1 = paramParcel1.readInt();
    boolean bool1 = false;
    if (i1 != 0)
      bool1 = true;
    a(str1, str2, bool1);
    paramParcel2.writeNoException();
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.IPluginManager.Stub
 * JD-Core Version:    0.6.0
 */