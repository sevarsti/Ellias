package com.tencent.component.ui.widget.drawable;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.util.SparseArray;

class l extends m
{
  private DrawableContainer a;
  private DrawableContainer.DrawableContainerState b;
  private SparseArray c;

  public l(DrawableContainer paramDrawableContainer)
  {
    this.a = paramDrawableContainer;
    this.c = new SparseArray(3);
    this.b = ((DrawableContainer.DrawableContainerState)this.a.getConstantState());
    for (int i = 0; i < this.b.getChildCount(); i++)
    {
      Drawable localDrawable = this.b.getChildren()[i];
      if (!(localDrawable instanceof BitmapDrawable))
        continue;
      this.c.put(i, new n((BitmapDrawable)localDrawable));
    }
  }

  private m b()
  {
    Drawable localDrawable = this.a.getCurrent();
    if (localDrawable != null)
      for (int i = 0; i < this.b.getChildCount(); i++)
        if (this.b.getChildren()[i] == localDrawable)
          return (m)this.c.get(i);
    return null;
  }

  public void a(Paint paramPaint)
  {
    m localm = b();
    if (localm != null)
      localm.a(paramPaint);
  }

  public void a(Rect paramRect)
  {
    m localm = b();
    if (localm != null)
      localm.a(paramRect);
  }

  public boolean a()
  {
    return this.a.getCurrent() != null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.l
 * JD-Core Version:    0.6.0
 */