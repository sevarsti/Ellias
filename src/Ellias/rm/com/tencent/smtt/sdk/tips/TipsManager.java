package com.tencent.smtt.sdk.tips;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class TipsManager
{
  private static final int BROWSER_MTT = 2;
  private static final int BROWSER_NONE = -1;
  private static final int BROWSER_QBX = 0;
  private static final int BROWSER_QBX5 = 1;
  private static final String EXTERNAL_DIR = "QBDownload";
  private static final int INTERVAL_OTHER_NET = 3600000;
  private static final int INTERVAL_WIFI = 600000;
  private static final String MTT_ACTION = "com.tencent.QQBrowser.action.VIEW";
  private static final String MTT_PACKAGE_MTT = "com.tencent.mtt";
  private static final String MTT_PACKAGE_MTT_X86 = "com.tencent.mtt.x86";
  private static final String MTT_PACKAGE_QBX = "com.tencent.qbx";
  private static final String MTT_PACKAGE_QBX5 = "com.tencent.qbx5";
  private static final String QQBROWSER_DOWNLOAD_URL = "http://mdc.html5.qq.com/mh?channel_id=50079&u=";
  private static final String QQB_FILENAME = "qqbrowser.apk";
  private static final int VERSION_420 = 420000;
  private static CompleteReceiver completeReceiver;
  private static DownloadManager dlm;
  private static long downloadId;
  private static boolean isEnabled = true;
  private static long lastReqTime = 0L;

  static
  {
    downloadId = -1L;
    dlm = null;
  }

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

  public static void destroy()
  {
    if (!isEnabled)
      return;
    try
    {
      ContextHolder.getContext().unregisterReceiver(completeReceiver);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }

  // ERROR //
  private static BrowserInfo getBrowserInfo(Context paramContext)
  {
    // Byte code:
    //   0: new 172	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo
    //   3: dup
    //   4: aconst_null
    //   5: invokespecial 173	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:<init>	(Lcom/tencent/smtt/sdk/tips/TipsManager$1;)V
    //   8: astore_1
    //   9: aload_0
    //   10: invokevirtual 89	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: astore_3
    //   14: aconst_null
    //   15: astore 4
    //   17: aload_3
    //   18: ldc 27
    //   20: iconst_0
    //   21: invokevirtual 177	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   24: astore 4
    //   26: aload_1
    //   27: iconst_2
    //   28: putfield 180	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:browserType	I
    //   31: aload_1
    //   32: ldc 182
    //   34: putfield 185	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:quahead	Ljava/lang/String;
    //   37: aload 4
    //   39: ifnull +62 -> 101
    //   42: aload 4
    //   44: getfield 190	android/content/pm/PackageInfo:versionCode	I
    //   47: ldc 44
    //   49: if_icmple +52 -> 101
    //   52: aload_1
    //   53: aload 4
    //   55: getfield 190	android/content/pm/PackageInfo:versionCode	I
    //   58: putfield 193	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:ver	I
    //   61: aload_1
    //   62: new 195	java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial 196	java/lang/StringBuilder:<init>	()V
    //   69: aload_1
    //   70: getfield 185	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:quahead	Ljava/lang/String;
    //   73: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: aload 4
    //   78: getfield 203	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   81: ldc 205
    //   83: ldc 207
    //   85: invokevirtual 211	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   88: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   94: putfield 185	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:quahead	Ljava/lang/String;
    //   97: aload_1
    //   98: areturn
    //   99: astore 5
    //   101: aload_3
    //   102: ldc 33
    //   104: iconst_0
    //   105: invokevirtual 177	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   108: astore 4
    //   110: aload_1
    //   111: iconst_0
    //   112: putfield 180	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:browserType	I
    //   115: aload_1
    //   116: ldc 217
    //   118: putfield 185	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:quahead	Ljava/lang/String;
    //   121: aload 4
    //   123: ifnull -26 -> 97
    //   126: aload_1
    //   127: aload 4
    //   129: getfield 190	android/content/pm/PackageInfo:versionCode	I
    //   132: putfield 193	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:ver	I
    //   135: aload_1
    //   136: new 195	java/lang/StringBuilder
    //   139: dup
    //   140: invokespecial 196	java/lang/StringBuilder:<init>	()V
    //   143: aload_1
    //   144: getfield 185	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:quahead	Ljava/lang/String;
    //   147: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: aload 4
    //   152: getfield 203	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   155: ldc 205
    //   157: ldc 207
    //   159: invokevirtual 211	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   162: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   168: putfield 185	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:quahead	Ljava/lang/String;
    //   171: aload_1
    //   172: areturn
    //   173: astore_2
    //   174: aload_1
    //   175: areturn
    //   176: astore 6
    //   178: aload_3
    //   179: ldc 36
    //   181: iconst_0
    //   182: invokevirtual 177	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   185: astore 4
    //   187: aload_1
    //   188: iconst_1
    //   189: putfield 180	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:browserType	I
    //   192: aload_1
    //   193: ldc 219
    //   195: putfield 185	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:quahead	Ljava/lang/String;
    //   198: goto -77 -> 121
    //   201: astore 7
    //   203: aload_3
    //   204: ldc 27
    //   206: iconst_0
    //   207: invokevirtual 177	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   210: astore 4
    //   212: aload_1
    //   213: iconst_2
    //   214: putfield 180	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:browserType	I
    //   217: aload_1
    //   218: ldc 182
    //   220: putfield 185	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:quahead	Ljava/lang/String;
    //   223: goto -102 -> 121
    //   226: astore 8
    //   228: aload_3
    //   229: ldc 30
    //   231: iconst_0
    //   232: invokevirtual 177	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   235: astore 4
    //   237: aload_1
    //   238: iconst_2
    //   239: putfield 180	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:browserType	I
    //   242: aload_1
    //   243: ldc 182
    //   245: putfield 185	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:quahead	Ljava/lang/String;
    //   248: goto -127 -> 121
    //   251: astore 9
    //   253: aload_0
    //   254: ldc 39
    //   256: invokestatic 225	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   259: invokestatic 227	com/tencent/smtt/sdk/tips/TipsManager:chooseClassName	(Landroid/content/Context;Landroid/net/Uri;)Lcom/tencent/smtt/sdk/tips/TipsManager$BrowserPackageInfo;
    //   262: astore 11
    //   264: aload 11
    //   266: ifnull -145 -> 121
    //   269: aload 11
    //   271: getfield 146	com/tencent/smtt/sdk/tips/TipsManager$BrowserPackageInfo:packagename	Ljava/lang/String;
    //   274: ifnull -153 -> 121
    //   277: aload 11
    //   279: getfield 146	com/tencent/smtt/sdk/tips/TipsManager$BrowserPackageInfo:packagename	Ljava/lang/String;
    //   282: invokevirtual 230	java/lang/String:length	()I
    //   285: ifeq -164 -> 121
    //   288: aload_3
    //   289: aload 11
    //   291: getfield 146	com/tencent/smtt/sdk/tips/TipsManager$BrowserPackageInfo:packagename	Ljava/lang/String;
    //   294: iconst_0
    //   295: invokevirtual 177	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   298: astore 4
    //   300: aload_1
    //   301: iconst_2
    //   302: putfield 180	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:browserType	I
    //   305: aload_1
    //   306: ldc 182
    //   308: putfield 185	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:quahead	Ljava/lang/String;
    //   311: goto -190 -> 121
    //   314: astore 10
    //   316: goto -195 -> 121
    //
    // Exception table:
    //   from	to	target	type
    //   17	37	99	android/content/pm/PackageManager$NameNotFoundException
    //   42	97	99	android/content/pm/PackageManager$NameNotFoundException
    //   9	14	173	java/lang/Exception
    //   17	37	173	java/lang/Exception
    //   42	97	173	java/lang/Exception
    //   101	121	173	java/lang/Exception
    //   126	171	173	java/lang/Exception
    //   178	198	173	java/lang/Exception
    //   203	223	173	java/lang/Exception
    //   101	121	176	android/content/pm/PackageManager$NameNotFoundException
    //   178	198	201	android/content/pm/PackageManager$NameNotFoundException
    //   203	223	226	android/content/pm/PackageManager$NameNotFoundException
    //   228	248	251	java/lang/Exception
    //   253	264	314	java/lang/Exception
    //   269	311	314	java/lang/Exception
  }

  private static void initInstaller()
  {
    try
    {
      completeReceiver = new CompleteReceiver(null);
      ContextHolder.getContext().registerReceiver(completeReceiver, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }

  // ERROR //
  public static void insertRecommand(com.tencent.smtt.sdk.WebView paramWebView)
  {
    // Byte code:
    //   0: getstatic 57	com/tencent/smtt/sdk/tips/TipsManager:isEnabled	Z
    //   3: ifne +4 -> 7
    //   6: return
    //   7: invokestatic 250	com/tencent/smtt/sdk/tips/Apn:isWifiMode	()Z
    //   10: ifeq +326 -> 336
    //   13: ldc2_w 251
    //   16: lstore_1
    //   17: invokestatic 257	java/lang/System:currentTimeMillis	()J
    //   20: lstore_3
    //   21: lload_3
    //   22: getstatic 59	com/tencent/smtt/sdk/tips/TipsManager:lastReqTime	J
    //   25: lsub
    //   26: lload_1
    //   27: lcmp
    //   28: ifge +11 -> 39
    //   31: getstatic 59	com/tencent/smtt/sdk/tips/TipsManager:lastReqTime	J
    //   34: lconst_0
    //   35: lcmp
    //   36: ifne -30 -> 6
    //   39: lload_3
    //   40: putstatic 59	com/tencent/smtt/sdk/tips/TipsManager:lastReqTime	J
    //   43: aload_0
    //   44: invokevirtual 260	com/tencent/smtt/sdk/WebView:getContext	()Landroid/content/Context;
    //   47: astore 6
    //   49: new 262	com/tencent/smtt/sdk/tips/RecommendParams
    //   52: dup
    //   53: invokespecial 263	com/tencent/smtt/sdk/tips/RecommendParams:<init>	()V
    //   56: astore 7
    //   58: aload 6
    //   60: ldc_w 265
    //   63: invokevirtual 269	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   66: checkcast 271	android/telephony/TelephonyManager
    //   69: astore 8
    //   71: aconst_null
    //   72: astore 9
    //   74: aconst_null
    //   75: astore 10
    //   77: aload 8
    //   79: ifnull +21 -> 100
    //   82: aload 8
    //   84: invokevirtual 274	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   87: astore 9
    //   89: aload 8
    //   91: invokevirtual 277	android/telephony/TelephonyManager:getSubscriberId	()Ljava/lang/String;
    //   94: astore 23
    //   96: aload 23
    //   98: astore 10
    //   100: aload 9
    //   102: ifnull +254 -> 356
    //   105: ldc 207
    //   107: aload 9
    //   109: invokevirtual 281	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   112: ifne +244 -> 356
    //   115: aload 7
    //   117: ldc_w 283
    //   120: aload 9
    //   122: invokevirtual 287	com/tencent/smtt/sdk/tips/RecommendParams:put	(Ljava/lang/String;Ljava/lang/String;)Lcom/tencent/smtt/sdk/tips/RecommendParams;
    //   125: pop
    //   126: aload 10
    //   128: ifnull +243 -> 371
    //   131: ldc 207
    //   133: aload 10
    //   135: invokevirtual 281	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   138: ifne +233 -> 371
    //   141: aload 7
    //   143: ldc_w 289
    //   146: aload 10
    //   148: invokevirtual 287	com/tencent/smtt/sdk/tips/RecommendParams:put	(Ljava/lang/String;Ljava/lang/String;)Lcom/tencent/smtt/sdk/tips/RecommendParams;
    //   151: pop
    //   152: aload 7
    //   154: ldc_w 291
    //   157: aload 6
    //   159: invokevirtual 294	android/content/Context:getPackageName	()Ljava/lang/String;
    //   162: invokevirtual 287	com/tencent/smtt/sdk/tips/RecommendParams:put	(Ljava/lang/String;Ljava/lang/String;)Lcom/tencent/smtt/sdk/tips/RecommendParams;
    //   165: pop
    //   166: aload 7
    //   168: ldc_w 296
    //   171: ldc_w 298
    //   174: invokevirtual 287	com/tencent/smtt/sdk/tips/RecommendParams:put	(Ljava/lang/String;Ljava/lang/String;)Lcom/tencent/smtt/sdk/tips/RecommendParams;
    //   177: pop
    //   178: aload 6
    //   180: invokestatic 300	com/tencent/smtt/sdk/tips/TipsManager:getBrowserInfo	(Landroid/content/Context;)Lcom/tencent/smtt/sdk/tips/TipsManager$BrowserInfo;
    //   183: astore 15
    //   185: aload 7
    //   187: ldc_w 302
    //   190: new 195	java/lang/StringBuilder
    //   193: dup
    //   194: invokespecial 196	java/lang/StringBuilder:<init>	()V
    //   197: aload 15
    //   199: getfield 193	com/tencent/smtt/sdk/tips/TipsManager$BrowserInfo:ver	I
    //   202: invokevirtual 305	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   205: ldc 207
    //   207: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   210: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   213: invokevirtual 287	com/tencent/smtt/sdk/tips/RecommendParams:put	(Ljava/lang/String;Ljava/lang/String;)Lcom/tencent/smtt/sdk/tips/RecommendParams;
    //   216: pop
    //   217: aload 6
    //   219: invokestatic 309	com/tencent/smtt/sdk/tips/ContextHolder:setContext	(Landroid/content/Context;)V
    //   222: aload 7
    //   224: ldc_w 311
    //   227: invokestatic 314	com/tencent/smtt/sdk/tips/Apn:getApnTypeS	()I
    //   230: invokestatic 318	com/tencent/smtt/sdk/tips/Apn:getApnName	(I)Ljava/lang/String;
    //   233: invokevirtual 287	com/tencent/smtt/sdk/tips/RecommendParams:put	(Ljava/lang/String;Ljava/lang/String;)Lcom/tencent/smtt/sdk/tips/RecommendParams;
    //   236: pop
    //   237: aload 7
    //   239: ldc_w 320
    //   242: aload_0
    //   243: invokevirtual 323	com/tencent/smtt/sdk/WebView:getUrl	()Ljava/lang/String;
    //   246: invokevirtual 287	com/tencent/smtt/sdk/tips/RecommendParams:put	(Ljava/lang/String;Ljava/lang/String;)Lcom/tencent/smtt/sdk/tips/RecommendParams;
    //   249: pop
    //   250: aload 7
    //   252: ldc_w 325
    //   255: invokevirtual 329	com/tencent/smtt/sdk/tips/RecommendParams:buildUpon	(Ljava/lang/String;)Ljava/lang/String;
    //   258: astore 19
    //   260: aload_0
    //   261: new 195	java/lang/StringBuilder
    //   264: dup
    //   265: invokespecial 196	java/lang/StringBuilder:<init>	()V
    //   268: ldc_w 331
    //   271: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: ldc_w 333
    //   277: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: ldc_w 335
    //   283: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: ldc_w 333
    //   289: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   292: ldc_w 337
    //   295: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: ldc_w 339
    //   301: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   304: ldc_w 341
    //   307: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: aload 19
    //   312: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: ldc_w 343
    //   318: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   324: invokevirtual 346	com/tencent/smtt/sdk/WebView:loadUrl	(Ljava/lang/String;)V
    //   327: return
    //   328: astore 5
    //   330: aload 5
    //   332: invokevirtual 164	java/lang/Throwable:printStackTrace	()V
    //   335: return
    //   336: ldc2_w 347
    //   339: lstore_1
    //   340: goto -323 -> 17
    //   343: astore 22
    //   345: aload 22
    //   347: invokevirtual 349	java/lang/Exception:printStackTrace	()V
    //   350: aconst_null
    //   351: astore 10
    //   353: goto -253 -> 100
    //   356: aload 7
    //   358: ldc_w 283
    //   361: ldc_w 298
    //   364: invokevirtual 287	com/tencent/smtt/sdk/tips/RecommendParams:put	(Ljava/lang/String;Ljava/lang/String;)Lcom/tencent/smtt/sdk/tips/RecommendParams;
    //   367: pop
    //   368: goto -242 -> 126
    //   371: aload 7
    //   373: ldc_w 289
    //   376: ldc_w 298
    //   379: invokevirtual 287	com/tencent/smtt/sdk/tips/RecommendParams:put	(Ljava/lang/String;Ljava/lang/String;)Lcom/tencent/smtt/sdk/tips/RecommendParams;
    //   382: pop
    //   383: goto -231 -> 152
    //
    // Exception table:
    //   from	to	target	type
    //   43	71	328	java/lang/Throwable
    //   82	96	328	java/lang/Throwable
    //   105	126	328	java/lang/Throwable
    //   131	152	328	java/lang/Throwable
    //   152	327	328	java/lang/Throwable
    //   345	350	328	java/lang/Throwable
    //   356	368	328	java/lang/Throwable
    //   371	383	328	java/lang/Throwable
    //   82	96	343	java/lang/Exception
  }

  private static boolean isFolderExist(String paramString)
  {
    File localFile = Environment.getExternalStoragePublicDirectory(paramString);
    if ((localFile.exists()) && (localFile.isDirectory()))
      return true;
    return localFile.mkdirs();
  }

  public static boolean onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    if (!isEnabled)
      return false;
    try
    {
      if ((!TextUtils.isEmpty(paramString1)) && (paramString1.contains("soft.imtt.qq.com/browser/")) && (paramString1.contains(".apk")))
      {
        Context localContext = ContextHolder.getContext();
        if (localContext != null)
          while (true)
          {
            try
            {
              dlm = (DownloadManager)ContextHolder.getContext().getSystemService("download");
              if (dlm == null)
                throw new Exception("can't get DOWNLOAD_SERVICE");
            }
            catch (Throwable localThrowable2)
            {
              localThrowable2.printStackTrace();
              Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString1));
              localIntent.setFlags(268435456);
              ContextHolder.getContext().startActivity(localIntent);
              return true;
            }
            DownloadManager.Request localRequest = new DownloadManager.Request(Uri.parse(paramString1));
            localRequest.setVisibleInDownloadsUi(true);
            localRequest.setNotificationVisibility(1);
            String str = paramString1.substring(paramString1.lastIndexOf("/"), 4 + paramString1.indexOf(".apk"));
            if (str == null)
              str = "qqbrowser.apk";
            if (!isFolderExist("QBDownload"))
              throw new Exception("sdcard can't be wrote");
            localRequest.setDestinationInExternalPublicDir("QBDownload", str);
            downloadId = dlm.enqueue(localRequest);
            initInstaller();
          }
      }
    }
    catch (Throwable localThrowable1)
    {
      localThrowable1.printStackTrace();
    }
    return false;
  }

  public static boolean shouldOverrideUrlLoading(String paramString)
  {
    if (!isEnabled)
      return false;
    int i;
    if (((TextUtils.isEmpty(paramString)) || (!paramString.contains("soft.imtt.qq.com/browser/")) || (!paramString.contains(".apk"))) && (!paramString.contains("http%3A%2F%2Fmdc%2Ehtml5%2Eqq%2Ecom%2Fd%2Fdirectdown%2Ejsp")))
    {
      boolean bool1 = paramString.contains("mdc.html5.qq.com");
      i = 0;
      if (bool1)
      {
        boolean bool2 = paramString.contains("directdown.jsp");
        i = 0;
        if (!bool2);
      }
    }
    else
    {
      i = 1;
    }
    return i;
  }

  private static class BrowserInfo
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

  private static class CompleteReceiver extends BroadcastReceiver
  {
    // ERROR //
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      // Byte code:
      //   0: aload_2
      //   1: invokevirtual 22	android/content/Intent:getAction	()Ljava/lang/String;
      //   4: ldc 24
      //   6: invokevirtual 30	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   9: ifeq +27 -> 36
      //   12: aload_2
      //   13: ldc 32
      //   15: ldc2_w 33
      //   18: invokevirtual 38	android/content/Intent:getLongExtra	(Ljava/lang/String;J)J
      //   21: lstore 4
      //   23: invokestatic 44	com/tencent/smtt/sdk/tips/TipsManager:access$200	()J
      //   26: lstore 6
      //   28: lload 4
      //   30: lload 6
      //   32: lcmp
      //   33: ifeq +4 -> 37
      //   36: return
      //   37: aconst_null
      //   38: astore 8
      //   40: aconst_null
      //   41: astore 9
      //   43: invokestatic 48	com/tencent/smtt/sdk/tips/TipsManager:access$300	()Landroid/app/DownloadManager;
      //   46: lload 4
      //   48: invokevirtual 54	android/app/DownloadManager:getUriForDownloadedFile	(J)Landroid/net/Uri;
      //   51: astore 8
      //   53: aload 8
      //   55: invokevirtual 59	android/net/Uri:toString	()Ljava/lang/String;
      //   58: astore 22
      //   60: aload 22
      //   62: astore 15
      //   64: iconst_0
      //   65: ifeq +9 -> 74
      //   68: aconst_null
      //   69: invokeinterface 64 1 0
      //   74: aload 15
      //   76: invokestatic 70	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   79: ifeq +149 -> 228
      //   82: invokestatic 73	com/tencent/smtt/sdk/tips/TipsManager:destroy	()V
      //   85: return
      //   86: astore_3
      //   87: aload_3
      //   88: invokevirtual 76	java/lang/Throwable:printStackTrace	()V
      //   91: return
      //   92: astore 11
      //   94: new 78	android/app/DownloadManager$Query
      //   97: dup
      //   98: invokespecial 79	android/app/DownloadManager$Query:<init>	()V
      //   101: astore 12
      //   103: aload 12
      //   105: iconst_1
      //   106: newarray long
      //   108: dup
      //   109: iconst_0
      //   110: lload 4
      //   112: lastore
      //   113: invokevirtual 83	android/app/DownloadManager$Query:setFilterById	([J)Landroid/app/DownloadManager$Query;
      //   116: pop
      //   117: invokestatic 48	com/tencent/smtt/sdk/tips/TipsManager:access$300	()Landroid/app/DownloadManager;
      //   120: aload 12
      //   122: invokevirtual 87	android/app/DownloadManager:query	(Landroid/app/DownloadManager$Query;)Landroid/database/Cursor;
      //   125: astore 9
      //   127: aload 9
      //   129: invokeinterface 91 1 0
      //   134: istore 14
      //   136: aconst_null
      //   137: astore 15
      //   139: iload 14
      //   141: ifeq +55 -> 196
      //   144: aload 9
      //   146: ldc 93
      //   148: invokeinterface 97 2 0
      //   153: istore 16
      //   155: iload 16
      //   157: iconst_m1
      //   158: if_icmpne +16 -> 174
      //   161: aload 9
      //   163: ifnull -127 -> 36
      //   166: aload 9
      //   168: invokeinterface 64 1 0
      //   173: return
      //   174: aload 9
      //   176: iload 16
      //   178: invokeinterface 101 2 0
      //   183: astore 15
      //   185: aload 15
      //   187: invokestatic 105	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
      //   190: astore 17
      //   192: aload 17
      //   194: astore 8
      //   196: aload 9
      //   198: ifnull -124 -> 74
      //   201: aload 9
      //   203: invokeinterface 64 1 0
      //   208: goto -134 -> 74
      //   211: astore 10
      //   213: aload 9
      //   215: ifnull +10 -> 225
      //   218: aload 9
      //   220: invokeinterface 64 1 0
      //   225: aload 10
      //   227: athrow
      //   228: aload 15
      //   230: ldc 107
      //   232: invokevirtual 111	java/lang/String:endsWith	(Ljava/lang/String;)Z
      //   235: ifeq +46 -> 281
      //   238: new 18	android/content/Intent
      //   241: dup
      //   242: invokespecial 112	android/content/Intent:<init>	()V
      //   245: astore 18
      //   247: aload 18
      //   249: ldc 114
      //   251: invokevirtual 118	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
      //   254: pop
      //   255: aload 18
      //   257: aload 8
      //   259: ldc 120
      //   261: invokevirtual 124	android/content/Intent:setDataAndType	(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/Intent;
      //   264: pop
      //   265: aload 18
      //   267: ldc 125
      //   269: invokevirtual 129	android/content/Intent:setFlags	(I)Landroid/content/Intent;
      //   272: pop
      //   273: invokestatic 135	com/tencent/smtt/sdk/tips/ContextHolder:getContext	()Landroid/content/Context;
      //   276: aload 18
      //   278: invokevirtual 141	android/content/Context:startActivity	(Landroid/content/Intent;)V
      //   281: invokestatic 73	com/tencent/smtt/sdk/tips/TipsManager:destroy	()V
      //   284: return
      //
      // Exception table:
      //   from	to	target	type
      //   0	28	86	java/lang/Throwable
      //   68	74	86	java/lang/Throwable
      //   74	85	86	java/lang/Throwable
      //   166	173	86	java/lang/Throwable
      //   201	208	86	java/lang/Throwable
      //   218	225	86	java/lang/Throwable
      //   225	228	86	java/lang/Throwable
      //   228	281	86	java/lang/Throwable
      //   281	284	86	java/lang/Throwable
      //   43	60	92	java/lang/NoSuchMethodError
      //   43	60	211	finally
      //   94	136	211	finally
      //   144	155	211	finally
      //   174	192	211	finally
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.tips.TipsManager
 * JD-Core Version:    0.6.0
 */