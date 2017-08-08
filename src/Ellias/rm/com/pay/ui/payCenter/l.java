package com.pay.ui.payCenter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.pay.common.tool.APLog;
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

final class l
  implements AdapterView.OnItemClickListener
{
  l(APPayGameListNumActivity paramAPPayGameListNumActivity)
  {
  }

  public final void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    APDataReportManager.getInstance().insertData("sdk.gamelist.click", APPayGameListNumActivity.a(this.a), null, "a" + String.valueOf(paramInt), null);
    if ((APMPSendInfo.getInstance().getIsHasUptoNumMpMPInfo()) && (APPayGameListNumActivity.b(this.a).saveType == 0))
    {
      int j = ((APMPSendItem)APMPSendInfo.getInstance().getUptoNumMpMpInfo().get(paramInt)).sendGames.limitNum;
      APLog.i("APPayGameListNumActivity", "list select value" + j);
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
        APPayGameListNumActivity.b(this.a).buyInfo.price = APPayGameListNumActivity.c(this.a)[paramInt];
        ((APBuyMonthInfo)APPayGameListNumActivity.b(this.a).buyInfo).productId = APSaveValueList.singleton().getSaveProduct()[paramInt];
        APMonthDataInterface.singleton().setUnit(APPayGameListNumActivity.d(this.a)[paramInt]);
        continue;
      }
      APPayGameListNumActivity.b(this.a).saveNum = APPayGameListNumActivity.e(this.a)[paramInt];
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payCenter.l
 * JD-Core Version:    0.6.0
 */