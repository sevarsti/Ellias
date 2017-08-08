package com.tencent.tmassistantsdk.a;

import android.os.IBinder;
import android.os.Parcel;

class c
  implements a
{
  private IBinder a;

  c(IBinder paramIBinder)
  {
    this.a = paramIBinder;
  }

  public void a(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceCallback");
      localParcel1.writeString(paramString1);
      localParcel1.writeString(paramString2);
      localParcel1.writeInt(paramInt1);
      localParcel1.writeInt(paramInt2);
      localParcel1.writeString(paramString3);
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

  public void a(String paramString1, String paramString2, long paramLong1, long paramLong2)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceCallback");
      localParcel1.writeString(paramString1);
      localParcel1.writeString(paramString2);
      localParcel1.writeLong(paramLong1);
      localParcel1.writeLong(paramLong2);
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
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.a.c
 * JD-Core Version:    0.6.0
 */