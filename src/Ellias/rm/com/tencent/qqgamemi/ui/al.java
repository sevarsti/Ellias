package com.tencent.qqgamemi.ui;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.tencent.qqgamemi.common.TLog;

class al
  implements CompoundButton.OnCheckedChangeListener
{
  al(MiuiNotifyDialog.Builder paramBuilder)
  {
  }

  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    TLog.c("MiuiNotifyDialog", "onCheckedChanged:" + paramBoolean);
    MiuiNotifyDialog.Builder localBuilder = this.a;
    if (!paramBoolean);
    for (boolean bool = true; ; bool = false)
    {
      MiuiNotifyDialog.Builder.a(localBuilder, bool);
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.al
 * JD-Core Version:    0.6.0
 */