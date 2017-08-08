package com.pay.buyManager;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.pay.AndroidPay;
import com.pay.common.tool.APLog;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.APGlobalInfo;
import com.pay.tool.APMonthDataInterface;
import com.pay.tool.APMonthDataInterface.MonthOpenType;
import com.pay.ui.channel.APChannelList;
import com.pay.ui.common.APDiamondInfo;
import com.pay.ui.common.APUICommonMethod;
import com.tenpay.tenpayplugin.TenpayCallback;
import com.tenpay.tenpayplugin.TenpayUtil;
import java.lang.ref.WeakReference;

public class APCallTenpay
{
  private WeakReference a;
  private String b = "";
  private String c = "";
  private String d = "";
  private String e = "";
  private String f = "";
  private String g = "";
  private String h = "";
  private String i = "";
  private String j = "";
  private int k = 1;
  private int l = 0;
  private TenpayCallback m = new a(this);
  private ResultReceiver n = new c(this, new Handler());

  public APCallTenpay(Context paramContext)
  {
    this.a = new WeakReference(paramContext);
  }

  public void toTenpay(String paramString1, int paramInt1, String paramString2, int paramInt2)
  {
    APLog.i("Call Tenpay", "tokenId = " + paramString1 + " count:" + paramString2);
    this.b = paramString2;
    this.l = paramInt2;
    String str1 = "数量";
    if ((paramString1 == null) || (paramString1.equals("")))
    {
      if (this.a == null)
      {
        APLog.w("APCallTenpay", "toTenpay context is null");
        return;
      }
      APUICommonMethod.showToast((Context)this.a.get(), "系统繁忙,请稍后再试");
      return;
    }
    byte[] arrayOfByte = null;
    int i1;
    label135: APOrderInfo localAPOrderInfo;
    label184: APUserInfo localAPUserInfo1;
    String str2;
    String str3;
    switch (paramInt1)
    {
    default:
      i1 = 0;
      localAPOrderInfo = APDataInterface.singleton().getOrderInfo();
      switch (paramInt1)
      {
      default:
        localAPUserInfo1 = APDataInterface.singleton().getUserInfo();
        this.h = localAPUserInfo1.openId;
        this.i = localAPUserInfo1.openKey;
        str2 = localAPUserInfo1.sessionId;
        str3 = localAPUserInfo1.sessionType;
        if ("sid".equals(str2))
        {
          this.j = "1";
          this.g = "";
          switch (paramInt2)
          {
          default:
            label272: if (!APAppDataInterface.singleton().getIsShowSaveNum())
            {
              this.c = "";
              this.d = "";
            }
            if (!APAppDataInterface.singleton().getIsShowSaveNum())
            {
              this.c = "";
              this.d = "";
            }
            APLog.i("Call Tenpay", "skey_type = " + this.j);
            APLog.i("Call Tenpay", "paytype = " + this.k);
            APLog.i("Call Tenpay", "uin = " + this.h);
            APLog.i("Call Tenpay", "price = " + this.e + "disPrice = " + this.f);
            if (this.a == null)
            {
              APLog.w("APCallTenpay", "toTenpay context is null");
              return;
            }
          case 1:
          case 2:
          }
        }
      case 0:
      case 1:
      case 3:
      case 2:
      case 4:
      case 5:
      }
    case 0:
    case 1:
      label240: if (TextUtils.isEmpty(APDataInterface.singleton().getOrderInfo().buyInfo.disPrice));
    case 2:
    case 3:
    case 4:
    case 5:
    }
    for (int i3 = APUICommonMethod.getResCurrDiamondInfo().imageResId; ; i3 = 0)
    {
      arrayOfByte = APDataInterface.singleton().getAppResId();
      this.c = " x ";
      i1 = i3;
      break label135;
      arrayOfByte = APCommMethod.BitmapResIdToByteArrary(APGlobalInfo.QBIMG);
      this.c = " x ";
      i1 = 0;
      break label135;
      arrayOfByte = APDataInterface.singleton().getAppResId();
      if (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate)
      {
        if (((Context)this.a.get()).getResources().getConfiguration().orientation == 2)
        {
          this.c = ": ";
          str1 = "数量";
          i1 = 0;
          break label135;
        }
        if (((Context)this.a.get()).getResources().getConfiguration().orientation != 1)
          break;
        this.c = "";
        str1 = "";
        i1 = 0;
        break label135;
      }
      this.c = ": ";
      str1 = "时长";
      i1 = 0;
      break label135;
      arrayOfByte = APDataInterface.singleton().getAppResId();
      if (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate)
      {
        if (((Context)this.a.get()).getResources().getConfiguration().orientation == 2)
        {
          this.c = ": ";
          str1 = "数量 ";
          i1 = 0;
          break label135;
        }
        if (((Context)this.a.get()).getResources().getConfiguration().orientation != 1)
          break;
        this.c = "";
        str1 = "";
        i1 = 0;
        break label135;
      }
      this.c = " × ";
      str1 = "数量";
      i1 = 0;
      break label135;
      this.d = this.b;
      this.e = ("1元=" + localAPOrderInfo.buyInfo.price + localAPOrderInfo.buyInfo.name);
      break label184;
      this.d = this.b;
      if (!TextUtils.isEmpty(localAPOrderInfo.buyInfo.disPrice))
        this.f = (APCommMethod.fenToYuan(localAPOrderInfo.buyInfo.disPrice, 2) + "元/个");
      this.e = (APCommMethod.fenToYuan(localAPOrderInfo.buyInfo.price, 2) + "元/个");
      break label184;
      this.d = this.b;
      this.e = "1元=1Q币";
      break label184;
      this.d = this.b;
      this.e = "1元=10Q点";
      break label184;
      if (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate)
      {
        if (((Context)this.a.get()).getResources().getConfiguration().orientation == 2)
          this.d = this.b;
        while (true)
        {
          this.e = localAPOrderInfo.buyInfo.offerName;
          break;
          if (((Context)this.a.get()).getResources().getConfiguration().orientation != 1)
            continue;
          this.d = "";
        }
      }
      APMonthDataInterface localAPMonthDataInterface = APMonthDataInterface.singleton();
      this.d = (this.b + localAPMonthDataInterface.getUnit());
      this.e = (APCommMethod.fenToYuan(localAPOrderInfo.buyInfo.price, 2) + "元/月");
      break label184;
      if ((APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate) && (((Context)this.a.get()).getResources().getConfiguration().orientation != 2))
        if (((Context)this.a.get()).getResources().getConfiguration().orientation != 1);
      for (this.d = ""; ; this.d = this.b)
      {
        this.e = localAPOrderInfo.buyInfo.offerName;
        break;
      }
      if (("uin".equals(str2)) && ("skey".equals(str3)))
      {
        this.j = "2";
        break label240;
      }
      if (("hy_gameid".equals(str2)) && ("wc_actoken".equals(str3)))
      {
        this.j = "2";
        this.h = localAPUserInfo1.payId;
        this.i = localAPUserInfo1.authKey;
        break label240;
      }
      if ("vkey".equals(str3))
      {
        this.j = "4";
        break label240;
      }
      if ("vask".equals(str3))
      {
        this.j = "5";
        break label240;
      }
      if ("kp_actoken".equals(str3))
      {
        this.j = "6";
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append("&appid=");
        localStringBuffer2.append(APAppDataInterface.singleton().getOfferid());
        localStringBuffer2.append("&openid=");
        localStringBuffer2.append(localAPUserInfo1.openId);
        localStringBuffer2.append("&paytoken=");
        localStringBuffer2.append(localAPUserInfo1.openKey);
        this.h = "";
        this.i = localStringBuffer2.toString();
        break label240;
      }
      if (!"openkey".equals(str3))
        break label240;
      this.j = "7";
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append("&appid=");
      localStringBuffer1.append(APAppDataInterface.singleton().getOfferid());
      localStringBuffer1.append("&openid=");
      localStringBuffer1.append(localAPUserInfo1.openId);
      localStringBuffer1.append("&openkey=");
      localStringBuffer1.append(localAPUserInfo1.openKey);
      this.h = "";
      this.i = localStringBuffer1.toString();
      break label240;
      if (TextUtils.isEmpty(APDataInterface.singleton().getOrderInfo().expressChannel))
      {
        this.k = 2;
        label1527: if ((paramInt1 != 3) && (paramInt1 != 2))
          break label1612;
      }
      label1612: for (String str5 = APChannelList.singleton().getAcctDiscount("cft"); ; str5 = APChannelList.singleton().getCommDiscount("cft"))
      {
        if ((str5.equals("")) || (paramInt1 == 3) || (paramInt1 == 2))
          break label1626;
        this.g = ("财付通余额(" + str5 + "折)");
        break;
        this.k = 0;
        break label1527;
      }
      label1626: this.g = "财付通余额";
      break label272;
      APUserInfo localAPUserInfo2 = APDataInterface.singleton().getUserInfo();
      if (AndroidPay.singleton().IsNeedUinLogin())
        if (localAPUserInfo2.isBindQQ)
          if (localAPUserInfo2.isKJUser)
            if ("bank".equals(APDataInterface.singleton().getOrderInfo().expressChannel))
            {
              this.k = 1;
              label1692: if ((paramInt1 != 3) && (paramInt1 != 2))
                break label1868;
            }
      label1868: for (String str4 = APChannelList.singleton().getAcctDiscount("bank"); ; str4 = APChannelList.singleton().getCommDiscount("bank"))
      {
        if ((str4.equals("")) || (paramInt1 == 3) || (paramInt1 == 2))
          break label1882;
        this.g = ("银行卡快捷支付(" + str4 + "折)");
        break;
        this.k = 3;
        break label1692;
        this.j = "-1";
        this.h = "";
        this.i = "";
        do
        {
          while (true)
          {
            this.k = 4;
            break;
            this.j = "-1";
            this.h = "";
            this.i = "";
          }
          if (!"bank".equals(APDataInterface.singleton().getOrderInfo().expressChannel))
            continue;
          this.k = 1;
          break;
        }
        while (!localAPUserInfo2.isKJUser);
        this.k = 3;
        break label1692;
      }
      label1882: this.g = "银行卡快捷支付";
      break label272;
      if ((AndroidPay.singleton().isValidPayChannelAndMarket()) && (APDataInterface.singleton().getPayAssignChannel().equals("bank")))
        APDataReportManager.getInstance().insertData("sdk.market.pay", APDataInterface.singleton().getOrderInfo().saveType, null, APDataInterface.singleton().getPayAssignChannel(), APDataInterface.singleton().getDiscountExtras());
      int i2 = APDataInterface.singleton().getScreenType();
      if ((i2 == 0) || (i2 == 1))
      {
        TenpayUtil.gotoTenpay((Context)this.a.get(), this.h, this.i, this.j, paramString1, this.k, arrayOfByte, str1, this.d, this.c, this.e, i1, this.f, this.g, i2, this.m, this.n);
        return;
      }
      TenpayUtil.gotoTenpay((Context)this.a.get(), this.h, this.i, this.j, paramString1, this.k, arrayOfByte, str1, this.d, this.c, this.e, i1, this.f, this.g, this.m, this.n);
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.APCallTenpay
 * JD-Core Version:    0.6.0
 */