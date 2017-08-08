package com.tencent.component.service;

import android.os.IBinder;
import android.os.Parcel;

class b
  implements ILeafServiceManager
{
  private IBinder a;

  b(IBinder paramIBinder)
  {
    this.a = paramIBinder;
  }

  public IBinder a(String paramString)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.service.ILeafServiceManager");
      localParcel1.writeString(paramString);
      this.a.transact(1, localParcel1, localParcel2, 0);
      localParcel2.readException();
      IBinder localIBinder = localParcel2.readStrongBinder();
      return localIBinder;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
    throw localObject;
  }

  public String a()
  {
    return "com.tencent.component.service.ILeafServiceManager";
  }

  public void a(String paramString, ILeafServiceConnection paramILeafServiceConnection)
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      localParcel.writeInterfaceToken("com.tencent.component.service.ILeafServiceManager");
      localParcel.writeString(paramString);
      IBinder localIBinder = null;
      if (paramILeafServiceConnection != null)
        localIBinder = paramILeafServiceConnection.asBinder();
      localParcel.writeStrongBinder(localIBinder);
      this.a.transact(3, localParcel, null, 1);
      return;
    }
    finally
    {
      localParcel.recycle();
    }
    throw localObject;
  }

  public boolean a(String paramString, IBinder paramIBinder)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.service.ILeafServiceManager");
      localParcel1.writeString(paramString);
      localParcel1.writeStrongBinder(paramIBinder);
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

  public IBinder asBinder()
  {
    return this.a;
  }

  public IBinder b(String paramString)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.component.service.ILeafServiceManager");
      localParcel1.writeString(paramString);
      this.a.transact(2, localParcel1, localParcel2, 0);
      localParcel2.readException();
      IBinder localIBinder = localParcel2.readStrongBinder();
      return localIBinder;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
    throw localObject;
  }

  public void b(String paramString, ILeafServiceConnection paramILeafServiceConnection)
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      localParcel.writeInterfaceToken("com.tencent.component.service.ILeafServiceManager");
      localParcel.writeString(paramString);
      IBinder localIBinder = null;
      if (paramILeafServiceConnection != null)
        localIBinder = paramILeafServiceConnection.asBinder();
      localParcel.writeStrongBinder(localIBinder);
      this.a.transact(4, localParcel, null, 1);
      return;
    }
    finally
    {
      localParcel.recycle();
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.service.b
 * JD-Core Version:    0.6.0
 */