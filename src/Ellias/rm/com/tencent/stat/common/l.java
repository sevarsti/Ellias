package com.tencent.stat.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

class l
{
  static int a()
  {
    try
    {
      int i = new File("/sys/devices/system/cpu/").listFiles(new m()).length;
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 1;
  }

  static int b()
  {
    String str1 = "";
    try
    {
      InputStream localInputStream = new ProcessBuilder(new String[] { "/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" }).start().getInputStream();
      byte[] arrayOfByte = new byte[24];
      while (localInputStream.read(arrayOfByte) != -1)
        str1 = str1 + new String(arrayOfByte);
      localInputStream.close();
      String str2 = str1.trim();
      int j = str2.length();
      i = 0;
      if (j > 0)
      {
        int k = Integer.valueOf(str2).intValue();
        i = k;
      }
      return i * 1000;
    }
    catch (Exception localException)
    {
      while (true)
      {
        k.g().e(localException);
        int i = 0;
      }
    }
  }

  static int c()
  {
    String str1 = "";
    try
    {
      InputStream localInputStream = new ProcessBuilder(new String[] { "/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq" }).start().getInputStream();
      byte[] arrayOfByte = new byte[24];
      while (localInputStream.read(arrayOfByte) != -1)
        str1 = str1 + new String(arrayOfByte);
      localInputStream.close();
      String str2 = str1.trim();
      int j = str2.length();
      i = 0;
      if (j > 0)
      {
        int k = Integer.valueOf(str2).intValue();
        i = k;
      }
      return i * 1000;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        k.g().e(localIOException);
        int i = 0;
      }
    }
  }

  static String d()
  {
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"));
      String[] arrayOfString = localBufferedReader.readLine().split(":\\s+", 2);
      for (int i = 0; i < arrayOfString.length; i++);
      localBufferedReader.close();
      String str = arrayOfString[1];
      return str;
    }
    catch (Throwable localThrowable)
    {
      k.g().e(localThrowable);
    }
    return "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.common.l
 * JD-Core Version:    0.6.0
 */