package com.pay.network.modle;

import com.pay.data.userInfo.APUserInfo;
import com.pay.http.APBaseHttpParam;
import com.pay.http.APHttpReqPost;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APDataInterface;
import java.util.HashMap;

public class APQueryMarketResultReq extends APHttpReqPost
{
  public APQueryMarketResultReq()
  {
    String str1 = APAppDataInterface.singleton().getOfferid();
    String str2 = String.format("/v1/r/%s/get_pay_result", new Object[] { str1 });
    String str3 = String.format("/v1/r/%s/get_pay_result", new Object[] { str1 });
    String str4 = String.format("/v1/r/%s/get_pay_result", new Object[] { str1 });
    Object localObject = "";
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = APAppDataInterface.singleton().getCustomCgi();
      arrayOfObject[1] = str1;
      String str5 = String.format("/v1/%s/%s/get_pay_result", arrayOfObject);
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
    this.httpParam.reqParam.put("uuid", localAPDataInterface.getUuid());
    this.httpParam.reqParam.put("key_len", "newkey");
    this.httpParam.reqParam.put("key_time", APAppDataInterface.singleton().getCryptKeyTime());
  }

  public void startService()
  {
    constructParam();
    startRequest();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APQueryMarketResultReq
 * JD-Core Version:    0.6.0
 */