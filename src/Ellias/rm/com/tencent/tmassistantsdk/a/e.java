package com.tencent.tmassistantsdk.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.tencent.tmassistantsdk.c.i;

public abstract class e extends Binder
  implements d
{
  public e()
  {
    attachInterface(this, "com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceInterface");
  }

  public static d a(IBinder paramIBinder)
  {
    if (paramIBinder == null)
      return null;
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceInterface");
    if ((localIInterface != null) && ((localIInterface instanceof d)))
      return (d)localIInterface;
    return new f(paramIBinder);
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
      paramParcel2.writeString("com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceInterface");
      return true;
    case 1:
      paramParcel1.enforceInterface("com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceInterface");
      int n = a();
      paramParcel2.writeNoException();
      paramParcel2.writeInt(n);
      return true;
    case 2:
      paramParcel1.enforceInterface("com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceInterface");
      int m = paramParcel1.readInt();
      boolean bool3 = false;
      if (m != 0)
        bool3 = true;
      a(bool3);
      paramParcel2.writeNoException();
      return true;
    case 3:
      paramParcel1.enforceInterface("com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceInterface");
      int k = paramParcel1.readInt();
      boolean bool2 = false;
      if (k != 0)
        bool2 = true;
      b(bool2);
      paramParcel2.writeNoException();
      return true;
    case 4:
      paramParcel1.enforceInterface("com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceInterface");
      a(paramParcel1.readInt());
      paramParcel2.writeNoException();
      return true;
    case 5:
      paramParcel1.enforceInterface("com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceInterface");
      boolean bool1 = b();
      paramParcel2.writeNoException();
      int j = 0;
      if (bool1)
        j = 1;
      paramParcel2.writeInt(j);
      return true;
    case 6:
      paramParcel1.enforceInterface("com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceInterface");
      i locali = a(paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      if (locali != null)
      {
        paramParcel2.writeInt(1);
        locali.writeToParcel(paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
      return true;
    case 7:
      paramParcel1.enforceInterface("com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceInterface");
      int i = a(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readHashMap(getClass().getClassLoader()));
      paramParcel2.writeNoException();
      paramParcel2.writeInt(i);
      return true;
    case 8:
      paramParcel1.enforceInterface("com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceInterface");
      b(paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    case 9:
      paramParcel1.enforceInterface("com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceInterface");
      c(paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    case 10:
      paramParcel1.enforceInterface("com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceInterface");
      a(paramParcel1.readString(), b.a(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    case 11:
    }
    paramParcel1.enforceInterface("com.tencent.tmassistantsdk.aidl.ITMAssistantDownloadSDKServiceInterface");
    b(paramParcel1.readString(), b.a(paramParcel1.readStrongBinder()));
    paramParcel2.writeNoException();
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.a.e
 * JD-Core Version:    0.6.0
 */