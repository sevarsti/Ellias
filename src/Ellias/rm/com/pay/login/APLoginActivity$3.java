package com.pay.login;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.ui.common.APUICommonMethod;

class APLoginActivity$3
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (APLoginActivity.a(this.a).isChecked())
    {
      int j = APCommMethod.getDrawableId(this.a, "unipay_pic_iconlock");
      APUICommonMethod.showToast(this.a, "记住密码", j);
      APDataReportManager.getInstance().insertData("sdk.login.savepwd", APDataInterface.singleton().getOrderInfo().saveType, null, String.valueOf(APLoginActivity.b(this.a)), null);
    }
    while (true)
    {
      APLoginActivity.a(this.a, APLoginActivity.a(this.a).isChecked());
      return;
      int i = APCommMethod.getDrawableId(this.a, "unipay_pic_iconunlock");
      APUICommonMethod.showToast(this.a, "不记住密码", i);
      APLoginActivity.c(this.a).clearLoginData();
      APDataReportManager.getInstance().insertData("sdk.login.nosavepwd", APDataInterface.singleton().getOrderInfo().saveType, null, String.valueOf(APLoginActivity.b(this.a)), null);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.login.APLoginActivity.3
 * JD-Core Version:    0.6.0
 */