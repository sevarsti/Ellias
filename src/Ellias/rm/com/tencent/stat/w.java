package com.tencent.stat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.k;

class w extends SQLiteOpenHelper
{
  public w(Context paramContext)
  {
    super(paramContext, k.v(paramContext), null, 3);
  }

  // ERROR //
  private void a(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc 21
    //   3: aconst_null
    //   4: aconst_null
    //   5: aconst_null
    //   6: aconst_null
    //   7: aconst_null
    //   8: aconst_null
    //   9: invokevirtual 27	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   12: astore 5
    //   14: aload 5
    //   16: astore_3
    //   17: new 29	android/content/ContentValues
    //   20: dup
    //   21: invokespecial 32	android/content/ContentValues:<init>	()V
    //   24: astore 6
    //   26: aload_3
    //   27: invokeinterface 38 1 0
    //   32: istore 7
    //   34: aconst_null
    //   35: astore 8
    //   37: iload 7
    //   39: ifeq +48 -> 87
    //   42: aload_3
    //   43: iconst_0
    //   44: invokeinterface 42 2 0
    //   49: astore 8
    //   51: aload_3
    //   52: iconst_1
    //   53: invokeinterface 46 2 0
    //   58: pop
    //   59: aload_3
    //   60: iconst_2
    //   61: invokeinterface 42 2 0
    //   66: pop
    //   67: aload_3
    //   68: iconst_3
    //   69: invokeinterface 50 2 0
    //   74: pop2
    //   75: aload 6
    //   77: ldc 52
    //   79: aload 8
    //   81: invokestatic 56	com/tencent/stat/common/k:c	(Ljava/lang/String;)Ljava/lang/String;
    //   84: invokevirtual 60	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   87: aload 8
    //   89: ifnull +23 -> 112
    //   92: aload_1
    //   93: ldc 21
    //   95: aload 6
    //   97: ldc 62
    //   99: iconst_1
    //   100: anewarray 64	java/lang/String
    //   103: dup
    //   104: iconst_0
    //   105: aload 8
    //   107: aastore
    //   108: invokevirtual 68	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   111: pop
    //   112: aload_3
    //   113: ifnull +9 -> 122
    //   116: aload_3
    //   117: invokeinterface 71 1 0
    //   122: return
    //   123: astore 4
    //   125: aconst_null
    //   126: astore_3
    //   127: invokestatic 77	com/tencent/stat/n:d	()Lcom/tencent/stat/common/StatLogger;
    //   130: aload 4
    //   132: invokevirtual 83	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   135: aload_3
    //   136: ifnull -14 -> 122
    //   139: aload_3
    //   140: invokeinterface 71 1 0
    //   145: return
    //   146: astore_2
    //   147: aconst_null
    //   148: astore_3
    //   149: aload_3
    //   150: ifnull +9 -> 159
    //   153: aload_3
    //   154: invokeinterface 71 1 0
    //   159: aload_2
    //   160: athrow
    //   161: astore_2
    //   162: goto -13 -> 149
    //   165: astore 4
    //   167: goto -40 -> 127
    //
    // Exception table:
    //   from	to	target	type
    //   0	14	123	java/lang/Throwable
    //   0	14	146	finally
    //   17	34	161	finally
    //   42	87	161	finally
    //   92	112	161	finally
    //   127	135	161	finally
    //   17	34	165	java/lang/Throwable
    //   42	87	165	java/lang/Throwable
    //   92	112	165	java/lang/Throwable
  }

