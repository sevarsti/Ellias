package com.tencent.component.ui.widget.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;

class o extends m
{
  private final Shader a;
  private final Bitmap b;
  private final int c;
  private final int d;
  private final Matrix e = new Matrix();

  public o(ImageDrawable paramImageDrawable)
  {
    Bitmap localBitmap = paramImageDrawable.h();
    BitmapShader localBitmapShader;
    int j;
    if (localBitmap == null)
    {
      localBitmapShader = null;
      this.a = localBitmapShader;
      this.b = localBitmap;
      if (localBitmap != null)
        break label82;
      j = i;
      label47: this.c = j;
      if (localBitmap != null)
        break label91;
    }
    while (true)
    {
      this.d = i;
      return;
      localBitmapShader = new BitmapShader(localBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
      break;
      label82: j = localBitmap.getWidth();
      break label47;
      label91: i = localBitmap.getHeight();
    }
  }

  public void a(Paint paramPaint)
  {
    paramPaint.setShader(this.a);
  }

  public void a(Rect paramRect)
  {
    float f1 = 1.0F;
    if (this.a == null)
      return;
    int i = paramRect.width();
    int j = paramRect.height();
    float f2;
    if (this.c <= 0)
    {
      f2 = f1;
      if (this.d > 0)
        break label80;
    }
    while (true)
    {
      this.e.reset();
      this.e.setScale(f2, f1);
      this.a.setLocalMatrix(this.e);
      return;
      f2 = i / this.c;
      break;
      label80: f1 = j / this.d;
    }
  }

  public boolean a()
  {
    return (this.b != null) && (!this.b.isRecycled());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.o
 * JD-Core Version:    0.6.0
 */