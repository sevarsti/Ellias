package com.pay.ui.channel;

import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.pay.tool.APDataReportManager;

final class v
  implements RadioGroup.OnCheckedChangeListener
{
  v(APMCardPayActivity paramAPMCardPayActivity, int[] paramArrayOfInt)
  {
  }

  public final void onCheckedChanged(RadioGroup paramRadioGroup, int paramInt)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramRadioGroup.getChildCount())
      {
        this.a.refreshNumber(APMCardPayActivity.b(this.a));
        if (!APMCardPayActivity.c(this.a))
        {
          APMCardPayActivity.a(this.a, true);
          this.a.showMCardDialog("所选面值必须和充值卡实际面值一致，以免造成卡内资金损失");
        }
        return;
      }
      if (paramInt != paramRadioGroup.getChildAt(i).getId())
        continue;
      APMCardPayActivity.a(this.a, this.b[i]);
      APDataReportManager.getInstance().insertData("sdk.pcard.click", APMCardPayActivity.a(this.a), null, "f" + String.valueOf(i), null);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.v
 * JD-Core Version:    0.6.0
 */