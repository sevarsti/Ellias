package com.tencent.component.image;

import com.tencent.component.utils.thread.ThreadPool.Job;
import com.tencent.component.utils.thread.ThreadPool.JobContext;

class a
  implements ThreadPool.Job
{
  a(ImageLoader paramImageLoader, String paramString, ImageLoader.Options paramOptions, ImageLoader.ImageLoadListener paramImageLoadListener)
  {
  }

  public Object run(ThreadPool.JobContext paramJobContext)
  {
    String str = ImageLoader.a(this.d, this.a, this.b);
    ImageLoader.a(this.d, ImageLoader.a(this.d, this.a, str, this.c, this.b));
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.a
 * JD-Core Version:    0.6.0
 */