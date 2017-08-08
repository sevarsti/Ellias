package com.tencent.msdk.qmi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.tools.Logger;
import java.lang.reflect.Method;

public class QmiSdkApiProxy
{
  public static void hideQMi(Context paramContext)
  {
    try
    {
      Class localClass = Class.forName("com.tencent.qqgamemi.QmiSdkApi");
      localClass.getMethod("hideQMi", new Class[] { Context.class }).invoke(localClass, new Object[] { paramContext });
      return;
    }
    catch (Exception localException)
    {
      Logger.e("hideQMi: Reference to 'QmiSdk' library project is a must if u want to use Qmi");
      localException.printStackTrace();
    }
  }

  public static void notifyQmiLogin()
  {
    Logger.d("notifyQmiLogin");
    try
    {
      Class localClass = Class.forName("com.tencent.qqgamemi.QMiService");
      Intent localIntent = new Intent();
      localIntent.putExtra("operation", 230);
      localIntent.setClass(WeGame.getInstance().getActivity(), localClass);
      WeGame.getInstance().getActivity().startService(localIntent);
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      Logger.d("com.tencent.qqgamemi.QMiService Class Not Found");
      localClassNotFoundException.printStackTrace();
    }
  }

  public static void setGameEngineType(String paramString)
  {
    try
    {
      Class localClass = Class.forName("com.tencent.qqgamemi.QmiSdkApi");
      localClass.getMethod("setGameEngineType", new Class[] { String.class }).invoke(localClass, new Object[] { paramString });
      return;
    }
    catch (Exception localException)
    {
      Logger.e("Reference to 'QmiSdk' library project is a must if u want to use Qmi");
      localException.printStackTrace();
    }
  }

  public static void showQMi(Context paramContext, String paramString)
  {
    try
    {
      Class localClass = Class.forName("com.tencent.qqgamemi.QmiSdkApi");
      localClass.getMethod("showQMi", new Class[] { Context.class, String.class }).invoke(localClass, new Object[] { paramContext, paramString });
      return;
    }
    catch (Exception localException)
    {
      Logger.e("showQMi: Reference to 'QmiSdk' library project is a must if u want to use Qmi");
      localException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.qmi.QmiSdkApiProxy
 * JD-Core Version:    0.6.0
 */