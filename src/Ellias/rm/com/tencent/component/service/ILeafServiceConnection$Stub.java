package com.tencent.component.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract class ILeafServiceConnection$Stub extends Binder
  implements ILeafServiceConnection
{
  static final int a = 1;
  private static final String b = "com.tencent.component.service.ILeafServiceConnection";

  public ILeafServiceConnection$Stub()
  {
    attachInterface(this, "com.tencent.component.service.ILeafServiceConnection");
  }

  public static ILeafServiceConnection a(IBinder paramIBinder)
  {
    if (paramIBinder == null)
      return null;
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.tencent.component.service.ILeafServiceConnection");
    if ((localIInterface != null) && ((localIInterface instanceof ILeafServiceConnection)))
      return (ILeafServiceConnection)localIInterface;
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
      paramParcel2.writeString("com.tencent.component.service.ILeafServiceConnection");
      return true;
    case 1:
    }
    paramParcel1.enforceInterface("com.tencent.component.service.ILeafServiceConnection");
    a(paramParcel1.readString(), paramParcel1.readStrongBinder());
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.service.ILeafServiceConnection.Stub
 * JD-Core Version:    0.6.0
 */