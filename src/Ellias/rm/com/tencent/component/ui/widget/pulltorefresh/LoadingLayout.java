package com.tencent.component.ui.widget.pulltorefresh;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.plugin.PluginContextWrapper;
import com.tencent.component.utils.ResourceUtil;

@PluginApi(a=6)
public class LoadingLayout extends FrameLayout
{
  static final int a = 600;
  public static final int b = 0;
  public static final int c = 1;
  private final View d;
  private final ImageView e;
  private final Matrix f;
  private final TextView g;
  private final TextView h;
  private boolean i = true;
  private Drawable j;
  private Drawable k;
  private Drawable l;
  private boolean m = true;
  private String n;
  private String o;
  private String p;
  private float q;
  private float r;
  private final Animation s;
  private int t = 0;
  private View u;

  @PluginApi(a=6)
  public LoadingLayout(Context paramContext, PullToRefreshBase.Mode paramMode)
  {
    super(paramContext);
    Context localContext = PluginContextWrapper.a(paramContext);
    ViewGroup localViewGroup = (ViewGroup)LayoutInflater.from(localContext).inflate(ResourceUtil.a("appfw_pull_to_refresh_header"), this);
    this.g = ((TextView)localViewGroup.findViewById(ResourceUtil.f("pull_to_refresh_text")));
    this.h = ((TextView)localViewGroup.findViewById(ResourceUtil.f("pull_to_refresh_sub_text")));
    this.d = localViewGroup.findViewById(ResourceUtil.f("pull_to_refresh_image_frame"));
    this.e = ((ImageView)localViewGroup.findViewById(ResourceUtil.f("pull_to_refresh_image")));
    this.e.setScaleType(ImageView.ScaleType.MATRIX);
    this.f = new Matrix();
    this.e.setImageMatrix(this.f);
    LinearInterpolator localLinearInterpolator = new LinearInterpolator();
    this.s = new RotateAnimation(0.0F, 360.0F, 1, 0.5F, 1, 0.5F);
    this.s.setInterpolator(localLinearInterpolator);
    this.s.setDuration(600L);
    this.s.setRepeatCount(-1);
    this.s.setRepeatMode(1);
    switch (b.a[paramMode.ordinal()])
    {
    default:
      this.n = localContext.getString(ResourceUtil.b("appfw_pull_to_refresh_pull_label"));
      this.o = localContext.getString(ResourceUtil.b("appfw_pull_to_refresh_refreshing_label"));
    case 1:
    }
    for (this.p = localContext.getString(ResourceUtil.b("appfw_pull_to_refresh_release_label")); ; this.p = localContext.getString(ResourceUtil.b("appfw_pull_to_refresh_from_bottom_release_label")))
    {
      Drawable localDrawable = null;
      if (0 == 0)
        localDrawable = localContext.getResources().getDrawable(ResourceUtil.c("appfw_default_ptr"));
      setLoadingDrawable(localDrawable);
      setPullDrawable(localDrawable);
      setReleaseDrawable(localDrawable);
      reset();
      return;
      this.n = localContext.getString(ResourceUtil.b("appfw_pull_to_refresh_from_bottom_pull_label"));
      this.o = localContext.getString(ResourceUtil.b("appfw_pull_to_refresh_from_bottom_refreshing_label"));
    }
  }

  private CharSequence a(String paramString)
  {
    if (!isInEditMode());
    try
    {
      Spanned localSpanned = Html.fromHtml(paramString);
      paramString = localSpanned;
      return paramString;
    }
    catch (Exception localException)
    {
    }
    return paramString;
  }

  private void a()
  {
    this.f.reset();
    this.e.setImageMatrix(this.f);
  }

  private void a(Drawable paramDrawable)
  {
    if (paramDrawable == null)
      return;
    this.q = (paramDrawable.getIntrinsicWidth() / 2.0F);
    this.r = (paramDrawable.getIntrinsicHeight() / 2.0F);
  }

  private void b()
  {
    int i1 = 0;
    ViewGroup.LayoutParams localLayoutParams = this.d.getLayoutParams();
    if (localLayoutParams == null)
      return;
    Drawable[] arrayOfDrawable = new Drawable[3];
    arrayOfDrawable[0] = this.j;
    arrayOfDrawable[1] = this.k;
    arrayOfDrawable[2] = this.l;
    int i2 = arrayOfDrawable.length;
    int i3 = 0;
    int i4 = 0;
    if (i3 < i2)
    {
      Drawable localDrawable = arrayOfDrawable[i3];
      if (localDrawable == null);
      while (true)
      {
        i3++;
        break;
        i4 = Math.max(i4, localDrawable.getIntrinsicWidth());
        i1 = Math.max(i1, localDrawable.getIntrinsicHeight());
      }
    }
    if (i4 == 0)
      i4 = -2;
    if (i1 == 0)
      i1 = -2;
    localLayoutParams.width = i4;
    localLayoutParams.height = i1;
    this.d.setLayoutParams(localLayoutParams);
  }

