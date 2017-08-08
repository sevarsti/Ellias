package com.tencent.component.service;

import android.os.RemoteException;

class l
  implements Runnable
{
  l(i parami, String paramString, ILeafServiceConnection paramILeafServiceConnection)
  {
  }

  public void run()
  {
    try
    {
      i.b(this.c).b(this.a, this.b);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.service.l
 * JD-Core Version:    0.6.0
 */