package com.tencent.qqgamemi.plugin;

import com.tencent.component.plugin.PluginManager.PluginListener;
import com.tencent.qqgamemi.common.TLog;
import java.util.List;

class a
  implements PluginManager.PluginListener
{
  a(QMiPluginManager paramQMiPluginManager)
  {
  }

  public void a()
  {
    TLog.c(QMiPluginManager.m(), "init onPlatformInitialStart");
  }

  public void a(String paramString, int paramInt1, int paramInt2)
  {
    TLog.c(QMiPluginManager.m(), "init onPluginChanged " + paramString);
  }

  public void a(List paramList)
  {
  }

  public void b()
  {
    TLog.c(QMiPluginManager.m(), "init onPlatformInitialFinish");
    QMiPluginManager.a(this.a, true);
    this.a.g();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.a
 * JD-Core Version:    0.6.0
 */