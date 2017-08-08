package com.tencent.component.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

class j
  implements ServiceConnection
{
  j(i parami)
  {
  }

  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    i.a(this.a, ILeafServiceManager.Stub.a(paramIBinder));
    synchronized (i.a(this.a))
    {
      i.a(this.a).notifyAll();
      return;
    }
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.service.j
 * JD-Core Version:    0.6.0
 */