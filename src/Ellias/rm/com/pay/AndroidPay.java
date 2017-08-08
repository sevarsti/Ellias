package com.pay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import com.pay.api.APPayResponseInfo;
import com.pay.common.tool.APLog;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.buyInfo.APBuyGoodsInfo;
import com.pay.data.buyInfo.APBuyMonthInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.http.APIPList;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APMonthDataInterface;
import com.pay.tool.APSecretKeyManager;
import com.pay.tool.APTools;
import com.pay.ui.common.APUICommonMethod;
import com.pay.ui.marketing.APWebMarketActivity;

public class AndroidPay
{
  public static final int LANDSCAPE = 0;
  public static final int PORTRAINT = 1;
  private static AndroidPay c = null;
  private int a;
  public Context applicationContext = null;
  private String b = "";
  public APBaseBuyInfo buyInfo = null;
  public Activity fromActivity;
  public boolean isUILaunched = true;
  public AndroidPay.APLaunchRootViewOption launchOption = AndroidPay.APLaunchRootViewOption.APPayGameInputNumView;
  public APOrderInfo orderInfo = null;
  public APOrderInfo originalOrderInfo = null;
  public APPayResponseInfo payResponseInfo = null;

  public static void Destory()
  {
  }

  public static void Initialize(Activity paramActivity)
  {
    singleton().applicationContext = paramActivity.getApplicationContext();
    c.fromActivity = paramActivity;
    APAppDataInterface.singleton().setAppOrientation(c.fromActivity.getResources().getConfiguration().orientation);
    int i = APTools.getNetWorkType(singleton().applicationContext);
    APAppDataInterface.singleton().setNetworkState(i);
    String str = APTools.collectDeviceInfo(singleton().applicationContext);
    APAppDataInterface.singleton().setDeviceInfo(str);
  }

  private static void a()
  {
    APIPList.getInstance(singleton().applicationContext).init();
    APUserInfo localAPUserInfo = APDataInterface.singleton().getUserInfo();
    localAPUserInfo.isUinLogin = false;
    String str1 = APSecretKeyManager.getInstance(singleton().applicationContext).readSecretKey(localAPUserInfo.openId);
    APAppDataInterface.singleton().setSecretKey(str1);
    String str2 = APSecretKeyManager.getInstance(singleton().applicationContext).readCryptKey(localAPUserInfo.openId);
    APAppDataInterface.singleton().setCryptKey(str2);
    String str3 = APSecretKeyManager.getInstance(singleton().applicationContext).readCryptKeyTime(localAPUserInfo.openId);
    APAppDataInterface.singleton().setCryptKeyTime(str3);
  }

