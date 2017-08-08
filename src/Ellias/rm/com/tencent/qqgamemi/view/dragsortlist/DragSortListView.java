package com.tencent.qqgamemi.view.dragsortlist;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.AbsListView.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;

public class DragSortListView extends ListView
{
  private static final int A = 1;
  private static final int B = 2;
  private static final int C = 3;
  private static final int D = 4;
  public static final int a = 1;
  private static final int ah = 0;
  private static final int ai = 1;
  private static final int aj = 2;
  private static final int as = 3;
  public static final int b = 2;
  public static final int c = 4;
  public static final int d = 8;
  private static final int z;
  private int E = 0;
  private int F = 1;
  private int G;
  private int H;
  private int I = 0;
  private View[] J = new View[1];
  private f K;
  private float L = 0.3333333F;
  private float M = 0.3333333F;
  private int N;
  private int O;
  private float P;
  private float Q;
  private float R;
  private float S;
  private float T = 0.5F;
  private DragSortListView.DragScrollProfile U = new b(this);
  private int V;
  private int W;
  private int Z;
  private boolean aA = false;
  private int aa;
  private int ab;
  private int ac = 0;
  private boolean ad = false;
  private boolean ae = false;
  private DragSortListView.FloatViewManager af = null;
  private MotionEvent ag;
  private int ak = 0;
  private float al = 0.25F;
  private float am = 0.0F;
  private d an;
  private boolean ao = false;
  private g ap;
  private boolean aq = false;
  private boolean ar = false;
  private i at = new i(this, 3);
  private k au;
  private j av;
  private h aw;
  private boolean ax;
  private float ay = 0.0F;
  private boolean az = false;
  private View e;
  private Point f = new Point();
  private Point g = new Point();
  private int h;
  private boolean i = false;
  private DataSetObserver j;
  private float k = 1.0F;
  private float l = 1.0F;
  private int m;
  private int n;
  private int o;
  private boolean p = false;
  private int q;
  private int r;
  private int s;
  private int t;
  private int u;
  private DragSortListView.DragListener v;
  private DragSortListView.DropListener w;
  private DragSortListView.RemoveListener x;
  private boolean y = true;

