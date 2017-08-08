package com.tencent.android.tpush;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class a
  implements DialogInterface.OnClickListener
{
  a(XGPushActivity paramXGPushActivity, Intent paramIntent)
  {
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    XGPushActivity.access$000(this.b, this.a);
    paramDialogInterface.cancel();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.a
 * JD-Core Version:    0.6.0
 */