package com.tencent.android.tpush.service.channel;

import android.os.Handler;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.b.i;
import com.tencent.android.tpush.service.channel.c.d;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class j
  implements Runnable
{
  private com.tencent.android.tpush.service.channel.a.a b = null;
  private i c = null;

  public j(b paramb, com.tencent.android.tpush.service.channel.a.a parama, i parami)
  {
    this.b = parama;
    this.c = parami;
  }

  public void run()
  {
    TLog.v("XGService", "@@ run()");
    ConcurrentHashMap localConcurrentHashMap = (ConcurrentHashMap)b.f(this.a).get(this.b);
    if (localConcurrentHashMap == null)
      return;
    m localm = (m)localConcurrentHashMap.get(Integer.valueOf(this.c.g()));
    if (localm == null)
    {
      TLog.e("XGService", ">> NetCallBackRunnable >>> 请求已被回调过，响应对应的request不存在。" + this.c);
      return;
    }
    TLog.i("XGService", ">> remove timeoutRunnable");
    k localk = (k)b.h(this.a).remove(localm);
    b.i(this.a).removeCallbacks(localk);
    TLog.i("XGService", ">> remove tpnsMessagesSent");
    localConcurrentHashMap.remove(Integer.valueOf(this.c.g()));
    n localn = localm.d;
    if (localn == null)
    {
      TLog.e("XGService", ">> messageHandler is null");
      return;
    }
    long l = System.currentTimeMillis() - localm.b;
    a locala = this.b.e();
    locala.a(3, Long.valueOf(l));
    try
    {
      JceStruct localJceStruct = d.a(this.c.f(), this.c.i());
      localn.a(localm.c, this.c.j(), localJceStruct, locala);
      return;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.j
 * JD-Core Version:    0.6.0
 */