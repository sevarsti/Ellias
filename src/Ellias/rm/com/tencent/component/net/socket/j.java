package com.tencent.component.net.socket;

import com.tencent.component.utils.log.LogUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

class j extends Thread
{
  private OutputStream b;
  private boolean c = false;

  public j(TcpSocketClient paramTcpSocketClient, OutputStream paramOutputStream)
  {
    super("SendThread");
    this.b = paramOutputStream;
  }

  private void a(ISocketSenderListener paramISocketSenderListener, int paramInt)
  {
    if (paramISocketSenderListener != null)
      paramISocketSenderListener.onStart(paramInt);
  }

  private void a(ISocketSenderListener paramISocketSenderListener, int paramInt1, int paramInt2)
  {
    if (paramISocketSenderListener != null)
      paramISocketSenderListener.onSendFailed(paramInt1, paramInt2);
  }

  private void a(ISocketSenderListener paramISocketSenderListener, long paramLong1, long paramLong2, int paramInt)
  {
    if (paramISocketSenderListener != null)
      paramISocketSenderListener.onProgressChanged(paramLong1, paramLong2, paramInt);
  }

  private void b(ISocketSenderListener paramISocketSenderListener, int paramInt)
  {
    if (paramISocketSenderListener != null)
      paramISocketSenderListener.onSendSuccuess(paramInt);
  }

  public void a()
  {
    this.c = true;
    try
    {
      this.b.close();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      return;
    }
    finally
    {
      TcpSocketClient.a(this.a, 0);
    }
    throw localObject;
  }

  public void run()
  {
    try
    {
      sleep(100L);
      while (true)
      {
        if (this.c)
          break label207;
        localISocketTraffic = this.a.d();
        localSocketData = null;
        try
        {
          localSocketData = (SocketData)TcpSocketClient.e(this.a).take();
          localISocketSenderListener = localSocketData.c();
          a(localISocketSenderListener, localSocketData.a());
          if (localSocketData.b() == null)
            continue;
          if (!localSocketData.d())
            break label216;
          TcpSocketClient.f(this.a).remove(Integer.valueOf(localSocketData.a()));
          b(localISocketSenderListener, localSocketData.a());
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          LogUtil.d("SocketClientLog", "client send error:" + localException.getMessage());
          if (localSocketData == null)
            break label274;
        }
      }
      a(localSocketData.c(), 0, localSocketData.a());
      i = localSocketData.a();
      TcpSocketClient.d(this.a).getAndIncrement();
      if (TcpSocketClient.a(this.a) != null)
        TcpSocketClient.a(this.a).a(TcpSocketClient.b(this.a), localException, i);
      this.a.b();
      label207: return;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
      {
        ISocketTraffic localISocketTraffic;
        SocketData localSocketData;
        ISocketSenderListener localISocketSenderListener;
        localInterruptedException.printStackTrace();
        continue;
        label216: this.b.write(localSocketData.b());
        this.b.flush();
        a(localISocketSenderListener, localSocketData.b().length, localSocketData.b().length, localSocketData.a());
        if (localISocketTraffic == null)
          continue;
        localISocketTraffic.sendBytes(localSocketData.b().length);
        continue;
        label274: int i = 0;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.j
 * JD-Core Version:    0.6.0
 */