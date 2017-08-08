package com.pay.network.modle;

import com.pay.data.userInfo.APUserInfo;
import com.pay.http.APBaseHttpParam;
import com.pay.http.APHttpReqPost;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APToolAES;
import java.util.HashMap;

public class APSmsCodeReq extends APHttpReqPost
{
  private String a;

  public APSmsCodeReq(String paramString)
  {
    String str1 = APAppDataInterface.singleton().getOfferid();
    String str2 = String.format("/v1/r/%s/mobile_mb_smscode", new Object[] { str1 });
    String str3 = String.format("/v1/r/%s/mobile_mb_smscode", new Object[] { str1 });
    String str4 = String.format("/v1/r/%s/mobile_mb_smscode", new Object[] { str1 });
    Object localObject = "";
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = APAppDataInterface.singleton().getCustomCgi();
      arrayOfObject[1] = str1;
      String str5 = String.format("/v1/%s/%s/get_pay_result", arrayOfObject);
      localObject = str5;
      label93: setUrl((String)localObject, str2, str3, str4);
      this.a = paramString;
      return;
    }
    catch (Exception localException)
    {
      break label93;
    }
  }

  public void constructParam()
  {
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    this.httpParam.reqParam.put("openid", localAPDataInterface.getUserInfo().openId);
    this.httpParam.reqParam.put("session_id", localAPDataInterface.getUserInfo().sessionId);
    this.httpParam.reqParam.put("key_len", "newkey");
    this.httpParam.reqParam.put("key_time", APAppDataInterface.singleton().getCryptKeyTime());
    HashMap localHashMap = new HashMap();
    localHashMap.put("openid", localAPDataInterface.getUserInfo().openId);
    localHashMap.put("openkey", localAPDataInterface.getUserInfo().openKey);
    localHashMap.put("pay_id", localAPDataInterface.getUserInfo().payId);
    localHashMap.put("auth_key", localAPDataInterface.getUserInfo().authKey);
    localHashMap.put("session_type", localAPDataInterface.getUserInfo().sessionType);
    localHashMap.put("sms_info", this.a);
    String str1 = APCommMethod.MaptoString(localHashMap);
    String str2 = APToolAES.doEncode(str1, APAppDataInterface.singleton().getCryptoKey());
    this.httpParam.reqParam.put("encrypt_msg", str2);
    this.httpParam.reqParam.put("msg_len", Integer.toString(str1.length()));
  }

  public void starService()
  {
    constructParam();
    startRequest();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APSmsCodeReq
 * JD-Core Version:    0.6.0
 */