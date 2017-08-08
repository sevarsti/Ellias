package com.tencent.component.utils.log;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.component.ComponentContext;
import com.tencent.component.debug.FileTracerConfig;
import com.tencent.component.utils.ProcessUtils;
import com.tencent.component.utils.thread.ThreadPool.Job;
import com.tencent.component.utils.thread.ThreadPool.JobContext;
import java.io.File;

class c
  implements ThreadPool.Job
{
  c(b paramb)
  {
  }

  public Object run(ThreadPool.JobContext paramJobContext)
  {
    File localFile = AppTracer.a();
    int i;
    long l;
    if (localFile != null)
    {
      i = LogConfig.a().b();
      l = LogConfig.a().c();
      Context localContext = ComponentContext.a();
      if (localContext == null)
        break label158;
      str = ProcessUtils.a(localContext);
      if (str == null);
    }
    label158: for (String str = str.toLowerCase().replace(':', '.'); ; str = null)
    {
      if (TextUtils.isEmpty(str))
        str = "main";
      FileTracerConfig localFileTracerConfig = new FileTracerConfig(localFile, i, 262144, 8192, "file.tracer." + str, 10000L, 10, "." + str + ".log", l);
      b.a(this.a, new AppTracer(localFileTracerConfig));
      return null;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.log.c
 * JD-Core Version:    0.6.0
 */