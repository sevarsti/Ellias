package com.tencent.component.service;

import android.os.IBinder;
import java.lang.ref.WeakReference;

class g extends ILeafServiceConnection.Stub
{
  private final WeakReference b;

  g(d paramd)
  {
    this.b = new WeakReference(paramd);
  }

  public void a(String paramString, IBinder paramIBinder)
  {
    d locald = (d)this.b.get();
    if (locald != null)
      locald.a(paramString, paramIBinder);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.service.g
 * JD-Core Version:    0.6.0
 */