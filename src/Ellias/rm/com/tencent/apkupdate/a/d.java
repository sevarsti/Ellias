package com.tencent.apkupdate.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;

public final class d
{
  private final byte[] a = new byte[4096];

  private void a(b paramb, DataInputStream paramDataInputStream, DataOutputStream paramDataOutputStream)
  {
    f.a(paramb, paramDataOutputStream);
    int i = paramb.h;
    if (i > 0)
    {
      if (i > 4096);
      for (int k = 4096; ; k = i)
      {
        int m = paramDataInputStream.read(this.a, 0, k);
        if (m <= 0)
          break;
        paramDataOutputStream.write(this.a, 0, m);
        i -= m;
        break;
      }
    }
    if ((0x8 & paramb.c) != 0);
    for (int j = 1; ; j = 0)
    {
      if (j != 0)
        a.a(paramb, paramDataOutputStream);
      return;
    }
  }

  private void a(b paramb, RandomAccessFile paramRandomAccessFile, g paramg, DataOutputStream paramDataOutputStream)
  {
    String str = new String(paramb.s, "utf-8");
    b localb1 = (b)paramg.b.get(str);
    if (localb1 == null)
      throw new Exception();
    paramb.d = localb1.d;
    paramb.h = localb1.h;
    paramb.g = localb1.g;
    paramb.k = localb1.k;
    paramb.t = localb1.t;
    paramb.l = localb1.l;
    paramb.u = localb1.u;
    f.a(paramb, paramDataOutputStream);
    int i = paramb.h;
    if (i > 0)
    {
      b localb2 = (b)paramg.b.get(str);
      if (localb2 == null)
        throw new FileNotFoundException();
      f localf = (f)paramg.a.get(str);
      if (localf == null)
        throw new FileNotFoundException();
      paramRandomAccessFile.seek(30 + localf.b + localf.c + localb2.q);
      int k = i;
      if (k > 0)
      {
        if (k > 4096);
        for (int m = 4096; ; m = k)
        {
          int n = paramRandomAccessFile.read(this.a, 0, m);
          if (n <= 0)
            break;
          paramDataOutputStream.write(this.a, 0, n);
          k -= n;
          break;
        }
      }
    }
    if ((0x8 & paramb.c) != 0);
    for (int j = 1; ; j = 0)
    {
      if (j != 0)
        a.a(paramb, paramDataOutputStream);
      return;
    }
  }

  public static int b(String paramString1, String paramString2, String paramString3)
  {
    int i = -1;
    PackageManager localPackageManager = com.tencent.apkupdate.logic.protocol.b.a().b().getPackageManager();
    do
    {
      PackageInfo localPackageInfo;
      try
      {
        localPackageInfo = localPackageManager.getPackageInfo(paramString1, 0);
        if (localPackageInfo == null)
          return i;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
        return i;
      }
      String str = localPackageInfo.applicationInfo.sourceDir;
      i = new d().a(str, paramString2, paramString3);
    }
    while ((i != 0) || (localPackageManager.getPackageArchiveInfo(paramString3, 1) != null));
    return -11;
  }