  public DragSortListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramAttributeSet);
  }

  public DragSortListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramAttributeSet);
  }

  private int a(int paramInt1, View paramView, int paramInt2, int paramInt3)
  {
    int i1 = f(paramInt1);
    int i2 = paramView.getHeight();
    int i3 = e(paramInt1, i1);
    int i5;
    int i4;
    if (paramInt1 != this.q)
    {
      i5 = i2 - i1;
      i4 = i3 - i1;
    }
    while (true)
    {
      int i6 = this.G;
      if ((this.q != this.n) && (this.q != this.o))
        i6 -= this.F;
      if (paramInt1 <= paramInt2)
      {
        if (paramInt1 > this.n)
          return 0 + (i6 - i4);
      }
      else
      {
        if (paramInt1 == paramInt3)
        {
          if (paramInt1 <= this.n)
            return 0 + (i5 - i6);
          if (paramInt1 == this.o)
            return 0 + (i2 - i3);
          return 0 + i5;
        }
        if (paramInt1 <= this.n)
          return 0 - i6;
        if (paramInt1 == this.o)
          return 0 - i4;
      }
      return 0;
      i4 = i3;
      i5 = i2;
    }
  }

  private static int a(SparseBooleanArray paramSparseBooleanArray, int paramInt)
  {
    int i1 = 0;
    int i2 = paramSparseBooleanArray.size();
    if (i2 - i1 > 0)
    {
      int i3 = i1 + i2 >> 1;
      if (paramSparseBooleanArray.keyAt(i3) < paramInt);
      for (int i4 = i3 + 1; ; i4 = i1)
      {
        i1 = i4;
        break;
        i2 = i3;
      }
    }
    return i1;
  }

  private static int a(SparseBooleanArray paramSparseBooleanArray, int paramInt1, int paramInt2)
  {
    int i1 = paramSparseBooleanArray.size();
    for (int i2 = a(paramSparseBooleanArray, paramInt1); (i2 < i1) && (paramSparseBooleanArray.keyAt(i2) < paramInt2) && (!paramSparseBooleanArray.valueAt(i2)); i2++);
    if ((i2 == i1) || (paramSparseBooleanArray.keyAt(i2) >= paramInt2))
      i2 = -1;
    return i2;
  }

  private static int a(SparseBooleanArray paramSparseBooleanArray, int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    int i1 = a(paramSparseBooleanArray, paramInt1, paramInt2);
    if (i1 == -1)
      return 0;
    int i2 = paramSparseBooleanArray.keyAt(i1);
    int i3 = i2 + 1;
    int i4 = i1 + 1;
    int i5 = 0;
    if (i4 < paramSparseBooleanArray.size())
    {
      int i7 = paramSparseBooleanArray.keyAt(i4);
      if (i7 < paramInt2)
      {
        if (!paramSparseBooleanArray.valueAt(i4));
        while (true)
        {
          i4++;
          break;
          if (i7 == i3)
          {
            i3++;
            continue;
          }
          paramArrayOfInt1[i5] = i2;
          paramArrayOfInt2[i5] = i3;
          i5++;
          i3 = i7 + 1;
          i2 = i7;
        }
      }
    }
    if (i3 == paramInt2)
      i3 = paramInt1;
    paramArrayOfInt1[i5] = i2;
    paramArrayOfInt2[i5] = i3;
    int i6 = i5 + 1;
    if ((i6 > 1) && (paramArrayOfInt1[0] == paramInt1) && (paramArrayOfInt2[(i6 - 1)] == paramInt1))
    {
      paramArrayOfInt1[0] = paramArrayOfInt1[(i6 - 1)];
      i6--;
    }
    return i6;
  }

  private void a(int paramInt, Canvas paramCanvas)
  {
    Drawable localDrawable = getDivider();
    int i1 = getDividerHeight();
    ViewGroup localViewGroup;
    int i2;
    int i3;
    int i4;
    int i6;
    int i5;
    if ((localDrawable != null) && (i1 != 0))
    {
      localViewGroup = (ViewGroup)getChildAt(paramInt - getFirstVisiblePosition());
      if (localViewGroup != null)
      {
        i2 = getPaddingLeft();
        i3 = getWidth() - getPaddingRight();
        i4 = localViewGroup.getChildAt(0).getHeight();
        if (paramInt <= this.q)
          break label133;
        i6 = i4 + localViewGroup.getTop();
        i5 = i6 + i1;
      }
    }
    while (true)
    {
      paramCanvas.save();
      paramCanvas.clipRect(i2, i6, i3, i5);
      localDrawable.setBounds(i2, i6, i3, i5);
      localDrawable.draw(paramCanvas);
      paramCanvas.restore();
      return;
      label133: i5 = localViewGroup.getBottom() - i4;
      i6 = i5 - i1;
    }
  }

  private void a(int paramInt, View paramView, boolean paramBoolean)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    int i1;
    if ((paramInt != this.q) && (paramInt != this.n) && (paramInt != this.o))
    {
      i1 = -2;
      if (i1 != localLayoutParams.height)
      {
        localLayoutParams.height = i1;
        paramView.setLayoutParams(localLayoutParams);
      }
      if ((paramInt == this.n) || (paramInt == this.o))
      {
        if (paramInt >= this.q)
          break label154;
        ((DragSortItemView)paramView).setGravity(80);
      }
    }
    while (true)
    {
      int i2 = paramView.getVisibility();
      int i3 = this.q;
      int i4 = 0;
      if (paramInt == i3)
      {
        View localView = this.e;
        i4 = 0;
        if (localView != null)
          i4 = 4;
      }
      if (i4 != i2)
        paramView.setVisibility(i4);
      return;
      i1 = c(paramInt, paramView, paramBoolean);
      break;
      label154: if (paramInt <= this.q)
        continue;
      ((DragSortItemView)paramView).setGravity(48);
    }
  }

  private void a(AttributeSet paramAttributeSet)
  {
    this.ap = new g(this);
    this.K = new f(this);
    this.au = new k(this, 0.5F, 150);
    this.aw = new h(this, 0.5F, 150);
    this.ag = MotionEvent.obtain(0L, 0L, 3, 0.0F, 0.0F, 0.0F, 0.0F, 0, 0.0F, 0.0F, 0, 0);
    this.j = new c(this);
  }

  private void a(View paramView)
  {
    Object localObject = paramView.getLayoutParams();
    if (localObject == null)
    {
      localObject = new AbsListView.LayoutParams(-1, -2);
      paramView.setLayoutParams((ViewGroup.LayoutParams)localObject);
    }
    int i1 = ViewGroup.getChildMeasureSpec(this.I, getListPaddingLeft() + getListPaddingRight(), ((ViewGroup.LayoutParams)localObject).width);
    if (((ViewGroup.LayoutParams)localObject).height > 0);
    for (int i2 = View.MeasureSpec.makeMeasureSpec(((ViewGroup.LayoutParams)localObject).height, 1073741824); ; i2 = View.MeasureSpec.makeMeasureSpec(0, 0))
    {
      paramView.measure(i1, i2);
      return;
    }
  }

  private static int b(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i1 = paramInt4 - paramInt3;
    int i2 = paramInt1 + paramInt2;
    if (i2 < paramInt3)
      i2 += i1;
    do
      return i2;
    while (i2 < paramInt4);
    return i2 - i1;
  }

  private int b(int paramInt, View paramView, boolean paramBoolean)
  {
    int i1 = this.q;
    int i2 = 0;
    if (paramInt == i1);
    do
    {
      return i2;
      if ((paramInt < getHeaderViewsCount()) || (paramInt >= getCount() - getFooterViewsCount()));
      while (true)
      {
        ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
        if ((localLayoutParams == null) || (localLayoutParams.height <= 0))
          break;
        return localLayoutParams.height;
        paramView = ((ViewGroup)paramView).getChildAt(0);
      }
      i2 = paramView.getHeight();
    }
    while ((i2 != 0) && (!paramBoolean));
    a(paramView);
    return paramView.getMeasuredHeight();
  }

  private void b(MotionEvent paramMotionEvent)
  {
    int i1 = 0xFF & paramMotionEvent.getAction();
    if (i1 != 0)
    {
      this.Z = this.V;
      this.aa = this.W;
    }
    this.V = (int)paramMotionEvent.getX();
    this.W = (int)paramMotionEvent.getY();
    if (i1 == 0)
    {
      this.Z = this.V;
      this.aa = this.W;
    }
    this.t = ((int)paramMotionEvent.getRawX() - this.V);
    this.u = ((int)paramMotionEvent.getRawY() - this.W);
  }

  private void b(boolean paramBoolean)
  {
    int i1 = getFirstVisiblePosition() + getChildCount() / 2;
    View localView = getChildAt(getChildCount() / 2);
    if (localView == null)
      return;
    d(i1, localView, paramBoolean);
  }

  private int c(int paramInt)
  {
    View localView = getChildAt(paramInt - getFirstVisiblePosition());
    if (localView != null)
      return localView.getHeight();
    return e(paramInt, f(paramInt));
  }

  private int c(int paramInt1, int paramInt2)
  {
    int i1 = getHeaderViewsCount();
    int i2 = getFooterViewsCount();
    if ((paramInt1 <= i1) || (paramInt1 >= getCount() - i2))
      return paramInt2;
    int i3 = getDividerHeight();
    int i4 = this.G - this.F;
    int i5 = f(paramInt1);
    int i6 = c(paramInt1);
    if (this.o <= this.q)
      if ((paramInt1 == this.o) && (this.n != this.o))
        if (paramInt1 == this.q)
          paramInt2 = paramInt2 + i6 - this.G;
    while (paramInt1 <= this.q)
    {
      return paramInt2 + (this.G - i3 - f(paramInt1 - 1)) / 2;
      paramInt2 = paramInt2 + (i6 - i5) - i4;
      continue;
      if ((paramInt1 <= this.o) || (paramInt1 > this.q))
        continue;
      paramInt2 -= i4;
      continue;
      if ((paramInt1 > this.q) && (paramInt1 <= this.n))
      {
        paramInt2 += i4;
        continue;
      }
      if ((paramInt1 != this.o) || (this.n == this.o))
        continue;
      paramInt2 += i6 - i5;
    }
    return paramInt2 + (i5 - i3 - this.G) / 2;
  }

  private int c(int paramInt, View paramView, boolean paramBoolean)
  {
    return e(paramInt, b(paramInt, paramView, paramBoolean));
  }

  private void d()
  {
    Log.d("mobeta", "mSrcPos=" + this.q + " mFirstExpPos=" + this.n + " mSecondExpPos=" + this.o);
  }

  private void d(int paramInt)
  {
    this.E = 1;
    if (this.x != null)
      this.x.a(paramInt);
    p();
    i();
    f();
    if (this.ae)
    {
      this.E = 3;
      return;
    }
    this.E = 0;
  }

  private void d(int paramInt1, int paramInt2)
  {
    this.f.x = (paramInt1 - this.r);
    this.f.y = (paramInt2 - this.s);
    b(true);
    int i1 = Math.min(paramInt2, this.h + this.H);
    int i2 = Math.max(paramInt2, this.h - this.H);
    int i3 = this.K.b();
    if ((i1 > this.aa) && (i1 > this.O) && (i3 != 1))
    {
      if (i3 != -1)
        this.K.a(true);
      this.K.a(1);
    }
    do
    {
      return;
      if ((i2 >= this.aa) || (i2 >= this.N) || (i3 == 0))
        continue;
      if (i3 != -1)
        this.K.a(true);
      this.K.a(0);
      return;
    }
    while ((i2 < this.N) || (i1 > this.O) || (!this.K.a()));
    this.K.a(true);
  }

  private void d(int paramInt, View paramView, boolean paramBoolean)
  {
    this.aq = true;
    o();
    int i1 = this.n;
    int i2 = this.o;
    boolean bool = e();
    if (bool)
    {
      l();
      setSelectionFromTop(paramInt, a(paramInt, paramView, i1, i2) + paramView.getTop() - getPaddingTop());
      layoutChildren();
    }
    if ((bool) || (paramBoolean))
      invalidate();
    this.aq = false;
  }

  private int e(int paramInt1, int paramInt2)
  {
    getDividerHeight();
    int i1;
    int i2;
    int i3;
    if ((this.p) && (this.n != this.o))
    {
      i1 = 1;
      i2 = this.G - this.F;
      i3 = (int)(this.am * i2);
      if (paramInt1 != this.q)
        break label117;
      if (this.q != this.n)
        break label93;
      if (i1 == 0)
        break label88;
      paramInt2 = i3 + this.F;
    }
    label88: label93: label117: 
    do
    {
      return paramInt2;
      i1 = 0;
      break;
      return this.G;
      if (this.q == this.o)
        return this.G - i3;
      return this.F;
      if (paramInt1 != this.n)
        continue;
      if (i1 != 0)
        return paramInt2 + i3;
      return paramInt2 + i2;
    }
    while (paramInt1 != this.o);
    return paramInt2 + i2 - i3;
  }

  private void e(int paramInt)
  {
    View localView = getChildAt(paramInt - getFirstVisiblePosition());
    if (localView != null)
      a(paramInt, localView, false);
  }

  private boolean e()
  {
    int i1 = getFirstVisiblePosition();
    int i2 = this.n;
    View localView = getChildAt(i2 - i1);
    if (localView == null)
    {
      i2 = i1 + getChildCount() / 2;
      localView = getChildAt(i2 - i1);
    }
    int i3 = localView.getTop();
    int i4 = localView.getHeight();
    int i5 = c(i2, i3);
    int i6 = getDividerHeight();
    int i23;
    int i10;
    int i11;
    int i12;
    int i24;
    label121: int i13;
    int i14;
    label177: float f2;
    int i22;
    if (this.h < i5)
    {
      i23 = i3;
      i10 = i5;
      i11 = i2;
      i12 = i5;
      if (i11 >= 0)
      {
        i11--;
        i24 = c(i11);
        if (i11 == 0)
          i10 = i23 - i6 - i24;
      }
      else
      {
        i13 = getHeaderViewsCount();
        i14 = getFooterViewsCount();
        int i15 = this.n;
        int i16 = this.o;
        float f1 = this.am;
        if (!this.p)
          break label569;
        int i18 = Math.abs(i10 - i12);
        if (this.h >= i10)
          break label493;
        int i20 = (int)(0.5F * this.al * i18);
        f2 = i20;
        int i21 = i12 + i20;
        i22 = i10 - i20;
        if (this.h >= i21)
          break label508;
        this.n = (i11 - 1);
        this.o = i11;
        this.am = (0.5F * (i21 - this.h) / f2);
        label251: if (this.n >= i13)
          break label584;
        this.n = i13;
        this.o = i13;
        i11 = i13;
        label276: if ((this.n == i15) && (this.o == i16) && (this.am == f1))
          break label627;
      }
    }
    label491: label493: label627: for (int i17 = 1; ; i17 = 0)
    {
      if (i11 != this.m)
      {
        if (this.v != null)
          this.v.b(this.m - i13, i11 - i13);
        this.m = i11;
        return true;
        i23 -= i24 + i6;
        i10 = c(i11, i23);
        if (this.h >= i10)
          break label121;
        i12 = i10;
        break;
        int i7 = getCount();
        int i8 = i4;
        int i9 = i3;
        i10 = i5;
        i11 = i2;
        for (i12 = i5; ; i12 = i10)
        {
          if (i11 >= i7)
            break label491;
          if (i11 == i7 - 1)
          {
            i10 = i8 + (i9 + i6);
            break;
          }
          i9 += i6 + i8;
          i8 = c(i11 + 1);
          i10 = c(i11 + 1, i9);
          if (this.h < i10)
            break;
          i11++;
        }
        break label121;
        int i19 = i12;
        i12 = i10;
        i10 = i19;
        break label177;
        label508: if (this.h < i22)
        {
          this.n = i11;
          this.o = i11;
          break label251;
        }
        this.n = i11;
        this.o = (i11 + 1);
        this.am = (0.5F * (1.0F + (i10 - this.h) / f2));
        break label251;
        this.n = i11;
        this.o = i11;
        break label251;
        if (this.o < getCount() - i14)
          break label276;
        i11 = -1 + (getCount() - i14);
        this.n = i11;
        this.o = i11;
        break label276;
      }
      return i17;
    }
  }

  private int f(int paramInt)
  {
    int i1 = this.q;
    int i2 = 0;
    if (paramInt == i1);
    do
    {
      return i2;
      View localView1 = getChildAt(paramInt - getFirstVisiblePosition());
      if (localView1 != null)
        return b(paramInt, localView1, false);
      i2 = this.at.a(paramInt);
    }
    while (i2 != -1);
    ListAdapter localListAdapter = getAdapter();
    int i3 = localListAdapter.getItemViewType(paramInt);
    int i4 = localListAdapter.getViewTypeCount();
    if (i4 != this.J.length)
      this.J = new View[i4];
    View localView2;
    if (i3 >= 0)
      if (this.J[i3] == null)
      {
        localView2 = localListAdapter.getView(paramInt, null, this);
        this.J[i3] = localView2;
      }
    while (true)
    {
      int i5 = b(paramInt, localView2, true);
      this.at.a(paramInt, i5);
      return i5;
      localView2 = localListAdapter.getView(paramInt, this.J[i3], this);
      continue;
      localView2 = localListAdapter.getView(paramInt, null, this);
    }
  }

  private void f()
  {
    this.q = -1;
    this.n = -1;
    this.o = -1;
    this.m = -1;
  }

  private void g()
  {
    this.E = 2;
    if ((this.w != null) && (this.m >= 0) && (this.m < getCount()))
    {
      int i1 = getHeaderViewsCount();
      this.w.a(this.q - i1, this.m - i1);
    }
    p();
    i();
    f();
    l();
    if (this.ae)
    {
      this.E = 3;
      return;
    }
    this.E = 0;
  }

  private void h()
  {
    d(this.q - getHeaderViewsCount());
  }

  private void i()
  {
    int i1 = getFirstVisiblePosition();
    if (this.q < i1)
    {
      View localView = getChildAt(0);
      int i2 = 0;
      if (localView != null)
        i2 = localView.getTop();
      setSelectionFromTop(i1 - 1, i2 - getPaddingTop());
    }
  }

  private void j()
  {
    this.ak = 0;
    this.ae = false;
    if (this.E == 3)
      this.E = 0;
    this.l = this.k;
    this.az = false;
    this.at.a();
  }

  private void k()
  {
    int i1 = getPaddingTop();
    int i2 = getHeight() - i1 - getPaddingBottom();
    float f1 = i2;
    this.Q = (i1 + f1 * this.L);
    this.P = (i1 + f1 * (1.0F - this.M));
    this.N = (int)this.Q;
    this.O = (int)this.P;
    this.R = (this.Q - i1);
    this.S = (i1 + i2 - this.P);
  }

  private void l()
  {
    int i1 = getFirstVisiblePosition();
    int i2 = getLastVisiblePosition();
    int i3 = Math.max(0, getHeaderViewsCount() - i1);
    int i4 = Math.min(i2 - i1, -1 + getCount() - getFooterViewsCount() - i1);
    while (i3 <= i4)
    {
      View localView = getChildAt(i3);
      if (localView != null)
        a(i1 + i3, localView, false);
      i3++;
    }
  }

  private void m()
  {
    if (this.e != null)
    {
      a(this.e);
      this.G = this.e.getMeasuredHeight();
      this.H = (this.G / 2);
    }
  }

  private void n()
  {
    this.aA = true;
  }

  private void o()
  {
    if (this.af != null)
    {
      this.g.set(this.V, this.W);
      this.af.a(this.e, this.f, this.g);
    }
    int i1 = this.f.x;
    int i2 = this.f.y;
    int i3 = getPaddingLeft();
    int i9;
    if (((0x1 & this.ac) == 0) && (i1 > i3))
    {
      this.f.x = i3;
      int i4 = getHeaderViewsCount();
      int i5 = getFooterViewsCount();
      int i6 = getFirstVisiblePosition();
      int i7 = getLastVisiblePosition();
      int i8 = getPaddingTop();
      if (i6 < i4)
        i8 = getChildAt(-1 + (i4 - i6)).getBottom();
      if (((0x8 & this.ac) == 0) && (i6 <= this.q))
        i8 = Math.max(getChildAt(this.q - i6).getTop(), i8);
      i9 = getHeight() - getPaddingBottom();
      if (i7 >= -1 + (getCount() - i5))
        i9 = getChildAt(-1 + (getCount() - i5) - i6).getBottom();
      if (((0x4 & this.ac) == 0) && (i7 >= this.q))
        i9 = Math.min(getChildAt(this.q - i6).getBottom(), i9);
      if (i2 >= i8)
        break label321;
      this.f.y = i8;
    }
    while (true)
    {
      this.h = (this.f.y + this.H);
      return;
      if (((0x2 & this.ac) != 0) || (i1 >= i3))
        break;
      this.f.x = i3;
      break;
      label321: if (i2 + this.G <= i9)
        continue;
      this.f.y = (i9 - this.G);
    }
  }

  private void p()
  {
    if (this.e != null)
    {
      this.e.setVisibility(8);
      if (this.af != null)
        this.af.a(this.e);
      this.e = null;
      invalidate();
    }
  }

  public void a()
  {
    if (this.E == 4)
    {
      this.K.a(true);
      p();
      f();
      l();
      if (this.ae)
        this.E = 3;
    }
    else
    {
      return;
    }
    this.E = 0;
  }

  public void a(float paramFloat1, float paramFloat2)
  {
    if (paramFloat2 > 0.5F)
    {
      this.M = 0.5F;
      if (paramFloat1 <= 0.5F)
        break label46;
    }
    label46: for (this.L = 0.5F; ; this.L = paramFloat1)
    {
      if (getHeight() != 0)
        k();
      return;
      this.M = paramFloat2;
      break;
    }
  }

  public void a(int paramInt)
  {
    this.ax = false;
    a(paramInt, 0.0F);
  }

  public void a(int paramInt, float paramFloat)
  {
    if ((this.E == 0) || (this.E == 4))
    {
      if (this.E == 0)
      {
        this.q = (paramInt + getHeaderViewsCount());
        this.n = this.q;
        this.o = this.q;
        this.m = this.q;
        View localView = getChildAt(this.q - getFirstVisiblePosition());
        if (localView != null)
          localView.setVisibility(4);
      }
      this.E = 1;
      this.ay = paramFloat;
      if (this.ae)
        switch (this.ak)
        {
        default:
        case 1:
        case 2:
        }
    }
    while (this.au != null)
    {
      this.au.c();
      return;
      super.onTouchEvent(this.ag);
      continue;
      super.onInterceptTouchEvent(this.ag);
    }
    d(paramInt);
  }

  public void a(int paramInt1, int paramInt2)
  {
    if (this.w != null)
    {
      int i1 = getInputAdapter().getCount();
      if ((paramInt1 >= 0) && (paramInt1 < i1) && (paramInt2 >= 0) && (paramInt2 < i1))
        this.w.a(paramInt1, paramInt2);
    }
  }

  public boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((!this.ae) || (this.af == null));
    View localView;
    do
    {
      return false;
      localView = this.af.a(paramInt1);
    }
    while (localView == null);
    return a(paramInt1, localView, paramInt2, paramInt3, paramInt4);
  }

  public boolean a(int paramInt1, View paramView, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool = true;
    if ((this.E != 0) || (!this.ae) || (this.e != null) || (paramView == null) || (!this.y))
    {
      bool = false;
      return bool;
    }
    if (getParent() != null)
      getParent().requestDisallowInterceptTouchEvent(bool);
    int i1 = paramInt1 + getHeaderViewsCount();
    this.n = i1;
    this.o = i1;
    this.q = i1;
    this.m = i1;
    this.E = 4;
    this.ac = 0;
    this.ac = (paramInt2 | this.ac);
    this.e = paramView;
    m();
    this.r = paramInt3;
    this.s = paramInt4;
    this.ab = this.W;
    this.f.x = (this.V - this.r);
    this.f.y = (this.W - this.s);
    View localView = getChildAt(this.q - getFirstVisiblePosition());
    if (localView != null)
      localView.setVisibility(4);
    if (this.ao)
      this.ap.a();
    switch (this.ak)
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      requestLayout();
      if (this.av == null)
        break;
      this.av.c();
      return bool;
      super.onTouchEvent(this.ag);
      continue;
      super.onInterceptTouchEvent(this.ag);
    }
  }

  protected boolean a(MotionEvent paramMotionEvent)
  {
    (0xFF & paramMotionEvent.getAction());
    switch (0xFF & paramMotionEvent.getAction())
    {
    default:
    case 3:
    case 1:
    case 2:
    }
    while (true)
    {
      return true;
      if (this.E == 4)
        a();
      j();
      continue;
      if (this.E == 4)
        a(false);
      j();
      continue;
      d((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
    }
  }

  public boolean a(boolean paramBoolean)
  {
    this.ax = false;
    return b(paramBoolean, 0.0F);
  }

  public boolean a(boolean paramBoolean, float paramFloat)
  {
    this.ax = true;
    return b(paramBoolean, paramFloat);
  }

  public void b(int paramInt)
  {
    SparseBooleanArray localSparseBooleanArray = getCheckedItemPositions();
    if (localSparseBooleanArray.size() == 0);
    while (true)
    {
      return;
      int[] arrayOfInt1 = new int[localSparseBooleanArray.size()];
      int[] arrayOfInt2 = new int[localSparseBooleanArray.size()];
      int i1 = 1 + localSparseBooleanArray.keyAt(-1 + localSparseBooleanArray.size());
      int i2 = a(localSparseBooleanArray, paramInt, i1, arrayOfInt1, arrayOfInt2);
      for (int i3 = 0; i3 != i2; i3++)
      {
        if ((arrayOfInt1[i3] != paramInt) && ((arrayOfInt2[i3] >= arrayOfInt1[i3]) || (arrayOfInt2[i3] <= paramInt)))
          setItemChecked(b(arrayOfInt1[i3], -1, paramInt, i1), true);
        setItemChecked(b(arrayOfInt2[i3], -1, paramInt, i1), false);
      }
    }
  }

  public void b(int paramInt1, int paramInt2)
  {
    SparseBooleanArray localSparseBooleanArray = getCheckedItemPositions();
    int i1;
    if (paramInt2 < paramInt1)
      i1 = paramInt1;
    for (int i2 = paramInt2; ; i2 = paramInt1)
    {
      int i3 = i1 + 1;
      int[] arrayOfInt1 = new int[localSparseBooleanArray.size()];
      int[] arrayOfInt2 = new int[localSparseBooleanArray.size()];
      int i4 = a(localSparseBooleanArray, i2, i3, arrayOfInt1, arrayOfInt2);
      if ((i4 == 1) && (arrayOfInt1[0] == arrayOfInt2[0]));
      while (true)
      {
        return;
        if (paramInt1 < paramInt2)
        {
          for (int i6 = 0; i6 != i4; i6++)
          {
            setItemChecked(b(arrayOfInt1[i6], -1, i2, i3), true);
            setItemChecked(b(arrayOfInt2[i6], -1, i2, i3), false);
          }
          continue;
        }
        for (int i5 = 0; i5 != i4; i5++)
        {
          setItemChecked(arrayOfInt1[i5], false);
          setItemChecked(arrayOfInt2[i5], true);
        }
      }
      i1 = paramInt2;
    }
  }

  public boolean b()
  {
    return this.az;
  }

  public boolean b(boolean paramBoolean, float paramFloat)
  {
    if (this.e != null)
    {
      this.K.a(true);
      if (paramBoolean)
        a(this.q - getHeaderViewsCount(), paramFloat);
      while (true)
      {
        if (this.ao)
          this.ap.d();
        return true;
        if (this.aw != null)
        {
          this.aw.c();
          continue;
        }
        g();
      }
    }
    return false;
  }

  public boolean c()
  {
    return this.y;
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    if (this.E != 0)
    {
      if (this.n != this.q)
        a(this.n, paramCanvas);
      if ((this.o != this.n) && (this.o != this.q))
        a(this.o, paramCanvas);
    }
    int i1;
    int i2;
    float f1;
    if (this.e != null)
    {
      i1 = this.e.getWidth();
      i2 = this.e.getHeight();
      int i3 = this.f.x;
      int i4 = getWidth();
      if (i3 < 0)
        i3 = -i3;
      if (i3 >= i4)
        break label217;
      float f2 = (i4 - i3) / i4;
      f1 = f2 * f2;
    }
    while (true)
    {
      int i5 = (int)(f1 * (255.0F * this.l));
      paramCanvas.save();
      paramCanvas.translate(this.f.x, this.f.y);
      paramCanvas.clipRect(0, 0, i1, i2);
      paramCanvas.saveLayerAlpha(0.0F, 0.0F, i1, i2, i5, 31);
      this.e.draw(paramCanvas);
      paramCanvas.restore();
      paramCanvas.restore();
      return;
      label217: f1 = 0.0F;
    }
  }

  public float getFloatAlpha()
  {
    return this.l;
  }

  public ListAdapter getInputAdapter()
  {
    if (this.an == null)
      return null;
    return this.an.a();
  }

  protected void layoutChildren()
  {
    super.layoutChildren();
    if (this.e != null)
    {
      if ((this.e.isLayoutRequested()) && (!this.i))
        m();
      this.e.layout(0, 0, this.e.getMeasuredWidth(), this.e.getMeasuredHeight());
      this.i = false;
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.ao)
      this.ap.b();
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!this.y)
      return super.onInterceptTouchEvent(paramMotionEvent);
    b(paramMotionEvent);
    this.ad = true;
    int i1 = 0xFF & paramMotionEvent.getAction();
    if (i1 == 0)
    {
      if (this.E != 0)
      {
        this.ar = true;
        return true;
      }
      this.ae = true;
    }
    if (this.e != null)
    {
      i2 = 1;
      if ((i1 == 1) || (i1 == 3))
        this.ae = false;
      return i2;
    }
    if (super.onInterceptTouchEvent(paramMotionEvent))
      this.az = true;
    for (int i2 = 1; ; i2 = 0)
      switch (i1)
      {
      case 2:
      default:
        if (i2 != 0)
          this.ak = 1;
        break;
      case 1:
      case 3:
        j();
        break;
        this.ak = 2;
        break;
      }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (this.e != null)
    {
      if (this.e.isLayoutRequested())
        m();
      this.i = true;
    }
    this.I = paramInt1;
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    k();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i1 = 0;
    if (this.ar)
      this.ar = false;
    do
    {
      return i1;
      if (!this.y)
        return super.onTouchEvent(paramMotionEvent);
      boolean bool1 = this.ad;
      this.ad = false;
      if (!bool1)
        b(paramMotionEvent);
      if (this.E == 4)
      {
        a(paramMotionEvent);
        return true;
      }
      int i2 = this.E;
      i1 = 0;
      if (i2 == 0)
      {
        boolean bool2 = super.onTouchEvent(paramMotionEvent);
        i1 = 0;
        if (bool2)
          i1 = 1;
      }
      switch (0xFF & paramMotionEvent.getAction())
      {
      case 2:
      default:
      case 1:
      case 3:
      }
    }
    while (i1 == 0);
    this.ak = 1;
    return i1;
    j();
    return i1;
  }

  public void requestLayout()
  {
    if (!this.aq)
      super.requestLayout();
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (paramListAdapter != null)
    {
      this.an = new d(this, paramListAdapter);
      paramListAdapter.registerDataSetObserver(this.j);
      if ((paramListAdapter instanceof DragSortListView.DropListener))
        setDropListener((DragSortListView.DropListener)paramListAdapter);
      if ((paramListAdapter instanceof DragSortListView.DragListener))
        setDragListener((DragSortListView.DragListener)paramListAdapter);
      if ((paramListAdapter instanceof DragSortListView.RemoveListener))
        setRemoveListener((DragSortListView.RemoveListener)paramListAdapter);
    }
    while (true)
    {
      super.setAdapter(this.an);
      return;
      this.an = null;
    }
  }

  public void setCollapsedHeight(int paramInt)
  {
    this.F = Math.max(1, paramInt);
  }

  public void setDragEnabled(boolean paramBoolean)
  {
    this.y = paramBoolean;
  }

  public void setDragListener(DragSortListView.DragListener paramDragListener)
  {
    this.v = paramDragListener;
  }

  public void setDragScrollProfile(DragSortListView.DragScrollProfile paramDragScrollProfile)
  {
    if (paramDragScrollProfile != null)
      this.U = paramDragScrollProfile;
  }

  public void setDragScrollStart(float paramFloat)
  {
    a(paramFloat, paramFloat);
  }

  public void setDragSortListener(DragSortListView.DragSortListener paramDragSortListener)
  {
    setDropListener(paramDragSortListener);
    setDragListener(paramDragSortListener);
    setRemoveListener(paramDragSortListener);
  }

  public void setDropListener(DragSortListView.DropListener paramDropListener)
  {
    this.w = paramDropListener;
  }

  public void setFloatAlpha(float paramFloat)
  {
    this.l = paramFloat;
  }

  public void setFloatViewManager(DragSortListView.FloatViewManager paramFloatViewManager)
  {
    this.af = paramFloatViewManager;
  }

  public void setMaxScrollSpeed(float paramFloat)
  {
    this.T = paramFloat;
  }

  public void setRemoveListener(DragSortListView.RemoveListener paramRemoveListener)
  {
    this.x = paramRemoveListener;
  }

  public void setSlideShuffleSpeed(float paramFloat)
  {
    this.al = Math.max(0.0F, Math.min(1.0F, 1.0F - paramFloat));
    if (this.al > 0.0F);
    for (boolean bool = true; ; bool = false)
    {
      this.p = bool;
      return;
    }
  }

  public void setTrackDragSort(boolean paramBoolean)
  {
    this.ao = paramBoolean;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.DragSortListView
 * JD-Core Version:    0.6.0
 */