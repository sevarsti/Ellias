package com.tencent.component.image.image;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.tencent.component.cache.image.ImageEntry;
import com.tencent.component.ui.widget.drawable.BitmapImageDrawable;
import com.tencent.component.utils.AssertUtil;

public class BitmapImage extends Image
{
  private final Bitmap a;
  private final Image.MetaInfo b = new Image.MetaInfo();

  public BitmapImage(Bitmap paramBitmap)
  {
    if (paramBitmap != null);
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      this.a = paramBitmap;
      g();
      return;
    }
  }

  private void g()
  {
    this.b.a = this.a.getWidth();
    this.b.b = this.a.getHeight();
  }

  public int a()
  {
    if (this.a.isRecycled())
      return 0;
    return this.a.getRowBytes() * this.a.getHeight();
  }

  public Drawable a(ImageEntry paramImageEntry)
  {
    int i = paramImageEntry.f;
    int j = paramImageEntry.g;
    if (paramImageEntry.b <= 1)
    {
      j = -1;
      i = j;
    }
    return new BitmapImageDrawable(this, i, j);
  }

  public void b()
  {
    if (!this.a.isRecycled())
      this.a.recycle();
  }

  public boolean c()
  {
    return this.a.isRecycled();
  }

  public boolean d()
  {
    return true;
  }

  public Bitmap e()
  {
    return this.a;
  }

  public Image.MetaInfo f()
  {
    return this.b;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.image.BitmapImage
 * JD-Core Version:    0.6.0
 */