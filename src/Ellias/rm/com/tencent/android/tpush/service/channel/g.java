package com.tencent.android.tpush.service.channel;

import android.os.Handler;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.b.i;
import java.util.Map;

class g
  implements Runnable
{
  private com.tencent.android.tpush.service.channel.a.a b = null;
  private i c = null;

  public g(b paramb, com.tencent.android.tpush.service.channel.a.a parama, i parami)
  {
    this.b = parama;
    this.c = parami;
  }

  public void run()
  {
    TLog.v("XGService", "@@ run()");
    long l = System.currentTimeMillis() - b.j(this.a).b;
    a locala = this.b.e();
    locala.a(3, Long.valueOf(l));
    n localn = b.j(this.a).d;
    if (localn == null)
    {
      TLog.e("XGService", ">> messageHandler is null");
      return;
    }
    TLog.i("XGService", ">> remove timeoutRunnable");
    k localk = (k)b.h(this.a).remove(b.j(this.a));
    b.i(this.a).removeCallbacks(localk);
    localn.a(b.j(this.a).c, this.c.j(), null, locala);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.g
 * JD-Core Version:    0.6.0
 */