package com.tencent.component.net.socket;

import com.tencent.component.utils.log.LogUtil;
import java.net.Socket;

class a
  implements ISocketStatusListener
{
  a(SocketNetworkService paramSocketNetworkService)
  {
  }

  public void a(Socket paramSocket)
  {
    if (SocketNetworkService.a(this.a) != null)
      SocketNetworkService.a(this.a).onNetworkConnected(SocketNetworkService.b(this.a));
    LogUtil.d("SocketLog", "onConnected");
  }

  public void a(Socket paramSocket, Exception paramException, int paramInt)
  {
    if (SocketNetworkService.a(this.a) != null)
      SocketNetworkService.a(this.a).onNetworkError(SocketNetworkService.b(this.a), paramException, paramInt);
    LogUtil.d("SocketLog", "onSocketError");
  }

  public void b(Socket paramSocket)
  {
    if (SocketNetworkService.a(this.a) != null)
      SocketNetworkService.a(this.a).onNetworkDisconnected(SocketNetworkService.b(this.a));
    LogUtil.d("SocketLog", "onDisconnedted");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.a
 * JD-Core Version:    0.6.0
 */