package oicq.wlogin_sdk.push;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import dalvik.system.PathClassLoader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import oicq.wlogin_sdk.request.oicq_request;
import oicq.wlogin_sdk.tools.util;

public class push_service extends Service
{
  private static final String ACTION_KEEPALIVE = "oicq.wlogin_sdk.push.KEEP_ALIVE";
  private static final String ACTION_RECONNECT = "oicq.wlogin_sdk.push.RECONNECT";
  public static final String ACTION_START = "oicq.wlogin_sdk.push.START";
  public static final String ACTION_STOP = "oicq.wlogin_sdk.push.STOP";
  private static final long INITIAL_RETRY_INTERVAL = 10000L;
  private static final long KEEP_ALIVE_INTERVAL = 270000L;
  private static final int NOTIF_CONNECTED = 0;
  public static final String TAG = "push_service";
  private static int mAppid;
  private static int mAppid0;
  private static long mMaxSeqence = 0L;
  private static int mNotifyId;
  private static int mNotifyId0 = 1610612736;
  private final push_service_aidl.Stub mBinder = new push_service_aidl.Stub()
  {
    public int invok_callback(int paramInt)
      throws RemoteException
    {
      monitorenter;
      try
      {
        util.LOGI("service invok_callback:" + paramInt);
        monitorexit;
        return paramInt;
      }
      finally
      {
        localObject = finally;
        monitorexit;
      }
      throw localObject;
    }

    public int register(push_service_callback parampush_service_callback, long paramLong1, long paramLong2, uin_app_info paramuin_app_info)
      throws RemoteException
    {
      monitorenter;
      try
      {
        uin_appid localuin_appid = new uin_appid(paramuin_app_info._uin, paramuin_app_info._appid, paramuin_app_info._sub_appid);
        uin_app_info localuin_app_info = push_service.this.mPushInfo.get(localuin_appid);
        push_service.this.mPushInfo.put(new uin_appid(paramLong1, paramLong2, paramuin_app_info._sub_appid), new uin_app_info(paramuin_app_info), parampush_service_callback);
        i = 0;
        if (localuin_app_info == null)
        {
          if (paramuin_app_info._msg_type != 0)
          {
            int j = push_service.this.mPushInfo.get_used_appid(paramLong2, paramuin_app_info._sub_appid);
            if (j == 0)
            {
              j = push_service.mAppid;
              push_service.mAppid = 1 + push_service.mAppid;
              if (push_service.mAppid >= push_service.mNotifyId0)
                push_service.mAppid = push_service.mAppid0;
            }
            paramuin_app_info._notify_id = j;
          }
          i = push_service.this.sendRequest(push_service.this.mPush.get_request_register(paramLong1, paramLong2, paramuin_app_info._sub_appid, paramuin_app_info._st, paramuin_app_info._st_key, paramuin_app_info._clear, paramuin_app_info._guid, false));
          util.LOGI("send register request");
        }
        return i;
      }
      catch (Exception localException)
      {
        while (true)
        {
          StringWriter localStringWriter = new StringWriter();
          PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
          localException.printStackTrace(localPrintWriter);
          localPrintWriter.flush();
          localStringWriter.flush();
          util.LOGW("exception:", localStringWriter.toString());
          int i = -1;
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    public int set_push_test(int paramInt, String paramString)
    {
      monitorenter;
      try
      {
        oicq_request.set_push_test(paramInt, paramString);
        monitorexit;
        return 0;
      }
      finally
      {
        localObject = finally;
        monitorexit;
      }
      throw localObject;
    }

    public int un_register(long paramLong1, long paramLong2, long paramLong3, int paramInt)
      throws RemoteException
    {
      monitorenter;
      try
      {
        util.LOGI("service un_register: uin" + new Long(paramLong1).toString() + ", appid" + new Long(paramLong2).toString() + ", sub appid:" + new Long(paramLong3).toString());
        uin_app_info localuin_app_info = push_service.this.mPushInfo.get(new uin_appid(paramLong1, paramLong2, paramLong3));
        int i = 0;
        if (localuin_app_info != null)
        {
          int j = push_service.this.sendRequest(push_service.this.mPush.get_request_unregister(paramLong1, paramLong2, paramLong3, localuin_app_info._st, localuin_app_info._st_key, paramInt, localuin_app_info._guid));
          i = j;
        }
        if (i < 0);
        while (true)
        {
          return i;
          push_service.this.mPushInfo.remove(new uin_appid(paramLong1, paramLong2, paramLong3));
          i = 0;
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }
  };
  private ConnectivityManager mConnMan;
  private boolean mConnected = false;
  private ConnectionThread mConnection;
  private BroadcastReceiver mConnectivityChanged = new BroadcastReceiver()
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      NetworkInfo localNetworkInfo = (NetworkInfo)paramIntent.getParcelableExtra("networkInfo");
      if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()));
      for (boolean bool = true; ; bool = false)
      {
        util.LOGI("Connecting changed: connected=" + bool);
        if (bool)
          push_service.this.reconnectIfNecessary();
        return;
      }
    }
  };
  private NotificationManager mNotifMan;
  private request_push mPush;
  private push_info mPushInfo = new push_info();
  private boolean mStarted = false;
  private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      util.LOGI("Service BroadcastReceiver recved");
      if (paramIntent.getAction().equals(push_client.PUSH_SET_UINFO_RECEIVED))
        try
        {
          ArrayList localArrayList = paramIntent.getParcelableArrayListExtra("UINFO");
          if ((localArrayList != null) && (localArrayList.size() > 0))
            for (int i = 0; ; i++)
            {
              int j = localArrayList.size();
              if (i >= j)
                return;
              uin_appid localuin_appid = new uin_appid(((uin_app_info)localArrayList.get(i))._uin, ((uin_app_info)localArrayList.get(i))._appid, ((uin_app_info)localArrayList.get(i))._sub_appid);
              push_service.this.mPushInfo.put(localuin_appid, new uin_app_info((uin_app_info)localArrayList.get(i)), null);
              if ((push_service.this.mPushInfo.get(localuin_appid) != null) || (!push_service.this.mConnected))
                continue;
              util.LOGI("Service BroadcastReceiver resend register");
              push_service.this.sendRequest(push_service.this.mPush.get_request_register(localuin_appid._uin, localuin_appid._appid, localuin_appid._sub_appid, ((uin_app_info)localArrayList.get(i))._st, ((uin_app_info)localArrayList.get(i))._st_key, ((uin_app_info)localArrayList.get(i))._clear, ((uin_app_info)localArrayList.get(i))._guid, true));
            }
        }
        catch (Exception localException)
        {
          StringWriter localStringWriter = new StringWriter();
          PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
          localException.printStackTrace(localPrintWriter);
          localPrintWriter.flush();
          localStringWriter.flush();
          util.LOGW("exception:", localStringWriter.toString());
        }
    }
  };

  static
  {
    mNotifyId = mNotifyId0;
    mAppid0 = 1073741824;
    mAppid = mAppid0;
  }

  private void handleCrashedService()
  {
    stopKeepAlives();
    start();
  }

  private void hideNotification()
  {
    this.mNotifMan.cancel(0);
  }

  private void keepAlive()
  {
    monitorenter;
    try
    {
      util.LOGI("keepAlive");
      try
      {
        if ((this.mStarted) && (this.mConnection != null))
        {
          if (!this.mConnection.mPingPending)
            break label41;
          this.mConnection.ManulClose();
        }
        while (true)
        {
          monitorexit;
          return;
          label41: this.mConnection.mPingPending = true;
          util.LOGI("mConnection.mPingPending = true");
          this.mConnection.sendKeepAlive();
        }
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          StringWriter localStringWriter = new StringWriter();
          PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
          localIOException.printStackTrace(localPrintWriter);
          localPrintWriter.flush();
          localStringWriter.flush();
          util.LOGW("exception:", localStringWriter.toString());
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void reconnectIfNecessary()
  {
    monitorenter;
    try
    {
      if ((this.mStarted) && (this.mConnection == null))
      {
        util.LOGI("Reconnecting...");
        this.mConnection = new ConnectionThread();
        this.mConnection.start();
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private void setStarted(boolean paramBoolean)
  {
    this.mStarted = paramBoolean;
  }

  private void showNotification(Context paramContext, long paramLong, Class<?> paramClass, int paramInt1, int paramInt2, int paramInt3, push_msg[] paramArrayOfpush_msg)
  {
    for (int i = 0; ; i++)
    {
      Notification localNotification;
      try
      {
        if (i >= paramArrayOfpush_msg.length)
          return;
        if (!util.unsigned_bigthan(paramArrayOfpush_msg[i]._seq, mMaxSeqence))
          continue;
        mMaxSeqence = paramArrayOfpush_msg[i]._seq;
        util.LOGI("showNotification :" + paramClass + ", icon=" + new Integer(paramInt3).toString());
        localNotification = new Notification();
        localNotification.flags = 16;
        localNotification.defaults = -1;
        localNotification.icon = paramInt3;
        localNotification.when = System.currentTimeMillis();
        localNotification.tickerText = new String(paramArrayOfpush_msg[i]._status_title);
        Intent localIntent = new Intent(paramContext, paramClass);
        localIntent.putExtra("BUFFER", paramArrayOfpush_msg[i]._buf);
        PendingIntent localPendingIntent = PendingIntent.getActivity(this, 0, localIntent, 0);
        localNotification.setLatestEventInfo(this, new String(paramArrayOfpush_msg[i]._title), new String(paramArrayOfpush_msg[i]._msg), localPendingIntent);
        if (paramInt1 == 0)
        {
          this.mNotifMan.notify(mNotifyId, localNotification);
          mNotifyId = 1 + mNotifyId;
          if (mNotifyId >= 0)
            continue;
          mNotifyId = mNotifyId0;
        }
      }
      catch (Exception localException)
      {
        util.LOGI("showNotification exception:" + localException.toString());
        StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
        localException.printStackTrace(localPrintWriter);
        localPrintWriter.flush();
        localStringWriter.flush();
        util.LOGW("exception:", localStringWriter.toString());
        return;
      }
      this.mNotifMan.notify(paramInt2, localNotification);
    }
  }

  private void start()
  {
    monitorenter;
    try
    {
      if (this.mStarted)
        Log.w("push_service", "Attempt to start connection that is already active");
      while (true)
      {
        return;
        setStarted(true);
        registerReceiver(this.mConnectivityChanged, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        util.LOGI("Connecting...");
        this.mConnection = new ConnectionThread();
        this.mConnection.start();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void startKeepAlives()
  {
    util.LOGI("startKeepAlives");
    Intent localIntent = new Intent();
    localIntent.setClass(this, push_service.class);
    localIntent.setAction("oicq.wlogin_sdk.push.KEEP_ALIVE");
    PendingIntent localPendingIntent = PendingIntent.getService(this, 0, localIntent, 0);
    ((AlarmManager)getSystemService("alarm")).setRepeating(0, 270000L + System.currentTimeMillis(), 270000L, localPendingIntent);
  }

  private void stop()
  {
    monitorenter;
    try
    {
      if (!this.mStarted)
        Log.w("push_service", "Attempt to stop connection not active.");
      while (true)
      {
        return;
        setStarted(false);
        unregisterReceiver(this.mConnectivityChanged);
        cancelReconnect();
        if (this.mConnection == null)
          continue;
        this.mConnection.abort();
        this.mConnection = null;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void stopKeepAlives()
  {
    util.LOGI("stopKeepAlives");
    try
    {
      Intent localIntent = new Intent();
      localIntent.setClass(this, push_service.class);
      localIntent.setAction("oicq.wlogin_sdk.push.KEEP_ALIVE");
      PendingIntent localPendingIntent = PendingIntent.getService(this, 0, localIntent, 0);
      ((AlarmManager)getSystemService("alarm")).cancel(localPendingIntent);
      return;
    }
    catch (Exception localException)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
      localException.printStackTrace(localPrintWriter);
      localPrintWriter.flush();
      localStringWriter.flush();
      util.LOGW("exception:", localStringWriter.toString());
    }
  }

  public void cancelReconnect()
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, push_service.class);
    localIntent.setAction("oicq.wlogin_sdk.push.RECONNECT");
    PendingIntent localPendingIntent = PendingIntent.getService(this, 0, localIntent, 0);
    ((AlarmManager)getSystemService("alarm")).cancel(localPendingIntent);
  }

  public IBinder onBind(Intent paramIntent)
  {
    return this.mBinder;
  }

  public void onCreate()
  {
    super.onCreate();
    this.mConnMan = ((ConnectivityManager)getSystemService("connectivity"));
    this.mNotifMan = ((NotificationManager)getSystemService("notification"));
    this.mPush = new request_push(getApplicationContext(), this.mPushInfo, this);
    IntentFilter localIntentFilter = new IntentFilter(push_client.PUSH_SET_UINFO_RECEIVED);
    registerReceiver(this.myBroadcastReceiver, localIntentFilter);
    Intent localIntent = new Intent(push_client.PUSH_GET_UINFO_RECEIVED);
    getApplicationContext().sendBroadcast(localIntent);
    util.LOGI("onCreate sendBroadcast for PUSH_GET_UINFO_RECEIVED");
    handleCrashedService();
  }

  public void onDestroy()
  {
    util.LOGI("Service destroyed (started=" + this.mStarted + ")");
    unregisterReceiver(this.myBroadcastReceiver);
    if (this.mStarted)
      stop();
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    util.LOGI("Service started with intent=" + paramIntent);
    super.onStart(paramIntent, paramInt);
    if (paramIntent.getAction().equals("oicq.wlogin_sdk.push.STOP"))
    {
      stop();
      stopSelf();
    }
    do
    {
      return;
      if (paramIntent.getAction().equals("oicq.wlogin_sdk.push.START"))
      {
        start();
        return;
      }
      if (!paramIntent.getAction().equals("oicq.wlogin_sdk.push.KEEP_ALIVE"))
        continue;
      keepAlive();
      return;
    }
    while (!paramIntent.getAction().equals("oicq.wlogin_sdk.push.RECONNECT"));
    reconnectIfNecessary();
  }

  public boolean pushCallback(long paramLong1, long paramLong2, long paramLong3, push_msg[] paramArrayOfpush_msg)
  {
    uin_app_info localuin_app_info = this.mPushInfo.get(paramLong1, paramLong2, paramLong3);
    if (localuin_app_info == null)
      return true;
    try
    {
      if ((this.mStarted) && (this.mConnection != null))
      {
        PathClassLoader localPathClassLoader = new PathClassLoader(localuin_app_info._cpath, ClassLoader.getSystemClassLoader());
        Class localClass = Class.forName(localuin_app_info._cname, true, localPathClassLoader);
        showNotification(createPackageContext(localuin_app_info._pkg_name, 2), paramLong2, localClass, localuin_app_info._msg_type, localuin_app_info._notify_id, localuin_app_info._icon, paramArrayOfpush_msg);
        return true;
      }
    }
    catch (Exception localException)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
      localException.printStackTrace(localPrintWriter);
      localPrintWriter.flush();
      localStringWriter.flush();
      util.LOGW("exception:", localStringWriter.toString());
      return false;
    }
    return false;
  }

  public void scheduleReconnect(long paramLong)
  {
    this.mPushInfo.set_all_unreg();
    long l = System.currentTimeMillis();
    util.LOGI("Rescheduling connection in " + 10000L + "ms.");
    Intent localIntent = new Intent();
    localIntent.setClass(this, push_service.class);
    localIntent.setAction("oicq.wlogin_sdk.push.RECONNECT");
    PendingIntent localPendingIntent = PendingIntent.getService(this, 0, localIntent, 0);
    ((AlarmManager)getSystemService("alarm")).set(0, l + 10000L, localPendingIntent);
  }

  public int sendRequest(byte[] paramArrayOfByte)
  {
    try
    {
      if ((this.mStarted) && (this.mConnection != null))
        this.mConnection.sendRequest(paramArrayOfByte);
      return 0;
    }
    catch (IOException localIOException)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
      localIOException.printStackTrace(localPrintWriter);
      localPrintWriter.flush();
      localStringWriter.flush();
      String str = localStringWriter.toString();
      util.LOGI("exception:" + str);
    }
    return -1;
  }

  private class ConnectionThread extends Thread
  {
    private volatile boolean mAbort = false;
    private InetAddress mHost;
    public volatile boolean mPingPending = false;
    private int mPingPkLen = 8;
    private volatile int mPingSeqence = 0;
    private int mPort = 0;
    private final Socket mSocket = new Socket();

    public ConnectionThread()
    {
    }

    private boolean isNetworkAvailable()
    {
      NetworkInfo localNetworkInfo = push_service.this.mConnMan.getActiveNetworkInfo();
      if (localNetworkInfo == null)
        return false;
      return localNetworkInfo.isConnected();
    }

    public void ManulClose()
    {
      util.LOGI("ManulClose.");
      try
      {
        this.mSocket.shutdownOutput();
        try
        {
          label12: this.mSocket.shutdownInput();
          try
          {
            label19: this.mSocket.close();
            return;
          }
          catch (IOException localIOException3)
          {
            return;
          }
        }
        catch (IOException localIOException2)
        {
          break label19;
        }
      }
      catch (IOException localIOException1)
      {
        break label12;
      }
    }

    public void abort()
    {
      util.LOGI("Connection aborting.");
      this.mAbort = true;
      try
      {
        this.mSocket.shutdownOutput();
        try
        {
          label17: this.mSocket.shutdownInput();
          try
          {
            label24: this.mSocket.close();
            while (true)
              try
              {
                label31: join();
                return;
              }
              catch (InterruptedException localInterruptedException)
              {
              }
          }
          catch (IOException localIOException3)
          {
            break label31;
          }
        }
        catch (IOException localIOException2)
        {
          break label24;
        }
      }
      catch (IOException localIOException1)
      {
        break label17;
      }
    }

    public boolean isConnected()
    {
      return this.mSocket.isConnected();
    }

    public void run()
    {
      Socket localSocket = this.mSocket;
      long l = System.currentTimeMillis();
      Object localObject1 = null;
      int i = 0;
      while (true)
      {
        if (i >= 3)
        {
          if (i < 3)
            break label272;
          if (localObject1 == null)
            break label230;
        }
        try
        {
          throw localObject1;
        }
        catch (Exception localException2)
        {
          StringWriter localStringWriter = new StringWriter();
          PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
          localException2.printStackTrace(localPrintWriter);
          localPrintWriter.flush();
          localStringWriter.flush();
          util.LOGW("exception:", localStringWriter.toString());
          push_service.this.stopKeepAlives();
          push_service.this.mConnected = false;
          if (!this.mAbort)
            break label682;
          util.LOGI("Connection aborted, shutting down.");
          return;
          try
          {
            this.mPort = push_service.this.mPush.get_port();
            this.mHost = push_service.this.mPush.resolve_server_addr(i);
            if (this.mHost == null)
            {
              i++;
              continue;
            }
            util.LOGI("Connection to " + this.mHost.getHostAddress() + ":" + this.mPort);
            localSocket.connect(new InetSocketAddress(this.mHost, this.mPort), 20000);
          }
          catch (Exception localException1)
          {
            localObject1 = localException1;
            i++;
          }
          continue;
          label230: throw new Exception("retry");
        }
        finally
        {
          push_service.this.stopKeepAlives();
          push_service.this.mConnected = false;
          if (!this.mAbort)
            break label730;
        }
      }
      util.LOGI("Connection aborted, shutting down.");
      throw localObject2;
      label272: util.LOGI("Connection established to " + localSocket.getInetAddress() + ":" + this.mPort);
      util.LOGI("resend register");
      push_service.this.mPushInfo.send_register(push_service.this, push_service.this.mPush);
      push_service.this.startKeepAlives();
      push_service.this.mConnected = true;
      InputStream localInputStream = localSocket.getInputStream();
      label353: byte[] arrayOfByte1 = new byte[1 + push_service.this.mPush._rsp_head_len];
      int j = 0;
      int k = 0;
      break label843;
      label378: int m = push_service.this.mPush.get_rsp_length(arrayOfByte1);
      if (m == this.mPingPkLen)
      {
        while (true)
        {
          if (j >= this.mPingPkLen);
          label423: int n;
          do
          {
            util.LOGI("mPingPending = false");
            this.mPingPending = false;
            break;
            k = localInputStream.read(arrayOfByte1, j, arrayOfByte1.length - j);
            if (k < 0)
              break label378;
            j += k;
            break label843;
            n = localInputStream.read(arrayOfByte1, j, this.mPingPkLen - j);
          }
          while (n < 0);
          j += n;
        }
        label488: k = localInputStream.read(arrayOfByte1, j, arrayOfByte1.length - j);
        if (k < 0)
        {
          break label852;
          label512: break label571;
        }
      }
      label513: label571: label730: label860: label866: 
      while (true)
      {
        if (!this.mAbort)
          util.LOGI("Server closed connection unexpectedly.");
        push_service.this.stopKeepAlives();
        push_service.this.mConnected = false;
        int i1;
        int i2;
        byte[] arrayOfByte2;
        if (this.mAbort)
        {
          util.LOGI("Connection aborted, shutting down.");
          return;
          j += k;
          if (j < arrayOfByte1.length)
            break label488;
          break label852;
          if (m <= arrayOfByte1.length)
            continue;
          i1 = arrayOfByte1.length;
          i2 = m - i1;
          arrayOfByte2 = new byte[m];
          System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte1.length);
          break label860;
        }
        while (true)
        {
          label612: if (k == -1)
            break label866;
          if (push_service.this.mPush.get_response(arrayOfByte2, arrayOfByte2.length) != -1100)
            break label353;
          break label513;
          do
          {
            while (true)
            {
              int i3 = localInputStream.read(arrayOfByte2, i1, i2);
              k = i3;
              if (k == -1)
                break label612;
              i1 += k;
              i2 -= k;
              break label860;
              try
              {
                while (true)
                {
                  localSocket.close();
                  synchronized (push_service.this)
                  {
                    push_service.this.mConnection = null;
                    if (!isNetworkAvailable())
                      break;
                    push_service.this.scheduleReconnect(l);
                    return;
                  }
                }
              }
              catch (IOException localIOException1)
              {
                try
                {
                  while (true)
                  {
                    localSocket.close();
                    synchronized (push_service.this)
                    {
                      push_service.this.mConnection = null;
                      if (!isNetworkAvailable())
                        break;
                      push_service.this.scheduleReconnect(l);
                    }
                  }
                }
                catch (IOException localIOException1)
                {
                  try
                  {
                    while (true)
                    {
                      while (true)
                      {
                        localSocket.close();
                        synchronized (push_service.this)
                        {
                          push_service.this.mConnection = null;
                          if (!isNetworkAvailable())
                            break;
                          push_service.this.scheduleReconnect(l);
                          return;
                        }
                      }
                      localIOException2 = localIOException2;
                    }
                    localIOException1 = localIOException1;
                  }
                  catch (IOException localIOException3)
                  {
                    break label784;
                  }
                }
              }
            }
            if (j < 3)
              break label423;
            break label378;
            if (k >= 0)
              break label512;
            break label513;
          }
          while (i2 > 0);
        }
      }
    }

    public void sendKeepAlive()
      throws IOException
    {
      Socket localSocket = this.mSocket;
      byte[] arrayOfByte = push_service.this.mPush.get_request_hello(0L, 0L, null, null, null, this.mPingSeqence);
      int i = 1 + this.mPingSeqence;
      this.mPingSeqence = i;
      if (i < 0)
        this.mPingSeqence = 0;
      localSocket.getOutputStream().write(arrayOfByte);
      util.LOGI("Keep-alive sent.");
    }

    public void sendRequest(byte[] paramArrayOfByte)
      throws IOException
    {
      this.mSocket.getOutputStream().write(paramArrayOfByte);
      util.LOGI("sendRequest sent.");
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.push.push_service
 * JD-Core Version:    0.6.0
 */