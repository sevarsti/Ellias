package com.tencent.component.ui.widget.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

class r extends a
{
  r(Drawable paramDrawable, ScaleDrawable paramScaleDrawable)
  {
    super(paramDrawable, paramScaleDrawable);
  }

  r(r paramr, ScaleDrawable paramScaleDrawable, Resources paramResources)
  {
    super(paramr, paramScaleDrawable, paramResources);
  }

  public Drawable newDrawable()
  {
    return new ScaleDrawable(this, null, null);
  }

  public Drawable newDrawable(Resources paramResources)
  {
    return new ScaleDrawable(this, paramResources, null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.r
 * JD-Core Version:    0.6.0
 */