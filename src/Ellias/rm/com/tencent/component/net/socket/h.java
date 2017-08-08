package com.tencent.component.net.socket;

import com.tencent.component.utils.log.LogUtil;
import java.io.IOException;
import java.net.Socket;

class h
  implements Runnable
{
  h(TcpSocketClient paramTcpSocketClient, Socket paramSocket, j paramj, i parami)
  {
  }

  public void run()
  {
    LogUtil.d("SocketClientLog", "real close");
    if (this.a != null);
    try
    {
      this.a.close();
      if (this.b != null)
        this.b.a();
      if (this.c != null)
        this.c.a();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.h
 * JD-Core Version:    0.6.0
 */