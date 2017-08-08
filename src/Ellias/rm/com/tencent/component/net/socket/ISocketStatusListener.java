package com.tencent.component.net.socket;

import java.net.Socket;

public abstract interface ISocketStatusListener
{
  public abstract void a(Socket paramSocket);

  public abstract void a(Socket paramSocket, Exception paramException, int paramInt);

  public abstract void b(Socket paramSocket);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.ISocketStatusListener
 * JD-Core Version:    0.6.0
 */