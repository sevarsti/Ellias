package com.tencent.beacon.a.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
{
  private long a = -1L;
  private int b = -1;
  private int c = -1;
  private long d = -1L;
  private byte[] e = null;
  private long f = 0L;

  public a()
  {
  }

  public a(int paramInt1, int paramInt2, long paramLong, byte[] paramArrayOfByte)
  {
    this.b = paramInt1;
    this.c = paramInt2;
    this.d = paramLong;
    this.e = paramArrayOfByte;
    if (paramArrayOfByte != null)
      this.f = paramArrayOfByte.length;
  }

  // ERROR //
  public static int a(Context paramContext, int[] paramArrayOfInt, long paramLong1, long paramLong2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: ldc 37
    //   5: iconst_0
    //   6: anewarray 4	java/lang/Object
    //   9: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   12: aload_0
    //   13: ifnonnull +18 -> 31
    //   16: ldc 44
    //   18: iconst_0
    //   19: anewarray 4	java/lang/Object
    //   22: invokestatic 46	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   25: iconst_m1
    //   26: istore 8
    //   28: iload 8
    //   30: ireturn
    //   31: lload_2
    //   32: lload 4
    //   34: lcmp
    //   35: istore 7
    //   37: iconst_0
    //   38: istore 8
    //   40: iload 7
    //   42: ifgt -14 -> 28
    //   45: new 48	java/lang/StringBuilder
    //   48: dup
    //   49: ldc 50
    //   51: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   54: lload_2
    //   55: invokevirtual 57	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   58: ldc 59
    //   60: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: ldc 64
    //   65: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: lload 4
    //   70: invokevirtual 57	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   73: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   76: astore 9
    //   78: aload_1
    //   79: ifnull +365 -> 444
    //   82: aload_1
    //   83: arraylength
    //   84: ifle +360 -> 444
    //   87: ldc 70
    //   89: astore 18
    //   91: iconst_0
    //   92: istore 19
    //   94: iload 19
    //   96: aload_1
    //   97: arraylength
    //   98: if_icmpge +38 -> 136
    //   101: new 48	java/lang/StringBuilder
    //   104: dup
    //   105: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   108: aload 18
    //   110: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: ldc 73
    //   115: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: aload_1
    //   119: iload 19
    //   121: iaload
    //   122: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   125: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   128: astore 18
    //   130: iinc 19 1
    //   133: goto -39 -> 94
    //   136: aload 18
    //   138: iconst_4
    //   139: invokevirtual 82	java/lang/String:substring	(I)Ljava/lang/String;
    //   142: astore 20
    //   144: new 48	java/lang/StringBuilder
    //   147: dup
    //   148: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   151: aload 9
    //   153: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: ldc 84
    //   158: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: aload 20
    //   163: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: ldc 86
    //   168: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   174: astore 10
    //   176: new 48	java/lang/StringBuilder
    //   179: dup
    //   180: ldc 88
    //   182: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   185: aload 10
    //   187: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: iconst_0
    //   194: anewarray 4	java/lang/Object
    //   197: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   200: aload_0
    //   201: invokestatic 93	com/tencent/beacon/a/a/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/a/c;
    //   204: astore 15
    //   206: aload 15
    //   208: astore 13
    //   210: aload 13
    //   212: invokevirtual 97	com/tencent/beacon/a/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   215: astore 16
    //   217: aload 16
    //   219: astore 12
    //   221: aload 12
    //   223: ldc 99
    //   225: aload 10
    //   227: aconst_null
    //   228: invokevirtual 105	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   231: istore 17
    //   233: new 48	java/lang/StringBuilder
    //   236: dup
    //   237: ldc 107
    //   239: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   242: iload 17
    //   244: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   247: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   250: iconst_0
    //   251: anewarray 4	java/lang/Object
    //   254: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   257: aload 12
    //   259: ifnull +16 -> 275
    //   262: aload 12
    //   264: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   267: ifeq +8 -> 275
    //   270: aload 12
    //   272: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   275: aload 13
    //   277: ifnull +8 -> 285
    //   280: aload 13
    //   282: invokevirtual 115	com/tencent/beacon/a/a/c:close	()V
    //   285: ldc 117
    //   287: iconst_0
    //   288: anewarray 4	java/lang/Object
    //   291: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   294: iload 17
    //   296: ireturn
    //   297: astore 14
    //   299: aconst_null
    //   300: astore 12
    //   302: aload 14
    //   304: invokevirtual 120	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   307: iconst_0
    //   308: anewarray 4	java/lang/Object
    //   311: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   314: aload 12
    //   316: ifnull +16 -> 332
    //   319: aload 12
    //   321: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   324: ifeq +8 -> 332
    //   327: aload 12
    //   329: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   332: aload 6
    //   334: ifnull +8 -> 342
    //   337: aload 6
    //   339: invokevirtual 115	com/tencent/beacon/a/a/c:close	()V
    //   342: ldc 117
    //   344: iconst_0
    //   345: anewarray 4	java/lang/Object
    //   348: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   351: iconst_m1
    //   352: ireturn
    //   353: astore 11
    //   355: aconst_null
    //   356: astore 12
    //   358: aconst_null
    //   359: astore 13
    //   361: aload 12
    //   363: ifnull +16 -> 379
    //   366: aload 12
    //   368: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   371: ifeq +8 -> 379
    //   374: aload 12
    //   376: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   379: aload 13
    //   381: ifnull +8 -> 389
    //   384: aload 13
    //   386: invokevirtual 115	com/tencent/beacon/a/a/c:close	()V
    //   389: ldc 117
    //   391: iconst_0
    //   392: anewarray 4	java/lang/Object
    //   395: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   398: aload 11
    //   400: athrow
    //   401: astore 11
    //   403: aconst_null
    //   404: astore 12
    //   406: goto -45 -> 361
    //   409: astore 11
    //   411: goto -50 -> 361
    //   414: astore 11
    //   416: aload 6
    //   418: astore 13
    //   420: goto -59 -> 361
    //   423: astore 14
    //   425: aload 13
    //   427: astore 6
    //   429: aconst_null
    //   430: astore 12
    //   432: goto -130 -> 302
    //   435: astore 14
    //   437: aload 13
    //   439: astore 6
    //   441: goto -139 -> 302
    //   444: aload 9
    //   446: astore 10
    //   448: goto -272 -> 176
    //
    // Exception table:
    //   from	to	target	type
    //   200	206	297	java/lang/Throwable
    //   200	206	353	finally
    //   210	217	401	finally
    //   221	257	409	finally
    //   302	314	414	finally
    //   210	217	423	java/lang/Throwable
    //   221	257	435	java/lang/Throwable
  }

  // ERROR //
  public static int a(Context paramContext, Long[] paramArrayOfLong)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: ldc 123
    //   4: iconst_0
    //   5: anewarray 4	java/lang/Object
    //   8: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   11: aload_0
    //   12: ifnonnull +14 -> 26
    //   15: ldc 125
    //   17: iconst_0
    //   18: anewarray 4	java/lang/Object
    //   21: invokestatic 127	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   24: iconst_m1
    //   25: ireturn
    //   26: aload_1
    //   27: ifnull +8 -> 35
    //   30: aload_1
    //   31: arraylength
    //   32: ifgt +5 -> 37
    //   35: iconst_0
    //   36: ireturn
    //   37: aload_0
    //   38: invokestatic 93	com/tencent/beacon/a/a/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/a/c;
    //   41: astore 6
    //   43: aload 6
    //   45: astore 4
    //   47: aload 4
    //   49: invokevirtual 97	com/tencent/beacon/a/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   52: astore_2
    //   53: new 129	java/lang/StringBuffer
    //   56: dup
    //   57: invokespecial 130	java/lang/StringBuffer:<init>	()V
    //   60: astore 7
    //   62: iconst_0
    //   63: istore 8
    //   65: iconst_0
    //   66: istore 9
    //   68: iload 9
    //   70: aload_1
    //   71: arraylength
    //   72: if_icmpge +123 -> 195
    //   75: aload_1
    //   76: iload 9
    //   78: aaload
    //   79: invokevirtual 136	java/lang/Long:longValue	()J
    //   82: lstore 10
    //   84: aload 7
    //   86: new 48	java/lang/StringBuilder
    //   89: dup
    //   90: ldc 138
    //   92: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   95: lload 10
    //   97: invokevirtual 57	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   100: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: invokevirtual 141	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   106: pop
    //   107: iload 9
    //   109: ifle +281 -> 390
    //   112: iload 9
    //   114: bipush 25
    //   116: irem
    //   117: ifne +273 -> 390
    //   120: new 48	java/lang/StringBuilder
    //   123: dup
    //   124: ldc 143
    //   126: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   129: iload 9
    //   131: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   134: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   137: iconst_0
    //   138: anewarray 4	java/lang/Object
    //   141: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   144: iload 8
    //   146: aload_2
    //   147: ldc 99
    //   149: aload 7
    //   151: iconst_4
    //   152: invokevirtual 144	java/lang/StringBuffer:substring	(I)Ljava/lang/String;
    //   155: aconst_null
    //   156: invokevirtual 105	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   159: iadd
    //   160: istore 8
    //   162: aload 7
    //   164: iconst_0
    //   165: invokevirtual 148	java/lang/StringBuffer:setLength	(I)V
    //   168: new 48	java/lang/StringBuilder
    //   171: dup
    //   172: ldc 150
    //   174: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   177: iload 8
    //   179: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   182: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   185: iconst_0
    //   186: anewarray 4	java/lang/Object
    //   189: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   192: goto +198 -> 390
    //   195: aload 7
    //   197: invokevirtual 154	java/lang/StringBuffer:length	()I
    //   200: ifle +27 -> 227
    //   203: iload 8
    //   205: aload_2
    //   206: ldc 99
    //   208: aload 7
    //   210: iconst_4
    //   211: invokevirtual 144	java/lang/StringBuffer:substring	(I)Ljava/lang/String;
    //   214: aconst_null
    //   215: invokevirtual 105	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   218: iadd
    //   219: istore 8
    //   221: aload 7
    //   223: iconst_0
    //   224: invokevirtual 148	java/lang/StringBuffer:setLength	(I)V
    //   227: new 48	java/lang/StringBuilder
    //   230: dup
    //   231: ldc 156
    //   233: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   236: iload 8
    //   238: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   241: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   244: iconst_0
    //   245: anewarray 4	java/lang/Object
    //   248: invokestatic 46	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   251: aload_2
    //   252: ifnull +14 -> 266
    //   255: aload_2
    //   256: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   259: ifeq +7 -> 266
    //   262: aload_2
    //   263: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   266: aload 4
    //   268: ifnull +8 -> 276
    //   271: aload 4
    //   273: invokevirtual 115	com/tencent/beacon/a/a/c:close	()V
    //   276: ldc 158
    //   278: iconst_0
    //   279: anewarray 4	java/lang/Object
    //   282: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   285: iload 8
    //   287: ireturn
    //   288: astore 5
    //   290: aconst_null
    //   291: astore 4
    //   293: aload 5
    //   295: invokevirtual 120	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   298: iconst_0
    //   299: anewarray 4	java/lang/Object
    //   302: invokestatic 127	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   305: aload_2
    //   306: ifnull +14 -> 320
    //   309: aload_2
    //   310: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   313: ifeq +7 -> 320
    //   316: aload_2
    //   317: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   320: aload 4
    //   322: ifnull +8 -> 330
    //   325: aload 4
    //   327: invokevirtual 115	com/tencent/beacon/a/a/c:close	()V
    //   330: ldc 158
    //   332: iconst_0
    //   333: anewarray 4	java/lang/Object
    //   336: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   339: iconst_m1
    //   340: ireturn
    //   341: astore_3
    //   342: aconst_null
    //   343: astore 4
    //   345: aload_2
    //   346: ifnull +14 -> 360
    //   349: aload_2
    //   350: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   353: ifeq +7 -> 360
    //   356: aload_2
    //   357: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   360: aload 4
    //   362: ifnull +8 -> 370
    //   365: aload 4
    //   367: invokevirtual 115	com/tencent/beacon/a/a/c:close	()V
    //   370: ldc 158
    //   372: iconst_0
    //   373: anewarray 4	java/lang/Object
    //   376: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   379: aload_3
    //   380: athrow
    //   381: astore_3
    //   382: goto -37 -> 345
    //   385: astore 5
    //   387: goto -94 -> 293
    //   390: iinc 9 1
    //   393: goto -325 -> 68
    //
    // Exception table:
    //   from	to	target	type
    //   37	43	288	java/lang/Throwable
    //   37	43	341	finally
    //   47	62	381	finally
    //   68	107	381	finally
    //   120	192	381	finally
    //   195	227	381	finally
    //   227	251	381	finally
    //   293	305	381	finally
    //   47	62	385	java/lang/Throwable
    //   68	107	385	java/lang/Throwable
    //   120	192	385	java/lang/Throwable
    //   195	227	385	java/lang/Throwable
    //   227	251	385	java/lang/Throwable
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
    return localContentValues;
  }

  // ERROR //
  public static List<a> a(Context paramContext, int[] paramArrayOfInt, int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong2, long paramLong3)
  {
    // Byte code:
    //   0: ldc 196
    //   2: iconst_0
    //   3: anewarray 4	java/lang/Object
    //   6: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   9: aload_0
    //   10: ifnull +49 -> 59
    //   13: lload 4
    //   15: lconst_0
    //   16: lcmp
    //   17: ifeq +42 -> 59
    //   20: lload 13
    //   22: lconst_0
    //   23: lcmp
    //   24: ifle +11 -> 35
    //   27: lload 11
    //   29: lload 13
    //   31: lcmp
    //   32: ifgt +27 -> 59
    //   35: iload 8
    //   37: ifle +10 -> 47
    //   40: iload 7
    //   42: iload 8
    //   44: if_icmpgt +15 -> 59
    //   47: iload 10
    //   49: ifle +21 -> 70
    //   52: iload 9
    //   54: iload 10
    //   56: if_icmple +14 -> 70
    //   59: ldc 198
    //   61: iconst_0
    //   62: anewarray 4	java/lang/Object
    //   65: invokestatic 127	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   68: aconst_null
    //   69: areturn
    //   70: lload 4
    //   72: lconst_0
    //   73: lcmp
    //   74: ifge +8 -> 82
    //   77: ldc2_w 199
    //   80: lstore 4
    //   82: aload_1
    //   83: ifnull +1589 -> 1672
    //   86: aload_1
    //   87: arraylength
    //   88: ifle +1584 -> 1672
    //   91: ldc 70
    //   93: astore 66
    //   95: iconst_0
    //   96: istore 67
    //   98: iload 67
    //   100: aload_1
    //   101: arraylength
    //   102: if_icmpge +38 -> 140
    //   105: new 48	java/lang/StringBuilder
    //   108: dup
    //   109: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   112: aload 66
    //   114: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   117: ldc 73
    //   119: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: aload_1
    //   123: iload 67
    //   125: iaload
    //   126: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   129: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   132: astore 66
    //   134: iinc 67 1
    //   137: goto -39 -> 98
    //   140: new 48	java/lang/StringBuilder
    //   143: dup
    //   144: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   147: ldc 70
    //   149: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: aload 66
    //   154: iconst_4
    //   155: invokevirtual 82	java/lang/String:substring	(I)Ljava/lang/String;
    //   158: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   164: astore 15
    //   166: aload 15
    //   168: invokevirtual 201	java/lang/String:length	()I
    //   171: ifle +557 -> 728
    //   174: new 48	java/lang/StringBuilder
    //   177: dup
    //   178: ldc 203
    //   180: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   183: aload 15
    //   185: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: ldc 205
    //   190: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   196: astore 16
    //   198: lload 11
    //   200: lconst_0
    //   201: lcmp
    //   202: iflt +56 -> 258
    //   205: new 48	java/lang/StringBuilder
    //   208: dup
    //   209: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   212: aload 16
    //   214: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: astore 64
    //   219: aload 16
    //   221: invokevirtual 201	java/lang/String:length	()I
    //   224: ifle +511 -> 735
    //   227: ldc 207
    //   229: astore 65
    //   231: aload 64
    //   233: aload 65
    //   235: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: ldc 50
    //   240: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: lload 11
    //   245: invokevirtual 57	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   248: ldc 209
    //   250: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   256: astore 16
    //   258: lload 13
    //   260: lconst_0
    //   261: lcmp
    //   262: iflt +56 -> 318
    //   265: new 48	java/lang/StringBuilder
    //   268: dup
    //   269: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   272: aload 16
    //   274: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   277: astore 62
    //   279: aload 16
    //   281: invokevirtual 201	java/lang/String:length	()I
    //   284: ifle +458 -> 742
    //   287: ldc 207
    //   289: astore 63
    //   291: aload 62
    //   293: aload 63
    //   295: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: ldc 211
    //   300: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: lload 13
    //   305: invokevirtual 57	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   308: ldc 209
    //   310: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   313: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   316: astore 16
    //   318: ldc 70
    //   320: astore 17
    //   322: iload_2
    //   323: tableswitch	default:+21 -> 344, 1:+426->749, 2:+452->775
    //   345: tableswitch	default:+23 -> 368, 1:+456->801, 2:+482->827
    //   369: sipush 4821
    //   372: invokevirtual 217	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   375: ifeq +1290 -> 1665
    //   378: aload 17
    //   380: iconst_0
    //   381: bipush 253
    //   383: aload 17
    //   385: invokevirtual 201	java/lang/String:length	()I
    //   388: iadd
    //   389: invokevirtual 220	java/lang/String:substring	(II)Ljava/lang/String;
    //   392: astore 18
    //   394: ldc 222
    //   396: iconst_1
    //   397: anewarray 4	java/lang/Object
    //   400: dup
    //   401: iconst_0
    //   402: aload 16
    //   404: aastore
    //   405: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   408: aconst_null
    //   409: astore 19
    //   411: new 224	java/util/ArrayList
    //   414: dup
    //   415: invokespecial 225	java/util/ArrayList:<init>	()V
    //   418: astore 20
    //   420: aload_0
    //   421: invokestatic 93	com/tencent/beacon/a/a/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/a/c;
    //   424: astore 26
    //   426: aload 26
    //   428: astore 25
    //   430: aload 25
    //   432: invokevirtual 97	com/tencent/beacon/a/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   435: astore 27
    //   437: lload 4
    //   439: lconst_0
    //   440: lcmp
    //   441: ifle +919 -> 1360
    //   444: aload 18
    //   446: invokevirtual 201	java/lang/String:length	()I
    //   449: ifle +25 -> 474
    //   452: new 48	java/lang/StringBuilder
    //   455: dup
    //   456: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   459: aload 18
    //   461: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   464: ldc 213
    //   466: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   469: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   472: astore 18
    //   474: new 48	java/lang/StringBuilder
    //   477: dup
    //   478: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   481: aload 18
    //   483: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: ldc 227
    //   488: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   491: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   494: astore 34
    //   496: iconst_2
    //   497: anewarray 78	java/lang/String
    //   500: dup
    //   501: iconst_0
    //   502: ldc 164
    //   504: aastore
    //   505: dup
    //   506: iconst_1
    //   507: ldc 193
    //   509: aastore
    //   510: astore 35
    //   512: iload 6
    //   514: iflt +339 -> 853
    //   517: new 48	java/lang/StringBuilder
    //   520: dup
    //   521: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   524: iload 6
    //   526: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   529: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   532: astore 36
    //   534: aload 27
    //   536: ldc 99
    //   538: aload 35
    //   540: aload 16
    //   542: aconst_null
    //   543: aconst_null
    //   544: aconst_null
    //   545: aload 34
    //   547: aload 36
    //   549: invokevirtual 231	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   552: astore 37
    //   554: aload 37
    //   556: astore 23
    //   558: aload 23
    //   560: ldc 164
    //   562: invokeinterface 237 2 0
    //   567: istore 38
    //   569: aload 23
    //   571: ldc 193
    //   573: invokeinterface 237 2 0
    //   578: istore 39
    //   580: new 239	java/util/LinkedHashMap
    //   583: dup
    //   584: invokespecial 240	java/util/LinkedHashMap:<init>	()V
    //   587: astore 40
    //   589: aload 23
    //   591: invokeinterface 243 1 0
    //   596: ifeq +263 -> 859
    //   599: aload 23
    //   601: iload 38
    //   603: invokeinterface 247 2 0
    //   608: lstore 57
    //   610: aload 23
    //   612: iload 39
    //   614: invokeinterface 247 2 0
    //   619: lstore 59
    //   621: aload 40
    //   623: lload 57
    //   625: invokestatic 168	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   628: lload 59
    //   630: invokestatic 168	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   633: invokevirtual 250	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   636: pop
    //   637: goto -48 -> 589
    //   640: astore 33
    //   642: aload 27
    //   644: astore 19
    //   646: aload 33
    //   648: astore 21
    //   650: aload 25
    //   652: astore 22
    //   654: aload 21
    //   656: invokevirtual 120	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   659: iconst_0
    //   660: anewarray 4	java/lang/Object
    //   663: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   666: aload 23
    //   668: ifnull +20 -> 688
    //   671: aload 23
    //   673: invokeinterface 253 1 0
    //   678: ifne +10 -> 688
    //   681: aload 23
    //   683: invokeinterface 254 1 0
    //   688: aload 19
    //   690: ifnull +16 -> 706
    //   693: aload 19
    //   695: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   698: ifeq +8 -> 706
    //   701: aload 19
    //   703: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   706: aload 22
    //   708: ifnull +8 -> 716
    //   711: aload 22
    //   713: invokevirtual 115	com/tencent/beacon/a/a/c:close	()V
    //   716: ldc_w 256
    //   719: iconst_0
    //   720: anewarray 4	java/lang/Object
    //   723: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   726: aconst_null
    //   727: areturn
    //   728: ldc 70
    //   730: astore 16
    //   732: goto -534 -> 198
    //   735: ldc 70
    //   737: astore 65
    //   739: goto -508 -> 231
    //   742: ldc 70
    //   744: astore 63
    //   746: goto -455 -> 291
    //   749: new 48	java/lang/StringBuilder
    //   752: dup
    //   753: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   756: aload 17
    //   758: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   761: ldc_w 258
    //   764: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   767: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   770: astore 17
    //   772: goto -428 -> 344
    //   775: new 48	java/lang/StringBuilder
    //   778: dup
    //   779: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   782: aload 17
    //   784: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   787: ldc_w 260
    //   790: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   793: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   796: astore 17
    //   798: goto -454 -> 344
    //   801: new 48	java/lang/StringBuilder
    //   804: dup
    //   805: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   808: aload 17
    //   810: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   813: ldc_w 262
    //   816: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   819: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   822: astore 17
    //   824: goto -456 -> 368
    //   827: new 48	java/lang/StringBuilder
    //   830: dup
    //   831: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   834: aload 17
    //   836: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   839: ldc_w 264
    //   842: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   845: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   848: astore 17
    //   850: goto -482 -> 368
    //   853: aconst_null
    //   854: astore 36
    //   856: goto -322 -> 534
    //   859: aload 23
    //   861: invokeinterface 254 1 0
    //   866: aload 40
    //   868: lload 4
    //   870: invokestatic 269	com/tencent/beacon/a/e:a	(Ljava/util/LinkedHashMap;J)[Ljava/lang/Long;
    //   873: astore 41
    //   875: aload 41
    //   877: ifnull +421 -> 1298
    //   880: aload 41
    //   882: arraylength
    //   883: ifle +415 -> 1298
    //   886: new 48	java/lang/StringBuilder
    //   889: dup
    //   890: ldc_w 271
    //   893: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   896: aload 41
    //   898: arraylength
    //   899: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   902: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   905: iconst_0
    //   906: anewarray 4	java/lang/Object
    //   909: invokestatic 46	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   912: new 129	java/lang/StringBuffer
    //   915: dup
    //   916: invokespecial 130	java/lang/StringBuffer:<init>	()V
    //   919: astore 42
    //   921: iconst_0
    //   922: istore 43
    //   924: iload 43
    //   926: aload 41
    //   928: arraylength
    //   929: if_icmpge +181 -> 1110
    //   932: aload 41
    //   934: iload 43
    //   936: aaload
    //   937: invokevirtual 136	java/lang/Long:longValue	()J
    //   940: lstore 47
    //   942: aload 42
    //   944: new 48	java/lang/StringBuilder
    //   947: dup
    //   948: ldc 138
    //   950: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   953: lload 47
    //   955: invokevirtual 57	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   958: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   961: invokevirtual 141	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   964: pop
    //   965: iload 43
    //   967: ifle +691 -> 1658
    //   970: iload 43
    //   972: bipush 25
    //   974: irem
    //   975: ifne +683 -> 1658
    //   978: new 48	java/lang/StringBuilder
    //   981: dup
    //   982: ldc 143
    //   984: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   987: iload 43
    //   989: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   992: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   995: iconst_0
    //   996: anewarray 4	java/lang/Object
    //   999: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1002: aload 42
    //   1004: iconst_4
    //   1005: invokevirtual 144	java/lang/StringBuffer:substring	(I)Ljava/lang/String;
    //   1008: astore 51
    //   1010: aload 42
    //   1012: iconst_0
    //   1013: invokevirtual 148	java/lang/StringBuffer:setLength	(I)V
    //   1016: aload 27
    //   1018: ldc 99
    //   1020: aconst_null
    //   1021: aload 51
    //   1023: aconst_null
    //   1024: aconst_null
    //   1025: aconst_null
    //   1026: aload 34
    //   1028: invokevirtual 274	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   1031: astore 52
    //   1033: aload 52
    //   1035: astore 50
    //   1037: aload 50
    //   1039: invokestatic 277	com/tencent/beacon/a/a/a:a	(Landroid/database/Cursor;)Ljava/util/List;
    //   1042: astore 55
    //   1044: aload 55
    //   1046: ifnull +47 -> 1093
    //   1049: aload 55
    //   1051: invokeinterface 282 1 0
    //   1056: ifle +37 -> 1093
    //   1059: aload 20
    //   1061: aload 55
    //   1063: invokeinterface 286 2 0
    //   1068: pop
    //   1069: new 48	java/lang/StringBuilder
    //   1072: dup
    //   1073: ldc_w 288
    //   1076: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1079: iconst_0
    //   1080: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1083: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1086: iconst_0
    //   1087: anewarray 4	java/lang/Object
    //   1090: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1093: aload 50
    //   1095: invokeinterface 254 1 0
    //   1100: iinc 43 1
    //   1103: aload 50
    //   1105: astore 23
    //   1107: goto -183 -> 924
    //   1110: aload 42
    //   1112: invokevirtual 154	java/lang/StringBuffer:length	()I
    //   1115: ifle +90 -> 1205
    //   1118: aload 42
    //   1120: iconst_4
    //   1121: invokevirtual 144	java/lang/StringBuffer:substring	(I)Ljava/lang/String;
    //   1124: astore 44
    //   1126: aload 42
    //   1128: iconst_0
    //   1129: invokevirtual 148	java/lang/StringBuffer:setLength	(I)V
    //   1132: aload 27
    //   1134: ldc 99
    //   1136: aconst_null
    //   1137: aload 44
    //   1139: aconst_null
    //   1140: aconst_null
    //   1141: aconst_null
    //   1142: aload 34
    //   1144: invokevirtual 274	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   1147: astore 23
    //   1149: aload 23
    //   1151: invokestatic 277	com/tencent/beacon/a/a/a:a	(Landroid/database/Cursor;)Ljava/util/List;
    //   1154: astore 45
    //   1156: aload 45
    //   1158: ifnull +47 -> 1205
    //   1161: aload 45
    //   1163: invokeinterface 282 1 0
    //   1168: ifle +37 -> 1205
    //   1171: aload 20
    //   1173: aload 45
    //   1175: invokeinterface 286 2 0
    //   1180: pop
    //   1181: new 48	java/lang/StringBuilder
    //   1184: dup
    //   1185: ldc_w 288
    //   1188: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1191: iconst_0
    //   1192: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1195: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1198: iconst_0
    //   1199: anewarray 4	java/lang/Object
    //   1202: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1205: new 48	java/lang/StringBuilder
    //   1208: dup
    //   1209: ldc_w 290
    //   1212: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1215: aload 20
    //   1217: invokeinterface 282 1 0
    //   1222: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1225: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1228: iconst_0
    //   1229: anewarray 4	java/lang/Object
    //   1232: invokestatic 46	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1235: aload 23
    //   1237: ifnull +20 -> 1257
    //   1240: aload 23
    //   1242: invokeinterface 253 1 0
    //   1247: ifne +10 -> 1257
    //   1250: aload 23
    //   1252: invokeinterface 254 1 0
    //   1257: aload 27
    //   1259: ifnull +16 -> 1275
    //   1262: aload 27
    //   1264: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   1267: ifeq +8 -> 1275
    //   1270: aload 27
    //   1272: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   1275: aload 25
    //   1277: ifnull +8 -> 1285
    //   1280: aload 25
    //   1282: invokevirtual 115	com/tencent/beacon/a/a/c:close	()V
    //   1285: ldc_w 256
    //   1288: iconst_0
    //   1289: anewarray 4	java/lang/Object
    //   1292: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1295: aload 20
    //   1297: areturn
    //   1298: aload 23
    //   1300: ifnull +20 -> 1320
    //   1303: aload 23
    //   1305: invokeinterface 253 1 0
    //   1310: ifne +10 -> 1320
    //   1313: aload 23
    //   1315: invokeinterface 254 1 0
    //   1320: aload 27
    //   1322: ifnull +16 -> 1338
    //   1325: aload 27
    //   1327: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   1330: ifeq +8 -> 1338
    //   1333: aload 27
    //   1335: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   1338: aload 25
    //   1340: ifnull +8 -> 1348
    //   1343: aload 25
    //   1345: invokevirtual 115	com/tencent/beacon/a/a/c:close	()V
    //   1348: ldc_w 256
    //   1351: iconst_0
    //   1352: anewarray 4	java/lang/Object
    //   1355: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1358: aconst_null
    //   1359: areturn
    //   1360: aload 27
    //   1362: ldc 99
    //   1364: aconst_null
    //   1365: aload 16
    //   1367: aconst_null
    //   1368: aconst_null
    //   1369: aconst_null
    //   1370: aload 18
    //   1372: invokevirtual 274	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   1375: astore 30
    //   1377: aload 30
    //   1379: astore 23
    //   1381: aload 23
    //   1383: invokestatic 277	com/tencent/beacon/a/a/a:a	(Landroid/database/Cursor;)Ljava/util/List;
    //   1386: astore 32
    //   1388: aload 23
    //   1390: ifnull +20 -> 1410
    //   1393: aload 23
    //   1395: invokeinterface 253 1 0
    //   1400: ifne +10 -> 1410
    //   1403: aload 23
    //   1405: invokeinterface 254 1 0
    //   1410: aload 27
    //   1412: ifnull +16 -> 1428
    //   1415: aload 27
    //   1417: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   1420: ifeq +8 -> 1428
    //   1423: aload 27
    //   1425: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   1428: aload 25
    //   1430: ifnull +8 -> 1438
    //   1433: aload 25
    //   1435: invokevirtual 115	com/tencent/beacon/a/a/c:close	()V
    //   1438: ldc_w 256
    //   1441: iconst_0
    //   1442: anewarray 4	java/lang/Object
    //   1445: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1448: aload 32
    //   1450: areturn
    //   1451: astore 24
    //   1453: aconst_null
    //   1454: astore 25
    //   1456: aconst_null
    //   1457: astore 23
    //   1459: aload 23
    //   1461: ifnull +20 -> 1481
    //   1464: aload 23
    //   1466: invokeinterface 253 1 0
    //   1471: ifne +10 -> 1481
    //   1474: aload 23
    //   1476: invokeinterface 254 1 0
    //   1481: aload 19
    //   1483: ifnull +16 -> 1499
    //   1486: aload 19
    //   1488: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   1491: ifeq +8 -> 1499
    //   1494: aload 19
    //   1496: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   1499: aload 25
    //   1501: ifnull +8 -> 1509
    //   1504: aload 25
    //   1506: invokevirtual 115	com/tencent/beacon/a/a/c:close	()V
    //   1509: ldc_w 256
    //   1512: iconst_0
    //   1513: anewarray 4	java/lang/Object
    //   1516: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1519: aload 24
    //   1521: athrow
    //   1522: astore 24
    //   1524: aconst_null
    //   1525: astore 19
    //   1527: aconst_null
    //   1528: astore 23
    //   1530: goto -71 -> 1459
    //   1533: astore 29
    //   1535: aload 27
    //   1537: astore 19
    //   1539: aload 29
    //   1541: astore 24
    //   1543: aconst_null
    //   1544: astore 23
    //   1546: goto -87 -> 1459
    //   1549: astore 31
    //   1551: aload 27
    //   1553: astore 19
    //   1555: aload 31
    //   1557: astore 24
    //   1559: goto -100 -> 1459
    //   1562: astore 54
    //   1564: aload 50
    //   1566: astore 23
    //   1568: aload 27
    //   1570: astore 19
    //   1572: aload 54
    //   1574: astore 24
    //   1576: goto -117 -> 1459
    //   1579: astore 24
    //   1581: aload 22
    //   1583: astore 25
    //   1585: goto -126 -> 1459
    //   1588: astore 21
    //   1590: aconst_null
    //   1591: astore 22
    //   1593: aconst_null
    //   1594: astore 19
    //   1596: aconst_null
    //   1597: astore 23
    //   1599: goto -945 -> 654
    //   1602: astore 21
    //   1604: aload 25
    //   1606: astore 22
    //   1608: aconst_null
    //   1609: astore 19
    //   1611: aconst_null
    //   1612: astore 23
    //   1614: goto -960 -> 654
    //   1617: astore 28
    //   1619: aload 27
    //   1621: astore 19
    //   1623: aload 28
    //   1625: astore 21
    //   1627: aload 25
    //   1629: astore 22
    //   1631: aconst_null
    //   1632: astore 23
    //   1634: goto -980 -> 654
    //   1637: astore 53
    //   1639: aload 50
    //   1641: astore 23
    //   1643: aload 25
    //   1645: astore 22
    //   1647: aload 27
    //   1649: astore 19
    //   1651: aload 53
    //   1653: astore 21
    //   1655: goto -1001 -> 654
    //   1658: aload 23
    //   1660: astore 50
    //   1662: goto -562 -> 1100
    //   1665: aload 17
    //   1667: astore 18
    //   1669: goto -1275 -> 394
    //   1672: ldc 70
    //   1674: astore 15
    //   1676: goto -1510 -> 166
    //
    // Exception table:
    //   from	to	target	type
    //   558	589	640	java/lang/Throwable
    //   589	637	640	java/lang/Throwable
    //   859	875	640	java/lang/Throwable
    //   880	921	640	java/lang/Throwable
    //   924	965	640	java/lang/Throwable
    //   978	1033	640	java/lang/Throwable
    //   1110	1156	640	java/lang/Throwable
    //   1161	1205	640	java/lang/Throwable
    //   1205	1235	640	java/lang/Throwable
    //   1381	1388	640	java/lang/Throwable
    //   420	426	1451	finally
    //   430	437	1522	finally
    //   444	474	1533	finally
    //   474	512	1533	finally
    //   517	534	1533	finally
    //   534	554	1533	finally
    //   1360	1377	1533	finally
    //   558	589	1549	finally
    //   589	637	1549	finally
    //   859	875	1549	finally
    //   880	921	1549	finally
    //   924	965	1549	finally
    //   978	1033	1549	finally
    //   1110	1156	1549	finally
    //   1161	1205	1549	finally
    //   1205	1235	1549	finally
    //   1381	1388	1549	finally
    //   1037	1044	1562	finally
    //   1049	1093	1562	finally
    //   1093	1100	1562	finally
    //   654	666	1579	finally
    //   420	426	1588	java/lang/Throwable
    //   430	437	1602	java/lang/Throwable
    //   444	474	1617	java/lang/Throwable
    //   474	512	1617	java/lang/Throwable
    //   517	534	1617	java/lang/Throwable
    //   534	554	1617	java/lang/Throwable
    //   1360	1377	1617	java/lang/Throwable
    //   1037	1044	1637	java/lang/Throwable
    //   1049	1093	1637	java/lang/Throwable
    //   1093	1100	1637	java/lang/Throwable
  }

  protected static List<a> a(Cursor paramCursor)
  {
    com.tencent.beacon.d.a.b(" in AnalyticsDAO.paserCursor() start", new Object[0]);
    if (paramCursor == null)
      return null;
    ArrayList localArrayList = new ArrayList();
    int i = paramCursor.getColumnIndex("_id");
    int j = paramCursor.getColumnIndex("_prority");
    int k = paramCursor.getColumnIndex("_time");
    int m = paramCursor.getColumnIndex("_type");
    int n = paramCursor.getColumnIndex("_datas");
    int i1 = paramCursor.getColumnIndex("_length");
    while (paramCursor.moveToNext())
    {
      a locala = new a();
      locala.a = paramCursor.getLong(i);
      locala.e = paramCursor.getBlob(n);
      locala.c = paramCursor.getInt(j);
      locala.d = paramCursor.getLong(k);
      locala.b = paramCursor.getInt(m);
      locala.f = paramCursor.getLong(i1);
      localArrayList.add(locala);
    }
    com.tencent.beacon.d.a.b(" in AnalyticsDAO.paserCursor() end", new Object[0]);
    return localArrayList;
  }

  // ERROR //
  public static boolean a(Context paramContext, List<a> paramList)
  {
    // Byte code:
    //   0: ldc_w 310
    //   3: iconst_0
    //   4: anewarray 4	java/lang/Object
    //   7: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   10: aload_0
    //   11: ifnull +7 -> 18
    //   14: aload_1
    //   15: ifnonnull +15 -> 30
    //   18: ldc_w 312
    //   21: iconst_0
    //   22: anewarray 4	java/lang/Object
    //   25: invokestatic 127	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   28: iconst_0
    //   29: ireturn
    //   30: aload_1
    //   31: invokeinterface 282 1 0
    //   36: ifgt +15 -> 51
    //   39: ldc_w 314
    //   42: iconst_0
    //   43: anewarray 4	java/lang/Object
    //   46: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   49: iconst_1
    //   50: ireturn
    //   51: aconst_null
    //   52: astore_2
    //   53: aload_0
    //   54: invokestatic 93	com/tencent/beacon/a/a/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/a/c;
    //   57: invokevirtual 97	com/tencent/beacon/a/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   60: astore 8
    //   62: aload 8
    //   64: astore 4
    //   66: aload 4
    //   68: invokevirtual 317	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   71: iconst_0
    //   72: istore 10
    //   74: iload 10
    //   76: aload_1
    //   77: invokeinterface 282 1 0
    //   82: if_icmpge +62 -> 144
    //   85: aload_1
    //   86: iload 10
    //   88: invokeinterface 321 2 0
    //   93: checkcast 2	com/tencent/beacon/a/a/a
    //   96: astore 11
    //   98: aload 4
    //   100: ldc 99
    //   102: ldc 164
    //   104: aload 11
    //   106: invokestatic 323	com/tencent/beacon/a/a/a:a	(Lcom/tencent/beacon/a/a/a;)Landroid/content/ContentValues;
    //   109: invokevirtual 327	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   112: lstore 12
    //   114: lload 12
    //   116: lconst_0
    //   117: lcmp
    //   118: ifge +13 -> 131
    //   121: ldc_w 329
    //   124: iconst_0
    //   125: anewarray 4	java/lang/Object
    //   128: invokestatic 127	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   131: aload 11
    //   133: lload 12
    //   135: putfield 21	com/tencent/beacon/a/a/a:a	J
    //   138: iinc 10 1
    //   141: goto -67 -> 74
    //   144: aload 4
    //   146: invokevirtual 332	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   149: aload 4
    //   151: ifnull +21 -> 172
    //   154: aload 4
    //   156: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   159: ifeq +13 -> 172
    //   162: aload 4
    //   164: invokevirtual 335	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   167: aload 4
    //   169: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   172: ldc_w 337
    //   175: iconst_0
    //   176: anewarray 4	java/lang/Object
    //   179: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   182: iconst_1
    //   183: ireturn
    //   184: astore 6
    //   186: ldc_w 339
    //   189: iconst_0
    //   190: anewarray 4	java/lang/Object
    //   193: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   196: aload_2
    //   197: ifnull +18 -> 215
    //   200: aload_2
    //   201: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   204: ifeq +11 -> 215
    //   207: aload_2
    //   208: invokevirtual 335	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   211: aload_2
    //   212: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   215: ldc_w 337
    //   218: iconst_0
    //   219: anewarray 4	java/lang/Object
    //   222: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   225: iconst_0
    //   226: ireturn
    //   227: astore_3
    //   228: aconst_null
    //   229: astore 4
    //   231: aload_3
    //   232: astore 5
    //   234: aload 4
    //   236: ifnull +21 -> 257
    //   239: aload 4
    //   241: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   244: ifeq +13 -> 257
    //   247: aload 4
    //   249: invokevirtual 335	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   252: aload 4
    //   254: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   257: ldc_w 337
    //   260: iconst_0
    //   261: anewarray 4	java/lang/Object
    //   264: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   267: aload 5
    //   269: athrow
    //   270: astore 5
    //   272: goto -38 -> 234
    //   275: astore 7
    //   277: aload_2
    //   278: astore 4
    //   280: aload 7
    //   282: astore 5
    //   284: goto -50 -> 234
    //   287: astore 9
    //   289: aload 4
    //   291: astore_2
    //   292: goto -106 -> 186
    //
    // Exception table:
    //   from	to	target	type
    //   53	62	184	java/lang/Throwable
    //   53	62	227	finally
    //   66	71	270	finally
    //   74	114	270	finally
    //   121	131	270	finally
    //   131	138	270	finally
    //   144	149	270	finally
    //   186	196	275	finally
    //   66	71	287	java/lang/Throwable
    //   74	114	287	java/lang/Throwable
    //   121	131	287	java/lang/Throwable
    //   131	138	287	java/lang/Throwable
    //   144	149	287	java/lang/Throwable
  }

  // ERROR //
  public static int b(Context paramContext, int[] paramArrayOfInt, long paramLong1, long paramLong2)
  {
    // Byte code:
    //   0: ldc_w 341
    //   3: iconst_0
    //   4: anewarray 4	java/lang/Object
    //   7: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   10: aload_0
    //   11: ifnonnull +15 -> 26
    //   14: ldc_w 343
    //   17: iconst_0
    //   18: anewarray 4	java/lang/Object
    //   21: invokestatic 46	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   24: iconst_m1
    //   25: ireturn
    //   26: ldc2_w 18
    //   29: ldc2_w 199
    //   32: lcmp
    //   33: ifle +5 -> 38
    //   36: iconst_0
    //   37: ireturn
    //   38: new 48	java/lang/StringBuilder
    //   41: dup
    //   42: ldc 50
    //   44: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   47: ldc2_w 18
    //   50: invokevirtual 57	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   53: ldc 59
    //   55: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: ldc_w 345
    //   61: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   67: astore 6
    //   69: aload_1
    //   70: ifnull +97 -> 167
    //   73: aload_1
    //   74: arraylength
    //   75: ifle +92 -> 167
    //   78: ldc 70
    //   80: astore 23
    //   82: iconst_0
    //   83: istore 24
    //   85: iload 24
    //   87: aload_1
    //   88: arraylength
    //   89: if_icmpge +38 -> 127
    //   92: new 48	java/lang/StringBuilder
    //   95: dup
    //   96: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   99: aload 23
    //   101: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: ldc 73
    //   106: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload_1
    //   110: iload 24
    //   112: iaload
    //   113: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   116: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: astore 23
    //   121: iinc 24 1
    //   124: goto -39 -> 85
    //   127: aload 23
    //   129: iconst_4
    //   130: invokevirtual 82	java/lang/String:substring	(I)Ljava/lang/String;
    //   133: astore 25
    //   135: new 48	java/lang/StringBuilder
    //   138: dup
    //   139: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   142: aload 6
    //   144: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: ldc 84
    //   149: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: aload 25
    //   154: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: ldc 86
    //   159: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   165: astore 6
    //   167: new 48	java/lang/StringBuilder
    //   170: dup
    //   171: ldc_w 347
    //   174: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   177: aload 6
    //   179: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   185: iconst_0
    //   186: anewarray 4	java/lang/Object
    //   189: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   192: aconst_null
    //   193: astore 7
    //   195: aconst_null
    //   196: astore 8
    //   198: aload_0
    //   199: invokestatic 93	com/tencent/beacon/a/a/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/a/c;
    //   202: astore 14
    //   204: aload 14
    //   206: astore 10
    //   208: aload 10
    //   210: invokevirtual 97	com/tencent/beacon/a/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   213: astore 15
    //   215: aload 15
    //   217: ldc 99
    //   219: iconst_1
    //   220: anewarray 78	java/lang/String
    //   223: dup
    //   224: iconst_0
    //   225: ldc_w 349
    //   228: aastore
    //   229: aload 6
    //   231: aconst_null
    //   232: aconst_null
    //   233: aconst_null
    //   234: aconst_null
    //   235: invokevirtual 274	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   238: astore 18
    //   240: aload 18
    //   242: astore 11
    //   244: aload 11
    //   246: invokeinterface 243 1 0
    //   251: pop
    //   252: aload 11
    //   254: aload 11
    //   256: ldc_w 351
    //   259: invokeinterface 237 2 0
    //   264: invokeinterface 301 2 0
    //   269: istore 22
    //   271: new 48	java/lang/StringBuilder
    //   274: dup
    //   275: ldc_w 353
    //   278: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   281: iload 22
    //   283: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   286: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   289: iconst_0
    //   290: anewarray 4	java/lang/Object
    //   293: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   296: aload 11
    //   298: ifnull +20 -> 318
    //   301: aload 11
    //   303: invokeinterface 253 1 0
    //   308: ifne +10 -> 318
    //   311: aload 11
    //   313: invokeinterface 254 1 0
    //   318: aload 15
    //   320: ifnull +16 -> 336
    //   323: aload 15
    //   325: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   328: ifeq +8 -> 336
    //   331: aload 15
    //   333: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   336: aload 10
    //   338: ifnull +8 -> 346
    //   341: aload 10
    //   343: invokevirtual 115	com/tencent/beacon/a/a/c:close	()V
    //   346: ldc_w 355
    //   349: iconst_0
    //   350: anewarray 4	java/lang/Object
    //   353: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   356: iload 22
    //   358: ireturn
    //   359: astore 12
    //   361: aconst_null
    //   362: astore 13
    //   364: aload 12
    //   366: invokevirtual 120	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   369: iconst_0
    //   370: anewarray 4	java/lang/Object
    //   373: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   376: aload 13
    //   378: ifnull +20 -> 398
    //   381: aload 13
    //   383: invokeinterface 253 1 0
    //   388: ifne +10 -> 398
    //   391: aload 13
    //   393: invokeinterface 254 1 0
    //   398: aload 8
    //   400: ifnull +16 -> 416
    //   403: aload 8
    //   405: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   408: ifeq +8 -> 416
    //   411: aload 8
    //   413: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   416: aload 7
    //   418: ifnull +8 -> 426
    //   421: aload 7
    //   423: invokevirtual 115	com/tencent/beacon/a/a/c:close	()V
    //   426: ldc_w 355
    //   429: iconst_0
    //   430: anewarray 4	java/lang/Object
    //   433: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   436: iconst_m1
    //   437: ireturn
    //   438: astore 9
    //   440: aconst_null
    //   441: astore 10
    //   443: aconst_null
    //   444: astore 11
    //   446: aload 11
    //   448: ifnull +20 -> 468
    //   451: aload 11
    //   453: invokeinterface 253 1 0
    //   458: ifne +10 -> 468
    //   461: aload 11
    //   463: invokeinterface 254 1 0
    //   468: aload 8
    //   470: ifnull +16 -> 486
    //   473: aload 8
    //   475: invokevirtual 111	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   478: ifeq +8 -> 486
    //   481: aload 8
    //   483: invokevirtual 114	android/database/sqlite/SQLiteDatabase:close	()V
    //   486: aload 10
    //   488: ifnull +8 -> 496
    //   491: aload 10
    //   493: invokevirtual 115	com/tencent/beacon/a/a/c:close	()V
    //   496: ldc_w 355
    //   499: iconst_0
    //   500: anewarray 4	java/lang/Object
    //   503: invokestatic 42	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   506: aload 9
    //   508: athrow
    //   509: astore 9
    //   511: aconst_null
    //   512: astore 8
    //   514: aconst_null
    //   515: astore 11
    //   517: goto -71 -> 446
    //   520: astore 17
    //   522: aload 17
    //   524: astore 9
    //   526: aload 15
    //   528: astore 8
    //   530: aconst_null
    //   531: astore 11
    //   533: goto -87 -> 446
    //   536: astore 20
    //   538: aload 15
    //   540: astore 8
    //   542: aload 20
    //   544: astore 9
    //   546: goto -100 -> 446
    //   549: astore 9
    //   551: aload 7
    //   553: astore 10
    //   555: aload 13
    //   557: astore 11
    //   559: goto -113 -> 446
    //   562: astore 12
    //   564: aload 10
    //   566: astore 7
    //   568: aconst_null
    //   569: astore 8
    //   571: aconst_null
    //   572: astore 13
    //   574: goto -210 -> 364
    //   577: astore 16
    //   579: aload 10
    //   581: astore 7
    //   583: aload 15
    //   585: astore 8
    //   587: aload 16
    //   589: astore 12
    //   591: aconst_null
    //   592: astore 13
    //   594: goto -230 -> 364
    //   597: astore 19
    //   599: aload 11
    //   601: astore 13
    //   603: aload 10
    //   605: astore 7
    //   607: aload 19
    //   609: astore 12
    //   611: aload 15
    //   613: astore 8
    //   615: goto -251 -> 364
    //
    // Exception table:
    //   from	to	target	type
    //   198	204	359	java/lang/Throwable
    //   198	204	438	finally
    //   208	215	509	finally
    //   215	240	520	finally
    //   244	296	536	finally
    //   364	376	549	finally
    //   208	215	562	java/lang/Throwable
    //   215	240	577	java/lang/Throwable
    //   244	296	597	java/lang/Throwable
  }

  public static boolean b(Context paramContext, List<a> paramList)
  {
    com.tencent.beacon.d.a.b(" insertOrUpdate alyticsBeans start!", new Object[0]);
    if ((paramContext == null) || (paramList == null) || (paramList.size() <= 0))
    {
      com.tencent.beacon.d.a.d(" context == null || list == null|| list.size() <= 0 ? pls check!", new Object[0]);
      return false;
    }
    SQLiteDatabase localSQLiteDatabase = null;
    try
    {
      localSQLiteDatabase = c.a(paramContext).getWritableDatabase();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        a locala = (a)localIterator.next();
        ContentValues localContentValues = a(locala);
        if (localContentValues == null)
        {
          if ((localSQLiteDatabase != null) && (localSQLiteDatabase.isOpen()))
            localSQLiteDatabase.close();
          return false;
        }
        long l = localSQLiteDatabase.replace("t_event", "_id", localContentValues);
        if (l < 0L)
        {
          com.tencent.beacon.d.a.d(" insertOrUpdate alyticsBeans failure! return", new Object[0]);
          if ((localSQLiteDatabase != null) && (localSQLiteDatabase.isOpen()))
            localSQLiteDatabase.close();
          return false;
        }
        com.tencent.beacon.d.a.b(" result id:" + l, new Object[0]);
        locala.a = l;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      return false;
      return true;
    }
    finally
    {
      if ((localSQLiteDatabase != null) && (localSQLiteDatabase.isOpen()))
        localSQLiteDatabase.close();
      com.tencent.beacon.d.a.b(" insertOrUpdate alyticsBeans end", new Object[0]);
    }
    throw localObject;
  }

  public final long a()
  {
    return this.a;
  }

  public final a a(long paramLong)
  {
    this.a = paramLong;
    return this;
  }

  public final byte[] b()
  {
    return this.e;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.a.a.a
 * JD-Core Version:    0.6.0
 */