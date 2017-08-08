package com.pay.buyManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.pay.AndroidPay;
import com.pay.AndroidPay.APLaunchRootViewOption;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.http.APBaseHttpAns;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APTools;
import com.pay.ui.channel.APChannelActivity;
import com.pay.ui.common.APUICommonMethod;
import com.pay.ui.payCenter.APPayGameInputNumActivity;
import com.pay.ui.payCenter.APPayGameListNumActivity;
import com.pay.ui.payExpress.APPayExpressActivity;
import com.pay.ui.payWeb.APWebBuyActivity;
import com.pay.ui.payWeb.APWebProtocol;
import com.pay.ui.saveAccount.APSaveAccountInputNumActivity;

public class APLanuchPayManager
  implements IAPHttpAnsObserver
{
  private APPayManager a = null;

  public static int coverChannelIdWithExpress(String paramString)
  {
    if (paramString.equals("qdqb"));
    do
      return 0;
    while (!paramString.equals("qbqd"));
    return 11;
  }

  public static boolean judgePayBySaveType(int paramInt)
  {
    if ((paramInt == 0) && (TextUtils.isEmpty(APDataInterface.singleton().getPayAssignChannel())) && (!APTools.isGameNumMoreMin(APDataInterface.singleton().getOrderInfo().saveNum)))
    {
      APUICommonMethod.dismissWaitDialog();
      APUICommonMethod.showToast(AndroidPay.singleton().applicationContext, "充值数量不能小于" + APDataInterface.singleton().getOrderInfo().buyInfo.minNum);
      APUICommonMethod.popActivity();
      APCommMethod.payErrorCallBack(-1, "充值数量不能小于最小充值数量");
      return false;
    }
    return true;
  }

  public static void modifyBySaveType(int paramInt, String paramString, Bundle paramBundle)
  {
    if (((paramInt == 4) || (paramInt == 5)) && (paramString.equals("qbqd")))
      paramBundle.putString("FROM_ACTIVITY", "AndroidPay");
  }

  public void LanuchChangeAmtwithType(int paramInt, Context paramContext)
  {
    APUICommonMethod.dismissWaitDialog();
    APDataInterface.singleton().setSourceActivity("APPayGameInputNumActivity");
    Intent localIntent = new Intent();
    if (paramInt == 3)
      localIntent.setClass(paramContext, APSaveAccountInputNumActivity.class);
    while (true)
    {
      paramContext.startActivity(localIntent);
      return;
      localIntent.setClass(paramContext, APPayGameInputNumActivity.class);
    }
  }

  public void LanuchChannelList(Context paramContext)
  {
    APUICommonMethod.dismissWaitDialog();
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, APChannelActivity.class);
    paramContext.startActivity(localIntent);
  }

  public void LanuchExpress(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    if (TextUtils.isEmpty(paramString))
    {
      LanuchChannelList(paramContext);
      return;
    }
    if ((AndroidPay.singleton().IsNeedUinLogin()) && (!APDataInterface.singleton().getUserInfo().isUinLogin))
    {
      LanuchChannelList(paramContext);
      return;
    }
    payExpress(paramContext, paramString, paramInt1, paramInt2);
  }

  public void LanuchGameList(Context paramContext)
  {
    APUICommonMethod.dismissWaitDialog();
    Intent localIntent = new Intent();
    if (APCommMethod.isPayGameListByWeb())
    {
      APDataInterface.singleton().setSourceActivity("APWebBuyActivity");
      APWebBuyActivity.loadWebPage = APWebProtocol.WEBPAGE_PAYGAME_LIST;
      localIntent.setClass(paramContext, APWebBuyActivity.class);
    }
    while (true)
    {
      paramContext.startActivity(localIntent);
      return;
      APDataInterface.singleton().setSourceActivity("APPayGameListNumActivity");
      localIntent.setClass(paramContext, APPayGameListNumActivity.class);
    }
  }

  public void LanuchPay(Context paramContext, AndroidPay.APLaunchRootViewOption paramAPLaunchRootViewOption)
  {
    String str = APDataInterface.singleton().getOrderInfo().expressChannel;
    int i = APDataInterface.singleton().getOrderInfo().saveType;
    APDataInterface.singleton().setPreSaveNumber(APDataInterface.singleton().getOrderInfo().saveNum);
    if (paramAPLaunchRootViewOption == AndroidPay.APLaunchRootViewOption.APPayGameListNumView)
      LanuchGameList(paramContext);
    do
    {
      do
        return;
      while (paramAPLaunchRootViewOption != AndroidPay.APLaunchRootViewOption.APPayGameInputNumView);
      if (!APDataInterface.singleton().getOrderInfo().isNumCanChange)
        continue;
      LanuchChangeAmtwithType(i, paramContext);
      return;
    }
    while ((OneChannelPay(i, paramContext)) || (!judgePayBySaveType(i)));
    LanuchExpress(paramContext, str, coverChannelIdWithExpress(str), i);
  }

  public boolean OneChannelPay(int paramInt, Context paramContext)
  {
    String str = APDataInterface.singleton().getPayAssignChannel();
    if ((str == null) || (str.equals("")));
    do
    {
      do
        return false;
      while (((paramInt == 3) || (paramInt == 2)) && ((str.equals("qdqb")) || (str.equals("qbqd"))));
      if (!str.equals("bank"))
        continue;
      this.a = new APPayManager(paramContext, this);
      this.a.toBankPay(paramInt);
      return true;
    }
    while (!str.equals("wechat"));
    this.a = new APPayManager(paramContext, this);
    this.a.toWeChatPay(paramInt);
    return true;
  }

  public void onError(APBaseHttpAns paramAPBaseHttpAns)
  {
  }

  public void onFinish(APBaseHttpAns paramAPBaseHttpAns)
  {
    if (this.a != null)
      this.a.progressPayManagerAns(paramAPBaseHttpAns);
  }

  public void onStop(APBaseHttpAns paramAPBaseHttpAns)
  {
  }

  public void payExpress(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    if (paramString.equals("cft"))
    {
      APDataInterface.singleton().getOrderInfo().expressChannel = paramString;
      this.a = new APPayManager(paramContext, this);
      this.a.toTenpayPay(paramInt2);
      return;
    }
    if (paramString.equals("bank"))
    {
      APDataInterface.singleton().getOrderInfo().expressChannel = paramString;
      this.a = new APPayManager(paramContext, this);
      this.a.toBankPay(paramInt2);
      return;
    }
    if (paramInt2 != 3)
    {
      APUICommonMethod.dismissWaitDialog();
      APDataInterface.singleton().getOrderInfo().expressChannel = paramString;
      Intent localIntent2 = new Intent();
      localIntent2.setClass(paramContext, APPayExpressActivity.class);
      Bundle localBundle = new Bundle();
      localBundle.putInt("subChannel", paramInt1);
      modifyBySaveType(paramInt2, paramString, localBundle);
      localIntent2.putExtras(localBundle);
      paramContext.startActivity(localIntent2);
      return;
    }
    APUICommonMethod.dismissWaitDialog();
    Intent localIntent1 = new Intent();
    localIntent1.setClass(paramContext, APChannelActivity.class);
    paramContext.startActivity(localIntent1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.APLanuchPayManager
 * JD-Core Version:    0.6.0
 */