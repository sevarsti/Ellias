package com.tencent.feedback.common.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.feedback.common.d;
import com.tencent.feedback.common.e;
import java.io.File;

public final class c extends SQLiteOpenHelper
{
  private Context a;

  public c(Context paramContext)
  {
    super(paramContext, "eup_db", null, 14);
    this.a = paramContext;
  }

  // ERROR //
  private boolean a(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 20	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 23	java/util/ArrayList:<init>	()V
    //   9: astore_2
    //   10: aload_1
    //   11: ldc 25
    //   13: iconst_1
    //   14: anewarray 27	java/lang/String
    //   17: dup
    //   18: iconst_0
    //   19: ldc 29
    //   21: aastore
    //   22: ldc 31
    //   24: aconst_null
    //   25: aconst_null
    //   26: aconst_null
    //   27: aconst_null
    //   28: invokevirtual 37	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   31: astore 8
    //   33: aload 8
    //   35: astore 4
    //   37: aload 4
    //   39: ifnull +66 -> 105
    //   42: aload 4
    //   44: invokeinterface 43 1 0
    //   49: ifeq +56 -> 105
    //   52: aload_2
    //   53: aload 4
    //   55: iconst_0
    //   56: invokeinterface 47 2 0
    //   61: invokeinterface 53 2 0
    //   66: pop
    //   67: goto -25 -> 42
    //   70: astore_3
    //   71: aload_3
    //   72: invokevirtual 56	java/lang/Throwable:printStackTrace	()V
    //   75: aload 4
    //   77: ifnull +20 -> 97
    //   80: aload 4
    //   82: invokeinterface 59 1 0
    //   87: ifne +10 -> 97
    //   90: aload 4
    //   92: invokeinterface 62 1 0
    //   97: iconst_0
    //   98: istore 7
    //   100: aload_0
    //   101: monitorexit
    //   102: iload 7
    //   104: ireturn
    //   105: aload_2
    //   106: invokeinterface 66 1 0
    //   111: ifle +105 -> 216
    //   114: aload_2
    //   115: invokeinterface 70 1 0
    //   120: astore 9
    //   122: aload 9
    //   124: invokeinterface 75 1 0
    //   129: ifeq +87 -> 216
    //   132: aload 9
    //   134: invokeinterface 79 1 0
    //   139: checkcast 27	java/lang/String
    //   142: astore 10
    //   144: aload_1
    //   145: getstatic 85	java/util/Locale:US	Ljava/util/Locale;
    //   148: ldc 87
    //   150: iconst_1
    //   151: anewarray 89	java/lang/Object
    //   154: dup
    //   155: iconst_0
    //   156: aload 10
    //   158: aastore
    //   159: invokestatic 93	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   162: invokevirtual 97	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   165: ldc 99
    //   167: iconst_1
    //   168: anewarray 89	java/lang/Object
    //   171: dup
    //   172: iconst_0
    //   173: aload 10
    //   175: aastore
    //   176: invokestatic 105	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   179: goto -57 -> 122
    //   182: astore 5
    //   184: aload 4
    //   186: ifnull +20 -> 206
    //   189: aload 4
    //   191: invokeinterface 59 1 0
    //   196: ifne +10 -> 206
    //   199: aload 4
    //   201: invokeinterface 62 1 0
    //   206: aload 5
    //   208: athrow
    //   209: astore 6
    //   211: aload_0
    //   212: monitorexit
    //   213: aload 6
    //   215: athrow
    //   216: aload 4
    //   218: ifnull +20 -> 238
    //   221: aload 4
    //   223: invokeinterface 59 1 0
    //   228: ifne +10 -> 238
    //   231: aload 4
    //   233: invokeinterface 62 1 0
    //   238: iconst_1
    //   239: istore 7
    //   241: goto -141 -> 100
    //   244: astore 5
    //   246: aconst_null
    //   247: astore 4
    //   249: goto -65 -> 184
    //   252: astore_3
    //   253: aconst_null
    //   254: astore 4
    //   256: goto -185 -> 71
    //
    // Exception table:
    //   from	to	target	type
    //   42	67	70	java/lang/Throwable
    //   105	122	70	java/lang/Throwable
    //   122	179	70	java/lang/Throwable
    //   42	67	182	finally
    //   71	75	182	finally
    //   105	122	182	finally
    //   122	179	182	finally
    //   80	97	209	finally
    //   189	206	209	finally
    //   206	209	209	finally
    //   221	238	209	finally
    //   2	33	244	finally
    //   2	33	252	java/lang/Throwable
  }

