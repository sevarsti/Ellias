package com.tencent.component.service;

import android.os.IBinder;
import android.os.IBinder.DeathRecipient;

final class f
  implements IBinder.DeathRecipient
{
  final String a;
  final IBinder b;

  f(d paramd, String paramString, IBinder paramIBinder)
  {
    this.a = paramString;
    this.b = paramIBinder;
  }

  public void binderDied()
  {
    this.c.c(this.a, this.b);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.service.f
 * JD-Core Version:    0.6.0
 */