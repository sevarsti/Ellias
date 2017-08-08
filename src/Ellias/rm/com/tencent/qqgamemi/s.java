package com.tencent.qqgamemi;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.VelocityTracker;
import android.view.WindowManager.LayoutParams;
import android.widget.Scroller;
import com.tencent.qqgamemi.common.TLog;

class s
  implements Runnable
{
  private Scroller b;
  private Point c;
  private float d;
  private float e;

  public s(QMiSpirit paramQMiSpirit, Context paramContext)
  {
    this.b = new Scroller(paramContext);
  }

  void a(float paramFloat1, float paramFloat2)
  {
    int i = QMiWindowManager.b().widthPixels;
    int j = QMiWindowManager.b().heightPixels;
    this.d = paramFloat1;
    this.e = paramFloat2;
    this.b.fling(0, 0, (int)Math.abs(this.d), (int)Math.abs(this.e), 0, i, 0, j);
    this.c = new Point(QMiWindowManager.e().x, QMiWindowManager.e().y);
    TLog.b(QMiSpirit.o(), "starting fling at " + this.c.x + "," + this.c.y + " velocity at " + (int)paramFloat1 + "," + (int)paramFloat1);
    QMiSpirit.b(this.a).b.post(this);
  }

  void a(VelocityTracker paramVelocityTracker)
  {
    int i = QMiWindowManager.b().widthPixels;
    int j = QMiWindowManager.b().heightPixels;
    this.d = paramVelocityTracker.getXVelocity();
    this.e = paramVelocityTracker.getYVelocity();
    this.b.fling(0, 0, (int)Math.abs(this.d), (int)Math.abs(this.e), 0, i, 0, j);
    this.c = new Point(QMiWindowManager.e().x, QMiWindowManager.e().y);
    TLog.b(QMiSpirit.o(), "starting fling at " + this.c.x + "," + this.c.y + " velocity at " + (int)paramVelocityTracker.getXVelocity() + "," + (int)paramVelocityTracker.getYVelocity());
    QMiSpirit.b(this.a).b.post(this);
    QMiSpirit.f(this.a);
  }

  boolean a()
  {
    return !this.b.isFinished();
  }

  void b()
  {
    if (!this.b.isFinished())
      this.b.forceFinished(true);
  }

  public void run()
  {
    if ((this.b.isFinished()) || (this.a.b()))
    {
      TLog.b(QMiSpirit.o(), "scroller is finished, done with fling");
      QMiSpirit.a(this.a).sendEmptyMessage(2);
      this.a.B = true;
      return;
    }
    boolean bool = this.b.computeScrollOffset();
    int i = this.b.getCurrX();
    int j = this.b.getCurrY();
    if ((i != 0) || (j != 0))
    {
      if (this.d <= 0.0F)
        break label238;
      Point localPoint4 = this.c;
      localPoint4.x = (i + localPoint4.x);
      label110: if (this.e <= 0.0F)
        break label259;
      Point localPoint3 = this.c;
      localPoint3.y = (j + localPoint3.y);
    }
    while (true)
    {
      if (!QMiWindowManager.b(QMiSpirit.b(this.a).g, this.c.x, this.c.y))
        this.b.abortAnimation();
      TLog.b(QMiViewManager.a, "Flinger updateViewLayout at " + this.c.x + "," + this.c.y);
      if (!bool)
        break;
      QMiSpirit.a(this.a).post(this);
      return;
      label238: Point localPoint1 = this.c;
      localPoint1.x -= i;
      break label110;
      label259: Point localPoint2 = this.c;
      localPoint2.y -= j;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.s
 * JD-Core Version:    0.6.0
 */