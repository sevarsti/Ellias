package com.tencent.feedback.common.a;

import android.content.ContentValues;
import android.database.Cursor;
import com.tencent.feedback.common.e;
import java.util.ArrayList;
import java.util.List;

public class a
{
  private long a = -1L;
  private int b = -1;
  private int c = -1;
  private long d = -1L;
  private byte[] e = null;
  private long f = 0L;
  private String g = null;
  private int h = 0;
  private int i = 0;
  private int j = -1;

  public a()
  {
  }

  public a(int paramInt1, int paramInt2, long paramLong, byte[] paramArrayOfByte)
  {
    this.b = paramInt1;
    this.c = 0;
    this.d = paramLong;
    this.e = paramArrayOfByte;
    if (paramArrayOfByte != null)
      this.f = paramArrayOfByte.length;
  }

  // ERROR //
  public static int a(android.content.Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +14 -> 15
    //   4: ldc 50
    //   6: iconst_0
    //   7: anewarray 4	java/lang/Object
    //   10: invokestatic 55	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   13: iconst_m1
    //   14: ireturn
    //   15: iconst_1
    //   16: anewarray 4	java/lang/Object
    //   19: astore_2
    //   20: aload_2
    //   21: iconst_0
    //   22: iconst_1
    //   23: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   26: aastore
    //   27: ldc 63
    //   29: aload_2
    //   30: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   33: new 67	com/tencent/feedback/common/a/c
    //   36: dup
    //   37: aload_0
    //   38: invokespecial 70	com/tencent/feedback/common/a/c:<init>	(Landroid/content/Context;)V
    //   41: astore_3
    //   42: aload_3
    //   43: invokevirtual 74	com/tencent/feedback/common/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   46: astore 14
    //   48: aload 14
    //   50: astore 7
    //   52: aload 7
    //   54: ifnonnull +66 -> 120
    //   57: ldc 76
    //   59: iconst_0
    //   60: anewarray 4	java/lang/Object
    //   63: invokestatic 78	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   66: ldc 80
    //   68: iconst_0
    //   69: anewarray 4	java/lang/Object
    //   72: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   75: aload 7
    //   77: ifnull +16 -> 93
    //   80: aload 7
    //   82: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   85: ifeq +8 -> 93
    //   88: aload 7
    //   90: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   93: aload_3
    //   94: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   97: iconst_1
    //   98: anewarray 4	java/lang/Object
    //   101: astore 20
    //   103: aload 20
    //   105: iconst_0
    //   106: iconst_0
    //   107: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   110: aastore
    //   111: ldc 94
    //   113: aload 20
    //   115: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   118: iconst_m1
    //   119: ireturn
    //   120: new 96	java/lang/StringBuilder
    //   123: dup
    //   124: ldc 98
    //   126: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   129: iconst_1
    //   130: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   133: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   136: astore 16
    //   138: aload 7
    //   140: ldc 111
    //   142: aload 16
    //   144: aconst_null
    //   145: invokevirtual 115	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   148: istore 17
    //   150: iload 17
    //   152: istore 6
    //   154: iconst_2
    //   155: anewarray 4	java/lang/Object
    //   158: astore 18
    //   160: aload 18
    //   162: iconst_0
    //   163: iload 6
    //   165: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   168: aastore
    //   169: aload 16
    //   171: ifnonnull +7 -> 178
    //   174: ldc 117
    //   176: astore 16
    //   178: aload 18
    //   180: iconst_1
    //   181: aload 16
    //   183: aastore
    //   184: ldc 119
    //   186: aload 18
    //   188: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   191: aload 7
    //   193: ifnull +16 -> 209
    //   196: aload 7
    //   198: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   201: ifeq +8 -> 209
    //   204: aload 7
    //   206: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   209: aload_3
    //   210: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   213: iconst_1
    //   214: anewarray 4	java/lang/Object
    //   217: astore 19
    //   219: aload 19
    //   221: iconst_0
    //   222: iload 6
    //   224: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   227: aastore
    //   228: ldc 94
    //   230: aload 19
    //   232: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   235: iload 6
    //   237: ireturn
    //   238: astore 21
    //   240: aconst_null
    //   241: astore 7
    //   243: aconst_null
    //   244: astore_3
    //   245: aload 21
    //   247: astore 5
    //   249: iconst_0
    //   250: istore 6
    //   252: ldc 121
    //   254: iconst_0
    //   255: anewarray 4	java/lang/Object
    //   258: invokestatic 78	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   261: iconst_1
    //   262: anewarray 4	java/lang/Object
    //   265: astore 12
    //   267: aload 12
    //   269: iconst_0
    //   270: aload 5
    //   272: invokevirtual 124	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   275: aastore
    //   276: ldc 126
    //   278: aload 12
    //   280: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   283: aload 5
    //   285: invokevirtual 129	java/lang/Throwable:printStackTrace	()V
    //   288: aload 7
    //   290: ifnull +16 -> 306
    //   293: aload 7
    //   295: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   298: ifeq +8 -> 306
    //   301: aload 7
    //   303: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   306: aload_3
    //   307: ifnull +7 -> 314
    //   310: aload_3
    //   311: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   314: iconst_1
    //   315: anewarray 4	java/lang/Object
    //   318: astore 13
    //   320: aload 13
    //   322: iconst_0
    //   323: iload 6
    //   325: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   328: aastore
    //   329: ldc 94
    //   331: aload 13
    //   333: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   336: iload 6
    //   338: ireturn
    //   339: astore 10
    //   341: aconst_null
    //   342: astore 7
    //   344: aconst_null
    //   345: astore_3
    //   346: iconst_0
    //   347: istore 9
    //   349: aload 7
    //   351: ifnull +16 -> 367
    //   354: aload 7
    //   356: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   359: ifeq +8 -> 367
    //   362: aload 7
    //   364: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   367: aload_3
    //   368: ifnull +7 -> 375
    //   371: aload_3
    //   372: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   375: iconst_1
    //   376: anewarray 4	java/lang/Object
    //   379: astore 11
    //   381: aload 11
    //   383: iconst_0
    //   384: iload 9
    //   386: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   389: aastore
    //   390: ldc 94
    //   392: aload 11
    //   394: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   397: aload 10
    //   399: athrow
    //   400: astore 10
    //   402: iconst_0
    //   403: istore 9
    //   405: aconst_null
    //   406: astore 7
    //   408: goto -59 -> 349
    //   411: astore 10
    //   413: iconst_0
    //   414: istore 9
    //   416: goto -67 -> 349
    //   419: astore 8
    //   421: iload 6
    //   423: istore 9
    //   425: aload 8
    //   427: astore 10
    //   429: goto -80 -> 349
    //   432: astore 4
    //   434: aload 4
    //   436: astore 5
    //   438: iconst_0
    //   439: istore 6
    //   441: aconst_null
    //   442: astore 7
    //   444: goto -192 -> 252
    //   447: astore 15
    //   449: aload 15
    //   451: astore 5
    //   453: iconst_0
    //   454: istore 6
    //   456: goto -204 -> 252
    //   459: astore 5
    //   461: goto -209 -> 252
    //
    // Exception table:
    //   from	to	target	type
    //   33	42	238	java/lang/Throwable
    //   33	42	339	finally
    //   42	48	400	finally
    //   57	75	411	finally
    //   120	150	411	finally
    //   154	169	419	finally
    //   178	191	419	finally
    //   252	288	419	finally
    //   42	48	432	java/lang/Throwable
    //   57	75	447	java/lang/Throwable
    //   120	150	447	java/lang/Throwable
    //   154	169	459	java/lang/Throwable
    //   178	191	459	java/lang/Throwable
  }

  // ERROR //
  public static int a(android.content.Context paramContext, int[] paramArrayOfInt, long paramLong1, long paramLong2, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: ldc 132
    //   5: iconst_0
    //   6: anewarray 4	java/lang/Object
    //   9: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   12: aload_0
    //   13: ifnonnull +18 -> 31
    //   16: ldc 134
    //   18: iconst_0
    //   19: anewarray 4	java/lang/Object
    //   22: invokestatic 136	com/tencent/feedback/common/e:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   25: iconst_m1
    //   26: istore 10
    //   28: iload 10
    //   30: ireturn
    //   31: lload_2
    //   32: lload 4
    //   34: lcmp
    //   35: istore 9
    //   37: iconst_0
    //   38: istore 10
    //   40: iload 9
    //   42: ifgt -14 -> 28
    //   45: new 96	java/lang/StringBuilder
    //   48: dup
    //   49: ldc 138
    //   51: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   54: lload_2
    //   55: invokevirtual 141	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   58: ldc 143
    //   60: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: ldc 148
    //   65: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: lload 4
    //   70: invokevirtual 141	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   73: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   76: astore 11
    //   78: iload 6
    //   80: iflt +30 -> 110
    //   83: new 96	java/lang/StringBuilder
    //   86: dup
    //   87: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   90: aload 11
    //   92: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: ldc 151
    //   97: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: iload 6
    //   102: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   105: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   108: astore 11
    //   110: iload 7
    //   112: iflt +30 -> 142
    //   115: new 96	java/lang/StringBuilder
    //   118: dup
    //   119: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   122: aload 11
    //   124: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: ldc 153
    //   129: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: iload 7
    //   134: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   137: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   140: astore 11
    //   142: aload_1
    //   143: ifnull +360 -> 503
    //   146: aload_1
    //   147: arraylength
    //   148: ifle +355 -> 503
    //   151: ldc 155
    //   153: astore 19
    //   155: iconst_0
    //   156: istore 20
    //   158: iload 20
    //   160: aload_1
    //   161: arraylength
    //   162: if_icmpge +38 -> 200
    //   165: new 96	java/lang/StringBuilder
    //   168: dup
    //   169: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   172: aload 19
    //   174: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: ldc 157
    //   179: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: aload_1
    //   183: iload 20
    //   185: iaload
    //   186: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   189: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   192: astore 19
    //   194: iinc 20 1
    //   197: goto -39 -> 158
    //   200: aload 19
    //   202: iconst_4
    //   203: invokevirtual 163	java/lang/String:substring	(I)Ljava/lang/String;
    //   206: astore 21
    //   208: new 96	java/lang/StringBuilder
    //   211: dup
    //   212: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   215: aload 11
    //   217: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: ldc 165
    //   222: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: aload 21
    //   227: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: ldc 167
    //   232: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   238: astore 12
    //   240: new 96	java/lang/StringBuilder
    //   243: dup
    //   244: ldc 169
    //   246: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   249: aload 12
    //   251: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   257: iconst_0
    //   258: anewarray 4	java/lang/Object
    //   261: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   264: new 67	com/tencent/feedback/common/a/c
    //   267: dup
    //   268: aload_0
    //   269: invokespecial 70	com/tencent/feedback/common/a/c:<init>	(Landroid/content/Context;)V
    //   272: astore 13
    //   274: aload 13
    //   276: invokevirtual 74	com/tencent/feedback/common/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   279: astore 17
    //   281: aload 17
    //   283: astore 15
    //   285: aload 15
    //   287: ldc 171
    //   289: aload 12
    //   291: aconst_null
    //   292: invokevirtual 115	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   295: istore 18
    //   297: new 96	java/lang/StringBuilder
    //   300: dup
    //   301: ldc 173
    //   303: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   306: iload 18
    //   308: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   311: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   314: iconst_0
    //   315: anewarray 4	java/lang/Object
    //   318: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   321: aload 15
    //   323: ifnull +16 -> 339
    //   326: aload 15
    //   328: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   331: ifeq +8 -> 339
    //   334: aload 15
    //   336: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   339: aload 13
    //   341: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   344: ldc 175
    //   346: iconst_0
    //   347: anewarray 4	java/lang/Object
    //   350: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   353: iload 18
    //   355: ireturn
    //   356: astore 14
    //   358: aconst_null
    //   359: astore 15
    //   361: aload 14
    //   363: invokevirtual 124	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   366: iconst_0
    //   367: anewarray 4	java/lang/Object
    //   370: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   373: aload 15
    //   375: ifnull +16 -> 391
    //   378: aload 15
    //   380: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   383: ifeq +8 -> 391
    //   386: aload 15
    //   388: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   391: aload 8
    //   393: ifnull +8 -> 401
    //   396: aload 8
    //   398: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   401: ldc 175
    //   403: iconst_0
    //   404: anewarray 4	java/lang/Object
    //   407: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   410: iconst_m1
    //   411: ireturn
    //   412: astore 16
    //   414: aconst_null
    //   415: astore 15
    //   417: aconst_null
    //   418: astore 13
    //   420: aload 15
    //   422: ifnull +16 -> 438
    //   425: aload 15
    //   427: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   430: ifeq +8 -> 438
    //   433: aload 15
    //   435: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   438: aload 13
    //   440: ifnull +8 -> 448
    //   443: aload 13
    //   445: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   448: ldc 175
    //   450: iconst_0
    //   451: anewarray 4	java/lang/Object
    //   454: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   457: aload 16
    //   459: athrow
    //   460: astore 16
    //   462: aconst_null
    //   463: astore 15
    //   465: goto -45 -> 420
    //   468: astore 16
    //   470: goto -50 -> 420
    //   473: astore 16
    //   475: aload 8
    //   477: astore 13
    //   479: goto -59 -> 420
    //   482: astore 14
    //   484: aload 13
    //   486: astore 8
    //   488: aconst_null
    //   489: astore 15
    //   491: goto -130 -> 361
    //   494: astore 14
    //   496: aload 13
    //   498: astore 8
    //   500: goto -139 -> 361
    //   503: aload 11
    //   505: astore 12
    //   507: goto -267 -> 240
    //
    // Exception table:
    //   from	to	target	type
    //   264	274	356	java/lang/Throwable
    //   264	274	412	finally
    //   274	281	460	finally
    //   285	321	468	finally
    //   361	373	473	finally
    //   274	281	482	java/lang/Throwable
    //   285	321	494	java/lang/Throwable
  }

