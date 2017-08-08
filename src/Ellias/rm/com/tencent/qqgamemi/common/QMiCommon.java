package com.tencent.qqgamemi.common;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.qq.taf.jce.HexUtil;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.QMiService;
import com.tencent.qqgamemi.login.QMiLoginManager;
import com.tencent.qqgamemi.view.QMiToast;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class QMiCommon
{
  public static final String a = "com.tencent.qqgame";
  public static final String b = "com.tencent.gamejoy";
  public static final String c = "com.tencent.ttx5";
  private static final String d = "QMiCommon";
  private static final String e = "/.QMiPlugin";
  private static final String f = "ro.miui.ui.version.name";
  private static final String g = "V5";
  private static final Object h = "com.tencent.qqgamemi.QMiService";

  public static int a(Context paramContext, String paramString)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramString, 0);
      if (localPackageInfo != null)
      {
        int i = localPackageInfo.versionCode;
        return i;
      }
    }
    catch (Exception localException)
    {
    }
    return -1;
  }

  public static Bitmap a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length != 0)
      return BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
    return null;
  }

  public static String a()
  {
    String str = Environment.getExternalStorageDirectory().getPath() + "/.QMiPlugin";
    File localFile = new File(str);
    if (!localFile.exists())
      localFile.mkdir();
    return str;
  }

  public static String a(long paramLong)
  {
    if (paramLong >= 1048576L)
      return paramLong / 1048576L + "MB";
    if (paramLong >= 1024L)
      return paramLong / 1024L + "KB";
    return paramLong + "B";
  }

  public static String a(Context paramContext)
  {
    ComponentName localComponentName = b(paramContext);
    if (localComponentName != null)
      return localComponentName.getPackageName();
    return "";
  }

  public static String a(String paramString)
  {
    return paramString.substring(1 + paramString.lastIndexOf(File.separator), paramString.length());
  }

  public static ComponentName b(Context paramContext)
  {
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    try
    {
      List localList = localActivityManager.getRunningTasks(1);
      if ((localList != null) && (localList.size() > 0))
      {
        ActivityManager.RunningTaskInfo localRunningTaskInfo = (ActivityManager.RunningTaskInfo)localList.get(0);
        if (localRunningTaskInfo != null)
        {
          ComponentName localComponentName = localRunningTaskInfo.topActivity;
          return localComponentName;
        }
      }
    }
    catch (Exception localException)
    {
      TLog.c("QMiCommon", localException.getMessage(), localException);
    }
    return null;
  }

  public static void b()
  {
    b(a());
  }

  public static void b(Context paramContext, String paramString)
  {
    TLog.c("QMiCommon", "install " + paramString);
    Intent localIntent = new Intent();
    localIntent.addFlags(268435456);
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      localActivityNotFoundException.printStackTrace();
    }
  }

  private static void b(String paramString)
  {
    File localFile1 = new File(paramString);
    if ((localFile1.exists()) && (localFile1.isDirectory()))
    {
      File[] arrayOfFile = localFile1.listFiles();
      int i = arrayOfFile.length;
      int j = 0;
      if (j < i)
      {
        File localFile2 = arrayOfFile[j];
        if (localFile2.isDirectory())
          b(localFile2.getAbsolutePath());
        while (true)
        {
          j++;
          break;
          localFile2.delete();
        }
      }
    }
  }

  public static void c(Context paramContext, String paramString)
  {
    QMiToast.a(paramContext, paramString, 1000).show();
  }

  public static boolean c()
  {
    try
    {
      Class localClass = Class.forName("android.os.SystemProperties");
      str = (String)localClass.getDeclaredMethod("get", new Class[] { String.class, String.class }).invoke(localClass, new Object[] { "ro.miui.ui.version.name", "" });
      TLog.c("QMiCommon", "systemName:" + str);
      if ((str != null) && (str.equals("V5")))
        return true;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      while (true)
      {
        localClassNotFoundException.printStackTrace();
        str = null;
      }
    }
    catch (SecurityException localSecurityException)
    {
      while (true)
      {
        localSecurityException.printStackTrace();
        str = null;
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      while (true)
      {
        localNoSuchMethodException.printStackTrace();
        str = null;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
      {
        localIllegalArgumentException.printStackTrace();
        str = null;
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      while (true)
      {
        localIllegalAccessException.printStackTrace();
        str = null;
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      while (true)
      {
        localInvocationTargetException.printStackTrace();
        String str = null;
      }
    }
    return false;
  }

  public static boolean c(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo("com.tencent.gamejoy", 0);
      int i = 0;
      if (localPackageInfo != null)
        i = 1;
      return i;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public static long d()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    return localStatFs.getAvailableBlocks() * localStatFs.getBlockSize();
  }

  public static void d(Context paramContext, String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramContext == null))
      return;
    CookieSyncManager.createInstance(paramContext);
    CookieSyncManager.getInstance().sync();
    CookieManager localCookieManager = CookieManager.getInstance();
    localCookieManager.setAcceptCookie(true);
    String str1 = "";
    String str2 = "";
    String str3 = "";
    String str4 = "";
    if (QMiLoginManager.a().j() != null)
      str1 = QMiLoginManager.a().j();
    if (QMiLoginManager.a().e() != null)
      str2 = HexUtil.bytes2HexStr(QMiLoginManager.a().e());
    String str5 = Long.toString(QMiLoginManager.a().i());
    if (QMiLoginManager.a().f() != null)
      str3 = QMiLoginManager.a().f();
    if (QMiLoginManager.a().h() != null)
      str4 = QMiLoginManager.a().h();
    String str6 = String.format("http://www.%s/", new Object[] { paramString });
    localCookieManager.setCookie(str6, String.format("iLoginType=%1$s;domain=%2$s; path=/", new Object[] { "2", paramString }));
    localCookieManager.setCookie(str6, String.format("sid=%1$s;domain=%2$s; path=/", new Object[] { str1, paramString }));
    localCookieManager.setCookie(str6, String.format("syb_token=%1$s;domain=%2$s; path=/", new Object[] { str2, paramString }));
    localCookieManager.setCookie(str6, String.format("syb_id=%1$s;domain=%2$s; path=/", new Object[] { str5, paramString }));
    localCookieManager.setCookie(str6, String.format("wechat_token=%1$s;domain=%2$s; path=/", new Object[] { str3, paramString }));
    localCookieManager.setCookie(str6, String.format("wechat_openid=%1$s;domain=%2$s; path=/", new Object[] { str4, paramString }));
  }

  public static boolean d(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo("com.tencent.qqgame", 0);
      int i = 0;
      if (localPackageInfo != null)
      {
        int j = localPackageInfo.versionCode;
        i = 0;
        if (j >= 30000)
        {
          int k = localPackageInfo.versionCode;
          i = 0;
          if (k < 30200)
          {
            if (DebugUtil.a())
              LogUtil.d("QMiCommon", "old hall versionCode:" + localPackageInfo.versionCode);
            i = 1;
          }
        }
      }
      return i;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public static boolean e(Context paramContext)
  {
    if ((DebugUtil.a()) && (a(paramContext).equals("com.tencent.gamejoy")));
    while (true)
    {
      return false;
      PackageManager localPackageManager = paramContext.getPackageManager();
      try
      {
        PackageInfo localPackageInfo2 = localPackageManager.getPackageInfo(a(paramContext), 4);
        localPackageInfo1 = localPackageInfo2;
        if ((localPackageInfo1 == null) || (localPackageInfo1.services == null))
          continue;
        ServiceInfo[] arrayOfServiceInfo = localPackageInfo1.services;
        int i = arrayOfServiceInfo.length;
        j = 0;
        if (j >= i)
          continue;
        ServiceInfo localServiceInfo = arrayOfServiceInfo[j];
        if ((localServiceInfo != null) && (localServiceInfo.name.equals(h)))
          return true;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        while (true)
        {
          int j;
          TLog.e("QMiCommon", "isCurrentGameIsSDKService error:" + a(paramContext));
          PackageInfo localPackageInfo1 = null;
          continue;
          j++;
        }
      }
    }
  }

  public static void f(Context paramContext)
  {
    if (paramContext != null)
      QMiService.a(paramContext, 190);
  }

  public static void hideQMi(Context paramContext)
  {
    if (paramContext != null)
      QMiService.a(paramContext, 101);
  }

  public static void showQMi(Context paramContext)
  {
    if (paramContext != null)
      QMiService.a(paramContext, 100);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.common.QMiCommon
 * JD-Core Version:    0.6.0
 */