package com.pay;

import android.content.Context;
import android.text.TextUtils;
import com.pay.buyManager.APGetKeyManager;
import com.pay.buyManager.APLanuchPayManager;
import com.pay.data.buyInfo.APBuyMonthInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APNetworkManager;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.network.modle.APGoodsTokenAns;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.APMonthDataInterface;
import com.pay.tool.APMonthDataInterface.MonthOpenType;
import com.pay.ui.common.APAlertDialog;
import com.pay.ui.common.APAlertDialog.Builder;
import com.pay.ui.common.APUICommonMethod;
import com.pay.ui.payCenter.APSaveValueList;

public class APBuyPage
  implements IAPHttpAnsObserver
{
  private Context a = null;
  private int b;
  private String c;
  private String d;
  private String e;
  private boolean f;
  private long g;
  private long h;
  private AndroidPay.APLaunchRootViewOption i;

  public APBuyPage(Context paramContext, AndroidPay.APLaunchRootViewOption paramAPLaunchRootViewOption)
  {
    this.a = paramContext;
    this.i = paramAPLaunchRootViewOption;
  }

  private void a()
  {
    String str = "正在进入安全支付环境";
    if (APAppDataInterface.singleton().getEnv().equals("test"))
      str = "正在进入沙箱支付环境\n版本号:" + APCommMethod.getVersion();
    APDataInterface.singleton().setSourceActivity("AndroidPay");
    APDataInterface.singleton().setDataValid(true);
    APUICommonMethod.showWaitDialog(this.a, str);
  }

  private void a(String paramString)
  {
    String str = APCommMethod.getStringId(this.a, paramString);
    APUICommonMethod.dismissWaitDialog();
    APUICommonMethod.showToast(this.a, str);
    APCommMethod.payErrorCallBack(-1, str);
  }

  private void a(String paramString1, String paramString2, boolean paramBoolean)
  {
    APNetworkManager.getInstance().goodsToken(paramString1, paramString2, paramBoolean, this);
  }

  private void b()
  {
    new APGetKeyManager(this.a).changeKey(new a(this));
  }

  private void c()
  {
    if (AndroidPay.singleton().isValidPayChannelAndMarket())
      APDataInterface.singleton().getOrderInfo().isNumCanChange = false;
    new APLanuchPayManager().LanuchPay(this.a, this.i);
  }

  public void buyInfo()
  {
    this.h = System.currentTimeMillis();
    switch (this.b)
    {
    case 2:
    default:
      return;
    case 0:
      APNetworkManager.getInstance().mobileBuyPage(this);
      return;
    case 1:
      APNetworkManager.getInstance().mobileBuyGoods(this.c, this);
      return;
    case 4:
    case 5:
      APBuyMonthInfo localAPBuyMonthInfo = (APBuyMonthInfo)APDataInterface.singleton().getOrderInfo().buyInfo;
      APNetworkManager.getInstance().mobileMonthInfo(localAPBuyMonthInfo.serviceCode, this);
      return;
    case 3:
    }
    APNetworkManager.getInstance().mobileBuyPage(this);
  }

  public void getBuyInfo(int paramInt, String paramString)
  {
    this.b = paramInt;
    this.c = paramString;
    a();
    if ((APAppDataInterface.singleton().getSecretKey().length() <= 0) || (APAppDataInterface.singleton().getCryptoKey().length() <= 0))
    {
      b();
      return;
    }
    buyInfo();
  }

  public void getGoodsInfo(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    this.b = paramInt;
    this.d = paramString1;
    this.e = paramString2;
    this.f = paramBoolean;
    a();
    if ((APAppDataInterface.singleton().getSecretKey().length() <= 0) || (APAppDataInterface.singleton().getCryptoKey().length() <= 0))
    {
      b();
      return;
    }
    a(paramString1, paramString2, paramBoolean);
  }

  public void onError(APBaseHttpAns paramAPBaseHttpAns)
  {
    APUICommonMethod.dismissWaitDialog();
    APUICommonMethod.showToast(this.a, paramAPBaseHttpAns.getErrorMessage());
    APCommMethod.payErrorCallBack(-1, paramAPBaseHttpAns.getResultMessage());
    this.g = System.currentTimeMillis();
    long l = this.g - this.h;
    APDataReportManager.getInstance().insertData("sdk.cgi.mobilebuygoods.failure", this.b, null, null, String.valueOf(l));
  }

  public void onFinish(APBaseHttpAns paramAPBaseHttpAns)
  {
    this.g = System.currentTimeMillis();
    long l = this.g - this.h;
    APDataReportManager.getInstance().insertData("sdk.cgi.mobilebuypage.sucess", this.b, null, null, String.valueOf(l));
    switch (paramAPBaseHttpAns.getResultCode())
    {
    default:
      APUICommonMethod.dismissWaitDialog();
      String str2 = paramAPBaseHttpAns.getResultMessage();
      APUICommonMethod.showToast(this.a, str2);
      APCommMethod.payErrorCallBack(-1, str2);
    case 1094:
    case 1099:
      return;
    case 0:
      switch (this.b)
      {
      case 2:
      default:
        return;
      case 0:
        c();
        return;
      case 4:
      case 5:
        APOrderInfo localAPOrderInfo = APDataInterface.singleton().getOrderInfo();
        if ((APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate) && (!TextUtils.isEmpty(localAPOrderInfo.saveNum)))
        {
          String str1 = ((APBuyMonthInfo)APDataInterface.singleton().getOrderInfo().buyInfo).productId;
          String[] arrayOfString1;
          String[] arrayOfString2;
          String[] arrayOfString3;
          if ((str1 != null) && (!str1.equals("")))
          {
            arrayOfString1 = APSaveValueList.singleton().getSaveMoney();
            arrayOfString2 = APSaveValueList.singleton().getSaveProduct();
            arrayOfString3 = APSaveValueList.singleton().getSaveName();
          }
          for (int k = 0; ; k++)
          {
            int m = arrayOfString2.length;
            int j = 0;
            if (k >= m);
            while (true)
            {
              if (j != 0)
                break label329;
              a("unipay_entry_pidError");
              return;
              if (!arrayOfString2[k].equals(str1))
                break;
              ((APBuyMonthInfo)APDataInterface.singleton().getOrderInfo().buyInfo).price = arrayOfString1[k];
              APMonthDataInterface.singleton().setUnit(arrayOfString3[k]);
              j = 1;
            }
          }
          if (localAPOrderInfo.isNumCanChange)
          {
            a("unipay_entry_disableChange");
            return;
          }
          if (!localAPOrderInfo.isNumCanChange)
          {
            if ((localAPOrderInfo.saveNum != null) && (!localAPOrderInfo.saveNum.equals("1")))
            {
              a("unipay_entry_numberIs1");
              return;
            }
            if (((APBuyMonthInfo)localAPOrderInfo.buyInfo).productId.equals(""))
            {
              a("unipay_entry_productNull");
              return;
            }
          }
        }
        c();
        return;
      case 3:
        label329: c();
        return;
      case 1:
      }
      if (paramAPBaseHttpAns.getHttpReqKey().equals("goodsToken"))
      {
        this.c = ((APGoodsTokenAns)paramAPBaseHttpAns).getGoodsTokenUrl();
        buyInfo();
        return;
      }
      c();
      return;
    case 1018:
    }
    APUICommonMethod.dismissWaitDialog();
    showLoginErrorAlert("登录过期，请重新登录");
  }

  public void onStop(APBaseHttpAns paramAPBaseHttpAns)
  {
    APUICommonMethod.dismissWaitDialog();
  }

  protected void showLoginErrorAlert(String paramString)
  {
    APAlertDialog.Builder localBuilder = new APAlertDialog.Builder(this.a);
    localBuilder.setTitle("温馨提示");
    localBuilder.setMessage(paramString);
    localBuilder.setPositiveButton("确定", new b(this));
    APAlertDialog localAPAlertDialog = localBuilder.create();
    if (localAPAlertDialog == null)
      return;
    localAPAlertDialog.setOnKeyListener(new c(this));
    localAPAlertDialog.show();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.APBuyPage
 * JD-Core Version:    0.6.0
 */