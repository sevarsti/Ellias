package com.tenpay.tenpayplugin.view;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class ClearableEditText$3
  implements View.OnTouchListener
{
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    ClearableEditText localClearableEditText = this.a;
    if (localClearableEditText.getCompoundDrawables()[2] == null);
    do
      return false;
    while ((paramMotionEvent.getAction() != 0) || (paramMotionEvent.getX() <= localClearableEditText.getWidth() - localClearableEditText.getPaddingRight() - this.a.a.getIntrinsicWidth()));
    localClearableEditText.setText("");
    this.a.removeClearButton();
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.ClearableEditText.3
 * JD-Core Version:    0.6.0
 */