package com.pay.login;

import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.APTools;

class APLoginActivity$4
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (APLoginActivity.d(this.a))
      APDataReportManager.getInstance().insertData("sdk.login.sure", APDataInterface.singleton().getOrderInfo().saveType, null, String.valueOf(APLoginActivity.b(this.a)), null);
    while (true)
    {
      APLoginActivity.a(this.a, APLoginActivity.e(this.a).getText().toString().trim(), APLoginActivity.f(this.a).getText().toString().trim());
      APTools.saveInfo(this.a, "TencentUnipay", "OPENID", APDataInterface.singleton().getUserInfo().openId);
      return;
      APDataReportManager.getInstance().insertData("sdk.login.pay", APDataInterface.singleton().getOrderInfo().saveType, null, String.valueOf(APLoginActivity.b(this.a)), null);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.login.APLoginActivity.4
 * JD-Core Version:    0.6.0
 */