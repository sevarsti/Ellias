package com.pay.tool;

import com.pay.http.APNetworkManager;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;

public class ApCrashReport
{
  private static String a(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      localStringBuffer.append("num=1");
      localStringBuffer.append("&record0=");
      localStringBuffer.append("31=" + APCommMethod.getVersion());
      localStringBuffer.append("|21=" + "sdk.cgi.crash");
      String str1 = APAppDataInterface.singleton().getDeviceInfo();
      localStringBuffer.append("|8=" + URLEncoder.encode(str1, "utf-8"));
      String str2 = String.valueOf(System.currentTimeMillis());
      localStringBuffer.append("|35=" + paramString);
      localStringBuffer.append("|38=" + str2);
      label153: return localStringBuffer.toString();
    }
    catch (Exception localException)
    {
      break label153;
    }
  }

  public static void reportCrashApLog(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
    paramThrowable.printStackTrace(localPrintWriter);
    Throwable localThrowable = paramThrowable.getCause();
    String str1;
    if (localThrowable == null)
    {
      str1 = localStringWriter.toString();
      localPrintWriter.close();
      if (!str1.contains("com.pay"))
        break label89;
    }
    label89: for (int i = 1; ; i = 0)
    {
      if (i != 0)
      {
        String str2 = a(str1);
        APNetworkManager.getInstance().dataReport(str2, null);
      }
      return;
      localThrowable.printStackTrace(localPrintWriter);
      localThrowable = localThrowable.getCause();
      break;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.ApCrashReport
 * JD-Core Version:    0.6.0
 */