package com.tencent.tmassistantsdk.e;

import com.qq.taf.jce.JceStruct;
import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.protocol.jce.GetSettingsRequest;
import com.tencent.tmassistantsdk.protocol.jce.GetSettingsResponse;

public class d extends g
{
  protected f a = null;

  public void a()
  {
    super.a(new GetSettingsRequest());
  }

  protected void a(JceStruct paramJceStruct1, JceStruct paramJceStruct2, int paramInt)
  {
    if (paramJceStruct2 == null)
      l.b("GetSettingHttpRequest", "response is null!");
    do
      return;
    while ((this.a == null) || (paramInt != 0) || (!(paramJceStruct2 instanceof GetSettingsResponse)));
    GetSettingsResponse localGetSettingsResponse = (GetSettingsResponse)paramJceStruct2;
    if (localGetSettingsResponse.ret == 0)
    {
      this.a.a((GetSettingsRequest)paramJceStruct1, localGetSettingsResponse, true);
      return;
    }
    this.a.a((GetSettingsRequest)paramJceStruct1, localGetSettingsResponse, false);
  }

  public void a(f paramf)
  {
    this.a = paramf;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.e.d
 * JD-Core Version:    0.6.0
 */