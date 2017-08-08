package com.tencent.qqgamemi.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.business.UrlDownLoadGameJoy;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.report.UserAccessStatics;

class aq
  implements View.OnClickListener
{
  aq(QmiSpecialDownloadDialog.Builder paramBuilder)
  {
  }

  public void onClick(View paramView)
  {
    int i = paramView.getId();
    if (i == ResourceUtil.f("AlertButtonCancelSubmit"))
    {
      QmiSpecialDownloadDialog.Builder.a(this.a).dismiss();
      UserAccessStatics.getInstance(QmiSpecialDownloadDialog.Builder.b(this.a)).addQMiAction(303, System.currentTimeMillis(), QMiCommon.a(QmiSpecialDownloadDialog.Builder.b(this.a)), null);
    }
    do
      return;
    while (i != ResourceUtil.f("AlertButtonOKSubmit"));
    QMiCommon.b(QmiSpecialDownloadDialog.Builder.b(this.a), UrlDownLoadGameJoy.a(QmiSpecialDownloadDialog.Builder.b(this.a)));
    QmiSpecialDownloadDialog.Builder.a(this.a).dismiss();
    UserAccessStatics.getInstance(QmiSpecialDownloadDialog.Builder.b(this.a)).addQMiAction(302, System.currentTimeMillis(), QMiCommon.a(QmiSpecialDownloadDialog.Builder.b(this.a)), null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.aq
 * JD-Core Version:    0.6.0
 */