package com.tencent.component.plugin;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.ThreadPool.Job;
import com.tencent.component.utils.thread.ThreadPool.JobContext;

public abstract class PluginManager$Code
  implements ThreadPool.Job
{
  protected PluginManager$Code(PluginManager paramPluginManager)
  {
  }

  public abstract void a();

  public Object run(ThreadPool.JobContext paramJobContext)
  {
    try
    {
      a();
      return null;
    }
    catch (DeadObjectException localDeadObjectException)
    {
      while (true)
      {
        LogUtil.e("PluginManager", "occure DeadObjectException,try to stopService ", localDeadObjectException);
        PluginManager.k(this.d);
      }
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        LogUtil.e("PluginManager", "Remote Code Exception : ", localRemoteException);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginManager.Code
 * JD-Core Version:    0.6.0
 */