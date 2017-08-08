package com.tencent.component.cache.image;

import android.os.Process;
import com.tencent.component.utils.log.LogUtil;
import java.lang.ref.ReferenceQueue;
import java.util.List;

class m extends Thread
{
  m(k paramk)
  {
    super("ImageTracker");
    setDaemon(true);
  }

  public void run()
  {
    Process.setThreadPriority(19);
    while (true)
      if ((!k.a(this.a)) || (!k.b(this.a).isEmpty()))
        try
        {
          localn = (n)k.c(this.a).remove();
          if (localn == null)
            continue;
          localn.clear();
          localn.a();
          k.b(this.a).remove(localn);
        }
        catch (Throwable localThrowable)
        {
          while (true)
          {
            LogUtil.d("ImageTracker", "fail to poll tracker", localThrowable);
            n localn = null;
          }
        }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.m
 * JD-Core Version:    0.6.0
 */