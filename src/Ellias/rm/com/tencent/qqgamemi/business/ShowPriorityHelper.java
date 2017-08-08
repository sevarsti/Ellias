package com.tencent.qqgamemi.business;

import android.content.Context;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.data.GameItem;

public class ShowPriorityHelper
{
  private static final String a = ShowPriorityHelper.class.getSimpleName();
  private static boolean b = false;
  private Context c;

  public ShowPriorityHelper(Context paramContext)
  {
    this.c = paramContext;
  }

  public void a()
  {
    if ((QMiConfig.c() == 2) && (QMiCommon.c(this.c)))
    {
      TLog.b(a, "gamejoy is install,hall qmi need quit");
      b = true;
    }
  }

  public boolean a(GameItem paramGameItem)
  {
    return false;
  }

  public void b()
  {
    if ((QMiConfig.c() == 2) && (QMiCommon.c(this.c)))
    {
      TLog.b(a, "shouyoubao is install,hall qmi need quit");
      b = true;
      QMiCommon.hideQMi(this.c);
    }
  }

  public void c()
  {
    if (QMiConfig.c() == 2)
    {
      TLog.b(a, "shouyoubao is uninstall,hall qmi need show");
      b = false;
      QMiCommon.showQMi(this.c);
    }
  }

  public boolean d()
  {
    return b;
  }

  public boolean e()
  {
    int i = 1;
    if (QMiConfig.c() == 3)
    {
      if (QMiCommon.d(this.c))
      {
        if (DebugUtil.a())
          LogUtil.d(a, "shouyoubao qmi, old hall is install");
        i = 0;
      }
      if (QMiCommon.e(this.c))
      {
        if (DebugUtil.a())
          LogUtil.d(a, "shouyoubao qmi, this game is qmi sdk");
        i = 0;
      }
    }
    do
    {
      do
        while (true)
        {
          return i;
          if (!QMiConfig.b())
            break;
          if (!QMiCommon.d(this.c))
            continue;
          if (DebugUtil.a())
            LogUtil.d(a, "sdk qmi, old hall is install");
          return false;
        }
      while (QMiConfig.c() != 2);
      if (!QMiCommon.c(this.c))
        continue;
      if (DebugUtil.a())
        LogUtil.d(a, "hall qmi, shouyoubao is install");
      i = 0;
    }
    while (!QMiCommon.e(this.c));
    if (DebugUtil.a())
      LogUtil.d(a, "hall qmi, this game is qmi sdk");
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.ShowPriorityHelper
 * JD-Core Version:    0.6.0
 */