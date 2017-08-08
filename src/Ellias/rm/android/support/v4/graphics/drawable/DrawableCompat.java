package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;

public class DrawableCompat
{
  static final DrawableImpl IMPL;

  static
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 19)
    {
      IMPL = new KitKatDrawableImpl();
      return;
    }
    if (i >= 11)
    {
      IMPL = new HoneycombDrawableImpl();
      return;
    }
    IMPL = new BaseDrawableImpl();
  }

  public static boolean isAutoMirrored(Drawable paramDrawable)
  {
    return IMPL.isAutoMirrored(paramDrawable);
  }

  public static void jumpToCurrentState(Drawable paramDrawable)
  {
    IMPL.jumpToCurrentState(paramDrawable);
  }

  public static void setAutoMirrored(Drawable paramDrawable, boolean paramBoolean)
  {
    IMPL.setAutoMirrored(paramDrawable, paramBoolean);
  }

  static class BaseDrawableImpl
    implements DrawableCompat.DrawableImpl
  {
    public boolean isAutoMirrored(Drawable paramDrawable)
    {
      return false;
    }

    public void jumpToCurrentState(Drawable paramDrawable)
    {
    }

    public void setAutoMirrored(Drawable paramDrawable, boolean paramBoolean)
    {
    }
  }

  static abstract interface DrawableImpl
  {
    public abstract boolean isAutoMirrored(Drawable paramDrawable);

    public abstract void jumpToCurrentState(Drawable paramDrawable);

    public abstract void setAutoMirrored(Drawable paramDrawable, boolean paramBoolean);
  }

  static class HoneycombDrawableImpl extends DrawableCompat.BaseDrawableImpl
  {
    public void jumpToCurrentState(Drawable paramDrawable)
    {
      DrawableCompatHoneycomb.jumpToCurrentState(paramDrawable);
    }
  }

  static class KitKatDrawableImpl extends DrawableCompat.HoneycombDrawableImpl
  {
    public boolean isAutoMirrored(Drawable paramDrawable)
    {
      return DrawableCompatKitKat.isAutoMirrored(paramDrawable);
    }

    public void setAutoMirrored(Drawable paramDrawable, boolean paramBoolean)
    {
      DrawableCompatKitKat.setAutoMirrored(paramDrawable, paramBoolean);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     android.support.v4.graphics.drawable.DrawableCompat
 * JD-Core Version:    0.6.0
 */