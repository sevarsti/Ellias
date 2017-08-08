package com.pay.ui.channel;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ImageButton;
import com.pay.tool.APCommMethod;

final class x
  implements View.OnFocusChangeListener
{
  x(APMCardPayActivity paramAPMCardPayActivity)
  {
  }

  public final void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      APMCardPayActivity.d(this.a);
      this.a.editLight(APCommMethod.getId(this.a, "unipay_id_TelCardNumLayout"));
      return;
    }
    this.a.editNotLight(APCommMethod.getId(this.a, "unipay_id_TelCardNumLayout"));
    ((ImageButton)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_CardNumDel"))).setVisibility(8);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.x
 * JD-Core Version:    0.6.0
 */