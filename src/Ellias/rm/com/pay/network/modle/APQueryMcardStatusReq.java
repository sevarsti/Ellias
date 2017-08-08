package com.pay.network.modle;

import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.http.APBaseHttpParam;
import com.pay.http.APHttpReqPost;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APDataInterface;
import java.util.HashMap;

public class APQueryMcardStatusReq extends APHttpReqPost
{
  private String a;

  public APQueryMcardStatusReq()
  {
    String str1 = APAppDataInterface.singleton().getOfferid();
    String str2 = String.format("/v1/r/%s/mobile_get_cardbill_info", new Object[] { str1 });
    String str3 = String.format("/v1/r/%s/mobile_get_cardbill_info", new Object[] { str1 });
    String str4 = String.format("/v1/r/%s/mobile_get_cardbill_info", new Object[] { str1 });
    Object localObject = "";
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = APAppDataInterface.singleton().getCustomCgi();
      arrayOfObject[1] = str1;
      String str5 = String.format("/v1/%s/%s/mobile_get_cardbill_info", arrayOfObject);
      localObject = str5;
      label92: setUrl((String)localObject, str2, str3, str4);
      return;
    }
    catch (Exception localException)
    {
      break label92;
    }
  }

  public void constructParam()
  {
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    APUserInfo localAPUserInfo = localAPDataInterface.getUserInfo();
    this.httpParam.reqParam.put("openid", localAPUserInfo.openId);
    this.httpParam.reqParam.put("openkey", localAPUserInfo.openKey);
    this.httpParam.reqParam.put("session_id", localAPUserInfo.sessionId);
    this.httpParam.reqParam.put("session_type", localAPUserInfo.sessionType);
    this.httpParam.reqParam.put("pf", localAPUserInfo.pf);
    this.httpParam.reqParam.put("pfkey", localAPUserInfo.pfKey);
    this.httpParam.reqParam.put("billno", this.a);
    this.httpParam.reqParam.put("format", "json");
    this.httpParam.reqParam.put("sdkversion", "1.3.7b");
    this.httpParam.reqParam.put("session_token", localAPDataInterface.getOrderInfo().sessionToken);
    this.httpParam.reqParam.put("key_len", "newkey");
    this.httpParam.reqParam.put("key_time", APAppDataInterface.singleton().getCryptKeyTime());
    if (APAppDataInterface.singleton().getEnv().equals("dev"))
      this.httpParam.reqParam.put("offer_id", APAppDataInterface.singleton().getOfferid());
  }

  public void startService(String paramString)
  {
    this.a = paramString;
    startRequest();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APQueryMcardStatusReq
 * JD-Core Version:    0.6.0
 */