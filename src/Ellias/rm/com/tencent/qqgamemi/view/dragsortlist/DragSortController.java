package com.tencent.qqgamemi.view.dragsortlist;

import android.graphics.Point;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;

public class DragSortController extends SimpleFloatViewManager
  implements GestureDetector.OnGestureListener, View.OnTouchListener
{
  public static final int c = 0;
  public static final int d = 1;
  public static final int e = 2;
  public static final int f = 0;
  public static final int g = 1;
  public static final int h = -1;
  private int A;
  private boolean B;
  private DragSortListView C;
  private int D;
  private GestureDetector.OnGestureListener E = new a(this);
  private int a = 0;
  private boolean b = true;
  private int i;
  private boolean j = false;
  private boolean k = false;
  private GestureDetector l;
  private GestureDetector m;
  private int n;
  private int o = -1;
  private int p = -1;
  private int q = -1;
  private int[] r = new int[2];
  private int s;
  private int t;
  private int u;
  private int v;
  private boolean w = false;
  private float x = 500.0F;
  private int y;
  private int z;

  public DragSortController(DragSortListView paramDragSortListView)
  {
    this(paramDragSortListView, 0, 0, 1);
  }

  public DragSortController(DragSortListView paramDragSortListView, int paramInt1, int paramInt2, int paramInt3)
  {
    this(paramDragSortListView, paramInt1, paramInt2, paramInt3, 0);
  }

  public DragSortController(DragSortListView paramDragSortListView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this(paramDragSortListView, paramInt1, paramInt2, paramInt3, paramInt4, 0);
  }

  public DragSortController(DragSortListView paramDragSortListView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    super(paramDragSortListView);
    this.C = paramDragSortListView;
    this.l = new GestureDetector(paramDragSortListView.getContext(), this);
    this.m = new GestureDetector(paramDragSortListView.getContext(), this.E);
    this.m.setIsLongpressEnabled(false);
    this.n = ViewConfiguration.get(paramDragSortListView.getContext()).getScaledTouchSlop();
    this.y = paramInt1;
    this.z = paramInt4;
    this.A = paramInt5;
    c(paramInt3);
    b(paramInt2);
  }

  public int a()
  {
    return this.a;
  }

  public int a(MotionEvent paramMotionEvent)
  {
    return c(paramMotionEvent);
  }

  public int a(MotionEvent paramMotionEvent, int paramInt)
  {
    int i1 = (int)paramMotionEvent.getX();
    int i2 = (int)paramMotionEvent.getY();
    int i3 = this.C.pointToPosition(i1, i2);
    int i4 = this.C.getHeaderViewsCount();
    int i5 = this.C.getFooterViewsCount();
    int i6 = this.C.getCount();
    if ((i3 != -1) && (i3 >= i4) && (i3 < i6 - i5))
    {
      View localView1 = this.C.getChildAt(i3 - this.C.getFirstVisiblePosition());
      int i7 = (int)paramMotionEvent.getRawX();
      int i8 = (int)paramMotionEvent.getRawY();
      if (paramInt == 0);
      for (View localView2 = localView1; localView2 != null; localView2 = localView1.findViewById(paramInt))
      {
        localView2.getLocationOnScreen(this.r);
        if ((i7 <= this.r[0]) || (i8 <= this.r[1]) || (i7 >= this.r[0] + localView2.getWidth()) || (i8 >= this.r[1] + localView2.getHeight()))
          break;
        this.s = localView1.getLeft();
        this.t = localView1.getTop();
        return i3;
      }
    }
    return -1;
  }

  public void a(View paramView, Point paramPoint1, Point paramPoint2)
  {
    if ((this.j) && (this.k))
      this.D = paramPoint1.x;
  }

  public void a(boolean paramBoolean)
  {
    this.b = paramBoolean;
  }

  public boolean a(int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool1 = this.b;
    int i1 = 0;
    if (bool1)
    {
      boolean bool2 = this.k;
      i1 = 0;
      if (!bool2)
        i1 = 12;
    }
    if ((this.j) && (this.k))
      i1 = 0x2 | (i1 | 0x1);
    this.w = this.C.a(paramInt1 - this.C.getHeaderViewsCount(), i1, paramInt2, paramInt3);
    return this.w;
  }

  public int b(MotionEvent paramMotionEvent)
  {
    if (this.i == 1)
      return d(paramMotionEvent);
    return -1;
  }

  public void b(int paramInt)
  {
    this.a = paramInt;
  }

  public void b(boolean paramBoolean)
  {
    this.j = paramBoolean;
  }

  public boolean b()
  {
    return this.b;
  }

  public int c()
  {
    return this.i;
  }

  public int c(MotionEvent paramMotionEvent)
  {
    return a(paramMotionEvent, this.y);
  }

  public void c(int paramInt)
  {
    this.i = paramInt;
  }

  public int d(MotionEvent paramMotionEvent)
  {
    return a(paramMotionEvent, this.A);
  }

  public void d(int paramInt)
  {
    this.y = paramInt;
  }

  public boolean d()
  {
    return this.j;
  }

  public void e(int paramInt)
  {
    this.A = paramInt;
  }

  public void f(int paramInt)
  {
    this.z = paramInt;
  }

  public boolean onDown(MotionEvent paramMotionEvent)
  {
    if ((this.j) && (this.i == 0))
      this.q = a(paramMotionEvent, this.z);
    this.o = a(paramMotionEvent);
    if ((this.o != -1) && (this.a == 0))
      a(this.o, (int)paramMotionEvent.getX() - this.s, (int)paramMotionEvent.getY() - this.t);
    this.k = false;
    this.B = true;
    this.D = 0;
    this.p = b(paramMotionEvent);
    return true;
  }

  public final boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    return false;
  }

  public void onLongPress(MotionEvent paramMotionEvent)
  {
    if ((this.o != -1) && (this.a == 2))
    {
      this.C.performHapticFeedback(0);
      a(this.o, this.u - this.s, this.v - this.t);
    }
  }

  public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    int i1 = (int)paramMotionEvent1.getX();
    int i2 = (int)paramMotionEvent1.getY();
    int i3 = (int)paramMotionEvent2.getX();
    int i4 = (int)paramMotionEvent2.getY();
    int i5 = i3 - this.s;
    int i6 = i4 - this.t;
    if ((this.B) && (!this.w) && ((this.o != -1) || (this.p != -1)))
    {
      if (this.o == -1)
        break label178;
      if ((this.a != 1) || (Math.abs(i4 - i2) <= this.n) || (!this.b))
        break label129;
      a(this.o, i5, i6);
    }
    label129: label178: 
    do
    {
      do
      {
        do
          return false;
        while ((this.a == 0) || (Math.abs(i3 - i1) <= this.n) || (!this.j));
        this.k = true;
        a(this.p, i5, i6);
        return false;
      }
      while (this.p == -1);
      if ((Math.abs(i3 - i1) <= this.n) || (!this.j))
        continue;
      this.k = true;
      a(this.p, i5, i6);
      return false;
    }
    while (Math.abs(i4 - i2) <= this.n);
    this.B = false;
    return false;
  }

  public void onShowPress(MotionEvent paramMotionEvent)
  {
  }

  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
  {
    if ((this.j) && (this.i == 0) && (this.q != -1))
      this.C.a(this.q - this.C.getHeaderViewsCount());
    return true;
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if ((!this.C.c()) || (this.C.b()));
    do
    {
      return false;
      this.l.onTouchEvent(paramMotionEvent);
      if ((this.j) && (this.w) && (this.i == 1))
        this.m.onTouchEvent(paramMotionEvent);
      switch (0xFF & paramMotionEvent.getAction())
      {
      case 2:
      default:
        return false;
      case 0:
        this.u = (int)paramMotionEvent.getX();
        this.v = (int)paramMotionEvent.getY();
        return false;
      case 1:
      case 3:
      }
    }
    while ((!this.j) || (!this.k));
    if (this.D >= 0);
    for (int i1 = this.D; i1 > this.C.getWidth() / 2; i1 = -this.D)
    {
      this.C.a(true, 0.0F);
      return false;
    }
    this.k = false;
    this.w = false;
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.DragSortController
 * JD-Core Version:    0.6.0
 */