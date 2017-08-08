package com.tencent.component.net.socket;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public abstract interface IReconnectSocket extends ITcpSocket
{
  @PluginApi(a=6)
  public abstract int getErrorCount();

  @PluginApi(a=6)
  public abstract void resetErrorCount();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.IReconnectSocket
 * JD-Core Version:    0.6.0
 */