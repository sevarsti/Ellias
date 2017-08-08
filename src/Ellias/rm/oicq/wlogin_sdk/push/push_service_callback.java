package oicq.wlogin_sdk.push;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface push_service_callback extends IInterface
{
  public abstract int push_callback(long paramLong1, long paramLong2, byte[] paramArrayOfByte)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements push_service_callback
  {
    private static final String DESCRIPTOR = "oicq.wlogin_sdk.push.push_service_callback";
    static final int TRANSACTION_push_callback = 1;

    public Stub()
    {
      attachInterface(this, "oicq.wlogin_sdk.push.push_service_callback");
    }

    public static push_service_callback asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("oicq.wlogin_sdk.push.push_service_callback");
      if ((localIInterface != null) && ((localIInterface instanceof push_service_callback)))
        return (push_service_callback)localIInterface;
      return new Proxy(paramIBinder);
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default:
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        paramParcel2.writeString("oicq.wlogin_sdk.push.push_service_callback");
        return true;
      case 1:
      }
      paramParcel1.enforceInterface("oicq.wlogin_sdk.push.push_service_callback");
      int i = push_callback(paramParcel1.readLong(), paramParcel1.readLong(), paramParcel1.createByteArray());
      paramParcel2.writeNoException();
      paramParcel2.writeInt(i);
      return true;
    }

    private static class Proxy
      implements push_service_callback
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public String getInterfaceDescriptor()
      {
        return "oicq.wlogin_sdk.push.push_service_callback";
      }

      public int push_callback(long paramLong1, long paramLong2, byte[] paramArrayOfByte)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("oicq.wlogin_sdk.push.push_service_callback");
          localParcel1.writeLong(paramLong1);
          localParcel1.writeLong(paramLong2);
          localParcel1.writeByteArray(paramArrayOfByte);
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.push.push_service_callback
 * JD-Core Version:    0.6.0
 */