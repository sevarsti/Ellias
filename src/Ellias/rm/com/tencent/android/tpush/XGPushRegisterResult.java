package com.tencent.android.tpush;

import android.content.Intent;

public class XGPushRegisterResult
  implements XGIResult
{
  long a = 0L;
  String b = "";
  String c = "";
  String d = "";
  short e = 0;
  String f = "";

  public long getAccessId()
  {
    return this.a;
  }

  public String getAccount()
  {
    return this.c;
  }

  public String getDeviceId()
  {
    return this.b;
  }

  public String getTicket()
  {
    return this.d;
  }

  public short getTicketType()
  {
    return this.e;
  }

  public String getToken()
  {
    return this.f;
  }

  public void parseIntent(Intent paramIntent)
  {
    this.a = paramIntent.getLongExtra("accId", -1L);
    this.b = paramIntent.getStringExtra("deviceId");
    this.c = paramIntent.getStringExtra("account");
    this.d = paramIntent.getStringExtra("ticket");
    this.e = paramIntent.getShortExtra("ticketType", 0);
    this.f = paramIntent.getStringExtra("token");
  }

  public String toString()
  {
    return "TPushRegisterMessage [accessId=" + this.a + ", deviceId=" + this.b + ", account=" + this.c + ", ticket=" + this.d + ", ticketType=" + this.e + ", token=" + this.f + "]";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.XGPushRegisterResult
 * JD-Core Version:    0.6.0
 */