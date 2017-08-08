package com.tencent.component.net.socket;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

class e extends Thread
{
  private InputStream b;
  private boolean c = false;
  private byte[] d = new byte[4048];

  public e(SocketWrapper paramSocketWrapper, InputStream paramInputStream)
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
    }
  }

  public void run()
  {
    while (!this.c)
      try
      {
        if (this.c)
          return;
        ISocketTraffic localISocketTraffic = this.a.c();
        int i = this.b.read(this.d);
        if (SocketWrapper.a(this.a) != null)
          SocketWrapper.a(this.a).a(this.d, i);
        if (localISocketTraffic == null)
          continue;
        localISocketTraffic.receiveBytes(i);
      }
      catch (Exception localException)
      {
        SocketWrapper.b(this.a).getAndIncrement();
        if (this.a.b != null)
          this.a.b.a(this.a);
        this.a.a();
      }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.e
 * JD-Core Version:    0.6.0
 */