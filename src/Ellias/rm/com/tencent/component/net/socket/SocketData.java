package com.tencent.component.net.socket;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;

public class SocketData
{
  private static AtomicInteger a = new AtomicInteger();
  private int b;
  private byte[] c;
  private WeakReference d;
  private boolean e = false;

  public SocketData(byte[] paramArrayOfByte, int paramInt, ISocketSenderListener paramISocketSenderListener)
  {
    this.c = paramArrayOfByte;
    if (paramISocketSenderListener != null)
      this.d = new WeakReference(paramISocketSenderListener);
  }

  public int a()
  {
    return this.b;
  }

  public void a(ISocketSenderListener paramISocketSenderListener)
  {
    this.d = new WeakReference(paramISocketSenderListener);
  }

  public void a(boolean paramBoolean)
  {
    this.e = paramBoolean;
  }

  public void a(byte[] paramArrayOfByte, int paramInt)
  {
    this.c = paramArrayOfByte;
    this.b = paramInt;
  }

  public byte[] b()
  {
    return this.c;
  }

  public ISocketSenderListener c()
  {
    if (this.d != null)
      return (ISocketSenderListener)this.d.get();
    return null;
  }

  public boolean d()
  {
    return this.e;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.SocketData
 * JD-Core Version:    0.6.0
 */