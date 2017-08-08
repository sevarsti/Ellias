package com.tencent.android.tpush.service;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.n;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterRsp;

class c
  implements n
{
  c(a parama, String paramString)
  {
  }

  public void a(JceStruct paramJceStruct1, int paramInt, JceStruct paramJceStruct2, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onResponse reponseCode:" + paramInt);
    if (paramInt == 0)
    {
      TLog.i("XGService", ">> regeister ack success rsp=" + parama.c() + " @host=" + parama.f() + " @http=" + parama.b());
      a.a(this.b, paramInt, (TpnsRegisterRsp)paramJceStruct2, (TpnsRegisterReq)paramJceStruct1, parama, this.a);
      return;
    }
    TLog.e("XGService", ">> regeister ack fail responseCode=" + paramInt);
    a.a(this.b, paramInt, "服务器处理失败，返回错误", (TpnsRegisterReq)paramJceStruct1, parama, this.a);
  }

  public void a(JceStruct paramJceStruct, com.tencent.android.tpush.service.channel.a parama)
  {
  }

  public void a(JceStruct paramJceStruct, ChannelException paramChannelException, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onMessageSendFailed " + paramChannelException.errorCode + "," + paramChannelException.getMessage());
    a.a(this.b, paramChannelException.errorCode, paramChannelException.getMessage(), (TpnsRegisterReq)paramJceStruct, parama, this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.c
 * JD-Core Version:    0.6.0
 */