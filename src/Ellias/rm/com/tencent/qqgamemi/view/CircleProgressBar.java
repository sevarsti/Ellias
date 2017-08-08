package com.tencent.qqgamemi.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.tencent.component.utils.ResourceUtil;

public class CircleProgressBar extends View
{
  private static final int e = 6;
  private static final int f = ResourceUtil.g("qmi_circle_progress_background");
  private static final int g = ResourceUtil.g("qmi_circle_progress_foreground");
  RectF a = new RectF();
  Paint b = new Paint();
  int c;
  int d;
  private int h = 100;
  private int i = 0;

  public CircleProgressBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.c = paramContext.getResources().getColor(f);
    this.d = paramContext.getResources().getColor(g);
  }

  public int getMaxProgress()
  {
    return this.h;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int j = getWidth();
    int k = getHeight();
    if (j != k)
    {
      k = Math.min(j, k);
      j = k;
    }
    this.b.setAntiAlias(true);
    paramCanvas.drawColor(0);
    this.b.setStrokeWidth(6.0F);
    this.b.setStyle(Paint.Style.STROKE);
    this.a.left = 3.0F;
    this.a.top = 3.0F;
    this.a.right = (j - 3);
    this.a.bottom = (k - 3);
    this.b.setColor(this.c);
    paramCanvas.drawArc(this.a, -90.0F, 360.0F, false, this.b);
    this.b.setColor(this.d);
    paramCanvas.drawArc(this.a, -90.0F, 360.0F * (this.i / this.h), false, this.b);
  }

  public void setMaxProgress(int paramInt)
  {
    this.h = paramInt;
    invalidate();
  }

  public void setProgress(int paramInt)
  {
    this.i = paramInt;
    invalidate();
  }

  public void setProgressNotInUiThread(int paramInt)
  {
    this.i = paramInt;
    postInvalidate();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.CircleProgressBar
 * JD-Core Version:    0.6.0
 */