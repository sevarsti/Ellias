package com.pay.ui.payCenter;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.data.buyInfo.APBuyMonthInfo;
import com.pay.data.mp.APMPGamesItem;
import com.pay.data.mp.APMPSendInfo;
import com.pay.data.mp.APMPSendItem;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.APMonthDataInterface;
import com.pay.tool.APMonthDataInterface.MonthOpenType;
import java.util.ArrayList;

final class m
  implements View.OnClickListener
{
  m(APPayGameListNumActivity paramAPPayGameListNumActivity, boolean paramBoolean)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.gamelist.click", APPayGameListNumActivity.a(this.a), null, paramView.getTag().toString(), null);
    Integer localInteger = (Integer)paramView.getTag();
    if (this.b)
    {
      int j = ((APMPSendItem)APMPSendInfo.getInstance().getUptoNumMpMpInfo().get(localInteger.intValue())).sendGames.limitNum;
      APPayGameListNumActivity.b(this.a).saveNum = String.valueOf(j);
    }
    while (true)
    {
      this.a.dopay();
      return;
      int i = APDataInterface.singleton().getOrderInfo().saveType;
      if (((i == 4) || (i == 5)) && (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate))
      {
        APPayGameListNumActivity.b(this.a).saveNum = "1";
        APPayGameListNumActivity.b(this.a).buyInfo.price = APPayGameListNumActivity.c(this.a)[localInteger.intValue()];
        ((APBuyMonthInfo)APPayGameListNumActivity.b(this.a).buyInfo).productId = APSaveValueList.singleton().getSaveProduct()[localInteger.intValue()];
        APMonthDataInterface.singleton().setUnit(APPayGameListNumActivity.d(this.a)[localInteger.intValue()]);
        continue;
      }
      APPayGameListNumActivity.b(this.a).saveNum = APPayGameListNumActivity.e(this.a)[localInteger.intValue()];
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payCenter.m
 * JD-Core Version:    0.6.0
 */