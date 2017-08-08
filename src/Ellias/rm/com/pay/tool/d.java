package com.pay.tool;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;
import java.util.TimerTask;

final class d extends TimerTask
{
  d(Activity paramActivity)
  {
  }

  public final void run()
  {
    ((InputMethodManager)this.a.getSystemService("input_method")).toggleSoftInput(2, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.d
 * JD-Core Version:    0.6.0
 */