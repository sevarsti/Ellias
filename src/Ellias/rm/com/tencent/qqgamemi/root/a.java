package com.tencent.qqgamemi.root;

import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.qqgamemi.ui.RootIntroduceDialog;

class a
  implements View.OnClickListener
{
  a(RootAlertDialog.Builder paramBuilder, RootAlertDialog paramRootAlertDialog)
  {
  }

  public void onClick(View paramView)
  {
    if (RootAlertDialog.Builder.a(this.b) != null)
      RootAlertDialog.Builder.a(this.b).onClick(this.a, -1);
    if (this.a.isShowing())
      this.a.dismiss();
    new RootIntroduceDialog(RootAlertDialog.Builder.b(this.b)).show();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.root.a
 * JD-Core Version:    0.6.0
 */