package oicq.wlogin_sdk.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import oicq.wlogin_sdk.request.WtloginHelper.HelperHandler;
import oicq.wlogin_sdk.tools.util;

public class push_client
{
  public static String PUSH_GET_UINFO_RECEIVED;
  public static String PUSH_SET_UINFO_RECEIVED;
  static int _test = 0;
  static String _test_host = "";
  private static String _uinfo_file_name;
  private static String mName = "oicq.wlogin_sdk.push.push_service_1_0";
  private WtloginHelper.HelperHandler _hander;
  private int _msg_type = 0;
  private final push_service_callback.Stub mCallback = new push_service_callback.Stub()
  {
    public int push_callback(long paramLong1, long paramLong2, byte[] paramArrayOfByte)
      throws RemoteException
    {
      util.LOGD("client push_callback");
      try
      {
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
        localObjectOutputStream.writeObject(new Long(paramLong1));
        localObjectOutputStream.writeObject(new Long(paramLong2));
        localObjectOutputStream.writeObject(paramArrayOfByte);
        byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
        Message localMessage = new Message();
        localMessage.what = util.ASYN_TRANSPORT_PUSH;
        Bundle localBundle = new Bundle();
        localBundle.putByteArray("param", arrayOfByte);
        localMessage.setData(localBundle);
        push_client.this._hander.sendMessage(localMessage);
        return 0;
      }
      catch (Exception localException)
      {
      }
      return 0;
    }
  };
  private ServiceConnection mConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      util.LOGD("", "onServiceConnected");
      push_client.this.mService = push_service_aidl.Stub.asInterface(paramIBinder);
      push_client.this.set_push_test(push_client._test, push_client._test_host);
      try
      {
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        new ObjectOutputStream(localByteArrayOutputStream).write(new byte[4]);
        Message localMessage = new Message();
        localMessage.what = util.ASYN_PUSH_CONNECTED;
        Bundle localBundle = new Bundle();
        localBundle.putByteArray("param", localByteArrayOutputStream.toByteArray());
        localMessage.setData(localBundle);
        push_client.this._hander.sendMessage(localMessage);
        return;
      }
      catch (Exception localException)
      {
      }
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      util.LOGD("", "disconnect service");
      push_client.this.mService = null;
      push_client.this.reInit();
      try
      {
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        new ObjectOutputStream(localByteArrayOutputStream).write(new byte[4]);
        Message localMessage = new Message();
        localMessage.what = util.ASYN_PUSH_DISCONNECTED;
        Bundle localBundle = new Bundle();
        localBundle.putByteArray("param", localByteArrayOutputStream.toByteArray());
        localMessage.setData(localBundle);
        push_client.this._hander.sendMessage(localMessage);
        return;
      }
      catch (Exception localException)
      {
      }
    }
  };
  private Context mContext;
  private push_service_aidl mService;

  static
  {
    _uinfo_file_name = "push_uin_appid_info";
    PUSH_GET_UINFO_RECEIVED = "oicq.wlogin_sdk.push.GET_UINFO_1_0";
    PUSH_SET_UINFO_RECEIVED = "oicq.wlogin_sdk.push.SET_UINFO_1_0";
  }

  // ERROR //
  public static uin_app_info[] loadUinPush(Context paramContext)
  {
    // Byte code:
    //   0: new 78	java/io/ObjectInputStream
    //   3: dup
    //   4: new 80	java/io/BufferedInputStream
    //   7: dup
    //   8: aload_0
    //   9: getstatic 39	oicq/wlogin_sdk/push/push_client:_uinfo_file_name	Ljava/lang/String;
    //   12: invokevirtual 86	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   15: invokespecial 89	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   18: invokespecial 90	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   21: astore_1
    //   22: aload_1
    //   23: invokevirtual 94	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   26: checkcast 96	[Loicq/wlogin_sdk/push/uin_app_info;
    //   29: astore 5
    //   31: aload_1
    //   32: invokevirtual 99	java/io/ObjectInputStream:close	()V
    //   35: aload 5
    //   37: areturn
    //   38: astore 9
    //   40: aconst_null
    //   41: areturn
    //   42: astore_2
    //   43: new 101	java/io/StringWriter
    //   46: dup
    //   47: invokespecial 102	java/io/StringWriter:<init>	()V
    //   50: astore_3
    //   51: new 104	java/io/PrintWriter
    //   54: dup
    //   55: aload_3
    //   56: iconst_1
    //   57: invokespecial 107	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   60: astore 4
    //   62: aload_2
    //   63: aload 4
    //   65: invokevirtual 111	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   68: aload 4
    //   70: invokevirtual 114	java/io/PrintWriter:flush	()V
    //   73: aload_3
    //   74: invokevirtual 115	java/io/StringWriter:flush	()V
    //   77: ldc 117
    //   79: aload_3
    //   80: invokevirtual 121	java/io/StringWriter:toString	()Ljava/lang/String;
    //   83: invokestatic 127	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;Ljava/lang/String;)V
    //   86: aconst_null
    //   87: areturn
    //   88: astore 6
    //   90: new 101	java/io/StringWriter
    //   93: dup
    //   94: invokespecial 102	java/io/StringWriter:<init>	()V
    //   97: astore 7
    //   99: new 104	java/io/PrintWriter
    //   102: dup
    //   103: aload 7
    //   105: iconst_1
    //   106: invokespecial 107	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   109: astore 8
    //   111: aload 6
    //   113: aload 8
    //   115: invokevirtual 111	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   118: aload 8
    //   120: invokevirtual 114	java/io/PrintWriter:flush	()V
    //   123: aload 7
    //   125: invokevirtual 115	java/io/StringWriter:flush	()V
    //   128: ldc 117
    //   130: aload 7
    //   132: invokevirtual 121	java/io/StringWriter:toString	()Ljava/lang/String;
    //   135: invokestatic 127	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;Ljava/lang/String;)V
    //   138: aconst_null
    //   139: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	22	38	java/lang/Exception
    //   22	31	42	java/lang/Exception
    //   31	35	88	java/lang/Exception
  }

  // ERROR //
  public static void saveUinPush(Context paramContext, uin_app_info[] paramArrayOfuin_app_info)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 131	java/io/ObjectOutputStream
    //   5: dup
    //   6: new 133	java/io/BufferedOutputStream
    //   9: dup
    //   10: aload_0
    //   11: getstatic 39	oicq/wlogin_sdk/push/push_client:_uinfo_file_name	Ljava/lang/String;
    //   14: iconst_0
    //   15: invokevirtual 137	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   18: invokespecial 140	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   21: invokespecial 141	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   24: astore_3
    //   25: aload_3
    //   26: aload_1
    //   27: invokevirtual 145	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   30: aload_3
    //   31: astore_2
    //   32: aload_2
    //   33: invokevirtual 146	java/io/ObjectOutputStream:close	()V
    //   36: return
    //   37: astore 4
    //   39: new 101	java/io/StringWriter
    //   42: dup
    //   43: invokespecial 102	java/io/StringWriter:<init>	()V
    //   46: astore 5
    //   48: new 104	java/io/PrintWriter
    //   51: dup
    //   52: aload 5
    //   54: iconst_1
    //   55: invokespecial 107	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   58: astore 6
    //   60: aload 4
    //   62: aload 6
    //   64: invokevirtual 111	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   67: aload 6
    //   69: invokevirtual 114	java/io/PrintWriter:flush	()V
    //   72: aload 5
    //   74: invokevirtual 115	java/io/StringWriter:flush	()V
    //   77: ldc 117
    //   79: aload 5
    //   81: invokevirtual 121	java/io/StringWriter:toString	()Ljava/lang/String;
    //   84: invokestatic 127	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;Ljava/lang/String;)V
    //   87: goto -55 -> 32
    //   90: astore 7
    //   92: new 101	java/io/StringWriter
    //   95: dup
    //   96: invokespecial 102	java/io/StringWriter:<init>	()V
    //   99: astore 8
    //   101: new 104	java/io/PrintWriter
    //   104: dup
    //   105: aload 8
    //   107: iconst_1
    //   108: invokespecial 107	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   111: astore 9
    //   113: aload 7
    //   115: aload 9
    //   117: invokevirtual 111	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   120: aload 9
    //   122: invokevirtual 114	java/io/PrintWriter:flush	()V
    //   125: aload 8
    //   127: invokevirtual 115	java/io/StringWriter:flush	()V
    //   130: ldc 117
    //   132: aload 8
    //   134: invokevirtual 121	java/io/StringWriter:toString	()Ljava/lang/String;
    //   137: invokestatic 127	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;Ljava/lang/String;)V
    //   140: return
    //   141: astore 4
    //   143: aload_3
    //   144: astore_2
    //   145: goto -106 -> 39
    //
    // Exception table:
    //   from	to	target	type
    //   2	25	37	java/lang/Exception
    //   32	36	90	java/lang/Exception
    //   25	30	141	java/lang/Exception
  }

  public void addUinPush(uin_app_info paramuin_app_info)
  {
    uin_app_info[] arrayOfuin_app_info1 = loadUinPush(this.mContext);
    int i = 0;
    if (arrayOfuin_app_info1 != null)
      i = arrayOfuin_app_info1.length;
    int j = 0;
    uin_app_info[] arrayOfuin_app_info2;
    if (j >= i)
      arrayOfuin_app_info2 = new uin_app_info[i + 1];
    for (int k = 0; ; k++)
    {
      if (k >= i)
      {
        arrayOfuin_app_info2[k] = paramuin_app_info;
        saveUinPush(this.mContext, arrayOfuin_app_info2);
        return;
        if ((arrayOfuin_app_info1[j]._uin == paramuin_app_info._uin) && (arrayOfuin_app_info1[j]._appid == paramuin_app_info._appid) && (arrayOfuin_app_info1[j]._sub_appid == paramuin_app_info._sub_appid))
        {
          arrayOfuin_app_info1[j] = paramuin_app_info;
          saveUinPush(this.mContext, arrayOfuin_app_info1);
          return;
        }
        j++;
        break;
      }
      arrayOfuin_app_info2[k] = arrayOfuin_app_info1[k];
    }
  }

  public void deinit()
  {
    this.mContext.unbindService(this.mConnection);
  }

  public void deleteUinPush(long paramLong1, long paramLong2, long paramLong3)
  {
    uin_app_info[] arrayOfuin_app_info1 = loadUinPush(this.mContext);
    if (arrayOfuin_app_info1 == null)
    {
      return;
      break label14;
      break label36;
    }
    label14: 
    while (arrayOfuin_app_info1.length == 0);
    int i = arrayOfuin_app_info1.length;
    label36: uin_app_info[] arrayOfuin_app_info2;
    int k;
    int m;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        if (j >= i)
          break;
        arrayOfuin_app_info2 = new uin_app_info[i - 1];
        k = 0;
        m = 0;
        if (j < i)
          break label121;
        saveUinPush(this.mContext, arrayOfuin_app_info2);
        return;
      }
      if ((arrayOfuin_app_info1[j]._uin == paramLong1) && (arrayOfuin_app_info1[j]._appid == paramLong2) && (arrayOfuin_app_info1[j]._sub_appid == paramLong3))
        break;
    }
    label121: int n;
    if (k == j)
      n = m;
    while (true)
    {
      k++;
      m = n;
      break;
      n = m + 1;
      arrayOfuin_app_info2[m] = arrayOfuin_app_info1[k];
    }
  }

  public boolean init(Context paramContext, WtloginHelper.HelperHandler paramHelperHandler, int paramInt)
  {
    this.mContext = paramContext;
    this._hander = paramHelperHandler;
    this._msg_type = paramInt;
    Intent localIntent = new Intent(mName);
    this.mContext.bindService(localIntent, this.mConnection, 1);
    return false;
  }

  void reInit()
  {
    if (this.mService != null)
      return;
    Intent localIntent = new Intent(mName);
    this.mContext.bindService(localIntent, this.mConnection, 1);
  }

  public int register(long paramLong1, long paramLong2, long paramLong3, String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, byte[] paramArrayOfByte3, int paramInt2)
  {
    int i = 0;
    try
    {
      push_service_aidl localpush_service_aidl = this.mService;
      i = 0;
      if (localpush_service_aidl == null)
      {
        reInit();
        return 0;
        int j = this._msg_type;
        uin_app_info localuin_app_info = new uin_app_info(paramLong1, paramLong2, paramLong3, paramArrayOfByte1, paramArrayOfByte2, paramInt1, paramArrayOfByte3, paramInt2, paramString2, paramString3, paramString1, j);
        i = this.mService.register(this.mCallback, paramLong1, paramLong2, localuin_app_info);
        util.LOGD("ret=" + i);
        if (i == 0)
          addUinPush(localuin_app_info);
        return i;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        reInit();
        continue;
        if (paramLong3 != 0L)
          continue;
        paramLong3 = 1L;
      }
    }
  }

  public int set_push_test(int paramInt, String paramString)
  {
    int i = 0;
    _test = paramInt;
    _test_host = paramString;
    try
    {
      push_service_aidl localpush_service_aidl = this.mService;
      i = 0;
      if (localpush_service_aidl == null)
      {
        reInit();
        return 0;
      }
      i = this.mService.set_push_test(paramInt, paramString);
      util.LOGD("ret=" + i);
      return i;
    }
    catch (Exception localException)
    {
      while (true)
        reInit();
    }
  }

  public int un_register(long paramLong1, long paramLong2, long paramLong3, int paramInt)
  {
    int i = 0;
    try
    {
      push_service_aidl localpush_service_aidl = this.mService;
      i = 0;
      if (localpush_service_aidl == null)
      {
        reInit();
        return 0;
        i = this.mService.un_register(paramLong1, paramLong2, paramLong3, paramInt);
        util.LOGD("ret=" + i);
        if (i == 0)
          deleteUinPush(paramLong1, paramLong2, paramLong3);
        return i;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        reInit();
        continue;
        if (paramLong3 != 0L)
          continue;
        paramLong3 = 1L;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.push.push_client
 * JD-Core Version:    0.6.0
 */