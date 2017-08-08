package com.tencent.component.net.socket;

class g
  implements Runnable
{
  g(TcpSocketClient paramTcpSocketClient)
  {
  }

  public void run()
  {
    if (TcpSocketClient.a(this.a) != null);
    try
    {
      TcpSocketClient.a(this.a).a(TcpSocketClient.b(this.a));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.g
 * JD-Core Version:    0.6.0
 */