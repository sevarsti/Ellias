package com.tencent.component.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract class ILeafServiceManager$Stub extends Binder
  implements ILeafServiceManager
{
  static final int a = 1;
  static final int b = 2;
  static final int c = 3;
  static final int d = 4;
  static final int e = 5;
  private static final String f = "com.tencent.component.service.ILeafServiceManager";

  public ILeafServiceManager$Stub()
  {
    attachInterface(this, "com.tencent.component.service.ILeafServiceManager");
  }

  public static ILeafServiceManager a(IBinder paramIBinder)
  {
    if (paramIBinder == null)
      return null;
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.tencent.component.service.ILeafServiceManager");
    if ((localIInterface != null) && ((localIInterface instanceof ILeafServiceManager)))
      return (ILeafServiceManager)localIInterface;
    return new b(paramIBinder);
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
      paramParcel2.writeString("com.tencent.component.service.ILeafServiceManager");
      return true;
    case 1:
      paramParcel1.enforceInterface("com.tencent.component.service.ILeafServiceManager");
      IBinder localIBinder2 = a(paramParcel1.readString());
      paramParcel2.writeNoException();
      paramParcel2.writeStrongBinder(localIBinder2);
      return true;
    case 2:
      paramParcel1.enforceInterface("com.tencent.component.service.ILeafServiceManager");
      IBinder localIBinder1 = b(paramParcel1.readString());
      paramParcel2.writeNoException();
      paramParcel2.writeStrongBinder(localIBinder1);
      return true;
    case 3:
      paramParcel1.enforceInterface("com.tencent.component.service.ILeafServiceManager");
      a(paramParcel1.readString(), ILeafServiceConnection.Stub.a(paramParcel1.readStrongBinder()));
      return true;
    case 4:
      paramParcel1.enforceInterface("com.tencent.component.service.ILeafServiceManager");
      b(paramParcel1.readString(), ILeafServiceConnection.Stub.a(paramParcel1.readStrongBinder()));
      return true;
    case 5:
    }
    paramParcel1.enforceInterface("com.tencent.component.service.ILeafServiceManager");
    boolean bool = a(paramParcel1.readString(), paramParcel1.readStrongBinder());
    paramParcel2.writeNoException();
    if (bool);
    for (int i = 1; ; i = 0)
    {
      paramParcel2.writeInt(i);
      return true;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.service.ILeafServiceManager.Stub
 * JD-Core Version:    0.6.0
 */