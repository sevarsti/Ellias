package com.tencent.android.tpush;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

class b
  implements DialogInterface.OnClickListener
{
  b(XGPushActivity paramXGPushActivity, String paramString, Intent paramIntent)
  {
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(this.a));
    localIntent.setFlags(268435456);
    XGPushActivity.access$000(this.c, this.b);
    this.c.startActivity(localIntent);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.b
 * JD-Core Version:    0.6.0
 */