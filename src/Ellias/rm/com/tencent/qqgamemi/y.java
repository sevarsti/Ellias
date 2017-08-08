package com.tencent.qqgamemi;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.qqgamemi.business.PluginUndealCountManager;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.plugin.PluginItem;
import com.tencent.qqgamemi.report.UserAccessStatics;

class y
  implements View.OnClickListener
{
  y(QMiViewManager paramQMiViewManager)
  {
  }

  public void onClick(View paramView)
  {
    TLog.c(QMiViewManager.a, "inflateItem onClick");
    if (QMiViewManager.b(this.a))
    {
      PluginItem localPluginItem = (PluginItem)paramView.getTag();
      if (localPluginItem != null)
      {
        QMiViewManager.a(this.a, false);
        this.a.b.sendEmptyMessageDelayed(3, 500L);
        this.a.h();
        localPluginItem.launchPlugin(this.a.c);
        PluginUndealCountManager.a().b(localPluginItem.id);
        UserAccessStatics.getInstance(this.a.c).addQMiAction(203, System.currentTimeMillis(), QMiCommon.a(this.a.c), localPluginItem.id);
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.y
 * JD-Core Version:    0.6.0
 */