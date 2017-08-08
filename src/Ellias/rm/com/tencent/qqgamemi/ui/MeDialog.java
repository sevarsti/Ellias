package com.tencent.qqgamemi.ui;

import android.content.Context;
import android.os.Handler;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.QMiConfig;

public class MeDialog extends QMiDialog
{
  private static final String a = "MeDialog";
  private Context b = null;

  public MeDialog(Context paramContext)
  {
    super(paramContext);
    this.b = paramContext;
  }

  public MeDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.b = paramContext;
  }

  public void show()
  {
    super.show();
    LogUtil.d("MeDialog", "showInstallDialog:" + QMiConfig.b() + " " + QMiCommon.a(getContext()));
    if (QMiConfig.b())
      new Handler().postDelayed(new af(this), 500L);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.MeDialog
 * JD-Core Version:    0.6.0
 */