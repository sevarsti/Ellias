package com.tenpay.tenpayplugin;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.tenpay.tenpayplugin.view.ValidDateEdit;

class TenpayPluginActivity$8
  implements View.OnFocusChangeListener
{
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      Drawable localDrawable = this.a.getResources().getDrawable(TenpayResourceUtil.getDrawableId(this.a, "unipay_tenpay_year"));
      localDrawable.setBounds(0, 0, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
      TenpayPluginActivity.t(this.a).setCompoundDrawables(TenpayPluginActivity.t(this.a).getCompoundDrawables()[0], TenpayPluginActivity.t(this.a).getCompoundDrawables()[1], localDrawable, TenpayPluginActivity.t(this.a).getCompoundDrawables()[3]);
      TenpayPluginActivity.t(this.a).setCompoundDrawablePadding(3);
      return;
    }
    TenpayPluginActivity.t(this.a).setCompoundDrawables(TenpayPluginActivity.t(this.a).getCompoundDrawables()[0], TenpayPluginActivity.t(this.a).getCompoundDrawables()[1], null, TenpayPluginActivity.t(this.a).getCompoundDrawables()[3]);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.8
 * JD-Core Version:    0.6.0
 */