package com.tenpay.tenpayplugin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

class TenpayNewCardActivity$MyBLCallbackListener$1$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("trace", TenpayNewCardActivity.MyBLCallbackListener.a(TenpayNewCardActivity.MyBLCallbackListener.1.a(this.a)).e.toString());
    localBundle.putInt("pay_type", TenpayNewCardActivity.MyBLCallbackListener.a(TenpayNewCardActivity.MyBLCallbackListener.1.a(this.a)).getPayType());
    localBundle.putInt("backfrom", TenpayNewCardActivity.d(TenpayNewCardActivity.MyBLCallbackListener.a(TenpayNewCardActivity.MyBLCallbackListener.1.a(this.a))));
    TenpayUtil.onCallback(TenpayNewCardActivity.MyBLCallbackListener.a(TenpayNewCardActivity.MyBLCallbackListener.1.a(this.a)), 6, localBundle);
    TenpayNewCardActivity.e(TenpayNewCardActivity.MyBLCallbackListener.a(TenpayNewCardActivity.MyBLCallbackListener.1.a(this.a))).dismiss();
    Intent localIntent = new Intent();
    localIntent.putExtra("trace", TenpayNewCardActivity.MyBLCallbackListener.a(TenpayNewCardActivity.MyBLCallbackListener.1.a(this.a)).e.toString());
    TenpayNewCardActivity.MyBLCallbackListener.a(TenpayNewCardActivity.MyBLCallbackListener.1.a(this.a)).setResult(100, localIntent);
    TenpayNewCardActivity.MyBLCallbackListener.a(TenpayNewCardActivity.MyBLCallbackListener.1.a(this.a)).finish();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.MyBLCallbackListener.1.2
 * JD-Core Version:    0.6.0
 */