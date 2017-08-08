package com.tencent.qqgamemi;

import com.tencent.qqgamemi.animation.AnimationManager;

class g
  implements Runnable
{
  g(f paramf)
  {
  }

  public void run()
  {
    AnimationManager.i.c(QMiSpirit.c(this.a.a), QMiSpirit.i(this.a.a));
    QMiSpirit.b(this.a.a).i();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.g
 * JD-Core Version:    0.6.0
 */