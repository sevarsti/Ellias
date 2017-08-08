package com.tencent.component.cache;

import android.content.Context;
import android.os.Environment;
import com.tencent.component.utils.FileUtil;
import com.tencent.component.utils.thread.ThreadPool.Job;
import com.tencent.component.utils.thread.ThreadPool.JobContext;
import java.io.File;

final class b
  implements ThreadPool.Job
{
  b(Context paramContext)
  {
  }

  public Object run(ThreadPool.JobContext paramJobContext)
  {
    paramJobContext.setMode(1);
    CacheManager.h(this.a);
    FileUtil.a(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Tencent/Qzone"), true);
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.b
 * JD-Core Version:    0.6.0
 */