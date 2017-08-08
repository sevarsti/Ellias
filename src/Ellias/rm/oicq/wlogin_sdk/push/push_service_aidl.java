package oicq.wlogin_sdk.push;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface push_service_aidl extends IInterface
{
  public abstract int invok_callback(int paramInt)
    throws RemoteException;

  public abstract int register(push_service_callback parampush_service_callback, long paramLong1, long paramLong2, uin_app_info paramuin_app_info)
    throws RemoteException;

  public abstract int set_push_test(int paramInt, String paramString)
    throws RemoteException;

  public abstract int un_register(long paramLong1, long paramLong2, long paramLong3, int paramInt)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements push_service_aidl
  {
    private static final String DESCRIPTOR = "oicq.wlogin_sdk.push.push_service_aidl";
    static final int TRANSACTION_invok_callback = 1;
    static final int TRANSACTION_register = 2;
    static final int TRANSACTION_set_push_test = 4;
    static final int TRANSACTION_un_register = 3;

    public Stub()
    {
      attachInterface(this, "oicq.wlogin_sdk.push.push_service_aidl");
    }

    public static push_service_aidl asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("oicq.wlogin_sdk.push.push_service_aidl");
      if ((localIInterface != null) && ((localIInterface instanceof push_service_aidl)))
        return (push_service_aidl)localIInterface;
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
        paramParcel2.writeString("oicq.wlogin_sdk.push.push_service_aidl");
        return true;
      case 1:
        paramParcel1.enforceInterface("oicq.wlogin_sdk.push.push_service_aidl");
        int m = invok_callback(paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(m);
        return true;
      case 2:
        paramParcel1.enforceInterface("oicq.wlogin_sdk.push.push_service_aidl");
        push_service_callback localpush_service_callback = push_service_callback.Stub.asInterface(paramParcel1.readStrongBinder());
        long l1 = paramParcel1.readLong();
        long l2 = paramParcel1.readLong();
        if (paramParcel1.readInt() != 0);
        for (uin_app_info localuin_app_info = (uin_app_info)uin_app_info.CREATOR.createFromParcel(paramParcel1); ; localuin_app_info = null)
        {
          int k = register(localpush_service_callback, l1, l2, localuin_app_info);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(k);
          return true;
        }
      case 3:
        paramParcel1.enforceInterface("oicq.wlogin_sdk.push.push_service_aidl");
        int j = un_register(paramParcel1.readLong(), paramParcel1.readLong(), paramParcel1.readLong(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(j);
        return true;
      case 4:
      }
      paramParcel1.enforceInterface("oicq.wlogin_sdk.push.push_service_aidl");
      int i = set_push_test(paramParcel1.readInt(), paramParcel1.readString());
      paramParcel2.writeNoException();
      paramParcel2.writeInt(i);
      return true;
    }

    private static class Proxy
      implements push_service_aidl
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
        return "oicq.wlogin_sdk.push.push_service_aidl";
      }

      public int invok_callback(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("oicq.wlogin_sdk.push.push_service_aidl");
          localParcel1.writeInt(paramInt);
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

      public int register(push_service_callback parampush_service_callback, long paramLong1, long paramLong2, uin_app_info paramuin_app_info)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("oicq.wlogin_sdk.push.push_service_aidl");
          IBinder localIBinder;
          if (parampush_service_callback != null)
          {
            localIBinder = parampush_service_callback.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeLong(paramLong1);
            localParcel1.writeLong(paramLong2);
            if (paramuin_app_info == null)
              break label115;
            localParcel1.writeInt(1);
            paramuin_app_info.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.mRemote.transact(2, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            return i;
            localIBinder = null;
            break;
            label115: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int set_push_test(int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("oicq.wlogin_sdk.push.push_service_aidl");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
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

      public int un_register(long paramLong1, long paramLong2, long paramLong3, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("oicq.wlogin_sdk.push.push_service_aidl");
          localParcel1.writeLong(paramLong1);
          localParcel1.writeLong(paramLong2);
          localParcel1.writeLong(paramLong3);
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
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
 * Qualified Name:     oicq.wlogin_sdk.push.push_service_aidl
 * JD-Core Version:    0.6.0
 */