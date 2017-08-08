package com.tencent.component.plugin.server;

import com.tencent.component.plugin.PluginPlatformConfig;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.ThreadPool.Job;
import com.tencent.component.utils.thread.ThreadPool.JobContext;

class l
  implements ThreadPool.Job
{
  l(k paramk, PluginPlatformConfig paramPluginPlatformConfig)
  {
  }

  public Void a(ThreadPool.JobContext paramJobContext)
  {
    String str = this.a.a();
    LogUtil.i("PlguinService", "receive hello from " + str);
    c localc = c.a(k.a(this.b), str);
    localc.a(this.a);
    localc.f().a();
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.l
 * JD-Core Version:    0.6.0
 */