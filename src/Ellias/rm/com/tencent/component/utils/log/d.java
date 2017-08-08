package com.tencent.component.utils.log;

import com.tencent.component.app.BaseActivity;
import com.tencent.component.utils.collections.EqualWeakReference;
import java.util.List;

class d
  implements Runnable
{
  d(MemLeakMonitor paramMemLeakMonitor)
  {
  }

  public void run()
  {
    System.gc();
    System.gc();
    int i = 0;
    if (i < MemLeakMonitor.a(this.a).size())
    {
      EqualWeakReference localEqualWeakReference = (EqualWeakReference)MemLeakMonitor.a(this.a).get(i);
      if (localEqualWeakReference != null)
      {
        BaseActivity localBaseActivity = (BaseActivity)localEqualWeakReference.get();
        if (localBaseActivity != null)
          LogUtil.w("MemLeak", localBaseActivity.getClass().getSimpleName() + " has leaked");
      }
      while (true)
      {
        i++;
        break;
        MemLeakMonitor.a(this.a).remove(i);
        i--;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.log.d
 * JD-Core Version:    0.6.0
 */