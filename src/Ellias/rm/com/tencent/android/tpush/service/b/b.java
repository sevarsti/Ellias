package com.tencent.android.tpush.service.b;

import android.content.Context;
import android.content.Intent;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.n;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq;
import com.tencent.android.tpush.service.i;
import com.tencent.android.tpush.service.l;
import com.tencent.android.tpush.service.report.ReportItem;
import com.tencent.android.tpush.service.report.e;

class b
  implements n
{
  b(a parama, Context paramContext, Intent paramIntent)
  {
  }

  public void a(JceStruct paramJceStruct1, int paramInt, JceStruct paramJceStruct2, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onResponse reponseCode:" + paramInt);
    a.a(false);
    if (paramInt == 0)
    {
      TLog.i("XGService", ">> msg ack success rsp=" + parama.c() + " @host=" + parama.f() + " @http=" + parama.b());
      this.c.b(i.e(), ((TpnsPushVerifyReq)paramJceStruct1).msgReportList);
      ReportItem localReportItem2 = new ReportItem(l.a(parama.b()), 0, -1, 9, parama.f(), parama.c(), "@_@");
      e.a().a(localReportItem2);
      com.tencent.android.tpush.common.c.a().a(new c(this), 30000L);
      return;
    }
    TLog.i("XGService", ">> msg ack onMessageSendFailed  responseCode=" + paramInt);
    ReportItem localReportItem1 = new ReportItem(l.a(parama.b()), 1, paramInt, 9, parama.f(), parama.c(), "@_@");
    e.a().a(localReportItem1);
  }

  public void a(JceStruct paramJceStruct, com.tencent.android.tpush.service.channel.a parama)
  {
    a.a(false);
  }

  public void a(JceStruct paramJceStruct, ChannelException paramChannelException, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onMessageSendFailed " + paramChannelException.errorCode + "," + paramChannelException.getMessage());
    a.a(false);
    ReportItem localReportItem = new ReportItem(l.a(parama.b()), 1, paramChannelException.errorCode, 9, parama.f(), parama.c(), paramChannelException.getMessage() + "@_@");
    e.a().a(localReportItem);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.b.b
 * JD-Core Version:    0.6.0
 */