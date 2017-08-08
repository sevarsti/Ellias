package com.tencent.map.b;

final class o
  implements Runnable
{
  o(n paramn)
  {
  }

  public final void run()
  {
    if (System.currentTimeMillis() - n.a(this.a) < 8000L)
      return;
    if ((n.b(this.a).b()) && (n.b(this.a).c()))
    {
      n.b(this.a).a(0L);
      return;
    }
    n.c(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.map.b.o
 * JD-Core Version:    0.6.0
 */