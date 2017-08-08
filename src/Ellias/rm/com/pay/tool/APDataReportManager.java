package com.pay.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.pay.AndroidPay;
import com.pay.common.tool.APLog;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.http.APNetworkManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class APDataReportManager
{
  public static final String ACCOUNTINPUT_BACK = "sdk.accountinput.back";
  public static final String ACCOUNTINPUT_CLOSE = "sdk.accountinput.close";
  public static final String ACCOUNTINPUT_KEYBACK = "sdk.accountinput.keyback";
  public static final String ACCOUNTINPUT_PRE = "e";
  public static final String ACCOUNTINPUT_SHOW = "sdk.accountinput.show";
  public static final String ACCOUNTINPUT_SURE = "sdk.accountinput.sure";
  public static final String ACCOUNTLIST_CLICK = "sdk.accountlist.click";
  public static final String ACCOUNTLIST_CLOSE = "sdk.accountlist.close";
  public static final String ACCOUNTLIST_ELSE = "sdk.accountlist.else";
  public static final String ACCOUNTLIST_KEYBACK = "sdk.accountlist.keyback";
  public static final String ACCOUNTLIST_PRE = "c";
  public static final String ACCOUNTLIST_SHOW = "sdk.accountlist.show";
  public static final String AUTO_FALSE = "disable";
  public static final String AUTO_TRUE = "able";
  public static final String CHANNELLIST_CLICK = "sdk.channellist.click";
  public static final String CHANNELLIST_DETAIL = "sdk.channellist.detail";
  public static final String CHANNELLIST_KEYBACK = "sdk.channellist.keyback";
  public static final String CHANNELLIST_MORE = "sdk.channellist.more";
  public static final String CHANNELLIST_SHOW = "sdk.channellist.show";
  public static final String CHANNEL_CLICK_INSTANT = "sdk.channellist.clickinstant";
  public static final String EXPRESS_CHANGE = "sdk.payexpress.change";
  public static final String EXPRESS_CLOSE = "sdk.payexpress.close";
  public static final String EXPRESS_KEYBACK = "sdk.payexpress.keyback";
  public static final String EXPRESS_SHOW = "sdk.payexpress.show";
  public static final String EXPRESS_SURE = "sdk.payexpress.sure";
  public static final String GAMEANDMONTHSINPUT_PRE = "b";
  public static final String GAMEANDMONTHSLIST_PRE = "a";
  public static final String GAMEINPUT_BACK = "sdk.gameinput.back";
  public static final String GAMEINPUT_CLOSE = "sdk.gameinput.close";
  public static final String GAMEINPUT_KEYBACK = "sdk.gameinput.keyback";
  public static final String GAMEINPUT_SHOW = "sdk.gameinput.show";
  public static final String GAMEINPUT_SURE = "sdk.gameinput.sure";
  public static final String GAMELIST_AUTO = "sdk.gamelist.auto";
  public static final String GAMELIST_CLICK = "sdk.gamelist.click";
  public static final String GAMELIST_CLOSE = "sdk.gamelist.close";
  public static final String GAMELIST_ELSE = "sdk.gamelist.else";
  public static final String GAMELIST_KEYBACK = "sdk.gamelist.keyback";
  public static final String GAMELIST_SHOW = "sdk.gamelist.show";
  public static final String GOLDCOUPONS_SHOW = "sdk.goldcoupons.show";
  public static final String GOLDCOUPONS_SURE = "sdk.goldcoupons.sure";
  public static final String GOODSANDMONTHSINPUT_PRE = "d";
  public static final String GOODS_ADD = "sdk.goods.add";
  public static final String GOODS_CLOSE = "sdk.goods.close";
  public static final String GOODS_DEL = "sdk.goods.del";
  public static final String GOODS_KEYBACK = "sdk.goods.keyback";
  public static final String GOODS_SHOW = "sdk.goods.show";
  public static final String GOODS_SURE = "sdk.goods.sure";
  public static final String H5_GAMEINPUT_PAY = "sdk.gameinput.h5.pay";
  public static final String H5_GAMELIST_BACK = "sdk.gamelist.h5.back";
  public static final String H5_GAMELIST_CLICK = "sdk.gamelist.h5.click";
  public static final String H5_GAMELIST_ERROR = "sdk.gamelist.h5.error";
  public static final String H5_GAMELIST_KEYBACK = "sdk.gamelist.h5.keyback";
  public static final String H5_GAMELIST_REFRESH = "sdk.gamelist.h5.refresh";
  public static final String H5_GAMELIST_SHOW = "sdk.gamelist.h5.show";
  public static final String H5_GAMERESULT_BACK = "sdk.result.h5.back";
  public static final String H5_GAMERESULT_ERROR = "sdk.result.h5.error";
  public static final String H5_GAMERESULT_KEYBACK = "sdk.result.h5.keyback";
  public static final String H5_GAMERESULT_PAY = "sdk.result.h5.pay";
  public static final String H5_GAMERESULT_REFRESH = "sdk.result.h5.refresh";
  public static final String H5_GAMERESULT_REPAY = "sdk.result.h5.repay";
  public static final String H5_GAMERESULT_SHOW = "sdk.result.h5.show";
  public static final String HFPAY_ATONCE_SHOW = "sdk.hfpay.show.now";
  public static final String HFPAY_BACK = "sdk.hf.back";
  public static final String HFPAY_CHECK = "sdk.hf.check";
  public static final String HFPAY_FILL = "sdk.hf.fill";
  public static final String HFPAY_KEYBACK = "sdk.hfpay.keyback";
  public static final String HFPAY_PAY = "sdk.hfpay.pay";
  public static final String HFPAY_SENDSMS_SHOW = "sdk.hfsendsms.show";
  public static final String HFPAY_SHOW = "sdk.hf.show";
  public static final String HFPAY_SUCC_BACK = "sdk.hfsucc.back";
  public static final String HFPAY_SUCC_CONTNUE = "sdk.hfsucc.continue";
  public static final String HFPAY_SUCC_KEYBACK = "sdk.hfsucc.keyback";
  public static final String HFPAY_SUCC_SEARCH = "sdk.hfsucc.search";
  public static final String HFPAY_SUCC_SHOW = "sdk.hfsucc.show";
  public static final String HFPAY_SURE = "sdk.hf.sure";
  public static final String HOME_KEY_PRESSED = "com.pay.homeKeyPressed";
  public static final String LOGINVC_KEYBACK = "sdk.loginvc.keyback";
  public static final String LOGINVC_PICCHANGE = "sdk.loginvc.picchange";
  public static final String LOGINVC_SHOW = "sdk.loginvc.show";
  public static final String LOGINVC_SURE = "sdk.loginvc.sure";
  public static final String LOGINVC_TEXTCHANGE = "sdk.loginvc.textchange";
  public static final String LOGIN_CANCEL = "sdk.login.cancel";
  public static final String LOGIN_KEYBACK = "sdk.login.keyback";
  public static final String LOGIN_NOSAVEPWD = "sdk.login.nosavepwd";
  public static final String LOGIN_PAY = "sdk.login.pay";
  public static final String LOGIN_SAVEPWD = "sdk.login.savepwd";
  public static final String LOGIN_SHOW = "sdk.login.show";
  public static final String LOGIN_SURE = "sdk.login.sure";
  public static final String MARKET_CANCEL = "sdk.market.cancel";
  public static final String MARKET_KEYBACK = "sdk.market.keyback";
  public static final String MARKET_PAY = "sdk.market.pay";
  public static final String MARKET_PAY_SEND = "sdk.market.send";
  public static final String MARKET_RESULT_CANCEL = "sdk.market.result.cancel";
  public static final String MARKET_RESULT_KEYBACK = "sdk.market.result.keyback";
  public static final String MARKET_RESULT_SHOW = "sdk.market.result.show";
  public static final String MARKET_RESULT_SURE = "sdk.market.result.sure";
  public static final String MARKET_SHOW = "sdk.market.show";
  public static final String MARKET_SURE = "sdk.market.sure";
  public static final String MCARDVALUE_PRE = "f";
  public static final String MIBAO_BACK = "sdk.mb.back";
  public static final String MIBAO_FRESH = "sdk.mb.fresh";
  public static final String MIBAO_KEYBACK = "sdk.mb.keyback";
  public static final String MIBAO_SHOW = "sdk.mb.show";
  public static final String MOBILEBUYGOODSINFO_FAILURE = "sdk.cgi.mobilebuygoods.failure";
  public static final String MOBILEBUYGOODSINFO_SUCESS = "sdk.cgi.mobilebuygoods.sucess";
  public static final String MOBILEBUYMOTHINFO_FAILURE = "sdk.cgi.moilebuymoth.failure";
  public static final String MOBILEBUYMOTHINFO_SUCESS = "sdk.cgi.moilebuymoth.sucess";
  public static final String MOBILEBUYPAGE_FAILURE = "sdk.cgi.mobilebuypage.failure";
  public static final String MOBILEBUYPAGE_SUCESS = "sdk.cgi.mobilebuypage.sucess";
  public static final String MOBILEGOOSTOKEN_FAILURE = "sdk.cgi.goodstoken.failure";
  public static final String MOBILEGOOSTOKEN_SUCESS = "sdk.cgi.goodstoken.sucess";
  public static final String MONTH_AUTO = "sdk.goods.auto";
  public static final String NETWORK_REQUEST = "sdk.midas.networkrequest";
  public static final String NOTENOUGH_CANCEL = "sdk.notenough.cancel";
  public static final String NOTENOUGH_KEYBACK = "sdk.notenough.keyback";
  public static final String NOTENOUGH_SHOW = "sdk.notenough.show";
  public static final String NOTENOUGH_SURE = "sdk.notenough.sure";
  public static final String PCARD_ATONCE_SHOW = "sdk.pcard.show.now";
  public static final String PCARD_CLICK = "sdk.pcard.click";
  public static final String PCARD_KEYBACK = "sdk.pcard.keyback";
  public static final String PCARD_PAY = "sdk.pcard.pay";
  public static final String PCARD_SHOW = "sdk.pcard.show";
  public static final String PHONE_DEVICE = "sdk.deviceinfo";
  public static final String QCARD_ATONCE_SHOW = "sdk.qcard.show.now";
  public static final String QCARD_KEYBACK = "sdk.qcard.keyback";
  public static final String QCARD_PAY = "sdk.qcard.pay";
  public static final String QCARD_SHOW = "sdk.qcard.show";
  public static final String SDKCRASH = "sdk.cgi.crash";
  public static final String SDKPAYSUCCESS_SHOW = "sdk.succ.toast";
  public static final String SDK_ASK_KEYBACK = "sdk.ask.keyback";
  public static final String SDK_ASK_NONSENT = "sdk.ask.nonsent";
  public static final String SDK_ASK_SENT = "sdk.ask.sent";
  public static final String SDK_ASK_SHOW = "sdk.ask.show";
  public static final String SDK_TENPAY_CALLBACK = "sdk.tenpaycallback";
  public static final String SDK_WECHAT_CALLBACK = "sdk.wechatcallback";
  public static final String SUCC_BACK = "sdk.succ.back";
  public static final String SUCC_CONTINUE = "sdk.succ.continue";
  public static final String SUCC_KEYBACK = "sdk.succ.keyback";
  public static final String SUCC_SHOW = "sdk.succ.show";
  public static final String TELSUCC_BACK = "sdk.telsucc.back";
  public static final String TELSUCC_CONTINUE = "sdk.telsucc.continue";
  public static final String TELSUCC_COPY = "sdk.telsucc.copy";
  public static final String TELSUCC_KEYBACK = "sdk.telsucc.keyback";
  public static final String TELSUCC_SEARCH = "sdk.telsucc.search";
  public static final String TELSUCC_SHOW = "sdk.telsucc.show";
  public static final String TENPAYPAYSUCCESS_SHOW = "sdk.tenpaysucctoast.show";
  public static final String TENPAYSUCC_PRE = "pt";
  public static final String VERIFYCODE_KEYBACK = "sdk.vc.keyback";
  public static final String VERIFYCODE_PICCHANGE = "sdk.vc.picchange";
  public static final String VERIFYCODE_SHOW = "sdk.vc.show";
  public static final String VERIFYCODE_SURE = "sdk.vc.sure";
  public static final String VERIFYCODE_TEXTCHANGE = "sdk.vc.textchange";
  private static APDataReportManager b = null;
  private List a = null;
  private int c = 0;

  private APDataReportManager()
  {
    Context localContext = AndroidPay.singleton().applicationContext;
    if (localContext != null)
    {
      SharedPreferences localSharedPreferences = localContext.getSharedPreferences("TencentUnipay", 0);
      if (localSharedPreferences != null)
        this.c = localSharedPreferences.getInt("dataCount", 0);
    }
  }

  private int a()
  {
    this.c = (1 + this.c);
    if (this.c == 30000)
      this.c = 0;
    return this.c;
  }

  public static APDataReportManager getInstance()
  {
    if (b == null)
      b = new APDataReportManager();
    return b;
  }

  public static void release()
  {
    b = null;
  }

  public void clearData()
  {
    this.a.clear();
  }

  public int getLogRecord(ArrayList paramArrayList)
  {
    paramArrayList.clear();
    int i = this.a.size();
    if (i <= 0)
      return 0;
    int j = 1 + i / 13;
    APUserInfo localAPUserInfo = APDataInterface.singleton().getUserInfo();
    String str1 = localAPUserInfo.openId;
    String str2 = "android_v" + APCommMethod.getVersion();
    StringBuffer localStringBuffer1 = new StringBuffer();
    int k = 0;
    if (k >= j)
      return j;
    int m = 0;
    int i1;
    for (int n = 0; ; n = i1)
    {
      if (m >= 12);
      do
      {
        if (localStringBuffer1.length() > 0)
          localStringBuffer1.deleteCharAt(-1 + localStringBuffer1.length());
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append("num=");
        localStringBuffer2.append(n);
        localStringBuffer2.append("&");
        localStringBuffer2.append(localStringBuffer1.toString());
        paramArrayList.add(localStringBuffer2.toString());
        localStringBuffer1.setLength(0);
        k++;
        break;
      }
      while (m + k * 12 >= i);
      i1 = n + 1;
      localStringBuffer1.append("record" + m + "=");
      localStringBuffer1.append("3=" + str1);
      String str3 = localAPUserInfo.sessionId;
      String str4 = localAPUserInfo.sessionType;
      if ((str3.equals("hy_gameid")) && (str4.equals("wc_actoken")))
        localStringBuffer1.append("|4=" + localAPUserInfo.payId);
      localStringBuffer1.append("|7=0");
      HashMap localHashMap = (HashMap)this.a.get(m + k * 12);
      localStringBuffer1.append("|13=" + a());
      localStringBuffer1.append("|21=" + (String)localHashMap.get("format"));
      localStringBuffer1.append("|24=" + APAppDataInterface.singleton().getOfferid());
      localStringBuffer1.append("|26=" + localAPUserInfo.pf);
      String str5 = (String)localHashMap.get("token");
      if (str5 != null)
        localStringBuffer1.append("|56=" + str5);
      String str6 = (String)localHashMap.get("extend");
      APLog.i("getLogRecord extend pre", str6);
      if (str6 != null)
      {
        String str9 = APTools.urlEncode(str6, 3);
        localStringBuffer1.append("|8=" + str9);
      }
      String str7 = (String)localHashMap.get("from");
      if (str7 != null)
        localStringBuffer1.append("|20=" + str7);
      String str8 = (String)localHashMap.get("buytype");
      if (str8 != null)
        localStringBuffer1.append("|47=" + str8);
      localStringBuffer1.append("|29=" + APDataInterface.singleton().getOrderInfo().sessionToken);
      localStringBuffer1.append("|31=" + str2);
      localStringBuffer1.append("|38=" + (String)localHashMap.get("times"));
      localStringBuffer1.append("|34=" + localAPUserInfo.uinTypeFromSvr);
      localStringBuffer1.append("|35=" + localAPUserInfo.uinFromSvr);
      localStringBuffer1.append("|37=" + localAPUserInfo.sessionId);
      localStringBuffer1.append("|43=" + localAPUserInfo.sessionType);
      localStringBuffer1.append("&");
      m++;
    }
  }

  public void insertData(String paramString, int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("format", paramString);
    localHashMap.put("times", String.valueOf(System.currentTimeMillis()));
    switch (paramInt)
    {
    default:
      localHashMap.put("buytype", "game");
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      this.a.add(localHashMap);
      return;
      localHashMap.put("buytype", "game");
      continue;
      localHashMap.put("buytype", "goods");
      continue;
      localHashMap.put("buytype", "acct");
      continue;
      localHashMap.put("buytype", "month");
      continue;
      localHashMap.put("buytype", "subscribe");
    }
  }

  public void insertData(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("format", paramString1);
    localHashMap.put("token", paramString2);
    localHashMap.put("from", paramString3);
    localHashMap.put("extend", paramString4);
    localHashMap.put("times", String.valueOf(System.currentTimeMillis()));
    switch (paramInt)
    {
    default:
      localHashMap.put("buytype", "game");
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      this.a.add(localHashMap);
      return;
      localHashMap.put("buytype", "game");
      continue;
      localHashMap.put("buytype", "goods");
      continue;
      localHashMap.put("buytype", "acct");
      continue;
      localHashMap.put("buytype", "month");
      continue;
      localHashMap.put("buytype", "subscribe");
    }
  }

  public void reportOneRecord(String paramString)
  {
    APUserInfo localAPUserInfo = APDataInterface.singleton().getUserInfo();
    String str1 = localAPUserInfo.openId;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("num=1&");
    String str2 = "android_v" + APCommMethod.getVersion();
    localStringBuffer.append("record0" + "=");
    localStringBuffer.append("3=" + str1);
    localStringBuffer.append("|7=0");
    localStringBuffer.append("|13=" + a());
    localStringBuffer.append("|21=" + paramString);
    localStringBuffer.append("|24=" + APAppDataInterface.singleton().getOfferid());
    localStringBuffer.append("|26=" + localAPUserInfo.pf);
    String str3 = localAPUserInfo.sessionId;
    String str4 = localAPUserInfo.sessionType;
    if ((str3.equals("hy_gameid")) && (str4.equals("wc_actoken")))
      localStringBuffer.append("|4=" + localAPUserInfo.payId);
    switch (APDataInterface.singleton().getOrderInfo().saveType)
    {
    default:
      localStringBuffer.append("|47=game");
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      localStringBuffer.append("|29=" + APDataInterface.singleton().getOrderInfo().sessionToken);
      localStringBuffer.append("|31=" + str2);
      localStringBuffer.append("|38=" + String.valueOf(System.currentTimeMillis()));
      localStringBuffer.append("|34=" + localAPUserInfo.uinTypeFromSvr);
      localStringBuffer.append("|35=" + localAPUserInfo.uinFromSvr);
      localStringBuffer.append("|37=" + localAPUserInfo.sessionId);
      localStringBuffer.append("|43=" + localAPUserInfo.sessionType);
      localStringBuffer.append("&");
      if (localStringBuffer.length() > 0)
        localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
      String str5 = localStringBuffer.toString();
      APNetworkManager.getInstance().dataReport(str5, new a(this));
      return;
      localStringBuffer.append("|47=game");
      continue;
      localStringBuffer.append("|47=goods");
      continue;
      localStringBuffer.append("|47=acct");
      continue;
      localStringBuffer.append("|47=month");
      continue;
      localStringBuffer.append("|47=subscribe");
    }
  }

  public void reportOneRecord(String paramString, int paramInt)
  {
    APUserInfo localAPUserInfo = APDataInterface.singleton().getUserInfo();
    String str1 = localAPUserInfo.openId;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("num=1&");
    String str2 = "android_v" + APCommMethod.getVersion();
    localStringBuffer.append("record0" + "=");
    localStringBuffer.append("3=" + str1);
    localStringBuffer.append("|7=0");
    localStringBuffer.append("|13=" + a());
    localStringBuffer.append("|20=" + paramInt);
    localStringBuffer.append("|21=" + paramString);
    localStringBuffer.append("|24=" + APAppDataInterface.singleton().getOfferid());
    localStringBuffer.append("|26=" + localAPUserInfo.pf);
    String str3 = localAPUserInfo.sessionId;
    String str4 = localAPUserInfo.sessionType;
    if ((str3.equals("hy_gameid")) && (str4.equals("wc_actoken")))
      localStringBuffer.append("|4=" + localAPUserInfo.payId);
    switch (APDataInterface.singleton().getOrderInfo().saveType)
    {
    default:
      localStringBuffer.append("|47=game");
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      localStringBuffer.append("|29=" + APDataInterface.singleton().getOrderInfo().sessionToken);
      localStringBuffer.append("|31=" + str2);
      localStringBuffer.append("|38=" + String.valueOf(System.currentTimeMillis()));
      localStringBuffer.append("|34=" + localAPUserInfo.uinTypeFromSvr);
      localStringBuffer.append("|35=" + localAPUserInfo.uinFromSvr);
      localStringBuffer.append("|37=" + localAPUserInfo.sessionId);
      localStringBuffer.append("|43=" + localAPUserInfo.sessionType);
      localStringBuffer.append("&");
      if (localStringBuffer.length() > 0)
        localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
      String str5 = localStringBuffer.toString();
      APNetworkManager.getInstance().dataReport(str5, new b(this));
      return;
      localStringBuffer.append("|47=game");
      continue;
      localStringBuffer.append("|47=goods");
      continue;
      localStringBuffer.append("|47=acct");
      continue;
      localStringBuffer.append("|47=month");
    }
  }

  public void saveDataId()
  {
    Context localContext = AndroidPay.singleton().applicationContext;
    if (localContext == null);
    SharedPreferences localSharedPreferences;
    do
    {
      return;
      localSharedPreferences = localContext.getSharedPreferences("TencentUnipay", 0);
    }
    while (localSharedPreferences == null);
    localSharedPreferences.edit().putInt("dataCount", this.c).commit();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.APDataReportManager
 * JD-Core Version:    0.6.0
 */