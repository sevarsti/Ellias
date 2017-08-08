package com.tencent.android.tpush.service;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.n;

class d
  implements n
{
  d(a parama, String paramString1, int paramInt, String paramString2)
  {
  }

  public void a(JceStruct paramJceStruct1, int paramInt, JceStruct paramJceStruct2, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "@@ TpnsMessage.IEventListener.onResponse reponseCode:" + paramInt);
    if (paramInt == 0)
      TLog.i("XGService", ">> tag ack success rsp=" + parama.c() + " @host=" + parama.f() + " @http=" + parama.b());
    while (true)
    {
      a.a(this.d, paramInt, this.a, this.b, this.c);
      return;
      TLog.e("XGService", ">> tag ack fail responseCode=" + paramInt);
    }
  }

  public void a(JceStruct paramJceStruct, com.tencent.android.tpush.service.channel.a parama)
  {
  }

  public void a(JceStruct paramJceStruct, ChannelException paramChannelException, com.tencent.android.tpush.service.channel.a parama)
  {
    if (paramChannelException != null)
    {
      TLog.v("XGService", "@@ TpnsMessage.IEventListener.onMessageSendFailed " + paramChannelException.errorCode + "," + paramChannelException.getMessage());
      a.a(this.d, paramChannelException.errorCode, this.a, this.b, this.c);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.d
 * JD-Core Version:    0.6.0
 */