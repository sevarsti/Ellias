package com.tencent.qqgamemi.ui;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

class ao
  implements View.OnClickListener
{
  ao(PluginManagerDialog paramPluginManagerDialog)
  {
  }

  public void onClick(View paramView)
  {
    this.a.dismiss();
    new MeDialog.Builder(PluginManagerDialog.a(this.a)).a().show();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.ao
 * JD-Core Version:    0.6.0
 */