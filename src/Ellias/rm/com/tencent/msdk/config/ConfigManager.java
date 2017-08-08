package com.tencent.msdk.config;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import com.tencent.msdk.notice.NoticeManager;
import com.tencent.msdk.push.PushConst;
import com.tencent.msdk.tools.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager
{
  private static final String ACCEPT_SERVER_INTERVAL_KEY = "ACCEPT_SERVER_INTERVAL";
  private static final String IS_BETA_KEY = "BETA";
  private static final String MAT_ID_KEY = "TEST_MAT_ID";
  public static final String NEED_LOCAL_LOG = "localLog";
  private static final String NEED_NOTICE_KEY = "needNotice";
  private static final String POLLING_INTERVAL_KEY = "TEST_POLLING_INTERVAL";
  private static final String POLLING_URL_KEY = "POLLING_URL";
  private static final String PUSH_URL_KEY = "PUSH_URL";
  private static String configFileName = "msdkconfig.ini";
  private static final String domainKey = "MSDK_URL";
  private static final String isBetaKey = "BETA";
  private static String sPushConfigFile = "pushconfig.ini";

  public static String getApiDomain(Context paramContext)
  {
    try
    {
      InputStream localInputStream = paramContext.getResources().getAssets().open(configFileName);
      Properties localProperties = new Properties();
      localProperties.load(localInputStream);
      String str = localProperties.getProperty("MSDK_URL", "");
      if ((str == null) || (str.length() == 0))
        Logger.e("No Domain Configed");
      return str;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      Logger.w("Please check your msdkconfig.ini file under /assets/");
    }
    return "";
  }

  public static String getConfigedMatId()
  {
    return readConfigFromSdCard(sPushConfigFile, "TEST_MAT_ID", "");
  }

  public static String getConfigedPollingInterval()
  {
    return readConfigFromSdCard(sPushConfigFile, "TEST_POLLING_INTERVAL", "");
  }

  public static String getIsServerIntervalAccepted()
  {
    return readConfigFromSdCard(sPushConfigFile, "ACCEPT_SERVER_INTERVAL", "");
  }

  public static int getNoticeTime(Context paramContext)
  {
    String str = readValueByKey(paramContext, "noticeTime");
    try
    {
      int i = Integer.parseInt(str);
      if (i > NoticeManager.sNoticeTimeLimit)
        return i;
      int j = NoticeManager.sDefaultNoticeTime;
      return j;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      Logger.e("Wrong Notice time :" + str);
    }
    return NoticeManager.sDefaultNoticeTime;
  }

  public static String getPushMsgDomain()
  {
    return readConfigFromSdCard(sPushConfigFile, "PUSH_URL", "http://push.msdk.qq.com");
  }

  public static String getPushPollingDomain()
  {
    return readConfigFromSdCard(sPushConfigFile, "POLLING_URL", "http://polling.msdk.qq.com");
  }

  public static boolean isBeta(Context paramContext)
  {
    try
    {
      InputStream localInputStream = paramContext.getResources().getAssets().open(configFileName);
      Properties localProperties = new Properties();
      localProperties.load(localInputStream);
      String str = localProperties.getProperty("BETA", "");
      if ((str == null) || (str.length() == 0))
      {
        Logger.d("Whitelist is Closed!");
        return false;
      }
      boolean bool = "true".equals(str.trim());
      if (bool)
        return true;
    }
    catch (IOException localIOException)
    {
      Logger.w("Please check your msdkconfig.ini file under /assets/");
      localIOException.printStackTrace();
    }
    return false;
  }

  public static boolean needNotice(Context paramContext)
  {
    try
    {
      InputStream localInputStream = paramContext.getResources().getAssets().open(configFileName);
      Properties localProperties = new Properties();
      localProperties.load(localInputStream);
      String str = localProperties.getProperty("needNotice", "");
      if ((str == null) || (str.length() == 0))
      {
        Logger.d("needNotice closed");
        return false;
      }
      boolean bool = "true".equals(str.trim());
      if (bool)
        return true;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      Logger.w("Please check your msdkconfig.ini file under /assets/");
    }
    return false;
  }

  private static String readConfigFromSdCard(String paramString1, String paramString2, String paramString3)
  {
    if (!"mounted".equals(Environment.getExternalStorageState()))
      return paramString3;
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(new File(PushConst.PUSH_ROOT_DIR + paramString1));
      Properties localProperties = new Properties();
      localProperties.load(localFileInputStream);
      str1 = localProperties.getProperty(paramString2, "");
      if ((str1 == null) || (str1.length() == 0))
      {
        Logger.d("no key: " + paramString2);
        return paramString3;
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      String str1;
      localFileNotFoundException.printStackTrace();
      return paramString3;
      String str2 = str1.trim();
      return str2;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return paramString3;
  }

  public static String readValueByKey(Context paramContext, String paramString)
  {
    try
    {
      InputStream localInputStream = paramContext.getResources().getAssets().open(configFileName);
      Properties localProperties = new Properties();
      localProperties.load(localInputStream);
      String str1 = localProperties.getProperty(paramString, "");
      if ((str1 == null) || (str1.length() == 0))
      {
        Logger.d("no key: " + paramString);
        return "";
      }
      String str2 = str1.trim();
      return str2;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      Logger.w("Please check your msdkconfig.ini file under /assets/");
    }
    return "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.config.ConfigManager
 * JD-Core Version:    0.6.0
 */