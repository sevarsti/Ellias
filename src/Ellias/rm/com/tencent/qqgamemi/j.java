package com.tencent.qqgamemi;

import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.animation.AnimationManager;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.data.GameItem;
import com.tencent.qqgamemi.plugin.QMiPluginManager;

class j extends Handler
{
  j(QMiSpirit paramQMiSpirit, Looper paramLooper)
  {
    super(paramLooper);
  }

  public void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    switch (paramMessage.what)
    {
    case 100:
    default:
      return;
    case 0:
      this.a.c(QMiSpirit.b(this.a));
      QMiSpirit.b(this.a).e = true;
      return;
    case 1:
      this.a.h();
      QMiSpirit.b(this.a).e = false;
      return;
    case 12:
      QMiSpirit.b(this.a).k();
      return;
    case 13:
      TLog.c("Benson", "[QMiSprite] AUTO HELLO, dir : " + paramMessage.arg1);
      if (QMiPluginManager.a().b())
      {
        TLog.c("Benson", "there is plugin running,so pass to say hello");
        QMiSpirit.a(this.a, this.a.F.a);
        return;
      }
      this.a.d();
      this.a.b(paramMessage.arg1);
      return;
    case 14:
      TLog.c(QMiSpirit.o(), "save position");
      GameItem localGameItem = (GameItem)paramMessage.obj;
      QMiWindowManager.b(localGameItem);
      return;
    case 2:
      int n = QMiSpirit.a(this.a.x[4], this.a.y[4], this.a.x[0], this.a.y[0]);
      AnimationManager.i.a(n, QMiSpirit.c(this.a), QMiSpirit.d(this.a));
      return;
    case 3:
      if ((QMiSpirit.b(this.a).b()) || (QMiSpirit.b(this.a).c()) || (QMiSpirit.b(this.a).d()) || (QMiSpirit.b(this.a).e()))
      {
        QMiSpirit.a(this.a, QMiServiceLogic.b);
        return;
      }
      Point localPoint1 = new Point(QMiWindowManager.e().x, QMiWindowManager.e().y);
      Point localPoint2 = QMiSpirit.b(this.a).f();
      TLog.c(QMiViewManager.a, "moveFloatViewToBorder from:" + localPoint1 + " to:" + localPoint2);
      QMiSpirit.a(localPoint1.x, localPoint1.y, localPoint2.x, localPoint2.y);
      this.a.g();
      return;
    case 4:
    case 5:
    }
    ImageView localImageView = QMiSpirit.c(this.a);
    int i = ((Integer)localImageView.getTag(ResourceUtil.f("qmi_tag_temp"))).intValue();
    int m;
    float f;
    int j;
    String str;
    if (paramMessage.what == 4)
      if (i < 0)
      {
        m = 3 * (localImageView.getWidth() / 4);
        f = 360.0F;
        this.a.F.a = 1;
        j = m;
        str = "translationX";
      }
    while (true)
    {
      QMiSpirit.a(this.a, new AnimatorSet());
      ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(localImageView, "rotation", new float[] { 0.0F, f });
      localObjectAnimator1.setDuration(200L);
      localObjectAnimator1.setInterpolator(new AnticipateOvershootInterpolator(3.0F));
      float[] arrayOfFloat = new float[2];
      arrayOfFloat[0] = 0.0F;
      arrayOfFloat[1] = j;
      ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(localImageView, str, arrayOfFloat);
      localObjectAnimator2.setInterpolator(new AnticipateOvershootInterpolator(3.0F));
      localObjectAnimator2.setDuration(200L);
      QMiSpirit.e(this.a).playTogether(new Animator[] { localObjectAnimator2, localObjectAnimator1 });
      QMiSpirit.e(this.a).addListener(this.a.F);
      QMiSpirit.e(this.a).start();
      QMiSpirit.b(this.a).f = true;
      return;
      m = 3 * (-localImageView.getWidth() / 4);
      f = -360.0F;
      this.a.F.a = 2;
      break;
      if (paramMessage.what == 5)
      {
        int k;
        if (i < 0)
        {
          k = 3 * (-localImageView.getHeight() / 4);
          f = -360.0F;
        }
        for (this.a.F.a = 3; ; this.a.F.a = 4)
        {
          j = k;
          str = "translationY";
          break;
          k = 3 * (localImageView.getHeight() / 4);
          f = 360.0F;
        }
      }
      str = "";
      f = 0.0F;
      j = 0;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.j
 * JD-Core Version:    0.6.0
 */