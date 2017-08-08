package com.tencent.stat;

import android.content.Context;
import android.os.Handler;
import com.tencent.stat.a.d;
import com.tencent.stat.common.StatLogger;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashSet;

class i
  implements Runnable
{
  private Context a = null;

  public i(Context paramContext)
  {
    this.a = paramContext;
  }

  public void run()
  {
    Iterator localIterator = StatNativeCrashReport.a(this.a).iterator();
    while (localIterator.hasNext())
    {
      File localFile = (File)localIterator.next();
      String str = StatNativeCrashReport.a(localFile);
      d locald = new d(this.a, StatService.a(this.a, false), str, 3, 10240);
      locald.a(StatNativeCrashReport.b(localFile));
      if (StatService.c(this.a) != null)
        StatService.c(this.a).post(new k(locald));
      localFile.delete();
      StatService.b().d("delete tombstone file:" + localFile.getAbsolutePath().toString());
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.i
 * JD-Core Version:    0.6.0
 */