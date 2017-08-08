package com.tencent.qqgamemi;

import android.app.Dialog;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.report.UserAccessStatics;
import com.tencent.qqgamemi.ui.MeDialog.Builder;

class x
  implements View.OnClickListener
{
  x(QMiViewManager paramQMiViewManager)
  {
  }

  public void onClick(View paramView)
  {
    if (QMiViewManager.b(this.a))
    {
      QMiViewManager.a(this.a, false);
      this.a.b.sendEmptyMessageDelayed(3, 500L);
      this.a.h();
      new MeDialog.Builder(this.a.c).a().show();
      UserAccessStatics.getInstance(this.a.c).addQMiAction(211, System.currentTimeMillis(), QMiCommon.a(this.a.c), null);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.x
 * JD-Core Version:    0.6.0
 */