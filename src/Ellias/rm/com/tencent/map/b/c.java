package com.tencent.map.b;

import android.net.wifi.ScanResult;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public final class c
{
  private static c a;
  private long b = 0L;
  private List<b> c = new ArrayList();
  private List<a> d = new ArrayList();
  private String e;

  public static c a()
  {
    if (a == null)
      a = new c();
    return a;
  }

  private static boolean a(StringBuffer paramStringBuffer)
  {
    try
    {
      double d1 = new JSONObject(paramStringBuffer.toString()).getJSONObject("location").getDouble("accuracy");
      boolean bool = d1 < 5000.0D;
      int i = 0;
      if (bool)
        i = 1;
      return i;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  private boolean a(List<ScanResult> paramList)
  {
    if (paramList == null);
    do
    {
      return false;
      int i;
      if (this.d != null)
      {
        int k = 0;
        i = 0;
        if (k < this.d.size())
        {
          String str = ((a)this.d.get(k)).a;
          for (int m = 0; ; m++)
          {
            if ((str != null) && (m < paramList.size()))
            {
              if (!str.equals(((ScanResult)paramList.get(m)).BSSID))
                continue;
              i++;
            }
            k++;
            break;
          }
        }
      }
      else
      {
        i = 0;
      }
      int j = paramList.size();
      if ((j >= 6) && (i >= 1 + j / 2))
        return true;
      if ((j < 6) && (i >= 2))
        return true;
    }
    while ((this.d.size() > 2) || (paramList.size() > 2) || (Math.abs(System.currentTimeMillis() - this.b) > 30000L));
    return true;
  }

  public final void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, List<ScanResult> paramList)
  {
    this.b = System.currentTimeMillis();
    this.e = null;
    this.c.clear();
    b localb = new b(0);
    localb.a = paramInt1;
    localb.b = paramInt2;
    localb.c = paramInt3;
    localb.d = paramInt4;
    this.c.add(localb);
    if (paramList != null)
    {
      this.d.clear();
      for (int i = 0; i < paramList.size(); i++)
      {
        a locala = new a(0);
        locala.a = ((ScanResult)paramList.get(i)).BSSID;
        this.d.add(locala);
      }
    }
  }

  public final void a(String paramString)
  {
    this.e = paramString;
  }

  public final String b(int paramInt1, int paramInt2, int paramInt3, int paramInt4, List<ScanResult> paramList)
  {
    if ((this.e == null) || (this.e.length() < 10));
    label237: label251: 
    do
    {
      return null;
      String str = this.e;
      if ((str == null) || (paramList == null));
      for (str = null; ; str = null)
      {
        long l;
        do
        {
          this.e = str;
          if (this.e == null)
            break;
          if ((this.c == null) || (this.c.size() <= 0))
            break label251;
          b localb = (b)this.c.get(0);
          if ((localb.a != paramInt1) || (localb.b != paramInt2) || (localb.c != paramInt3) || (localb.d != paramInt4))
            break;
          if (((this.d != null) && (this.d.size() != 0)) || ((paramList != null) && (paramList.size() != 0)))
            break label237;
          return this.e;
          l = Math.abs(System.currentTimeMillis() - this.b);
        }
        while (((l <= 30000L) || (paramList.size() <= 2)) && ((l <= 45000L) || (paramList.size() > 2)) && (a(new StringBuffer(str))));
      }
      if (a(paramList))
        return this.e;
    }
    while (!a(paramList));
    return this.e;
  }

  public final void b()
  {
    this.e = null;
  }

  static final class a
  {
    public String a = null;
  }

  static final class b
  {
    public int a = -1;
    public int b = -1;
    public int c = -1;
    public int d = -1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.map.b.c
 * JD-Core Version:    0.6.0
 */