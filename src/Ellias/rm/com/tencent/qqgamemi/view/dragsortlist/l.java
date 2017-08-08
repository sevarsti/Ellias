package com.tencent.qqgamemi.view.dragsortlist;

import android.os.SystemClock;

class l
  implements Runnable
{
  private float a;
  protected long b;
  private float d;
  private float e;
  private float f;
  private float g;
  private float h;
  private boolean i;

  public l(DragSortListView paramDragSortListView, float paramFloat, int paramInt)
  {
    this.d = paramFloat;
    this.a = paramInt;
    float f1 = 1.0F / (2.0F * this.d * (1.0F - this.d));
    this.h = f1;
    this.e = f1;
    this.f = (this.d / (2.0F * (this.d - 1.0F)));
    this.g = (1.0F / (1.0F - this.d));
  }

  public float a(float paramFloat)
  {
    if (paramFloat < this.d)
      return paramFloat * (paramFloat * this.e);
    if (paramFloat < 1.0F - this.d)
      return this.f + paramFloat * this.g;
    return 1.0F - this.h * (paramFloat - 1.0F) * (paramFloat - 1.0F);
  }

  public void a()
  {
  }

  public void a(float paramFloat1, float paramFloat2)
  {
  }

  public void b()
  {
  }

  public void c()
  {
    this.b = SystemClock.uptimeMillis();
    this.i = false;
    a();
    this.c.post(this);
  }

  public void d()
  {
    this.i = true;
  }

  public void run()
  {
    if (this.i)
      return;
    float f1 = (float)(SystemClock.uptimeMillis() - this.b) / this.a;
    if (f1 >= 1.0F)
    {
      a(1.0F, 1.0F);
      b();
      return;
    }
    a(f1, a(f1));
    this.c.post(this);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.l
 * JD-Core Version:    0.6.0
 */