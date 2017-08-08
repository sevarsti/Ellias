package com.pay.ui.channel;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ImageButton;
import com.pay.tool.APCommMethod;
import com.pay.ui.common.APScrollView;

final class z
  implements View.OnFocusChangeListener
{
  z(APMCardPayActivity paramAPMCardPayActivity, APScrollView paramAPScrollView)
  {
  }

  public final void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.b.smoothScrollTo(0, 390);
      this.a.editLight(APCommMethod.getId(this.a, "unipay_id_TelCardPWDLayout"));
      return;
    }
    this.a.editNotLight(APCommMethod.getId(this.a, "unipay_id_TelCardPWDLayout"));
    ((ImageButton)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_CardPWDDel"))).setVisibility(8);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.z
 * JD-Core Version:    0.6.0
 */