  private boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      APUICommonMethod.showToast(this.applicationContext, APCommMethod.getStringId(c.applicationContext, "unipay_entry_numberNull"));
      APCommMethod.payErrorCallBack(3, APCommMethod.getStringId(c.applicationContext, "unipay_entry_numberNull"));
      return false;
    }
    if (paramString.length() > 7)
    {
      APUICommonMethod.showToast(this.applicationContext, APCommMethod.getStringId(c.applicationContext, "unipay_entry_numberError"));
      APCommMethod.payErrorCallBack(3, APCommMethod.getStringId(c.applicationContext, "unipay_entry_numberError"));
      return false;
    }
    return true;
  }

  private boolean a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    if (TextUtils.isEmpty(paramString1))
    {
      APUICommonMethod.showToast(this.applicationContext, APCommMethod.getStringId(this.applicationContext, "unipay_entry_offeridNull"));
      return false;
    }
    if (TextUtils.isEmpty(paramString2))
    {
      APUICommonMethod.showToast(this.applicationContext, APCommMethod.getStringId(this.applicationContext, "unipay_entry_openidNull"));
      return false;
    }
    if (TextUtils.isEmpty(paramString3))
    {
      APUICommonMethod.showToast(this.applicationContext, APCommMethod.getStringId(this.applicationContext, "unipay_entry_openkeyNull"));
      return false;
    }
    if (TextUtils.isEmpty(paramString4))
    {
      APUICommonMethod.showToast(this.applicationContext, APCommMethod.getStringId(this.applicationContext, "unipay_entry_sessionidNull"));
      return false;
    }
    if (TextUtils.isEmpty(paramString5))
    {
      APUICommonMethod.showToast(this.applicationContext, APCommMethod.getStringId(this.applicationContext, "unipay_entry_sessiontypeNull"));
      return false;
    }
    if (TextUtils.isEmpty(paramString6))
    {
      APUICommonMethod.showToast(this.applicationContext, APCommMethod.getStringId(this.applicationContext, "unipay_entry_pfNull"));
      return false;
    }
    if (TextUtils.isEmpty(paramString7))
    {
      APUICommonMethod.showToast(this.applicationContext, APCommMethod.getStringId(this.applicationContext, "unipay_entry_pfkeyNull"));
      return false;
    }
    return true;
  }

  public static String getPaySDKVersion()
  {
    return "1.3.7b";
  }

  public static void setCustomUrlParam(String paramString)
  {
    APAppDataInterface.singleton().setCustomCgi(paramString);
  }

  public static void setElseNumberVisible(boolean paramBoolean)
  {
    APAppDataInterface.singleton().setElseNumberVisible(paramBoolean);
  }

  public static void setEnv(String paramString)
  {
    APAppDataInterface.singleton().setEnv(paramString);
    APIPList.initIPList(singleton().applicationContext);
  }

  public static void setIsShowSaveNum(boolean paramBoolean)
  {
    APAppDataInterface.singleton().setIsShowSaveNum(paramBoolean);
  }

  public static void setLogEnable(boolean paramBoolean)
  {
    APLog.setLogEnable(paramBoolean);
  }

  public static void setNumberVisible(boolean paramBoolean)
  {
    setIsShowSaveNum(paramBoolean);
  }

  public static void setOfferId(String paramString)
  {
    APAppDataInterface.singleton().setOfferid(paramString);
  }

  public static void setPropUnit(String paramString)
  {
    c.b = paramString;
  }

  public static void setResData(byte[] paramArrayOfByte)
  {
    APDataInterface.singleton().setAppResId(paramArrayOfByte);
  }

  public static void setScreenType(int paramInt)
  {
    if ((paramInt != 1) && (paramInt != 0))
    {
      APDataInterface.singleton().setScreenType(-1);
      return;
    }
    APDataInterface.singleton().setScreenType(paramInt);
  }

  public static void setWechatAppId(String paramString)
  {
    APAppDataInterface.singleton().setWechatAppId(paramString);
  }

  public static AndroidPay singleton()
  {
    if (c == null)
      c = new AndroidPay();
    return c;
  }

  public Context GetContext()
  {
    return this.applicationContext;
  }

  public boolean IsNeedUinLogin()
  {
    String str1 = APDataInterface.singleton().getUserInfo().sessionId;
    String str2 = APDataInterface.singleton().getUserInfo().sessionType;
    return (str1.equals("hy_gameid")) && (str2.equals("wc_actoken"));
  }

  public void buyGoods(AndroidPay.APLaunchRootViewOption paramAPLaunchRootViewOption, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, boolean paramBoolean, String paramString10)
  {
    this.launchOption = paramAPLaunchRootViewOption;
    this.isUILaunched = false;
    if (!a(APAppDataInterface.singleton().getOfferid(), paramString1, paramString2, paramString3, paramString4, paramString6, paramString7))
    {
      APCommMethod.payErrorCallBack(3, APCommMethod.getStringId(this.applicationContext, "unipay_entry_paramNull"));
      return;
    }
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    APUserInfo localAPUserInfo = localAPDataInterface.getUserInfo();
    localAPUserInfo.openId = paramString1;
    localAPUserInfo.openKey = paramString2;
    localAPUserInfo.sessionId = paramString3;
    localAPUserInfo.sessionType = paramString4;
    localAPUserInfo.pf = paramString6;
    localAPUserInfo.pfKey = paramString7;
    localAPUserInfo.zoneId = paramString5;
    localAPUserInfo.payId = paramString1;
    localAPUserInfo.authKey = paramString2;
    if (this.a > 0)
      localAPDataInterface.setAppResId(APCommMethod.BitmapResIdToByteArrary(this.a));
    a();
    this.orderInfo = new APOrderInfo(1);
    this.orderInfo.saveNum = paramString9;
    this.orderInfo.isNumCanChange = paramBoolean;
    this.orderInfo.buyInfo.unit = this.b;
    APDataInterface.singleton().setOrderInfo(this.orderInfo);
    try
    {
      this.originalOrderInfo = new APOrderInfo(1);
      this.originalOrderInfo = ((APOrderInfo)this.orderInfo.clone());
      new APBuyPage(this.fromActivity, paramAPLaunchRootViewOption).getGoodsInfo(1, paramString8, paramString9, paramBoolean);
      return;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      while (true)
        localCloneNotSupportedException.printStackTrace();
    }
  }

  public void buyGoodsWithMarket(AndroidPay.APLaunchRootViewOption paramAPLaunchRootViewOption, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, int paramInt, String paramString9, String paramString10, String paramString11, String paramString12)
  {
    c.isUILaunched = false;
    if (TextUtils.isEmpty(paramString9))
    {
      APUICommonMethod.showToast(this.applicationContext, "payChannel不能为空");
      APCommMethod.payErrorCallBack(3, "payChannel不能为空");
    }
    do
    {
      return;
      if ((!TextUtils.isEmpty(paramString9)) && (TextUtils.isEmpty(paramString10)) && (!TextUtils.isEmpty(paramString11)))
      {
        APUICommonMethod.showToast(this.applicationContext, "discountType不能为空");
        APCommMethod.payErrorCallBack(3, "discountType不能为空");
        return;
      }
      if ((!TextUtils.isEmpty(paramString9)) && (!TextUtils.isEmpty(paramString10)) && (TextUtils.isEmpty(paramString11)))
      {
        APUICommonMethod.showToast(this.applicationContext, "discountUrl不能为空");
        APCommMethod.payErrorCallBack(3, "discountUrl不能为空");
        return;
      }
      String str = paramString11 + "&t=" + System.currentTimeMillis();
      APDataInterface localAPDataInterface = APDataInterface.singleton();
      localAPDataInterface.setPayAssignChannel(paramString9);
      localAPDataInterface.setDiscountType(paramString10);
      localAPDataInterface.setDiscountUrl(str);
      localAPDataInterface.setDiscountExtras(paramString12);
      localAPDataInterface.setUuid(APTools.getUUID());
      buyGoodsWithToken(paramAPLaunchRootViewOption, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramInt);
    }
    while (this.orderInfo == null);
    this.orderInfo.isNumCanChange = false;
  }

  public void buyGoodsWithToken(AndroidPay.APLaunchRootViewOption paramAPLaunchRootViewOption, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, int paramInt)
  {
    this.launchOption = paramAPLaunchRootViewOption;
    this.isUILaunched = false;
    this.a = paramInt;
    if (!a(APAppDataInterface.singleton().getOfferid(), paramString1, paramString2, paramString3, paramString4, paramString6, paramString7))
    {
      APCommMethod.payErrorCallBack(3, APCommMethod.getStringId(this.applicationContext, "unipay_entry_paramNull"));
      return;
    }
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    APUserInfo localAPUserInfo = localAPDataInterface.getUserInfo();
    localAPUserInfo.openId = paramString1;
    localAPUserInfo.openKey = paramString2;
    localAPUserInfo.sessionId = paramString3;
    localAPUserInfo.sessionType = paramString4;
    localAPUserInfo.pf = paramString6;
    localAPUserInfo.pfKey = paramString7;
    localAPUserInfo.zoneId = paramString5;
    localAPUserInfo.payId = paramString1;
    localAPUserInfo.authKey = paramString2;
    if ((paramString8 == null) || (paramString8.equals("")))
    {
      APUICommonMethod.showToast(this.applicationContext, APCommMethod.getStringId(this.applicationContext, "unipay_entry_orderNull"));
      APCommMethod.payErrorCallBack(3, APCommMethod.getStringId(this.applicationContext, "unipay_entry_orderNull"));
      return;
    }
    if (paramInt > 0)
      localAPDataInterface.setAppResId(APCommMethod.BitmapResIdToByteArrary(paramInt));
    a();
    this.orderInfo = new APOrderInfo(1);
    this.orderInfo.buyInfo.unit = this.b;
    ((APBuyGoodsInfo)this.orderInfo.buyInfo).tokenUrl = paramString8;
    APDataInterface.singleton().setOrderInfo(this.orderInfo);
    try
    {
      this.originalOrderInfo = new APOrderInfo(1);
      this.originalOrderInfo = ((APOrderInfo)this.orderInfo.clone());
      if (isValidPayChannelAndMarket())
      {
        APDataInterface.singleton().setDataValid(true);
        showMarketActivity();
        return;
      }
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      while (true)
        localCloneNotSupportedException.printStackTrace();
      new APBuyPage(this.fromActivity, paramAPLaunchRootViewOption).getBuyInfo(1, paramString8);
    }
  }

  public void buyGoodsWithoutMarket(AndroidPay.APLaunchRootViewOption paramAPLaunchRootViewOption, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, int paramInt)
  {
    c.isUILaunched = false;
    APDataInterface.singleton().setPayAssignChannel("");
    buyGoodsWithToken(paramAPLaunchRootViewOption, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramInt);
    if (this.orderInfo != null)
      this.orderInfo.isNumCanChange = true;
  }

  public boolean isValidPayChannelAndMarket()
  {
    String str1 = APDataInterface.singleton().getPayAssignChannel();
    String str2 = APDataInterface.singleton().getDiscountType();
    String str3 = APDataInterface.singleton().getDiscountUrl();
    int i;
    if (!str1.equals("wechat"))
    {
      boolean bool3 = str1.equals("bank");
      i = 0;
      if (!bool3);
    }
    else
    {
      boolean bool1 = TextUtils.isEmpty(str2);
      i = 0;
      if (!bool1)
      {
        boolean bool2 = TextUtils.isEmpty(str3);
        i = 0;
        if (!bool2)
          i = 1;
      }
    }
    return i;
  }

  public void payGameInputNumber(AndroidPay.APLaunchRootViewOption paramAPLaunchRootViewOption, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, boolean paramBoolean, int paramInt)
  {
    this.launchOption = paramAPLaunchRootViewOption;
    this.a = paramInt;
    c.isUILaunched = false;
    if (!a(APAppDataInterface.singleton().getOfferid(), paramString1, paramString2, paramString3, paramString4, paramString6, paramString7))
      APCommMethod.payErrorCallBack(3, APCommMethod.getStringId(this.applicationContext, "unipay_entry_paramNull"));
    do
      return;
    while (!a(paramString9));
    if ((paramInt <= 0) && (APDataInterface.singleton().getAppResId() == null))
    {
      APUICommonMethod.showToast(this.applicationContext, APCommMethod.getStringId(this.applicationContext, "unipay_entry_iconNull"));
      APCommMethod.payErrorCallBack(3, APCommMethod.getStringId(this.applicationContext, "unipay_entry_iconNull"));
      return;
    }
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    APUserInfo localAPUserInfo = localAPDataInterface.getUserInfo();
    localAPUserInfo.openId = paramString1;
    localAPUserInfo.openKey = paramString2;
    localAPUserInfo.sessionId = paramString3;
    localAPUserInfo.sessionType = paramString4;
    localAPUserInfo.pf = paramString6;
    localAPUserInfo.pfKey = paramString7;
    localAPUserInfo.zoneId = paramString5;
    localAPUserInfo.payId = paramString1;
    localAPUserInfo.authKey = paramString2;
    localAPUserInfo.acctType = paramString8;
    if (paramInt > 0)
      localAPDataInterface.setAppResId(APCommMethod.BitmapResIdToByteArrary(paramInt));
    a();
    this.orderInfo = new APOrderInfo(0);
    this.orderInfo.saveNum = paramString9;
    this.orderInfo.isNumCanChange = paramBoolean;
    this.orderInfo.buyInfo.unit = this.b;
    localAPDataInterface.setOrderInfo(this.orderInfo);
    try
    {
      this.originalOrderInfo = new APOrderInfo(0);
      this.originalOrderInfo = ((APOrderInfo)this.orderInfo.clone());
      if (isValidPayChannelAndMarket())
      {
        APDataInterface.singleton().setDataValid(true);
        showMarketActivity();
        return;
      }
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      while (true)
        localCloneNotSupportedException.printStackTrace();
      new APBuyPage(this.fromActivity, paramAPLaunchRootViewOption).getBuyInfo(0, null);
    }
  }

  public void payGameInputNumberAndMarket(AndroidPay.APLaunchRootViewOption paramAPLaunchRootViewOption, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, boolean paramBoolean, int paramInt, String paramString10, String paramString11, String paramString12, String paramString13)
  {
    c.isUILaunched = false;
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    if (TextUtils.isEmpty(paramString10))
    {
      APUICommonMethod.showToast(this.applicationContext, "payChannel不能为空");
      APCommMethod.payErrorCallBack(3, "payChannel不能为空");
      return;
    }
    if ((!TextUtils.isEmpty(paramString10)) && (TextUtils.isEmpty(paramString11)) && (!TextUtils.isEmpty(paramString12)))
    {
      APUICommonMethod.showToast(this.applicationContext, "discountType不能为空");
      APCommMethod.payErrorCallBack(3, "discountType不能为空");
      return;
    }
    if ((!TextUtils.isEmpty(paramString10)) && (!TextUtils.isEmpty(paramString11)) && (TextUtils.isEmpty(paramString12)))
    {
      APUICommonMethod.showToast(this.applicationContext, "discountUrl不能为空");
      APCommMethod.payErrorCallBack(3, "discountUrl不能为空");
      return;
    }
    this.orderInfo = new APOrderInfo(0);
    this.orderInfo.saveNum = paramString9;
    this.orderInfo.isNumCanChange = paramBoolean;
    this.orderInfo.assignChannel = paramString10;
    this.orderInfo.buyInfo.unit = this.b;
    localAPDataInterface.setOrderInfo(this.orderInfo);
    try
    {
      this.originalOrderInfo = new APOrderInfo(0);
      this.originalOrderInfo = ((APOrderInfo)this.orderInfo.clone());
      localAPDataInterface.setPayAssignChannel(paramString10);
      localAPDataInterface.setDiscountType(paramString11);
      localAPDataInterface.setDiscountUrl(paramString12);
      localAPDataInterface.setDiscountExtras(paramString13);
      localAPDataInterface.setUuid(APTools.getUUID());
      payGameInputNumber(paramAPLaunchRootViewOption, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramBoolean, paramInt);
      return;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      while (true)
        localCloneNotSupportedException.printStackTrace();
    }
  }

  public void payGameInputNumberWithoutMarket(AndroidPay.APLaunchRootViewOption paramAPLaunchRootViewOption, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, boolean paramBoolean, int paramInt)
  {
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    localAPDataInterface.setPayAssignChannel("");
    c.isUILaunched = false;
    this.orderInfo = new APOrderInfo(0);
    this.orderInfo.saveNum = paramString9;
    this.orderInfo.isNumCanChange = paramBoolean;
    this.orderInfo.buyInfo.unit = this.b;
    this.orderInfo.assignChannel = "";
    localAPDataInterface.setOrderInfo(this.orderInfo);
    try
    {
      this.originalOrderInfo = new APOrderInfo(0);
      this.originalOrderInfo = ((APOrderInfo)this.orderInfo.clone());
      payGameInputNumber(paramAPLaunchRootViewOption, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramBoolean, paramInt);
      return;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      while (true)
        localCloneNotSupportedException.printStackTrace();
    }
  }

  public void payGameListNumber(AndroidPay.APLaunchRootViewOption paramAPLaunchRootViewOption, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, int paramInt)
  {
    this.launchOption = paramAPLaunchRootViewOption;
    this.a = paramInt;
    this.isUILaunched = false;
    if (!a(APAppDataInterface.singleton().getOfferid(), paramString1, paramString2, paramString3, paramString4, paramString6, paramString7))
    {
      APCommMethod.payErrorCallBack(3, APCommMethod.getStringId(this.applicationContext, "unipay_entry_paramNull"));
      return;
    }
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    APUserInfo localAPUserInfo = localAPDataInterface.getUserInfo();
    localAPUserInfo.openId = paramString1;
    localAPUserInfo.openKey = paramString2;
    localAPUserInfo.sessionId = paramString3;
    localAPUserInfo.sessionType = paramString4;
    localAPUserInfo.pf = paramString6;
    localAPUserInfo.pfKey = paramString7;
    localAPUserInfo.zoneId = paramString5;
    localAPUserInfo.payId = paramString1;
    localAPUserInfo.authKey = paramString2;
    localAPUserInfo.acctType = paramString8;
    localAPDataInterface.setPayAssignChannel("");
    if ((paramInt <= 0) && (APDataInterface.singleton().getAppResId() == null))
    {
      APUICommonMethod.showToast(this.applicationContext, APCommMethod.getStringId(this.applicationContext, "unipay_entry_iconNull"));
      APCommMethod.payErrorCallBack(3, APCommMethod.getStringId(this.applicationContext, "unipay_entry_iconNull"));
      return;
    }
    if (paramInt > 0)
      localAPDataInterface.setAppResId(APCommMethod.BitmapResIdToByteArrary(paramInt));
    a();
    this.orderInfo = new APOrderInfo(0);
    this.orderInfo.isNumCanChange = true;
    this.orderInfo.buyInfo.unit = this.b;
    APDataInterface.singleton().setOrderInfo(this.orderInfo);
    try
    {
      this.originalOrderInfo = new APOrderInfo(0);
      this.originalOrderInfo = ((APOrderInfo)this.orderInfo.clone());
      new APBuyPage(this.fromActivity, paramAPLaunchRootViewOption).getBuyInfo(0, null);
      return;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      while (true)
        localCloneNotSupportedException.printStackTrace();
    }
  }

  public void payMonth(AndroidPay.APLaunchRootViewOption paramAPLaunchRootViewOption, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, int paramInt, String paramString10, String paramString11, boolean paramBoolean, String paramString12)
  {
    this.launchOption = paramAPLaunchRootViewOption;
    this.a = paramInt;
    this.isUILaunched = false;
    this.orderInfo = new APOrderInfo(4);
    if (!a(APAppDataInterface.singleton().getOfferid(), paramString1, paramString2, paramString3, paramString4, paramString6, paramString7))
    {
      APCommMethod.payErrorCallBack(3, APCommMethod.getStringId(this.applicationContext, "unipay_entry_paramNull"));
      return;
    }
    if (paramBoolean)
      APMonthDataInterface.singleton().setAutoPay("1");
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    if (TextUtils.isEmpty(paramString10));
    for (this.orderInfo.isNumCanChange = true; ; this.orderInfo.isNumCanChange = paramBoolean)
    {
      if (TextUtils.isEmpty(paramString11))
        paramString11 = "";
      this.orderInfo.buyInfo.name = paramString9;
      APUserInfo localAPUserInfo = localAPDataInterface.getUserInfo();
      localAPUserInfo.openId = paramString1;
      localAPUserInfo.openKey = paramString2;
      localAPUserInfo.sessionId = paramString3;
      localAPUserInfo.sessionType = paramString4;
      localAPUserInfo.pf = paramString6;
      localAPUserInfo.pfKey = paramString7;
      localAPUserInfo.payId = paramString1;
      localAPUserInfo.authKey = paramString2;
      localAPUserInfo.zoneId = paramString5;
      localAPDataInterface.setPayAssignChannel("");
      if ((paramInt > 0) || (APDataInterface.singleton().getAppResId() != null))
        break label274;
      APUICommonMethod.showToast(this.applicationContext, APCommMethod.getStringId(this.applicationContext, "unipay_entry_iconNull"));
      APCommMethod.payErrorCallBack(3, APCommMethod.getStringId(this.applicationContext, "unipay_entry_iconNull"));
      return;
      if (!a(paramString10))
        break;
      this.orderInfo.saveNum = paramString10;
    }
    label274: if (paramInt > 0)
      localAPDataInterface.setAppResId(APCommMethod.BitmapResIdToByteArrary(paramInt));
    a();
    this.orderInfo.saveNum = paramString10;
    APBuyMonthInfo localAPBuyMonthInfo = (APBuyMonthInfo)this.orderInfo.buyInfo;
    localAPBuyMonthInfo.name = paramString9;
    localAPBuyMonthInfo.serviceCode = paramString8;
    localAPBuyMonthInfo.serviceName = paramString9;
    localAPBuyMonthInfo.payRemark = paramString12;
    localAPBuyMonthInfo.unit = this.b;
    localAPBuyMonthInfo.productId = paramString11;
    APDataInterface.singleton().setOrderInfo(this.orderInfo);
    try
    {
      this.originalOrderInfo = new APOrderInfo(4);
      this.originalOrderInfo = ((APOrderInfo)this.orderInfo.clone());
      new APBuyPage(this.fromActivity, paramAPLaunchRootViewOption).getBuyInfo(4, "");
      return;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      while (true)
        localCloneNotSupportedException.printStackTrace();
    }
  }

  public void saveAgain()
  {
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    APOrderInfo localAPOrderInfo = this.originalOrderInfo;
    APUserInfo localAPUserInfo = localAPDataInterface.getUserInfo();
    localAPOrderInfo.saveType = localAPOrderInfo.originalSaveType;
    localAPDataInterface.setOrderInfo(this.originalOrderInfo);
    APLog.i("AndroidPay", "saveAgain originalSaveType:" + localAPOrderInfo.originalSaveType);
    if (localAPOrderInfo.originalSaveType == 4)
    {
      if (localAPOrderInfo.isNumCanChange)
        singleton().launchOption = AndroidPay.APLaunchRootViewOption.APPayGameListNumView;
      payMonth(singleton().launchOption, localAPUserInfo.openId, localAPUserInfo.openKey, localAPUserInfo.sessionId, localAPUserInfo.sessionType, localAPUserInfo.zoneId, localAPUserInfo.pf, localAPUserInfo.pfKey, ((APBuyMonthInfo)localAPDataInterface.getOrderInfo().buyInfo).serviceCode, localAPOrderInfo.buyInfo.name, this.a, localAPDataInterface.getPreSaveNumber(), ((APBuyMonthInfo)localAPDataInterface.getOrderInfo().buyInfo).productId, localAPOrderInfo.isNumCanChange, ((APBuyMonthInfo)localAPDataInterface.getOrderInfo().buyInfo).payRemark);
    }
    do
    {
      return;
      if (localAPOrderInfo.originalSaveType != 5)
        continue;
      singleton().launchOption = AndroidPay.APLaunchRootViewOption.APPayGameListNumView;
      payMonth(singleton().launchOption, localAPUserInfo.openId, localAPUserInfo.openKey, localAPUserInfo.sessionId, localAPUserInfo.sessionType, localAPUserInfo.zoneId, localAPUserInfo.pf, localAPUserInfo.pfKey, ((APBuyMonthInfo)localAPDataInterface.getOrderInfo().buyInfo).serviceCode, localAPOrderInfo.buyInfo.name, this.a, null, "", localAPOrderInfo.isNumCanChange, ((APBuyMonthInfo)localAPDataInterface.getOrderInfo().buyInfo).payRemark);
      return;
    }
    while (localAPOrderInfo.originalSaveType != 0);
    if (localAPOrderInfo.isNumCanChange)
    {
      singleton().launchOption = AndroidPay.APLaunchRootViewOption.APPayGameListNumView;
      payGameListNumber(singleton().launchOption, localAPUserInfo.openId, localAPUserInfo.openKey, localAPUserInfo.sessionId, localAPUserInfo.sessionType, localAPUserInfo.zoneId, localAPUserInfo.pf, localAPUserInfo.pfKey, localAPUserInfo.acctType, this.a);
      return;
    }
    payGameInputNumber(singleton().launchOption, localAPUserInfo.openId, localAPUserInfo.openKey, localAPUserInfo.sessionId, localAPUserInfo.sessionType, localAPUserInfo.zoneId, localAPUserInfo.pf, localAPUserInfo.pfKey, localAPUserInfo.acctType, localAPDataInterface.getPreSaveNumber(), localAPOrderInfo.isNumCanChange, this.a);
  }

  public void saveQB(AndroidPay.APLaunchRootViewOption paramAPLaunchRootViewOption, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, boolean paramBoolean)
  {
    this.launchOption = paramAPLaunchRootViewOption;
    this.isUILaunched = false;
    this.a = APCommMethod.getDrawableId(this.applicationContext, "unipay_pic_channel_icon3");
    if (!a(APAppDataInterface.singleton().getOfferid(), paramString1, paramString2, paramString3, paramString4, paramString5, paramString6))
      APCommMethod.payErrorCallBack(3, APCommMethod.getStringId(this.applicationContext, "unipay_entry_paramNull"));
    APDataInterface localAPDataInterface;
    do
    {
      return;
      localAPDataInterface = APDataInterface.singleton();
      if (!paramString7.equals("充值Q币，不带默认值"))
        this.orderInfo.saveNum = paramString7;
      this.orderInfo = new APOrderInfo(3);
      this.orderInfo.isNumCanChange = paramBoolean;
      APUserInfo localAPUserInfo = localAPDataInterface.getUserInfo();
      localAPUserInfo.openId = paramString1;
      localAPUserInfo.openKey = paramString2;
      localAPUserInfo.sessionId = paramString3;
      localAPUserInfo.sessionType = paramString4;
      localAPUserInfo.pf = paramString5;
      localAPUserInfo.pfKey = paramString6;
      localAPUserInfo.payId = paramString1;
      localAPUserInfo.authKey = paramString2;
      localAPUserInfo.acctType = "qb";
    }
    while ((!paramString7.equals("充值Q币，不带默认值")) && (!a(paramString7)));
    if ((this.a <= 0) && (APDataInterface.singleton().getAppResId() == null))
    {
      APUICommonMethod.showToast(this.applicationContext, APCommMethod.getStringId(this.applicationContext, "unipay_entry_iconNull"));
      APCommMethod.payErrorCallBack(3, APCommMethod.getStringId(this.applicationContext, "unipay_entry_iconNull"));
      return;
    }
    if (this.a > 0)
      localAPDataInterface.setAppResId(APCommMethod.BitmapResIdToByteArrary(this.a));
    a();
    new APBuyPage(this.fromActivity, paramAPLaunchRootViewOption).getBuyInfo(3, "");
  }

  public void showMarketActivity()
  {
    Intent localIntent = new Intent();
    Bundle localBundle = new Bundle();
    localBundle.putString("loadUI", "showStartActivity");
    localIntent.putExtras(localBundle);
    localIntent.setClass(this.fromActivity, APWebMarketActivity.class);
    this.fromActivity.startActivity(localIntent);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.AndroidPay
 * JD-Core Version:    0.6.0
 */