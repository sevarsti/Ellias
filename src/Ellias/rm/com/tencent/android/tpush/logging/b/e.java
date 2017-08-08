package com.tencent.android.tpush.logging.b;

import android.os.StatFs;
import java.io.File;
import java.util.Locale;

public class e
{
  private File a;
  private long b;
  private long c;

  public static e b(File paramFile)
  {
    e locale = new e();
    locale.a(paramFile);
    StatFs localStatFs = new StatFs(paramFile.getAbsolutePath());
    long l1 = localStatFs.getBlockSize();
    long l2 = localStatFs.getBlockCount();
    long l3 = localStatFs.getAvailableBlocks();
    locale.a(l2 * l1);
    locale.b(l3 * l1);
    return locale;
  }

  public File a()
  {
    return this.a;
  }

  public void a(long paramLong)
  {
    this.b = paramLong;
  }

  public void a(File paramFile)
  {
    this.a = paramFile;
  }

  public long b()
  {
    return this.b;
  }

  public void b(long paramLong)
  {
    this.c = paramLong;
  }

  public long c()
  {
    return this.c;
  }

  public String toString()
  {
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = a().getAbsolutePath();
    arrayOfObject[1] = Long.valueOf(c());
    arrayOfObject[2] = Long.valueOf(b());
    return String.format(localLocale, "[%s : %d / %d]", arrayOfObject);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.b.e
 * JD-Core Version:    0.6.0
 */