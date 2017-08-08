package com.pay.ui.channel;

import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import com.pay.tool.APCommMethod;

final class g
  implements View.OnTouchListener
{
  g(APHFPayActivity paramAPHFPayActivity)
  {
  }

  public final boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (APHFPayActivity.a(this.a).getText().toString().length() > 0)
    {
      APHFPayActivity.u(this.a).setVisibility(0);
      APHFPayActivity.a(this.a).setCursorVisible(true);
      this.a.editLight(APCommMethod.getId(this.a, "unipay_id_apMobileNumLayout"));
      return false;
    }
    APHFPayActivity.u(this.a).setVisibility(8);
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.g
 * JD-Core Version:    0.6.0
 */