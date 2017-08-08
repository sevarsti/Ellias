package com.tencent.msdk.webview;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MttLoader
{
  private static final int BROWSER_MTT = 2;
  private static final int BROWSER_NONE = -1;
  private static final int BROWSER_QBX = 0;
  private static final int BROWSER_QBX5 = 1;

  @Deprecated
  public static final String KEY_ACTIVITY_NAME = "KEY_ACT";

  @Deprecated
  public static final String KEY_APP_NAME = "KEY_APPNAME";
  public static final String KEY_EUSESTAT = "KEY_EUSESTAT";

  @Deprecated
  public static final String KEY_PACKAGE = "KEY_PKG";
  public static final String KEY_PID = "KEY_PID";
  public static final String MTT_ACTION = "com.tencent.QQBrowser.action.VIEW";
  private static final String MTT_PACKAGE_MTT = "com.tencent.mtt";
  private static final String MTT_PACKAGE_MTT_X86 = "com.tencent.mtt.x86";
  private static final String MTT_PACKAGE_QBX = "com.tencent.qbx";
  private static final String MTT_PACKAGE_QBX5 = "com.tencent.qbx5";
  public static final String PID_MOBILE_QQ = "50079";
  public static final String PID_QQPIM = "50190";
  public static final String QQBROWSER_DOWNLOAD_URL = "http://mdc.html5.qq.com/mh?channel_id=21380&u=";
  public static final int RESULT_INVALID_CONTEXT = 3;
  public static final int RESULT_INVALID_URL = 2;
  public static final int RESULT_NOT_INSTALL_QQBROWSER = 4;
  public static final int RESULT_OK = 0;
  public static final int RESULT_QQBROWSER_LOW = 5;
  public static final int RESULT_UNKNOWN = 1;
  private static final int SUPPORT_3RD_PARTY_CALL_VERSION = 33;
  private static final int SUPPORT_QB_SCHEME_VERSION = 42;
  private static final int VERSION_420 = 420000;

  private static BrowserPackageInfo chooseClassName(Context paramContext, Uri paramUri)
  {
    Intent localIntent = new Intent("com.tencent.QQBrowser.action.VIEW");
    localIntent.setData(paramUri);
    List localList = paramContext.getPackageManager().queryIntentActivities(localIntent, 0);
    BrowserPackageInfo localBrowserPackageInfo;
    if (localList.size() <= 0)
      localBrowserPackageInfo = null;
    while (true)
    {
      return localBrowserPackageInfo;
      localBrowserPackageInfo = new BrowserPackageInfo(null);
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
        String str = localResolveInfo.activityInfo.packageName;
        if (str.contains("com.tencent.mtt"))
        {
          localBrowserPackageInfo.classname = localResolveInfo.activityInfo.name;
          localBrowserPackageInfo.packagename = localResolveInfo.activityInfo.packageName;
          return localBrowserPackageInfo;
        }
        if (!str.contains("com.tencent.qbx"))
          continue;
        localBrowserPackageInfo.classname = localResolveInfo.activityInfo.name;
        localBrowserPackageInfo.packagename = localResolveInfo.activityInfo.packageName;
      }
    }
  }

  // ERROR //
  public static BrowserInfo getBrowserInfo(Context paramContext)
  {
    // Byte code:
    //   0: new 153	com/tencent/msdk/webview/MttLoader$BrowserInfo
    //   3: dup
    //   4: invokespecial 154	com/tencent/msdk/webview/MttLoader$BrowserInfo:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: invokevirtual 88	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   12: astore_3
    //   13: aconst_null
    //   14: astore 4
    //   16: aload_3
    //   17: ldc 34
    //   19: iconst_0
    //   20: invokevirtual 158	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   23: astore 4
    //   25: aload_1
    //   26: iconst_2
    //   27: putfield 161	com/tencent/msdk/webview/MttLoader$BrowserInfo:browserType	I
    //   30: aload_1
    //   31: ldc 163
    //   33: putfield 166	com/tencent/msdk/webview/MttLoader$BrowserInfo:quahead	Ljava/lang/String;
    //   36: aload 4
    //   38: ifnull +62 -> 100
    //   41: aload 4
    //   43: getfield 171	android/content/pm/PackageInfo:versionCode	I
    //   46: ldc 67
    //   48: if_icmple +52 -> 100
    //   51: aload_1
    //   52: aload 4
    //   54: getfield 171	android/content/pm/PackageInfo:versionCode	I
    //   57: putfield 174	com/tencent/msdk/webview/MttLoader$BrowserInfo:ver	I
    //   60: aload_1
    //   61: new 176	java/lang/StringBuilder
    //   64: dup
    //   65: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   68: aload_1
    //   69: getfield 166	com/tencent/msdk/webview/MttLoader$BrowserInfo:quahead	Ljava/lang/String;
    //   72: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: aload 4
    //   77: getfield 184	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   80: ldc 186
    //   82: ldc 188
    //   84: invokevirtual 192	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   87: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   93: putfield 166	com/tencent/msdk/webview/MttLoader$BrowserInfo:quahead	Ljava/lang/String;
    //   96: aload_1
    //   97: areturn
    //   98: astore 5
    //   100: aload_3
    //   101: ldc 40
    //   103: iconst_0
    //   104: invokevirtual 158	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   107: astore 4
    //   109: aload_1
    //   110: iconst_0
    //   111: putfield 161	com/tencent/msdk/webview/MttLoader$BrowserInfo:browserType	I
    //   114: aload_1
    //   115: ldc 198
    //   117: putfield 166	com/tencent/msdk/webview/MttLoader$BrowserInfo:quahead	Ljava/lang/String;
    //   120: aload 4
    //   122: ifnull -26 -> 96
    //   125: aload_1
    //   126: aload 4
    //   128: getfield 171	android/content/pm/PackageInfo:versionCode	I
    //   131: putfield 174	com/tencent/msdk/webview/MttLoader$BrowserInfo:ver	I
    //   134: aload_1
    //   135: new 176	java/lang/StringBuilder
    //   138: dup
    //   139: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   142: aload_1
    //   143: getfield 166	com/tencent/msdk/webview/MttLoader$BrowserInfo:quahead	Ljava/lang/String;
    //   146: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: aload 4
    //   151: getfield 184	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   154: ldc 186
    //   156: ldc 188
    //   158: invokevirtual 192	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   161: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   167: putfield 166	com/tencent/msdk/webview/MttLoader$BrowserInfo:quahead	Ljava/lang/String;
    //   170: aload_1
    //   171: areturn
    //   172: astore_2
    //   173: aload_1
    //   174: areturn
    //   175: astore 6
    //   177: aload_3
    //   178: ldc 43
    //   180: iconst_0
    //   181: invokevirtual 158	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   184: astore 4
    //   186: aload_1
    //   187: iconst_1
    //   188: putfield 161	com/tencent/msdk/webview/MttLoader$BrowserInfo:browserType	I
    //   191: aload_1
    //   192: ldc 200
    //   194: putfield 166	com/tencent/msdk/webview/MttLoader$BrowserInfo:quahead	Ljava/lang/String;
    //   197: goto -77 -> 120
    //   200: astore 7
    //   202: aload_3
    //   203: ldc 34
    //   205: iconst_0
    //   206: invokevirtual 158	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   209: astore 4
    //   211: aload_1
    //   212: iconst_2
    //   213: putfield 161	com/tencent/msdk/webview/MttLoader$BrowserInfo:browserType	I
    //   216: aload_1
    //   217: ldc 163
    //   219: putfield 166	com/tencent/msdk/webview/MttLoader$BrowserInfo:quahead	Ljava/lang/String;
    //   222: goto -102 -> 120
    //   225: astore 8
    //   227: aload_3
    //   228: ldc 37
    //   230: iconst_0
    //   231: invokevirtual 158	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   234: astore 4
    //   236: aload_1
    //   237: iconst_2
    //   238: putfield 161	com/tencent/msdk/webview/MttLoader$BrowserInfo:browserType	I
    //   241: aload_1
    //   242: ldc 163
    //   244: putfield 166	com/tencent/msdk/webview/MttLoader$BrowserInfo:quahead	Ljava/lang/String;
    //   247: goto -127 -> 120
    //   250: astore 9
    //   252: aload_0
    //   253: ldc 52
    //   255: invokestatic 206	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   258: invokestatic 208	com/tencent/msdk/webview/MttLoader:chooseClassName	(Landroid/content/Context;Landroid/net/Uri;)Lcom/tencent/msdk/webview/MttLoader$BrowserPackageInfo;
    //   261: astore 11
    //   263: aload 11
    //   265: ifnull -145 -> 120
    //   268: aload 11
    //   270: getfield 145	com/tencent/msdk/webview/MttLoader$BrowserPackageInfo:packagename	Ljava/lang/String;
    //   273: invokestatic 213	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   276: ifne -156 -> 120
    //   279: aload_3
    //   280: aload 11
    //   282: getfield 145	com/tencent/msdk/webview/MttLoader$BrowserPackageInfo:packagename	Ljava/lang/String;
    //   285: iconst_0
    //   286: invokevirtual 158	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   289: astore 4
    //   291: aload_1
    //   292: iconst_2
    //   293: putfield 161	com/tencent/msdk/webview/MttLoader$BrowserInfo:browserType	I
    //   296: aload_1
    //   297: ldc 163
    //   299: putfield 166	com/tencent/msdk/webview/MttLoader$BrowserInfo:quahead	Ljava/lang/String;
    //   302: goto -182 -> 120
    //   305: astore 10
    //   307: goto -187 -> 120
    //
    // Exception table:
    //   from	to	target	type
    //   16	36	98	android/content/pm/PackageManager$NameNotFoundException
    //   41	96	98	android/content/pm/PackageManager$NameNotFoundException
    //   8	13	172	java/lang/Exception
    //   16	36	172	java/lang/Exception
    //   41	96	172	java/lang/Exception
    //   100	120	172	java/lang/Exception
    //   125	170	172	java/lang/Exception
    //   177	197	172	java/lang/Exception
    //   202	222	172	java/lang/Exception
    //   100	120	175	android/content/pm/PackageManager$NameNotFoundException
    //   177	197	200	android/content/pm/PackageManager$NameNotFoundException
    //   202	222	225	android/content/pm/PackageManager$NameNotFoundException
    //   227	247	250	java/lang/Exception
    //   252	263	305	java/lang/Exception
    //   268	302	305	java/lang/Exception
  }

  public static String getDownloadUrlWithQb(String paramString)
  {
    try
    {
      String str = "http://mdc.html5.qq.com/mh?channel_id=21380&u=" + URLEncoder.encode(paramString, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return "http://mdc.html5.qq.com/mh?channel_id=21380&u=";
  }

  public static String getValidQBUrl(Context paramContext, String paramString)
  {
    BrowserInfo localBrowserInfo;
    int j;
    if (paramString.toLowerCase().startsWith("qb://"))
    {
      localBrowserInfo = getBrowserInfo(paramContext);
      if (localBrowserInfo.browserType != -1)
        break label40;
      j = 1;
    }
    while (true)
    {
      if (j != 0)
        paramString = getDownloadUrlWithQb(paramString);
      return paramString;
      label40: int i = localBrowserInfo.browserType;
      j = 0;
      if (i != 2)
        continue;
      int k = localBrowserInfo.ver;
      j = 0;
      if (k >= 33)
        continue;
      j = 1;
    }
  }

  private static boolean hasValidProtocal(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    String str;
    int i;
    int j;
    do
    {
      return false;
      str = paramString.trim();
      i = str.toLowerCase().indexOf("://");
      j = str.toLowerCase().indexOf('.');
    }
    while ((i > 0) && (j > 0) && (i > j));
    return str.toLowerCase().contains("://");
  }

  public static boolean isBrowserInstalled(Context paramContext)
  {
    return getBrowserInfo(paramContext).browserType != -1;
  }

  public static boolean isSupportQBScheme(Context paramContext)
  {
    BrowserInfo localBrowserInfo = getBrowserInfo(paramContext);
    if (localBrowserInfo.browserType == -1);
    do
      return false;
    while ((localBrowserInfo.browserType == 2) && (localBrowserInfo.ver < 42));
    return true;
  }

  public static int loadUrl(Activity paramActivity, String paramString, HashMap<String, String> paramHashMap)
  {
    if (paramActivity == null)
      return 3;
    if (!hasValidProtocal(paramString))
      paramString = "http://" + paramString;
    Object localObject;
    BrowserInfo localBrowserInfo;
    try
    {
      localObject = Uri.parse(paramString);
      if (localObject == null)
        return 2;
      if ((((Uri)localObject).getScheme().toLowerCase().equals("qb")) && (!isSupportQBScheme(paramActivity)))
      {
        Uri localUri = Uri.parse("http://mdc.html5.qq.com/mh?channel_id=21380&u=" + URLEncoder.encode(paramString, "UTF-8"));
        localObject = localUri;
      }
      localBrowserInfo = getBrowserInfo(paramActivity);
      if (localBrowserInfo.browserType == -1)
        return 4;
    }
    catch (Exception localException)
    {
      return 2;
    }
    if ((localBrowserInfo.browserType == 2) && (localBrowserInfo.ver < 33))
      return 5;
    Intent localIntent = new Intent("android.intent.action.VIEW");
    if (localBrowserInfo.browserType == 2)
      if ((localBrowserInfo.ver >= 33) && (localBrowserInfo.ver <= 39))
        localIntent.setClassName("com.tencent.mtt", "com.tencent.mtt.MainActivity");
    while (true)
    {
      localIntent.setData((Uri)localObject);
      if (paramHashMap == null)
        break;
      Set localSet = paramHashMap.keySet();
      if (localSet == null)
        break;
      Iterator localIterator = localSet.iterator();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        String str2 = (String)paramHashMap.get(str1);
        if (TextUtils.isEmpty(str2))
          continue;
        localIntent.putExtra(str1, str2);
      }
      if ((localBrowserInfo.ver >= 40) && (localBrowserInfo.ver <= 45))
      {
        localIntent.setClassName("com.tencent.mtt", "com.tencent.mtt.SplashActivity");
        continue;
      }
      if (localBrowserInfo.ver < 46)
        continue;
      localIntent = new Intent("com.tencent.QQBrowser.action.VIEW");
      BrowserPackageInfo localBrowserPackageInfo3 = chooseClassName(paramActivity, (Uri)localObject);
      if ((localBrowserPackageInfo3 == null) || (TextUtils.isEmpty(localBrowserPackageInfo3.classname)))
        continue;
      localIntent.setClassName(localBrowserPackageInfo3.packagename, localBrowserPackageInfo3.classname);
      continue;
      if (localBrowserInfo.browserType == 1)
      {
        if (localBrowserInfo.ver == 1)
        {
          localIntent.setClassName("com.tencent.qbx5", "com.tencent.qbx5.MainActivity");
          continue;
        }
        if (localBrowserInfo.ver != 2)
          continue;
        localIntent.setClassName("com.tencent.qbx5", "com.tencent.qbx5.SplashActivity");
        continue;
      }
      if (localBrowserInfo.browserType == 0)
      {
        if ((localBrowserInfo.ver >= 4) && (localBrowserInfo.ver <= 6))
        {
          localIntent.setClassName("com.tencent.qbx", "com.tencent.qbx.SplashActivity");
          continue;
        }
        if (localBrowserInfo.ver <= 6)
          continue;
        localIntent = new Intent("com.tencent.QQBrowser.action.VIEW");
        BrowserPackageInfo localBrowserPackageInfo2 = chooseClassName(paramActivity, (Uri)localObject);
        if ((localBrowserPackageInfo2 == null) || (TextUtils.isEmpty(localBrowserPackageInfo2.classname)))
          continue;
        localIntent.setClassName(localBrowserPackageInfo2.packagename, localBrowserPackageInfo2.classname);
        continue;
      }
      localIntent = new Intent("com.tencent.QQBrowser.action.VIEW");
      BrowserPackageInfo localBrowserPackageInfo1 = chooseClassName(paramActivity, (Uri)localObject);
      if ((localBrowserPackageInfo1 == null) || (TextUtils.isEmpty(localBrowserPackageInfo1.classname)))
        continue;
      localIntent.setClassName(localBrowserPackageInfo1.packagename, localBrowserPackageInfo1.classname);
    }
    try
    {
      paramActivity.startActivity(localIntent);
      return 0;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
    }
    return 4;
  }

  public static class BrowserInfo
  {
    public int browserType = -1;
    public String quahead = "";
    public int ver = -1;
  }

  private static class BrowserPackageInfo
  {
    public String classname = "";
    public String packagename = "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.webview.MttLoader
 * JD-Core Version:    0.6.0
 */