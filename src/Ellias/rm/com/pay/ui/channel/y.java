package com.pay.ui.channel;

import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import com.pay.tool.APCommMethod;

final class y
  implements View.OnTouchListener
{
  y(APMCardPayActivity paramAPMCardPayActivity)
  {
  }

  public final boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (this.a.b.getText().toString().length() > 0)
      ((ImageButton)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_CardPWDDel"))).setVisibility(0);
    this.a.b.setCursorVisible(true);
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.y
 * JD-Core Version:    0.6.0
 */