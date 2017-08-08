package com.tencent.component.net.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class SocketWrapper
{
  public static final int a = 4048;
  private static final int c = 0;
  private static final int d = 1;
  private static final int e = 2;
  ISocketListener b;
  private ISocketDataReceiveListener f;
  private Socket g;
  private ISocketTraffic h;
  private LinkedBlockingQueue i;
  private int j = 0;
  private f k;
  private e l;
  private AtomicInteger m = new AtomicInteger(0);

  public SocketWrapper(ISocketDataReceiveListener paramISocketDataReceiveListener, Socket paramSocket, ISocketListener paramISocketListener)
  {
    this.f = paramISocketDataReceiveListener;
    this.i = new LinkedBlockingQueue();
    this.g = paramSocket;
    this.b = paramISocketListener;
    d();
  }

  private void d()
  {
    try
    {
      this.k = new f(this, this.g.getOutputStream());
      this.l = new e(this, this.g.getInputStream());
      this.k.start();
      this.l.start();
      return;
    }
    catch (IOException localIOException)
    {
      do
        localIOException.printStackTrace();
      while (this.b == null);
      this.b.a(this);
    }
  }

  public void a()
  {
    if ((this.g != null) && (this.g.isConnected()));
    try
    {
      if (this.k != null)
        this.k.a();
      if (this.l != null)
        this.l.a();
      this.g.close();
      this.b.a(this);
      this.j = 0;
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public void a(ISocketTraffic paramISocketTraffic)
  {
    this.h = paramISocketTraffic;
  }

  public void a(SocketData paramSocketData)
  {
    try
    {
      this.i.put(paramSocketData);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
    }
  }

  public boolean b()
  {
    if (this.g != null)
      return this.g.isConnected();
    return false;
  }

  public ISocketTraffic c()
  {
    return this.h;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.SocketWrapper
 * JD-Core Version:    0.6.0
 */