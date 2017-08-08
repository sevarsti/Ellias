package com.pay.network.modle;

import com.pay.common.tool.APLog;
import com.pay.data.buyInfo.APBuyGoodsInfo;
import com.pay.data.buyInfo.APBuyMonthInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.http.APBaseHttpParam;
import com.pay.http.APHttpReqPost;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APMonthDataInterface;
import com.pay.tool.APMonthDataInterface.MonthOpenType;
import com.pay.tool.APToolAES;
import java.util.HashMap;

public class APGetExpressPayReq extends APHttpReqPost
{
  public static final int EXPRESSTYPE_GAMES = 0;
  public static final int EXPRESSTYPE_GOODS = 1;
  public static final int EXPRESSTYPE_MONTH = 3;
  public static final int EXPRESSTYPE_QDQB = 2;
  public int curReqType;

  public APGetExpressPayReq()
  {
    String str1 = APAppDataInterface.singleton().getOfferid();
    String str7;
    String str8;
    String str2;
    String str3;
    String str4;
    String str5;
    if (APDataInterface.singleton().getOrderInfo().saveType == 1)
    {
      String str6 = ((APBuyGoodsInfo)APDataInterface.singleton().getOrderInfo().buyInfo).buyGoodsUrl;
      str7 = APAppDataInterface.singleton().getEnv();
      str8 = String.format("/v1/r/%s/mobile_get_channel", new Object[] { str1 });
      str2 = str6.replace("mobile_goods_info", "mobile_get_channel");
      if (str7.equals("test"))
      {
        str3 = str8;
        str4 = str2;
        str2 = "";
        str5 = "";
      }
    }
    while (true)
    {
      setUrl(str2, str3, str4, str5);
      return;
      if (str7.equals("release"))
      {
        str4 = "";
        str3 = str8;
        str5 = str2;
        str2 = "";
        continue;
      }
      if (str7.equals("custom"))
      {
        str5 = "";
        str4 = "";
        str3 = str8;
        continue;
        str2 = String.format("/v1/%s/%s/mobile_get_channel", new Object[] { "r", str1 });
        str3 = String.format("/v1/r/%s/mobile_get_channel", new Object[] { str1 });
        str4 = String.format("/v1/r/%s/mobile_get_channel", new Object[] { str1 });
        str5 = String.format("/v1/r/%s/mobile_get_channel", new Object[] { str1 });
        continue;
      }
      str2 = "";
      str5 = "";
      str4 = "";
      str3 = str8;
    }
  }

  public void constructParam()
  {
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    APUserInfo localAPUserInfo = localAPDataInterface.getUserInfo();
    APOrderInfo localAPOrderInfo = APDataInterface.singleton().getOrderInfo();
    HashMap localHashMap = new HashMap();
    localHashMap.put("openid", localAPUserInfo.openId);
    localHashMap.put("openkey", localAPUserInfo.openKey);
    localHashMap.put("session_id", localAPUserInfo.sessionId);
    localHashMap.put("session_type", localAPUserInfo.sessionType);
    localHashMap.put("mb_recommend_flag", "1");
    localHashMap.put("buy_quantity", localAPOrderInfo.saveNum);
    localHashMap.put("pf", localAPUserInfo.pf);
    localHashMap.put("pfkey", localAPUserInfo.pfKey);
    localHashMap.put("reqtype", "cpay");
    String str1 = "";
    String str2;
    String str3;
    switch (this.curReqType)
    {
    default:
      localHashMap.put("type", str1);
      str2 = APCommMethod.MaptoString(localHashMap);
      str3 = APAppDataInterface.singleton().getCryptoKey();
      if ((str3 == null) || (!str3.equals("")))
        break;
      APLog.w("APGetTokenReq", "EncodeKey is null");
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    String str5;
    do
    {
      return;
      localHashMap.put("zoneid", localAPUserInfo.zoneId);
      localHashMap.put("accounttype", localAPUserInfo.acctType);
      str1 = "save";
      break;
      localHashMap.put("zoneid", localAPUserInfo.zoneId);
      str1 = "bg";
      break;
      str1 = "qd";
      break;
      str1 = "qb";
      break;
      APBuyMonthInfo localAPBuyMonthInfo2 = (APBuyMonthInfo)localAPDataInterface.getOrderInfo().buyInfo;
      localHashMap.put("service_code", localAPBuyMonthInfo2.serviceCode);
      if (((localAPDataInterface.getOrderInfo().saveType == 5) || (localAPDataInterface.getOrderInfo().saveType == 4)) && (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate))
        localHashMap.put("product_id", localAPBuyMonthInfo2.productId);
      if (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate)
      {
        str1 = "unimonth";
        break;
      }
      str1 = "month";
      break;
      APBuyMonthInfo localAPBuyMonthInfo1 = (APBuyMonthInfo)localAPDataInterface.getOrderInfo().buyInfo;
      localHashMap.put("service_code", localAPBuyMonthInfo1.serviceCode);
      localHashMap.put("product_id", localAPBuyMonthInfo1.productId);
      str1 = "unimonth";
      break;
      String str4 = APToolAES.doEncode(str2, str3);
      this.httpParam.reqParam.put("token_id", localAPDataInterface.getOrderInfo().tokenId);
      this.httpParam.reqParam.put("openid", localAPUserInfo.openId);
      this.httpParam.reqParam.put("format", "json");
      this.httpParam.reqParam.put("sdkversion", APCommMethod.getVersion());
      this.httpParam.reqParam.put("session_token", localAPOrderInfo.sessionToken);
      this.httpParam.reqParam.put("encrypt_msg", str4);
      this.httpParam.reqParam.put("msg_len", Integer.toString(str2.length()));
      this.httpParam.reqParam.put("key_len", "newkey");
      this.httpParam.reqParam.put("key_time", APAppDataInterface.singleton().getCryptKeyTime());
      str5 = APAppDataInterface.singleton().getEnv();
    }
    while ((!str5.equals("dev")) && (!str5.equals("test")));
    this.httpParam.reqParam.put("offer_id", APAppDataInterface.singleton().getOfferid());
  }

  public void startService(int paramInt)
  {
    this.curReqType = paramInt;
    startRequest();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APGetExpressPayReq
 * JD-Core Version:    0.6.0
 */