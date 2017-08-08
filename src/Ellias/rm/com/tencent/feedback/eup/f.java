package com.tencent.feedback.eup;

import android.content.Context;
import com.tencent.feedback.common.e;
import com.tencent.feedback.upload.AbstractUploadDatas;
import common.RequestPackage;
import exceptionupload.AppInfo;
import exceptionupload.Attachment;
import exceptionupload.ExceptionUpload;
import exceptionupload.ExceptionUploadPackage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.List<Lcom.tencent.feedback.eup.d;>;

public final class f extends AbstractUploadDatas
{
  private static f d = null;
  private byte[] e = null;
  private RequestPackage f = null;
  private List<d> g = null;

  private f(Context paramContext)
  {
    super(paramContext, 3, 206);
  }

  public static f a(Context paramContext)
  {
    monitorenter;
    if (paramContext != null);
    try
    {
      if (d == null)
        d = new f(paramContext);
      f localf = d;
      return localf;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  private static Attachment a(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +7 -> 8
    //   4: aload_0
    //   5: ifnonnull +14 -> 19
    //   8: ldc 39
    //   10: iconst_0
    //   11: anewarray 41	java/lang/Object
    //   14: invokestatic 47	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   17: aconst_null
    //   18: areturn
    //   19: ldc 49
    //   21: iconst_1
    //   22: anewarray 41	java/lang/Object
    //   25: dup
    //   26: iconst_0
    //   27: aload_1
    //   28: aastore
    //   29: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   32: new 53	java/io/File
    //   35: dup
    //   36: aload_1
    //   37: invokespecial 56	java/io/File:<init>	(Ljava/lang/String;)V
    //   40: astore_2
    //   41: new 53	java/io/File
    //   44: dup
    //   45: aload_0
    //   46: invokevirtual 62	android/content/Context:getCacheDir	()Ljava/io/File;
    //   49: ldc 64
    //   51: invokespecial 67	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   54: astore_3
    //   55: aload_2
    //   56: aload_3
    //   57: sipush 5000
    //   60: invokestatic 72	com/tencent/feedback/anr/a:a	(Ljava/io/File;Ljava/io/File;I)Z
    //   63: ifne +14 -> 77
    //   66: ldc 74
    //   68: iconst_0
    //   69: anewarray 41	java/lang/Object
    //   72: invokestatic 47	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   75: aconst_null
    //   76: areturn
    //   77: new 76	java/io/ByteArrayOutputStream
    //   80: dup
    //   81: invokespecial 78	java/io/ByteArrayOutputStream:<init>	()V
    //   84: astore 4
    //   86: new 80	java/io/FileInputStream
    //   89: dup
    //   90: aload_3
    //   91: invokespecial 83	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   94: astore 5
    //   96: sipush 1000
    //   99: newarray byte
    //   101: astore 12
    //   103: aload 5
    //   105: aload 12
    //   107: invokevirtual 87	java/io/FileInputStream:read	([B)I
    //   110: istore 13
    //   112: iload 13
    //   114: ifle +61 -> 175
    //   117: aload 4
    //   119: aload 12
    //   121: iconst_0
    //   122: iload 13
    //   124: invokevirtual 91	java/io/ByteArrayOutputStream:write	([BII)V
    //   127: aload 4
    //   129: invokevirtual 94	java/io/ByteArrayOutputStream:flush	()V
    //   132: goto -29 -> 103
    //   135: astore 9
    //   137: aload 9
    //   139: invokevirtual 97	java/lang/Throwable:printStackTrace	()V
    //   142: aload 5
    //   144: ifnull +8 -> 152
    //   147: aload 5
    //   149: invokevirtual 100	java/io/FileInputStream:close	()V
    //   152: aload_3
    //   153: invokevirtual 104	java/io/File:exists	()Z
    //   156: ifeq -139 -> 17
    //   159: ldc 106
    //   161: iconst_0
    //   162: anewarray 41	java/lang/Object
    //   165: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   168: aload_3
    //   169: invokevirtual 109	java/io/File:delete	()Z
    //   172: pop
    //   173: aconst_null
    //   174: areturn
    //   175: aload 4
    //   177: invokevirtual 113	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   180: astore 14
    //   182: iconst_1
    //   183: anewarray 41	java/lang/Object
    //   186: astore 15
    //   188: aload 15
    //   190: iconst_0
    //   191: aload 14
    //   193: arraylength
    //   194: invokestatic 119	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   197: aastore
    //   198: ldc 121
    //   200: aload 15
    //   202: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   205: new 123	exceptionupload/Attachment
    //   208: dup
    //   209: iconst_2
    //   210: aload_3
    //   211: invokevirtual 127	java/io/File:getName	()Ljava/lang/String;
    //   214: aload 14
    //   216: invokespecial 130	exceptionupload/Attachment:<init>	(BLjava/lang/String;[B)V
    //   219: astore 16
    //   221: aload 5
    //   223: invokevirtual 100	java/io/FileInputStream:close	()V
    //   226: aload_3
    //   227: invokevirtual 104	java/io/File:exists	()Z
    //   230: ifeq +17 -> 247
    //   233: ldc 106
    //   235: iconst_0
    //   236: anewarray 41	java/lang/Object
    //   239: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   242: aload_3
    //   243: invokevirtual 109	java/io/File:delete	()Z
    //   246: pop
    //   247: aload 16
    //   249: areturn
    //   250: astore 17
    //   252: aload 17
    //   254: invokevirtual 131	java/io/IOException:printStackTrace	()V
    //   257: goto -31 -> 226
    //   260: astore 11
    //   262: aload 11
    //   264: invokevirtual 131	java/io/IOException:printStackTrace	()V
    //   267: goto -115 -> 152
    //   270: astore 19
    //   272: aconst_null
    //   273: astore 5
    //   275: aload 19
    //   277: astore 6
    //   279: aload 5
    //   281: ifnull +8 -> 289
    //   284: aload 5
    //   286: invokevirtual 100	java/io/FileInputStream:close	()V
    //   289: aload_3
    //   290: invokevirtual 104	java/io/File:exists	()Z
    //   293: ifeq +17 -> 310
    //   296: ldc 106
    //   298: iconst_0
    //   299: anewarray 41	java/lang/Object
    //   302: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   305: aload_3
    //   306: invokevirtual 109	java/io/File:delete	()Z
    //   309: pop
    //   310: aload 6
    //   312: athrow
    //   313: astore 8
    //   315: aload 8
    //   317: invokevirtual 131	java/io/IOException:printStackTrace	()V
    //   320: goto -31 -> 289
    //   323: astore 6
    //   325: goto -46 -> 279
    //   328: astore 9
    //   330: aconst_null
    //   331: astore 5
    //   333: goto -196 -> 137
    //
    // Exception table:
    //   from	to	target	type
    //   96	103	135	java/lang/Throwable
    //   103	112	135	java/lang/Throwable
    //   117	132	135	java/lang/Throwable
    //   175	221	135	java/lang/Throwable
    //   221	226	250	java/io/IOException
    //   147	152	260	java/io/IOException
    //   86	96	270	finally
    //   284	289	313	java/io/IOException
    //   96	103	323	finally
    //   103	112	323	finally
    //   117	132	323	finally
    //   137	142	323	finally
    //   175	221	323	finally
    //   86	96	328	java/lang/Throwable
  }

  private static Attachment a(byte[] paramArrayOfByte, String paramString)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0) || (paramString == null) || (paramString.trim().length() <= 0))
      return null;
    try
    {
      Attachment localAttachment = new Attachment();
      localAttachment.setType(1);
      localAttachment.setFileName(paramString);
      localAttachment.setData(paramArrayOfByte);
      return localAttachment;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }

