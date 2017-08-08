package oicq.wlogin_sdk.sharemem;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

public abstract interface sharemem_service_aidl extends IInterface
{
  public abstract void clear_account(String paramString)
    throws RemoteException;

  public abstract void clear_sig(long paramLong1, long paramLong2)
    throws RemoteException;

  public abstract List<WloginRemoteData> get_all_logined_account(long paramLong, long[] paramArrayOfLong, WloginRemoteData paramWloginRemoteData)
    throws RemoteException;

  public abstract int get_siginfo_remote(String paramString, long paramLong1, long paramLong2, long paramLong3, long paramLong4, int paramInt1, int paramInt2, int paramInt3, int paramInt4, long[] paramArrayOfLong, byte[] paramArrayOfByte1, long paramLong5, long paramLong6, long paramLong7, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, WloginRemoteData paramWloginRemoteData, List<WloginRemoteData> paramList)
    throws RemoteException;

  public abstract void put_account(String paramString, long paramLong, WloginRemoteData paramWloginRemoteData)
    throws RemoteException;

  public abstract int put_siginfo(long paramLong1, long paramLong2, WloginRemoteData paramWloginRemoteData1, WloginRemoteData paramWloginRemoteData2)
    throws RemoteException;

  public abstract int register_callback(server_callback paramserver_callback, long paramLong1, long paramLong2, WloginRemoteData paramWloginRemoteData)
    throws RemoteException;

