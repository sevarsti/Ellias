package com.tencent.android.tpush.service;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.n;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq;

class e
  implements n
{
  e(a parama, String paramString)
  {
  }

  public void a(JceStruct paramJceStruct1, int paramInt, JceStruct paramJceStruct2, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onResponse reponseCode:" + paramInt);
    if (paramInt == 0)
    {
      TLog.i("XGService", ">> unregeister ack success rsp=" + parama.c() + " @host=" + parama.f() + " @http=" + parama.b());
      a.a(this.b, paramInt, (TpnsUnregisterReq)paramJceStruct1, parama, this.a);
      return;
    }
    TLog.e("XGService", ">> unregeister ack failed responseCode=" + paramInt);
    a.a(this.b, paramInt, "服务器处理失败，返回错误", (TpnsUnregisterReq)paramJceStruct1, parama, this.a);
  }

  public void a(JceStruct paramJceStruct, com.tencent.android.tpush.service.channel.a parama)
  {
  }

  public void a(JceStruct paramJceStruct, ChannelException paramChannelException, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "@@ unregister onMessageSendFailed " + paramChannelException.errorCode + "," + paramChannelException.getMessage());
    a.a(this.b, paramChannelException.errorCode, paramChannelException.getMessage(), (TpnsUnregisterReq)paramJceStruct, parama, this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.e
 * JD-Core Version:    0.6.0
 */