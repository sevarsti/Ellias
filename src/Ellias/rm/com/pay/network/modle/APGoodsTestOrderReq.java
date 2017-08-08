package com.pay.network.modle;

import com.pay.data.userInfo.APUserInfo;
import com.pay.http.APBaseHttpParam;
import com.pay.http.APHttpReqPost;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class APGoodsTestOrderReq extends APHttpReqPost
{
  private String a;
  private String b;

  public APGoodsTestOrderReq()
  {
    String str1 = APAppDataInterface.singleton().getOfferid();
    String str2 = String.format("/v1/r/%s/buy_goods_sdk", new Object[] { str1 });
    String str3 = String.format("/v1/r/%s/buy_goods_sdk", new Object[] { str1 });
    String str4 = String.format("/v1/r/%s/buy_goods_sdk", new Object[] { str1 });
    Object localObject = "";
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = APAppDataInterface.singleton().getCustomCgi();
      arrayOfObject[1] = str1;
      String str5 = String.format("/v1/%s/%s/buy_goods_sdk", arrayOfObject);
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
    APUserInfo localAPUserInfo = APDataInterface.singleton().getUserInfo();
    this.httpParam.reqParam.put("openid", localAPUserInfo.openId);
    this.httpParam.reqParam.put("openkey", localAPUserInfo.openKey);
    this.httpParam.reqParam.put("session_id", localAPUserInfo.sessionId);
    this.httpParam.reqParam.put("session_type", localAPUserInfo.sessionType);
    this.httpParam.reqParam.put("pf", localAPUserInfo.pf);
    this.httpParam.reqParam.put("pfkey", "pfKey");
    this.httpParam.reqParam.put("payitem", "1*1*" + this.b);
    this.httpParam.reqParam.put("key_len", "newkey");
    this.httpParam.reqParam.put("key_time", APAppDataInterface.singleton().getCryptKeyTime());
    try
    {
      this.httpParam.reqParam.put("goodsmeta", URLEncoder.encode("道具测试*数平钻石礼包测试", "UTF-8").toString());
      this.httpParam.reqParam.put("goodsurl", "");
      this.httpParam.reqParam.put("max_num", "100");
      this.httpParam.reqParam.put("appmode", this.a);
      this.httpParam.reqParam.put("app_metadata", "sdktest");
      this.httpParam.reqParam.put("reqtype", "cpay");
      this.httpParam.reqParam.put("zoneid", "1");
      this.httpParam.reqParam.put("format", "json");
      this.httpParam.reqParam.put("sdkversion", APCommMethod.getVersion());
      this.httpParam.reqParam.put("accounttype", localAPUserInfo.acctType);
      this.httpParam.reqParam.put("appid", APAppDataInterface.singleton().getOfferid());
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
        localUnsupportedEncodingException.printStackTrace();
    }
  }

  public void startService(String paramString1, String paramString2)
  {
    this.a = paramString1;
    this.b = paramString2;
    startRequest();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APGoodsTestOrderReq
 * JD-Core Version:    0.6.0
 */