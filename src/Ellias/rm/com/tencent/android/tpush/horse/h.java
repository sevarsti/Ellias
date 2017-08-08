package com.tencent.android.tpush.horse;

import java.util.concurrent.LinkedBlockingQueue;

public class h extends a
{
  private static h a;

  public static h j()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new h();
      h localh = a;
      return localh;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void f()
  {
    j().e().clear();
  }

  public void g()
  {
    j().a(-1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.h
 * JD-Core Version:    0.6.0
 */