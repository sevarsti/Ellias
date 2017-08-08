package com.tencent.android.tpush.service.channel;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.a.a;
import com.tencent.android.tpush.service.channel.b.i;
import com.tencent.android.tpush.service.channel.c.d;
import com.tencent.android.tpush.service.l;

class h
  implements Runnable
{
  private a b = null;
  private i c = null;

  public h(b paramb, a parama, i parami)
  {
    this.b = parama;
    this.c = parami;
  }

  public void run()
  {
    TLog.v("XGService", "@@ run()");
    try
    {
      JceStruct localJceStruct = d.a(this.c.f(), this.c.i());
      l.a().a(localJceStruct, this.b.e());
      return;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.h
 * JD-Core Version:    0.6.0
 */