package com.tencent.component.image.image;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.tencent.component.cache.image.ImageEntry;
import com.tencent.component.ui.widget.drawable.GifImageDrawable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GifImage extends Image
{
  private ArrayList a = new ArrayList();
  private int b = 0;
  private boolean c = false;

  private void b(GifImage.Frame paramFrame)
  {
    if (paramFrame == null);
    for (Bitmap localBitmap = null; ; localBitmap = paramFrame.a)
    {
      if ((localBitmap != null) && (!localBitmap.isRecycled()))
        localBitmap.recycle();
      return;
    }
  }

  private int c(GifImage.Frame paramFrame)
  {
    if (paramFrame == null);
    for (Bitmap localBitmap = null; (localBitmap == null) || (localBitmap.isRecycled()); localBitmap = paramFrame.a)
      return 0;
    return localBitmap.getRowBytes() * localBitmap.getHeight();
  }

  public int a()
  {
    Iterator localIterator = this.a.iterator();
    int i = 0;
    while (localIterator.hasNext())
      i += c((GifImage.Frame)localIterator.next());
    return i;
  }

  public Drawable a(ImageEntry paramImageEntry)
  {
    GifImageDrawable localGifImageDrawable = new GifImageDrawable(this);
    if (localGifImageDrawable.getNumberOfFrames() <= 0)
      localGifImageDrawable = null;
    return localGifImageDrawable;
  }

  public void a(GifImage.Frame paramFrame)
  {
    if (paramFrame == null)
      return;
    this.a.add(paramFrame);
    this.b = (1 + this.b);
  }

  public void b()
  {
    if (!this.c)
    {
      Iterator localIterator = this.a.iterator();
      while (localIterator.hasNext())
        b((GifImage.Frame)localIterator.next());
      this.a.clear();
      this.c = true;
    }
  }

  public boolean c()
  {
    return this.c;
  }

  public boolean d()
  {
    return false;
  }

  public int e()
  {
    return this.b;
  }

  public List f()
  {
    return this.a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.image.GifImage
 * JD-Core Version:    0.6.0
 */