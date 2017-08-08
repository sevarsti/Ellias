package com.tencent.component.plugin;

import android.os.IBinder;
import android.os.Parcel;

class o
  implements PluginManageInternalHandler
{
  private IBinder a;

  o(IBinder paramIBinder)
  {
    this.a = paramIBinder;
  }

  public String a()
  {
    return "com.tencent.component.plugin.PluginManageInternalHandler";
  }

  public boolean a(String paramString)
  {
    int i = 1;
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.plugin.PluginManageInternalHandler");
      localParcel1.writeString(paramString);
      this.a.transact(1, localParcel1, localParcel2, 0);
      localParcel2.readException();
      int j = localParcel2.readInt();
      if (j != 0)
        return i;
      i = 0;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
  }

  public IBinder asBinder()
  {
    return this.a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.o
 * JD-Core Version:    0.6.0
 */