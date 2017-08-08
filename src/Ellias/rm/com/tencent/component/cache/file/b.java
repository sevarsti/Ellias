package com.tencent.component.cache.file;

import com.tencent.component.utils.thread.ThreadPool.Job;
import com.tencent.component.utils.thread.ThreadPool.JobContext;

class b
  implements ThreadPool.Job
{
  b(FileCacheService paramFileCacheService)
  {
  }

  public Object run(ThreadPool.JobContext paramJobContext)
  {
    FileCacheService.a(this.a, false);
    FileCacheService.a(this.a, true);
    FileCacheService.b(this.a, true);
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.file.b
 * JD-Core Version:    0.6.0
 */