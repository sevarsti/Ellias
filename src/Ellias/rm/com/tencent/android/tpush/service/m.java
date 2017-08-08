package com.tencent.android.tpush.service;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.n;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectRsp;
import com.tencent.android.tpush.service.report.ReportItem;
import com.tencent.android.tpush.service.report.e;
import java.util.ArrayList;

class m
  implements n
{
  m(l paraml)
  {
  }

  public void a(JceStruct paramJceStruct1, int paramInt, JceStruct paramJceStruct2, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onResponse reponseCode:" + paramInt);
    if (paramInt == 0)
    {
      TLog.i("XGService", ">> reconn success rsp=" + parama.c() + " @host=" + parama.f() + " @http=" + parama.b());
      int j;
      int k;
      label176: TpnsReconnectRsp localTpnsReconnectRsp;
      StringBuilder localStringBuilder1;
      if (paramJceStruct1 != null)
      {
        StringBuilder localStringBuilder2 = new StringBuilder().append(">> unregInfoList size=");
        if ((paramJceStruct1 == null) || (((TpnsReconnectReq)paramJceStruct1).unregInfoList == null))
        {
          j = 0;
          TLog.i("XGService", j);
          CacheManager.updateUnregUninList(i.e(), ((TpnsReconnectReq)paramJceStruct1).unregInfoList);
          StringBuilder localStringBuilder3 = new StringBuilder().append(">> recvMsgList size=");
          if ((paramJceStruct1 != null) && (((TpnsReconnectReq)paramJceStruct1).recvMsgList != null))
            break label395;
          k = 0;
          TLog.i("XGService", k);
          com.tencent.android.tpush.service.b.a.a().b(i.e(), ((TpnsReconnectReq)paramJceStruct1).recvMsgList);
          com.tencent.android.tpush.service.b.a.a().a(i.e(), ((TpnsReconnectReq)paramJceStruct1).msgClickList);
        }
      }
      else
      {
        localTpnsReconnectRsp = (TpnsReconnectRsp)paramJceStruct2;
        if ((localTpnsReconnectRsp != null) && (localTpnsReconnectRsp.appOfflinePushMsgList != null) && (localTpnsReconnectRsp.appOfflinePushMsgList.size() > 0))
        {
          localStringBuilder1 = new StringBuilder().append(">> appOfflinePushMsgList size=");
          if ((localTpnsReconnectRsp != null) && (localTpnsReconnectRsp.appOfflinePushMsgList != null))
            break label410;
        }
      }
      label395: label410: for (int i = 0; ; i = localTpnsReconnectRsp.appOfflinePushMsgList.size())
      {
        TLog.i("XGService", i);
        com.tencent.android.tpush.service.b.a.a().a(localTpnsReconnectRsp.appOfflinePushMsgList, localTpnsReconnectRsp.timeUs, parama);
        if (localTpnsReconnectRsp != null)
          this.a.a(parama.b(), localTpnsReconnectRsp.confVersion);
        ReportItem localReportItem2 = new ReportItem(l.a(parama.b()), 0, paramInt, 6, parama.f(), parama.c(), "");
        e.a().a(localReportItem2);
        return;
        j = ((TpnsReconnectReq)paramJceStruct1).unregInfoList.size();
        break;
        k = ((TpnsReconnectReq)paramJceStruct1).recvMsgList.size();
        break label176;
      }
    }
    TLog.e("XGService", ">> reconn failed responseCode=" + paramInt);
    ReportItem localReportItem1 = new ReportItem(l.a(parama.b()), 1, paramInt, 6, parama.f(), parama.c(), "");
    e.a().a(localReportItem1);
  }

  public void a(JceStruct paramJceStruct, com.tencent.android.tpush.service.channel.a parama)
  {
  }

  public void a(JceStruct paramJceStruct, ChannelException paramChannelException, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onMessageSendFailed " + paramChannelException.errorCode + "," + paramChannelException.getMessage());
    ReportItem localReportItem = new ReportItem(l.a(parama.b()), 1, -1, 6, parama.f(), parama.c(), "");
    e.a().a(localReportItem);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.m
 * JD-Core Version:    0.6.0
 */