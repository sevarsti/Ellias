package com.tencent.component.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import com.tencent.component.ComponentContext;
import com.tencent.component.utils.log.LogUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class PerformanceUtil
{
  public static final int a = 0;
  public static final int b = 1;
  public static final int c = 2;
  private static final String d = "PerformanceUtil";
  private static int e = 0;
  private static long f = 0L;
  private static long g = 0L;

  public static int a()
  {
    if (e == 0);
    try
    {
      File[] arrayOfFile = new File("/sys/devices/system/cpu/").listFiles(new f());
      if (arrayOfFile == null);
      for (int i = 0; ; i = arrayOfFile.length)
      {
        e = i;
        return e;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        LogUtil.e("PerformanceUtil", "getNumCores exception occured,e=", localException);
        e = 1;
      }
    }
  }

  public static long b()
  {
    if (f == 0L);
    try
    {
      f = Long.parseLong(new BufferedReader(new InputStreamReader(new ProcessBuilder(new String[] { "/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" }).start().getInputStream())).readLine());
      return f;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        LogUtil.e("PerformanceUtil", "getCpuFrequence IOException occured,e=", localIOException);
        f = -1L;
      }
    }
    catch (ClassCastException localClassCastException)
    {
      while (true)
      {
        LogUtil.e("PerformanceUtil", "getCpuFrequence ClassCastException occured,e=", localClassCastException);
        f = -1L;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        LogUtil.e("PerformanceUtil", "getCpuFrequence Exception occured,e=", localException);
        f = -1L;
      }
    }
  }

  public static long c()
  {
    ActivityManager localActivityManager = (ActivityManager)ComponentContext.a().getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem;
  }

  // ERROR //
  public static long d()
  {
    // Byte code:
    //   0: getstatic 27	com/tencent/component/utils/PerformanceUtil:g	J
    //   3: lconst_0
    //   4: lcmp
    //   5: ifne +79 -> 84
    //   8: new 135	java/io/FileReader
    //   11: dup
    //   12: ldc 137
    //   14: invokespecial 138	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   17: astore_0
    //   18: new 61	java/io/BufferedReader
    //   21: dup
    //   22: aload_0
    //   23: sipush 8192
    //   26: invokespecial 141	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   29: astore_1
    //   30: aload_1
    //   31: invokevirtual 94	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   34: astore 11
    //   36: aload 11
    //   38: ifnull +161 -> 199
    //   41: aload 11
    //   43: ldc 143
    //   45: invokevirtual 147	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   48: iconst_1
    //   49: aaload
    //   50: invokestatic 153	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   53: invokevirtual 156	java/lang/Integer:intValue	()I
    //   56: istore 12
    //   58: iload 12
    //   60: sipush 1024
    //   63: imul
    //   64: i2l
    //   65: lstore 4
    //   67: aload_1
    //   68: invokevirtual 159	java/io/BufferedReader:close	()V
    //   71: aload_0
    //   72: ifnull +7 -> 79
    //   75: aload_0
    //   76: invokevirtual 160	java/io/FileReader:close	()V
    //   79: lload 4
    //   81: putstatic 27	com/tencent/component/utils/PerformanceUtil:g	J
    //   84: getstatic 27	com/tencent/component/utils/PerformanceUtil:g	J
    //   87: lreturn
    //   88: astore 13
    //   90: ldc 15
    //   92: ldc 162
    //   94: aload 13
    //   96: invokestatic 167	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   99: pop
    //   100: goto -21 -> 79
    //   103: astore 15
    //   105: aload 15
    //   107: astore_3
    //   108: aconst_null
    //   109: astore_0
    //   110: ldc2_w 103
    //   113: lstore 4
    //   115: ldc 15
    //   117: ldc 169
    //   119: aload_3
    //   120: invokestatic 54	com/tencent/component/utils/log/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   123: aload_0
    //   124: ifnull -45 -> 79
    //   127: aload_0
    //   128: invokevirtual 160	java/io/FileReader:close	()V
    //   131: goto -52 -> 79
    //   134: astore 9
    //   136: ldc 15
    //   138: ldc 162
    //   140: aload 9
    //   142: invokestatic 167	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   145: pop
    //   146: goto -67 -> 79
    //   149: astore 6
    //   151: aconst_null
    //   152: astore_0
    //   153: aload_0
    //   154: ifnull +7 -> 161
    //   157: aload_0
    //   158: invokevirtual 160	java/io/FileReader:close	()V
    //   161: aload 6
    //   163: athrow
    //   164: astore 7
    //   166: ldc 15
    //   168: ldc 162
    //   170: aload 7
    //   172: invokestatic 167	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   175: pop
    //   176: goto -15 -> 161
    //   179: astore 6
    //   181: goto -28 -> 153
    //   184: astore_2
    //   185: aload_2
    //   186: astore_3
    //   187: ldc2_w 103
    //   190: lstore 4
    //   192: goto -77 -> 115
    //   195: astore_3
    //   196: goto -81 -> 115
    //   199: ldc2_w 103
    //   202: lstore 4
    //   204: goto -137 -> 67
    //
    // Exception table:
    //   from	to	target	type
    //   75	79	88	java/io/IOException
    //   8	18	103	java/io/IOException
    //   127	131	134	java/io/IOException
    //   8	18	149	finally
    //   157	161	164	java/io/IOException
    //   18	36	179	finally
    //   41	58	179	finally
    //   67	71	179	finally
    //   115	123	179	finally
    //   18	36	184	java/io/IOException
    //   41	58	184	java/io/IOException
    //   67	71	195	java/io/IOException
  }

  public static int e()
  {
    int i = ((ActivityManager)ComponentContext.a().getSystemService("activity")).getMemoryClass();
    int j;
    if (i < 36)
      j = 2;
    do
    {
      return j;
      j = 0;
    }
    while (i >= 42);
    return 1;
  }

  public static String f()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("此设备性能信息：");
    localStringBuilder.append("Cpu频率 ");
    localStringBuilder.append(b());
    localStringBuilder.append(" Cpu核数 ");
    localStringBuilder.append(a());
    localStringBuilder.append(" 总内存大小 ");
    localStringBuilder.append(d());
    return localStringBuilder.toString();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.PerformanceUtil
 * JD-Core Version:    0.6.0
 */