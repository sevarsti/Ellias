package com.pay.network.modle;

import com.pay.data.orderInfo.APOrderInfo;
import com.pay.http.APBaseHttpParam;
import com.pay.http.APHttpReqPost;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import java.util.HashMap;

public class APMpReq extends APHttpReqPost
{
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;

  public APMpReq(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    String str1 = APAppDataInterface.singleton().getOfferid();
    String str2 = String.format("/v1/r/%s/mobile_mp_info", new Object[] { str1 });
    String str3 = String.format("/v1/r/%s/mobile_mp_info", new Object[] { str1 });
    String str4 = String.format("/v1/r/%s/mobile_mp_info", new Object[] { str1 });
    Object localObject = "";
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = APAppDataInterface.singleton().getCustomCgi();
      arrayOfObject[1] = str1;
      String str5 = String.format("/v1/%s/%s/mobile_mp_info", arrayOfObject);
      localObject = str5;
      label99: setUrl((String)localObject, str2, str3, str4);
      this.a = paramString1;
      this.b = paramString2;
      this.c = paramString3;
      this.d = paramString4;
      this.e = paramString5;
      this.f = paramString6;
      this.g = paramString7;
      return;
    }
    catch (Exception localException)
    {
      break label99;
    }
  }

  public void constructParam()
  {
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    this.httpParam.reqParam.put("openid", this.a);
    this.httpParam.reqParam.put("pf", this.f);
    this.httpParam.reqParam.put("pfkey", this.g);
    this.httpParam.reqParam.put("format", "json");
    String str = "";
    if (localAPDataInterface.getOrderInfo() != null)
      str = localAPDataInterface.getOrderInfo().sessionToken;
    this.httpParam.reqParam.put("session_token", str);
    this.httpParam.reqParam.put("openkey", this.b);
    this.httpParam.reqParam.put("session_id", this.c);
    this.httpParam.reqParam.put("zoneid", this.e);
    this.httpParam.reqParam.put("session_type", this.d);
    this.httpParam.reqParam.put("sdkversion", APCommMethod.getVersion());
    this.httpParam.reqParam.put("key_len", "newkey");
    this.httpParam.reqParam.put("key_time", APAppDataInterface.singleton().getCryptKeyTime());
    if (APAppDataInterface.singleton().getEnv().equals("dev"))
      this.httpParam.reqParam.put("offer_id", APAppDataInterface.singleton().getOfferid());
  }

  public void starService()
  {
    startRequest();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APMpReq
 * JD-Core Version:    0.6.0
 */