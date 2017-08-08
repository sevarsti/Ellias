package com.tencent.component.ui.widget.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class GifDrawable extends AnimationDrawable
{
  private int a = -1;
  private int b = -1;
  private int c = 0;

  public GifDrawable()
  {
    b();
  }

  public GifDrawable(Bitmap paramBitmap, int paramInt)
  {
    this();
    a(paramBitmap, paramInt);
  }

  private void b()
  {
    setOneShot(false);
    setVisible(true, false);
  }

  private void c()
  {
    if ((this.a <= 0) || (this.b <= 0))
    {
      Drawable localDrawable = getFrame(0);
      if (localDrawable != null)
      {
        this.a = localDrawable.getIntrinsicWidth();
        this.b = localDrawable.getIntrinsicHeight();
      }
    }
  }

  public int a()
  {
    return this.c;
  }

  public void a(Bitmap paramBitmap, int paramInt)
  {
    if ((paramBitmap == null) || (paramBitmap.isRecycled()))
      return;
    addFrame(new BitmapDrawable(paramBitmap), paramInt);
    c();
    this.c = (paramInt + this.c);
  }

  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    start();
  }

  public int getIntrinsicHeight()
  {
    return this.b;
  }

  public int getIntrinsicWidth()
  {
    return this.a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.GifDrawable
 * JD-Core Version:    0.6.0
 */