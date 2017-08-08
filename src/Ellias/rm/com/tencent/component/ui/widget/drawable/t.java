package com.tencent.component.ui.widget.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

class t extends a
{
  int c;
  int d;

  t(Drawable paramDrawable, DrawableContainer paramDrawableContainer)
  {
    super(paramDrawable, paramDrawableContainer);
  }

  t(t paramt, DrawableContainer paramDrawableContainer, Resources paramResources)
  {
    super(paramt, paramDrawableContainer, paramResources);
    this.c = paramt.c;
    this.d = paramt.d;
  }

  public Drawable newDrawable()
  {
    return new SpecifiedDrawable(this, null, null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.t
 * JD-Core Version:    0.6.0
 */