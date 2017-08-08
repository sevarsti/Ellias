package com.tencent.qqgamemi.ui;

import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.business.UrlDownLoadGameJoy;
import com.tencent.qqgamemi.common.QMiCommon;
import java.io.File;

class af
  implements Runnable
{
  af(MeDialog paramMeDialog)
  {
  }

  public void run()
  {
    LogUtil.d("MeDialog", "showInstallDialog:" + QMiCommon.c(this.a.getContext()));
    if (!QMiCommon.c(this.a.getContext()))
    {
      File localFile = new File(UrlDownLoadGameJoy.a(MeDialog.a(this.a)));
      LogUtil.d("MeDialog", "showInstallDialog:" + UrlDownLoadGameJoy.a(MeDialog.a(this.a)) + localFile.exists());
      if (localFile.exists())
        new QmiSpecialDownloadDialog.Builder(this.a.getContext()).a().show();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.af
 * JD-Core Version:    0.6.0
 */