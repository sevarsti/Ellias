package com.tencent.component.net.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

class f extends Thread
{
  private OutputStream b;
  private boolean c = false;

  public f(SocketWrapper paramSocketWrapper, OutputStream paramOutputStream)
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
    }
  }

  public void run()
  {
    if (!this.c)
    {
      ISocketTraffic localISocketTraffic = this.a.c();
      SocketData localSocketData = null;
      while (true)
      {
        ISocketSenderListener localISocketSenderListener;
        try
        {
          localSocketData = (SocketData)SocketWrapper.c(this.a).take();
          localISocketSenderListener = localSocketData.c();
          a(localISocketSenderListener, localSocketData.a());
          if (localSocketData.b() == null)
            break;
          if (!localSocketData.d())
            break label133;
          b(localISocketSenderListener, localSocketData.a());
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          if (localSocketData == null)
            continue;
          a(localSocketData.c(), 0, localSocketData.a());
          SocketWrapper.b(this.a).getAndIncrement();
          this.a.b.a(this.a);
          this.a.a();
        }
        break;
        label133: this.b.write(localSocketData.b());
        a(localISocketSenderListener, localSocketData.b().length, localSocketData.b().length, localSocketData.a());
        if (localISocketTraffic == null)
          continue;
        localISocketTraffic.sendBytes(localSocketData.b().length);
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.f
 * JD-Core Version:    0.6.0
 */