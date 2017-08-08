package com.tencent.component.service;

import android.os.IBinder;
import android.os.IInterface;

public abstract interface ILeafServiceManager extends IInterface
{
  public abstract IBinder a(String paramString);

  public abstract void a(String paramString, ILeafServiceConnection paramILeafServiceConnection);

  public abstract boolean a(String paramString, IBinder paramIBinder);

  public abstract IBinder b(String paramString);

  public abstract void b(String paramString, ILeafServiceConnection paramILeafServiceConnection);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.service.ILeafServiceManager
 * JD-Core Version:    0.6.0
 */