  // ERROR //
  public static int a(android.content.Context paramContext, int[] paramArrayOfInt, long paramLong1, long paramLong2, String paramString)
  {
    // Byte code:
    //   0: ldc 178
    //   2: iconst_0
    //   3: anewarray 4	java/lang/Object
    //   6: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   9: aload_0
    //   10: ifnonnull +14 -> 24
    //   13: ldc 180
    //   15: iconst_0
    //   16: anewarray 4	java/lang/Object
    //   19: invokestatic 136	com/tencent/feedback/common/e:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   22: iconst_m1
    //   23: ireturn
    //   24: lload_2
    //   25: lload 4
    //   27: lcmp
    //   28: ifle +5 -> 33
    //   31: iconst_0
    //   32: ireturn
    //   33: new 96	java/lang/StringBuilder
    //   36: dup
    //   37: ldc 138
    //   39: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   42: lload_2
    //   43: invokevirtual 141	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   46: ldc 143
    //   48: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: ldc 148
    //   53: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: lload 4
    //   58: invokevirtual 141	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   61: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   64: astore 7
    //   66: aload_1
    //   67: ifnull +97 -> 164
    //   70: aload_1
    //   71: arraylength
    //   72: ifle +92 -> 164
    //   75: ldc 155
    //   77: astore 23
    //   79: iconst_0
    //   80: istore 24
    //   82: iload 24
    //   84: aload_1
    //   85: arraylength
    //   86: if_icmpge +38 -> 124
    //   89: new 96	java/lang/StringBuilder
    //   92: dup
    //   93: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   96: aload 23
    //   98: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: ldc 157
    //   103: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: aload_1
    //   107: iload 24
    //   109: iaload
    //   110: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   113: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: astore 23
    //   118: iinc 24 1
    //   121: goto -39 -> 82
    //   124: aload 23
    //   126: iconst_4
    //   127: invokevirtual 163	java/lang/String:substring	(I)Ljava/lang/String;
    //   130: astore 25
    //   132: new 96	java/lang/StringBuilder
    //   135: dup
    //   136: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   139: aload 7
    //   141: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: ldc 165
    //   146: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: aload 25
    //   151: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: ldc 167
    //   156: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   162: astore 7
    //   164: aload 6
    //   166: ifnull +35 -> 201
    //   169: new 96	java/lang/StringBuilder
    //   172: dup
    //   173: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   176: aload 7
    //   178: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: ldc 182
    //   183: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: aload 6
    //   188: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: ldc 184
    //   193: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   199: astore 7
    //   201: new 96	java/lang/StringBuilder
    //   204: dup
    //   205: ldc 186
    //   207: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   210: aload 7
    //   212: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   218: iconst_0
    //   219: anewarray 4	java/lang/Object
    //   222: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   225: aconst_null
    //   226: astore 8
    //   228: aconst_null
    //   229: astore 9
    //   231: new 67	com/tencent/feedback/common/a/c
    //   234: dup
    //   235: aload_0
    //   236: invokespecial 70	com/tencent/feedback/common/a/c:<init>	(Landroid/content/Context;)V
    //   239: astore 10
    //   241: aload 10
    //   243: invokevirtual 74	com/tencent/feedback/common/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   246: astore 15
    //   248: aload 15
    //   250: ldc 171
    //   252: iconst_1
    //   253: anewarray 159	java/lang/String
    //   256: dup
    //   257: iconst_0
    //   258: ldc 188
    //   260: aastore
    //   261: aload 7
    //   263: aconst_null
    //   264: aconst_null
    //   265: aconst_null
    //   266: aconst_null
    //   267: invokevirtual 192	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   270: astore 18
    //   272: aload 18
    //   274: astore 14
    //   276: aload 14
    //   278: invokeinterface 197 1 0
    //   283: pop
    //   284: aload 14
    //   286: aload 14
    //   288: ldc 199
    //   290: invokeinterface 203 2 0
    //   295: invokeinterface 207 2 0
    //   300: istore 22
    //   302: new 96	java/lang/StringBuilder
    //   305: dup
    //   306: ldc 209
    //   308: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   311: iload 22
    //   313: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   316: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   319: iconst_0
    //   320: anewarray 4	java/lang/Object
    //   323: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   326: aload 14
    //   328: ifnull +20 -> 348
    //   331: aload 14
    //   333: invokeinterface 212 1 0
    //   338: ifne +10 -> 348
    //   341: aload 14
    //   343: invokeinterface 213 1 0
    //   348: aload 15
    //   350: ifnull +16 -> 366
    //   353: aload 15
    //   355: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   358: ifeq +8 -> 366
    //   361: aload 15
    //   363: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   366: aload 10
    //   368: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   371: ldc 215
    //   373: iconst_0
    //   374: anewarray 4	java/lang/Object
    //   377: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   380: iload 22
    //   382: ireturn
    //   383: astore 11
    //   385: aconst_null
    //   386: astore 12
    //   388: aload 11
    //   390: invokevirtual 124	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   393: iconst_0
    //   394: anewarray 4	java/lang/Object
    //   397: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   400: aload 12
    //   402: ifnull +20 -> 422
    //   405: aload 12
    //   407: invokeinterface 212 1 0
    //   412: ifne +10 -> 422
    //   415: aload 12
    //   417: invokeinterface 213 1 0
    //   422: aload 9
    //   424: ifnull +16 -> 440
    //   427: aload 9
    //   429: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   432: ifeq +8 -> 440
    //   435: aload 9
    //   437: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   440: aload 8
    //   442: ifnull +8 -> 450
    //   445: aload 8
    //   447: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   450: ldc 215
    //   452: iconst_0
    //   453: anewarray 4	java/lang/Object
    //   456: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   459: iconst_m1
    //   460: ireturn
    //   461: astore 13
    //   463: aconst_null
    //   464: astore 10
    //   466: aconst_null
    //   467: astore 14
    //   469: aload 14
    //   471: ifnull +20 -> 491
    //   474: aload 14
    //   476: invokeinterface 212 1 0
    //   481: ifne +10 -> 491
    //   484: aload 14
    //   486: invokeinterface 213 1 0
    //   491: aload 9
    //   493: ifnull +16 -> 509
    //   496: aload 9
    //   498: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   501: ifeq +8 -> 509
    //   504: aload 9
    //   506: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   509: aload 10
    //   511: ifnull +8 -> 519
    //   514: aload 10
    //   516: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   519: ldc 215
    //   521: iconst_0
    //   522: anewarray 4	java/lang/Object
    //   525: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   528: aload 13
    //   530: athrow
    //   531: astore 13
    //   533: aconst_null
    //   534: astore 9
    //   536: aconst_null
    //   537: astore 14
    //   539: goto -70 -> 469
    //   542: astore 17
    //   544: aload 17
    //   546: astore 13
    //   548: aload 15
    //   550: astore 9
    //   552: aconst_null
    //   553: astore 14
    //   555: goto -86 -> 469
    //   558: astore 20
    //   560: aload 15
    //   562: astore 9
    //   564: aload 20
    //   566: astore 13
    //   568: goto -99 -> 469
    //   571: astore 13
    //   573: aload 8
    //   575: astore 10
    //   577: aload 12
    //   579: astore 14
    //   581: goto -112 -> 469
    //   584: astore 11
    //   586: aload 10
    //   588: astore 8
    //   590: aconst_null
    //   591: astore 9
    //   593: aconst_null
    //   594: astore 12
    //   596: goto -208 -> 388
    //   599: astore 16
    //   601: aload 10
    //   603: astore 8
    //   605: aload 15
    //   607: astore 9
    //   609: aload 16
    //   611: astore 11
    //   613: aconst_null
    //   614: astore 12
    //   616: goto -228 -> 388
    //   619: astore 19
    //   621: aload 14
    //   623: astore 12
    //   625: aload 10
    //   627: astore 8
    //   629: aload 19
    //   631: astore 11
    //   633: aload 15
    //   635: astore 9
    //   637: goto -249 -> 388
    //
    // Exception table:
    //   from	to	target	type
    //   231	241	383	java/lang/Throwable
    //   231	241	461	finally
    //   241	248	531	finally
    //   248	272	542	finally
    //   276	326	558	finally
    //   388	400	571	finally
    //   241	248	584	java/lang/Throwable
    //   248	272	599	java/lang/Throwable
    //   276	326	619	java/lang/Throwable
  }

