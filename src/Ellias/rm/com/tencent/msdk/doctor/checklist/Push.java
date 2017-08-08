package com.tencent.msdk.doctor.checklist;

import android.app.Activity;
import com.tencent.msdk.doctor.CheckBase;

public class Push extends CheckBase
{
  public Push(Activity paramActivity)
  {
    super(paramActivity);
  }

  // ERROR //
  public java.util.ArrayList<java.lang.String> check()
  {
    // Byte code:
    //   0: new 16	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 19	java/util/ArrayList:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: getfield 23	com/tencent/msdk/doctor/checklist/Push:mContext	Landroid/app/Activity;
    //   12: invokevirtual 29	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   15: astore_2
    //   16: new 31	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 32	java/lang/StringBuilder:<init>	()V
    //   23: aload_2
    //   24: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: ldc 38
    //   29: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual 41	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: astore_3
    //   36: aload_3
    //   37: invokestatic 47	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   40: pop
    //   41: aload_0
    //   42: getfield 23	com/tencent/msdk/doctor/checklist/Push:mContext	Landroid/app/Activity;
    //   45: invokevirtual 51	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   48: new 53	android/content/ComponentName
    //   51: dup
    //   52: aload_2
    //   53: aload_3
    //   54: invokespecial 56	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   57: sipush 128
    //   60: invokevirtual 62	android/content/pm/PackageManager:getActivityInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   63: astore 17
    //   65: bipush 32
    //   67: aload 17
    //   69: getfield 68	android/content/pm/ActivityInfo:flags	I
    //   72: iand
    //   73: bipush 32
    //   75: if_icmpeq +32 -> 107
    //   78: aload_1
    //   79: new 31	java/lang/StringBuilder
    //   82: dup
    //   83: invokespecial 32	java/lang/StringBuilder:<init>	()V
    //   86: ldc 70
    //   88: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: aload_3
    //   92: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: ldc 72
    //   97: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: invokevirtual 41	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: invokevirtual 76	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   106: pop
    //   107: aload 17
    //   109: getfield 80	android/content/pm/ActivityInfo:exported	Z
    //   112: iconst_1
    //   113: if_icmpeq +32 -> 145
    //   116: aload_1
    //   117: new 31	java/lang/StringBuilder
    //   120: dup
    //   121: invokespecial 32	java/lang/StringBuilder:<init>	()V
    //   124: ldc 82
    //   126: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: aload_3
    //   130: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: ldc 72
    //   135: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: invokevirtual 41	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   141: invokevirtual 76	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   144: pop
    //   145: aload 17
    //   147: getfield 85	android/content/pm/ActivityInfo:launchMode	I
    //   150: iconst_1
    //   151: if_icmpeq +32 -> 183
    //   154: aload_1
    //   155: new 31	java/lang/StringBuilder
    //   158: dup
    //   159: invokespecial 32	java/lang/StringBuilder:<init>	()V
    //   162: ldc 87
    //   164: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: aload_3
    //   168: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: ldc 89
    //   173: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: invokevirtual 41	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   179: invokevirtual 76	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   182: pop
    //   183: aload 17
    //   185: getfield 93	android/content/pm/ActivityInfo:taskAffinity	Ljava/lang/String;
    //   188: aload_2
    //   189: invokevirtual 98	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   192: ifeq +36 -> 228
    //   195: aload_1
    //   196: new 31	java/lang/StringBuilder
    //   199: dup
    //   200: invokespecial 32	java/lang/StringBuilder:<init>	()V
    //   203: ldc 100
    //   205: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: aload_3
    //   209: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: ldc 102
    //   214: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: aload_2
    //   218: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: invokevirtual 41	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   224: invokevirtual 76	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   227: pop
    //   228: aload_0
    //   229: getfield 23	com/tencent/msdk/doctor/checklist/Push:mContext	Landroid/app/Activity;
    //   232: invokevirtual 51	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   235: new 53	android/content/ComponentName
    //   238: dup
    //   239: aload_2
    //   240: ldc 104
    //   242: invokespecial 56	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   245: sipush 128
    //   248: invokevirtual 108	android/content/pm/PackageManager:getServiceInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ServiceInfo;
    //   251: astore 11
    //   253: aload 11
    //   255: getfield 111	android/content/pm/ServiceInfo:exported	Z
    //   258: iconst_1
    //   259: if_icmpeq +33 -> 292
    //   262: aload_1
    //   263: new 31	java/lang/StringBuilder
    //   266: dup
    //   267: invokespecial 32	java/lang/StringBuilder:<init>	()V
    //   270: ldc 82
    //   272: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: ldc 104
    //   277: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: ldc 72
    //   282: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   285: invokevirtual 41	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   288: invokevirtual 76	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   291: pop
    //   292: aload 11
    //   294: getfield 114	android/content/pm/ServiceInfo:processName	Ljava/lang/String;
    //   297: ldc 116
    //   299: invokevirtual 98	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   302: ifne +33 -> 335
    //   305: aload_1
    //   306: new 31	java/lang/StringBuilder
    //   309: dup
    //   310: invokespecial 32	java/lang/StringBuilder:<init>	()V
    //   313: ldc 118
    //   315: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: ldc 104
    //   320: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   323: ldc 120
    //   325: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: invokevirtual 41	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   331: invokevirtual 76	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   334: pop
    //   335: aload_0
    //   336: getfield 23	com/tencent/msdk/doctor/checklist/Push:mContext	Landroid/app/Activity;
    //   339: invokevirtual 51	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   342: new 53	android/content/ComponentName
    //   345: dup
    //   346: aload_2
    //   347: ldc 122
    //   349: invokespecial 56	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   352: sipush 128
    //   355: invokevirtual 125	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   358: getfield 126	android/content/pm/ActivityInfo:processName	Ljava/lang/String;
    //   361: ldc 116
    //   363: invokevirtual 98	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   366: ifne +33 -> 399
    //   369: aload_1
    //   370: new 31	java/lang/StringBuilder
    //   373: dup
    //   374: invokespecial 32	java/lang/StringBuilder:<init>	()V
    //   377: ldc 118
    //   379: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   382: ldc 122
    //   384: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   387: ldc 120
    //   389: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   392: invokevirtual 41	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   395: invokevirtual 76	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   398: pop
    //   399: aload_1
    //   400: areturn
    //   401: astore 14
    //   403: aload 14
    //   405: invokevirtual 129	java/lang/ClassNotFoundException:printStackTrace	()V
    //   408: aload_1
    //   409: new 31	java/lang/StringBuilder
    //   412: dup
    //   413: invokespecial 32	java/lang/StringBuilder:<init>	()V
    //   416: ldc 131
    //   418: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   421: aload_0
    //   422: getfield 23	com/tencent/msdk/doctor/checklist/Push:mContext	Landroid/app/Activity;
    //   425: invokevirtual 29	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   428: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   431: ldc 133
    //   433: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: invokevirtual 41	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   439: invokevirtual 76	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   442: pop
    //   443: goto -215 -> 228
    //   446: astore 4
    //   448: aload 4
    //   450: invokevirtual 134	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   453: aload_1
    //   454: ldc 136
    //   456: invokevirtual 76	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   459: pop
    //   460: goto -232 -> 228
    //   463: astore 6
    //   465: aload 6
    //   467: invokevirtual 134	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   470: aload 6
    //   472: invokevirtual 137	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   475: ldc 104
    //   477: invokevirtual 141	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   480: ifeq +28 -> 508
    //   483: aload_1
    //   484: new 31	java/lang/StringBuilder
    //   487: dup
    //   488: invokespecial 32	java/lang/StringBuilder:<init>	()V
    //   491: ldc 143
    //   493: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   496: ldc 104
    //   498: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   501: invokevirtual 41	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   504: invokevirtual 76	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   507: pop
    //   508: aload 6
    //   510: invokevirtual 137	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   513: ldc 122
    //   515: invokevirtual 141	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   518: ifeq -183 -> 335
    //   521: aload_1
    //   522: new 31	java/lang/StringBuilder
    //   525: dup
    //   526: invokespecial 32	java/lang/StringBuilder:<init>	()V
    //   529: ldc 145
    //   531: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   534: ldc 122
    //   536: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   539: invokevirtual 41	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   542: invokevirtual 76	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   545: pop
    //   546: goto -211 -> 335
    //   549: astore 8
    //   551: aload 8
    //   553: invokevirtual 134	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   556: aload_1
    //   557: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   36	107	401	java/lang/ClassNotFoundException
    //   107	145	401	java/lang/ClassNotFoundException
    //   145	183	401	java/lang/ClassNotFoundException
    //   183	228	401	java/lang/ClassNotFoundException
    //   36	107	446	android/content/pm/PackageManager$NameNotFoundException
    //   107	145	446	android/content/pm/PackageManager$NameNotFoundException
    //   145	183	446	android/content/pm/PackageManager$NameNotFoundException
    //   183	228	446	android/content/pm/PackageManager$NameNotFoundException
    //   228	292	463	android/content/pm/PackageManager$NameNotFoundException
    //   292	335	463	android/content/pm/PackageManager$NameNotFoundException
    //   335	399	549	android/content/pm/PackageManager$NameNotFoundException
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.doctor.checklist.Push
 * JD-Core Version:    0.6.0
 */