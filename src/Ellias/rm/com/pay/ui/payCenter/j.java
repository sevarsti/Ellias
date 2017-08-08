package com.pay.ui.payCenter;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.pay.tool.APDataReportManager;

final class j
  implements View.OnClickListener
{
  j(APPayGameListNumActivity paramAPPayGameListNumActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.gamelist.else", APPayGameListNumActivity.a(this.a));
    APPayGameListNumActivity.b(this.a).saveNum = "";
    Intent localIntent = new Intent();
    localIntent.setClass(this.a, APPayGameInputNumActivity.class);
    this.a.startActivity(localIntent);
    this.a.finish();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payCenter.j
 * JD-Core Version:    0.6.0
 */