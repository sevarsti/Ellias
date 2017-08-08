package com.tencent.android.tpush.logging;

import android.text.format.Time;
import com.tencent.android.tpush.logging.a.i;
import com.tencent.android.tpush.logging.c.b;
import com.tencent.android.tpush.logging.c.c;
import com.tencent.android.tpush.logging.c.d;
import java.io.File;

public class LogUtil
{
  private static final String a = LogUtil.class.getName();
  public static String uin = "";

  private static String b(long paramLong1, long paramLong2)
  {
    if (d.a(paramLong1).equals(d.a(paramLong2)))
    {
      File localFile1 = new File(i.c(), d.a(paramLong1));
      File[] arrayOfFile1 = localFile1.listFiles(new c(paramLong1, paramLong1));
      File localFile2 = new File(localFile1.getPath() + File.separator + "log.zip");
      b.a(arrayOfFile1, localFile2);
      return localFile2.getPath();
    }
    File localFile3 = new File(i.c(), d.a(paramLong1));
    File localFile4 = new File(i.c(), d.a(paramLong2));
    TLog.i(a, ">>startWorkFolder=" + localFile3.getPath() + " endfolder=" + localFile4.getPath());
    Time localTime = new Time();
    localTime.set(paramLong1);
    for (File localFile8 : localFile3.listFiles(new c(localTime.hour, 23)))
      TLog.i(a, "filePath=" + localFile8.getPath());
    File localFile5 = new File(localFile3.getPath() + File.separator + localFile3.getName() + ".zip");
    b.a(???, localFile5);
    localTime.set(paramLong2);
    File[] arrayOfFile3 = localFile4.listFiles(new c(0, localTime.hour));
    File localFile6 = new File(localFile4.getPath() + File.separator + localFile4.getName() + ".zip");
    b.a(arrayOfFile3, localFile6);
    File localFile7 = new File(localFile4.getPath() + File.separator + "log.zip");
    b.a(new File[] { localFile5, localFile6 }, localFile7);
    localFile5.delete();
    localFile6.delete();
    return localFile7.getPath();
  }

  public static File[] getlogFiles(long paramLong1, long paramLong2)
  {
    try
    {
      File[] arrayOfFile = new File(i.c(), d.a(paramLong1)).listFiles(new c(paramLong1, paramLong2));
      return arrayOfFile;
    }
    catch (Throwable localThrowable)
    {
      TLog.e(a, "getlogFiles error : " + localThrowable.toString());
    }
    return null;
  }

  public static void upload(long paramLong1, long paramLong2)
  {
    new a(paramLong1, paramLong2).start();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.LogUtil
 * JD-Core Version:    0.6.0
 */