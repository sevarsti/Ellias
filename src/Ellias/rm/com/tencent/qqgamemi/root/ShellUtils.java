package com.tencent.qqgamemi.root;

import java.util.List;

public class ShellUtils
{
  public static final String a = "su";
  public static final String b = "sh";
  public static final String c = "exit\n";
  public static final String d = "\n";

  public static ShellUtils.CommandResult a(String paramString, boolean paramBoolean)
  {
    return a(new String[] { paramString }, paramBoolean, true);
  }

  public static ShellUtils.CommandResult a(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    return a(new String[] { paramString }, paramBoolean1, paramBoolean2);
  }

  public static ShellUtils.CommandResult a(List paramList, boolean paramBoolean)
  {
    if (paramList == null);
    for (String[] arrayOfString = null; ; arrayOfString = (String[])paramList.toArray(new String[0]))
      return a(arrayOfString, paramBoolean, true);
  }

  public static ShellUtils.CommandResult a(List paramList, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramList == null);
    for (String[] arrayOfString = null; ; arrayOfString = (String[])paramList.toArray(new String[0]))
      return a(arrayOfString, paramBoolean1, paramBoolean2);
  }

  public static ShellUtils.CommandResult a(String[] paramArrayOfString, boolean paramBoolean)
  {
    return a(paramArrayOfString, paramBoolean, true);
  }

  // ERROR //
  public static ShellUtils.CommandResult a(String[] paramArrayOfString, boolean paramBoolean1, boolean paramBoolean2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: iconst_m1
    //   3: istore 4
    //   5: aload_0
    //   6: ifnull +8 -> 14
    //   9: aload_0
    //   10: arraylength
    //   11: ifne +15 -> 26
    //   14: new 45	com/tencent/qqgamemi/root/ShellUtils$CommandResult
    //   17: dup
    //   18: iload 4
    //   20: aconst_null
    //   21: aconst_null
    //   22: invokespecial 48	com/tencent/qqgamemi/root/ShellUtils$CommandResult:<init>	(ILjava/lang/String;Ljava/lang/String;)V
    //   25: areturn
    //   26: invokestatic 54	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   29: astore 22
    //   31: iload_1
    //   32: ifeq +65 -> 97
    //   35: ldc 8
    //   37: astore 23
    //   39: aload 22
    //   41: aload 23
    //   43: invokevirtual 58	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   46: astore 24
    //   48: aload 24
    //   50: astore 12
    //   52: new 60	java/io/DataOutputStream
    //   55: dup
    //   56: aload 12
    //   58: invokevirtual 66	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   61: invokespecial 69	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   64: astore 14
    //   66: aload_0
    //   67: arraylength
    //   68: istore 27
    //   70: iconst_0
    //   71: istore 28
    //   73: iload 28
    //   75: iload 27
    //   77: if_icmpge +149 -> 226
    //   80: aload_0
    //   81: iload 28
    //   83: aaload
    //   84: astore 29
    //   86: aload 29
    //   88: ifnonnull +16 -> 104
    //   91: iinc 28 1
    //   94: goto -21 -> 73
    //   97: ldc 11
    //   99: astore 23
    //   101: goto -62 -> 39
    //   104: aload 14
    //   106: aload 29
    //   108: invokevirtual 73	java/lang/String:getBytes	()[B
    //   111: invokevirtual 77	java/io/DataOutputStream:write	([B)V
    //   114: aload 14
    //   116: ldc 17
    //   118: invokevirtual 81	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   121: aload 14
    //   123: invokevirtual 84	java/io/DataOutputStream:flush	()V
    //   126: goto -35 -> 91
    //   129: astore 26
    //   131: aload 14
    //   133: astore 9
    //   135: aconst_null
    //   136: astore 8
    //   138: aconst_null
    //   139: astore 10
    //   141: aconst_null
    //   142: astore 11
    //   144: aload 26
    //   146: astore 6
    //   148: aconst_null
    //   149: astore 7
    //   151: aload 6
    //   153: invokevirtual 87	java/io/IOException:printStackTrace	()V
    //   156: aload 9
    //   158: ifnull +8 -> 166
    //   161: aload 9
    //   163: invokevirtual 90	java/io/DataOutputStream:close	()V
    //   166: aload 11
    //   168: ifnull +8 -> 176
    //   171: aload 11
    //   173: invokevirtual 93	java/io/BufferedReader:close	()V
    //   176: aload 10
    //   178: ifnull +8 -> 186
    //   181: aload 10
    //   183: invokevirtual 93	java/io/BufferedReader:close	()V
    //   186: aload 12
    //   188: ifnull +8 -> 196
    //   191: aload 12
    //   193: invokevirtual 96	java/lang/Process:destroy	()V
    //   196: aload 8
    //   198: ifnonnull +366 -> 564
    //   201: aconst_null
    //   202: astore 17
    //   204: aload 7
    //   206: ifnonnull +368 -> 574
    //   209: aconst_null
    //   210: astore 18
    //   212: new 45	com/tencent/qqgamemi/root/ShellUtils$CommandResult
    //   215: dup
    //   216: iload 4
    //   218: aload 17
    //   220: aload 18
    //   222: invokespecial 48	com/tencent/qqgamemi/root/ShellUtils$CommandResult:<init>	(ILjava/lang/String;Ljava/lang/String;)V
    //   225: areturn
    //   226: aload 14
    //   228: ldc 14
    //   230: invokevirtual 81	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   233: aload 14
    //   235: invokevirtual 84	java/io/DataOutputStream:flush	()V
    //   238: aload 12
    //   240: invokevirtual 100	java/lang/Process:waitFor	()I
    //   243: istore 4
    //   245: iload_2
    //   246: ifeq +677 -> 923
    //   249: new 102	java/lang/StringBuilder
    //   252: dup
    //   253: invokespecial 103	java/lang/StringBuilder:<init>	()V
    //   256: astore 30
    //   258: new 102	java/lang/StringBuilder
    //   261: dup
    //   262: invokespecial 103	java/lang/StringBuilder:<init>	()V
    //   265: astore 31
    //   267: new 92	java/io/BufferedReader
    //   270: dup
    //   271: new 105	java/io/InputStreamReader
    //   274: dup
    //   275: aload 12
    //   277: invokevirtual 109	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   280: invokespecial 112	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   283: invokespecial 115	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   286: astore 11
    //   288: new 92	java/io/BufferedReader
    //   291: dup
    //   292: new 105	java/io/InputStreamReader
    //   295: dup
    //   296: aload 12
    //   298: invokevirtual 118	java/lang/Process:getErrorStream	()Ljava/io/InputStream;
    //   301: invokespecial 112	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   304: invokespecial 115	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   307: astore 10
    //   309: aload 11
    //   311: invokevirtual 122	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   314: astore 34
    //   316: aload 34
    //   318: ifnull +14 -> 332
    //   321: aload 30
    //   323: aload 34
    //   325: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: pop
    //   329: goto -20 -> 309
    //   332: aload 10
    //   334: invokevirtual 122	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   337: astore 36
    //   339: aload 36
    //   341: ifnull +80 -> 421
    //   344: aload 31
    //   346: aload 36
    //   348: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: pop
    //   352: goto -20 -> 332
    //   355: astore 32
    //   357: aload 31
    //   359: astore 7
    //   361: aload 30
    //   363: astore 8
    //   365: aload 14
    //   367: astore 9
    //   369: aload 32
    //   371: astore 20
    //   373: aload 20
    //   375: invokevirtual 127	java/lang/Exception:printStackTrace	()V
    //   378: aload 9
    //   380: ifnull +8 -> 388
    //   383: aload 9
    //   385: invokevirtual 90	java/io/DataOutputStream:close	()V
    //   388: aload 11
    //   390: ifnull +8 -> 398
    //   393: aload 11
    //   395: invokevirtual 93	java/io/BufferedReader:close	()V
    //   398: aload 10
    //   400: ifnull +8 -> 408
    //   403: aload 10
    //   405: invokevirtual 93	java/io/BufferedReader:close	()V
    //   408: aload 12
    //   410: ifnull -214 -> 196
    //   413: aload 12
    //   415: invokevirtual 96	java/lang/Process:destroy	()V
    //   418: goto -222 -> 196
    //   421: aload 31
    //   423: astore 7
    //   425: aload 30
    //   427: astore 8
    //   429: aload 14
    //   431: ifnull +8 -> 439
    //   434: aload 14
    //   436: invokevirtual 90	java/io/DataOutputStream:close	()V
    //   439: aload 11
    //   441: ifnull +8 -> 449
    //   444: aload 11
    //   446: invokevirtual 93	java/io/BufferedReader:close	()V
    //   449: aload 10
    //   451: ifnull +8 -> 459
    //   454: aload 10
    //   456: invokevirtual 93	java/io/BufferedReader:close	()V
    //   459: aload 12
    //   461: ifnull -265 -> 196
    //   464: aload 12
    //   466: invokevirtual 96	java/lang/Process:destroy	()V
    //   469: goto -273 -> 196
    //   472: astore 38
    //   474: aload 38
    //   476: invokevirtual 87	java/io/IOException:printStackTrace	()V
    //   479: goto -20 -> 459
    //   482: astore 16
    //   484: aload 16
    //   486: invokevirtual 87	java/io/IOException:printStackTrace	()V
    //   489: goto -303 -> 186
    //   492: astore 21
    //   494: aload 21
    //   496: invokevirtual 87	java/io/IOException:printStackTrace	()V
    //   499: goto -91 -> 408
    //   502: astore 13
    //   504: aconst_null
    //   505: astore 14
    //   507: aconst_null
    //   508: astore 11
    //   510: aconst_null
    //   511: astore 12
    //   513: aload 14
    //   515: ifnull +8 -> 523
    //   518: aload 14
    //   520: invokevirtual 90	java/io/DataOutputStream:close	()V
    //   523: aload 11
    //   525: ifnull +8 -> 533
    //   528: aload 11
    //   530: invokevirtual 93	java/io/BufferedReader:close	()V
    //   533: aload_3
    //   534: ifnull +7 -> 541
    //   537: aload_3
    //   538: invokevirtual 93	java/io/BufferedReader:close	()V
    //   541: aload 12
    //   543: ifnull +8 -> 551
    //   546: aload 12
    //   548: invokevirtual 96	java/lang/Process:destroy	()V
    //   551: aload 13
    //   553: athrow
    //   554: astore 15
    //   556: aload 15
    //   558: invokevirtual 87	java/io/IOException:printStackTrace	()V
    //   561: goto -20 -> 541
    //   564: aload 8
    //   566: invokevirtual 130	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   569: astore 17
    //   571: goto -367 -> 204
    //   574: aload 7
    //   576: invokevirtual 130	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   579: astore 18
    //   581: goto -369 -> 212
    //   584: astore 13
    //   586: aconst_null
    //   587: astore_3
    //   588: aconst_null
    //   589: astore 14
    //   591: aconst_null
    //   592: astore 11
    //   594: goto -81 -> 513
    //   597: astore 13
    //   599: aconst_null
    //   600: astore_3
    //   601: aconst_null
    //   602: astore 11
    //   604: goto -91 -> 513
    //   607: astore 13
    //   609: aconst_null
    //   610: astore_3
    //   611: goto -98 -> 513
    //   614: astore 13
    //   616: aload 10
    //   618: astore_3
    //   619: goto -106 -> 513
    //   622: astore 13
    //   624: aload 9
    //   626: astore 14
    //   628: aload 10
    //   630: astore_3
    //   631: goto -118 -> 513
    //   634: astore 19
    //   636: aload 19
    //   638: astore 20
    //   640: aconst_null
    //   641: astore 7
    //   643: aconst_null
    //   644: astore 8
    //   646: aconst_null
    //   647: astore 9
    //   649: aconst_null
    //   650: astore 10
    //   652: aconst_null
    //   653: astore 11
    //   655: aconst_null
    //   656: astore 12
    //   658: goto -285 -> 373
    //   661: astore 48
    //   663: aload 48
    //   665: astore 20
    //   667: aconst_null
    //   668: astore 7
    //   670: aconst_null
    //   671: astore 8
    //   673: aconst_null
    //   674: astore 9
    //   676: aconst_null
    //   677: astore 10
    //   679: aconst_null
    //   680: astore 11
    //   682: goto -309 -> 373
    //   685: astore 25
    //   687: aload 14
    //   689: astore 9
    //   691: aload 25
    //   693: astore 20
    //   695: aconst_null
    //   696: astore 7
    //   698: aconst_null
    //   699: astore 8
    //   701: aconst_null
    //   702: astore 10
    //   704: aconst_null
    //   705: astore 11
    //   707: goto -334 -> 373
    //   710: astore 46
    //   712: aload 30
    //   714: astore 8
    //   716: aload 14
    //   718: astore 9
    //   720: aload 46
    //   722: astore 20
    //   724: aconst_null
    //   725: astore 7
    //   727: aconst_null
    //   728: astore 10
    //   730: aconst_null
    //   731: astore 11
    //   733: goto -360 -> 373
    //   736: astore 44
    //   738: aload 30
    //   740: astore 8
    //   742: aload 14
    //   744: astore 9
    //   746: aload 44
    //   748: astore 20
    //   750: aload 31
    //   752: astore 7
    //   754: aconst_null
    //   755: astore 10
    //   757: aconst_null
    //   758: astore 11
    //   760: goto -387 -> 373
    //   763: astore 41
    //   765: aload 14
    //   767: astore 42
    //   769: aload 41
    //   771: astore 20
    //   773: aload 31
    //   775: astore 7
    //   777: aload 30
    //   779: astore 8
    //   781: aload 42
    //   783: astore 9
    //   785: aconst_null
    //   786: astore 10
    //   788: goto -415 -> 373
    //   791: astore 5
    //   793: aload 5
    //   795: astore 6
    //   797: aconst_null
    //   798: astore 7
    //   800: aconst_null
    //   801: astore 8
    //   803: aconst_null
    //   804: astore 9
    //   806: aconst_null
    //   807: astore 10
    //   809: aconst_null
    //   810: astore 11
    //   812: aconst_null
    //   813: astore 12
    //   815: goto -664 -> 151
    //   818: astore 47
    //   820: aload 47
    //   822: astore 6
    //   824: aconst_null
    //   825: astore 7
    //   827: aconst_null
    //   828: astore 8
    //   830: aconst_null
    //   831: astore 9
    //   833: aconst_null
    //   834: astore 10
    //   836: aconst_null
    //   837: astore 11
    //   839: goto -688 -> 151
    //   842: astore 45
    //   844: aload 30
    //   846: astore 8
    //   848: aload 14
    //   850: astore 9
    //   852: aload 45
    //   854: astore 6
    //   856: aconst_null
    //   857: astore 7
    //   859: aconst_null
    //   860: astore 10
    //   862: aconst_null
    //   863: astore 11
    //   865: goto -714 -> 151
    //   868: astore 43
    //   870: aload 30
    //   872: astore 8
    //   874: aload 14
    //   876: astore 9
    //   878: aload 43
    //   880: astore 6
    //   882: aload 31
    //   884: astore 7
    //   886: aconst_null
    //   887: astore 10
    //   889: aconst_null
    //   890: astore 11
    //   892: goto -741 -> 151
    //   895: astore 39
    //   897: aload 14
    //   899: astore 40
    //   901: aload 39
    //   903: astore 6
    //   905: aload 31
    //   907: astore 7
    //   909: aload 30
    //   911: astore 8
    //   913: aload 40
    //   915: astore 9
    //   917: aconst_null
    //   918: astore 10
    //   920: goto -769 -> 151
    //   923: aconst_null
    //   924: astore 7
    //   926: aconst_null
    //   927: astore 8
    //   929: aconst_null
    //   930: astore 10
    //   932: aconst_null
    //   933: astore 11
    //   935: goto -506 -> 429
    //   938: astore 33
    //   940: aload 31
    //   942: astore 7
    //   944: aload 30
    //   946: astore 8
    //   948: aload 14
    //   950: astore 9
    //   952: aload 33
    //   954: astore 6
    //   956: goto -805 -> 151
    //
    // Exception table:
    //   from	to	target	type
    //   66	70	129	java/io/IOException
    //   80	86	129	java/io/IOException
    //   104	126	129	java/io/IOException
    //   226	245	129	java/io/IOException
    //   249	258	129	java/io/IOException
    //   309	316	355	java/lang/Exception
    //   321	329	355	java/lang/Exception
    //   332	339	355	java/lang/Exception
    //   344	352	355	java/lang/Exception
    //   434	439	472	java/io/IOException
    //   444	449	472	java/io/IOException
    //   454	459	472	java/io/IOException
    //   161	166	482	java/io/IOException
    //   171	176	482	java/io/IOException
    //   181	186	482	java/io/IOException
    //   383	388	492	java/io/IOException
    //   393	398	492	java/io/IOException
    //   403	408	492	java/io/IOException
    //   26	31	502	finally
    //   39	48	502	finally
    //   518	523	554	java/io/IOException
    //   528	533	554	java/io/IOException
    //   537	541	554	java/io/IOException
    //   52	66	584	finally
    //   66	70	597	finally
    //   80	86	597	finally
    //   104	126	597	finally
    //   226	245	597	finally
    //   249	258	597	finally
    //   258	267	597	finally
    //   267	288	597	finally
    //   288	309	607	finally
    //   309	316	614	finally
    //   321	329	614	finally
    //   332	339	614	finally
    //   344	352	614	finally
    //   151	156	622	finally
    //   373	378	622	finally
    //   26	31	634	java/lang/Exception
    //   39	48	634	java/lang/Exception
    //   52	66	661	java/lang/Exception
    //   66	70	685	java/lang/Exception
    //   80	86	685	java/lang/Exception
    //   104	126	685	java/lang/Exception
    //   226	245	685	java/lang/Exception
    //   249	258	685	java/lang/Exception
    //   258	267	710	java/lang/Exception
    //   267	288	736	java/lang/Exception
    //   288	309	763	java/lang/Exception
    //   26	31	791	java/io/IOException
    //   39	48	791	java/io/IOException
    //   52	66	818	java/io/IOException
    //   258	267	842	java/io/IOException
    //   267	288	868	java/io/IOException
    //   288	309	895	java/io/IOException
    //   309	316	938	java/io/IOException
    //   321	329	938	java/io/IOException
    //   332	339	938	java/io/IOException
    //   344	352	938	java/io/IOException
  }

  public static boolean a()
  {
    return a("echo root", true, false).a == 0;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.root.ShellUtils
 * JD-Core Version:    0.6.0
 */