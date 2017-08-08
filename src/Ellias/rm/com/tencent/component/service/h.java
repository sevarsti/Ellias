package com.tencent.component.service;

import android.os.IBinder;

final class h
  implements Runnable
{
  final String a;
  final IBinder b;
  final int c;

  h(d paramd, String paramString, IBinder paramIBinder, int paramInt)
  {
    this.a = paramString;
    this.b = paramIBinder;
    this.c = paramInt;
  }

  public void run()
  {
    if (this.c == 0)
      this.d.b(this.a, this.b);
    do
      return;
    while (this.c != 1);
    this.d.d(this.a, this.b);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.service.h
 * JD-Core Version:    0.6.0
 */