package com.tencent.component.net.socket;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public abstract interface ISocketSenderListener
{
  @PluginApi(a=7)
  public abstract void onProgressChanged(long paramLong1, long paramLong2, int paramInt);

  @PluginApi(a=6)
  public abstract void onSendFailed(int paramInt1, int paramInt2);

  @PluginApi(a=7)
  public abstract void onSendSuccuess(int paramInt);

  @PluginApi(a=7)
  public abstract void onStart(int paramInt);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.ISocketSenderListener
 * JD-Core Version:    0.6.0
 */