package com.tencent.component.ui.widget.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;

class n extends m
{
  private final Shader a;
  private final Shader.TileMode b;
  private final Shader.TileMode c;
  private final boolean d;
  private final int e;
  private final int f;
  private final int g;
  private Matrix h;

  public n(BitmapDrawable paramBitmapDrawable)
  {
    Bitmap localBitmap = paramBitmapDrawable.getBitmap();
    Shader.TileMode localTileMode1 = paramBitmapDrawable.getTileModeX();
    Shader.TileMode localTileMode2 = paramBitmapDrawable.getTileModeY();
    Shader.TileMode localTileMode3;
    Shader.TileMode localTileMode4;
    label49: boolean bool;
    label68: BitmapShader localBitmapShader;
    label81: int j;
    if (localTileMode1 == null)
    {
      localTileMode3 = Shader.TileMode.CLAMP;
      this.b = localTileMode3;
      if (localTileMode2 != null)
        break label125;
      localTileMode4 = Shader.TileMode.CLAMP;
      this.c = localTileMode4;
      if ((localTileMode1 != null) || (localTileMode2 != null))
        break label132;
      bool = true;
      this.d = bool;
      if (localBitmap != null)
        break label138;
      localBitmapShader = null;
      this.a = localBitmapShader;
      this.e = paramBitmapDrawable.getGravity();
      if (localBitmap != null)
        break label159;
      j = i;
      label102: this.f = j;
      if (localBitmap != null)
        break label168;
    }
    while (true)
    {
      this.g = i;
      return;
      localTileMode3 = localTileMode1;
      break;
      label125: localTileMode4 = localTileMode2;
      break label49;
      label132: bool = false;
      break label68;
      label138: localBitmapShader = new BitmapShader(localBitmap, this.b, this.c);
      break label81;
      label159: j = localBitmap.getWidth();
      break label102;
      label168: i = localBitmap.getHeight();
    }
  }

  public void a(Paint paramPaint)
  {
    paramPaint.setShader(this.a);
  }

  public void a(Rect paramRect)
  {
    float f1 = 1.0F;
    if (this.a == null);
    do
      return;
    while ((this.e != 119) || (!this.d));
    int i = paramRect.width();
    int j = paramRect.height();
    float f2;
    if (this.f <= 0)
    {
      f2 = f1;
      if (this.g > 0)
        break label114;
    }
    while (true)
    {
      if (this.h == null)
        this.h = new Matrix();
      this.h.reset();
      this.h.setScale(f2, f1);
      this.a.setLocalMatrix(this.h);
      return;
      f2 = i / this.f;
      break;
      label114: f1 = j / this.g;
    }
  }

  public boolean a()
  {
    return this.a != null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.n
 * JD-Core Version:    0.6.0
 */