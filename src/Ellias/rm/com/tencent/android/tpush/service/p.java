package com.tencent.android.tpush.service;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.horse.DefaultServer;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.n;
import com.tencent.android.tpush.service.channel.protocol.TpnsGetApListRsp;
import com.tencent.android.tpush.service.report.ReportItem;
import com.tencent.android.tpush.service.report.e;

class p
  implements n
{
  p(l paraml)
  {
  }

  public void a(JceStruct paramJceStruct1, int paramInt, JceStruct paramJceStruct2, a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onResponse reponseCode:" + paramInt);
    if (paramInt == 0)
    {
      TLog.i("XGService", ">> loadAppList success rsp=" + parama.c() + " @host=" + parama.f() + " @http=" + parama.b());
      DefaultServer.a(((TpnsGetApListRsp)paramJceStruct2).apList);
      TLog.v("XGService", ">>> load ap response. ap list " + ((TpnsGetApListRsp)paramJceStruct2).apList);
      CacheManager.saveLoadIpTime(i.e(), System.currentTimeMillis());
    }
    for (int i = 0; ; i = 1)
    {
      ReportItem localReportItem = new ReportItem(l.a(parama.b()), i, paramInt, 11, parama.f(), parama.c(), "");
      e.a().a(localReportItem);
      return;
      TLog.e("XGService", ">> loadAppList fail responseCode=" + paramInt);
    }
  }

  public void a(JceStruct paramJceStruct, a parama)
  {
  }

  public void a(JceStruct paramJceStruct, ChannelException paramChannelException, a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onMessageSendFailed " + paramChannelException.errorCode + "," + paramChannelException.getMessage());
    ReportItem localReportItem = new ReportItem(l.a(parama.b()), 1, paramChannelException.errorCode, 11, parama.f(), parama.c(), paramChannelException.getMessage());
    e.a().a(localReportItem);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.p
 * JD-Core Version:    0.6.0
 */