package com.pay.ui.common;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.util.TimerTask;

final class c extends TimerTask
{
  c(APActivity paramAPActivity, InputMethodManager paramInputMethodManager, View paramView)
  {
  }

  public final void run()
  {
    this.a.showSoftInput(this.b, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.c
 * JD-Core Version:    0.6.0
 */