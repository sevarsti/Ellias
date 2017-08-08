package com.pay.ui.channel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import com.pay.AndroidPay;
import com.pay.buyManager.APPayManager;
import com.pay.common.tool.APLog;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.buyInfo.APBuyGoodsInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APNetworkManager;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.network.modle.APGetExpressPayAns;
import com.pay.network.modle.APSaveAns;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APPassWordTools.passChannel;
import com.pay.ui.common.APActivity;
import com.pay.ui.common.APAlertDialog;
import com.pay.ui.common.APAlertDialog.Builder;
import com.pay.ui.common.APPayVerifyCodeActivity;
import com.pay.ui.common.APUICommonMethod;
import com.pay.ui.payExpress.APPayExpressActivity;
import java.util.ArrayList;
import java.util.Collections;

public class APRecoChannelActivity extends APActivity
  implements IAPHttpAnsObserver
{
  protected APPayManager payManager;
  protected String vc = "";
  protected String vs = "";

  private void a()
  {
    startActivity(new Intent(this, APChannelActivity.class));
    overridePendingTransition(APCommMethod.getAnimId(this, "unipay_anim_in_from_right"), APCommMethod.getAnimId(this, "unipay_anim_out_to_left"));
  }

  private boolean b()
  {
    try
    {
      int i6 = Integer.valueOf(this.orderInfo.saveNum).intValue();
      i = i6;
      if (this.saveType != 0);
    }
    catch (Exception localException1)
    {
      while (true)
      {
        int i;
        StringBuffer localStringBuffer;
        String str1;
        ArrayList localArrayList;
        int m;
        int i1;
        String str4;
        try
        {
          int i5 = Integer.valueOf(this.orderInfo.buyInfo.price).intValue();
          j = i * 100 / i5;
          localStringBuffer = new StringBuffer();
          str1 = String.valueOf(j);
          int k = APHFAmountList.singleton().getHFAmountList().size();
          localArrayList = new ArrayList();
          localArrayList.clear();
          m = 0;
          if (m < k)
            break label328;
          Collections.sort(localArrayList);
          i1 = 0;
          if (i1 < localArrayList.size())
            break label391;
          if (localStringBuffer.length() <= 0)
            continue;
          localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
          String str3 = localStringBuffer.toString();
          if ((str3 == null) || (str3.equals("")))
            break label457;
          str4 = "话费支付仅支持以下金额(元):\n" + str3;
          showMessageDialog(str4);
          return false;
          localException1 = localException1;
          i = 0;
        }
        catch (Exception localException4)
        {
          j = 0;
          continue;
        }
        if (this.saveType == 1)
        {
          try
          {
            APBuyGoodsInfo localAPBuyGoodsInfo = (APBuyGoodsInfo)this.orderInfo.buyInfo;
            if (localAPBuyGoodsInfo.disPrice.length() != 0)
            {
              j = i * Integer.valueOf(localAPBuyGoodsInfo.disPrice).intValue();
              continue;
            }
            int i4 = Integer.valueOf(localAPBuyGoodsInfo.price).intValue();
            j = i4 * i;
          }
          catch (Exception localException3)
          {
            j = 0;
          }
          continue;
        }
        if (this.saveType == 2)
        {
          j = i * 10;
          continue;
        }
        if (this.saveType == 3)
        {
          j = i * 100;
          continue;
        }
        if ((this.saveType == 4) || (this.saveType == 5))
        {
          return true;
          label328: String str2 = (String)APHFAmountList.singleton().getHFAmountList().get(m);
          try
          {
            int n = Integer.valueOf(str2).intValue() / 100;
            if (n != 0)
              localArrayList.add(Integer.valueOf(n));
            if (str2.equals(str1))
              return true;
            m++;
            continue;
            label391: int i2 = ((Integer)localArrayList.get(i1)).intValue();
            if (i1 > 0)
            {
              i3 = ((Integer)localArrayList.get(i1 - 1)).intValue();
              if (i2 != i3)
              {
                localStringBuffer.append(i2);
                localStringBuffer.append("，");
              }
              i1++;
              continue;
              label457: str4 = "当前金额暂时不支持话费支付";
            }
          }
          catch (Exception localException2)
          {
            while (true)
            {
              continue;
              int i3 = 0;
            }
          }
        }
        int j = 0;
      }
    }
  }

  protected void accountPay(int paramInt1, int paramInt2)
  {
    this.payManager = new APPayManager(this, this);
    this.payManager.toAccountPay(paramInt1, paramInt2);
  }

  protected void bankPay(int paramInt)
  {
    this.payManager = new APPayManager(this, this);
    this.payManager.toBankPay(paramInt);
  }

  protected void doMCardPay()
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("saveType", this.saveType);
    localIntent.setClass(this, APMCardPayActivity.class);
    startActivity(localIntent);
    overridePendingTransition(APCommMethod.getAnimId(this, "unipay_anim_in_from_right"), APCommMethod.getAnimId(this, "unipay_anim_out_to_left"));
  }

  protected void doPay()
  {
  }

  protected void doQQCardPay()
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("saveType", this.saveType);
    localIntent.setClass(this, APQCardPayActivity.class);
    startActivity(localIntent);
    overridePendingTransition(APCommMethod.getAnimId(this, "unipay_anim_in_from_right"), APCommMethod.getAnimId(this, "unipay_anim_out_to_left"));
  }

  protected void dohfPay()
  {
    if (!b())
      return;
    Intent localIntent = new Intent(this, APHFPayActivity.class);
    localIntent.putExtra("saveType", this.saveType);
    startActivity(localIntent);
    overridePendingTransition(APCommMethod.getAnimId(this, "unipay_anim_in_from_right"), APCommMethod.getAnimId(this, "unipay_anim_out_to_left"));
  }

  protected void goldCouponsPay(int paramInt)
  {
    this.payManager = new APPayManager(this, this);
    this.payManager.goldCouponsPay(paramInt);
  }

  protected void hfPay(String paramString1, String paramString2, String paramString3, String paramString4, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    this.payManager = new APPayManager(this, paramIAPHttpAnsObserver);
    this.payManager.toHFPay(this.saveType, paramString1, paramString2, paramString3, paramString4);
  }

  protected void kjPay(int paramInt)
  {
    this.payManager = new APPayManager(this, this);
    this.payManager.toKjPay(paramInt);
  }

  protected void mCardPay(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    this.payManager = new APPayManager(this, this);
    this.payManager.toMCardPay(this.saveType, paramString1, paramString2, paramInt1, paramInt2, this.vc, this.vs);
    this.payManager.setVerifyCode("", "");
    this.vs = "";
    this.vc = "";
  }

  public void needVerifyCode(boolean paramBoolean, String paramString1, String paramString2, String paramString3)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("tittle", "请输入验证码");
    localBundle.putString("vc", paramString2);
    localBundle.putString("vs", paramString3);
    localBundle.putString("pay_method", paramString1);
    localBundle.putBoolean("vcerror", paramBoolean);
    Intent localIntent = new Intent();
    localIntent.putExtras(localBundle);
    localIntent.setClass(this, APPayVerifyCodeActivity.class);
    startActivityForResult(localIntent, 10002);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    APLog.i("APRecoChannleActivity", "req = " + paramInt1 + "ret" + paramInt2);
    switch (paramInt1)
    {
    default:
    case 100001:
    case 10002:
    }
    label169: label179: 
    do
      while (true)
      {
        return;
        if (paramInt2 != 10)
          continue;
        APPassWordTools.passChannel localpassChannel = (APPassWordTools.passChannel)paramIntent.getSerializableExtra("channel");
        String str = paramIntent.getStringExtra("sig");
        if (str.length() > 15)
        {
          APDataInterface.singleton().setMbSig(str);
          if (localpassChannel != APPassWordTools.passChannel.Sms)
            break label169;
          this.payManager.setMbType("2");
          APDataInterface.singleton().setMbSig(str);
        }
        while (true)
        {
          if (APDataInterface.singleton().getMbSig().equals(""))
            break label179;
          this.payManager.doSave(this.saveType);
          return;
          APDataInterface.singleton().setMbSig("");
          break;
          this.payManager.setMbType("");
        }
      }
    while (paramIntent == null);
    Bundle localBundle = paramIntent.getExtras();
    this.payManager.setVerifyCode(localBundle.getString("vc"), localBundle.getString("vs"));
    this.payManager.doSave(this.saveType);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((!APDataInterface.singleton().getDataValid()) || (AndroidPay.singleton().applicationContext == null))
      return;
    this.waitDialog.setOnCancelListener(new K(this));
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public void onError(APBaseHttpAns paramAPBaseHttpAns)
  {
    APUICommonMethod.dismissWaitDialog();
    if (paramAPBaseHttpAns.getHttpReqKey().equals("getexpress"))
    {
      a();
      return;
    }
    APUICommonMethod.showToast(this, paramAPBaseHttpAns.getResultMessage());
  }

  public void onFinish(APBaseHttpAns paramAPBaseHttpAns)
  {
    String str;
    if (paramAPBaseHttpAns.getHttpReqKey().equals("getexpress"))
    {
      APGetExpressPayAns localAPGetExpressPayAns = (APGetExpressPayAns)paramAPBaseHttpAns;
      int j = localAPGetExpressPayAns.getResultCode();
      str = localAPGetExpressPayAns.getExpress();
      switch (j)
      {
      default:
        if ((str != null) && (str.length() != 0))
          break;
        APUICommonMethod.dismissWaitDialog();
        a();
      case 1094:
      case 1099:
      case 0:
      }
    }
    do
    {
      return;
      if ((str.equals("qdqb")) && (!AndroidPay.singleton().IsNeedUinLogin()))
      {
        APUICommonMethod.dismissWaitDialog();
        this.orderInfo.expressChannel = "qdqb";
        Intent localIntent4 = new Intent(this, APPayExpressActivity.class);
        Bundle localBundle4 = new Bundle();
        localBundle4.putInt("subChannel", 0);
        localIntent4.putExtras(localBundle4);
        startActivity(localIntent4);
        finish();
        return;
      }
      if ((str.equals("qbqd")) && (!AndroidPay.singleton().IsNeedUinLogin()))
      {
        APUICommonMethod.dismissWaitDialog();
        this.orderInfo.expressChannel = "qbqd";
        Intent localIntent3 = new Intent(this, APPayExpressActivity.class);
        Bundle localBundle3 = new Bundle();
        localBundle3.putInt("subChannel", 11);
        localIntent3.putExtras(localBundle3);
        startActivity(localIntent3);
        finish();
        return;
      }
      if ((str.equals("cft")) && (!AndroidPay.singleton().IsNeedUinLogin()))
      {
        this.orderInfo.expressChannel = "cft";
        tenpayPay(this.saveType);
        return;
      }
      if ((str.equals("bank")) && (!AndroidPay.singleton().IsNeedUinLogin()))
      {
        this.orderInfo.expressChannel = "bank";
        bankPay(this.saveType);
        return;
      }
      APUICommonMethod.dismissWaitDialog();
      a();
      return;
    }
    while (!paramAPBaseHttpAns.getHttpReqKey().equals("save"));
    int i = paramAPBaseHttpAns.getResultCode();
    if (this.payManager != null)
      this.payManager.progressPayManagerAns(paramAPBaseHttpAns);
    APSaveAns localAPSaveAns = (APSaveAns)paramAPBaseHttpAns;
    switch (i)
    {
    default:
      return;
    case 10002:
      Bundle localBundle2 = new Bundle();
      localBundle2.putString("tittle", "请输入验证码");
      localBundle2.putString("vc", localAPSaveAns.getVerifyCode());
      localBundle2.putString("vs", localAPSaveAns.getVerifySession());
      localBundle2.putString("pay_method", this.payManager.getCurrentChannel());
      localBundle2.putBoolean("vcerror", false);
      Intent localIntent2 = new Intent();
      localIntent2.putExtras(localBundle2);
      localIntent2.setClass(this, APPayVerifyCodeActivity.class);
      startActivityForResult(localIntent2, 10002);
      return;
    case 10006:
    }
    Bundle localBundle1 = new Bundle();
    localBundle1.putString("tittle", "请输入验证码");
    localBundle1.putString("vc", localAPSaveAns.getVerifyCode());
    localBundle1.putString("vs", localAPSaveAns.getVerifySession());
    localBundle1.putString("pay_method", this.payManager.getCurrentChannel());
    localBundle1.putBoolean("vcerror", true);
    Intent localIntent1 = new Intent();
    localIntent1.putExtras(localBundle1);
    localIntent1.setClass(this, APPayVerifyCodeActivity.class);
    startActivityForResult(localIntent1, 10002);
  }

  public void onStop(APBaseHttpAns paramAPBaseHttpAns)
  {
    APUICommonMethod.dismissWaitDialog();
  }

  protected void payAutoSelect()
  {
    String str = APDataInterface.singleton().getPayAssignChannel();
    APPayManager localAPPayManager = new APPayManager(this, this);
    int i = 0;
    if (str != null)
    {
      boolean bool1 = str.equals("");
      i = 0;
      if (!bool1)
        break label43;
    }
    while (i != 0)
    {
      return;
      label43: if ((this.saveType == 3) || (this.saveType == 2))
      {
        boolean bool2 = str.equals("qdqb");
        i = 0;
        if (bool2)
          continue;
        boolean bool3 = str.equals("qbqd");
        i = 0;
        if (bool3)
          continue;
      }
      if (str.equals("bank"))
      {
        localAPPayManager.toBankPay(this.saveType);
        i = 1;
        continue;
      }
      boolean bool4 = str.equals("wechat");
      i = 0;
      if (!bool4)
        continue;
      localAPPayManager.toWeChatPay(this.saveType);
      i = 1;
    }
    APUICommonMethod.showWaitDialog(this, null);
    APNetworkManager.getInstance().getExpress(this.saveType, this);
  }

  protected void qCardPay(String paramString1, String paramString2, String paramString3)
  {
    this.payManager = new APPayManager(this, this);
    this.payManager.toQQCardPay(this.saveType, paramString1, paramString2, paramString3, this.vc, this.vs);
    this.payManager.setVerifyCode("", "");
    this.vs = "";
    this.vc = "";
  }

  protected void showMessageDialog(String paramString)
  {
    APAlertDialog.Builder localBuilder = new APAlertDialog.Builder(this);
    localBuilder.setTitle("温馨提示");
    localBuilder.setMessage(paramString);
    localBuilder.setNeutralButton("确定", new L(this));
    APAlertDialog localAPAlertDialog = localBuilder.create();
    if (localAPAlertDialog == null)
      return;
    localAPAlertDialog.show();
  }

  protected void tenpayPay(int paramInt)
  {
    this.payManager = new APPayManager(this, this);
    this.payManager.toTenpayPay(paramInt);
  }

  protected void weChatPay(int paramInt)
  {
    this.payManager = new APPayManager(this, this);
    this.payManager.toWeChatPay(paramInt);
  }

  protected void ybPay(int paramInt)
  {
    this.payManager = new APPayManager(this, this);
    this.payManager.toYbPay(paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.APRecoChannelActivity
 * JD-Core Version:    0.6.0
 */