package com.pay.buyManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.pay.AndroidPay;
import com.pay.common.tool.APLog;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APNetworkManager;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.network.modle.APGetTokenAns;
import com.pay.network.modle.APSaveAns;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.APMonthDataInterface;
import com.pay.tool.APMonthDataInterface.MonthOpenType;
import com.pay.tool.APPassWordTools;
import com.pay.ui.channel.APChannelActivity;
import com.pay.ui.channel.APWechatActivity;
import com.pay.ui.common.APAlertDialog;
import com.pay.ui.common.APAlertDialog.Builder;
import com.pay.ui.common.APQCardSuccessActivity;
import com.pay.ui.common.APUICommonMethod;
import com.pay.ui.payWeb.APWebBuyActivity;
import com.pay.ui.payWeb.APWebProtocol;

public class APPayManager extends APPayBase
  implements IAPHttpAnsObserver
{
  private String a;
  private String b;
  private String c;
  private String d;
  private int e;
  private int f;
  private String g;
  private String h;
  private String i;
  private String j;
  private String k;
  private String l;
  private IAPHttpAnsObserver m;

  public APPayManager(Context paramContext, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    super(paramContext);
    this.m = paramIAPHttpAnsObserver;
  }

  private int a(String paramString)
  {
    int n;
    APOrderInfo localAPOrderInfo;
    try
    {
      int i2 = Integer.valueOf(paramString).intValue();
      n = i2;
      localAPOrderInfo = APDataInterface.singleton().getOrderInfo();
      switch (this.saveType)
      {
      default:
        return 0;
      case 0:
      case 1:
      case 3:
      case 2:
      case 4:
      case 5:
      }
    }
    catch (Exception localException)
    {
      while (true)
        n = 0;
      return n * Integer.valueOf(localAPOrderInfo.buyInfo.price).intValue() / 100;
    }
    int i1 = Integer.valueOf(localAPOrderInfo.buyInfo.price).intValue();
    if (!TextUtils.isEmpty(localAPOrderInfo.buyInfo.disPrice))
      i1 = Integer.valueOf(localAPOrderInfo.buyInfo.price).intValue();
    return n / i1;
    return n / 100;
    return n / 10;
    return n / Integer.valueOf(localAPOrderInfo.buyInfo.price).intValue();
  }

  private void a()
  {
    APUICommonMethod.showWaitDialog(this.context, null);
    if (this.curChannel == 5)
    {
      int i5 = this.saveType;
      String str9 = this.a;
      String str10 = this.b;
      int i6 = this.e;
      int i7 = this.f;
      String str11 = this.j;
      String str12 = this.k;
      this.saveType = i5;
      this.curChannel = 5;
      APNetworkManager.getInstance().saveByMcard(str9, str10, String.valueOf(i6 * 100), String.valueOf(i7), i5, "", str11, str12, this.m);
    }
    while (true)
    {
      this.j = "";
      this.k = "";
      return;
      if (this.curChannel == 4)
      {
        String str8 = this.c;
        if (!TextUtils.isEmpty(this.d))
          str8 = this.d;
        a(this.saveType, this.a, this.b, str8, this.j, this.k);
        this.d = "";
        continue;
      }
      if (this.curChannel == 9)
      {
        int i4 = this.saveType;
        String str4 = this.g;
        String str5 = this.h;
        String str6 = this.c;
        String str7 = this.i;
        this.saveType = i4;
        this.g = str4;
        this.h = str5;
        this.curChannel = 9;
        APNetworkManager.getInstance().saveByHF(i4, str4, str5, str6, str7, this.m);
        continue;
      }
      if ((this.curChannel == 0) || (this.curChannel == 11))
      {
        int n = this.saveType;
        int i1 = this.curChannel;
        String str1 = this.l;
        this.saveType = n;
        this.curChannel = i1;
        String str2 = APDataInterface.singleton().getOrderInfo().saveNum;
        APNetworkManager.getInstance().saveByAcct(n, i1, APDataInterface.singleton().getMbSig(), str1, str2, this.m);
        continue;
      }
      int i2 = this.saveType;
      int i3 = this.curChannel;
      this.saveType = i2;
      this.curChannel = i3;
      String str3 = APDataInterface.singleton().getOrderInfo().saveNum;
      switch (i3)
      {
      case 3:
      case 4:
      case 5:
      case 6:
      default:
        break;
      case 1:
        APNetworkManager.getInstance().saveByTenpay(1, i2, str3, this.m);
        break;
      case 7:
        APNetworkManager.getInstance().saveByYB(i2, str3, this.m);
        break;
      case 10:
        APNetworkManager.getInstance().saveByGoldCoupons(i2, str3, this.m);
        break;
      case 9:
        APNetworkManager.getInstance().saveByHF(i2, this.g, this.h, str3, null, this.m);
        break;
      case 2:
        APNetworkManager.getInstance().saveByTenpay(2, i2, str3, this.m);
        break;
      case 8:
        APNetworkManager.getInstance().saveByWeChat(i2, str3, this.m);
      }
    }
  }

  private void a(int paramInt)
  {
    String str1;
    String str2;
    if (paramInt == 11)
    {
      str1 = "充值Q币";
      str2 = "您的Q币余额不足";
    }
    while (true)
    {
      APDataReportManager.getInstance().insertData("sdk.notenough.show", this.saveType, null, String.valueOf(paramInt), null);
      APAlertDialog.Builder localBuilder = new APAlertDialog.Builder(this.context);
      localBuilder.setTitle("温馨提示");
      localBuilder.setMessage(str2);
      localBuilder.setPositiveButton(str1, new k(this, paramInt));
      localBuilder.setNegativeButton("返回", new l(this, paramInt));
      APAlertDialog localAPAlertDialog = localBuilder.create();
      if (localAPAlertDialog == null)
      {
        return;
        if (paramInt == 0)
        {
          str1 = "充值Q点";
          str2 = "您的Q点余额不足";
          continue;
        }
      }
      else
      {
        localAPAlertDialog.setOnKeyListener(new m(this, paramInt));
        localAPAlertDialog.show();
        return;
      }
      str1 = "";
      str2 = "";
    }
  }

  private void a(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.saveType = paramInt;
    this.curChannel = 4;
    this.a = paramString1;
    this.b = paramString2;
    APNetworkManager.getInstance().saveByQQCard(paramString1, paramString2, paramString3, paramInt, paramString4, paramString5, this.m);
  }

  private void a(APSaveAns paramAPSaveAns)
  {
    String str1 = paramAPSaveAns.getSaveCount();
    switch (this.curChannel)
    {
    case 6:
    case 9:
    default:
      return;
    case 1:
    case 2:
    case 3:
      String str4 = paramAPSaveAns.getTenpayUrl();
      new APCallTenpay(this.context).toTenpay(str4, this.saveType, str1, this.curChannel);
      return;
    case 0:
      APDataInterface.singleton().setSmsInfo("");
      if (("qdqb".equals(APDataInterface.singleton().getOrderInfo().expressChannel)) && (APDataInterface.singleton().getMbSig().length() < 10))
      {
        if (APCommMethod.isPayGameResultByWeb())
        {
          Bundle localBundle6 = new Bundle();
          localBundle6.putInt("saveType", this.saveType);
          localBundle6.putString("count", str1);
          localBundle6.putInt("channel", this.curChannel);
          Intent localIntent7 = new Intent();
          localIntent7.putExtras(localBundle6);
          APWebBuyActivity.loadWebPage = APWebProtocol.WEBPAGE_PAYGAME_RESULT;
          localIntent7.setClass(this.context, APWebBuyActivity.class);
          this.context.startActivity(localIntent7);
          ((Activity)this.context).overridePendingTransition(APCommMethod.getAnimId(this.context, "unipay_anim_in_from_right"), APCommMethod.getAnimId(this.context, "unipay_anim_out_to_left"));
          return;
        }
        APUICommonMethod.popActivity();
        APDataReportManager.getInstance().insertData("sdk.succ.toast", APDataInterface.singleton().getOrderInfo().saveType, null, String.valueOf(this.curChannel), null);
        APUICommonMethod.successToast(this.context, APDataInterface.singleton().getOrderInfo().saveType, APDataInterface.singleton().getOrderInfo().saveNum);
        APCommMethod.paySuccCallBack(this.curChannel, 0, 0);
        return;
      }
      Bundle localBundle5 = new Bundle();
      localBundle5.putInt("saveType", this.saveType);
      localBundle5.putString("count", str1);
      localBundle5.putInt("channel", this.curChannel);
      Intent localIntent6 = new Intent();
      localIntent6.putExtras(localBundle5);
      if (APDataInterface.singleton().getOrderInfo().saveType == 0)
        if (APCommMethod.isPayGameResultByWeb())
        {
          APWebBuyActivity.loadWebPage = APWebProtocol.WEBPAGE_PAYGAME_RESULT;
          localIntent6.setClass(this.context, APWebBuyActivity.class);
        }
      while (true)
      {
        this.context.startActivity(localIntent6);
        ((Activity)this.context).overridePendingTransition(APCommMethod.getAnimId(this.context, "unipay_anim_in_from_right"), APCommMethod.getAnimId(this.context, "unipay_anim_out_to_left"));
        return;
        localIntent6.setClass(this.context, APQCardSuccessActivity.class);
        continue;
        localIntent6.setClass(this.context, APQCardSuccessActivity.class);
      }
    case 11:
      APDataInterface.singleton().setSmsInfo("");
      if (("qbqd".equals(APDataInterface.singleton().getOrderInfo().expressChannel)) && (APDataInterface.singleton().getMbSig().length() < 10))
      {
        if (APCommMethod.isPayGameResultByWeb())
        {
          Bundle localBundle4 = new Bundle();
          localBundle4.putInt("saveType", this.saveType);
          localBundle4.putString("count", str1);
          localBundle4.putInt("channel", this.curChannel);
          Intent localIntent5 = new Intent();
          localIntent5.putExtras(localBundle4);
          APWebBuyActivity.loadWebPage = APWebProtocol.WEBPAGE_PAYGAME_RESULT;
          localIntent5.setClass(this.context, APWebBuyActivity.class);
          this.context.startActivity(localIntent5);
          ((Activity)this.context).overridePendingTransition(APCommMethod.getAnimId(this.context, "unipay_anim_in_from_right"), APCommMethod.getAnimId(this.context, "unipay_anim_out_to_left"));
          return;
        }
        APUICommonMethod.popActivity();
        APDataReportManager.getInstance().insertData("sdk.succ.toast", APDataInterface.singleton().getOrderInfo().saveType, null, String.valueOf(this.curChannel), null);
        APUICommonMethod.successToast(this.context, APDataInterface.singleton().getOrderInfo().saveType, APDataInterface.singleton().getOrderInfo().saveNum);
        APCommMethod.paySuccCallBack(this.curChannel, 0, 0);
        return;
      }
      Bundle localBundle3 = new Bundle();
      localBundle3.putInt("saveType", this.saveType);
      localBundle3.putString("count", str1);
      localBundle3.putInt("channel", this.curChannel);
      Intent localIntent4 = new Intent();
      localIntent4.putExtras(localBundle3);
      if (APDataInterface.singleton().getOrderInfo().saveType == 0)
        if (APCommMethod.isPayGameResultByWeb())
        {
          APWebBuyActivity.loadWebPage = APWebProtocol.WEBPAGE_PAYGAME_RESULT;
          localIntent4.setClass(this.context, APWebBuyActivity.class);
        }
      while (true)
      {
        this.context.startActivity(localIntent4);
        ((Activity)this.context).overridePendingTransition(APCommMethod.getAnimId(this.context, "unipay_anim_in_from_right"), APCommMethod.getAnimId(this.context, "unipay_anim_out_to_left"));
        return;
        localIntent4.setClass(this.context, APQCardSuccessActivity.class);
        continue;
        localIntent4.setClass(this.context, APQCardSuccessActivity.class);
      }
    case 7:
    case 10:
      Bundle localBundle2 = new Bundle();
      localBundle2.putInt("saveType", this.saveType);
      localBundle2.putInt("channel", this.curChannel);
      Intent localIntent3 = new Intent();
      localIntent3.putExtras(localBundle2);
      if (APDataInterface.singleton().getOrderInfo().saveType == 0)
        if (APCommMethod.isPayGameResultByWeb())
        {
          APWebBuyActivity.loadWebPage = APWebProtocol.WEBPAGE_PAYGAME_RESULT;
          localIntent3.setClass(this.context, APWebBuyActivity.class);
        }
      while (true)
      {
        this.context.startActivity(localIntent3);
        ((Activity)this.context).overridePendingTransition(APCommMethod.getAnimId(this.context, "unipay_anim_in_from_right"), APCommMethod.getAnimId(this.context, "unipay_anim_out_to_left"));
        return;
        localIntent3.setClass(this.context, APQCardSuccessActivity.class);
        continue;
        localIntent3.setClass(this.context, APQCardSuccessActivity.class);
      }
    case 4:
      APDataInterface.singleton().getOrderInfo().saveNum = str1;
      Bundle localBundle1 = new Bundle();
      localBundle1.putInt("saveType", this.saveType);
      localBundle1.putInt("channel", this.curChannel);
      localBundle1.putString("count", str1);
      Intent localIntent2 = new Intent();
      localIntent2.putExtras(localBundle1);
      if (APDataInterface.singleton().getOrderInfo().saveType == 0)
        if (APCommMethod.isPayGameResultByWeb())
        {
          APWebBuyActivity.loadWebPage = APWebProtocol.WEBPAGE_PAYGAME_RESULT;
          localIntent2.setClass(this.context, APWebBuyActivity.class);
        }
      while (true)
      {
        this.context.startActivity(localIntent2);
        ((Activity)this.context).overridePendingTransition(APCommMethod.getAnimId(this.context, "unipay_anim_in_from_right"), APCommMethod.getAnimId(this.context, "unipay_anim_out_to_left"));
        return;
        localIntent2.setClass(this.context, APQCardSuccessActivity.class);
        continue;
        localIntent2.setClass(this.context, APQCardSuccessActivity.class);
      }
    case 8:
      Intent localIntent1 = new Intent(this.context, APWechatActivity.class);
      this.context.startActivity(localIntent1);
      APWechatPayAPI localAPWechatPayAPI = new APWechatPayAPI(this.context);
      String str3 = APAppDataInterface.singleton().getWechatAppId();
      if (str3.equals(""))
        str3 = paramAPSaveAns.getWXAppId();
      APLog.i("APSavemanager", "toWeChat wxappid:" + str3);
      APLog.i("APSavemanager", "toWeChat partnerid:1000018901");
      APLog.i("APSavemanager", "toWeChat url:" + paramAPSaveAns.getTenpayUrl());
      APLog.i("APSavemanager", "toWeChat sign:" + paramAPSaveAns.getWXSign());
      if ((AndroidPay.singleton().isValidPayChannelAndMarket()) && (APDataInterface.singleton().getPayAssignChannel().equals("wechat")))
        APDataReportManager.getInstance().insertData("sdk.market.pay", APDataInterface.singleton().getOrderInfo().saveType, null, APDataInterface.singleton().getPayAssignChannel(), APDataInterface.singleton().getDiscountExtras());
      localAPWechatPayAPI.toWeChat(paramAPSaveAns.getWXAppId(), "1000018901", paramAPSaveAns.getTenpayUrl(), paramAPSaveAns.getWXNonce(), paramAPSaveAns.getWXTime(), paramAPSaveAns.getWxPackage(), paramAPSaveAns.getWXSign());
      return;
    case 5:
    }
    String str2 = paramAPSaveAns.getMcardSerialNo();
    APDataInterface.singleton().getOrderInfo().orderId = str2;
    queryMcardState(str2);
  }

  private void a(String paramString, int paramInt)
  {
    APAlertDialog.Builder localBuilder = new APAlertDialog.Builder(this.context);
    localBuilder.setTitle("温馨提示");
    localBuilder.setMessage(paramString);
    if (paramInt == 4)
      APDataReportManager.getInstance().insertData("sdk.notenough.show", this.saveType, null, "16", null);
    APAlertDialog localAPAlertDialog;
    while (true)
    {
      localBuilder.setPositiveButton("确定", new f(this, paramInt));
      localAPAlertDialog = localBuilder.create();
      if (localAPAlertDialog != null)
        break;
      return;
      APDataReportManager.getInstance().insertData("sdk.notenough.show", this.saveType, null, String.valueOf(paramInt), null);
    }
    localAPAlertDialog.setOnKeyListener(new j(this, paramInt));
    localAPAlertDialog.show();
  }

  private void a(String paramString1, String paramString2)
  {
    if ((paramString2.equals("error")) && (!AndroidPay.singleton().isUILaunched))
    {
      APLog.i("fromerror", String.valueOf(AndroidPay.singleton().isUILaunched));
      APCommMethod.payErrorCallBack(-1, paramString1);
    }
    APAlertDialog.Builder localBuilder = new APAlertDialog.Builder(this.context);
    localBuilder.setTitle("温馨提示");
    localBuilder.setMessage(paramString1);
    localBuilder.setNeutralButton("确定", new g(this, paramString2));
    APAlertDialog localAPAlertDialog = localBuilder.create();
    localAPAlertDialog.setOnKeyListener(new h(this, paramString2));
    localAPAlertDialog.show();
  }

  public void doSave(int paramInt)
  {
    super.doSave(paramInt);
    this.saveType = paramInt;
    switch (paramInt)
    {
    default:
      APLog.w("APPayManager", "saveType error");
      return;
    case 0:
    case 2:
    case 3:
    case 4:
    case 5:
      a();
      return;
    case 1:
    }
    a();
  }

  public int getCurChannel()
  {
    return this.curChannel;
  }

  protected void getTokenAndSave()
  {
    APUICommonMethod.showWaitDialog(this.context, null);
    APNetworkManager.getInstance().getToken(this);
  }

  public void getTokenAndSave(int paramInt)
  {
    this.saveType = paramInt;
    switch (paramInt)
    {
    default:
      APLog.w("APPayManager", "save type error");
      return;
    case 0:
    case 2:
    case 3:
    case 4:
    case 5:
      getTokenAndSave();
      return;
    case 1:
    }
    doSave(paramInt);
  }

  public void goldCouponsPay(int paramInt)
  {
    this.saveType = paramInt;
    this.curChannel = 10;
    getTokenAndSave(paramInt);
  }

  public void onError(APBaseHttpAns paramAPBaseHttpAns)
  {
    APUICommonMethod.dismissWaitDialog();
    String str = paramAPBaseHttpAns.getResultMessage();
    APUICommonMethod.showToast(this.context, str);
  }

  public void onFinish(APBaseHttpAns paramAPBaseHttpAns)
  {
    APGetTokenAns localAPGetTokenAns;
    if (paramAPBaseHttpAns.getHttpReqKey().equals("gettoken"))
      localAPGetTokenAns = (APGetTokenAns)paramAPBaseHttpAns;
    switch (localAPGetTokenAns.getResultCode())
    {
    default:
      APUICommonMethod.dismissWaitDialog();
      String str2 = paramAPBaseHttpAns.getResultMessage();
      APUICommonMethod.showToast(this.context, str2);
    case 1094:
    case 1099:
      return;
    case 0:
      String str1 = localAPGetTokenAns.getToken();
      APDataInterface.singleton().getOrderInfo().tokenId = str1;
      doSave(this.saveType);
      return;
    case 1018:
    }
    APUICommonMethod.dismissWaitDialog();
    loginErrorProgress(4);
  }

  public void onStop(APBaseHttpAns paramAPBaseHttpAns)
  {
    APUICommonMethod.dismissWaitDialog();
  }

  public void progressPayManagerAns(APBaseHttpAns paramAPBaseHttpAns)
  {
    APUICommonMethod.dismissWaitDialog();
    int n = paramAPBaseHttpAns.getResultCode();
    APSaveAns localAPSaveAns = (APSaveAns)paramAPBaseHttpAns;
    label219: String str7;
    String str8;
    int i2;
    switch (n)
    {
    default:
      Intent localIntent = new Intent();
      if ((TextUtils.isEmpty(APDataInterface.singleton().getOrderInfo().expressChannel)) || ((this.curChannel != 1) && (this.curChannel != 3)))
        break;
      APUICommonMethod.dismissWaitDialog();
      APDataInterface.singleton().getOrderInfo().expressChannel = null;
      localIntent.setClass(this.context, APChannelActivity.class);
      this.context.startActivity(localIntent);
    case 1094:
    case 1099:
    case 10002:
    case 10006:
    case 0:
    case 10008:
    case 1004:
    case 1035:
    case 1023:
      do
      {
        do
        {
          return;
          a(localAPSaveAns);
          return;
          a(localAPSaveAns);
          return;
          if (this.curChannel == 7)
          {
            a("您的元宝余额不足", this.curChannel);
            return;
          }
          if (this.curChannel == 10)
          {
            a("您的金券余额不足", this.curChannel);
            return;
          }
          if (this.curChannel != 0)
            continue;
          a(this.curChannel);
          return;
        }
        while (this.curChannel != 11);
        a(this.curChannel);
        return;
        String str9 = localAPSaveAns.getResultMessage();
        APAlertDialog.Builder localBuilder2 = new APAlertDialog.Builder(this.context);
        localBuilder2.setTitle("温馨提示");
        localBuilder2.setMessage(str9);
        localBuilder2.setNeutralButton("确定", new q(this));
        localBuilder2.create().show();
        return;
      }
      while (this.saveType != 1);
      APUICommonMethod.popActivity();
      APCommMethod.payErrorCallBack(-1, "订单失效");
      return;
    case 1018:
      loginErrorProgress(5);
      return;
    case 10001:
      str7 = localAPSaveAns.getMbUrl();
      str8 = localAPSaveAns.getSmsInfo();
      i2 = localAPSaveAns.getSmsRemain();
      if (!localAPSaveAns.getSmsMbOnly().equals("1"));
    case 1125:
    case 1058:
    case 10003:
    case 1014:
    }
    for (boolean bool = true; ; bool = false)
    {
      while (true)
      {
        if (str8.length() > 0)
        {
          APPassWordTools.launPassActicity((Activity)this.context, true, i2, str7, str8, bool);
          return;
        }
        APPassWordTools.launPassActicity((Activity)this.context, false, 0, str7, str8, false);
        return;
        APUICommonMethod.showToast(this.context, localAPSaveAns.getErrorMessage(), null, false);
        return;
        a("系统繁忙,请稍后再试", "protalgetway");
        return;
        APUICommonMethod.showToast(this.context, "需要手机支付密码");
        return;
        if (((this.saveType == 5) || (this.saveType == 4)) && (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate))
        {
          a("Q卡余额不足", this.curChannel);
          return;
        }
        int i1 = a(localAPSaveAns.getQCardBalance());
        if ((i1 != 0) && (APDataInterface.singleton().getOrderInfo().isNumCanChange) && (i1 > APDataInterface.singleton().getOrderInfo().buyInfo.minNum))
          try
          {
            String str6 = String.valueOf(Float.valueOf(Integer.valueOf(localAPSaveAns.getQCardBalance()).intValue() / 100.0F));
            str1 = str6;
            String str2 = this.a;
            String str3 = this.b;
            String str4 = String.valueOf(i1);
            APDataReportManager.getInstance().insertData("sdk.notenough.show", this.saveType, null, "15", null);
            str5 = "";
            switch (this.saveType)
            {
            default:
              APAlertDialog.Builder localBuilder1 = new APAlertDialog.Builder(this.context);
              localBuilder1.setTitle("温馨提示");
              localBuilder1.setMessage(str5);
              localBuilder1.setNegativeButton("取消", new n(this));
              localBuilder1.setPositiveButton("确定", new o(this, str4, str2, str3));
              APAlertDialog localAPAlertDialog = localBuilder1.create();
              if (localAPAlertDialog == null)
                break label219;
              localAPAlertDialog.setOnKeyListener(new p(this));
              localAPAlertDialog.show();
              return;
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            }
          }
          catch (Exception localException)
          {
            while (true)
            {
              String str1 = "";
              continue;
              String str5 = "QQ卡余额仅为" + str1 + "元,是否全部用于充值" + APDataInterface.singleton().getOrderInfo().buyInfo.name + "?";
              continue;
              str5 = "QQ卡余额仅为" + str1 + "元,是否全部用于购买" + APDataInterface.singleton().getOrderInfo().buyInfo.name + "?";
              continue;
              str5 = "QQ卡余额仅为" + str1 + "元,是否全部用于充值Q点?";
              continue;
              str5 = "QQ卡余额仅为" + str1 + "元,是否全部用于充值Q币?";
              continue;
              str5 = "QQ卡余额仅为" + str1 + "元,是否全部用于开通" + APDataInterface.singleton().getOrderInfo().buyInfo.name + "?";
              continue;
              str5 = "QQ卡余额仅为" + str1 + "元,是否全部用于购买" + APDataInterface.singleton().getOrderInfo().buyInfo.name + "?";
            }
          }
      }
      a("Q卡余额不足", this.curChannel);
      return;
      a(localAPSaveAns.getResultMessage(), "error");
      return;
    }
  }

  protected void queryMcardState(String paramString)
  {
    APNetworkManager.getInstance().queryMcardStatus(paramString, new i(this));
  }

  public void setMbType(String paramString)
  {
    this.l = paramString;
  }

  public void setVerifyCode(String paramString1, String paramString2)
  {
    this.j = paramString1;
    this.k = paramString2;
  }

  public void toAccountPay(int paramInt1, int paramInt2)
  {
    this.saveType = paramInt1;
    this.curChannel = paramInt2;
    String str = "您正在使用Q点Q币支付";
    if (this.curChannel == 11)
      str = "您正在使用Q币Q点支付";
    if ((AndroidPay.singleton().IsNeedUinLogin()) && (!APDataInterface.singleton().getUserInfo().isUinLogin))
    {
      loadLogin(true, str, 4);
      return;
    }
    getTokenAndSave(paramInt1);
  }

  public void toBankPay(int paramInt)
  {
    this.curChannel = 2;
    this.saveType = paramInt;
    if ((AndroidPay.singleton().IsNeedUinLogin()) && (APDataInterface.singleton().getUserInfo().isBindQQ) && (!APDataInterface.singleton().getUserInfo().isUinLogin))
    {
      if (APDataInterface.singleton().getUserInfo().isKJUser)
      {
        loadLogin(true, "银行卡快捷支付(91折)", 4);
        return;
      }
      getTokenAndSave(paramInt);
      return;
    }
    getTokenAndSave(paramInt);
  }

  public void toHFPay(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.saveType = paramInt;
    this.h = paramString2;
    this.g = paramString1;
    this.c = paramString3;
    this.i = paramString4;
    this.curChannel = 9;
    getTokenAndSave(paramInt);
  }

  public void toKjPay(int paramInt)
  {
    this.curChannel = 3;
    this.saveType = paramInt;
    getTokenAndSave(paramInt);
  }

  public void toMCardPay(int paramInt1, String paramString1, String paramString2, int paramInt2, int paramInt3, String paramString3, String paramString4)
  {
    this.saveType = paramInt1;
    this.curChannel = 5;
    this.a = paramString1;
    this.b = paramString2;
    this.e = paramInt2;
    this.f = paramInt3;
    this.j = paramString3;
    this.k = paramString4;
    getTokenAndSave(paramInt1);
  }

  public void toQQCardPay(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.saveType = paramInt;
    this.curChannel = 4;
    this.a = paramString1;
    this.b = paramString2;
    this.c = paramString3;
    this.j = paramString4;
    this.k = paramString5;
    getTokenAndSave(paramInt);
  }

  public void toTenpayPay(int paramInt)
  {
    this.curChannel = 1;
    this.saveType = paramInt;
    if (AndroidPay.singleton().IsNeedUinLogin())
    {
      if (!APDataInterface.singleton().getUserInfo().isUinLogin)
      {
        loadLogin(true, "财付通余额支付(91折)", 4);
        return;
      }
      getTokenAndSave(paramInt);
      return;
    }
    if (!APDataInterface.singleton().getUserInfo().isCFTUser)
    {
      APUICommonMethod.showToast(this.context, "您还没有开通财付通,请使用其它方式支付");
      return;
    }
    getTokenAndSave(paramInt);
  }

  public void toWeChatPay(int paramInt)
  {
    this.saveType = paramInt;
    APWechatPayAPI localAPWechatPayAPI = new APWechatPayAPI(this.context);
    if (!localAPWechatPayAPI.getWechatCanUse())
      APUICommonMethod.showToast(this.context, "微信支付暂不可用");
    while (true)
    {
      return;
      if (localAPWechatPayAPI.isInstallWechat())
        break;
      APLog.i("APSaveManager", "wechat is unInstalled");
      APUICommonMethod.dismissWaitDialog();
      APUICommonMethod.showToast(this.context, "您还没安装微信");
      if (TextUtils.isEmpty(APDataInterface.singleton().getPayAssignChannel()))
        continue;
      APCommMethod.payErrorCallBack(2, "您还没安装微信");
      return;
    }
    if (!localAPWechatPayAPI.isSupportWechatAPI())
    {
      APUICommonMethod.dismissWaitDialog();
      APUICommonMethod.showToast(this.context, "您的微信版本过低,请更新");
      return;
    }
    this.curChannel = 8;
    getTokenAndSave(paramInt);
  }

  public void toYbPay(int paramInt)
  {
    this.saveType = paramInt;
    this.curChannel = 7;
    getTokenAndSave(paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.APPayManager
 * JD-Core Version:    0.6.0
 */