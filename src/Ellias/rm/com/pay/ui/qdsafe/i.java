package com.pay.ui.qdsafe;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

final class i
  implements View.OnClickListener
{
  i(APSmmActivity paramAPSmmActivity)
  {
  }

  public final void onClick(View paramView)
  {
    Intent localIntent = new Intent(this.a, APSafeCenterWebActivity.class);
    localIntent.putExtra("requesturl", this.a.e);
    this.a.startActivityForResult(localIntent, 100002);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.qdsafe.i
 * JD-Core Version:    0.6.0
 */