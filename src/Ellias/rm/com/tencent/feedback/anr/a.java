package com.tencent.feedback.anr;

import B;
import android.content.Context;
import com.tencent.feedback.common.a.f;
import com.tencent.feedback.common.h;
import common.RequestPackage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class a
{
  private String a;
  private long b;
  private String c;
  private String d;
  private int e;

  public static int a(Context paramContext, com.tencent.feedback.common.a.e[] paramArrayOfe)
  {
    int i;
    if ((paramContext == null) || (paramArrayOfe == null) || (paramArrayOfe.length <= 0))
      i = -1;
    ArrayList localArrayList;
    int m;
    do
    {
      return i;
      localArrayList = new ArrayList(paramArrayOfe.length);
      int j = paramArrayOfe.length;
      for (int k = 0; k < j; k++)
      {
        com.tencent.feedback.common.a.e locale = paramArrayOfe[k];
        byte[] arrayOfByte = a(locale);
        if (arrayOfByte == null)
          continue;
        com.tencent.feedback.common.a.a locala = new com.tencent.feedback.common.a.a(7, 0, 0L, arrayOfByte);
        locala.a(locale.a());
        localArrayList.add(locala);
      }
      m = localArrayList.size();
      i = 0;
    }
    while (m <= 0);
    if (com.tencent.feedback.common.a.a.b(paramContext, localArrayList))
      return localArrayList.size();
    return -1;
  }

  public static int a(Context paramContext, f[] paramArrayOff)
  {
    monitorenter;
    if ((paramContext != null) && (paramArrayOff != null));
    while (true)
    {
      ArrayList localArrayList;
      int k;
      try
      {
        if (paramArrayOff.length > 0)
          continue;
        com.tencent.feedback.common.e.c("rqdp{  args error}", new Object[0]);
        i = 0;
        return i;
        localArrayList = new ArrayList(paramArrayOff.length);
        int j = paramArrayOff.length;
        k = 0;
        if (k < j)
        {
          f localf = paramArrayOff[k];
          byte[] arrayOfByte = a(localf);
          if (arrayOfByte != null)
            continue;
          com.tencent.feedback.common.e.c("rqdp{ getSerData error }", new Object[0]);
          break label169;
          com.tencent.feedback.common.a.a locala = new com.tencent.feedback.common.a.a(9, 0, localf.a(), arrayOfByte);
          locala.a(localf.c());
          localArrayList.add(locala);
        }
      }
      finally
      {
        monitorexit;
      }
      if ((localArrayList.size() > 0) && (com.tencent.feedback.common.a.a.a(paramContext, localArrayList)))
      {
        int m = localArrayList.size();
        i = m;
        continue;
      }
      int i = 0;
      continue;
      label169: k++;
    }
  }

  // ERROR //
  public static com.tencent.feedback.common.b.i a(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: ifnonnull +14 -> 17
    //   6: ldc 75
    //   8: iconst_0
    //   9: anewarray 4	java/lang/Object
    //   12: invokestatic 57	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   15: aconst_null
    //   16: areturn
    //   17: new 77	com/tencent/feedback/common/a/c
    //   20: dup
    //   21: aload_0
    //   22: invokespecial 80	com/tencent/feedback/common/a/c:<init>	(Landroid/content/Context;)V
    //   25: astore_3
    //   26: aload_3
    //   27: invokevirtual 84	com/tencent/feedback/common/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   30: astore 11
    //   32: aload 11
    //   34: ifnonnull +36 -> 70
    //   37: ldc 86
    //   39: iconst_0
    //   40: anewarray 4	java/lang/Object
    //   43: invokestatic 57	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   46: aload 11
    //   48: ifnull +16 -> 64
    //   51: aload 11
    //   53: invokevirtual 92	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   56: ifeq +8 -> 64
    //   59: aload 11
    //   61: invokevirtual 95	android/database/sqlite/SQLiteDatabase:close	()V
    //   64: aload_3
    //   65: invokevirtual 96	com/tencent/feedback/common/a/c:close	()V
    //   68: aconst_null
    //   69: areturn
    //   70: getstatic 102	java/util/Locale:US	Ljava/util/Locale;
    //   73: astore 14
    //   75: iconst_2
    //   76: anewarray 4	java/lang/Object
    //   79: astore 15
    //   81: aload 15
    //   83: iconst_0
    //   84: ldc 104
    //   86: aastore
    //   87: aload 15
    //   89: iconst_1
    //   90: iload_1
    //   91: invokestatic 110	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   94: aastore
    //   95: aload 11
    //   97: ldc 112
    //   99: aconst_null
    //   100: aload 14
    //   102: ldc 114
    //   104: aload 15
    //   106: invokestatic 120	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   109: aconst_null
    //   110: aconst_null
    //   111: aconst_null
    //   112: aconst_null
    //   113: invokevirtual 124	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   116: astore 16
    //   118: aload 16
    //   120: astore 9
    //   122: aload 9
    //   124: ifnull +302 -> 426
    //   127: aload 9
    //   129: invokeinterface 129 1 0
    //   134: ifeq +292 -> 426
    //   137: aload 9
    //   139: ifnull +488 -> 627
    //   142: aload 9
    //   144: invokeinterface 132 1 0
    //   149: ifne +478 -> 627
    //   152: aload 9
    //   154: invokeinterface 135 1 0
    //   159: ifeq +83 -> 242
    //   162: goto +465 -> 627
    //   165: aload 20
    //   167: ifnull +259 -> 426
    //   170: iconst_1
    //   171: anewarray 4	java/lang/Object
    //   174: astore 21
    //   176: aload 21
    //   178: iconst_0
    //   179: aload 20
    //   181: invokevirtual 139	com/tencent/feedback/common/b/i:b	()I
    //   184: invokestatic 110	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   187: aastore
    //   188: ldc 141
    //   190: aload 21
    //   192: invokestatic 144	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   195: aload 9
    //   197: ifnull +20 -> 217
    //   200: aload 9
    //   202: invokeinterface 147 1 0
    //   207: ifne +10 -> 217
    //   210: aload 9
    //   212: invokeinterface 148 1 0
    //   217: aload 11
    //   219: ifnull +16 -> 235
    //   222: aload 11
    //   224: invokevirtual 92	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   227: ifeq +8 -> 235
    //   230: aload 11
    //   232: invokevirtual 95	android/database/sqlite/SQLiteDatabase:close	()V
    //   235: aload_3
    //   236: invokevirtual 96	com/tencent/feedback/common/a/c:close	()V
    //   239: aload 20
    //   241: areturn
    //   242: ldc 150
    //   244: iconst_0
    //   245: anewarray 4	java/lang/Object
    //   248: invokestatic 152	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   251: new 137	com/tencent/feedback/common/b/i
    //   254: dup
    //   255: invokespecial 153	com/tencent/feedback/common/b/i:<init>	()V
    //   258: astore 20
    //   260: aload 20
    //   262: aload 9
    //   264: aload 9
    //   266: ldc 155
    //   268: invokeinterface 159 2 0
    //   273: invokeinterface 163 2 0
    //   278: invokevirtual 166	com/tencent/feedback/common/b/i:a	(J)V
    //   281: aload 20
    //   283: aload 9
    //   285: aload 9
    //   287: ldc 104
    //   289: invokeinterface 159 2 0
    //   294: invokeinterface 170 2 0
    //   299: invokevirtual 172	com/tencent/feedback/common/b/i:a	(I)V
    //   302: aload 20
    //   304: aload 9
    //   306: aload 9
    //   308: ldc 174
    //   310: invokeinterface 159 2 0
    //   315: invokeinterface 178 2 0
    //   320: invokevirtual 181	com/tencent/feedback/common/b/i:a	([B)V
    //   323: goto -158 -> 165
    //   326: astore 18
    //   328: aload_3
    //   329: astore 5
    //   331: aload 9
    //   333: astore 19
    //   335: aload 11
    //   337: astore 7
    //   339: aload 18
    //   341: astore 4
    //   343: aload 19
    //   345: astore 6
    //   347: aload 4
    //   349: invokevirtual 184	java/lang/Throwable:printStackTrace	()V
    //   352: iconst_1
    //   353: anewarray 4	java/lang/Object
    //   356: astore 10
    //   358: aload 10
    //   360: iconst_0
    //   361: aload 4
    //   363: invokevirtual 187	java/lang/Throwable:toString	()Ljava/lang/String;
    //   366: aastore
    //   367: ldc 189
    //   369: aload 10
    //   371: invokestatic 191	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   374: aload 6
    //   376: ifnull +20 -> 396
    //   379: aload 6
    //   381: invokeinterface 147 1 0
    //   386: ifne +10 -> 396
    //   389: aload 6
    //   391: invokeinterface 148 1 0
    //   396: aload 7
    //   398: ifnull +16 -> 414
    //   401: aload 7
    //   403: invokevirtual 92	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   406: ifeq +8 -> 414
    //   409: aload 7
    //   411: invokevirtual 95	android/database/sqlite/SQLiteDatabase:close	()V
    //   414: aload 5
    //   416: ifnull +8 -> 424
    //   419: aload 5
    //   421: invokevirtual 96	com/tencent/feedback/common/a/c:close	()V
    //   424: aconst_null
    //   425: areturn
    //   426: aload 9
    //   428: ifnull +20 -> 448
    //   431: aload 9
    //   433: invokeinterface 147 1 0
    //   438: ifne +10 -> 448
    //   441: aload 9
    //   443: invokeinterface 148 1 0
    //   448: aload 11
    //   450: ifnull +16 -> 466
    //   453: aload 11
    //   455: invokevirtual 92	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   458: ifeq +8 -> 466
    //   461: aload 11
    //   463: invokevirtual 95	android/database/sqlite/SQLiteDatabase:close	()V
    //   466: aload_3
    //   467: invokevirtual 96	com/tencent/feedback/common/a/c:close	()V
    //   470: goto -46 -> 424
    //   473: astore 8
    //   475: aconst_null
    //   476: astore 9
    //   478: aconst_null
    //   479: astore_3
    //   480: aload 9
    //   482: ifnull +20 -> 502
    //   485: aload 9
    //   487: invokeinterface 147 1 0
    //   492: ifne +10 -> 502
    //   495: aload 9
    //   497: invokeinterface 148 1 0
    //   502: aload_2
    //   503: ifnull +14 -> 517
    //   506: aload_2
    //   507: invokevirtual 92	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   510: ifeq +7 -> 517
    //   513: aload_2
    //   514: invokevirtual 95	android/database/sqlite/SQLiteDatabase:close	()V
    //   517: aload_3
    //   518: ifnull +7 -> 525
    //   521: aload_3
    //   522: invokevirtual 96	com/tencent/feedback/common/a/c:close	()V
    //   525: aload 8
    //   527: athrow
    //   528: astore 8
    //   530: aconst_null
    //   531: astore 9
    //   533: aconst_null
    //   534: astore_2
    //   535: goto -55 -> 480
    //   538: astore 13
    //   540: aload 11
    //   542: astore_2
    //   543: aload 13
    //   545: astore 8
    //   547: aconst_null
    //   548: astore 9
    //   550: goto -70 -> 480
    //   553: astore 17
    //   555: aload 11
    //   557: astore_2
    //   558: aload 17
    //   560: astore 8
    //   562: goto -82 -> 480
    //   565: astore 8
    //   567: aload 7
    //   569: astore_2
    //   570: aload 5
    //   572: astore_3
    //   573: aload 6
    //   575: astore 9
    //   577: goto -97 -> 480
    //   580: astore 4
    //   582: aconst_null
    //   583: astore 6
    //   585: aconst_null
    //   586: astore 7
    //   588: aconst_null
    //   589: astore 5
    //   591: goto -244 -> 347
    //   594: astore 4
    //   596: aload_3
    //   597: astore 5
    //   599: aconst_null
    //   600: astore 6
    //   602: aconst_null
    //   603: astore 7
    //   605: goto -258 -> 347
    //   608: astore 12
    //   610: aload 11
    //   612: astore 7
    //   614: aload_3
    //   615: astore 5
    //   617: aload 12
    //   619: astore 4
    //   621: aconst_null
    //   622: astore 6
    //   624: goto -277 -> 347
    //   627: aconst_null
    //   628: astore 20
    //   630: goto -465 -> 165
    //
    // Exception table:
    //   from	to	target	type
    //   127	137	326	java/lang/Throwable
    //   142	162	326	java/lang/Throwable
    //   170	195	326	java/lang/Throwable
    //   242	323	326	java/lang/Throwable
    //   17	26	473	finally
    //   26	32	528	finally
    //   37	46	538	finally
    //   70	118	538	finally
    //   127	137	553	finally
    //   142	162	553	finally
    //   170	195	553	finally
    //   242	323	553	finally
    //   347	374	565	finally
    //   17	26	580	java/lang/Throwable
    //   26	32	594	java/lang/Throwable
    //   37	46	608	java/lang/Throwable
    //   70	118	608	java/lang/Throwable
  }

  public static RequestPackage a(int paramInt1, com.tencent.feedback.common.c paramc, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    com.tencent.feedback.common.e.b("rqdp{  en2Req }", new Object[0]);
    if (paramc == null)
    {
      com.tencent.feedback.common.e.d("rqdp{  error no com info!}", new Object[0]);
      return null;
    }
    try
    {
      RequestPackage localRequestPackage = new RequestPackage();
      monitorenter;
      try
      {
        localRequestPackage.setHardware_os(paramc.a());
        localRequestPackage.setPlatformId(paramc.b());
        localRequestPackage.setProductId(paramc.m());
        localRequestPackage.setProductVersion(paramc.d());
        localRequestPackage.setSdkId(paramc.e());
        localRequestPackage.setSdkVersion(paramc.f());
        localRequestPackage.setQua(paramc.g());
        localRequestPackage.setProductIdentity(paramc.j());
        localRequestPackage.setReserved(paramc.k());
        localRequestPackage.setBundleId(paramc.c());
        try
        {
          localRequestPackage.setQimei(h.a(paramc.o()).a());
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = localRequestPackage.getQimei();
          com.tencent.feedback.common.e.b("qimei:%s", arrayOfObject);
          monitorexit;
          localRequestPackage.setCmd(paramInt1);
          localRequestPackage.setEncryType((byte)paramInt3);
          localRequestPackage.setZipType((byte)paramInt2);
          if (paramArrayOfByte == null)
            paramArrayOfByte = "".getBytes();
          localRequestPackage.setSBuffer(paramArrayOfByte);
          return localRequestPackage;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
      finally
      {
        monitorexit;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }

  // ERROR //
  public static Object a(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: ldc_w 309
    //   3: iconst_0
    //   4: anewarray 4	java/lang/Object
    //   7: invokestatic 152	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   10: aload_0
    //   11: ifnull +8 -> 19
    //   14: aload_0
    //   15: arraylength
    //   16: ifge +5 -> 21
    //   19: aconst_null
    //   20: areturn
    //   21: new 311	java/io/ByteArrayInputStream
    //   24: dup
    //   25: aload_0
    //   26: invokespecial 313	java/io/ByteArrayInputStream:<init>	([B)V
    //   29: astore_1
    //   30: new 315	java/io/ObjectInputStream
    //   33: dup
    //   34: aload_1
    //   35: invokespecial 318	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   38: astore_2
    //   39: aload_2
    //   40: invokevirtual 322	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   43: astore 9
    //   45: aload_2
    //   46: invokevirtual 323	java/io/ObjectInputStream:close	()V
    //   49: aload_1
    //   50: invokevirtual 324	java/io/ByteArrayInputStream:close	()V
    //   53: aload 9
    //   55: areturn
    //   56: astore 11
    //   58: aload 11
    //   60: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   63: aload 9
    //   65: areturn
    //   66: astore 10
    //   68: aload 10
    //   70: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   73: goto -24 -> 49
    //   76: astore_3
    //   77: aconst_null
    //   78: astore_2
    //   79: aload_3
    //   80: invokevirtual 184	java/lang/Throwable:printStackTrace	()V
    //   83: aload_3
    //   84: invokevirtual 328	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   87: iconst_0
    //   88: anewarray 4	java/lang/Object
    //   91: invokestatic 191	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   94: aload_2
    //   95: ifnull +7 -> 102
    //   98: aload_2
    //   99: invokevirtual 323	java/io/ObjectInputStream:close	()V
    //   102: aload_1
    //   103: invokevirtual 324	java/io/ByteArrayInputStream:close	()V
    //   106: aconst_null
    //   107: areturn
    //   108: astore 7
    //   110: aload 7
    //   112: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   115: aconst_null
    //   116: areturn
    //   117: astore 8
    //   119: aload 8
    //   121: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   124: goto -22 -> 102
    //   127: astore 12
    //   129: aconst_null
    //   130: astore_2
    //   131: aload 12
    //   133: astore 4
    //   135: aload_2
    //   136: ifnull +7 -> 143
    //   139: aload_2
    //   140: invokevirtual 323	java/io/ObjectInputStream:close	()V
    //   143: aload_1
    //   144: invokevirtual 324	java/io/ByteArrayInputStream:close	()V
    //   147: aload 4
    //   149: athrow
    //   150: astore 6
    //   152: aload 6
    //   154: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   157: goto -14 -> 143
    //   160: astore 5
    //   162: aload 5
    //   164: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   167: goto -20 -> 147
    //   170: astore 4
    //   172: goto -37 -> 135
    //   175: astore_3
    //   176: goto -97 -> 79
    //
    // Exception table:
    //   from	to	target	type
    //   49	53	56	java/io/IOException
    //   45	49	66	java/io/IOException
    //   30	39	76	java/lang/Throwable
    //   102	106	108	java/io/IOException
    //   98	102	117	java/io/IOException
    //   30	39	127	finally
    //   139	143	150	java/io/IOException
    //   143	147	160	java/io/IOException
    //   39	45	170	finally
    //   79	94	170	finally
    //   39	45	175	java/lang/Throwable
  }

  protected static String a(BufferedReader paramBufferedReader)
    throws IOException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < 3; i++)
    {
      String str = paramBufferedReader.readLine();
      if (str == null)
        return null;
      localStringBuffer.append(str + "\n");
    }
    return localStringBuffer.toString();
  }

  public static String a(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    if (paramArrayOfByte == null)
      return "";
    int i = paramArrayOfByte.length;
    StringBuffer localStringBuffer = new StringBuffer(i << 1);
    Formatter localFormatter = new Formatter(localStringBuffer);
    for (int j = 0; j < i; j++)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Byte.valueOf(paramArrayOfByte[j]);
      localFormatter.format("%02x", arrayOfObject);
    }
    localFormatter.close();
    return localStringBuffer.toString().toLowerCase();
  }

  // ERROR //
  public static ArrayList<String> a(String[] paramArrayOfString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 19	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 366	java/util/ArrayList:<init>	()V
    //   9: astore_2
    //   10: invokestatic 372	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   13: aload_0
    //   14: invokevirtual 376	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   17: astore 11
    //   19: new 334	java/io/BufferedReader
    //   22: dup
    //   23: new 378	java/io/InputStreamReader
    //   26: dup
    //   27: aload 11
    //   29: invokevirtual 384	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   32: invokespecial 385	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   35: invokespecial 388	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   38: astore 5
    //   40: aload 5
    //   42: invokevirtual 337	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   45: astore 12
    //   47: aload 12
    //   49: ifnull +43 -> 92
    //   52: aload_2
    //   53: aload 12
    //   55: invokevirtual 42	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   58: pop
    //   59: goto -19 -> 40
    //   62: astore_3
    //   63: aconst_null
    //   64: astore 4
    //   66: aload_3
    //   67: invokevirtual 184	java/lang/Throwable:printStackTrace	()V
    //   70: aload 5
    //   72: ifnull +8 -> 80
    //   75: aload 5
    //   77: invokevirtual 389	java/io/BufferedReader:close	()V
    //   80: aload 4
    //   82: ifnull +8 -> 90
    //   85: aload 4
    //   87: invokevirtual 389	java/io/BufferedReader:close	()V
    //   90: aconst_null
    //   91: areturn
    //   92: new 334	java/io/BufferedReader
    //   95: dup
    //   96: new 378	java/io/InputStreamReader
    //   99: dup
    //   100: aload 11
    //   102: invokevirtual 392	java/lang/Process:getErrorStream	()Ljava/io/InputStream;
    //   105: invokespecial 385	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   108: invokespecial 388	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   111: astore 4
    //   113: aload 4
    //   115: invokevirtual 337	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   118: astore 14
    //   120: aload 14
    //   122: ifnull +17 -> 139
    //   125: aload_2
    //   126: aload 14
    //   128: invokevirtual 42	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   131: pop
    //   132: goto -19 -> 113
    //   135: astore_3
    //   136: goto -70 -> 66
    //   139: aload 5
    //   141: invokevirtual 389	java/io/BufferedReader:close	()V
    //   144: aload 4
    //   146: invokevirtual 389	java/io/BufferedReader:close	()V
    //   149: aload_2
    //   150: areturn
    //   151: astore 17
    //   153: aload 17
    //   155: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   158: aload_2
    //   159: areturn
    //   160: astore 16
    //   162: aload 16
    //   164: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   167: goto -23 -> 144
    //   170: astore 10
    //   172: aload 10
    //   174: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   177: goto -97 -> 80
    //   180: astore 9
    //   182: aload 9
    //   184: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   187: goto -97 -> 90
    //   190: astore 6
    //   192: aconst_null
    //   193: astore 5
    //   195: aload 5
    //   197: ifnull +8 -> 205
    //   200: aload 5
    //   202: invokevirtual 389	java/io/BufferedReader:close	()V
    //   205: aload_1
    //   206: ifnull +7 -> 213
    //   209: aload_1
    //   210: invokevirtual 389	java/io/BufferedReader:close	()V
    //   213: aload 6
    //   215: athrow
    //   216: astore 8
    //   218: aload 8
    //   220: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   223: goto -18 -> 205
    //   226: astore 7
    //   228: aload 7
    //   230: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   233: goto -20 -> 213
    //   236: astore 6
    //   238: aconst_null
    //   239: astore_1
    //   240: goto -45 -> 195
    //   243: astore 6
    //   245: aload 4
    //   247: astore_1
    //   248: goto -53 -> 195
    //   251: astore_3
    //   252: aconst_null
    //   253: astore 4
    //   255: aconst_null
    //   256: astore 5
    //   258: goto -192 -> 66
    //
    // Exception table:
    //   from	to	target	type
    //   40	47	62	java/lang/Throwable
    //   52	59	62	java/lang/Throwable
    //   92	113	62	java/lang/Throwable
    //   113	120	135	java/lang/Throwable
    //   125	132	135	java/lang/Throwable
    //   144	149	151	java/io/IOException
    //   139	144	160	java/io/IOException
    //   75	80	170	java/io/IOException
    //   85	90	180	java/io/IOException
    //   10	40	190	finally
    //   200	205	216	java/io/IOException
    //   209	213	226	java/io/IOException
    //   40	47	236	finally
    //   52	59	236	finally
    //   92	113	236	finally
    //   66	70	243	finally
    //   113	120	243	finally
    //   125	132	243	finally
    //   10	40	251	java/lang/Throwable
  }

  public static List<com.tencent.feedback.common.a.e> a(Context paramContext)
  {
    if (paramContext == null)
      return null;
    List localList = com.tencent.feedback.common.a.a.a(paramContext, new int[] { 7 }, -1, -1, 9223372036854775807L, 5, null, -1, -1, -1, -1, -1L, 9223372036854775807L, -1);
    if ((localList == null) || (localList.size() <= 0))
      return null;
    ArrayList localArrayList = new ArrayList(localList.size());
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      com.tencent.feedback.common.a.a locala = (com.tencent.feedback.common.a.a)localIterator.next();
      try
      {
        com.tencent.feedback.common.a.e locale = (com.tencent.feedback.common.a.e)com.tencent.feedback.common.a.e.class.cast(a(locala.b()));
        locale.a(locala.a());
        localArrayList.add(locale);
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localThrowable.toString();
        com.tencent.feedback.common.e.d("rqdp{  netconsume error }%s", arrayOfObject);
      }
    }
    return localArrayList;
  }

  // ERROR //
  public static void a(String paramString, e parame)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +7 -> 8
    //   4: aload_1
    //   5: ifnonnull +4 -> 9
    //   8: return
    //   9: new 433	java/io/File
    //   12: dup
    //   13: aload_0
    //   14: invokespecial 435	java/io/File:<init>	(Ljava/lang/String;)V
    //   17: astore_2
    //   18: aload_2
    //   19: invokevirtual 438	java/io/File:exists	()Z
    //   22: ifeq -14 -> 8
    //   25: aload_2
    //   26: invokevirtual 441	java/io/File:lastModified	()J
    //   29: pop2
    //   30: aload_2
    //   31: invokevirtual 444	java/io/File:length	()J
    //   34: pop2
    //   35: new 334	java/io/BufferedReader
    //   38: dup
    //   39: new 446	java/io/FileReader
    //   42: dup
    //   43: aload_2
    //   44: invokespecial 449	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   47: invokespecial 388	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   50: astore 7
    //   52: ldc_w 451
    //   55: invokestatic 457	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   58: astore 13
    //   60: ldc_w 459
    //   63: invokestatic 457	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   66: astore 14
    //   68: ldc_w 461
    //   71: invokestatic 457	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   74: astore 15
    //   76: ldc_w 463
    //   79: invokestatic 457	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   82: astore 16
    //   84: new 465	java/text/SimpleDateFormat
    //   87: dup
    //   88: ldc_w 467
    //   91: getstatic 102	java/util/Locale:US	Ljava/util/Locale;
    //   94: invokespecial 470	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   97: astore 17
    //   99: aload 7
    //   101: iconst_1
    //   102: anewarray 453	java/util/regex/Pattern
    //   105: dup
    //   106: iconst_0
    //   107: aload 13
    //   109: aastore
    //   110: invokestatic 473	com/tencent/feedback/anr/a:a	(Ljava/io/BufferedReader;[Ljava/util/regex/Pattern;)[Ljava/lang/Object;
    //   113: astore 18
    //   115: aload 18
    //   117: ifnull +401 -> 518
    //   120: aload 18
    //   122: iconst_1
    //   123: aaload
    //   124: invokevirtual 474	java/lang/Object:toString	()Ljava/lang/String;
    //   127: ldc_w 476
    //   130: invokevirtual 480	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   133: astore 19
    //   135: aload 19
    //   137: iconst_2
    //   138: aaload
    //   139: invokestatic 486	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   142: lstore 20
    //   144: aload 17
    //   146: new 261	java/lang/StringBuilder
    //   149: dup
    //   150: invokespecial 262	java/lang/StringBuilder:<init>	()V
    //   153: aload 19
    //   155: iconst_4
    //   156: aaload
    //   157: invokevirtual 276	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: ldc_w 488
    //   163: invokevirtual 276	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: aload 19
    //   168: iconst_5
    //   169: aaload
    //   170: invokevirtual 276	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: invokevirtual 277	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   176: invokevirtual 492	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   179: invokevirtual 497	java/util/Date:getTime	()J
    //   182: lstore 22
    //   184: aload 7
    //   186: iconst_1
    //   187: anewarray 453	java/util/regex/Pattern
    //   190: dup
    //   191: iconst_0
    //   192: aload 15
    //   194: aastore
    //   195: invokestatic 473	com/tencent/feedback/anr/a:a	(Ljava/io/BufferedReader;[Ljava/util/regex/Pattern;)[Ljava/lang/Object;
    //   198: astore 24
    //   200: aload 24
    //   202: ifnonnull +17 -> 219
    //   205: aload 7
    //   207: invokevirtual 389	java/io/BufferedReader:close	()V
    //   210: return
    //   211: astore 43
    //   213: aload 43
    //   215: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   218: return
    //   219: aload 15
    //   221: aload 24
    //   223: iconst_1
    //   224: aaload
    //   225: invokevirtual 474	java/lang/Object:toString	()Ljava/lang/String;
    //   228: invokevirtual 501	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   231: astore 25
    //   233: aload 25
    //   235: invokevirtual 506	java/util/regex/Matcher:find	()Z
    //   238: pop
    //   239: aload 25
    //   241: iconst_1
    //   242: invokevirtual 510	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   245: pop
    //   246: aload_1
    //   247: lload 20
    //   249: lload 22
    //   251: aload 25
    //   253: iconst_1
    //   254: invokevirtual 510	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   257: invokeinterface 515 6 0
    //   262: istore 28
    //   264: iload 28
    //   266: ifne +17 -> 283
    //   269: aload 7
    //   271: invokevirtual 389	java/io/BufferedReader:close	()V
    //   274: return
    //   275: astore 42
    //   277: aload 42
    //   279: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   282: return
    //   283: aload 7
    //   285: iconst_2
    //   286: anewarray 453	java/util/regex/Pattern
    //   289: dup
    //   290: iconst_0
    //   291: aload 16
    //   293: aastore
    //   294: dup
    //   295: iconst_1
    //   296: aload 14
    //   298: aastore
    //   299: invokestatic 473	com/tencent/feedback/anr/a:a	(Ljava/io/BufferedReader;[Ljava/util/regex/Pattern;)[Ljava/lang/Object;
    //   302: astore 29
    //   304: aload 29
    //   306: ifnull -207 -> 99
    //   309: aload 29
    //   311: iconst_0
    //   312: aaload
    //   313: aload 16
    //   315: if_acmpne +158 -> 473
    //   318: aload 29
    //   320: iconst_1
    //   321: aaload
    //   322: invokevirtual 474	java/lang/Object:toString	()Ljava/lang/String;
    //   325: astore 32
    //   327: ldc_w 517
    //   330: invokestatic 457	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   333: aload 32
    //   335: invokevirtual 501	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   338: astore 33
    //   340: aload 33
    //   342: invokevirtual 506	java/util/regex/Matcher:find	()Z
    //   345: pop
    //   346: aload 33
    //   348: invokevirtual 519	java/util/regex/Matcher:group	()Ljava/lang/String;
    //   351: astore 35
    //   353: aload 35
    //   355: iconst_1
    //   356: iconst_m1
    //   357: aload 35
    //   359: invokevirtual 521	java/lang/String:length	()I
    //   362: iadd
    //   363: invokevirtual 525	java/lang/String:substring	(II)Ljava/lang/String;
    //   366: astore 36
    //   368: aload 32
    //   370: ldc_w 527
    //   373: invokevirtual 531	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   376: pop
    //   377: ldc_w 533
    //   380: invokestatic 457	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   383: aload 32
    //   385: invokevirtual 501	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   388: astore 38
    //   390: aload 38
    //   392: invokevirtual 506	java/util/regex/Matcher:find	()Z
    //   395: pop
    //   396: aload 38
    //   398: invokevirtual 519	java/util/regex/Matcher:group	()Ljava/lang/String;
    //   401: astore 40
    //   403: aload_1
    //   404: aload 36
    //   406: aload 40
    //   408: iconst_1
    //   409: aload 40
    //   411: ldc_w 535
    //   414: invokevirtual 538	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   417: iadd
    //   418: invokevirtual 540	java/lang/String:substring	(I)Ljava/lang/String;
    //   421: invokestatic 543	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   424: aload 7
    //   426: invokestatic 545	com/tencent/feedback/anr/a:a	(Ljava/io/BufferedReader;)Ljava/lang/String;
    //   429: aload 7
    //   431: invokestatic 547	com/tencent/feedback/anr/a:b	(Ljava/io/BufferedReader;)Ljava/lang/String;
    //   434: invokeinterface 550 5 0
    //   439: pop
    //   440: goto -157 -> 283
    //   443: astore 10
    //   445: aload 7
    //   447: astore 11
    //   449: aload 10
    //   451: invokevirtual 304	java/lang/Exception:printStackTrace	()V
    //   454: aload 11
    //   456: ifnull -448 -> 8
    //   459: aload 11
    //   461: invokevirtual 389	java/io/BufferedReader:close	()V
    //   464: return
    //   465: astore 12
    //   467: aload 12
    //   469: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   472: return
    //   473: aload_1
    //   474: aload 29
    //   476: iconst_1
    //   477: aaload
    //   478: invokevirtual 474	java/lang/Object:toString	()Ljava/lang/String;
    //   481: ldc_w 476
    //   484: invokevirtual 480	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   487: iconst_2
    //   488: aaload
    //   489: invokestatic 486	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   492: invokeinterface 553 3 0
    //   497: istore 30
    //   499: iload 30
    //   501: ifne -402 -> 99
    //   504: aload 7
    //   506: invokevirtual 389	java/io/BufferedReader:close	()V
    //   509: return
    //   510: astore 31
    //   512: aload 31
    //   514: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   517: return
    //   518: aload 7
    //   520: invokevirtual 389	java/io/BufferedReader:close	()V
    //   523: return
    //   524: astore 44
    //   526: aload 44
    //   528: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   531: return
    //   532: astore 8
    //   534: aconst_null
    //   535: astore 7
    //   537: aload 7
    //   539: ifnull +8 -> 547
    //   542: aload 7
    //   544: invokevirtual 389	java/io/BufferedReader:close	()V
    //   547: aload 8
    //   549: athrow
    //   550: astore 9
    //   552: aload 9
    //   554: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   557: goto -10 -> 547
    //   560: astore 8
    //   562: goto -25 -> 537
    //   565: astore 8
    //   567: aload 11
    //   569: astore 7
    //   571: goto -34 -> 537
    //   574: astore 10
    //   576: aconst_null
    //   577: astore 11
    //   579: goto -130 -> 449
    //
    // Exception table:
    //   from	to	target	type
    //   205	210	211	java/io/IOException
    //   269	274	275	java/io/IOException
    //   52	99	443	java/lang/Exception
    //   99	115	443	java/lang/Exception
    //   120	200	443	java/lang/Exception
    //   219	264	443	java/lang/Exception
    //   283	304	443	java/lang/Exception
    //   309	440	443	java/lang/Exception
    //   473	499	443	java/lang/Exception
    //   459	464	465	java/io/IOException
    //   504	509	510	java/io/IOException
    //   518	523	524	java/io/IOException
    //   35	52	532	finally
    //   542	547	550	java/io/IOException
    //   52	99	560	finally
    //   99	115	560	finally
    //   120	200	560	finally
    //   219	264	560	finally
    //   283	304	560	finally
    //   309	440	560	finally
    //   473	499	560	finally
    //   449	454	565	finally
    //   35	52	574	java/lang/Exception
  }

  public static void a(String paramString1, String paramString2, int paramInt)
  {
    com.tencent.feedback.common.e.b("rqdp{  sv sd start} %s", new Object[] { paramString1 });
    if ((paramString2 == null) || (paramString2.trim().length() <= 0))
      return;
    File localFile = new File(paramString1);
    try
    {
      if (!localFile.exists())
      {
        if (localFile.getParentFile() != null)
          localFile.getParentFile().mkdirs();
        localFile.createNewFile();
      }
      localObject1 = null;
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        boolean bool = localFile.length() < paramInt;
        localObject1 = null;
        if (!bool);
        FileOutputStream localFileOutputStream;
        for (localObject1 = new FileOutputStream(localFile, false); ; localObject1 = localFileOutputStream)
        {
          ((FileOutputStream)localObject1).write(paramString2.getBytes("UTF-8"));
          ((FileOutputStream)localObject1).flush();
          ((FileOutputStream)localObject1).close();
          com.tencent.feedback.common.e.b("rqdp{  sv sd end}", new Object[0]);
          return;
          localFileOutputStream = new FileOutputStream(localFile, true);
        }
      }
      catch (Throwable localThrowable2)
      {
        while (true)
        {
          localThrowable2.printStackTrace();
          com.tencent.feedback.common.e.d("rqdp{  err can't write!}", new Object[0]);
          if (localObject1 == null)
            continue;
          ((FileOutputStream)localObject1).close();
          continue;
          localThrowable1 = localThrowable1;
          localThrowable1.printStackTrace();
        }
      }
      finally
      {
        Object localObject1;
        if (localObject1 != null)
          ((FileOutputStream)localObject1).close();
      }
    }
    throw localObject2;
  }

  // ERROR //
  public static boolean a(Context paramContext, com.tencent.feedback.common.b.i parami)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +7 -> 8
    //   4: aload_1
    //   5: ifnonnull +15 -> 20
    //   8: ldc_w 593
    //   11: iconst_0
    //   12: anewarray 4	java/lang/Object
    //   15: invokestatic 57	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   18: iconst_0
    //   19: ireturn
    //   20: new 77	com/tencent/feedback/common/a/c
    //   23: dup
    //   24: aload_0
    //   25: invokespecial 80	com/tencent/feedback/common/a/c:<init>	(Landroid/content/Context;)V
    //   28: astore_2
    //   29: aload_2
    //   30: invokevirtual 84	com/tencent/feedback/common/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   33: astore 8
    //   35: aload 8
    //   37: astore 5
    //   39: aload 5
    //   41: ifnonnull +37 -> 78
    //   44: ldc_w 595
    //   47: iconst_0
    //   48: anewarray 4	java/lang/Object
    //   51: invokestatic 191	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   54: aload 5
    //   56: ifnull +16 -> 72
    //   59: aload 5
    //   61: invokevirtual 92	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   64: ifeq +8 -> 72
    //   67: aload 5
    //   69: invokevirtual 95	android/database/sqlite/SQLiteDatabase:close	()V
    //   72: aload_2
    //   73: invokevirtual 96	com/tencent/feedback/common/a/c:close	()V
    //   76: iconst_0
    //   77: ireturn
    //   78: aconst_null
    //   79: astore 9
    //   81: aload_1
    //   82: ifnonnull +62 -> 144
    //   85: aload 9
    //   87: ifnull +232 -> 319
    //   90: aload 5
    //   92: ldc 112
    //   94: ldc 155
    //   96: aload 9
    //   98: invokevirtual 599	android/database/sqlite/SQLiteDatabase:replace	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   101: lstore 10
    //   103: lload 10
    //   105: lconst_0
    //   106: lcmp
    //   107: ifge +157 -> 264
    //   110: ldc_w 601
    //   113: iconst_0
    //   114: anewarray 4	java/lang/Object
    //   117: invokestatic 57	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   120: aload 5
    //   122: ifnull +16 -> 138
    //   125: aload 5
    //   127: invokevirtual 92	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   130: ifeq +8 -> 138
    //   133: aload 5
    //   135: invokevirtual 95	android/database/sqlite/SQLiteDatabase:close	()V
    //   138: aload_2
    //   139: invokevirtual 96	com/tencent/feedback/common/a/c:close	()V
    //   142: iconst_0
    //   143: ireturn
    //   144: new 603	android/content/ContentValues
    //   147: dup
    //   148: invokespecial 604	android/content/ContentValues:<init>	()V
    //   151: astore 9
    //   153: aload_1
    //   154: invokevirtual 605	com/tencent/feedback/common/b/i:a	()J
    //   157: lconst_0
    //   158: lcmp
    //   159: iflt +17 -> 176
    //   162: aload 9
    //   164: ldc 155
    //   166: aload_1
    //   167: invokevirtual 605	com/tencent/feedback/common/b/i:a	()J
    //   170: invokestatic 608	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   173: invokevirtual 612	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   176: aload 9
    //   178: ldc 104
    //   180: aload_1
    //   181: invokevirtual 139	com/tencent/feedback/common/b/i:b	()I
    //   184: invokestatic 110	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   187: invokevirtual 615	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   190: aload 9
    //   192: ldc 174
    //   194: aload_1
    //   195: invokevirtual 617	com/tencent/feedback/common/b/i:c	()[B
    //   198: invokevirtual 620	android/content/ContentValues:put	(Ljava/lang/String;[B)V
    //   201: goto -116 -> 85
    //   204: astore_3
    //   205: aload_2
    //   206: astore 4
    //   208: iconst_1
    //   209: anewarray 4	java/lang/Object
    //   212: astore 7
    //   214: aload 7
    //   216: iconst_0
    //   217: aload_3
    //   218: invokevirtual 187	java/lang/Throwable:toString	()Ljava/lang/String;
    //   221: aastore
    //   222: ldc_w 622
    //   225: aload 7
    //   227: invokestatic 191	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   230: aload_3
    //   231: invokevirtual 184	java/lang/Throwable:printStackTrace	()V
    //   234: aload 5
    //   236: ifnull +16 -> 252
    //   239: aload 5
    //   241: invokevirtual 92	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   244: ifeq +8 -> 252
    //   247: aload 5
    //   249: invokevirtual 95	android/database/sqlite/SQLiteDatabase:close	()V
    //   252: aload 4
    //   254: ifnull -236 -> 18
    //   257: aload 4
    //   259: invokevirtual 96	com/tencent/feedback/common/a/c:close	()V
    //   262: iconst_0
    //   263: ireturn
    //   264: aload_1
    //   265: lload 10
    //   267: invokevirtual 166	com/tencent/feedback/common/b/i:a	(J)V
    //   270: iconst_1
    //   271: anewarray 4	java/lang/Object
    //   274: astore 12
    //   276: aload 12
    //   278: iconst_0
    //   279: aload_1
    //   280: invokevirtual 139	com/tencent/feedback/common/b/i:b	()I
    //   283: invokestatic 110	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   286: aastore
    //   287: ldc_w 624
    //   290: aload 12
    //   292: invokestatic 626	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   295: aload 5
    //   297: ifnull +16 -> 313
    //   300: aload 5
    //   302: invokevirtual 92	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   305: ifeq +8 -> 313
    //   308: aload 5
    //   310: invokevirtual 95	android/database/sqlite/SQLiteDatabase:close	()V
    //   313: aload_2
    //   314: invokevirtual 96	com/tencent/feedback/common/a/c:close	()V
    //   317: iconst_1
    //   318: ireturn
    //   319: aload 5
    //   321: ifnull +16 -> 337
    //   324: aload 5
    //   326: invokevirtual 92	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   329: ifeq +8 -> 337
    //   332: aload 5
    //   334: invokevirtual 95	android/database/sqlite/SQLiteDatabase:close	()V
    //   337: aload_2
    //   338: invokevirtual 96	com/tencent/feedback/common/a/c:close	()V
    //   341: iconst_0
    //   342: ireturn
    //   343: astore 6
    //   345: aconst_null
    //   346: astore 5
    //   348: aconst_null
    //   349: astore_2
    //   350: aload 5
    //   352: ifnull +16 -> 368
    //   355: aload 5
    //   357: invokevirtual 92	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   360: ifeq +8 -> 368
    //   363: aload 5
    //   365: invokevirtual 95	android/database/sqlite/SQLiteDatabase:close	()V
    //   368: aload_2
    //   369: ifnull +7 -> 376
    //   372: aload_2
    //   373: invokevirtual 96	com/tencent/feedback/common/a/c:close	()V
    //   376: aload 6
    //   378: athrow
    //   379: astore 6
    //   381: aconst_null
    //   382: astore 5
    //   384: goto -34 -> 350
    //   387: astore 6
    //   389: goto -39 -> 350
    //   392: astore 6
    //   394: aload 4
    //   396: astore_2
    //   397: goto -47 -> 350
    //   400: astore_3
    //   401: aconst_null
    //   402: astore 5
    //   404: aconst_null
    //   405: astore 4
    //   407: goto -199 -> 208
    //   410: astore_3
    //   411: aload_2
    //   412: astore 4
    //   414: aconst_null
    //   415: astore 5
    //   417: goto -209 -> 208
    //
    // Exception table:
    //   from	to	target	type
    //   44	54	204	java/lang/Throwable
    //   90	103	204	java/lang/Throwable
    //   110	120	204	java/lang/Throwable
    //   144	176	204	java/lang/Throwable
    //   176	201	204	java/lang/Throwable
    //   264	295	204	java/lang/Throwable
    //   20	29	343	finally
    //   29	35	379	finally
    //   44	54	387	finally
    //   90	103	387	finally
    //   110	120	387	finally
    //   144	176	387	finally
    //   176	201	387	finally
    //   264	295	387	finally
    //   208	234	392	finally
    //   20	29	400	java/lang/Throwable
    //   29	35	410	java/lang/Throwable
  }

  // ERROR //
  public static boolean a(File paramFile1, File paramFile2, int paramInt)
  {
    // Byte code:
    //   0: ldc_w 629
    //   3: iconst_0
    //   4: anewarray 4	java/lang/Object
    //   7: invokestatic 152	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   10: aload_0
    //   11: ifnull +15 -> 26
    //   14: aload_1
    //   15: ifnull +11 -> 26
    //   18: aload_0
    //   19: aload_1
    //   20: invokevirtual 632	java/io/File:equals	(Ljava/lang/Object;)Z
    //   23: ifeq +15 -> 38
    //   26: ldc_w 634
    //   29: iconst_0
    //   30: anewarray 4	java/lang/Object
    //   33: invokestatic 57	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   36: iconst_0
    //   37: ireturn
    //   38: aload_0
    //   39: invokevirtual 438	java/io/File:exists	()Z
    //   42: ifeq +10 -> 52
    //   45: aload_0
    //   46: invokevirtual 637	java/io/File:canRead	()Z
    //   49: ifne +15 -> 64
    //   52: ldc_w 639
    //   55: iconst_0
    //   56: anewarray 4	java/lang/Object
    //   59: invokestatic 57	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   62: iconst_0
    //   63: ireturn
    //   64: aload_1
    //   65: invokevirtual 563	java/io/File:getParentFile	()Ljava/io/File;
    //   68: ifnull +21 -> 89
    //   71: aload_1
    //   72: invokevirtual 563	java/io/File:getParentFile	()Ljava/io/File;
    //   75: invokevirtual 438	java/io/File:exists	()Z
    //   78: ifne +11 -> 89
    //   81: aload_1
    //   82: invokevirtual 563	java/io/File:getParentFile	()Ljava/io/File;
    //   85: invokevirtual 566	java/io/File:mkdirs	()Z
    //   88: pop
    //   89: aload_1
    //   90: invokevirtual 438	java/io/File:exists	()Z
    //   93: ifne +8 -> 101
    //   96: aload_1
    //   97: invokevirtual 569	java/io/File:createNewFile	()Z
    //   100: pop
    //   101: aload_1
    //   102: invokevirtual 438	java/io/File:exists	()Z
    //   105: ifeq -69 -> 36
    //   108: aload_1
    //   109: invokevirtual 637	java/io/File:canRead	()Z
    //   112: ifeq -76 -> 36
    //   115: new 641	java/io/FileInputStream
    //   118: dup
    //   119: aload_0
    //   120: invokespecial 642	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   123: astore 4
    //   125: new 644	java/util/zip/ZipOutputStream
    //   128: dup
    //   129: new 571	java/io/FileOutputStream
    //   132: dup
    //   133: aload_1
    //   134: invokespecial 645	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   137: invokespecial 648	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   140: astore 5
    //   142: aload 5
    //   144: bipush 8
    //   146: invokevirtual 651	java/util/zip/ZipOutputStream:setMethod	(I)V
    //   149: aload 5
    //   151: new 653	java/util/zip/ZipEntry
    //   154: dup
    //   155: aload_0
    //   156: invokevirtual 656	java/io/File:getName	()Ljava/lang/String;
    //   159: invokespecial 657	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   162: invokevirtual 661	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   165: sipush 5000
    //   168: newarray byte
    //   170: astore 13
    //   172: aload 4
    //   174: aload 13
    //   176: invokevirtual 665	java/io/FileInputStream:read	([B)I
    //   179: istore 14
    //   181: iload 14
    //   183: ifle +66 -> 249
    //   186: aload 5
    //   188: aload 13
    //   190: iconst_0
    //   191: iload 14
    //   193: invokevirtual 668	java/util/zip/ZipOutputStream:write	([BII)V
    //   196: goto -24 -> 172
    //   199: astore 9
    //   201: aload 4
    //   203: astore 10
    //   205: aload 9
    //   207: invokevirtual 184	java/lang/Throwable:printStackTrace	()V
    //   210: aload 10
    //   212: ifnull +8 -> 220
    //   215: aload 10
    //   217: invokevirtual 669	java/io/FileInputStream:close	()V
    //   220: aload 5
    //   222: ifnull +8 -> 230
    //   225: aload 5
    //   227: invokevirtual 670	java/util/zip/ZipOutputStream:close	()V
    //   230: ldc_w 672
    //   233: iconst_0
    //   234: anewarray 4	java/lang/Object
    //   237: invokestatic 152	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   240: iconst_0
    //   241: ireturn
    //   242: astore_3
    //   243: aload_3
    //   244: invokevirtual 184	java/lang/Throwable:printStackTrace	()V
    //   247: iconst_0
    //   248: ireturn
    //   249: aload 5
    //   251: invokevirtual 673	java/util/zip/ZipOutputStream:flush	()V
    //   254: aload 5
    //   256: invokevirtual 676	java/util/zip/ZipOutputStream:closeEntry	()V
    //   259: aload 4
    //   261: invokevirtual 669	java/io/FileInputStream:close	()V
    //   264: aload 5
    //   266: invokevirtual 670	java/util/zip/ZipOutputStream:close	()V
    //   269: ldc_w 672
    //   272: iconst_0
    //   273: anewarray 4	java/lang/Object
    //   276: invokestatic 152	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   279: iconst_1
    //   280: ireturn
    //   281: astore 15
    //   283: aload 15
    //   285: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   288: goto -24 -> 264
    //   291: astore 16
    //   293: aload 16
    //   295: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   298: goto -29 -> 269
    //   301: astore 12
    //   303: aload 12
    //   305: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   308: goto -88 -> 220
    //   311: astore 11
    //   313: aload 11
    //   315: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   318: goto -88 -> 230
    //   321: astore 6
    //   323: aconst_null
    //   324: astore 5
    //   326: aconst_null
    //   327: astore 4
    //   329: aload 4
    //   331: ifnull +8 -> 339
    //   334: aload 4
    //   336: invokevirtual 669	java/io/FileInputStream:close	()V
    //   339: aload 5
    //   341: ifnull +8 -> 349
    //   344: aload 5
    //   346: invokevirtual 670	java/util/zip/ZipOutputStream:close	()V
    //   349: ldc_w 672
    //   352: iconst_0
    //   353: anewarray 4	java/lang/Object
    //   356: invokestatic 152	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   359: aload 6
    //   361: athrow
    //   362: astore 8
    //   364: aload 8
    //   366: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   369: goto -30 -> 339
    //   372: astore 7
    //   374: aload 7
    //   376: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   379: goto -30 -> 349
    //   382: astore 6
    //   384: aconst_null
    //   385: astore 5
    //   387: goto -58 -> 329
    //   390: astore 6
    //   392: goto -63 -> 329
    //   395: astore 6
    //   397: aload 10
    //   399: astore 4
    //   401: goto -72 -> 329
    //   404: astore 9
    //   406: aconst_null
    //   407: astore 5
    //   409: aconst_null
    //   410: astore 10
    //   412: goto -207 -> 205
    //   415: astore 9
    //   417: aload 4
    //   419: astore 10
    //   421: aconst_null
    //   422: astore 5
    //   424: goto -219 -> 205
    //
    // Exception table:
    //   from	to	target	type
    //   142	172	199	java/lang/Throwable
    //   172	181	199	java/lang/Throwable
    //   186	196	199	java/lang/Throwable
    //   249	259	199	java/lang/Throwable
    //   64	89	242	java/lang/Throwable
    //   89	101	242	java/lang/Throwable
    //   259	264	281	java/io/IOException
    //   264	269	291	java/io/IOException
    //   215	220	301	java/io/IOException
    //   225	230	311	java/io/IOException
    //   115	125	321	finally
    //   334	339	362	java/io/IOException
    //   344	349	372	java/io/IOException
    //   125	142	382	finally
    //   142	172	390	finally
    //   172	181	390	finally
    //   186	196	390	finally
    //   249	259	390	finally
    //   205	210	395	finally
    //   115	125	404	java/lang/Throwable
    //   125	142	415	java/lang/Throwable
  }

  // ERROR //
  public static boolean a(String[] paramArrayOfString, com.tencent.feedback.eup.a<String> parama)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +8 -> 9
    //   4: aload_0
    //   5: arraylength
    //   6: ifgt +5 -> 11
    //   9: iconst_0
    //   10: ireturn
    //   11: aconst_null
    //   12: astore_2
    //   13: invokestatic 372	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   16: aload_0
    //   17: invokevirtual 376	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   20: astore_2
    //   21: aload_1
    //   22: ifnull +132 -> 154
    //   25: new 334	java/io/BufferedReader
    //   28: dup
    //   29: new 378	java/io/InputStreamReader
    //   32: dup
    //   33: aload_2
    //   34: invokevirtual 384	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   37: invokespecial 385	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   40: invokespecial 388	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   43: astore 11
    //   45: aload 11
    //   47: invokevirtual 337	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   50: astore 12
    //   52: aload 12
    //   54: ifnull +68 -> 122
    //   57: aload_1
    //   58: aload 12
    //   60: invokevirtual 680	com/tencent/feedback/eup/a:add	(Ljava/lang/Object;)Z
    //   63: pop
    //   64: goto -19 -> 45
    //   67: astore 7
    //   69: aload 7
    //   71: invokevirtual 184	java/lang/Throwable:printStackTrace	()V
    //   74: aload 7
    //   76: invokevirtual 328	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   79: iconst_0
    //   80: anewarray 4	java/lang/Object
    //   83: invokestatic 191	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   86: aload_2
    //   87: ifnull -78 -> 9
    //   90: aload_2
    //   91: invokevirtual 684	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   94: invokevirtual 687	java/io/OutputStream:close	()V
    //   97: aload_2
    //   98: invokevirtual 384	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   101: invokevirtual 690	java/io/InputStream:close	()V
    //   104: aload_2
    //   105: invokevirtual 392	java/lang/Process:getErrorStream	()Ljava/io/InputStream;
    //   108: invokevirtual 690	java/io/InputStream:close	()V
    //   111: iconst_0
    //   112: ireturn
    //   113: astore 10
    //   115: aload 10
    //   117: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   120: iconst_0
    //   121: ireturn
    //   122: aload 11
    //   124: invokevirtual 389	java/io/BufferedReader:close	()V
    //   127: aload_2
    //   128: ifnull +24 -> 152
    //   131: aload_2
    //   132: invokevirtual 684	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   135: invokevirtual 687	java/io/OutputStream:close	()V
    //   138: aload_2
    //   139: invokevirtual 384	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   142: invokevirtual 690	java/io/InputStream:close	()V
    //   145: aload_2
    //   146: invokevirtual 392	java/lang/Process:getErrorStream	()Ljava/io/InputStream;
    //   149: invokevirtual 690	java/io/InputStream:close	()V
    //   152: iconst_1
    //   153: ireturn
    //   154: aload_2
    //   155: invokevirtual 693	java/lang/Process:waitFor	()I
    //   158: pop
    //   159: goto -32 -> 127
    //   162: astore_3
    //   163: aload_2
    //   164: ifnull +24 -> 188
    //   167: aload_2
    //   168: invokevirtual 684	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   171: invokevirtual 687	java/io/OutputStream:close	()V
    //   174: aload_2
    //   175: invokevirtual 384	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   178: invokevirtual 690	java/io/InputStream:close	()V
    //   181: aload_2
    //   182: invokevirtual 392	java/lang/Process:getErrorStream	()Ljava/io/InputStream;
    //   185: invokevirtual 690	java/io/InputStream:close	()V
    //   188: aload_3
    //   189: athrow
    //   190: astore 14
    //   192: aload 14
    //   194: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   197: goto -59 -> 138
    //   200: astore 15
    //   202: aload 15
    //   204: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   207: goto -62 -> 145
    //   210: astore 16
    //   212: aload 16
    //   214: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   217: goto -65 -> 152
    //   220: astore 8
    //   222: aload 8
    //   224: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   227: goto -130 -> 97
    //   230: astore 9
    //   232: aload 9
    //   234: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   237: goto -133 -> 104
    //   240: astore 4
    //   242: aload 4
    //   244: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   247: goto -73 -> 174
    //   250: astore 5
    //   252: aload 5
    //   254: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   257: goto -76 -> 181
    //   260: astore 6
    //   262: aload 6
    //   264: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   267: goto -79 -> 188
    //
    // Exception table:
    //   from	to	target	type
    //   13	21	67	java/lang/Throwable
    //   25	45	67	java/lang/Throwable
    //   45	52	67	java/lang/Throwable
    //   57	64	67	java/lang/Throwable
    //   122	127	67	java/lang/Throwable
    //   154	159	67	java/lang/Throwable
    //   104	111	113	java/io/IOException
    //   13	21	162	finally
    //   25	45	162	finally
    //   45	52	162	finally
    //   57	64	162	finally
    //   69	86	162	finally
    //   122	127	162	finally
    //   154	159	162	finally
    //   131	138	190	java/io/IOException
    //   138	145	200	java/io/IOException
    //   145	152	210	java/io/IOException
    //   90	97	220	java/io/IOException
    //   97	104	230	java/io/IOException
    //   167	174	240	java/io/IOException
    //   174	181	250	java/io/IOException
    //   181	188	260	java/io/IOException
  }

  // ERROR //
  public static byte[] a(Object paramObject)
  {
    // Byte code:
    //   0: ldc_w 695
    //   3: iconst_0
    //   4: anewarray 4	java/lang/Object
    //   7: invokestatic 152	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   10: aload_0
    //   11: ifnull +13 -> 24
    //   14: ldc_w 697
    //   17: aload_0
    //   18: invokevirtual 700	java/lang/Class:isInstance	(Ljava/lang/Object;)Z
    //   21: ifne +15 -> 36
    //   24: ldc_w 702
    //   27: iconst_0
    //   28: anewarray 4	java/lang/Object
    //   31: invokestatic 57	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   34: aconst_null
    //   35: areturn
    //   36: new 704	java/io/ByteArrayOutputStream
    //   39: dup
    //   40: invokespecial 705	java/io/ByteArrayOutputStream:<init>	()V
    //   43: astore_1
    //   44: new 707	java/io/ObjectOutputStream
    //   47: dup
    //   48: aload_1
    //   49: invokespecial 708	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   52: astore_2
    //   53: aload_2
    //   54: aload_0
    //   55: invokevirtual 712	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   58: aload_2
    //   59: invokevirtual 713	java/io/ObjectOutputStream:flush	()V
    //   62: aload_1
    //   63: invokevirtual 716	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   66: astore 9
    //   68: aload_2
    //   69: invokevirtual 717	java/io/ObjectOutputStream:close	()V
    //   72: aload_1
    //   73: invokevirtual 718	java/io/ByteArrayOutputStream:close	()V
    //   76: aload 9
    //   78: areturn
    //   79: astore 11
    //   81: aload 11
    //   83: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   86: aload 9
    //   88: areturn
    //   89: astore 10
    //   91: aload 10
    //   93: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   96: goto -24 -> 72
    //   99: astore_3
    //   100: aconst_null
    //   101: astore_2
    //   102: aload_3
    //   103: invokevirtual 184	java/lang/Throwable:printStackTrace	()V
    //   106: aload_3
    //   107: invokevirtual 328	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   110: iconst_0
    //   111: anewarray 4	java/lang/Object
    //   114: invokestatic 191	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   117: aload_2
    //   118: ifnull +7 -> 125
    //   121: aload_2
    //   122: invokevirtual 717	java/io/ObjectOutputStream:close	()V
    //   125: aload_1
    //   126: invokevirtual 718	java/io/ByteArrayOutputStream:close	()V
    //   129: aconst_null
    //   130: areturn
    //   131: astore 7
    //   133: aload 7
    //   135: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   138: aconst_null
    //   139: areturn
    //   140: astore 8
    //   142: aload 8
    //   144: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   147: goto -22 -> 125
    //   150: astore 12
    //   152: aconst_null
    //   153: astore_2
    //   154: aload 12
    //   156: astore 4
    //   158: aload_2
    //   159: ifnull +7 -> 166
    //   162: aload_2
    //   163: invokevirtual 717	java/io/ObjectOutputStream:close	()V
    //   166: aload_1
    //   167: invokevirtual 718	java/io/ByteArrayOutputStream:close	()V
    //   170: aload 4
    //   172: athrow
    //   173: astore 6
    //   175: aload 6
    //   177: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   180: goto -14 -> 166
    //   183: astore 5
    //   185: aload 5
    //   187: invokevirtual 325	java/io/IOException:printStackTrace	()V
    //   190: goto -20 -> 170
    //   193: astore 4
    //   195: goto -37 -> 158
    //   198: astore_3
    //   199: goto -97 -> 102
    //
    // Exception table:
    //   from	to	target	type
    //   72	76	79	java/io/IOException
    //   68	72	89	java/io/IOException
    //   44	53	99	java/lang/Throwable
    //   125	129	131	java/io/IOException
    //   121	125	140	java/io/IOException
    //   44	53	150	finally
    //   162	166	173	java/io/IOException
    //   166	170	183	java/io/IOException
    //   53	68	193	finally
    //   102	117	193	finally
    //   53	68	198	java/lang/Throwable
  }

  public static byte[] a(String paramString, int paramInt)
  {
    com.tencent.feedback.common.e.a("rqdp{  LogcatManager.getLogDatas() start + count:}" + paramInt, new Object[0]);
    ArrayList localArrayList = new ArrayList();
    localArrayList.clear();
    localArrayList.add("logcat");
    localArrayList.add("-d");
    localArrayList.add("-v");
    localArrayList.add("time");
    if ((paramString != null) && (paramString.length() > 0))
    {
      localArrayList.add("-s");
      localArrayList.add(paramString);
    }
    String[] arrayOfString = new String[localArrayList.size()];
    localArrayList.toArray(arrayOfString);
    com.tencent.feedback.eup.a locala = new com.tencent.feedback.eup.a(paramInt);
    a(arrayOfString, locala);
    if (locala.size() <= 0)
      return null;
    com.tencent.feedback.common.e.a("rqdp{  get log success in list size:}" + locala.size(), new Object[0]);
    try
    {
      localStringBuffer = new StringBuffer();
      Iterator localIterator = locala.iterator();
      while (localIterator.hasNext())
        localStringBuffer.append((String)localIterator.next() + "\n");
    }
    catch (Throwable localThrowable)
    {
      StringBuffer localStringBuffer;
      localThrowable.printStackTrace();
      com.tencent.feedback.common.e.d("rqdp{  change to byte[] failed}", new Object[0]);
      return null;
      locala.clear();
      byte[] arrayOfByte = localStringBuffer.toString().getBytes("utf-8");
      localStringBuffer.setLength(0);
      return arrayOfByte;
    }
    finally
    {
      com.tencent.feedback.common.e.a("rqdp{  LogcatManager.getLogDatas() end}", new Object[0]);
    }
    throw localObject;
  }

  // ERROR //
  public static byte[] a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +10 -> 11
    //   4: aload_0
    //   5: invokevirtual 521	java/lang/String:length	()I
    //   8: ifne +5 -> 13
    //   11: aconst_null
    //   12: areturn
    //   13: new 433	java/io/File
    //   16: dup
    //   17: aload_0
    //   18: invokespecial 435	java/io/File:<init>	(Ljava/lang/String;)V
    //   21: astore_2
    //   22: aload_2
    //   23: invokevirtual 438	java/io/File:exists	()Z
    //   26: ifeq -15 -> 11
    //   29: aload_2
    //   30: invokevirtual 637	java/io/File:canRead	()Z
    //   33: ifeq -22 -> 11
    //   36: new 641	java/io/FileInputStream
    //   39: dup
    //   40: aload_2
    //   41: invokespecial 642	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   44: astore_3
    //   45: aload_1
    //   46: ifnull +177 -> 223
    //   49: aload_1
    //   50: invokevirtual 521	java/lang/String:length	()I
    //   53: ifne +65 -> 118
    //   56: goto +167 -> 223
    //   59: aload_1
    //   60: invokevirtual 767	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   63: invokestatic 773	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   66: astore 10
    //   68: sipush 4096
    //   71: newarray byte
    //   73: astore 11
    //   75: aload_3
    //   76: aload 11
    //   78: invokevirtual 665	java/io/FileInputStream:read	([B)I
    //   81: istore 12
    //   83: iload 12
    //   85: iconst_m1
    //   86: if_icmpeq +59 -> 145
    //   89: aload 10
    //   91: aload 11
    //   93: iconst_0
    //   94: iload 12
    //   96: invokevirtual 776	java/security/MessageDigest:update	([BII)V
    //   99: goto -24 -> 75
    //   102: astore 8
    //   104: aload_3
    //   105: ifnull -94 -> 11
    //   108: aload_3
    //   109: invokevirtual 669	java/io/FileInputStream:close	()V
    //   112: aconst_null
    //   113: areturn
    //   114: astore 9
    //   116: aconst_null
    //   117: areturn
    //   118: aload_1
    //   119: ldc_w 778
    //   122: invokevirtual 782	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   125: ifne -66 -> 59
    //   128: aload_1
    //   129: ldc_w 784
    //   132: invokevirtual 782	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   135: ifne -76 -> 59
    //   138: ldc_w 778
    //   141: astore_1
    //   142: goto -83 -> 59
    //   145: aload 10
    //   147: invokevirtual 787	java/security/MessageDigest:digest	()[B
    //   150: astore 13
    //   152: aload_3
    //   153: invokevirtual 669	java/io/FileInputStream:close	()V
    //   156: aload 13
    //   158: areturn
    //   159: astore 14
    //   161: aload 13
    //   163: areturn
    //   164: astore 17
    //   166: aconst_null
    //   167: astore_3
    //   168: aload_3
    //   169: ifnull -158 -> 11
    //   172: aload_3
    //   173: invokevirtual 669	java/io/FileInputStream:close	()V
    //   176: aconst_null
    //   177: areturn
    //   178: astore 5
    //   180: aconst_null
    //   181: areturn
    //   182: astore 16
    //   184: aconst_null
    //   185: astore_3
    //   186: aload 16
    //   188: astore 6
    //   190: aload_3
    //   191: ifnull +7 -> 198
    //   194: aload_3
    //   195: invokevirtual 669	java/io/FileInputStream:close	()V
    //   198: aload 6
    //   200: athrow
    //   201: astore 7
    //   203: goto -5 -> 198
    //   206: astore 6
    //   208: goto -18 -> 190
    //   211: astore 4
    //   213: goto -45 -> 168
    //   216: astore 15
    //   218: aconst_null
    //   219: astore_3
    //   220: goto -116 -> 104
    //   223: ldc_w 778
    //   226: astore_1
    //   227: goto -168 -> 59
    //
    // Exception table:
    //   from	to	target	type
    //   49	56	102	java/io/IOException
    //   59	75	102	java/io/IOException
    //   75	83	102	java/io/IOException
    //   89	99	102	java/io/IOException
    //   118	138	102	java/io/IOException
    //   145	152	102	java/io/IOException
    //   108	112	114	java/io/IOException
    //   152	156	159	java/io/IOException
    //   36	45	164	java/security/NoSuchAlgorithmException
    //   172	176	178	java/io/IOException
    //   36	45	182	finally
    //   194	198	201	java/io/IOException
    //   49	56	206	finally
    //   59	75	206	finally
    //   75	83	206	finally
    //   89	99	206	finally
    //   118	138	206	finally
    //   145	152	206	finally
    //   49	56	211	java/security/NoSuchAlgorithmException
    //   59	75	211	java/security/NoSuchAlgorithmException
    //   75	83	211	java/security/NoSuchAlgorithmException
    //   89	99	211	java/security/NoSuchAlgorithmException
    //   118	138	211	java/security/NoSuchAlgorithmException
    //   145	152	211	java/security/NoSuchAlgorithmException
    //   36	45	216	java/io/IOException
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    if ((paramArrayOfByte == null) || (paramInt == -1))
      return paramArrayOfByte;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Integer.valueOf(paramInt);
    arrayOfObject1[1] = Integer.valueOf(paramArrayOfByte.length);
    com.tencent.feedback.common.e.b("rqdp{  zp:} %s rqdp{  len:} %s", arrayOfObject1);
    try
    {
      mqq.a.a.a.b localb = mqq.a.a.a.a.a(paramInt);
      if (localb == null)
        return null;
      byte[] arrayOfByte = localb.a(paramArrayOfByte);
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = localThrowable.toString();
      com.tencent.feedback.common.e.d("rqdp{  err zp :} %s", arrayOfObject2);
    }
    return null;
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, String paramString)
  {
    if (paramArrayOfByte == null)
      return null;
    try
    {
      byte[] arrayOfByte = a(a(paramArrayOfByte, paramInt1), paramInt2, paramString);
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt, String paramString)
  {
    if ((paramArrayOfByte == null) || (paramInt == -1))
      return paramArrayOfByte;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Integer.valueOf(paramArrayOfByte.length);
    arrayOfObject1[1] = Integer.valueOf(paramInt);
    com.tencent.feedback.common.e.b("rqdp{  enD:} %d %d", arrayOfObject1);
    if (paramInt == 1);
    try
    {
      localObject = new mqq.a.a.b.b();
      break label131;
      if (paramInt == 3)
        localObject = new mqq.a.a.b.a();
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = localThrowable.toString();
      com.tencent.feedback.common.e.d("rqdp{  err enD: }%s", arrayOfObject2);
      return null;
    }
    Object localObject = null;
    label131: 
    while (localObject != null)
    {
      ((mqq.a.a.b.c)localObject).a(paramString);
      byte[] arrayOfByte = ((mqq.a.a.b.c)localObject).b(paramArrayOfByte);
      return arrayOfByte;
    }
    return (B)null;
  }

  public static f[] a(Context paramContext, String paramString)
  {
    monitorenter;
    if ((paramContext == null) || (paramString == null));
    while (true)
    {
      ArrayList localArrayList;
      try
      {
        com.tencent.feedback.common.e.c("rqdp{  args error}", new Object[0]);
        arrayOff = null;
        return arrayOff;
        List localList = com.tencent.feedback.common.a.a.a(paramContext, new int[] { 9 }, -1, -1, 10000L, -1, paramString, -1, -1, -1, -1, -1L, 9223372036854775807L, -1);
        if ((localList == null) || (localList.size() <= 0))
          break label217;
        localArrayList = new ArrayList(localList.size());
        Iterator localIterator = localList.iterator();
        if (localIterator.hasNext())
        {
          com.tencent.feedback.common.a.a locala = (com.tencent.feedback.common.a.a)localIterator.next();
          Object localObject2 = a(locala.b());
          if ((localObject2 == null) || (!f.class.isInstance(localObject2)))
            continue;
          f localf = (f)f.class.cast(localObject2);
          localf.b(locala.a());
          localArrayList.add(localf);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      if (localArrayList.size() > 0)
      {
        arrayOff = (f[])localArrayList.toArray(new f[0]);
        continue;
      }
      f[] arrayOff = null;
      continue;
      label217: arrayOff = null;
    }
  }

  public static Long[] a(LinkedHashMap<Long, Long> paramLinkedHashMap, long paramLong)
  {
    if ((paramLinkedHashMap == null) || (paramLinkedHashMap.size() <= 0) || (paramLong <= 0L))
      return null;
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramLinkedHashMap.keySet().iterator();
    long l1 = 0L;
    long l4;
    if ((localIterator.hasNext()) && (l1 < paramLong))
    {
      long l2 = ((Long)localIterator.next()).longValue();
      long l3 = ((Long)paramLinkedHashMap.get(Long.valueOf(l2))).longValue();
      if (l1 + l3 > paramLong)
        break label145;
      localArrayList.add(Long.valueOf(l2));
      l4 = l1 + l3;
    }
    while (true)
    {
      l1 = l4;
      break;
      if (localArrayList.size() > 0)
        return (Long[])localArrayList.toArray(new Long[1]);
      return null;
      label145: l4 = l1;
    }
  }

  protected static Object[] a(BufferedReader paramBufferedReader, Pattern[] paramArrayOfPattern)
    throws IOException
  {
    if ((paramBufferedReader == null) || (paramArrayOfPattern == null))
      return null;
    while (true)
    {
      String str = paramBufferedReader.readLine();
      if (str == null)
        break;
      int i = paramArrayOfPattern.length;
      for (int j = 0; j < i; j++)
      {
        Pattern localPattern = paramArrayOfPattern[j];
        if (localPattern.matcher(str).matches())
          return new Object[] { localPattern, str };
      }
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
    //   6: ldc_w 856
    //   9: iconst_0
    //   10: anewarray 4	java/lang/Object
    //   13: invokestatic 57	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   16: iconst_0
    //   17: ireturn
    //   18: new 77	com/tencent/feedback/common/a/c
    //   21: dup
    //   22: aload_0
    //   23: invokespecial 80	com/tencent/feedback/common/a/c:<init>	(Landroid/content/Context;)V
    //   26: astore_3
    //   27: aload_3
    //   28: invokevirtual 84	com/tencent/feedback/common/a/c:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   31: astore_2
    //   32: aload_2
    //   33: ifnonnull +34 -> 67
    //   36: ldc_w 595
    //   39: iconst_0
    //   40: anewarray 4	java/lang/Object
    //   43: invokestatic 191	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   46: aload_2
    //   47: ifnull +14 -> 61
    //   50: aload_2
    //   51: invokevirtual 92	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   54: ifeq +7 -> 61
    //   57: aload_2
    //   58: invokevirtual 95	android/database/sqlite/SQLiteDatabase:close	()V
    //   61: aload_3
    //   62: invokevirtual 96	com/tencent/feedback/common/a/c:close	()V
    //   65: iconst_0
    //   66: ireturn
    //   67: iconst_2
    //   68: anewarray 4	java/lang/Object
    //   71: astore 7
    //   73: aload 7
    //   75: iconst_0
    //   76: ldc 104
    //   78: aastore
    //   79: aload 7
    //   81: iconst_1
    //   82: sipush 302
    //   85: invokestatic 110	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   88: aastore
    //   89: aload_2
    //   90: ldc 112
    //   92: ldc_w 858
    //   95: aload 7
    //   97: invokestatic 861	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   100: aconst_null
    //   101: invokevirtual 865	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   104: istore 8
    //   106: iconst_2
    //   107: anewarray 4	java/lang/Object
    //   110: astore 9
    //   112: aload 9
    //   114: iconst_0
    //   115: sipush 302
    //   118: invokestatic 110	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   121: aastore
    //   122: aload 9
    //   124: iconst_1
    //   125: iload 8
    //   127: invokestatic 110	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   130: aastore
    //   131: ldc_w 867
    //   134: aload 9
    //   136: invokestatic 144	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   139: aload_2
    //   140: ifnull +14 -> 154
    //   143: aload_2
    //   144: invokevirtual 92	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   147: ifeq +7 -> 154
    //   150: aload_2
    //   151: invokevirtual 95	android/database/sqlite/SQLiteDatabase:close	()V
    //   154: aload_3
    //   155: invokevirtual 96	com/tencent/feedback/common/a/c:close	()V
    //   158: iload 8
    //   160: ireturn
    //   161: astore 4
    //   163: aconst_null
    //   164: astore_3
    //   165: aload 4
    //   167: invokevirtual 184	java/lang/Throwable:printStackTrace	()V
    //   170: iconst_1
    //   171: anewarray 4	java/lang/Object
    //   174: astore 6
    //   176: aload 6
    //   178: iconst_0
    //   179: aload 4
    //   181: invokevirtual 187	java/lang/Throwable:toString	()Ljava/lang/String;
    //   184: aastore
    //   185: ldc_w 869
    //   188: aload 6
    //   190: invokestatic 191	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   193: aload_2
    //   194: ifnull +14 -> 208
    //   197: aload_2
    //   198: invokevirtual 92	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   201: ifeq +7 -> 208
    //   204: aload_2
    //   205: invokevirtual 95	android/database/sqlite/SQLiteDatabase:close	()V
    //   208: aload_3
    //   209: ifnull -193 -> 16
    //   212: aload_3
    //   213: invokevirtual 96	com/tencent/feedback/common/a/c:close	()V
    //   216: iconst_0
    //   217: ireturn
    //   218: astore 5
    //   220: aconst_null
    //   221: astore_3
    //   222: aload_2
    //   223: ifnull +14 -> 237
    //   226: aload_2
    //   227: invokevirtual 92	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   230: ifeq +7 -> 237
    //   233: aload_2
    //   234: invokevirtual 95	android/database/sqlite/SQLiteDatabase:close	()V
    //   237: aload_3
    //   238: ifnull +7 -> 245
    //   241: aload_3
    //   242: invokevirtual 96	com/tencent/feedback/common/a/c:close	()V
    //   245: aload 5
    //   247: athrow
    //   248: astore 5
    //   250: goto -28 -> 222
    //   253: astore 4
    //   255: goto -90 -> 165
    //
    // Exception table:
    //   from	to	target	type
    //   18	27	161	java/lang/Throwable
    //   18	27	218	finally
    //   27	32	248	finally
    //   36	46	248	finally
    //   67	139	248	finally
    //   165	193	248	finally
    //   27	32	253	java/lang/Throwable
    //   36	46	253	java/lang/Throwable
    //   67	139	253	java/lang/Throwable
  }

  public static int b(Context paramContext, String paramString)
  {
    int i = 0;
    monitorenter;
    if ((paramContext == null) || (paramString == null));
    try
    {
      com.tencent.feedback.common.e.c("rqdp{  args error}", new Object[0]);
      while (true)
      {
        return i;
        int j = com.tencent.feedback.common.a.a.a(paramContext, new int[] { 9 }, -1L, 9223372036854775807L, paramString);
        i = j;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static int b(Context paramContext, com.tencent.feedback.common.a.e[] paramArrayOfe)
  {
    if (paramContext == null)
      return -1;
    if (paramArrayOfe == null)
      return com.tencent.feedback.common.a.a.a(paramContext, new int[] { 7 }, -1L, 9223372036854775807L, -1, -1);
    ArrayList localArrayList = new ArrayList();
    int i = paramArrayOfe.length;
    for (int j = 0; j < i; j++)
    {
      com.tencent.feedback.common.a.e locale = paramArrayOfe[j];
      if (locale.a() < 0L)
        continue;
      localArrayList.add(Long.valueOf(locale.a()));
    }
    if (localArrayList.size() > 0)
      return com.tencent.feedback.common.a.a.a(paramContext, (Long[])localArrayList.toArray(new Long[0]));
    return 0;
  }

  public static int b(Context paramContext, f[] paramArrayOff)
  {
    int i = 0;
    monitorenter;
    if ((paramContext != null) && (paramArrayOff != null));
    while (true)
    {
      int k;
      try
      {
        if (paramArrayOff.length > 0)
          continue;
        com.tencent.feedback.common.e.c("rqdp{  args error}", new Object[0]);
        return i;
        ArrayList localArrayList = new ArrayList(paramArrayOff.length);
        int j = paramArrayOff.length;
        k = 0;
        if (k >= j)
          continue;
        f localf = paramArrayOff[k];
        if (localf.d() >= 0L)
        {
          localArrayList.add(Long.valueOf(localf.d()));
          break label140;
          int m = localArrayList.size();
          i = 0;
          if (m <= 0)
            continue;
          int n = com.tencent.feedback.common.a.a.a(paramContext, (Long[])localArrayList.toArray(new Long[0]));
          i = n;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      label140: k++;
    }
  }

  protected static String b(BufferedReader paramBufferedReader)
    throws IOException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    while (true)
    {
      String str = paramBufferedReader.readLine();
      if ((str == null) || (str.trim().length() <= 0))
        break;
      localStringBuffer.append(str + "\n");
    }
    return localStringBuffer.toString();
  }

  public static byte[] b(byte[] paramArrayOfByte, int paramInt)
  {
    if ((paramArrayOfByte == null) || (paramInt == -1))
      return paramArrayOfByte;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    arrayOfObject[1] = Integer.valueOf(paramArrayOfByte.length);
    com.tencent.feedback.common.e.b("rqdp{  unzp:} %s rqdp{  len:} %s", arrayOfObject);
    try
    {
      mqq.a.a.a.b localb = mqq.a.a.a.a.a(paramInt);
      if (localb == null)
        return null;
      byte[] arrayOfByte = localb.b(paramArrayOfByte);
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      com.tencent.feedback.common.e.d("rqdp{  err unzp}" + localThrowable.toString(), new Object[0]);
    }
    return null;
  }

  public static byte[] b(byte[] paramArrayOfByte, int paramInt1, int paramInt2, String paramString)
  {
    try
    {
      byte[] arrayOfByte = b(b(paramArrayOfByte, paramInt2, paramString), paramInt1);
      return arrayOfByte;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static byte[] b(byte[] paramArrayOfByte, int paramInt, String paramString)
  {
    if ((paramArrayOfByte == null) || (paramInt == -1))
      return paramArrayOfByte;
    if (paramInt == 1);
    try
    {
      localObject = new mqq.a.a.b.b();
      break label99;
      if (paramInt == 3)
        localObject = new mqq.a.a.b.a();
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localThrowable.toString();
      com.tencent.feedback.common.e.d("rqdp{  err unD:} %s", arrayOfObject);
      return null;
    }
    Object localObject = null;
    label99: 
    while (localObject != null)
    {
      ((mqq.a.a.b.c)localObject).a(paramString);
      byte[] arrayOfByte = ((mqq.a.a.b.c)localObject).a(paramArrayOfByte);
      return arrayOfByte;
    }
    return (B)null;
  }

  public static String d(String paramString)
  {
    int i = 0;
    if ((paramString == null) || (paramString.length() == 0))
      return null;
    byte[] arrayOfByte;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes());
      arrayOfByte = localMessageDigest.digest();
      if (arrayOfByte == null)
        return "";
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
      com.tencent.feedback.common.e.d(localNoSuchAlgorithmException.getMessage(), new Object[0]);
      return null;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    while (i < arrayOfByte.length)
    {
      String str = Integer.toHexString(0xFF & arrayOfByte[i]);
      if (str.length() == 1)
        localStringBuffer.append("0");
      localStringBuffer.append(str);
      i++;
    }
    return localStringBuffer.toString().toUpperCase();
  }

  public static long f()
  {
    try
    {
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
      long l = localSimpleDateFormat.parse(localSimpleDateFormat.format(new Date())).getTime();
      return l;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return -1L;
  }

  public static String g()
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

  public static String h()
  {
    try
    {
      String str = UUID.randomUUID().toString();
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }

  public final String a()
  {
    return this.a;
  }

  public final void a(int paramInt)
  {
    this.e = paramInt;
  }

  public final void a(long paramLong)
  {
    this.b = paramLong;
  }

  public final void a(String paramString)
  {
    this.a = paramString;
  }

  public final long b()
  {
    return this.b;
  }

  public final void b(String paramString)
  {
    this.c = paramString;
  }

  public final int c()
  {
    return this.e;
  }

  public final void c(String paramString)
  {
    this.d = paramString;
  }

  public final String d()
  {
    return this.c;
  }

  public final String e()
  {
    return this.d;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.anr.a
 * JD-Core Version:    0.6.0
 */