  // ERROR //
  private ExceptionUpload a(Context paramContext, d paramd)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +14 -> 15
    //   4: ldc 156
    //   6: iconst_0
    //   7: anewarray 41	java/lang/Object
    //   10: invokestatic 47	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   13: aconst_null
    //   14: areturn
    //   15: iconst_3
    //   16: anewarray 41	java/lang/Object
    //   19: astore_3
    //   20: aload_3
    //   21: iconst_0
    //   22: aload_2
    //   23: invokevirtual 160	com/tencent/feedback/eup/d:f	()Ljava/lang/String;
    //   26: aastore
    //   27: aload_3
    //   28: iconst_1
    //   29: aload_2
    //   30: invokevirtual 162	com/tencent/feedback/eup/d:c	()Z
    //   33: invokestatic 167	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   36: aastore
    //   37: aload_3
    //   38: iconst_2
    //   39: aload_2
    //   40: invokevirtual 169	com/tencent/feedback/eup/d:d	()Z
    //   43: invokestatic 167	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   46: aastore
    //   47: ldc 171
    //   49: aload_3
    //   50: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   53: aload_2
    //   54: invokevirtual 169	com/tencent/feedback/eup/d:d	()Z
    //   57: ifeq +1142 -> 1199
    //   60: aload_2
    //   61: invokevirtual 162	com/tencent/feedback/eup/d:c	()Z
    //   64: ifeq +1127 -> 1191
    //   67: ldc 173
    //   69: astore 5
    //   71: new 175	exceptionupload/ExceptionUpload
    //   74: dup
    //   75: invokespecial 176	exceptionupload/ExceptionUpload:<init>	()V
    //   78: astore 6
    //   80: aload 6
    //   82: aload_2
    //   83: invokevirtual 179	com/tencent/feedback/eup/d:A	()Ljava/lang/String;
    //   86: invokevirtual 182	exceptionupload/ExceptionUpload:setExpuid	(Ljava/lang/String;)V
    //   89: aload 6
    //   91: aload 5
    //   93: invokevirtual 184	exceptionupload/ExceptionUpload:setType	(Ljava/lang/String;)V
    //   96: aload 6
    //   98: aload_2
    //   99: invokevirtual 188	com/tencent/feedback/eup/d:i	()J
    //   102: invokevirtual 192	exceptionupload/ExceptionUpload:setCashTime	(J)V
    //   105: aload 6
    //   107: new 194	java/lang/StringBuilder
    //   110: dup
    //   111: invokespecial 195	java/lang/StringBuilder:<init>	()V
    //   114: aload_2
    //   115: invokevirtual 197	com/tencent/feedback/eup/d:e	()Ljava/lang/String;
    //   118: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   124: invokevirtual 207	exceptionupload/ExceptionUpload:setExceptionType	(Ljava/lang/String;)V
    //   127: aload 6
    //   129: new 194	java/lang/StringBuilder
    //   132: dup
    //   133: invokespecial 195	java/lang/StringBuilder:<init>	()V
    //   136: aload_2
    //   137: invokevirtual 209	com/tencent/feedback/eup/d:g	()Ljava/lang/String;
    //   140: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   146: invokevirtual 212	exceptionupload/ExceptionUpload:setExcepitonAddress	(Ljava/lang/String;)V
    //   149: aload 6
    //   151: new 194	java/lang/StringBuilder
    //   154: dup
    //   155: invokespecial 195	java/lang/StringBuilder:<init>	()V
    //   158: aload_2
    //   159: invokevirtual 215	com/tencent/feedback/eup/d:w	()Ljava/lang/String;
    //   162: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   168: invokevirtual 218	exceptionupload/ExceptionUpload:setThreadName	(Ljava/lang/String;)V
    //   171: aload 6
    //   173: aload_2
    //   174: invokevirtual 221	com/tencent/feedback/eup/d:t	()Ljava/lang/String;
    //   177: invokevirtual 224	exceptionupload/ExceptionUpload:setHash	(Ljava/lang/String;)V
    //   180: aload 6
    //   182: new 194	java/lang/StringBuilder
    //   185: dup
    //   186: invokespecial 195	java/lang/StringBuilder:<init>	()V
    //   189: aload_2
    //   190: invokevirtual 160	com/tencent/feedback/eup/d:f	()Ljava/lang/String;
    //   193: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: ldc 226
    //   198: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: aload_2
    //   202: invokevirtual 229	com/tencent/feedback/eup/d:h	()Ljava/lang/String;
    //   205: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   211: invokevirtual 232	exceptionupload/ExceptionUpload:setCallStack	(Ljava/lang/String;)V
    //   214: aload 6
    //   216: ldc 234
    //   218: invokevirtual 237	exceptionupload/ExceptionUpload:setCauseBy	(Ljava/lang/String;)V
    //   221: aload 6
    //   223: new 194	java/lang/StringBuilder
    //   226: dup
    //   227: invokespecial 195	java/lang/StringBuilder:<init>	()V
    //   230: aload_2
    //   231: invokevirtual 240	com/tencent/feedback/eup/d:v	()Ljava/lang/String;
    //   234: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   237: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   240: invokevirtual 243	exceptionupload/ExceptionUpload:setProcessName	(Ljava/lang/String;)V
    //   243: aload 6
    //   245: aload_2
    //   246: invokevirtual 246	com/tencent/feedback/eup/d:r	()I
    //   249: i2l
    //   250: invokevirtual 249	exceptionupload/ExceptionUpload:setCrashCount	(J)V
    //   253: aload_1
    //   254: invokestatic 254	com/tencent/feedback/common/d:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/d;
    //   257: pop
    //   258: aload 6
    //   260: invokestatic 256	com/tencent/feedback/common/d:d	()Ljava/lang/String;
    //   263: invokevirtual 259	exceptionupload/ExceptionUpload:setArchVersion	(Ljava/lang/String;)V
    //   266: new 261	java/util/ArrayList
    //   269: dup
    //   270: invokespecial 262	java/util/ArrayList:<init>	()V
    //   273: astore 8
    //   275: aload_2
    //   276: invokevirtual 265	com/tencent/feedback/eup/d:s	()[B
    //   279: ldc_w 267
    //   282: invokestatic 269	com/tencent/feedback/eup/f:a	([BLjava/lang/String;)Lexceptionupload/Attachment;
    //   285: astore 66
    //   287: aload 66
    //   289: ifnull +21 -> 310
    //   292: ldc_w 271
    //   295: iconst_0
    //   296: anewarray 41	java/lang/Object
    //   299: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   302: aload 8
    //   304: aload 66
    //   306: invokevirtual 275	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   309: pop
    //   310: aload_2
    //   311: invokevirtual 162	com/tencent/feedback/eup/d:c	()Z
    //   314: ifeq +52 -> 366
    //   317: aload_2
    //   318: invokevirtual 246	com/tencent/feedback/eup/d:r	()I
    //   321: iconst_1
    //   322: if_icmple +44 -> 366
    //   325: aload_2
    //   326: invokevirtual 278	com/tencent/feedback/eup/d:q	()Ljava/lang/String;
    //   329: ldc_w 280
    //   332: invokevirtual 284	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   335: ldc_w 286
    //   338: invokestatic 269	com/tencent/feedback/eup/f:a	([BLjava/lang/String;)Lexceptionupload/Attachment;
    //   341: astore 77
    //   343: aload 77
    //   345: ifnull +21 -> 366
    //   348: ldc_w 288
    //   351: iconst_0
    //   352: anewarray 41	java/lang/Object
    //   355: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   358: aload 8
    //   360: aload 77
    //   362: invokevirtual 275	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   365: pop
    //   366: aload_2
    //   367: invokevirtual 169	com/tencent/feedback/eup/d:d	()Z
    //   370: ifeq +43 -> 413
    //   373: aload_2
    //   374: invokevirtual 291	com/tencent/feedback/eup/d:u	()Ljava/lang/String;
    //   377: ifnull +36 -> 413
    //   380: aload_1
    //   381: aload_2
    //   382: invokevirtual 291	com/tencent/feedback/eup/d:u	()Ljava/lang/String;
    //   385: invokestatic 293	com/tencent/feedback/eup/f:a	(Landroid/content/Context;Ljava/lang/String;)Lexceptionupload/Attachment;
    //   388: astore 75
    //   390: aload 75
    //   392: ifnull +21 -> 413
    //   395: ldc_w 295
    //   398: iconst_0
    //   399: anewarray 41	java/lang/Object
    //   402: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   405: aload 8
    //   407: aload 75
    //   409: invokevirtual 275	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   412: pop
    //   413: aload_2
    //   414: invokevirtual 169	com/tencent/feedback/eup/d:d	()Z
    //   417: ifeq +64 -> 481
    //   420: aload_0
    //   421: getfield 24	com/tencent/feedback/eup/f:e	[B
    //   424: ifnonnull +15 -> 439
    //   427: aload_1
    //   428: invokestatic 254	com/tencent/feedback/common/d:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/d;
    //   431: pop
    //   432: aload_0
    //   433: invokestatic 297	com/tencent/feedback/common/d:e	()[B
    //   436: putfield 24	com/tencent/feedback/eup/f:e	[B
    //   439: aload_0
    //   440: getfield 24	com/tencent/feedback/eup/f:e	[B
    //   443: ifnull +38 -> 481
    //   446: aload_0
    //   447: getfield 24	com/tencent/feedback/eup/f:e	[B
    //   450: ldc_w 299
    //   453: invokestatic 269	com/tencent/feedback/eup/f:a	([BLjava/lang/String;)Lexceptionupload/Attachment;
    //   456: astore 72
    //   458: aload 72
    //   460: ifnull +21 -> 481
    //   463: ldc_w 301
    //   466: iconst_0
    //   467: anewarray 41	java/lang/Object
    //   470: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   473: aload 8
    //   475: aload 72
    //   477: invokevirtual 275	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   480: pop
    //   481: aload_2
    //   482: invokevirtual 304	com/tencent/feedback/eup/d:x	()Ljava/lang/String;
    //   485: ifnull +44 -> 529
    //   488: aload_2
    //   489: invokevirtual 304	com/tencent/feedback/eup/d:x	()Ljava/lang/String;
    //   492: ldc_w 280
    //   495: invokevirtual 284	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   498: ldc_w 306
    //   501: invokestatic 269	com/tencent/feedback/eup/f:a	([BLjava/lang/String;)Lexceptionupload/Attachment;
    //   504: astore 70
    //   506: aload 70
    //   508: ifnull +21 -> 529
    //   511: ldc_w 308
    //   514: iconst_0
    //   515: anewarray 41	java/lang/Object
    //   518: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   521: aload 8
    //   523: aload 70
    //   525: invokevirtual 275	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   528: pop
    //   529: aload_2
    //   530: invokevirtual 311	com/tencent/feedback/eup/d:y	()[B
    //   533: ifnull +38 -> 571
    //   536: aload_2
    //   537: invokevirtual 311	com/tencent/feedback/eup/d:y	()[B
    //   540: ldc_w 313
    //   543: invokestatic 269	com/tencent/feedback/eup/f:a	([BLjava/lang/String;)Lexceptionupload/Attachment;
    //   546: astore 68
    //   548: aload 68
    //   550: ifnull +21 -> 571
    //   553: ldc_w 315
    //   556: iconst_0
    //   557: anewarray 41	java/lang/Object
    //   560: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   563: aload 8
    //   565: aload 68
    //   567: invokevirtual 275	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   570: pop
    //   571: aload 8
    //   573: invokevirtual 318	java/util/ArrayList:size	()I
    //   576: ifle +10 -> 586
    //   579: aload 6
    //   581: aload 8
    //   583: invokevirtual 322	exceptionupload/ExceptionUpload:setAttachmentList	(Ljava/util/ArrayList;)V
    //   586: new 324	exceptionupload/RunInfo
    //   589: dup
    //   590: invokespecial 325	exceptionupload/RunInfo:<init>	()V
    //   593: astore 10
    //   595: aload 10
    //   597: aload_2
    //   598: invokevirtual 329	com/tencent/feedback/eup/d:j	()F
    //   601: invokevirtual 333	exceptionupload/RunInfo:setBattery	(F)V
    //   604: aload 10
    //   606: aload_2
    //   607: invokevirtual 336	com/tencent/feedback/eup/d:k	()F
    //   610: invokevirtual 339	exceptionupload/RunInfo:setCpu	(F)V
    //   613: aload 10
    //   615: aload_2
    //   616: invokevirtual 342	com/tencent/feedback/eup/d:l	()J
    //   619: invokevirtual 345	exceptionupload/RunInfo:setFreeMem	(J)V
    //   622: aload 10
    //   624: aload_2
    //   625: invokevirtual 348	com/tencent/feedback/eup/d:m	()J
    //   628: invokevirtual 351	exceptionupload/RunInfo:setFreeStorage	(J)V
    //   631: aload 10
    //   633: aload_2
    //   634: invokevirtual 354	com/tencent/feedback/eup/d:n	()J
    //   637: invokevirtual 357	exceptionupload/RunInfo:setFreeSDCard	(J)V
    //   640: iconst_3
    //   641: anewarray 41	java/lang/Object
    //   644: astore 11
    //   646: aload 11
    //   648: iconst_0
    //   649: aload 10
    //   651: getfield 361	exceptionupload/RunInfo:freeMem	J
    //   654: invokestatic 366	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   657: aastore
    //   658: aload 11
    //   660: iconst_1
    //   661: aload 10
    //   663: getfield 369	exceptionupload/RunInfo:freeStorage	J
    //   666: invokestatic 366	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   669: aastore
    //   670: aload 11
    //   672: iconst_2
    //   673: aload 10
    //   675: getfield 372	exceptionupload/RunInfo:freeSDCard	J
    //   678: invokestatic 366	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   681: aastore
    //   682: ldc_w 374
    //   685: aload 11
    //   687: invokestatic 377	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   690: aload 6
    //   692: aload 10
    //   694: invokevirtual 381	exceptionupload/ExceptionUpload:setRunInfo	(Lexceptionupload/RunInfo;)V
    //   697: invokestatic 387	com/tencent/feedback/common/c:p	()Lcom/tencent/feedback/common/c;
    //   700: astore 12
    //   702: new 389	exceptionupload/MobileDetail
    //   705: dup
    //   706: invokespecial 390	exceptionupload/MobileDetail:<init>	()V
    //   709: astore 13
    //   711: new 194	java/lang/StringBuilder
    //   714: dup
    //   715: invokespecial 195	java/lang/StringBuilder:<init>	()V
    //   718: aload 12
    //   720: invokevirtual 393	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   723: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   726: ifnull +1118 -> 1844
    //   729: aload 12
    //   731: invokevirtual 395	com/tencent/feedback/common/c:l	()Ljava/lang/String;
    //   734: astore 14
    //   736: aload 13
    //   738: aload 14
    //   740: invokevirtual 398	exceptionupload/MobileDetail:setSymbol	(Ljava/lang/String;)V
    //   743: aload_2
    //   744: invokevirtual 401	com/tencent/feedback/eup/d:D	()Ljava/lang/String;
    //   747: ldc_w 403
    //   750: invokestatic 409	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   753: astore 65
    //   755: aload 65
    //   757: astore 16
    //   759: aload 13
    //   761: new 194	java/lang/StringBuilder
    //   764: dup
    //   765: ldc_w 411
    //   768: invokespecial 412	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   771: invokestatic 417	com/tencent/feedback/common/i:a	()Lcom/tencent/feedback/common/i;
    //   774: invokevirtual 419	com/tencent/feedback/common/i:b	()Z
    //   777: invokevirtual 422	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   780: ldc_w 424
    //   783: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   786: aload 16
    //   788: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   791: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   794: invokevirtual 427	exceptionupload/MobileDetail:setOther	(Ljava/lang/String;)V
    //   797: aload 6
    //   799: aload 13
    //   801: invokevirtual 431	exceptionupload/ExceptionUpload:setMobile	(Lexceptionupload/MobileDetail;)V
    //   804: new 433	exceptionupload/ContactInfo
    //   807: dup
    //   808: invokespecial 434	exceptionupload/ContactInfo:<init>	()V
    //   811: astore 17
    //   813: aload 17
    //   815: new 194	java/lang/StringBuilder
    //   818: dup
    //   819: invokespecial 195	java/lang/StringBuilder:<init>	()V
    //   822: aload_2
    //   823: invokevirtual 436	com/tencent/feedback/eup/d:p	()Ljava/lang/String;
    //   826: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   829: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   832: invokevirtual 439	exceptionupload/ContactInfo:setAll	(Ljava/lang/String;)V
    //   835: aload 17
    //   837: new 194	java/lang/StringBuilder
    //   840: dup
    //   841: invokespecial 195	java/lang/StringBuilder:<init>	()V
    //   844: aload_2
    //   845: invokevirtual 436	com/tencent/feedback/eup/d:p	()Ljava/lang/String;
    //   848: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   851: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   854: invokevirtual 442	exceptionupload/ContactInfo:setQua	(Ljava/lang/String;)V
    //   857: aload 6
    //   859: aload 17
    //   861: invokevirtual 446	exceptionupload/ExceptionUpload:setContact	(Lexceptionupload/ContactInfo;)V
    //   864: aload_2
    //   865: invokevirtual 169	com/tencent/feedback/eup/d:d	()Z
    //   868: ifeq +47 -> 915
    //   871: aload_1
    //   872: invokestatic 449	com/tencent/feedback/eup/f:b	(Landroid/content/Context;)Ljava/util/ArrayList;
    //   875: astore 62
    //   877: aload 6
    //   879: aload 62
    //   881: invokevirtual 452	exceptionupload/ExceptionUpload:setAppInfo	(Ljava/util/ArrayList;)V
    //   884: iconst_1
    //   885: anewarray 41	java/lang/Object
    //   888: astore 63
    //   890: aload 62
    //   892: ifnonnull +391 -> 1283
    //   895: iconst_0
    //   896: istore 64
    //   898: aload 63
    //   900: iconst_0
    //   901: iload 64
    //   903: invokestatic 119	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   906: aastore
    //   907: ldc_w 454
    //   910: aload 63
    //   912: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   915: aload 12
    //   917: ifnull +935 -> 1852
    //   920: aload 12
    //   922: invokevirtual 456	com/tencent/feedback/common/c:j	()Ljava/lang/String;
    //   925: astore 18
    //   927: aload 6
    //   929: aload 18
    //   931: invokevirtual 459	exceptionupload/ExceptionUpload:setAppUUID	(Ljava/lang/String;)V
    //   934: aload_2
    //   935: invokevirtual 463	com/tencent/feedback/eup/d:z	()Ljava/util/Map;
    //   938: astore 19
    //   940: iconst_1
    //   941: anewarray 41	java/lang/Object
    //   944: astore 20
    //   946: aload 19
    //   948: ifnonnull +345 -> 1293
    //   951: iconst_0
    //   952: istore 21
    //   954: aload 20
    //   956: iconst_0
    //   957: iload 21
    //   959: invokestatic 119	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   962: aastore
    //   963: ldc_w 465
    //   966: aload 20
    //   968: invokestatic 377	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   971: aload 19
    //   973: ifnull +339 -> 1312
    //   976: aload 19
    //   978: invokeinterface 468 1 0
    //   983: ifle +329 -> 1312
    //   986: new 261	java/util/ArrayList
    //   989: dup
    //   990: invokespecial 262	java/util/ArrayList:<init>	()V
    //   993: astore 22
    //   995: aload 19
    //   997: invokeinterface 472 1 0
    //   1002: invokeinterface 478 1 0
    //   1007: astore 23
    //   1009: aload 23
    //   1011: invokeinterface 483 1 0
    //   1016: ifeq +289 -> 1305
    //   1019: aload 23
    //   1021: invokeinterface 487 1 0
    //   1026: checkcast 489	java/util/Map$Entry
    //   1029: astore 58
    //   1031: new 491	exceptionupload/PlugInfo
    //   1034: dup
    //   1035: invokespecial 492	exceptionupload/PlugInfo:<init>	()V
    //   1038: astore 59
    //   1040: aload 59
    //   1042: aload 58
    //   1044: invokeinterface 495 1 0
    //   1049: checkcast 134	java/lang/String
    //   1052: putfield 499	exceptionupload/PlugInfo:pluginId	Ljava/lang/String;
    //   1055: aload 59
    //   1057: aload 58
    //   1059: invokeinterface 502 1 0
    //   1064: checkcast 504	com/tencent/feedback/common/PlugInInfo
    //   1067: getfield 507	com/tencent/feedback/common/PlugInInfo:plugInVersion	Ljava/lang/String;
    //   1070: putfield 510	exceptionupload/PlugInfo:pluginVer	Ljava/lang/String;
    //   1073: aload 59
    //   1075: aload 58
    //   1077: invokeinterface 502 1 0
    //   1082: checkcast 504	com/tencent/feedback/common/PlugInInfo
    //   1085: getfield 513	com/tencent/feedback/common/PlugInInfo:plugInUUID	Ljava/lang/String;
    //   1088: putfield 516	exceptionupload/PlugInfo:pluginUUID	Ljava/lang/String;
    //   1091: iconst_3
    //   1092: anewarray 41	java/lang/Object
    //   1095: astore 60
    //   1097: aload 60
    //   1099: iconst_0
    //   1100: new 194	java/lang/StringBuilder
    //   1103: dup
    //   1104: invokespecial 195	java/lang/StringBuilder:<init>	()V
    //   1107: aload 59
    //   1109: getfield 499	exceptionupload/PlugInfo:pluginId	Ljava/lang/String;
    //   1112: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1115: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1118: aastore
    //   1119: aload 60
    //   1121: iconst_1
    //   1122: new 194	java/lang/StringBuilder
    //   1125: dup
    //   1126: invokespecial 195	java/lang/StringBuilder:<init>	()V
    //   1129: aload 59
    //   1131: getfield 510	exceptionupload/PlugInfo:pluginVer	Ljava/lang/String;
    //   1134: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1137: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1140: aastore
    //   1141: aload 60
    //   1143: iconst_2
    //   1144: new 194	java/lang/StringBuilder
    //   1147: dup
    //   1148: invokespecial 195	java/lang/StringBuilder:<init>	()V
    //   1151: aload 59
    //   1153: getfield 516	exceptionupload/PlugInfo:pluginUUID	Ljava/lang/String;
    //   1156: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1159: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1162: aastore
    //   1163: ldc_w 518
    //   1166: aload 60
    //   1168: invokestatic 377	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1171: aload 22
    //   1173: aload 59
    //   1175: invokevirtual 275	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1178: pop
    //   1179: goto -170 -> 1009
    //   1182: astore 4
    //   1184: aload 4
    //   1186: invokevirtual 97	java/lang/Throwable:printStackTrace	()V
    //   1189: aconst_null
    //   1190: areturn
    //   1191: ldc_w 520
    //   1194: astore 5
    //   1196: goto -1125 -> 71
    //   1199: aload_2
    //   1200: invokevirtual 521	com/tencent/feedback/eup/d:b	()Z
    //   1203: ifeq +18 -> 1221
    //   1206: aload_2
    //   1207: invokevirtual 162	com/tencent/feedback/eup/d:c	()Z
    //   1210: ifeq +610 -> 1820
    //   1213: ldc_w 523
    //   1216: astore 5
    //   1218: goto -1147 -> 71
    //   1221: aload_2
    //   1222: invokevirtual 526	com/tencent/feedback/eup/d:C	()Z
    //   1225: ifeq +18 -> 1243
    //   1228: aload_2
    //   1229: invokevirtual 162	com/tencent/feedback/eup/d:c	()Z
    //   1232: ifeq +596 -> 1828
    //   1235: ldc_w 528
    //   1238: astore 5
    //   1240: goto -1169 -> 71
    //   1243: aload_2
    //   1244: invokevirtual 162	com/tencent/feedback/eup/d:c	()Z
    //   1247: ifeq +589 -> 1836
    //   1250: ldc_w 530
    //   1253: astore 5
    //   1255: goto -1184 -> 71
    //   1258: astore 9
    //   1260: aload 9
    //   1262: invokevirtual 97	java/lang/Throwable:printStackTrace	()V
    //   1265: goto -694 -> 571
    //   1268: astore 15
    //   1270: aload 15
    //   1272: invokevirtual 97	java/lang/Throwable:printStackTrace	()V
    //   1275: ldc_w 532
    //   1278: astore 16
    //   1280: goto -521 -> 759
    //   1283: aload 62
    //   1285: invokevirtual 318	java/util/ArrayList:size	()I
    //   1288: istore 64
    //   1290: goto -392 -> 898
    //   1293: aload 19
    //   1295: invokeinterface 468 1 0
    //   1300: istore 21
    //   1302: goto -348 -> 954
    //   1305: aload 6
    //   1307: aload 22
    //   1309: putfield 536	exceptionupload/ExceptionUpload:plugins	Ljava/util/ArrayList;
    //   1312: invokestatic 387	com/tencent/feedback/common/c:p	()Lcom/tencent/feedback/common/c;
    //   1315: astore 25
    //   1317: aload 25
    //   1319: ifnonnull +40 -> 1359
    //   1322: ldc_w 538
    //   1325: iconst_0
    //   1326: anewarray 41	java/lang/Object
    //   1329: invokestatic 47	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1332: aconst_null
    //   1333: astore 26
    //   1335: aload 26
    //   1337: ifnull +523 -> 1860
    //   1340: aload 6
    //   1342: aload 26
    //   1344: invokevirtual 541	exceptionupload/ExceptionUpload:setMemo	(Ljava/lang/String;)V
    //   1347: aload 26
    //   1349: iconst_0
    //   1350: anewarray 41	java/lang/Object
    //   1353: invokestatic 377	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1356: goto +504 -> 1860
    //   1359: aload_1
    //   1360: invokestatic 254	com/tencent/feedback/common/d:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/d;
    //   1363: pop
    //   1364: new 543	java/lang/StringBuffer
    //   1367: dup
    //   1368: invokespecial 544	java/lang/StringBuffer:<init>	()V
    //   1371: astore 28
    //   1373: aload 28
    //   1375: invokestatic 546	com/tencent/feedback/common/d:b	()Ljava/lang/String;
    //   1378: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1381: ldc_w 551
    //   1384: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1387: pop
    //   1388: aload 28
    //   1390: invokestatic 553	com/tencent/feedback/common/d:c	()Ljava/lang/String;
    //   1393: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1396: ldc_w 551
    //   1399: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1402: pop
    //   1403: aload 28
    //   1405: ldc 234
    //   1407: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1410: ldc_w 551
    //   1413: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1416: pop
    //   1417: aload 28
    //   1419: ldc 234
    //   1421: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1424: ldc_w 551
    //   1427: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1430: pop
    //   1431: aload 28
    //   1433: aload 25
    //   1435: invokevirtual 554	com/tencent/feedback/common/c:h	()Ljava/lang/String;
    //   1438: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1441: ldc_w 551
    //   1444: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1447: pop
    //   1448: aload 28
    //   1450: aload_1
    //   1451: invokestatic 557	com/tencent/feedback/common/d:c	(Landroid/content/Context;)Ljava/lang/String;
    //   1454: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1457: ldc_w 551
    //   1460: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1463: pop
    //   1464: aload 28
    //   1466: ldc 234
    //   1468: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1471: ldc_w 551
    //   1474: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1477: pop
    //   1478: aload 28
    //   1480: aload_1
    //   1481: invokestatic 559	com/tencent/feedback/common/d:f	(Landroid/content/Context;)Ljava/lang/String;
    //   1484: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1487: ldc_w 551
    //   1490: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1493: pop
    //   1494: aload 28
    //   1496: aload_1
    //   1497: invokestatic 564	com/tencent/feedback/common/h:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/h;
    //   1500: invokevirtual 566	com/tencent/feedback/common/h:a	()Ljava/lang/String;
    //   1503: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1506: ldc_w 551
    //   1509: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1512: pop
    //   1513: aload 28
    //   1515: aload_1
    //   1516: invokestatic 568	com/tencent/feedback/common/d:d	(Landroid/content/Context;)Ljava/lang/String;
    //   1519: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1522: ldc_w 551
    //   1525: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1528: pop
    //   1529: aload 28
    //   1531: ldc 234
    //   1533: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1536: ldc_w 551
    //   1539: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1542: pop
    //   1543: aload 28
    //   1545: invokestatic 570	com/tencent/feedback/common/d:m	()Ljava/lang/String;
    //   1548: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1551: ldc_w 551
    //   1554: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1557: pop
    //   1558: aload 28
    //   1560: invokestatic 571	com/tencent/feedback/common/d:a	()Ljava/lang/String;
    //   1563: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1566: ldc_w 551
    //   1569: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1572: pop
    //   1573: aload 28
    //   1575: ldc 234
    //   1577: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1580: ldc_w 551
    //   1583: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1586: pop
    //   1587: aload 28
    //   1589: ldc 234
    //   1591: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1594: ldc_w 551
    //   1597: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1600: pop
    //   1601: aload 28
    //   1603: ldc 234
    //   1605: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1608: ldc_w 551
    //   1611: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1614: pop
    //   1615: aload 28
    //   1617: ldc 234
    //   1619: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1622: ldc_w 551
    //   1625: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1628: pop
    //   1629: aload 28
    //   1631: invokestatic 572	com/tencent/feedback/common/d:g	()Ljava/lang/String;
    //   1634: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1637: ldc_w 551
    //   1640: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1643: pop
    //   1644: aload 28
    //   1646: invokestatic 573	com/tencent/feedback/common/d:h	()Ljava/lang/String;
    //   1649: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1652: ldc_w 551
    //   1655: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1658: pop
    //   1659: aload 28
    //   1661: invokestatic 574	com/tencent/feedback/common/d:f	()Ljava/lang/String;
    //   1664: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1667: ldc_w 551
    //   1670: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1673: pop
    //   1674: aload 28
    //   1676: ldc 234
    //   1678: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1681: ldc_w 551
    //   1684: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1687: pop
    //   1688: aload 28
    //   1690: invokestatic 572	com/tencent/feedback/common/d:g	()Ljava/lang/String;
    //   1693: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1696: ldc_w 551
    //   1699: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1702: pop
    //   1703: aload 28
    //   1705: aload_1
    //   1706: invokestatic 577	com/tencent/feedback/common/d:g	(Landroid/content/Context;)Landroid/util/DisplayMetrics;
    //   1709: invokevirtual 580	java/lang/StringBuffer:append	(Ljava/lang/Object;)Ljava/lang/StringBuffer;
    //   1712: ldc_w 551
    //   1715: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1718: pop
    //   1719: aload 28
    //   1721: aload_1
    //   1722: invokestatic 583	com/tencent/feedback/common/g:c	(Landroid/content/Context;)Ljava/lang/String;
    //   1725: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1728: ldc_w 551
    //   1731: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1734: pop
    //   1735: aload_1
    //   1736: invokestatic 254	com/tencent/feedback/common/d:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/d;
    //   1739: pop
    //   1740: aload 28
    //   1742: aload_1
    //   1743: invokestatic 585	com/tencent/feedback/common/d:i	(Landroid/content/Context;)Ljava/lang/String;
    //   1746: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1749: ldc_w 551
    //   1752: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1755: pop
    //   1756: aload 28
    //   1758: ldc 234
    //   1760: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1763: ldc_w 551
    //   1766: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1769: pop
    //   1770: aload 28
    //   1772: invokestatic 587	com/tencent/feedback/common/d:k	()Ljava/lang/String;
    //   1775: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1778: ldc_w 551
    //   1781: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1784: pop
    //   1785: aload 28
    //   1787: invokestatic 588	com/tencent/feedback/common/d:l	()Ljava/lang/String;
    //   1790: invokevirtual 549	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1793: pop
    //   1794: aload 28
    //   1796: invokevirtual 589	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   1799: astore 26
    //   1801: aload 28
    //   1803: iconst_0
    //   1804: invokevirtual 593	java/lang/StringBuffer:setLength	(I)V
    //   1807: goto -472 -> 1335
    //   1810: astore 24
    //   1812: aload 24
    //   1814: invokevirtual 97	java/lang/Throwable:printStackTrace	()V
    //   1817: goto +43 -> 1860
    //   1820: ldc_w 595
    //   1823: astore 5
    //   1825: goto -1754 -> 71
    //   1828: ldc_w 597
    //   1831: astore 5
    //   1833: goto -1762 -> 71
    //   1836: ldc_w 599
    //   1839: astore 5
    //   1841: goto -1770 -> 71
    //   1844: ldc_w 601
    //   1847: astore 14
    //   1849: goto -1113 -> 736
    //   1852: ldc_w 601
    //   1855: astore 18
    //   1857: goto -930 -> 927
    //   1860: aload 6
    //   1862: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   53	67	1182	java/lang/Throwable
    //   71	275	1182	java/lang/Throwable
    //   571	586	1182	java/lang/Throwable
    //   586	736	1182	java/lang/Throwable
    //   736	743	1182	java/lang/Throwable
    //   759	890	1182	java/lang/Throwable
    //   898	915	1182	java/lang/Throwable
    //   920	927	1182	java/lang/Throwable
    //   927	946	1182	java/lang/Throwable
    //   954	971	1182	java/lang/Throwable
    //   976	1009	1182	java/lang/Throwable
    //   1009	1179	1182	java/lang/Throwable
    //   1199	1213	1182	java/lang/Throwable
    //   1221	1235	1182	java/lang/Throwable
    //   1243	1250	1182	java/lang/Throwable
    //   1260	1265	1182	java/lang/Throwable
    //   1270	1275	1182	java/lang/Throwable
    //   1283	1290	1182	java/lang/Throwable
    //   1293	1302	1182	java/lang/Throwable
    //   1305	1312	1182	java/lang/Throwable
    //   1812	1817	1182	java/lang/Throwable
    //   275	287	1258	java/lang/Throwable
    //   292	310	1258	java/lang/Throwable
    //   310	343	1258	java/lang/Throwable
    //   348	366	1258	java/lang/Throwable
    //   366	390	1258	java/lang/Throwable
    //   395	413	1258	java/lang/Throwable
    //   413	439	1258	java/lang/Throwable
    //   439	458	1258	java/lang/Throwable
    //   463	481	1258	java/lang/Throwable
    //   481	506	1258	java/lang/Throwable
    //   511	529	1258	java/lang/Throwable
    //   529	548	1258	java/lang/Throwable
    //   553	571	1258	java/lang/Throwable
    //   743	755	1268	java/lang/Throwable
    //   1312	1317	1810	java/lang/Throwable
    //   1322	1332	1810	java/lang/Throwable
    //   1340	1356	1810	java/lang/Throwable
    //   1359	1807	1810	java/lang/Throwable
  }

  private ExceptionUploadPackage a(Context paramContext, List<d> paramList, String paramString)
  {
    ExceptionUploadPackage localExceptionUploadPackage;
    if ((paramContext == null) || (paramList == null) || (paramList.size() <= 0))
    {
      e.c("rqdp{  params!}", new Object[0]);
      localExceptionUploadPackage = null;
    }
    ArrayList localArrayList2;
    do
    {
      return localExceptionUploadPackage;
      ArrayList localArrayList1;
      while (true)
      {
        d locald;
        try
        {
          localArrayList1 = new ArrayList();
          localArrayList2 = new ArrayList();
          Iterator localIterator = paramList.iterator();
          if (!localIterator.hasNext())
            break;
          locald = (d)localIterator.next();
          ExceptionUpload localExceptionUpload = a(paramContext, locald);
          if (localExceptionUpload != null)
          {
            localArrayList1.add(localExceptionUpload);
            continue;
          }
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
          return null;
        }
        localArrayList2.add(locald);
      }
      localExceptionUploadPackage = new ExceptionUploadPackage();
      localExceptionUploadPackage.setList(localArrayList1);
    }
    while (localArrayList2.size() <= 0);
    int i = c.a(paramContext, localArrayList2);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(i);
    e.c("rqdp{ delete error eup} %d", arrayOfObject);
    paramList.removeAll(localArrayList2);
    return localExceptionUploadPackage;
  }

  private static void a(List<d> paramList)
  {
    if (paramList != null)
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        d locald = (d)localIterator.next();
        locald.a(1 + locald.o());
      }
    }
  }

  private static ArrayList<AppInfo> b(Context paramContext)
  {
    ArrayList localArrayList;
    try
    {
      List localList = com.tencent.feedback.common.a.a.a(paramContext, null, 1, 50);
      if ((localList != null) && (localList.size() > 0))
      {
        localArrayList = new ArrayList();
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          com.tencent.feedback.common.a.d locald = (com.tencent.feedback.common.a.d)localIterator.next();
          AppInfo localAppInfo = new AppInfo();
          localAppInfo.libArch = locald.f();
          localAppInfo.libName = locald.a();
          localAppInfo.libUUID = locald.d();
          localArrayList.add(localAppInfo);
        }
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      e.d("rqdp{  Error: lb pack fail!}", new Object[0]);
    }
    return null;
    return localArrayList;
  }

  private List<d> b(Context paramContext, int paramInt)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    e.e("rqdp{  get MN:}%d", arrayOfObject);
    if ((paramContext == null) || (paramInt <= 0))
    {
      e.c("rqdp{  params!}", new Object[0]);
      return null;
    }
    try
    {
      Object localObject = c.a(paramContext, paramInt, "desc", 1, null, -1, -1, -1, 3, -1L, -1L, null);
      if (localObject == null)
        localObject = new ArrayList();
      if (((List)localObject).size() < paramInt)
      {
        List localList = c.a(paramContext, paramInt - ((List)localObject).size(), "desc", 2, null, -1, -1, -1, 3, -1L, -1L, null);
        if ((localList != null) && (localList.size() > 0))
          ((List)localObject).addAll(localList);
      }
      a((List)localObject);
      c.b(paramContext, (List)localObject);
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return (List<d>)null;
  }

  // ERROR //
  public final RequestPackage a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 26	com/tencent/feedback/eup/f:f	Lcommon/RequestPackage;
    //   6: ifnull +12 -> 18
    //   9: aload_0
    //   10: getfield 26	com/tencent/feedback/eup/f:f	Lcommon/RequestPackage;
    //   13: astore_3
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_3
    //   17: areturn
    //   18: invokestatic 683	com/tencent/feedback/eup/e:k	()Lcom/tencent/feedback/eup/e;
    //   21: astore_2
    //   22: aconst_null
    //   23: astore_3
    //   24: aload_2
    //   25: ifnull -11 -> 14
    //   28: aload_2
    //   29: invokevirtual 685	com/tencent/feedback/eup/e:a	()Z
    //   32: istore 4
    //   34: aconst_null
    //   35: astore_3
    //   36: iload 4
    //   38: ifeq -24 -> 14
    //   41: invokestatic 683	com/tencent/feedback/eup/e:k	()Lcom/tencent/feedback/eup/e;
    //   44: invokevirtual 688	com/tencent/feedback/eup/e:q	()Lcom/tencent/feedback/eup/CrashStrategyBean;
    //   47: astore 7
    //   49: aload_0
    //   50: aconst_null
    //   51: putfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   54: aload 7
    //   56: invokevirtual 693	com/tencent/feedback/eup/CrashStrategyBean:isMerged	()Z
    //   59: istore 13
    //   61: aload_0
    //   62: getfield 696	com/tencent/feedback/eup/f:c	Landroid/content/Context;
    //   65: invokestatic 699	com/tencent/feedback/common/g:a	(Landroid/content/Context;)Z
    //   68: ifeq +197 -> 265
    //   71: aload 7
    //   73: invokevirtual 702	com/tencent/feedback/eup/CrashStrategyBean:getMaxUploadNum_Wifi	()I
    //   76: istore 14
    //   78: iload 13
    //   80: ifne +195 -> 275
    //   83: ldc_w 704
    //   86: iconst_0
    //   87: anewarray 41	java/lang/Object
    //   90: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   93: aload_0
    //   94: aload_0
    //   95: aload_0
    //   96: getfield 696	com/tencent/feedback/eup/f:c	Landroid/content/Context;
    //   99: iload 14
    //   101: invokespecial 706	com/tencent/feedback/eup/f:b	(Landroid/content/Context;I)Ljava/util/List;
    //   104: putfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   107: aload_0
    //   108: getfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   111: ifnull +15 -> 126
    //   114: aload_0
    //   115: getfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   118: invokeinterface 605 1 0
    //   123: ifgt +179 -> 302
    //   126: ldc_w 708
    //   129: iconst_0
    //   130: anewarray 41	java/lang/Object
    //   133: invokestatic 47	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   136: aconst_null
    //   137: astore_3
    //   138: goto -124 -> 14
    //   141: astore 8
    //   143: aload 8
    //   145: invokevirtual 97	java/lang/Throwable:printStackTrace	()V
    //   148: aload_0
    //   149: getfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   152: astore 9
    //   154: aconst_null
    //   155: astore_3
    //   156: aload 9
    //   158: ifnull -144 -> 14
    //   161: aload_0
    //   162: getfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   165: invokeinterface 605 1 0
    //   170: istore 10
    //   172: aconst_null
    //   173: astore_3
    //   174: iload 10
    //   176: ifle -162 -> 14
    //   179: aload_0
    //   180: getfield 696	com/tencent/feedback/eup/f:c	Landroid/content/Context;
    //   183: aload_0
    //   184: getfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   187: invokestatic 619	com/tencent/feedback/eup/c:a	(Landroid/content/Context;Ljava/util/List;)I
    //   190: istore 11
    //   192: iconst_1
    //   193: anewarray 41	java/lang/Object
    //   196: astore 12
    //   198: aload 12
    //   200: iconst_0
    //   201: iload 11
    //   203: invokestatic 119	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   206: aastore
    //   207: ldc_w 710
    //   210: aload 12
    //   212: invokestatic 47	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   215: aload_0
    //   216: aconst_null
    //   217: putfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   220: aconst_null
    //   221: astore_3
    //   222: goto -208 -> 14
    //   225: astore_1
    //   226: aload_0
    //   227: monitorexit
    //   228: aload_1
    //   229: athrow
    //   230: astore 5
    //   232: aload 5
    //   234: invokevirtual 97	java/lang/Throwable:printStackTrace	()V
    //   237: iconst_1
    //   238: anewarray 41	java/lang/Object
    //   241: astore 6
    //   243: aload 6
    //   245: iconst_0
    //   246: aload 5
    //   248: invokevirtual 711	java/lang/Throwable:toString	()Ljava/lang/String;
    //   251: aastore
    //   252: ldc_w 713
    //   255: aload 6
    //   257: invokestatic 657	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   260: aconst_null
    //   261: astore_3
    //   262: goto -248 -> 14
    //   265: aload 7
    //   267: invokevirtual 716	com/tencent/feedback/eup/CrashStrategyBean:getMaxUploadNum_GPRS	()I
    //   270: istore 14
    //   272: goto -194 -> 78
    //   275: ldc_w 718
    //   278: iconst_0
    //   279: anewarray 41	java/lang/Object
    //   282: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   285: aload_0
    //   286: aload_0
    //   287: aload_0
    //   288: getfield 696	com/tencent/feedback/eup/f:c	Landroid/content/Context;
    //   291: iload 14
    //   293: invokevirtual 720	com/tencent/feedback/eup/f:a	(Landroid/content/Context;I)Ljava/util/List;
    //   296: putfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   299: goto -192 -> 107
    //   302: iconst_2
    //   303: anewarray 41	java/lang/Object
    //   306: astore 15
    //   308: aload 15
    //   310: iconst_0
    //   311: aload_0
    //   312: getfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   315: invokeinterface 605 1 0
    //   320: invokestatic 119	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   323: aastore
    //   324: aload 15
    //   326: iconst_1
    //   327: iload 13
    //   329: invokestatic 167	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   332: aastore
    //   333: ldc_w 722
    //   336: aload 15
    //   338: invokestatic 51	com/tencent/feedback/common/e:f	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   341: aload_0
    //   342: aload_0
    //   343: getfield 696	com/tencent/feedback/eup/f:c	Landroid/content/Context;
    //   346: aload_0
    //   347: getfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   350: aconst_null
    //   351: invokespecial 724	com/tencent/feedback/eup/f:a	(Landroid/content/Context;Ljava/util/List;Ljava/lang/String;)Lexceptionupload/ExceptionUploadPackage;
    //   354: astore 16
    //   356: aconst_null
    //   357: astore_3
    //   358: aload 16
    //   360: ifnull -346 -> 14
    //   363: aload 16
    //   365: invokevirtual 725	exceptionupload/ExceptionUploadPackage:toByteArray	()[B
    //   368: astore 17
    //   370: aload 17
    //   372: ifnonnull +18 -> 390
    //   375: ldc_w 727
    //   378: iconst_0
    //   379: anewarray 41	java/lang/Object
    //   382: invokestatic 47	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   385: aconst_null
    //   386: astore_3
    //   387: goto -373 -> 14
    //   390: aload_0
    //   391: aload_0
    //   392: getfield 696	com/tencent/feedback/eup/f:c	Landroid/content/Context;
    //   395: aload_0
    //   396: getfield 730	com/tencent/feedback/eup/f:a	I
    //   399: aload 17
    //   401: invokestatic 733	com/tencent/feedback/eup/f:a	(Landroid/content/Context;I[B)Lcommon/RequestPackage;
    //   404: putfield 26	com/tencent/feedback/eup/f:f	Lcommon/RequestPackage;
    //   407: aload_0
    //   408: getfield 26	com/tencent/feedback/eup/f:f	Lcommon/RequestPackage;
    //   411: astore_3
    //   412: goto -398 -> 14
    //
    // Exception table:
    //   from	to	target	type
    //   54	78	141	java/lang/Throwable
    //   83	107	141	java/lang/Throwable
    //   107	126	141	java/lang/Throwable
    //   126	136	141	java/lang/Throwable
    //   265	272	141	java/lang/Throwable
    //   275	299	141	java/lang/Throwable
    //   302	356	141	java/lang/Throwable
    //   363	370	141	java/lang/Throwable
    //   375	385	141	java/lang/Throwable
    //   390	412	141	java/lang/Throwable
    //   2	14	225	finally
    //   18	22	225	finally
    //   28	34	225	finally
    //   41	49	225	finally
    //   49	54	225	finally
    //   54	78	225	finally
    //   83	107	225	finally
    //   107	126	225	finally
    //   126	136	225	finally
    //   143	154	225	finally
    //   161	172	225	finally
    //   179	220	225	finally
    //   232	260	225	finally
    //   265	272	225	finally
    //   275	299	225	finally
    //   302	356	225	finally
    //   363	370	225	finally
    //   375	385	225	finally
    //   390	412	225	finally
    //   41	49	230	java/lang/Throwable
  }

  protected final List<d> a(Context paramContext, int paramInt)
  {
    e.e("rqdp{  getEupInMe}", new Object[0]);
    if ((paramContext == null) || (paramInt <= 0))
    {
      e.d("rqdp{  params!}", new Object[0]);
      return null;
    }
    try
    {
      long l = com.tencent.feedback.anr.a.f();
      ArrayList localArrayList = new ArrayList();
      List localList1 = c.a(paramContext, paramInt, "desc", -1, null, -1, -1, -1, 3, l, -1L, Boolean.valueOf(false));
      if ((localList1 != null) && (localList1.size() > 0))
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(localList1.size());
        e.b("rqdp{  tdeup ge c=1, n:}%d", arrayOfObject);
        localArrayList.addAll(localList1);
        localList1.clear();
      }
      if (localArrayList.size() < paramInt)
      {
        List localList3 = c.a(paramContext, paramInt, "desc", -1, null, 2, -1, -1, 3, -1L, l, null);
        if ((localList3 != null) && (localList3.size() > 0))
        {
          e.b("rqdp{  yeseup c>=2,n:}%d" + localList3.size(), new Object[0]);
          localArrayList.addAll(localList3);
          localList3.clear();
        }
      }
      if (localArrayList.size() < paramInt)
      {
        List localList2 = c.a(paramContext, paramInt, "desc", -1, null, 0, 1, -1, -1, 3L, l, Boolean.valueOf(false));
        if ((localList2 != null) && (localList2.size() > 0))
        {
          e.b("rqdp{  yeseup c>=2,n:}%d" + localList2.size(), new Object[0]);
          localArrayList.addAll(localList2);
          localList2.clear();
        }
      }
      a(localArrayList);
      c.b(paramContext, localArrayList);
      return localArrayList;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }

  // ERROR //
  public final void done(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_1
    //   3: anewarray 41	java/lang/Object
    //   6: astore_3
    //   7: iload_1
    //   8: ifeq +150 -> 158
    //   11: ldc_w 753
    //   14: astore 4
    //   16: aload_3
    //   17: iconst_0
    //   18: aload 4
    //   20: aastore
    //   21: ldc_w 755
    //   24: aload_3
    //   25: invokestatic 662	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   28: aload_0
    //   29: getfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   32: astore 5
    //   34: aload 5
    //   36: ifnull +229 -> 265
    //   39: iload_1
    //   40: ifeq +225 -> 265
    //   43: invokestatic 683	com/tencent/feedback/eup/e:k	()Lcom/tencent/feedback/eup/e;
    //   46: invokevirtual 688	com/tencent/feedback/eup/e:q	()Lcom/tencent/feedback/eup/CrashStrategyBean;
    //   49: invokevirtual 693	com/tencent/feedback/eup/CrashStrategyBean:isMerged	()Z
    //   52: istore 19
    //   54: iload 19
    //   56: istore 7
    //   58: iload 7
    //   60: ifeq +218 -> 278
    //   63: invokestatic 737	com/tencent/feedback/anr/a:f	()J
    //   66: lstore 9
    //   68: aload_0
    //   69: getfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   72: invokeinterface 606 1 0
    //   77: astore 11
    //   79: new 261	java/util/ArrayList
    //   82: dup
    //   83: invokespecial 262	java/util/ArrayList:<init>	()V
    //   86: astore 12
    //   88: aload 11
    //   90: invokeinterface 483 1 0
    //   95: ifeq +84 -> 179
    //   98: aload 11
    //   100: invokeinterface 487 1 0
    //   105: checkcast 158	com/tencent/feedback/eup/d
    //   108: astore 17
    //   110: aload 17
    //   112: invokevirtual 188	com/tencent/feedback/eup/d:i	()J
    //   115: lload 9
    //   117: lcmp
    //   118: ifle -30 -> 88
    //   121: aload 17
    //   123: iconst_1
    //   124: invokevirtual 757	com/tencent/feedback/eup/d:d	(Z)V
    //   127: aload 17
    //   129: iconst_0
    //   130: invokevirtual 631	com/tencent/feedback/eup/d:a	(I)V
    //   133: aload 12
    //   135: aload 17
    //   137: invokeinterface 758 2 0
    //   142: pop
    //   143: aload 11
    //   145: invokeinterface 761 1 0
    //   150: goto -62 -> 88
    //   153: astore_2
    //   154: aload_0
    //   155: monitorexit
    //   156: aload_2
    //   157: athrow
    //   158: ldc_w 763
    //   161: astore 4
    //   163: goto -147 -> 16
    //   166: astore 6
    //   168: aload 6
    //   170: invokevirtual 97	java/lang/Throwable:printStackTrace	()V
    //   173: iconst_0
    //   174: istore 7
    //   176: goto -118 -> 58
    //   179: aload_0
    //   180: getfield 696	com/tencent/feedback/eup/f:c	Landroid/content/Context;
    //   183: aload 12
    //   185: invokestatic 677	com/tencent/feedback/eup/c:b	(Landroid/content/Context;Ljava/util/List;)Z
    //   188: istore 13
    //   190: iconst_2
    //   191: anewarray 41	java/lang/Object
    //   194: astore 14
    //   196: aload 14
    //   198: iconst_0
    //   199: aload_0
    //   200: getfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   203: invokeinterface 605 1 0
    //   208: invokestatic 119	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   211: aastore
    //   212: aload 14
    //   214: iconst_1
    //   215: iload 13
    //   217: invokestatic 167	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   220: aastore
    //   221: ldc_w 765
    //   224: aload 14
    //   226: invokestatic 377	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   229: aload_0
    //   230: getfield 696	com/tencent/feedback/eup/f:c	Landroid/content/Context;
    //   233: aload_0
    //   234: getfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   237: invokestatic 619	com/tencent/feedback/eup/c:a	(Landroid/content/Context;Ljava/util/List;)I
    //   240: istore 15
    //   242: iconst_1
    //   243: anewarray 41	java/lang/Object
    //   246: astore 16
    //   248: aload 16
    //   250: iconst_0
    //   251: iload 15
    //   253: invokestatic 119	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   256: aastore
    //   257: ldc_w 767
    //   260: aload 16
    //   262: invokestatic 377	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   265: aload_0
    //   266: aconst_null
    //   267: putfield 26	com/tencent/feedback/eup/f:f	Lcommon/RequestPackage;
    //   270: aload_0
    //   271: aconst_null
    //   272: putfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   275: aload_0
    //   276: monitorexit
    //   277: return
    //   278: aload_0
    //   279: getfield 696	com/tencent/feedback/eup/f:c	Landroid/content/Context;
    //   282: aload_0
    //   283: getfield 28	com/tencent/feedback/eup/f:g	Ljava/util/List;
    //   286: invokestatic 619	com/tencent/feedback/eup/c:a	(Landroid/content/Context;Ljava/util/List;)I
    //   289: istore 8
    //   291: new 194	java/lang/StringBuilder
    //   294: dup
    //   295: ldc_w 769
    //   298: invokespecial 412	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   301: iload 8
    //   303: invokevirtual 747	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   306: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   309: iconst_0
    //   310: anewarray 41	java/lang/Object
    //   313: invokestatic 377	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   316: goto -51 -> 265
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	153	finally
    //   16	34	153	finally
    //   43	54	153	finally
    //   63	88	153	finally
    //   88	150	153	finally
    //   168	173	153	finally
    //   179	265	153	finally
    //   265	275	153	finally
    //   278	316	153	finally
    //   43	54	166	java/lang/Throwable
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.eup.f
 * JD-Core Version:    0.6.0
 */