  // ERROR //
  private void b(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc 86
    //   3: aconst_null
    //   4: aconst_null
    //   5: aconst_null
    //   6: aconst_null
    //   7: aconst_null
    //   8: aconst_null
    //   9: invokevirtual 27	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   12: astore 6
    //   14: aload 6
    //   16: astore 5
    //   18: new 88	java/util/ArrayList
    //   21: dup
    //   22: invokespecial 89	java/util/ArrayList:<init>	()V
    //   25: astore 7
    //   27: aload 5
    //   29: invokeinterface 38 1 0
    //   34: ifeq +75 -> 109
    //   37: aload 7
    //   39: new 91	com/tencent/stat/x
    //   42: dup
    //   43: aload 5
    //   45: iconst_0
    //   46: invokeinterface 50 2 0
    //   51: aload 5
    //   53: iconst_1
    //   54: invokeinterface 42 2 0
    //   59: aload 5
    //   61: iconst_2
    //   62: invokeinterface 46 2 0
    //   67: aload 5
    //   69: iconst_3
    //   70: invokeinterface 46 2 0
    //   75: invokespecial 94	com/tencent/stat/x:<init>	(JLjava/lang/String;II)V
    //   78: invokeinterface 100 2 0
    //   83: pop
    //   84: goto -57 -> 27
    //   87: astore_2
    //   88: aload 5
    //   90: astore_3
    //   91: invokestatic 77	com/tencent/stat/n:d	()Lcom/tencent/stat/common/StatLogger;
    //   94: aload_2
    //   95: invokevirtual 83	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   98: aload_3
    //   99: ifnull +9 -> 108
    //   102: aload_3
    //   103: invokeinterface 71 1 0
    //   108: return
    //   109: new 29	android/content/ContentValues
    //   112: dup
    //   113: invokespecial 32	android/content/ContentValues:<init>	()V
    //   116: astore 8
    //   118: aload 7
    //   120: invokeinterface 104 1 0
    //   125: astore 9
    //   127: aload 9
    //   129: invokeinterface 109 1 0
    //   134: ifeq +81 -> 215
    //   137: aload 9
    //   139: invokeinterface 113 1 0
    //   144: checkcast 91	com/tencent/stat/x
    //   147: astore 10
    //   149: aload 8
    //   151: ldc 115
    //   153: aload 10
    //   155: getfield 118	com/tencent/stat/x:b	Ljava/lang/String;
    //   158: invokestatic 56	com/tencent/stat/common/k:c	(Ljava/lang/String;)Ljava/lang/String;
    //   161: invokevirtual 60	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   164: iconst_1
    //   165: anewarray 64	java/lang/String
    //   168: astore 11
    //   170: aload 11
    //   172: iconst_0
    //   173: aload 10
    //   175: getfield 121	com/tencent/stat/x:a	J
    //   178: invokestatic 127	java/lang/Long:toString	(J)Ljava/lang/String;
    //   181: aastore
    //   182: aload_1
    //   183: ldc 86
    //   185: aload 8
    //   187: ldc 129
    //   189: aload 11
    //   191: invokevirtual 68	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   194: pop
    //   195: goto -68 -> 127
    //   198: astore 4
    //   200: aload 5
    //   202: ifnull +10 -> 212
    //   205: aload 5
    //   207: invokeinterface 71 1 0
    //   212: aload 4
    //   214: athrow
    //   215: aload 5
    //   217: ifnull -109 -> 108
    //   220: aload 5
    //   222: invokeinterface 71 1 0
    //   227: return
    //   228: astore 4
    //   230: aconst_null
    //   231: astore 5
    //   233: goto -33 -> 200
    //   236: astore 4
    //   238: aload_3
    //   239: astore 5
    //   241: goto -41 -> 200
    //   244: astore_2
    //   245: aconst_null
    //   246: astore_3
    //   247: goto -156 -> 91
    //
    // Exception table:
    //   from	to	target	type
    //   18	27	87	java/lang/Throwable
    //   27	84	87	java/lang/Throwable
    //   109	127	87	java/lang/Throwable
    //   127	195	87	java/lang/Throwable
    //   18	27	198	finally
    //   27	84	198	finally
    //   109	127	198	finally
    //   127	195	198	finally
    //   0	14	228	finally
    //   91	98	236	finally
    //   0	14	244	java/lang/Throwable
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("create table if not exists events(event_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, content TEXT, status INTEGER, send_count INTEGER, timestamp LONG)");
    paramSQLiteDatabase.execSQL("create table if not exists user(uid TEXT PRIMARY KEY, user_type INTEGER, app_ver TEXT, ts INTEGER)");
    paramSQLiteDatabase.execSQL("create table if not exists config(type INTEGER PRIMARY KEY NOT NULL, content TEXT, md5sum TEXT, version INTEGER)");
    paramSQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    n.d().debug("upgrade DB from oldVersion " + paramInt1 + " to newVersion " + paramInt2);
    if (paramInt1 == 1)
    {
      paramSQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
      a(paramSQLiteDatabase);
      b(paramSQLiteDatabase);
    }
    if (paramInt1 == 2)
    {
      a(paramSQLiteDatabase);
      b(paramSQLiteDatabase);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.w
 * JD-Core Version:    0.6.0
 */