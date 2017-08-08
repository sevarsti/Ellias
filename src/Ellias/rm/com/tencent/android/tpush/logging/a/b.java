package com.tencent.android.tpush.logging.a;

import android.text.format.Time;
import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class b
{
  private static FileFilter a = new c();
  private String b = "Tracer.File";
  private int c = 2147483647;
  private int d = 2147483647;
  private int e = 4096;
  private long f = 10000L;
  private File g;
  private int h = 10;
  private String i = ".log";
  private long j = 9223372036854775807L;
  private FileFilter k = new d(this);
  private Comparator l = new e(this);

  public b(File paramFile, int paramInt1, int paramInt2, int paramInt3, String paramString1, long paramLong1, int paramInt4, String paramString2, long paramLong2)
  {
    c(paramFile);
    b(paramInt1);
    a(paramInt2);
    c(paramInt3);
    a(paramString1);
    b(paramLong1);
    d(paramInt4);
    b(paramString2);
    c(paramLong2);
  }

  public static long a(File paramFile)
  {
    try
    {
      long l1 = com.tencent.android.tpush.logging.c.d.a("yyyyMMdd").parse(paramFile.getName()).getTime();
      return l1;
    }
    catch (Exception localException)
    {
    }
    return -1L;
  }

  private File d(long paramLong)
  {
    return e(a(paramLong));
  }

  private File e(File paramFile)
  {
    Time localTime = new Time();
    localTime.set(System.currentTimeMillis());
    File[] arrayOfFile = b(paramFile);
    File localFile;
    if ((arrayOfFile == null) || (arrayOfFile.length == 0) || (!arrayOfFile[0].getName().startsWith(String.valueOf(localTime.hour))))
      localFile = new File(paramFile, localTime.hour + ".1" + j());
    while (true)
    {
      return localFile;
      a(arrayOfFile);
      localFile = arrayOfFile[(-1 + arrayOfFile.length)];
      int m = arrayOfFile.length - e();
      int n = (int)localFile.length();
      int i1 = d();
      int i2 = 0;
      if (n > i1)
      {
        int i3 = 1 + f(localFile);
        localFile = new File(paramFile, localTime.hour + "." + i3 + j());
        m++;
      }
      while (i2 < m)
      {
        arrayOfFile[i2].delete();
        i2++;
      }
    }
  }

  private static int f(File paramFile)
  {
    try
    {
      int m = Integer.parseInt(paramFile.getName().split("\\.")[1]);
      return m;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return -1;
  }

  public File a()
  {
    return d(System.currentTimeMillis());
  }

  public File a(long paramLong)
  {
    File localFile = new File(h(), com.tencent.android.tpush.logging.c.d.a(paramLong));
    if ((!localFile.mkdirs()) && (localFile.isDirectory()));
    return localFile;
  }

  public void a(int paramInt)
  {
    this.c = paramInt;
  }

  public void a(String paramString)
  {
    this.b = paramString;
  }

  public File[] a(File[] paramArrayOfFile)
  {
    Arrays.sort(paramArrayOfFile, this.l);
    return paramArrayOfFile;
  }

  public void b()
  {
    if (h() == null);
    while (true)
    {
      return;
      File[] arrayOfFile = h().listFiles(a);
      if (arrayOfFile == null)
        continue;
      int m = arrayOfFile.length;
      for (int n = 0; n < m; n++)
      {
        File localFile = arrayOfFile[n];
        long l1 = a(localFile);
        if (System.currentTimeMillis() - l1 <= k())
          continue;
        com.tencent.android.tpush.logging.c.b.a(localFile);
      }
    }
  }

  public void b(int paramInt)
  {
    this.d = paramInt;
  }

  public void b(long paramLong)
  {
    this.f = paramLong;
  }

  public void b(String paramString)
  {
    this.i = paramString;
  }

  public File[] b(File paramFile)
  {
    return paramFile.listFiles(this.k);
  }

  public String c()
  {
    return this.b;
  }

  public void c(int paramInt)
  {
    this.e = paramInt;
  }

  public void c(long paramLong)
  {
    this.j = paramLong;
  }

  public void c(File paramFile)
  {
    this.g = paramFile;
  }

  public int d()
  {
    return this.c;
  }

  public void d(int paramInt)
  {
    this.h = paramInt;
  }

  public int e()
  {
    return this.d;
  }

  public int f()
  {
    return this.e;
  }

  public long g()
  {
    return this.f;
  }

  public File h()
  {
    return this.g;
  }

  public int i()
  {
    return this.h;
  }

  public String j()
  {
    return this.i;
  }

  public long k()
  {
    return this.j;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.a.b
 * JD-Core Version:    0.6.0
 */