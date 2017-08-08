package com.pay.network.modle;

import com.pay.common.tool.APLog;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.http.APBaseHttpParam;
import com.pay.http.APHttpReqPost;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APToolAES;
import java.util.HashMap;

public class APGoodsTokenReq extends APHttpReqPost
{
  private String a;
  private String b;
  private boolean c;

  public APGoodsTokenReq()
  {
    String str1 = APAppDataInterface.singleton().getOfferid();
    String str2 = String.format("/v1/r/%s/activity_proc", new Object[] { str1 });
    String str3 = String.format("/v1/r/%s/activity_proc", new Object[] { str1 });
    String str4 = String.format("/v1/r/%s/activity_proc", new Object[] { str1 });
    Object localObject = "";
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = APAppDataInterface.singleton().getCustomCgi();
      arrayOfObject[1] = str1;
      String str5 = String.format("/v1/%s/%s/activity_proc", arrayOfObject);
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
    HashMap localHashMap = new HashMap();
    APUserInfo localAPUserInfo = localAPDataInterface.getUserInfo();
    localHashMap.put("openid", localAPUserInfo.openId);
    localHashMap.put("openkey", localAPUserInfo.openKey);
    localHashMap.put("session_id", localAPUserInfo.sessionId);
    localHashMap.put("session_type", localAPUserInfo.sessionType);
    localHashMap.put("zoneid", localAPUserInfo.zoneId);
    localHashMap.put("productid", this.a);
    localHashMap.put("itemnum", this.b);
    String str1;
    if (this.c)
    {
      localHashMap.put("appmode", "2");
      str1 = APAppDataInterface.singleton().getCryptoKey();
      if (!str1.equals(""))
        break label148;
      APLog.w("APGetTokenReq", "EncodeKey is null");
    }
    label148: 
    do
    {
      return;
      localHashMap.put("appmode", "1");
      break;
      String str2 = APCommMethod.MaptoString(localHashMap);
      String str3 = APToolAES.doEncode(str2, str1);
      this.httpParam.reqParam.put("openid", localAPUserInfo.openId);
      this.httpParam.reqParam.put("pf", localAPUserInfo.pf);
      this.httpParam.reqParam.put("pfkey", localAPUserInfo.pfKey);
      this.httpParam.reqParam.put("encrypt_msg", str3);
      this.httpParam.reqParam.put("msg_len", Integer.toString(str2.length()));
      this.httpParam.reqParam.put("key_len", "newkey");
      this.httpParam.reqParam.put("key_time", APAppDataInterface.singleton().getCryptKeyTime());
      this.httpParam.reqParam.put("format", "json");
      this.httpParam.reqParam.put("sdkversion", APCommMethod.getVersion());
      this.httpParam.reqParam.put("session_token", localAPDataInterface.getOrderInfo().sessionToken);
    }
    while (!APAppDataInterface.singleton().getEnv().equals("dev"));
    this.httpParam.reqParam.put("offer_id", APAppDataInterface.singleton().getOfferid());
  }

  public void startService(String paramString1, String paramString2, boolean paramBoolean)
  {
    this.a = paramString1;
    this.b = paramString2;
    this.c = paramBoolean;
    startRequest();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APGoodsTokenReq
 * JD-Core Version:    0.6.0
 */