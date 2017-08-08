package com.tencent.qqgamemi.business;

import android.os.Handler;
import android.text.TextUtils;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.data.GameItem;
import com.tencent.qqgamemi.protocol.MsgHandle;

public class GameActionReportHelper
{
  private static final long a = 300000L;
  private static long b = 0L;
  private static final int c = 320;
  private static final int d = 321;
  private static final int e = 322;
  private static final int f = 323;
  private static GameActionReportHelper l = null;
  private GameItem g;
  private GameItem h;
  private boolean i = false;
  private boolean j = false;
  private GameActionObservable k = new GameActionObservable();
  private Handler m = new c(this);

  public static GameActionReportHelper a()
  {
    if (l == null)
      l = new GameActionReportHelper();
    return l;
  }

  private void d()
  {
    this.m.removeMessages(321);
    this.m.sendEmptyMessageDelayed(321, b);
  }

  private void e()
  {
    this.m.removeMessages(321);
  }

  private void f()
  {
    if ((this.g != null) && (!TextUtils.isEmpty(this.g.packageName)))
    {
      TLog.c("gameAction", "GameAction " + this.g.packageName + " start");
      this.k.a(this.g.packageName);
      if (QMiEnvironmentHelper.a.longValue() != 0L)
        MsgHandle.a(this.m, 320, this.g.packageName, 0);
    }
    else
    {
      return;
    }
    LogUtil.d("gameAction", "uin is 0");
  }

  private void g()
  {
    if ((this.g != null) && (!TextUtils.isEmpty(this.g.packageName)))
    {
      TLog.c("gameAction", "GameAction " + this.g.packageName + " playing");
      this.k.b(this.g.packageName);
      if (QMiEnvironmentHelper.a.longValue() != 0L)
        MsgHandle.a(this.m, 323, this.g.packageName, 1);
    }
    else
    {
      return;
    }
    LogUtil.d("gameAction", "uin is 0");
  }

  private void h()
  {
    if ((this.h != null) && (!TextUtils.isEmpty(this.h.packageName)))
    {
      TLog.c("gameAction", "GameAction " + this.h.packageName + " over");
      this.k.c(this.h.packageName);
      if (QMiEnvironmentHelper.a.longValue() != 0L)
        MsgHandle.a(this.m, 322, this.h.packageName, 2);
    }
    else
    {
      return;
    }
    LogUtil.d("gameAction", "uin is 0");
  }

  public void a(GameActionObserver paramGameActionObserver)
  {
    try
    {
      this.k.registerObserver(paramGameActionObserver);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void a(GameItem paramGameItem)
  {
    if (!this.j)
    {
      if ((paramGameItem != null) && (paramGameItem.type != 0))
      {
        TLog.c("gameAction", "currentGameChanged," + paramGameItem + " is game");
        this.i = true;
        this.j = true;
        this.g = paramGameItem;
        return;
      }
      this.i = false;
      this.j = false;
      return;
    }
    if (!this.g.equals(paramGameItem))
    {
      this.i = true;
      if ((paramGameItem != null) && (paramGameItem.type != 0))
        TLog.c("gameAction", "currentGameChanged," + paramGameItem + " a new game");
      for (this.j = true; ; this.j = false)
      {
        this.h = this.g;
        this.g = paramGameItem;
        return;
        TLog.c("gameAction", "currentGameChanged," + paramGameItem + " not game");
      }
    }
    this.i = false;
  }

  public void b()
  {
    if (this.i)
    {
      this.i = false;
      if (this.j)
        f();
    }
    else
    {
      return;
    }
    e();
    h();
  }

  public void b(GameActionObserver paramGameActionObserver)
  {
    try
    {
      this.k.unregisterObserver(paramGameActionObserver);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public boolean c()
  {
    return this.i;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.GameActionReportHelper
 * JD-Core Version:    0.6.0
 */