package com.qq.jce.wup;

import java.io.InputStream;
import java.util.Properties;

public class WupInfo
{
  private static String clientBuilt;
  private static String clientInfo = null;
  private static String clientNumber;

  static
  {
    clientBuilt = null;
    clientNumber = null;
    try
    {
      InputStream localInputStream = WupInfo.class.getResourceAsStream("/com/qq/jce/wup/wup.properties");
      Properties localProperties = new Properties();
      localProperties.load(localInputStream);
      localInputStream.close();
      clientInfo = localProperties.getProperty("client.info");
      clientBuilt = localProperties.getProperty("client.built");
      clientNumber = localProperties.getProperty("client.number");
      label64: if (clientInfo == null)
        clientInfo = "Tencent Taf";
      if (clientBuilt == null)
        clientBuilt = "unknown";
      if (clientNumber == null)
        clientNumber = "unknown";
      return;
    }
    catch (Throwable localThrowable)
    {
      break label64;
    }
  }

  public static String getClientBuilt()
  {
    return clientBuilt;
  }

  public static String getClientInfo()
  {
    return clientInfo;
  }

  public static String getClientNumber()
  {
    return clientNumber;
  }

  public static String showString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Client version: " + getClientInfo() + "\n");
    localStringBuilder.append("Client built:   " + getClientBuilt() + "\n");
    localStringBuilder.append("Client number:  " + getClientNumber() + "\n");
    localStringBuilder.append("OS Name:        " + System.getProperty("os.name") + "\n");
    localStringBuilder.append("OS Version:     " + System.getProperty("os.version") + "\n");
    localStringBuilder.append("Architecture:   " + System.getProperty("os.arch") + "\n");
    localStringBuilder.append("JVM Version:    " + System.getProperty("java.runtime.version") + "\n");
    localStringBuilder.append("JVM Vendor:     " + System.getProperty("java.vm.vendor") + "\n");
    return localStringBuilder.toString();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.qq.jce.wup.WupInfo
 * JD-Core Version:    0.6.0
 */