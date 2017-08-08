package com.tencent.component.image.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import com.tencent.component.cache.image.ImageEntry;
import com.tencent.component.ui.widget.drawable.GifStreamImageDrawable;
import com.tencent.component.utils.DecodeUtils;
import java.util.ArrayList;

public class GifStreamImage extends Image
{
  private static final int a = 4;
  private final GifStreamImage.AsyncGifDecoder b;
  private final int c;
  private final int d;
  private final int e;
  private final ArrayList f = new ArrayList();

  public GifStreamImage(String paramString)
  {
    this(paramString, 1.0F);
  }

  public GifStreamImage(String paramString, float paramFloat)
  {
    this.b = new GifStreamImage.AsyncGifDecoder(paramString, paramFloat, new a(this));
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    try
    {
      DecodeUtils.b(paramString, localOptions);
      label49: this.c = a(localOptions.outWidth, paramFloat);
      this.d = a(localOptions.outHeight, paramFloat);
      this.e = a(localOptions.outWidth, localOptions.outHeight, paramFloat);
      return;
    }
    catch (Throwable localThrowable)
    {
      break label49;
    }
  }

  private static int a(int paramInt, float paramFloat)
  {
    if (paramFloat < 1.0F)
      paramFloat = 1.0F;
    return (int)(0.5F + paramInt / paramFloat);
  }

  private static int a(int paramInt1, int paramInt2, float paramFloat)
  {
    if (paramFloat < 1.0F)
      paramFloat = 1.0F;
    int i;
    if (paramInt1 > 0)
    {
      i = (int)(0.5F + paramInt1 / paramFloat);
      if (paramInt2 <= 0)
        break label66;
    }
    label66: for (int j = (int)(0.5F + paramInt2 / paramFloat); ; j = 0)
    {
      int k = 0;
      if (i > 0)
      {
        k = 0;
        if (j > 0)
          k = 4 * (i * j);
      }
      return k;
      i = 0;
      break;
    }
  }

  private void a(Bitmap paramBitmap)
  {
    while (true)
    {
      GifStreamImage.Callback localCallback;
      synchronized (this.f)
      {
        int i = this.f.size();
        if (i <= 0)
          break label99;
        GifStreamImage.Callback[] arrayOfCallback1 = new GifStreamImage.Callback[i];
        arrayOfCallback2 = (GifStreamImage.Callback[])this.f.toArray(arrayOfCallback1);
        if (arrayOfCallback2 == null)
          break label98;
        int j = arrayOfCallback2.length;
        int k = 0;
        if (k >= j)
          break label98;
        localCallback = arrayOfCallback2[k];
        if (localCallback == null)
          k++;
      }
      localCallback.a(paramBitmap);
      continue;
      label98: return;
      label99: GifStreamImage.Callback[] arrayOfCallback2 = null;
    }
  }

  public int a()
  {
    return this.e;
  }

  public Drawable a(ImageEntry paramImageEntry)
  {
    return new GifStreamImageDrawable(this);
  }

  public void a(GifStreamImage.Callback paramCallback)
  {
    if (paramCallback == null)
      return;
    synchronized (this.f)
    {
      if (this.f.contains(paramCallback))
        return;
    }
    boolean bool = this.f.isEmpty();
    this.f.add(paramCallback);
    if (bool)
      this.b.a();
    monitorexit;
  }

  public void b()
  {
    this.b.b();
  }

  public void b(GifStreamImage.Callback paramCallback)
  {
    if (paramCallback == null)
      return;
    synchronized (this.f)
    {
      if (!this.f.contains(paramCallback))
        return;
    }
    boolean bool = this.f.isEmpty();
    this.f.remove(paramCallback);
    if ((!bool) && (this.f.isEmpty()))
      this.b.b();
    monitorexit;
  }

  public boolean c()
  {
    return !this.b.c();
  }

  public boolean d()
  {
    return false;
  }

  public int e()
  {
    return this.c;
  }

  public int f()
  {
    return this.d;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.image.GifStreamImage
 * JD-Core Version:    0.6.0
 */