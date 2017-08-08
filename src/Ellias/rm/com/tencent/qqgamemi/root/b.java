package com.tencent.qqgamemi.root;

import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.View.OnClickListener;

class b
  implements View.OnClickListener
{
  b(RootAlertDialog.Builder paramBuilder, RootAlertDialog paramRootAlertDialog)
  {
  }

  public void onClick(View paramView)
  {
    if (RootAlertDialog.Builder.c(this.b) != null)
      RootAlertDialog.Builder.c(this.b).onClick(this.a, -2);
    if (this.a.isShowing())
      this.a.dismiss();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.root.b
 * JD-Core Version:    0.6.0
 */