package com.tencent.component.os.info;

import android.os.StatFs;
import java.io.File;

public class StorageInfo
{
  private File a;
  private long b;
  private long c;

  public static StorageInfo b(File paramFile)
  {
    StorageInfo localStorageInfo = new StorageInfo();
    localStorageInfo.a(paramFile);
    try
    {
      StatFs localStatFs = new StatFs(paramFile.getAbsolutePath());
      long l1 = localStatFs.getBlockSize();
      long l2 = localStatFs.getBlockCount();
      long l3 = localStatFs.getAvailableBlocks();
      localStorageInfo.a(l2 * l1);
      localStorageInfo.b(l3 * l1);
      return localStorageInfo;
    }
    catch (Exception localException)
    {
      localStorageInfo.b(0L);
      localStorageInfo.a(0L);
    }
    return localStorageInfo;
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
    Object[] arrayOfObject = new Object[3];
    if (this.a == null);
    for (String str = "NULL"; ; str = this.a.getAbsolutePath())
    {
      arrayOfObject[0] = str;
      arrayOfObject[1] = Long.valueOf(c());
      arrayOfObject[2] = Long.valueOf(b());
      return String.format("[%s : %d / %d]", arrayOfObject);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.os.info.StorageInfo
 * JD-Core Version:    0.6.0
 */