package com.tencent.stat.common;

import android.util.Log;

public final class StatLogger
{
  private String a = "default";
  private boolean b = true;
  private int c = 2;

  public StatLogger()
  {
  }

  public StatLogger(String paramString)
  {
    this.a = paramString;
  }

  private String a()
  {
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    if (arrayOfStackTraceElement == null)
      return null;
    int i = arrayOfStackTraceElement.length;
    int j = 0;
    label18: StackTraceElement localStackTraceElement;
    if (j < i)
    {
      localStackTraceElement = arrayOfStackTraceElement[j];
      if (!localStackTraceElement.isNativeMethod())
        break label42;
    }
    label42: 
    do
    {
      j++;
      break label18;
      break;
    }
    while ((localStackTraceElement.getClassName().equals(Thread.class.getName())) || (localStackTraceElement.getClassName().equals(getClass().getName())));
    return "[" + Thread.currentThread().getName() + "(" + Thread.currentThread().getId() + "): " + localStackTraceElement.getFileName() + ":" + localStackTraceElement.getLineNumber() + "]";
  }

  public void d(Object paramObject)
  {
    if (isDebugEnable())
      debug(paramObject);
  }

  public void debug(Object paramObject)
  {
    String str1;
    if (this.c <= 3)
    {
      str1 = a();
      if (str1 != null)
        break label32;
    }
    label32: for (String str2 = paramObject.toString(); ; str2 = str1 + " - " + paramObject)
    {
      Log.d(this.a, str2);
      return;
    }
  }

  public void e(Exception paramException)
  {
    if (isDebugEnable())
      error(paramException);
  }

  public void e(Object paramObject)
  {
    if (isDebugEnable())
      error(paramObject);
  }

  public void error(Exception paramException)
  {
    if (this.c <= 6)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      String str = a();
      StackTraceElement[] arrayOfStackTraceElement = paramException.getStackTrace();
      if (str != null)
        localStringBuffer.append(str + " - " + paramException + "\r\n");
      while ((arrayOfStackTraceElement != null) && (arrayOfStackTraceElement.length > 0))
      {
        int i = arrayOfStackTraceElement.length;
        for (int j = 0; j < i; j++)
        {
          StackTraceElement localStackTraceElement = arrayOfStackTraceElement[j];
          if (localStackTraceElement == null)
            continue;
          localStringBuffer.append("[ " + localStackTraceElement.getFileName() + ":" + localStackTraceElement.getLineNumber() + " ]\r\n");
        }
        localStringBuffer.append(paramException + "\r\n");
      }
      Log.e(this.a, localStringBuffer.toString());
    }
  }

  public void error(Object paramObject)
  {
    String str1;
    if (this.c <= 6)
    {
      str1 = a();
      if (str1 != null)
        break label33;
    }
    label33: for (String str2 = paramObject.toString(); ; str2 = str1 + " - " + paramObject)
    {
      Log.e(this.a, str2);
      return;
    }
  }

  public int getLogLevel()
  {
    return this.c;
  }

  public void i(Object paramObject)
  {
    if (isDebugEnable())
      info(paramObject);
  }

  public void info(Object paramObject)
  {
    String str1;
    if (this.c <= 4)
    {
      str1 = a();
      if (str1 != null)
        break label32;
    }
    label32: for (String str2 = paramObject.toString(); ; str2 = str1 + " - " + paramObject)
    {
      Log.i(this.a, str2);
      return;
    }
  }

  public boolean isDebugEnable()
  {
    return this.b;
  }

  public void setDebugEnable(boolean paramBoolean)
  {
    this.b = paramBoolean;
  }

  public void setLogLevel(int paramInt)
  {
    this.c = paramInt;
  }

  public void setTag(String paramString)
  {
    this.a = paramString;
  }

  public void v(Object paramObject)
  {
    if (isDebugEnable())
      verbose(paramObject);
  }

  public void verbose(Object paramObject)
  {
    String str1;
    if (this.c <= 2)
    {
      str1 = a();
      if (str1 != null)
        break label32;
    }
    label32: for (String str2 = paramObject.toString(); ; str2 = str1 + " - " + paramObject)
    {
      Log.v(this.a, str2);
      return;
    }
  }

  public void w(Object paramObject)
  {
    if (isDebugEnable())
      warn(paramObject);
  }

  public void warn(Object paramObject)
  {
    String str1;
    if (this.c <= 5)
    {
      str1 = a();
      if (str1 != null)
        break label32;
    }
    label32: for (String str2 = paramObject.toString(); ; str2 = str1 + " - " + paramObject)
    {
      Log.w(this.a, str2);
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.common.StatLogger
 * JD-Core Version:    0.6.0
 */