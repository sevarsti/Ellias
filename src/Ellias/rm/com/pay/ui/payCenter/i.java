package com.pay.ui.payCenter;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataReportManager;
import com.pay.ui.common.APUICommonMethod;

final class i
  implements View.OnClickListener
{
  i(APPayGameListNumActivity paramAPPayGameListNumActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.gamelist.close", APPayGameListNumActivity.a(this.a));
    APUICommonMethod.popActivity();
    APCommMethod.payErrorCallBack(2, "");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payCenter.i
 * JD-Core Version:    0.6.0
 */