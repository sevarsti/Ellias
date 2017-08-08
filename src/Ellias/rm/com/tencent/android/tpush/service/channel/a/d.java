package com.tencent.android.tpush.service.channel.a;

import java.nio.channels.SocketChannel;

public class d extends c
{
  public d(SocketChannel paramSocketChannel, b paramb, String paramString, int paramInt)
  {
  }

  protected boolean a()
  {
    return super.a();
  }

  protected boolean b()
  {
    if ((this.f == null) && (super.b()))
      ((com.tencent.android.tpush.service.channel.b.b)this.f).a("X-Online-Host", this.m);
    return this.f != null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.a.d
 * JD-Core Version:    0.6.0
 */