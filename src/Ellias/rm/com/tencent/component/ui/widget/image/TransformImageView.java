package com.tencent.component.ui.widget.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class TransformImageView extends ImageView
{
  public static int a = 0;
  public static int b = 0;
  public static int c = 0;
  public static int d = 0;
  public static final float e = 0.2F;
  public static final float f = 4.0F;
  public static final float g = 0.25F;
  protected Matrix h = new Matrix();
  protected Matrix i = new Matrix();
  protected final Matrix j = new Matrix();
  private final float[] k = new float[9];
  private ImageView.ScaleType l;
  private Interpolator m;
  private boolean n = false;
  private float o = 4.0F;
  private float p = 0.25F;
  private float q = 0.2F;
  private Runnable r = null;
  private TransformImageView.InitialImageShowStrategy s = new TransformImageView.PictureViewerShowStrategy();

  public TransformImageView(Context paramContext)
  {
    this(paramContext, null);
  }

  public TransformImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public TransformImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private float a(Matrix paramMatrix)
  {
    float f1 = Math.abs(a(paramMatrix, 0));
    if (f1 - 0.0F < 1.0E-006D)
      f1 = Math.abs(a(paramMatrix, 1));
    return f1;
  }

  private float a(Matrix paramMatrix, int paramInt)
  {
    paramMatrix.getValues(this.k);
    return this.k[paramInt];
  }

  private void b(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.i.postScale(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }

  private void d(float paramFloat1, float paramFloat2)
  {
    this.i.postTranslate(paramFloat1, paramFloat2);
  }

  private void e(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.i.postRotate(paramFloat1, paramFloat2, paramFloat3);
  }

  private int getAvailableHeight()
  {
    return getHeight() - getPaddingTop() - getPaddingBottom();
  }

  private int getAvailableWidth()
  {
    return getWidth() - getPaddingLeft() - getPaddingRight();
  }

  private void setScaleTypeInternal(ImageView.ScaleType paramScaleType)
  {
    super.setScaleType(paramScaleType);
  }

  protected int a(boolean paramBoolean1, boolean paramBoolean2)
  {
    int i1 = 0;
    Drawable localDrawable = getDrawable();
    if (localDrawable == null)
      return 0;
    RectF localRectF = new RectF(0.0F, 0.0F, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
    getTransform().mapRect(localRectF);
    float f1 = localRectF.height();
    float f2 = localRectF.width();
    int i3;
    float f3;
    if (paramBoolean2)
    {
      i3 = getHeight();
      if (f1 < i3)
        f3 = (i3 - f1) / 2.0F - localRectF.top;
    }
    while (true)
    {
      float f4 = 0.0F;
      int i2;
      if (paramBoolean1)
      {
        i2 = getWidth();
        if (f2 >= i2)
          break label226;
        f4 = (i2 - f2) / 2.0F - localRectF.left;
      }
      while (true)
      {
        d(f4, f3);
        c();
        return i1;
        if (localRectF.top > 0.0F)
        {
          int i5 = 0x0 | b;
          float f6 = -localRectF.top;
          i1 = i5;
          f3 = f6;
          break;
        }
        if (localRectF.bottom >= i3)
          break label292;
        int i4 = 0x0 | d;
        float f5 = getHeight() - localRectF.bottom;
        i1 = i4;
        f3 = f5;
        break;
        label226: if (localRectF.left > 0.0F)
        {
          i1 |= a;
          f4 = -localRectF.left;
          continue;
        }
        boolean bool = localRectF.right < i2;
        f4 = 0.0F;
        if (!bool)
          continue;
        i1 |= c;
        f4 = i2 - localRectF.right;
      }
      label292: i1 = 0;
      f3 = 0.0F;
    }
  }

  public void a(float paramFloat)
  {
    float f1 = getAvailableWidth() / 2.0F;
    float f2 = getAvailableHeight() / 2.0F;
    if ((f1 <= 0.0F) || (f2 <= 0.0F))
      return;
    a(paramFloat, f1, f2);
  }

  public void a(float paramFloat1, float paramFloat2)
  {
    float f1;
    if (paramFloat2 > paramFloat1)
    {
      f1 = paramFloat2;
      this.o = f1;
      if (paramFloat2 <= paramFloat1)
        break label30;
    }
    while (true)
    {
      this.p = paramFloat1;
      return;
      f1 = paramFloat1;
      break;
      label30: paramFloat1 = paramFloat2;
    }
  }

  public void a(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    b(paramFloat1 * getZoomScale(), paramFloat2, paramFloat3);
  }

  public void a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    long l1 = System.currentTimeMillis();
    float f1 = getZoomScale();
    i();
    post(new p(this, l1, paramFloat4, f1, paramFloat1, paramFloat2, paramFloat3));
  }

  public boolean a()
  {
    return (this.n) && (getScaleType() == ImageView.ScaleType.MATRIX);
  }

  public int b(float paramFloat1, float paramFloat2)
  {
    d(paramFloat1, paramFloat2);
    return a(true, true);
  }

  public void b()
  {
    if (!this.i.isIdentity())
    {
      this.i.reset();
      c();
    }
  }

  public void b(float paramFloat)
  {
    float f1 = getAvailableWidth() / 2.0F;
    float f2 = getAvailableHeight() / 2.0F;
    if ((f1 <= 0.0F) || (f2 <= 0.0F))
      return;
    b(paramFloat, f1, f2);
  }

  public void b(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    float f1 = h();
    float f2 = g();
    if (paramFloat1 < f1)
      paramFloat1 = f1;
    while (true)
    {
      float f3 = paramFloat1 / getZoomScale();
      b(f3, f3, paramFloat2, paramFloat3);
      a(true, true);
      return;
      if (paramFloat1 <= f2)
        continue;
      paramFloat1 = f2;
    }
  }

  protected void c()
  {
    if (!a())
      return;
    setImageMatrix(getTransform());
  }

  public void c(float paramFloat)
  {
    float f1 = getAvailableWidth() / 2.0F;
    float f2 = getAvailableHeight() / 2.0F;
    if ((f1 <= 0.0F) || (f2 <= 0.0F))
      return;
    d(paramFloat, f1, f2);
  }

  public void c(float paramFloat1, float paramFloat2)
  {
    d(paramFloat1, paramFloat2);
    c();
  }

  public void c(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    float f1 = paramFloat1 / getZoomScale();
    b(f1, f1, paramFloat2, paramFloat3);
    a(true, true);
  }

  public void d(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    e(paramFloat1, paramFloat2, paramFloat3);
    c();
  }

  protected boolean d()
  {
    if (!a());
    int i1;
    int i2;
    do
    {
      return false;
      i1 = getAvailableWidth();
      i2 = getAvailableHeight();
    }
    while ((i1 <= 0) || (i2 <= 0));
    Drawable localDrawable = getDrawable();
    Matrix localMatrix = this.h;
    if (localDrawable == null)
      localMatrix.reset();
    while (true)
    {
      c();
      return true;
      if (this.s == null)
        continue;
      this.s.a(this, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight(), i1, i2, localMatrix);
    }
  }

  public void e()
  {
    a(1.0F + this.q);
  }

  public void f()
  {
    a(1.0F - this.q);
  }

  public float g()
  {
    return this.o;
  }

  protected Matrix getTransform()
  {
    Matrix localMatrix = this.j;
    localMatrix.set(this.h);
    localMatrix.postConcat(this.i);
    return localMatrix;
  }

  public Matrix getTransformMatrix()
  {
    return new Matrix(this.i);
  }

  public RectF getTransformRect()
  {
    Drawable localDrawable = getDrawable();
    if (localDrawable == null)
      return null;
    RectF localRectF = new RectF(0.0F, 0.0F, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
    getTransform().mapRect(localRectF);
    return localRectF;
  }

  public float getZoomScale()
  {
    return a(this.i);
  }

  public float h()
  {
    return this.p;
  }

  protected void i()
  {
    if (this.m == null)
      this.m = new AccelerateDecelerateInterpolator();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0))
      return true;
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (getZoomScale() > 1.0F))
    {
      b(1.0F);
      return true;
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    Runnable localRunnable = this.r;
    if (localRunnable != null)
    {
      this.r = null;
      localRunnable.run();
    }
    do
      return;
    while (getDrawable() == null);
    d();
  }

  protected boolean setFrame(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool = super.setFrame(paramInt1, paramInt2, paramInt3, paramInt4);
    d();
    return bool;
  }

  public void setImageBitmap(Bitmap paramBitmap)
  {
    super.setImageBitmap(paramBitmap);
    if (getWidth() <= 0)
    {
      this.r = new n(this);
      return;
    }
    d();
  }

  public void setImageDrawable(Drawable paramDrawable)
  {
    super.setImageDrawable(paramDrawable);
    if (paramDrawable == null)
    {
      this.h.reset();
      this.i.reset();
      return;
    }
    if (getWidth() <= 0)
    {
      this.r = new o(this);
      return;
    }
    d();
  }

  public void setImageResource(int paramInt)
  {
    super.setImageResource(paramInt);
    d();
  }

  public void setImageURI(Uri paramUri)
  {
    super.setImageURI(paramUri);
    d();
  }

  public void setInitialImageShowStratege(TransformImageView.InitialImageShowStrategy paramInitialImageShowStrategy)
  {
    this.s = paramInitialImageShowStrategy;
  }

  public void setScaleType(ImageView.ScaleType paramScaleType)
  {
    super.setScaleType(paramScaleType);
    this.l = paramScaleType;
  }

  public void setTransformEnabled(boolean paramBoolean)
  {
    if (this.n != paramBoolean)
    {
      this.n = paramBoolean;
      if (!paramBoolean)
        break label30;
      setScaleTypeInternal(ImageView.ScaleType.MATRIX);
    }
    while (true)
    {
      d();
      return;
      label30: ImageView.ScaleType localScaleType = this.l;
      if (localScaleType == null)
        continue;
      setScaleTypeInternal(localScaleType);
    }
  }

  public void setTransformMatrix(Matrix paramMatrix)
  {
    this.i.set(paramMatrix);
  }

  public void setZoomStep(float paramFloat)
  {
    if ((paramFloat <= 0.0F) || (paramFloat >= 1.0F))
      return;
    this.q = paramFloat;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.TransformImageView
 * JD-Core Version:    0.6.0
 */