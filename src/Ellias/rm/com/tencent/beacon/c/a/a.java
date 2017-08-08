package com.tencent.beacon.c.a;

import com.tencent.beacon.e.b;
import com.tencent.beacon.e.c;
import java.util.HashMap;
import java.util.Map;

public final class a extends c
  implements Cloneable
{
  private static Map<Integer, byte[]> b;
  public Map<Integer, byte[]> a = null;

  public final void a(com.tencent.beacon.e.a parama)
  {
    if (b == null)
    {
      b = new HashMap();
      Integer localInteger = Integer.valueOf(0);
      byte[] arrayOfByte = { 0 };
      b.put(localInteger, arrayOfByte);
    }
    this.a = ((Map)parama.a(b, 0, true));
  }

  public final void a(b paramb)
  {
    paramb.a(this.a, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.c.a.a
 * JD-Core Version:    0.6.0
 */