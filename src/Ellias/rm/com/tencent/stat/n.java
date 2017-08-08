package com.tencent.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.tencent.stat.a.e;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.k;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class n
{
  private static StatLogger e = k.b();
  private static n f = null;
  Handler a = null;
  volatile int b = 0;
  DeviceInfo c = null;
  private w d;
  private HashMap<String, String> g = new HashMap();

  private n(Context paramContext)
  {
    try
    {
      HandlerThread localHandlerThread = new HandlerThread("StatStore");
      localHandlerThread.start();
      e.w("Launch store thread:" + localHandlerThread);
      this.a = new Handler(localHandlerThread.getLooper());
      Context localContext = paramContext.getApplicationContext();
      this.d = new w(localContext);
      this.d.getWritableDatabase();
      this.d.getReadableDatabase();
      b(localContext);
      c();
      f();
      this.a.post(new o(this));
      return;
    }
    catch (Throwable localThrowable)
    {
      e.e(localThrowable);
    }
  }

  public static n a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (f == null)
        f = new n(paramContext);
      n localn = f;
      return localn;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static n b()
  {
    return f;
  }

  private void b(int paramInt)
  {
    monitorenter;
    while (true)
    {
      ArrayList localArrayList1;
      ArrayList localArrayList2;
      try
      {
        int i = this.b;
        if ((i <= 0) || (paramInt <= 0))
          return;
        e.i("Load " + Integer.toString(this.b) + " unsent events");
        localArrayList1 = new ArrayList();
        localArrayList2 = new ArrayList();
        if ((paramInt != -1) && (paramInt <= StatConfig.a()))
          continue;
        paramInt = StatConfig.a();
        this.b -= paramInt;
        c(localArrayList2, paramInt);
        e.i("Peek " + Integer.toString(localArrayList2.size()) + " unsent events.");
        if (localArrayList2.isEmpty())
          continue;
        b(localArrayList2, 2);
        Iterator localIterator = localArrayList2.iterator();
        if (localIterator.hasNext())
        {
          localArrayList1.add(((x)localIterator.next()).b);
          continue;
        }
      }
      catch (Throwable localThrowable)
      {
        e.e(localThrowable);
        continue;
      }
      finally
      {
        monitorexit;
      }
      d.b().b(localArrayList1, new u(this, localArrayList2, paramInt));
    }
  }

  // ERROR //
  private void b(e parame, c paramc)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 223	com/tencent/stat/StatConfig:getMaxStoreEventCount	()I
    //   5: istore 4
    //   7: iload 4
    //   9: ifgt +6 -> 15
    //   12: aload_0
    //   13: monitorexit
    //   14: return
    //   15: aload_0
    //   16: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   19: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   22: invokevirtual 228	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   25: aload_0
    //   26: getfield 40	com/tencent/stat/n:b	I
    //   29: invokestatic 223	com/tencent/stat/StatConfig:getMaxStoreEventCount	()I
    //   32: if_icmple +35 -> 67
    //   35: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   38: ldc 230
    //   40: invokevirtual 233	com/tencent/stat/common/StatLogger:warn	(Ljava/lang/Object;)V
    //   43: aload_0
    //   44: aload_0
    //   45: getfield 40	com/tencent/stat/n:b	I
    //   48: aload_0
    //   49: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   52: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   55: ldc 235
    //   57: ldc 237
    //   59: aconst_null
    //   60: invokevirtual 241	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   63: isub
    //   64: putfield 40	com/tencent/stat/n:b	I
    //   67: new 243	android/content/ContentValues
    //   70: dup
    //   71: invokespecial 244	android/content/ContentValues:<init>	()V
    //   74: astore 9
    //   76: aload_1
    //   77: invokevirtual 248	com/tencent/stat/a/e:d	()Ljava/lang/String;
    //   80: invokestatic 251	com/tencent/stat/common/k:c	(Ljava/lang/String;)Ljava/lang/String;
    //   83: astore 10
    //   85: aload 9
    //   87: ldc 253
    //   89: aload 10
    //   91: invokevirtual 257	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   94: aload 9
    //   96: ldc_w 259
    //   99: ldc_w 261
    //   102: invokevirtual 257	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   105: aload 9
    //   107: ldc_w 263
    //   110: iconst_1
    //   111: invokestatic 158	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   114: invokevirtual 257	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   117: aload 9
    //   119: ldc_w 265
    //   122: aload_1
    //   123: invokevirtual 268	com/tencent/stat/a/e:b	()J
    //   126: invokestatic 274	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   129: invokevirtual 277	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   132: aload_0
    //   133: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   136: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   139: ldc 235
    //   141: aconst_null
    //   142: aload 9
    //   144: invokevirtual 281	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   147: ldc2_w 282
    //   150: lcmp
    //   151: ifne +48 -> 199
    //   154: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   157: new 59	java/lang/StringBuilder
    //   160: dup
    //   161: invokespecial 60	java/lang/StringBuilder:<init>	()V
    //   164: ldc_w 285
    //   167: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: aload 10
    //   172: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: invokevirtual 288	com/tencent/stat/common/StatLogger:error	(Ljava/lang/Object;)V
    //   181: aload_0
    //   182: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   185: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   188: invokevirtual 291	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   191: goto -179 -> 12
    //   194: astore 11
    //   196: goto -184 -> 12
    //   199: aload_0
    //   200: iconst_1
    //   201: aload_0
    //   202: getfield 40	com/tencent/stat/n:b	I
    //   205: iadd
    //   206: putfield 40	com/tencent/stat/n:b	I
    //   209: aload_0
    //   210: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   213: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   216: invokevirtual 294	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   219: aload_2
    //   220: ifnull -39 -> 181
    //   223: aload_2
    //   224: invokeinterface 298 1 0
    //   229: goto -48 -> 181
    //   232: astore 7
    //   234: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   237: aload 7
    //   239: invokevirtual 125	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   242: aload_0
    //   243: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   246: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   249: invokevirtual 291	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   252: goto -240 -> 12
    //   255: astore 8
    //   257: goto -245 -> 12
    //   260: astore 5
    //   262: aload_0
    //   263: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   266: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   269: invokevirtual 291	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   272: aload 5
    //   274: athrow
    //   275: astore_3
    //   276: aload_0
    //   277: monitorexit
    //   278: aload_3
    //   279: athrow
    //   280: astore 6
    //   282: goto -10 -> 272
    //
    // Exception table:
    //   from	to	target	type
    //   181	191	194	java/lang/Throwable
    //   15	67	232	java/lang/Throwable
    //   67	181	232	java/lang/Throwable
    //   199	219	232	java/lang/Throwable
    //   223	229	232	java/lang/Throwable
    //   242	252	255	java/lang/Throwable
    //   15	67	260	finally
    //   67	181	260	finally
    //   199	219	260	finally
    //   223	229	260	finally
    //   234	242	260	finally
    //   2	7	275	finally
    //   181	191	275	finally
    //   242	252	275	finally
    //   262	272	275	finally
    //   272	275	275	finally
    //   262	272	280	java/lang/Throwable
  }

  // ERROR //
  private void b(b paramb)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 302	com/tencent/stat/b:a	()Ljava/lang/String;
    //   6: astore 6
    //   8: aload 6
    //   10: invokestatic 304	com/tencent/stat/common/k:a	(Ljava/lang/String;)Ljava/lang/String;
    //   13: astore 7
    //   15: new 243	android/content/ContentValues
    //   18: dup
    //   19: invokespecial 244	android/content/ContentValues:<init>	()V
    //   22: astore 8
    //   24: aload 8
    //   26: ldc 253
    //   28: aload_1
    //   29: getfield 307	com/tencent/stat/b:b	Lorg/json/JSONObject;
    //   32: invokevirtual 310	org/json/JSONObject:toString	()Ljava/lang/String;
    //   35: invokevirtual 257	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   38: aload 8
    //   40: ldc_w 312
    //   43: aload 7
    //   45: invokevirtual 257	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   48: aload_1
    //   49: aload 7
    //   51: putfield 314	com/tencent/stat/b:c	Ljava/lang/String;
    //   54: aload 8
    //   56: ldc_w 316
    //   59: aload_1
    //   60: getfield 318	com/tencent/stat/b:d	I
    //   63: invokestatic 321	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   66: invokevirtual 324	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   69: aload_0
    //   70: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   73: invokevirtual 107	com/tencent/stat/w:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   76: ldc_w 326
    //   79: aconst_null
    //   80: aconst_null
    //   81: aconst_null
    //   82: aconst_null
    //   83: aconst_null
    //   84: aconst_null
    //   85: invokevirtual 330	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   88: astore 9
    //   90: aload 9
    //   92: astore_3
    //   93: aload_3
    //   94: invokeinterface 335 1 0
    //   99: ifeq +241 -> 340
    //   102: aload_3
    //   103: iconst_0
    //   104: invokeinterface 339 2 0
    //   109: aload_1
    //   110: getfield 341	com/tencent/stat/b:a	I
    //   113: if_icmpne -20 -> 93
    //   116: iconst_1
    //   117: istore 10
    //   119: iconst_1
    //   120: iload 10
    //   122: if_icmpne +96 -> 218
    //   125: aload_0
    //   126: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   129: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   132: astore 11
    //   134: iconst_1
    //   135: anewarray 343	java/lang/String
    //   138: astore 12
    //   140: aload 12
    //   142: iconst_0
    //   143: aload_1
    //   144: getfield 341	com/tencent/stat/b:a	I
    //   147: invokestatic 158	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   150: aastore
    //   151: aload 11
    //   153: ldc_w 326
    //   156: aload 8
    //   158: ldc_w 345
    //   161: aload 12
    //   163: invokevirtual 349	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   166: i2l
    //   167: lstore 13
    //   169: lload 13
    //   171: ldc2_w 282
    //   174: lcmp
    //   175: ifne +79 -> 254
    //   178: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   181: new 59	java/lang/StringBuilder
    //   184: dup
    //   185: invokespecial 60	java/lang/StringBuilder:<init>	()V
    //   188: ldc_w 351
    //   191: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: aload 6
    //   196: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   202: invokevirtual 125	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   205: aload_3
    //   206: ifnull +9 -> 215
    //   209: aload_3
    //   210: invokeinterface 354 1 0
    //   215: aload_0
    //   216: monitorexit
    //   217: return
    //   218: aload 8
    //   220: ldc_w 356
    //   223: aload_1
    //   224: getfield 341	com/tencent/stat/b:a	I
    //   227: invokestatic 321	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   230: invokevirtual 324	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   233: aload_0
    //   234: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   237: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   240: ldc_w 326
    //   243: aconst_null
    //   244: aload 8
    //   246: invokevirtual 281	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   249: lstore 13
    //   251: goto -82 -> 169
    //   254: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   257: new 59	java/lang/StringBuilder
    //   260: dup
    //   261: invokespecial 60	java/lang/StringBuilder:<init>	()V
    //   264: ldc_w 358
    //   267: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   270: aload 6
    //   272: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   278: invokevirtual 360	com/tencent/stat/common/StatLogger:d	(Ljava/lang/Object;)V
    //   281: goto -76 -> 205
    //   284: astore_2
    //   285: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   288: aload_2
    //   289: invokevirtual 125	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   292: aload_3
    //   293: ifnull -78 -> 215
    //   296: aload_3
    //   297: invokeinterface 354 1 0
    //   302: goto -87 -> 215
    //   305: astore 5
    //   307: aload_0
    //   308: monitorexit
    //   309: aload 5
    //   311: athrow
    //   312: astore 4
    //   314: aconst_null
    //   315: astore_3
    //   316: aload_3
    //   317: ifnull +9 -> 326
    //   320: aload_3
    //   321: invokeinterface 354 1 0
    //   326: aload 4
    //   328: athrow
    //   329: astore 4
    //   331: goto -15 -> 316
    //   334: astore_2
    //   335: aconst_null
    //   336: astore_3
    //   337: goto -52 -> 285
    //   340: iconst_0
    //   341: istore 10
    //   343: goto -224 -> 119
    //
    // Exception table:
    //   from	to	target	type
    //   93	116	284	java/lang/Throwable
    //   125	169	284	java/lang/Throwable
    //   178	205	284	java/lang/Throwable
    //   218	251	284	java/lang/Throwable
    //   254	281	284	java/lang/Throwable
    //   209	215	305	finally
    //   296	302	305	finally
    //   320	326	305	finally
    //   326	329	305	finally
    //   2	90	312	finally
    //   93	116	329	finally
    //   125	169	329	finally
    //   178	205	329	finally
    //   218	251	329	finally
    //   254	281	329	finally
    //   285	292	329	finally
    //   2	90	334	java/lang/Throwable
  }

  // ERROR //
  private void b(List<x> paramList)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   5: new 59	java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial 60	java/lang/StringBuilder:<init>	()V
    //   12: ldc_w 364
    //   15: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: aload_1
    //   19: invokeinterface 180 1 0
    //   24: invokevirtual 367	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   27: ldc_w 369
    //   30: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: invokestatic 375	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   36: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   39: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   42: invokevirtual 163	com/tencent/stat/common/StatLogger:i	(Ljava/lang/Object;)V
    //   45: aload_0
    //   46: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   49: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   52: invokevirtual 228	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   55: aload_1
    //   56: invokeinterface 190 1 0
    //   61: astore 7
    //   63: aload 7
    //   65: invokeinterface 195 1 0
    //   70: ifeq +93 -> 163
    //   73: aload 7
    //   75: invokeinterface 199 1 0
    //   80: checkcast 201	com/tencent/stat/x
    //   83: astore 9
    //   85: aload_0
    //   86: getfield 40	com/tencent/stat/n:b	I
    //   89: istore 10
    //   91: aload_0
    //   92: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   95: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   98: astore 11
    //   100: iconst_1
    //   101: anewarray 343	java/lang/String
    //   104: astore 12
    //   106: aload 12
    //   108: iconst_0
    //   109: aload 9
    //   111: getfield 378	com/tencent/stat/x:a	J
    //   114: invokestatic 381	java/lang/Long:toString	(J)Ljava/lang/String;
    //   117: aastore
    //   118: aload_0
    //   119: iload 10
    //   121: aload 11
    //   123: ldc 235
    //   125: ldc_w 383
    //   128: aload 12
    //   130: invokevirtual 241	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   133: isub
    //   134: putfield 40	com/tencent/stat/n:b	I
    //   137: goto -74 -> 63
    //   140: astore 5
    //   142: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   145: aload 5
    //   147: invokevirtual 125	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   150: aload_0
    //   151: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   154: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   157: invokevirtual 291	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   160: aload_0
    //   161: monitorexit
    //   162: return
    //   163: aload_0
    //   164: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   167: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   170: invokevirtual 294	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   173: aload_0
    //   174: aload_0
    //   175: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   178: invokevirtual 107	com/tencent/stat/w:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   181: ldc 235
    //   183: invokestatic 389	android/database/DatabaseUtils:queryNumEntries	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)J
    //   186: l2i
    //   187: putfield 40	com/tencent/stat/n:b	I
    //   190: aload_0
    //   191: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   194: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   197: invokevirtual 291	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   200: goto -40 -> 160
    //   203: astore 8
    //   205: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   208: aload 8
    //   210: invokevirtual 392	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Exception;)V
    //   213: goto -53 -> 160
    //   216: astore_2
    //   217: aload_0
    //   218: monitorexit
    //   219: aload_2
    //   220: athrow
    //   221: astore 6
    //   223: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   226: aload 6
    //   228: invokevirtual 392	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Exception;)V
    //   231: goto -71 -> 160
    //   234: astore_3
    //   235: aload_0
    //   236: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   239: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   242: invokevirtual 291	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   245: aload_3
    //   246: athrow
    //   247: astore 4
    //   249: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   252: aload 4
    //   254: invokevirtual 392	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Exception;)V
    //   257: goto -12 -> 245
    //
    // Exception table:
    //   from	to	target	type
    //   45	63	140	java/lang/Throwable
    //   63	137	140	java/lang/Throwable
    //   163	190	140	java/lang/Throwable
    //   190	200	203	android/database/sqlite/SQLiteException
    //   2	45	216	finally
    //   150	160	216	finally
    //   190	200	216	finally
    //   205	213	216	finally
    //   223	231	216	finally
    //   235	245	216	finally
    //   245	247	216	finally
    //   249	257	216	finally
    //   150	160	221	android/database/sqlite/SQLiteException
    //   45	63	234	finally
    //   63	137	234	finally
    //   142	150	234	finally
    //   163	190	234	finally
    //   235	245	247	android/database/sqlite/SQLiteException
  }

  // ERROR //
  private void b(List<x> paramList, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   5: new 59	java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial 60	java/lang/StringBuilder:<init>	()V
    //   12: ldc_w 394
    //   15: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: aload_1
    //   19: invokeinterface 180 1 0
    //   24: invokevirtual 367	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   27: ldc_w 396
    //   30: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: iload_2
    //   34: invokevirtual 367	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   37: ldc_w 398
    //   40: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: invokestatic 375	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   46: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   49: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: invokevirtual 163	com/tencent/stat/common/StatLogger:i	(Ljava/lang/Object;)V
    //   55: new 243	android/content/ContentValues
    //   58: dup
    //   59: invokespecial 244	android/content/ContentValues:<init>	()V
    //   62: astore 4
    //   64: aload 4
    //   66: ldc_w 263
    //   69: iload_2
    //   70: invokestatic 158	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   73: invokevirtual 257	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   76: aload_0
    //   77: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   80: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   83: invokevirtual 228	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   86: aload_1
    //   87: invokeinterface 190 1 0
    //   92: astore 9
    //   94: aload 9
    //   96: invokeinterface 195 1 0
    //   101: ifeq +266 -> 367
    //   104: aload 9
    //   106: invokeinterface 199 1 0
    //   111: checkcast 201	com/tencent/stat/x
    //   114: astore 11
    //   116: iconst_1
    //   117: aload 11
    //   119: getfield 399	com/tencent/stat/x:d	I
    //   122: iadd
    //   123: invokestatic 402	com/tencent/stat/StatConfig:getMaxSendRetryCount	()I
    //   126: if_icmple +81 -> 207
    //   129: aload_0
    //   130: getfield 40	com/tencent/stat/n:b	I
    //   133: istore 15
    //   135: aload_0
    //   136: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   139: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   142: astore 16
    //   144: iconst_1
    //   145: anewarray 343	java/lang/String
    //   148: astore 17
    //   150: aload 17
    //   152: iconst_0
    //   153: aload 11
    //   155: getfield 378	com/tencent/stat/x:a	J
    //   158: invokestatic 381	java/lang/Long:toString	(J)Ljava/lang/String;
    //   161: aastore
    //   162: aload_0
    //   163: iload 15
    //   165: aload 16
    //   167: ldc 235
    //   169: ldc_w 404
    //   172: aload 17
    //   174: invokevirtual 241	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   177: isub
    //   178: putfield 40	com/tencent/stat/n:b	I
    //   181: goto -87 -> 94
    //   184: astore 7
    //   186: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   189: aload 7
    //   191: invokevirtual 125	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   194: aload_0
    //   195: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   198: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   201: invokevirtual 291	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   204: aload_0
    //   205: monitorexit
    //   206: return
    //   207: aload 4
    //   209: ldc_w 259
    //   212: iconst_1
    //   213: aload 11
    //   215: getfield 399	com/tencent/stat/x:d	I
    //   218: iadd
    //   219: invokestatic 321	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   222: invokevirtual 324	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   225: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   228: new 59	java/lang/StringBuilder
    //   231: dup
    //   232: invokespecial 60	java/lang/StringBuilder:<init>	()V
    //   235: ldc_w 406
    //   238: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   241: aload 11
    //   243: getfield 378	com/tencent/stat/x:a	J
    //   246: invokevirtual 409	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   249: ldc_w 411
    //   252: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: aload 4
    //   257: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   260: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   263: invokevirtual 163	com/tencent/stat/common/StatLogger:i	(Ljava/lang/Object;)V
    //   266: aload_0
    //   267: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   270: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   273: astore 12
    //   275: iconst_1
    //   276: anewarray 343	java/lang/String
    //   279: astore 13
    //   281: aload 13
    //   283: iconst_0
    //   284: aload 11
    //   286: getfield 378	com/tencent/stat/x:a	J
    //   289: invokestatic 381	java/lang/Long:toString	(J)Ljava/lang/String;
    //   292: aastore
    //   293: aload 12
    //   295: ldc 235
    //   297: aload 4
    //   299: ldc_w 404
    //   302: aload 13
    //   304: invokevirtual 349	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   307: istore 14
    //   309: iload 14
    //   311: ifgt -217 -> 94
    //   314: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   317: new 59	java/lang/StringBuilder
    //   320: dup
    //   321: invokespecial 60	java/lang/StringBuilder:<init>	()V
    //   324: ldc_w 413
    //   327: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: iload 14
    //   332: invokestatic 158	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   335: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   341: invokevirtual 125	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   344: goto -250 -> 94
    //   347: astore 5
    //   349: aload_0
    //   350: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   353: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   356: invokevirtual 291	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   359: aload 5
    //   361: athrow
    //   362: astore_3
    //   363: aload_0
    //   364: monitorexit
    //   365: aload_3
    //   366: athrow
    //   367: aload_0
    //   368: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   371: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   374: invokevirtual 294	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   377: aload_0
    //   378: aload_0
    //   379: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   382: invokevirtual 107	com/tencent/stat/w:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   385: ldc 235
    //   387: invokestatic 389	android/database/DatabaseUtils:queryNumEntries	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)J
    //   390: l2i
    //   391: putfield 40	com/tencent/stat/n:b	I
    //   394: aload_0
    //   395: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   398: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   401: invokevirtual 291	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   404: goto -200 -> 204
    //   407: astore 10
    //   409: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   412: aload 10
    //   414: invokevirtual 392	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Exception;)V
    //   417: goto -213 -> 204
    //   420: astore 8
    //   422: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   425: aload 8
    //   427: invokevirtual 392	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Exception;)V
    //   430: goto -226 -> 204
    //   433: astore 6
    //   435: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   438: aload 6
    //   440: invokevirtual 392	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Exception;)V
    //   443: goto -84 -> 359
    //
    // Exception table:
    //   from	to	target	type
    //   55	94	184	java/lang/Throwable
    //   94	181	184	java/lang/Throwable
    //   207	309	184	java/lang/Throwable
    //   314	344	184	java/lang/Throwable
    //   367	394	184	java/lang/Throwable
    //   55	94	347	finally
    //   94	181	347	finally
    //   186	194	347	finally
    //   207	309	347	finally
    //   314	344	347	finally
    //   367	394	347	finally
    //   2	55	362	finally
    //   194	204	362	finally
    //   349	359	362	finally
    //   359	362	362	finally
    //   394	404	362	finally
    //   409	417	362	finally
    //   422	430	362	finally
    //   435	443	362	finally
    //   394	404	407	android/database/sqlite/SQLiteException
    //   194	204	420	android/database/sqlite/SQLiteException
    //   349	359	433	android/database/sqlite/SQLiteException
  }

  // ERROR //
  private void c(List<x> paramList, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   6: invokevirtual 107	com/tencent/stat/w:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   9: astore 7
    //   11: iconst_1
    //   12: anewarray 343	java/lang/String
    //   15: astore 8
    //   17: aload 8
    //   19: iconst_0
    //   20: iconst_1
    //   21: invokestatic 158	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   24: aastore
    //   25: aload 7
    //   27: ldc 235
    //   29: aconst_null
    //   30: ldc_w 415
    //   33: aload 8
    //   35: aconst_null
    //   36: aconst_null
    //   37: ldc_w 417
    //   40: iload_2
    //   41: invokestatic 158	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   44: invokevirtual 420	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   47: astore 9
    //   49: aload 9
    //   51: invokeinterface 335 1 0
    //   56: ifeq +82 -> 138
    //   59: aload_1
    //   60: new 201	com/tencent/stat/x
    //   63: dup
    //   64: aload 9
    //   66: iconst_0
    //   67: invokeinterface 424 2 0
    //   72: aload 9
    //   74: iconst_1
    //   75: invokeinterface 427 2 0
    //   80: invokestatic 429	com/tencent/stat/common/k:d	(Ljava/lang/String;)Ljava/lang/String;
    //   83: aload 9
    //   85: iconst_2
    //   86: invokeinterface 339 2 0
    //   91: aload 9
    //   93: iconst_3
    //   94: invokeinterface 339 2 0
    //   99: invokespecial 432	com/tencent/stat/x:<init>	(JLjava/lang/String;II)V
    //   102: invokeinterface 208 2 0
    //   107: pop
    //   108: goto -59 -> 49
    //   111: astore 4
    //   113: aload 9
    //   115: astore 5
    //   117: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   120: aload 4
    //   122: invokevirtual 125	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   125: aload 5
    //   127: ifnull +10 -> 137
    //   130: aload 5
    //   132: invokeinterface 354 1 0
    //   137: return
    //   138: aload 9
    //   140: ifnull -3 -> 137
    //   143: aload 9
    //   145: invokeinterface 354 1 0
    //   150: return
    //   151: astore 6
    //   153: aload_3
    //   154: ifnull +9 -> 163
    //   157: aload_3
    //   158: invokeinterface 354 1 0
    //   163: aload 6
    //   165: athrow
    //   166: astore 6
    //   168: aload 9
    //   170: astore_3
    //   171: goto -18 -> 153
    //   174: astore 6
    //   176: aload 5
    //   178: astore_3
    //   179: goto -26 -> 153
    //   182: astore 4
    //   184: aconst_null
    //   185: astore 5
    //   187: goto -70 -> 117
    //
    // Exception table:
    //   from	to	target	type
    //   49	108	111	java/lang/Throwable
    //   2	49	151	finally
    //   49	108	166	finally
    //   117	125	174	finally
    //   2	49	182	java/lang/Throwable
  }

  private void e()
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("status", Integer.valueOf(1));
      SQLiteDatabase localSQLiteDatabase = this.d.getWritableDatabase();
      String[] arrayOfString = new String[1];
      arrayOfString[0] = Long.toString(2L);
      localSQLiteDatabase.update("events", localContentValues, "status=?", arrayOfString);
      this.b = (int)DatabaseUtils.queryNumEntries(this.d.getReadableDatabase(), "events");
      e.i("Total " + this.b + " unsent events.");
      return;
    }
    catch (Throwable localThrowable)
    {
      e.e(localThrowable);
    }
  }

  // ERROR //
  private void f()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   4: invokevirtual 107	com/tencent/stat/w:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: ldc_w 438
    //   10: aconst_null
    //   11: aconst_null
    //   12: aconst_null
    //   13: aconst_null
    //   14: aconst_null
    //   15: aconst_null
    //   16: invokevirtual 330	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   19: astore 4
    //   21: aload 4
    //   23: astore_2
    //   24: aload_2
    //   25: invokeinterface 335 1 0
    //   30: ifeq +47 -> 77
    //   33: aload_0
    //   34: getfield 47	com/tencent/stat/n:g	Ljava/util/HashMap;
    //   37: aload_2
    //   38: iconst_0
    //   39: invokeinterface 427 2 0
    //   44: aload_2
    //   45: iconst_1
    //   46: invokeinterface 427 2 0
    //   51: invokevirtual 441	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   54: pop
    //   55: goto -31 -> 24
    //   58: astore_1
    //   59: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   62: aload_1
    //   63: invokevirtual 125	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   66: aload_2
    //   67: ifnull +9 -> 76
    //   70: aload_2
    //   71: invokeinterface 354 1 0
    //   76: return
    //   77: aload_2
    //   78: ifnull -2 -> 76
    //   81: aload_2
    //   82: invokeinterface 354 1 0
    //   87: return
    //   88: astore_3
    //   89: aconst_null
    //   90: astore_2
    //   91: aload_2
    //   92: ifnull +9 -> 101
    //   95: aload_2
    //   96: invokeinterface 354 1 0
    //   101: aload_3
    //   102: athrow
    //   103: astore_3
    //   104: goto -13 -> 91
    //   107: astore_1
    //   108: aconst_null
    //   109: astore_2
    //   110: goto -51 -> 59
    //
    // Exception table:
    //   from	to	target	type
    //   24	55	58	java/lang/Throwable
    //   0	21	88	finally
    //   24	55	103	finally
    //   59	66	103	finally
    //   0	21	107	java/lang/Throwable
  }

  public int a()
  {
    return this.b;
  }

  void a(int paramInt)
  {
    this.a.post(new v(this, paramInt));
  }

  void a(e parame, c paramc)
  {
    if (!StatConfig.isEnableStatService())
      return;
    try
    {
      if (Thread.currentThread().getId() == this.a.getLooper().getThread().getId())
      {
        b(parame, paramc);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      e.e(localThrowable);
      return;
    }
    this.a.post(new r(this, parame, paramc));
  }

  void a(b paramb)
  {
    if (paramb == null)
      return;
    this.a.post(new s(this, paramb));
  }

  void a(List<x> paramList)
  {
    try
    {
      if (Thread.currentThread().getId() == this.a.getLooper().getThread().getId())
      {
        b(paramList);
        return;
      }
      this.a.post(new q(this, paramList));
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      e.e(localSQLiteException);
    }
  }

  void a(List<x> paramList, int paramInt)
  {
    try
    {
      if (Thread.currentThread().getId() == this.a.getLooper().getThread().getId())
      {
        b(paramList, paramInt);
        return;
      }
      this.a.post(new p(this, paramList, paramInt));
      return;
    }
    catch (Throwable localThrowable)
    {
      e.e(localThrowable);
    }
  }

  // ERROR //
  public DeviceInfo b(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 42	com/tencent/stat/n:c	Lcom/tencent/stat/DeviceInfo;
    //   6: ifnull +14 -> 20
    //   9: aload_0
    //   10: getfield 42	com/tencent/stat/n:c	Lcom/tencent/stat/DeviceInfo;
    //   13: astore 7
    //   15: aload_0
    //   16: monitorexit
    //   17: aload 7
    //   19: areturn
    //   20: aload_0
    //   21: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   24: invokevirtual 107	com/tencent/stat/w:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   27: ldc_w 474
    //   30: aconst_null
    //   31: aconst_null
    //   32: aconst_null
    //   33: aconst_null
    //   34: aconst_null
    //   35: aconst_null
    //   36: aconst_null
    //   37: invokevirtual 420	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   40: astore 8
    //   42: aload 8
    //   44: astore 4
    //   46: aload 4
    //   48: invokeinterface 335 1 0
    //   53: istore 9
    //   55: iconst_0
    //   56: istore 10
    //   58: iload 9
    //   60: ifeq +362 -> 422
    //   63: aload 4
    //   65: iconst_0
    //   66: invokeinterface 427 2 0
    //   71: astore 11
    //   73: aload 11
    //   75: invokestatic 429	com/tencent/stat/common/k:d	(Ljava/lang/String;)Ljava/lang/String;
    //   78: astore 12
    //   80: aload 4
    //   82: iconst_1
    //   83: invokeinterface 339 2 0
    //   88: istore 13
    //   90: aload 4
    //   92: iconst_2
    //   93: invokeinterface 427 2 0
    //   98: astore 14
    //   100: aload 4
    //   102: iconst_3
    //   103: invokeinterface 424 2 0
    //   108: lstore 15
    //   110: invokestatic 479	java/lang/System:currentTimeMillis	()J
    //   113: ldc2_w 480
    //   116: ldiv
    //   117: lstore 17
    //   119: iload 13
    //   121: iconst_1
    //   122: if_icmpeq +677 -> 799
    //   125: lload 15
    //   127: ldc2_w 480
    //   130: lmul
    //   131: invokestatic 483	com/tencent/stat/common/k:a	(J)Ljava/lang/String;
    //   134: ldc2_w 480
    //   137: lload 17
    //   139: lmul
    //   140: invokestatic 483	com/tencent/stat/common/k:a	(J)Ljava/lang/String;
    //   143: invokevirtual 486	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   146: ifne +653 -> 799
    //   149: iconst_1
    //   150: istore 19
    //   152: aload 14
    //   154: aload_1
    //   155: invokestatic 490	com/tencent/stat/common/k:r	(Landroid/content/Context;)Ljava/lang/String;
    //   158: invokevirtual 486	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   161: ifne +631 -> 792
    //   164: iload 19
    //   166: iconst_2
    //   167: ior
    //   168: istore 20
    //   170: aload 12
    //   172: ldc_w 492
    //   175: invokevirtual 496	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   178: astore 21
    //   180: aload 21
    //   182: ifnull +424 -> 606
    //   185: aload 21
    //   187: arraylength
    //   188: ifle +418 -> 606
    //   191: aload 21
    //   193: iconst_0
    //   194: aaload
    //   195: astore 41
    //   197: aload 41
    //   199: ifnull +13 -> 212
    //   202: aload 41
    //   204: invokevirtual 499	java/lang/String:length	()I
    //   207: bipush 11
    //   209: if_icmpge +569 -> 778
    //   212: aload_1
    //   213: invokestatic 502	com/tencent/stat/common/k:l	(Landroid/content/Context;)Ljava/lang/String;
    //   216: astore 42
    //   218: aload 42
    //   220: ifnull +548 -> 768
    //   223: aload 42
    //   225: invokevirtual 499	java/lang/String:length	()I
    //   228: bipush 10
    //   230: if_icmple +538 -> 768
    //   233: iconst_1
    //   234: istore 25
    //   236: goto +570 -> 806
    //   239: aload 21
    //   241: ifnull +385 -> 626
    //   244: aload 21
    //   246: arraylength
    //   247: iconst_2
    //   248: if_icmplt +378 -> 626
    //   251: aload 21
    //   253: iconst_1
    //   254: aaload
    //   255: astore 26
    //   257: new 59	java/lang/StringBuilder
    //   260: dup
    //   261: invokespecial 60	java/lang/StringBuilder:<init>	()V
    //   264: aload 23
    //   266: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: ldc_w 492
    //   272: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: aload 26
    //   277: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   283: astore 24
    //   285: aload_0
    //   286: new 504	com/tencent/stat/DeviceInfo
    //   289: dup
    //   290: aload 23
    //   292: aload 26
    //   294: iload 20
    //   296: invokespecial 507	com/tencent/stat/DeviceInfo:<init>	(Ljava/lang/String;Ljava/lang/String;I)V
    //   299: putfield 42	com/tencent/stat/n:c	Lcom/tencent/stat/DeviceInfo;
    //   302: new 243	android/content/ContentValues
    //   305: dup
    //   306: invokespecial 244	android/content/ContentValues:<init>	()V
    //   309: astore 28
    //   311: aload 28
    //   313: ldc_w 509
    //   316: aload 24
    //   318: invokestatic 251	com/tencent/stat/common/k:c	(Ljava/lang/String;)Ljava/lang/String;
    //   321: invokevirtual 257	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   324: aload 28
    //   326: ldc_w 511
    //   329: iload 20
    //   331: invokestatic 321	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   334: invokevirtual 324	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   337: aload 28
    //   339: ldc_w 513
    //   342: aload_1
    //   343: invokestatic 490	com/tencent/stat/common/k:r	(Landroid/content/Context;)Ljava/lang/String;
    //   346: invokevirtual 257	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   349: aload 28
    //   351: ldc_w 515
    //   354: lload 17
    //   356: invokestatic 274	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   359: invokevirtual 277	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   362: iload 25
    //   364: ifeq +31 -> 395
    //   367: aload_0
    //   368: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   371: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   374: ldc_w 474
    //   377: aload 28
    //   379: ldc_w 517
    //   382: iconst_1
    //   383: anewarray 343	java/lang/String
    //   386: dup
    //   387: iconst_0
    //   388: aload 11
    //   390: aastore
    //   391: invokevirtual 349	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   394: pop
    //   395: iload 20
    //   397: iload 13
    //   399: if_icmpeq +363 -> 762
    //   402: aload_0
    //   403: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   406: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   409: ldc_w 474
    //   412: aconst_null
    //   413: aload 28
    //   415: invokevirtual 520	android/database/sqlite/SQLiteDatabase:replace	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   418: pop2
    //   419: iconst_1
    //   420: istore 10
    //   422: iload 10
    //   424: ifne +161 -> 585
    //   427: aload_1
    //   428: invokestatic 522	com/tencent/stat/common/k:b	(Landroid/content/Context;)Ljava/lang/String;
    //   431: astore 32
    //   433: aload_1
    //   434: invokestatic 524	com/tencent/stat/common/k:c	(Landroid/content/Context;)Ljava/lang/String;
    //   437: astore 33
    //   439: aload 33
    //   441: ifnull +314 -> 755
    //   444: aload 33
    //   446: invokevirtual 499	java/lang/String:length	()I
    //   449: ifle +306 -> 755
    //   452: new 59	java/lang/StringBuilder
    //   455: dup
    //   456: invokespecial 60	java/lang/StringBuilder:<init>	()V
    //   459: aload 32
    //   461: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   464: ldc_w 492
    //   467: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   470: aload 33
    //   472: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   475: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   478: astore 34
    //   480: invokestatic 479	java/lang/System:currentTimeMillis	()J
    //   483: ldc2_w 480
    //   486: ldiv
    //   487: lstore 35
    //   489: aload_1
    //   490: invokestatic 490	com/tencent/stat/common/k:r	(Landroid/content/Context;)Ljava/lang/String;
    //   493: astore 37
    //   495: new 243	android/content/ContentValues
    //   498: dup
    //   499: invokespecial 244	android/content/ContentValues:<init>	()V
    //   502: astore 38
    //   504: aload 38
    //   506: ldc_w 509
    //   509: aload 34
    //   511: invokestatic 251	com/tencent/stat/common/k:c	(Ljava/lang/String;)Ljava/lang/String;
    //   514: invokevirtual 257	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   517: aload 38
    //   519: ldc_w 511
    //   522: iconst_0
    //   523: invokestatic 321	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   526: invokevirtual 324	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   529: aload 38
    //   531: ldc_w 513
    //   534: aload 37
    //   536: invokevirtual 257	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   539: aload 38
    //   541: ldc_w 515
    //   544: lload 35
    //   546: invokestatic 274	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   549: invokevirtual 277	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   552: aload_0
    //   553: getfield 100	com/tencent/stat/n:d	Lcom/tencent/stat/w;
    //   556: invokevirtual 104	com/tencent/stat/w:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   559: ldc_w 474
    //   562: aconst_null
    //   563: aload 38
    //   565: invokevirtual 281	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   568: pop2
    //   569: aload_0
    //   570: new 504	com/tencent/stat/DeviceInfo
    //   573: dup
    //   574: aload 32
    //   576: aload 33
    //   578: iconst_0
    //   579: invokespecial 507	com/tencent/stat/DeviceInfo:<init>	(Ljava/lang/String;Ljava/lang/String;I)V
    //   582: putfield 42	com/tencent/stat/n:c	Lcom/tencent/stat/DeviceInfo;
    //   585: aload 4
    //   587: ifnull +10 -> 597
    //   590: aload 4
    //   592: invokeinterface 354 1 0
    //   597: aload_0
    //   598: getfield 42	com/tencent/stat/n:c	Lcom/tencent/stat/DeviceInfo;
    //   601: astore 7
    //   603: goto -588 -> 15
    //   606: aload_1
    //   607: invokestatic 522	com/tencent/stat/common/k:b	(Landroid/content/Context;)Ljava/lang/String;
    //   610: astore 22
    //   612: aload 22
    //   614: astore 23
    //   616: aload 22
    //   618: astore 24
    //   620: iconst_1
    //   621: istore 25
    //   623: goto -384 -> 239
    //   626: aload_1
    //   627: invokestatic 524	com/tencent/stat/common/k:c	(Landroid/content/Context;)Ljava/lang/String;
    //   630: astore 26
    //   632: aload 26
    //   634: ifnull -349 -> 285
    //   637: aload 26
    //   639: invokevirtual 499	java/lang/String:length	()I
    //   642: ifle -357 -> 285
    //   645: new 59	java/lang/StringBuilder
    //   648: dup
    //   649: invokespecial 60	java/lang/StringBuilder:<init>	()V
    //   652: aload 23
    //   654: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   657: ldc_w 492
    //   660: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   663: aload 26
    //   665: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   668: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   671: astore 27
    //   673: aload 27
    //   675: astore 24
    //   677: iconst_1
    //   678: istore 25
    //   680: goto -395 -> 285
    //   683: astore 5
    //   685: aconst_null
    //   686: astore 6
    //   688: getstatic 28	com/tencent/stat/n:e	Lcom/tencent/stat/common/StatLogger;
    //   691: aload 5
    //   693: invokevirtual 125	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   696: aload 6
    //   698: ifnull -101 -> 597
    //   701: aload 6
    //   703: invokeinterface 354 1 0
    //   708: goto -111 -> 597
    //   711: astore_2
    //   712: aload_0
    //   713: monitorexit
    //   714: aload_2
    //   715: athrow
    //   716: astore_3
    //   717: aconst_null
    //   718: astore 4
    //   720: aload 4
    //   722: ifnull +10 -> 732
    //   725: aload 4
    //   727: invokeinterface 354 1 0
    //   732: aload_3
    //   733: athrow
    //   734: astore_3
    //   735: goto -15 -> 720
    //   738: astore_3
    //   739: aload 6
    //   741: astore 4
    //   743: goto -23 -> 720
    //   746: astore 5
    //   748: aload 4
    //   750: astore 6
    //   752: goto -64 -> 688
    //   755: aload 32
    //   757: astore 34
    //   759: goto -279 -> 480
    //   762: iconst_1
    //   763: istore 10
    //   765: goto -343 -> 422
    //   768: aload 41
    //   770: astore 42
    //   772: iconst_0
    //   773: istore 25
    //   775: goto +31 -> 806
    //   778: aload 12
    //   780: astore 24
    //   782: aload 41
    //   784: astore 23
    //   786: iconst_0
    //   787: istore 25
    //   789: goto -550 -> 239
    //   792: iload 19
    //   794: istore 20
    //   796: goto -626 -> 170
    //   799: iload 13
    //   801: istore 19
    //   803: goto -651 -> 152
    //   806: aload 12
    //   808: astore 24
    //   810: aload 42
    //   812: astore 23
    //   814: goto -575 -> 239
    //
    // Exception table:
    //   from	to	target	type
    //   20	42	683	java/lang/Throwable
    //   2	15	711	finally
    //   590	597	711	finally
    //   597	603	711	finally
    //   701	708	711	finally
    //   725	732	711	finally
    //   732	734	711	finally
    //   20	42	716	finally
    //   46	55	734	finally
    //   63	119	734	finally
    //   125	149	734	finally
    //   152	164	734	finally
    //   170	180	734	finally
    //   185	197	734	finally
    //   202	212	734	finally
    //   212	218	734	finally
    //   223	233	734	finally
    //   244	285	734	finally
    //   285	362	734	finally
    //   367	395	734	finally
    //   402	419	734	finally
    //   427	439	734	finally
    //   444	480	734	finally
    //   480	585	734	finally
    //   606	612	734	finally
    //   626	632	734	finally
    //   637	673	734	finally
    //   688	696	738	finally
    //   46	55	746	java/lang/Throwable
    //   63	119	746	java/lang/Throwable
    //   125	149	746	java/lang/Throwable
    //   152	164	746	java/lang/Throwable
    //   170	180	746	java/lang/Throwable
    //   185	197	746	java/lang/Throwable
    //   202	212	746	java/lang/Throwable
    //   212	218	746	java/lang/Throwable
    //   223	233	746	java/lang/Throwable
    //   244	285	746	java/lang/Throwable
    //   285	362	746	java/lang/Throwable
    //   367	395	746	java/lang/Throwable
    //   402	419	746	java/lang/Throwable
    //   427	439	746	java/lang/Throwable
    //   444	480	746	java/lang/Throwable
    //   480	585	746	java/lang/Throwable
    //   606	612	746	java/lang/Throwable
    //   626	632	746	java/lang/Throwable
    //   637	673	746	java/lang/Throwable
  }

  void c()
  {
    this.a.post(new t(this));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.n
 * JD-Core Version:    0.6.0
 */