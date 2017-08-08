package com.tencent.component.net.socket;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public abstract interface ISocketDataReceiveListener
{
  public abstract void a(byte[] paramArrayOfByte, int paramInt);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.ISocketDataReceiveListener
 * JD-Core Version:    0.6.0
 */