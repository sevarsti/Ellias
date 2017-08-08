package com.tencent.component.net.socket;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public abstract interface ISocketTraffic
{
  @PluginApi(a=6)
  public abstract void receiveBytes(int paramInt);

  @PluginApi(a=6)
  public abstract void sendBytes(int paramInt);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.ISocketTraffic
 * JD-Core Version:    0.6.0
 */