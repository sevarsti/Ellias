package com.tenpay.tenpayplugin;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

class TenpayPluginActivity$MyBLCallbackListener$1$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("trace", TenpayPluginActivity.MyBLCallbackListener.a(TenpayPluginActivity.MyBLCallbackListener.1.a(this.a)).e.toString());
    localBundle.putInt("pay_type", TenpayPluginActivity.MyBLCallbackListener.a(TenpayPluginActivity.MyBLCallbackListener.1.a(this.a)).getPayType());
    localBundle.putInt("backfrom", TenpayPluginActivity.c(TenpayPluginActivity.MyBLCallbackListener.a(TenpayPluginActivity.MyBLCallbackListener.1.a(this.a))));
    TenpayUtil.onCallback(TenpayPluginActivity.MyBLCallbackListener.a(TenpayPluginActivity.MyBLCallbackListener.1.a(this.a)), 6, localBundle);
    TenpayPluginActivity.e(TenpayPluginActivity.MyBLCallbackListener.a(TenpayPluginActivity.MyBLCallbackListener.1.a(this.a))).dismiss();
    TenpayPluginActivity.MyBLCallbackListener.a(TenpayPluginActivity.MyBLCallbackListener.1.a(this.a)).finish();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.MyBLCallbackListener.1.2
 * JD-Core Version:    0.6.0
 */