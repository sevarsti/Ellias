package com.tencent.component.ui.widget.pulltorefresh;

import android.content.Context;
import android.content.res.Resources;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.tencent.component.plugin.PluginContextWrapper;
import com.tencent.component.utils.ResourceUtil;

public class IndicatorLayout extends FrameLayout
  implements Animation.AnimationListener
{
  static final int a = 150;
  private Animation b;
  private Animation c;
  private ImageView d;
  private final Animation e;
  private final Animation f;
  private Context g;

  public IndicatorLayout(Context paramContext, PullToRefreshBase.Mode paramMode)
  {
    super(PluginContextWrapper.a(paramContext));
    this.g = PluginContextWrapper.a(paramContext);
    this.d = new ImageView(paramContext);
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
    int i = getResources().getDimensionPixelSize(ResourceUtil.j("appfw_indicator_internal_padding"));
    localLayoutParams.rightMargin = i;
    localLayoutParams.leftMargin = i;
    localLayoutParams.bottomMargin = i;
    localLayoutParams.topMargin = i;
    addView(this.d, localLayoutParams);
    int j;
    int k;
    switch (a.a[paramMode.ordinal()])
    {
    default:
      j = ResourceUtil.i("appfw_comm_slide_in_from_top");
      k = ResourceUtil.i("appfw_comm_slide_out_to_top");
      setBackgroundResource(ResourceUtil.c("appfw_shape_indicator_top"));
      this.d.setImageResource(ResourceUtil.c("appfw_arrow_down"));
    case 1:
    }
    while (true)
    {
      this.b = AnimationUtils.loadAnimation(this.g, j);
      this.b.setAnimationListener(this);
      this.c = AnimationUtils.loadAnimation(this.g, k);
      this.c.setAnimationListener(this);
      LinearInterpolator localLinearInterpolator = new LinearInterpolator();
      this.e = new RotateAnimation(0.0F, -180.0F, 1, 0.5F, 1, 0.5F);
      this.e.setInterpolator(localLinearInterpolator);
      this.e.setDuration(150L);
      this.e.setFillAfter(true);
      this.f = new RotateAnimation(-180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
      this.f.setInterpolator(localLinearInterpolator);
      this.f.setDuration(150L);
      this.f.setFillAfter(true);
      return;
      j = ResourceUtil.i("appfw_comm_slide_in_from_bottom");
      k = ResourceUtil.i("appfw_comm_slide_out_to_bottom");
      setBackgroundResource(ResourceUtil.c("appfw_shape_indicator_bottom"));
      this.d.setImageResource(ResourceUtil.c("appfw_arrow_up"));
    }
  }

  public final boolean a()
  {
    Animation localAnimation = getAnimation();
    if (localAnimation != null)
      if (this.b != localAnimation);
    do
    {
      return true;
      return false;
    }
    while (getVisibility() == 0);
    return false;
  }

  public void b()
  {
    startAnimation(this.c);
  }

  public void c()
  {
    this.d.clearAnimation();
    startAnimation(this.b);
  }

  public void d()
  {
    this.d.startAnimation(this.e);
  }

  public void e()
  {
    this.d.startAnimation(this.f);
  }

  public void onAnimationEnd(Animation paramAnimation)
  {
    if (paramAnimation == this.c)
    {
      this.d.clearAnimation();
      setVisibility(8);
    }
    while (true)
    {
      clearAnimation();
      return;
      if (paramAnimation != this.b)
        continue;
      setVisibility(0);
    }
  }

  public void onAnimationRepeat(Animation paramAnimation)
  {
  }

  public void onAnimationStart(Animation paramAnimation)
  {
    setVisibility(0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.pulltorefresh.IndicatorLayout
 * JD-Core Version:    0.6.0
 */