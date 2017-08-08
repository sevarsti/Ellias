package com.tencent.stat.common;

import java.io.File;

class o
{
  private static int a = -1;

  public static boolean a()
  {
    if (a == 1)
      return true;
    if (a == 0)
      return false;
    String[] arrayOfString = { "/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/" };
    for (int i = 0; ; i++)
      try
      {
        if (i < arrayOfString.length)
        {
          File localFile = new File(arrayOfString[i] + "su");
          if ((localFile == null) || (!localFile.exists()))
            continue;
          a = 1;
          return true;
        }
      }
      catch (Exception localException)
      {
        a = 0;
        return false;
      }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.common.o
 * JD-Core Version:    0.6.0
 */