  public void a(float paramFloat)
  {
    if (!this.m)
      return;
    a(this.e.getDrawable());
    this.f.setRotate(90.0F * paramFloat, this.q, this.r);
    this.e.setImageMatrix(this.f);
  }

  @PluginApi(a=6)
  public void addSpaceView(View paramView, LinearLayout.LayoutParams paramLayoutParams)
  {
    this.u = paramView;
    ((LinearLayout)findViewById(ResourceUtil.f("head_root"))).addView(paramView, 0, paramLayoutParams);
  }

  public int getPosition()
  {
    return this.t;
  }

  @PluginApi(a=6)
  public View getSpaceView()
  {
    return this.u;
  }

  @PluginApi(a=6)
  public void pullToRefresh()
  {
    this.g.setText(a(this.n));
    this.e.setImageDrawable(this.k);
  }

  @PluginApi(a=6)
  public void refreshing()
  {
    this.g.setText(a(this.o));
    this.e.setImageDrawable(this.j);
    this.e.startAnimation(this.s);
    if (!this.i)
      this.h.setVisibility(8);
  }

  @PluginApi(a=6)
  public void releaseToRefresh()
  {
    this.g.setText(a(this.p));
    this.e.setImageDrawable(this.l);
  }

  @PluginApi(a=6)
  public void reset()
  {
    this.g.setText(a(this.n));
    this.e.setImageDrawable(this.k);
    this.e.setVisibility(0);
    this.e.clearAnimation();
    a();
    if (TextUtils.isEmpty(this.h.getText()))
    {
      this.h.setVisibility(8);
      return;
    }
    this.h.setVisibility(0);
  }

  @PluginApi(a=6)
  public void setLoadingDrawable(Drawable paramDrawable)
  {
    this.j = paramDrawable;
    b();
  }

  public void setPosition(int paramInt)
  {
    this.t = paramInt;
  }

  @PluginApi(a=6)
  public void setPullAnimationEnabled(boolean paramBoolean)
  {
    this.m = paramBoolean;
  }

  @PluginApi(a=6)
  public void setPullDrawable(Drawable paramDrawable)
  {
    this.k = paramDrawable;
    this.e.setImageDrawable(paramDrawable);
    b();
  }

  @PluginApi(a=6)
  public void setPullLabel(String paramString)
  {
    this.n = paramString;
    this.g.setText(a(paramString));
  }

  @PluginApi(a=6)
  public void setRefreshingLabel(String paramString)
  {
    this.o = paramString;
  }

  @PluginApi(a=6)
  public void setReleaseDrawable(Drawable paramDrawable)
  {
    this.l = paramDrawable;
    b();
  }

  @PluginApi(a=6)
  public void setReleaseLabel(String paramString)
  {
    this.p = paramString;
  }

  @PluginApi(a=6)
  public void setSubHeaderText(CharSequence paramCharSequence)
  {
    if (TextUtils.isEmpty(paramCharSequence))
    {
      this.h.setVisibility(8);
      return;
    }
    this.h.setText(paramCharSequence);
    this.h.setVisibility(0);
  }

  @PluginApi(a=6)
  public void setSubTextColor(int paramInt)
  {
    setSubTextColor(ColorStateList.valueOf(paramInt));
  }

  @PluginApi(a=6)
  public void setSubTextColor(ColorStateList paramColorStateList)
  {
    this.h.setTextColor(paramColorStateList);
  }

  @PluginApi(a=6)
  public void setSubTextSize(float paramFloat)
  {
    this.h.setTextSize(paramFloat);
  }

  @PluginApi(a=6)
  public void setSubVisibleWhenRefreshing(boolean paramBoolean)
  {
    this.i = paramBoolean;
  }

  @PluginApi(a=6)
  public void setTextColor(int paramInt)
  {
    setTextColor(ColorStateList.valueOf(paramInt));
  }

  @PluginApi(a=6)
  public void setTextColor(ColorStateList paramColorStateList)
  {
    this.g.setTextColor(paramColorStateList);
  }

  @PluginApi(a=6)
  public void setTextSize(float paramFloat)
  {
    this.g.setTextSize(paramFloat);
  }

  public void setVisibility(int paramInt)
  {
    if (this.t == 0)
    {
      findViewById(ResourceUtil.f("pull_to_refresh_header")).setVisibility(paramInt);
      return;
    }
    super.setVisibility(paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.pulltorefresh.LoadingLayout
 * JD-Core Version:    0.6.0
 */