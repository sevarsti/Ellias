package com.tencent.component.ui.widget.image;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.ui.widget.image.processor.ImageProcessor;
import com.tencent.component.utils.log.LogUtil;

@PluginApi(a=4)
public class ExtendImageView extends ImageView
{
  private static final ThreadLocal a = new d();
  private boolean b = false;
  private boolean c = false;
  private boolean d = false;
  private boolean e = false;
  private ViewForeground f = new ViewForeground(this, null);
  private Drawable g;
  private Drawable h;
  private ImageProcessor i;

  @PluginApi(a=4)
  public ExtendImageView(Context paramContext)
  {
    super(paramContext);
  }

  @PluginApi(a=4)
  public ExtendImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  @PluginApi(a=4)
  public ExtendImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private void a(Animation paramAnimation, Runnable paramRunnable)
  {
    if (paramAnimation == null)
    {
      if (paramRunnable != null)
        paramRunnable.run();
      return;
    }
    clearAnimation();
    paramAnimation.setAnimationListener(new i(this, paramRunnable));
    startAnimation(paramAnimation);
  }

  private void a(ImageProcessor paramImageProcessor)
  {
    if (this.f != null)
    {
      Drawable localDrawable2 = this.g;
      if (localDrawable2 != null)
        setForeground(localDrawable2);
    }
    Drawable localDrawable1 = this.h;
    if (localDrawable1 != null)
      setImageDrawable(localDrawable1);
  }

  private boolean a(int paramInt1, int paramInt2)
  {
    int j = View.MeasureSpec.getMode(paramInt1);
    int k = View.MeasureSpec.getMode(paramInt2);
    return (j == 1073741824) && (k == 1073741824);
  }

  private boolean a(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    Rect localRect = (Rect)a.get();
    int j;
    if ((paramDrawable1 != null) && (paramDrawable1.getPadding(localRect)))
      j = 1;
    while (j == 0)
    {
      if ((paramDrawable2 != null) && (paramDrawable2.getPadding(localRect)))
      {
        return true;
        j = 0;
        continue;
      }
      return false;
    }
    return j;
  }

  private void setImageBitmapInternal(Bitmap paramBitmap)
  {
    setImageDrawable(new BitmapDrawable(getResources(), paramBitmap));
  }

  private void setImageDrawableInternal(Drawable paramDrawable)
  {
    this.c = true;
    this.h = paramDrawable;
    ImageProcessor localImageProcessor = this.i;
    if ((paramDrawable != null) && (localImageProcessor != null))
      paramDrawable = localImageProcessor.doProcess(paramDrawable);
    super.setImageDrawable(paramDrawable);
    this.c = false;
  }

  private void setImageResourceInternal(int paramInt)
  {
    try
    {
      setImageDrawable(getResources().getDrawable(paramInt));
      return;
    }
    catch (Exception localException)
    {
      LogUtil.w("ImageView", "Unable to find resource: " + paramInt, localException);
    }
  }

