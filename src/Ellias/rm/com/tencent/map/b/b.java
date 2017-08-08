package com.tencent.map.b;

import android.location.Location;

public class b
{
  private static b b;
  public String a = "";
  private double c = 0.0D;
  private double d = 0.0D;
  private double e = 0.0D;
  private double f = 0.0D;
  private double g = 0.0D;
  private double h = 0.0D;
  private a i;
  private b j = null;
  private boolean k = false;

  public static b a()
  {
    if (b == null)
      b = new b();
    return b;
  }

  public static u a(String paramString1, String paramString2, byte[] paramArrayOfByte)
    throws k, h, Exception
  {
    int m = 1;
    if (t.b() != null);
    while (m == 0)
    {
      throw new k();
      m = 0;
    }
    try
    {
      u localu = i.a(false, paramString1, paramString2, null, paramArrayOfByte, false, true);
      return localu;
    }
    catch (Exception localException)
    {
    }
    throw localException;
  }

  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() == 0);
  }

  public final void a(double paramDouble1, double paramDouble2, a parama)
  {
    this.i = parama;
    if ((this.g != 0.0D) && (this.h != 0.0D))
    {
      float[] arrayOfFloat = new float[10];
      Location.distanceBetween(paramDouble1, paramDouble2, this.c, this.d, arrayOfFloat);
      if (arrayOfFloat[0] < 1500.0F)
        this.i.a(paramDouble1 + this.g, paramDouble2 + this.h);
    }
    do
      return;
    while (this.k);
    this.a = ("{\"source\":101,\"access_token\":\"160e7bd42dec9428721034e0146fc6dd\",\"location\":{\"latitude\":" + paramDouble1 + ",\"longitude\":" + paramDouble2 + "}\t}");
    this.e = paramDouble1;
    this.f = paramDouble2;
    this.j = new b();
    this.j.start();
  }

  public static abstract interface a
  {
    public abstract void a(double paramDouble1, double paramDouble2);
  }

  public final class b extends Thread
  {
    public b()
    {
    }

    public final void run()
    {
      try
      {
        byte[] arrayOfByte2 = r.a(b.this.a.getBytes());
        b.a(b.this, true);
        u localu2 = b.a("http://ls.map.soso.com/deflect?c=1", "SOSO MAP LBS SDK", arrayOfByte2);
        b.a(b.this, false);
        byte[] arrayOfByte3 = r.b(localu2.a);
        b.a(b.this, arrayOfByte3, localu2.b);
        return;
      }
      catch (Exception localException1)
      {
        do
        {
          int i = 0;
          while (true)
          {
            i++;
            if (i > 3)
              break;
            try
            {
              sleep(2000L);
              u localu1 = b.a("http://ls.map.soso.com/deflect?c=1", "SOSO MAP LBS SDK", r.a(b.this.a.getBytes()));
              b.a(b.this, false);
              byte[] arrayOfByte1 = r.b(localu1.a);
              b.a(b.this, arrayOfByte1, localu1.b);
              return;
            }
            catch (Exception localException2)
            {
            }
          }
          b.a(b.this, false);
        }
        while (b.a(b.this) == null);
        b.a(b.this).a(360.0D, 360.0D);
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.map.b.b
 * JD-Core Version:    0.6.0
 */