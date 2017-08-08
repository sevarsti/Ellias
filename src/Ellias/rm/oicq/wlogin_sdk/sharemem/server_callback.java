package oicq.wlogin_sdk.sharemem;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface server_callback extends IInterface
{
  public abstract int request_check_apk(int paramInt, WloginRemoteData paramWloginRemoteData)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements server_callback
  {
    private static final String DESCRIPTOR = "oicq.wlogin_sdk.sharemem.server_callback";
    static final int TRANSACTION_request_check_apk = 1;

    public Stub()
    {
      attachInterface(this, "oicq.wlogin_sdk.sharemem.server_callback");
    }

    public static server_callback asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("oicq.wlogin_sdk.sharemem.server_callback");
      if ((localIInterface != null) && ((localIInterface instanceof server_callback)))
        return (server_callback)localIInterface;
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
        paramParcel2.writeString("oicq.wlogin_sdk.sharemem.server_callback");
        return true;
      case 1:
      }
      paramParcel1.enforceInterface("oicq.wlogin_sdk.sharemem.server_callback");
      int i = paramParcel1.readInt();
      if (paramParcel1.readInt() != 0);
      for (WloginRemoteData localWloginRemoteData = (WloginRemoteData)WloginRemoteData.CREATOR.createFromParcel(paramParcel1); ; localWloginRemoteData = null)
      {
        int j = request_check_apk(i, localWloginRemoteData);
        paramParcel2.writeNoException();
        paramParcel2.writeInt(j);
        return true;
      }
    }

    private static class Proxy
      implements server_callback
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
        return "oicq.wlogin_sdk.sharemem.server_callback";
      }

      public int request_check_apk(int paramInt, WloginRemoteData paramWloginRemoteData)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("oicq.wlogin_sdk.sharemem.server_callback");
          localParcel1.writeInt(paramInt);
          if (paramWloginRemoteData != null)
          {
            localParcel1.writeInt(1);
            paramWloginRemoteData.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.mRemote.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            return i;
            localParcel1.writeInt(0);
          }
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
 * Qualified Name:     oicq.wlogin_sdk.sharemem.server_callback
 * JD-Core Version:    0.6.0
 */