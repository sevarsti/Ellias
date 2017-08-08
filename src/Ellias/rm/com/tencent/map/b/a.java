package com.tencent.map.b;

public final class a
{
  private static a a = null;

  public static a a()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new a();
      a locala = a;
      return locala;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final boolean a(String paramString1, String paramString2)
  {
    if (!s.a(paramString1));
    while (true)
    {
      return false;
      if ((!s.b(paramString2)) || (!new Object(0)
      {
        public boolean a(String, String)
        {
        }
      }
      .a(paramString1, paramString2)))
        continue;
      int i = 0;
      int j = 0;
      while (i < 27)
      {
        j = 0xFF & j >> 8 ^ s.b[(0xFF & (j ^ s.a(paramString2.charAt(i))))];
        i++;
      }
      int k = j & 0x1F;
      int n;
      if (paramString2.charAt(27) != s.a.charAt(k))
        n = 0;
      while (n != 0)
      {
        return true;
        int m = 0x1F & j >> 5;
        if (paramString2.charAt(28) != s.a.charAt(m))
        {
          n = 0;
          continue;
        }
        n = 1;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.map.b.a
 * JD-Core Version:    0.6.0
 */