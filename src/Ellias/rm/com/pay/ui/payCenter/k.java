package com.pay.ui.payCenter;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.pay.data.buyInfo.APBuyMonthInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.APMonthDataInterface;

final class k
  implements CompoundButton.OnCheckedChangeListener
{
  k(APPayGameListNumActivity paramAPPayGameListNumActivity)
  {
  }

  public final void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    APBuyMonthInfo localAPBuyMonthInfo = (APBuyMonthInfo)APDataInterface.singleton().getOrderInfo().buyInfo;
    if (paramBoolean)
    {
      APDataReportManager.getInstance().insertData("sdk.gamelist.auto", APPayGameListNumActivity.a(this.a), null, "able", null);
      localAPBuyMonthInfo.autoPay = "1";
      APMonthDataInterface.singleton().setAutoPay("1");
      return;
    }
    APDataReportManager.getInstance().insertData("sdk.gamelist.auto", APPayGameListNumActivity.a(this.a), null, "disable", null);
    localAPBuyMonthInfo.autoPay = "0";
    APMonthDataInterface.singleton().setAutoPay("0");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payCenter.k
 * JD-Core Version:    0.6.0
 */