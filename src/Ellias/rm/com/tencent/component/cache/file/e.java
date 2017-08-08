package com.tencent.component.cache.file;

import android.content.Context;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.ThreadPool.Job;
import com.tencent.component.utils.thread.ThreadPool.JobContext;
import java.util.Collection;
import java.util.Iterator;

class e
  implements ThreadPool.Job
{
  e(FileStorageHandler paramFileStorageHandler, boolean paramBoolean, Context paramContext)
  {
  }

  public Object run(ThreadPool.JobContext paramJobContext)
  {
    paramJobContext.setMode(1);
    Collection localCollection = FileStorageHandler.a(this.c).a();
    int i;
    int j;
    float f;
    if (localCollection != null)
    {
      Iterator localIterator = localCollection.iterator();
      i = 0;
      j = 0;
      while (localIterator.hasNext())
      {
        FileCacheService localFileCacheService = (FileCacheService)localIterator.next();
        int k = localFileCacheService.c(this.a);
        int m = localFileCacheService.b(this.a);
        int n = FileStorageHandler.a(this.c, k, m);
        localFileCacheService.a(this.a, n);
        LogUtil.i("FileStorageHandler", "clear cache service:" + localFileCacheService + ": remain=" + n);
        j += m;
        i += k;
      }
      if (i > 0)
        break label189;
      f = 3.4028235E+38F;
    }
    while (true)
    {
      if (f < 0.1F)
        FileStorageHandler.a(this.c, this.b);
      return null;
      label189: f = j / i;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.file.e
 * JD-Core Version:    0.6.0
 */