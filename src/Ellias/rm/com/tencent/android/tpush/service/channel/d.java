package com.tencent.android.tpush.service.channel;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.report.ReportItem;
import com.tencent.android.tpush.service.report.e;

class d
  implements n
{
  d(b paramb)
  {
  }

  public void a(JceStruct paramJceStruct1, int paramInt, JceStruct paramJceStruct2, a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onResponse reponseCode:" + paramInt);
    TLog.i("XGService", ">> regeister heartbeat success rsp=" + parama.c() + " @http=" + parama.b());
    while (true)
    {
      synchronized (this.a)
      {
        if (b.d >= 0L)
          continue;
        b.d = 2L;
        b.c += b.c / (10L * b.d);
        if (parama.b())
        {
          i = 1;
          ReportItem localReportItem = new ReportItem(i, 0, 0, 8, parama.f(), parama.c(), "");
          e.a().a(localReportItem);
          return;
        }
      }
      int i = 0;
    }
  }

  public void a(JceStruct paramJceStruct, a parama)
  {
  }

  public void a(JceStruct paramJceStruct, ChannelException paramChannelException, a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onMessageSendFailed " + paramChannelException.errorCode + "," + paramChannelException.getMessage());
    if (parama.b());
    for (int i = 2; ; i = 1)
    {
      ReportItem localReportItem = new ReportItem(i, 1, 0, 8, parama.f(), parama.c(), "");
      e.a().a(localReportItem);
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.d
 * JD-Core Version:    0.6.0
 */