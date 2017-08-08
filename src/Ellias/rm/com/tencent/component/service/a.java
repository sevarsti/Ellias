package com.tencent.component.service;

import android.os.IBinder;
import android.os.Parcel;

class a
  implements ILeafServiceConnection
{
  private IBinder a;

  a(IBinder paramIBinder)
  {
    this.a = paramIBinder;
  }

  public String a()
  {
    return "com.tencent.component.service.ILeafServiceConnection";
  }

  public void a(String paramString, IBinder paramIBinder)
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      localParcel.writeInterfaceToken("com.tencent.component.service.ILeafServiceConnection");
      localParcel.writeString(paramString);
      localParcel.writeStrongBinder(paramIBinder);
      this.a.transact(1, localParcel, null, 1);
      return;
    }
    finally
    {
      localParcel.recycle();
    }
    throw localObject;
  }

  public IBinder asBinder()
  {
    return this.a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.service.a
 * JD-Core Version:    0.6.0
 */