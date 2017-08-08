package com.tencent.component.ui.widget.drawable;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.ShapeDrawable;

public class XfermodeDrawable extends DrawableContainer
{
  private v a;

  public XfermodeDrawable(Drawable paramDrawable)
  {
    this(paramDrawable, null);
  }

  public XfermodeDrawable(Drawable paramDrawable, Xfermode paramXfermode)
  {
    if (!a(paramDrawable))
      throw new RuntimeException("No xfermode support for " + paramDrawable.getClass().getSimpleName());
    this.a = new v(paramDrawable, this, paramXfermode);
    a(this.a);
  }

  private XfermodeDrawable(v paramv, Resources paramResources)
  {
    this.a = paramv;
    a(this.a);
  }

  public static Drawable a(Drawable paramDrawable, Xfermode paramXfermode)
  {
    if ((paramDrawable != null) && (a(paramDrawable)))
      paramDrawable = new XfermodeDrawable(paramDrawable, paramXfermode);
    return paramDrawable;
  }

  public static boolean a(Drawable paramDrawable)
  {
    return (paramDrawable == null) || (b(paramDrawable) != null);
  }

  private static Paint b(Drawable paramDrawable)
  {
    if (paramDrawable == null);
    do
    {
      do
        return null;
      while ((paramDrawable instanceof XfermodeDrawable));
      if ((paramDrawable instanceof BitmapDrawable))
        return ((BitmapDrawable)paramDrawable).getPaint();
      if ((paramDrawable instanceof NinePatchDrawable))
        return ((NinePatchDrawable)paramDrawable).getPaint();
      if ((paramDrawable instanceof ShapeDrawable))
        return ((ShapeDrawable)paramDrawable).getPaint();
      if ((paramDrawable instanceof ImageDrawable))
        return ((ImageDrawable)paramDrawable).c();
    }
    while (!(paramDrawable instanceof DrawableContainer));
    return b(((DrawableContainer)paramDrawable).a());
  }

  public void a(Xfermode paramXfermode)
  {
    if (this.a.c != paramXfermode)
    {
      this.a.c = paramXfermode;
      invalidateSelf();
    }
  }

  public void draw(Canvas paramCanvas)
  {
    Paint localPaint = b(this.a.a);
    Xfermode localXfermode = this.a.c;
    if ((localPaint != null) && (localPaint.getXfermode() != localXfermode))
      localPaint.setXfermode(localXfermode);
    super.draw(paramCanvas);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.XfermodeDrawable
 * JD-Core Version:    0.6.0
 */