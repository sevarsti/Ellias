package com.tencent.qqgamemi;

import android.os.Handler;
import com.tencent.qqgamemi.animation.ActionListener;
import com.tencent.qqgamemi.animation.AnimationParam;
import com.tencent.qqgamemi.common.TLog;

class i
  implements ActionListener
{
  i(QMiSpirit paramQMiSpirit)
  {
  }

  public void a()
  {
  }

  public void a(AnimationParam paramAnimationParam)
  {
  }

  public void b()
  {
    TLog.c("Benson", "tunetoBallListenerY onActionEnd");
    QMiSpirit.a(this.a).sendEmptyMessage(5);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.i
 * JD-Core Version:    0.6.0
 */