  public abstract int set_share_test(int paramInt, String paramString)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements sharemem_service_aidl
  {
    private static final String DESCRIPTOR = "oicq.wlogin_sdk.sharemem.sharemem_service_aidl";
    static final int TRANSACTION_clear_account = 6;
    static final int TRANSACTION_clear_sig = 4;
    static final int TRANSACTION_get_all_logined_account = 7;
    static final int TRANSACTION_get_siginfo_remote = 8;
    static final int TRANSACTION_put_account = 5;
    static final int TRANSACTION_put_siginfo = 3;
    static final int TRANSACTION_register_callback = 2;
    static final int TRANSACTION_set_share_test = 1;

    public Stub()
    {
      attachInterface(this, "oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
    }

    public static sharemem_service_aidl asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
      if ((localIInterface != null) && ((localIInterface instanceof sharemem_service_aidl)))
        return (sharemem_service_aidl)localIInterface;
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
        paramParcel2.writeString("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
        return true;
      case 1:
        paramParcel1.enforceInterface("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
        int i3 = set_share_test(paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i3);
        return true;
      case 2:
        paramParcel1.enforceInterface("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
        server_callback localserver_callback = server_callback.Stub.asInterface(paramParcel1.readStrongBinder());
        long l12 = paramParcel1.readLong();
        long l13 = paramParcel1.readLong();
        if (paramParcel1.readInt() != 0);
        for (WloginRemoteData localWloginRemoteData6 = (WloginRemoteData)WloginRemoteData.CREATOR.createFromParcel(paramParcel1); ; localWloginRemoteData6 = null)
        {
          int i2 = register_callback(localserver_callback, l12, l13, localWloginRemoteData6);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i2);
          return true;
        }
      case 3:
        paramParcel1.enforceInterface("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
        long l10 = paramParcel1.readLong();
        long l11 = paramParcel1.readLong();
        WloginRemoteData localWloginRemoteData4;
        if (paramParcel1.readInt() != 0)
        {
          localWloginRemoteData4 = (WloginRemoteData)WloginRemoteData.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0)
            break label306;
        }
        for (WloginRemoteData localWloginRemoteData5 = (WloginRemoteData)WloginRemoteData.CREATOR.createFromParcel(paramParcel1); ; localWloginRemoteData5 = null)
        {
          int i1 = put_siginfo(l10, l11, localWloginRemoteData4, localWloginRemoteData5);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i1);
          return true;
          localWloginRemoteData4 = null;
          break;
        }
      case 4:
        paramParcel1.enforceInterface("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
        clear_sig(paramParcel1.readLong(), paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 5:
        paramParcel1.enforceInterface("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
        String str2 = paramParcel1.readString();
        long l9 = paramParcel1.readLong();
        if (paramParcel1.readInt() != 0);
        for (WloginRemoteData localWloginRemoteData3 = (WloginRemoteData)WloginRemoteData.CREATOR.createFromParcel(paramParcel1); ; localWloginRemoteData3 = null)
        {
          put_account(str2, l9, localWloginRemoteData3);
          paramParcel2.writeNoException();
          return true;
        }
      case 6:
        paramParcel1.enforceInterface("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
        clear_account(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 7:
        label306: paramParcel1.enforceInterface("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
        long l8 = paramParcel1.readLong();
        long[] arrayOfLong2 = paramParcel1.createLongArray();
        if (paramParcel1.readInt() != 0);
        for (WloginRemoteData localWloginRemoteData2 = (WloginRemoteData)WloginRemoteData.CREATOR.createFromParcel(paramParcel1); ; localWloginRemoteData2 = null)
        {
          List localList = get_all_logined_account(l8, arrayOfLong2, localWloginRemoteData2);
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(localList);
          return true;
        }
      case 8:
      }
      paramParcel1.enforceInterface("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
      String str1 = paramParcel1.readString();
      long l1 = paramParcel1.readLong();
      long l2 = paramParcel1.readLong();
      long l3 = paramParcel1.readLong();
      long l4 = paramParcel1.readLong();
      int i = paramParcel1.readInt();
      int j = paramParcel1.readInt();
      int k = paramParcel1.readInt();
      int m = paramParcel1.readInt();
      long[] arrayOfLong1 = paramParcel1.createLongArray();
      byte[] arrayOfByte1 = paramParcel1.createByteArray();
      long l5 = paramParcel1.readLong();
      long l6 = paramParcel1.readLong();
      long l7 = paramParcel1.readLong();
      byte[] arrayOfByte2 = paramParcel1.createByteArray();
      byte[] arrayOfByte3 = paramParcel1.createByteArray();
      byte[] arrayOfByte4 = paramParcel1.createByteArray();
      if (paramParcel1.readInt() != 0);
      for (WloginRemoteData localWloginRemoteData1 = (WloginRemoteData)WloginRemoteData.CREATOR.createFromParcel(paramParcel1); ; localWloginRemoteData1 = null)
      {
        ArrayList localArrayList = new ArrayList();
        int n = get_siginfo_remote(str1, l1, l2, l3, l4, i, j, k, m, arrayOfLong1, arrayOfByte1, l5, l6, l7, arrayOfByte2, arrayOfByte3, arrayOfByte4, localWloginRemoteData1, localArrayList);
        paramParcel2.writeNoException();
        paramParcel2.writeInt(n);
        paramParcel2.writeTypedList(localArrayList);
        return true;
      }
    }

    private static class Proxy
      implements sharemem_service_aidl
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

      public void clear_account(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
          localParcel1.writeString(paramString);
          this.mRemote.transact(6, localParcel1, localParcel2, 0);
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

      public void clear_sig(long paramLong1, long paramLong2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
          localParcel1.writeLong(paramLong1);
          localParcel1.writeLong(paramLong2);
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
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

      public String getInterfaceDescriptor()
      {
        return "oicq.wlogin_sdk.sharemem.sharemem_service_aidl";
      }

      public List<WloginRemoteData> get_all_logined_account(long paramLong, long[] paramArrayOfLong, WloginRemoteData paramWloginRemoteData)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
          localParcel1.writeLong(paramLong);
          localParcel1.writeLongArray(paramArrayOfLong);
          if (paramWloginRemoteData != null)
          {
            localParcel1.writeInt(1);
            paramWloginRemoteData.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.mRemote.transact(7, localParcel1, localParcel2, 0);
            localParcel2.readException();
            ArrayList localArrayList = localParcel2.createTypedArrayList(WloginRemoteData.CREATOR);
            return localArrayList;
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

      public int get_siginfo_remote(String paramString, long paramLong1, long paramLong2, long paramLong3, long paramLong4, int paramInt1, int paramInt2, int paramInt3, int paramInt4, long[] paramArrayOfLong, byte[] paramArrayOfByte1, long paramLong5, long paramLong6, long paramLong7, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, WloginRemoteData paramWloginRemoteData, List<WloginRemoteData> paramList)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
          localParcel1.writeString(paramString);
          localParcel1.writeLong(paramLong1);
          localParcel1.writeLong(paramLong2);
          localParcel1.writeLong(paramLong3);
          localParcel1.writeLong(paramLong4);
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeInt(paramInt3);
          localParcel1.writeInt(paramInt4);
          localParcel1.writeLongArray(paramArrayOfLong);
          localParcel1.writeByteArray(paramArrayOfByte1);
          localParcel1.writeLong(paramLong5);
          localParcel1.writeLong(paramLong6);
          localParcel1.writeLong(paramLong7);
          localParcel1.writeByteArray(paramArrayOfByte2);
          localParcel1.writeByteArray(paramArrayOfByte3);
          localParcel1.writeByteArray(paramArrayOfByte4);
          if (paramWloginRemoteData != null)
          {
            localParcel1.writeInt(1);
            paramWloginRemoteData.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.mRemote.transact(8, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            localParcel2.readTypedList(paramList, WloginRemoteData.CREATOR);
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

      public void put_account(String paramString, long paramLong, WloginRemoteData paramWloginRemoteData)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
          localParcel1.writeString(paramString);
          localParcel1.writeLong(paramLong);
          if (paramWloginRemoteData != null)
          {
            localParcel1.writeInt(1);
            paramWloginRemoteData.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.mRemote.transact(5, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
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

      public int put_siginfo(long paramLong1, long paramLong2, WloginRemoteData paramWloginRemoteData1, WloginRemoteData paramWloginRemoteData2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
            localParcel1.writeLong(paramLong1);
            localParcel1.writeLong(paramLong2);
            if (paramWloginRemoteData1 == null)
              continue;
            localParcel1.writeInt(1);
            paramWloginRemoteData1.writeToParcel(localParcel1, 0);
            if (paramWloginRemoteData2 != null)
            {
              localParcel1.writeInt(1);
              paramWloginRemoteData2.writeToParcel(localParcel1, 0);
              this.mRemote.transact(3, localParcel1, localParcel2, 0);
              localParcel2.readException();
              int i = localParcel2.readInt();
              return i;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          localParcel1.writeInt(0);
        }
      }

      public int register_callback(server_callback paramserver_callback, long paramLong1, long paramLong2, WloginRemoteData paramWloginRemoteData)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
          IBinder localIBinder;
          if (paramserver_callback != null)
          {
            localIBinder = paramserver_callback.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeLong(paramLong1);
            localParcel1.writeLong(paramLong2);
            if (paramWloginRemoteData == null)
              break label115;
            localParcel1.writeInt(1);
            paramWloginRemoteData.writeToParcel(localParcel1, 0);
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

      public int set_share_test(int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("oicq.wlogin_sdk.sharemem.sharemem_service_aidl");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
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
 * Qualified Name:     oicq.wlogin_sdk.sharemem.sharemem_service_aidl
 * JD-Core Version:    0.6.0
 */