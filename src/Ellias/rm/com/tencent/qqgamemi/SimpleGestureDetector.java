package com.tencent.qqgamemi;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public class SimpleGestureDetector
{
  private static final int f = 0;
  private static final int g = 0;
  private static final int h = 0;
  private static final int i = 1;
  private static final int j = 2;
  private static final int k = 3;
  private int a = 400;
  private int b;
  private int c;
  private int d;
  private int e;
  private final Handler l;
  private final SimpleGestureDetector.OnGestureListener m;
  private SimpleGestureDetector.OnDoubleTapListener n;
  private boolean o;
  private boolean p;
  private boolean q;
  private boolean r;
  private MotionEvent s;
  private MotionEvent t;
  private boolean u;
  private float v;
  private float w;
  private boolean x;
  private boolean y;
  private VelocityTracker z;

  public SimpleGestureDetector(Context paramContext, SimpleGestureDetector.OnGestureListener paramOnGestureListener)
  {
    this(paramContext, paramOnGestureListener, null);
  }

  public SimpleGestureDetector(Context paramContext, SimpleGestureDetector.OnGestureListener paramOnGestureListener, Handler paramHandler)
  {
  }

  public SimpleGestureDetector(Context paramContext, SimpleGestureDetector.OnGestureListener paramOnGestureListener, Handler paramHandler, boolean paramBoolean)
  {
    if (paramHandler != null);
    for (this.l = new ag(this, paramHandler); ; this.l = new ag(this))
    {
      this.m = paramOnGestureListener;
      if ((paramOnGestureListener instanceof SimpleGestureDetector.OnDoubleTapListener))
        a((SimpleGestureDetector.OnDoubleTapListener)paramOnGestureListener);
      a(paramContext, paramBoolean);
      return;
    }
  }

  @Deprecated
  public SimpleGestureDetector(SimpleGestureDetector.OnGestureListener paramOnGestureListener)
  {
    this(null, paramOnGestureListener, null);
  }

  @Deprecated
  public SimpleGestureDetector(SimpleGestureDetector.OnGestureListener paramOnGestureListener, Handler paramHandler)
  {
    this(null, paramOnGestureListener, paramHandler);
  }

  private void a(Context paramContext, boolean paramBoolean)
  {
    if (this.m == null)
      throw new NullPointerException("OnGestureListener must not be null");
    this.x = true;
    this.y = paramBoolean;
    int i1;
    int i2;
    if (paramContext == null)
    {
      i1 = ViewConfiguration.getTouchSlop();
      i2 = 100;
      this.d = ViewConfiguration.getMinimumFlingVelocity();
    }
    ViewConfiguration localViewConfiguration;
    for (this.e = ViewConfiguration.getMaximumFlingVelocity(); ; this.e = localViewConfiguration.getScaledMaximumFlingVelocity())
    {
      this.b = (i1 * i1);
      this.c = (i2 * i2);
      return;
      localViewConfiguration = ViewConfiguration.get(paramContext);
      i1 = localViewConfiguration.getScaledTouchSlop();
      i2 = localViewConfiguration.getScaledDoubleTapSlop();
      this.d = localViewConfiguration.getScaledMinimumFlingVelocity();
    }
  }

  private boolean a(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, MotionEvent paramMotionEvent3)
  {
    if (!this.r);
    int i1;
    int i2;
    do
    {
      do
        return false;
      while (paramMotionEvent3.getEventTime() - paramMotionEvent2.getEventTime() > h);
      i1 = (int)paramMotionEvent1.getX() - (int)paramMotionEvent3.getX();
      i2 = (int)paramMotionEvent1.getY() - (int)paramMotionEvent3.getY();
    }
    while (i1 * i1 + i2 * i2 >= this.c);
    return true;
  }

  private void b()
  {
    this.l.removeMessages(1);
    this.l.removeMessages(2);
    this.l.removeMessages(3);
    this.z.recycle();
    this.z = null;
    this.u = false;
    this.o = false;
    this.q = false;
    this.r = false;
    if (this.p)
      this.p = false;
  }

  private void c()
  {
    this.l.removeMessages(3);
    this.p = true;
    this.m.g(this.s);
  }

  public void a(SimpleGestureDetector.OnDoubleTapListener paramOnDoubleTapListener)
  {
    this.n = paramOnDoubleTapListener;
  }

  public void a(boolean paramBoolean)
  {
    this.x = paramBoolean;
  }

  public boolean a()
  {
    return this.x;
  }

  public boolean a(MotionEvent paramMotionEvent)
  {
    int i1 = 1;
    int i2 = paramMotionEvent.getAction();
    float f1 = paramMotionEvent.getY();
    float f2 = paramMotionEvent.getX();
    if (this.z == null)
      this.z = VelocityTracker.obtain();
    this.z.addMovement(paramMotionEvent);
    float f5;
    float f6;
    int i5;
    boolean bool4;
    switch (i2 & 0xFF)
    {
    case 4:
    default:
    case 5:
    case 6:
    case 0:
    case 2:
      do
      {
        do
        {
          do
            return false;
          while (!this.y);
          b();
          return false;
        }
        while ((!this.y) || (paramMotionEvent.getPointerCount() != 2));
        if ((i2 & 0xFF00) >> 8 == 0);
        while (true)
        {
          this.w = paramMotionEvent.getX(i1);
          this.v = paramMotionEvent.getY(i1);
          this.z.recycle();
          this.z = VelocityTracker.obtain();
          return false;
          i1 = 0;
        }
        if (this.n != null)
        {
          boolean bool6 = this.l.hasMessages(3);
          if (bool6)
            this.l.removeMessages(3);
          if ((this.s != null) && (this.t != null) && (bool6) && (a(this.s, this.t, paramMotionEvent)))
            this.u = i1;
        }
        for (boolean bool5 = false | this.n.a(this.s) | this.n.c(paramMotionEvent); ; bool5 = false)
        {
          this.w = f2;
          this.v = f1;
          if (this.s != null)
            this.s.recycle();
          this.s = MotionEvent.obtain(paramMotionEvent);
          this.q = i1;
          this.r = i1;
          this.o = i1;
          this.p = false;
          if (this.x)
          {
            this.l.removeMessages(2);
            this.l.sendEmptyMessageAtTime(2, this.s.getDownTime() + g + f);
          }
          this.l.sendEmptyMessageAtTime(i1, this.s.getDownTime() + g);
          return bool5 | this.m.d(paramMotionEvent);
          this.l.sendEmptyMessageDelayed(3, h);
        }
      }
      while ((this.p) || ((this.y) && (paramMotionEvent.getPointerCount() > i1)));
      f5 = this.w - f2;
      f6 = this.v - f1;
      if (this.u)
        return false | this.n.c(paramMotionEvent);
      if (!this.q)
        break;
      int i3 = (int)(f2 - this.s.getX());
      int i4 = (int)(f1 - this.s.getY());
      i5 = i3 * i3 + i4 * i4;
      if (i5 > this.b)
      {
        bool4 = this.m.a(this.s, paramMotionEvent, f5, f6);
        this.w = f2;
        this.v = f1;
        this.q = false;
        this.l.removeMessages(3);
        this.l.removeMessages(i1);
        this.l.removeMessages(2);
      }
    case 1:
    case 3:
    }
    for (boolean bool3 = bool4; ; bool3 = false)
    {
      if (i5 > this.a)
        this.r = false;
      return bool3;
      if ((Math.abs(f5) < 1.0F) && (Math.abs(f6) < 1.0F))
        break;
      boolean bool2 = this.m.a(this.s, paramMotionEvent, f5, f6);
      this.w = f2;
      this.v = f1;
      return bool2;
      this.o = false;
      MotionEvent localMotionEvent = MotionEvent.obtain(paramMotionEvent);
      boolean bool1;
      if (this.u)
        bool1 = false | this.n.c(paramMotionEvent);
      while (true)
      {
        if (this.t != null)
          this.t.recycle();
        this.t = localMotionEvent;
        this.z.recycle();
        this.z = null;
        this.u = false;
        this.l.removeMessages(i1);
        this.l.removeMessages(2);
        return bool1;
        if (this.p)
        {
          this.l.removeMessages(3);
          this.p = false;
          bool1 = false;
          continue;
        }
        if (this.q)
        {
          bool1 = this.m.f(paramMotionEvent);
          continue;
        }
        VelocityTracker localVelocityTracker = this.z;
        localVelocityTracker.computeCurrentVelocity(1000, this.e);
        float f3 = localVelocityTracker.getYVelocity();
        float f4 = localVelocityTracker.getXVelocity();
        bool1 = this.m.b(this.s, paramMotionEvent, f4, f3);
      }
      b();
      return false;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.SimpleGestureDetector
 * JD-Core Version:    0.6.0
 */