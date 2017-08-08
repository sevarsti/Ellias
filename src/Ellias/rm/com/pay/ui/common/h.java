package com.pay.ui.common;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final class h
  implements View.OnFocusChangeListener
{
  h(APEditText paramAPEditText)
  {
  }

  public final void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.a.setHighLight();
      return;
    }
    this.a.setNotLight();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.h
 * JD-Core Version:    0.6.0
 */