  // ERROR //
  public final int a(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: ldc 157
    //   8: ldc 159
    //   10: invokestatic 165	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   13: pop
    //   14: new 56	com/tencent/apkupdate/a/g
    //   17: dup
    //   18: invokespecial 166	com/tencent/apkupdate/a/g:<init>	()V
    //   21: astore 7
    //   23: aload 7
    //   25: aload_1
    //   26: invokevirtual 169	com/tencent/apkupdate/a/g:a	(Ljava/lang/String;)V
    //   29: ldc 157
    //   31: ldc 171
    //   33: invokestatic 165	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   36: pop
    //   37: new 26	java/io/DataInputStream
    //   40: dup
    //   41: new 173	java/io/BufferedInputStream
    //   44: dup
    //   45: new 175	java/io/FileInputStream
    //   48: dup
    //   49: aload_2
    //   50: invokespecial 177	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   53: invokespecial 180	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   56: invokespecial 181	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   59: astore 9
    //   61: new 32	java/io/DataOutputStream
    //   64: dup
    //   65: new 183	java/io/BufferedOutputStream
    //   68: dup
    //   69: new 185	java/io/FileOutputStream
    //   72: dup
    //   73: aload_3
    //   74: invokespecial 186	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   77: invokespecial 189	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   80: invokespecial 190	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   83: astore 10
    //   85: new 100	java/io/RandomAccessFile
    //   88: dup
    //   89: aload_1
    //   90: ldc 192
    //   92: invokespecial 195	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   95: astore 11
    //   97: new 197	java/util/LinkedList
    //   100: dup
    //   101: invokespecial 198	java/util/LinkedList:<init>	()V
    //   104: astore 25
    //   106: aload 9
    //   108: invokevirtual 202	java/io/DataInputStream:readInt	()I
    //   111: istore 28
    //   113: iconst_0
    //   114: istore 29
    //   116: iload 29
    //   118: iload 28
    //   120: if_icmpge +604 -> 724
    //   123: new 20	com/tencent/apkupdate/a/b
    //   126: dup
    //   127: invokespecial 203	com/tencent/apkupdate/a/b:<init>	()V
    //   130: astore 86
    //   132: aload 86
    //   134: aload 9
    //   136: invokevirtual 207	java/io/DataInputStream:readShort	()S
    //   139: putfield 209	com/tencent/apkupdate/a/b:a	S
    //   142: aload 86
    //   144: aload 9
    //   146: invokevirtual 207	java/io/DataInputStream:readShort	()S
    //   149: putfield 210	com/tencent/apkupdate/a/b:b	S
    //   152: aload 86
    //   154: aload 9
    //   156: invokevirtual 207	java/io/DataInputStream:readShort	()S
    //   159: putfield 40	com/tencent/apkupdate/a/b:c	S
    //   162: aload 86
    //   164: aload 9
    //   166: invokevirtual 207	java/io/DataInputStream:readShort	()S
    //   169: putfield 72	com/tencent/apkupdate/a/b:d	S
    //   172: aload 86
    //   174: aload 9
    //   176: invokevirtual 207	java/io/DataInputStream:readShort	()S
    //   179: putfield 213	com/tencent/apkupdate/a/b:e	S
    //   182: aload 86
    //   184: aload 9
    //   186: invokevirtual 207	java/io/DataInputStream:readShort	()S
    //   189: putfield 216	com/tencent/apkupdate/a/b:f	S
    //   192: aload 86
    //   194: aload 9
    //   196: invokevirtual 202	java/io/DataInputStream:readInt	()I
    //   199: putfield 75	com/tencent/apkupdate/a/b:g	I
    //   202: aload 86
    //   204: aload 9
    //   206: invokevirtual 202	java/io/DataInputStream:readInt	()I
    //   209: putfield 24	com/tencent/apkupdate/a/b:h	I
    //   212: aload 86
    //   214: aload 9
    //   216: invokevirtual 202	java/io/DataInputStream:readInt	()I
    //   219: putfield 218	com/tencent/apkupdate/a/b:i	I
    //   222: aload 86
    //   224: aload 9
    //   226: invokevirtual 207	java/io/DataInputStream:readShort	()S
    //   229: putfield 221	com/tencent/apkupdate/a/b:j	S
    //   232: aload 86
    //   234: aload 9
    //   236: invokevirtual 207	java/io/DataInputStream:readShort	()S
    //   239: putfield 78	com/tencent/apkupdate/a/b:k	S
    //   242: aload 86
    //   244: aload 9
    //   246: invokevirtual 207	java/io/DataInputStream:readShort	()S
    //   249: putfield 84	com/tencent/apkupdate/a/b:l	S
    //   252: aload 86
    //   254: aload 9
    //   256: invokevirtual 207	java/io/DataInputStream:readShort	()S
    //   259: putfield 224	com/tencent/apkupdate/a/b:m	S
    //   262: aload 86
    //   264: aload 9
    //   266: invokevirtual 207	java/io/DataInputStream:readShort	()S
    //   269: putfield 227	com/tencent/apkupdate/a/b:n	S
    //   272: aload 86
    //   274: aload 9
    //   276: invokevirtual 207	java/io/DataInputStream:readShort	()S
    //   279: putfield 230	com/tencent/apkupdate/a/b:o	S
    //   282: aload 86
    //   284: aload 9
    //   286: invokevirtual 202	java/io/DataInputStream:readInt	()I
    //   289: putfield 233	com/tencent/apkupdate/a/b:p	I
    //   292: aload 86
    //   294: aload 9
    //   296: invokevirtual 202	java/io/DataInputStream:readInt	()I
    //   299: putfield 98	com/tencent/apkupdate/a/b:q	I
    //   302: aload 86
    //   304: aload 9
    //   306: invokevirtual 237	java/io/DataInputStream:readBoolean	()Z
    //   309: putfield 240	com/tencent/apkupdate/a/b:r	Z
    //   312: aload 86
    //   314: aload 86
    //   316: getfield 221	com/tencent/apkupdate/a/b:j	S
    //   319: newarray byte
    //   321: putfield 49	com/tencent/apkupdate/a/b:s	[B
    //   324: aload 86
    //   326: aload 86
    //   328: getfield 78	com/tencent/apkupdate/a/b:k	S
    //   331: newarray byte
    //   333: putfield 81	com/tencent/apkupdate/a/b:t	[B
    //   336: aload 86
    //   338: aload 86
    //   340: getfield 84	com/tencent/apkupdate/a/b:l	S
    //   343: newarray byte
    //   345: putfield 87	com/tencent/apkupdate/a/b:u	[B
    //   348: aload 86
    //   350: aload 86
    //   352: getfield 224	com/tencent/apkupdate/a/b:m	S
    //   355: newarray byte
    //   357: putfield 243	com/tencent/apkupdate/a/b:v	[B
    //   360: aload 9
    //   362: aload 86
    //   364: getfield 49	com/tencent/apkupdate/a/b:s	[B
    //   367: iconst_0
    //   368: aload 86
    //   370: getfield 221	com/tencent/apkupdate/a/b:j	S
    //   373: invokevirtual 30	java/io/DataInputStream:read	([BII)I
    //   376: pop
    //   377: aload 9
    //   379: aload 86
    //   381: getfield 81	com/tencent/apkupdate/a/b:t	[B
    //   384: iconst_0
    //   385: aload 86
    //   387: getfield 78	com/tencent/apkupdate/a/b:k	S
    //   390: invokevirtual 30	java/io/DataInputStream:read	([BII)I
    //   393: pop
    //   394: aload 9
    //   396: aload 86
    //   398: getfield 87	com/tencent/apkupdate/a/b:u	[B
    //   401: iconst_0
    //   402: aload 86
    //   404: getfield 84	com/tencent/apkupdate/a/b:l	S
    //   407: invokevirtual 30	java/io/DataInputStream:read	([BII)I
    //   410: pop
    //   411: aload 9
    //   413: aload 86
    //   415: getfield 243	com/tencent/apkupdate/a/b:v	[B
    //   418: iconst_0
    //   419: aload 86
    //   421: getfield 224	com/tencent/apkupdate/a/b:m	S
    //   424: invokevirtual 30	java/io/DataInputStream:read	([BII)I
    //   427: pop
    //   428: aload 25
    //   430: aload 86
    //   432: invokevirtual 247	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   435: pop
    //   436: iinc 29 1
    //   439: goto -323 -> 116
    //   442: astore 23
    //   444: aload 23
    //   446: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   449: iconst_m1
    //   450: istore 4
    //   452: new 151	java/lang/Throwable
    //   455: dup
    //   456: invokespecial 249	java/lang/Throwable:<init>	()V
    //   459: athrow
    //   460: astore 15
    //   462: iload 4
    //   464: istore 16
    //   466: aconst_null
    //   467: astore 17
    //   469: aconst_null
    //   470: astore 18
    //   472: ldc 157
    //   474: new 251	java/lang/StringBuilder
    //   477: dup
    //   478: ldc 253
    //   480: invokespecial 254	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   483: iload 16
    //   485: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   488: invokevirtual 262	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   491: invokestatic 165	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   494: pop
    //   495: aload 17
    //   497: ifnull +8 -> 505
    //   500: aload 17
    //   502: invokevirtual 265	java/io/RandomAccessFile:close	()V
    //   505: aload 5
    //   507: ifnull +8 -> 515
    //   510: aload 5
    //   512: invokevirtual 266	java/io/DataOutputStream:close	()V
    //   515: aload 18
    //   517: ifnull +8 -> 525
    //   520: aload 18
    //   522: invokevirtual 267	java/io/DataInputStream:close	()V
    //   525: iload 16
    //   527: ireturn
    //   528: astore 98
    //   530: aload 98
    //   532: invokevirtual 268	java/io/FileNotFoundException:printStackTrace	()V
    //   535: bipush 254
    //   537: istore 4
    //   539: new 151	java/lang/Throwable
    //   542: dup
    //   543: invokespecial 249	java/lang/Throwable:<init>	()V
    //   546: athrow
    //   547: astore 8
    //   549: aconst_null
    //   550: astore 9
    //   552: aconst_null
    //   553: astore 10
    //   555: aconst_null
    //   556: astore 11
    //   558: aload 11
    //   560: ifnull +8 -> 568
    //   563: aload 11
    //   565: invokevirtual 265	java/io/RandomAccessFile:close	()V
    //   568: aload 10
    //   570: ifnull +8 -> 578
    //   573: aload 10
    //   575: invokevirtual 266	java/io/DataOutputStream:close	()V
    //   578: aload 9
    //   580: ifnull +8 -> 588
    //   583: aload 9
    //   585: invokevirtual 267	java/io/DataInputStream:close	()V
    //   588: aload 8
    //   590: athrow
    //   591: astore 97
    //   593: aload 97
    //   595: invokevirtual 268	java/io/FileNotFoundException:printStackTrace	()V
    //   598: bipush 253
    //   600: istore 4
    //   602: new 151	java/lang/Throwable
    //   605: dup
    //   606: invokespecial 249	java/lang/Throwable:<init>	()V
    //   609: athrow
    //   610: astore 96
    //   612: iload 4
    //   614: istore 16
    //   616: aload 9
    //   618: astore 18
    //   620: aconst_null
    //   621: astore 17
    //   623: aconst_null
    //   624: astore 5
    //   626: goto -154 -> 472
    //   629: astore 95
    //   631: aload 95
    //   633: invokevirtual 268	java/io/FileNotFoundException:printStackTrace	()V
    //   636: iconst_m1
    //   637: istore 4
    //   639: new 151	java/lang/Throwable
    //   642: dup
    //   643: invokespecial 249	java/lang/Throwable:<init>	()V
    //   646: athrow
    //   647: astore 94
    //   649: aload 10
    //   651: astore 5
    //   653: iload 4
    //   655: istore 16
    //   657: aload 9
    //   659: astore 18
    //   661: aconst_null
    //   662: astore 17
    //   664: goto -192 -> 472
    //   667: astore 27
    //   669: aload 27
    //   671: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   674: bipush 252
    //   676: istore 4
    //   678: new 151	java/lang/Throwable
    //   681: dup
    //   682: invokespecial 249	java/lang/Throwable:<init>	()V
    //   685: athrow
    //   686: astore 88
    //   688: aload 88
    //   690: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   693: bipush 251
    //   695: istore 4
    //   697: new 151	java/lang/Throwable
    //   700: dup
    //   701: invokespecial 249	java/lang/Throwable:<init>	()V
    //   704: athrow
    //   705: astore 87
    //   707: aload 87
    //   709: invokevirtual 269	java/lang/ClassNotFoundException:printStackTrace	()V
    //   712: bipush 250
    //   714: istore 4
    //   716: new 151	java/lang/Throwable
    //   719: dup
    //   720: invokespecial 249	java/lang/Throwable:<init>	()V
    //   723: athrow
    //   724: ldc 157
    //   726: ldc_w 271
    //   729: invokestatic 165	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   732: pop
    //   733: aload 25
    //   735: invokevirtual 275	java/util/LinkedList:iterator	()Ljava/util/Iterator;
    //   738: astore 32
    //   740: aload 32
    //   742: invokeinterface 280 1 0
    //   747: istore 33
    //   749: iconst_0
    //   750: istore 4
    //   752: iload 33
    //   754: ifeq +83 -> 837
    //   757: aload 32
    //   759: invokeinterface 284 1 0
    //   764: checkcast 20	com/tencent/apkupdate/a/b
    //   767: astore 34
    //   769: aload 34
    //   771: iconst_0
    //   772: putfield 40	com/tencent/apkupdate/a/b:c	S
    //   775: aload 34
    //   777: getfield 240	com/tencent/apkupdate/a/b:r	Z
    //   780: istore 35
    //   782: iconst_0
    //   783: istore 4
    //   785: iload 35
    //   787: ifeq +35 -> 822
    //   790: aload_0
    //   791: aload 34
    //   793: aload 9
    //   795: aload 10
    //   797: invokespecial 286	com/tencent/apkupdate/a/d:a	(Lcom/tencent/apkupdate/a/b;Ljava/io/DataInputStream;Ljava/io/DataOutputStream;)V
    //   800: goto -60 -> 740
    //   803: astore 31
    //   805: aload 31
    //   807: invokevirtual 287	java/lang/Exception:printStackTrace	()V
    //   810: bipush 246
    //   812: istore 4
    //   814: new 151	java/lang/Throwable
    //   817: dup
    //   818: invokespecial 249	java/lang/Throwable:<init>	()V
    //   821: athrow
    //   822: aload_0
    //   823: aload 34
    //   825: aload 11
    //   827: aload 7
    //   829: aload 10
    //   831: invokespecial 289	com/tencent/apkupdate/a/d:a	(Lcom/tencent/apkupdate/a/b;Ljava/io/RandomAccessFile;Lcom/tencent/apkupdate/a/g;Ljava/io/DataOutputStream;)V
    //   834: goto -94 -> 740
    //   837: ldc 157
    //   839: ldc_w 291
    //   842: invokestatic 165	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   845: pop
    //   846: aload 10
    //   848: invokevirtual 294	java/io/DataOutputStream:size	()I
    //   851: istore 37
    //   853: aload 25
    //   855: invokevirtual 275	java/util/LinkedList:iterator	()Ljava/util/Iterator;
    //   858: astore 39
    //   860: aload 39
    //   862: invokeinterface 280 1 0
    //   867: istore 40
    //   869: iconst_0
    //   870: istore 4
    //   872: iload 40
    //   874: ifeq +581 -> 1455
    //   877: aload 39
    //   879: invokeinterface 284 1 0
    //   884: checkcast 20	com/tencent/apkupdate/a/b
    //   887: astore 41
    //   889: aload 10
    //   891: ldc_w 295
    //   894: invokevirtual 299	java/io/DataOutputStream:writeInt	(I)V
    //   897: aload 41
    //   899: getfield 209	com/tencent/apkupdate/a/b:a	S
    //   902: istore 42
    //   904: iload 42
    //   906: sipush 255
    //   909: iand
    //   910: istore 43
    //   912: aload 10
    //   914: sipush 255
    //   917: iload 42
    //   919: bipush 8
    //   921: ishr
    //   922: iand
    //   923: iload 43
    //   925: bipush 8
    //   927: ishl
    //   928: ior
    //   929: i2s
    //   930: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   933: aload 41
    //   935: getfield 210	com/tencent/apkupdate/a/b:b	S
    //   938: istore 44
    //   940: iload 44
    //   942: sipush 255
    //   945: iand
    //   946: istore 45
    //   948: aload 10
    //   950: sipush 255
    //   953: iload 44
    //   955: bipush 8
    //   957: ishr
    //   958: iand
    //   959: iload 45
    //   961: bipush 8
    //   963: ishl
    //   964: ior
    //   965: i2s
    //   966: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   969: aload 41
    //   971: getfield 40	com/tencent/apkupdate/a/b:c	S
    //   974: istore 46
    //   976: iload 46
    //   978: sipush 255
    //   981: iand
    //   982: istore 47
    //   984: aload 10
    //   986: sipush 255
    //   989: iload 46
    //   991: bipush 8
    //   993: ishr
    //   994: iand
    //   995: iload 47
    //   997: bipush 8
    //   999: ishl
    //   1000: ior
    //   1001: i2s
    //   1002: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   1005: aload 41
    //   1007: getfield 72	com/tencent/apkupdate/a/b:d	S
    //   1010: istore 48
    //   1012: iload 48
    //   1014: sipush 255
    //   1017: iand
    //   1018: istore 49
    //   1020: aload 10
    //   1022: sipush 255
    //   1025: iload 48
    //   1027: bipush 8
    //   1029: ishr
    //   1030: iand
    //   1031: iload 49
    //   1033: bipush 8
    //   1035: ishl
    //   1036: ior
    //   1037: i2s
    //   1038: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   1041: aload 41
    //   1043: getfield 213	com/tencent/apkupdate/a/b:e	S
    //   1046: istore 50
    //   1048: iload 50
    //   1050: sipush 255
    //   1053: iand
    //   1054: istore 51
    //   1056: aload 10
    //   1058: sipush 255
    //   1061: iload 50
    //   1063: bipush 8
    //   1065: ishr
    //   1066: iand
    //   1067: iload 51
    //   1069: bipush 8
    //   1071: ishl
    //   1072: ior
    //   1073: i2s
    //   1074: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   1077: aload 41
    //   1079: getfield 216	com/tencent/apkupdate/a/b:f	S
    //   1082: istore 52
    //   1084: iload 52
    //   1086: sipush 255
    //   1089: iand
    //   1090: istore 53
    //   1092: aload 10
    //   1094: sipush 255
    //   1097: iload 52
    //   1099: bipush 8
    //   1101: ishr
    //   1102: iand
    //   1103: iload 53
    //   1105: bipush 8
    //   1107: ishl
    //   1108: ior
    //   1109: i2s
    //   1110: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   1113: aload 10
    //   1115: aload 41
    //   1117: getfield 75	com/tencent/apkupdate/a/b:g	I
    //   1120: invokestatic 305	com/tencent/apkupdate/a/a:a	(I)I
    //   1123: invokevirtual 299	java/io/DataOutputStream:writeInt	(I)V
    //   1126: aload 10
    //   1128: aload 41
    //   1130: getfield 24	com/tencent/apkupdate/a/b:h	I
    //   1133: invokestatic 305	com/tencent/apkupdate/a/a:a	(I)I
    //   1136: invokevirtual 299	java/io/DataOutputStream:writeInt	(I)V
    //   1139: aload 10
    //   1141: aload 41
    //   1143: getfield 218	com/tencent/apkupdate/a/b:i	I
    //   1146: invokestatic 305	com/tencent/apkupdate/a/a:a	(I)I
    //   1149: invokevirtual 299	java/io/DataOutputStream:writeInt	(I)V
    //   1152: aload 41
    //   1154: getfield 221	com/tencent/apkupdate/a/b:j	S
    //   1157: istore 54
    //   1159: iload 54
    //   1161: sipush 255
    //   1164: iand
    //   1165: istore 55
    //   1167: aload 10
    //   1169: sipush 255
    //   1172: iload 54
    //   1174: bipush 8
    //   1176: ishr
    //   1177: iand
    //   1178: iload 55
    //   1180: bipush 8
    //   1182: ishl
    //   1183: ior
    //   1184: i2s
    //   1185: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   1188: aload 41
    //   1190: getfield 84	com/tencent/apkupdate/a/b:l	S
    //   1193: istore 56
    //   1195: iload 56
    //   1197: sipush 255
    //   1200: iand
    //   1201: istore 57
    //   1203: aload 10
    //   1205: sipush 255
    //   1208: iload 56
    //   1210: bipush 8
    //   1212: ishr
    //   1213: iand
    //   1214: iload 57
    //   1216: bipush 8
    //   1218: ishl
    //   1219: ior
    //   1220: i2s
    //   1221: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   1224: aload 41
    //   1226: getfield 224	com/tencent/apkupdate/a/b:m	S
    //   1229: istore 58
    //   1231: iload 58
    //   1233: sipush 255
    //   1236: iand
    //   1237: istore 59
    //   1239: aload 10
    //   1241: sipush 255
    //   1244: iload 58
    //   1246: bipush 8
    //   1248: ishr
    //   1249: iand
    //   1250: iload 59
    //   1252: bipush 8
    //   1254: ishl
    //   1255: ior
    //   1256: i2s
    //   1257: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   1260: aload 41
    //   1262: getfield 227	com/tencent/apkupdate/a/b:n	S
    //   1265: istore 60
    //   1267: iload 60
    //   1269: sipush 255
    //   1272: iand
    //   1273: istore 61
    //   1275: aload 10
    //   1277: sipush 255
    //   1280: iload 60
    //   1282: bipush 8
    //   1284: ishr
    //   1285: iand
    //   1286: iload 61
    //   1288: bipush 8
    //   1290: ishl
    //   1291: ior
    //   1292: i2s
    //   1293: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   1296: aload 41
    //   1298: getfield 230	com/tencent/apkupdate/a/b:o	S
    //   1301: istore 62
    //   1303: iload 62
    //   1305: sipush 255
    //   1308: iand
    //   1309: istore 63
    //   1311: aload 10
    //   1313: sipush 255
    //   1316: iload 62
    //   1318: bipush 8
    //   1320: ishr
    //   1321: iand
    //   1322: iload 63
    //   1324: bipush 8
    //   1326: ishl
    //   1327: ior
    //   1328: i2s
    //   1329: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   1332: aload 10
    //   1334: aload 41
    //   1336: getfield 233	com/tencent/apkupdate/a/b:p	I
    //   1339: invokestatic 305	com/tencent/apkupdate/a/a:a	(I)I
    //   1342: invokevirtual 299	java/io/DataOutputStream:writeInt	(I)V
    //   1345: aload 10
    //   1347: aload 41
    //   1349: getfield 98	com/tencent/apkupdate/a/b:q	I
    //   1352: invokestatic 305	com/tencent/apkupdate/a/a:a	(I)I
    //   1355: invokevirtual 299	java/io/DataOutputStream:writeInt	(I)V
    //   1358: aload 41
    //   1360: getfield 221	com/tencent/apkupdate/a/b:j	S
    //   1363: istore 64
    //   1365: iconst_0
    //   1366: istore 4
    //   1368: iload 64
    //   1370: ifle +13 -> 1383
    //   1373: aload 10
    //   1375: aload 41
    //   1377: getfield 49	com/tencent/apkupdate/a/b:s	[B
    //   1380: invokevirtual 308	java/io/DataOutputStream:write	([B)V
    //   1383: aload 41
    //   1385: getfield 84	com/tencent/apkupdate/a/b:l	S
    //   1388: istore 65
    //   1390: iconst_0
    //   1391: istore 4
    //   1393: iload 65
    //   1395: ifle +13 -> 1408
    //   1398: aload 10
    //   1400: aload 41
    //   1402: getfield 87	com/tencent/apkupdate/a/b:u	[B
    //   1405: invokevirtual 308	java/io/DataOutputStream:write	([B)V
    //   1408: aload 41
    //   1410: getfield 224	com/tencent/apkupdate/a/b:m	S
    //   1413: istore 66
    //   1415: iconst_0
    //   1416: istore 4
    //   1418: iload 66
    //   1420: ifle -560 -> 860
    //   1423: aload 10
    //   1425: aload 41
    //   1427: getfield 243	com/tencent/apkupdate/a/b:v	[B
    //   1430: invokevirtual 308	java/io/DataOutputStream:write	([B)V
    //   1433: goto -573 -> 860
    //   1436: astore 38
    //   1438: aload 38
    //   1440: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   1443: bipush 249
    //   1445: istore 4
    //   1447: new 151	java/lang/Throwable
    //   1450: dup
    //   1451: invokespecial 249	java/lang/Throwable:<init>	()V
    //   1454: athrow
    //   1455: aload 9
    //   1457: invokevirtual 202	java/io/DataInputStream:readInt	()I
    //   1460: istore 68
    //   1462: aload 10
    //   1464: invokevirtual 294	java/io/DataOutputStream:size	()I
    //   1467: iload 37
    //   1469: isub
    //   1470: istore 69
    //   1472: iconst_0
    //   1473: istore 4
    //   1475: iload 68
    //   1477: ldc_w 309
    //   1480: if_icmpne +298 -> 1778
    //   1483: new 311	com/tencent/apkupdate/a/c
    //   1486: dup
    //   1487: invokespecial 312	com/tencent/apkupdate/a/c:<init>	()V
    //   1490: astore 70
    //   1492: aload 70
    //   1494: aload 9
    //   1496: invokevirtual 315	com/tencent/apkupdate/a/c:a	(Ljava/io/DataInputStream;)V
    //   1499: aload 70
    //   1501: iload 37
    //   1503: putfield 317	com/tencent/apkupdate/a/c:f	I
    //   1506: aload 70
    //   1508: iload 69
    //   1510: putfield 319	com/tencent/apkupdate/a/c:e	I
    //   1513: aload 10
    //   1515: ldc_w 309
    //   1518: invokevirtual 299	java/io/DataOutputStream:writeInt	(I)V
    //   1521: aload 70
    //   1523: getfield 320	com/tencent/apkupdate/a/c:a	S
    //   1526: istore 71
    //   1528: iload 71
    //   1530: sipush 255
    //   1533: iand
    //   1534: istore 72
    //   1536: aload 10
    //   1538: sipush 255
    //   1541: iload 71
    //   1543: bipush 8
    //   1545: ishr
    //   1546: iand
    //   1547: iload 72
    //   1549: bipush 8
    //   1551: ishl
    //   1552: ior
    //   1553: i2s
    //   1554: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   1557: aload 70
    //   1559: getfield 321	com/tencent/apkupdate/a/c:b	S
    //   1562: istore 73
    //   1564: iload 73
    //   1566: sipush 255
    //   1569: iand
    //   1570: istore 74
    //   1572: aload 10
    //   1574: sipush 255
    //   1577: iload 73
    //   1579: bipush 8
    //   1581: ishr
    //   1582: iand
    //   1583: iload 74
    //   1585: bipush 8
    //   1587: ishl
    //   1588: ior
    //   1589: i2s
    //   1590: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   1593: aload 70
    //   1595: getfield 322	com/tencent/apkupdate/a/c:c	S
    //   1598: istore 75
    //   1600: iload 75
    //   1602: sipush 255
    //   1605: iand
    //   1606: istore 76
    //   1608: aload 10
    //   1610: sipush 255
    //   1613: iload 75
    //   1615: bipush 8
    //   1617: ishr
    //   1618: iand
    //   1619: iload 76
    //   1621: bipush 8
    //   1623: ishl
    //   1624: ior
    //   1625: i2s
    //   1626: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   1629: aload 70
    //   1631: getfield 323	com/tencent/apkupdate/a/c:d	S
    //   1634: istore 77
    //   1636: iload 77
    //   1638: sipush 255
    //   1641: iand
    //   1642: istore 78
    //   1644: aload 10
    //   1646: sipush 255
    //   1649: iload 77
    //   1651: bipush 8
    //   1653: ishr
    //   1654: iand
    //   1655: iload 78
    //   1657: bipush 8
    //   1659: ishl
    //   1660: ior
    //   1661: i2s
    //   1662: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   1665: aload 10
    //   1667: aload 70
    //   1669: getfield 319	com/tencent/apkupdate/a/c:e	I
    //   1672: invokestatic 305	com/tencent/apkupdate/a/a:a	(I)I
    //   1675: invokevirtual 299	java/io/DataOutputStream:writeInt	(I)V
    //   1678: aload 10
    //   1680: aload 70
    //   1682: getfield 317	com/tencent/apkupdate/a/c:f	I
    //   1685: invokestatic 305	com/tencent/apkupdate/a/a:a	(I)I
    //   1688: invokevirtual 299	java/io/DataOutputStream:writeInt	(I)V
    //   1691: aload 70
    //   1693: getfield 325	com/tencent/apkupdate/a/c:g	S
    //   1696: istore 79
    //   1698: iload 79
    //   1700: sipush 255
    //   1703: iand
    //   1704: istore 80
    //   1706: aload 10
    //   1708: sipush 255
    //   1711: iload 79
    //   1713: bipush 8
    //   1715: ishr
    //   1716: iand
    //   1717: iload 80
    //   1719: bipush 8
    //   1721: ishl
    //   1722: ior
    //   1723: i2s
    //   1724: invokevirtual 302	java/io/DataOutputStream:writeShort	(I)V
    //   1727: aload 70
    //   1729: getfield 325	com/tencent/apkupdate/a/c:g	S
    //   1732: istore 81
    //   1734: iconst_0
    //   1735: istore 4
    //   1737: iload 81
    //   1739: ifle +13 -> 1752
    //   1742: aload 10
    //   1744: aload 70
    //   1746: getfield 327	com/tencent/apkupdate/a/c:h	[B
    //   1749: invokevirtual 308	java/io/DataOutputStream:write	([B)V
    //   1752: ldc 157
    //   1754: ldc_w 329
    //   1757: invokestatic 165	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   1760: pop
    //   1761: aload 11
    //   1763: invokevirtual 265	java/io/RandomAccessFile:close	()V
    //   1766: aload 10
    //   1768: invokevirtual 266	java/io/DataOutputStream:close	()V
    //   1771: aload 9
    //   1773: invokevirtual 267	java/io/DataInputStream:close	()V
    //   1776: iconst_0
    //   1777: ireturn
    //   1778: bipush 248
    //   1780: istore 4
    //   1782: new 151	java/lang/Throwable
    //   1785: dup
    //   1786: invokespecial 249	java/lang/Throwable:<init>	()V
    //   1789: athrow
    //   1790: astore 67
    //   1792: aload 67
    //   1794: invokevirtual 287	java/lang/Exception:printStackTrace	()V
    //   1797: bipush 247
    //   1799: istore 4
    //   1801: new 151	java/lang/Throwable
    //   1804: dup
    //   1805: invokespecial 249	java/lang/Throwable:<init>	()V
    //   1808: athrow
    //   1809: astore 83
    //   1811: aload 83
    //   1813: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   1816: goto -50 -> 1766
    //   1819: astore 84
    //   1821: aload 84
    //   1823: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   1826: goto -55 -> 1771
    //   1829: astore 85
    //   1831: aload 85
    //   1833: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   1836: iconst_0
    //   1837: ireturn
    //   1838: astore 22
    //   1840: aload 22
    //   1842: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   1845: goto -1340 -> 505
    //   1848: astore 21
    //   1850: aload 21
    //   1852: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   1855: goto -1340 -> 515
    //   1858: astore 20
    //   1860: aload 20
    //   1862: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   1865: iload 16
    //   1867: ireturn
    //   1868: astore 14
    //   1870: aload 14
    //   1872: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   1875: goto -1307 -> 568
    //   1878: astore 13
    //   1880: aload 13
    //   1882: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   1885: goto -1307 -> 578
    //   1888: astore 12
    //   1890: aload 12
    //   1892: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   1895: goto -1307 -> 588
    //   1898: astore 8
    //   1900: aconst_null
    //   1901: astore 11
    //   1903: aconst_null
    //   1904: astore 10
    //   1906: goto -1348 -> 558
    //   1909: astore 8
    //   1911: aconst_null
    //   1912: astore 11
    //   1914: goto -1356 -> 558
    //   1917: astore 8
    //   1919: aload 5
    //   1921: astore 10
    //   1923: aload 18
    //   1925: astore 9
    //   1927: aload 17
    //   1929: astore 11
    //   1931: goto -1373 -> 558
    //   1934: astore 26
    //   1936: aload 9
    //   1938: astore 18
    //   1940: iload 4
    //   1942: istore 16
    //   1944: aload 11
    //   1946: astore 17
    //   1948: aload 10
    //   1950: astore 5
    //   1952: goto -1480 -> 472
    //   1955: astore 8
    //   1957: goto -1399 -> 558
    //
    // Exception table:
    //   from	to	target	type
    //   23	29	442	java/io/IOException
    //   14	23	460	java/lang/Throwable
    //   23	29	460	java/lang/Throwable
    //   29	37	460	java/lang/Throwable
    //   37	61	460	java/lang/Throwable
    //   444	449	460	java/lang/Throwable
    //   452	460	460	java/lang/Throwable
    //   530	535	460	java/lang/Throwable
    //   539	547	460	java/lang/Throwable
    //   37	61	528	java/io/FileNotFoundException
    //   14	23	547	finally
    //   23	29	547	finally
    //   29	37	547	finally
    //   37	61	547	finally
    //   444	449	547	finally
    //   452	460	547	finally
    //   530	535	547	finally
    //   539	547	547	finally
    //   61	85	591	java/io/FileNotFoundException
    //   61	85	610	java/lang/Throwable
    //   593	598	610	java/lang/Throwable
    //   602	610	610	java/lang/Throwable
    //   85	97	629	java/io/FileNotFoundException
    //   85	97	647	java/lang/Throwable
    //   631	636	647	java/lang/Throwable
    //   639	647	647	java/lang/Throwable
    //   106	113	667	java/io/IOException
    //   123	436	686	java/io/IOException
    //   123	436	705	java/lang/ClassNotFoundException
    //   733	740	803	java/lang/Exception
    //   740	749	803	java/lang/Exception
    //   757	782	803	java/lang/Exception
    //   790	800	803	java/lang/Exception
    //   822	834	803	java/lang/Exception
    //   853	860	1436	java/io/IOException
    //   860	869	1436	java/io/IOException
    //   877	904	1436	java/io/IOException
    //   912	940	1436	java/io/IOException
    //   948	976	1436	java/io/IOException
    //   984	1012	1436	java/io/IOException
    //   1020	1048	1436	java/io/IOException
    //   1056	1084	1436	java/io/IOException
    //   1092	1159	1436	java/io/IOException
    //   1167	1195	1436	java/io/IOException
    //   1203	1231	1436	java/io/IOException
    //   1239	1267	1436	java/io/IOException
    //   1275	1303	1436	java/io/IOException
    //   1311	1365	1436	java/io/IOException
    //   1373	1383	1436	java/io/IOException
    //   1383	1390	1436	java/io/IOException
    //   1398	1408	1436	java/io/IOException
    //   1408	1415	1436	java/io/IOException
    //   1423	1433	1436	java/io/IOException
    //   1455	1472	1790	java/lang/Exception
    //   1483	1528	1790	java/lang/Exception
    //   1536	1564	1790	java/lang/Exception
    //   1572	1600	1790	java/lang/Exception
    //   1608	1636	1790	java/lang/Exception
    //   1644	1698	1790	java/lang/Exception
    //   1706	1734	1790	java/lang/Exception
    //   1742	1752	1790	java/lang/Exception
    //   1782	1790	1790	java/lang/Exception
    //   1761	1766	1809	java/io/IOException
    //   1766	1771	1819	java/io/IOException
    //   1771	1776	1829	java/io/IOException
    //   500	505	1838	java/io/IOException
    //   510	515	1848	java/io/IOException
    //   520	525	1858	java/io/IOException
    //   563	568	1868	java/io/IOException
    //   573	578	1878	java/io/IOException
    //   583	588	1888	java/io/IOException
    //   61	85	1898	finally
    //   593	598	1898	finally
    //   602	610	1898	finally
    //   85	97	1909	finally
    //   631	636	1909	finally
    //   639	647	1909	finally
    //   472	495	1917	finally
    //   97	106	1934	java/lang/Throwable
    //   106	113	1934	java/lang/Throwable
    //   123	436	1934	java/lang/Throwable
    //   669	674	1934	java/lang/Throwable
    //   678	686	1934	java/lang/Throwable
    //   688	693	1934	java/lang/Throwable
    //   697	705	1934	java/lang/Throwable
    //   707	712	1934	java/lang/Throwable
    //   716	724	1934	java/lang/Throwable
    //   724	733	1934	java/lang/Throwable
    //   733	740	1934	java/lang/Throwable
    //   740	749	1934	java/lang/Throwable
    //   757	782	1934	java/lang/Throwable
    //   790	800	1934	java/lang/Throwable
    //   805	810	1934	java/lang/Throwable
    //   814	822	1934	java/lang/Throwable
    //   822	834	1934	java/lang/Throwable
    //   837	853	1934	java/lang/Throwable
    //   853	860	1934	java/lang/Throwable
    //   860	869	1934	java/lang/Throwable
    //   877	904	1934	java/lang/Throwable
    //   912	940	1934	java/lang/Throwable
    //   948	976	1934	java/lang/Throwable
    //   984	1012	1934	java/lang/Throwable
    //   1020	1048	1934	java/lang/Throwable
    //   1056	1084	1934	java/lang/Throwable
    //   1092	1159	1934	java/lang/Throwable
    //   1167	1195	1934	java/lang/Throwable
    //   1203	1231	1934	java/lang/Throwable
    //   1239	1267	1934	java/lang/Throwable
    //   1275	1303	1934	java/lang/Throwable
    //   1311	1365	1934	java/lang/Throwable
    //   1373	1383	1934	java/lang/Throwable
    //   1383	1390	1934	java/lang/Throwable
    //   1398	1408	1934	java/lang/Throwable
    //   1408	1415	1934	java/lang/Throwable
    //   1423	1433	1934	java/lang/Throwable
    //   1438	1443	1934	java/lang/Throwable
    //   1447	1455	1934	java/lang/Throwable
    //   1455	1472	1934	java/lang/Throwable
    //   1483	1528	1934	java/lang/Throwable
    //   1536	1564	1934	java/lang/Throwable
    //   1572	1600	1934	java/lang/Throwable
    //   1608	1636	1934	java/lang/Throwable
    //   1644	1698	1934	java/lang/Throwable
    //   1706	1734	1934	java/lang/Throwable
    //   1742	1752	1934	java/lang/Throwable
    //   1752	1761	1934	java/lang/Throwable
    //   1782	1790	1934	java/lang/Throwable
    //   1792	1797	1934	java/lang/Throwable
    //   1801	1809	1934	java/lang/Throwable
    //   97	106	1955	finally
    //   106	113	1955	finally
    //   123	436	1955	finally
    //   669	674	1955	finally
    //   678	686	1955	finally
    //   688	693	1955	finally
    //   697	705	1955	finally
    //   707	712	1955	finally
    //   716	724	1955	finally
    //   724	733	1955	finally
    //   733	740	1955	finally
    //   740	749	1955	finally
    //   757	782	1955	finally
    //   790	800	1955	finally
    //   805	810	1955	finally
    //   814	822	1955	finally
    //   822	834	1955	finally
    //   837	853	1955	finally
    //   853	860	1955	finally
    //   860	869	1955	finally
    //   877	904	1955	finally
    //   912	940	1955	finally
    //   948	976	1955	finally
    //   984	1012	1955	finally
    //   1020	1048	1955	finally
    //   1056	1084	1955	finally
    //   1092	1159	1955	finally
    //   1167	1195	1955	finally
    //   1203	1231	1955	finally
    //   1239	1267	1955	finally
    //   1275	1303	1955	finally
    //   1311	1365	1955	finally
    //   1373	1383	1955	finally
    //   1383	1390	1955	finally
    //   1398	1408	1955	finally
    //   1408	1415	1955	finally
    //   1423	1433	1955	finally
    //   1438	1443	1955	finally
    //   1447	1455	1955	finally
    //   1455	1472	1955	finally
    //   1483	1528	1955	finally
    //   1536	1564	1955	finally
    //   1572	1600	1955	finally
    //   1608	1636	1955	finally
    //   1644	1698	1955	finally
    //   1706	1734	1955	finally
    //   1742	1752	1955	finally
    //   1752	1761	1955	finally
    //   1782	1790	1955	finally
    //   1792	1797	1955	finally
    //   1801	1809	1955	finally
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.a.d
 * JD-Core Version:    0.6.0
 */