package com.tenpay.tenpayplugin;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

class TenpayNewCardActivity$12
  implements View.OnFocusChangeListener
{
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      Drawable localDrawable = this.a.getResources().getDrawable(TenpayResourceUtil.getDrawableId(this.a, "unipay_tenpay_ccv"));
      localDrawable.setBounds(0, 0, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
      TenpayNewCardActivity.u(this.a).setCompoundDrawables(TenpayNewCardActivity.u(this.a).getCompoundDrawables()[0], TenpayNewCardActivity.u(this.a).getCompoundDrawables()[1], localDrawable, TenpayNewCardActivity.u(this.a).getCompoundDrawables()[3]);
      TenpayNewCardActivity.u(this.a).setCompoundDrawablePadding(3);
      return;
    }
    TenpayNewCardActivity.u(this.a).setCompoundDrawables(TenpayNewCardActivity.u(this.a).getCompoundDrawables()[0], TenpayNewCardActivity.u(this.a).getCompoundDrawables()[1], null, TenpayNewCardActivity.u(this.a).getCompoundDrawables()[3]);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.12
 * JD-Core Version:    0.6.0
 */