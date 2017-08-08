package com.tencent.component.ui.widget.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;

abstract class a extends Drawable.ConstantState
{
  Drawable a;
  int b;
  private boolean c;
  private boolean d;

  a(Drawable paramDrawable, DrawableContainer paramDrawableContainer)
  {
    this.a = paramDrawable;
    this.a.setCallback(paramDrawableContainer);
  }

  a(a parama, DrawableContainer paramDrawableContainer, Resources paramResources)
  {
    if (parama != null)
      if (paramResources == null)
        break label46;
    label46: for (this.a = parama.a.getConstantState().newDrawable(paramResources); ; this.a = parama.a.getConstantState().newDrawable())
    {
      this.a.setCallback(paramDrawableContainer);
      this.d = true;
      this.c = true;
      return;
    }
  }

  boolean a()
  {
    if (!this.c)
      if (this.a.getConstantState() == null)
        break label34;
    label34: for (boolean bool = true; ; bool = false)
    {
      this.d = bool;
      this.c = true;
      return this.d;
    }
  }

  public int getChangingConfigurations()
  {
    return this.b;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.a
 * JD-Core Version:    0.6.0
 */