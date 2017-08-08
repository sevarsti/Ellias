package com.tencent.qqgamemi;

import android.graphics.Point;
import android.view.View;
import com.nineoldandroids.animation.TypeEvaluator;

class r
  implements TypeEvaluator
{
  private View b;

  public r(QMiSpirit paramQMiSpirit, View paramView)
  {
    this.b = paramView;
  }

  public Point a(float paramFloat, Point paramPoint1, Point paramPoint2)
  {
    Point localPoint = new Point((int)(paramPoint1.x + paramFloat * (paramPoint2.x - paramPoint1.x)), (int)(paramPoint1.y + paramFloat * (paramPoint2.y - paramPoint1.y)));
    QMiWindowManager.b(this.a, localPoint.x, localPoint.y);
    return localPoint;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.r
 * JD-Core Version:    0.6.0
 */