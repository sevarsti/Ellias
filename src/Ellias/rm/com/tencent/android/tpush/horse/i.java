package com.tencent.android.tpush.horse;

import android.os.Handler;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.a.a;
import java.util.Timer;

public class i
{
  static long a = 1L;
  static long b = 0L;
  private static long i = 0L;
  private static long j = 0L;
  private static int l;
  private final Object c = new Object();
  private volatile int d = 0;
  private volatile boolean e = false;
  private long f;
  private n g;
  private o h;
  private Timer k = new Timer();
  private Handler m = com.tencent.android.tpush.common.c.a().b();
  private b n = new k(this);
  private b o = new l(this);

  public static i a()
  {
    return p.a;
  }

  private void a(int paramInt, String paramString)
  {
    TLog.v("XGHorse", "@@ notifyFail(" + paramInt + "," + paramString + ")");
    if (this.g != null)
      this.g.a(paramInt, paramString);
  }

  private void a(long paramLong)
  {
    TLog.v("XGHorse", "@@ timeLock(" + paramLong + ")");
    this.k.purge();
    this.k.schedule(new m(this), paramLong);
  }

  // ERROR //
  private void a(String paramString)
  {
    // Byte code:
    //   0: ldc 91
    //   2: ldc 152
    //   4: invokestatic 117	com/tencent/android/tpush/logging/TLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   7: invokestatic 157	com/tencent/android/tpush/service/i:e	()Landroid/content/Context;
    //   10: invokestatic 162	com/tencent/android/tpush/service/c/a:g	(Landroid/content/Context;)Z
    //   13: ifne +20 -> 33
    //   16: ldc 91
    //   18: ldc 164
    //   20: invokestatic 166	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   23: aload_0
    //   24: sipush 10100
    //   27: ldc 168
    //   29: invokespecial 142	com/tencent/android/tpush/horse/i:a	(ILjava/lang/String;)V
    //   32: return
    //   33: invokestatic 173	com/tencent/android/tpush/horse/t:j	()Lcom/tencent/android/tpush/horse/t;
    //   36: invokevirtual 176	com/tencent/android/tpush/horse/t:c	()Z
    //   39: ifne +12 -> 51
    //   42: invokestatic 181	com/tencent/android/tpush/horse/h:j	()Lcom/tencent/android/tpush/horse/h;
    //   45: invokevirtual 182	com/tencent/android/tpush/horse/h:c	()Z
    //   48: ifeq +11 -> 59
    //   51: ldc 91
    //   53: ldc 184
    //   55: invokestatic 166	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   58: return
    //   59: invokestatic 157	com/tencent/android/tpush/service/i:e	()Landroid/content/Context;
    //   62: aload_1
    //   63: new 186	com/tencent/android/tpush/horse/data/OptStrategyList
    //   66: dup
    //   67: invokespecial 187	com/tencent/android/tpush/horse/data/OptStrategyList:<init>	()V
    //   70: invokestatic 193	com/tencent/android/tpush/service/cache/CacheManager:addOptStrategyList	(Landroid/content/Context;Ljava/lang/String;Lcom/tencent/android/tpush/horse/data/OptStrategyList;)Z
    //   73: pop
    //   74: aload_1
    //   75: ldc 195
    //   77: invokevirtual 201	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   80: ifne +21 -> 101
    //   83: aload_1
    //   84: ldc 203
    //   86: invokevirtual 201	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   89: ifne +12 -> 101
    //   92: aload_1
    //   93: ldc 205
    //   95: invokevirtual 201	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   98: ifeq +88 -> 186
    //   101: invokestatic 157	com/tencent/android/tpush/service/i:e	()Landroid/content/Context;
    //   104: aload_1
    //   105: invokestatic 209	com/tencent/android/tpush/service/cache/CacheManager:getServerItems	(Landroid/content/Context;Ljava/lang/String;)Ljava/util/ArrayList;
    //   108: astore 34
    //   110: aload 34
    //   112: astore 6
    //   114: ldc 91
    //   116: ldc 211
    //   118: invokestatic 166	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   121: aload 6
    //   123: ifnonnull +843 -> 966
    //   126: new 213	java/util/ArrayList
    //   129: dup
    //   130: invokespecial 214	java/util/ArrayList:<init>	()V
    //   133: astore 7
    //   135: invokestatic 219	com/tencent/android/tpush/horse/DefaultServer:a	()Ljava/util/ArrayList;
    //   138: invokevirtual 223	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   141: astore 8
    //   143: aload 8
    //   145: invokeinterface 228 1 0
    //   150: ifeq +142 -> 292
    //   153: aload 8
    //   155: invokeinterface 232 1 0
    //   160: checkcast 234	com/tencent/android/tpush/horse/data/ServerItem
    //   163: astore 31
    //   165: aload 7
    //   167: aload 31
    //   169: invokevirtual 237	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   172: ifne -29 -> 143
    //   175: aload 7
    //   177: aload 31
    //   179: invokevirtual 240	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   182: pop
    //   183: goto -40 -> 143
    //   186: aload_1
    //   187: invokestatic 243	com/tencent/android/tpush/horse/DefaultServer:a	(Ljava/lang/String;)Ljava/util/ArrayList;
    //   190: astore 36
    //   192: aload 36
    //   194: astore 6
    //   196: ldc 91
    //   198: ldc 245
    //   200: invokestatic 166	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   203: goto -82 -> 121
    //   206: astore 35
    //   208: aload 6
    //   210: astore 4
    //   212: aload 35
    //   214: astore_3
    //   215: ldc 91
    //   217: new 93	java/lang/StringBuilder
    //   220: dup
    //   221: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   224: ldc 247
    //   226: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   229: aload_3
    //   230: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
    //   233: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   236: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   239: invokestatic 250	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   242: aload_1
    //   243: invokestatic 243	com/tencent/android/tpush/horse/DefaultServer:a	(Ljava/lang/String;)Ljava/util/ArrayList;
    //   246: astore 33
    //   248: aload 33
    //   250: astore 6
    //   252: goto -131 -> 121
    //   255: astore 5
    //   257: ldc 91
    //   259: new 93	java/lang/StringBuilder
    //   262: dup
    //   263: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   266: ldc 252
    //   268: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: aload 5
    //   273: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
    //   276: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   279: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   282: invokestatic 250	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   285: aload 4
    //   287: astore 6
    //   289: goto -168 -> 121
    //   292: invokestatic 157	com/tencent/android/tpush/service/i:e	()Landroid/content/Context;
    //   295: invokestatic 258	com/tencent/android/tpush/horse/Tools:getChannelType	(Landroid/content/Context;)I
    //   298: istore 9
    //   300: ldc 91
    //   302: new 93	java/lang/StringBuilder
    //   305: dup
    //   306: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   309: ldc_w 260
    //   312: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: iload 9
    //   317: invokevirtual 103	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   320: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   323: invokestatic 166	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   326: iload 9
    //   328: tableswitch	default:+28 -> 356, 1:+101->429, 2:+218->546, 3:+335->663
    //   357: iconst_4
    //   358: aload_1
    //   359: invokestatic 265	com/tencent/android/tpush/horse/s:a	(Ljava/util/List;Ljava/lang/String;)Ljava/util/List;
    //   362: astore 29
    //   364: aload 29
    //   366: astore 25
    //   368: aload 7
    //   370: aload_1
    //   371: invokestatic 267	com/tencent/android/tpush/horse/s:b	(Ljava/util/List;Ljava/lang/String;)Ljava/util/List;
    //   374: astore 30
    //   376: aload 30
    //   378: astore 26
    //   380: invokestatic 173	com/tencent/android/tpush/horse/t:j	()Lcom/tencent/android/tpush/horse/t;
    //   383: aload_0
    //   384: getfield 74	com/tencent/android/tpush/horse/i:o	Lcom/tencent/android/tpush/horse/b;
    //   387: invokevirtual 270	com/tencent/android/tpush/horse/t:a	(Lcom/tencent/android/tpush/horse/b;)V
    //   390: invokestatic 173	com/tencent/android/tpush/horse/t:j	()Lcom/tencent/android/tpush/horse/t;
    //   393: aload 25
    //   395: invokevirtual 273	com/tencent/android/tpush/horse/t:a	(Ljava/util/List;)V
    //   398: invokestatic 173	com/tencent/android/tpush/horse/t:j	()Lcom/tencent/android/tpush/horse/t;
    //   401: invokevirtual 275	com/tencent/android/tpush/horse/t:h	()V
    //   404: invokestatic 181	com/tencent/android/tpush/horse/h:j	()Lcom/tencent/android/tpush/horse/h;
    //   407: aload_0
    //   408: getfield 69	com/tencent/android/tpush/horse/i:n	Lcom/tencent/android/tpush/horse/b;
    //   411: invokevirtual 276	com/tencent/android/tpush/horse/h:a	(Lcom/tencent/android/tpush/horse/b;)V
    //   414: invokestatic 181	com/tencent/android/tpush/horse/h:j	()Lcom/tencent/android/tpush/horse/h;
    //   417: aload 26
    //   419: invokevirtual 277	com/tencent/android/tpush/horse/h:a	(Ljava/util/List;)V
    //   422: invokestatic 181	com/tencent/android/tpush/horse/h:j	()Lcom/tencent/android/tpush/horse/h;
    //   425: invokevirtual 278	com/tencent/android/tpush/horse/h:h	()V
    //   428: return
    //   429: aload 7
    //   431: aload_1
    //   432: invokestatic 265	com/tencent/android/tpush/horse/s:a	(Ljava/util/List;Ljava/lang/String;)Ljava/util/List;
    //   435: astore 22
    //   437: invokestatic 173	com/tencent/android/tpush/horse/t:j	()Lcom/tencent/android/tpush/horse/t;
    //   440: aload_0
    //   441: getfield 74	com/tencent/android/tpush/horse/i:o	Lcom/tencent/android/tpush/horse/b;
    //   444: invokevirtual 270	com/tencent/android/tpush/horse/t:a	(Lcom/tencent/android/tpush/horse/b;)V
    //   447: invokestatic 173	com/tencent/android/tpush/horse/t:j	()Lcom/tencent/android/tpush/horse/t;
    //   450: aload 22
    //   452: invokevirtual 273	com/tencent/android/tpush/horse/t:a	(Ljava/util/List;)V
    //   455: invokestatic 173	com/tencent/android/tpush/horse/t:j	()Lcom/tencent/android/tpush/horse/t;
    //   458: invokevirtual 275	com/tencent/android/tpush/horse/t:h	()V
    //   461: return
    //   462: astore 21
    //   464: ldc 91
    //   466: new 93	java/lang/StringBuilder
    //   469: dup
    //   470: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   473: ldc_w 280
    //   476: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   479: aload 21
    //   481: invokevirtual 281	com/tencent/android/tpush/service/channel/exception/NullReturnException:toString	()Ljava/lang/String;
    //   484: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   487: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   490: invokestatic 250	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   493: aload_0
    //   494: sipush 10101
    //   497: ldc_w 283
    //   500: invokespecial 142	com/tencent/android/tpush/horse/i:a	(ILjava/lang/String;)V
    //   503: return
    //   504: astore 20
    //   506: ldc 91
    //   508: new 93	java/lang/StringBuilder
    //   511: dup
    //   512: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   515: ldc_w 285
    //   518: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   521: aload 20
    //   523: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
    //   526: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   529: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   532: invokestatic 250	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   535: aload_0
    //   536: sipush 10101
    //   539: ldc_w 283
    //   542: invokespecial 142	com/tencent/android/tpush/horse/i:a	(ILjava/lang/String;)V
    //   545: return
    //   546: aload 7
    //   548: aload_1
    //   549: invokestatic 267	com/tencent/android/tpush/horse/s:b	(Ljava/util/List;Ljava/lang/String;)Ljava/util/List;
    //   552: astore 19
    //   554: invokestatic 181	com/tencent/android/tpush/horse/h:j	()Lcom/tencent/android/tpush/horse/h;
    //   557: aload_0
    //   558: getfield 69	com/tencent/android/tpush/horse/i:n	Lcom/tencent/android/tpush/horse/b;
    //   561: invokevirtual 276	com/tencent/android/tpush/horse/h:a	(Lcom/tencent/android/tpush/horse/b;)V
    //   564: invokestatic 181	com/tencent/android/tpush/horse/h:j	()Lcom/tencent/android/tpush/horse/h;
    //   567: aload 19
    //   569: invokevirtual 277	com/tencent/android/tpush/horse/h:a	(Ljava/util/List;)V
    //   572: invokestatic 181	com/tencent/android/tpush/horse/h:j	()Lcom/tencent/android/tpush/horse/h;
    //   575: invokevirtual 278	com/tencent/android/tpush/horse/h:h	()V
    //   578: return
    //   579: astore 18
    //   581: ldc 91
    //   583: new 93	java/lang/StringBuilder
    //   586: dup
    //   587: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   590: ldc_w 287
    //   593: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   596: aload 18
    //   598: invokevirtual 281	com/tencent/android/tpush/service/channel/exception/NullReturnException:toString	()Ljava/lang/String;
    //   601: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   604: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   607: invokestatic 250	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   610: aload_0
    //   611: sipush 10101
    //   614: ldc_w 289
    //   617: invokespecial 142	com/tencent/android/tpush/horse/i:a	(ILjava/lang/String;)V
    //   620: return
    //   621: astore 17
    //   623: ldc 91
    //   625: new 93	java/lang/StringBuilder
    //   628: dup
    //   629: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   632: ldc_w 291
    //   635: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   638: aload 17
    //   640: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
    //   643: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   646: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   649: invokestatic 250	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   652: aload_0
    //   653: sipush 10101
    //   656: ldc_w 289
    //   659: invokespecial 142	com/tencent/android/tpush/horse/i:a	(ILjava/lang/String;)V
    //   662: return
    //   663: aload 7
    //   665: aload_1
    //   666: invokestatic 267	com/tencent/android/tpush/horse/s:b	(Ljava/util/List;Ljava/lang/String;)Ljava/util/List;
    //   669: astore 12
    //   671: new 213	java/util/ArrayList
    //   674: dup
    //   675: invokespecial 214	java/util/ArrayList:<init>	()V
    //   678: astore 13
    //   680: aload 12
    //   682: invokeinterface 294 1 0
    //   687: astore 14
    //   689: aload 14
    //   691: invokeinterface 228 1 0
    //   696: ifeq +78 -> 774
    //   699: aload 14
    //   701: invokeinterface 232 1 0
    //   706: checkcast 296	com/tencent/android/tpush/horse/data/StrategyItem
    //   709: astore 15
    //   711: aload 15
    //   713: invokevirtual 298	com/tencent/android/tpush/horse/data/StrategyItem:h	()Z
    //   716: ifeq -27 -> 689
    //   719: aload 13
    //   721: aload 15
    //   723: invokeinterface 299 2 0
    //   728: pop
    //   729: goto -40 -> 689
    //   732: astore 11
    //   734: ldc 91
    //   736: new 93	java/lang/StringBuilder
    //   739: dup
    //   740: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   743: ldc_w 301
    //   746: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   749: aload 11
    //   751: invokevirtual 281	com/tencent/android/tpush/service/channel/exception/NullReturnException:toString	()Ljava/lang/String;
    //   754: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   757: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   760: invokestatic 250	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   763: aload_0
    //   764: sipush 10101
    //   767: ldc_w 303
    //   770: invokespecial 142	com/tencent/android/tpush/horse/i:a	(ILjava/lang/String;)V
    //   773: return
    //   774: invokestatic 181	com/tencent/android/tpush/horse/h:j	()Lcom/tencent/android/tpush/horse/h;
    //   777: aload_0
    //   778: getfield 69	com/tencent/android/tpush/horse/i:n	Lcom/tencent/android/tpush/horse/b;
    //   781: invokevirtual 276	com/tencent/android/tpush/horse/h:a	(Lcom/tencent/android/tpush/horse/b;)V
    //   784: invokestatic 181	com/tencent/android/tpush/horse/h:j	()Lcom/tencent/android/tpush/horse/h;
    //   787: aload 13
    //   789: invokevirtual 277	com/tencent/android/tpush/horse/h:a	(Ljava/util/List;)V
    //   792: invokestatic 181	com/tencent/android/tpush/horse/h:j	()Lcom/tencent/android/tpush/horse/h;
    //   795: invokevirtual 278	com/tencent/android/tpush/horse/h:h	()V
    //   798: return
    //   799: astore 10
    //   801: ldc 91
    //   803: new 93	java/lang/StringBuilder
    //   806: dup
    //   807: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   810: ldc_w 305
    //   813: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   816: aload 10
    //   818: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
    //   821: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   824: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   827: invokestatic 250	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   830: aload_0
    //   831: sipush 10101
    //   834: ldc_w 303
    //   837: invokespecial 142	com/tencent/android/tpush/horse/i:a	(ILjava/lang/String;)V
    //   840: return
    //   841: astore 27
    //   843: aload 27
    //   845: astore 28
    //   847: aconst_null
    //   848: astore 25
    //   850: ldc 91
    //   852: new 93	java/lang/StringBuilder
    //   855: dup
    //   856: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   859: ldc_w 307
    //   862: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   865: aload 28
    //   867: invokevirtual 281	com/tencent/android/tpush/service/channel/exception/NullReturnException:toString	()Ljava/lang/String;
    //   870: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   873: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   876: invokestatic 250	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   879: aload_0
    //   880: sipush 10101
    //   883: ldc_w 309
    //   886: invokespecial 142	com/tencent/android/tpush/horse/i:a	(ILjava/lang/String;)V
    //   889: aconst_null
    //   890: astore 26
    //   892: goto -512 -> 380
    //   895: astore 23
    //   897: aload 23
    //   899: astore 24
    //   901: aconst_null
    //   902: astore 25
    //   904: ldc 91
    //   906: new 93	java/lang/StringBuilder
    //   909: dup
    //   910: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   913: ldc_w 311
    //   916: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   919: aload 24
    //   921: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
    //   924: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   927: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   930: invokestatic 250	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   933: aload_0
    //   934: sipush 10101
    //   937: ldc_w 309
    //   940: invokespecial 142	com/tencent/android/tpush/horse/i:a	(ILjava/lang/String;)V
    //   943: aconst_null
    //   944: astore 26
    //   946: goto -566 -> 380
    //   949: astore 24
    //   951: goto -47 -> 904
    //   954: astore 28
    //   956: goto -106 -> 850
    //   959: astore_3
    //   960: aconst_null
    //   961: astore 4
    //   963: goto -748 -> 215
    //   966: aload 6
    //   968: astore 7
    //   970: goto -835 -> 135
    //
    // Exception table:
    //   from	to	target	type
    //   114	121	206	java/lang/Exception
    //   196	203	206	java/lang/Exception
    //   242	248	255	java/lang/Exception
    //   429	461	462	com/tencent/android/tpush/service/channel/exception/NullReturnException
    //   429	461	504	java/lang/Exception
    //   546	578	579	com/tencent/android/tpush/service/channel/exception/NullReturnException
    //   546	578	621	java/lang/Exception
    //   663	689	732	com/tencent/android/tpush/service/channel/exception/NullReturnException
    //   689	729	732	com/tencent/android/tpush/service/channel/exception/NullReturnException
    //   774	798	732	com/tencent/android/tpush/service/channel/exception/NullReturnException
    //   663	689	799	java/lang/Exception
    //   689	729	799	java/lang/Exception
    //   774	798	799	java/lang/Exception
    //   356	364	841	com/tencent/android/tpush/service/channel/exception/NullReturnException
    //   356	364	895	java/lang/Exception
    //   368	376	949	java/lang/Exception
    //   368	376	954	com/tencent/android/tpush/service/channel/exception/NullReturnException
    //   74	101	959	java/lang/Exception
    //   101	110	959	java/lang/Exception
    //   186	192	959	java/lang/Exception
  }

