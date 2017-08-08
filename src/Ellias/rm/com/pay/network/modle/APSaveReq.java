package com.pay.network.modle;

import android.text.TextUtils;
import com.pay.AndroidPay;
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
import com.pay.tool.APSecretKeyManager;
import com.pay.tool.APToolAES;
import com.pay.ui.channel.APChannelList;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class APSaveReq extends APHttpReqPost
{
  public static final int KEYTYPE_CRYPTOKEY = 1;
  public static final int KEYTYPE_SECRETKEY;
  private HashMap a = new HashMap();
  public int curChannel = 0;
  public int curReqType;

  public APSaveReq(int paramInt)
  {
    this.httpParam.connectTimeout = 15000;
    this.httpParam.readTimeout = 20000;
    this.httpParam.reTryTimes = 0;
    String str1 = APAppDataInterface.singleton().getOfferid();
    Object localObject1 = "";
    Object localObject2 = "";
    Object localObject3 = "";
    String str2 = "";
    switch (paramInt)
    {
    default:
    case 0:
    case 2:
    case 3:
    case 4:
    case 5:
    case 1:
    }
    while (true)
    {
      setUrl((String)localObject1, (String)localObject2, (String)localObject3, str2);
      return;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = APAppDataInterface.singleton().getCustomCgi();
      arrayOfObject2[1] = str1;
      localObject1 = String.format("/v1/%s/%s/mobile_save", arrayOfObject2);
      localObject2 = String.format("/v1/r/%s/mobile_save", new Object[] { str1 });
      localObject3 = String.format("/v1/r/%s/mobile_save", new Object[] { str1 });
      str2 = String.format("/v1/r/%s/mobile_save", new Object[] { str1 });
      continue;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = APAppDataInterface.singleton().getCustomCgi();
      arrayOfObject1[1] = str1;
      localObject1 = String.format("/v1/%s/%s/mobile_save_month", arrayOfObject1);
      localObject2 = String.format("/v1/r/%s/mobile_save_month", new Object[] { str1 });
      localObject3 = String.format("/v1/r/%s/mobile_save_month", new Object[] { str1 });
      str2 = String.format("/v1/r/%s/mobile_save_month", new Object[] { str1 });
      continue;
      String str3 = ((APBuyGoodsInfo)APDataInterface.singleton().getOrderInfo().buyInfo).goodsSaveUrl;
      str2 = str3.substring(0, str3.lastIndexOf("?"));
      String str4 = str3.substring(9 + str3.lastIndexOf("token_id="));
      setUrl(str2, str2, str2, str2);
      APDataInterface.singleton().getOrderInfo().tokenId = str4;
      localObject3 = str2;
      localObject2 = str2;
      localObject1 = str2;
    }
  }

  public void constructParam()
  {
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    String str1 = APAppDataInterface.singleton().getCryptoKey();
    String str2 = APAppDataInterface.singleton().getCryptKeyTime();
    if ((AndroidPay.singleton().IsNeedUinLogin()) && ((localAPDataInterface.getOrderInfo().saveType == 2) || (localAPDataInterface.getOrderInfo().saveType == 3)))
    {
      str1 = APSecretKeyManager.getInstance(AndroidPay.singleton().applicationContext).readCryptKey(localAPDataInterface.getUserInfo().payId);
      str2 = APSecretKeyManager.getInstance(AndroidPay.singleton().applicationContext).readCryptKeyTime(localAPDataInterface.getUserInfo().payId);
    }
    this.httpParam.reqParam.put("key_time", str2);
    String str3 = APCommMethod.MaptoString(this.a);
    this.httpParam.reqParam.put("encrypt_msg", APToolAES.doEncode(str3, str1));
    this.httpParam.reqParam.put("msg_len", Integer.toString(str3.length()));
    super.constructParam();
  }

  protected void saveCommonParams(int paramInt, HashMap paramHashMap1, HashMap paramHashMap2)
  {
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    APUserInfo localAPUserInfo = localAPDataInterface.getUserInfo();
    if (("hy_gameid".equals(localAPUserInfo.sessionId)) && ("wc_actoken".equals(localAPUserInfo.sessionType)) && ((paramInt == 2) || (paramInt == 3)))
    {
      paramHashMap1.put("openid", localAPUserInfo.payId);
      paramHashMap2.put("openid", localAPUserInfo.payId);
      paramHashMap2.put("openkey", localAPUserInfo.authKey);
      paramHashMap2.put("session_id", "uin");
      paramHashMap2.put("session_type", "skey");
    }
    while (true)
    {
      paramHashMap2.put("token_id", localAPDataInterface.getOrderInfo().tokenId);
      String str = APAppDataInterface.singleton().getEnv();
      if ((str.equals("dev")) || (str.equals("test")))
        paramHashMap1.put("offer_id", APAppDataInterface.singleton().getOfferid());
      paramHashMap1.put("pf", localAPUserInfo.pf);
      paramHashMap1.put("pfkey", localAPUserInfo.pfKey);
      paramHashMap1.put("token_id", localAPDataInterface.getOrderInfo().tokenId);
      paramHashMap1.put("reqtype", "cpay");
      paramHashMap1.put("sdkversion", APCommMethod.getVersion());
      paramHashMap1.put("session_token", localAPDataInterface.getOrderInfo().sessionToken);
      paramHashMap1.put("express_channel", localAPDataInterface.getOrderInfo().expressChannel);
      paramHashMap1.put("default_channel", APChannelList.singleton().getDefaultChannel());
      paramHashMap1.put("key_len", "newkey");
      switch (paramInt)
      {
      default:
        return;
        paramHashMap1.put("openid", localAPUserInfo.openId);
        paramHashMap2.put("openid", localAPUserInfo.openId);
        paramHashMap2.put("openkey", localAPUserInfo.openKey);
        paramHashMap2.put("session_id", localAPUserInfo.sessionId);
        paramHashMap2.put("session_type", localAPUserInfo.sessionType);
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
    }
    paramHashMap2.put("accounttype", localAPUserInfo.acctType);
    paramHashMap2.put("zoneid", localAPUserInfo.zoneId);
    return;
    paramHashMap2.put("accounttype", "qd");
    return;
    paramHashMap2.put("accounttype", "qb");
    return;
    APBuyMonthInfo localAPBuyMonthInfo = (APBuyMonthInfo)localAPDataInterface.getOrderInfo().buyInfo;
    paramHashMap1.put("service_code", localAPBuyMonthInfo.serviceCode);
    paramHashMap1.put("product_id", localAPBuyMonthInfo.productId);
    paramHashMap1.put("auto_cont", localAPBuyMonthInfo.autoPay);
    paramHashMap2.put("zoneid", localAPUserInfo.zoneId);
    try
    {
      APMonthDataInterface localAPMonthDataInterface = APMonthDataInterface.singleton();
      if (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate)
        paramHashMap1.put("service_name", URLEncoder.encode(localAPMonthDataInterface.getUnit(), "UTF-8").toString());
      while (true)
      {
        try
        {
          paramHashMap1.put("remark", URLEncoder.encode(localAPBuyMonthInfo.payRemark, "UTF-8").toString());
          return;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException2)
        {
          localUnsupportedEncodingException2.printStackTrace();
          return;
        }
        paramHashMap1.put("service_name", URLEncoder.encode(localAPBuyMonthInfo.serviceName, "UTF-8").toString());
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException1)
    {
      while (true)
        localUnsupportedEncodingException1.printStackTrace();
    }
  }

  public void startServiceAcct(int paramInt1, int paramInt2, String paramString)
  {
    this.curChannel = paramInt2;
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    saveCommonParams(paramInt1, this.httpParam.reqParam, this.a);
    this.a.put("session_id", localAPDataInterface.getUserInfo().sessionId);
    this.a.put("session_type", localAPDataInterface.getUserInfo().sessionType);
    if (paramInt2 == 0)
      this.a.put("pay_method", "qdqb");
    while (true)
    {
      this.a.put("buy_quantity", paramString);
      this.a.put("pay_id", localAPDataInterface.getUserInfo().payId);
      this.a.put("auth_key", localAPDataInterface.getUserInfo().authKey);
      this.a.put("h5_mb_url", URLEncoder.encode("http://unipay.mibaocheck"));
      startRequest();
      return;
      if (paramInt2 != 11)
        continue;
      this.a.put("pay_method", "qbqd");
    }
  }

  public void startServiceAcct(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3)
  {
    this.a.put("mb_pwd", paramString1);
    this.httpParam.reqParam.put("sms_info", APDataInterface.singleton().getSmsInfo());
    this.httpParam.reqParam.put("mb_type", paramString2);
    this.curChannel = paramInt2;
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    saveCommonParams(paramInt1, this.httpParam.reqParam, this.a);
    this.a.put("session_id", localAPDataInterface.getUserInfo().sessionId);
    this.a.put("session_type", localAPDataInterface.getUserInfo().sessionType);
    if (paramInt2 == 0)
      this.a.put("pay_method", "qdqb");
    while (true)
    {
      this.a.put("buy_quantity", paramString3);
      this.a.put("pay_id", localAPDataInterface.getUserInfo().payId);
      this.a.put("auth_key", localAPDataInterface.getUserInfo().authKey);
      this.a.put("h5_mb_url", URLEncoder.encode("http://unipay.mibaocheck"));
      startRequest();
      return;
      if (paramInt2 != 11)
        continue;
      this.a.put("pay_method", "qbqd");
    }
  }

  public void startServiceGoldCoupons(int paramInt, String paramString)
  {
    this.curChannel = 10;
    saveCommonParams(paramInt, this.httpParam.reqParam, this.a);
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    this.a.put("pay_method", "gold_coupons");
    this.a.put("buy_quantity", paramString);
    this.a.put("auth_key", localAPDataInterface.getUserInfo().authKey);
    startRequest();
  }

  public void startServiceHF(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.curChannel = 9;
    saveCommonParams(paramInt, this.httpParam.reqParam, this.a);
    this.a.put("pay_method", "hfpay");
    this.a.put("buy_quantity", paramString3);
    this.a.put("mfrom", paramString4);
    this.a.put("mobile", paramString2);
    this.a.put("mobile_loc", paramString1);
    startRequest();
  }

  public void startServiceMcard(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, String paramString5, String paramString6, String paramString7)
  {
    this.curChannel = 5;
    saveCommonParams(paramInt, this.httpParam.reqParam, this.a);
    this.a.put("pay_method", "mcard");
    this.a.put("buy_quantity", paramString4);
    this.a.put("pay_id", paramString1);
    this.a.put("auth_key", paramString2);
    this.a.put("card_value", paramString3);
    this.httpParam.reqParam.put("verify_code", paramString6);
    this.httpParam.reqParam.put("verify_session", paramString7);
    startRequest();
  }

  public void startServiceQQCard(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5)
  {
    this.curChannel = 4;
    saveCommonParams(paramInt, this.httpParam.reqParam, this.a);
    this.a.put("pay_method", "qqcard");
    this.a.put("buy_quantity", paramString3);
    this.a.put("pay_id", paramString1);
    this.a.put("auth_key", paramString2);
    this.httpParam.reqParam.put("verify_code", paramString4);
    this.httpParam.reqParam.put("verify_session", paramString5);
    startRequest();
  }

  public void startServiceTenpay(int paramInt1, int paramInt2, String paramString)
  {
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    switch (paramInt1)
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      startRequest();
      return;
      this.curChannel = 1;
      saveCommonParams(paramInt2, this.httpParam.reqParam, this.a);
      this.a.put("pay_method", "cft");
      this.a.put("buy_quantity", paramString);
      this.a.put("pay_id", localAPDataInterface.getUserInfo().payId);
      this.a.put("auth_key", localAPDataInterface.getUserInfo().authKey);
      this.httpParam.reqParam.put("cft_type", "tokenid");
      continue;
      this.curChannel = 2;
      saveCommonParams(paramInt2, this.httpParam.reqParam, this.a);
      this.a.put("pay_method", "bank");
      this.a.put("buy_quantity", paramString);
      this.a.put("pay_id", localAPDataInterface.getUserInfo().payId);
      this.a.put("auth_key", localAPDataInterface.getUserInfo().authKey);
      if ((!TextUtils.isEmpty(localAPDataInterface.getPayAssignChannel())) && (!TextUtils.isEmpty(localAPDataInterface.getDiscountType())) && (!TextUtils.isEmpty(localAPDataInterface.getDiscountUrl())))
      {
        this.httpParam.reqParam.put("discounttype", localAPDataInterface.getDiscountType());
        this.httpParam.reqParam.put("discountextras", localAPDataInterface.getDiscountExtras());
        this.httpParam.reqParam.put("uuid", localAPDataInterface.getUuid());
        APLog.i("discounttype == ", localAPDataInterface.getDiscountType());
        APLog.i("uuid == ", localAPDataInterface.getUuid());
      }
      if ((AndroidPay.singleton().IsNeedUinLogin()) && (!APDataInterface.singleton().getUserInfo().isUinLogin) && (!APDataInterface.singleton().getUserInfo().isKJUser))
      {
        this.a.put("pay_id", "");
        this.a.put("auth_key", "");
      }
      this.httpParam.reqParam.put("cft_type", "tokenid");
      continue;
      this.curChannel = 3;
      saveCommonParams(paramInt2, this.httpParam.reqParam, this.a);
      this.a.put("pay_method", "kj");
      this.a.put("buy_quantity", paramString);
      this.a.put("pay_id", localAPDataInterface.getUserInfo().payId);
      this.a.put("auth_key", localAPDataInterface.getUserInfo().authKey);
    }
  }

  public void startServiceWeChat(int paramInt, String paramString)
  {
    this.curChannel = 8;
    saveCommonParams(paramInt, this.httpParam.reqParam, this.a);
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    this.a.put("pay_method", "wechat");
    this.a.put("buy_quantity", paramString);
    this.a.put("pay_id", localAPDataInterface.getUserInfo().payId);
    this.a.put("auth_key", localAPDataInterface.getUserInfo().authKey);
    if ((!TextUtils.isEmpty(localAPDataInterface.getPayAssignChannel())) && (!TextUtils.isEmpty(localAPDataInterface.getDiscountType())) && (!TextUtils.isEmpty(localAPDataInterface.getDiscountUrl())))
    {
      this.httpParam.reqParam.put("discounttype", localAPDataInterface.getDiscountType());
      this.httpParam.reqParam.put("discountextras", localAPDataInterface.getDiscountExtras());
      this.httpParam.reqParam.put("uuid", localAPDataInterface.getUuid());
      APLog.i("discounttype == ", localAPDataInterface.getDiscountType());
      APLog.i("uuid == ", localAPDataInterface.getUuid());
    }
    startRequest();
  }

  public void startServiceYB(int paramInt, String paramString)
  {
    this.curChannel = 7;
    saveCommonParams(paramInt, this.httpParam.reqParam, this.a);
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    this.a.put("pay_method", "yb");
    this.a.put("buy_quantity", paramString);
    this.a.put("auth_key", localAPDataInterface.getUserInfo().authKey);
    startRequest();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APSaveReq
 * JD-Core Version:    0.6.0
 */