package com.tenpay.tenpayplugin.view;

import android.text.Editable;
import android.view.View;
import android.view.View.OnFocusChangeListener;

class ClearableEditText$2
  implements View.OnFocusChangeListener
{
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (!paramBoolean)
      this.a.removeClearButton();
    do
      return;
    while (this.a.getText().toString().equals(""));
    this.a.addClearButton();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.ClearableEditText.2
 * JD-Core Version:    0.6.0
 */