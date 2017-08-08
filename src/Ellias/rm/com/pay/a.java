package com.pay;

import android.text.TextUtils;
import com.pay.buyManager.IAPGetKeyCallBack;
import com.pay.tool.APCommMethod;
import com.pay.ui.common.APUICommonMethod;

final class a
  implements IAPGetKeyCallBack
{
  a(APBuyPage paramAPBuyPage)
  {
  }

  public final void onGetKeyCancel()
  {
    APUICommonMethod.popActivity();
    APCommMethod.payErrorCallBack(2, "");
  }

  public final void onGetKeyFail(int paramInt, String paramString)
  {
    if (paramInt == 1100)
      APUICommonMethod.showToast(APBuyPage.e(this.a), "您的系统时间不正确，请修改");
    APUICommonMethod.popActivity();
    APCommMethod.payErrorCallBack(paramInt, paramString);
  }

  public final void onGetKeySucc(String paramString)
  {
    if ((APBuyPage.a(this.a) == 1) && (!TextUtils.isEmpty(APBuyPage.b(this.a))))
    {
      APBuyPage.a(this.a, APBuyPage.b(this.a), APBuyPage.c(this.a), APBuyPage.d(this.a));
      return;
    }
    this.a.buyInfo();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.a
 * JD-Core Version:    0.6.0
 */