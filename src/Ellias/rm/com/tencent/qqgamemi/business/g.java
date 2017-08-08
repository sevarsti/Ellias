package com.tencent.qqgamemi.business;

import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.report.UserAccessStatics;

class g
  implements View.OnClickListener
{
  g(UrlDownloadGameJoyDialog paramUrlDownloadGameJoyDialog)
  {
  }

  public void onClick(View paramView)
  {
    if (paramView == UrlDownloadGameJoyDialog.a(this.a))
    {
      this.a.dismiss();
      UrlDownLoadGameJoy.a().a(UrlDownloadGameJoyDialog.b(this.a), UrlDownloadGameJoyDialog.c(this.a));
      UserAccessStatics.getInstance(UrlDownloadGameJoyDialog.d(this.a)).addQMiAction(207, System.currentTimeMillis(), QMiCommon.a(UrlDownloadGameJoyDialog.d(this.a)), null);
    }
    do
      return;
    while (paramView != UrlDownloadGameJoyDialog.e(this.a));
    this.a.dismiss();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.g
 * JD-Core Version:    0.6.0
 */