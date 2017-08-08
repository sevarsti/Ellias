package com.pay.ui.channel;

import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import com.pay.tool.APCommMethod;

final class w
  implements View.OnTouchListener
{
  w(APMCardPayActivity paramAPMCardPayActivity)
  {
  }

  public final boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (this.a.a.getText().toString().length() > 0)
      ((ImageButton)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_CardNumDel"))).setVisibility(0);
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.w
 * JD-Core Version:    0.6.0
 */