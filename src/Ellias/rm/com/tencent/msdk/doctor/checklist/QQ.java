package com.tencent.msdk.doctor.checklist;

import android.app.Activity;
import com.tencent.msdk.doctor.CheckBase;

public class QQ extends CheckBase
{
  public QQ(Activity paramActivity)
  {
    super(paramActivity);
  }

  // ERROR //
  public java.util.ArrayList<java.lang.String> check()
  {
    // Byte code:
    //   0: new 14	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 17	java/util/ArrayList:<init>	()V
    //   7: astore_1
    //   8: invokestatic 23	com/tencent/msdk/WeGame:getInstance	()Lcom/tencent/msdk/WeGame;
    //   11: getfield 27	com/tencent/msdk/WeGame:qq_appid	Ljava/lang/String;
    //   14: invokestatic 33	com/tencent/msdk/tools/T:ckIsEmpty	(Ljava/lang/String;)Z
    //   17: ifeq +10 -> 27
    //   20: aload_1
    //   21: ldc 35
    //   23: invokevirtual 39	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   26: pop
    //   27: invokestatic 23	com/tencent/msdk/WeGame:getInstance	()Lcom/tencent/msdk/WeGame;
    //   30: getfield 42	com/tencent/msdk/WeGame:qqAppKey	Ljava/lang/String;
    //   33: invokestatic 33	com/tencent/msdk/tools/T:ckIsEmpty	(Ljava/lang/String;)Z
    //   36: ifeq +10 -> 46
    //   39: aload_1
    //   40: ldc 44
    //   42: invokevirtual 39	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   45: pop
    //   46: invokestatic 23	com/tencent/msdk/WeGame:getInstance	()Lcom/tencent/msdk/WeGame;
    //   49: getfield 47	com/tencent/msdk/WeGame:offerId	Ljava/lang/String;
    //   52: invokestatic 33	com/tencent/msdk/tools/T:ckIsEmpty	(Ljava/lang/String;)Z
    //   55: ifeq +10 -> 65
    //   58: aload_1
    //   59: ldc 49
    //   61: invokevirtual 39	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   64: pop
    //   65: aload_0
    //   66: getfield 53	com/tencent/msdk/doctor/checklist/QQ:mContext	Landroid/app/Activity;
    //   69: invokevirtual 59	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   72: astore_2
    //   73: getstatic 64	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   76: astore_3
    //   77: aload_0
    //   78: getfield 53	com/tencent/msdk/doctor/checklist/QQ:mContext	Landroid/app/Activity;
    //   81: invokevirtual 68	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   84: new 70	android/content/ComponentName
    //   87: dup
    //   88: aload_2
    //   89: ldc 72
    //   91: invokespecial 75	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   94: sipush 128
    //   97: invokevirtual 81	android/content/pm/PackageManager:getActivityInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   100: astore 25
    //   102: aload 25
    //   104: getfield 87	android/content/pm/ActivityInfo:launchMode	I
    //   107: iconst_2
    //   108: if_icmpeq +33 -> 141
    //   111: aload_1
    //   112: new 89	java/lang/StringBuilder
    //   115: dup
    //   116: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   119: ldc 92
    //   121: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: ldc 72
    //   126: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: ldc 98
    //   131: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   137: invokevirtual 39	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   140: pop
    //   141: sipush 128
    //   144: aload 25
    //   146: getfield 104	android/content/pm/ActivityInfo:flags	I
    //   149: iand
    //   150: sipush 128
    //   153: if_icmpeq +33 -> 186
    //   156: aload_1
    //   157: new 89	java/lang/StringBuilder
    //   160: dup
    //   161: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   164: ldc 106
    //   166: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: ldc 72
    //   171: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: ldc 108
    //   176: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   182: invokevirtual 39	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   185: pop
    //   186: aload_0
    //   187: getfield 53	com/tencent/msdk/doctor/checklist/QQ:mContext	Landroid/app/Activity;
    //   190: invokevirtual 68	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   193: new 70	android/content/ComponentName
    //   196: dup
    //   197: aload_2
    //   198: ldc 110
    //   200: invokespecial 75	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   203: sipush 128
    //   206: invokevirtual 81	android/content/pm/PackageManager:getActivityInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   209: astore 8
    //   211: aload 8
    //   213: getfield 113	android/content/pm/ActivityInfo:screenOrientation	I
    //   216: iconst_1
    //   217: if_icmpeq +33 -> 250
    //   220: aload_1
    //   221: new 89	java/lang/StringBuilder
    //   224: dup
    //   225: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   228: ldc 115
    //   230: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: ldc 110
    //   235: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: ldc 117
    //   240: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   246: invokevirtual 39	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   249: pop
    //   250: aload_3
    //   251: invokestatic 123	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   254: istore 9
    //   256: iload 9
    //   258: bipush 13
    //   260: if_icmpge +368 -> 628
    //   263: sipush 160
    //   266: istore 10
    //   268: iload 10
    //   270: aload 8
    //   272: getfield 126	android/content/pm/ActivityInfo:configChanges	I
    //   275: iand
    //   276: iload 10
    //   278: if_icmpeq +45 -> 323
    //   281: iload 9
    //   283: bipush 13
    //   285: if_icmpge +351 -> 636
    //   288: aload_1
    //   289: new 89	java/lang/StringBuilder
    //   292: dup
    //   293: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   296: ldc 128
    //   298: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   301: ldc 110
    //   303: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: ldc 130
    //   308: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   311: ldc 132
    //   313: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   316: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   319: invokevirtual 39	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   322: pop
    //   323: aload 8
    //   325: getfield 135	android/content/pm/ActivityInfo:theme	I
    //   328: ldc 136
    //   330: if_icmpeq +33 -> 363
    //   333: aload_1
    //   334: new 89	java/lang/StringBuilder
    //   337: dup
    //   338: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   341: ldc 138
    //   343: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: ldc 110
    //   348: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: ldc 140
    //   353: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   356: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   359: invokevirtual 39	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   362: pop
    //   363: new 142	android/content/Intent
    //   366: dup
    //   367: invokespecial 143	android/content/Intent:<init>	()V
    //   370: astore 11
    //   372: aload 11
    //   374: new 89	java/lang/StringBuilder
    //   377: dup
    //   378: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   381: ldc 145
    //   383: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   386: invokestatic 23	com/tencent/msdk/WeGame:getInstance	()Lcom/tencent/msdk/WeGame;
    //   389: getfield 27	com/tencent/msdk/WeGame:qq_appid	Ljava/lang/String;
    //   392: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   395: ldc 147
    //   397: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   400: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   403: invokestatic 153	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   406: invokevirtual 157	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   409: pop
    //   410: aload_0
    //   411: aload 11
    //   413: ldc 72
    //   415: invokevirtual 161	com/tencent/msdk/doctor/checklist/QQ:queryIntentFilter	(Landroid/content/Intent;Ljava/lang/String;)Z
    //   418: ifne +10 -> 428
    //   421: aload_1
    //   422: ldc 163
    //   424: invokevirtual 39	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   427: pop
    //   428: aload 11
    //   430: ldc 165
    //   432: invokevirtual 169	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
    //   435: pop
    //   436: aload 11
    //   438: ldc 171
    //   440: invokevirtual 174	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
    //   443: pop
    //   444: aload 11
    //   446: ldc 176
    //   448: invokevirtual 174	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
    //   451: pop
    //   452: aload_0
    //   453: aload 11
    //   455: ldc 72
    //   457: invokevirtual 161	com/tencent/msdk/doctor/checklist/QQ:queryIntentFilter	(Landroid/content/Intent;Ljava/lang/String;)Z
    //   460: ifne +10 -> 470
    //   463: aload_1
    //   464: ldc 178
    //   466: invokevirtual 39	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   469: pop
    //   470: iload 10
    //   472: aload_0
    //   473: getfield 53	com/tencent/msdk/doctor/checklist/QQ:mContext	Landroid/app/Activity;
    //   476: invokevirtual 68	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   479: new 70	android/content/ComponentName
    //   482: dup
    //   483: aload_2
    //   484: aload_0
    //   485: getfield 53	com/tencent/msdk/doctor/checklist/QQ:mContext	Landroid/app/Activity;
    //   488: invokevirtual 184	java/lang/Object:getClass	()Ljava/lang/Class;
    //   491: invokevirtual 189	java/lang/Class:getName	()Ljava/lang/String;
    //   494: invokespecial 75	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   497: sipush 128
    //   500: invokevirtual 81	android/content/pm/PackageManager:getActivityInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   503: getfield 126	android/content/pm/ActivityInfo:configChanges	I
    //   506: iand
    //   507: iload 10
    //   509: if_icmpeq +48 -> 557
    //   512: iload 9
    //   514: bipush 13
    //   516: if_icmpge +158 -> 674
    //   519: aload_1
    //   520: new 89	java/lang/StringBuilder
    //   523: dup
    //   524: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   527: ldc 191
    //   529: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   532: aload_0
    //   533: getfield 53	com/tencent/msdk/doctor/checklist/QQ:mContext	Landroid/app/Activity;
    //   536: invokevirtual 184	java/lang/Object:getClass	()Ljava/lang/Class;
    //   539: invokevirtual 189	java/lang/Class:getName	()Ljava/lang/String;
    //   542: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   545: ldc 193
    //   547: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   550: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   553: invokevirtual 39	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   556: pop
    //   557: aload_1
    //   558: areturn
    //   559: astore 4
    //   561: aload 4
    //   563: invokevirtual 196	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   566: aload_1
    //   567: new 89	java/lang/StringBuilder
    //   570: dup
    //   571: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   574: ldc 198
    //   576: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   579: ldc 72
    //   581: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   584: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   587: invokevirtual 39	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   590: pop
    //   591: goto -405 -> 186
    //   594: astore 6
    //   596: aload 6
    //   598: invokevirtual 196	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   601: aload_1
    //   602: new 89	java/lang/StringBuilder
    //   605: dup
    //   606: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   609: ldc 198
    //   611: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   614: ldc 110
    //   616: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   619: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   622: invokevirtual 39	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   625: pop
    //   626: aload_1
    //   627: areturn
    //   628: sipush 1184
    //   631: istore 10
    //   633: goto -365 -> 268
    //   636: aload_1
    //   637: new 89	java/lang/StringBuilder
    //   640: dup
    //   641: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   644: ldc 128
    //   646: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   649: ldc 110
    //   651: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   654: ldc 130
    //   656: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   659: ldc 200
    //   661: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   664: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   667: invokevirtual 39	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   670: pop
    //   671: goto -348 -> 323
    //   674: aload_1
    //   675: new 89	java/lang/StringBuilder
    //   678: dup
    //   679: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   682: ldc 128
    //   684: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   687: aload_0
    //   688: getfield 53	com/tencent/msdk/doctor/checklist/QQ:mContext	Landroid/app/Activity;
    //   691: invokevirtual 184	java/lang/Object:getClass	()Ljava/lang/Class;
    //   694: invokevirtual 189	java/lang/Class:getName	()Ljava/lang/String;
    //   697: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   700: ldc 202
    //   702: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   705: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   708: invokevirtual 39	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   711: pop
    //   712: aload_1
    //   713: areturn
    //   714: astore 16
    //   716: aload 16
    //   718: invokevirtual 196	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   721: aload_1
    //   722: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   77	141	559	android/content/pm/PackageManager$NameNotFoundException
    //   141	186	559	android/content/pm/PackageManager$NameNotFoundException
    //   186	250	594	android/content/pm/PackageManager$NameNotFoundException
    //   470	512	714	android/content/pm/PackageManager$NameNotFoundException
    //   519	557	714	android/content/pm/PackageManager$NameNotFoundException
    //   674	712	714	android/content/pm/PackageManager$NameNotFoundException
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.doctor.checklist.QQ
 * JD-Core Version:    0.6.0
 */