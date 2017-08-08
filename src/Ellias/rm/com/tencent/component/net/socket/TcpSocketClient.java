package com.tencent.component.net.socket;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import com.tencent.component.ComponentContext;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.ThreadPool;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TcpSocketClient
  implements IReconnectSocket
{
  public static final int a = 4048;
  private static final int b = 0;
  private static final int c = 1;
  private static final int d = 2;
  private static final String e = "SocketClientLog";
  private SocketConfigure f;
  private ISocketDataReceiveListener g;
  private Socket h;
  private ISocketStatusListener i;
  private ISocketTraffic j;
  private LinkedBlockingQueue k;
  private ConcurrentHashMap l;
  private int m = 0;
  private j n;
  private i o;
  private AtomicInteger p = new AtomicInteger(0);
  private PowerManager.WakeLock q;

  public TcpSocketClient(SocketConfigure paramSocketConfigure, ISocketDataReceiveListener paramISocketDataReceiveListener, ISocketStatusListener paramISocketStatusListener)
  {
    this.f = paramSocketConfigure;
    this.g = paramISocketDataReceiveListener;
    this.k = new LinkedBlockingQueue();
    this.l = new ConcurrentHashMap();
    this.i = paramISocketStatusListener;
  }

  private void a(Socket paramSocket)
  {
    try
    {
      paramSocket.setSoLinger(true, 2);
      paramSocket.setSendBufferSize(32768);
      paramSocket.setReceiveBufferSize(32768);
      paramSocket.setTcpNoDelay(true);
      return;
    }
    catch (SocketException localSocketException)
    {
      localSocketException.printStackTrace();
    }
  }

  public void a()
  {
    LogUtil.d("SocketClientLog", "try connect status:" + this.m);
    monitorenter;
    try
    {
      if ((this.m == 0) || (!this.h.isConnected()))
        this.m = 1;
      try
      {
        b();
        LogUtil.d("SOCKET", "client:CONNECTING:" + this.f.a);
        this.h = new Socket();
        InetSocketAddress localInetSocketAddress = new InetSocketAddress(this.f.a, this.f.b);
        a(this.h);
        this.h.connect(localInetSocketAddress);
        this.m = 2;
        this.n = new j(this, this.h.getOutputStream());
        this.o = new i(this, this.h.getInputStream());
        this.o.start();
        this.n.start();
        LogUtil.d("SOCKET", "client:CONNECTED");
        PowerManager localPowerManager = (PowerManager)ComponentContext.a().getSystemService("power");
        if (this.q == null)
          this.q = localPowerManager.newWakeLock(1, "qmichat");
        if (!this.q.isHeld())
          this.q.acquire();
        if (this.i != null)
          ThreadPool.runOnNonUIThread(new g(this));
        LogUtil.d("SocketClientLog", "real connected" + this.m);
        monitorexit;
        return;
      }
      catch (Exception localException)
      {
        while (true)
        {
          localException.printStackTrace();
          LogUtil.d("SocketClientLog", "connect error" + localException.getMessage());
          this.p.getAndIncrement();
          this.m = 0;
          if (this.i == null)
            continue;
          this.i.a(this.h, localException, 0);
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(ISocketTraffic paramISocketTraffic)
  {
    this.j = paramISocketTraffic;
  }

  // ERROR //
  public void a(SocketData paramSocketData)
  {
    // Byte code:
    //   0: ldc 19
    //   2: ldc 250
    //   4: invokestatic 127	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   7: ldc 2
    //   9: monitorenter
    //   10: aload_0
    //   11: getfield 49	com/tencent/component/net/socket/TcpSocketClient:m	I
    //   14: istore_3
    //   15: ldc 2
    //   17: monitorexit
    //   18: iload_3
    //   19: ifeq +13 -> 32
    //   22: aload_0
    //   23: getfield 100	com/tencent/component/net/socket/TcpSocketClient:h	Ljava/net/Socket;
    //   26: invokevirtual 131	java/net/Socket:isConnected	()Z
    //   29: ifne +12 -> 41
    //   32: iload_3
    //   33: iconst_1
    //   34: if_icmpeq +7 -> 41
    //   37: aload_0
    //   38: invokevirtual 252	com/tencent/component/net/socket/TcpSocketClient:a	()V
    //   41: aload_0
    //   42: getfield 65	com/tencent/component/net/socket/TcpSocketClient:k	Ljava/util/concurrent/LinkedBlockingQueue;
    //   45: aload_1
    //   46: invokevirtual 256	java/util/concurrent/LinkedBlockingQueue:put	(Ljava/lang/Object;)V
    //   49: aload_0
    //   50: getfield 70	com/tencent/component/net/socket/TcpSocketClient:l	Ljava/util/concurrent/ConcurrentHashMap;
    //   53: aload_1
    //   54: invokevirtual 260	com/tencent/component/net/socket/SocketData:a	()I
    //   57: invokestatic 266	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   60: aload_1
    //   61: invokevirtual 269	java/util/concurrent/ConcurrentHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   64: pop
    //   65: return
    //   66: astore_2
    //   67: ldc 2
    //   69: monitorexit
    //   70: aload_2
    //   71: athrow
    //   72: astore 4
    //   74: aload 4
    //   76: invokevirtual 270	java/lang/InterruptedException:printStackTrace	()V
    //   79: return
    //
    // Exception table:
    //   from	to	target	type
    //   10	18	66	finally
    //   67	70	66	finally
    //   41	65	72	java/lang/InterruptedException
  }

  public boolean a(int paramInt)
  {
    SocketData localSocketData = (SocketData)this.l.remove(Integer.valueOf(paramInt));
    return this.k.remove(localSocketData);
  }

  public void b()
  {
    if ((this.q != null) && (this.q.isHeld()))
      this.q.release();
    Socket localSocket = this.h;
    j localj = this.n;
    i locali = this.o;
    this.h = null;
    this.n = null;
    this.o = null;
    this.m = 0;
    if (this.i != null)
      this.i.b(localSocket);
    ThreadPool.runOnNonUIThread(new h(this, localSocket, localj, locali));
  }

  public boolean c()
  {
    if (this.h != null)
      return this.h.isConnected();
    return false;
  }

  public ISocketTraffic d()
  {
    return this.j;
  }

  public int getErrorCount()
  {
    return this.p.get();
  }

  public void resetErrorCount()
  {
    this.p.set(0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.TcpSocketClient
 * JD-Core Version:    0.6.0
 */