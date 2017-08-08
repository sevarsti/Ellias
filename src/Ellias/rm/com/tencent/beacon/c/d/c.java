package com.tencent.beacon.c.d;

import java.util.ArrayList;

public final class c extends com.tencent.beacon.e.c
  implements Cloneable
{
  private static ArrayList<b> d;
  private static ArrayList<a> e;
  private static ArrayList<d> f;
  public ArrayList<b> a = null;
  public ArrayList<a> b = null;
  public ArrayList<d> c = null;

  public final void a(com.tencent.beacon.e.a parama)
  {
    if (d == null)
    {
      d = new ArrayList();
      b localb = new b();
      d.add(localb);
    }
    this.a = ((ArrayList)parama.a(d, 0, true));
    if (e == null)
    {
      e = new ArrayList();
      a locala = new a();
      e.add(locala);
    }
    this.b = ((ArrayList)parama.a(e, 1, true));
    if (f == null)
    {
      f = new ArrayList();
      d locald = new d();
      f.add(locald);
    }
    this.c = ((ArrayList)parama.a(f, 2, false));
  }

  public final void a(com.tencent.beacon.e.b paramb)
  {
    paramb.a(this.a, 0);
    paramb.a(this.b, 1);
    if (this.c != null)
      paramb.a(this.c, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.c.d.c
 * JD-Core Version:    0.6.0
 */