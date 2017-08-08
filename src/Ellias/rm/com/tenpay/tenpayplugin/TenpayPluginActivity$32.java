package com.tenpay.tenpayplugin;

import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

class TenpayPluginActivity$32
  implements DialogInterface.OnShowListener
{
  public void onShow(DialogInterface paramDialogInterface)
  {
    this.b.setBackgroundResource(TenpayResourceUtil.getAnimId(this.a, "unipay_tenpay_floating_anim"));
    AnimationDrawable localAnimationDrawable = (AnimationDrawable)this.b.getBackground();
    localAnimationDrawable.stop();
    localAnimationDrawable.start();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.32
 * JD-Core Version:    0.6.0
 */