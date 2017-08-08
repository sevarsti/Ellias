package com.tencent.game.helper;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.pay.AndroidPay;
import com.pay.api.APPayGameService;
import com.pay.api.APPayOpenService;
import com.pay.api.APPayResponseInfo;
import com.pay.api.IAPPayGameServiceCallBack;
import com.pay.api.IAPPayOpenServiceCallBack;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.TokenRet;
import com.tencent.msdk.api.WGPlatform;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

public class m3ePay
  implements IAPPayGameServiceCallBack, IAPPayOpenServiceCallBack
{
  private static m3ePay instance = new m3ePay();
  private String acctType = "common";
  private boolean isCanChange = false;
  private Activity mActivity;
  private String pf = "openmobile_android-2001";
  private String pfKey = "pfKey";
  private int resId = 0;
  private String saveValue = "";
  private String sessionId = "openid";
  private String sessionType = "kp_actoken";
  private String userId = "";
  private String userKey = "";
  private String zoneId = "1";

  public static m3ePay getInstance()
  {
    return instance;
  }

  public void LaunchMPSaveCurrencyView(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Log.d("loefang", "LaunchMPSaveCurrencyView(String discountType,  String discountUrl)");
    Log.d("loefang", "discountType: " + paramString3);
    Log.d("loefang", "discountUrl: " + paramString4);
    LoginRet localLoginRet = new LoginRet();
    int i = WGPlatform.WGGetLoginRecord(localLoginRet);
    String str1 = "";
    String str2 = "";
    String str3 = "";
    AndroidPay.setOfferId("1450000581");
    String str4 = "";
    if (WeGame.WXPLATID == i)
    {
      str1 = localLoginRet.getTokenByType(3);
      str4 = "wechat";
      str2 = "hy_gameid";
      str3 = "wc_actoken";
    }
    while (true)
    {
      Log.d("leofang", "APPayGameService.LaunchMPSaveCurrencyView Begin");
      Log.d("leofang:LaunchMPSaveCurrencyView", "userId:" + paramString1 + "|payToken:" + str1 + "|sessionId:" + str2 + "|sessionType:" + str3 + "|zoneId:" + "1" + "|pf:" + paramString2 + "|pfkey:" + localLoginRet.pf_key + "|acctType:" + "common" + "|saveValue:" + "1" + "|gameCoinResId:" + 2130837674 + "|payChannel:" + str4 + "|discountType:" + paramString3 + "|discountUrl:" + paramString4);
      APPayGameService.SetDelegate(this);
      APPayGameService.LaunchMPSaveCurrencyView(paramString1, str1, str2, str3, "1", paramString2, localLoginRet.pf_key, "common", "1", 2130837674, str4, paramString3, paramString4, "");
      Log.d("leofang", "APPayGameService.LaunchMPSaveCurrencyView End");
      return;
      if (WeGame.QQPLATID != i)
        continue;
      str1 = localLoginRet.getTokenByType(2);
      str4 = "bank";
      str2 = "openid";
      str3 = "kp_actoken";
    }
  }

  public void LaunchOpenServiceView(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt)
  {
    AndroidPay.setOfferId("1450000581");
    APPayOpenService.SetDelegate(this);
    LoginRet localLoginRet = new LoginRet();
    int i = WGPlatform.WGGetLoginRecord(localLoginRet);
    String str1 = "";
    if (WeGame.WXPLATID == i)
      str1 = localLoginRet.getTokenByType(3);
    String str2;
    String str3;
    while (true)
    {
      str2 = "";
      str3 = "";
      if (paramInt != 4)
        break;
      APPayOpenService.LaunchOpenServiceView(paramString1, str1, paramString3, paramString4, "", paramString5, paramString6, "1001", "超级贵族", 2130837671, "1", "1001-1001", false, "");
      return;
      if (WeGame.QQPLATID != i)
        continue;
      str1 = localLoginRet.getTokenByType(2);
    }
    int j;
    if (paramInt == 1)
    {
      str2 = "LTMCLUB";
      str3 = "QQ会员";
      j = 2130837672;
    }
    try
    {
      while (true)
      {
        str4 = new String(str3.getBytes("UTF-8"), "UTF-8");
        Log.d("m3ePay", "serviceName:" + str4);
        APPayOpenService.LaunchOpenServiceView(paramString1, str1, paramString3, paramString4, "", paramString5, paramString6, str2, str4, j, "");
        return;
        if (paramInt != 2)
        {
          j = 0;
          if (paramInt != 3)
            continue;
        }
        str2 = "CJCLUBT";
        str3 = "超级会员";
        j = 2130837671;
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
      {
        localUnsupportedEncodingException.printStackTrace();
        String str4 = str3;
      }
    }
  }

  public void LaunchPayView(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    if (paramString7.equals(new String("1")))
    {
      AndroidPay.setOfferId("1450000133");
      this.resId = 2130837673;
    }
    while (true)
    {
      Log.d("m3ePay", "userid=" + paramString1 + ", userKey=" + paramString2 + ", pf=" + paramString3 + ", sessionId=" + paramString4 + ", sesstionType=" + paramString5 + ", saveValue=" + paramString6);
      if (paramString1 != "")
        this.userId = paramString1;
      if (paramString2 != "")
        this.userKey = paramString2;
      if (paramString3 != "")
        this.pf = paramString3;
      if (paramString4 != "")
        this.sessionId = paramString4;
      if (paramString5 != "")
        this.sessionType = paramString5;
      if (paramString6 != "")
        this.saveValue = paramString6;
      APPayGameService.SetDelegate(this);
      APPayGameService.LaunchSaveCurrencyView(this.userId, this.userKey, this.sessionId, this.sessionType, this.zoneId, this.pf, this.pfKey, this.acctType, this.resId);
      Log.d("m3ePay", "LaunchPayView 2");
      return;
      if (!paramString7.equals(new String("2")))
        continue;
      AndroidPay.setOfferId("1450000581");
      this.resId = 2130837674;
    }
  }

  public void PayGameNeedLogin()
  {
  }

  public void PayGameServiceCallBack(int paramInt, String paramString)
  {
    Log.d("m3ePay", "resultCode=" + paramInt + ", message=" + paramString);
  }

  public void PayGameServiceCallBack(APPayResponseInfo paramAPPayResponseInfo)
  {
    if (paramAPPayResponseInfo.payState == 0)
      getBlance();
    Log.d("m3ePay", "resultCode=" + paramAPPayResponseInfo.resultCode + ", realSaveNum=" + paramAPPayResponseInfo.realSaveNum + ", payChannel=" + paramAPPayResponseInfo.payChannel + ", payState=" + paramAPPayResponseInfo.payState + ", provideState=" + paramAPPayResponseInfo.provideState + ", resultMsg=" + paramAPPayResponseInfo.resultMsg);
  }

  public void PayOpenServiceCallBack(APPayResponseInfo paramAPPayResponseInfo)
  {
    if ((paramAPPayResponseInfo.resultCode == 0) && (paramAPPayResponseInfo.payState == 0))
    {
      getQQVipInfo();
      getBlance();
    }
    Log.d("m3ePay", "resultCode=" + paramAPPayResponseInfo.resultCode + ", realSaveNum=" + paramAPPayResponseInfo.realSaveNum + ", payChannel=" + paramAPPayResponseInfo.payChannel + ", payState=" + paramAPPayResponseInfo.payState + ", provideState=" + paramAPPayResponseInfo.provideState + ", resultMsg=" + paramAPPayResponseInfo.resultMsg);
  }

  public void PayOpenServiceNeedLogin()
  {
  }

  public native void getBlance();

  public native void getQQVipInfo();

  public void handlePay(int paramInt1, String paramString, int paramInt2)
  {
    APPayGameService.SetDelegate(this);
    LoginRet localLoginRet;
    if (paramInt2 == 4)
    {
      localLoginRet = new LoginRet();
      WGPlatform.WGGetLoginRecord(localLoginRet);
      if (WeGame.WXPLATID != localLoginRet.platform)
        break label258;
      Log.e("ret.token.elementAt(0).value %s", ((TokenRet)localLoginRet.token.elementAt(0)).value);
      Log.e("ret.token.elementAt(1).value %s", ((TokenRet)localLoginRet.token.elementAt(1)).value);
      Log.e("pf %s", paramString);
      if (paramInt1 >= 0)
        break label167;
      APPayGameService.LaunchSaveCurrencyView(localLoginRet.open_id, ((TokenRet)localLoginRet.token.elementAt(0)).value, "hy_gameid", "wc_actoken", "1", paramString, WGPlatform.WGGetPfKey(), "common", 2130837674);
    }
    label167: 
    do
    {
      return;
      if (paramInt2 == 2)
      {
        AndroidPay.setOfferId("1450000133");
        this.resId = 2130837673;
        break;
      }
      if (paramInt2 != 3)
        break;
      AndroidPay.setOfferId("1450000581");
      this.resId = 2130837674;
      break;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(paramInt1);
      String str5 = String.format("%d", arrayOfObject2);
      String str6 = localLoginRet.open_id;
      String str7 = ((TokenRet)localLoginRet.token.elementAt(0)).value;
      String str8 = WGPlatform.WGGetPfKey();
      if (paramInt1 == 0);
      for (boolean bool2 = true; ; bool2 = false)
      {
        APPayGameService.LaunchSaveCurrencyView(str6, str7, "hy_gameid", "wc_actoken", "1", paramString, str8, "common", str5, bool2, 2130837674);
        return;
      }
    }
    while (WeGame.QQPLATID != localLoginRet.platform);
    label258: Log.e("pf %s", paramString);
    if (paramInt1 < 0)
    {
      APPayGameService.LaunchSaveCurrencyView(localLoginRet.open_id, ((TokenRet)localLoginRet.token.elementAt(1)).value, "openid", "kp_actoken", "1", paramString, WGPlatform.WGGetPfKey(), "common", 2130837674);
      return;
    }
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Integer.valueOf(paramInt1);
    String str1 = String.format("%d", arrayOfObject1);
    String str2 = localLoginRet.open_id;
    String str3 = ((TokenRet)localLoginRet.token.elementAt(1)).value;
    String str4 = WGPlatform.WGGetPfKey();
    if (paramInt1 == 0);
    for (boolean bool1 = true; ; bool1 = false)
    {
      APPayGameService.LaunchSaveCurrencyView(str2, str3, "openid", "kp_actoken", "1", paramString, str4, "common", str1, bool1, 2130837674);
      return;
    }
  }

  public void init(Context paramContext)
  {
    Log.d("m3ePay", "init");
    AndroidPay.Initialize((Activity)paramContext);
    AndroidPay.setEnv("release");
    APPayGameService.SetDelegate(this);
    this.mActivity = ((Activity)paramContext);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.game.helper.m3ePay
 * JD-Core Version:    0.6.0
 */