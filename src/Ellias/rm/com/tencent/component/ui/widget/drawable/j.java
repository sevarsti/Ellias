package com.tencent.component.ui.widget.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

class j extends a
{
  j(Drawable paramDrawable, DrawableContainer paramDrawableContainer)
  {
    super(paramDrawable, paramDrawableContainer);
  }

  j(a parama, DrawableContainer paramDrawableContainer, Resources paramResources)
  {
    super(parama, paramDrawableContainer, paramResources);
  }

  public Drawable newDrawable()
  {
    return new MatrixDrawable(this, null, null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.j
 * JD-Core Version:    0.6.0
 */