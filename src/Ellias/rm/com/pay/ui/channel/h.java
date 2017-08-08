package com.pay.ui.channel;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ImageButton;
import com.pay.tool.APCommMethod;

final class h
  implements View.OnFocusChangeListener
{
  h(APHFPayActivity paramAPHFPayActivity)
  {
  }

  public final void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.a.editLight(APCommMethod.getId(this.a, "unipay_id_apMobileNumLayout"));
      APHFPayActivity.u(this.a).setVisibility(0);
      return;
    }
    this.a.editNotLight(APCommMethod.getId(this.a, "unipay_id_apMobileNumLayout"));
    ((ImageButton)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_MobileNumDel"))).setVisibility(8);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.h
 * JD-Core Version:    0.6.0
 */