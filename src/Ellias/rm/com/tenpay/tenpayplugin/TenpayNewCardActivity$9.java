package com.tenpay.tenpayplugin;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.tenpay.tenpayplugin.view.ValidDateEdit;

class TenpayNewCardActivity$9
  implements View.OnFocusChangeListener
{
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      Drawable localDrawable = this.a.getResources().getDrawable(TenpayResourceUtil.getDrawableId(this.a, "unipay_tenpay_year"));
      localDrawable.setBounds(0, 0, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
      TenpayNewCardActivity.v(this.a).setCompoundDrawables(TenpayNewCardActivity.v(this.a).getCompoundDrawables()[0], TenpayNewCardActivity.v(this.a).getCompoundDrawables()[1], localDrawable, TenpayNewCardActivity.v(this.a).getCompoundDrawables()[3]);
      TenpayNewCardActivity.v(this.a).setCompoundDrawablePadding(3);
      return;
    }
    TenpayNewCardActivity.v(this.a).setCompoundDrawables(TenpayNewCardActivity.v(this.a).getCompoundDrawables()[0], TenpayNewCardActivity.v(this.a).getCompoundDrawables()[1], null, TenpayNewCardActivity.v(this.a).getCompoundDrawables()[3]);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.9
 * JD-Core Version:    0.6.0
 */