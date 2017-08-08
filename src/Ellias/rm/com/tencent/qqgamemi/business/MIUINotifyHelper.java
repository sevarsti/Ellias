package com.tencent.qqgamemi.business;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.ui.MiuiNotifyDialog;
import com.tencent.qqgamemi.ui.MiuiNotifyDialog.Builder;

public class MIUINotifyHelper
{
  private static final String a = MIUINotifyHelper.class.getSimpleName();
  private Context b;

  public MIUINotifyHelper(Context paramContext)
  {
    this.b = paramContext;
  }

  private void a(boolean paramBoolean)
  {
    TLog.c(a, "setNeedCheckRom:" + paramBoolean);
    SharedPreferences.Editor localEditor = this.b.getSharedPreferences("QMiCheckRom", 2).edit();
    localEditor.putBoolean("needCheckRom", paramBoolean);
    localEditor.commit();
  }

  private boolean b()
  {
    boolean bool = this.b.getSharedPreferences("QMiCheckRom", 0).getBoolean("needCheckRom", true);
    TLog.c(a, "needCheckRom:" + bool);
    return bool;
  }

  private void c()
  {
    new MiuiNotifyDialog.Builder(this.b).a().show();
  }

  public void a()
  {
    if ((!QMiConfig.b()) && (b()))
    {
      if (QMiCommon.c())
        c();
    }
    else
      return;
    a(false);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.MIUINotifyHelper
 * JD-Core Version:    0.6.0
 */