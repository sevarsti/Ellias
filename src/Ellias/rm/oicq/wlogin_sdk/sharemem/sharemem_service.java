package oicq.wlogin_sdk.sharemem;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;
import oicq.wlogin_sdk.request.WtloginHelper;
import oicq.wlogin_sdk.request.account_sig_info_map;
import oicq.wlogin_sdk.tools.util;

public class sharemem_service extends Service
{
  private static final String TAG = "sharemem_service";
  private account_sig_info_map _account_sig_info_map = new account_sig_info_map(null);
  private server_callback _callback;
  private WtloginHelper _login_helper = null;
  private int count = 0;
  private final sharemem_service_aidl.Stub mBinder = new sharemem_service_aidl.Stub()
  {
    public void clear_account(String paramString)
    {
      sharemem_service.this._account_sig_info_map.clear_account(paramString);
    }

    public void clear_sig(long paramLong1, long paramLong2)
    {
      sharemem_service.this._account_sig_info_map.clear_sig(Long.valueOf(paramLong1), Long.valueOf(paramLong2));
    }

    public List<WloginRemoteData> get_all_logined_account(long paramLong, long[] paramArrayOfLong, WloginRemoteData paramWloginRemoteData)
      throws RemoteException
    {
      ArrayList localArrayList1 = new ArrayList();
      int i = 0;
      List localList;
      ArrayList localArrayList2;
      if ((paramArrayOfLong == null) || (i >= paramArrayOfLong.length))
      {
        localList = sharemem_service.this._account_sig_info_map.get_all_logined_account(paramLong, localArrayList1, false);
        localArrayList2 = null;
        if (localList != null)
          localArrayList2 = new ArrayList();
      }
      for (int j = 0; ; j++)
      {
        if (j >= localList.size())
        {
          util.LOGI("get_all_logined_account size=" + localArrayList2.size());
          return localArrayList2;
          localArrayList1.add(Long.valueOf(paramArrayOfLong[i]));
          i++;
          break;
        }
        localArrayList2.add(((WloginLoginInfo)localList.get(j)).getWloginRemoteData());
      }
    }

    public int get_siginfo_remote(String paramString, long paramLong1, long paramLong2, long paramLong3, long paramLong4, int paramInt1, int paramInt2, int paramInt3, int paramInt4, long[] paramArrayOfLong, byte[] paramArrayOfByte1, long paramLong5, long paramLong6, long paramLong7, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, WloginRemoteData paramWloginRemoteData, List<WloginRemoteData> paramList)
      throws RemoteException
    {
      int i = sharemem_service_aidl.Stub.getCallingPid();
      byte[] arrayOfByte1 = util.getPkgNameFromPid(sharemem_service.this, i).getBytes();
      byte[] arrayOfByte2 = util.get_apk_v(sharemem_service.this, new String(arrayOfByte1));
      byte[] arrayOfByte3 = util.getPkgPublicKeyFromPid(sharemem_service.this, i);
      return sharemem_service.this._login_helper.StubGetRemoteStWithoutPasswd(paramString, paramLong1, paramLong2, paramLong3, paramLong4, paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfLong, arrayOfByte1, paramLong5, paramLong6, paramLong7, arrayOfByte2, arrayOfByte3, paramArrayOfByte4, paramList, sharemem_service.this._account_sig_info_map);
    }

    public void put_account(String paramString, long paramLong, WloginRemoteData paramWloginRemoteData)
      throws RemoteException
    {
      util.LOGI("put_account: name:" + paramString + ",uin:" + paramLong);
      sharemem_service.this._account_sig_info_map.put_account(paramString, Long.valueOf(paramLong));
    }

    public int put_siginfo(long paramLong1, long paramLong2, WloginRemoteData paramWloginRemoteData1, WloginRemoteData paramWloginRemoteData2)
      throws RemoteException
    {
      try
      {
        util.LOGI("put_siginfo1 : uin:" + paramLong1 + ",appid:" + paramLong2 + " caller:" + sharemem_service_aidl.Stub.getCallingPid() + "," + sharemem_service_aidl.Stub.getCallingUid(), sharemem_service.this.getApplicationContext(), paramLong1, 0);
        paramWloginRemoteData1.getLongData();
        List localList1 = paramWloginRemoteData1.getByteData();
        if ((localList1 != null) && (localList1.size() >= 4))
        {
          int i = localList1.size();
          byte[][] arrayOfByte1 = null;
          if (i > 4)
            arrayOfByte1 = new byte[-4 + localList1.size()][];
          List localList2;
          List localList3;
          for (int j = 4; ; j++)
          {
            int k = localList1.size();
            if (j >= k)
            {
              localList2 = paramWloginRemoteData2.getLongData();
              localList3 = paramWloginRemoteData2.getByteData();
              if (localList2 == null)
                break label585;
              if (localList2.size() >= 4)
                break;
              break label585;
            }
            arrayOfByte1[(j - 4)] = ((byte[])localList1.get(j));
          }
          if ((localList3 == null) || (localList3.size() < 12))
            break label589;
          int m = localList3.size();
          byte[][] arrayOfByte2 = null;
          if (m > 12)
            arrayOfByte2 = new byte[-12 + localList3.size()][];
          for (int n = 16; ; n++)
          {
            int i1 = localList3.size();
            if (n >= i1)
              return sharemem_service.this._account_sig_info_map.put_siginfo(paramLong1, paramLong2, ((Long)localList2.get(0)).longValue(), ((Long)localList2.get(1)).longValue(), ((Long)localList2.get(2)).longValue(), ((Long)localList2.get(3)).longValue(), (byte[])localList1.get(0), (byte[])localList1.get(1), (byte[])localList1.get(2), (byte[])localList1.get(3), arrayOfByte1, (byte[])localList3.get(0), (byte[])localList3.get(1), (byte[])localList3.get(2), (byte[])localList3.get(3), (byte[])localList3.get(4), (byte[])localList3.get(5), (byte[])localList3.get(6), (byte[])localList3.get(7), (byte[])localList3.get(8), (byte[])localList3.get(9), (byte[])localList3.get(10), (byte[])localList3.get(11), arrayOfByte2, null);
            arrayOfByte2[(n - 12)] = ((byte[])localList3.get(n));
          }
        }
      }
      catch (Exception localException)
      {
        util.printException(localException);
        return -1020;
      }
      return -1019;
      label585: return -1019;
      label589: return -1019;
    }

    public int register_callback(server_callback paramserver_callback, long paramLong1, long paramLong2, WloginRemoteData paramWloginRemoteData)
      throws RemoteException
    {
      util.LOGI("service register_callback: calling pid=" + sharemem_service_aidl.Stub.getCallingPid() + ", calling uid=" + sharemem_service_aidl.Stub.getCallingUid());
      sharemem_service.this._callback = paramserver_callback;
      sharemem_service.this._callback.request_check_apk(123456, new WloginRemoteData());
      return 0;
    }

    public int set_share_test(int paramInt, String paramString)
      throws RemoteException
    {
      util.LOGI("service set_share_test:" + paramInt + "," + paramString + "calling pid=" + sharemem_service_aidl.Stub.getCallingPid() + ", calling uid=" + sharemem_service_aidl.Stub.getCallingUid());
      sharemem_service.this._login_helper.SetTestHost(paramInt, paramString);
      return 0;
    }
  };

  public IBinder onBind(Intent paramIntent)
  {
    util.LOGI("service on bind:pid=" + sharemem_service_aidl.Stub.getCallingPid() + "," + "uid=" + sharemem_service_aidl.Stub.getCallingUid());
    return this.mBinder;
  }

  public void onCreate()
  {
    util.LOGI("service create");
    this._login_helper = new WtloginHelper(getApplicationContext(), 1);
    Intent localIntent = new Intent(sharemem_client.SHAREMEM_GET_UINFO_RECEIVED);
    getApplicationContext().sendBroadcast(localIntent);
    util.LOGI("onCreate sendBroadcast for SHAREMEM_GET_UINFO_RECEIVED");
  }

  public void onDestroy()
  {
    util.LOGI("service on destroy");
    super.onDestroy();
  }

  public void onRebind(Intent paramIntent)
  {
    util.LOGI("service on rebind");
    super.onRebind(paramIntent);
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    util.LOGI("service start id=" + paramInt);
    super.onStart(paramIntent, paramInt);
  }

  public boolean onUnbind(Intent paramIntent)
  {
    util.LOGI("service on unbind");
    return super.onUnbind(paramIntent);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.sharemem.sharemem_service
 * JD-Core Version:    0.6.0
 */