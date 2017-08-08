package com.tencent.component.plugin;

import android.os.IBinder;
import android.os.Parcel;

class ao
  implements UninstallPluginListener
{
  private IBinder a;

  ao(IBinder paramIBinder)
  {
    this.a = paramIBinder;
  }

  public void a()
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.plugin.UninstallPluginListener");
      this.a.transact(1, localParcel1, localParcel2, 0);
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

  public void a(String paramString)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.plugin.UninstallPluginListener");
      localParcel1.writeString(paramString);
      this.a.transact(2, localParcel1, localParcel2, 0);
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

  public IBinder asBinder()
  {
    return this.a;
  }

  public String b()
  {
    return "com.tencent.component.plugin.UninstallPluginListener";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.ao
 * JD-Core Version:    0.6.0
 */