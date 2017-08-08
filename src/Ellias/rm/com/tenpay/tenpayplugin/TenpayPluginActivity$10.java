package com.tenpay.tenpayplugin;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

class TenpayPluginActivity$10
  implements View.OnFocusChangeListener
{
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      Drawable localDrawable = this.a.getResources().getDrawable(TenpayResourceUtil.getDrawableId(this.a, "unipay_tenpay_ccv"));
      localDrawable.setBounds(0, 0, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
      TenpayPluginActivity.v(this.a).setCompoundDrawables(TenpayPluginActivity.v(this.a).getCompoundDrawables()[0], TenpayPluginActivity.v(this.a).getCompoundDrawables()[1], localDrawable, TenpayPluginActivity.v(this.a).getCompoundDrawables()[3]);
      TenpayPluginActivity.v(this.a).setCompoundDrawablePadding(3);
      return;
    }
    TenpayPluginActivity.v(this.a).setCompoundDrawables(TenpayPluginActivity.v(this.a).getCompoundDrawables()[0], TenpayPluginActivity.v(this.a).getCompoundDrawables()[1], null, TenpayPluginActivity.v(this.a).getCompoundDrawables()[3]);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.10
 * JD-Core Version:    0.6.0
 */