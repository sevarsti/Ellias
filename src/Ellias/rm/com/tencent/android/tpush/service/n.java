package com.tencent.android.tpush.service;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.protocol.TpnsConfigRsp;
import com.tencent.android.tpush.service.report.ReportItem;
import com.tencent.android.tpush.service.report.e;

class n
  implements com.tencent.android.tpush.service.channel.n
{
  n(l paraml)
  {
  }

  public void a(JceStruct paramJceStruct1, int paramInt, JceStruct paramJceStruct2, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onResponse reponseCode:" + paramInt);
    if (paramInt == 0)
    {
      TLog.i("XGService", ">> loadConfig success rsp=" + parama.c() + " @host=" + parama.f() + " @http=" + parama.b());
      TpnsConfigRsp localTpnsConfigRsp = (TpnsConfigRsp)paramJceStruct2;
      com.tencent.android.tpush.service.a.a.a(localTpnsConfigRsp.confContent);
      TLog.i("XGService", ">> TpnsConfig " + localTpnsConfigRsp.confContent);
      ReportItem localReportItem = new ReportItem(l.a(parama.b()), 0, paramInt, 3, parama.f(), parama.c(), "");
      e.a().a(localReportItem);
      return;
    }
    TLog.e("XGService", ">> loadConfig fail responseCode=" + paramInt);
    l.a(this.a, paramInt, "", parama);
  }

  public void a(JceStruct paramJceStruct, com.tencent.android.tpush.service.channel.a parama)
  {
  }

  public void a(JceStruct paramJceStruct, ChannelException paramChannelException, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onMessageSendFailed " + paramChannelException.errorCode + "," + paramChannelException.getMessage());
    l.a(this.a, paramChannelException.errorCode, paramChannelException.getMessage(), parama);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.n
 * JD-Core Version:    0.6.0
 */