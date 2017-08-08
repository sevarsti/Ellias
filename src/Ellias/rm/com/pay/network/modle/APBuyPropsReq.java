package com.pay.network.modle;

import com.pay.common.tool.APLog;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.http.APBaseHttpParam;
import com.pay.http.APHttpReqPost;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APToolAES;
import java.util.HashMap;

public class APBuyPropsReq extends APHttpReqPost
{
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private String h;
  private String i;
  private String j;
  private String k;
  private String l;
  private String m;
  private String n;

  public APBuyPropsReq(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    this.a = paramString1;
    this.b = paramString2;
    this.c = paramString3;
    this.d = paramString4;
    this.e = paramString5;
    this.f = paramString6;
    this.g = paramString7;
    String str1 = APAppDataInterface.singleton().getOfferid();
    String str2 = String.format("/cgi-bin/buy_props.fcg", new Object[] { str1 });
    String str3 = String.format("/v1/r/%s/buy_props.fcg", new Object[] { str1 });
    String str4 = String.format("/v1/r/%s/buy_props.fcg", new Object[] { str1 });
    Object localObject = "";
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = APAppDataInterface.singleton().getCustomCgi();
      arrayOfObject[1] = str1;
      String str5 = String.format("/v1/%s/%s/buy_props.fcg", arrayOfObject);
      localObject = str5;
      label138: setUrl((String)localObject, str2, str3, str4);
      return;
    }
    catch (Exception localException)
    {
      break label138;
    }
  }

  public void constructParam()
  {
    String str1 = APAppDataInterface.singleton().getCryptoKey();
    if (str1.equals(""))
    {
      APLog.w("APGetTokenReq", "EncodeKey is null");
      return;
    }
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    this.httpParam.reqParam.put("openid", this.a);
    HashMap localHashMap = new HashMap();
    localHashMap.put("openid", this.a);
    localHashMap.put("openkey", this.b);
    localHashMap.put("session_id", this.c);
    localHashMap.put("session_type", this.d);
    localHashMap.put("offer_id", APAppDataInterface.singleton().getOfferid());
    localHashMap.put("productid", this.j);
    localHashMap.put("currencytype", this.l);
    localHashMap.put("itemtype", this.n);
    localHashMap.put("itemnum", this.k);
    localHashMap.put("payitem", this.m);
    String str2 = APCommMethod.MaptoString(localHashMap);
    String str3 = APToolAES.doEncode(str2, str1);
    this.httpParam.reqParam.put("openid", this.a);
    this.httpParam.reqParam.put("pf", this.f);
    this.httpParam.reqParam.put("pfkey", this.g);
    this.httpParam.reqParam.put("zoneid", this.e);
    this.httpParam.reqParam.put("goodsmeta", this.h);
    this.httpParam.reqParam.put("goodsurl", this.i);
    this.httpParam.reqParam.put("encrypt_msg", str3);
    this.httpParam.reqParam.put("msg_len", Integer.toString(str2.length()));
    this.httpParam.reqParam.put("format", "json");
    this.httpParam.reqParam.put("version", "1");
    this.httpParam.reqParam.put("sdkversion", APCommMethod.getVersion());
    this.httpParam.reqParam.put("session_token", localAPDataInterface.getOrderInfo().sessionToken);
    this.httpParam.reqParam.put("key_len", "newkey");
    this.httpParam.reqParam.put("key_time", APAppDataInterface.singleton().getCryptKeyTime());
    if (APAppDataInterface.singleton().getEnv().equals("dev"))
      this.httpParam.reqParam.put("offer_id", APAppDataInterface.singleton().getOfferid());
    super.constructParam();
  }

  public void startService(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8)
  {
    this.h = paramString1;
    this.i = paramString2;
    this.j = paramString3;
    this.k = paramString4;
    this.l = paramString5;
    this.m = paramString6;
    this.n = paramString7;
    startRequest();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APBuyPropsReq
 * JD-Core Version:    0.6.0
 */