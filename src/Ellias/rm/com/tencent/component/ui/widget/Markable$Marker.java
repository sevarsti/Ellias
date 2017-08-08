package com.tencent.component.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Markable$Marker
  implements Markable
{
  private View a;
  private Context b;
  private Drawable c;
  private boolean d = false;
  private boolean e = false;
  private int f = 1;
  private int g;
  private int h;
  private int i;
  private int j;
  private int k = -1;
  private int l = -1;
  private boolean m = false;
  private boolean n = false;
  private Markable.OnMarkerClickListener o;
  private Markable p;

  public Markable$Marker(View paramView)
  {
    this(paramView, null);
  }

  public Markable$Marker(View paramView, AttributeSet paramAttributeSet)
  {
    this.a = paramView;
    this.b = paramView.getContext();
    if ((paramView instanceof Markable))
      a((Markable)paramView);
  }

  private static int a(int paramInt1, int paramInt2, int paramInt3)
  {
    int i1 = 0;
    switch (paramInt3)
    {
    default:
      i1 = (paramInt1 - paramInt2) / 2;
    case 2:
    case 5:
    case 6:
      return i1;
    case 3:
      return paramInt1 - paramInt2;
    case 4:
      return paramInt1 - paramInt2;
    case 7:
    }
    return paramInt1 - paramInt2;
  }

  private int b()
  {
    if (this.k > 0)
      return this.k;
    return this.c.getIntrinsicWidth();
  }

  private static int b(int paramInt1, int paramInt2, int paramInt3)
  {
    int i1 = 0;
    switch (paramInt3)
    {
    case 6:
    case 7:
    default:
      i1 = (paramInt1 - paramInt2) / 2;
    case 2:
    case 3:
    case 8:
      return i1;
    case 4:
      return paramInt1 - paramInt2;
    case 5:
      return paramInt1 - paramInt2;
    case 9:
    }
    return paramInt1 - paramInt2;
  }

  private boolean b(MotionEvent paramMotionEvent)
  {
    if ((!g()) || (this.o == null));
    int i1;
    int i2;
    int i3;
    int i4;
    int i5;
    int i6;
    do
    {
      return false;
      i1 = (int)paramMotionEvent.getX();
      i2 = (int)paramMotionEvent.getY();
      i3 = d();
      i4 = i3 + b();
      i5 = e();
      i6 = i5 + c();
    }
    while ((i1 < i3) || (i1 > i4) || (i2 < i5) || (i2 > i6));
    return true;
  }

  private int c()
  {
    if (this.l > 0)
      return this.l;
    return this.c.getIntrinsicHeight();
  }

  private int d()
  {
    return this.g + this.i;
  }

  private int e()
  {
    return this.h + this.j;
  }

  private void f()
  {
    if (this.o != null)
      if (this.p == null)
        break label30;
    label30: for (Object localObject = this.p; ; localObject = this)
    {
      this.o.onClick((Markable)localObject);
      return;
    }
  }

  private boolean g()
  {
    return (isMarkerVisible()) && (this.c != null);
  }

  public void a()
  {
    if (this.c == null)
      return;
    int i1 = this.a.getWidth();
    int i2 = this.a.getHeight();
    int i3 = b();
    int i4 = c();
    this.g = a(i1, i3, this.f);
    this.h = b(i2, i4, this.f);
    this.c.setBounds(0, 0, i3, i4);
  }

  public void a(Canvas paramCanvas)
  {
    if (this.m)
    {
      a();
      this.m = false;
    }
    if (g())
    {
      int i1 = d();
      int i2 = e();
      if ((i1 != 0) || (i2 != 0))
        paramCanvas.translate(i1, i2);
      this.c.draw(paramCanvas);
    }
  }

  protected void a(Markable paramMarkable)
  {
    this.p = paramMarkable;
  }

  public boolean a(MotionEvent paramMotionEvent)
  {
    int i1 = paramMotionEvent.getAction();
    if (b(paramMotionEvent))
    {
      if (i1 == 0)
      {
        this.n = true;
        return true;
      }
      if ((i1 == 1) && (this.n))
      {
        f();
        this.n = false;
        return true;
      }
    }
    if ((i1 == 1) || (i1 == 3))
      this.n = false;
    return false;
  }

  public void getMarkerSize(int[] paramArrayOfInt)
  {
    if ((paramArrayOfInt == null) || (paramArrayOfInt.length < 2))
      throw new IllegalArgumentException("location must be an array of two integers");
    int i2;
    int i1;
    if (this.c != null)
    {
      i2 = b();
      i1 = c();
    }
    while (true)
    {
      paramArrayOfInt[0] = i2;
      paramArrayOfInt[1] = i1;
      return;
      i1 = 0;
      i2 = 0;
    }
  }

  public boolean isMarkerVisible()
  {
    return (this.d) || ((this.e) && (this.a.isSelected()));
  }

  public void setMarker(int paramInt)
  {
    setMarker(this.b.getResources().getDrawable(paramInt));
  }

  public void setMarker(Drawable paramDrawable)
  {
    if (this.c == paramDrawable)
      return;
    this.c = paramDrawable;
    this.m = true;
    this.a.invalidate();
  }

  public void setMarkerPaddingOffset(int paramInt1, int paramInt2)
  {
    if ((this.i == paramInt1) && (this.j == paramInt2))
      return;
    this.i = paramInt1;
    this.j = paramInt2;
    this.a.invalidate();
  }

  public void setMarkerPosition(int paramInt)
  {
    if (this.f == paramInt)
      return;
    this.f = paramInt;
    this.m = true;
    this.a.invalidate();
  }

  public void setMarkerSize(int paramInt1, int paramInt2)
  {
    if ((this.k == paramInt1) && (this.l == paramInt2))
      return;
    this.k = paramInt1;
    this.l = paramInt2;
    this.m = true;
    this.a.invalidate();
  }

  public void setMarkerVisible(boolean paramBoolean)
  {
    if (this.d == paramBoolean)
      return;
    this.d = paramBoolean;
    this.a.invalidate();
  }

  public void setMarkerVisibleWhenSelected(boolean paramBoolean)
  {
    if (this.e == paramBoolean)
      return;
    this.e = paramBoolean;
    this.a.invalidate();
  }

  public void setOnMarkerClickListener(Markable.OnMarkerClickListener paramOnMarkerClickListener)
  {
    this.o = paramOnMarkerClickListener;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.Markable.Marker
 * JD-Core Version:    0.6.0
 */