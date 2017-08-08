package com.tencent.component.ui.widget.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class TextDrawable extends Drawable
{
  private static final int a = 1;
  private static final int b = 2;
  private static final int c = 3;
  private static final int[] t = { 16842804 };
  private static final int[] u = { 16842901, 16842902, 16842903, 16842904 };
  private Resources d;
  private TextPaint e;
  private StaticLayout f;
  private Layout.Alignment g = Layout.Alignment.ALIGN_NORMAL;
  private Path h;
  private ColorStateList i;
  private Rect j;
  private Rect k;
  private CharSequence l = "";
  private Drawable m;
  private int n;
  private int o;
  private int p;
  private int q;
  private int r;
  private boolean s;

  public TextDrawable(Context paramContext)
  {
    this.d = paramContext.getResources();
    this.j = new Rect();
    this.k = new Rect();
    this.e = new TextPaint(1);
    this.e.density = this.d.getDisplayMetrics().density;
    this.e.setDither(true);
    int i2 = 15;
    TypedArray localTypedArray1 = paramContext.getTheme().obtainStyledAttributes(t);
    int i3 = localTypedArray1.getResourceId(0, i1);
    localTypedArray1.recycle();
    if (i3 != i1);
    for (TypedArray localTypedArray2 = paramContext.obtainStyledAttributes(i3, u); ; localTypedArray2 = null)
    {
      ColorStateList localColorStateList1;
      int i4;
      if (localTypedArray2 != null)
      {
        int i5 = 0;
        ColorStateList localColorStateList2 = null;
        int i6 = i1;
        if (i5 < localTypedArray2.getIndexCount())
        {
          int i8 = localTypedArray2.getIndex(i5);
          switch (i8)
          {
          default:
          case 0:
          case 1:
          case 2:
          case 3:
          }
          while (true)
          {
            i5++;
            break;
            i2 = localTypedArray1.getDimensionPixelSize(i8, i2);
            continue;
            i6 = localTypedArray1.getInt(i8, i6);
            continue;
            i1 = localTypedArray1.getInt(i8, i1);
            continue;
            localColorStateList2 = localTypedArray1.getColorStateList(i8);
          }
        }
        localTypedArray2.recycle();
        int i7 = i6;
        localColorStateList1 = localColorStateList2;
        i4 = i1;
        i1 = i7;
      }
      while (true)
      {
        Typeface localTypeface;
        if (localColorStateList1 != null)
        {
          a(localColorStateList1);
          c(i2);
          localTypeface = null;
          switch (i1)
          {
          default:
          case 1:
          case 2:
          case 3:
          }
        }
        while (true)
        {
          a(localTypeface, i4);
          return;
          localColorStateList1 = ColorStateList.valueOf(-16777216);
          break;
          localTypeface = Typeface.SANS_SERIF;
          continue;
          localTypeface = Typeface.SERIF;
          continue;
          localTypeface = Typeface.MONOSPACE;
        }
        i4 = i1;
        localColorStateList1 = null;
      }
    }
  }

  private boolean a(int[] paramArrayOfInt)
  {
    int i1 = this.i.getColorForState(paramArrayOfInt, -1);
    if (this.e.getColor() != i1)
    {
      this.e.setColor(i1);
      return true;
    }
    return false;
  }

  private void c(float paramFloat)
  {
    if (paramFloat != this.e.getTextSize())
    {
      this.e.setTextSize(paramFloat);
      j();
    }
  }

  private void j()
  {
    if (this.h != null)
    {
      this.f = null;
      this.j.setEmpty();
    }
    while (true)
    {
      invalidateSelf();
      return;
      float f1 = Layout.getDesiredWidth(this.l, this.e);
      this.f = new StaticLayout(this.l, this.e, (int)f1, this.g, 1.0F, 0.0F, false);
      this.j.set(0, 0, this.f.getWidth(), this.f.getHeight());
    }
  }

  public CharSequence a()
  {
    return this.l;
  }

  public void a(float paramFloat)
  {
    a(2, paramFloat);
  }

  public void a(int paramInt)
  {
    a(ColorStateList.valueOf(paramInt));
  }

  public void a(int paramInt, float paramFloat)
  {
    c(TypedValue.applyDimension(paramInt, paramFloat, this.d.getDisplayMetrics()));
  }

  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i1 = 1;
    int i2 = this.o;
    int i3 = 0;
    if (i2 != paramInt1)
    {
      this.o = paramInt1;
      i3 = i1;
    }
    if (this.q != paramInt2)
    {
      this.q = paramInt2;
      i3 = i1;
    }
    if (this.p != paramInt3)
    {
      this.p = paramInt3;
      i3 = i1;
    }
    if (this.r != paramInt4)
      this.r = paramInt4;
    while (true)
    {
      if (i1 != 0)
        invalidateSelf();
      return;
      i1 = i3;
    }
  }

  public void a(ColorStateList paramColorStateList)
  {
    this.i = paramColorStateList;
    a(getState());
  }

  public void a(Path paramPath)
  {
    if (this.h != paramPath)
    {
      this.h = paramPath;
      j();
    }
  }

  public void a(Typeface paramTypeface)
  {
    if (this.e.getTypeface() != paramTypeface)
    {
      this.e.setTypeface(paramTypeface);
      j();
    }
  }

  public void a(Typeface paramTypeface, int paramInt)
  {
    if (paramInt > 0)
    {
      Typeface localTypeface;
      int i1;
      label28: TextPaint localTextPaint2;
      float f1;
      if (paramTypeface == null)
      {
        localTypeface = Typeface.defaultFromStyle(paramInt);
        a(localTypeface);
        if (localTypeface == null)
          break label101;
        i1 = localTypeface.getStyle();
        int i2 = paramInt & (i1 ^ 0xFFFFFFFF);
        TextPaint localTextPaint1 = this.e;
        int i3 = i2 & 0x1;
        boolean bool = false;
        if (i3 != 0)
          bool = true;
        localTextPaint1.setFakeBoldText(bool);
        localTextPaint2 = this.e;
        if ((i2 & 0x2) == 0)
          break label107;
        f1 = -0.25F;
      }
      while (true)
      {
        localTextPaint2.setTextSkewX(f1);
        return;
        localTypeface = Typeface.create(paramTypeface, paramInt);
        break;
        label101: i1 = 0;
        break label28;
        label107: f1 = 0.0F;
      }
    }
    this.e.setFakeBoldText(false);
    this.e.setTextSkewX(0.0F);
    a(paramTypeface);
  }

  public void a(Drawable paramDrawable)
  {
    b(paramDrawable);
  }

  public void a(Layout.Alignment paramAlignment)
  {
    if (this.g != paramAlignment)
    {
      this.g = paramAlignment;
      j();
    }
  }

  public void a(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null)
      paramCharSequence = "";
    this.l = paramCharSequence;
    j();
  }

  public float b()
  {
    return this.e.getTextSize();
  }

  public void b(float paramFloat)
  {
    if (paramFloat != this.e.getTextScaleX())
    {
      this.e.setTextScaleX(paramFloat);
      j();
    }
  }

  public void b(int paramInt)
  {
    a(new ColorDrawable(paramInt));
  }

  @Deprecated
  public void b(Drawable paramDrawable)
  {
    if (paramDrawable == this.m)
      return;
    this.m = paramDrawable;
    this.n = 0;
    invalidateSelf();
  }

  public float c()
  {
    return this.e.getTextScaleX();
  }

  public void c(int paramInt)
  {
    if ((paramInt != 0) && (paramInt == this.n))
      return;
    Drawable localDrawable = null;
    if (paramInt != 0)
      localDrawable = this.d.getDrawable(paramInt);
    a(localDrawable);
    this.n = paramInt;
  }

  public Layout.Alignment d()
  {
    return this.g;
  }

  public void draw(Canvas paramCanvas)
  {
    Drawable localDrawable = this.m;
    if (localDrawable != null)
    {
      localDrawable.setBounds(0, 0, getIntrinsicWidth(), getIntrinsicHeight());
      localDrawable.draw(paramCanvas);
    }
    int i1 = paramCanvas.save();
    if ((!this.j.isEmpty()) && (this.g == Layout.Alignment.ALIGN_CENTER))
    {
      int i2 = getIntrinsicWidth() - (this.j.right - this.j.left);
      int i3 = getIntrinsicHeight() - (this.j.bottom - this.j.top);
      int i4 = Math.max(0, i2) / 2;
      int i5 = Math.max(0, i3) / 2;
      paramCanvas.translate(i4, i5);
    }
    if (this.h == null)
      this.f.draw(paramCanvas);
    while (true)
    {
      paramCanvas.restoreToCount(i1);
      return;
      paramCanvas.drawTextOnPath(this.l.toString(), this.h, 0.0F, 0.0F, this.e);
    }
  }

  public Typeface e()
  {
    return this.e.getTypeface();
  }

  public int f()
  {
    return this.q;
  }

  public int g()
  {
    return this.r;
  }

  public int getIntrinsicHeight()
  {
    if (this.k.isEmpty())
    {
      if (this.j.isEmpty())
        return -1;
      return this.j.bottom - this.j.top + (this.q + this.r);
    }
    return this.k.bottom - this.k.top + (this.q + this.r);
  }

  public int getIntrinsicWidth()
  {
    if (this.k.isEmpty())
    {
      if (this.j.isEmpty())
        return -1;
      return this.j.right - this.j.left + (this.o + this.p);
    }
    return this.k.right - this.k.left + (this.o + this.p);
  }

  public int getOpacity()
  {
    return this.e.getAlpha();
  }

  public int h()
  {
    return this.o;
  }

  public int i()
  {
    return this.p;
  }

  public boolean isStateful()
  {
    return this.i.isStateful();
  }

  protected void onBoundsChange(Rect paramRect)
  {
    this.k.set(paramRect);
  }

  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    return a(paramArrayOfInt);
  }

  public void setAlpha(int paramInt)
  {
    if (this.e.getAlpha() != paramInt)
      this.e.setAlpha(paramInt);
  }

  public void setColorFilter(ColorFilter paramColorFilter)
  {
    if (this.e.getColorFilter() != paramColorFilter)
      this.e.setColorFilter(paramColorFilter);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.TextDrawable
 * JD-Core Version:    0.6.0
 */