  public void a(n paramn)
  {
    monitorenter;
    try
    {
      TLog.v("XGHorse", "@@ checkAndSetupClient(" + paramn + ")");
      this.d = 0;
      this.g = paramn;
      if (this.m == null)
      {
        TLog.i("XGHorse", "@@ responderHandler is null");
        a(10110, "responderHandler is null");
      }
      while (true)
      {
        return;
        this.m.post(new j(this));
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(o paramo)
  {
    this.h = paramo;
  }

  // ERROR //
  public void b()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 91
    //   4: ldc_w 343
    //   7: invokestatic 117	com/tencent/android/tpush/logging/TLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   10: aload_0
    //   11: getfield 52	com/tencent/android/tpush/horse/i:k	Ljava/util/Timer;
    //   14: astore_2
    //   15: aload_2
    //   16: monitorenter
    //   17: invokestatic 349	java/lang/System:currentTimeMillis	()J
    //   20: getstatic 36	com/tencent/android/tpush/horse/i:i	J
    //   23: lsub
    //   24: putstatic 38	com/tencent/android/tpush/horse/i:j	J
    //   27: ldc2_w 350
    //   30: getstatic 32	com/tencent/android/tpush/horse/i:a	J
    //   33: lmul
    //   34: putstatic 34	com/tencent/android/tpush/horse/i:b	J
    //   37: invokestatic 349	java/lang/System:currentTimeMillis	()J
    //   40: putstatic 36	com/tencent/android/tpush/horse/i:i	J
    //   43: invokestatic 157	com/tencent/android/tpush/service/i:e	()Landroid/content/Context;
    //   46: ifnull +13 -> 59
    //   49: getstatic 38	com/tencent/android/tpush/horse/i:j	J
    //   52: getstatic 34	com/tencent/android/tpush/horse/i:b	J
    //   55: lcmp
    //   56: ifge +66 -> 122
    //   59: ldc 91
    //   61: ldc_w 353
    //   64: invokestatic 166	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   67: lconst_1
    //   68: getstatic 32	com/tencent/android/tpush/horse/i:a	J
    //   71: ladd
    //   72: putstatic 32	com/tencent/android/tpush/horse/i:a	J
    //   75: getstatic 32	com/tencent/android/tpush/horse/i:a	J
    //   78: ldc2_w 354
    //   81: lcmp
    //   82: ifgt +17 -> 99
    //   85: getstatic 38	com/tencent/android/tpush/horse/i:j	J
    //   88: ldc2_w 356
    //   91: getstatic 34	com/tencent/android/tpush/horse/i:b	J
    //   94: lmul
    //   95: lcmp
    //   96: ifle +21 -> 117
    //   99: ldc 91
    //   101: ldc_w 359
    //   104: invokestatic 166	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   107: getstatic 32	com/tencent/android/tpush/horse/i:a	J
    //   110: ldc2_w 356
    //   113: ldiv
    //   114: putstatic 32	com/tencent/android/tpush/horse/i:a	J
    //   117: aload_2
    //   118: monitorexit
    //   119: aload_0
    //   120: monitorexit
    //   121: return
    //   122: ldc2_w 350
    //   125: getstatic 32	com/tencent/android/tpush/horse/i:a	J
    //   128: lmul
    //   129: lstore 4
    //   131: aload_2
    //   132: monitorexit
    //   133: lload 4
    //   135: lconst_0
    //   136: lcmp
    //   137: ifle -18 -> 119
    //   140: aload_0
    //   141: lload 4
    //   143: invokespecial 361	com/tencent/android/tpush/horse/i:a	(J)V
    //   146: goto -27 -> 119
    //   149: astore_1
    //   150: aload_0
    //   151: monitorexit
    //   152: aload_1
    //   153: athrow
    //   154: astore_3
    //   155: aload_2
    //   156: monitorexit
    //   157: aload_3
    //   158: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	17	149	finally
    //   140	146	149	finally
    //   157	159	149	finally
    //   17	59	154	finally
    //   59	99	154	finally
    //   99	117	154	finally
    //   117	119	154	finally
    //   122	133	154	finally
    //   155	157	154	finally
  }

  public void c()
  {
    l = 1 + l;
    TLog.v("XGHorse", "@@ onCreateSocketErr errCount : " + l + "," + a.t);
    if (l < a.t)
    {
      a().a(com.tencent.android.tpush.service.c.c.f(com.tencent.android.tpush.service.i.e()));
      return;
    }
    a(10101, "create socket err");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.i
 * JD-Core Version:    0.6.0
 */