  // ERROR //
  public static int a(android.content.Context paramContext, Long[] paramArrayOfLong)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: ldc 218
    //   4: iconst_0
    //   5: anewarray 4	java/lang/Object
    //   8: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   11: aload_0
    //   12: ifnonnull +14 -> 26
    //   15: ldc 220
    //   17: iconst_0
    //   18: anewarray 4	java/lang/Object
    //   21: invokestatic 78	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   24: iconst_m1
    //   25: ireturn
    //   26: aload_1
    //   27: ifnull +8 -> 35
    //   30: aload_1
    //   31: arraylength
    //   32: ifgt +5 -> 37
    //   35: iconst_0
    //   36: ireturn
    //   37: new 67	com/tencent/feedback/common/a/c
    //   40: dup
    //   41: aload_0
    //   42: invokespecial 70	com/tencent/feedback/common/a/c:<init>	(Landroid/content/Context;)V
    //   45: astore_3
    //   46: aload_3
    //   47: invokevirtual 74	com/tencent/feedback/common/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   50: astore_2
    //   51: new 222	java/lang/StringBuffer
    //   54: dup
    //   55: invokespecial 223	java/lang/StringBuffer:<init>	()V
    //   58: astore 6
    //   60: iconst_0
    //   61: istore 7
    //   63: iconst_0
    //   64: istore 8
    //   66: iload 8
    //   68: aload_1
    //   69: arraylength
    //   70: if_icmpge +123 -> 193
    //   73: aload_1
    //   74: iload 8
    //   76: aaload
    //   77: invokevirtual 229	java/lang/Long:longValue	()J
    //   80: lstore 9
    //   82: aload 6
    //   84: new 96	java/lang/StringBuilder
    //   87: dup
    //   88: ldc 231
    //   90: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   93: lload 9
    //   95: invokevirtual 141	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   98: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   101: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   104: pop
    //   105: iload 8
    //   107: ifle +272 -> 379
    //   110: iload 8
    //   112: bipush 50
    //   114: irem
    //   115: ifne +264 -> 379
    //   118: new 96	java/lang/StringBuilder
    //   121: dup
    //   122: ldc 236
    //   124: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   127: iload 8
    //   129: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   132: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   135: iconst_0
    //   136: anewarray 4	java/lang/Object
    //   139: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   142: iload 7
    //   144: aload_2
    //   145: ldc 171
    //   147: aload 6
    //   149: iconst_4
    //   150: invokevirtual 237	java/lang/StringBuffer:substring	(I)Ljava/lang/String;
    //   153: aconst_null
    //   154: invokevirtual 115	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   157: iadd
    //   158: istore 7
    //   160: aload 6
    //   162: iconst_0
    //   163: invokevirtual 241	java/lang/StringBuffer:setLength	(I)V
    //   166: new 96	java/lang/StringBuilder
    //   169: dup
    //   170: ldc 243
    //   172: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   175: iload 7
    //   177: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   180: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   183: iconst_0
    //   184: anewarray 4	java/lang/Object
    //   187: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   190: goto +189 -> 379
    //   193: aload 6
    //   195: invokevirtual 247	java/lang/StringBuffer:length	()I
    //   198: ifle +27 -> 225
    //   201: iload 7
    //   203: aload_2
    //   204: ldc 171
    //   206: aload 6
    //   208: iconst_4
    //   209: invokevirtual 237	java/lang/StringBuffer:substring	(I)Ljava/lang/String;
    //   212: aconst_null
    //   213: invokevirtual 115	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   216: iadd
    //   217: istore 7
    //   219: aload 6
    //   221: iconst_0
    //   222: invokevirtual 241	java/lang/StringBuffer:setLength	(I)V
    //   225: new 96	java/lang/StringBuilder
    //   228: dup
    //   229: ldc 249
    //   231: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   234: iload 7
    //   236: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   239: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   242: iconst_0
    //   243: anewarray 4	java/lang/Object
    //   246: invokestatic 136	com/tencent/feedback/common/e:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   249: aload_2
    //   250: ifnull +14 -> 264
    //   253: aload_2
    //   254: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   257: ifeq +7 -> 264
    //   260: aload_2
    //   261: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   264: aload_3
    //   265: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   268: ldc 251
    //   270: iconst_0
    //   271: anewarray 4	java/lang/Object
    //   274: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   277: iload 7
    //   279: ireturn
    //   280: astore 4
    //   282: aconst_null
    //   283: astore_3
    //   284: aload 4
    //   286: invokevirtual 124	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   289: iconst_0
    //   290: anewarray 4	java/lang/Object
    //   293: invokestatic 78	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   296: aload_2
    //   297: ifnull +14 -> 311
    //   300: aload_2
    //   301: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   304: ifeq +7 -> 311
    //   307: aload_2
    //   308: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   311: aload_3
    //   312: ifnull +7 -> 319
    //   315: aload_3
    //   316: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   319: ldc 251
    //   321: iconst_0
    //   322: anewarray 4	java/lang/Object
    //   325: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   328: iconst_m1
    //   329: ireturn
    //   330: astore 5
    //   332: aconst_null
    //   333: astore_3
    //   334: aload_2
    //   335: ifnull +14 -> 349
    //   338: aload_2
    //   339: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   342: ifeq +7 -> 349
    //   345: aload_2
    //   346: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   349: aload_3
    //   350: ifnull +7 -> 357
    //   353: aload_3
    //   354: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   357: ldc 251
    //   359: iconst_0
    //   360: anewarray 4	java/lang/Object
    //   363: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   366: aload 5
    //   368: athrow
    //   369: astore 5
    //   371: goto -37 -> 334
    //   374: astore 4
    //   376: goto -92 -> 284
    //   379: iinc 8 1
    //   382: goto -316 -> 66
    //
    // Exception table:
    //   from	to	target	type
    //   37	46	280	java/lang/Throwable
    //   37	46	330	finally
    //   46	60	369	finally
    //   66	105	369	finally
    //   118	190	369	finally
    //   193	225	369	finally
    //   225	249	369	finally
    //   284	296	369	finally
    //   46	60	374	java/lang/Throwable
    //   66	105	374	java/lang/Throwable
    //   118	190	374	java/lang/Throwable
    //   193	225	374	java/lang/Throwable
    //   225	249	374	java/lang/Throwable
  }

  public static ContentValues a(a parama)
  {
    ContentValues localContentValues = new ContentValues();
    if (parama.a > 0L)
      localContentValues.put("_id", Long.valueOf(parama.a));
    localContentValues.put("_prority", Integer.valueOf(parama.c));
    localContentValues.put("_time", Long.valueOf(parama.d));
    localContentValues.put("_type", Integer.valueOf(parama.b));
    localContentValues.put("_datas", parama.e);
    localContentValues.put("_length", Long.valueOf(parama.f));
    localContentValues.put("_key", parama.g);
    localContentValues.put("_count", Integer.valueOf(parama.c()));
    localContentValues.put("_upCounts", Integer.valueOf(parama.d()));
    localContentValues.put("_state", Integer.valueOf(parama.e()));
    return localContentValues;
  }

  // ERROR //
  public static List<d> a(android.content.Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +7 -> 8
    //   4: iload_3
    //   5: ifne +27 -> 32
    //   8: iconst_1
    //   9: anewarray 4	java/lang/Object
    //   12: astore 4
    //   14: aload 4
    //   16: iconst_0
    //   17: iload_3
    //   18: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   21: aastore
    //   22: ldc_w 300
    //   25: aload 4
    //   27: invokestatic 55	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   30: aconst_null
    //   31: areturn
    //   32: iconst_3
    //   33: anewarray 4	java/lang/Object
    //   36: astore 5
    //   38: aload_1
    //   39: ifnonnull +159 -> 198
    //   42: ldc_w 302
    //   45: astore 6
    //   47: aload 5
    //   49: iconst_0
    //   50: aload 6
    //   52: aastore
    //   53: aload 5
    //   55: iconst_1
    //   56: iload_2
    //   57: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   60: aastore
    //   61: aload 5
    //   63: iconst_2
    //   64: iload_3
    //   65: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   68: aastore
    //   69: ldc_w 304
    //   72: aload 5
    //   74: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   77: aconst_null
    //   78: astore 7
    //   80: aconst_null
    //   81: astore 8
    //   83: new 67	com/tencent/feedback/common/a/c
    //   86: dup
    //   87: aload_0
    //   88: invokespecial 70	com/tencent/feedback/common/a/c:<init>	(Landroid/content/Context;)V
    //   91: astore 9
    //   93: aload 9
    //   95: invokevirtual 74	com/tencent/feedback/common/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   98: astore 25
    //   100: aload 25
    //   102: astore 17
    //   104: aload 17
    //   106: ifnonnull +98 -> 204
    //   109: ldc_w 306
    //   112: iconst_0
    //   113: anewarray 4	java/lang/Object
    //   116: invokestatic 78	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   119: ldc_w 308
    //   122: iconst_0
    //   123: anewarray 4	java/lang/Object
    //   126: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   129: aload 17
    //   131: ifnull +16 -> 147
    //   134: aload 17
    //   136: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   139: ifeq +8 -> 147
    //   142: aload 17
    //   144: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   147: aload 9
    //   149: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   152: iconst_1
    //   153: anewarray 4	java/lang/Object
    //   156: astore 50
    //   158: aload 50
    //   160: iconst_0
    //   161: iconst_m1
    //   162: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   165: aastore
    //   166: ldc_w 310
    //   169: aload 50
    //   171: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   174: iconst_1
    //   175: anewarray 4	java/lang/Object
    //   178: astore 51
    //   180: aload 51
    //   182: iconst_0
    //   183: iconst_m1
    //   184: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   187: aastore
    //   188: ldc_w 312
    //   191: aload 51
    //   193: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   196: aconst_null
    //   197: areturn
    //   198: aload_1
    //   199: astore 6
    //   201: goto -154 -> 47
    //   204: new 222	java/lang/StringBuffer
    //   207: dup
    //   208: invokespecial 223	java/lang/StringBuffer:<init>	()V
    //   211: astore 26
    //   213: aload_1
    //   214: ifnull +54 -> 268
    //   217: aload 26
    //   219: invokevirtual 247	java/lang/StringBuffer:length	()I
    //   222: ifle +12 -> 234
    //   225: aload 26
    //   227: ldc_w 314
    //   230: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   233: pop
    //   234: aload 26
    //   236: ldc_w 316
    //   239: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   242: pop
    //   243: aload 26
    //   245: ldc_w 318
    //   248: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   251: pop
    //   252: aload 26
    //   254: aload_1
    //   255: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   258: pop
    //   259: aload 26
    //   261: ldc_w 320
    //   264: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   267: pop
    //   268: iload_2
    //   269: iflt +45 -> 314
    //   272: aload 26
    //   274: invokevirtual 247	java/lang/StringBuffer:length	()I
    //   277: ifle +12 -> 289
    //   280: aload 26
    //   282: ldc_w 314
    //   285: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   288: pop
    //   289: aload 26
    //   291: ldc_w 322
    //   294: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   297: pop
    //   298: aload 26
    //   300: ldc_w 324
    //   303: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   306: pop
    //   307: aload 26
    //   309: iload_2
    //   310: invokevirtual 327	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   313: pop
    //   314: aload 26
    //   316: invokevirtual 247	java/lang/StringBuffer:length	()I
    //   319: ifle +310 -> 629
    //   322: aload 26
    //   324: invokevirtual 328	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   327: astore 35
    //   329: iload_3
    //   330: ifle +305 -> 635
    //   333: new 96	java/lang/StringBuilder
    //   336: dup
    //   337: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   340: iload_3
    //   341: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   344: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   347: astore 36
    //   349: aload 17
    //   351: ldc 111
    //   353: aconst_null
    //   354: aload 35
    //   356: aconst_null
    //   357: aconst_null
    //   358: aconst_null
    //   359: aconst_null
    //   360: aload 36
    //   362: invokevirtual 331	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   365: astore 37
    //   367: aload 37
    //   369: astore 13
    //   371: aload 13
    //   373: ifnull +680 -> 1053
    //   376: new 333	java/util/ArrayList
    //   379: dup
    //   380: invokespecial 334	java/util/ArrayList:<init>	()V
    //   383: astore 42
    //   385: aload 13
    //   387: invokeinterface 197 1 0
    //   392: ifeq +249 -> 641
    //   395: aload 13
    //   397: invokestatic 337	com/tencent/feedback/common/a/a:b	(Landroid/database/Cursor;)Lcom/tencent/feedback/common/a/d;
    //   400: astore 44
    //   402: aload 44
    //   404: ifnull +237 -> 641
    //   407: iconst_4
    //   408: anewarray 4	java/lang/Object
    //   411: astore 45
    //   413: aload 45
    //   415: iconst_0
    //   416: aload 44
    //   418: invokevirtual 341	com/tencent/feedback/common/a/d:a	()Ljava/lang/String;
    //   421: aastore
    //   422: aload 45
    //   424: iconst_1
    //   425: aload 44
    //   427: invokevirtual 343	com/tencent/feedback/common/a/d:d	()Ljava/lang/String;
    //   430: aastore
    //   431: aload 45
    //   433: iconst_2
    //   434: aload 44
    //   436: invokevirtual 344	com/tencent/feedback/common/a/d:e	()I
    //   439: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   442: aastore
    //   443: aload 45
    //   445: iconst_3
    //   446: aload 44
    //   448: invokevirtual 346	com/tencent/feedback/common/a/d:f	()Ljava/lang/String;
    //   451: aastore
    //   452: ldc_w 348
    //   455: aload 45
    //   457: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   460: aload 42
    //   462: aload 44
    //   464: invokeinterface 354 2 0
    //   469: pop
    //   470: goto -85 -> 385
    //   473: astore 43
    //   475: aload 9
    //   477: astore 11
    //   479: aload 17
    //   481: astore 14
    //   483: aload 43
    //   485: astore 10
    //   487: aload 42
    //   489: astore 12
    //   491: ldc_w 356
    //   494: iconst_0
    //   495: anewarray 4	java/lang/Object
    //   498: invokestatic 78	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   501: ldc_w 308
    //   504: iconst_0
    //   505: anewarray 4	java/lang/Object
    //   508: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   511: aload 10
    //   513: invokevirtual 129	java/lang/Throwable:printStackTrace	()V
    //   516: aload 13
    //   518: ifnull +20 -> 538
    //   521: aload 13
    //   523: invokeinterface 212 1 0
    //   528: ifne +10 -> 538
    //   531: aload 13
    //   533: invokeinterface 213 1 0
    //   538: aload 14
    //   540: ifnull +16 -> 556
    //   543: aload 14
    //   545: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   548: ifeq +8 -> 556
    //   551: aload 14
    //   553: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   556: aload 11
    //   558: ifnull +8 -> 566
    //   561: aload 11
    //   563: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   566: aload 12
    //   568: ifnull +191 -> 759
    //   571: aload 12
    //   573: invokeinterface 359 1 0
    //   578: istore 21
    //   580: iconst_1
    //   581: anewarray 4	java/lang/Object
    //   584: astore 22
    //   586: aload 22
    //   588: iconst_0
    //   589: iload 21
    //   591: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   594: aastore
    //   595: ldc_w 310
    //   598: aload 22
    //   600: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   603: iconst_1
    //   604: anewarray 4	java/lang/Object
    //   607: astore 23
    //   609: aload 23
    //   611: iconst_0
    //   612: iload 21
    //   614: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   617: aastore
    //   618: ldc_w 312
    //   621: aload 23
    //   623: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   626: aload 12
    //   628: areturn
    //   629: aconst_null
    //   630: astore 35
    //   632: goto -303 -> 329
    //   635: aconst_null
    //   636: astore 36
    //   638: goto -289 -> 349
    //   641: aload 42
    //   643: astore 38
    //   645: aload 13
    //   647: ifnull +20 -> 667
    //   650: aload 13
    //   652: invokeinterface 212 1 0
    //   657: ifne +10 -> 667
    //   660: aload 13
    //   662: invokeinterface 213 1 0
    //   667: aload 17
    //   669: ifnull +16 -> 685
    //   672: aload 17
    //   674: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   677: ifeq +8 -> 685
    //   680: aload 17
    //   682: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   685: aload 9
    //   687: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   690: aload 38
    //   692: ifnull +61 -> 753
    //   695: aload 38
    //   697: invokeinterface 359 1 0
    //   702: istore 39
    //   704: iconst_1
    //   705: anewarray 4	java/lang/Object
    //   708: astore 40
    //   710: aload 40
    //   712: iconst_0
    //   713: iload 39
    //   715: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   718: aastore
    //   719: ldc_w 310
    //   722: aload 40
    //   724: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   727: iconst_1
    //   728: anewarray 4	java/lang/Object
    //   731: astore 41
    //   733: aload 41
    //   735: iconst_0
    //   736: iload 39
    //   738: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   741: aastore
    //   742: ldc_w 312
    //   745: aload 41
    //   747: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   750: aload 38
    //   752: areturn
    //   753: iconst_m1
    //   754: istore 39
    //   756: goto -52 -> 704
    //   759: iconst_m1
    //   760: istore 21
    //   762: goto -182 -> 580
    //   765: astore 52
    //   767: aconst_null
    //   768: astore 9
    //   770: aload 52
    //   772: astore 16
    //   774: aconst_null
    //   775: astore 17
    //   777: aload 8
    //   779: ifnull +20 -> 799
    //   782: aload 8
    //   784: invokeinterface 212 1 0
    //   789: ifne +10 -> 799
    //   792: aload 8
    //   794: invokeinterface 213 1 0
    //   799: aload 17
    //   801: ifnull +16 -> 817
    //   804: aload 17
    //   806: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   809: ifeq +8 -> 817
    //   812: aload 17
    //   814: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   817: aload 9
    //   819: ifnull +8 -> 827
    //   822: aload 9
    //   824: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   827: aload 7
    //   829: ifnull +61 -> 890
    //   832: aload 7
    //   834: invokeinterface 359 1 0
    //   839: istore 18
    //   841: iconst_1
    //   842: anewarray 4	java/lang/Object
    //   845: astore 19
    //   847: aload 19
    //   849: iconst_0
    //   850: iload 18
    //   852: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   855: aastore
    //   856: ldc_w 310
    //   859: aload 19
    //   861: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   864: iconst_1
    //   865: anewarray 4	java/lang/Object
    //   868: astore 20
    //   870: aload 20
    //   872: iconst_0
    //   873: iload 18
    //   875: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   878: aastore
    //   879: ldc_w 312
    //   882: aload 20
    //   884: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   887: aload 16
    //   889: athrow
    //   890: iconst_m1
    //   891: istore 18
    //   893: goto -52 -> 841
    //   896: astore 24
    //   898: aload 24
    //   900: astore 16
    //   902: aconst_null
    //   903: astore 17
    //   905: aconst_null
    //   906: astore 8
    //   908: aconst_null
    //   909: astore 7
    //   911: goto -134 -> 777
    //   914: astore 16
    //   916: aconst_null
    //   917: astore 8
    //   919: aconst_null
    //   920: astore 7
    //   922: goto -145 -> 777
    //   925: astore 16
    //   927: aload 13
    //   929: astore 8
    //   931: aconst_null
    //   932: astore 7
    //   934: goto -157 -> 777
    //   937: astore 16
    //   939: aload 13
    //   941: astore 8
    //   943: aload 42
    //   945: astore 7
    //   947: goto -170 -> 777
    //   950: astore 15
    //   952: aload 13
    //   954: astore 8
    //   956: aload 12
    //   958: astore 7
    //   960: aload 11
    //   962: astore 9
    //   964: aload 15
    //   966: astore 16
    //   968: aload 14
    //   970: astore 17
    //   972: goto -195 -> 777
    //   975: astore 10
    //   977: aconst_null
    //   978: astore 12
    //   980: aconst_null
    //   981: astore 13
    //   983: aconst_null
    //   984: astore 14
    //   986: aconst_null
    //   987: astore 11
    //   989: goto -498 -> 491
    //   992: astore 10
    //   994: aload 9
    //   996: astore 11
    //   998: aconst_null
    //   999: astore 12
    //   1001: aconst_null
    //   1002: astore 13
    //   1004: aconst_null
    //   1005: astore 14
    //   1007: goto -516 -> 491
    //   1010: astore 27
    //   1012: aload 17
    //   1014: astore 14
    //   1016: aload 9
    //   1018: astore 11
    //   1020: aload 27
    //   1022: astore 10
    //   1024: aconst_null
    //   1025: astore 12
    //   1027: aconst_null
    //   1028: astore 13
    //   1030: goto -539 -> 491
    //   1033: astore 47
    //   1035: aload 17
    //   1037: astore 14
    //   1039: aload 9
    //   1041: astore 11
    //   1043: aload 47
    //   1045: astore 10
    //   1047: aconst_null
    //   1048: astore 12
    //   1050: goto -559 -> 491
    //   1053: aconst_null
    //   1054: astore 38
    //   1056: goto -411 -> 645
    //
    // Exception table:
    //   from	to	target	type
    //   385	402	473	java/lang/Throwable
    //   407	470	473	java/lang/Throwable
    //   83	93	765	finally
    //   93	100	896	finally
    //   109	129	914	finally
    //   204	213	914	finally
    //   217	234	914	finally
    //   234	268	914	finally
    //   272	289	914	finally
    //   289	314	914	finally
    //   314	329	914	finally
    //   333	349	914	finally
    //   349	367	914	finally
    //   376	385	925	finally
    //   385	402	937	finally
    //   407	470	937	finally
    //   491	516	950	finally
    //   83	93	975	java/lang/Throwable
    //   93	100	992	java/lang/Throwable
    //   109	129	1010	java/lang/Throwable
    //   204	213	1010	java/lang/Throwable
    //   217	234	1010	java/lang/Throwable
    //   234	268	1010	java/lang/Throwable
    //   272	289	1010	java/lang/Throwable
    //   289	314	1010	java/lang/Throwable
    //   314	329	1010	java/lang/Throwable
    //   333	349	1010	java/lang/Throwable
    //   349	367	1010	java/lang/Throwable
    //   376	385	1033	java/lang/Throwable
  }

  // ERROR //
  public static List<a> a(android.content.Context paramContext, int[] paramArrayOfInt, int paramInt1, int paramInt2, long paramLong1, int paramInt3, String paramString, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong2, long paramLong3, int paramInt8)
  {
    // Byte code:
    //   0: ldc_w 362
    //   3: iconst_0
    //   4: anewarray 4	java/lang/Object
    //   7: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   10: aload_0
    //   11: ifnull +49 -> 60
    //   14: lload 4
    //   16: lconst_0
    //   17: lcmp
    //   18: ifeq +42 -> 60
    //   21: lload 14
    //   23: lconst_0
    //   24: lcmp
    //   25: ifle +11 -> 36
    //   28: lload 12
    //   30: lload 14
    //   32: lcmp
    //   33: ifgt +27 -> 60
    //   36: iload 9
    //   38: ifle +10 -> 48
    //   41: iload 8
    //   43: iload 9
    //   45: if_icmpgt +15 -> 60
    //   48: iload 11
    //   50: ifle +22 -> 72
    //   53: iload 10
    //   55: iload 11
    //   57: if_icmple +15 -> 72
    //   60: ldc_w 364
    //   63: iconst_0
    //   64: anewarray 4	java/lang/Object
    //   67: invokestatic 78	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   70: aconst_null
    //   71: areturn
    //   72: lload 4
    //   74: lconst_0
    //   75: lcmp
    //   76: ifge +8 -> 84
    //   79: ldc2_w 365
    //   82: lstore 4
    //   84: aload_1
    //   85: ifnull +1998 -> 2083
    //   88: aload_1
    //   89: arraylength
    //   90: ifle +1993 -> 2083
    //   93: ldc 155
    //   95: astore 79
    //   97: iconst_0
    //   98: istore 80
    //   100: iload 80
    //   102: aload_1
    //   103: arraylength
    //   104: if_icmpge +38 -> 142
    //   107: new 96	java/lang/StringBuilder
    //   110: dup
    //   111: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   114: aload 79
    //   116: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: ldc 157
    //   121: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: aload_1
    //   125: iload 80
    //   127: iaload
    //   128: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   131: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: astore 79
    //   136: iinc 80 1
    //   139: goto -39 -> 100
    //   142: new 96	java/lang/StringBuilder
    //   145: dup
    //   146: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   149: ldc 155
    //   151: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: aload 79
    //   156: iconst_4
    //   157: invokevirtual 163	java/lang/String:substring	(I)Ljava/lang/String;
    //   160: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   166: astore 17
    //   168: aload 17
    //   170: invokevirtual 367	java/lang/String:length	()I
    //   173: ifle +939 -> 1112
    //   176: new 96	java/lang/StringBuilder
    //   179: dup
    //   180: ldc_w 369
    //   183: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   186: aload 17
    //   188: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: ldc_w 371
    //   194: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   200: astore 18
    //   202: aload 7
    //   204: ifnull +59 -> 263
    //   207: new 96	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   214: aload 18
    //   216: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: astore 77
    //   221: aload 18
    //   223: invokevirtual 367	java/lang/String:length	()I
    //   226: ifle +893 -> 1119
    //   229: ldc_w 314
    //   232: astore 78
    //   234: aload 77
    //   236: aload 78
    //   238: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   241: ldc_w 373
    //   244: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   247: aload 7
    //   249: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: ldc_w 320
    //   255: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   261: astore 18
    //   263: iload 8
    //   265: iflt +59 -> 324
    //   268: new 96	java/lang/StringBuilder
    //   271: dup
    //   272: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   275: aload 18
    //   277: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: astore 75
    //   282: aload 18
    //   284: invokevirtual 367	java/lang/String:length	()I
    //   287: ifle +839 -> 1126
    //   290: ldc_w 314
    //   293: astore 76
    //   295: aload 75
    //   297: aload 76
    //   299: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   302: ldc_w 375
    //   305: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: iload 8
    //   310: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   313: ldc_w 377
    //   316: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   319: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   322: astore 18
    //   324: iload 9
    //   326: iflt +59 -> 385
    //   329: new 96	java/lang/StringBuilder
    //   332: dup
    //   333: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   336: aload 18
    //   338: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   341: astore 73
    //   343: aload 18
    //   345: invokevirtual 367	java/lang/String:length	()I
    //   348: ifle +785 -> 1133
    //   351: ldc_w 314
    //   354: astore 74
    //   356: aload 73
    //   358: aload 74
    //   360: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   363: ldc_w 379
    //   366: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   369: iload 9
    //   371: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   374: ldc_w 377
    //   377: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   380: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   383: astore 18
    //   385: iload 10
    //   387: iflt +59 -> 446
    //   390: new 96	java/lang/StringBuilder
    //   393: dup
    //   394: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   397: aload 18
    //   399: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   402: astore 71
    //   404: aload 18
    //   406: invokevirtual 367	java/lang/String:length	()I
    //   409: ifle +731 -> 1140
    //   412: ldc_w 314
    //   415: astore 72
    //   417: aload 71
    //   419: aload 72
    //   421: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   424: ldc_w 381
    //   427: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   430: iload 10
    //   432: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   435: ldc_w 377
    //   438: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   441: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   444: astore 18
    //   446: iload 11
    //   448: iflt +59 -> 507
    //   451: new 96	java/lang/StringBuilder
    //   454: dup
    //   455: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   458: aload 18
    //   460: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   463: astore 69
    //   465: aload 18
    //   467: invokevirtual 367	java/lang/String:length	()I
    //   470: ifle +677 -> 1147
    //   473: ldc_w 314
    //   476: astore 70
    //   478: aload 69
    //   480: aload 70
    //   482: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   485: ldc_w 383
    //   488: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   491: iload 11
    //   493: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   496: ldc_w 377
    //   499: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   502: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   505: astore 18
    //   507: lload 12
    //   509: lconst_0
    //   510: lcmp
    //   511: iflt +58 -> 569
    //   514: new 96	java/lang/StringBuilder
    //   517: dup
    //   518: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   521: aload 18
    //   523: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   526: astore 67
    //   528: aload 18
    //   530: invokevirtual 367	java/lang/String:length	()I
    //   533: ifle +621 -> 1154
    //   536: ldc_w 314
    //   539: astore 68
    //   541: aload 67
    //   543: aload 68
    //   545: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   548: ldc 138
    //   550: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   553: lload 12
    //   555: invokevirtual 141	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   558: ldc_w 377
    //   561: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   564: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   567: astore 18
    //   569: lload 14
    //   571: lconst_0
    //   572: lcmp
    //   573: iflt +59 -> 632
    //   576: new 96	java/lang/StringBuilder
    //   579: dup
    //   580: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   583: aload 18
    //   585: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   588: astore 65
    //   590: aload 18
    //   592: invokevirtual 367	java/lang/String:length	()I
    //   595: ifle +566 -> 1161
    //   598: ldc_w 314
    //   601: astore 66
    //   603: aload 65
    //   605: aload 66
    //   607: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   610: ldc_w 385
    //   613: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   616: lload 14
    //   618: invokevirtual 141	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   621: ldc_w 377
    //   624: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   627: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   630: astore 18
    //   632: iload 16
    //   634: iflt +59 -> 693
    //   637: new 96	java/lang/StringBuilder
    //   640: dup
    //   641: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   644: aload 18
    //   646: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   649: astore 63
    //   651: aload 18
    //   653: invokevirtual 367	java/lang/String:length	()I
    //   656: ifle +512 -> 1168
    //   659: ldc_w 314
    //   662: astore 64
    //   664: aload 63
    //   666: aload 64
    //   668: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   671: ldc_w 387
    //   674: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   677: iload 16
    //   679: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   682: ldc_w 377
    //   685: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   688: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   691: astore 18
    //   693: ldc 155
    //   695: astore 19
    //   697: iload_2
    //   698: tableswitch	default:+22 -> 720, 1:+477->1175, 2:+503->1201
    //   721: tableswitch	default:+23 -> 744, 1:+506->1227, 2:+532->1253
    //   745: ldc_w 4865
    //   748: i2l
    //   749: invokevirtual 393	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   752: ifeq +1324 -> 2076
    //   755: aload 19
    //   757: iconst_0
    //   758: bipush 253
    //   760: aload 19
    //   762: invokevirtual 367	java/lang/String:length	()I
    //   765: iadd
    //   766: invokevirtual 396	java/lang/String:substring	(II)Ljava/lang/String;
    //   769: astore 20
    //   771: ldc_w 398
    //   774: iconst_1
    //   775: anewarray 4	java/lang/Object
    //   778: dup
    //   779: iconst_0
    //   780: aload 18
    //   782: aastore
    //   783: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   786: aconst_null
    //   787: astore 21
    //   789: new 333	java/util/ArrayList
    //   792: dup
    //   793: invokespecial 334	java/util/ArrayList:<init>	()V
    //   796: astore 22
    //   798: new 67	com/tencent/feedback/common/a/c
    //   801: dup
    //   802: aload_0
    //   803: invokespecial 70	com/tencent/feedback/common/a/c:<init>	(Landroid/content/Context;)V
    //   806: astore 23
    //   808: aload 23
    //   810: invokevirtual 74	com/tencent/feedback/common/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   813: astore 28
    //   815: lload 4
    //   817: lconst_0
    //   818: lcmp
    //   819: ifle +957 -> 1776
    //   822: aload 20
    //   824: invokevirtual 367	java/lang/String:length	()I
    //   827: ifle +26 -> 853
    //   830: new 96	java/lang/StringBuilder
    //   833: dup
    //   834: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   837: aload 20
    //   839: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   842: ldc_w 389
    //   845: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   848: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   851: astore 20
    //   853: new 96	java/lang/StringBuilder
    //   856: dup
    //   857: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   860: aload 20
    //   862: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   865: ldc_w 400
    //   868: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   871: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   874: astore 35
    //   876: iconst_2
    //   877: anewarray 159	java/lang/String
    //   880: dup
    //   881: iconst_0
    //   882: ldc_w 257
    //   885: aastore
    //   886: dup
    //   887: iconst_1
    //   888: ldc_w 280
    //   891: aastore
    //   892: astore 36
    //   894: iload 6
    //   896: iflt +383 -> 1279
    //   899: new 96	java/lang/StringBuilder
    //   902: dup
    //   903: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   906: iload 6
    //   908: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   911: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   914: astore 37
    //   916: aload 28
    //   918: ldc 171
    //   920: aload 36
    //   922: aload 18
    //   924: aconst_null
    //   925: aconst_null
    //   926: aconst_null
    //   927: aload 35
    //   929: aload 37
    //   931: invokevirtual 331	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   934: astore 38
    //   936: aload 38
    //   938: astore 26
    //   940: aload 26
    //   942: ldc_w 257
    //   945: invokeinterface 203 2 0
    //   950: istore 39
    //   952: aload 26
    //   954: ldc_w 280
    //   957: invokeinterface 203 2 0
    //   962: istore 40
    //   964: new 402	java/util/LinkedHashMap
    //   967: dup
    //   968: invokespecial 403	java/util/LinkedHashMap:<init>	()V
    //   971: astore 41
    //   973: aload 26
    //   975: invokeinterface 197 1 0
    //   980: ifeq +305 -> 1285
    //   983: aload 26
    //   985: iload 39
    //   987: invokeinterface 407 2 0
    //   992: lstore 58
    //   994: aload 26
    //   996: iload 40
    //   998: invokeinterface 407 2 0
    //   1003: lstore 60
    //   1005: aload 41
    //   1007: lload 58
    //   1009: invokestatic 260	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1012: lload 60
    //   1014: invokestatic 260	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1017: invokevirtual 410	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1020: pop
    //   1021: goto -48 -> 973
    //   1024: astore 34
    //   1026: aload 28
    //   1028: astore 21
    //   1030: aload 34
    //   1032: astore 24
    //   1034: aload 23
    //   1036: astore 25
    //   1038: aload 24
    //   1040: invokevirtual 124	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   1043: iconst_0
    //   1044: anewarray 4	java/lang/Object
    //   1047: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1050: aload 26
    //   1052: ifnull +20 -> 1072
    //   1055: aload 26
    //   1057: invokeinterface 212 1 0
    //   1062: ifne +10 -> 1072
    //   1065: aload 26
    //   1067: invokeinterface 213 1 0
    //   1072: aload 21
    //   1074: ifnull +16 -> 1090
    //   1077: aload 21
    //   1079: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   1082: ifeq +8 -> 1090
    //   1085: aload 21
    //   1087: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   1090: aload 25
    //   1092: ifnull +8 -> 1100
    //   1095: aload 25
    //   1097: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   1100: ldc_w 412
    //   1103: iconst_0
    //   1104: anewarray 4	java/lang/Object
    //   1107: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1110: aconst_null
    //   1111: areturn
    //   1112: ldc 155
    //   1114: astore 18
    //   1116: goto -914 -> 202
    //   1119: ldc 155
    //   1121: astore 78
    //   1123: goto -889 -> 234
    //   1126: ldc 155
    //   1128: astore 76
    //   1130: goto -835 -> 295
    //   1133: ldc 155
    //   1135: astore 74
    //   1137: goto -781 -> 356
    //   1140: ldc 155
    //   1142: astore 72
    //   1144: goto -727 -> 417
    //   1147: ldc 155
    //   1149: astore 70
    //   1151: goto -673 -> 478
    //   1154: ldc 155
    //   1156: astore 68
    //   1158: goto -617 -> 541
    //   1161: ldc 155
    //   1163: astore 66
    //   1165: goto -562 -> 603
    //   1168: ldc 155
    //   1170: astore 64
    //   1172: goto -508 -> 664
    //   1175: new 96	java/lang/StringBuilder
    //   1178: dup
    //   1179: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   1182: aload 19
    //   1184: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1187: ldc_w 414
    //   1190: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1193: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1196: astore 19
    //   1198: goto -478 -> 720
    //   1201: new 96	java/lang/StringBuilder
    //   1204: dup
    //   1205: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   1208: aload 19
    //   1210: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1213: ldc_w 416
    //   1216: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1219: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1222: astore 19
    //   1224: goto -504 -> 720
    //   1227: new 96	java/lang/StringBuilder
    //   1230: dup
    //   1231: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   1234: aload 19
    //   1236: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1239: ldc_w 418
    //   1242: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1245: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1248: astore 19
    //   1250: goto -506 -> 744
    //   1253: new 96	java/lang/StringBuilder
    //   1256: dup
    //   1257: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   1260: aload 19
    //   1262: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1265: ldc_w 420
    //   1268: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1271: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1274: astore 19
    //   1276: goto -532 -> 744
    //   1279: aconst_null
    //   1280: astore 37
    //   1282: goto -366 -> 916
    //   1285: aload 26
    //   1287: invokeinterface 213 1 0
    //   1292: aload 41
    //   1294: lload 4
    //   1296: invokestatic 425	com/tencent/feedback/anr/a:a	(Ljava/util/LinkedHashMap;J)[Ljava/lang/Long;
    //   1299: astore 42
    //   1301: aload 42
    //   1303: ifnull +416 -> 1719
    //   1306: aload 42
    //   1308: arraylength
    //   1309: ifle +410 -> 1719
    //   1312: new 96	java/lang/StringBuilder
    //   1315: dup
    //   1316: ldc_w 427
    //   1319: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1322: aload 42
    //   1324: arraylength
    //   1325: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1328: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1331: iconst_0
    //   1332: anewarray 4	java/lang/Object
    //   1335: invokestatic 136	com/tencent/feedback/common/e:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1338: new 222	java/lang/StringBuffer
    //   1341: dup
    //   1342: invokespecial 223	java/lang/StringBuffer:<init>	()V
    //   1345: astore 43
    //   1347: iconst_0
    //   1348: istore 44
    //   1350: iload 44
    //   1352: aload 42
    //   1354: arraylength
    //   1355: if_icmpge +181 -> 1536
    //   1358: aload 42
    //   1360: iload 44
    //   1362: aaload
    //   1363: invokevirtual 229	java/lang/Long:longValue	()J
    //   1366: lstore 48
    //   1368: aload 43
    //   1370: new 96	java/lang/StringBuilder
    //   1373: dup
    //   1374: ldc 231
    //   1376: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1379: lload 48
    //   1381: invokevirtual 141	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1384: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1387: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1390: pop
    //   1391: iload 44
    //   1393: ifle +676 -> 2069
    //   1396: iload 44
    //   1398: bipush 50
    //   1400: irem
    //   1401: ifne +668 -> 2069
    //   1404: new 96	java/lang/StringBuilder
    //   1407: dup
    //   1408: ldc 236
    //   1410: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1413: iload 44
    //   1415: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1418: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1421: iconst_0
    //   1422: anewarray 4	java/lang/Object
    //   1425: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1428: aload 43
    //   1430: iconst_4
    //   1431: invokevirtual 237	java/lang/StringBuffer:substring	(I)Ljava/lang/String;
    //   1434: astore 52
    //   1436: aload 43
    //   1438: iconst_0
    //   1439: invokevirtual 241	java/lang/StringBuffer:setLength	(I)V
    //   1442: aload 28
    //   1444: ldc 171
    //   1446: aconst_null
    //   1447: aload 52
    //   1449: aconst_null
    //   1450: aconst_null
    //   1451: aconst_null
    //   1452: aload 35
    //   1454: invokevirtual 192	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   1457: astore 53
    //   1459: aload 53
    //   1461: astore 51
    //   1463: aload 51
    //   1465: invokestatic 430	com/tencent/feedback/common/a/a:a	(Landroid/database/Cursor;)Ljava/util/List;
    //   1468: astore 56
    //   1470: aload 56
    //   1472: ifnull +47 -> 1519
    //   1475: aload 56
    //   1477: invokeinterface 359 1 0
    //   1482: ifle +37 -> 1519
    //   1485: aload 22
    //   1487: aload 56
    //   1489: invokeinterface 434 2 0
    //   1494: pop
    //   1495: new 96	java/lang/StringBuilder
    //   1498: dup
    //   1499: ldc_w 436
    //   1502: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1505: iconst_0
    //   1506: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1509: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1512: iconst_0
    //   1513: anewarray 4	java/lang/Object
    //   1516: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1519: aload 51
    //   1521: invokeinterface 213 1 0
    //   1526: iinc 44 1
    //   1529: aload 51
    //   1531: astore 26
    //   1533: goto -183 -> 1350
    //   1536: aload 43
    //   1538: invokevirtual 247	java/lang/StringBuffer:length	()I
    //   1541: ifle +90 -> 1631
    //   1544: aload 43
    //   1546: iconst_4
    //   1547: invokevirtual 237	java/lang/StringBuffer:substring	(I)Ljava/lang/String;
    //   1550: astore 45
    //   1552: aload 43
    //   1554: iconst_0
    //   1555: invokevirtual 241	java/lang/StringBuffer:setLength	(I)V
    //   1558: aload 28
    //   1560: ldc 171
    //   1562: aconst_null
    //   1563: aload 45
    //   1565: aconst_null
    //   1566: aconst_null
    //   1567: aconst_null
    //   1568: aload 35
    //   1570: invokevirtual 192	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   1573: astore 26
    //   1575: aload 26
    //   1577: invokestatic 430	com/tencent/feedback/common/a/a:a	(Landroid/database/Cursor;)Ljava/util/List;
    //   1580: astore 46
    //   1582: aload 46
    //   1584: ifnull +47 -> 1631
    //   1587: aload 46
    //   1589: invokeinterface 359 1 0
    //   1594: ifle +37 -> 1631
    //   1597: aload 22
    //   1599: aload 46
    //   1601: invokeinterface 434 2 0
    //   1606: pop
    //   1607: new 96	java/lang/StringBuilder
    //   1610: dup
    //   1611: ldc_w 436
    //   1614: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1617: iconst_0
    //   1618: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1621: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1624: iconst_0
    //   1625: anewarray 4	java/lang/Object
    //   1628: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1631: new 96	java/lang/StringBuilder
    //   1634: dup
    //   1635: ldc_w 438
    //   1638: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1641: aload 22
    //   1643: invokeinterface 359 1 0
    //   1648: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1651: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1654: iconst_0
    //   1655: anewarray 4	java/lang/Object
    //   1658: invokestatic 136	com/tencent/feedback/common/e:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1661: aload 26
    //   1663: ifnull +20 -> 1683
    //   1666: aload 26
    //   1668: invokeinterface 212 1 0
    //   1673: ifne +10 -> 1683
    //   1676: aload 26
    //   1678: invokeinterface 213 1 0
    //   1683: aload 28
    //   1685: ifnull +16 -> 1701
    //   1688: aload 28
    //   1690: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   1693: ifeq +8 -> 1701
    //   1696: aload 28
    //   1698: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   1701: aload 23
    //   1703: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   1706: ldc_w 412
    //   1709: iconst_0
    //   1710: anewarray 4	java/lang/Object
    //   1713: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1716: aload 22
    //   1718: areturn
    //   1719: aload 26
    //   1721: ifnull +20 -> 1741
    //   1724: aload 26
    //   1726: invokeinterface 212 1 0
    //   1731: ifne +10 -> 1741
    //   1734: aload 26
    //   1736: invokeinterface 213 1 0
    //   1741: aload 28
    //   1743: ifnull +16 -> 1759
    //   1746: aload 28
    //   1748: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   1751: ifeq +8 -> 1759
    //   1754: aload 28
    //   1756: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   1759: aload 23
    //   1761: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   1764: ldc_w 412
    //   1767: iconst_0
    //   1768: anewarray 4	java/lang/Object
    //   1771: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1774: aconst_null
    //   1775: areturn
    //   1776: aload 28
    //   1778: ldc 171
    //   1780: aconst_null
    //   1781: aload 18
    //   1783: aconst_null
    //   1784: aconst_null
    //   1785: aconst_null
    //   1786: aload 20
    //   1788: invokevirtual 192	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   1791: astore 31
    //   1793: aload 31
    //   1795: astore 26
    //   1797: aload 26
    //   1799: invokestatic 430	com/tencent/feedback/common/a/a:a	(Landroid/database/Cursor;)Ljava/util/List;
    //   1802: astore 33
    //   1804: aload 26
    //   1806: ifnull +20 -> 1826
    //   1809: aload 26
    //   1811: invokeinterface 212 1 0
    //   1816: ifne +10 -> 1826
    //   1819: aload 26
    //   1821: invokeinterface 213 1 0
    //   1826: aload 28
    //   1828: ifnull +16 -> 1844
    //   1831: aload 28
    //   1833: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   1836: ifeq +8 -> 1844
    //   1839: aload 28
    //   1841: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   1844: aload 23
    //   1846: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   1849: ldc_w 412
    //   1852: iconst_0
    //   1853: anewarray 4	java/lang/Object
    //   1856: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1859: aload 33
    //   1861: areturn
    //   1862: astore 27
    //   1864: aconst_null
    //   1865: astore 23
    //   1867: aconst_null
    //   1868: astore 26
    //   1870: aload 26
    //   1872: ifnull +20 -> 1892
    //   1875: aload 26
    //   1877: invokeinterface 212 1 0
    //   1882: ifne +10 -> 1892
    //   1885: aload 26
    //   1887: invokeinterface 213 1 0
    //   1892: aload 21
    //   1894: ifnull +16 -> 1910
    //   1897: aload 21
    //   1899: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   1902: ifeq +8 -> 1910
    //   1905: aload 21
    //   1907: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   1910: aload 23
    //   1912: ifnull +8 -> 1920
    //   1915: aload 23
    //   1917: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   1920: ldc_w 412
    //   1923: iconst_0
    //   1924: anewarray 4	java/lang/Object
    //   1927: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1930: aload 27
    //   1932: athrow
    //   1933: astore 27
    //   1935: aconst_null
    //   1936: astore 21
    //   1938: aconst_null
    //   1939: astore 26
    //   1941: goto -71 -> 1870
    //   1944: astore 30
    //   1946: aload 28
    //   1948: astore 21
    //   1950: aload 30
    //   1952: astore 27
    //   1954: aconst_null
    //   1955: astore 26
    //   1957: goto -87 -> 1870
    //   1960: astore 32
    //   1962: aload 28
    //   1964: astore 21
    //   1966: aload 32
    //   1968: astore 27
    //   1970: goto -100 -> 1870
    //   1973: astore 55
    //   1975: aload 51
    //   1977: astore 26
    //   1979: aload 28
    //   1981: astore 21
    //   1983: aload 55
    //   1985: astore 27
    //   1987: goto -117 -> 1870
    //   1990: astore 27
    //   1992: aload 25
    //   1994: astore 23
    //   1996: goto -126 -> 1870
    //   1999: astore 24
    //   2001: aconst_null
    //   2002: astore 25
    //   2004: aconst_null
    //   2005: astore 21
    //   2007: aconst_null
    //   2008: astore 26
    //   2010: goto -972 -> 1038
    //   2013: astore 24
    //   2015: aload 23
    //   2017: astore 25
    //   2019: aconst_null
    //   2020: astore 21
    //   2022: aconst_null
    //   2023: astore 26
    //   2025: goto -987 -> 1038
    //   2028: astore 29
    //   2030: aload 28
    //   2032: astore 21
    //   2034: aload 29
    //   2036: astore 24
    //   2038: aload 23
    //   2040: astore 25
    //   2042: aconst_null
    //   2043: astore 26
    //   2045: goto -1007 -> 1038
    //   2048: astore 54
    //   2050: aload 51
    //   2052: astore 26
    //   2054: aload 23
    //   2056: astore 25
    //   2058: aload 28
    //   2060: astore 21
    //   2062: aload 54
    //   2064: astore 24
    //   2066: goto -1028 -> 1038
    //   2069: aload 26
    //   2071: astore 51
    //   2073: goto -547 -> 1526
    //   2076: aload 19
    //   2078: astore 20
    //   2080: goto -1309 -> 771
    //   2083: ldc 155
    //   2085: astore 17
    //   2087: goto -1919 -> 168
    //
    // Exception table:
    //   from	to	target	type
    //   940	973	1024	java/lang/Throwable
    //   973	1021	1024	java/lang/Throwable
    //   1285	1301	1024	java/lang/Throwable
    //   1306	1347	1024	java/lang/Throwable
    //   1350	1391	1024	java/lang/Throwable
    //   1404	1459	1024	java/lang/Throwable
    //   1536	1582	1024	java/lang/Throwable
    //   1587	1631	1024	java/lang/Throwable
    //   1631	1661	1024	java/lang/Throwable
    //   1797	1804	1024	java/lang/Throwable
    //   798	808	1862	finally
    //   808	815	1933	finally
    //   822	853	1944	finally
    //   853	894	1944	finally
    //   899	916	1944	finally
    //   916	936	1944	finally
    //   1776	1793	1944	finally
    //   940	973	1960	finally
    //   973	1021	1960	finally
    //   1285	1301	1960	finally
    //   1306	1347	1960	finally
    //   1350	1391	1960	finally
    //   1404	1459	1960	finally
    //   1536	1582	1960	finally
    //   1587	1631	1960	finally
    //   1631	1661	1960	finally
    //   1797	1804	1960	finally
    //   1463	1470	1973	finally
    //   1475	1519	1973	finally
    //   1519	1526	1973	finally
    //   1038	1050	1990	finally
    //   798	808	1999	java/lang/Throwable
    //   808	815	2013	java/lang/Throwable
    //   822	853	2028	java/lang/Throwable
    //   853	894	2028	java/lang/Throwable
    //   899	916	2028	java/lang/Throwable
    //   916	936	2028	java/lang/Throwable
    //   1776	1793	2028	java/lang/Throwable
    //   1463	1470	2048	java/lang/Throwable
    //   1475	1519	2048	java/lang/Throwable
    //   1519	1526	2048	java/lang/Throwable
  }

  protected static List<a> a(Cursor paramCursor)
  {
    e.b("rqdp{  in AnalyticsDAO.paserCursor() start}", new Object[0]);
    if (paramCursor == null)
      return null;
    ArrayList localArrayList = new ArrayList();
    int k = paramCursor.getColumnIndex("_id");
    int m = paramCursor.getColumnIndex("_prority");
    int n = paramCursor.getColumnIndex("_time");
    int i1 = paramCursor.getColumnIndex("_type");
    int i2 = paramCursor.getColumnIndex("_datas");
    int i3 = paramCursor.getColumnIndex("_length");
    int i4 = paramCursor.getColumnIndex("_key");
    int i5 = paramCursor.getColumnIndex("_count");
    int i6 = paramCursor.getColumnIndex("_upCounts");
    int i7 = paramCursor.getColumnIndex("_state");
    while (paramCursor.moveToNext())
    {
      a locala = new a();
      locala.a = paramCursor.getLong(k);
      locala.e = paramCursor.getBlob(i2);
      locala.c = paramCursor.getInt(m);
      locala.d = paramCursor.getLong(n);
      locala.b = paramCursor.getInt(i1);
      locala.f = paramCursor.getLong(i3);
      locala.g = paramCursor.getString(i4);
      locala.a(paramCursor.getInt(i5));
      locala.b(paramCursor.getInt(i6));
      locala.c(paramCursor.getInt(i7));
      localArrayList.add(locala);
    }
    e.b("rqdp{  in AnalyticsDAO.paserCursor() end}", new Object[0]);
    return localArrayList;
  }

  // ERROR //
  public static boolean a(android.content.Context paramContext, List<a> paramList)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: ldc_w 460
    //   5: iconst_0
    //   6: anewarray 4	java/lang/Object
    //   9: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   12: aload_0
    //   13: ifnull +7 -> 20
    //   16: aload_1
    //   17: ifnonnull +15 -> 32
    //   20: ldc_w 462
    //   23: iconst_0
    //   24: anewarray 4	java/lang/Object
    //   27: invokestatic 78	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   30: iconst_0
    //   31: ireturn
    //   32: aload_1
    //   33: invokeinterface 359 1 0
    //   38: ifgt +15 -> 53
    //   41: ldc_w 464
    //   44: iconst_0
    //   45: anewarray 4	java/lang/Object
    //   48: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   51: iconst_1
    //   52: ireturn
    //   53: new 67	com/tencent/feedback/common/a/c
    //   56: dup
    //   57: aload_0
    //   58: invokespecial 70	com/tencent/feedback/common/a/c:<init>	(Landroid/content/Context;)V
    //   61: astore_3
    //   62: aload_3
    //   63: invokevirtual 74	com/tencent/feedback/common/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   66: astore 11
    //   68: aload 11
    //   70: astore 9
    //   72: iconst_0
    //   73: istore 12
    //   75: iload 12
    //   77: aload_1
    //   78: invokeinterface 359 1 0
    //   83: if_icmpge +97 -> 180
    //   86: aload_1
    //   87: iload 12
    //   89: invokeinterface 468 2 0
    //   94: checkcast 2	com/tencent/feedback/common/a/a
    //   97: astore 14
    //   99: aload 9
    //   101: ldc 171
    //   103: ldc_w 257
    //   106: aload 14
    //   108: invokestatic 470	com/tencent/feedback/common/a/a:a	(Lcom/tencent/feedback/common/a/a;)Landroid/content/ContentValues;
    //   111: invokevirtual 474	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   114: lstore 15
    //   116: lload 15
    //   118: lconst_0
    //   119: lcmp
    //   120: ifge +47 -> 167
    //   123: ldc_w 476
    //   126: iconst_0
    //   127: anewarray 4	java/lang/Object
    //   130: invokestatic 78	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   133: aload 9
    //   135: ifnull +16 -> 151
    //   138: aload 9
    //   140: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   143: ifeq +8 -> 151
    //   146: aload 9
    //   148: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   151: aload_3
    //   152: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   155: ldc_w 478
    //   158: iconst_0
    //   159: anewarray 4	java/lang/Object
    //   162: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   165: iconst_0
    //   166: ireturn
    //   167: aload 14
    //   169: lload 15
    //   171: putfield 26	com/tencent/feedback/common/a/a:a	J
    //   174: iinc 12 1
    //   177: goto -102 -> 75
    //   180: aload 9
    //   182: ifnull +16 -> 198
    //   185: aload 9
    //   187: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   190: ifeq +8 -> 198
    //   193: aload 9
    //   195: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   198: aload_3
    //   199: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   202: ldc_w 478
    //   205: iconst_0
    //   206: anewarray 4	java/lang/Object
    //   209: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   212: iconst_1
    //   213: ireturn
    //   214: astore 18
    //   216: aconst_null
    //   217: astore 5
    //   219: ldc_w 480
    //   222: iconst_0
    //   223: anewarray 4	java/lang/Object
    //   226: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   229: aload_2
    //   230: ifnull +14 -> 244
    //   233: aload_2
    //   234: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   237: ifeq +7 -> 244
    //   240: aload_2
    //   241: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   244: aload 5
    //   246: ifnull +8 -> 254
    //   249: aload 5
    //   251: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   254: ldc_w 478
    //   257: iconst_0
    //   258: anewarray 4	java/lang/Object
    //   261: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   264: iconst_0
    //   265: ireturn
    //   266: astore 17
    //   268: aconst_null
    //   269: astore 9
    //   271: aconst_null
    //   272: astore_3
    //   273: aload 17
    //   275: astore 8
    //   277: aload 9
    //   279: ifnull +16 -> 295
    //   282: aload 9
    //   284: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   287: ifeq +8 -> 295
    //   290: aload 9
    //   292: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   295: aload_3
    //   296: ifnull +7 -> 303
    //   299: aload_3
    //   300: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   303: ldc_w 478
    //   306: iconst_0
    //   307: anewarray 4	java/lang/Object
    //   310: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   313: aload 8
    //   315: athrow
    //   316: astore 10
    //   318: aload 10
    //   320: astore 8
    //   322: aconst_null
    //   323: astore 9
    //   325: goto -48 -> 277
    //   328: astore 8
    //   330: goto -53 -> 277
    //   333: astore 6
    //   335: aload 5
    //   337: astore_3
    //   338: aload_2
    //   339: astore 7
    //   341: aload 6
    //   343: astore 8
    //   345: aload 7
    //   347: astore 9
    //   349: goto -72 -> 277
    //   352: astore 4
    //   354: aload_3
    //   355: astore 5
    //   357: aconst_null
    //   358: astore_2
    //   359: goto -140 -> 219
    //   362: astore 13
    //   364: aload 9
    //   366: astore_2
    //   367: aload_3
    //   368: astore 5
    //   370: goto -151 -> 219
    //
    // Exception table:
    //   from	to	target	type
    //   53	62	214	java/lang/Throwable
    //   53	62	266	finally
    //   62	68	316	finally
    //   75	116	328	finally
    //   123	133	328	finally
    //   167	174	328	finally
    //   219	229	333	finally
    //   62	68	352	java/lang/Throwable
    //   75	116	362	java/lang/Throwable
    //   123	133	362	java/lang/Throwable
    //   167	174	362	java/lang/Throwable
  }

  protected static d b(Cursor paramCursor)
  {
    if ((paramCursor == null) || (paramCursor.isBeforeFirst()) || (paramCursor.isAfterLast()))
      return null;
    d locald = new d();
    try
    {
      locald.a(paramCursor.getString(paramCursor.getColumnIndex("_n")));
      locald.b(paramCursor.getString(paramCursor.getColumnIndex("_sa")));
      locald.a(paramCursor.getInt(paramCursor.getColumnIndex("_id")));
      locald.a(paramCursor.getInt(paramCursor.getColumnIndex("_t")));
      locald.c(paramCursor.getLong(paramCursor.getColumnIndex("_sz")));
      locald.b(paramCursor.getLong(paramCursor.getColumnIndex("_ut")));
      locald.c(paramCursor.getString(paramCursor.getColumnIndex("_ac")));
      return locald;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      e.d("rqdp{  Error:getFileBean fail!}", new Object[0]);
      e.g("rqdp{  Error: getFileBean fail!}", new Object[0]);
    }
    return null;
  }

  // ERROR //
  public static boolean b(android.content.Context paramContext, List<a> paramList)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: ldc_w 516
    //   5: iconst_0
    //   6: anewarray 4	java/lang/Object
    //   9: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   12: aload_0
    //   13: ifnull +16 -> 29
    //   16: aload_1
    //   17: ifnull +12 -> 29
    //   20: aload_1
    //   21: invokeinterface 359 1 0
    //   26: ifgt +15 -> 41
    //   29: ldc_w 518
    //   32: iconst_0
    //   33: anewarray 4	java/lang/Object
    //   36: invokestatic 78	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   39: iconst_0
    //   40: ireturn
    //   41: new 67	com/tencent/feedback/common/a/c
    //   44: dup
    //   45: aload_0
    //   46: invokespecial 70	com/tencent/feedback/common/a/c:<init>	(Landroid/content/Context;)V
    //   49: astore_3
    //   50: aload_3
    //   51: invokevirtual 74	com/tencent/feedback/common/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   54: astore_2
    //   55: aload_1
    //   56: invokeinterface 522 1 0
    //   61: astore 6
    //   63: aload 6
    //   65: invokeinterface 527 1 0
    //   70: ifeq +196 -> 266
    //   73: aload 6
    //   75: invokeinterface 531 1 0
    //   80: checkcast 2	com/tencent/feedback/common/a/a
    //   83: astore 7
    //   85: aload 7
    //   87: invokestatic 470	com/tencent/feedback/common/a/a:a	(Lcom/tencent/feedback/common/a/a;)Landroid/content/ContentValues;
    //   90: astore 8
    //   92: aload 8
    //   94: ifnonnull +34 -> 128
    //   97: aload_2
    //   98: ifnull +14 -> 112
    //   101: aload_2
    //   102: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   105: ifeq +7 -> 112
    //   108: aload_2
    //   109: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   112: aload_3
    //   113: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   116: ldc_w 533
    //   119: iconst_0
    //   120: anewarray 4	java/lang/Object
    //   123: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   126: iconst_0
    //   127: ireturn
    //   128: aload_2
    //   129: ldc 171
    //   131: ldc_w 257
    //   134: aload 8
    //   136: invokevirtual 536	android/database/sqlite/SQLiteDatabase:replace	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   139: lstore 9
    //   141: lload 9
    //   143: lconst_0
    //   144: lcmp
    //   145: ifge +44 -> 189
    //   148: ldc_w 538
    //   151: iconst_0
    //   152: anewarray 4	java/lang/Object
    //   155: invokestatic 78	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   158: aload_2
    //   159: ifnull +14 -> 173
    //   162: aload_2
    //   163: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   166: ifeq +7 -> 173
    //   169: aload_2
    //   170: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   173: aload_3
    //   174: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   177: ldc_w 533
    //   180: iconst_0
    //   181: anewarray 4	java/lang/Object
    //   184: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   187: iconst_0
    //   188: ireturn
    //   189: new 96	java/lang/StringBuilder
    //   192: dup
    //   193: ldc_w 540
    //   196: invokespecial 101	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   199: lload 9
    //   201: invokevirtual 141	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   204: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   207: iconst_0
    //   208: anewarray 4	java/lang/Object
    //   211: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   214: aload 7
    //   216: lload 9
    //   218: putfield 26	com/tencent/feedback/common/a/a:a	J
    //   221: goto -158 -> 63
    //   224: astore 5
    //   226: aload 5
    //   228: invokevirtual 129	java/lang/Throwable:printStackTrace	()V
    //   231: aload_2
    //   232: ifnull +14 -> 246
    //   235: aload_2
    //   236: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   239: ifeq +7 -> 246
    //   242: aload_2
    //   243: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   246: aload_3
    //   247: ifnull +7 -> 254
    //   250: aload_3
    //   251: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   254: ldc_w 533
    //   257: iconst_0
    //   258: anewarray 4	java/lang/Object
    //   261: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   264: iconst_0
    //   265: ireturn
    //   266: aload_2
    //   267: ifnull +14 -> 281
    //   270: aload_2
    //   271: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   274: ifeq +7 -> 281
    //   277: aload_2
    //   278: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   281: aload_3
    //   282: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   285: ldc_w 533
    //   288: iconst_0
    //   289: anewarray 4	java/lang/Object
    //   292: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   295: iconst_1
    //   296: ireturn
    //   297: astore 4
    //   299: aconst_null
    //   300: astore_3
    //   301: aload_2
    //   302: ifnull +14 -> 316
    //   305: aload_2
    //   306: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   309: ifeq +7 -> 316
    //   312: aload_2
    //   313: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   316: aload_3
    //   317: ifnull +7 -> 324
    //   320: aload_3
    //   321: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   324: ldc_w 533
    //   327: iconst_0
    //   328: anewarray 4	java/lang/Object
    //   331: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   334: aload 4
    //   336: athrow
    //   337: astore 4
    //   339: goto -38 -> 301
    //   342: astore 5
    //   344: aconst_null
    //   345: astore_2
    //   346: aconst_null
    //   347: astore_3
    //   348: goto -122 -> 226
    //
    // Exception table:
    //   from	to	target	type
    //   50	63	224	java/lang/Throwable
    //   63	92	224	java/lang/Throwable
    //   128	141	224	java/lang/Throwable
    //   148	158	224	java/lang/Throwable
    //   189	221	224	java/lang/Throwable
    //   41	50	297	finally
    //   50	63	337	finally
    //   63	92	337	finally
    //   128	141	337	finally
    //   148	158	337	finally
    //   189	221	337	finally
    //   226	231	337	finally
    //   41	50	342	java/lang/Throwable
  }

  // ERROR //
  public static int c(android.content.Context paramContext, List<d> paramList)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +16 -> 17
    //   4: aload_1
    //   5: ifnull +12 -> 17
    //   8: aload_1
    //   9: invokeinterface 359 1 0
    //   14: ifne +52 -> 66
    //   17: iconst_1
    //   18: anewarray 4	java/lang/Object
    //   21: astore_2
    //   22: aload_1
    //   23: ifnonnull +20 -> 43
    //   26: ldc_w 543
    //   29: astore_3
    //   30: aload_2
    //   31: iconst_0
    //   32: aload_3
    //   33: aastore
    //   34: ldc_w 545
    //   37: aload_2
    //   38: invokestatic 55	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   41: iconst_m1
    //   42: ireturn
    //   43: new 96	java/lang/StringBuilder
    //   46: dup
    //   47: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   50: aload_1
    //   51: invokeinterface 359 1 0
    //   56: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   59: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   62: astore_3
    //   63: goto -33 -> 30
    //   66: iconst_1
    //   67: anewarray 4	java/lang/Object
    //   70: astore 4
    //   72: aload 4
    //   74: iconst_0
    //   75: aload_1
    //   76: invokeinterface 359 1 0
    //   81: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   84: aastore
    //   85: ldc_w 547
    //   88: aload 4
    //   90: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   93: aconst_null
    //   94: astore 5
    //   96: iconst_0
    //   97: istore 6
    //   99: new 67	com/tencent/feedback/common/a/c
    //   102: dup
    //   103: aload_0
    //   104: invokespecial 70	com/tencent/feedback/common/a/c:<init>	(Landroid/content/Context;)V
    //   107: astore 7
    //   109: aload 7
    //   111: invokevirtual 74	com/tencent/feedback/common/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   114: astore 5
    //   116: aload 5
    //   118: ifnonnull +70 -> 188
    //   121: ldc_w 306
    //   124: iconst_0
    //   125: anewarray 4	java/lang/Object
    //   128: invokestatic 78	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   131: ldc_w 549
    //   134: iconst_0
    //   135: anewarray 4	java/lang/Object
    //   138: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   141: aload 5
    //   143: ifnull +16 -> 159
    //   146: aload 5
    //   148: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   151: ifeq +8 -> 159
    //   154: aload 5
    //   156: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   159: aload 7
    //   161: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   164: iconst_1
    //   165: anewarray 4	java/lang/Object
    //   168: astore 13
    //   170: aload 13
    //   172: iconst_0
    //   173: iconst_0
    //   174: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   177: aastore
    //   178: ldc_w 551
    //   181: aload 13
    //   183: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   186: iconst_m1
    //   187: ireturn
    //   188: aload_1
    //   189: invokeinterface 522 1 0
    //   194: astore 14
    //   196: aload 14
    //   198: invokeinterface 527 1 0
    //   203: ifeq +315 -> 518
    //   206: aload 14
    //   208: invokeinterface 531 1 0
    //   213: checkcast 339	com/tencent/feedback/common/a/d
    //   216: astore 16
    //   218: aload 16
    //   220: ifnonnull +94 -> 314
    //   223: aconst_null
    //   224: astore 17
    //   226: aload 17
    //   228: ifnull +417 -> 645
    //   231: aload 5
    //   233: ldc 111
    //   235: ldc_w 257
    //   238: aload 17
    //   240: invokevirtual 474	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   243: lstore 18
    //   245: aload 16
    //   247: lload 18
    //   249: invokevirtual 496	com/tencent/feedback/common/a/d:a	(J)V
    //   252: lload 18
    //   254: lconst_0
    //   255: lcmp
    //   256: iflt +250 -> 506
    //   259: iconst_1
    //   260: istore 20
    //   262: iload 6
    //   264: iload 20
    //   266: iadd
    //   267: istore 6
    //   269: iconst_2
    //   270: anewarray 4	java/lang/Object
    //   273: astore 21
    //   275: aload 21
    //   277: iconst_0
    //   278: aload 16
    //   280: invokevirtual 341	com/tencent/feedback/common/a/d:a	()Ljava/lang/String;
    //   283: aastore
    //   284: lload 18
    //   286: lconst_0
    //   287: lcmp
    //   288: iflt +224 -> 512
    //   291: iconst_1
    //   292: istore 22
    //   294: aload 21
    //   296: iconst_1
    //   297: iload 22
    //   299: invokestatic 556	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   302: aastore
    //   303: ldc_w 558
    //   306: aload 21
    //   308: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   311: goto +334 -> 645
    //   314: new 254	android/content/ContentValues
    //   317: dup
    //   318: invokespecial 255	android/content/ContentValues:<init>	()V
    //   321: astore 17
    //   323: aload 17
    //   325: ldc_w 316
    //   328: aload 16
    //   330: invokevirtual 341	com/tencent/feedback/common/a/d:a	()Ljava/lang/String;
    //   333: invokevirtual 285	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   336: aload 17
    //   338: ldc_w 491
    //   341: aload 16
    //   343: invokevirtual 343	com/tencent/feedback/common/a/d:d	()Ljava/lang/String;
    //   346: invokevirtual 285	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   349: aload 17
    //   351: ldc_w 500
    //   354: aload 16
    //   356: invokevirtual 560	com/tencent/feedback/common/a/d:c	()J
    //   359: invokestatic 260	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   362: invokevirtual 264	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   365: aload 17
    //   367: ldc_w 504
    //   370: aload 16
    //   372: invokevirtual 562	com/tencent/feedback/common/a/d:b	()J
    //   375: invokestatic 260	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   378: invokevirtual 264	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   381: aload 17
    //   383: ldc_w 322
    //   386: aload 16
    //   388: invokevirtual 344	com/tencent/feedback/common/a/d:e	()I
    //   391: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   394: invokevirtual 269	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   397: aload 17
    //   399: ldc_w 508
    //   402: aload 16
    //   404: invokevirtual 346	com/tencent/feedback/common/a/d:f	()Ljava/lang/String;
    //   407: invokevirtual 285	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   410: goto -184 -> 226
    //   413: astore 10
    //   415: ldc 121
    //   417: iconst_0
    //   418: anewarray 4	java/lang/Object
    //   421: invokestatic 78	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   424: iconst_1
    //   425: anewarray 4	java/lang/Object
    //   428: astore 11
    //   430: aload 11
    //   432: iconst_0
    //   433: aload 10
    //   435: invokevirtual 124	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   438: aastore
    //   439: ldc_w 564
    //   442: aload 11
    //   444: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   447: aload 10
    //   449: invokevirtual 129	java/lang/Throwable:printStackTrace	()V
    //   452: aload 5
    //   454: ifnull +16 -> 470
    //   457: aload 5
    //   459: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   462: ifeq +8 -> 470
    //   465: aload 5
    //   467: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   470: aload 7
    //   472: ifnull +8 -> 480
    //   475: aload 7
    //   477: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   480: iconst_1
    //   481: anewarray 4	java/lang/Object
    //   484: astore 12
    //   486: aload 12
    //   488: iconst_0
    //   489: iload 6
    //   491: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   494: aastore
    //   495: ldc_w 551
    //   498: aload 12
    //   500: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   503: iload 6
    //   505: ireturn
    //   506: iconst_0
    //   507: istore 20
    //   509: goto -247 -> 262
    //   512: iconst_0
    //   513: istore 22
    //   515: goto -221 -> 294
    //   518: aload 5
    //   520: ifnull +16 -> 536
    //   523: aload 5
    //   525: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   528: ifeq +8 -> 536
    //   531: aload 5
    //   533: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   536: aload 7
    //   538: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   541: iconst_1
    //   542: anewarray 4	java/lang/Object
    //   545: astore 15
    //   547: aload 15
    //   549: iconst_0
    //   550: iload 6
    //   552: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   555: aastore
    //   556: ldc_w 551
    //   559: aload 15
    //   561: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   564: iload 6
    //   566: ireturn
    //   567: astore 8
    //   569: aconst_null
    //   570: astore 7
    //   572: aload 5
    //   574: ifnull +16 -> 590
    //   577: aload 5
    //   579: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   582: ifeq +8 -> 590
    //   585: aload 5
    //   587: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   590: aload 7
    //   592: ifnull +8 -> 600
    //   595: aload 7
    //   597: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   600: iconst_1
    //   601: anewarray 4	java/lang/Object
    //   604: astore 9
    //   606: aload 9
    //   608: iconst_0
    //   609: iload 6
    //   611: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   614: aastore
    //   615: ldc_w 551
    //   618: aload 9
    //   620: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   623: aload 8
    //   625: athrow
    //   626: astore 8
    //   628: goto -56 -> 572
    //   631: astore 10
    //   633: iconst_0
    //   634: istore 6
    //   636: aconst_null
    //   637: astore 5
    //   639: aconst_null
    //   640: astore 7
    //   642: goto -227 -> 415
    //   645: goto -449 -> 196
    //
    // Exception table:
    //   from	to	target	type
    //   109	116	413	java/lang/Throwable
    //   121	141	413	java/lang/Throwable
    //   188	196	413	java/lang/Throwable
    //   196	218	413	java/lang/Throwable
    //   231	252	413	java/lang/Throwable
    //   269	284	413	java/lang/Throwable
    //   294	311	413	java/lang/Throwable
    //   314	410	413	java/lang/Throwable
    //   99	109	567	finally
    //   109	116	626	finally
    //   121	141	626	finally
    //   188	196	626	finally
    //   196	218	626	finally
    //   231	252	626	finally
    //   269	284	626	finally
    //   294	311	626	finally
    //   314	410	626	finally
    //   415	452	626	finally
    //   99	109	631	java/lang/Throwable
  }

  // ERROR //
  public static int d(android.content.Context paramContext, List<d> paramList)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: ifnull +16 -> 19
    //   6: aload_1
    //   7: ifnull +12 -> 19
    //   10: aload_1
    //   11: invokeinterface 359 1 0
    //   16: ifne +55 -> 71
    //   19: iconst_1
    //   20: anewarray 4	java/lang/Object
    //   23: astore_3
    //   24: aload_1
    //   25: ifnonnull +22 -> 47
    //   28: ldc_w 543
    //   31: astore 4
    //   33: aload_3
    //   34: iconst_0
    //   35: aload 4
    //   37: aastore
    //   38: ldc_w 566
    //   41: aload_3
    //   42: invokestatic 55	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   45: iconst_m1
    //   46: ireturn
    //   47: new 96	java/lang/StringBuilder
    //   50: dup
    //   51: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   54: aload_1
    //   55: invokeinterface 359 1 0
    //   60: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   63: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: astore 4
    //   68: goto -35 -> 33
    //   71: iconst_1
    //   72: anewarray 4	java/lang/Object
    //   75: astore 5
    //   77: aload 5
    //   79: iconst_0
    //   80: aload_1
    //   81: invokeinterface 359 1 0
    //   86: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   89: aastore
    //   90: ldc_w 568
    //   93: aload 5
    //   95: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   98: new 67	com/tencent/feedback/common/a/c
    //   101: dup
    //   102: aload_0
    //   103: invokespecial 70	com/tencent/feedback/common/a/c:<init>	(Landroid/content/Context;)V
    //   106: astore 6
    //   108: aload 6
    //   110: invokevirtual 74	com/tencent/feedback/common/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   113: astore_2
    //   114: aload_2
    //   115: ifnonnull +66 -> 181
    //   118: ldc 76
    //   120: iconst_0
    //   121: anewarray 4	java/lang/Object
    //   124: invokestatic 78	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   127: ldc_w 570
    //   130: iconst_0
    //   131: anewarray 4	java/lang/Object
    //   134: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   137: aload_2
    //   138: ifnull +14 -> 152
    //   141: aload_2
    //   142: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   145: ifeq +7 -> 152
    //   148: aload_2
    //   149: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   152: aload 6
    //   154: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   157: iconst_1
    //   158: anewarray 4	java/lang/Object
    //   161: astore 16
    //   163: aload 16
    //   165: iconst_0
    //   166: iconst_0
    //   167: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   170: aastore
    //   171: ldc_w 572
    //   174: aload 16
    //   176: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   179: iconst_m1
    //   180: ireturn
    //   181: new 222	java/lang/StringBuffer
    //   184: dup
    //   185: invokespecial 223	java/lang/StringBuffer:<init>	()V
    //   188: astore 17
    //   190: aload_1
    //   191: invokeinterface 522 1 0
    //   196: astore 18
    //   198: aload 18
    //   200: invokeinterface 527 1 0
    //   205: ifeq +201 -> 406
    //   208: aload 18
    //   210: invokeinterface 531 1 0
    //   215: checkcast 339	com/tencent/feedback/common/a/d
    //   218: astore 23
    //   220: aload 17
    //   222: ldc_w 574
    //   225: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   228: pop
    //   229: aload 17
    //   231: ldc_w 316
    //   234: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   237: pop
    //   238: aload 17
    //   240: ldc_w 318
    //   243: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   246: pop
    //   247: aload 17
    //   249: aload 23
    //   251: invokevirtual 341	com/tencent/feedback/common/a/d:a	()Ljava/lang/String;
    //   254: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   257: pop
    //   258: aload 17
    //   260: ldc_w 576
    //   263: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   266: pop
    //   267: aload 17
    //   269: ldc_w 322
    //   272: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   275: pop
    //   276: aload 17
    //   278: ldc_w 324
    //   281: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   284: pop
    //   285: aload 17
    //   287: aload 23
    //   289: invokevirtual 344	com/tencent/feedback/common/a/d:e	()I
    //   292: invokevirtual 327	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   295: pop
    //   296: aload 17
    //   298: ldc_w 371
    //   301: invokevirtual 234	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   304: pop
    //   305: goto -107 -> 198
    //   308: astore 10
    //   310: aload 10
    //   312: astore 11
    //   314: iconst_0
    //   315: istore 12
    //   317: aload 11
    //   319: invokevirtual 129	java/lang/Throwable:printStackTrace	()V
    //   322: ldc_w 578
    //   325: iconst_0
    //   326: anewarray 4	java/lang/Object
    //   329: invokestatic 78	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   332: iconst_1
    //   333: anewarray 4	java/lang/Object
    //   336: astore 14
    //   338: aload 14
    //   340: iconst_0
    //   341: aload 11
    //   343: invokevirtual 124	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   346: aastore
    //   347: ldc_w 580
    //   350: aload 14
    //   352: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   355: aload_2
    //   356: ifnull +14 -> 370
    //   359: aload_2
    //   360: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   363: ifeq +7 -> 370
    //   366: aload_2
    //   367: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   370: aload 6
    //   372: ifnull +8 -> 380
    //   375: aload 6
    //   377: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   380: iconst_1
    //   381: anewarray 4	java/lang/Object
    //   384: astore 15
    //   386: aload 15
    //   388: iconst_0
    //   389: iload 12
    //   391: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   394: aastore
    //   395: ldc_w 572
    //   398: aload 15
    //   400: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   403: iload 12
    //   405: ireturn
    //   406: aload 17
    //   408: iconst_2
    //   409: invokevirtual 237	java/lang/StringBuffer:substring	(I)Ljava/lang/String;
    //   412: astore 19
    //   414: aload_2
    //   415: ldc 111
    //   417: aload 19
    //   419: aconst_null
    //   420: invokevirtual 115	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   423: istore 20
    //   425: iload 20
    //   427: istore 12
    //   429: iconst_2
    //   430: anewarray 4	java/lang/Object
    //   433: astore 21
    //   435: aload 21
    //   437: iconst_0
    //   438: iload 12
    //   440: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   443: aastore
    //   444: aload 21
    //   446: iconst_1
    //   447: aload 19
    //   449: aastore
    //   450: ldc_w 582
    //   453: aload 21
    //   455: invokestatic 82	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   458: aload_2
    //   459: ifnull +14 -> 473
    //   462: aload_2
    //   463: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   466: ifeq +7 -> 473
    //   469: aload_2
    //   470: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   473: aload 6
    //   475: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   478: iconst_1
    //   479: anewarray 4	java/lang/Object
    //   482: astore 22
    //   484: aload 22
    //   486: iconst_0
    //   487: iload 12
    //   489: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   492: aastore
    //   493: ldc_w 572
    //   496: aload 22
    //   498: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   501: iload 12
    //   503: ireturn
    //   504: astore 7
    //   506: iconst_0
    //   507: istore 8
    //   509: aconst_null
    //   510: astore 6
    //   512: aload_2
    //   513: ifnull +14 -> 527
    //   516: aload_2
    //   517: invokevirtual 88	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   520: ifeq +7 -> 527
    //   523: aload_2
    //   524: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   527: aload 6
    //   529: ifnull +8 -> 537
    //   532: aload 6
    //   534: invokevirtual 92	com/tencent/feedback/common/a/c:close	()V
    //   537: iconst_1
    //   538: anewarray 4	java/lang/Object
    //   541: astore 9
    //   543: aload 9
    //   545: iconst_0
    //   546: iload 8
    //   548: invokestatic 61	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   551: aastore
    //   552: ldc_w 572
    //   555: aload 9
    //   557: invokestatic 65	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   560: aload 7
    //   562: athrow
    //   563: astore 7
    //   565: iconst_0
    //   566: istore 8
    //   568: goto -56 -> 512
    //   571: astore 13
    //   573: iload 12
    //   575: istore 8
    //   577: aload 13
    //   579: astore 7
    //   581: goto -69 -> 512
    //   584: astore 33
    //   586: aload 33
    //   588: astore 11
    //   590: iconst_0
    //   591: istore 12
    //   593: aconst_null
    //   594: astore_2
    //   595: aconst_null
    //   596: astore 6
    //   598: goto -281 -> 317
    //   601: astore 11
    //   603: goto -286 -> 317
    //
    // Exception table:
    //   from	to	target	type
    //   108	114	308	java/lang/Throwable
    //   118	137	308	java/lang/Throwable
    //   181	198	308	java/lang/Throwable
    //   198	305	308	java/lang/Throwable
    //   406	425	308	java/lang/Throwable
    //   98	108	504	finally
    //   108	114	563	finally
    //   118	137	563	finally
    //   181	198	563	finally
    //   198	305	563	finally
    //   406	425	563	finally
    //   317	355	571	finally
    //   429	458	571	finally
    //   98	108	584	java/lang/Throwable
    //   429	458	601	java/lang/Throwable
  }

  public final long a()
  {
    return this.a;
  }

  public final a a(int paramInt)
  {
    monitorenter;
    try
    {
      this.h = paramInt;
      monitorexit;
      return this;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final a a(long paramLong)
  {
    this.a = paramLong;
    return this;
  }

  public final a a(String paramString)
  {
    this.g = paramString;
    return this;
  }

  public final a b(int paramInt)
  {
    monitorenter;
    try
    {
      this.i = paramInt;
      monitorexit;
      return this;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final byte[] b()
  {
    return this.e;
  }

  public final int c()
  {
    monitorenter;
    try
    {
      int k = this.h;
      monitorexit;
      return k;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final a c(int paramInt)
  {
    monitorenter;
    try
    {
      this.j = paramInt;
      monitorexit;
      return this;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final int d()
  {
    monitorenter;
    try
    {
      int k = this.i;
      monitorexit;
      return k;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final int e()
  {
    monitorenter;
    try
    {
      int k = this.j;
      monitorexit;
      return k;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.a.a
 * JD-Core Version:    0.6.0
 */