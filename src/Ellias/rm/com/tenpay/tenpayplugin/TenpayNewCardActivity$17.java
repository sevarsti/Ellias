package com.tenpay.tenpayplugin;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import com.tenpay.tenpayplugin.view.ClearableEditText;

class TenpayNewCardActivity$17
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    int i = 8;
    int j = paramView.getId();
    if (j == TenpayResourceUtil.getId(this.a, "bin_keyboard_1"));
    while (true)
    {
      if (TenpayNewCardActivity.H(this.a) != null)
      {
        TenpayNewCardActivity.H(this.a).dispatchKeyEvent(new KeyEvent(0, i));
        TenpayNewCardActivity.H(this.a).dispatchKeyEvent(new KeyEvent(1, 59));
      }
      if (TenpayNewCardActivity.L(this.a) == TenpayNewCardActivity.F(this.a))
      {
        TenpayNewCardActivity.F(this.a).dispatchKeyEvent(new KeyEvent(0, i));
        TenpayNewCardActivity.F(this.a).dispatchKeyEvent(new KeyEvent(1, 59));
        return;
        if (j == TenpayResourceUtil.getId(this.a, "bin_keyboard_2"))
        {
          i = 9;
          continue;
        }
        if (j == TenpayResourceUtil.getId(this.a, "bin_keyboard_3"))
        {
          i = 10;
          continue;
        }
        if (j == TenpayResourceUtil.getId(this.a, "bin_keyboard_4"))
        {
          i = 11;
          continue;
        }
        if (j == TenpayResourceUtil.getId(this.a, "bin_keyboard_5"))
        {
          i = 12;
          continue;
        }
        if (j == TenpayResourceUtil.getId(this.a, "bin_keyboard_6"))
        {
          i = 13;
          continue;
        }
        if (j == TenpayResourceUtil.getId(this.a, "bin_keyboard_7"))
        {
          i = 14;
          continue;
        }
        if (j == TenpayResourceUtil.getId(this.a, "bin_keyboard_8"))
        {
          i = 15;
          continue;
        }
        if (j == TenpayResourceUtil.getId(this.a, "bin_keyboard_9"))
        {
          i = 16;
          continue;
        }
        if (j == TenpayResourceUtil.getId(this.a, "bin_keyboard_x"))
        {
          if (TenpayNewCardActivity.L(this.a) == TenpayNewCardActivity.F(this.a))
          {
            TenpayNewCardActivity.F(this.a).dispatchKeyEvent(new KeyEvent(0, 59));
            if (TenpayNewCardActivity.H(this.a) != null)
              TenpayNewCardActivity.H(this.a).dispatchKeyEvent(new KeyEvent(0, 59));
            i = 52;
            continue;
          }
          if (TenpayNewCardActivity.J(this.a) != null)
            TenpayNewCardActivity.J(this.a).setVisibility(i);
          TenpayNewCardActivity.K(this.a).setVisibility(i);
          return;
        }
        if (j == TenpayResourceUtil.getId(this.a, "bin_keyboard_0"))
        {
          i = 7;
          continue;
        }
        if (j == TenpayResourceUtil.getId(this.a, "bin_keyboard_d"))
        {
          i = 67;
          continue;
        }
      }
      else
      {
        TenpayNewCardActivity.f(this.a).dispatchKeyEvent(new KeyEvent(0, i));
        return;
      }
      i = 54;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.17
 * JD-Core Version:    0.6.0
 */