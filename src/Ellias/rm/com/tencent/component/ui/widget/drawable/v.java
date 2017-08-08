package com.tencent.component.ui.widget.drawable;

import android.content.res.Resources;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;

class v extends a
{
  Xfermode c;

  v(Drawable paramDrawable, DrawableContainer paramDrawableContainer, Xfermode paramXfermode)
  {
    super(paramDrawable, paramDrawableContainer);
    this.c = paramXfermode;
  }

  v(v paramv, DrawableContainer paramDrawableContainer, Resources paramResources)
  {
    super(paramv, paramDrawableContainer, paramResources);
    this.c = paramv.c;
  }

  public Drawable newDrawable()
  {
    return new XfermodeDrawable(this, null, null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.v
 * JD-Core Version:    0.6.0
 */