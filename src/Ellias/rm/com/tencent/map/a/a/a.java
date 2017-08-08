package com.tencent.map.a.a;

import android.content.Context;
import com.tencent.map.b.n;

public class a
{
  private static n a = n.a();
  private static a b;

  public static a a()
  {
    monitorenter;
    try
    {
      if (b == null)
        b = new a();
      a locala = b;
      return locala;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean a(Context paramContext, b paramb)
  {
    return a.a(paramContext, paramb);
  }

  public boolean a(String paramString1, String paramString2)
  {
    return a.a(paramString1, paramString2);
  }

  public void b()
  {
    a.b();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.map.a.a.a
 * JD-Core Version:    0.6.0
 */