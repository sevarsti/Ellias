package com.pay.network.modle;

import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.http.APBaseHttpParam;
import com.pay.http.APHttpsReqPost;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APToolAES;
import java.util.HashMap;

public class APGetKeyReq extends APHttpsReqPost
{
  public static final int CHANGEKEY_REASON_SAVEACCT = 1;
  public static final int KEYTYPE_CRYPTOKEY = 1;
  public static final int KEYTYPE_SECRETKEY;
  private int a;
  public int changeKeyReason = 0;
  public int curReqType;

  public APGetKeyReq()
  {
    this.httpParam.port = "442";
    this.httpParam.reTryTimes = 3;
    String str1 = APAppDataInterface.singleton().getOfferid();
    String str2 = String.format("/v1/r/%s/mobile_get_key", new Object[] { str1 });
    String str3 = String.format("/v1/r/%s/mobile_get_key", new Object[] { str1 });
    String str4 = String.format("/v1/r/%s/mobile_get_key", new Object[] { str1 });
    Object localObject = "";
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = APAppDataInterface.singleton().getCustomCgi();
      arrayOfObject[1] = str1;
      String str5 = String.format("/v1/%s/%s/mobile_get_key", arrayOfObject);
      localObject = str5;
      label114: setUrl((String)localObject, str2, str3, str4);
      return;
    }
    catch (Exception localException)
    {
      break label114;
    }
  }

  public void constructParam()
  {
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    APUserInfo localAPUserInfo = localAPDataInterface.getUserInfo();
    this.httpParam.reqParam.put("pf", localAPUserInfo.pf);
    this.httpParam.reqParam.put("pfkey", localAPUserInfo.pfKey);
    this.httpParam.reqParam.put("format", "json");
    this.httpParam.reqParam.put("session_token", localAPDataInterface.getOrderInfo().sessionToken);
    this.httpParam.reqParam.put("sdkversion", APCommMethod.getVersion());
    this.httpParam.reqParam.put("key_len", "newkey");
    HashMap localHashMap = new HashMap();
    if (this.changeKeyReason == 1)
    {
      this.httpParam.reqParam.put("openid", localAPUserInfo.payId);
      localHashMap.put("openid", localAPUserInfo.payId);
      localHashMap.put("openkey", localAPUserInfo.authKey);
      localHashMap.put("session_id", "uin");
      localHashMap.put("session_type", "skey");
    }
    while (true)
      switch (this.a)
      {
      default:
        return;
        this.httpParam.reqParam.put("openid", localAPUserInfo.openId);
        localHashMap.put("openid", localAPUserInfo.openId);
        localHashMap.put("openkey", localAPUserInfo.openKey);
        localHashMap.put("session_id", localAPUserInfo.sessionId);
        localHashMap.put("session_type", localAPUserInfo.sessionType);
      case 0:
      case 1:
      }
    localHashMap.put("key", APAppDataInterface.singleton().getBaseKey());
    this.httpParam.reqParam.put("type", "secret");
    this.httpParam.reqParam.put("vid", APAppDataInterface.singleton().getVid());
    this.curReqType = 0;
    String str1 = APCommMethod.MaptoString(localHashMap);
    for (String str2 = APToolAES.doEncode(str1, APAppDataInterface.singleton().getBaseKey()); ; str2 = APToolAES.doEncode(str1, APAppDataInterface.singleton().getSecretKey()))
    {
      this.httpParam.reqParam.put("encrypt_msg", str2);
      this.httpParam.reqParam.put("msg_len", Integer.toString(str1.length()));
      if (!APAppDataInterface.singleton().getEnv().equals("dev"))
        break;
      this.httpParam.reqParam.put("offer_id", APAppDataInterface.singleton().getOfferid());
      return;
      localHashMap.put("key", APAppDataInterface.singleton().getSecretKey());
      this.httpParam.reqParam.put("type", "crypto");
      this.httpParam.reqParam.put("vid", "");
      this.curReqType = 1;
      str1 = APCommMethod.MaptoString(localHashMap);
    }
  }

  public void starService(int paramInt1, int paramInt2)
  {
    this.a = paramInt1;
    this.changeKeyReason = paramInt2;
    startRequest();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APGetKeyReq
 * JD-Core Version:    0.6.0
 */