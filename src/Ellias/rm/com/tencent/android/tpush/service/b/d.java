package com.tencent.android.tpush.service.b;

import android.content.Context;
import android.content.Intent;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.common.c;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.n;
import com.tencent.android.tpush.service.i;
import com.tencent.android.tpush.service.l;
import com.tencent.android.tpush.service.report.ReportItem;
import java.util.ArrayList;

class d
  implements n
{
  d(a parama, ArrayList paramArrayList, Context paramContext, Intent paramIntent)
  {
  }

  public void a(JceStruct paramJceStruct1, int paramInt, JceStruct paramJceStruct2, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onResponse reponseCode:" + paramInt);
    if (paramInt == 0)
    {
      TLog.i("XGService", ">> msg ckicled ack success rsp=" + parama.c() + " @host=" + parama.f() + " @http=" + parama.b());
      this.d.a(i.e(), this.a);
      ReportItem localReportItem2 = new ReportItem(l.a(parama.b()), 0, -1, 12, parama.f(), parama.c(), "@_@");
      com.tencent.android.tpush.service.report.e.a().a(localReportItem2);
      c.a().a(new e(this), 30000L);
      return;
    }
    TLog.i("XGService", ">> msg ckicled ack failed responseCode=" + paramInt);
    ReportItem localReportItem1 = new ReportItem(l.a(parama.b()), 1, paramInt, 12, parama.f(), parama.c(), "@_@");
    com.tencent.android.tpush.service.report.e.a().a(localReportItem1);
  }

  public void a(JceStruct paramJceStruct, com.tencent.android.tpush.service.channel.a parama)
  {
  }

  public void a(JceStruct paramJceStruct, ChannelException paramChannelException, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.d("XGService", "### msg ack onMessageSendFailed  responseCode=" + paramChannelException.errorCode);
    ReportItem localReportItem = new ReportItem(l.a(parama.b()), 1, paramChannelException.errorCode, 12, parama.f(), parama.c(), "@_@");
    com.tencent.android.tpush.service.report.e.a().a(localReportItem);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.b.d
 * JD-Core Version:    0.6.0
 */