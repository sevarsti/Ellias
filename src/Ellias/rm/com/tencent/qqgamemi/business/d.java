package com.tencent.qqgamemi.business;

import com.tencent.component.utils.clock.Clock;
import com.tencent.component.utils.clock.OnClockListener;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.login.QMiLoginManager;

class d
  implements OnClockListener
{
  d(PluginUndealCountManager paramPluginUndealCountManager)
  {
  }

  public boolean onClockArrived(Clock paramClock)
  {
    if (QMiLoginManager.a().m())
    {
      long l = System.currentTimeMillis();
      TLog.b("PluginUndealCountManager", "onClockArrived (clockId:" + paramClock.b() + ") interval:" + (System.currentTimeMillis() - PluginUndealCountManager.a(this.a)));
      PluginUndealCountManager.a(this.a, l);
      if (l - PluginUndealCountManager.b(this.a) >= 10000L)
        PluginUndealCountManager.c(this.a);
      PluginUndealCountManager.b(this.a, l);
    }
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.d
 * JD-Core Version:    0.6.0
 */