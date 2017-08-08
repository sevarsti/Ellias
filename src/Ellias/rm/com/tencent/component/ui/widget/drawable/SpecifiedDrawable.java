package com.tencent.component.ui.widget.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class SpecifiedDrawable extends DrawableContainer
{
  private t a;

  public SpecifiedDrawable(Drawable paramDrawable)
  {
    this(paramDrawable, -1, -1);
  }

  public SpecifiedDrawable(Drawable paramDrawable, int paramInt1, int paramInt2)
  {
    this.a = new t(paramDrawable, this);
    this.a.c = paramInt1;
    this.a.d = paramInt2;
    a(this.a);
  }

  private SpecifiedDrawable(t paramt, Resources paramResources)
  {
    this.a = new t(paramt, this, paramResources);
    a(this.a);
  }

  public void a(int paramInt1, int paramInt2)
  {
    if ((this.a.c != paramInt1) || (this.a.d != paramInt2))
    {
      this.a.c = paramInt1;
      this.a.d = paramInt2;
      invalidateSelf();
    }
  }

  public int getIntrinsicHeight()
  {
    int i = this.a.d;
    if (i > 0)
      return i;
    return super.getIntrinsicHeight();
  }

  public int getIntrinsicWidth()
  {
    int i = this.a.c;
    if (i > 0)
      return i;
    return super.getIntrinsicWidth();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.SpecifiedDrawable
 * JD-Core Version:    0.6.0
 */