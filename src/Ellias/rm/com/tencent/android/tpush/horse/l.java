package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.logging.TLog;
import java.nio.channels.SocketChannel;

class l
  implements b
{
  l(i parami)
  {
  }

  public void a(StrategyItem paramStrategyItem)
  {
    TLog.v("XGHorse", "@@ onFail(" + paramStrategyItem + ")");
    if (i.f(this.a))
    {
      i.a(this.a, false);
      g.a(1, 102, 5, System.currentTimeMillis() - i.c(this.a), paramStrategyItem, null);
      this.a.c();
      return;
    }
    StringBuilder localStringBuilder1 = new StringBuilder().append(">> item:");
    String str1;
    label96: StringBuilder localStringBuilder2;
    if (paramStrategyItem == null)
    {
      str1 = "null";
      TLog.i("XGHorse", str1 + " status=" + i.e(this.a));
      if (t.j().c())
        break label317;
      if ((i.e(this.a) == 0) && (!h.j().c()))
      {
        i.a(this.a, 2);
        localStringBuilder2 = new StringBuilder().append(">> ");
        if (paramStrategyItem != null)
          break label308;
      }
    }
    label308: for (String str2 = "null"; ; str2 = paramStrategyItem.toString())
    {
      TLog.i("XGHorse", str2);
      if (i.b(this.a) != null)
        i.a(this.a, 10101, "create channel fail!");
      if (i.e(this.a) != 1)
        break;
      synchronized (i.d(this.a))
      {
        i.a(this.a, 2);
        TLog.i("XGHorse", ">> http notify 421>>" + Thread.currentThread());
        i.d(this.a).notify();
        return;
      }
      str1 = paramStrategyItem.toString();
      break label96;
    }
    label317: TLog.i("XGHorse", ">> tcp has remain");
  }

  public void a(SocketChannel paramSocketChannel, StrategyItem paramStrategyItem)
  {
    TLog.v("XGHorse", "@@ onSuccess(" + paramSocketChannel + ")");
    i.a(0);
    if ((paramSocketChannel == null) || (paramStrategyItem == null))
    {
      TLog.i("XGHorse", ">> socketChannel or item is null ,notifyFail()");
      i.a(this.a, 10101, "create channel fail!");
      return;
    }
    if (paramSocketChannel.isConnected())
    {
      TLog.i("XGHorse", ">> isConnected item:");
      if ((i.b(this.a) != null) && ((!paramStrategyItem.j()) || (i.f(this.a))));
    }
    while (true)
    {
      try
      {
        paramSocketChannel.close();
        if (!i.f(this.a))
          continue;
        i.a(this.a, false);
        g.a(0, 101, 5, System.currentTimeMillis() - i.c(this.a), paramStrategyItem, null);
        synchronized (i.d(this.a))
        {
          i.a(this.a, 2);
          TLog.i("XGHorse", ">> TcpTask has result >>" + Thread.currentThread());
          i.d(this.a).notify();
          return;
        }
      }
      catch (Exception localException)
      {
        TLog.e("XGHorse", localException.toString());
        continue;
      }
      i.b(this.a).a(paramSocketChannel, paramStrategyItem);
      Tools.sendCurStrategyItem(com.tencent.android.tpush.service.i.e(), paramStrategyItem);
      continue;
      TLog.i("XGHorse", ">> not connected >> notifyFail()");
      i.a(this.a, 10101, "create channel fail!");
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.l
 * JD-Core Version:    0.6.0
 */