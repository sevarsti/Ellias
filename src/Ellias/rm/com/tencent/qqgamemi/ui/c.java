package com.tencent.qqgamemi.ui;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

class c
  implements View.OnClickListener
{
  c(FeedbackDialog paramFeedbackDialog)
  {
  }

  public void onClick(View paramView)
  {
    this.a.dismiss();
    new MeDialog.Builder(FeedbackDialog.a(this.a)).a().show();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.c
 * JD-Core Version:    0.6.0
 */