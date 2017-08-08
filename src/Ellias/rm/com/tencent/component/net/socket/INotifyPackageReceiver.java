package com.tencent.component.net.socket;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public abstract interface INotifyPackageReceiver
{
  @PluginApi(a=6)
  public abstract void onPackageReveiver(byte[] paramArrayOfByte);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.INotifyPackageReceiver
 * JD-Core Version:    0.6.0
 */