package com.tencent.android.tpush.service;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.b.f;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.n;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq;
import com.tencent.android.tpush.service.report.ReportItem;
import com.tencent.android.tpush.service.report.e;

class o
  implements n
{
  o(l paraml, String paramString)
  {
  }

  public void a(JceStruct paramJceStruct1, int paramInt, JceStruct paramJceStruct2, a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onResponse reponseCode:" + paramInt);
    if (paramInt == 0)
    {
      TLog.i("XGService", ">> uninstall report success rsp=" + parama.c() + " @host=" + parama.f() + " @http=" + parama.b());
      CacheManager.UninstallInfoSuccessByPkgName(this.a);
      f.a().a(i.e(), this.a);
      ReportItem localReportItem = new ReportItem(l.a(parama.b()), 0, paramInt, 2, parama.f(), parama.c(), this.a);
      e.a().a(localReportItem);
      return;
    }
    TLog.e("XGService", ">> uninstall report fail responseCode=" + paramInt);
    l.a(this.b, paramInt, "服务器处理失败，返回错误", this.a, (TpnsUnregisterReq)paramJceStruct1, parama);
  }

  public void a(JceStruct paramJceStruct, a parama)
  {
  }

  public void a(JceStruct paramJceStruct, ChannelException paramChannelException, a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onMessageSendFailed " + paramChannelException.errorCode + "," + paramChannelException.getMessage());
    l.a(this.b, paramChannelException.errorCode, paramChannelException.getMessage(), this.a, (TpnsUnregisterReq)paramJceStruct, parama);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.o
 * JD-Core Version:    0.6.0
 */