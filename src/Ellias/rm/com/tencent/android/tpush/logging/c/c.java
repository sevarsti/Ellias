package com.tencent.android.tpush.logging.c;

import android.text.format.Time;
import com.tencent.android.tpush.logging.TLog;
import java.io.File;
import java.io.FileFilter;

public class c
  implements FileFilter
{
  private static final String a = c.class.getName();
  private int b;
  private int c;

  public c(int paramInt1, int paramInt2)
  {
    this.b = paramInt1;
    this.c = paramInt2;
  }

  public c(long paramLong1, long paramLong2)
  {
    try
    {
      Time localTime = new Time();
      localTime.set(paramLong1);
      this.b = localTime.hour;
      localTime.set(paramLong2);
      this.c = localTime.hour;
      return;
    }
    catch (Exception localException)
    {
      TLog.e(a, "parse time err", localException);
    }
  }

  public boolean accept(File paramFile)
  {
    try
    {
      int i = Integer.parseInt(paramFile.getName().split("\\.")[0]);
      int j = this.b;
      int k = 0;
      if (i >= j)
      {
        int m = this.c;
        k = 0;
        if (i <= m)
          k = 1;
      }
      return k;
    }
    catch (Exception localException)
    {
      TLog.i(a, "file filter err:", localException);
    }
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.c.c
 * JD-Core Version:    0.6.0
 */