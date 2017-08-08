package com.tencent.component.ui.widget.image;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class ViewForeground
{
  private Context a;
  private View b;
  private Drawable c;
  private int d;
  private int e;
  private boolean f = false;
  private boolean g = true;

  public ViewForeground(View paramView)
  {
    this.a = paramView.getContext();
    this.b = paramView;
  }

  public ViewForeground(View paramView, int paramInt)
  {
    this(paramView);
    a(paramInt);
  }

  public ViewForeground(View paramView, Drawable paramDrawable)
  {
    this(paramView);
    a(paramDrawable);
  }

  private void b(Drawable paramDrawable)
  {
    View localView = this.b;
    if (this.c != null)
    {
      this.c.setCallback(null);
      localView.unscheduleDrawable(this.c);
    }
    this.c = paramDrawable;
    if (paramDrawable != null)
    {
      paramDrawable.setCallback(localView);
      if (paramDrawable.isStateful())
        paramDrawable.setState(localView.getDrawableState());
      this.d = paramDrawable.getIntrinsicWidth();
      this.e = paramDrawable.getIntrinsicHeight();
      return;
    }
    this.e = -1;
    this.d = -1;
  }

  public Drawable a()
  {
    return this.c;
  }

  public void a(int paramInt)
  {
    a(this.a.getResources().getDrawable(paramInt));
  }

  public void a(Canvas paramCanvas)
  {
    Drawable localDrawable = this.c;
    int i;
    int j;
    int m;
    int k;
    if (localDrawable != null)
    {
      View localView = this.b;
      if (this.f)
      {
        this.f = false;
        i = localView.getWidth();
        j = localView.getHeight();
        if (!this.g)
          break label93;
        m = localView.getPaddingLeft();
        i -= localView.getPaddingRight();
        k = localView.getPaddingTop();
        j -= localView.getPaddingBottom();
      }
    }
    while (true)
    {
      localDrawable.setBounds(m, k, i, j);
      localDrawable.draw(paramCanvas);
      return;
      label93: k = 0;
      m = 0;
    }
  }

  public void a(Drawable paramDrawable)
  {
    if (this.c != paramDrawable)
    {
      View localView = this.b;
      int i = this.d;
      int j = this.e;
      b(paramDrawable);
      if ((i != this.d) || (j != this.e))
        localView.requestLayout();
      localView.invalidate();
    }
  }

  public void a(boolean paramBoolean)
  {
    if (this.g != paramBoolean)
    {
      this.g = paramBoolean;
      c();
    }
  }

  public void b()
  {
    if ((this.c != null) && (this.c.isStateful()))
      this.c.setState(this.b.getDrawableState());
  }

  public void c()
  {
    this.f = true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.ViewForeground
 * JD-Core Version:    0.6.0
 */