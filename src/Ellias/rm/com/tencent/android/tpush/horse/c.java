package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.protocol.TpnsRedirectReq;
import com.tencent.android.tpush.service.i;
import java.util.concurrent.LinkedBlockingQueue;

class c extends Thread
{
  private q b;
  private int c;
  private StrategyItem d;
  private r e = new d(this);

  public c(a parama, int paramInt)
  {
    this.c = paramInt;
  }

  public q a()
  {
    return this.b;
  }

  public void run()
  {
    TLog.e("XGHorse", "@@ =================run()====================");
    while (a.a(this.a).size() > 0)
      try
      {
        this.d = ((StrategyItem)a.a(this.a).remove());
        TpnsRedirectReq localTpnsRedirectReq = new TpnsRedirectReq();
        localTpnsRedirectReq.network = com.tencent.android.tpush.service.c.c.d(i.e());
        localTpnsRedirectReq.op = com.tencent.android.tpush.service.c.c.e(i.e());
        this.b = new q();
        this.b.a(this.e);
        try
        {
          TLog.i("XGHorse", ">> HorseThread:" + getClass().getSimpleName() + Thread.currentThread() + "current NetworkType:" + localTpnsRedirectReq.network + ",current Isp:" + localTpnsRedirectReq.op);
          TLog.e("XGHorse", ">> connect");
          this.b.a(this.d);
          TLog.e("XGHorse", ">> send");
          this.b.a(localTpnsRedirectReq);
          TLog.e("XGHorse", ">> recv");
          this.b.b();
          TLog.e("XGHorse", ">> end");
        }
        catch (Exception localException3)
        {
          TLog.e("XGHorse", localException3.toString());
        }
      }
      catch (Exception localException1)
      {
        TLog.e("XGHorse", ">>can not get strateItem from strategyItems>>", localException1);
        try
        {
          Thread.sleep(5000L);
        }
        catch (Exception localException2)
        {
          TLog.e("XGHorse", localException2.toString());
        }
      }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.c
 * JD-Core Version:    0.6.0
 */