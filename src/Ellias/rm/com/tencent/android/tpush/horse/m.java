package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.service.c.a;
import java.util.TimerTask;

class m extends TimerTask
{
  m(i parami)
  {
  }

  public void run()
  {
    if (a.g(com.tencent.android.tpush.service.i.e()));
    synchronized (i.g(this.a))
    {
      i.a = 1L;
      if (i.h(this.a) != null)
      {
        i.h(this.a).a();
        return;
      }
    }
    com.tencent.android.tpush.service.i.a(com.tencent.android.tpush.service.i.e(), null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.m
 * JD-Core Version:    0.6.0
 */