  private void setImageURIInternal(Uri paramUri)
  {
    this.c = true;
    super.setImageURI(paramUri);
    this.c = false;
  }

  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    ViewForeground localViewForeground = this.f;
    if (localViewForeground != null)
      localViewForeground.b();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    ViewForeground localViewForeground = this.f;
    if (localViewForeground != null)
      localViewForeground.a(paramCanvas);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    ViewForeground localViewForeground = this.f;
    if (localViewForeground != null)
      localViewForeground.c();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.b = a(paramInt1, paramInt2);
    super.onMeasure(paramInt1, paramInt2);
    if ((this.d) && (!this.e))
      setMeasuredDimension(getDefaultSize(getMeasuredWidth(), paramInt1), getDefaultSize(getMeasuredHeight(), paramInt2));
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    ViewForeground localViewForeground = this.f;
    if (localViewForeground != null)
      localViewForeground.c();
  }

  public void requestLayout()
  {
    if ((this.c) && (this.b))
      return;
    super.requestLayout();
  }

  @PluginApi(a=4)
  public void setAdjustViewBounds(boolean paramBoolean)
  {
    super.setAdjustViewBounds(paramBoolean);
    this.e = paramBoolean;
  }

  @PluginApi(a=4)
  public void setBackgroundColor(int paramInt)
  {
    this.c = true;
    super.setBackgroundColor(paramInt);
    this.c = false;
  }

  @PluginApi(a=4)
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    if (!a(getBackground(), paramDrawable));
    for (boolean bool = true; ; bool = false)
    {
      this.c = bool;
      super.setBackgroundDrawable(paramDrawable);
      this.c = false;
      return;
    }
  }

  @PluginApi(a=4)
  public void setBackgroundResource(int paramInt)
  {
    this.c = true;
    super.setBackgroundResource(paramInt);
    this.c = false;
  }

  @PluginApi(a=4)
  public void setForeground(int paramInt)
  {
    this.c = true;
    if (paramInt != 0);
    for (Drawable localDrawable = getResources().getDrawable(paramInt); ; localDrawable = null)
    {
      setForeground(localDrawable);
      this.c = false;
      return;
    }
  }

  @PluginApi(a=4)
  public void setForeground(Drawable paramDrawable)
  {
    this.c = true;
    this.g = paramDrawable;
    ImageProcessor localImageProcessor = this.i;
    if ((paramDrawable != null) && (localImageProcessor != null))
      paramDrawable = localImageProcessor.doProcess(paramDrawable);
    this.f.a(paramDrawable);
    this.c = false;
  }

  public void setIgnoreContentBounds(boolean paramBoolean)
  {
    if (this.d != paramBoolean)
    {
      this.d = paramBoolean;
      requestLayout();
    }
  }

  @PluginApi(a=4)
  public void setImageBitmap(Bitmap paramBitmap)
  {
    setImageBitmapInternal(paramBitmap);
  }

  @PluginApi(a=4)
  public void setImageBitmap(Bitmap paramBitmap, Animation paramAnimation1, Animation paramAnimation2)
  {
    if (paramAnimation2 != null)
      a(paramAnimation2, new e(this, paramBitmap, paramAnimation1));
    do
    {
      return;
      setImageBitmapInternal(paramBitmap);
    }
    while (paramAnimation1 == null);
    a(paramAnimation1, null);
  }

  @PluginApi(a=4)
  public void setImageDrawable(Drawable paramDrawable)
  {
    setImageDrawableInternal(paramDrawable);
  }

  @PluginApi(a=4)
  public void setImageDrawable(Drawable paramDrawable, Animation paramAnimation1, Animation paramAnimation2)
  {
    if (paramAnimation2 != null)
      a(paramAnimation2, new f(this, paramDrawable, paramAnimation1));
    do
    {
      return;
      setImageDrawableInternal(paramDrawable);
    }
    while (paramAnimation1 == null);
    a(paramAnimation1, null);
  }

  @PluginApi(a=4)
  public void setImageProcessor(ImageProcessor paramImageProcessor)
  {
    if (this.i != paramImageProcessor)
    {
      this.i = paramImageProcessor;
      a(paramImageProcessor);
    }
  }

  @PluginApi(a=4)
  public void setImageResource(int paramInt)
  {
    setImageResourceInternal(paramInt);
  }

  @PluginApi(a=4)
  public void setImageResource(int paramInt, Animation paramAnimation1, Animation paramAnimation2)
  {
    if (paramAnimation2 != null)
      a(paramAnimation2, new g(this, paramInt, paramAnimation1));
    do
    {
      return;
      setImageResourceInternal(paramInt);
    }
    while (paramAnimation1 == null);
    a(paramAnimation1, null);
  }

  @PluginApi(a=4)
  public void setImageURI(Uri paramUri)
  {
    setImageURIInternal(paramUri);
  }

  @PluginApi(a=4)
  public void setImageURI(Uri paramUri, Animation paramAnimation1, Animation paramAnimation2)
  {
    if (paramAnimation2 != null)
      a(paramAnimation2, new h(this, paramUri, paramAnimation1));
    do
    {
      return;
      setImageURIInternal(paramUri);
    }
    while (paramAnimation1 == null);
    a(paramAnimation1, null);
  }

  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    ViewForeground localViewForeground = this.f;
    if (localViewForeground == null);
    for (Drawable localDrawable = null; (localDrawable == paramDrawable) || (super.verifyDrawable(paramDrawable)); localDrawable = localViewForeground.a())
      return true;
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.ExtendImageView
 * JD-Core Version:    0.6.0
 */