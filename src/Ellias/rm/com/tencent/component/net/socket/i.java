package com.tencent.component.net.socket;

import com.tencent.component.utils.log.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

class i extends Thread
{
  private InputStream b;
  private boolean c = false;
  private byte[] d = new byte[4048];

  public i(TcpSocketClient paramTcpSocketClient, InputStream paramInputStream)
  {
    super("ReceiveThread");
    this.b = paramInputStream;
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
    while (true)
    {
      if (!this.c)
        try
        {
          if (this.b.available() > 0)
            break label113;
          monitorenter;
          try
          {
            wait(200L);
            monitorexit;
            continue;
          }
          finally
          {
            monitorexit;
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          LogUtil.d("SocketClientLog", "client receive error:" + localException.getMessage());
          if (TcpSocketClient.a(this.a) != null)
            TcpSocketClient.a(this.a).a(TcpSocketClient.b(this.a), localException, -1);
          TcpSocketClient.d(this.a).getAndIncrement();
        }
      return;
      label113: ISocketTraffic localISocketTraffic = this.a.d();
      int i = this.b.read(this.d);
      LogUtil.d("SocketClientLog", "client receive:" + i);
      if (i == -1)
      {
        Thread.sleep(1000L);
        continue;
      }
      if (TcpSocketClient.c(this.a) != null)
        TcpSocketClient.c(this.a).a(this.d, i);
      if (localISocketTraffic == null)
        continue;
      localISocketTraffic.receiveBytes(i);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.i
 * JD-Core Version:    0.6.0
 */