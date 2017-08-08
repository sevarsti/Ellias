package com.tencent.component.net.socket;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public abstract interface IReconnectStrategy
{
  @PluginApi(a=6)
  public abstract void onNetworkConnected(IReconnectSocket paramIReconnectSocket);

  @PluginApi(a=6)
  public abstract void onNetworkDisconnected(IReconnectSocket paramIReconnectSocket);

  @PluginApi(a=6)
  public abstract void onNetworkError(IReconnectSocket paramIReconnectSocket, Exception paramException, int paramInt);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.IReconnectStrategy
 * JD-Core Version:    0.6.0
 */