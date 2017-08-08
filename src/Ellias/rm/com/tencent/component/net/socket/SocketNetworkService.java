package com.tencent.component.net.socket;

import com.tencent.component.annotation.PluginApi;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.ThreadPool;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@PluginApi(a=6)
public class SocketNetworkService
  implements ISocketDataReceiveListener
{
  public static final String a = "SocketLog";
  private static final String c = "";
  private static final int d = 8888;
  private static SocketNetworkService f;
  ExecutorService b = Executors.newSingleThreadExecutor();
  private TcpSocketClient e;
  private ISocketPackageDataListener g = new NetworkDataPackager();
  private SocketConfigure h = new SocketConfigure("", 8888);
  private INotifyPackageReceiver i;
  private IReconnectStrategy j;
  private ISocketStatusListener k = new a(this);
  private ISocketTraffic l;

  @PluginApi(a=6)
  public static SocketNetworkService getInstance()
  {
    if (f == null)
      monitorenter;
    try
    {
      if (f == null)
        f = new SocketNetworkService();
      return f;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(byte[] paramArrayOfByte, int paramInt)
  {
    ISocketPackageDataListener localISocketPackageDataListener = this.g;
    if (localISocketPackageDataListener != null)
    {
      ArrayList localArrayList = localISocketPackageDataListener.a(paramArrayOfByte, paramInt);
      if (localArrayList != null)
        for (int m = 0; m < localArrayList.size(); m++)
        {
          byte[] arrayOfByte = (byte[])localArrayList.get(m);
          LogUtil.d("SocketLog", "receive protocolData");
          ThreadPool.runOnNonUIThread(new d(this, arrayOfByte));
        }
    }
  }

  @PluginApi(a=6)
  public boolean cancelData(int paramInt)
  {
    return this.e.a(paramInt);
  }

  @PluginApi(a=6)
  public void close()
  {
    if (this.e != null)
      this.e.b();
    LogUtil.d("SocketLog", "close socket");
  }

  @PluginApi(a=6)
  public void connect()
  {
    LogUtil.d("SocketLog", "connect");
    ThreadPool.runOnNonUIThread(new c(this));
  }

  @PluginApi(a=6)
  public INotifyPackageReceiver getProtocolDispatch()
  {
    return this.i;
  }

  @PluginApi(a=6)
  public IReconnectStrategy getReconnectStrategy()
  {
    return this.j;
  }

  @PluginApi(a=6)
  public SocketConfigure getSocketConfigure()
  {
    return this.h;
  }

  @PluginApi(a=6)
  public ISocketTraffic getTraffic()
  {
    return this.l;
  }

  @PluginApi(a=6)
  public void init()
  {
    this.e = new TcpSocketClient(this.h, this, this.k);
    this.e.a(this.l);
    LogUtil.d("SocketLog", "init Socket");
  }

  @PluginApi(a=6)
  public int send(byte[] paramArrayOfByte, int paramInt)
  {
    return send(paramArrayOfByte, paramInt, null);
  }

  @PluginApi(a=6)
  public int send(byte[] paramArrayOfByte, int paramInt, ISocketSenderListener paramISocketSenderListener)
  {
    ISocketPackageDataListener localISocketPackageDataListener = this.g;
    if (localISocketPackageDataListener != null)
    {
      SocketData localSocketData = new SocketData(localISocketPackageDataListener.a(paramArrayOfByte), paramInt, paramISocketSenderListener);
      LogUtil.d("SocketLog", "senddata seq:" + paramInt);
      this.b.execute(new b(this, localSocketData));
      return localSocketData.a();
    }
    return -1;
  }

  @PluginApi(a=6)
  public void setProtocolDispatch(INotifyPackageReceiver paramINotifyPackageReceiver)
  {
    this.i = paramINotifyPackageReceiver;
  }

  @PluginApi(a=6)
  public void setReconnectStrategy(IReconnectStrategy paramIReconnectStrategy)
  {
    this.j = paramIReconnectStrategy;
  }

  @PluginApi(a=6)
  public void setSocketConfigure(SocketConfigure paramSocketConfigure)
  {
    this.h = paramSocketConfigure;
  }

  @PluginApi(a=6)
  public void setTraffic(ISocketTraffic paramISocketTraffic)
  {
    this.l = paramISocketTraffic;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.SocketNetworkService
 * JD-Core Version:    0.6.0
 */