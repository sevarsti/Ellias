package com.pay.network.modle;

import com.pay.data.buyInfo.APBuyGoodsInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.http.APBaseHttpParam;
import com.pay.http.APHttpReqPost;
import com.pay.http.IAPHttpAns;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import java.util.HashMap;

public class APMobileBuyGoodsReq extends APHttpReqPost
{
  private String a;

  public void constructParam()
  {
    APUserInfo localAPUserInfo = APDataInterface.singleton().getUserInfo();
    this.httpParam.reqParam.put("token_id", this.a);
    this.httpParam.reqParam.put("openid", localAPUserInfo.openId);
    this.httpParam.reqParam.put("openkey", localAPUserInfo.openKey);
    this.httpParam.reqParam.put("session_id", localAPUserInfo.sessionId);
    this.httpParam.reqParam.put("session_type", localAPUserInfo.sessionType);
    this.httpParam.reqParam.put("sdkversion", APCommMethod.getVersion());
    this.httpParam.reqParam.put("mb_recommend_flag", "1");
    this.httpParam.reqParam.put("key_len", "newkey");
    this.httpParam.reqParam.put("key_time", APAppDataInterface.singleton().getCryptKeyTime());
    String str = APAppDataInterface.singleton().getEnv();
    if ((str.equals("dev")) || (str.equals("test")))
      this.httpParam.reqParam.put("offer_id", APAppDataInterface.singleton().getOfferid());
    this.httpParam.reqParam.put("networkType", String.valueOf(APAppDataInterface.singleton().getNetworkState()));
  }

  public void startService(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
    {
      this.httpAns.onError(this, 2000, "系统繁忙,请稍后再试\n(订单获取失败)");
      return;
    }
    int i = paramString.lastIndexOf("?");
    if (i <= 0)
    {
      this.httpAns.onError(this, 2000, "订单参数错误");
      return;
    }
    String str1 = paramString.substring(0, i);
    int j = paramString.lastIndexOf("token_id=");
    if (j <= 0)
    {
      this.httpAns.onError(this, 2000, "订单参数错误");
      return;
    }
    this.a = paramString.substring(j + 9);
    String str2 = paramString.replace("mobile_goods_info", "mobile_save_goods");
    APBuyGoodsInfo localAPBuyGoodsInfo = (APBuyGoodsInfo)APDataInterface.singleton().getOrderInfo().buyInfo;
    localAPBuyGoodsInfo.buyGoodsUrl = str1;
    localAPBuyGoodsInfo.goodsSaveUrl = str2;
    APDataInterface.singleton().getOrderInfo().tokenId = this.a;
    setUrl(str1, str1, str1, str1);
    startRequest();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APMobileBuyGoodsReq
 * JD-Core Version:    0.6.0
 */