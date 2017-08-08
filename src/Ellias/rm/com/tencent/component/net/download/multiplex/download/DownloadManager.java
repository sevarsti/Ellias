package com.tencent.component.net.download.multiplex.download;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.tencent.component.ComponentContext;
import com.tencent.component.net.download.multiplex.download.extension.DownFileInfo;
import com.tencent.component.net.download.multiplex.download.extension.FileUtils;
import com.tencent.component.net.download.multiplex.http.Apn;
import com.tencent.component.net.download.multiplex.task.Task;
import com.tencent.component.net.download.multiplex.task.TaskObserver;
import com.tencent.component.utils.log.LogUtil;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

public class DownloadManager
  implements TaskObserver
{
  public static final int a = 1;
  public static final int b = 2;
  public static final String c = "http://disk.html5.qq.com/u?action=fetch";
  private static final String d = "DownloadManager";
  private static final long e = 1000L;
  private static final int f = 8;
  private Timer g;
  private DownloadTaskManager h = new DownloadTaskManager();
  private DownloadDBHelper i = new DownloadDBHelper();
  private List j = new LinkedList();
  private List k = new LinkedList();
  private List l = new LinkedList();
  private List m = new LinkedList();
  private boolean n = true;
  private boolean o = false;
  private Object p = new Object();
  private boolean q = false;
  private boolean r = true;

  private DownloadTask a(Cursor paramCursor)
  {
    if (paramCursor == null)
      return null;
    int i1 = paramCursor.getColumnIndexOrThrow("id");
    int i2 = paramCursor.getColumnIndexOrThrow("status");
    int i3 = paramCursor.getColumnIndexOrThrow("url");
    int i4 = paramCursor.getColumnIndexOrThrow("filename");
    int i5 = paramCursor.getColumnIndexOrThrow("filefolderpath");
    int i6 = paramCursor.getColumnIndexOrThrow("downloadsize");
    int i7 = paramCursor.getColumnIndexOrThrow("totalsize");
    int i8 = paramCursor.getColumnIndexOrThrow("supportresume");
    int i9 = paramCursor.getColumnIndexOrThrow("referer");
    int i10 = paramCursor.getColumnIndexOrThrow("flag");
    int i11 = paramCursor.getColumnIndexOrThrow("costtime");
    int i12 = paramCursor.getColumnIndexOrThrow("createdate");
    int i13 = paramCursor.getColumnIndexOrThrow("etag");
    int i14 = paramCursor.getColumnIndexOrThrow("threadnum");
    int i15 = paramCursor.getColumnIndexOrThrow("annotation");
    int i16 = paramCursor.getColumnIndexOrThrow("annotationext");
    int i17 = paramCursor.getColumnIndexOrThrow("extend_1");
    int i18 = paramCursor.getColumnIndexOrThrow("extend_2");
    int i19 = paramCursor.getInt(i1);
    byte b1 = (byte)paramCursor.getInt(i2);
    String str1 = paramCursor.getString(i3);
    String str2 = paramCursor.getString(i4);
    String str3 = paramCursor.getString(i5);
    long l1 = paramCursor.getLong(i6);
    long l2 = paramCursor.getLong(i7);
    if (paramCursor.getInt(i8) == 1);
    for (boolean bool = true; ; bool = false)
    {
      DownloadTask localDownloadTask = new DownloadTask(i19, b1, str1, str2, str3, l1, l2, bool, paramCursor.getString(i9), paramCursor.getInt(i10), true, paramCursor.getLong(i11), paramCursor.getString(i17));
      localDownloadTask.c(paramCursor.getLong(i12));
      localDownloadTask.f(paramCursor.getString(i13));
      localDownloadTask.b(paramCursor.getInt(i14));
      localDownloadTask.i(paramCursor.getString(i15));
      localDownloadTask.h(paramCursor.getString(i16));
      localDownloadTask.a(paramCursor.getLong(i18));
      return localDownloadTask;
    }
  }

  private ArrayList a(ArrayList paramArrayList, List paramList)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      DownloadTask localDownloadTask = (DownloadTask)localIterator.next();
      if (!paramArrayList.contains(Integer.valueOf(localDownloadTask.E())))
        continue;
      localIterator.remove();
      localArrayList.add(localDownloadTask);
    }
    return localArrayList;
  }

  private void a(Intent paramIntent)
  {
    String str = c(paramIntent);
    Cursor localCursor;
    if (!TextUtils.isEmpty(str))
    {
      LogUtil.d("DownloadManager", "[DownloadManager] install start" + str);
      localCursor = null;
    }
    try
    {
      localCursor = this.i.b(str);
      if ((localCursor != null) && (localCursor.moveToNext()))
      {
        DownloadTask localDownloadTask = a(localCursor);
        if (localDownloadTask != null)
        {
          LogUtil.d("DownloadManager", "[DownloadManager] install" + str);
          localDownloadTask.a(true);
          g(localDownloadTask);
        }
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
    throw localObject;
  }

  private void a(DownloadInfo paramDownloadInfo, boolean paramBoolean, DownloadManager.OnDownloadFeedbackListener paramOnDownloadFeedbackListener)
  {
    if (FileUtils.p() < paramDownloadInfo.i);
    DownloadTask localDownloadTask;
    do
    {
      return;
      if (paramBoolean)
      {
        o(paramDownloadInfo.g);
        return;
      }
      localDownloadTask = l(paramDownloadInfo.g);
    }
    while ((!Apn.g()) || (localDownloadTask != null));
    e(paramDownloadInfo);
  }

  private boolean a(int paramInt, String paramString1, String paramString2)
  {
    File localFile = new File(paramString1);
    if ((localFile.exists()) && (localFile.isDirectory()))
    {
      ArrayList localArrayList = b(paramInt, paramString1, paramString2);
      if (localArrayList != null)
      {
        Iterator localIterator = localArrayList.iterator();
        while (localIterator.hasNext())
          ((File)localIterator.next()).delete();
        LogUtil.d("DownloadManager", "[DownloadManager] deleteM3U8Files,taskId=" + paramInt + ",deleted " + localArrayList.size() + " files");
      }
      localFile.delete();
      return true;
    }
    return false;
  }

  // ERROR //
  private boolean a(DownloadTask paramDownloadTask, boolean paramBoolean1, boolean paramBoolean2)
  {
    // Byte code:
    //   0: ldc 18
    //   2: new 211	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   9: ldc_w 305
    //   12: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: aload_1
    //   16: invokevirtual 307	com/tencent/component/net/download/multiplex/download/DownloadTask:q	()Ljava/lang/String;
    //   19: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   28: aload_1
    //   29: invokevirtual 310	com/tencent/component/net/download/multiplex/download/DownloadTask:t	()Ljava/lang/String;
    //   32: aload_1
    //   33: invokevirtual 307	com/tencent/component/net/download/multiplex/download/DownloadTask:q	()Ljava/lang/String;
    //   36: invokestatic 313	com/tencent/component/net/download/multiplex/download/extension/FileUtils:c	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   39: astore 4
    //   41: aload_1
    //   42: aload 4
    //   44: invokevirtual 315	com/tencent/component/net/download/multiplex/download/DownloadTask:c	(Ljava/lang/String;)V
    //   47: ldc 18
    //   49: new 211	java/lang/StringBuilder
    //   52: dup
    //   53: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   56: ldc_w 317
    //   59: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: aload 4
    //   64: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   73: aload_1
    //   74: invokevirtual 320	com/tencent/component/net/download/multiplex/download/DownloadTask:v	()Ljava/lang/String;
    //   77: astore 5
    //   79: aload_0
    //   80: getfield 71	com/tencent/component/net/download/multiplex/download/DownloadManager:i	Lcom/tencent/component/net/download/multiplex/download/DownloadDBHelper;
    //   83: aload 5
    //   85: invokevirtual 322	com/tencent/component/net/download/multiplex/download/DownloadDBHelper:a	(Ljava/lang/String;)Landroid/database/Cursor;
    //   88: astore 11
    //   90: aload 11
    //   92: astore 9
    //   94: ldc 18
    //   96: new 211	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   103: ldc_w 324
    //   106: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload 9
    //   111: invokevirtual 327	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   114: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   117: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   120: aload 9
    //   122: ifnull +782 -> 904
    //   125: aload 9
    //   127: invokeinterface 330 1 0
    //   132: ifeq +772 -> 904
    //   135: aload 9
    //   137: ldc 88
    //   139: invokeinterface 86 2 0
    //   144: istore 13
    //   146: aload 9
    //   148: iload 13
    //   150: invokeinterface 124 2 0
    //   155: istore 14
    //   157: aload 9
    //   159: ldc 80
    //   161: invokeinterface 86 2 0
    //   166: istore 15
    //   168: aload 9
    //   170: iload 15
    //   172: invokeinterface 124 2 0
    //   177: istore 16
    //   179: ldc 18
    //   181: new 211	java/lang/StringBuilder
    //   184: dup
    //   185: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   188: ldc_w 332
    //   191: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: iload 14
    //   196: invokevirtual 295	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   199: ldc_w 334
    //   202: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: iload 16
    //   207: invokevirtual 295	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   210: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   213: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   216: aload_0
    //   217: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   220: astore 17
    //   222: aload 17
    //   224: monitorenter
    //   225: aload_0
    //   226: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   229: invokeinterface 167 1 0
    //   234: astore 19
    //   236: aload 19
    //   238: invokeinterface 173 1 0
    //   243: istore 20
    //   245: aconst_null
    //   246: astore 21
    //   248: iload 20
    //   250: ifeq +29 -> 279
    //   253: aload 19
    //   255: invokeinterface 177 1 0
    //   260: checkcast 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   263: astore 22
    //   265: aload 22
    //   267: invokevirtual 181	com/tencent/component/net/download/multiplex/download/DownloadTask:E	()I
    //   270: iload 16
    //   272: if_icmpne -36 -> 236
    //   275: aload 22
    //   277: astore 21
    //   279: aload 17
    //   281: monitorexit
    //   282: ldc 18
    //   284: new 211	java/lang/StringBuilder
    //   287: dup
    //   288: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   291: ldc_w 336
    //   294: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: aload 21
    //   299: invokevirtual 327	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   302: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   305: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   308: aload 21
    //   310: ifnull +649 -> 959
    //   313: aload 21
    //   315: invokevirtual 340	com/tencent/component/net/download/multiplex/download/DownloadTask:an	()B
    //   318: istore 23
    //   320: ldc 18
    //   322: new 211	java/lang/StringBuilder
    //   325: dup
    //   326: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   329: ldc_w 342
    //   332: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   335: iload 23
    //   337: invokevirtual 295	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   340: ldc_w 344
    //   343: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: aload 21
    //   348: invokevirtual 347	com/tencent/component/net/download/multiplex/download/DownloadTask:al	()Z
    //   351: invokevirtual 350	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   354: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   357: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   360: iload 23
    //   362: tableswitch	default:+42 -> 404, 0:+610->972, 1:+610->972, 2:+610->972, 3:+100->462, 4:+167->529, 5:+167->529, 6:+167->529
    //   405: istore 24
    //   407: iload 24
    //   409: istore 10
    //   411: aload 9
    //   413: ifnull +10 -> 423
    //   416: aload 9
    //   418: invokeinterface 246 1 0
    //   423: iload 10
    //   425: ireturn
    //   426: astore 18
    //   428: aload 17
    //   430: monitorexit
    //   431: aload 18
    //   433: athrow
    //   434: astore 6
    //   436: aload 9
    //   438: astore 7
    //   440: aload 6
    //   442: invokevirtual 249	java/lang/Exception:printStackTrace	()V
    //   445: iconst_0
    //   446: istore 10
    //   448: aload 7
    //   450: ifnull -27 -> 423
    //   453: aload 7
    //   455: invokeinterface 246 1 0
    //   460: iconst_0
    //   461: ireturn
    //   462: aload 9
    //   464: ldc 94
    //   466: invokeinterface 86 2 0
    //   471: istore 50
    //   473: aload 9
    //   475: iload 50
    //   477: invokeinterface 128 2 0
    //   482: astore 51
    //   484: aload 9
    //   486: ldc 92
    //   488: invokeinterface 86 2 0
    //   493: istore 52
    //   495: new 279	java/io/File
    //   498: dup
    //   499: aload 51
    //   501: aload 9
    //   503: iload 52
    //   505: invokeinterface 128 2 0
    //   510: invokespecial 352	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   513: invokevirtual 284	java/io/File:exists	()Z
    //   516: ifeq -112 -> 404
    //   519: iload_2
    //   520: ifeq -116 -> 404
    //   523: iconst_1
    //   524: istore 24
    //   526: goto -119 -> 407
    //   529: aload 21
    //   531: ifnonnull -127 -> 404
    //   534: aload 9
    //   536: ldc 110
    //   538: invokeinterface 86 2 0
    //   543: istore 25
    //   545: aload 9
    //   547: ldc 112
    //   549: invokeinterface 86 2 0
    //   554: istore 26
    //   556: aload 9
    //   558: ldc 114
    //   560: invokeinterface 86 2 0
    //   565: istore 27
    //   567: aload 9
    //   569: ldc 116
    //   571: invokeinterface 86 2 0
    //   576: istore 28
    //   578: aload 9
    //   580: ldc 118
    //   582: invokeinterface 86 2 0
    //   587: istore 29
    //   589: aload 9
    //   591: ldc 120
    //   593: invokeinterface 86 2 0
    //   598: istore 30
    //   600: aload 9
    //   602: ldc 104
    //   604: invokeinterface 86 2 0
    //   609: pop
    //   610: aload 9
    //   612: ldc 90
    //   614: invokeinterface 86 2 0
    //   619: istore 32
    //   621: aload 9
    //   623: iload 32
    //   625: invokeinterface 128 2 0
    //   630: astore 33
    //   632: aload 9
    //   634: ldc 92
    //   636: invokeinterface 86 2 0
    //   641: istore 34
    //   643: aload 9
    //   645: iload 34
    //   647: invokeinterface 128 2 0
    //   652: astore 35
    //   654: aload 9
    //   656: ldc 94
    //   658: invokeinterface 86 2 0
    //   663: istore 36
    //   665: aload 9
    //   667: iload 36
    //   669: invokeinterface 128 2 0
    //   674: astore 37
    //   676: aload 9
    //   678: ldc 96
    //   680: invokeinterface 86 2 0
    //   685: istore 38
    //   687: aload 9
    //   689: iload 38
    //   691: invokeinterface 132 2 0
    //   696: lstore 39
    //   698: aload 9
    //   700: ldc 98
    //   702: invokeinterface 86 2 0
    //   707: istore 41
    //   709: aload 9
    //   711: iload 41
    //   713: invokeinterface 132 2 0
    //   718: lstore 42
    //   720: aload 9
    //   722: ldc 100
    //   724: invokeinterface 86 2 0
    //   729: istore 44
    //   731: aload 9
    //   733: iload 44
    //   735: invokeinterface 124 2 0
    //   740: iconst_1
    //   741: if_icmpne +225 -> 966
    //   744: iconst_1
    //   745: istore 45
    //   747: aload 9
    //   749: ldc 102
    //   751: invokeinterface 86 2 0
    //   756: istore 46
    //   758: aload 9
    //   760: iload 46
    //   762: invokeinterface 128 2 0
    //   767: astore 47
    //   769: aload 9
    //   771: ldc 104
    //   773: invokeinterface 86 2 0
    //   778: istore 48
    //   780: new 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   783: dup
    //   784: iconst_m1
    //   785: aload 33
    //   787: aload 35
    //   789: aload 37
    //   791: lload 39
    //   793: lload 42
    //   795: iload 45
    //   797: aload 47
    //   799: aload 9
    //   801: iload 48
    //   803: invokeinterface 124 2 0
    //   808: iconst_1
    //   809: invokespecial 355	com/tencent/component/net/download/multiplex/download/DownloadTask:<init>	(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;JJZLjava/lang/String;IZ)V
    //   812: astore 49
    //   814: aload 49
    //   816: aload 9
    //   818: iload 25
    //   820: invokeinterface 128 2 0
    //   825: invokevirtual 143	com/tencent/component/net/download/multiplex/download/DownloadTask:f	(Ljava/lang/String;)V
    //   828: aload 49
    //   830: aload 9
    //   832: iload 26
    //   834: invokeinterface 124 2 0
    //   839: invokevirtual 146	com/tencent/component/net/download/multiplex/download/DownloadTask:b	(I)V
    //   842: aload 49
    //   844: aload 9
    //   846: iload 27
    //   848: invokeinterface 128 2 0
    //   853: invokevirtual 148	com/tencent/component/net/download/multiplex/download/DownloadTask:i	(Ljava/lang/String;)V
    //   856: aload 49
    //   858: aload 9
    //   860: iload 28
    //   862: invokeinterface 128 2 0
    //   867: invokevirtual 150	com/tencent/component/net/download/multiplex/download/DownloadTask:h	(Ljava/lang/String;)V
    //   870: aload 49
    //   872: aload 9
    //   874: iload 29
    //   876: invokeinterface 128 2 0
    //   881: invokevirtual 357	com/tencent/component/net/download/multiplex/download/DownloadTask:a	(Ljava/lang/String;)V
    //   884: aload 49
    //   886: aload 9
    //   888: iload 30
    //   890: invokeinterface 132 2 0
    //   895: invokevirtual 152	com/tencent/component/net/download/multiplex/download/DownloadTask:a	(J)V
    //   898: iconst_0
    //   899: istore 24
    //   901: goto -494 -> 407
    //   904: aload_0
    //   905: aload_1
    //   906: iconst_1
    //   907: invokevirtual 360	com/tencent/component/net/download/multiplex/download/DownloadManager:a	(Lcom/tencent/component/net/download/multiplex/download/DownloadTask;Z)Lcom/tencent/component/net/download/multiplex/download/DownloadTask;
    //   910: pop
    //   911: iconst_0
    //   912: istore 10
    //   914: goto -503 -> 411
    //   917: astore 8
    //   919: aload 9
    //   921: ifnull +10 -> 931
    //   924: aload 9
    //   926: invokeinterface 246 1 0
    //   931: aload 8
    //   933: athrow
    //   934: astore 8
    //   936: aconst_null
    //   937: astore 9
    //   939: goto -20 -> 919
    //   942: astore 8
    //   944: aload 7
    //   946: astore 9
    //   948: goto -29 -> 919
    //   951: astore 6
    //   953: aconst_null
    //   954: astore 7
    //   956: goto -516 -> 440
    //   959: iload 14
    //   961: istore 23
    //   963: goto -603 -> 360
    //   966: iconst_0
    //   967: istore 45
    //   969: goto -222 -> 747
    //   972: iconst_0
    //   973: istore 24
    //   975: goto -568 -> 407
    //
    // Exception table:
    //   from	to	target	type
    //   225	236	426	finally
    //   236	245	426	finally
    //   253	275	426	finally
    //   279	282	426	finally
    //   428	431	426	finally
    //   94	120	434	java/lang/Exception
    //   125	225	434	java/lang/Exception
    //   282	308	434	java/lang/Exception
    //   313	360	434	java/lang/Exception
    //   431	434	434	java/lang/Exception
    //   462	519	434	java/lang/Exception
    //   534	744	434	java/lang/Exception
    //   747	898	434	java/lang/Exception
    //   904	911	434	java/lang/Exception
    //   94	120	917	finally
    //   125	225	917	finally
    //   282	308	917	finally
    //   313	360	917	finally
    //   431	434	917	finally
    //   462	519	917	finally
    //   534	744	917	finally
    //   747	898	917	finally
    //   904	911	917	finally
    //   79	90	934	finally
    //   440	445	942	finally
    //   79	90	951	java/lang/Exception
  }

  private boolean a(Integer paramInteger, List paramList)
  {
    Cursor localCursor = null;
    try
    {
      localCursor = this.i.b(paramInteger.intValue());
      i1 = 0;
      String str1;
      String str2;
      if (localCursor != null)
      {
        boolean bool = localCursor.moveToFirst();
        i1 = 0;
        if (bool)
        {
          int i2 = localCursor.getInt(localCursor.getColumnIndexOrThrow("flag"));
          str1 = localCursor.getString(localCursor.getColumnIndexOrThrow("filefolderpath"));
          str2 = localCursor.getString(localCursor.getColumnIndexOrThrow("filename"));
          if (!DownloadTask.e(i2))
            break label137;
          ArrayList localArrayList = b(paramInteger.intValue(), str1, str2);
          if (localArrayList != null)
            paramList.addAll(localArrayList);
        }
      }
      for (i1 = 1; ; i1 = 0)
      {
        return i1;
        label137: paramList.add(new File(str1, str2));
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      int i1 = 0;
      return false;
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
    throw localObject;
  }

  private String b(Intent paramIntent)
  {
    PackageManager localPackageManager = ComponentContext.a().getPackageManager();
    String str = c(paramIntent);
    LogUtil.d("DownloadManager", "[DownloadManager] get package name from intent:" + str);
    Object localObject1 = "";
    while (true)
    {
      try
      {
        Object localObject2 = PackageManager.class.getMethod("getInstallerPackageName", new Class[] { String.class }).invoke(localPackageManager, new Object[] { str });
        if (localObject2 == null)
          break label171;
        localObject3 = (String)localObject2;
        localObject1 = localObject3;
        LogUtil.d("DownloadManager", "[DownloadManager] get task id from intent:" + (String)localObject1);
        if (!TextUtils.isEmpty((CharSequence)localObject1))
          return localObject1;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        localNoSuchMethodException.printStackTrace();
        continue;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        localIllegalArgumentException.printStackTrace();
        continue;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        localIllegalAccessException.printStackTrace();
        continue;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        localInvocationTargetException.printStackTrace();
        continue;
      }
      return null;
      label171: Object localObject3 = localObject1;
    }
  }

  private ArrayList b(int paramInt, String paramString1, String paramString2)
  {
    File localFile1 = new File(paramString1);
    ArrayList localArrayList;
    File[] arrayOfFile;
    if ((paramString1.startsWith(FileUtils.m())) && (paramString1.length() > 1 + FileUtils.m().length()) && (localFile1.exists()) && (localFile1.isDirectory()))
    {
      localArrayList = new ArrayList();
      arrayOfFile = localFile1.listFiles();
      if (arrayOfFile != null);
    }
    else
    {
      return null;
    }
    int i1 = arrayOfFile.length;
    for (int i2 = 0; i2 < i1; i2++)
    {
      File localFile2 = arrayOfFile[i2];
      if ((localFile2.isDirectory()) || (localFile2.getName().indexOf(paramString2) < 0))
        continue;
      localArrayList.add(localFile2);
      LogUtil.d("DownloadManager", "[DownloadManager] getM3U8DownloadFiles,taskId=" + paramInt + ",add file=" + localFile2.getAbsolutePath());
    }
    return localArrayList;
  }

  private void b(DownloadInfo paramDownloadInfo)
  {
  }

  private void b(ArrayList paramArrayList)
  {
    if ((paramArrayList == null) || (paramArrayList.size() < 1));
    while (true)
    {
      return;
      Iterator localIterator = paramArrayList.iterator();
      while (localIterator.hasNext())
      {
        DownloadTask localDownloadTask = (DownloadTask)localIterator.next();
        this.h.a(localDownloadTask.E());
        localDownloadTask.b(this);
        localDownloadTask.g(true);
        localDownloadTask.ai();
      }
    }
  }

  private DownloadTask c(DownloadInfo paramDownloadInfo)
  {
    if (paramDownloadInfo == null);
    DownloadTask localDownloadTask1;
    DownloadTask localDownloadTask3;
    do
    {
      return null;
      if (paramDownloadInfo.z != 99)
      {
        c(paramDownloadInfo, paramDownloadInfo.v);
        return null;
      }
      if (paramDownloadInfo.p)
        return f(paramDownloadInfo);
      if (paramDownloadInfo.o)
      {
        a(paramDownloadInfo, paramDownloadInfo.v);
        return null;
      }
      if (paramDownloadInfo.n);
      localDownloadTask1 = d(paramDownloadInfo);
      DownloadTask localDownloadTask2 = m(paramDownloadInfo.g);
      if ((localDownloadTask2 != null) && ((!localDownloadTask2.N()) || (paramDownloadInfo.x)))
        a(localDownloadTask2.E(), true);
      String str = paramDownloadInfo.w;
      if (!TextUtils.isEmpty(str))
        localDownloadTask1.i(str);
      localDownloadTask3 = d(localDownloadTask1);
    }
    while (localDownloadTask3 == null);
    if ((localDownloadTask3 != localDownloadTask1) && (localDownloadTask3 != null))
    {
      int i1 = localDownloadTask3.aD;
      if ((i1 == 1) || (i1 == 2) || (i1 == 6) || (i1 == 5) || (i1 == 4))
      {
        b(localDownloadTask3);
        return localDownloadTask3;
      }
      if (i1 == 7)
      {
        localDownloadTask3.ag();
        return null;
      }
    }
    return localDownloadTask3;
  }

  public static File c(DownloadTask paramDownloadTask)
  {
    if (paramDownloadTask == null);
    String str1;
    String str2;
    do
    {
      return null;
      str1 = paramDownloadTask.t();
      str2 = paramDownloadTask.q();
    }
    while ((TextUtils.isEmpty(str1)) || (TextUtils.isEmpty(str2)));
    return new File(str1, str2);
  }

  private String c(Intent paramIntent)
  {
    try
    {
      String str = paramIntent.getDataString().substring(8);
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  private void c(DownloadInfo paramDownloadInfo, DownloadManager.OnDownloadFeedbackListener paramOnDownloadFeedbackListener)
  {
    LogUtil.d("DownloadManager", "[DownloadManager] downloadInfo.isM3U8=" + paramDownloadInfo.z);
    a(new DownloadTask(paramDownloadInfo.g, paramDownloadInfo.h, paramDownloadInfo.k, paramDownloadInfo.i, paramDownloadInfo.j, true), true, false);
  }

  private DownloadTask d(DownloadInfo paramDownloadInfo)
  {
    if (paramDownloadInfo == null)
      return null;
    DownloadTask localDownloadTask = new DownloadTask(-1, paramDownloadInfo.g, paramDownloadInfo.h, paramDownloadInfo.k, 0L, paramDownloadInfo.i, true, paramDownloadInfo.j, paramDownloadInfo.m, false);
    localDownloadTask.i(paramDownloadInfo.t);
    localDownloadTask.h(paramDownloadInfo.u);
    localDownloadTask.a(paramDownloadInfo.y);
    return localDownloadTask;
  }

  private void e(DownloadInfo paramDownloadInfo)
  {
    paramDownloadInfo.C = true;
    if (paramDownloadInfo.z == 0)
    {
      paramDownloadInfo.k = FileUtils.m();
      a(paramDownloadInfo);
      return;
    }
    if (paramDownloadInfo.z == 1)
    {
      paramDownloadInfo.k = (FileUtils.m() + "/" + paramDownloadInfo.h);
      a(paramDownloadInfo);
      return;
    }
    DownloadTask localDownloadTask = new DownloadTask(paramDownloadInfo.g, paramDownloadInfo.h, null, paramDownloadInfo.i, paramDownloadInfo.j, paramDownloadInfo.l);
    if (!TextUtils.isEmpty(paramDownloadInfo.w))
      localDownloadTask.i(paramDownloadInfo.w);
    localDownloadTask.e(true);
    a(localDownloadTask, false, false);
  }

  private void e(DownloadTask paramDownloadTask)
  {
    int i1 = paramDownloadTask.an();
    if (i1 == 3)
      if (!paramDownloadTask.c())
        if (this.n)
          break label70;
    label70: for (int i2 = 0; ; i2 = 1)
    {
      this.n = true;
      if ((i2 != 0) && (paramDownloadTask.k()));
      do
        return;
      while ((i1 != 5) || (paramDownloadTask.c()) || (!this.n) || (e() == 0));
      return;
    }
  }

  private DownloadTask f(DownloadInfo paramDownloadInfo)
  {
    DownloadTask localDownloadTask1 = null;
    if (paramDownloadInfo == null);
    do
    {
      return localDownloadTask1;
      DownloadTask localDownloadTask2 = j(paramDownloadInfo.g);
      if (localDownloadTask2 != null)
      {
        int i1 = localDownloadTask2.aD;
        if ((i1 == 1) || (i1 == 2) || (i1 == 6) || (i1 == 5) || (i1 == 4))
        {
          b(localDownloadTask2);
          return localDownloadTask2;
        }
        if (i1 == 7)
        {
          localDownloadTask2.ag();
          return null;
        }
      }
      DownloadTask localDownloadTask3 = m(paramDownloadInfo.g);
      if (localDownloadTask3 != null)
        a(localDownloadTask3.E(), true);
      DownloadTask localDownloadTask4 = new DownloadTask(paramDownloadInfo.g, paramDownloadInfo.h, paramDownloadInfo.k);
      localDownloadTask4.f(paramDownloadInfo.q);
      if (!TextUtils.isEmpty(paramDownloadInfo.t))
        localDownloadTask4.i(paramDownloadInfo.t);
      String str = paramDownloadInfo.w;
      if (!TextUtils.isEmpty(str))
        localDownloadTask4.i(str);
      localDownloadTask4.d(true);
      localDownloadTask1 = a(localDownloadTask4, true);
    }
    while (localDownloadTask1 == null);
    b(localDownloadTask1);
    return localDownloadTask1;
  }

  // ERROR //
  private DownloadTask f(DownloadTask paramDownloadTask)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 71	com/tencent/component/net/download/multiplex/download/DownloadManager:i	Lcom/tencent/component/net/download/multiplex/download/DownloadDBHelper;
    //   4: aload_1
    //   5: invokevirtual 320	com/tencent/component/net/download/multiplex/download/DownloadTask:v	()Ljava/lang/String;
    //   8: invokevirtual 322	com/tencent/component/net/download/multiplex/download/DownloadDBHelper:a	(Ljava/lang/String;)Landroid/database/Cursor;
    //   11: astore 7
    //   13: aload 7
    //   15: astore_3
    //   16: aconst_null
    //   17: astore 6
    //   19: aload_3
    //   20: ifnull +46 -> 66
    //   23: aload_3
    //   24: invokeinterface 233 1 0
    //   29: istore 8
    //   31: aconst_null
    //   32: astore 6
    //   34: iload 8
    //   36: ifeq +30 -> 66
    //   39: ldc 18
    //   41: ldc_w 577
    //   44: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   47: aload_0
    //   48: aload_1
    //   49: invokevirtual 320	com/tencent/component/net/download/multiplex/download/DownloadTask:v	()Ljava/lang/String;
    //   52: invokevirtual 567	com/tencent/component/net/download/multiplex/download/DownloadManager:j	(Ljava/lang/String;)Lcom/tencent/component/net/download/multiplex/download/DownloadTask;
    //   55: astore 9
    //   57: aload 9
    //   59: ifnull +20 -> 79
    //   62: aload 9
    //   64: astore 6
    //   66: aload_3
    //   67: ifnull +9 -> 76
    //   70: aload_3
    //   71: invokeinterface 246 1 0
    //   76: aload 6
    //   78: areturn
    //   79: aload_0
    //   80: aload_3
    //   81: invokespecial 235	com/tencent/component/net/download/multiplex/download/DownloadManager:a	(Landroid/database/Cursor;)Lcom/tencent/component/net/download/multiplex/download/DownloadTask;
    //   84: astore 10
    //   86: aload 10
    //   88: astore 6
    //   90: goto -24 -> 66
    //   93: astore 5
    //   95: aconst_null
    //   96: astore_3
    //   97: aload 5
    //   99: invokevirtual 249	java/lang/Exception:printStackTrace	()V
    //   102: aconst_null
    //   103: astore 6
    //   105: aload_3
    //   106: ifnull -30 -> 76
    //   109: aload_3
    //   110: invokeinterface 246 1 0
    //   115: aconst_null
    //   116: areturn
    //   117: astore_2
    //   118: aconst_null
    //   119: astore_3
    //   120: aload_2
    //   121: astore 4
    //   123: aload_3
    //   124: ifnull +9 -> 133
    //   127: aload_3
    //   128: invokeinterface 246 1 0
    //   133: aload 4
    //   135: athrow
    //   136: astore 4
    //   138: goto -15 -> 123
    //   141: astore 5
    //   143: goto -46 -> 97
    //
    // Exception table:
    //   from	to	target	type
    //   0	13	93	java/lang/Exception
    //   0	13	117	finally
    //   23	31	136	finally
    //   39	57	136	finally
    //   79	86	136	finally
    //   97	102	136	finally
    //   23	31	141	java/lang/Exception
    //   39	57	141	java/lang/Exception
    //   79	86	141	java/lang/Exception
  }

  private void g(DownloadTask paramDownloadTask)
  {
    a(paramDownloadTask);
    synchronized (this.j)
    {
      Iterator localIterator = this.j.iterator();
      if (localIterator.hasNext())
        ((TaskObserver)localIterator.next()).g(paramDownloadTask);
    }
    monitorexit;
  }

  private void o(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (!paramString.startsWith("http://")));
    DownloadTask localDownloadTask2;
    do
    {
      DownloadTask localDownloadTask1;
      do
      {
        return;
        localDownloadTask1 = new DownloadTask(paramString);
        localDownloadTask1.d(1);
      }
      while (!b(localDownloadTask1, paramString.contains(".apk")));
      localDownloadTask2 = e(paramString);
    }
    while (localDownloadTask2 == null);
    FileUtils.a(ComponentContext.a(), localDownloadTask2.t(), localDownloadTask2.q());
  }

  private void p()
  {
    monitorenter;
    try
    {
      if (this.g == null)
      {
        this.g = new Timer("DownloadManager", true);
        this.g.schedule(new b(this), 1000L, 1000L);
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void q()
  {
    if (!this.h.b())
    {
      LogUtil.d("DownloadManager", "[DownloadManager] Cancel download manager timer.");
      monitorenter;
      try
      {
        if (this.g != null)
        {
          this.g.cancel();
          this.g = null;
        }
        return;
      }
      finally
      {
        monitorexit;
      }
    }
  }

  public DownloadTask a(int paramInt)
  {
    DownloadTask localDownloadTask = this.h.a(paramInt);
    if (localDownloadTask != null);
    synchronized (this.l)
    {
      this.l.remove(localDownloadTask);
      localDownloadTask.b(this);
      this.i.b(localDownloadTask);
      return localDownloadTask;
    }
  }

  public DownloadTask a(DownloadInfo paramDownloadInfo)
  {
    DownloadTask localDownloadTask = null;
    if (paramDownloadInfo == null);
    label103: 
    do
    {
      while (true)
      {
        return localDownloadTask;
        if ((!this.r) || (!Apn.j()) || ((paramDownloadInfo.B != 1) && (paramDownloadInfo.B != 3) && (paramDownloadInfo.B != 2)))
          break label103;
        if (paramDownloadInfo.B != 3)
          break;
        localDownloadTask = c(paramDownloadInfo);
        if (paramDownloadInfo.A == null)
          continue;
        paramDownloadInfo.A.a(localDownloadTask);
        return localDownloadTask;
      }
      new Handler(Looper.getMainLooper()).post(new a(this, paramDownloadInfo));
      return null;
      localDownloadTask = c(paramDownloadInfo);
    }
    while (paramDownloadInfo.A == null);
    paramDownloadInfo.A.a(localDownloadTask);
    return localDownloadTask;
  }

  // ERROR //
  public DownloadTask a(DownloadTask paramDownloadTask, boolean paramBoolean)
  {
    // Byte code:
    //   0: ldc_w 662
    //   3: ldc_w 664
    //   6: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   9: aload_1
    //   10: ifnull +11 -> 21
    //   13: aload_1
    //   14: invokevirtual 181	com/tencent/component/net/download/multiplex/download/DownloadTask:E	()I
    //   17: iconst_m1
    //   18: if_icmpeq +32 -> 50
    //   21: ldc 18
    //   23: new 211	java/lang/StringBuilder
    //   26: dup
    //   27: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   30: ldc_w 666
    //   33: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: aload_1
    //   37: invokevirtual 327	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   40: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   43: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   46: aconst_null
    //   47: astore_1
    //   48: aload_1
    //   49: areturn
    //   50: aload_0
    //   51: aload_1
    //   52: invokespecial 668	com/tencent/component/net/download/multiplex/download/DownloadManager:f	(Lcom/tencent/component/net/download/multiplex/download/DownloadTask;)Lcom/tencent/component/net/download/multiplex/download/DownloadTask;
    //   55: astore_3
    //   56: aload_3
    //   57: ifnull +13 -> 70
    //   60: ldc 18
    //   62: ldc_w 670
    //   65: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   68: aload_3
    //   69: areturn
    //   70: aload_1
    //   71: aload_0
    //   72: invokevirtual 672	com/tencent/component/net/download/multiplex/download/DownloadTask:a	(Lcom/tencent/component/net/download/multiplex/task/TaskObserver;)V
    //   75: aload_0
    //   76: getfield 71	com/tencent/component/net/download/multiplex/download/DownloadManager:i	Lcom/tencent/component/net/download/multiplex/download/DownloadDBHelper;
    //   79: aload_1
    //   80: invokevirtual 674	com/tencent/component/net/download/multiplex/download/DownloadDBHelper:a	(Lcom/tencent/component/net/download/multiplex/download/DownloadTask;)Z
    //   83: pop
    //   84: aload_0
    //   85: getfield 61	com/tencent/component/net/download/multiplex/download/DownloadManager:h	Lcom/tencent/component/net/download/multiplex/download/DownloadTaskManager;
    //   88: aload_1
    //   89: invokevirtual 675	com/tencent/component/net/download/multiplex/download/DownloadTaskManager:a	(Lcom/tencent/component/net/download/multiplex/download/DownloadTask;)V
    //   92: aload_1
    //   93: aload_0
    //   94: getfield 50	com/tencent/component/net/download/multiplex/download/DownloadManager:o	Z
    //   97: invokevirtual 677	com/tencent/component/net/download/multiplex/download/DownloadTask:l	(Z)V
    //   100: aload_1
    //   101: invokevirtual 181	com/tencent/component/net/download/multiplex/download/DownloadTask:E	()I
    //   104: istore 6
    //   106: ldc 18
    //   108: new 211	java/lang/StringBuilder
    //   111: dup
    //   112: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   115: ldc_w 679
    //   118: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: aload_1
    //   122: invokevirtual 347	com/tencent/component/net/download/multiplex/download/DownloadTask:al	()Z
    //   125: invokevirtual 350	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   128: ldc_w 334
    //   131: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: aload_1
    //   135: invokevirtual 181	com/tencent/component/net/download/multiplex/download/DownloadTask:E	()I
    //   138: invokevirtual 295	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   141: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   144: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   147: aload_0
    //   148: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   151: astore 7
    //   153: aload 7
    //   155: monitorenter
    //   156: aload_0
    //   157: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   160: invokeinterface 300 1 0
    //   165: istore 9
    //   167: iconst_0
    //   168: istore 10
    //   170: iload 10
    //   172: iload 9
    //   174: if_icmpge +25 -> 199
    //   177: aload_0
    //   178: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   181: iload 10
    //   183: invokeinterface 683 2 0
    //   188: checkcast 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   191: invokevirtual 181	com/tencent/component/net/download/multiplex/download/DownloadTask:E	()I
    //   194: iload 6
    //   196: if_icmple +50 -> 246
    //   199: ldc 18
    //   201: ldc_w 685
    //   204: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   207: aload_0
    //   208: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   211: iload 10
    //   213: aload_1
    //   214: invokeinterface 688 3 0
    //   219: aload 7
    //   221: monitorexit
    //   222: iload_2
    //   223: ifeq -175 -> 48
    //   226: aload_0
    //   227: invokespecial 690	com/tencent/component/net/download/multiplex/download/DownloadManager:p	()V
    //   230: aload_1
    //   231: areturn
    //   232: astore 4
    //   234: ldc 18
    //   236: ldc_w 692
    //   239: aload 4
    //   241: invokestatic 695	com/tencent/component/utils/log/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   244: aconst_null
    //   245: areturn
    //   246: iinc 10 1
    //   249: goto -79 -> 170
    //   252: astore 8
    //   254: aload 7
    //   256: monitorexit
    //   257: aload 8
    //   259: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   75	84	232	android/database/sqlite/SQLiteException
    //   156	167	252	finally
    //   177	199	252	finally
    //   199	222	252	finally
    //   254	257	252	finally
  }

  public DownloadTask a(String paramString1, TaskObserver paramTaskObserver, String paramString2, boolean paramBoolean)
  {
    this.n = paramBoolean;
    String str2;
    String str1;
    if (paramString2 != null)
    {
      int i1 = paramString2.lastIndexOf('/');
      if (i1 != -1)
      {
        str2 = paramString2.substring(0, i1 + 1);
        str1 = paramString2.substring(i1 + 1, paramString2.length());
        LogUtil.d("DownloadManager", "[DownloadManager] path : " + str2);
        LogUtil.d("DownloadManager", "[DownloadManager] fileName : " + str1);
      }
    }
    while (true)
    {
      DownloadTask localDownloadTask = new DownloadTask(paramString1, str2);
      localDownloadTask.a(paramTaskObserver);
      localDownloadTask.a(this);
      localDownloadTask.f(false);
      localDownloadTask.c(str1);
      Thread localThread = new Thread(localDownloadTask, "download_startTask2");
      localThread.setPriority(1);
      localThread.start();
      p();
      return localDownloadTask;
      str1 = null;
      str2 = null;
    }
  }

  public DownloadTask a(String paramString1, String paramString2, long paramLong, TaskObserver paramTaskObserver, String paramString3, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.n = paramBoolean1;
    DownloadTask localDownloadTask = new DownloadTask(paramString1, paramString2, paramString3, paramLong, null, paramBoolean2);
    localDownloadTask.a(paramTaskObserver);
    localDownloadTask.a(this);
    localDownloadTask.c(paramString2);
    localDownloadTask.f(false);
    new Thread(localDownloadTask, "download_startImageAttachmentTask").start();
    p();
    return localDownloadTask;
  }

  public DownloadTask a(String paramString1, String paramString2, TaskObserver paramTaskObserver)
  {
    DownloadTask localDownloadTask = new DownloadTask(paramString1, paramString2);
    localDownloadTask.a(paramTaskObserver);
    localDownloadTask.a(this);
    Thread localThread = new Thread(localDownloadTask, "download_startTask");
    localThread.setPriority(1);
    localThread.start();
    p();
    return localDownloadTask;
  }

  public DownloadTask a(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    boolean bool = TextUtils.isEmpty(paramString1);
    DownloadTask localDownloadTask1 = null;
    if (bool);
    do
    {
      return localDownloadTask1;
      DownloadTask localDownloadTask2 = j(paramString1);
      if (localDownloadTask2 == null)
        localDownloadTask2 = m(paramString1);
      if (localDownloadTask2 != null)
      {
        File localFile = new File(FileUtils.l(), localDownloadTask2.q());
        if ((localFile != null) && (localFile.exists()))
        {
          int i1 = localDownloadTask2.aD;
          if ((i1 == 1) || (i1 == 2) || (i1 == 6) || (i1 == 5) || (i1 == 4))
          {
            b(localDownloadTask2);
            return localDownloadTask2;
          }
          if (i1 == 3)
          {
            FileUtils.a(ComponentContext.a(), localFile.getParent(), localFile.getName());
            return null;
          }
        }
      }
      if (localDownloadTask2 != null)
        a(localDownloadTask2.E(), true);
      DownloadTask localDownloadTask3 = new DownloadTask(paramString1, paramString2, paramString3);
      localDownloadTask3.f(paramBoolean);
      localDownloadTask1 = a(localDownloadTask3, true);
    }
    while (localDownloadTask1 == null);
    b(localDownloadTask1);
    return localDownloadTask1;
  }

  public DownloadTask a(String paramString1, String paramString2, boolean paramBoolean)
  {
    return a(paramString1, paramString2, null, paramBoolean);
  }

  public Task a(String paramString, TaskObserver paramTaskObserver)
  {
    DownloadTask localDownloadTask = new DownloadTask(paramString);
    localDownloadTask.a(paramTaskObserver);
    localDownloadTask.a(this);
    Thread localThread = new Thread(localDownloadTask, "download_startTask");
    localThread.setPriority(1);
    localThread.start();
    p();
    return localDownloadTask;
  }

  // ERROR //
  public ArrayList a(ArrayList paramArrayList)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 71	com/tencent/component/net/download/multiplex/download/DownloadManager:i	Lcom/tencent/component/net/download/multiplex/download/DownloadDBHelper;
    //   4: invokevirtual 746	com/tencent/component/net/download/multiplex/download/DownloadDBHelper:e	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore_2
    //   8: aload_2
    //   9: ifnonnull +13 -> 22
    //   12: ldc 18
    //   14: ldc_w 748
    //   17: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   20: aconst_null
    //   21: areturn
    //   22: new 160	java/util/ArrayList
    //   25: dup
    //   26: invokespecial 161	java/util/ArrayList:<init>	()V
    //   29: astore_3
    //   30: aload_2
    //   31: invokevirtual 753	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   34: aload_1
    //   35: invokevirtual 452	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   38: astore 20
    //   40: aload 20
    //   42: invokeinterface 173 1 0
    //   47: ifeq +225 -> 272
    //   50: aload 20
    //   52: invokeinterface 177 1 0
    //   57: checkcast 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   60: astore 22
    //   62: aload 22
    //   64: ifnull +12 -> 76
    //   67: aload 22
    //   69: invokevirtual 181	com/tencent/component/net/download/multiplex/download/DownloadTask:E	()I
    //   72: iconst_m1
    //   73: if_icmpeq +121 -> 194
    //   76: ldc 18
    //   78: new 211	java/lang/StringBuilder
    //   81: dup
    //   82: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   85: ldc_w 666
    //   88: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: aload 22
    //   93: invokevirtual 327	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   96: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   102: aload 20
    //   104: invokeinterface 194 1 0
    //   109: goto -69 -> 40
    //   112: astore 6
    //   114: aload 6
    //   116: invokevirtual 249	java/lang/Exception:printStackTrace	()V
    //   119: aload_2
    //   120: ifnull +348 -> 468
    //   123: aload_2
    //   124: ifnull +344 -> 468
    //   127: aload_2
    //   128: invokevirtual 756	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   131: iconst_0
    //   132: istore 7
    //   134: iload 7
    //   136: ifeq +316 -> 452
    //   139: aload_1
    //   140: invokevirtual 452	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   143: astore 8
    //   145: aload 8
    //   147: invokeinterface 173 1 0
    //   152: ifeq +142 -> 294
    //   155: aload 8
    //   157: invokeinterface 177 1 0
    //   162: checkcast 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   165: astore 18
    //   167: aload 18
    //   169: aload_0
    //   170: invokevirtual 672	com/tencent/component/net/download/multiplex/download/DownloadTask:a	(Lcom/tencent/component/net/download/multiplex/task/TaskObserver;)V
    //   173: aload_0
    //   174: getfield 61	com/tencent/component/net/download/multiplex/download/DownloadManager:h	Lcom/tencent/component/net/download/multiplex/download/DownloadTaskManager;
    //   177: aload 18
    //   179: invokevirtual 675	com/tencent/component/net/download/multiplex/download/DownloadTaskManager:a	(Lcom/tencent/component/net/download/multiplex/download/DownloadTask;)V
    //   182: aload 18
    //   184: aload_0
    //   185: getfield 50	com/tencent/component/net/download/multiplex/download/DownloadManager:o	Z
    //   188: invokevirtual 677	com/tencent/component/net/download/multiplex/download/DownloadTask:l	(Z)V
    //   191: goto -46 -> 145
    //   194: aload_0
    //   195: aload 22
    //   197: invokespecial 668	com/tencent/component/net/download/multiplex/download/DownloadManager:f	(Lcom/tencent/component/net/download/multiplex/download/DownloadTask;)Lcom/tencent/component/net/download/multiplex/download/DownloadTask;
    //   200: astore 23
    //   202: aload 23
    //   204: ifnull +37 -> 241
    //   207: aload_3
    //   208: aload 23
    //   210: invokevirtual 197	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   213: pop
    //   214: aload 20
    //   216: invokeinterface 194 1 0
    //   221: goto -181 -> 40
    //   224: astore 4
    //   226: aload_2
    //   227: ifnull +11 -> 238
    //   230: aload_2
    //   231: ifnull +7 -> 238
    //   234: aload_2
    //   235: invokevirtual 756	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   238: aload 4
    //   240: athrow
    //   241: aload 22
    //   243: aload 22
    //   245: invokevirtual 310	com/tencent/component/net/download/multiplex/download/DownloadTask:t	()Ljava/lang/String;
    //   248: aload 22
    //   250: invokevirtual 307	com/tencent/component/net/download/multiplex/download/DownloadTask:q	()Ljava/lang/String;
    //   253: invokestatic 313	com/tencent/component/net/download/multiplex/download/extension/FileUtils:c	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   256: invokevirtual 315	com/tencent/component/net/download/multiplex/download/DownloadTask:c	(Ljava/lang/String;)V
    //   259: aload_0
    //   260: getfield 71	com/tencent/component/net/download/multiplex/download/DownloadManager:i	Lcom/tencent/component/net/download/multiplex/download/DownloadDBHelper;
    //   263: aload 22
    //   265: invokevirtual 674	com/tencent/component/net/download/multiplex/download/DownloadDBHelper:a	(Lcom/tencent/component/net/download/multiplex/download/DownloadTask;)Z
    //   268: pop
    //   269: goto -229 -> 40
    //   272: aload_2
    //   273: invokevirtual 759	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   276: aload_2
    //   277: ifnull +197 -> 474
    //   280: aload_2
    //   281: ifnull +193 -> 474
    //   284: aload_2
    //   285: invokevirtual 756	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   288: iconst_1
    //   289: istore 7
    //   291: goto -157 -> 134
    //   294: aload_0
    //   295: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   298: astore 9
    //   300: aload 9
    //   302: monitorenter
    //   303: aload_1
    //   304: invokevirtual 452	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   307: astore 11
    //   309: aload 11
    //   311: invokeinterface 173 1 0
    //   316: ifeq +99 -> 415
    //   319: aload 11
    //   321: invokeinterface 177 1 0
    //   326: checkcast 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   329: astore 14
    //   331: aload 14
    //   333: invokevirtual 181	com/tencent/component/net/download/multiplex/download/DownloadTask:E	()I
    //   336: istore 15
    //   338: aload_0
    //   339: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   342: invokeinterface 300 1 0
    //   347: istore 16
    //   349: iconst_0
    //   350: istore 17
    //   352: iload 17
    //   354: iload 16
    //   356: if_icmpge +25 -> 381
    //   359: aload_0
    //   360: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   363: iload 17
    //   365: invokeinterface 683 2 0
    //   370: checkcast 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   373: invokevirtual 181	com/tencent/component/net/download/multiplex/download/DownloadTask:E	()I
    //   376: iload 15
    //   378: if_icmple +31 -> 409
    //   381: aload_0
    //   382: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   385: iload 17
    //   387: aload 14
    //   389: invokeinterface 688 3 0
    //   394: aload_0
    //   395: invokespecial 690	com/tencent/component/net/download/multiplex/download/DownloadManager:p	()V
    //   398: goto -89 -> 309
    //   401: astore 10
    //   403: aload 9
    //   405: monitorexit
    //   406: aload 10
    //   408: athrow
    //   409: iinc 17 1
    //   412: goto -60 -> 352
    //   415: aload 9
    //   417: monitorexit
    //   418: aload_3
    //   419: invokevirtual 452	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   422: astore 12
    //   424: aload 12
    //   426: invokeinterface 173 1 0
    //   431: ifeq +23 -> 454
    //   434: aload_1
    //   435: aload 12
    //   437: invokeinterface 177 1 0
    //   442: checkcast 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   445: invokevirtual 197	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   448: pop
    //   449: goto -25 -> 424
    //   452: aconst_null
    //   453: astore_1
    //   454: aload_1
    //   455: areturn
    //   456: astore 21
    //   458: aconst_null
    //   459: areturn
    //   460: astore 19
    //   462: aconst_null
    //   463: areturn
    //   464: astore 5
    //   466: aconst_null
    //   467: areturn
    //   468: iconst_0
    //   469: istore 7
    //   471: goto -337 -> 134
    //   474: iconst_1
    //   475: istore 7
    //   477: goto -343 -> 134
    //
    // Exception table:
    //   from	to	target	type
    //   30	40	112	java/lang/Exception
    //   40	62	112	java/lang/Exception
    //   67	76	112	java/lang/Exception
    //   76	109	112	java/lang/Exception
    //   194	202	112	java/lang/Exception
    //   207	221	112	java/lang/Exception
    //   241	269	112	java/lang/Exception
    //   272	276	112	java/lang/Exception
    //   30	40	224	finally
    //   40	62	224	finally
    //   67	76	224	finally
    //   76	109	224	finally
    //   114	119	224	finally
    //   194	202	224	finally
    //   207	221	224	finally
    //   241	269	224	finally
    //   272	276	224	finally
    //   303	309	401	finally
    //   309	349	401	finally
    //   359	381	401	finally
    //   381	398	401	finally
    //   403	406	401	finally
    //   415	418	401	finally
    //   284	288	456	java/lang/IllegalStateException
    //   127	131	460	java/lang/IllegalStateException
    //   234	238	464	java/lang/IllegalStateException
  }

  public void a()
  {
    LogUtil.d("DownloadManager", "begin init");
    Cursor localCursor;
    synchronized (this.p)
    {
      if (this.q)
      {
        LogUtil.d("DownloadManager", "already init and return");
        return;
      }
      this.i.a();
      localCursor = null;
    }
    try
    {
      localCursor = this.i.b();
      if (localCursor != null)
        while (localCursor.moveToNext())
        {
          DownloadTask localDownloadTask = a(localCursor);
          if (localDownloadTask.aD == 7)
            localDownloadTask.aD = 6;
          this.m.add(localDownloadTask);
        }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      if (localCursor != null)
        localCursor.close();
      while (true)
      {
        this.q = true;
        i();
        LogUtil.d("DownloadManager", "end init");
        monitorexit;
        return;
        localObject2 = finally;
        monitorexit;
        throw localObject2;
        if (localCursor == null)
          continue;
        localCursor.close();
      }
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
    throw localObject3;
  }

  public void a(DownloadInfo paramDownloadInfo, DownloadManager.OnDownloadFeedbackListener paramOnDownloadFeedbackListener)
  {
    b(paramDownloadInfo, paramOnDownloadFeedbackListener);
  }

  public void a(DownloadManager.OnDownloadedTaskListener paramOnDownloadedTaskListener)
  {
    synchronized (this.k)
    {
      if (!this.k.contains(paramOnDownloadedTaskListener))
        this.k.add(paramOnDownloadedTaskListener);
      return;
    }
  }

  public void a(DownloadTask paramDownloadTask)
  {
    if (this.i != null)
    {
      monitorenter;
      try
      {
        this.i.b(paramDownloadTask);
        return;
      }
      finally
      {
        monitorexit;
      }
    }
  }

  public void a(Task paramTask)
  {
    synchronized (this.j)
    {
      Iterator localIterator = this.j.iterator();
      if (localIterator.hasNext())
        ((TaskObserver)localIterator.next()).a(paramTask);
    }
    monitorexit;
    DownloadTask localDownloadTask = (DownloadTask)paramTask;
    int i1 = 1;
    if (this.h.c(localDownloadTask.E()) == null)
      i1 = 0;
    if ((!localDownloadTask.c()) && (i1 == 0) && ((0x2 & localDownloadTask.K()) == 0));
  }

  public void a(TaskObserver paramTaskObserver)
  {
    synchronized (this.j)
    {
      if (!this.j.contains(paramTaskObserver))
        this.j.add(paramTaskObserver);
      return;
    }
  }

  public void a(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    while (true)
    {
      return;
      synchronized (this.m)
      {
        Iterator localIterator = this.m.iterator();
        while (localIterator.hasNext())
        {
          DownloadTask localDownloadTask2 = (DownloadTask)localIterator.next();
          if (!paramString.equalsIgnoreCase(localDownloadTask2.v()))
            continue;
          localDownloadTask2.e(false);
          e(localDownloadTask2);
        }
        DownloadTask localDownloadTask1 = l(paramString);
        if ((localDownloadTask1 == null) || (!localDownloadTask1.d()))
          continue;
        localDownloadTask1.e(false);
        a(localDownloadTask1);
        e(localDownloadTask1);
        return;
      }
    }
  }

  public void a(ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    long l1 = 0L;
    for (int i1 = 0; i1 < paramArrayList1.size(); i1++)
      l1 += ((DownFileInfo)paramArrayList1.get(i1)).c;
    if (FileUtils.p() < l1);
  }

  public void a(ArrayList paramArrayList, boolean paramBoolean, DownloadManager.FileDeletedListener paramFileDeletedListener)
  {
    a(paramArrayList, paramBoolean, paramFileDeletedListener, null);
  }

  // ERROR //
  public void a(ArrayList paramArrayList1, boolean paramBoolean, DownloadManager.FileDeletedListener paramFileDeletedListener, ArrayList paramArrayList2)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 5
    //   3: ldc 18
    //   5: new 211	java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   12: ldc_w 802
    //   15: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: iload_2
    //   19: invokevirtual 350	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   22: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   28: new 804	java/util/HashMap
    //   31: dup
    //   32: invokespecial 805	java/util/HashMap:<init>	()V
    //   35: astore 6
    //   37: aload_0
    //   38: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   41: astore 7
    //   43: aload 7
    //   45: monitorenter
    //   46: aload_0
    //   47: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   50: invokeinterface 167 1 0
    //   55: astore 9
    //   57: aload 9
    //   59: invokeinterface 173 1 0
    //   64: ifeq +119 -> 183
    //   67: aload 9
    //   69: invokeinterface 177 1 0
    //   74: checkcast 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   77: astore 36
    //   79: aload 36
    //   81: ifnull -24 -> 57
    //   84: aload_1
    //   85: aload 36
    //   87: invokevirtual 181	com/tencent/component/net/download/multiplex/download/DownloadTask:E	()I
    //   90: invokestatic 187	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   93: invokevirtual 191	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   96: ifeq -39 -> 57
    //   99: new 183	java/lang/Integer
    //   102: dup
    //   103: aload 36
    //   105: invokevirtual 181	com/tencent/component/net/download/multiplex/download/DownloadTask:E	()I
    //   108: invokespecial 807	java/lang/Integer:<init>	(I)V
    //   111: astore 37
    //   113: new 257	com/tencent/component/net/download/multiplex/download/DownloadInfo
    //   116: dup
    //   117: invokespecial 808	com/tencent/component/net/download/multiplex/download/DownloadInfo:<init>	()V
    //   120: astore 38
    //   122: aload 38
    //   124: aload 36
    //   126: invokevirtual 320	com/tencent/component/net/download/multiplex/download/DownloadTask:v	()Ljava/lang/String;
    //   129: putfield 261	com/tencent/component/net/download/multiplex/download/DownloadInfo:g	Ljava/lang/String;
    //   132: aload 38
    //   134: aload 36
    //   136: invokevirtual 811	com/tencent/component/net/download/multiplex/download/DownloadTask:ak	()Ljava/lang/String;
    //   139: putfield 539	com/tencent/component/net/download/multiplex/download/DownloadInfo:t	Ljava/lang/String;
    //   142: aload 38
    //   144: aload 36
    //   146: invokevirtual 783	com/tencent/component/net/download/multiplex/download/DownloadTask:K	()I
    //   149: putfield 537	com/tencent/component/net/download/multiplex/download/DownloadInfo:m	I
    //   152: aload 38
    //   154: aload 36
    //   156: invokevirtual 814	com/tencent/component/net/download/multiplex/download/DownloadTask:G	()J
    //   159: putfield 816	com/tencent/component/net/download/multiplex/download/DownloadInfo:r	J
    //   162: aload 6
    //   164: aload 37
    //   166: aload 38
    //   168: invokevirtual 820	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   171: pop
    //   172: goto -115 -> 57
    //   175: astore 8
    //   177: aload 7
    //   179: monitorexit
    //   180: aload 8
    //   182: athrow
    //   183: aload_0
    //   184: aload_1
    //   185: aload_0
    //   186: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   189: invokespecial 822	com/tencent/component/net/download/multiplex/download/DownloadManager:a	(Ljava/util/ArrayList;Ljava/util/List;)Ljava/util/ArrayList;
    //   192: astore 10
    //   194: aload 7
    //   196: monitorexit
    //   197: aload_0
    //   198: aload 10
    //   200: invokespecial 824	com/tencent/component/net/download/multiplex/download/DownloadManager:b	(Ljava/util/ArrayList;)V
    //   203: aload_0
    //   204: getfield 66	com/tencent/component/net/download/multiplex/download/DownloadManager:l	Ljava/util/List;
    //   207: astore 11
    //   209: aload 11
    //   211: monitorenter
    //   212: aload_0
    //   213: aload_1
    //   214: aload_0
    //   215: getfield 66	com/tencent/component/net/download/multiplex/download/DownloadManager:l	Ljava/util/List;
    //   218: invokespecial 822	com/tencent/component/net/download/multiplex/download/DownloadManager:a	(Ljava/util/ArrayList;Ljava/util/List;)Ljava/util/ArrayList;
    //   221: astore 13
    //   223: aload 11
    //   225: monitorexit
    //   226: aload_0
    //   227: aload 13
    //   229: invokespecial 824	com/tencent/component/net/download/multiplex/download/DownloadManager:b	(Ljava/util/ArrayList;)V
    //   232: aload_0
    //   233: getfield 71	com/tencent/component/net/download/multiplex/download/DownloadManager:i	Lcom/tencent/component/net/download/multiplex/download/DownloadDBHelper;
    //   236: invokevirtual 746	com/tencent/component/net/download/multiplex/download/DownloadDBHelper:e	()Landroid/database/sqlite/SQLiteDatabase;
    //   239: astore 14
    //   241: aload 14
    //   243: ifnonnull +20 -> 263
    //   246: ldc 18
    //   248: ldc_w 826
    //   251: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   254: return
    //   255: astore 12
    //   257: aload 11
    //   259: monitorexit
    //   260: aload 12
    //   262: athrow
    //   263: aload 14
    //   265: invokevirtual 753	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   268: iload_2
    //   269: ifeq +309 -> 578
    //   272: new 160	java/util/ArrayList
    //   275: dup
    //   276: invokespecial 161	java/util/ArrayList:<init>	()V
    //   279: astore 25
    //   281: aload 4
    //   283: ifnull +370 -> 653
    //   286: aload 25
    //   288: aload 4
    //   290: invokevirtual 827	java/util/ArrayList:addAll	(Ljava/util/Collection;)Z
    //   293: pop
    //   294: aload_1
    //   295: invokevirtual 452	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   298: astore 27
    //   300: aload 27
    //   302: invokeinterface 173 1 0
    //   307: ifeq +257 -> 564
    //   310: aload 27
    //   312: invokeinterface 177 1 0
    //   317: checkcast 183	java/lang/Integer
    //   320: astore 32
    //   322: new 160	java/util/ArrayList
    //   325: dup
    //   326: invokespecial 161	java/util/ArrayList:<init>	()V
    //   329: astore 33
    //   331: aload_0
    //   332: aload 32
    //   334: invokevirtual 364	java/lang/Integer:intValue	()I
    //   337: invokevirtual 829	com/tencent/component/net/download/multiplex/download/DownloadManager:f	(I)Lcom/tencent/component/net/download/multiplex/download/DownloadTask;
    //   340: astore 34
    //   342: aload 34
    //   344: ifnull -44 -> 300
    //   347: aload 34
    //   349: invokevirtual 347	com/tencent/component/net/download/multiplex/download/DownloadTask:al	()Z
    //   352: ifeq +181 -> 533
    //   355: new 709	java/lang/Thread
    //   358: dup
    //   359: new 831	com/tencent/component/net/download/multiplex/download/c
    //   362: dup
    //   363: aload_0
    //   364: aload 32
    //   366: aload 34
    //   368: aload_3
    //   369: invokespecial 834	com/tencent/component/net/download/multiplex/download/c:<init>	(Lcom/tencent/component/net/download/multiplex/download/DownloadManager;Ljava/lang/Integer;Lcom/tencent/component/net/download/multiplex/download/DownloadTask;Lcom/tencent/component/net/download/multiplex/download/DownloadManager$FileDeletedListener;)V
    //   372: invokespecial 837	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   375: invokevirtual 720	java/lang/Thread:start	()V
    //   378: goto -78 -> 300
    //   381: astore 17
    //   383: aload 17
    //   385: invokevirtual 249	java/lang/Exception:printStackTrace	()V
    //   388: aload 14
    //   390: ifnull +8 -> 398
    //   393: aload 14
    //   395: invokevirtual 756	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   398: aload_0
    //   399: getfield 75	com/tencent/component/net/download/multiplex/download/DownloadManager:k	Ljava/util/List;
    //   402: astore 18
    //   404: aload 18
    //   406: monitorenter
    //   407: aload_1
    //   408: invokevirtual 452	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   411: astore 20
    //   413: aload 20
    //   415: invokeinterface 173 1 0
    //   420: ifeq +223 -> 643
    //   423: aload 20
    //   425: invokeinterface 177 1 0
    //   430: checkcast 183	java/lang/Integer
    //   433: astore 21
    //   435: aload 6
    //   437: aload 21
    //   439: invokevirtual 840	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   442: checkcast 257	com/tencent/component/net/download/multiplex/download/DownloadInfo
    //   445: astore 22
    //   447: aload 22
    //   449: ifnonnull +28 -> 477
    //   452: new 257	com/tencent/component/net/download/multiplex/download/DownloadInfo
    //   455: dup
    //   456: invokespecial 808	com/tencent/component/net/download/multiplex/download/DownloadInfo:<init>	()V
    //   459: astore 22
    //   461: aload 22
    //   463: aload 21
    //   465: invokevirtual 364	java/lang/Integer:intValue	()I
    //   468: putfield 842	com/tencent/component/net/download/multiplex/download/DownloadInfo:f	I
    //   471: aload 22
    //   473: iconst_1
    //   474: putfield 844	com/tencent/component/net/download/multiplex/download/DownloadInfo:d	Z
    //   477: aload 22
    //   479: bipush 8
    //   481: putfield 846	com/tencent/component/net/download/multiplex/download/DownloadInfo:e	B
    //   484: aload_0
    //   485: getfield 75	com/tencent/component/net/download/multiplex/download/DownloadManager:k	Ljava/util/List;
    //   488: invokeinterface 167 1 0
    //   493: astore 23
    //   495: aload 23
    //   497: invokeinterface 173 1 0
    //   502: ifeq -89 -> 413
    //   505: aload 23
    //   507: invokeinterface 177 1 0
    //   512: checkcast 848	com/tencent/component/net/download/multiplex/download/DownloadManager$OnDownloadedTaskListener
    //   515: aload 22
    //   517: invokeinterface 850 2 0
    //   522: goto -27 -> 495
    //   525: astore 19
    //   527: aload 18
    //   529: monitorexit
    //   530: aload 19
    //   532: athrow
    //   533: iload 5
    //   535: ifne -235 -> 300
    //   538: aload 25
    //   540: aload 33
    //   542: invokevirtual 827	java/util/ArrayList:addAll	(Ljava/util/Collection;)Z
    //   545: pop
    //   546: goto -246 -> 300
    //   549: astore 15
    //   551: aload 14
    //   553: ifnull +8 -> 561
    //   556: aload 14
    //   558: invokevirtual 756	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   561: aload 15
    //   563: athrow
    //   564: new 852	com/tencent/component/net/download/multiplex/download/g
    //   567: dup
    //   568: aload_0
    //   569: aload 25
    //   571: aload_3
    //   572: invokespecial 855	com/tencent/component/net/download/multiplex/download/g:<init>	(Lcom/tencent/component/net/download/multiplex/download/DownloadManager;Ljava/util/ArrayList;Lcom/tencent/component/net/download/multiplex/download/DownloadManager$FileDeletedListener;)V
    //   575: invokevirtual 856	com/tencent/component/net/download/multiplex/download/g:a	()V
    //   578: aload_1
    //   579: invokevirtual 452	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   582: astore 28
    //   584: aload 28
    //   586: invokeinterface 173 1 0
    //   591: ifeq +31 -> 622
    //   594: aload 28
    //   596: invokeinterface 177 1 0
    //   601: checkcast 183	java/lang/Integer
    //   604: astore 30
    //   606: aload_0
    //   607: getfield 71	com/tencent/component/net/download/multiplex/download/DownloadManager:i	Lcom/tencent/component/net/download/multiplex/download/DownloadDBHelper;
    //   610: aload 30
    //   612: invokevirtual 364	java/lang/Integer:intValue	()I
    //   615: invokevirtual 858	com/tencent/component/net/download/multiplex/download/DownloadDBHelper:a	(I)Z
    //   618: pop
    //   619: goto -35 -> 584
    //   622: aload 14
    //   624: invokevirtual 759	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   627: aload 14
    //   629: ifnull -231 -> 398
    //   632: aload 14
    //   634: invokevirtual 756	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   637: goto -239 -> 398
    //   640: astore 29
    //   642: return
    //   643: aload 18
    //   645: monitorexit
    //   646: return
    //   647: astore 24
    //   649: return
    //   650: astore 16
    //   652: return
    //   653: iconst_0
    //   654: istore 5
    //   656: goto -362 -> 294
    //
    // Exception table:
    //   from	to	target	type
    //   46	57	175	finally
    //   57	79	175	finally
    //   84	172	175	finally
    //   177	180	175	finally
    //   183	197	175	finally
    //   212	226	255	finally
    //   257	260	255	finally
    //   263	268	381	java/lang/Exception
    //   272	281	381	java/lang/Exception
    //   286	294	381	java/lang/Exception
    //   294	300	381	java/lang/Exception
    //   300	342	381	java/lang/Exception
    //   347	378	381	java/lang/Exception
    //   538	546	381	java/lang/Exception
    //   564	578	381	java/lang/Exception
    //   578	584	381	java/lang/Exception
    //   584	619	381	java/lang/Exception
    //   622	627	381	java/lang/Exception
    //   407	413	525	finally
    //   413	447	525	finally
    //   452	477	525	finally
    //   477	495	525	finally
    //   495	522	525	finally
    //   527	530	525	finally
    //   643	646	525	finally
    //   263	268	549	finally
    //   272	281	549	finally
    //   286	294	549	finally
    //   294	300	549	finally
    //   300	342	549	finally
    //   347	378	549	finally
    //   383	388	549	finally
    //   538	546	549	finally
    //   564	578	549	finally
    //   578	584	549	finally
    //   584	619	549	finally
    //   622	627	549	finally
    //   632	637	640	java/lang/IllegalStateException
    //   393	398	647	java/lang/IllegalStateException
    //   556	561	650	java/lang/IllegalStateException
  }

  public void a(boolean paramBoolean)
  {
    this.o = paramBoolean;
    synchronized (this.m)
    {
      Iterator localIterator = this.m.iterator();
      if (localIterator.hasNext())
        ((DownloadTask)localIterator.next()).l(paramBoolean);
    }
    monitorexit;
  }

  // ERROR //
  public boolean a(int paramInt, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: iconst_1
    //   3: istore 4
    //   5: iconst_0
    //   6: istore 5
    //   8: ldc 18
    //   10: new 211	java/lang/StringBuilder
    //   13: dup
    //   14: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   17: ldc_w 860
    //   20: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: iload_2
    //   24: invokevirtual 350	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   27: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   33: aload_0
    //   34: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   37: astore 6
    //   39: aload 6
    //   41: monitorenter
    //   42: aload_0
    //   43: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   46: invokeinterface 167 1 0
    //   51: astore 8
    //   53: aload 8
    //   55: invokeinterface 173 1 0
    //   60: ifeq +835 -> 895
    //   63: aload 8
    //   65: invokeinterface 177 1 0
    //   70: checkcast 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   73: astore 36
    //   75: aload 36
    //   77: invokevirtual 181	com/tencent/component/net/download/multiplex/download/DownloadTask:E	()I
    //   80: iload_1
    //   81: if_icmpne -28 -> 53
    //   84: aload 36
    //   86: astore 9
    //   88: aload 6
    //   90: monitorexit
    //   91: new 257	com/tencent/component/net/download/multiplex/download/DownloadInfo
    //   94: dup
    //   95: invokespecial 808	com/tencent/component/net/download/multiplex/download/DownloadInfo:<init>	()V
    //   98: astore 10
    //   100: aload 10
    //   102: iload_1
    //   103: putfield 842	com/tencent/component/net/download/multiplex/download/DownloadInfo:f	I
    //   106: aload 10
    //   108: bipush 8
    //   110: putfield 846	com/tencent/component/net/download/multiplex/download/DownloadInfo:e	B
    //   113: aload 9
    //   115: ifnull +95 -> 210
    //   118: aload 9
    //   120: invokevirtual 340	com/tencent/component/net/download/multiplex/download/DownloadTask:an	()B
    //   123: iconst_3
    //   124: if_icmpne +432 -> 556
    //   127: iload 4
    //   129: istore 35
    //   131: aload 10
    //   133: iload 35
    //   135: putfield 844	com/tencent/component/net/download/multiplex/download/DownloadInfo:d	Z
    //   138: aload 9
    //   140: iload 4
    //   142: invokevirtual 460	com/tencent/component/net/download/multiplex/download/DownloadTask:g	(Z)V
    //   145: aload 9
    //   147: invokevirtual 463	com/tencent/component/net/download/multiplex/download/DownloadTask:ai	()V
    //   150: aload 10
    //   152: aload 9
    //   154: invokevirtual 783	com/tencent/component/net/download/multiplex/download/DownloadTask:K	()I
    //   157: putfield 537	com/tencent/component/net/download/multiplex/download/DownloadInfo:m	I
    //   160: aload 10
    //   162: aload 9
    //   164: invokevirtual 811	com/tencent/component/net/download/multiplex/download/DownloadTask:ak	()Ljava/lang/String;
    //   167: putfield 539	com/tencent/component/net/download/multiplex/download/DownloadInfo:t	Ljava/lang/String;
    //   170: aload 10
    //   172: aload 9
    //   174: invokevirtual 310	com/tencent/component/net/download/multiplex/download/DownloadTask:t	()Ljava/lang/String;
    //   177: putfield 528	com/tencent/component/net/download/multiplex/download/DownloadInfo:k	Ljava/lang/String;
    //   180: aload 10
    //   182: aload 9
    //   184: invokevirtual 307	com/tencent/component/net/download/multiplex/download/DownloadTask:q	()Ljava/lang/String;
    //   187: putfield 526	com/tencent/component/net/download/multiplex/download/DownloadInfo:h	Ljava/lang/String;
    //   190: aload 10
    //   192: aload 9
    //   194: invokevirtual 320	com/tencent/component/net/download/multiplex/download/DownloadTask:v	()Ljava/lang/String;
    //   197: putfield 261	com/tencent/component/net/download/multiplex/download/DownloadInfo:g	Ljava/lang/String;
    //   200: aload 10
    //   202: aload 9
    //   204: invokevirtual 814	com/tencent/component/net/download/multiplex/download/DownloadTask:G	()J
    //   207: putfield 816	com/tencent/component/net/download/multiplex/download/DownloadInfo:r	J
    //   210: aload_0
    //   211: iload_1
    //   212: invokevirtual 861	com/tencent/component/net/download/multiplex/download/DownloadManager:a	(I)Lcom/tencent/component/net/download/multiplex/download/DownloadTask;
    //   215: astore 11
    //   217: iload_2
    //   218: ifeq +671 -> 889
    //   221: aload 11
    //   223: ifnull +352 -> 575
    //   226: ldc 18
    //   228: ldc_w 863
    //   231: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   234: aload 11
    //   236: invokevirtual 310	com/tencent/component/net/download/multiplex/download/DownloadTask:t	()Ljava/lang/String;
    //   239: astore 33
    //   241: aload 11
    //   243: invokevirtual 307	com/tencent/component/net/download/multiplex/download/DownloadTask:q	()Ljava/lang/String;
    //   246: astore 34
    //   248: aload 34
    //   250: aload 33
    //   252: invokestatic 865	com/tencent/component/net/download/multiplex/download/extension/FileUtils:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   255: aload 11
    //   257: invokevirtual 347	com/tencent/component/net/download/multiplex/download/DownloadTask:al	()Z
    //   260: ifeq +302 -> 562
    //   263: new 709	java/lang/Thread
    //   266: dup
    //   267: new 867	com/tencent/component/net/download/multiplex/download/d
    //   270: dup
    //   271: aload_0
    //   272: aload 11
    //   274: aload 33
    //   276: aload 34
    //   278: invokespecial 870	com/tencent/component/net/download/multiplex/download/d:<init>	(Lcom/tencent/component/net/download/multiplex/download/DownloadManager;Lcom/tencent/component/net/download/multiplex/download/DownloadTask;Ljava/lang/String;Ljava/lang/String;)V
    //   281: invokespecial 837	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   284: invokevirtual 720	java/lang/Thread:start	()V
    //   287: ldc 18
    //   289: new 211	java/lang/StringBuilder
    //   292: dup
    //   293: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   296: ldc_w 872
    //   299: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   302: aload 33
    //   304: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   307: ldc_w 874
    //   310: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   313: ldc_w 876
    //   316: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   319: aload 34
    //   321: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: ldc_w 874
    //   327: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: ldc_w 878
    //   333: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   336: iload 4
    //   338: invokevirtual 350	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   341: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   344: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   347: aload_0
    //   348: getfield 71	com/tencent/component/net/download/multiplex/download/DownloadManager:i	Lcom/tencent/component/net/download/multiplex/download/DownloadDBHelper;
    //   351: iload_1
    //   352: invokevirtual 858	com/tencent/component/net/download/multiplex/download/DownloadDBHelper:a	(I)Z
    //   355: pop
    //   356: aload_0
    //   357: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   360: astore 13
    //   362: aload 13
    //   364: monitorenter
    //   365: aload_0
    //   366: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   369: invokeinterface 300 1 0
    //   374: istore 15
    //   376: iconst_0
    //   377: istore 16
    //   379: iload 16
    //   381: iload 15
    //   383: if_icmpge +36 -> 419
    //   386: aload_0
    //   387: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   390: iload 16
    //   392: invokeinterface 683 2 0
    //   397: checkcast 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   400: invokevirtual 181	com/tencent/component/net/download/multiplex/download/DownloadTask:E	()I
    //   403: iload_1
    //   404: if_icmpne +440 -> 844
    //   407: aload_0
    //   408: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   411: iload 16
    //   413: invokeinterface 880 2 0
    //   418: pop
    //   419: aload 13
    //   421: monitorexit
    //   422: aload_0
    //   423: getfield 66	com/tencent/component/net/download/multiplex/download/DownloadManager:l	Ljava/util/List;
    //   426: astore 18
    //   428: aload 18
    //   430: monitorenter
    //   431: aload_0
    //   432: getfield 66	com/tencent/component/net/download/multiplex/download/DownloadManager:l	Ljava/util/List;
    //   435: invokeinterface 300 1 0
    //   440: istore 20
    //   442: iload 5
    //   444: iload 20
    //   446: if_icmpge +36 -> 482
    //   449: aload_0
    //   450: getfield 66	com/tencent/component/net/download/multiplex/download/DownloadManager:l	Ljava/util/List;
    //   453: iload 5
    //   455: invokeinterface 683 2 0
    //   460: checkcast 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   463: invokevirtual 181	com/tencent/component/net/download/multiplex/download/DownloadTask:E	()I
    //   466: iload_1
    //   467: if_icmpne +391 -> 858
    //   470: aload_0
    //   471: getfield 66	com/tencent/component/net/download/multiplex/download/DownloadManager:l	Ljava/util/List;
    //   474: iload 5
    //   476: invokeinterface 880 2 0
    //   481: pop
    //   482: aload 18
    //   484: monitorexit
    //   485: aload 10
    //   487: ifnull +388 -> 875
    //   490: aload_0
    //   491: getfield 75	com/tencent/component/net/download/multiplex/download/DownloadManager:k	Ljava/util/List;
    //   494: astore 22
    //   496: aload 22
    //   498: monitorenter
    //   499: aload_0
    //   500: getfield 75	com/tencent/component/net/download/multiplex/download/DownloadManager:k	Ljava/util/List;
    //   503: invokeinterface 167 1 0
    //   508: astore 24
    //   510: aload 24
    //   512: invokeinterface 173 1 0
    //   517: ifeq +355 -> 872
    //   520: aload 24
    //   522: invokeinterface 177 1 0
    //   527: checkcast 848	com/tencent/component/net/download/multiplex/download/DownloadManager$OnDownloadedTaskListener
    //   530: aload 10
    //   532: invokeinterface 850 2 0
    //   537: goto -27 -> 510
    //   540: astore 23
    //   542: aload 22
    //   544: monitorexit
    //   545: aload 23
    //   547: athrow
    //   548: astore 7
    //   550: aload 6
    //   552: monitorexit
    //   553: aload 7
    //   555: athrow
    //   556: iconst_0
    //   557: istore 35
    //   559: goto -428 -> 131
    //   562: aload_0
    //   563: aload 33
    //   565: aload 34
    //   567: invokevirtual 883	com/tencent/component/net/download/multiplex/download/DownloadManager:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   570: istore 4
    //   572: goto -285 -> 287
    //   575: ldc 18
    //   577: ldc_w 885
    //   580: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   583: aload_0
    //   584: getfield 71	com/tencent/component/net/download/multiplex/download/DownloadManager:i	Lcom/tencent/component/net/download/multiplex/download/DownloadDBHelper;
    //   587: iload_1
    //   588: invokevirtual 367	com/tencent/component/net/download/multiplex/download/DownloadDBHelper:b	(I)Landroid/database/Cursor;
    //   591: astore_3
    //   592: aload_3
    //   593: ifnull +290 -> 883
    //   596: aload_3
    //   597: invokeinterface 330 1 0
    //   602: ifeq +281 -> 883
    //   605: aload_3
    //   606: aload_3
    //   607: ldc 80
    //   609: invokeinterface 86 2 0
    //   614: invokeinterface 124 2 0
    //   619: istore 27
    //   621: aload_3
    //   622: aload_3
    //   623: ldc 104
    //   625: invokeinterface 86 2 0
    //   630: invokeinterface 124 2 0
    //   635: istore 28
    //   637: aload_3
    //   638: aload_3
    //   639: ldc 94
    //   641: invokeinterface 86 2 0
    //   646: invokeinterface 128 2 0
    //   651: astore 29
    //   653: aload_3
    //   654: aload_3
    //   655: ldc 92
    //   657: invokeinterface 86 2 0
    //   662: invokeinterface 128 2 0
    //   667: astore 30
    //   669: aload_3
    //   670: aload_3
    //   671: ldc 90
    //   673: invokeinterface 86 2 0
    //   678: invokeinterface 128 2 0
    //   683: pop
    //   684: iload 28
    //   686: invokestatic 370	com/tencent/component/net/download/multiplex/download/DownloadTask:e	(I)Z
    //   689: ifeq +100 -> 789
    //   692: new 709	java/lang/Thread
    //   695: dup
    //   696: new 887	com/tencent/component/net/download/multiplex/download/e
    //   699: dup
    //   700: aload_0
    //   701: iload 27
    //   703: aload 29
    //   705: aload 30
    //   707: invokespecial 890	com/tencent/component/net/download/multiplex/download/e:<init>	(Lcom/tencent/component/net/download/multiplex/download/DownloadManager;ILjava/lang/String;Ljava/lang/String;)V
    //   710: invokespecial 837	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   713: invokevirtual 720	java/lang/Thread:start	()V
    //   716: ldc 18
    //   718: new 211	java/lang/StringBuilder
    //   721: dup
    //   722: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   725: ldc_w 872
    //   728: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   731: aload 29
    //   733: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   736: ldc_w 874
    //   739: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   742: ldc_w 876
    //   745: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   748: aload 30
    //   750: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   753: ldc_w 874
    //   756: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   759: ldc_w 878
    //   762: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   765: iload 4
    //   767: invokevirtual 350	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   770: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   773: invokestatic 227	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   776: aload_3
    //   777: ifnull -430 -> 347
    //   780: aload_3
    //   781: invokeinterface 246 1 0
    //   786: goto -439 -> 347
    //   789: aload_0
    //   790: aload 29
    //   792: aload 30
    //   794: invokevirtual 883	com/tencent/component/net/download/multiplex/download/DownloadManager:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   797: istore 32
    //   799: iload 32
    //   801: istore 4
    //   803: goto -87 -> 716
    //   806: astore 26
    //   808: iconst_0
    //   809: istore 4
    //   811: aload 26
    //   813: invokevirtual 249	java/lang/Exception:printStackTrace	()V
    //   816: aload_3
    //   817: ifnull -470 -> 347
    //   820: aload_3
    //   821: invokeinterface 246 1 0
    //   826: goto -479 -> 347
    //   829: astore 25
    //   831: aload_3
    //   832: ifnull +9 -> 841
    //   835: aload_3
    //   836: invokeinterface 246 1 0
    //   841: aload 25
    //   843: athrow
    //   844: iinc 16 1
    //   847: goto -468 -> 379
    //   850: astore 14
    //   852: aload 13
    //   854: monitorexit
    //   855: aload 14
    //   857: athrow
    //   858: iinc 5 1
    //   861: goto -419 -> 442
    //   864: astore 19
    //   866: aload 18
    //   868: monitorexit
    //   869: aload 19
    //   871: athrow
    //   872: aload 22
    //   874: monitorexit
    //   875: iload 4
    //   877: ireturn
    //   878: astore 26
    //   880: goto -69 -> 811
    //   883: iconst_0
    //   884: istore 4
    //   886: goto -110 -> 776
    //   889: iconst_0
    //   890: istore 4
    //   892: goto -545 -> 347
    //   895: aconst_null
    //   896: astore 9
    //   898: goto -810 -> 88
    //
    // Exception table:
    //   from	to	target	type
    //   499	510	540	finally
    //   510	537	540	finally
    //   542	545	540	finally
    //   872	875	540	finally
    //   42	53	548	finally
    //   53	84	548	finally
    //   88	91	548	finally
    //   550	553	548	finally
    //   583	592	806	java/lang/Exception
    //   596	716	806	java/lang/Exception
    //   789	799	806	java/lang/Exception
    //   583	592	829	finally
    //   596	716	829	finally
    //   716	776	829	finally
    //   789	799	829	finally
    //   811	816	829	finally
    //   365	376	850	finally
    //   386	419	850	finally
    //   419	422	850	finally
    //   852	855	850	finally
    //   431	442	864	finally
    //   449	482	864	finally
    //   482	485	864	finally
    //   866	869	864	finally
    //   716	776	878	java/lang/Exception
  }

  public boolean a(String paramString1, String paramString2)
  {
    try
    {
      File localFile1 = new File(paramString1, "." + paramString2 + ".dltmp");
      if ((localFile1 != null) && (localFile1.exists()))
      {
        localFile1.delete();
        File localFile3 = new File(paramString1, paramString2 + ".qbdltmp");
        if (localFile3.exists())
          localFile3.delete();
      }
      File localFile2 = new File(paramString1, paramString2);
      if (localFile2.exists())
      {
        boolean bool = localFile2.delete();
        return bool;
      }
      return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  // ERROR //
  public DownloadTask b(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: iload_1
    //   2: invokevirtual 861	com/tencent/component/net/download/multiplex/download/DownloadManager:a	(I)Lcom/tencent/component/net/download/multiplex/download/DownloadTask;
    //   5: pop
    //   6: aconst_null
    //   7: astore_3
    //   8: aload_0
    //   9: getfield 71	com/tencent/component/net/download/multiplex/download/DownloadManager:i	Lcom/tencent/component/net/download/multiplex/download/DownloadDBHelper;
    //   12: iload_1
    //   13: invokevirtual 367	com/tencent/component/net/download/multiplex/download/DownloadDBHelper:b	(I)Landroid/database/Cursor;
    //   16: astore 20
    //   18: aload 20
    //   20: astore 5
    //   22: aload 5
    //   24: invokeinterface 330 1 0
    //   29: ifeq +628 -> 657
    //   32: aload 5
    //   34: aload 5
    //   36: ldc 92
    //   38: invokeinterface 86 2 0
    //   43: invokeinterface 128 2 0
    //   48: astore 21
    //   50: aload 5
    //   52: aload 5
    //   54: ldc 94
    //   56: invokeinterface 86 2 0
    //   61: invokeinterface 128 2 0
    //   66: astore 22
    //   68: aload 5
    //   70: aload 5
    //   72: ldc 90
    //   74: invokeinterface 86 2 0
    //   79: invokeinterface 128 2 0
    //   84: pop
    //   85: aload_0
    //   86: aload 22
    //   88: aload 21
    //   90: invokevirtual 883	com/tencent/component/net/download/multiplex/download/DownloadManager:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   93: pop
    //   94: aload 5
    //   96: ldc 110
    //   98: invokeinterface 86 2 0
    //   103: istore 25
    //   105: aload 5
    //   107: ldc 112
    //   109: invokeinterface 86 2 0
    //   114: istore 26
    //   116: aload 5
    //   118: ldc 114
    //   120: invokeinterface 86 2 0
    //   125: istore 27
    //   127: aload 5
    //   129: ldc 116
    //   131: invokeinterface 86 2 0
    //   136: istore 28
    //   138: aload 5
    //   140: ldc 118
    //   142: invokeinterface 86 2 0
    //   147: istore 29
    //   149: aload 5
    //   151: ldc 120
    //   153: invokeinterface 86 2 0
    //   158: istore 30
    //   160: aload 5
    //   162: ldc 104
    //   164: invokeinterface 86 2 0
    //   169: pop
    //   170: aload 5
    //   172: aload 5
    //   174: ldc 90
    //   176: invokeinterface 86 2 0
    //   181: invokeinterface 128 2 0
    //   186: astore 32
    //   188: aload 5
    //   190: aload 5
    //   192: ldc 98
    //   194: invokeinterface 86 2 0
    //   199: invokeinterface 132 2 0
    //   204: lstore 33
    //   206: aload 5
    //   208: aload 5
    //   210: ldc 100
    //   212: invokeinterface 86 2 0
    //   217: invokeinterface 124 2 0
    //   222: iconst_1
    //   223: if_icmpne +317 -> 540
    //   226: iconst_1
    //   227: istore 35
    //   229: new 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   232: dup
    //   233: iconst_m1
    //   234: aload 32
    //   236: aload 21
    //   238: aload 22
    //   240: lconst_0
    //   241: lload 33
    //   243: iload 35
    //   245: aload 5
    //   247: aload 5
    //   249: ldc 102
    //   251: invokeinterface 86 2 0
    //   256: invokeinterface 128 2 0
    //   261: aload 5
    //   263: aload 5
    //   265: ldc 104
    //   267: invokeinterface 86 2 0
    //   272: invokeinterface 124 2 0
    //   277: iconst_0
    //   278: invokespecial 355	com/tencent/component/net/download/multiplex/download/DownloadTask:<init>	(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;JJZLjava/lang/String;IZ)V
    //   281: astore 36
    //   283: aload 36
    //   285: aload 5
    //   287: iload 25
    //   289: invokeinterface 128 2 0
    //   294: invokevirtual 143	com/tencent/component/net/download/multiplex/download/DownloadTask:f	(Ljava/lang/String;)V
    //   297: aload 36
    //   299: aload 5
    //   301: iload 26
    //   303: invokeinterface 124 2 0
    //   308: invokevirtual 146	com/tencent/component/net/download/multiplex/download/DownloadTask:b	(I)V
    //   311: aload 36
    //   313: aload 5
    //   315: iload 27
    //   317: invokeinterface 128 2 0
    //   322: invokevirtual 148	com/tencent/component/net/download/multiplex/download/DownloadTask:i	(Ljava/lang/String;)V
    //   325: aload 36
    //   327: aload 5
    //   329: iload 28
    //   331: invokeinterface 128 2 0
    //   336: invokevirtual 150	com/tencent/component/net/download/multiplex/download/DownloadTask:h	(Ljava/lang/String;)V
    //   339: aload 36
    //   341: aload 5
    //   343: iload 29
    //   345: invokeinterface 128 2 0
    //   350: invokevirtual 357	com/tencent/component/net/download/multiplex/download/DownloadTask:a	(Ljava/lang/String;)V
    //   353: aload 36
    //   355: aload 5
    //   357: iload 30
    //   359: invokeinterface 132 2 0
    //   364: invokevirtual 152	com/tencent/component/net/download/multiplex/download/DownloadTask:a	(J)V
    //   367: aload 36
    //   369: astore 7
    //   371: aload 5
    //   373: ifnull +10 -> 383
    //   376: aload 5
    //   378: invokeinterface 246 1 0
    //   383: aload 7
    //   385: ifnull +152 -> 537
    //   388: aload_0
    //   389: getfield 71	com/tencent/component/net/download/multiplex/download/DownloadManager:i	Lcom/tencent/component/net/download/multiplex/download/DownloadDBHelper;
    //   392: iload_1
    //   393: invokevirtual 858	com/tencent/component/net/download/multiplex/download/DownloadDBHelper:a	(I)Z
    //   396: pop
    //   397: aload_0
    //   398: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   401: astore 9
    //   403: aload 9
    //   405: monitorenter
    //   406: aload_0
    //   407: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   410: invokeinterface 300 1 0
    //   415: istore 11
    //   417: iconst_0
    //   418: istore 12
    //   420: iload 12
    //   422: iload 11
    //   424: if_icmpge +36 -> 460
    //   427: aload_0
    //   428: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   431: iload 12
    //   433: invokeinterface 683 2 0
    //   438: checkcast 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   441: invokevirtual 181	com/tencent/component/net/download/multiplex/download/DownloadTask:E	()I
    //   444: iload_1
    //   445: if_icmpne +144 -> 589
    //   448: aload_0
    //   449: getfield 77	com/tencent/component/net/download/multiplex/download/DownloadManager:m	Ljava/util/List;
    //   452: iload 12
    //   454: invokeinterface 880 2 0
    //   459: pop
    //   460: aload 9
    //   462: monitorexit
    //   463: aload_0
    //   464: getfield 66	com/tencent/component/net/download/multiplex/download/DownloadManager:l	Ljava/util/List;
    //   467: astore 14
    //   469: aload 14
    //   471: monitorenter
    //   472: aload_0
    //   473: getfield 66	com/tencent/component/net/download/multiplex/download/DownloadManager:l	Ljava/util/List;
    //   476: invokeinterface 300 1 0
    //   481: istore 16
    //   483: iconst_0
    //   484: istore 17
    //   486: iload 17
    //   488: iload 16
    //   490: if_icmpge +36 -> 526
    //   493: aload_0
    //   494: getfield 66	com/tencent/component/net/download/multiplex/download/DownloadManager:l	Ljava/util/List;
    //   497: iload 17
    //   499: invokeinterface 683 2 0
    //   504: checkcast 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   507: invokevirtual 181	com/tencent/component/net/download/multiplex/download/DownloadTask:E	()I
    //   510: iload_1
    //   511: if_icmpne +92 -> 603
    //   514: aload_0
    //   515: getfield 66	com/tencent/component/net/download/multiplex/download/DownloadManager:l	Ljava/util/List;
    //   518: iload 17
    //   520: invokeinterface 880 2 0
    //   525: pop
    //   526: aload 14
    //   528: monitorexit
    //   529: aload_0
    //   530: aload 7
    //   532: iconst_1
    //   533: invokevirtual 360	com/tencent/component/net/download/multiplex/download/DownloadManager:a	(Lcom/tencent/component/net/download/multiplex/download/DownloadTask;Z)Lcom/tencent/component/net/download/multiplex/download/DownloadTask;
    //   536: pop
    //   537: aload 7
    //   539: areturn
    //   540: iconst_0
    //   541: istore 35
    //   543: goto -314 -> 229
    //   546: astore 6
    //   548: aconst_null
    //   549: astore 7
    //   551: aload 6
    //   553: invokevirtual 249	java/lang/Exception:printStackTrace	()V
    //   556: aload_3
    //   557: ifnull -174 -> 383
    //   560: aload_3
    //   561: invokeinterface 246 1 0
    //   566: goto -183 -> 383
    //   569: astore 4
    //   571: aconst_null
    //   572: astore 5
    //   574: aload 5
    //   576: ifnull +10 -> 586
    //   579: aload 5
    //   581: invokeinterface 246 1 0
    //   586: aload 4
    //   588: athrow
    //   589: iinc 12 1
    //   592: goto -172 -> 420
    //   595: astore 10
    //   597: aload 9
    //   599: monitorexit
    //   600: aload 10
    //   602: athrow
    //   603: iinc 17 1
    //   606: goto -120 -> 486
    //   609: astore 15
    //   611: aload 14
    //   613: monitorexit
    //   614: aload 15
    //   616: athrow
    //   617: astore 4
    //   619: goto -45 -> 574
    //   622: astore 4
    //   624: aload_3
    //   625: astore 5
    //   627: goto -53 -> 574
    //   630: astore 6
    //   632: aload 5
    //   634: astore_3
    //   635: aconst_null
    //   636: astore 7
    //   638: goto -87 -> 551
    //   641: astore 37
    //   643: aload 36
    //   645: astore 7
    //   647: aload 37
    //   649: astore 6
    //   651: aload 5
    //   653: astore_3
    //   654: goto -103 -> 551
    //   657: aconst_null
    //   658: astore 7
    //   660: goto -289 -> 371
    //
    // Exception table:
    //   from	to	target	type
    //   8	18	546	java/lang/Exception
    //   8	18	569	finally
    //   406	417	595	finally
    //   427	460	595	finally
    //   460	463	595	finally
    //   597	600	595	finally
    //   472	483	609	finally
    //   493	526	609	finally
    //   526	529	609	finally
    //   611	614	609	finally
    //   22	226	617	finally
    //   229	283	617	finally
    //   283	367	617	finally
    //   551	556	622	finally
    //   22	226	630	java/lang/Exception
    //   229	283	630	java/lang/Exception
    //   283	367	641	java/lang/Exception
  }

  public DownloadTask b(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    while (true)
    {
      int i2;
      synchronized (this.l)
      {
        int i1 = this.l.size();
        i2 = 0;
        if (i2 >= i1)
          break;
        DownloadTask localDownloadTask = (DownloadTask)this.l.get(i2);
        if (paramString.equalsIgnoreCase(localDownloadTask.ak()))
          return localDownloadTask;
      }
      i2++;
    }
    monitorexit;
    return null;
  }

  public List b(boolean paramBoolean)
  {
    LinkedList localLinkedList;
    synchronized (this.m)
    {
      localLinkedList = new LinkedList();
      Iterator localIterator = this.m.iterator();
      while (localIterator.hasNext())
      {
        DownloadTask localDownloadTask = (DownloadTask)localIterator.next();
        if (localDownloadTask.c() != paramBoolean)
          continue;
        localLinkedList.add(localDownloadTask);
      }
    }
    monitorexit;
    return localLinkedList;
  }

  public void b(DownloadInfo paramDownloadInfo, DownloadManager.OnDownloadFeedbackListener paramOnDownloadFeedbackListener)
  {
    a(paramDownloadInfo, false, paramOnDownloadFeedbackListener);
  }

  public void b(DownloadManager.OnDownloadedTaskListener paramOnDownloadedTaskListener)
  {
    synchronized (this.k)
    {
      this.k.remove(paramOnDownloadedTaskListener);
      return;
    }
  }

  public void b(DownloadTask paramDownloadTask)
  {
    if (paramDownloadTask == null);
    do
      return;
    while ((paramDownloadTask.aD != 6) && (paramDownloadTask.aD != 5) && (paramDownloadTask.aD != 4));
    if (paramDownloadTask.aD == 5);
    paramDownloadTask.aD = 0;
    paramDownloadTask.a(this);
    this.i.b(paramDownloadTask);
    this.h.a(paramDownloadTask);
    p();
  }

  public void b(Task paramTask)
  {
    DownloadTask localDownloadTask = (DownloadTask)paramTask;
    this.i.b(localDownloadTask);
    synchronized (this.j)
    {
      Iterator localIterator = this.j.iterator();
      if (localIterator.hasNext())
        ((TaskObserver)localIterator.next()).b(paramTask);
    }
    monitorexit;
    synchronized (this.l)
    {
      this.l.add(localDownloadTask);
      return;
    }
  }

  public void b(TaskObserver paramTaskObserver)
  {
    synchronized (this.j)
    {
      this.j.remove(paramTaskObserver);
      return;
    }
  }

  public void b(String paramString1, String paramString2)
  {
    DownloadTask localDownloadTask = m(paramString1);
    if (localDownloadTask == null)
      localDownloadTask = j(paramString1);
    if (localDownloadTask != null)
    {
      localDownloadTask.e(FileUtils.c(localDownloadTask.t(), paramString2));
      a(localDownloadTask);
    }
  }

  public boolean b()
  {
    return this.q;
  }

  public boolean b(DownloadTask paramDownloadTask, boolean paramBoolean)
  {
    return a(paramDownloadTask, paramBoolean, true);
  }

  public DownloadTask c(int paramInt)
  {
    return this.h.c(paramInt);
  }

  public List c(boolean paramBoolean)
  {
    LinkedList localLinkedList;
    synchronized (this.l)
    {
      localLinkedList = new LinkedList();
      Iterator localIterator = this.l.iterator();
      while (localIterator.hasNext())
      {
        DownloadTask localDownloadTask = (DownloadTask)localIterator.next();
        if (localDownloadTask.c() != paramBoolean)
          continue;
        localLinkedList.add(localDownloadTask);
      }
    }
    monitorexit;
    return localLinkedList;
  }

  public void c()
  {
    synchronized (this.m)
    {
      Iterator localIterator = this.m.iterator();
      if (localIterator.hasNext())
        b((DownloadTask)localIterator.next());
    }
    monitorexit;
  }

  public void c(Task paramTask)
  {
    synchronized (this.j)
    {
      Iterator localIterator = this.j.iterator();
      if (localIterator.hasNext())
        ((TaskObserver)localIterator.next()).c(paramTask);
    }
    monitorexit;
  }

  public boolean c(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return false;
    while (true)
    {
      int i2;
      synchronized (this.m)
      {
        int i1 = this.m.size();
        i2 = 0;
        if (i2 >= i1)
          break;
        DownloadTask localDownloadTask = (DownloadTask)this.m.get(i2);
        if ((paramString.equalsIgnoreCase(localDownloadTask.ak())) && (localDownloadTask.an() == 5))
          return true;
      }
      i2++;
    }
    monitorexit;
    return false;
  }

  public DownloadTask d(DownloadTask paramDownloadTask)
  {
    paramDownloadTask.c(FileUtils.c(paramDownloadTask.t(), paramDownloadTask.q()));
    return a(paramDownloadTask, true);
  }

  // ERROR //
  public ArrayList d(boolean paramBoolean)
  {
    // Byte code:
    //   0: new 160	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 161	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: aload_0
    //   9: invokevirtual 908	com/tencent/component/net/download/multiplex/download/DownloadManager:g	()Landroid/database/Cursor;
    //   12: astore 7
    //   14: aload 7
    //   16: astore 6
    //   18: aload 6
    //   20: ifnull +517 -> 537
    //   23: aload 6
    //   25: ldc 80
    //   27: invokeinterface 86 2 0
    //   32: istore 8
    //   34: aload 6
    //   36: ldc 88
    //   38: invokeinterface 86 2 0
    //   43: istore 9
    //   45: aload 6
    //   47: ldc 90
    //   49: invokeinterface 86 2 0
    //   54: istore 10
    //   56: aload 6
    //   58: ldc 92
    //   60: invokeinterface 86 2 0
    //   65: istore 11
    //   67: aload 6
    //   69: ldc 94
    //   71: invokeinterface 86 2 0
    //   76: istore 12
    //   78: aload 6
    //   80: ldc 96
    //   82: invokeinterface 86 2 0
    //   87: istore 13
    //   89: aload 6
    //   91: ldc 98
    //   93: invokeinterface 86 2 0
    //   98: istore 14
    //   100: aload 6
    //   102: ldc 100
    //   104: invokeinterface 86 2 0
    //   109: istore 15
    //   111: aload 6
    //   113: ldc 102
    //   115: invokeinterface 86 2 0
    //   120: istore 16
    //   122: aload 6
    //   124: ldc 104
    //   126: invokeinterface 86 2 0
    //   131: istore 17
    //   133: aload 6
    //   135: ldc 106
    //   137: invokeinterface 86 2 0
    //   142: istore 18
    //   144: aload 6
    //   146: ldc 110
    //   148: invokeinterface 86 2 0
    //   153: istore 19
    //   155: aload 6
    //   157: ldc 112
    //   159: invokeinterface 86 2 0
    //   164: istore 20
    //   166: aload 6
    //   168: ldc 114
    //   170: invokeinterface 86 2 0
    //   175: istore 21
    //   177: aload 6
    //   179: ldc 116
    //   181: invokeinterface 86 2 0
    //   186: istore 22
    //   188: aload 6
    //   190: ldc 118
    //   192: invokeinterface 86 2 0
    //   197: istore 23
    //   199: aload 6
    //   201: ldc 120
    //   203: invokeinterface 86 2 0
    //   208: istore 24
    //   210: aload 6
    //   212: invokeinterface 233 1 0
    //   217: ifeq +320 -> 537
    //   220: aload 6
    //   222: iload 17
    //   224: invokeinterface 124 2 0
    //   229: istore 25
    //   231: iload 25
    //   233: bipush 32
    //   235: iand
    //   236: bipush 32
    //   238: if_icmpne +287 -> 525
    //   241: iconst_1
    //   242: istore 26
    //   244: iload 26
    //   246: iload_1
    //   247: if_icmpne -37 -> 210
    //   250: aload 6
    //   252: iload 8
    //   254: invokeinterface 124 2 0
    //   259: istore 27
    //   261: aload 6
    //   263: iload 9
    //   265: invokeinterface 124 2 0
    //   270: i2b
    //   271: istore 28
    //   273: aload 6
    //   275: iload 10
    //   277: invokeinterface 128 2 0
    //   282: astore 29
    //   284: aload 6
    //   286: iload 11
    //   288: invokeinterface 128 2 0
    //   293: astore 30
    //   295: aload 6
    //   297: iload 12
    //   299: invokeinterface 128 2 0
    //   304: astore 31
    //   306: aload 6
    //   308: iload 13
    //   310: invokeinterface 132 2 0
    //   315: lstore 32
    //   317: aload 6
    //   319: iload 14
    //   321: invokeinterface 132 2 0
    //   326: lstore 34
    //   328: aload 6
    //   330: iload 15
    //   332: invokeinterface 124 2 0
    //   337: iconst_1
    //   338: if_icmpne +193 -> 531
    //   341: iconst_1
    //   342: istore 36
    //   344: new 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   347: dup
    //   348: iload 27
    //   350: iload 28
    //   352: aload 29
    //   354: aload 30
    //   356: aload 31
    //   358: lload 32
    //   360: lload 34
    //   362: iload 36
    //   364: aload 6
    //   366: iload 16
    //   368: invokeinterface 128 2 0
    //   373: iload 25
    //   375: iconst_1
    //   376: aload 6
    //   378: iload 18
    //   380: invokeinterface 132 2 0
    //   385: aload 6
    //   387: iload 23
    //   389: invokeinterface 128 2 0
    //   394: invokespecial 137	com/tencent/component/net/download/multiplex/download/DownloadTask:<init>	(IBLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JJZLjava/lang/String;IZJLjava/lang/String;)V
    //   397: astore 37
    //   399: aload 37
    //   401: aload 6
    //   403: iload 19
    //   405: invokeinterface 128 2 0
    //   410: invokevirtual 143	com/tencent/component/net/download/multiplex/download/DownloadTask:f	(Ljava/lang/String;)V
    //   413: aload 37
    //   415: aload 6
    //   417: iload 20
    //   419: invokeinterface 124 2 0
    //   424: invokevirtual 146	com/tencent/component/net/download/multiplex/download/DownloadTask:b	(I)V
    //   427: aload 37
    //   429: aload 6
    //   431: iload 21
    //   433: invokeinterface 128 2 0
    //   438: invokevirtual 148	com/tencent/component/net/download/multiplex/download/DownloadTask:i	(Ljava/lang/String;)V
    //   441: aload 37
    //   443: aload 6
    //   445: iload 22
    //   447: invokeinterface 128 2 0
    //   452: invokevirtual 150	com/tencent/component/net/download/multiplex/download/DownloadTask:h	(Ljava/lang/String;)V
    //   455: aload 37
    //   457: aload 6
    //   459: iload 23
    //   461: invokeinterface 128 2 0
    //   466: invokevirtual 357	com/tencent/component/net/download/multiplex/download/DownloadTask:a	(Ljava/lang/String;)V
    //   469: aload 37
    //   471: aload 6
    //   473: iload 24
    //   475: invokeinterface 132 2 0
    //   480: invokevirtual 152	com/tencent/component/net/download/multiplex/download/DownloadTask:a	(J)V
    //   483: aload 37
    //   485: getfield 508	com/tencent/component/net/download/multiplex/download/DownloadTask:aD	B
    //   488: iconst_3
    //   489: if_icmpne -279 -> 210
    //   492: aload_2
    //   493: aload 37
    //   495: invokevirtual 197	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   498: pop
    //   499: goto -289 -> 210
    //   502: astore_3
    //   503: aload 6
    //   505: astore 4
    //   507: aload_3
    //   508: invokevirtual 249	java/lang/Exception:printStackTrace	()V
    //   511: aload 4
    //   513: ifnull +10 -> 523
    //   516: aload 4
    //   518: invokeinterface 246 1 0
    //   523: aload_2
    //   524: areturn
    //   525: iconst_0
    //   526: istore 26
    //   528: goto -284 -> 244
    //   531: iconst_0
    //   532: istore 36
    //   534: goto -190 -> 344
    //   537: aload 6
    //   539: ifnull -16 -> 523
    //   542: aload 6
    //   544: invokeinterface 246 1 0
    //   549: aload_2
    //   550: areturn
    //   551: astore 5
    //   553: aconst_null
    //   554: astore 6
    //   556: aload 6
    //   558: ifnull +10 -> 568
    //   561: aload 6
    //   563: invokeinterface 246 1 0
    //   568: aload 5
    //   570: athrow
    //   571: astore 5
    //   573: goto -17 -> 556
    //   576: astore 5
    //   578: aload 4
    //   580: astore 6
    //   582: goto -26 -> 556
    //   585: astore_3
    //   586: aconst_null
    //   587: astore 4
    //   589: goto -82 -> 507
    //
    // Exception table:
    //   from	to	target	type
    //   23	210	502	java/lang/Exception
    //   210	231	502	java/lang/Exception
    //   250	341	502	java/lang/Exception
    //   344	499	502	java/lang/Exception
    //   8	14	551	finally
    //   23	210	571	finally
    //   210	231	571	finally
    //   250	341	571	finally
    //   344	499	571	finally
    //   507	511	576	finally
    //   8	14	585	java/lang/Exception
  }

  public void d()
  {
    synchronized (this.l)
    {
      ArrayList localArrayList = new ArrayList();
      for (int i1 = 0; i1 < this.l.size(); i1++)
        localArrayList.add(Integer.valueOf(((DownloadTask)this.l.get(i1)).E()));
      Iterator localIterator = localArrayList.iterator();
      if (localIterator.hasNext())
        a(((Integer)localIterator.next()).intValue());
    }
    monitorexit;
  }

  public void d(int paramInt)
  {
    this.h.b(paramInt);
  }

  public void d(Task paramTask)
  {
    LogUtil.d("Benson", "[DownloadManager] onTaskCompleted()");
    DownloadTask localDownloadTask = (DownloadTask)paramTask;
    this.h.b(localDownloadTask);
    this.i.b(localDownloadTask);
    synchronized (this.m)
    {
      this.m.remove(localDownloadTask);
    }
    synchronized (this.l)
    {
      this.l.remove(localDownloadTask);
      synchronized (this.j)
      {
        Iterator localIterator = this.j.iterator();
        if (!localIterator.hasNext())
          break label150;
        ((TaskObserver)localIterator.next()).d(localDownloadTask);
      }
      localObject1 = finally;
      monitorexit;
      throw localObject1;
    }
    label150: monitorexit;
    if (!localDownloadTask.c())
      if (this.n)
        break label283;
    label283: for (int i1 = 0; ; i1 = 1)
    {
      this.n = true;
      if ((!localDownloadTask.d()) && (i1 != 0) && (localDownloadTask.k()));
      Uri localUri = Uri.parse("file://" + localDownloadTask.t() + "/" + localDownloadTask.q());
      ComponentContext.a().getApplicationContext().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", localUri));
      if ((0x1 & localDownloadTask.K()) == 1)
        new File(localDownloadTask.t(), localDownloadTask.q());
      return;
    }
  }

  public boolean d(String paramString)
  {
    int i1 = 1;
    if (TextUtils.isEmpty(paramString))
      return false;
    Cursor localCursor = null;
    while (true)
    {
      try
      {
        localCursor = this.i.d();
        if (localCursor == null)
          continue;
        int i2 = localCursor.getColumnIndexOrThrow("id");
        int i3 = localCursor.getColumnIndexOrThrow("annotation");
        if (!localCursor.moveToNext())
          continue;
        if (!paramString.equalsIgnoreCase(localCursor.getString(i3)))
          continue;
        a(localCursor.getInt(i2), true);
        continue;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        if (localCursor != null)
        {
          localCursor.close();
          i1 = 0;
          return i1;
          if (localCursor == null)
            continue;
          localCursor.close();
          continue;
        }
      }
      finally
      {
        if (localCursor == null)
          continue;
        localCursor.close();
      }
      i1 = 0;
    }
  }

  public int e()
  {
    int i2;
    for (int i1 = 0; ; i1 = i2)
    {
      synchronized (this.m)
      {
        Iterator localIterator = this.m.iterator();
        if (localIterator.hasNext())
        {
          DownloadTask localDownloadTask = (DownloadTask)localIterator.next();
          if ((localDownloadTask != null) && (!localDownloadTask.c()) && (localDownloadTask.an() == 5))
          {
            i2 = i1 + 1;
            continue;
          }
        }
        else
        {
          return i1;
        }
      }
      i2 = i1;
    }
  }

  public DownloadTask e(int paramInt)
  {
    synchronized (this.m)
    {
      Iterator localIterator1 = this.m.iterator();
      while (localIterator1.hasNext())
      {
        DownloadTask localDownloadTask2 = (DownloadTask)localIterator1.next();
        if (localDownloadTask2.E() == paramInt)
          return localDownloadTask2;
      }
      synchronized (this.l)
      {
        Iterator localIterator2 = this.l.iterator();
        while (localIterator2.hasNext())
        {
          DownloadTask localDownloadTask1 = (DownloadTask)localIterator2.next();
          if (localDownloadTask1.E() == paramInt)
            return localDownloadTask1;
        }
      }
    }
    monitorexit;
    return null;
  }

  // ERROR //
  public DownloadTask e(String paramString)
  {
    // Byte code:
    //   0: new 134	com/tencent/component/net/download/multiplex/download/DownloadTask
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 585	com/tencent/component/net/download/multiplex/download/DownloadTask:<init>	(Ljava/lang/String;)V
    //   8: astore_2
    //   9: aload_0
    //   10: getfield 71	com/tencent/component/net/download/multiplex/download/DownloadManager:i	Lcom/tencent/component/net/download/multiplex/download/DownloadDBHelper;
    //   13: aload_1
    //   14: invokevirtual 322	com/tencent/component/net/download/multiplex/download/DownloadDBHelper:a	(Ljava/lang/String;)Landroid/database/Cursor;
    //   17: astore 8
    //   19: aload 8
    //   21: astore 4
    //   23: aload 4
    //   25: ifnull +163 -> 188
    //   28: aload 4
    //   30: invokeinterface 330 1 0
    //   35: ifeq +153 -> 188
    //   38: aload 4
    //   40: aload 4
    //   42: ldc 88
    //   44: invokeinterface 86 2 0
    //   49: invokeinterface 124 2 0
    //   54: iconst_3
    //   55: if_icmpne +133 -> 188
    //   58: aload_2
    //   59: aload 4
    //   61: aload 4
    //   63: ldc 92
    //   65: invokeinterface 86 2 0
    //   70: invokeinterface 128 2 0
    //   75: invokevirtual 315	com/tencent/component/net/download/multiplex/download/DownloadTask:c	(Ljava/lang/String;)V
    //   78: aload_2
    //   79: aload 4
    //   81: aload 4
    //   83: ldc 94
    //   85: invokeinterface 86 2 0
    //   90: invokeinterface 128 2 0
    //   95: invokevirtual 937	com/tencent/component/net/download/multiplex/download/DownloadTask:d	(Ljava/lang/String;)V
    //   98: iconst_1
    //   99: istore 7
    //   101: aload 4
    //   103: ifnull +10 -> 113
    //   106: aload 4
    //   108: invokeinterface 246 1 0
    //   113: iload 7
    //   115: ifne +65 -> 180
    //   118: aconst_null
    //   119: areturn
    //   120: astore 6
    //   122: aconst_null
    //   123: astore 4
    //   125: aload 6
    //   127: invokevirtual 249	java/lang/Exception:printStackTrace	()V
    //   130: aload 4
    //   132: ifnull +50 -> 182
    //   135: aload 4
    //   137: invokeinterface 246 1 0
    //   142: iconst_0
    //   143: istore 7
    //   145: goto -32 -> 113
    //   148: astore_3
    //   149: aconst_null
    //   150: astore 4
    //   152: aload_3
    //   153: astore 5
    //   155: aload 4
    //   157: ifnull +10 -> 167
    //   160: aload 4
    //   162: invokeinterface 246 1 0
    //   167: aload 5
    //   169: athrow
    //   170: astore 5
    //   172: goto -17 -> 155
    //   175: astore 6
    //   177: goto -52 -> 125
    //   180: aload_2
    //   181: areturn
    //   182: iconst_0
    //   183: istore 7
    //   185: goto -72 -> 113
    //   188: iconst_0
    //   189: istore 7
    //   191: goto -90 -> 101
    //
    // Exception table:
    //   from	to	target	type
    //   9	19	120	java/lang/Exception
    //   9	19	148	finally
    //   28	98	170	finally
    //   125	130	170	finally
    //   28	98	175	java/lang/Exception
  }

  public void e(Task paramTask)
  {
    LogUtil.d("DownloadManager", "[DownloadManager] onTaskFailed() - " + paramTask.aD);
    DownloadTask localDownloadTask = (DownloadTask)paramTask;
    this.h.b(localDownloadTask);
    this.i.b(localDownloadTask);
    synchronized (this.l)
    {
      this.l.remove(localDownloadTask);
    }
    synchronized (this.j)
    {
      if (this.j.size() == 0)
      {
        String str = localDownloadTask.O();
        if ((str == null) || ("".equals(str)));
      }
      Iterator localIterator;
      do
      {
        if ((!localDownloadTask.c()) && (!localDownloadTask.d()) && (this.n) && (e() != 0));
        LogUtil.d("DownloadManager", "DownloadManager onTaskFailed flow:" + localDownloadTask.ar());
        return;
        localObject1 = finally;
        monitorexit;
        throw localObject1;
        localIterator = this.j.iterator();
      }
      while (!localIterator.hasNext());
      ((TaskObserver)localIterator.next()).e(localDownloadTask);
    }
  }

  public void e(boolean paramBoolean)
  {
    this.r = paramBoolean;
  }

  // ERROR //
  public DownloadTask f(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 71	com/tencent/component/net/download/multiplex/download/DownloadManager:i	Lcom/tencent/component/net/download/multiplex/download/DownloadDBHelper;
    //   4: iload_1
    //   5: invokevirtual 367	com/tencent/component/net/download/multiplex/download/DownloadDBHelper:b	(I)Landroid/database/Cursor;
    //   8: astore 7
    //   10: aload 7
    //   12: astore_3
    //   13: aconst_null
    //   14: astore 6
    //   16: aload_3
    //   17: ifnull +30 -> 47
    //   20: aload_3
    //   21: invokeinterface 233 1 0
    //   26: istore 8
    //   28: aconst_null
    //   29: astore 6
    //   31: iload 8
    //   33: ifeq +14 -> 47
    //   36: aload_0
    //   37: aload_3
    //   38: invokespecial 235	com/tencent/component/net/download/multiplex/download/DownloadManager:a	(Landroid/database/Cursor;)Lcom/tencent/component/net/download/multiplex/download/DownloadTask;
    //   41: astore 9
    //   43: aload 9
    //   45: astore 6
    //   47: aload_3
    //   48: ifnull +9 -> 57
    //   51: aload_3
    //   52: invokeinterface 246 1 0
    //   57: aload 6
    //   59: areturn
    //   60: astore 5
    //   62: aconst_null
    //   63: astore_3
    //   64: aload 5
    //   66: invokevirtual 249	java/lang/Exception:printStackTrace	()V
    //   69: aconst_null
    //   70: astore 6
    //   72: aload_3
    //   73: ifnull -16 -> 57
    //   76: aload_3
    //   77: invokeinterface 246 1 0
    //   82: aconst_null
    //   83: areturn
    //   84: astore_2
    //   85: aconst_null
    //   86: astore_3
    //   87: aload_2
    //   88: astore 4
    //   90: aload_3
    //   91: ifnull +9 -> 100
    //   94: aload_3
    //   95: invokeinterface 246 1 0
    //   100: aload 4
    //   102: athrow
    //   103: astore 4
    //   105: goto -15 -> 90
    //   108: astore 5
    //   110: goto -46 -> 64
    //
    // Exception table:
    //   from	to	target	type
    //   0	10	60	java/lang/Exception
    //   0	10	84	finally
    //   20	28	103	finally
    //   36	43	103	finally
    //   64	69	103	finally
    //   20	28	108	java/lang/Exception
    //   36	43	108	java/lang/Exception
  }

  public void f(String paramString)
  {
  }

  public boolean f()
  {
    monitorenter;
    try
    {
      int i2;
      if (this.l != null)
        if ((this.l.size() > 0) && (this.m.size() > 0))
        {
          i2 = 1;
          if (i2 == 0)
            break label91;
          Iterator localIterator = this.l.iterator();
          boolean bool;
          do
          {
            if (!localIterator.hasNext())
              break;
            bool = ((DownloadTask)localIterator.next()).c();
          }
          while (bool);
        }
      label91: for (int i1 = 1; ; i1 = 0)
      {
        return i1;
        i2 = 0;
        break;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean f(Task paramTask)
  {
    return (paramTask != null) && ((paramTask instanceof DownloadTask)) && (((DownloadTask)paramTask).M());
  }

  public Cursor g()
  {
    return this.i.c();
  }

  public void g(Task paramTask)
  {
    a((DownloadTask)paramTask);
    synchronized (this.j)
    {
      Iterator localIterator = this.j.iterator();
      if (localIterator.hasNext())
        ((TaskObserver)localIterator.next()).g(paramTask);
    }
    monitorexit;
  }

  public boolean g(String paramString)
  {
    while (true)
    {
      synchronized (this.m)
      {
        Iterator localIterator = this.m.iterator();
        if (localIterator.hasNext())
        {
          if (!((DownloadTask)localIterator.next()).v().equals(paramString))
            continue;
          i1 = 1;
          return i1;
        }
      }
      int i1 = 0;
    }
  }

  public DownloadTask h(String paramString)
  {
    Object localObject1 = null;
    if (TextUtils.isEmpty(paramString))
      return null;
    while (true)
    {
      synchronized (this.m)
      {
        Iterator localIterator = this.m.iterator();
        if (!localIterator.hasNext())
          continue;
        localObject3 = (DownloadTask)localIterator.next();
        if (paramString.equals(((DownloadTask)localObject3).v()))
        {
          break label80;
          return localObject1;
        }
      }
      Object localObject3 = localObject1;
      label80: localObject1 = localObject3;
    }
  }

  public void h()
  {
    try
    {
      LogUtil.d("DownloadManager", "[DownloadManager] begin update when shutdown");
      synchronized (this.l)
      {
        Iterator localIterator = this.l.iterator();
        if (localIterator.hasNext())
        {
          DownloadTask localDownloadTask = (DownloadTask)localIterator.next();
          localDownloadTask.I();
          this.i.b(localDownloadTask);
        }
      }
    }
    catch (Exception localException)
    {
      LogUtil.d("DownloadManager", "[DownloadManager] Error while shutdowning DownloadManager - " + localException);
    }
    while (true)
    {
      LogUtil.d("DownloadManager", "[DownloadManager] downloa manager cancel all notifications");
      return;
      monitorexit;
      LogUtil.d("DownloadManager", "[DownloadManager] end update when shutdown");
    }
  }

  public int i(String paramString)
  {
    int i1 = 0;
    if (TextUtils.isEmpty(paramString))
      return 0;
    while (true)
    {
      synchronized (this.m)
      {
        Iterator localIterator = this.m.iterator();
        if (!localIterator.hasNext())
          continue;
        DownloadTask localDownloadTask = (DownloadTask)localIterator.next();
        if (paramString.equals(localDownloadTask.v()))
        {
          i2 = localDownloadTask.u();
          break label87;
          return i1;
        }
      }
      int i2 = i1;
      label87: i1 = i2;
    }
  }

  public void i()
  {
    Object localObject3;
    for (Object localObject1 = null; ; localObject1 = localObject3)
    {
      synchronized (this.m)
      {
        Iterator localIterator = this.m.iterator();
        if (!localIterator.hasNext())
          break label124;
        localObject3 = (DownloadTask)localIterator.next();
        if (((DownloadTask)localObject3).d())
          a(((DownloadTask)localObject3).E(), true);
      }
      if ((((DownloadTask)localObject3).aD == 0) || (((DownloadTask)localObject3).aD == 1) || (((DownloadTask)localObject3).aD == 2))
      {
        if ((0x1 & ((DownloadTask)localObject3).K()) == 1)
        {
          ((DownloadTask)localObject3).aD = 6;
          continue;
        }
        if (!((DownloadTask)localObject3).c())
        {
          label124: monitorexit;
          if (localObject1 != null)
            b((DownloadTask)localObject1);
          k();
          return;
        }
      }
      localObject3 = localObject1;
    }
  }

  public DownloadTask j(String paramString)
  {
    synchronized (this.m)
    {
      Iterator localIterator1 = this.m.iterator();
      while (localIterator1.hasNext())
      {
        DownloadTask localDownloadTask2 = (DownloadTask)localIterator1.next();
        if ((localDownloadTask2 != null) && (localDownloadTask2.v().equals(paramString)))
          return localDownloadTask2;
      }
      synchronized (this.l)
      {
        Iterator localIterator2 = this.l.iterator();
        while (localIterator2.hasNext())
        {
          DownloadTask localDownloadTask1 = (DownloadTask)localIterator2.next();
          if ((localDownloadTask1 != null) && (localDownloadTask1.v().equals(paramString)))
            return localDownloadTask1;
        }
      }
    }
    monitorexit;
    return null;
  }

  public void j()
  {
    while (true)
    {
      DownloadTask localDownloadTask;
      synchronized (this.m)
      {
        Iterator localIterator = this.m.iterator();
        if (!localIterator.hasNext())
          break;
        localDownloadTask = (DownloadTask)localIterator.next();
        if ((localDownloadTask.aD != 0) && (localDownloadTask.aD != 1) && (localDownloadTask.aD != 2))
          continue;
        localDownloadTask.aD = 6;
        if (!localDownloadTask.c())
        {
          localDownloadTask.d(0x2 | localDownloadTask.K());
          b(localDownloadTask);
        }
      }
      this.i.b(localDownloadTask);
    }
    monitorexit;
  }

  public DownloadTask k(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    synchronized (this.m)
    {
      Iterator localIterator1 = this.m.iterator();
      while (localIterator1.hasNext())
      {
        DownloadTask localDownloadTask2 = (DownloadTask)localIterator1.next();
        if (paramString.equals(localDownloadTask2.q()))
          return localDownloadTask2;
      }
    }
    monitorexit;
    synchronized (this.l)
    {
      Iterator localIterator2 = this.l.iterator();
      while (localIterator2.hasNext())
      {
        DownloadTask localDownloadTask1 = (DownloadTask)localIterator2.next();
        if (paramString.equals(localDownloadTask1.q()))
          return localDownloadTask1;
      }
    }
    monitorexit;
    return null;
  }

  public void k()
  {
    synchronized (this.m)
    {
      Iterator localIterator = this.m.iterator();
      while (localIterator.hasNext())
      {
        DownloadTask localDownloadTask = (DownloadTask)localIterator.next();
        if ((localDownloadTask.aD != 0) && (localDownloadTask.aD != 1) && (localDownloadTask.aD != 2))
          continue;
        localDownloadTask.aD = 6;
        this.i.b(localDownloadTask);
      }
    }
    monitorexit;
  }

  // ERROR //
  public DownloadTask l(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 209	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: istore_2
    //   5: aconst_null
    //   6: astore_3
    //   7: iload_2
    //   8: ifeq +5 -> 13
    //   11: aload_3
    //   12: areturn
    //   13: aload_0
    //   14: getfield 71	com/tencent/component/net/download/multiplex/download/DownloadManager:i	Lcom/tencent/component/net/download/multiplex/download/DownloadDBHelper;
    //   17: aload_1
    //   18: invokevirtual 322	com/tencent/component/net/download/multiplex/download/DownloadDBHelper:a	(Ljava/lang/String;)Landroid/database/Cursor;
    //   21: astore 8
    //   23: aload 8
    //   25: astore 5
    //   27: aconst_null
    //   28: astore_3
    //   29: aload 5
    //   31: ifnull +30 -> 61
    //   34: aload 5
    //   36: invokeinterface 233 1 0
    //   41: istore 9
    //   43: aconst_null
    //   44: astore_3
    //   45: iload 9
    //   47: ifeq +14 -> 61
    //   50: aload_0
    //   51: aload 5
    //   53: invokespecial 235	com/tencent/component/net/download/multiplex/download/DownloadManager:a	(Landroid/database/Cursor;)Lcom/tencent/component/net/download/multiplex/download/DownloadTask;
    //   56: astore 10
    //   58: aload 10
    //   60: astore_3
    //   61: aload 5
    //   63: ifnull -52 -> 11
    //   66: aload 5
    //   68: invokeinterface 246 1 0
    //   73: aload_3
    //   74: areturn
    //   75: astore 7
    //   77: aconst_null
    //   78: astore 5
    //   80: aload 7
    //   82: invokevirtual 249	java/lang/Exception:printStackTrace	()V
    //   85: aconst_null
    //   86: astore_3
    //   87: aload 5
    //   89: ifnull -78 -> 11
    //   92: aload 5
    //   94: invokeinterface 246 1 0
    //   99: aconst_null
    //   100: areturn
    //   101: astore 4
    //   103: aconst_null
    //   104: astore 5
    //   106: aload 4
    //   108: astore 6
    //   110: aload 5
    //   112: ifnull +10 -> 122
    //   115: aload 5
    //   117: invokeinterface 246 1 0
    //   122: aload 6
    //   124: athrow
    //   125: astore 6
    //   127: goto -17 -> 110
    //   130: astore 7
    //   132: goto -52 -> 80
    //
    // Exception table:
    //   from	to	target	type
    //   13	23	75	java/lang/Exception
    //   13	23	101	finally
    //   34	43	125	finally
    //   50	58	125	finally
    //   80	85	125	finally
    //   34	43	130	java/lang/Exception
    //   50	58	130	java/lang/Exception
  }

  public void l()
  {
  }

  // ERROR //
  public DownloadTask m(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 209	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: istore_2
    //   5: aconst_null
    //   6: astore_3
    //   7: iload_2
    //   8: ifeq +5 -> 13
    //   11: aload_3
    //   12: areturn
    //   13: aload_0
    //   14: getfield 71	com/tencent/component/net/download/multiplex/download/DownloadManager:i	Lcom/tencent/component/net/download/multiplex/download/DownloadDBHelper;
    //   17: aload_1
    //   18: invokevirtual 977	com/tencent/component/net/download/multiplex/download/DownloadDBHelper:c	(Ljava/lang/String;)Landroid/database/Cursor;
    //   21: astore 8
    //   23: aload 8
    //   25: astore 5
    //   27: aconst_null
    //   28: astore_3
    //   29: aload 5
    //   31: ifnull +30 -> 61
    //   34: aload 5
    //   36: invokeinterface 233 1 0
    //   41: istore 9
    //   43: aconst_null
    //   44: astore_3
    //   45: iload 9
    //   47: ifeq +14 -> 61
    //   50: aload_0
    //   51: aload 5
    //   53: invokespecial 235	com/tencent/component/net/download/multiplex/download/DownloadManager:a	(Landroid/database/Cursor;)Lcom/tencent/component/net/download/multiplex/download/DownloadTask;
    //   56: astore 10
    //   58: aload 10
    //   60: astore_3
    //   61: aload 5
    //   63: ifnull -52 -> 11
    //   66: aload 5
    //   68: invokeinterface 246 1 0
    //   73: aload_3
    //   74: areturn
    //   75: astore 7
    //   77: aconst_null
    //   78: astore 5
    //   80: aload 7
    //   82: invokevirtual 249	java/lang/Exception:printStackTrace	()V
    //   85: aconst_null
    //   86: astore_3
    //   87: aload 5
    //   89: ifnull -78 -> 11
    //   92: aload 5
    //   94: invokeinterface 246 1 0
    //   99: aconst_null
    //   100: areturn
    //   101: astore 4
    //   103: aconst_null
    //   104: astore 5
    //   106: aload 4
    //   108: astore 6
    //   110: aload 5
    //   112: ifnull +10 -> 122
    //   115: aload 5
    //   117: invokeinterface 246 1 0
    //   122: aload 6
    //   124: athrow
    //   125: astore 6
    //   127: goto -17 -> 110
    //   130: astore 7
    //   132: goto -52 -> 80
    //
    // Exception table:
    //   from	to	target	type
    //   13	23	75	java/lang/Exception
    //   13	23	101	finally
    //   34	43	125	finally
    //   50	58	125	finally
    //   80	85	125	finally
    //   34	43	130	java/lang/Exception
    //   50	58	130	java/lang/Exception
  }

  public void m()
  {
  }

  public boolean n()
  {
    int i1 = 1;
    ArrayList localArrayList = d(false);
    if (((localArrayList == null) || (localArrayList.size() <= 0)) && ((this.m == null) || (this.m.size() <= 0)))
      i1 = 0;
    return i1;
  }

  public boolean n(String paramString)
  {
    boolean bool1 = TextUtils.isEmpty(paramString);
    int i1 = 0;
    if (bool1);
    while (true)
    {
      return i1;
      Cursor localCursor = null;
      try
      {
        localCursor = this.i.d(paramString);
        i1 = 0;
        if (localCursor != null)
        {
          boolean bool2 = localCursor.moveToNext();
          i1 = 0;
          if (bool2)
            i1 = 1;
        }
        return i1;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        i1 = 0;
        return false;
      }
      finally
      {
        if (localCursor != null)
          localCursor.close();
      }
    }
    throw localObject;
  }

  public boolean o()
  {
    return this.r;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.DownloadManager
 * JD-Core Version:    0.6.0
 */