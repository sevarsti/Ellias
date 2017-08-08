package com.tencent.msdk.push;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import com.tencent.beacon.event.UserAction;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.push.db.PushClientDbModel;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PushHelper
{
  private static String mMatId;
  private static long sMatIdTimeOut = 10000L;

  static
  {
    mMatId = "";
  }

  public static Context getContext()
  {
    Context localContext;
    if (HttpPushService.sHttpPushServiceContext != null)
      localContext = HttpPushService.sHttpPushServiceContext;
    Activity localActivity;
    do
    {
      return localContext;
      localActivity = WeGame.getInstance().getActivity();
      localContext = null;
    }
    while (localActivity == null);
    return WeGame.getInstance().getActivity();
  }

  public static String getTestValue(String paramString)
  {
    if (Environment.getExternalStorageState() == "mounted")
    {
      File localFile = new File("msdk_push_test_config.ini");
      try
      {
        FileInputStream localFileInputStream = new FileInputStream(localFile);
        Properties localProperties = new Properties();
        localProperties.load(localFileInputStream);
        String str = localProperties.getProperty(paramString, "");
        if (T.ckIsEmpty(str))
          Logger.e("No " + paramString + " Configed");
        return str;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        return "";
      }
    }
    return "";
  }

  public static void reqMatid(MatIdCallback paramMatIdCallback)
  {
    String str = PushClientDbModel.getMatId();
    if (!T.ckIsEmpty(str))
    {
      paramMatIdCallback.onSuccess(str);
      return;
    }
    new Thread(paramMatIdCallback)
    {
      public void run()
      {
        long l = System.currentTimeMillis();
        while (System.currentTimeMillis() < l + PushHelper.sMatIdTimeOut)
        {
          try
          {
            PushHelper.access$102(UserAction.getQIMEI());
          }
          catch (Exception localException1)
          {
            try
            {
              while (true)
              {
                String str = ConfigManager.getConfigedMatId();
                if (!T.ckIsEmpty(str))
                  PushHelper.access$102(str);
                Logger.d("matid: " + PushHelper.mMatId);
                if (T.ckIsEmpty(PushHelper.mMatId))
                  break;
                this.val$callback.onSuccess(PushHelper.mMatId);
                return;
                localException1 = localException1;
                localException1.printStackTrace();
              }
            }
            catch (Exception localException2)
            {
              localException2.printStackTrace();
            }
          }
          continue;
          try
          {
            sleep(1000L);
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
        }
        this.val$callback.onTimeout();
      }
    }
    .start();
  }

  public static abstract interface MatIdCallback
  {
    public abstract void onSuccess(String paramString);

    public abstract void onTimeout();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.PushHelper
 * JD-Core Version:    0.6.0
 */