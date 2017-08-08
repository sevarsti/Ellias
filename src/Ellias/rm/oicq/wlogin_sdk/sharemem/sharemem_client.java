package oicq.wlogin_sdk.sharemem;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;
import oicq.wlogin_sdk.request.request_app_signature;
import oicq.wlogin_sdk.request.request_global;
import oicq.wlogin_sdk.tools.util;

public class sharemem_client
{
  public static String SHAREMEM_GET_UINFO_RECEIVED;
  public static String SHAREMEM_SET_UINFO_RECEIVED;
  private static final String TAG = "ShareMemAidl";
  private static String mName;
  private static boolean mServiceChecked = false;
  private static int mServicePid = 0;
  request_app_signature mApkCheck;
  private final server_callback.Stub mCallback = new server_callback.Stub()
  {
    public int request_check_apk(int paramInt, WloginRemoteData paramWloginRemoteData)
      throws RemoteException
    {
      util.LOGD("client invok_callback:" + new Integer(paramInt).toString());
      sharemem_client.mServicePid = server_callback.Stub.getCallingPid();
      new ApkChecker(sharemem_client.this.mContext, sharemem_client.this, sharemem_client.this.mApkCheck, sharemem_client.mServicePid).start();
      return 0;
    }
  };
  private ServiceConnection mConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      util.LOGI("ShareMem onServiceConnected");
      sharemem_client.this.mService = sharemem_service_aidl.Stub.asInterface(paramIBinder);
      try
      {
        sharemem_client.this.mService.set_share_test(sharemem_client.this.mTest, sharemem_client.this.mTestHost);
        sharemem_client.this.mService.register_callback(sharemem_client.this.mCallback, 0L, 0L, new WloginRemoteData());
        return;
      }
      catch (Exception localException)
      {
        util.printException(localException);
      }
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      sharemem_client.this.Log("disconnect service");
      sharemem_client.this.mService = null;
      sharemem_client.mServiceChecked = false;
      sharemem_client.mServicePid = 0;
      sharemem_client.this.reinit();
    }
  };
  Context mContext;
  request_global mG;
  sharemem_service_aidl mService;
  int mTest = 0;
  String mTestHost = "";

  static
  {
    mName = "oicq.wlogin_sdk.sharemem.sharemem_service_1_0";
    SHAREMEM_GET_UINFO_RECEIVED = "oicq.wlogin_sdk.sharemem_client.GET_UINFO_1_0";
    SHAREMEM_SET_UINFO_RECEIVED = "oicq.wlogin_sdk.sharemem_client.SET_UINFO_1_0";
  }

  private void Log(String paramString)
  {
    util.LOGD("ShareMemAidl------ " + paramString + "------");
  }

  public void clear_account(String paramString)
  {
    try
    {
      if (this.mService == null)
      {
        reinit();
        return;
      }
      this.mService.clear_account(paramString);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      reinit();
    }
  }

  public void clear_sig(long paramLong1, long paramLong2)
  {
    try
    {
      if (this.mService == null)
      {
        reinit();
        return;
      }
      this.mService.clear_sig(paramLong1, paramLong2);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      reinit();
    }
  }

  public void deinit()
  {
    this.mContext.unbindService(this.mConnection);
  }

  public List<WloginLoginInfo> get_all_logined_account(long paramLong, long[] paramArrayOfLong)
  {
    ArrayList localArrayList;
    try
    {
      if (this.mService == null)
      {
        reinit();
        return null;
      }
      List localList = this.mService.get_all_logined_account(paramLong, paramArrayOfLong, new WloginRemoteData());
      if (localList != null)
      {
        localArrayList = new ArrayList();
        for (int i = 0; i < localList.size(); i++)
          localArrayList.add(WloginLoginInfo.getWloginLoginInfo((WloginRemoteData)localList.get(i)));
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      reinit();
      localArrayList = new ArrayList();
    }
    return localArrayList;
  }

  public int get_siginfo_remote(String paramString, long paramLong1, long paramLong2, long paramLong3, long paramLong4, int paramInt1, int paramInt2, int paramInt3, int paramInt4, long[] paramArrayOfLong, byte[] paramArrayOfByte1, long paramLong5, long paramLong6, long paramLong7, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, List<WloginRemoteData> paramList)
  {
    try
    {
      if (this.mService == null)
      {
        reinit();
        return -1020;
      }
      int i = this.mService.get_siginfo_remote(paramString, paramLong1, paramLong2, paramLong3, paramLong4, paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfLong, paramArrayOfByte1, paramLong5, paramLong6, paramLong7, paramArrayOfByte2, paramArrayOfByte3, paramArrayOfByte4, new WloginRemoteData(), paramList);
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      reinit();
    }
    return -1020;
  }

  public boolean init(Context paramContext, request_app_signature paramrequest_app_signature)
  {
    this.mContext = paramContext;
    this.mApkCheck = paramrequest_app_signature;
    Intent localIntent = new Intent(mName);
    return this.mContext.bindService(localIntent, this.mConnection, 1);
  }

  public void put_account(String paramString, long paramLong)
  {
    if (!mServiceChecked)
    {
      new ApkChecker(this.mContext, this, this.mApkCheck, mServicePid).start();
      return;
    }
    try
    {
      if (this.mService == null)
      {
        reinit();
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      reinit();
      return;
    }
    this.mService.put_account(paramString, paramLong, new WloginRemoteData());
  }

  public int put_siginfo(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[][] paramArrayOfByte5, byte[] paramArrayOfByte6, byte[] paramArrayOfByte7, byte[] paramArrayOfByte8, byte[] paramArrayOfByte9, byte[] paramArrayOfByte10, byte[] paramArrayOfByte11, byte[] paramArrayOfByte12, byte[] paramArrayOfByte13, byte[] paramArrayOfByte14, byte[] paramArrayOfByte15, byte[] paramArrayOfByte16, byte[] paramArrayOfByte17, byte[][] paramArrayOfByte18)
  {
    if (!mServiceChecked)
    {
      new ApkChecker(this.mContext, this, this.mApkCheck, mServicePid).start();
      return -1018;
    }
    try
    {
      if (this.mService == null)
      {
        reinit();
        return -1020;
      }
      int i = this.mService.put_siginfo(paramLong1, paramLong2, new WloginRemoteData(0L, paramArrayOfByte1, paramArrayOfByte2, paramArrayOfByte3, paramArrayOfByte4, paramArrayOfByte5), new WloginRemoteData(0L, paramLong3, paramLong4, paramLong5, paramLong6, paramArrayOfByte6, paramArrayOfByte7, paramArrayOfByte8, paramArrayOfByte9, paramArrayOfByte10, paramArrayOfByte11, paramArrayOfByte12, paramArrayOfByte13, paramArrayOfByte14, paramArrayOfByte15, paramArrayOfByte16, paramArrayOfByte17, paramArrayOfByte18));
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      reinit();
    }
    return 0;
  }

  public int put_siginfo(long paramLong1, long paramLong2, WloginRemoteData paramWloginRemoteData1, WloginRemoteData paramWloginRemoteData2)
  {
    if (!mServiceChecked)
    {
      new ApkChecker(this.mContext, this, this.mApkCheck, mServicePid).start();
      return -1018;
    }
    try
    {
      if (this.mService == null)
      {
        reinit();
        return -1020;
      }
      int i = this.mService.put_siginfo(paramLong1, paramLong2, paramWloginRemoteData1, paramWloginRemoteData2);
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      reinit();
    }
    return 0;
  }

  void reinit()
  {
    if (this.mService != null)
      return;
    Intent localIntent = new Intent(mName);
    this.mContext.bindService(localIntent, this.mConnection, 1);
  }

  public void set_checked(boolean paramBoolean)
  {
    mServiceChecked = paramBoolean;
  }

  public int set_share_test(int paramInt, String paramString)
  {
    this.mTest = paramInt;
    this.mTestHost = paramString;
    try
    {
      if (this.mService == null)
      {
        reinit();
        return -1020;
      }
      int i = this.mService.set_share_test(paramInt, paramString);
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      reinit();
    }
    return -1020;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.sharemem.sharemem_client
 * JD-Core Version:    0.6.0
 */