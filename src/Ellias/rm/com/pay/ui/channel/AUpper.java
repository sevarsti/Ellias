package com.pay.ui.channel;

import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import com.pay.tool.APCommMethod;

final class AUpper
  implements View.OnTouchListener
{
  AUpper(APQCardPayActivity paramAPQCardPayActivity)
  {
  }

  public final boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (APQCardPayActivity.a(this.a).getText().toString().length() > 0)
      ((ImageButton)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_CardPWDDel"))).setVisibility(0);
    return false;
  }
}