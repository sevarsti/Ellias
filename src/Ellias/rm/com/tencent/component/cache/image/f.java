package com.tencent.component.cache.image;

import com.tencent.component.cache.common.LruCache;
import com.tencent.component.image.image.Image;
import com.tencent.component.image.request.ImageRequest;
import com.tencent.component.utils.thread.Future;
import com.tencent.component.utils.thread.ThreadPool;
import com.tencent.component.utils.thread.ThreadPool.Job;
import com.tencent.component.utils.thread.ThreadPool.JobContext;
import com.tencent.component.utils.thread.ThreadPool.Priority;
import java.io.FileNotFoundException;
import java.util.HashMap;

class f
  implements ThreadPool.Job
{
  f(ImageCacheService paramImageCacheService, j paramj, ImageCacheService.Options paramOptions, ImageCacheService.ImageCacheListener paramImageCacheListener, boolean paramBoolean)
  {
  }

  public Object run(ThreadPool.JobContext paramJobContext)
  {
    paramJobContext.setMode(0);
    ImageEntry localImageEntry1 = (ImageEntry)ImageCacheService.b(this.e).b(this.a);
    ImageEntry localImageEntry2;
    if (localImageEntry1 != null)
    {
      localImageEntry2 = localImageEntry1;
      if (ImageCacheService.b(this.a.a))
        break label135;
      FileNotFoundException localFileNotFoundException = new FileNotFoundException("file " + this.a.a + " doesn't exist or is not a file!");
      ImageCacheService.a(this.e, localFileNotFoundException);
      ImageCacheService.a(this.e, localImageEntry2, this.c, localFileNotFoundException);
    }
    label135: 
    do
    {
      return null;
      localImageEntry2 = ImageCacheService.a(this.e, this.a.a, this.b);
      break;
      if (localImageEntry1 == null)
        ImageCacheService.b(this.e).b(this.a, localImageEntry2);
      Image localImage = ImageCacheService.a(this.e).a(localImageEntry2);
      if (!ImageCacheService.a(localImage))
        continue;
      ImageCacheService.a(this.e, localImageEntry2, this.c, ImageCacheService.a(localImageEntry2, localImage));
      return null;
    }
    while (!ImageCacheService.a(this.e, localImageEntry2, this.c));
    ThreadPool localThreadPool = ImageCacheService.d(this.e);
    ImageRequest localImageRequest = ImageCacheService.a(this.e, localImageEntry2);
    g localg = new g(this, localImageEntry2);
    if (this.d);
    for (ThreadPool.Priority localPriority = ThreadPool.Priority.HIGH; ; localPriority = ThreadPool.Priority.NORMAL)
    {
      Future localFuture = localThreadPool.submit(localImageRequest, localg, localPriority);
      synchronized (ImageCacheService.c(this.e))
      {
        ImageCacheService.c(this.e).put(localImageEntry2, localFuture);
        return null;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.f
 * JD-Core Version:    0.6.0
 */