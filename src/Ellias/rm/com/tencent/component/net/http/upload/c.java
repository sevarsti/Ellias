package com.tencent.component.net.http.upload;

import com.tencent.component.utils.thread.ThreadPool.Job;
import com.tencent.component.utils.thread.ThreadPool.JobContext;

class c
  implements ThreadPool.Job
{
  c(UploadManager paramUploadManager)
  {
  }

  public Object run(ThreadPool.JobContext paramJobContext)
  {
    UploadManager.a(this.a, UploadManager.b(this.a).a());
    UploadManager.a(this.a, true);
    UploadManager.c(this.a);
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.upload.c
 * JD-Core Version:    0.6.0
 */