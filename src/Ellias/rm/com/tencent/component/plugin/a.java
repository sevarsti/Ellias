package com.tencent.component.plugin;

import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

class a
  implements IPluginManager
{
  private IBinder a;

  a(IBinder paramIBinder)
  {
    this.a = paramIBinder;
  }

  public Intent a(String paramString1, String paramString2, Uri paramUri)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    while (true)
    {
      try
      {
        localParcel1.writeInterfaceToken("com.tencent.component.plugin.IPluginManager");
        localParcel1.writeString(paramString1);
        localParcel1.writeString(paramString2);
        if (paramUri == null)
          continue;
        localParcel1.writeInt(1);
        paramUri.writeToParcel(localParcel1, 0);
        this.a.transact(12, localParcel1, localParcel2, 0);
        localParcel2.readException();
        if (localParcel2.readInt() != 0)
        {
          localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
          return localIntent;
          localParcel1.writeInt(0);
          continue;
        }
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
      Intent localIntent = null;
    }
  }

  public String a()
  {
    return "com.tencent.component.plugin.IPluginManager";
  }

  public List a(String paramString)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.plugin.IPluginManager");
      localParcel1.writeString(paramString);
      this.a.transact(10, localParcel1, localParcel2, 0);
      localParcel2.readException();
      ArrayList localArrayList = localParcel2.createTypedArrayList(PluginInfo.CREATOR);
      return localArrayList;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
    throw localObject;
  }

  public void a(PluginPlatformConfig paramPluginPlatformConfig)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.plugin.IPluginManager");
      if (paramPluginPlatformConfig != null)
      {
        localParcel1.writeInt(1);
        paramPluginPlatformConfig.writeToParcel(localParcel1, 0);
      }
      while (true)
      {
        this.a.transact(1, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
        localParcel1.writeInt(0);
      }
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
    throw localObject;
  }

  public void a(String paramString, PluginInfo paramPluginInfo, UninstallPluginListener paramUninstallPluginListener)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    while (true)
    {
      try
      {
        localParcel1.writeInterfaceToken("com.tencent.component.plugin.IPluginManager");
        localParcel1.writeString(paramString);
        if (paramPluginInfo == null)
          continue;
        localParcel1.writeInt(1);
        paramPluginInfo.writeToParcel(localParcel1, 0);
        if (paramUninstallPluginListener != null)
        {
          localIBinder = paramUninstallPluginListener.asBinder();
          localParcel1.writeStrongBinder(localIBinder);
          this.a.transact(14, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
          localParcel1.writeInt(0);
          continue;
        }
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
      IBinder localIBinder = null;
    }
  }

  public void a(String paramString, PluginManageHandler paramPluginManageHandler)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.plugin.IPluginManager");
      localParcel1.writeString(paramString);
      if (paramPluginManageHandler != null);
      for (IBinder localIBinder = paramPluginManageHandler.asBinder(); ; localIBinder = null)
      {
        localParcel1.writeStrongBinder(localIBinder);
        this.a.transact(11, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
    throw localObject;
  }

  public void a(String paramString1, String paramString2, InstallPluginListener paramInstallPluginListener)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.plugin.IPluginManager");
      localParcel1.writeString(paramString1);
      localParcel1.writeString(paramString2);
      if (paramInstallPluginListener != null);
      for (IBinder localIBinder = paramInstallPluginListener.asBinder(); ; localIBinder = null)
      {
        localParcel1.writeStrongBinder(localIBinder);
        this.a.transact(13, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
    throw localObject;
  }

  public void a(String paramString1, String paramString2, boolean paramBoolean)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.plugin.IPluginManager");
      localParcel1.writeString(paramString1);
      localParcel1.writeString(paramString2);
      int i = 0;
      if (paramBoolean)
        i = 1;
      localParcel1.writeInt(i);
      this.a.transact(15, localParcel1, localParcel2, 0);
      localParcel2.readException();
      return;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
    throw localObject;
  }

  public boolean a(String paramString1, String paramString2)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.plugin.IPluginManager");
      localParcel1.writeString(paramString1);
      localParcel1.writeString(paramString2);
      this.a.transact(3, localParcel1, localParcel2, 0);
      localParcel2.readException();
      int i = localParcel2.readInt();
      int j = 0;
      if (i != 0)
        j = 1;
      return j;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
    throw localObject;
  }

  public boolean a(String paramString1, String paramString2, PluginInfo paramPluginInfo)
  {
    int i = 1;
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    while (true)
    {
      try
      {
        localParcel1.writeInterfaceToken("com.tencent.component.plugin.IPluginManager");
        localParcel1.writeString(paramString1);
        localParcel1.writeString(paramString2);
        if (paramPluginInfo == null)
          continue;
        localParcel1.writeInt(1);
        paramPluginInfo.writeToParcel(localParcel1, 0);
        this.a.transact(2, localParcel1, localParcel2, 0);
        localParcel2.readException();
        int j = localParcel2.readInt();
        if (j != 0)
        {
          return i;
          localParcel1.writeInt(0);
          continue;
        }
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
      i = 0;
    }
  }

  public IBinder asBinder()
  {
    return this.a;
  }

  public boolean b(String paramString1, String paramString2)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.plugin.IPluginManager");
      localParcel1.writeString(paramString1);
      localParcel1.writeString(paramString2);
      this.a.transact(4, localParcel1, localParcel2, 0);
      localParcel2.readException();
      int i = localParcel2.readInt();
      int j = 0;
      if (i != 0)
        j = 1;
      return j;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
    throw localObject;
  }

  public boolean c(String paramString1, String paramString2)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.plugin.IPluginManager");
      localParcel1.writeString(paramString1);
      localParcel1.writeString(paramString2);
      this.a.transact(5, localParcel1, localParcel2, 0);
      localParcel2.readException();
      int i = localParcel2.readInt();
      int j = 0;
      if (i != 0)
        j = 1;
      return j;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
    throw localObject;
  }

  public boolean d(String paramString1, String paramString2)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.plugin.IPluginManager");
      localParcel1.writeString(paramString1);
      localParcel1.writeString(paramString2);
      this.a.transact(6, localParcel1, localParcel2, 0);
      localParcel2.readException();
      int i = localParcel2.readInt();
      int j = 0;
      if (i != 0)
        j = 1;
      return j;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
    throw localObject;
  }

  public boolean e(String paramString1, String paramString2)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.plugin.IPluginManager");
      localParcel1.writeString(paramString1);
      localParcel1.writeString(paramString2);
      this.a.transact(7, localParcel1, localParcel2, 0);
      localParcel2.readException();
      int i = localParcel2.readInt();
      int j = 0;
      if (i != 0)
        j = 1;
      return j;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
    throw localObject;
  }

  public PluginInfo f(String paramString1, String paramString2)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.plugin.IPluginManager");
      localParcel1.writeString(paramString1);
      localParcel1.writeString(paramString2);
      this.a.transact(8, localParcel1, localParcel2, 0);
      localParcel2.readException();
      if (localParcel2.readInt() != 0)
      {
        localPluginInfo = (PluginInfo)PluginInfo.CREATOR.createFromParcel(localParcel2);
        return localPluginInfo;
      }
      PluginInfo localPluginInfo = null;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
  }

  public PluginInfo g(String paramString1, String paramString2)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.plugin.IPluginManager");
      localParcel1.writeString(paramString1);
      localParcel1.writeString(paramString2);
      this.a.transact(9, localParcel1, localParcel2, 0);
      localParcel2.readException();
      if (localParcel2.readInt() != 0)
      {
        localPluginInfo = (PluginInfo)PluginInfo.CREATOR.createFromParcel(localParcel2);
        return localPluginInfo;
      }
      PluginInfo localPluginInfo = null;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.a
 * JD-Core Version:    0.6.0
 */