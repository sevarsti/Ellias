package com.tencent.beacon.applog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.beacon.event.h;
import com.tencent.beacon.event.m;
import com.tencent.beacon.upload.g;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class a
{
  private d a;
  private Context b;
  private Runnable c = new Runnable()
  {
    public final void run()
    {
      try
      {
        a.this.a();
        return;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    }
  };
  private Runnable d = new Runnable()
  {
    public final void run()
    {
      com.tencent.beacon.d.a.f(" maxN to up", new Object[0]);
      try
      {
        AppLogUploadAction.doUploadAppLogRecords();
        return;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    }
  };

  public a(Context paramContext)
  {
    this.b = paramContext;
  }

  // ERROR //
  public static int a(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: ldc 35
    //   4: iconst_0
    //   5: anewarray 4	java/lang/Object
    //   8: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   11: aload_0
    //   12: invokestatic 45	com/tencent/beacon/a/a/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/a/c;
    //   15: astore 8
    //   17: aload 8
    //   19: astore 4
    //   21: aload 4
    //   23: invokevirtual 49	com/tencent/beacon/a/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   26: astore 9
    //   28: aload 9
    //   30: ldc 51
    //   32: iconst_1
    //   33: anewarray 53	java/lang/String
    //   36: dup
    //   37: iconst_0
    //   38: ldc 55
    //   40: aastore
    //   41: aconst_null
    //   42: aconst_null
    //   43: aconst_null
    //   44: aconst_null
    //   45: aconst_null
    //   46: invokevirtual 61	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   49: astore 12
    //   51: aload 12
    //   53: astore_3
    //   54: aload_3
    //   55: invokeinterface 67 1 0
    //   60: pop
    //   61: aload_3
    //   62: aload_3
    //   63: ldc 69
    //   65: invokeinterface 73 2 0
    //   70: invokeinterface 77 2 0
    //   75: istore 16
    //   77: new 79	java/lang/StringBuilder
    //   80: dup
    //   81: ldc 81
    //   83: invokespecial 84	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   86: iload 16
    //   88: invokevirtual 88	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   91: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   94: iconst_0
    //   95: anewarray 4	java/lang/Object
    //   98: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   101: aload_3
    //   102: ifnull +18 -> 120
    //   105: aload_3
    //   106: invokeinterface 95 1 0
    //   111: ifne +9 -> 120
    //   114: aload_3
    //   115: invokeinterface 98 1 0
    //   120: aload 9
    //   122: ifnull +16 -> 138
    //   125: aload 9
    //   127: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   130: ifeq +8 -> 138
    //   133: aload 9
    //   135: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   138: aload 4
    //   140: ifnull +8 -> 148
    //   143: aload 4
    //   145: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   148: ldc 105
    //   150: iconst_0
    //   151: anewarray 4	java/lang/Object
    //   154: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   157: iload 16
    //   159: ireturn
    //   160: astore 5
    //   162: aconst_null
    //   163: astore 6
    //   165: aconst_null
    //   166: astore 7
    //   168: aload 5
    //   170: invokevirtual 108	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   173: iconst_0
    //   174: anewarray 4	java/lang/Object
    //   177: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   180: aload 6
    //   182: ifnull +20 -> 202
    //   185: aload 6
    //   187: invokeinterface 95 1 0
    //   192: ifne +10 -> 202
    //   195: aload 6
    //   197: invokeinterface 98 1 0
    //   202: aload_1
    //   203: ifnull +14 -> 217
    //   206: aload_1
    //   207: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   210: ifeq +7 -> 217
    //   213: aload_1
    //   214: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   217: aload 7
    //   219: ifnull +8 -> 227
    //   222: aload 7
    //   224: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   227: ldc 105
    //   229: iconst_0
    //   230: anewarray 4	java/lang/Object
    //   233: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   236: iconst_m1
    //   237: ireturn
    //   238: astore_2
    //   239: aconst_null
    //   240: astore_3
    //   241: aconst_null
    //   242: astore 4
    //   244: aload_3
    //   245: ifnull +18 -> 263
    //   248: aload_3
    //   249: invokeinterface 95 1 0
    //   254: ifne +9 -> 263
    //   257: aload_3
    //   258: invokeinterface 98 1 0
    //   263: aload_1
    //   264: ifnull +14 -> 278
    //   267: aload_1
    //   268: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   271: ifeq +7 -> 278
    //   274: aload_1
    //   275: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   278: aload 4
    //   280: ifnull +8 -> 288
    //   283: aload 4
    //   285: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   288: ldc 105
    //   290: iconst_0
    //   291: anewarray 4	java/lang/Object
    //   294: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   297: aload_2
    //   298: athrow
    //   299: astore_2
    //   300: aconst_null
    //   301: astore_3
    //   302: aconst_null
    //   303: astore_1
    //   304: goto -60 -> 244
    //   307: astore 11
    //   309: aload 9
    //   311: astore_1
    //   312: aload 11
    //   314: astore_2
    //   315: aconst_null
    //   316: astore_3
    //   317: goto -73 -> 244
    //   320: astore 14
    //   322: aload 9
    //   324: astore_1
    //   325: aload 14
    //   327: astore_2
    //   328: goto -84 -> 244
    //   331: astore_2
    //   332: aload 7
    //   334: astore 4
    //   336: aload 6
    //   338: astore_3
    //   339: goto -95 -> 244
    //   342: astore 5
    //   344: aload 4
    //   346: astore 7
    //   348: aconst_null
    //   349: astore 6
    //   351: aconst_null
    //   352: astore_1
    //   353: goto -185 -> 168
    //   356: astore 10
    //   358: aload 4
    //   360: astore 7
    //   362: aload 9
    //   364: astore_1
    //   365: aload 10
    //   367: astore 5
    //   369: aconst_null
    //   370: astore 6
    //   372: goto -204 -> 168
    //   375: astore 13
    //   377: aload 9
    //   379: astore_1
    //   380: aload 13
    //   382: astore 5
    //   384: aload_3
    //   385: astore 6
    //   387: aload 4
    //   389: astore 7
    //   391: goto -223 -> 168
    //
    // Exception table:
    //   from	to	target	type
    //   11	17	160	java/lang/Throwable
    //   11	17	238	finally
    //   21	28	299	finally
    //   28	51	307	finally
    //   54	101	320	finally
    //   168	180	331	finally
    //   21	28	342	java/lang/Throwable
    //   28	51	356	java/lang/Throwable
    //   54	101	375	java/lang/Throwable
  }

  // ERROR //
  public static int a(Context paramContext, List<d> paramList)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: ifnull +16 -> 19
    //   6: aload_1
    //   7: ifnull +12 -> 19
    //   10: aload_1
    //   11: invokeinterface 115 1 0
    //   16: ifne +53 -> 69
    //   19: iconst_1
    //   20: anewarray 4	java/lang/Object
    //   23: astore_3
    //   24: aload_1
    //   25: ifnonnull +20 -> 45
    //   28: ldc 117
    //   30: astore 4
    //   32: aload_3
    //   33: iconst_0
    //   34: aload 4
    //   36: aastore
    //   37: ldc 119
    //   39: aload_3
    //   40: invokestatic 121	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   43: iconst_m1
    //   44: ireturn
    //   45: new 79	java/lang/StringBuilder
    //   48: dup
    //   49: invokespecial 122	java/lang/StringBuilder:<init>	()V
    //   52: aload_1
    //   53: invokeinterface 115 1 0
    //   58: invokevirtual 88	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   61: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   64: astore 4
    //   66: goto -34 -> 32
    //   69: iconst_1
    //   70: anewarray 4	java/lang/Object
    //   73: astore 5
    //   75: aload 5
    //   77: iconst_0
    //   78: aload_1
    //   79: invokeinterface 115 1 0
    //   84: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   87: aastore
    //   88: ldc 130
    //   90: aload 5
    //   92: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   95: aload_0
    //   96: invokestatic 45	com/tencent/beacon/a/a/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/a/c;
    //   99: astore 13
    //   101: aload 13
    //   103: astore 8
    //   105: aload 8
    //   107: invokevirtual 49	com/tencent/beacon/a/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   110: astore_2
    //   111: aload_2
    //   112: ifnonnull +69 -> 181
    //   115: ldc 132
    //   117: iconst_0
    //   118: anewarray 4	java/lang/Object
    //   121: invokestatic 134	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   124: ldc 136
    //   126: iconst_0
    //   127: anewarray 4	java/lang/Object
    //   130: invokestatic 139	com/tencent/beacon/d/a:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   133: aload_2
    //   134: ifnull +14 -> 148
    //   137: aload_2
    //   138: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   141: ifeq +7 -> 148
    //   144: aload_2
    //   145: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   148: aload 8
    //   150: ifnull +8 -> 158
    //   153: aload 8
    //   155: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   158: iconst_1
    //   159: anewarray 4	java/lang/Object
    //   162: astore 14
    //   164: aload 14
    //   166: iconst_0
    //   167: iconst_0
    //   168: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   171: aastore
    //   172: ldc 141
    //   174: aload 14
    //   176: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   179: iconst_m1
    //   180: ireturn
    //   181: aload_1
    //   182: invokeinterface 145 1 0
    //   187: astore 15
    //   189: iconst_0
    //   190: istore 7
    //   192: aload 15
    //   194: invokeinterface 150 1 0
    //   199: ifeq +119 -> 318
    //   202: aload 15
    //   204: invokeinterface 154 1 0
    //   209: checkcast 156	com/tencent/beacon/applog/d
    //   212: astore 17
    //   214: aload 17
    //   216: invokestatic 159	com/tencent/beacon/applog/a:c	(Lcom/tencent/beacon/applog/d;)Landroid/content/ContentValues;
    //   219: astore 18
    //   221: aload 18
    //   223: ifnull +80 -> 303
    //   226: aload_2
    //   227: ldc 51
    //   229: ldc 161
    //   231: aload 18
    //   233: invokevirtual 165	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   236: lstore 19
    //   238: aload 17
    //   240: lload 19
    //   242: invokevirtual 168	com/tencent/beacon/applog/d:a	(J)V
    //   245: lload 19
    //   247: lconst_0
    //   248: lcmp
    //   249: iflt +57 -> 306
    //   252: iconst_1
    //   253: istore 21
    //   255: iload 7
    //   257: iload 21
    //   259: iadd
    //   260: istore 7
    //   262: iconst_2
    //   263: anewarray 4	java/lang/Object
    //   266: astore 22
    //   268: aload 22
    //   270: iconst_0
    //   271: aload 17
    //   273: invokevirtual 170	com/tencent/beacon/applog/d:b	()Ljava/lang/String;
    //   276: aastore
    //   277: lload 19
    //   279: lconst_0
    //   280: lcmp
    //   281: iflt +31 -> 312
    //   284: iconst_1
    //   285: istore 23
    //   287: aload 22
    //   289: iconst_1
    //   290: iload 23
    //   292: invokestatic 175	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   295: aastore
    //   296: ldc 177
    //   298: aload 22
    //   300: invokestatic 139	com/tencent/beacon/d/a:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   303: goto -111 -> 192
    //   306: iconst_0
    //   307: istore 21
    //   309: goto -54 -> 255
    //   312: iconst_0
    //   313: istore 23
    //   315: goto -28 -> 287
    //   318: aload_2
    //   319: ifnull +14 -> 333
    //   322: aload_2
    //   323: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   326: ifeq +7 -> 333
    //   329: aload_2
    //   330: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   333: aload 8
    //   335: ifnull +8 -> 343
    //   338: aload 8
    //   340: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   343: iconst_1
    //   344: anewarray 4	java/lang/Object
    //   347: astore 16
    //   349: aload 16
    //   351: iconst_0
    //   352: iload 7
    //   354: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   357: aastore
    //   358: ldc 141
    //   360: aload 16
    //   362: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   365: iload 7
    //   367: ireturn
    //   368: astore 10
    //   370: iconst_0
    //   371: istore 7
    //   373: aconst_null
    //   374: astore 8
    //   376: ldc 179
    //   378: iconst_0
    //   379: anewarray 4	java/lang/Object
    //   382: invokestatic 134	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   385: iconst_1
    //   386: anewarray 4	java/lang/Object
    //   389: astore 11
    //   391: aload 11
    //   393: iconst_0
    //   394: aload 10
    //   396: invokevirtual 108	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   399: aastore
    //   400: ldc 181
    //   402: aload 11
    //   404: invokestatic 139	com/tencent/beacon/d/a:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   407: aload 10
    //   409: invokevirtual 184	java/lang/Throwable:printStackTrace	()V
    //   412: aload_2
    //   413: ifnull +14 -> 427
    //   416: aload_2
    //   417: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   420: ifeq +7 -> 427
    //   423: aload_2
    //   424: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   427: aload 8
    //   429: ifnull +8 -> 437
    //   432: aload 8
    //   434: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   437: iconst_1
    //   438: anewarray 4	java/lang/Object
    //   441: astore 12
    //   443: aload 12
    //   445: iconst_0
    //   446: iload 7
    //   448: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   451: aastore
    //   452: ldc 141
    //   454: aload 12
    //   456: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   459: iload 7
    //   461: ireturn
    //   462: astore 6
    //   464: iconst_0
    //   465: istore 7
    //   467: aconst_null
    //   468: astore 8
    //   470: aload_2
    //   471: ifnull +14 -> 485
    //   474: aload_2
    //   475: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   478: ifeq +7 -> 485
    //   481: aload_2
    //   482: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   485: aload 8
    //   487: ifnull +8 -> 495
    //   490: aload 8
    //   492: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   495: iconst_1
    //   496: anewarray 4	java/lang/Object
    //   499: astore 9
    //   501: aload 9
    //   503: iconst_0
    //   504: iload 7
    //   506: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   509: aastore
    //   510: ldc 141
    //   512: aload 9
    //   514: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   517: aload 6
    //   519: athrow
    //   520: astore 6
    //   522: iconst_0
    //   523: istore 7
    //   525: goto -55 -> 470
    //   528: astore 6
    //   530: goto -60 -> 470
    //   533: astore 10
    //   535: iconst_0
    //   536: istore 7
    //   538: goto -162 -> 376
    //   541: astore 10
    //   543: goto -167 -> 376
    //
    // Exception table:
    //   from	to	target	type
    //   95	101	368	java/lang/Throwable
    //   95	101	462	finally
    //   105	111	520	finally
    //   115	133	520	finally
    //   181	189	520	finally
    //   192	221	528	finally
    //   226	245	528	finally
    //   262	277	528	finally
    //   287	303	528	finally
    //   376	412	528	finally
    //   105	111	533	java/lang/Throwable
    //   115	133	533	java/lang/Throwable
    //   181	189	533	java/lang/Throwable
    //   192	221	541	java/lang/Throwable
    //   226	245	541	java/lang/Throwable
    //   262	277	541	java/lang/Throwable
    //   287	303	541	java/lang/Throwable
  }

  public static int a(Context paramContext, com.tencent.beacon.a.a.d[] paramArrayOfd)
  {
    int i;
    if ((paramContext == null) || (paramArrayOfd == null) || (paramArrayOfd.length <= 0))
      i = -1;
    ArrayList localArrayList;
    int m;
    do
    {
      return i;
      localArrayList = new ArrayList(paramArrayOfd.length);
      int j = paramArrayOfd.length;
      for (int k = 0; k < j; k++)
      {
        com.tencent.beacon.a.a.d locald = paramArrayOfd[k];
        byte[] arrayOfByte = com.tencent.beacon.a.e.a(locald);
        if (arrayOfByte == null)
          continue;
        com.tencent.beacon.a.a.a locala = new com.tencent.beacon.a.a.a(6, 0, 0L, arrayOfByte);
        locala.a(locald.a());
        localArrayList.add(locala);
      }
      m = localArrayList.size();
      i = 0;
    }
    while (m <= 0);
    if (com.tencent.beacon.a.a.a.b(paramContext, localArrayList))
      return localArrayList.size();
    return -1;
  }

  public static int a(Context paramContext, Long[] paramArrayOfLong)
  {
    com.tencent.beacon.d.a.a(" RecordDAO.deleteRecordList() start", new Object[0]);
    if (paramContext == null)
    {
      com.tencent.beacon.d.a.d(" deleteRecordList() have null args!", new Object[0]);
      return -1;
    }
    com.tencent.beacon.d.a.a(" RecordDAO.deleteRecordList() end", new Object[0]);
    return com.tencent.beacon.a.a.a.a(paramContext, paramArrayOfLong);
  }

  public static long a(String paramString)
  {
    if (paramString == null)
      return 0L;
    try
    {
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0L;
  }

  // ERROR //
  public static com.tencent.beacon.a.b.h a(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: ifnonnull +14 -> 17
    //   6: ldc 239
    //   8: iconst_0
    //   9: anewarray 4	java/lang/Object
    //   12: invokestatic 121	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   15: aconst_null
    //   16: areturn
    //   17: aload_0
    //   18: invokestatic 45	com/tencent/beacon/a/a/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/a/c;
    //   21: astore 11
    //   23: aload 11
    //   25: astore 8
    //   27: aload 8
    //   29: invokevirtual 49	com/tencent/beacon/a/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: astore 12
    //   34: aload 12
    //   36: ifnonnull +42 -> 78
    //   39: ldc 241
    //   41: iconst_0
    //   42: anewarray 4	java/lang/Object
    //   45: invokestatic 121	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   48: aload 12
    //   50: ifnull +16 -> 66
    //   53: aload 12
    //   55: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   58: ifeq +8 -> 66
    //   61: aload 12
    //   63: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   66: aload 8
    //   68: ifnull +8 -> 76
    //   71: aload 8
    //   73: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   76: aconst_null
    //   77: areturn
    //   78: getstatic 247	java/util/Locale:US	Ljava/util/Locale;
    //   81: astore 15
    //   83: iconst_2
    //   84: anewarray 4	java/lang/Object
    //   87: astore 16
    //   89: aload 16
    //   91: iconst_0
    //   92: ldc 249
    //   94: aastore
    //   95: aload 16
    //   97: iconst_1
    //   98: bipush 101
    //   100: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   103: aastore
    //   104: aload 12
    //   106: ldc 251
    //   108: aconst_null
    //   109: aload 15
    //   111: ldc 253
    //   113: aload 16
    //   115: invokestatic 257	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   118: aconst_null
    //   119: aconst_null
    //   120: aconst_null
    //   121: aconst_null
    //   122: invokevirtual 61	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   125: astore 17
    //   127: aload 17
    //   129: astore 9
    //   131: aload 9
    //   133: ifnull +310 -> 443
    //   136: aload 9
    //   138: invokeinterface 67 1 0
    //   143: ifeq +300 -> 443
    //   146: aload 9
    //   148: ifnull +505 -> 653
    //   151: aload 9
    //   153: invokeinterface 260 1 0
    //   158: ifne +495 -> 653
    //   161: aload 9
    //   163: invokeinterface 263 1 0
    //   168: ifeq +90 -> 258
    //   171: goto +482 -> 653
    //   174: aload 21
    //   176: ifnull +267 -> 443
    //   179: iconst_1
    //   180: anewarray 4	java/lang/Object
    //   183: astore 22
    //   185: aload 22
    //   187: iconst_0
    //   188: aload 21
    //   190: invokevirtual 267	com/tencent/beacon/a/b/h:b	()I
    //   193: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   196: aastore
    //   197: ldc_w 269
    //   200: aload 22
    //   202: invokestatic 139	com/tencent/beacon/d/a:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   205: aload 9
    //   207: ifnull +20 -> 227
    //   210: aload 9
    //   212: invokeinterface 95 1 0
    //   217: ifne +10 -> 227
    //   220: aload 9
    //   222: invokeinterface 98 1 0
    //   227: aload 12
    //   229: ifnull +16 -> 245
    //   232: aload 12
    //   234: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   237: ifeq +8 -> 245
    //   240: aload 12
    //   242: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   245: aload 8
    //   247: ifnull +8 -> 255
    //   250: aload 8
    //   252: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   255: aload 21
    //   257: areturn
    //   258: ldc_w 271
    //   261: iconst_0
    //   262: anewarray 4	java/lang/Object
    //   265: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   268: new 265	com/tencent/beacon/a/b/h
    //   271: dup
    //   272: invokespecial 272	com/tencent/beacon/a/b/h:<init>	()V
    //   275: astore 21
    //   277: aload 21
    //   279: aload 9
    //   281: aload 9
    //   283: ldc 161
    //   285: invokeinterface 73 2 0
    //   290: invokeinterface 276 2 0
    //   295: invokevirtual 277	com/tencent/beacon/a/b/h:a	(J)V
    //   298: aload 21
    //   300: aload 9
    //   302: aload 9
    //   304: ldc 249
    //   306: invokeinterface 73 2 0
    //   311: invokeinterface 77 2 0
    //   316: invokevirtual 279	com/tencent/beacon/a/b/h:a	(I)V
    //   319: aload 21
    //   321: aload 9
    //   323: aload 9
    //   325: ldc_w 281
    //   328: invokeinterface 73 2 0
    //   333: invokeinterface 285 2 0
    //   338: invokevirtual 288	com/tencent/beacon/a/b/h:a	([B)V
    //   341: goto -167 -> 174
    //   344: astore 19
    //   346: aload 8
    //   348: astore 6
    //   350: aload 9
    //   352: astore 20
    //   354: aload 12
    //   356: astore 5
    //   358: aload 19
    //   360: astore_3
    //   361: aload 20
    //   363: astore 4
    //   365: aload_3
    //   366: invokevirtual 184	java/lang/Throwable:printStackTrace	()V
    //   369: iconst_1
    //   370: anewarray 4	java/lang/Object
    //   373: astore 10
    //   375: aload 10
    //   377: iconst_0
    //   378: aload_3
    //   379: invokevirtual 289	java/lang/Throwable:toString	()Ljava/lang/String;
    //   382: aastore
    //   383: ldc_w 291
    //   386: aload 10
    //   388: invokestatic 134	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   391: aload 4
    //   393: ifnull +20 -> 413
    //   396: aload 4
    //   398: invokeinterface 95 1 0
    //   403: ifne +10 -> 413
    //   406: aload 4
    //   408: invokeinterface 98 1 0
    //   413: aload 5
    //   415: ifnull +16 -> 431
    //   418: aload 5
    //   420: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   423: ifeq +8 -> 431
    //   426: aload 5
    //   428: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   431: aload 6
    //   433: ifnull +8 -> 441
    //   436: aload 6
    //   438: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   441: aconst_null
    //   442: areturn
    //   443: aload 9
    //   445: ifnull +20 -> 465
    //   448: aload 9
    //   450: invokeinterface 95 1 0
    //   455: ifne +10 -> 465
    //   458: aload 9
    //   460: invokeinterface 98 1 0
    //   465: aload 12
    //   467: ifnull +16 -> 483
    //   470: aload 12
    //   472: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   475: ifeq +8 -> 483
    //   478: aload 12
    //   480: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   483: aload 8
    //   485: ifnull -44 -> 441
    //   488: aload 8
    //   490: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   493: goto -52 -> 441
    //   496: astore 7
    //   498: aconst_null
    //   499: astore 9
    //   501: aconst_null
    //   502: astore 8
    //   504: aload 9
    //   506: ifnull +20 -> 526
    //   509: aload 9
    //   511: invokeinterface 95 1 0
    //   516: ifne +10 -> 526
    //   519: aload 9
    //   521: invokeinterface 98 1 0
    //   526: aload_2
    //   527: ifnull +14 -> 541
    //   530: aload_2
    //   531: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   534: ifeq +7 -> 541
    //   537: aload_2
    //   538: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   541: aload 8
    //   543: ifnull +8 -> 551
    //   546: aload 8
    //   548: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   551: aload 7
    //   553: athrow
    //   554: astore 7
    //   556: aconst_null
    //   557: astore 9
    //   559: aconst_null
    //   560: astore_2
    //   561: goto -57 -> 504
    //   564: astore 14
    //   566: aload 12
    //   568: astore_2
    //   569: aload 14
    //   571: astore 7
    //   573: aconst_null
    //   574: astore 9
    //   576: goto -72 -> 504
    //   579: astore 18
    //   581: aload 12
    //   583: astore_2
    //   584: aload 18
    //   586: astore 7
    //   588: goto -84 -> 504
    //   591: astore 7
    //   593: aload 5
    //   595: astore_2
    //   596: aload 6
    //   598: astore 8
    //   600: aload 4
    //   602: astore 9
    //   604: goto -100 -> 504
    //   607: astore_3
    //   608: aconst_null
    //   609: astore 4
    //   611: aconst_null
    //   612: astore 5
    //   614: aconst_null
    //   615: astore 6
    //   617: goto -252 -> 365
    //   620: astore_3
    //   621: aload 8
    //   623: astore 6
    //   625: aconst_null
    //   626: astore 4
    //   628: aconst_null
    //   629: astore 5
    //   631: goto -266 -> 365
    //   634: astore 13
    //   636: aload 12
    //   638: astore 5
    //   640: aload 8
    //   642: astore 6
    //   644: aload 13
    //   646: astore_3
    //   647: aconst_null
    //   648: astore 4
    //   650: goto -285 -> 365
    //   653: aconst_null
    //   654: astore 21
    //   656: goto -482 -> 174
    //
    // Exception table:
    //   from	to	target	type
    //   136	146	344	java/lang/Throwable
    //   151	171	344	java/lang/Throwable
    //   179	205	344	java/lang/Throwable
    //   258	341	344	java/lang/Throwable
    //   17	23	496	finally
    //   27	34	554	finally
    //   39	48	564	finally
    //   78	127	564	finally
    //   136	146	579	finally
    //   151	171	579	finally
    //   179	205	579	finally
    //   258	341	579	finally
    //   365	391	591	finally
    //   17	23	607	java/lang/Throwable
    //   27	34	620	java/lang/Throwable
    //   39	48	634	java/lang/Throwable
    //   78	127	634	java/lang/Throwable
  }

  protected static d a(Cursor paramCursor)
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
      locald.d(paramCursor.getString(paramCursor.getColumnIndex("_p")));
      return locald;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      com.tencent.beacon.d.a.d("rqdp{  Error:getFileBean fail!}", new Object[0]);
      com.tencent.beacon.d.a.g("rqdp{  Error: getFileBean fail!}", new Object[0]);
    }
    return null;
  }

  public static String a(Map<String, String> paramMap)
  {
    com.tencent.beacon.d.a.b("map 2 str", new Object[0]);
    if (paramMap == null)
      return "";
    Set localSet = paramMap.keySet();
    if (localSet == null)
      return "";
    if (localSet.size() > 50)
      com.tencent.beacon.d.a.c("The Map<String, String> params size is more than 50, effective size is <= 50!", new Object[0]);
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = localSet.iterator();
    String str2;
    String str5;
    while (true)
    {
      if (!localIterator.hasNext())
        break label364;
      str2 = (String)localIterator.next();
      int i = str2.trim().length();
      if ((i > 0) && (e(str2)))
      {
        String str3 = str2.trim();
        if (i > 64)
          str3 = str3.substring(0, 64);
        localStringBuffer.append("&");
        localStringBuffer.append(str3.replace("|", "%7C").replace("&", "%26").replace("=", "%3D"));
        localStringBuffer.append("=");
        String str4 = (String)paramMap.get(str2);
        if (str4 == null)
          continue;
        str5 = str4.trim();
        if (!str5.contains(";"))
          break label339;
        if (str5.length() <= 10240)
          break;
        String str6 = str5.substring(0, 10240);
        str5 = str6.substring(0, str6.lastIndexOf(";"));
      }
    }
    while (true)
    {
      localStringBuffer.append(str5.replace('\n', ' ').replace('\r', ' ').replace("|", "%7C").replace("&", "%26").replace("=", "%3D"));
      break;
      com.tencent.beacon.d.a.c("The Map<String, String> params key is invalid!! key should be ASCII code in 32-126! key:" + str2, new Object[0]);
      break;
      label339: if (str5.length() <= 1024)
        continue;
      str5 = str5.substring(0, 1024);
    }
    label364: String str1 = localStringBuffer.substring(1);
    localStringBuffer.setLength(0);
    return str1;
  }

  // ERROR //
  public static List<d> a(Context paramContext, String paramString, int paramInt1, int paramInt2)
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
    //   18: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   21: aastore
    //   22: ldc_w 410
    //   25: aload 4
    //   27: invokestatic 121	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   30: aconst_null
    //   31: areturn
    //   32: iconst_3
    //   33: anewarray 4	java/lang/Object
    //   36: astore 5
    //   38: aload 5
    //   40: iconst_0
    //   41: ldc_w 412
    //   44: aastore
    //   45: aload 5
    //   47: iconst_1
    //   48: iconst_m1
    //   49: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   52: aastore
    //   53: aload 5
    //   55: iconst_2
    //   56: iload_3
    //   57: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   60: aastore
    //   61: ldc_w 414
    //   64: aload 5
    //   66: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   69: aconst_null
    //   70: astore 6
    //   72: aload_0
    //   73: invokestatic 45	com/tencent/beacon/a/a/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/a/c;
    //   76: astore 23
    //   78: aload 23
    //   80: astore 13
    //   82: aload 13
    //   84: invokevirtual 49	com/tencent/beacon/a/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   87: astore 25
    //   89: aload 25
    //   91: astore 15
    //   93: aload 15
    //   95: ifnonnull +96 -> 191
    //   98: ldc 132
    //   100: iconst_0
    //   101: anewarray 4	java/lang/Object
    //   104: invokestatic 134	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   107: ldc_w 416
    //   110: iconst_0
    //   111: anewarray 4	java/lang/Object
    //   114: invokestatic 139	com/tencent/beacon/d/a:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   117: aload 15
    //   119: ifnull +16 -> 135
    //   122: aload 15
    //   124: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   127: ifeq +8 -> 135
    //   130: aload 15
    //   132: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   135: aload 13
    //   137: ifnull +8 -> 145
    //   140: aload 13
    //   142: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   145: iconst_1
    //   146: anewarray 4	java/lang/Object
    //   149: astore 41
    //   151: aload 41
    //   153: iconst_0
    //   154: iconst_m1
    //   155: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   158: aastore
    //   159: ldc_w 418
    //   162: aload 41
    //   164: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   167: iconst_1
    //   168: anewarray 4	java/lang/Object
    //   171: astore 42
    //   173: aload 42
    //   175: iconst_0
    //   176: iconst_m1
    //   177: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   180: aastore
    //   181: ldc_w 420
    //   184: aload 42
    //   186: invokestatic 139	com/tencent/beacon/d/a:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   189: aconst_null
    //   190: areturn
    //   191: new 346	java/lang/StringBuffer
    //   194: dup
    //   195: invokespecial 347	java/lang/StringBuffer:<init>	()V
    //   198: astore 26
    //   200: aload 26
    //   202: invokevirtual 421	java/lang/StringBuffer:length	()I
    //   205: ifle +310 -> 515
    //   208: aload 26
    //   210: invokevirtual 422	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   213: astore 28
    //   215: iload_3
    //   216: ifle +305 -> 521
    //   219: new 79	java/lang/StringBuilder
    //   222: dup
    //   223: invokespecial 122	java/lang/StringBuilder:<init>	()V
    //   226: iload_3
    //   227: invokevirtual 88	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   230: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   233: astore 29
    //   235: aload 15
    //   237: ldc 51
    //   239: aconst_null
    //   240: aload 28
    //   242: aconst_null
    //   243: aconst_null
    //   244: aconst_null
    //   245: aconst_null
    //   246: aload 29
    //   248: invokevirtual 425	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   251: astore 30
    //   253: aload 30
    //   255: astore 9
    //   257: aload 9
    //   259: ifnull +676 -> 935
    //   262: new 187	java/util/ArrayList
    //   265: dup
    //   266: invokespecial 426	java/util/ArrayList:<init>	()V
    //   269: astore 35
    //   271: aload 9
    //   273: invokeinterface 67 1 0
    //   278: ifeq +249 -> 527
    //   281: aload 9
    //   283: invokestatic 428	com/tencent/beacon/applog/a:a	(Landroid/database/Cursor;)Lcom/tencent/beacon/applog/d;
    //   286: astore 37
    //   288: aload 37
    //   290: ifnull +237 -> 527
    //   293: iconst_4
    //   294: anewarray 4	java/lang/Object
    //   297: astore 38
    //   299: aload 38
    //   301: iconst_0
    //   302: aload 37
    //   304: invokevirtual 170	com/tencent/beacon/applog/d:b	()Ljava/lang/String;
    //   307: aastore
    //   308: aload 38
    //   310: iconst_1
    //   311: aload 37
    //   313: invokevirtual 430	com/tencent/beacon/applog/d:e	()Ljava/lang/String;
    //   316: aastore
    //   317: aload 38
    //   319: iconst_2
    //   320: aload 37
    //   322: invokevirtual 433	com/tencent/beacon/applog/d:f	()I
    //   325: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   328: aastore
    //   329: aload 38
    //   331: iconst_3
    //   332: aload 37
    //   334: invokevirtual 435	com/tencent/beacon/applog/d:g	()Ljava/lang/String;
    //   337: aastore
    //   338: ldc_w 437
    //   341: aload 38
    //   343: invokestatic 139	com/tencent/beacon/d/a:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   346: aload 35
    //   348: aload 37
    //   350: invokeinterface 438 2 0
    //   355: pop
    //   356: goto -85 -> 271
    //   359: astore 36
    //   361: aload 13
    //   363: astore 11
    //   365: aload 15
    //   367: astore 10
    //   369: aload 36
    //   371: astore 7
    //   373: aload 35
    //   375: astore 8
    //   377: ldc_w 440
    //   380: iconst_0
    //   381: anewarray 4	java/lang/Object
    //   384: invokestatic 134	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   387: ldc_w 416
    //   390: iconst_0
    //   391: anewarray 4	java/lang/Object
    //   394: invokestatic 139	com/tencent/beacon/d/a:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   397: aload 7
    //   399: invokevirtual 184	java/lang/Throwable:printStackTrace	()V
    //   402: aload 9
    //   404: ifnull +20 -> 424
    //   407: aload 9
    //   409: invokeinterface 95 1 0
    //   414: ifne +10 -> 424
    //   417: aload 9
    //   419: invokeinterface 98 1 0
    //   424: aload 10
    //   426: ifnull +16 -> 442
    //   429: aload 10
    //   431: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   434: ifeq +8 -> 442
    //   437: aload 10
    //   439: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   442: aload 11
    //   444: ifnull +8 -> 452
    //   447: aload 11
    //   449: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   452: aload 8
    //   454: ifnull +196 -> 650
    //   457: aload 8
    //   459: invokeinterface 115 1 0
    //   464: istore 19
    //   466: iconst_1
    //   467: anewarray 4	java/lang/Object
    //   470: astore 20
    //   472: aload 20
    //   474: iconst_0
    //   475: iload 19
    //   477: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   480: aastore
    //   481: ldc_w 418
    //   484: aload 20
    //   486: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   489: iconst_1
    //   490: anewarray 4	java/lang/Object
    //   493: astore 21
    //   495: aload 21
    //   497: iconst_0
    //   498: iload 19
    //   500: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   503: aastore
    //   504: ldc_w 420
    //   507: aload 21
    //   509: invokestatic 139	com/tencent/beacon/d/a:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   512: aload 8
    //   514: areturn
    //   515: aconst_null
    //   516: astore 28
    //   518: goto -303 -> 215
    //   521: aconst_null
    //   522: astore 29
    //   524: goto -289 -> 235
    //   527: aload 35
    //   529: astore 31
    //   531: aload 9
    //   533: ifnull +20 -> 553
    //   536: aload 9
    //   538: invokeinterface 95 1 0
    //   543: ifne +10 -> 553
    //   546: aload 9
    //   548: invokeinterface 98 1 0
    //   553: aload 15
    //   555: ifnull +16 -> 571
    //   558: aload 15
    //   560: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   563: ifeq +8 -> 571
    //   566: aload 15
    //   568: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   571: aload 13
    //   573: ifnull +8 -> 581
    //   576: aload 13
    //   578: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   581: aload 31
    //   583: ifnull +61 -> 644
    //   586: aload 31
    //   588: invokeinterface 115 1 0
    //   593: istore 32
    //   595: iconst_1
    //   596: anewarray 4	java/lang/Object
    //   599: astore 33
    //   601: aload 33
    //   603: iconst_0
    //   604: iload 32
    //   606: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   609: aastore
    //   610: ldc_w 418
    //   613: aload 33
    //   615: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   618: iconst_1
    //   619: anewarray 4	java/lang/Object
    //   622: astore 34
    //   624: aload 34
    //   626: iconst_0
    //   627: iload 32
    //   629: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   632: aastore
    //   633: ldc_w 420
    //   636: aload 34
    //   638: invokestatic 139	com/tencent/beacon/d/a:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   641: aload 31
    //   643: areturn
    //   644: iconst_m1
    //   645: istore 32
    //   647: goto -52 -> 595
    //   650: iconst_m1
    //   651: istore 19
    //   653: goto -187 -> 466
    //   656: astore 22
    //   658: aconst_null
    //   659: astore 13
    //   661: aconst_null
    //   662: astore 9
    //   664: aload 22
    //   666: astore 14
    //   668: aconst_null
    //   669: astore 15
    //   671: aload 9
    //   673: ifnull +20 -> 693
    //   676: aload 9
    //   678: invokeinterface 95 1 0
    //   683: ifne +10 -> 693
    //   686: aload 9
    //   688: invokeinterface 98 1 0
    //   693: aload 15
    //   695: ifnull +16 -> 711
    //   698: aload 15
    //   700: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   703: ifeq +8 -> 711
    //   706: aload 15
    //   708: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   711: aload 13
    //   713: ifnull +8 -> 721
    //   716: aload 13
    //   718: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   721: aload 6
    //   723: ifnull +61 -> 784
    //   726: aload 6
    //   728: invokeinterface 115 1 0
    //   733: istore 16
    //   735: iconst_1
    //   736: anewarray 4	java/lang/Object
    //   739: astore 17
    //   741: aload 17
    //   743: iconst_0
    //   744: iload 16
    //   746: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   749: aastore
    //   750: ldc_w 418
    //   753: aload 17
    //   755: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   758: iconst_1
    //   759: anewarray 4	java/lang/Object
    //   762: astore 18
    //   764: aload 18
    //   766: iconst_0
    //   767: iload 16
    //   769: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   772: aastore
    //   773: ldc_w 420
    //   776: aload 18
    //   778: invokestatic 139	com/tencent/beacon/d/a:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   781: aload 14
    //   783: athrow
    //   784: iconst_m1
    //   785: istore 16
    //   787: goto -52 -> 735
    //   790: astore 24
    //   792: aload 24
    //   794: astore 14
    //   796: aconst_null
    //   797: astore 15
    //   799: aconst_null
    //   800: astore 9
    //   802: aconst_null
    //   803: astore 6
    //   805: goto -134 -> 671
    //   808: astore 14
    //   810: aconst_null
    //   811: astore 9
    //   813: aconst_null
    //   814: astore 6
    //   816: goto -145 -> 671
    //   819: astore 14
    //   821: aconst_null
    //   822: astore 6
    //   824: goto -153 -> 671
    //   827: astore 14
    //   829: aload 35
    //   831: astore 6
    //   833: goto -162 -> 671
    //   836: astore 12
    //   838: aload 8
    //   840: astore 6
    //   842: aload 11
    //   844: astore 13
    //   846: aload 12
    //   848: astore 14
    //   850: aload 10
    //   852: astore 15
    //   854: goto -183 -> 671
    //   857: astore 7
    //   859: aconst_null
    //   860: astore 8
    //   862: aconst_null
    //   863: astore 9
    //   865: aconst_null
    //   866: astore 10
    //   868: aconst_null
    //   869: astore 11
    //   871: goto -494 -> 377
    //   874: astore 7
    //   876: aload 13
    //   878: astore 11
    //   880: aconst_null
    //   881: astore 8
    //   883: aconst_null
    //   884: astore 9
    //   886: aconst_null
    //   887: astore 10
    //   889: goto -512 -> 377
    //   892: astore 27
    //   894: aload 15
    //   896: astore 10
    //   898: aload 13
    //   900: astore 11
    //   902: aload 27
    //   904: astore 7
    //   906: aconst_null
    //   907: astore 8
    //   909: aconst_null
    //   910: astore 9
    //   912: goto -535 -> 377
    //   915: astore 40
    //   917: aload 15
    //   919: astore 10
    //   921: aload 13
    //   923: astore 11
    //   925: aload 40
    //   927: astore 7
    //   929: aconst_null
    //   930: astore 8
    //   932: goto -555 -> 377
    //   935: aconst_null
    //   936: astore 31
    //   938: goto -407 -> 531
    //
    // Exception table:
    //   from	to	target	type
    //   271	288	359	java/lang/Throwable
    //   293	356	359	java/lang/Throwable
    //   72	78	656	finally
    //   82	89	790	finally
    //   98	117	808	finally
    //   191	215	808	finally
    //   219	235	808	finally
    //   235	253	808	finally
    //   262	271	819	finally
    //   271	288	827	finally
    //   293	356	827	finally
    //   377	402	836	finally
    //   72	78	857	java/lang/Throwable
    //   82	89	874	java/lang/Throwable
    //   98	117	892	java/lang/Throwable
    //   191	215	892	java/lang/Throwable
    //   219	235	892	java/lang/Throwable
    //   235	253	892	java/lang/Throwable
    //   262	271	915	java/lang/Throwable
  }

  public static List<h> a(Context paramContext, String[] paramArrayOfString, long paramLong)
  {
    com.tencent.beacon.d.a.a(" RecordDAO.queryRecentRecord() start", new Object[0]);
    if (paramContext == null)
    {
      com.tencent.beacon.d.a.d(" queryRecentRecord() have null args!", new Object[0]);
      return null;
    }
    List localList = com.tencent.beacon.a.a.a.a(paramContext, new int[] { 1, 2, 3, 4 }, 1, 2, paramLong, -1, -1, -1, -1, -1, -1L, -1L);
    if ((localList == null) || (localList.size() <= 0))
      return null;
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      com.tencent.beacon.a.a.a locala = (com.tencent.beacon.a.a.a)localIterator.next();
      try
      {
        Object localObject = com.tencent.beacon.a.e.a(locala.b());
        if ((localObject == null) || (!h.class.isInstance(localObject)))
          continue;
        h localh = (h)h.class.cast(localObject);
        localh.a(locala.a());
        localArrayList.add(localh);
        localIterator.remove();
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        com.tencent.beacon.d.a.d(" query have error!", new Object[0]);
      }
    }
    if (localList.size() > 0)
    {
      com.tencent.beacon.d.a.a(" there are error datas ,should be remove " + localList.size(), new Object[0]);
      Long[] arrayOfLong = new Long[localList.size()];
      for (int i = 0; i < localList.size(); i++)
        arrayOfLong[i] = Long.valueOf(((com.tencent.beacon.a.a.a)localList.get(i)).a());
      com.tencent.beacon.a.a.a.a(paramContext, arrayOfLong);
    }
    com.tencent.beacon.d.a.a(" RecordDAO.queryRecentRecord() end", new Object[0]);
    return localArrayList;
  }

  public static void a(Map<String, String> paramMap, String paramString, long paramLong)
  {
    if ((paramMap == null) || (paramString == null))
    {
      com.tencent.beacon.d.a.c(" err addVMap, pls check!}", new Object[0]);
      return;
    }
    paramMap.put(paramString, Long.toString(paramLong + a((String)paramMap.get(paramString))));
  }

  // ERROR //
  public static boolean a(Context paramContext, com.tencent.beacon.a.b.h paramh)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +7 -> 8
    //   4: aload_1
    //   5: ifnonnull +15 -> 20
    //   8: ldc_w 498
    //   11: iconst_0
    //   12: anewarray 4	java/lang/Object
    //   15: invokestatic 121	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   18: iconst_0
    //   19: ireturn
    //   20: aload_0
    //   21: invokestatic 45	com/tencent/beacon/a/a/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/a/c;
    //   24: invokevirtual 49	com/tencent/beacon/a/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   27: astore 6
    //   29: aload 6
    //   31: astore_3
    //   32: aload_3
    //   33: ifnonnull +30 -> 63
    //   36: ldc_w 500
    //   39: iconst_0
    //   40: anewarray 4	java/lang/Object
    //   43: invokestatic 134	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   46: aload_3
    //   47: ifnull -29 -> 18
    //   50: aload_3
    //   51: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   54: ifeq -36 -> 18
    //   57: aload_3
    //   58: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   61: iconst_0
    //   62: ireturn
    //   63: aconst_null
    //   64: astore 7
    //   66: aload_1
    //   67: ifnonnull +54 -> 121
    //   70: aload 7
    //   72: ifnull +202 -> 274
    //   75: aload_3
    //   76: ldc 251
    //   78: ldc 161
    //   80: aload 7
    //   82: invokevirtual 502	android/database/sqlite/SQLiteDatabase:replace	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   85: lstore 8
    //   87: lload 8
    //   89: lconst_0
    //   90: lcmp
    //   91: ifge +135 -> 226
    //   94: ldc_w 504
    //   97: iconst_0
    //   98: anewarray 4	java/lang/Object
    //   101: invokestatic 121	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   104: aload_3
    //   105: ifnull -87 -> 18
    //   108: aload_3
    //   109: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   112: ifeq -94 -> 18
    //   115: aload_3
    //   116: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   119: iconst_0
    //   120: ireturn
    //   121: new 506	android/content/ContentValues
    //   124: dup
    //   125: invokespecial 507	android/content/ContentValues:<init>	()V
    //   128: astore 7
    //   130: aload_1
    //   131: invokevirtual 508	com/tencent/beacon/a/b/h:a	()J
    //   134: lconst_0
    //   135: lcmp
    //   136: iflt +17 -> 153
    //   139: aload 7
    //   141: ldc 161
    //   143: aload_1
    //   144: invokevirtual 508	com/tencent/beacon/a/b/h:a	()J
    //   147: invokestatic 481	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   150: invokevirtual 511	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   153: aload 7
    //   155: ldc 249
    //   157: aload_1
    //   158: invokevirtual 267	com/tencent/beacon/a/b/h:b	()I
    //   161: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   164: invokevirtual 514	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   167: aload 7
    //   169: ldc_w 281
    //   172: aload_1
    //   173: invokevirtual 516	com/tencent/beacon/a/b/h:c	()[B
    //   176: invokevirtual 519	android/content/ContentValues:put	(Ljava/lang/String;[B)V
    //   179: goto -109 -> 70
    //   182: astore_2
    //   183: iconst_1
    //   184: anewarray 4	java/lang/Object
    //   187: astore 5
    //   189: aload 5
    //   191: iconst_0
    //   192: aload_2
    //   193: invokevirtual 289	java/lang/Throwable:toString	()Ljava/lang/String;
    //   196: aastore
    //   197: ldc_w 521
    //   200: aload 5
    //   202: invokestatic 134	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   205: aload_2
    //   206: invokevirtual 184	java/lang/Throwable:printStackTrace	()V
    //   209: aload_3
    //   210: ifnull -192 -> 18
    //   213: aload_3
    //   214: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   217: ifeq -199 -> 18
    //   220: aload_3
    //   221: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   224: iconst_0
    //   225: ireturn
    //   226: aload_1
    //   227: lload 8
    //   229: invokevirtual 277	com/tencent/beacon/a/b/h:a	(J)V
    //   232: iconst_1
    //   233: anewarray 4	java/lang/Object
    //   236: astore 10
    //   238: aload 10
    //   240: iconst_0
    //   241: aload_1
    //   242: invokevirtual 267	com/tencent/beacon/a/b/h:b	()I
    //   245: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   248: aastore
    //   249: ldc_w 523
    //   252: aload 10
    //   254: invokestatic 525	com/tencent/beacon/d/a:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   257: aload_3
    //   258: ifnull +14 -> 272
    //   261: aload_3
    //   262: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   265: ifeq +7 -> 272
    //   268: aload_3
    //   269: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   272: iconst_1
    //   273: ireturn
    //   274: aload_3
    //   275: ifnull -257 -> 18
    //   278: aload_3
    //   279: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   282: ifeq -264 -> 18
    //   285: aload_3
    //   286: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   289: iconst_0
    //   290: ireturn
    //   291: astore 4
    //   293: aconst_null
    //   294: astore_3
    //   295: aload_3
    //   296: ifnull +14 -> 310
    //   299: aload_3
    //   300: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   303: ifeq +7 -> 310
    //   306: aload_3
    //   307: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   310: aload 4
    //   312: athrow
    //   313: astore 4
    //   315: goto -20 -> 295
    //   318: astore_2
    //   319: aconst_null
    //   320: astore_3
    //   321: goto -138 -> 183
    //
    // Exception table:
    //   from	to	target	type
    //   36	46	182	java/lang/Throwable
    //   75	87	182	java/lang/Throwable
    //   94	104	182	java/lang/Throwable
    //   121	153	182	java/lang/Throwable
    //   153	179	182	java/lang/Throwable
    //   226	257	182	java/lang/Throwable
    //   20	29	291	finally
    //   36	46	313	finally
    //   75	87	313	finally
    //   94	104	313	finally
    //   121	153	313	finally
    //   153	179	313	finally
    //   183	209	313	finally
    //   226	257	313	finally
    //   20	29	318	java/lang/Throwable
  }

  public static boolean a(Context paramContext, h paramh)
  {
    int i = 3;
    com.tencent.beacon.d.a.a(" RecordDAO.insert() start", new Object[0]);
    if ((paramContext == null) || (paramh == null) || (paramh.b() == null))
    {
      com.tencent.beacon.d.a.d(" insert() have null args!", new Object[0]);
      return false;
    }
    int j;
    if (paramh.b().equals("UA"))
      j = 1;
    try
    {
      com.tencent.beacon.a.a.a locala = new com.tencent.beacon.a.a.a(j, i, paramh.c(), com.tencent.beacon.a.e.a(paramh));
      if ((paramContext == null) || (locala == null))
        com.tencent.beacon.d.a.a("AnalyticsDAO.insert() have null args", new Object[0]);
      boolean bool;
      for (int k = 0; ; k = bool)
      {
        if (k != 0)
          paramh.a(locala.a());
        return k;
        if (paramh.b().equals("IP"))
        {
          j = 2;
          i = 0;
          break;
        }
        if (paramh.b().equals("DN"))
        {
          j = i;
          i = 0;
          break;
        }
        if (paramh.b().equals("HO"))
        {
          j = 4;
          i = 0;
          break;
        }
        return false;
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(locala);
        bool = com.tencent.beacon.a.a.a.a(paramContext, localArrayList);
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      com.tencent.beacon.d.a.d(" insert error!", new Object[0]);
      return false;
    }
    finally
    {
      com.tencent.beacon.d.a.a(" RecordDAO.insert() end", new Object[0]);
    }
    throw localObject;
  }

  // ERROR //
  public static boolean a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: invokestatic 562	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   6: ifne +10 -> 16
    //   9: aload_1
    //   10: invokestatic 562	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   13: ifeq +5 -> 18
    //   16: iconst_0
    //   17: ireturn
    //   18: new 564	java/io/File
    //   21: dup
    //   22: aload_1
    //   23: invokespecial 565	java/io/File:<init>	(Ljava/lang/String;)V
    //   26: astore_3
    //   27: aload_3
    //   28: invokevirtual 568	java/io/File:exists	()Z
    //   31: ifeq +8 -> 39
    //   34: aload_3
    //   35: invokevirtual 571	java/io/File:delete	()Z
    //   38: pop
    //   39: new 573	java/io/FileWriter
    //   42: dup
    //   43: aload_3
    //   44: invokespecial 576	java/io/FileWriter:<init>	(Ljava/io/File;)V
    //   47: astore 4
    //   49: new 578	java/io/BufferedWriter
    //   52: dup
    //   53: aload 4
    //   55: invokespecial 581	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   58: astore 5
    //   60: aload 5
    //   62: aload_0
    //   63: iconst_0
    //   64: aload_0
    //   65: invokevirtual 354	java/lang/String:length	()I
    //   68: invokevirtual 585	java/io/BufferedWriter:write	(Ljava/lang/String;II)V
    //   71: aload 5
    //   73: invokevirtual 586	java/io/BufferedWriter:close	()V
    //   76: aload 4
    //   78: invokevirtual 587	java/io/FileWriter:close	()V
    //   81: iconst_1
    //   82: ireturn
    //   83: astore 13
    //   85: aload 13
    //   87: invokevirtual 588	java/io/IOException:printStackTrace	()V
    //   90: iconst_1
    //   91: ireturn
    //   92: astore 12
    //   94: aload 12
    //   96: invokevirtual 588	java/io/IOException:printStackTrace	()V
    //   99: goto -23 -> 76
    //   102: astore 6
    //   104: aconst_null
    //   105: astore 5
    //   107: aload 6
    //   109: invokevirtual 588	java/io/IOException:printStackTrace	()V
    //   112: aload 5
    //   114: ifnull +8 -> 122
    //   117: aload 5
    //   119: invokevirtual 586	java/io/BufferedWriter:close	()V
    //   122: aload_2
    //   123: ifnull -107 -> 16
    //   126: aload_2
    //   127: invokevirtual 587	java/io/FileWriter:close	()V
    //   130: iconst_0
    //   131: ireturn
    //   132: astore 10
    //   134: aload 10
    //   136: invokevirtual 588	java/io/IOException:printStackTrace	()V
    //   139: iconst_0
    //   140: ireturn
    //   141: astore 11
    //   143: aload 11
    //   145: invokevirtual 588	java/io/IOException:printStackTrace	()V
    //   148: goto -26 -> 122
    //   151: astore 7
    //   153: aconst_null
    //   154: astore 4
    //   156: aload_2
    //   157: ifnull +7 -> 164
    //   160: aload_2
    //   161: invokevirtual 586	java/io/BufferedWriter:close	()V
    //   164: aload 4
    //   166: ifnull +8 -> 174
    //   169: aload 4
    //   171: invokevirtual 587	java/io/FileWriter:close	()V
    //   174: aload 7
    //   176: athrow
    //   177: astore 9
    //   179: aload 9
    //   181: invokevirtual 588	java/io/IOException:printStackTrace	()V
    //   184: goto -20 -> 164
    //   187: astore 8
    //   189: aload 8
    //   191: invokevirtual 588	java/io/IOException:printStackTrace	()V
    //   194: goto -20 -> 174
    //   197: astore 7
    //   199: aconst_null
    //   200: astore_2
    //   201: goto -45 -> 156
    //   204: astore 7
    //   206: aload 5
    //   208: astore_2
    //   209: goto -53 -> 156
    //   212: astore 7
    //   214: aload_2
    //   215: astore 4
    //   217: aload 5
    //   219: astore_2
    //   220: goto -64 -> 156
    //   223: astore 6
    //   225: aload 4
    //   227: astore_2
    //   228: aconst_null
    //   229: astore 5
    //   231: goto -124 -> 107
    //   234: astore 6
    //   236: aload 4
    //   238: astore_2
    //   239: goto -132 -> 107
    //
    // Exception table:
    //   from	to	target	type
    //   76	81	83	java/io/IOException
    //   71	76	92	java/io/IOException
    //   39	49	102	java/io/IOException
    //   126	130	132	java/io/IOException
    //   117	122	141	java/io/IOException
    //   39	49	151	finally
    //   160	164	177	java/io/IOException
    //   169	174	187	java/io/IOException
    //   49	60	197	finally
    //   60	71	204	finally
    //   107	112	212	finally
    //   49	60	223	java/io/IOException
    //   60	71	234	java/io/IOException
  }

  public static byte[] a(int paramInt, String paramString, byte[] paramArrayOfByte)
    throws Exception
  {
    if (paramInt == 1)
      if ((paramString != null) && (paramArrayOfByte != null));
    do
    {
      return null;
      Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      DESKeySpec localDESKeySpec = new DESKeySpec(paramString.getBytes("UTF-8"));
      localCipher.init(2, SecretKeyFactory.getInstance("DES").generateSecret(localDESKeySpec), new IvParameterSpec(paramString.getBytes("UTF-8")));
      return localCipher.doFinal(paramArrayOfByte);
    }
    while (paramInt != 3);
    return a(paramString, paramArrayOfByte);
  }

  public static byte[] a(int paramInt, byte[] paramArrayOfByte)
    throws Exception
  {
    if (paramInt == 1)
    {
      ByteArrayOutputStream localByteArrayOutputStream1 = new ByteArrayOutputStream();
      ZipOutputStream localZipOutputStream = new ZipOutputStream(localByteArrayOutputStream1);
      ZipEntry localZipEntry = new ZipEntry("zip");
      localZipEntry.setSize(paramArrayOfByte.length);
      localZipOutputStream.putNextEntry(localZipEntry);
      localZipOutputStream.write(paramArrayOfByte);
      localZipOutputStream.closeEntry();
      localZipOutputStream.close();
      byte[] arrayOfByte1 = localByteArrayOutputStream1.toByteArray();
      localByteArrayOutputStream1.close();
      return arrayOfByte1;
    }
    if (paramInt == 2)
    {
      ByteArrayOutputStream localByteArrayOutputStream2 = new ByteArrayOutputStream();
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream2);
      localGZIPOutputStream.write(paramArrayOfByte);
      localGZIPOutputStream.finish();
      localGZIPOutputStream.close();
      byte[] arrayOfByte2 = localByteArrayOutputStream2.toByteArray();
      localByteArrayOutputStream2.close();
      return arrayOfByte2;
    }
    return null;
  }

  private static byte[] a(String paramString, byte[] paramArrayOfByte)
    throws Exception
  {
    int i = 0;
    byte[] arrayOfByte;
    if ((paramString == null) || (paramArrayOfByte == null))
    {
      arrayOfByte = null;
      return arrayOfByte;
    }
    int j = paramString.length();
    label20: String str;
    StringBuffer localStringBuffer1;
    int k;
    if (j >= 16)
    {
      str = paramString.substring(0, 16);
      localStringBuffer1 = new StringBuffer();
      k = paramArrayOfByte.length;
    }
    for (int m = 0; ; m++)
    {
      if (m >= k)
      {
        SecretKeySpec localSecretKeySpec = new SecretKeySpec(str.getBytes(), "AES");
        Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        localCipher.init(2, localSecretKeySpec, new IvParameterSpec(str.getBytes()));
        arrayOfByte = localCipher.doFinal(paramArrayOfByte);
        StringBuffer localStringBuffer2 = new StringBuffer();
        int n = arrayOfByte.length;
        while (i < n)
        {
          localStringBuffer2.append(arrayOfByte[i] + " ");
          i++;
        }
        break;
        paramString = paramString + "0";
        j++;
        break label20;
      }
      localStringBuffer1.append(paramArrayOfByte[m] + " ");
    }
  }

  private static byte[] a(byte[] paramArrayOfByte)
    throws Exception
  {
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    GZIPInputStream localGZIPInputStream = new GZIPInputStream(localByteArrayInputStream);
    byte[] arrayOfByte1 = new byte[1024];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    while (true)
    {
      int i = localGZIPInputStream.read(arrayOfByte1, 0, arrayOfByte1.length);
      if (i == -1)
      {
        byte[] arrayOfByte2 = localByteArrayOutputStream.toByteArray();
        localByteArrayOutputStream.flush();
        localByteArrayOutputStream.close();
        localGZIPInputStream.close();
        localByteArrayInputStream.close();
        return arrayOfByte2;
      }
      localByteArrayOutputStream.write(arrayOfByte1, 0, i);
    }
  }

  // ERROR //
  public static int b(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: ifnonnull +15 -> 18
    //   6: ldc_w 714
    //   9: iconst_0
    //   10: anewarray 4	java/lang/Object
    //   13: invokestatic 121	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   16: iconst_0
    //   17: ireturn
    //   18: aload_0
    //   19: invokestatic 45	com/tencent/beacon/a/a/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/a/c;
    //   22: astore 7
    //   24: aload 7
    //   26: astore 4
    //   28: aload 4
    //   30: invokevirtual 49	com/tencent/beacon/a/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   33: astore_2
    //   34: aload_2
    //   35: ifnonnull +40 -> 75
    //   38: ldc_w 716
    //   41: iconst_0
    //   42: anewarray 4	java/lang/Object
    //   45: invokestatic 134	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   48: aload_2
    //   49: ifnull +14 -> 63
    //   52: aload_2
    //   53: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   56: ifeq +7 -> 63
    //   59: aload_2
    //   60: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   63: aload 4
    //   65: ifnull -49 -> 16
    //   68: aload 4
    //   70: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   73: iconst_0
    //   74: ireturn
    //   75: iconst_2
    //   76: anewarray 4	java/lang/Object
    //   79: astore 8
    //   81: aload 8
    //   83: iconst_0
    //   84: ldc 249
    //   86: aastore
    //   87: aload 8
    //   89: iconst_1
    //   90: bipush 101
    //   92: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   95: aastore
    //   96: aload_2
    //   97: ldc 251
    //   99: ldc_w 718
    //   102: aload 8
    //   104: invokestatic 721	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   107: aconst_null
    //   108: invokevirtual 724	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   111: istore 9
    //   113: iconst_2
    //   114: anewarray 4	java/lang/Object
    //   117: astore 10
    //   119: aload 10
    //   121: iconst_0
    //   122: bipush 101
    //   124: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   127: aastore
    //   128: aload 10
    //   130: iconst_1
    //   131: iload 9
    //   133: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   136: aastore
    //   137: ldc_w 726
    //   140: aload 10
    //   142: invokestatic 139	com/tencent/beacon/d/a:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   145: aload_2
    //   146: ifnull +14 -> 160
    //   149: aload_2
    //   150: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   153: ifeq +7 -> 160
    //   156: aload_2
    //   157: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   160: aload 4
    //   162: ifnull +8 -> 170
    //   165: aload 4
    //   167: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   170: iload 9
    //   172: ireturn
    //   173: astore 5
    //   175: aconst_null
    //   176: astore 4
    //   178: aload 5
    //   180: invokevirtual 184	java/lang/Throwable:printStackTrace	()V
    //   183: iconst_1
    //   184: anewarray 4	java/lang/Object
    //   187: astore 6
    //   189: aload 6
    //   191: iconst_0
    //   192: aload 5
    //   194: invokevirtual 289	java/lang/Throwable:toString	()Ljava/lang/String;
    //   197: aastore
    //   198: ldc_w 728
    //   201: aload 6
    //   203: invokestatic 134	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   206: aload_2
    //   207: ifnull +14 -> 221
    //   210: aload_2
    //   211: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   214: ifeq +7 -> 221
    //   217: aload_2
    //   218: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   221: aload 4
    //   223: ifnull -207 -> 16
    //   226: aload 4
    //   228: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   231: iconst_0
    //   232: ireturn
    //   233: astore_3
    //   234: aconst_null
    //   235: astore 4
    //   237: aload_2
    //   238: ifnull +14 -> 252
    //   241: aload_2
    //   242: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   245: ifeq +7 -> 252
    //   248: aload_2
    //   249: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   252: aload 4
    //   254: ifnull +8 -> 262
    //   257: aload 4
    //   259: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   262: aload_3
    //   263: athrow
    //   264: astore_3
    //   265: goto -28 -> 237
    //   268: astore 5
    //   270: goto -92 -> 178
    //
    // Exception table:
    //   from	to	target	type
    //   18	24	173	java/lang/Throwable
    //   18	24	233	finally
    //   28	34	264	finally
    //   38	48	264	finally
    //   75	145	264	finally
    //   178	206	264	finally
    //   28	34	268	java/lang/Throwable
    //   38	48	268	java/lang/Throwable
    //   75	145	268	java/lang/Throwable
  }

  // ERROR //
  public static int b(Context paramContext, List<d> paramList)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: ifnull +16 -> 19
    //   6: aload_1
    //   7: ifnull +12 -> 19
    //   10: aload_1
    //   11: invokeinterface 115 1 0
    //   16: ifne +54 -> 70
    //   19: iconst_1
    //   20: anewarray 4	java/lang/Object
    //   23: astore_3
    //   24: aload_1
    //   25: ifnonnull +21 -> 46
    //   28: ldc 117
    //   30: astore 4
    //   32: aload_3
    //   33: iconst_0
    //   34: aload 4
    //   36: aastore
    //   37: ldc_w 730
    //   40: aload_3
    //   41: invokestatic 121	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   44: iconst_m1
    //   45: ireturn
    //   46: new 79	java/lang/StringBuilder
    //   49: dup
    //   50: invokespecial 122	java/lang/StringBuilder:<init>	()V
    //   53: aload_1
    //   54: invokeinterface 115 1 0
    //   59: invokevirtual 88	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   62: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: astore 4
    //   67: goto -35 -> 32
    //   70: iconst_1
    //   71: anewarray 4	java/lang/Object
    //   74: astore 5
    //   76: aload 5
    //   78: iconst_0
    //   79: aload_1
    //   80: invokeinterface 115 1 0
    //   85: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   88: aastore
    //   89: ldc_w 732
    //   92: aload 5
    //   94: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   97: aload_0
    //   98: invokestatic 45	com/tencent/beacon/a/a/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/a/c;
    //   101: astore 16
    //   103: aload 16
    //   105: astore 9
    //   107: aload 9
    //   109: invokevirtual 49	com/tencent/beacon/a/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   112: astore_2
    //   113: aload_2
    //   114: ifnonnull +72 -> 186
    //   117: ldc_w 734
    //   120: iconst_0
    //   121: anewarray 4	java/lang/Object
    //   124: invokestatic 134	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   127: ldc_w 736
    //   130: iconst_0
    //   131: anewarray 4	java/lang/Object
    //   134: invokestatic 139	com/tencent/beacon/d/a:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   137: aload_2
    //   138: ifnull +14 -> 152
    //   141: aload_2
    //   142: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   145: ifeq +7 -> 152
    //   148: aload_2
    //   149: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   152: aload 9
    //   154: ifnull +8 -> 162
    //   157: aload 9
    //   159: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   162: iconst_1
    //   163: anewarray 4	java/lang/Object
    //   166: astore 18
    //   168: aload 18
    //   170: iconst_0
    //   171: iconst_0
    //   172: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   175: aastore
    //   176: ldc_w 738
    //   179: aload 18
    //   181: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   184: iconst_m1
    //   185: ireturn
    //   186: new 346	java/lang/StringBuffer
    //   189: dup
    //   190: invokespecial 347	java/lang/StringBuffer:<init>	()V
    //   193: astore 19
    //   195: aload_1
    //   196: invokeinterface 145 1 0
    //   201: astore 20
    //   203: aload 20
    //   205: invokeinterface 150 1 0
    //   210: ifeq +201 -> 411
    //   213: aload 20
    //   215: invokeinterface 154 1 0
    //   220: checkcast 156	com/tencent/beacon/applog/d
    //   223: astore 25
    //   225: aload 19
    //   227: ldc_w 740
    //   230: invokevirtual 367	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   233: pop
    //   234: aload 19
    //   236: ldc_w 295
    //   239: invokevirtual 367	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   242: pop
    //   243: aload 19
    //   245: ldc_w 742
    //   248: invokevirtual 367	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   251: pop
    //   252: aload 19
    //   254: aload 25
    //   256: invokevirtual 170	com/tencent/beacon/applog/d:b	()Ljava/lang/String;
    //   259: invokevirtual 367	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   262: pop
    //   263: aload 19
    //   265: ldc_w 744
    //   268: invokevirtual 367	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   271: pop
    //   272: aload 19
    //   274: ldc_w 307
    //   277: invokevirtual 367	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   280: pop
    //   281: aload 19
    //   283: ldc_w 746
    //   286: invokevirtual 367	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   289: pop
    //   290: aload 19
    //   292: aload 25
    //   294: invokevirtual 433	com/tencent/beacon/applog/d:f	()I
    //   297: invokevirtual 749	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   300: pop
    //   301: aload 19
    //   303: ldc_w 751
    //   306: invokevirtual 367	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   309: pop
    //   310: goto -107 -> 203
    //   313: astore 17
    //   315: aload 17
    //   317: astore 7
    //   319: iconst_0
    //   320: istore 8
    //   322: aload 7
    //   324: invokevirtual 184	java/lang/Throwable:printStackTrace	()V
    //   327: ldc_w 753
    //   330: iconst_0
    //   331: anewarray 4	java/lang/Object
    //   334: invokestatic 134	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   337: iconst_1
    //   338: anewarray 4	java/lang/Object
    //   341: astore 14
    //   343: aload 14
    //   345: iconst_0
    //   346: aload 7
    //   348: invokevirtual 108	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   351: aastore
    //   352: ldc_w 755
    //   355: aload 14
    //   357: invokestatic 139	com/tencent/beacon/d/a:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   360: aload_2
    //   361: ifnull +14 -> 375
    //   364: aload_2
    //   365: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   368: ifeq +7 -> 375
    //   371: aload_2
    //   372: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   375: aload 9
    //   377: ifnull +8 -> 385
    //   380: aload 9
    //   382: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   385: iconst_1
    //   386: anewarray 4	java/lang/Object
    //   389: astore 15
    //   391: aload 15
    //   393: iconst_0
    //   394: iload 8
    //   396: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   399: aastore
    //   400: ldc_w 738
    //   403: aload 15
    //   405: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   408: iload 8
    //   410: ireturn
    //   411: aload 19
    //   413: iconst_2
    //   414: invokevirtual 404	java/lang/StringBuffer:substring	(I)Ljava/lang/String;
    //   417: astore 21
    //   419: aload_2
    //   420: ldc 51
    //   422: aload 21
    //   424: aconst_null
    //   425: invokevirtual 724	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   428: istore 22
    //   430: iload 22
    //   432: istore 8
    //   434: iconst_2
    //   435: anewarray 4	java/lang/Object
    //   438: astore 23
    //   440: aload 23
    //   442: iconst_0
    //   443: iload 8
    //   445: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   448: aastore
    //   449: aload 23
    //   451: iconst_1
    //   452: aload 21
    //   454: aastore
    //   455: ldc_w 757
    //   458: aload 23
    //   460: invokestatic 139	com/tencent/beacon/d/a:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   463: aload_2
    //   464: ifnull +14 -> 478
    //   467: aload_2
    //   468: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   471: ifeq +7 -> 478
    //   474: aload_2
    //   475: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   478: aload 9
    //   480: ifnull +8 -> 488
    //   483: aload 9
    //   485: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   488: iconst_1
    //   489: anewarray 4	java/lang/Object
    //   492: astore 24
    //   494: aload 24
    //   496: iconst_0
    //   497: iload 8
    //   499: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   502: aastore
    //   503: ldc_w 738
    //   506: aload 24
    //   508: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   511: iload 8
    //   513: ireturn
    //   514: astore 12
    //   516: iconst_0
    //   517: istore 11
    //   519: aconst_null
    //   520: astore 9
    //   522: aload_2
    //   523: ifnull +14 -> 537
    //   526: aload_2
    //   527: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   530: ifeq +7 -> 537
    //   533: aload_2
    //   534: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   537: aload 9
    //   539: ifnull +8 -> 547
    //   542: aload 9
    //   544: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   547: iconst_1
    //   548: anewarray 4	java/lang/Object
    //   551: astore 13
    //   553: aload 13
    //   555: iconst_0
    //   556: iload 11
    //   558: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   561: aastore
    //   562: ldc_w 738
    //   565: aload 13
    //   567: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   570: aload 12
    //   572: athrow
    //   573: astore 12
    //   575: iconst_0
    //   576: istore 11
    //   578: goto -56 -> 522
    //   581: astore 10
    //   583: iload 8
    //   585: istore 11
    //   587: aload 10
    //   589: astore 12
    //   591: goto -69 -> 522
    //   594: astore 6
    //   596: aload 6
    //   598: astore 7
    //   600: iconst_0
    //   601: istore 8
    //   603: aconst_null
    //   604: astore_2
    //   605: aconst_null
    //   606: astore 9
    //   608: goto -286 -> 322
    //   611: astore 7
    //   613: goto -291 -> 322
    //
    // Exception table:
    //   from	to	target	type
    //   107	113	313	java/lang/Throwable
    //   117	137	313	java/lang/Throwable
    //   186	203	313	java/lang/Throwable
    //   203	310	313	java/lang/Throwable
    //   411	430	313	java/lang/Throwable
    //   97	103	514	finally
    //   107	113	573	finally
    //   117	137	573	finally
    //   186	203	573	finally
    //   203	310	573	finally
    //   411	430	573	finally
    //   322	360	581	finally
    //   434	463	581	finally
    //   97	103	594	java/lang/Throwable
    //   434	463	611	java/lang/Throwable
  }

  public static int b(Context paramContext, com.tencent.beacon.a.a.d[] paramArrayOfd)
  {
    int m;
    if (paramContext == null)
      m = -1;
    ArrayList localArrayList;
    int k;
    do
    {
      return m;
      if (paramArrayOfd == null)
        return com.tencent.beacon.a.a.a.a(paramContext, new int[] { 6 }, -1L, 9223372036854775807L);
      localArrayList = new ArrayList();
      int i = paramArrayOfd.length;
      for (int j = 0; j < i; j++)
      {
        com.tencent.beacon.a.a.d locald = paramArrayOfd[j];
        if (locald.a() < 0L)
          continue;
        localArrayList.add(Long.valueOf(locald.a()));
      }
      k = localArrayList.size();
      m = 0;
    }
    while (k <= 0);
    return com.tencent.beacon.a.a.a.a(paramContext, (Long[])localArrayList.toArray(new Long[0]));
  }

  public static String b()
  {
    try
    {
      String str = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return "";
  }

  public static String b(String paramString)
  {
    String str = paramString.replace('|', '_').trim();
    if (str.length() == 0)
    {
      com.tencent.beacon.d.a.c("eventName is invalid!! eventName length == 0!", new Object[0]);
      str = null;
    }
    while (true)
    {
      return str;
      if (!e(str))
        break;
      if (str.length() <= 128)
        continue;
      com.tencent.beacon.d.a.c("eventName is invalid!! eventName length should be less than 128! eventName:" + paramString, new Object[0]);
      return str.substring(0, 128);
    }
    com.tencent.beacon.d.a.c("eventName is invalid!! eventName should be ASCII code in 32-126! eventName:" + paramString, new Object[0]);
    return null;
  }

  public static List<com.tencent.beacon.a.a.d> b(Context paramContext)
  {
    if (paramContext == null)
      return null;
    List localList = com.tencent.beacon.a.a.a.a(paramContext, new int[] { 6 }, -1, -1, 9223372036854775807L, 5, -1, -1, -1, -1, -1L, 9223372036854775807L);
    if ((localList == null) || (localList.size() <= 0))
      return null;
    ArrayList localArrayList = new ArrayList(localList.size());
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      com.tencent.beacon.a.a.a locala = (com.tencent.beacon.a.a.a)localIterator.next();
      try
      {
        com.tencent.beacon.a.a.d locald = (com.tencent.beacon.a.a.d)com.tencent.beacon.a.a.d.class.cast(com.tencent.beacon.a.e.a(locala.b()));
        locald.a(locala.a());
        localArrayList.add(locald);
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localThrowable.toString();
        com.tencent.beacon.d.a.d("netconsume error %s", arrayOfObject);
      }
    }
    return localArrayList;
  }

  public static byte[] b(int paramInt, String paramString, byte[] paramArrayOfByte)
    throws Exception, NoSuchAlgorithmException
  {
    if (paramInt == 1)
      if ((paramString != null) && (paramArrayOfByte != null));
    do
    {
      return null;
      Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      DESKeySpec localDESKeySpec = new DESKeySpec(paramString.getBytes("UTF-8"));
      localCipher.init(1, SecretKeyFactory.getInstance("DES").generateSecret(localDESKeySpec), new IvParameterSpec(paramString.getBytes("UTF-8")));
      return localCipher.doFinal(paramArrayOfByte);
    }
    while (paramInt != 3);
    return b(paramString, paramArrayOfByte);
  }

  public static byte[] b(int paramInt, byte[] paramArrayOfByte)
    throws Exception
  {
    byte[] arrayOfByte1 = null;
    ZipInputStream localZipInputStream;
    if (paramInt == 1)
    {
      ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
      localZipInputStream = new ZipInputStream(localByteArrayInputStream);
      if (localZipInputStream.getNextEntry() == null)
      {
        localZipInputStream.close();
        localByteArrayInputStream.close();
      }
    }
    do
    {
      return arrayOfByte1;
      byte[] arrayOfByte2 = new byte[1024];
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      while (true)
      {
        int i = localZipInputStream.read(arrayOfByte2, 0, arrayOfByte2.length);
        if (i == -1)
        {
          arrayOfByte1 = localByteArrayOutputStream.toByteArray();
          localByteArrayOutputStream.flush();
          localByteArrayOutputStream.close();
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte2, 0, i);
      }
      arrayOfByte1 = null;
    }
    while (paramInt != 2);
    return a(paramArrayOfByte);
  }

  private static byte[] b(String paramString, byte[] paramArrayOfByte)
    throws Exception, NoSuchAlgorithmException
  {
    int i = 0;
    byte[] arrayOfByte;
    if ((paramString == null) || (paramArrayOfByte == null))
    {
      arrayOfByte = null;
      return arrayOfByte;
    }
    int j = paramString.length();
    label20: String str;
    StringBuffer localStringBuffer1;
    int k;
    if (j >= 16)
    {
      str = paramString.substring(0, 16);
      localStringBuffer1 = new StringBuffer();
      k = paramArrayOfByte.length;
    }
    for (int m = 0; ; m++)
    {
      if (m >= k)
      {
        SecretKeySpec localSecretKeySpec = new SecretKeySpec(str.getBytes(), "AES");
        Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        localCipher.init(1, localSecretKeySpec, new IvParameterSpec(str.getBytes()));
        arrayOfByte = localCipher.doFinal(paramArrayOfByte);
        StringBuffer localStringBuffer2 = new StringBuffer();
        int n = arrayOfByte.length;
        while (i < n)
        {
          localStringBuffer2.append(arrayOfByte[i] + " ");
          i++;
        }
        break;
        paramString = paramString + "0";
        j++;
        break label20;
      }
      localStringBuffer1.append(paramArrayOfByte[m] + " ");
    }
  }

  protected static ContentValues c(d paramd)
  {
    if (paramd == null)
      return null;
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_id", Long.valueOf(paramd.a()));
    localContentValues.put("_n", paramd.b());
    localContentValues.put("_sa", paramd.e());
    localContentValues.put("_sz", Long.valueOf(paramd.d()));
    localContentValues.put("_ut", Long.valueOf(paramd.c()));
    localContentValues.put("_t", Integer.valueOf(paramd.f()));
    localContentValues.put("_ac", paramd.g());
    localContentValues.put("_p", paramd.h());
    return localContentValues;
  }

  public static com.tencent.beacon.a.a.e c(Context paramContext)
  {
    com.tencent.beacon.a.a.e locale;
    if (paramContext == null)
      locale = null;
    while (true)
    {
      return locale;
      List localList = com.tencent.beacon.a.a.a.a(paramContext, new int[] { 8 }, -1, -1, -1L, 1, -1, -1, -1, -1, -1L, -1L);
      if ((localList == null) || (localList.size() <= 0))
        return null;
      com.tencent.beacon.a.a.a locala = (com.tencent.beacon.a.a.a)localList.get(0);
      if (locala == null)
        break;
      Object localObject = com.tencent.beacon.a.e.a(locala.b());
      if ((localObject == null) || (!com.tencent.beacon.a.a.e.class.isInstance(localObject)))
        break;
      locale = (com.tencent.beacon.a.a.e)com.tencent.beacon.a.a.e.class.cast(localObject);
      if (locale == null)
        continue;
      locale.e(locala.a());
      return locale;
    }
    return null;
  }

  public static String c(String paramString)
  {
    String str = paramString.replace('|', '_').trim();
    if (e(str))
    {
      if (str.length() < 5)
        com.tencent.beacon.d.a.c(" userID is invalid!! userID is too short, length < 5!", new Object[0]);
      if (str.length() > 128)
        str = str.substring(0, 128);
      return str;
    }
    com.tencent.beacon.d.a.c("userID is invalid!! userID should be ASCII code in 32-126! userID:" + paramString, new Object[0]);
    return "10000";
  }

  public static Long[] c(Context paramContext, List<h> paramList)
  {
    com.tencent.beacon.d.a.a(" RecordDAO.insertList() start", new Object[0]);
    if ((paramContext == null) || (paramList == null))
    {
      com.tencent.beacon.d.a.d(" insertList() have null args!", new Object[0]);
      return null;
    }
    int i = paramList.size();
    if (i == 0)
    {
      com.tencent.beacon.d.a.b(" list siez == 0 , return true!", new Object[0]);
      return null;
    }
    Long[] arrayOfLong = new Long[i];
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    while (true)
      if (j < i)
      {
        h localh = (h)paramList.get(j);
        int m;
        int n;
        if (localh.b().equals("UA"))
        {
          m = 1;
          n = 3;
        }
        try
        {
          label109: localArrayList.add(new com.tencent.beacon.a.a.a(m, n, localh.c(), com.tencent.beacon.a.e.a(localh)));
          while (true)
          {
            j++;
            break;
            if (localh.b().equals("IP"))
            {
              m = 2;
              n = 0;
              break label109;
            }
            if (localh.b().equals("DN"))
            {
              m = 3;
              n = 0;
              break label109;
            }
            if (localh.b().equals("HO"))
            {
              m = 4;
              n = 0;
              break label109;
            }
            com.tencent.beacon.d.a.d(" bean's type is error!", new Object[0]);
          }
        }
        catch (Throwable localThrowable)
        {
          while (true)
            localThrowable.printStackTrace();
        }
      }
    if (!com.tencent.beacon.a.a.a.a(paramContext, localArrayList))
      return null;
    Iterator localIterator = localArrayList.iterator();
    for (int k = 0; localIterator.hasNext(); k++)
    {
      com.tencent.beacon.a.a.a locala = (com.tencent.beacon.a.a.a)localIterator.next();
      if (k >= i)
        continue;
      arrayOfLong[k] = Long.valueOf(locala.a());
    }
    return arrayOfLong;
  }

  public static int d(Context paramContext)
  {
    com.tencent.beacon.d.a.a(" RecordDAO.countRecordNum() start", new Object[0]);
    if (paramContext == null)
    {
      com.tencent.beacon.d.a.d(" countRecordNum() have null args!", new Object[0]);
      return -1;
    }
    return com.tencent.beacon.a.a.a.b(paramContext, new int[] { 1, 2, 3, 4 }, -1L, 9223372036854775807L);
  }

  public static String d(String paramString)
  {
    String str1 = "unknown";
    if ((paramString == null) || (paramString.trim().length() == 0))
      return str1;
    String str2 = paramString.trim();
    int i = str2.length();
    int k;
    do
    {
      i--;
      if (i < 0)
        break;
      k = str2.charAt(i);
    }
    while ((k >= 48) && (k <= 57));
    for (int j = 0; ; j = 1)
    {
      if (j == 0)
        break label94;
      str1 = paramString.trim();
      if (str1.length() <= 16)
        break;
      return str1.substring(0, 16);
    }
    label94: com.tencent.beacon.d.a.c("channelID is invalid!! channelID should be Numeric! channelID:" + paramString, new Object[0]);
    return str1;
  }

  private static boolean d(Context paramContext, List<d> paramList)
  {
    com.tencent.beacon.d.a.b(" insertOrUpdate FileBean start!", new Object[0]);
    if ((paramContext == null) || (paramList == null) || (paramList.size() <= 0))
    {
      com.tencent.beacon.d.a.d(" context == null || list == null|| list.size() <= 0 ? pls check!", new Object[0]);
      return false;
    }
    SQLiteDatabase localSQLiteDatabase = null;
    try
    {
      localSQLiteDatabase = com.tencent.beacon.a.a.c.a(paramContext).getWritableDatabase();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        d locald = (d)localIterator.next();
        ContentValues localContentValues = c(locald);
        if (localContentValues == null)
        {
          if ((localSQLiteDatabase != null) && (localSQLiteDatabase.isOpen()))
            localSQLiteDatabase.close();
          return false;
        }
        long l = localSQLiteDatabase.replace("t_file", "_id", localContentValues);
        if (l < 0L)
        {
          com.tencent.beacon.d.a.d(" insertOrUpdate FileBean failure! return", new Object[0]);
          if ((localSQLiteDatabase != null) && (localSQLiteDatabase.isOpen()))
            localSQLiteDatabase.close();
          return false;
        }
        com.tencent.beacon.d.a.b(" result id:" + l, new Object[0]);
        locald.a(l);
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

  // ERROR //
  private static d e(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: ldc 35
    //   4: iconst_0
    //   5: anewarray 4	java/lang/Object
    //   8: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   11: aload_0
    //   12: invokestatic 45	com/tencent/beacon/a/a/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/a/c;
    //   15: astore 8
    //   17: aload 8
    //   19: astore 4
    //   21: aload 4
    //   23: invokevirtual 49	com/tencent/beacon/a/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   26: astore 9
    //   28: aload 9
    //   30: ldc 51
    //   32: aconst_null
    //   33: aconst_null
    //   34: aconst_null
    //   35: aconst_null
    //   36: aconst_null
    //   37: ldc_w 865
    //   40: invokevirtual 61	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   43: astore 12
    //   45: aload 12
    //   47: ifnull +326 -> 373
    //   50: aload 12
    //   52: invokeinterface 67 1 0
    //   57: ifeq +316 -> 373
    //   60: aload 12
    //   62: invokestatic 428	com/tencent/beacon/applog/a:a	(Landroid/database/Cursor;)Lcom/tencent/beacon/applog/d;
    //   65: astore 16
    //   67: aload 16
    //   69: astore 13
    //   71: aload 12
    //   73: ifnull +20 -> 93
    //   76: aload 12
    //   78: invokeinterface 95 1 0
    //   83: ifne +10 -> 93
    //   86: aload 12
    //   88: invokeinterface 98 1 0
    //   93: aload 9
    //   95: ifnull +16 -> 111
    //   98: aload 9
    //   100: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   103: ifeq +8 -> 111
    //   106: aload 9
    //   108: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   111: aload 4
    //   113: ifnull +8 -> 121
    //   116: aload 4
    //   118: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   121: ldc 105
    //   123: iconst_0
    //   124: anewarray 4	java/lang/Object
    //   127: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   130: aload 13
    //   132: areturn
    //   133: astore 5
    //   135: aconst_null
    //   136: astore 6
    //   138: aconst_null
    //   139: astore_3
    //   140: aconst_null
    //   141: astore 7
    //   143: aload 5
    //   145: invokevirtual 108	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   148: iconst_0
    //   149: anewarray 4	java/lang/Object
    //   152: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   155: aload 6
    //   157: ifnull +20 -> 177
    //   160: aload 6
    //   162: invokeinterface 95 1 0
    //   167: ifne +10 -> 177
    //   170: aload 6
    //   172: invokeinterface 98 1 0
    //   177: aload_3
    //   178: ifnull +14 -> 192
    //   181: aload_3
    //   182: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   185: ifeq +7 -> 192
    //   188: aload_3
    //   189: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   192: aload 7
    //   194: ifnull +8 -> 202
    //   197: aload 7
    //   199: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   202: ldc 105
    //   204: iconst_0
    //   205: anewarray 4	java/lang/Object
    //   208: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   211: aconst_null
    //   212: areturn
    //   213: astore_2
    //   214: aconst_null
    //   215: astore_3
    //   216: aconst_null
    //   217: astore 4
    //   219: aload_1
    //   220: ifnull +18 -> 238
    //   223: aload_1
    //   224: invokeinterface 95 1 0
    //   229: ifne +9 -> 238
    //   232: aload_1
    //   233: invokeinterface 98 1 0
    //   238: aload_3
    //   239: ifnull +14 -> 253
    //   242: aload_3
    //   243: invokevirtual 101	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   246: ifeq +7 -> 253
    //   249: aload_3
    //   250: invokevirtual 102	android/database/sqlite/SQLiteDatabase:close	()V
    //   253: aload 4
    //   255: ifnull +8 -> 263
    //   258: aload 4
    //   260: invokevirtual 103	com/tencent/beacon/a/a/c:close	()V
    //   263: ldc 105
    //   265: iconst_0
    //   266: anewarray 4	java/lang/Object
    //   269: invokestatic 40	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   272: aload_2
    //   273: athrow
    //   274: astore_2
    //   275: aconst_null
    //   276: astore_3
    //   277: aconst_null
    //   278: astore_1
    //   279: goto -60 -> 219
    //   282: astore 11
    //   284: aload 9
    //   286: astore_3
    //   287: aload 11
    //   289: astore_2
    //   290: aconst_null
    //   291: astore_1
    //   292: goto -73 -> 219
    //   295: astore 15
    //   297: aload 12
    //   299: astore_1
    //   300: aload 9
    //   302: astore_3
    //   303: aload 15
    //   305: astore_2
    //   306: goto -87 -> 219
    //   309: astore_2
    //   310: aload 6
    //   312: astore_1
    //   313: aload 7
    //   315: astore 4
    //   317: goto -98 -> 219
    //   320: astore 5
    //   322: aload 4
    //   324: astore 7
    //   326: aconst_null
    //   327: astore 6
    //   329: aconst_null
    //   330: astore_3
    //   331: goto -188 -> 143
    //   334: astore 10
    //   336: aload 9
    //   338: astore_3
    //   339: aload 4
    //   341: astore 7
    //   343: aload 10
    //   345: astore 5
    //   347: aconst_null
    //   348: astore 6
    //   350: goto -207 -> 143
    //   353: astore 14
    //   355: aload 4
    //   357: astore 7
    //   359: aload 9
    //   361: astore_3
    //   362: aload 14
    //   364: astore 5
    //   366: aload 12
    //   368: astore 6
    //   370: goto -227 -> 143
    //   373: aconst_null
    //   374: astore 13
    //   376: goto -305 -> 71
    //
    // Exception table:
    //   from	to	target	type
    //   11	17	133	java/lang/Throwable
    //   11	17	213	finally
    //   21	28	274	finally
    //   28	45	282	finally
    //   50	67	295	finally
    //   143	155	309	finally
    //   21	28	320	java/lang/Throwable
    //   28	45	334	java/lang/Throwable
    //   50	67	353	java/lang/Throwable
  }

  public static boolean e(String paramString)
  {
    int i = paramString.length();
    int j = 1;
    int k = i;
    while (true)
    {
      k--;
      if (k < 0)
        break;
      int m = paramString.charAt(k);
      if ((m >= 32) && (m < 127))
        continue;
      j = 0;
    }
    return j;
  }

  protected final void a()
  {
    g localg;
    if (this.a != null)
    {
      localg = m.d().j();
      if ((!com.tencent.beacon.d.b.b(this.b)) || (localg == null))
        b(this.a);
    }
    else
    {
      return;
    }
    localg.a(new c(this.b, this.a, true));
  }

  public final boolean a(d paramd)
  {
    int i = 0;
    monitorenter;
    while (true)
    {
      try
      {
        if ((this.b != null) && (paramd != null))
          continue;
        com.tencent.beacon.d.a.c("processUA return false, context is null or bean is null !", new Object[0]);
        return i;
        this.a = paramd;
        if (b.a() == null)
        {
          com.tencent.beacon.d.a.d("isModuleAble:not init ua", new Object[0]);
          i = 0;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      com.tencent.beacon.a.c.a().a(this.c);
      com.tencent.beacon.d.a.a("processUA:true!", new Object[0]);
      i = 1;
    }
  }

  public final boolean b(d paramd)
  {
    monitorenter;
    while (true)
    {
      List localList;
      try
      {
        if (b.a() != null)
          continue;
        com.tencent.beacon.d.a.d("isModuleAble:not init ua", new Object[0]);
        m = 0;
        if (m == 0)
          continue;
        if ((paramd.h() == null) || ("".equals(paramd.h())))
          break label400;
        com.tencent.beacon.d.a.e("appLog upload    create newLog File with user path", new Object[0]);
        if (!a(paramd.i(), paramd.h()))
          continue;
        Context localContext2 = this.b;
        ArrayList localArrayList3 = new ArrayList();
        localArrayList3.add(paramd);
        if (a(localContext2, localArrayList3) == -1)
          continue;
        long l = m.d().k().r();
        if (!com.tencent.beacon.d.b.a(this.b))
          continue;
        com.tencent.beacon.d.a.e(" onwifi, so half mSZ " + l, new Object[0]);
        l /= 2L;
        if (a(this.b) < l)
          break label759;
        i1 = 1;
        if (i1 == 0)
          continue;
        this.d.run();
        com.tencent.beacon.d.a.e(" max Up", new Object[0]);
        return false;
        com.tencent.beacon.event.d locald1 = m.d().k();
        int i = a(this.b);
        int j = locald1.q();
        com.tencent.beacon.d.a.e("appLog upload   recordNum:" + i, new Object[0]);
        com.tencent.beacon.d.a.e("appLog upload   recordNumMax:" + j, new Object[0]);
        if (i < j)
          break label765;
        int k = i - j / 2;
        localList = a(this.b, null, -1, k);
        com.tencent.beacon.d.a.e("appLog upload   deleteRecords:" + localList.size(), new Object[0]);
        Iterator localIterator = localList.iterator();
        if (localIterator.hasNext())
        {
          new File(((d)localIterator.next()).h()).delete();
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      b(this.b, localList);
      break label765;
      label400: d locald = e(this.b);
      com.tencent.beacon.event.d locald2 = m.d().k();
      if ((locald != null) && (locald2.p() - locald.d() > paramd.d()))
      {
        com.tencent.beacon.d.a.e("appLog upload    append Log to File", new Object[0]);
        File localFile = new File(locald.h());
        String str = paramd.i();
        boolean bool = localFile.exists();
        if (bool);
        while (true)
        {
          try
          {
            FileWriter localFileWriter = new FileWriter(localFile, true);
            localFileWriter.write(str);
            localFileWriter.close();
            com.tencent.beacon.d.a.e("appLog upload   file size before append:" + locald.d(), new Object[0]);
            locald.c(paramd.d() + locald.d());
            com.tencent.beacon.d.a.e("appLog upload   file size after append:" + locald.d(), new Object[0]);
            ArrayList localArrayList2 = new ArrayList();
            localArrayList2.add(locald);
            d(this.b, localArrayList2);
          }
          catch (IOException localIOException)
          {
            localIOException.printStackTrace();
            continue;
          }
          com.tencent.beacon.d.a.e("appLog upload  File not exist", new Object[0]);
        }
      }
      com.tencent.beacon.d.a.e("appLog upload    create newLog File with default path", new Object[0]);
      if ((paramd.h() == null) || ("".equals(paramd.h())))
        paramd.d(this.b.getFilesDir().toString() + "/log" + System.currentTimeMillis());
      if (!a(paramd.i(), paramd.h()))
        continue;
      Context localContext1 = this.b;
      ArrayList localArrayList1 = new ArrayList();
      localArrayList1.add(paramd);
      int n = a(localContext1, localArrayList1);
      if (n == -1)
        continue;
      continue;
      label759: int i1 = 0;
      continue;
      label765: int m = 1;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.applog.a
 * JD-Core Version:    0.6.0
 */