  public final SQLiteDatabase getReadableDatabase()
  {
    int i = 0;
    monitorenter;
    Object localObject1 = null;
    while ((localObject1 == null) && (i < 5))
    {
      i++;
      try
      {
        SQLiteDatabase localSQLiteDatabase = super.getReadableDatabase();
        localObject1 = localSQLiteDatabase;
        continue;
      }
      catch (Throwable localThrowable)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(i);
        e.c("rqdp{  getReadableDatabase error count} %d", arrayOfObject);
        if (i == 5)
          e.d("rqdp{  error get DB failed}", new Object[0]);
        try
        {
          Thread.sleep(200L);
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
        continue;
      }
      finally
      {
        monitorexit;
      }
    }
    monitorexit;
    return localObject1;
  }

  public final SQLiteDatabase getWritableDatabase()
  {
    int i = 0;
    monitorenter;
    Object localObject1 = null;
    while ((localObject1 == null) && (i < 5))
    {
      i++;
      try
      {
        SQLiteDatabase localSQLiteDatabase = super.getWritableDatabase();
        localObject1 = localSQLiteDatabase;
        continue;
      }
      catch (Exception localException)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(i);
        e.c("rqdp{  getWritableDatabase error count} %d", arrayOfObject);
        if (i == 5)
          e.d("rqdp{  error get DB failed}", new Object[0]);
        try
        {
          Thread.sleep(200L);
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
        continue;
      }
      finally
      {
        monitorexit;
      }
    }
    monitorexit;
    return localObject1;
  }

  public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    int i = 0;
    monitorenter;
    if (paramSQLiteDatabase != null);
    try
    {
      if (b.a != null)
      {
        String[][] arrayOfString = b.a;
        int j = arrayOfString.length;
        while (i < j)
        {
          String[] arrayOfString1 = arrayOfString[i];
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = arrayOfString1[0];
          arrayOfObject[1] = arrayOfString1[1];
          e.g("rqdp{  table:}%s %s", arrayOfObject);
          paramSQLiteDatabase.execSQL(arrayOfString1[1]);
          i++;
        }
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @TargetApi(11)
  public final void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    monitorenter;
    try
    {
      d.a(this.a);
      if (Integer.parseInt(d.c()) >= 11)
      {
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = "eup_db";
        arrayOfObject[1] = Integer.valueOf(paramInt1);
        arrayOfObject[2] = Integer.valueOf(paramInt2);
        e.g("rqdp{  downgrade a db} [%s] rqdp{  from v}%d rqdp{  to} v%d rqdp{  , deleted all tables!}", arrayOfObject);
        if (!a(paramSQLiteDatabase))
          break label81;
        e.g("rqdp{  drop all success recreate!}", new Object[0]);
        onCreate(paramSQLiteDatabase);
      }
      while (true)
      {
        return;
        label81: e.d("rqdp{  drop all fail try deleted file,may next time will success!}", new Object[0]);
        File localFile = this.a.getDatabasePath("eup_db");
        if ((localFile == null) || (!localFile.canWrite()))
          continue;
        localFile.delete();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    monitorenter;
    try
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = "eup_db";
      arrayOfObject[1] = Integer.valueOf(paramInt1);
      arrayOfObject[2] = Integer.valueOf(paramInt2);
      e.g("rqdp{  upgrade a db }[%s]rqdp{   from v}%d rqdp{  to v}%d rqdp{  , deleted all tables!}", arrayOfObject);
      if (a(paramSQLiteDatabase))
      {
        e.g("rqdp{  drop all success recreate!}", new Object[0]);
        onCreate(paramSQLiteDatabase);
      }
      while (true)
      {
        return;
        e.d("rqdp{  drop all fail try deleted file,may next time will success!}", new Object[0]);
        File localFile = this.a.getDatabasePath("eup_db");
        if ((localFile == null) || (!localFile.canWrite()))
          continue;
        localFile.delete();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.a.c
 * JD-Core Version:    0.6.0
 */