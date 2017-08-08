package com.tencent.beacon.c.e;

import com.tencent.beacon.e.a;
import java.util.ArrayList;

public final class e extends com.tencent.beacon.e.c
{
  private static ArrayList<c> d;
  private static ArrayList<b> e;
  private static ArrayList<f> f;
  public ArrayList<c> a = null;
  public ArrayList<b> b = null;
  public ArrayList<f> c = null;

  public final void a(a parama)
  {
    if (d == null)
    {
      d = new ArrayList();
      c localc = new c();
      d.add(localc);
    }
    this.a = ((ArrayList)parama.a(d, 0, true));
    if (e == null)
    {
      e = new ArrayList();
      b localb = new b();
      e.add(localb);
    }
    this.b = ((ArrayList)parama.a(e, 1, true));
    if (f == null)
    {
      f = new ArrayList();
      f localf = new f();
      f.add(localf);
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
 * Qualified Name:     com.tencent.beacon.c.e.e
 * JD-Core Version:    0.6.0
 */