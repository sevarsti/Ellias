package com.pay.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;

class APLoginVerifyCodeActivity$3
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.loginvc.sure", APDataInterface.singleton().getOrderInfo().saveType);
    if (APLoginVerifyCodeActivity.b(this.a))
    {
      String str = APLoginVerifyCodeActivity.c(this.a).getText().toString().trim();
      Intent localIntent = this.a.getIntent();
      Bundle localBundle = new Bundle();
      localBundle.putInt("ret", 0);
      localBundle.putString("vc", str);
      localIntent.putExtras(localBundle);
      this.a.setResult(10002, localIntent);
      this.a.finish();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.login.APLoginVerifyCodeActivity.3
 * JD-Core Version:    0.6.0
 */