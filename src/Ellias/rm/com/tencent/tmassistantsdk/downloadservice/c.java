package com.tencent.tmassistantsdk.downloadservice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.PowerManager.WakeLock;
import android.os.StatFs;
import android.text.TextUtils;
import com.tencent.tmassistantsdk.f.b;
import com.tencent.tmassistantsdk.g.f;
import com.tencent.tmassistantsdk.g.l;
import java.io.File;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class c
{
  public static final String a = c.class.getSimpleName();

  public static PowerManager.WakeLock a()
  {
    return null;
  }

  public static String a(String paramString)
  {
    String str1 = paramString.replace("\r", "").replace("\n", "").trim();
    Object localObject = new String(str1);
    try
    {
      Uri localUri = Uri.parse(str1);
      String str2 = localUri.getLastPathSegment();
      if ((str2 != null) && (str2.length() > 0))
      {
        String str3 = ((String)localObject).replace(str2, URLEncoder.encode(localUri.getLastPathSegment()).replace("+", "%20"));
        localObject = str3;
      }
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return (String)localObject;
  }

  public static String a(String paramString1, String paramString2)
  {
    String str1 = f.c(paramString1);
    if (TextUtils.isEmpty(str1))
      str1 = Integer.toString(Math.abs(paramString1.hashCode()));
    String str2 = "";
    if (!TextUtils.isEmpty(paramString2))
    {
      if (!paramString2.equals("application/vnd.android.package-archive"))
        break label64;
      str2 = ".apk";
    }
    while (true)
    {
      return str1 + str2;
      label64: if (paramString2.equals("application/tm.android.apkdiff"))
      {
        str2 = ".diff";
        continue;
      }
      if (!paramString2.equals("resource/tm.android.unknown"))
        continue;
      str2 = ".other";
    }
  }

  public static boolean a(String paramString, long paramLong)
  {
    int i = 1;
    long l1 = 0L;
    long l2 = -1L;
    int j = h(paramString);
    if (j == i)
    {
      StatFs localStatFs1 = new StatFs(Environment.getDataDirectory().getPath());
      l2 = localStatFs1.getBlockSize() * (localStatFs1.getAvailableBlocks() - 4L);
      if (l2 >= l1)
        break label160;
    }
    while (true)
    {
      long l3 = ()(1.5F * (float)paramLong);
      if (l3 <= 104857600L)
        break;
      if (l1 >= l3)
      {
        return i;
        if (j == 2)
        {
          if (!"mounted".equals(Environment.getExternalStorageState()))
            continue;
          StatFs localStatFs2 = new StatFs(new File(Environment.getExternalStorageDirectory().getPath()).getPath());
          l2 = localStatFs2.getBlockSize() * (localStatFs2.getAvailableBlocks() - 4L);
          if (l2 < l1)
            continue;
        }
        label160: l1 = l2;
        continue;
      }
      return false;
    }
    if (l1 >= 104857600L);
    while (true)
    {
      return i;
      i = 0;
    }
  }

  public static String b()
  {
    monitorenter;
    try
    {
      Context localContext = f.a().b();
      String str1;
      if (localContext == null)
        str1 = "";
      while (true)
      {
        return str1;
        try
        {
          if (localContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
            break label44;
          str1 = "";
        }
        catch (Exception localException)
        {
          str1 = "";
        }
        continue;
        label44: NetworkInfo localNetworkInfo = ((ConnectivityManager)localContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (localNetworkInfo == null)
        {
          str1 = "";
          continue;
        }
        if (localNetworkInfo.getType() == 1)
        {
          str1 = "wifi";
          continue;
        }
        String str2 = localNetworkInfo.getExtraInfo();
        if (str2 == null)
        {
          str1 = "";
          continue;
        }
        str1 = str2.toLowerCase();
        l.a(a, "netInfo  =  " + str1);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static String b(String paramString)
  {
    if (paramString.contains(".apk"))
    {
      String str = paramString.trim().substring(1 + paramString.lastIndexOf("/")).trim();
      if (str.contains("?"))
        str = str.substring(0, str.lastIndexOf("?"));
      if (!TextUtils.isEmpty(str))
      {
        l.b(a, "file name = " + str);
        return e(str);
      }
    }
    return null;
  }

  public static boolean b(String paramString1, String paramString2)
  {
    try
    {
      boolean bool = new File(b.b(a(paramString1, paramString2))).exists();
      int i = 0;
      if (bool)
        i = 1;
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return false;
    }
    finally
    {
    }
    throw localObject;
  }

  public static String c(String paramString)
  {
    if (paramString != null)
      return URLDecoder.decode(paramString);
    return null;
  }

  public static boolean c()
  {
    Context localContext = f.a().b();
    if (localContext == null)
    {
      l.d(a, "GlobalUtil.getInstance().getContext() == null.");
      return false;
    }
    NetworkInfo localNetworkInfo = ((ConnectivityManager)localContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo != null);
    for (boolean bool = localNetworkInfo.isAvailable(); ; bool = false)
      return bool;
  }

  public static String d(String paramString)
  {
    return paramString.replace("?", "_").replace("*", "_").replace(" ", "_").replace("$", "_").replace("&", "_").replace("@", "_").replace("#", "_").replace("<", "_").replace(">", "_").replace("|", "_").replace(":", "_").replace("/", "_").replace("\\", "_").replace("\"", "_");
  }

  public static String e(String paramString)
  {
    if (paramString == null)
      return null;
    int i = paramString.lastIndexOf('.');
    if ((i <= 0) || (i == -1 + paramString.length()))
      return paramString;
    String[] arrayOfString = new String[2];
    arrayOfString[0] = paramString.substring(0, i);
    arrayOfString[1] = paramString.substring(i, paramString.length());
    int j = 0;
    if (j == 0);
    for (String str = paramString; ; str = arrayOfString[0] + "(" + j + ")" + arrayOfString[1])
    {
      j++;
      File localFile = new File(b.e() + File.separator + str);
      if ((localFile == null) || (localFile.exists()))
        break;
      return str;
    }
  }

  public static boolean f(String paramString)
  {
    try
    {
      new URI(a(paramString));
      return true;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return false;
  }

  public static boolean g(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return false;
      try
      {
        boolean bool = new File(b.b(paramString)).exists();
        if (bool)
          return true;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return false;
      }
      finally
      {
      }
    }
    throw localObject;
  }

  public static int h(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    do
    {
      return 0;
      if ((paramString != null) && (paramString.contains("/data/data")))
        return 1;
    }
    while (!b.g());
    return 2;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.c
 * JD-Core Version:    0.6.0
 */