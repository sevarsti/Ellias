package com.tencent.beacon.b;

import android.content.Context;
import com.tencent.beacon.a.d;
import com.tencent.beacon.a.e;
import com.tencent.beacon.event.h;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class c
  implements Runnable
{
  private Context a;
  private List<b> b;

  public c(Context paramContext, List<b> paramList)
  {
    this.a = paramContext;
    this.b = paramList;
  }

  // ERROR //
  private static byte[] a(String paramString1, byte[] paramArrayOfByte, String paramString2, int paramInt, String paramString3)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: new 27	org/apache/http/params/BasicHttpParams
    //   6: dup
    //   7: invokespecial 28	org/apache/http/params/BasicHttpParams:<init>	()V
    //   10: astore 6
    //   12: aload 6
    //   14: ldc 29
    //   16: invokestatic 35	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   19: aload 6
    //   21: ldc 29
    //   23: invokestatic 38	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   26: new 40	org/apache/http/entity/ByteArrayEntity
    //   29: dup
    //   30: aload_1
    //   31: invokespecial 43	org/apache/http/entity/ByteArrayEntity:<init>	([B)V
    //   34: astore 7
    //   36: aload_2
    //   37: invokevirtual 49	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   40: ldc 51
    //   42: invokevirtual 55	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   45: ifeq +167 -> 212
    //   48: new 57	org/apache/http/client/methods/HttpPost
    //   51: dup
    //   52: aload_0
    //   53: invokespecial 60	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   56: astore 8
    //   58: aload 8
    //   60: checkcast 57	org/apache/http/client/methods/HttpPost
    //   63: aload 7
    //   65: invokevirtual 64	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   68: new 66	org/apache/http/impl/client/DefaultHttpClient
    //   71: dup
    //   72: aload 6
    //   74: invokespecial 69	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
    //   77: astore 9
    //   79: aconst_null
    //   80: astore 5
    //   82: aload 4
    //   84: ifnull +77 -> 161
    //   87: aload 4
    //   89: invokevirtual 49	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   92: ldc 71
    //   94: invokevirtual 75	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   97: istore 16
    //   99: aconst_null
    //   100: astore 5
    //   102: iload 16
    //   104: ifeq +57 -> 161
    //   107: new 77	java/lang/StringBuilder
    //   110: dup
    //   111: ldc 79
    //   113: invokespecial 80	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   116: aload 4
    //   118: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   124: iconst_0
    //   125: anewarray 4	java/lang/Object
    //   128: invokestatic 92	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   131: new 94	org/apache/http/HttpHost
    //   134: dup
    //   135: invokestatic 99	android/net/Proxy:getDefaultHost	()Ljava/lang/String;
    //   138: invokestatic 103	android/net/Proxy:getDefaultPort	()I
    //   141: invokespecial 106	org/apache/http/HttpHost:<init>	(Ljava/lang/String;I)V
    //   144: astore 17
    //   146: aload 9
    //   148: invokevirtual 110	org/apache/http/impl/client/DefaultHttpClient:getParams	()Lorg/apache/http/params/HttpParams;
    //   151: ldc 112
    //   153: aload 17
    //   155: invokeinterface 118 3 0
    //   160: pop
    //   161: ldc 120
    //   163: iconst_0
    //   164: anewarray 4	java/lang/Object
    //   167: invokestatic 92	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   170: aload 9
    //   172: aload 8
    //   174: invokevirtual 124	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   177: astore 19
    //   179: aload 19
    //   181: invokeinterface 130 1 0
    //   186: invokeinterface 135 1 0
    //   191: sipush 200
    //   194: if_icmpeq +85 -> 279
    //   197: ldc 137
    //   199: iconst_0
    //   200: anewarray 4	java/lang/Object
    //   203: invokestatic 140	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   206: aconst_null
    //   207: astore 12
    //   209: aload 12
    //   211: areturn
    //   212: aload_1
    //   213: ifnonnull +38 -> 251
    //   216: ldc 142
    //   218: astore 27
    //   220: new 144	org/apache/http/client/methods/HttpGet
    //   223: dup
    //   224: new 77	java/lang/StringBuilder
    //   227: dup
    //   228: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   231: aload_0
    //   232: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: aload 27
    //   237: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   240: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   243: invokespecial 146	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   246: astore 8
    //   248: goto -180 -> 68
    //   251: new 77	java/lang/StringBuilder
    //   254: dup
    //   255: ldc 148
    //   257: invokespecial 80	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   260: new 45	java/lang/String
    //   263: dup
    //   264: aload_1
    //   265: invokespecial 149	java/lang/String:<init>	([B)V
    //   268: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   274: astore 27
    //   276: goto -56 -> 220
    //   279: aload 19
    //   281: invokeinterface 153 1 0
    //   286: astore 20
    //   288: aload 20
    //   290: ifnonnull +14 -> 304
    //   293: ldc 155
    //   295: iconst_0
    //   296: anewarray 4	java/lang/Object
    //   299: invokestatic 140	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   302: aconst_null
    //   303: areturn
    //   304: ldc 157
    //   306: iconst_0
    //   307: anewarray 4	java/lang/Object
    //   310: invokestatic 92	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   313: new 159	java/io/DataInputStream
    //   316: dup
    //   317: aload 20
    //   319: invokeinterface 165 1 0
    //   324: invokespecial 168	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   327: astore 21
    //   329: new 170	java/io/ByteArrayOutputStream
    //   332: dup
    //   333: invokespecial 171	java/io/ByteArrayOutputStream:<init>	()V
    //   336: astore 22
    //   338: aload 21
    //   340: invokevirtual 174	java/io/DataInputStream:read	()I
    //   343: istore 24
    //   345: iload 24
    //   347: iconst_m1
    //   348: if_icmpeq +78 -> 426
    //   351: aload 22
    //   353: iload 24
    //   355: invokevirtual 178	java/io/ByteArrayOutputStream:write	(I)V
    //   358: goto -20 -> 338
    //   361: astore 23
    //   363: aload 23
    //   365: astore 11
    //   367: aconst_null
    //   368: astore 12
    //   370: aload 21
    //   372: astore 5
    //   374: aload 11
    //   376: invokevirtual 181	java/lang/Throwable:printStackTrace	()V
    //   379: aload 11
    //   381: invokevirtual 184	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   384: iconst_0
    //   385: anewarray 4	java/lang/Object
    //   388: invokestatic 140	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   391: aload 5
    //   393: ifnull -184 -> 209
    //   396: aload 5
    //   398: invokevirtual 187	java/io/DataInputStream:close	()V
    //   401: aload 12
    //   403: areturn
    //   404: astore 15
    //   406: aload 15
    //   408: invokevirtual 188	java/io/IOException:printStackTrace	()V
    //   411: aload 15
    //   413: invokevirtual 189	java/io/IOException:getMessage	()Ljava/lang/String;
    //   416: iconst_0
    //   417: anewarray 4	java/lang/Object
    //   420: invokestatic 140	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   423: aload 12
    //   425: areturn
    //   426: aload 22
    //   428: invokevirtual 192	java/io/ByteArrayOutputStream:flush	()V
    //   431: aload 22
    //   433: invokevirtual 196	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   436: astore 25
    //   438: aload 25
    //   440: astore 12
    //   442: ldc 198
    //   444: iconst_0
    //   445: anewarray 4	java/lang/Object
    //   448: invokestatic 92	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   451: aload 21
    //   453: invokevirtual 187	java/io/DataInputStream:close	()V
    //   456: aload 12
    //   458: areturn
    //   459: astore 26
    //   461: aload 26
    //   463: invokevirtual 188	java/io/IOException:printStackTrace	()V
    //   466: aload 26
    //   468: invokevirtual 189	java/io/IOException:getMessage	()Ljava/lang/String;
    //   471: iconst_0
    //   472: anewarray 4	java/lang/Object
    //   475: invokestatic 140	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   478: aload 12
    //   480: areturn
    //   481: astore 13
    //   483: aload 5
    //   485: ifnull +8 -> 493
    //   488: aload 5
    //   490: invokevirtual 187	java/io/DataInputStream:close	()V
    //   493: aload 13
    //   495: athrow
    //   496: astore 14
    //   498: aload 14
    //   500: invokevirtual 188	java/io/IOException:printStackTrace	()V
    //   503: aload 14
    //   505: invokevirtual 189	java/io/IOException:getMessage	()Ljava/lang/String;
    //   508: iconst_0
    //   509: anewarray 4	java/lang/Object
    //   512: invokestatic 140	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   515: goto -22 -> 493
    //   518: astore 13
    //   520: aload 21
    //   522: astore 5
    //   524: goto -41 -> 483
    //   527: astore 10
    //   529: aload 10
    //   531: astore 11
    //   533: aconst_null
    //   534: astore 12
    //   536: aconst_null
    //   537: astore 5
    //   539: goto -165 -> 374
    //   542: astore 11
    //   544: aload 21
    //   546: astore 5
    //   548: goto -174 -> 374
    //
    // Exception table:
    //   from	to	target	type
    //   329	338	361	java/lang/Throwable
    //   338	345	361	java/lang/Throwable
    //   351	358	361	java/lang/Throwable
    //   426	438	361	java/lang/Throwable
    //   396	401	404	java/io/IOException
    //   451	456	459	java/io/IOException
    //   68	79	481	finally
    //   87	99	481	finally
    //   107	161	481	finally
    //   161	206	481	finally
    //   279	288	481	finally
    //   293	302	481	finally
    //   304	329	481	finally
    //   374	391	481	finally
    //   488	493	496	java/io/IOException
    //   329	338	518	finally
    //   338	345	518	finally
    //   351	358	518	finally
    //   426	438	518	finally
    //   442	451	518	finally
    //   68	79	527	java/lang/Throwable
    //   87	99	527	java/lang/Throwable
    //   107	161	527	java/lang/Throwable
    //   161	206	527	java/lang/Throwable
    //   279	288	527	java/lang/Throwable
    //   293	302	527	java/lang/Throwable
    //   304	329	527	java/lang/Throwable
    //   442	451	542	java/lang/Throwable
  }

  public final void run()
  {
    if ((this.a == null) || (this.b == null))
      return;
    d locald = d.m();
    if (locald == null)
    {
      com.tencent.beacon.d.a.d(" common info null", new Object[0]);
      return;
    }
    monitorenter;
    while (true)
    {
      long l1;
      String str2;
      String str4;
      HashMap localHashMap;
      b localb;
      a.b localb2;
      Object localObject2;
      try
      {
        l1 = locald.h();
        String str1 = locald.g();
        monitorexit;
        str2 = com.tencent.beacon.d.b.c(this.a);
        if (str2 != null)
          break label1832;
        str3 = "null";
        str4 = "null";
        if (e.a(this.a) == null)
          break label1825;
        str4 = e.j(this.a);
        if (str4 != null)
          break label1825;
        str5 = "null";
        localHashMap = new HashMap();
        Iterator localIterator = this.b.iterator();
        if (!localIterator.hasNext())
          break label997;
        localb = (b)localIterator.next();
        com.tencent.beacon.d.a.a(" check " + localb.a(), new Object[0]);
        com.tencent.beacon.d.a.b(" address:" + localb.b(), new Object[0]);
        localHashMap.clear();
        localHashMap.put("A28", str1);
        localHashMap.put("A3", a.a(this.a).a());
        localHashMap.put("A33", str5);
        StringBuilder localStringBuilder1 = new StringBuilder();
        e.a(this.a);
        localHashMap.put("A20", e.f(this.a));
        StringBuilder localStringBuilder2 = new StringBuilder();
        e.a(this.a);
        localHashMap.put("A74", e.k(this.a));
        if (!"PG".equals(localb.a()))
          break label926;
        if (!localb.f())
          continue;
        localHashMap.put("test", "Y");
        if (!str3.toLowerCase().contains("wap"))
          continue;
        com.tencent.beacon.d.a.a(" using wap request", new Object[0]);
        localb2 = a.a(localb.b(), str3);
        if (localb2 != null)
          continue;
        localb2 = new a.b();
        if (localb2.a + localb2.b + localb2.c + localb2.d + localb2.e > 0L)
          continue;
        com.tencent.beacon.d.a.a(" elapse bean is not avilable!try apach}", new Object[0]);
        long l11 = System.currentTimeMillis();
        if (a(localb.b(), " ".getBytes(), "post", 60000, str3) == null)
          continue;
        localb2.e = (System.currentTimeMillis() - l11);
        com.tencent.beacon.d.a.a(" get a total time}" + localb2.e, new Object[0]);
        h localh2 = new h();
        localh2.b(localb.b());
        long l9 = new Date().getTime();
        com.tencent.beacon.d.a.a(" loc time:}" + l9, new Object[0]);
        long l10 = l9 + l1;
        com.tencent.beacon.d.a.a(" to stime:}" + l10, new Object[0]);
        localh2.b(l10);
        localh2.a("DN");
        localHashMap.put("A19", str3);
        localHashMap.put("A40", localb2.b);
        localHashMap.put("A34", "unknown");
        localHashMap.put("A39", localb2.f);
        localHashMap.put("A35", localb2.a);
        localHashMap.put("A36", localb2.c);
        localHashMap.put("A38", localb2.e);
        localHashMap.put("A37", localb2.d);
        localh2.a(localHashMap);
        localObject2 = localh2;
        if (localObject2 == null)
          continue;
        com.tencent.beacon.d.a.a(" insert record type " + localb.a(), new Object[0]);
        if (com.tencent.beacon.applog.a.a(this.a, (h)localObject2))
          continue;
        com.tencent.beacon.d.a.a(" insert record fail!", new Object[0]);
      }
      finally
      {
        monitorexit;
      }
      continue;
      label926: label997: Context localContext;
      long l2;
      if ("IP".equals(localb.a()))
      {
        String str7 = localb.b().trim();
        String[] arrayOfString = str7.split(":");
        if ((arrayOfString == null) || (arrayOfString.length != 2))
        {
          com.tencent.beacon.d.a.d(" ip dest wrong }" + str7, new Object[0]);
          localContext = this.a;
          l2 = new Date().getTime();
          com.tencent.beacon.d.a.a(" MonitorDAO.deleteSpeedMonitorSources() start}", new Object[0]);
          if (localContext == null)
          {
            com.tencent.beacon.d.a.a(" MonitorDAO.deleteSpeedMonitorSources() context is null arg}", new Object[0]);
            return;
          }
        }
        else
        {
          if (localb.f())
            localHashMap.put("test", "Y");
          long l6 = a.a(arrayOfString[0], Integer.parseInt(arrayOfString[1]));
          localObject2 = new h();
          ((h)localObject2).b(localb.b());
          long l7 = new Date().getTime();
          com.tencent.beacon.d.a.a(" loc time:}" + l7, new Object[0]);
          long l8 = l7 + l1;
          com.tencent.beacon.d.a.a(" to stime:}" + l8, new Object[0]);
          ((h)localObject2).b(l8);
          ((h)localObject2).a("IP");
          localHashMap.put("A19", str3);
          localHashMap.put("A26", l6);
          ((h)localObject2).a(localHashMap);
          continue;
        }
      }
      else
      {
        boolean bool = "HOST".equals(localb.a());
        localObject2 = null;
        if (!bool)
          continue;
        String str6 = "http://" + localb.c() + localb.b();
        com.tencent.beacon.d.a.a(" host domain test:" + str6, new Object[0]);
        if (str3.toLowerCase().contains("wap"))
          com.tencent.beacon.d.a.a(" using wap request", new Object[0]);
        for (a.b localb1 = a.a(str6, str3); ; localb1 = a.a(localb.d(), localb.c(), localb.b()))
        {
          if (localb1 == null)
            localb1 = new a.b();
          if (localb1.a + localb1.b + localb1.c + localb1.d + localb1.e <= 0L)
          {
            com.tencent.beacon.d.a.a(" elapse bean is not avilable!try apach", new Object[0]);
            long l5 = System.currentTimeMillis();
            if (a(str6, " ".getBytes(), "post", 60000, str3) != null)
            {
              localb1.e = (System.currentTimeMillis() - l5);
              com.tencent.beacon.d.a.a(" get a total time}" + localb1.e, new Object[0]);
            }
          }
          h localh1 = new h();
          localh1.b(localb.b());
          long l3 = new Date().getTime();
          com.tencent.beacon.d.a.a("loc time:" + l3, new Object[0]);
          long l4 = l3 + l1;
          com.tencent.beacon.d.a.a("to stime:" + l4, new Object[0]);
          localh1.b(l4);
          localh1.a("HO");
          localHashMap.put("A19", str3);
          localHashMap.put("A40", localb1.b);
          localHashMap.put("A34", localb.c());
          localHashMap.put("hostip", localb.d());
          localHashMap.put("A39", localb1.f);
          localHashMap.put("A35", localb1.a);
          localHashMap.put("A36", localb1.c);
          localHashMap.put("A38", localb1.e);
          localHashMap.put("A37", localb1.d);
          localh1.a(localHashMap);
          localObject2 = localh1;
          break;
        }
      }
      com.tencent.beacon.a.a.a.a(localContext, new int[] { 5 }, -1L, l2);
      return;
      label1825: String str5 = str4;
      continue;
      label1832: String str3 = str2;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.b.c
 * JD-Core Version:    0.6.0
 */