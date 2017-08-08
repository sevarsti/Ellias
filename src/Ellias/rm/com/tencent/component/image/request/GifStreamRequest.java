package com.tencent.component.image.request;

import android.graphics.Bitmap.Config;
import com.tencent.component.cache.image.ImageEntry;
import com.tencent.component.cache.image.ImageResult;
import com.tencent.component.image.image.GifStreamImage;
import com.tencent.component.image.image.Image;
import com.tencent.component.utils.thread.ThreadPool.JobContext;
import java.io.File;

public final class GifStreamRequest extends ImageRequest
{
  private static final int a = 1048576;
  private static final long b = 10485760L;

  public GifStreamRequest(ImageEntry paramImageEntry, ImageRequest.Callback paramCallback, Bitmap.Config paramConfig)
  {
    super(paramImageEntry, paramCallback, paramConfig);
  }

  private ImageResult b(ThreadPool.JobContext paramJobContext)
  {
    return new BitmapRequest(a(), b(), c()).a(paramJobContext);
  }

  public ImageResult a(ThreadPool.JobContext paramJobContext)
  {
    ImageResult localImageResult = new ImageResult();
    if (paramJobContext.isCancelled())
      return localImageResult;
    ImageEntry localImageEntry = a();
    Image localImage = b().a(localImageEntry);
    if ((localImage != null) && (!localImage.c()))
    {
      localImageResult.a(localImage);
      return localImageResult;
    }
    if (localImageEntry.c)
      return b(paramJobContext);
    if ((10485760L > 0L) && (new File(localImageEntry.a).length() > 10485760L))
      return b(paramJobContext);
    paramJobContext.setMode(1);
    try
    {
      localGifStreamImage = new GifStreamImage(localImageEntry.a, localImageEntry.b);
      if (localGifStreamImage == null)
        return b(paramJobContext);
    }
    catch (Throwable localThrowable)
    {
      GifStreamImage localGifStreamImage;
      while (true)
      {
        localImageResult.a(localThrowable);
        localGifStreamImage = null;
      }
      localImageResult.a(localGifStreamImage);
    }
    return localImageResult;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.request.GifStreamRequest
 * JD-Core Version:    0.6.0
 */