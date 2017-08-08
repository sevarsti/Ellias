package com.tencent.feedback.eup;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class b
  implements Thread.UncaughtExceptionHandler
{
  private static b c = null;
  private Thread.UncaughtExceptionHandler a = null;
  private Context b = null;

  private b(Context paramContext)
  {
    this.b = paramContext.getApplicationContext();
  }

  private int a(List<d> paramList, int paramInt, boolean paramBoolean)
  {
    if ((paramList == null) || (paramInt <= 0))
      return 0;
    ArrayList localArrayList = new ArrayList();
    Collections.sort(paramList, new Comparator()
    {
    });
    Iterator localIterator = paramList.iterator();
    while ((localIterator.hasNext()) && (paramInt > localArrayList.size()))
    {
      d locald = (d)localIterator.next();
      if ((locald.b()) && (!paramBoolean))
        break;
      localArrayList.add(locald);
      localIterator.remove();
    }
    if (localArrayList.size() > 0)
      return c.a(this.b, localArrayList);
    return 0;
  }

  public static b a(Context paramContext)
  {
    monitorenter;
    try
    {
      if ((c == null) && (paramContext != null))
        c = new b(paramContext);
      b localb = c;
      return localb;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  public static d a(Context paramContext, String paramString1, long paramLong1, java.util.Map<String, com.tencent.feedback.common.PlugInInfo> paramMap, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, long paramLong2, String paramString8, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 67	com/tencent/feedback/eup/d
    //   3: dup
    //   4: invokespecial 88	com/tencent/feedback/eup/d:<init>	()V
    //   7: astore 15
    //   9: aload 15
    //   11: aload 5
    //   13: invokevirtual 92	com/tencent/feedback/eup/d:i	(Ljava/lang/String;)V
    //   16: aload 15
    //   18: aload 6
    //   20: invokevirtual 95	com/tencent/feedback/eup/d:j	(Ljava/lang/String;)V
    //   23: aload 15
    //   25: lload 11
    //   27: lload_2
    //   28: ladd
    //   29: invokevirtual 98	com/tencent/feedback/eup/d:b	(J)V
    //   32: aload 13
    //   34: ifnull +47 -> 81
    //   37: aload 13
    //   39: invokevirtual 103	java/lang/String:length	()I
    //   42: sipush 10000
    //   45: if_icmple +36 -> 81
    //   48: sipush -10000
    //   51: aload 13
    //   53: invokevirtual 103	java/lang/String:length	()I
    //   56: iadd
    //   57: istore 29
    //   59: aload 13
    //   61: invokevirtual 103	java/lang/String:length	()I
    //   64: istore 30
    //   66: aload 13
    //   68: iload 29
    //   70: iload 30
    //   72: invokevirtual 107	java/lang/String:substring	(II)Ljava/lang/String;
    //   75: astore 31
    //   77: aload 31
    //   79: astore 13
    //   81: aload 14
    //   83: ifnull +76 -> 159
    //   86: aload 14
    //   88: arraylength
    //   89: sipush 10000
    //   92: if_icmple +67 -> 159
    //   95: sipush 10000
    //   98: newarray byte
    //   100: astore 25
    //   102: iconst_m1
    //   103: aload 25
    //   105: arraylength
    //   106: iadd
    //   107: istore 26
    //   109: iconst_m1
    //   110: aload 14
    //   112: arraylength
    //   113: iadd
    //   114: istore 27
    //   116: iload 26
    //   118: iflt +37 -> 155
    //   121: iload 27
    //   123: iflt +32 -> 155
    //   126: aload 25
    //   128: iload 26
    //   130: aload 14
    //   132: iload 27
    //   134: baload
    //   135: bastore
    //   136: iinc 26 255
    //   139: iinc 27 255
    //   142: goto -26 -> 116
    //   145: astore 28
    //   147: aload 28
    //   149: invokevirtual 110	java/lang/Throwable:printStackTrace	()V
    //   152: goto -71 -> 81
    //   155: aload 25
    //   157: astore 14
    //   159: aload 15
    //   161: aload 13
    //   163: invokevirtual 113	com/tencent/feedback/eup/d:l	(Ljava/lang/String;)V
    //   166: aload 15
    //   168: aload 14
    //   170: invokevirtual 116	com/tencent/feedback/eup/d:b	([B)V
    //   173: aload 15
    //   175: aload 7
    //   177: invokevirtual 118	com/tencent/feedback/eup/d:c	(Ljava/lang/String;)V
    //   180: aload 9
    //   182: ifnull +14 -> 196
    //   185: aload 9
    //   187: invokevirtual 122	java/lang/String:trim	()Ljava/lang/String;
    //   190: invokevirtual 103	java/lang/String:length	()I
    //   193: ifne +272 -> 465
    //   196: ldc 124
    //   198: astore 9
    //   200: aload 15
    //   202: aload 9
    //   204: invokevirtual 126	com/tencent/feedback/eup/d:b	(Ljava/lang/String;)V
    //   207: aload 15
    //   209: aload 8
    //   211: invokevirtual 128	com/tencent/feedback/eup/d:a	(Ljava/lang/String;)V
    //   214: aload 10
    //   216: ifnull +14 -> 230
    //   219: aload 10
    //   221: invokevirtual 122	java/lang/String:trim	()Ljava/lang/String;
    //   224: invokevirtual 103	java/lang/String:length	()I
    //   227: ifne +7 -> 234
    //   230: ldc 130
    //   232: astore 10
    //   234: aload 15
    //   236: aload 10
    //   238: invokevirtual 133	com/tencent/feedback/eup/d:d	(Ljava/lang/String;)V
    //   241: aload 15
    //   243: ldc 134
    //   245: invokevirtual 137	com/tencent/feedback/eup/d:a	(F)V
    //   248: aload_0
    //   249: invokestatic 142	com/tencent/feedback/common/d:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/d;
    //   252: pop
    //   253: aload 15
    //   255: aload_0
    //   256: invokestatic 146	com/tencent/feedback/common/d:h	(Landroid/content/Context;)J
    //   259: invokevirtual 148	com/tencent/feedback/eup/d:c	(J)V
    //   262: aload 15
    //   264: aload_0
    //   265: invokestatic 142	com/tencent/feedback/common/d:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/d;
    //   268: invokevirtual 151	com/tencent/feedback/common/d:j	()J
    //   271: invokevirtual 154	com/tencent/feedback/eup/d:e	(J)V
    //   274: aload_0
    //   275: invokestatic 142	com/tencent/feedback/common/d:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/d;
    //   278: pop
    //   279: aload 15
    //   281: invokestatic 156	com/tencent/feedback/common/d:i	()J
    //   284: invokevirtual 158	com/tencent/feedback/eup/d:d	(J)V
    //   287: aload 15
    //   289: aload_1
    //   290: invokevirtual 160	com/tencent/feedback/eup/d:e	(Ljava/lang/String;)V
    //   293: aload 15
    //   295: aload_1
    //   296: invokevirtual 163	com/tencent/feedback/eup/d:k	(Ljava/lang/String;)V
    //   299: aload 15
    //   301: aload 4
    //   303: invokevirtual 166	com/tencent/feedback/eup/d:a	(Ljava/util/Map;)V
    //   306: aload 15
    //   308: new 168	java/lang/StringBuilder
    //   311: dup
    //   312: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   315: invokestatic 173	com/tencent/feedback/anr/a:h	()Ljava/lang/String;
    //   318: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: invokevirtual 180	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   324: invokevirtual 183	com/tencent/feedback/eup/d:m	(Ljava/lang/String;)V
    //   327: new 168	java/lang/StringBuilder
    //   330: dup
    //   331: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   334: astore 18
    //   336: aload_0
    //   337: invokestatic 142	com/tencent/feedback/common/d:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/d;
    //   340: pop
    //   341: iconst_3
    //   342: anewarray 100	java/lang/String
    //   345: dup
    //   346: iconst_0
    //   347: ldc 185
    //   349: aastore
    //   350: dup
    //   351: iconst_1
    //   352: ldc 187
    //   354: aastore
    //   355: dup
    //   356: iconst_2
    //   357: ldc 189
    //   359: aastore
    //   360: invokestatic 192	com/tencent/feedback/anr/a:a	([Ljava/lang/String;)Ljava/util/ArrayList;
    //   363: astore 20
    //   365: aload 20
    //   367: ifnull +123 -> 490
    //   370: aload 20
    //   372: invokeinterface 61 1 0
    //   377: ifle +113 -> 490
    //   380: aload 20
    //   382: iconst_0
    //   383: invokeinterface 196 2 0
    //   388: checkcast 100	java/lang/String
    //   391: astore 21
    //   393: aload 15
    //   395: aload 18
    //   397: aload 21
    //   399: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   402: invokevirtual 180	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   405: invokevirtual 199	com/tencent/feedback/eup/d:n	(Ljava/lang/String;)V
    //   408: iconst_1
    //   409: anewarray 4	java/lang/Object
    //   412: astore 22
    //   414: aload 22
    //   416: iconst_0
    //   417: aload 15
    //   419: invokevirtual 202	com/tencent/feedback/eup/d:A	()Ljava/lang/String;
    //   422: aastore
    //   423: ldc 204
    //   425: aload 22
    //   427: invokestatic 209	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   430: iconst_1
    //   431: anewarray 4	java/lang/Object
    //   434: astore 23
    //   436: aload 23
    //   438: iconst_0
    //   439: aload 15
    //   441: invokevirtual 212	com/tencent/feedback/eup/d:D	()Ljava/lang/String;
    //   444: aastore
    //   445: ldc 214
    //   447: aload 23
    //   449: invokestatic 209	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   452: aload 15
    //   454: areturn
    //   455: astore 24
    //   457: aload 24
    //   459: invokevirtual 110	java/lang/Throwable:printStackTrace	()V
    //   462: goto -303 -> 159
    //   465: aload 9
    //   467: invokevirtual 103	java/lang/String:length	()I
    //   470: sipush 1000
    //   473: if_icmple -273 -> 200
    //   476: aload 9
    //   478: iconst_0
    //   479: sipush 1000
    //   482: invokevirtual 107	java/lang/String:substring	(II)Ljava/lang/String;
    //   485: astore 9
    //   487: goto -287 -> 200
    //   490: ldc 216
    //   492: astore 21
    //   494: goto -101 -> 393
    //
    // Exception table:
    //   from	to	target	type
    //   48	77	145	java/lang/Throwable
    //   95	116	455	java/lang/Throwable
    //   126	136	455	java/lang/Throwable
  }

  public static d a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, long paramLong, String paramString7, byte[] paramArrayOfByte)
  {
    com.tencent.feedback.common.c localc = com.tencent.feedback.common.c.p();
    return a(paramContext, localc.g(), localc.i(), localc.n(), paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramLong, paramString7, paramArrayOfByte);
  }

  private static void a(ArrayList<String> paramArrayList, Throwable paramThrowable, int paramInt1, int paramInt2, int paramInt3)
  {
    while (true)
    {
      if ((paramArrayList == null) || (paramThrowable == null) || (paramInt1 > paramInt2) || (paramArrayList.size() > paramInt3));
      do
      {
        return;
        paramInt1++;
        StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
        if (arrayOfStackTraceElement == null)
          continue;
        int i = arrayOfStackTraceElement.length;
        for (int j = 0; j < i; j++)
          paramArrayList.add(arrayOfStackTraceElement[j].toString());
      }
      while (paramThrowable.getCause() == null);
      paramArrayList.add("cause by:");
      paramThrowable = paramThrowable.getCause();
    }
  }

  private boolean b(d paramd, CrashStrategyBean paramCrashStrategyBean)
  {
    int i = 0;
    if (paramCrashStrategyBean != null)
    {
      boolean bool = paramCrashStrategyBean.isStoreCrashSdcard();
      i = 0;
      if (bool)
      {
        String str5;
        try
        {
          com.tencent.feedback.common.e.b("save eup logs", new Object[0]);
          com.tencent.feedback.common.c localc = com.tencent.feedback.common.c.p();
          String str1 = localc.c();
          String str2 = localc.d();
          String str3 = paramd.v();
          Locale localLocale = Locale.US;
          Object[] arrayOfObject2 = new Object[9];
          arrayOfObject2[0] = str1;
          arrayOfObject2[1] = str2;
          arrayOfObject2[2] = localc.f();
          arrayOfObject2[3] = str3;
          Date localDate = new Date(paramd.i());
          String str4;
          if (localDate == null)
            str4 = null;
          while (true)
          {
            arrayOfObject2[4] = str4;
            arrayOfObject2[5] = paramd.e();
            arrayOfObject2[6] = paramd.f();
            arrayOfObject2[7] = paramd.h();
            arrayOfObject2[8] = paramd.A();
            str5 = String.format(localLocale, "#--------\npackage:%s\nversion:%s\nsdk:%s\nprocess:%s\ndate:%s\ntype:%s\nmessage:%s\nstack:\n%s\neupID:%s\n", arrayOfObject2);
            if (paramCrashStrategyBean.getStoreDirectoryPath() != null)
              break;
            if (!com.tencent.feedback.common.a.f(this.b))
            {
              return false;
              str4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(localDate);
              continue;
            }
            Context localContext = this.b;
            int j = paramCrashStrategyBean.getCrashSdcardMaxSize();
            com.tencent.feedback.common.e.b("rqdp{  sv sd start}", new Object[0]);
            if ((str5 == null) || (str5.trim().length() <= 0))
              break label419;
            if (Environment.getExternalStorageState().equals("mounted"))
              com.tencent.feedback.anr.a.a(new File(Environment.getExternalStorageDirectory(), "/Tencent/" + com.tencent.feedback.common.a.b(localContext) + "/euplog.txt").getAbsolutePath(), str5, j);
            com.tencent.feedback.common.e.b("rqdp{  sv sd end}", new Object[0]);
          }
        }
        catch (Throwable localThrowable)
        {
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = localThrowable.toString();
          com.tencent.feedback.common.e.c("rqdp{  save error} %s", arrayOfObject1);
          localThrowable.printStackTrace();
          return false;
        }
        File localFile1 = new File(paramCrashStrategyBean.getStoreDirectoryPath());
        if (!localFile1.isFile())
          localFile1 = localFile1.getParentFile();
        File localFile2 = new File(localFile1, "euplog.txt");
        com.tencent.feedback.anr.a.a(localFile2.getAbsolutePath(), str5, paramCrashStrategyBean.getCrashSdcardMaxSize());
        i = 1;
      }
    }
    return i;
    label419: return true;
  }

  private void d()
  {
    2 local2 = new Thread()
    {
      public final void run()
      {
        e.l();
      }
    };
    local2.setName("ImmediateEUP");
    local2.start();
    try
    {
      local2.join(3000L);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
    }
  }

  public final void a()
  {
    monitorenter;
    try
    {
      com.tencent.feedback.common.e.e("rqdp{ eup regist}", new Object[0]);
      Thread.UncaughtExceptionHandler localUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
      if (localUncaughtExceptionHandler != this)
      {
        this.a = localUncaughtExceptionHandler;
        Thread.setDefaultUncaughtExceptionHandler(this);
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final boolean a(d paramd)
  {
    return a(paramd, e.k().q());
  }

  public final boolean a(d paramd, CrashStrategyBean paramCrashStrategyBean)
  {
    if (paramd == null)
    {
      com.tencent.feedback.common.e.c("bean == null ", new Object[0]);
      return false;
    }
    if (paramCrashStrategyBean == null)
    {
      com.tencent.feedback.common.e.c("no strategy!", new Object[0]);
      return false;
    }
    if (paramCrashStrategyBean.isMerged())
    {
      d locald2;
      if (paramd == null)
        locald2 = null;
      label158: label552: 
      while (locald2 != null)
      {
        com.tencent.feedback.common.e.e("merge success return", new Object[0]);
        if ((!locald2.B()) && (locald2.r() >= 2))
        {
          com.tencent.feedback.common.e.e("rqdp{ may be crash too frequent! do immediate upload in merge!}", new Object[0]);
          d();
        }
        return true;
        String str1 = paramd.h();
        if (str1 == null);
        String str3;
        for (String str2 = null; ; str2 = Pattern.compile("(0x[\\d[a-z]]{8})|(\\d)").matcher(str1).replaceAll(""))
        {
          str3 = com.tencent.feedback.anr.a.d(str2);
          if (str3 != null)
            break label158;
          com.tencent.feedback.common.e.c("rqdp{  md5 error!}", new Object[0]);
          locald2 = null;
          break;
        }
        paramd.g(str3);
        paramd.b(true);
        paramd.b(1);
        paramd.a(0);
        List localList2 = c.a(this.b, 1, "desc", -1, str3, -1, -1, -1, -1, -1L, -1L, null);
        if ((localList2 == null) || (localList2.size() <= 0))
        {
          com.tencent.feedback.common.e.b("rqdp{  new one ,no merged!}", new Object[0]);
          locald2 = null;
          continue;
        }
        locald2 = (d)localList2.get(0);
        if ((locald2.q() != null) && (locald2.q().contains(paramd.i())))
        {
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = Long.valueOf(paramd.i());
          com.tencent.feedback.common.e.b("rqdp{ already merged} %d", arrayOfObject3);
          continue;
        }
        locald2.b(1 + locald2.r());
        if (locald2.q() == null)
          locald2.f("");
        locald2.f(locald2.q() + paramd.i() + "\n");
        Context localContext = this.b;
        com.tencent.feedback.common.e.b("rqdp{  EUPDAO.insertOrUpdateEUP() start}", new Object[0]);
        if ((localContext == null) || (locald2 == null))
          com.tencent.feedback.common.e.c("rqdp{  context == null || bean == null,pls check}", new Object[0]);
        ArrayList localArrayList;
        for (boolean bool2 = false; ; bool2 = c.b(localContext, localArrayList))
        {
          if (!bool2)
            break label552;
          Object[] arrayOfObject2 = new Object[4];
          arrayOfObject2[0] = Boolean.valueOf(bool2);
          arrayOfObject2[1] = Integer.valueOf(locald2.r());
          arrayOfObject2[2] = locald2.q();
          arrayOfObject2[3] = Integer.valueOf(locald2.o());
          com.tencent.feedback.common.e.g("rqdp{  eupMeg update success} %b , c:%d , at:%s up:%d", arrayOfObject2);
          if (paramd.u() == null)
            break;
          File localFile = new File(paramd.u());
          if ((!localFile.exists()) || (!localFile.isFile()))
            break;
          localFile.delete();
          break;
          localArrayList = new ArrayList();
          localArrayList.add(locald2);
        }
      }
    }
    int i = paramCrashStrategyBean.getMaxStoredNum();
    List localList1 = c.a(this.b, i + 1, "asc", -1, null, -1, -1, -1, -1, -1L, -1L, null);
    Object localObject1;
    Object localObject2;
    if ((localList1 != null) && (localList1.size() > 0))
    {
      int j = 1 + (localList1.size() - i);
      if ((j > 0) && (a(localList1, j, paramd.b()) < j))
      {
        com.tencent.feedback.common.e.e("can't add more eup!", new Object[0]);
        return false;
      }
      if (localList1.size() > 0)
      {
        d locald1 = (d)localList1.get(0);
        Iterator localIterator = localList1.iterator();
        localObject1 = locald1;
        if (localIterator.hasNext())
        {
          localObject2 = (d)localIterator.next();
          if ((((d)localObject1).i() >= ((d)localObject2).i()) || (!((d)localObject2).b()))
            break label891;
        }
      }
    }
    while (true)
    {
      localObject1 = localObject2;
      break;
      if ((((d)localObject1).b()) && (paramd.i() - ((d)localObject1).i() < 60000L))
      {
        com.tencent.feedback.common.e.e("rqdp{ may be crash too frequent! do immediate upload in not merge!}", new Object[0]);
        d();
      }
      b(paramd, paramCrashStrategyBean);
      if (com.tencent.feedback.common.a.e(this.b))
      {
        com.tencent.feedback.common.e.e("save log", new Object[0]);
        paramd.a(com.tencent.feedback.anr.a.a(paramCrashStrategyBean.getOnlyLogTag(), paramCrashStrategyBean.getMaxLogRow()));
      }
      while (true)
      {
        boolean bool1 = c.a(this.b, paramd);
        Object[] arrayOfObject1 = new Object[4];
        arrayOfObject1[0] = paramd.v();
        arrayOfObject1[1] = Boolean.valueOf(paramd.c());
        arrayOfObject1[2] = Boolean.valueOf(paramd.d());
        arrayOfObject1[3] = Boolean.valueOf(bool1);
        com.tencent.feedback.common.e.g("store new eup pn:%s, isMe:%b , isNa:%b , res:%b ", arrayOfObject1);
        return bool1;
        paramd.a(null);
      }
      label891: localObject2 = localObject1;
    }
  }

  public final boolean a(String paramString1, Throwable paramThrowable, String paramString2, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    String str1 = com.tencent.feedback.common.a.h(this.b);
    String str2;
    if (paramThrowable != null)
    {
      str2 = paramThrowable.toString();
      if (paramThrowable == null)
        break label217;
    }
    String str5;
    StringBuffer localStringBuffer;
    label217: for (String str3 = paramThrowable.getClass().getName(); ; str3 = "")
    {
      int i = 3;
      int j = 100;
      CrashStrategyBean localCrashStrategyBean = CrashReport.getCrashRuntimeStrategy();
      if (localCrashStrategyBean != null)
      {
        i = Math.max(3, localCrashStrategyBean.getMaxStackFrame());
        j = Math.max(100, localCrashStrategyBean.getMaxStackLine());
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(i);
        arrayOfObject[1] = Integer.valueOf(j);
        com.tencent.feedback.common.e.b("change frame:%d  line:%d", arrayOfObject);
      }
      ArrayList localArrayList = new ArrayList();
      a(localArrayList, paramThrowable, 0, i, j);
      int k = localArrayList.size();
      localObject = null;
      str4 = null;
      if (k <= 0)
        break label257;
      str5 = (String)localArrayList.get(0);
      localStringBuffer = new StringBuffer(localArrayList.size());
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
        localStringBuffer.append((String)localIterator.next()).append("\n");
      str2 = "";
      break;
    }
    String str4 = localStringBuffer.toString();
    localStringBuffer.setLength(0);
    com.tencent.feedback.common.e.b("rqdp{ stack:}%s", new Object[] { str4 });
    Object localObject = str5;
    label257: d locald = a(this.b, str1, paramString1, localObject, str3, str2, str4, new Date().getTime(), paramString2, paramArrayOfByte);
    locald.a(paramBoolean);
    return a(locald);
  }

  public final void b()
  {
    monitorenter;
    try
    {
      com.tencent.feedback.common.e.e("rqdp{ eup unregister}", new Object[0]);
      if (Thread.getDefaultUncaughtExceptionHandler() == this)
      {
        Thread.setDefaultUncaughtExceptionHandler(this.a);
        this.a = null;
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final boolean c()
  {
    if (com.tencent.feedback.anr.a.f() < 0L)
    {
      com.tencent.feedback.common.e.e("rqdp{  today fail?}", new Object[0]);
      new Date().getTime();
    }
    List localList = f.a(this.b).a(this.b, 1);
    return (localList != null) && (localList.size() > 0);
  }

  // ERROR //
  public final void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 399	com/tencent/feedback/eup/e:k	()Lcom/tencent/feedback/eup/e;
    //   5: astore 4
    //   7: aload 4
    //   9: ifnonnull +163 -> 172
    //   12: ldc_w 636
    //   15: iconst_0
    //   16: anewarray 4	java/lang/Object
    //   19: invokestatic 351	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   22: aconst_null
    //   23: astore 5
    //   25: aload 5
    //   27: ifnull +12 -> 39
    //   30: aload 5
    //   32: aload_1
    //   33: aload_2
    //   34: invokeinterface 641 3 0
    //   39: aload 5
    //   41: ifnull +316 -> 357
    //   44: aload 5
    //   46: invokeinterface 645 1 0
    //   51: astore 20
    //   53: aload 20
    //   55: astore 7
    //   57: aload 5
    //   59: invokeinterface 648 1 0
    //   64: astore 19
    //   66: aload 19
    //   68: astore 6
    //   70: aload_1
    //   71: ifnonnull +221 -> 292
    //   74: ldc_w 443
    //   77: astore 8
    //   79: aload_0
    //   80: aload 8
    //   82: aload_2
    //   83: aload 6
    //   85: aload 7
    //   87: iconst_1
    //   88: invokevirtual 650	com/tencent/feedback/eup/b:a	(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[BZ)Z
    //   91: istore 9
    //   93: iconst_1
    //   94: anewarray 4	java/lang/Object
    //   97: astore 10
    //   99: aload 10
    //   101: iconst_0
    //   102: iload 9
    //   104: invokestatic 497	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   107: aastore
    //   108: ldc_w 652
    //   111: aload 10
    //   113: invokestatic 385	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   116: aload 5
    //   118: ifnull +248 -> 366
    //   121: aload 5
    //   123: aload_1
    //   124: aload_2
    //   125: invokeinterface 656 3 0
    //   130: istore 14
    //   132: iload 14
    //   134: istore 11
    //   136: iload 11
    //   138: ifeq +31 -> 169
    //   141: aload_0
    //   142: getfield 22	com/tencent/feedback/eup/b:a	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   145: ifnull +189 -> 334
    //   148: ldc_w 658
    //   151: iconst_0
    //   152: anewarray 4	java/lang/Object
    //   155: invokestatic 385	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   158: aload_0
    //   159: getfield 22	com/tencent/feedback/eup/b:a	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   162: aload_1
    //   163: aload_2
    //   164: invokeinterface 660 3 0
    //   169: aload_0
    //   170: monitorexit
    //   171: return
    //   172: aload 4
    //   174: invokevirtual 663	com/tencent/feedback/eup/e:p	()Lcom/tencent/feedback/eup/CrashHandleListener;
    //   177: astore 5
    //   179: goto -154 -> 25
    //   182: astore 21
    //   184: iconst_1
    //   185: anewarray 4	java/lang/Object
    //   188: astore 22
    //   190: aload 22
    //   192: iconst_0
    //   193: aload 21
    //   195: invokevirtual 347	java/lang/Throwable:toString	()Ljava/lang/String;
    //   198: aastore
    //   199: ldc_w 665
    //   202: aload 22
    //   204: invokestatic 667	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   207: aload 21
    //   209: invokevirtual 110	java/lang/Throwable:printStackTrace	()V
    //   212: goto -173 -> 39
    //   215: astore_3
    //   216: aload_0
    //   217: monitorexit
    //   218: aload_3
    //   219: athrow
    //   220: astore 15
    //   222: iconst_1
    //   223: anewarray 4	java/lang/Object
    //   226: astore 16
    //   228: aload 16
    //   230: iconst_0
    //   231: aload 15
    //   233: invokevirtual 347	java/lang/Throwable:toString	()Ljava/lang/String;
    //   236: aastore
    //   237: ldc_w 669
    //   240: aload 16
    //   242: invokestatic 667	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   245: aload 15
    //   247: invokevirtual 110	java/lang/Throwable:printStackTrace	()V
    //   250: aconst_null
    //   251: astore 7
    //   253: goto -196 -> 57
    //   256: astore 17
    //   258: iconst_1
    //   259: anewarray 4	java/lang/Object
    //   262: astore 18
    //   264: aload 18
    //   266: iconst_0
    //   267: aload 17
    //   269: invokevirtual 347	java/lang/Throwable:toString	()Ljava/lang/String;
    //   272: aastore
    //   273: ldc_w 671
    //   276: aload 18
    //   278: invokestatic 667	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   281: aload 17
    //   283: invokevirtual 110	java/lang/Throwable:printStackTrace	()V
    //   286: aconst_null
    //   287: astore 6
    //   289: goto -219 -> 70
    //   292: aload_1
    //   293: invokevirtual 672	java/lang/Thread:getName	()Ljava/lang/String;
    //   296: astore 8
    //   298: goto -219 -> 79
    //   301: astore 12
    //   303: iconst_1
    //   304: anewarray 4	java/lang/Object
    //   307: astore 13
    //   309: aload 13
    //   311: iconst_0
    //   312: aload 12
    //   314: invokevirtual 347	java/lang/Throwable:toString	()Ljava/lang/String;
    //   317: aastore
    //   318: ldc_w 674
    //   321: aload 13
    //   323: invokestatic 667	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   326: aload 12
    //   328: invokevirtual 110	java/lang/Throwable:printStackTrace	()V
    //   331: goto +35 -> 366
    //   334: ldc_w 676
    //   337: iconst_0
    //   338: anewarray 4	java/lang/Object
    //   341: invokestatic 385	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   344: invokestatic 681	android/os/Process:myPid	()I
    //   347: invokestatic 684	android/os/Process:killProcess	(I)V
    //   350: iconst_1
    //   351: invokestatic 689	java/lang/System:exit	(I)V
    //   354: goto -185 -> 169
    //   357: aconst_null
    //   358: astore 6
    //   360: aconst_null
    //   361: astore 7
    //   363: goto -293 -> 70
    //   366: iconst_1
    //   367: istore 11
    //   369: goto -233 -> 136
    //
    // Exception table:
    //   from	to	target	type
    //   30	39	182	java/lang/Throwable
    //   2	7	215	finally
    //   12	22	215	finally
    //   30	39	215	finally
    //   44	53	215	finally
    //   57	66	215	finally
    //   79	116	215	finally
    //   121	132	215	finally
    //   141	169	215	finally
    //   172	179	215	finally
    //   184	212	215	finally
    //   222	250	215	finally
    //   258	286	215	finally
    //   292	298	215	finally
    //   303	331	215	finally
    //   334	354	215	finally
    //   44	53	220	java/lang/Throwable
    //   57	66	256	java/lang/Throwable
    //   121	132	301	java/lang/Throwable
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.eup.b
 * JD-Core Version:    0.6.0
 */