package com.tencent.beacon.e;

import java.util.HashMap;
import java.util.Map;

public final class e extends c
{
  private static byte[] k = null;
  private static Map<String, String> l = null;
  public short a = 3;
  public int b = 0;
  public String c = null;
  public String d = null;
  public byte[] e;
  private byte f = 0;
  private int g = 0;
  private int h = 0;
  private Map<String, String> i;
  private Map<String, String> j;

  public final void a(a parama)
  {
    try
    {
      this.a = parama.a(this.a, 1, true);
      this.f = parama.a(this.f, 2, true);
      this.g = parama.a(this.g, 3, true);
      this.b = parama.a(this.b, 4, true);
      this.c = parama.b(5, true);
      this.d = parama.b(6, true);
      if (k == null)
        k = new byte[1];
      this.e = parama.c(7, true);
      this.h = parama.a(this.h, 8, true);
      if (l == null)
      {
        HashMap localHashMap1 = new HashMap();
        l = localHashMap1;
        localHashMap1.put("", "");
      }
      this.i = ((Map)parama.a(l, 9, true));
      if (l == null)
      {
        HashMap localHashMap2 = new HashMap();
        l = localHashMap2;
        localHashMap2.put("", "");
      }
      this.j = ((Map)parama.a(l, 10, true));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    throw new RuntimeException(localException);
  }

  public final void a(b paramb)
  {
    paramb.a(this.a, 1);
    paramb.a(this.f, 2);
    paramb.a(this.g, 3);
    paramb.a(this.b, 4);
    paramb.a(this.c, 5);
    paramb.a(this.d, 6);
    paramb.a(this.e, 7);
    paramb.a(this.h, 8);
    paramb.a(this.i, 9);
    paramb.a(this.j, 10);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.e.e
 * JD-Core Version:    0.6.0
 */