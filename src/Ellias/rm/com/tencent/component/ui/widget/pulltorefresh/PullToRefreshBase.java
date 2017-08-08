package com.tencent.component.ui.widget.pulltorefresh;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.CycleInterpolator;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public abstract class PullToRefreshBase extends LinearLayout
{
  static final boolean b = false;
  static final String c = "PullToRefresh";
  protected static final float d = 2.0F;
  protected static final int e = 0;
  protected static final int f = 1;
  protected static final int g = 2;
  protected static final int h = 3;
  protected static final PullToRefreshBase.Mode i = PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH;
  protected static final String j = "ptr_state";
  protected static final String k = "ptr_mode";
  protected static final String l = "ptr_current_mode";
  protected static final String m = "ptr_disable_scrolling";
  protected static final String n = "ptr_show_refreshing_view";
  protected static final String o = "ptr_super";
  protected static final int p = 300;
  protected static final int q = 600;
  protected static final int r = 600;
  protected View A;
  protected boolean B = true;
  protected boolean C = true;
  protected boolean D = true;
  protected boolean E = true;
  protected LoadingLayout F;
  protected LoadingLayout G;
  protected int H;
  protected int I;
  protected PullToRefreshBase.OnRefreshListener J;
  protected PullToRefreshBase.OnRefreshListener2 K;
  protected PullToRefreshBase.OnPullEventListener L;
  protected PullToRefreshBase.OnScrollChangedListener M;
  protected PullToRefreshBase.OnScrollChangedListener2 N;
  protected PullToRefreshBase.OnDispatchTouchEventListener O;
  protected e P;
  protected int Q;
  protected int R;
  protected int S;
  protected int T;
  protected final PullToRefreshOptions U = new PullToRefreshOptions();
  protected int s;
  protected float t;
  protected float u;
  protected float v;
  protected boolean w = false;
  protected int x = 0;
  protected PullToRefreshBase.Mode y = i;
  protected PullToRefreshBase.Mode z;

  @PluginApi(a=6)
  public PullToRefreshBase(Context paramContext)
  {
    super(paramContext);
    b(paramContext, null);
  }

  @PluginApi(a=6)
  public PullToRefreshBase(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    b(paramContext, paramAttributeSet);
  }

  @PluginApi(a=6)
  public PullToRefreshBase(Context paramContext, PullToRefreshBase.Mode paramMode)
  {
    super(paramContext);
    this.y = paramMode;
    b(paramContext, null);
  }

  protected static int a(int paramInt1, int paramInt2)
  {
    if (paramInt1 > paramInt2)
      return paramInt1;
    return paramInt2;
  }

  protected static int b(int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2)
      return paramInt1;
    return paramInt2;
  }

  protected abstract View a(Context paramContext, AttributeSet paramAttributeSet);

  protected void a(float paramFloat)
  {
    if (this.L != null)
      if (getCurrentMode() != PullToRefreshBase.Mode.PULL_UP_TO_REFRESH)
        break label34;
    label34: for (PullToRefreshBase.Direction localDirection = PullToRefreshBase.Direction.FROM_END; ; localDirection = PullToRefreshBase.Direction.FROM_START)
    {
      this.L.onPullChanged(this, localDirection, paramFloat);
      return;
    }
  }

  protected final void a(int paramInt)
  {
    if (this.P != null)
      this.P.a();
    if (getScrollY() != paramInt)
    {
      this.P = new e(this, getScrollY(), paramInt, 300);
      post(this.P);
    }
  }

  protected void a(Context paramContext, View paramView)
  {
    a(paramView, new LinearLayout.LayoutParams(-1, 0, 1.0F));
  }

  protected void a(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    if (localLayoutParams == null)
      localLayoutParams = new ViewGroup.LayoutParams(-1, -2);
    int i1 = ViewGroup.getChildMeasureSpec(0, 0, localLayoutParams.width);
    int i2 = localLayoutParams.height;
    if (i2 > 0);
    for (int i3 = View.MeasureSpec.makeMeasureSpec(i2, 1073741824); ; i3 = View.MeasureSpec.makeMeasureSpec(0, 0))
    {
      paramView.measure(i1, i3);
      return;
    }
  }

  public final void a(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.addView(paramView, paramInt, paramLayoutParams);
  }

  public final void a(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.addView(paramView, -1, paramLayoutParams);
  }

  public void a(View paramView, LinearLayout.LayoutParams paramLayoutParams)
  {
    this.F.addSpaceView(paramView, paramLayoutParams);
  }

  protected void a(boolean paramBoolean)
  {
  }

  protected abstract boolean a();

  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    View localView = getRefreshableView();
    if ((localView instanceof ViewGroup))
    {
      ((ViewGroup)localView).addView(paramView, paramInt, paramLayoutParams);
      return;
    }
    throw new UnsupportedOperationException("Refreshable View is not a ViewGroup so can't addView");
  }

  protected final void b(int paramInt)
  {
    if (this.P != null)
      this.P.a();
    if (getScrollY() != paramInt)
    {
      this.P = new e(this, getScrollY(), paramInt, 600);
      post(this.P);
    }
  }

  protected void b(Context paramContext, AttributeSet paramAttributeSet)
  {
    setOrientation(1);
    this.s = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    this.A = a(paramContext, paramAttributeSet);
    a(paramContext, this.A);
    this.F = new LoadingLayout(paramContext, PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
    this.G = new LoadingLayout(paramContext, PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
    this.F.setPosition(0);
    this.G.setPosition(1);
    f();
  }

  protected abstract boolean b();

  protected void c()
  {
    switch (d.a[getCurrentMode().ordinal()])
    {
    default:
      return;
    case 1:
      this.G.pullToRefresh();
      return;
    case 2:
    }
    this.F.pullToRefresh();
  }

  protected void c(int paramInt)
  {
    if (this.P != null)
      this.P.a();
    if (getScrollY() != paramInt)
    {
      this.P = new e(this, getScrollY(), paramInt, 600, new CycleInterpolator(0.5F));
      post(this.P);
    }
  }

  protected void d()
  {
    switch (d.a[getCurrentMode().ordinal()])
    {
    default:
      return;
    case 1:
      this.G.releaseToRefresh();
      return;
    case 2:
    }
    this.F.releaseToRefresh();
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((this.O != null) && (this.O.onDispatchTouchEventListener(paramMotionEvent)))
      return true;
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  protected void e()
  {
    boolean bool = isRefreshing();
    this.x = 0;
    if (this.w)
    {
      this.w = false;
      k();
    }
    if (this.y.canPullDown())
      this.F.reset();
    if (this.y.canPullUp())
      this.G.reset();
    if (!bool)
    {
      a(-getHeadSpaceHeight());
      return;
    }
    b(-getHeadSpaceHeight());
  }

  protected void f()
  {
    if (this == this.F.getParent())
      removeView(this.F);
    if (this.y.canPullDown())
      a(this.F, 0, new LinearLayout.LayoutParams(-1, -2));
    if (this == this.G.getParent())
      removeView(this.G);
    if (this.y.canPullUp())
      a(this.G, new LinearLayout.LayoutParams(-1, -2));
    p();
    if (this.y != PullToRefreshBase.Mode.BOTH);
    for (PullToRefreshBase.Mode localMode = this.y; ; localMode = PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH)
    {
      setCurrentMode(localMode);
      return;
    }
  }

  protected int g()
  {
    return 0;
  }

  public final PullToRefreshBase.Mode getCurrentMode()
  {
    return this.z;
  }

  public final boolean getFilterTouchEvents()
  {
    return this.E;
  }

  protected final LoadingLayout getFooterLayout()
  {
    return this.G;
  }

  public int getHeadSpaceHeight()
  {
    return this.I;
  }

  protected final int getHeaderHeight()
  {
    return this.H;
  }

  protected final LoadingLayout getHeaderLayout()
  {
    return this.F;
  }

  @PluginApi(a=6)
  public final PullToRefreshBase.Mode getMode()
  {
    return this.y;
  }

  @PluginApi(a=6)
  public final int getPullDownLimit()
  {
    if (this.U == null)
      return 0;
    return this.U.a;
  }

  @PluginApi(a=6)
  public final int getPullPaddingBottom()
  {
    return this.T;
  }

  @PluginApi(a=6)
  public final int getPullPaddingLeft()
  {
    return this.Q;
  }

  @PluginApi(a=6)
  public final int getPullPaddingRight()
  {
    return this.R;
  }

  @PluginApi(a=6)
  public final int getPullPaddingTop()
  {
    return this.S;
  }

  @PluginApi(a=6)
  public final int getPullUpLimit()
  {
    if (this.U == null)
      return 0;
    return this.U.b;
  }

  @PluginApi(a=6)
  public final View getRefreshableView()
  {
    return this.A;
  }

  @PluginApi(a=6)
  public final boolean getShowViewWhilePull()
  {
    return this.C;
  }

  @PluginApi(a=6)
  public final boolean getShowViewWhileRefreshing()
  {
    return this.B;
  }

  protected final int getState()
  {
    return this.x;
  }

  public final boolean h()
  {
    return getCurrentMode() == PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH;
  }

  protected void i()
  {
  }

  @PluginApi(a=6)
  public final boolean isDisableScrollingWhileRefreshing()
  {
    return this.D;
  }

  @PluginApi(a=6)
  public final boolean isPullToRefreshEnabled()
  {
    return this.y != PullToRefreshBase.Mode.DISABLED;
  }

  @PluginApi(a=6)
  public final boolean isRefreshing()
  {
    return (this.x == 2) || (this.x == 3);
  }

  protected void j()
  {
    l();
    if (this.L != null)
      if (getCurrentMode() != PullToRefreshBase.Mode.PULL_UP_TO_REFRESH)
        break label37;
    label37: for (PullToRefreshBase.Direction localDirection = PullToRefreshBase.Direction.FROM_END; ; localDirection = PullToRefreshBase.Direction.FROM_START)
    {
      this.L.onPullStart(this, localDirection);
      return;
    }
  }

  protected void k()
  {
    m();
    if (this.L != null)
      if (getCurrentMode() != PullToRefreshBase.Mode.PULL_UP_TO_REFRESH)
        break label37;
    label37: for (PullToRefreshBase.Direction localDirection = PullToRefreshBase.Direction.FROM_END; ; localDirection = PullToRefreshBase.Direction.FROM_START)
    {
      this.L.onPullEnd(this, localDirection);
      return;
    }
  }

  protected void l()
  {
  }

  protected void m()
  {
  }

  protected boolean n()
  {
    switch (d.a[this.y.ordinal()])
    {
    default:
    case 2:
    case 1:
    case 3:
    case 4:
    }
    do
    {
      do
      {
        return false;
        return a();
        return b();
      }
      while ((!b()) && (!a()));
      return true;
    }
    while ((!b()) && (!a()));
    return true;
  }

  protected boolean o()
  {
    int i1 = -1;
    int i2 = getScrollY();
    int i3;
    int i4;
    float f1;
    switch (d.a[getCurrentMode().ordinal()])
    {
    default:
      i3 = Math.round(Math.min(this.v - this.u, 0.0F) / 2.0F);
      if (this.U.a > 0)
        i1 = a(this.U.a, 1 + this.H);
      if (i1 > 0)
        i3 = a(-i1, i3);
      i4 = i3 + getHeadSpaceHeight();
      setHeaderScroll(i4);
      if (i4 == 0)
        break;
      f1 = Math.abs(i4) / this.H;
      switch (d.a[getCurrentMode().ordinal()])
      {
      default:
      case 1:
      case 2:
      }
    case 1:
      while (true)
      {
        if ((this.x != 0) || (this.H >= Math.abs(i4)))
          break label273;
        this.x = 1;
        d();
        return true;
        i3 = Math.round(Math.max(this.v - this.u, 0.0F) / 2.0F);
        if (this.U.b > 0)
          i1 = a(this.U.b, 1 + this.H);
        if (i1 <= 0)
          break;
        i3 = b(i1, i3);
        break;
        this.G.a(f1);
        continue;
        this.F.a(f1);
      }
      label273: if ((this.x == 1) && (this.H >= Math.abs(i4)))
      {
        this.x = 0;
        c();
        return true;
      }
      a(f1);
    }
    if (i2 != i4);
    for (int i5 = 1; ; i5 = 0)
      return i5;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!isPullToRefreshEnabled())
      return false;
    int i1 = paramMotionEvent.getAction();
    if ((i1 == 3) || (i1 == 1))
    {
      this.w = false;
      return false;
    }
    if ((i1 != 0) && (this.w))
      return true;
    switch (i1)
    {
    case 1:
    default:
    case 2:
    case 0:
    }
    while (true)
    {
      return this.w;
      if ((this.D) && (isRefreshing()))
        return true;
      if (!n())
        continue;
      float f2 = paramMotionEvent.getY();
      float f3 = f2 - this.u;
      float f4 = Math.abs(f3);
      float f5 = Math.abs(paramMotionEvent.getX() - this.t);
      if ((f4 <= this.s) || ((this.E) && (f4 <= f5)))
        continue;
      if ((this.y.canPullDown()) && (f3 >= 1.0F) && (a()))
      {
        this.u = f2;
        this.w = true;
        if (this.y == PullToRefreshBase.Mode.BOTH)
          setCurrentMode(PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
        j();
        continue;
      }
      if ((!this.y.canPullUp()) || (f3 > -1.0F) || (!b()))
        continue;
      this.u = f2;
      this.w = true;
      if (this.y == PullToRefreshBase.Mode.BOTH)
        setCurrentMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
      j();
      continue;
      if (!n())
        continue;
      float f1 = paramMotionEvent.getY();
      this.v = f1;
      this.u = f1;
      this.t = paramMotionEvent.getX();
      this.w = false;
    }
  }

  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if ((paramParcelable instanceof Bundle))
    {
      Bundle localBundle = (Bundle)paramParcelable;
      this.y = PullToRefreshBase.Mode.mapIntToMode(localBundle.getInt("ptr_mode", 0));
      setCurrentMode(PullToRefreshBase.Mode.mapIntToMode(localBundle.getInt("ptr_current_mode", 0)));
      this.D = localBundle.getBoolean("ptr_disable_scrolling", true);
      this.B = localBundle.getBoolean("ptr_show_refreshing_view", true);
      super.onRestoreInstanceState(localBundle.getParcelable("ptr_super"));
      int i1 = localBundle.getInt("ptr_state", 0);
      if (i1 == 2)
      {
        setRefreshingInternal(true);
        this.x = i1;
      }
      return;
    }
    super.onRestoreInstanceState(paramParcelable);
  }

  protected Parcelable onSaveInstanceState()
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("ptr_state", this.x);
    localBundle.putInt("ptr_mode", this.y.getIntValue());
    localBundle.putInt("ptr_current_mode", getCurrentMode().getIntValue());
    localBundle.putBoolean("ptr_disable_scrolling", this.D);
    localBundle.putBoolean("ptr_show_refreshing_view", this.B);
    localBundle.putParcelable("ptr_super", super.onSaveInstanceState());
    return localBundle;
  }

  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.M != null)
      this.M.onScrollChanged(this, paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.N != null)
      this.N.onScrollChanged(this, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!isPullToRefreshEnabled());
    do
    {
      do
      {
        do
        {
          do
          {
            return false;
            if ((this.D) && (isRefreshing()))
              return true;
          }
          while ((paramMotionEvent.getAction() == 0) && (paramMotionEvent.getEdgeFlags() != 0));
          switch (paramMotionEvent.getAction())
          {
          default:
            return false;
          case 0:
          case 2:
          case 1:
          case 3:
          }
        }
        while (!n());
        float f1 = paramMotionEvent.getY();
        this.v = f1;
        this.u = f1;
        return true;
      }
      while (!this.w);
      this.u = paramMotionEvent.getY();
      o();
      return true;
    }
    while (!this.w);
    this.w = false;
    k();
    if (this.x == 1)
    {
      if (this.J != null)
      {
        setRefreshingInternal(true);
        this.J.onRefresh(this);
        return true;
      }
      if (this.K != null)
      {
        setRefreshingInternal(true);
        if (getCurrentMode() == PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH)
          this.K.onPullDownToRefresh(this);
        while (true)
        {
          return true;
          if (getCurrentMode() != PullToRefreshBase.Mode.PULL_UP_TO_REFRESH)
            continue;
          this.K.onPullUpToRefresh(this);
        }
      }
      e();
      return true;
    }
    a(g());
    return true;
  }

  protected void p()
  {
    if (this.y.canPullDown())
    {
      a(this.F);
      this.H = this.F.getMeasuredHeight();
    }
    int i1;
    int i2;
    int i3;
    int i4;
    while (true)
    {
      i1 = this.Q;
      i2 = this.R;
      i3 = this.S;
      i4 = this.T;
      switch (d.a[this.y.ordinal()])
      {
      case 2:
      case 4:
      default:
        setPadding(i1, i3 - this.H, i2, i4);
        return;
        if (this.y.canPullUp())
        {
          a(this.G);
          this.H = this.G.getMeasuredHeight();
          continue;
        }
        this.H = 0;
      case 5:
      case 3:
      case 1:
      }
    }
    setPadding(i1, i3, i2, i4);
    return;
    setPadding(i1, i3 - this.H, i2, i4 - this.H);
    return;
    setPadding(i1, i3, i2, i4 - this.H);
  }

  public void scrollTo(int paramInt1, int paramInt2)
  {
    super.scrollTo(paramInt1, paramInt2);
  }

  protected void setCurrentMode(PullToRefreshBase.Mode paramMode)
  {
    this.z = paramMode;
  }

  @PluginApi(a=6)
  public final void setDisableScrollingWhileRefreshing(boolean paramBoolean)
  {
    this.D = paramBoolean;
  }

  public final void setFilterTouchEvents(boolean paramBoolean)
  {
    this.E = paramBoolean;
  }

  public void setHeadSpaceHeight(int paramInt)
  {
    this.I = paramInt;
    scrollTo(0, -paramInt);
  }

  protected final void setHeaderScroll(int paramInt)
  {
    scrollTo(0, paramInt);
  }

  @PluginApi(a=6)
  public void setLastUpdateLabelVisibleWhenRefreshing(boolean paramBoolean)
  {
    if (this.F != null)
      this.F.setSubVisibleWhenRefreshing(paramBoolean);
    if (this.G != null)
      this.G.setSubVisibleWhenRefreshing(paramBoolean);
  }

  @PluginApi(a=6)
  public void setLastUpdatedLabel(CharSequence paramCharSequence)
  {
    if (this.F != null)
      this.F.setSubHeaderText(paramCharSequence);
    if (this.G != null)
      this.G.setSubHeaderText(paramCharSequence);
    p();
  }

  @PluginApi(a=6)
  public void setLoadingDrawable(Drawable paramDrawable)
  {
    setLoadingDrawable(paramDrawable, PullToRefreshBase.Mode.BOTH);
  }

  @PluginApi(a=6)
  public void setLoadingDrawable(Drawable paramDrawable, PullToRefreshBase.Mode paramMode)
  {
    if ((this.F != null) && (paramMode.canPullDown()))
      this.F.setLoadingDrawable(paramDrawable);
    if ((this.G != null) && (paramMode.canPullUp()))
      this.G.setLoadingDrawable(paramDrawable);
    p();
  }

  public void setLongClickable(boolean paramBoolean)
  {
    getRefreshableView().setLongClickable(paramBoolean);
  }

  @PluginApi(a=6)
  public final void setMode(PullToRefreshBase.Mode paramMode)
  {
    if (paramMode != this.y)
    {
      this.y = paramMode;
      f();
    }
  }

  @PluginApi(a=6)
  public void setOnDispatchTouchEventListener(PullToRefreshBase.OnDispatchTouchEventListener paramOnDispatchTouchEventListener)
  {
    this.O = paramOnDispatchTouchEventListener;
  }

  @PluginApi(a=6)
  public final void setOnPullEventListener(PullToRefreshBase.OnPullEventListener paramOnPullEventListener)
  {
    this.L = paramOnPullEventListener;
  }

  @PluginApi(a=6)
  public final void setOnRefreshListener(PullToRefreshBase.OnRefreshListener2 paramOnRefreshListener2)
  {
    this.K = paramOnRefreshListener2;
  }

  @PluginApi(a=6)
  public final void setOnRefreshListener(PullToRefreshBase.OnRefreshListener paramOnRefreshListener)
  {
    this.J = paramOnRefreshListener;
  }

  @PluginApi(a=6)
  public final void setOnScrollChangedListener(PullToRefreshBase.OnScrollChangedListener2 paramOnScrollChangedListener2)
  {
    this.N = paramOnScrollChangedListener2;
  }

  @PluginApi(a=6)
  public final void setOnScrollChangedListener(PullToRefreshBase.OnScrollChangedListener paramOnScrollChangedListener)
  {
    this.M = paramOnScrollChangedListener;
  }

  @PluginApi(a=6)
  public void setPullAnimationEnabled(boolean paramBoolean)
  {
    setPullAnimationEnabled(paramBoolean, PullToRefreshBase.Mode.BOTH);
  }

  @PluginApi(a=6)
  public void setPullAnimationEnabled(boolean paramBoolean, PullToRefreshBase.Mode paramMode)
  {
    if ((this.F != null) && (paramMode.canPullDown()))
      this.F.setPullAnimationEnabled(paramBoolean);
    if ((this.G != null) && (paramMode.canPullUp()))
      this.G.setPullAnimationEnabled(paramBoolean);
  }

  @PluginApi(a=6)
  public void setPullBackground(Drawable paramDrawable)
  {
    setPullBackground(paramDrawable, PullToRefreshBase.Mode.BOTH);
  }

  @PluginApi(a=6)
  public void setPullBackground(Drawable paramDrawable, PullToRefreshBase.Mode paramMode)
  {
    if ((this.F != null) && (paramMode.canPullDown()))
      this.F.setBackgroundDrawable(paramDrawable);
    if ((this.G != null) && (paramMode.canPullUp()))
      this.G.setBackgroundDrawable(paramDrawable);
  }

  @PluginApi(a=6)
  public void setPullDrawable(Drawable paramDrawable, PullToRefreshBase.Mode paramMode)
  {
    if ((this.F != null) && (paramMode.canPullDown()))
      this.F.setPullDrawable(paramDrawable);
    if ((this.G != null) && (paramMode.canPullUp()))
      this.G.setPullDrawable(paramDrawable);
  }

  @PluginApi(a=6)
  public void setPullLabel(String paramString)
  {
    setPullLabel(paramString, PullToRefreshBase.Mode.BOTH);
  }

  @PluginApi(a=6)
  public void setPullLabel(String paramString, PullToRefreshBase.Mode paramMode)
  {
    if ((this.F != null) && (paramMode.canPullDown()))
      this.F.setPullLabel(paramString);
    if ((this.G != null) && (paramMode.canPullUp()))
      this.G.setPullLabel(paramString);
  }

  @PluginApi(a=6)
  public final void setPullLimit(int paramInt, PullToRefreshBase.Mode paramMode)
  {
    if (paramMode.canPullDown())
      this.U.a = paramInt;
    if (paramMode.canPullUp())
      this.U.b = paramInt;
  }

  @PluginApi(a=6)
  public final void setPullPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((this.Q != paramInt1) || (this.S != paramInt2) || (this.R != paramInt3) || (this.T != paramInt4))
    {
      this.Q = paramInt1;
      this.R = paramInt3;
      this.S = paramInt2;
      this.T = paramInt4;
      p();
    }
  }

  @PluginApi(a=6)
  public final void setPullToRefreshEnabled(boolean paramBoolean)
  {
    if (paramBoolean);
    for (PullToRefreshBase.Mode localMode = i; ; localMode = PullToRefreshBase.Mode.DISABLED)
    {
      setMode(localMode);
      return;
    }
  }

  @PluginApi(a=6)
  public void setRefreshComplete(boolean paramBoolean)
  {
    if (this.x != 0)
    {
      e();
      a(paramBoolean);
      if (this.J == null)
        break label34;
      this.J.onRefreshComplete(this);
    }
    label34: 
    do
      return;
    while (this.K == null);
    this.K.onRefreshComplete(this);
  }

  @PluginApi(a=6)
  public final void setRefreshing()
  {
    setRefreshing(true);
  }

  @PluginApi(a=6)
  public final void setRefreshing(boolean paramBoolean)
  {
    int i1;
    if (!isRefreshing())
    {
      setRefreshingInternal(paramBoolean);
      this.x = 3;
      if ((paramBoolean) && (!this.B))
      {
        if (getCurrentMode() != PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH)
          break label94;
        i1 = -this.H;
        c(i1);
      }
      if (this.J != null)
        this.J.onRefresh(this);
      if (this.K != null)
      {
        if (getCurrentMode() != PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH)
          break label102;
        this.K.onPullDownToRefresh(this);
      }
    }
    label94: label102: 
    do
    {
      return;
      i1 = this.H;
      break;
    }
    while (getCurrentMode() != PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
    this.K.onPullUpToRefresh(this);
  }

  protected void setRefreshingInternal(boolean paramBoolean)
  {
    this.x = 2;
    if (this.y.canPullDown())
      this.F.refreshing();
    if (this.y.canPullUp())
      this.G.refreshing();
    int i1;
    if (paramBoolean)
    {
      if (!this.B)
        break label84;
      if (getCurrentMode() != PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH)
        break label76;
      i1 = -this.H;
      a(i1);
    }
    while (true)
    {
      i();
      return;
      label76: i1 = this.H;
      break;
      label84: a(-getHeadSpaceHeight());
    }
  }

  @PluginApi(a=6)
  public void setRefreshingLabel(String paramString)
  {
    setRefreshingLabel(paramString, PullToRefreshBase.Mode.BOTH);
  }

  @PluginApi(a=6)
  public void setRefreshingLabel(String paramString, PullToRefreshBase.Mode paramMode)
  {
    if ((this.F != null) && (paramMode.canPullDown()))
      this.F.setRefreshingLabel(paramString);
    if ((this.G != null) && (paramMode.canPullUp()))
      this.G.setRefreshingLabel(paramString);
  }

  @PluginApi(a=6)
  public void setReleaseDrawable(Drawable paramDrawable, PullToRefreshBase.Mode paramMode)
  {
    if ((this.F != null) && (paramMode.canPullDown()))
      this.F.setReleaseDrawable(paramDrawable);
    if ((this.G != null) && (paramMode.canPullUp()))
      this.G.setReleaseDrawable(paramDrawable);
  }

  @PluginApi(a=6)
  public void setReleaseLabel(String paramString)
  {
    setReleaseLabel(paramString, PullToRefreshBase.Mode.BOTH);
  }

  @PluginApi(a=6)
  public void setReleaseLabel(String paramString, PullToRefreshBase.Mode paramMode)
  {
    if ((this.F != null) && (paramMode.canPullDown()))
      this.F.setReleaseLabel(paramString);
    if ((this.G != null) && (paramMode.canPullUp()))
      this.G.setReleaseLabel(paramString);
  }

  @PluginApi(a=6)
  public final void setShowViewWhilePull(boolean paramBoolean)
  {
    this.C = paramBoolean;
    int i2;
    LoadingLayout localLoadingLayout1;
    int i1;
    if (this.F != null)
    {
      LoadingLayout localLoadingLayout2 = this.F;
      if (paramBoolean)
      {
        i2 = 0;
        localLoadingLayout2.setVisibility(i2);
      }
    }
    else if (this.G != null)
    {
      localLoadingLayout1 = this.G;
      i1 = 0;
      if (!paramBoolean)
        break label62;
    }
    while (true)
    {
      localLoadingLayout1.setVisibility(i1);
      return;
      i2 = 4;
      break;
      label62: i1 = 4;
    }
  }

  @PluginApi(a=6)
  public final void setShowViewWhileRefreshing(boolean paramBoolean)
  {
    this.B = paramBoolean;
  }

  @PluginApi(a=6)
  public void setTextColor(int paramInt, PullToRefreshBase.TextType paramTextType)
  {
    setTextColor(paramInt, paramTextType, PullToRefreshBase.Mode.BOTH);
  }

  @PluginApi(a=6)
  public void setTextColor(int paramInt, PullToRefreshBase.TextType paramTextType, PullToRefreshBase.Mode paramMode)
  {
    setTextColor(ColorStateList.valueOf(paramInt), paramTextType, paramMode);
  }

  @PluginApi(a=6)
  public void setTextColor(ColorStateList paramColorStateList, PullToRefreshBase.TextType paramTextType)
  {
    setTextColor(paramColorStateList, paramTextType, PullToRefreshBase.Mode.BOTH);
  }

  @PluginApi(a=6)
  public void setTextColor(ColorStateList paramColorStateList, PullToRefreshBase.TextType paramTextType, PullToRefreshBase.Mode paramMode)
  {
    if ((this.F != null) && (paramMode.canPullDown()))
    {
      if (!paramTextType.isMain())
        break label59;
      this.F.setTextColor(paramColorStateList);
    }
    label59: 
    do
      while (true)
      {
        if ((this.G != null) && (paramMode.canPullUp()))
        {
          if (!paramTextType.isMain())
            break;
          this.G.setTextColor(paramColorStateList);
        }
        return;
        if (!paramTextType.isSub())
          continue;
        this.F.setSubTextColor(paramColorStateList);
      }
    while (!paramTextType.isSub());
    this.G.setSubTextColor(paramColorStateList);
  }

  @PluginApi(a=6)
  public void setTextSize(float paramFloat, PullToRefreshBase.TextType paramTextType)
  {
    setTextSize(paramFloat, paramTextType, PullToRefreshBase.Mode.BOTH);
  }

  @PluginApi(a=6)
  public void setTextSize(float paramFloat, PullToRefreshBase.TextType paramTextType, PullToRefreshBase.Mode paramMode)
  {
    if ((this.F != null) && (paramMode.canPullDown()))
    {
      if (paramTextType.isMain())
        this.F.setTextSize(paramFloat);
    }
    else if ((this.G != null) && (paramMode.canPullUp()))
    {
      if (!paramTextType.isMain())
        break label81;
      this.G.setTextSize(paramFloat);
    }
    while (true)
    {
      p();
      return;
      if (!paramTextType.isSub())
        break;
      this.F.setSubTextSize(paramFloat);
      break;
      label81: if (!paramTextType.isSub())
        continue;
      this.G.setSubTextSize(paramFloat);
    }
  }

  public void setVisibility(int paramInt)
  {
    if (this.A != null)
      this.A.setVisibility(paramInt);
    super.setVisibility(paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.pulltorefresh.PullToRefreshBase
 * JD-Core Version:    0.6.0
 */