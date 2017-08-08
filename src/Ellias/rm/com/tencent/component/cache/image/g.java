package com.tencent.component.cache.image;

import com.tencent.component.image.image.Image;
import com.tencent.component.utils.thread.Future;
import com.tencent.component.utils.thread.FutureListener;
import java.util.Collection;
import java.util.HashMap;

class g
  implements FutureListener
{
  g(f paramf, ImageEntry paramImageEntry)
  {
  }

  public void onFutureBegin(Future paramFuture)
  {
  }

  public void onFutureDone(Future paramFuture)
  {
    Image localImage;
    Collection localCollection;
    label171: 
    do
    {
      while (true)
      {
        ImageResult localImageResult;
        synchronized (ImageCacheService.c(this.b.e))
        {
          ImageCacheService.c(this.b.e).remove(this.a);
          localImageResult = (ImageResult)paramFuture.get();
          if (localImageResult == null)
          {
            localImage = null;
            localCollection = ImageCacheService.a(this.b.e, this.a, null);
            if (localImageResult == null)
              continue;
            ImageCacheService.a(this.b.e, localImageResult.b());
            if (ImageCacheService.a(localImage))
              break;
            if (paramFuture.isCancelled())
              continue;
            ImageCacheService localImageCacheService = this.b.e;
            ImageEntry localImageEntry = this.a;
            localThrowable = null;
            if (localImageResult != null)
              break label171;
            ImageCacheService.a(localImageCacheService, localImageEntry, localCollection, localThrowable);
            return;
          }
        }
        localImage = ImageCacheService.a(this.b.e, localImageResult.a(), this.a);
        continue;
        Throwable localThrowable = localImageResult.b();
      }
      ImageCacheService.a(this.b.e).a(this.a, localImage);
      ImageCacheService.a(this.b.e, localImage);
    }
    while (paramFuture.isCancelled());
    ImageCacheService.a(this.b.e, this.a, localCollection, ImageCacheService.a(this.a, localImage));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.g
 * JD-Core Version:    0.6.0
 */