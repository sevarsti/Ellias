package com.tencent.component.debug;

import com.tencent.component.ComponentContext;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.FileUtil;
import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class FileTracerConfig
{
  public static final int a = 2147483647;
  public static final long b = 9223372036854775807L;
  public static final int c = 10;
  public static final int d = 0;
  public static final int e = 4096;
  public static final String f = ".log";
  public static final String g = "yyyy-MM-dd";
  public static final String h = "Tracer.File";
  public static final long i = 10000L;
  private static FileFilter j = new b();
  private String k = "Tracer.File";
  private int l = 2147483647;
  private int m = 2147483647;
  private int n = 4096;
  private long o = 10000L;
  private File p;
  private int q = 10;
  private String r = ".log";
  private long s = 9223372036854775807L;
  private FileFilter t = new c(this);
  private Comparator u = new d(this);

  public FileTracerConfig(File paramFile)
  {
    this(paramFile, 2147483647, 2147483647, 4096, "Tracer.File", 10000L, 10, ".log", 9223372036854775807L);
  }

  public FileTracerConfig(File paramFile, int paramInt1, int paramInt2, int paramInt3, String paramString1, long paramLong1, int paramInt4, String paramString2, long paramLong2)
  {
    d(paramFile);
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
      long l1 = new SimpleDateFormat("yyyy-MM-dd").parse(paramFile.getName()).getTime();
      return l1;
    }
    catch (Exception localException)
    {
    }
    return -1L;
  }

  private File d(long paramLong)
  {
    return f(a(paramLong));
  }

  private File f(File paramFile)
  {
    File[] arrayOfFile = b(paramFile);
    File localFile;
    if ((arrayOfFile == null) || (arrayOfFile.length == 0))
      localFile = new File(paramFile, "1" + j());
    while (true)
    {
      return localFile;
      b(arrayOfFile);
      localFile = arrayOfFile[(-1 + arrayOfFile.length)];
      int i1 = arrayOfFile.length - e();
      if ((int)localFile.length() > d())
      {
        int i3 = 1 + g(localFile);
        localFile = new File(paramFile, i3 + j());
        i1++;
      }
      for (int i2 = 0; i2 < i1; i2++)
        arrayOfFile[i2].delete();
    }
  }

  private static int g(File paramFile)
  {
    try
    {
      String str = paramFile.getName();
      int i1 = Integer.parseInt(str.substring(0, str.indexOf('.')));
      return i1;
    }
    catch (Exception localException)
    {
    }
    return -1;
  }

  public long a(File[] paramArrayOfFile)
  {
    int i1 = paramArrayOfFile.length;
    long l1 = 0L;
    for (int i2 = 0; i2 < i1; i2++)
    {
      File localFile = paramArrayOfFile[i2];
      if ((!localFile.exists()) || (!localFile.isFile()))
        continue;
      l1 += localFile.length();
    }
    return l1;
  }

  public File a()
  {
    return d(System.currentTimeMillis());
  }

  public File a(long paramLong)
  {
    File localFile = new File(h(), new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(paramLong)));
    localFile.mkdirs();
    return localFile;
  }

  public void a(int paramInt)
  {
    this.l = paramInt;
  }

  public void a(String paramString)
  {
    this.k = paramString;
  }

  public void b()
  {
    if (h() == null);
    while (true)
    {
      return;
      File[] arrayOfFile = h().listFiles(j);
      if (arrayOfFile == null)
        continue;
      int i1 = arrayOfFile.length;
      for (int i2 = 0; i2 < i1; i2++)
      {
        File localFile = arrayOfFile[i2];
        long l1 = a(localFile);
        if (System.currentTimeMillis() - l1 <= k())
          continue;
        FileUtil.a(localFile);
      }
    }
  }

  public void b(int paramInt)
  {
    this.m = paramInt;
  }

  public void b(long paramLong)
  {
    this.o = paramLong;
  }

  public void b(String paramString)
  {
    this.r = paramString;
  }

  public File[] b(File paramFile)
  {
    return paramFile.listFiles(this.t);
  }

  public File[] b(File[] paramArrayOfFile)
  {
    Arrays.sort(paramArrayOfFile, this.u);
    return paramArrayOfFile;
  }

  public long c(File paramFile)
  {
    f(paramFile);
    return a(b(paramFile));
  }

  public String c()
  {
    return this.k;
  }

  public void c(int paramInt)
  {
    this.n = paramInt;
  }

  public void c(long paramLong)
  {
    this.s = paramLong;
  }

  public int d()
  {
    return this.l;
  }

  public void d(int paramInt)
  {
    this.q = paramInt;
  }

  public void d(File paramFile)
  {
    this.p = paramFile;
  }

  public int e()
  {
    if (DebugUtil.a(ComponentContext.a()))
      return 100;
    return this.m;
  }

  public int f()
  {
    return this.n;
  }

  public long g()
  {
    return this.o;
  }

  public File h()
  {
    return this.p;
  }

  public int i()
  {
    return this.q;
  }

  public String j()
  {
    return this.r;
  }

  public long k()
  {
    return this.s;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.debug.FileTracerConfig
 * JD-Core Version:    0.6.0
 */