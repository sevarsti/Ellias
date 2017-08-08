package com.tencent.msdk.tools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.tencent.msdk.config.ConfigManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class Logger
{
  public static final int ASSERT = 7;
  public static final int DEBUG = 3;
  public static String DEFAULT_TAG = "WeGame";
  public static final int ERROR = 6;
  public static final int INFO = 4;
  public static final int LOG_BOTH = 3;
  public static final int LOG_CONSOLE = 1;
  public static final int LOG_FILE = 2;
  private static final long LOG_FILE_SIZE = 10485760L;
  private static final int LOG_NULL = 0;
  private static final int STACK_TRACE_DEEP = 4;
  public static final int VERBOSE = 2;
  public static final int WARN = 5;
  private static FileLogHandler fileLog;
  private static int logDevice = 1;

  public static void d(Intent paramIntent)
  {
    if (logDevice == 0);
    while (true)
    {
      return;
      String str1 = getTag(null, 4);
      if ((paramIntent == null) || (paramIntent.getExtras() == null))
      {
        showLog(3, str1, "empty Intent", logDevice);
        return;
      }
      showLog(3, str1, "Action: " + paramIntent.getAction(), logDevice);
      showLog(3, str1, "Component: " + paramIntent.getComponent(), logDevice);
      showLog(3, str1, "Flags: " + paramIntent.getFlags(), logDevice);
      showLog(3, str1, "Scheme: " + paramIntent.getScheme(), logDevice);
      Bundle localBundle = paramIntent.getExtras();
      Iterator localIterator = localBundle.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str2 = (String)localIterator.next();
        if ((localBundle.get(str2) instanceof byte[]))
        {
          showLog(3, str1, str2 + ":" + HexUtil.bytes2HexStr(localBundle.getByteArray(str2)), logDevice);
          continue;
        }
        if ((localBundle.get(str2) instanceof String))
        {
          showLog(3, str1, str2 + ":" + localBundle.getString(str2), logDevice);
          continue;
        }
        if ((localBundle.get(str2) instanceof Long))
        {
          showLog(3, str1, str2 + ":" + localBundle.getLong(str2), logDevice);
          continue;
        }
        if ((localBundle.get(str2) instanceof Integer))
        {
          showLog(3, str1, str2 + ":" + localBundle.getInt(str2), logDevice);
          continue;
        }
        showLog(3, str1, str2, logDevice);
      }
    }
  }

  public static void d(Bundle paramBundle)
  {
    if (logDevice == 0);
    while (true)
    {
      return;
      String str1 = getTag(null, 4);
      if (paramBundle == null)
      {
        showLog(3, str1, "empty bundle", logDevice);
        return;
      }
      Iterator localIterator = paramBundle.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str2 = (String)localIterator.next();
        if ((paramBundle.get(str2) instanceof byte[]))
        {
          showLog(3, str1, str2 + ":" + HexUtil.bytes2HexStr(paramBundle.getByteArray(str2)), logDevice);
          continue;
        }
        if ((paramBundle.get(str2) instanceof String))
        {
          showLog(3, str1, str2 + ":" + paramBundle.getString(str2), logDevice);
          continue;
        }
        if ((paramBundle.get(str2) instanceof Long))
        {
          showLog(3, str1, str2 + ":" + paramBundle.getLong(str2), logDevice);
          continue;
        }
        if ((paramBundle.get(str2) instanceof Integer))
        {
          showLog(3, str1, str2 + ":" + paramBundle.getInt(str2), logDevice);
          continue;
        }
        showLog(3, str1, str2, logDevice);
      }
    }
  }

  public static void d(Object paramObject)
  {
    if (logDevice == 0)
      return;
    String str = getTag(null, 4);
    if (paramObject == null)
    {
      showLog(3, str, "empty msg", logDevice);
      return;
    }
    showLog(3, str, paramObject.toString(), logDevice);
  }

  public static void d(String paramString)
  {
    if (logDevice > 0)
      showLog(3, getTag(null, 4), " " + paramString, logDevice);
  }

  public static void d(String paramString1, String paramString2)
  {
    if (logDevice > 0)
      showLog(3, paramString1, paramString2, logDevice);
  }

  public static void e(String paramString)
  {
    if (logDevice > 0)
      showLog(6, getTag(null, 4), paramString, logDevice);
  }

  public static void e(String paramString1, String paramString2)
  {
    if (logDevice > 0)
      showLog(6, paramString1, paramString2, logDevice);
  }

  public static void e(String paramString, Throwable paramThrowable)
  {
    if (paramThrowable == null)
      return;
    StackTraceElement[] arrayOfStackTraceElement = new Throwable().getStackTrace();
    if (arrayOfStackTraceElement.length > 1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("class : ").append(arrayOfStackTraceElement[1].getClassName()).append("; line : ").append(arrayOfStackTraceElement[1].getLineNumber());
      showLog(6, paramString, localStringBuilder.toString(), logDevice);
    }
    paramThrowable.printStackTrace();
  }

  public static String getTag(String paramString, int paramInt)
  {
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    if ((paramInt < 0) || (paramInt >= arrayOfStackTraceElement.length))
      return "";
    String str1 = arrayOfStackTraceElement[paramInt].getClassName();
    String str2 = arrayOfStackTraceElement[paramInt].getMethodName();
    String str3 = "";
    int i = str1.lastIndexOf('.');
    if (i != -1)
      str3 = str1.substring(i + 1);
    if (CommonUtil.ckIsEmpty(paramString));
    for (String str4 = DEFAULT_TAG + " " + str3 + "." + str2; ; str4 = DEFAULT_TAG + ">" + paramString + " " + str3 + "." + str2)
      return str4;
  }

  public static void init()
  {
    if (logDevice > 1)
      fileLog = new FileLogHandler();
  }

  public static void setLogType(Activity paramActivity)
  {
    String str = ConfigManager.readValueByKey(paramActivity.getBaseContext(), "localLog");
    try
    {
      int j = Integer.parseInt(str);
      i = j;
      Log.d(DEFAULT_TAG, "Logger type: " + i);
      switch (i)
      {
      default:
        logDevice = 1;
        if (logDevice > 1)
          fileLog = new FileLogHandler();
        return;
      case 1:
      case 2:
      case 3:
      case 0:
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        int i = 1;
        continue;
        logDevice = 1;
        continue;
        logDevice = 2;
        continue;
        logDevice = 3;
        continue;
        logDevice = 0;
      }
    }
  }

  public static void showInConsole(int paramInt, String paramString1, String paramString2)
  {
    if (paramString2 == null)
      paramString2 = "NULL MSG";
    switch (paramInt)
    {
    default:
      return;
    case 6:
      Log.e(paramString1, paramString2);
      return;
    case 5:
      Log.w(paramString1, paramString2);
      return;
    case 3:
      Log.d(paramString1, paramString2);
      return;
    case 4:
      Log.i(paramString1, paramString2);
      return;
    case 2:
    }
    Log.v(paramString1, paramString2);
  }

  public static void showLog(int paramInt1, String paramString1, String paramString2, int paramInt2)
  {
    if (T.ckIsEmpty(paramString2))
      paramString2 = "NULL MSG";
    if (paramString1.length() > 89)
    {
      showInConsole(6, DEFAULT_TAG, "tag is longer than 89");
      paramString1 = paramString1.substring(0, 86) + "...";
    }
    switch (paramInt2)
    {
    default:
      return;
    case 1:
      showInConsole(paramInt1, paramString1, paramString2);
      return;
    case 2:
      writeToLog(System.currentTimeMillis() / 1000L + "\t" + paramString1 + "\t" + paramString2);
      return;
    case 3:
    }
    showInConsole(paramInt1, paramString1, paramString2);
    writeToLog(System.currentTimeMillis() / 1000L + "\t" + paramString1 + "\t" + paramString2);
  }

  public static void timeStamp(Exception paramException, String paramString)
  {
    StackTraceElement localStackTraceElement = paramException.getStackTrace()[0];
    String str1 = localStackTraceElement.getClassName();
    String str2 = localStackTraceElement.getMethodName();
    int i = localStackTraceElement.getLineNumber();
    if (paramString == null);
    for (String str3 = ""; ; str3 = paramString + "-")
    {
      d("TimeStamp", str3 + str1 + "." + str2 + "():" + i);
      return;
    }
  }

  public static void w(String paramString)
  {
    if (logDevice > 0)
      showLog(5, getTag(null, 4), paramString, logDevice);
  }

  public static void w(String paramString1, String paramString2)
  {
    if (logDevice > 0)
      showLog(5, paramString1, paramString2, logDevice);
  }

  private static void writeToLog(String paramString)
  {
    Message localMessage = fileLog.obtainMessage();
    localMessage.obj = paramString;
    fileLog.sendMessage(localMessage);
  }

  private static class FileLogHandler extends Handler
  {
    private boolean hasSDCard = true;
    private File logFile;
    private FileOutputStream logOutput;

    FileLogHandler()
    {
      if (this.hasSDCard)
        try
        {
          this.logFile = FileUtils.getLogFile();
          if (!this.logFile.exists())
          {
            this.logFile.createNewFile();
            return;
          }
          long l = this.logFile.length();
          if (l > 10485760L)
          {
            Log.d(Logger.DEFAULT_TAG, "Log size larger than LOG_FILE_SIZE:" + String.valueOf(l));
            this.logFile.delete();
            this.logFile.createNewFile();
            return;
          }
        }
        catch (IOException localIOException)
        {
        }
    }

    FileOutputStream getLogOutput()
      throws Exception
    {
      if (this.logOutput == null)
        this.logOutput = new FileOutputStream(this.logFile, true);
      return this.logOutput;
    }

    public void handleMessage(Message paramMessage)
    {
      if (!this.hasSDCard);
      while (true)
      {
        return;
        try
        {
          String str = (String)paramMessage.obj + "\n";
          if (str == null)
            continue;
          byte[] arrayOfByte = str.getBytes();
          getLogOutput().write(arrayOfByte, 0, arrayOfByte.length);
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.Logger
 * JD-Core Version:    0.6.0
 */