package com.tencent.android.tpush.horse;

import java.util.concurrent.LinkedBlockingQueue;

public class t extends a
{
  private static t a;

  public static t j()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new t();
      t localt = a;
      return localt;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void f()
  {
    h.j().e().clear();
  }

  public void g()
  {
    h.j().a(-1);
    h.j().b();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.t
 * JD-Core Version:    0.6.0
 */