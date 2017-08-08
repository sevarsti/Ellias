package com.pay.http;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import com.pay.common.tool.APLog;

public class APIPDatabase
{
  public static final String DB_IPH5TABLE = "TencentUnipayIPH5Table";
  public static final String DB_IPTABLE = "TencentUnipayIPTable";
  private Context a;
  private APIPDatabase.APIPDBHelper b;
  private SQLiteDatabase c;

  public APIPDatabase(Context paramContext)
  {
    this.a = paramContext;
    b();
  }

  private void a()
  {
    if (this.b == null)
      b();
    if ((this.c != null) && (!this.c.isOpen()))
      this.c = this.b.getWritableDatabase();
  }

  private static boolean a(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    String str = "CREATE TABLE IF NOT EXISTS " + paramString + " ( ip" + " VARCHAR(64), accessTimes" + " INTEGER, seqFailTimes" + " INTEGER, succTimes" + " INTEGER, failTimes" + " INTEGER, ansTims" + " INTEGER,  ipEnv" + " VARCHAR(12), province" + " VARCHAR(64), city" + " VARCHAR(64))";
    try
    {
      paramSQLiteDatabase.execSQL(str);
      return true;
    }
    catch (SQLException localSQLException)
    {
      Log.w("APIPDatabase", String.valueOf(localSQLException));
    }
    return false;
  }

  private void b()
  {
    if (this.b == null)
    {
      this.b = new APIPDatabase.APIPDBHelper(this, this.a, "TencentUnipayIPList.db", null, 1);
      this.c = this.b.getWritableDatabase();
    }
  }

  public void clearAll(String paramString)
  {
    try
    {
      a();
      this.c.delete(paramString, null, null);
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      APLog.w("deleteAccount", String.valueOf(localSQLiteException));
    }
  }

  public void closeDB()
  {
    if (this.b != null)
    {
      this.b.close();
      this.c.close();
    }
  }

  public void deleteIP(String paramString1, String paramString2)
  {
    String str = "delete from " + paramString2 + " where ip = '" + paramString1 + "'";
    try
    {
      a();
      this.c.execSQL(str);
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      APLog.w("deleteAccount", String.valueOf(localSQLiteException));
    }
  }

  // ERROR //
  public int getCount(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 110	com/pay/http/APIPDatabase:a	()V
    //   4: aload_0
    //   5: getfield 30	com/pay/http/APIPDatabase:c	Landroid/database/sqlite/SQLiteDatabase;
    //   8: ifnonnull +5 -> 13
    //   11: iconst_0
    //   12: ireturn
    //   13: new 47	java/lang/StringBuilder
    //   16: dup
    //   17: ldc 138
    //   19: invokespecial 52	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   22: aload_1
    //   23: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: ldc 133
    //   28: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   34: astore_3
    //   35: aload_0
    //   36: getfield 30	com/pay/http/APIPDatabase:c	Landroid/database/sqlite/SQLiteDatabase;
    //   39: aload_2
    //   40: aconst_null
    //   41: aload_3
    //   42: aconst_null
    //   43: aconst_null
    //   44: aconst_null
    //   45: aconst_null
    //   46: invokevirtual 142	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   49: astore 10
    //   51: aload 10
    //   53: astore 5
    //   55: aload 5
    //   57: invokeinterface 147 1 0
    //   62: ifle +118 -> 180
    //   65: aload 5
    //   67: invokeinterface 147 1 0
    //   72: istore 12
    //   74: iload 12
    //   76: istore 8
    //   78: aload 5
    //   80: ifnull +10 -> 90
    //   83: aload 5
    //   85: invokeinterface 148 1 0
    //   90: iload 8
    //   92: ireturn
    //   93: astore 7
    //   95: aconst_null
    //   96: astore 5
    //   98: ldc 85
    //   100: aload 7
    //   102: invokestatic 91	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   105: invokestatic 121	com/pay/common/tool/APLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   108: aload 5
    //   110: ifnull +64 -> 174
    //   113: aload 5
    //   115: invokeinterface 148 1 0
    //   120: iconst_0
    //   121: istore 8
    //   123: goto -33 -> 90
    //   126: astore 9
    //   128: iconst_0
    //   129: istore 8
    //   131: goto -41 -> 90
    //   134: astore 4
    //   136: aconst_null
    //   137: astore 5
    //   139: aload 5
    //   141: ifnull +10 -> 151
    //   144: aload 5
    //   146: invokeinterface 148 1 0
    //   151: aload 4
    //   153: athrow
    //   154: astore 6
    //   156: goto -5 -> 151
    //   159: astore 11
    //   161: goto -71 -> 90
    //   164: astore 4
    //   166: goto -27 -> 139
    //   169: astore 7
    //   171: goto -73 -> 98
    //   174: iconst_0
    //   175: istore 8
    //   177: goto -87 -> 90
    //   180: iconst_0
    //   181: istore 8
    //   183: goto -105 -> 78
    //
    // Exception table:
    //   from	to	target	type
    //   35	51	93	java/lang/Exception
    //   113	120	126	java/lang/Exception
    //   35	51	134	finally
    //   144	151	154	java/lang/Exception
    //   83	90	159	java/lang/Exception
    //   55	74	164	finally
    //   98	108	164	finally
    //   55	74	169	java/lang/Exception
  }

  // ERROR //
  public void getIPStateMap(java.util.HashMap paramHashMap, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 110	com/pay/http/APIPDatabase:a	()V
    //   4: aload_0
    //   5: getfield 30	com/pay/http/APIPDatabase:c	Landroid/database/sqlite/SQLiteDatabase;
    //   8: aload_3
    //   9: aconst_null
    //   10: aconst_null
    //   11: aconst_null
    //   12: aconst_null
    //   13: aconst_null
    //   14: aconst_null
    //   15: invokevirtual 142	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   18: astore 9
    //   20: aload 9
    //   22: astore 5
    //   24: aload 5
    //   26: invokeinterface 147 1 0
    //   31: ifle +30 -> 61
    //   34: aload 5
    //   36: invokeinterface 147 1 0
    //   41: istore 11
    //   43: aload 5
    //   45: invokeinterface 153 1 0
    //   50: pop
    //   51: iconst_0
    //   52: istore 13
    //   54: iload 13
    //   56: iload 11
    //   58: if_icmplt +16 -> 74
    //   61: aload 5
    //   63: ifnull +10 -> 73
    //   66: aload 5
    //   68: invokeinterface 148 1 0
    //   73: return
    //   74: new 155	com/pay/http/APIPState
    //   77: dup
    //   78: invokespecial 156	com/pay/http/APIPState:<init>	()V
    //   81: astore 14
    //   83: aload 14
    //   85: aload 5
    //   87: aload 5
    //   89: ldc 158
    //   91: invokeinterface 162 2 0
    //   96: invokeinterface 166 2 0
    //   101: putfield 168	com/pay/http/APIPState:ip	Ljava/lang/String;
    //   104: aload 14
    //   106: aload 5
    //   108: aload 5
    //   110: ldc 170
    //   112: invokeinterface 162 2 0
    //   117: invokeinterface 174 2 0
    //   122: putfield 177	com/pay/http/APIPState:accessTimes	I
    //   125: aload 14
    //   127: aload 5
    //   129: aload 5
    //   131: ldc 179
    //   133: invokeinterface 162 2 0
    //   138: invokeinterface 174 2 0
    //   143: putfield 181	com/pay/http/APIPState:seqFailTimes	I
    //   146: aload 14
    //   148: aload 5
    //   150: aload 5
    //   152: ldc 183
    //   154: invokeinterface 162 2 0
    //   159: invokeinterface 174 2 0
    //   164: putfield 185	com/pay/http/APIPState:succTimes	I
    //   167: aload 14
    //   169: aload 5
    //   171: aload 5
    //   173: ldc 187
    //   175: invokeinterface 162 2 0
    //   180: invokeinterface 174 2 0
    //   185: putfield 189	com/pay/http/APIPState:failTimes	I
    //   188: aload 14
    //   190: aload 5
    //   192: aload 5
    //   194: ldc 191
    //   196: invokeinterface 162 2 0
    //   201: invokeinterface 174 2 0
    //   206: putfield 193	com/pay/http/APIPState:ansTims	I
    //   209: aload 14
    //   211: aload 5
    //   213: aload 5
    //   215: ldc 195
    //   217: invokeinterface 162 2 0
    //   222: invokeinterface 166 2 0
    //   227: putfield 197	com/pay/http/APIPState:ipEnv	Ljava/lang/String;
    //   230: aload 14
    //   232: getfield 197	com/pay/http/APIPState:ipEnv	Ljava/lang/String;
    //   235: aload_2
    //   236: invokevirtual 201	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   239: ifeq +15 -> 254
    //   242: aload_1
    //   243: aload 14
    //   245: getfield 168	com/pay/http/APIPState:ip	Ljava/lang/String;
    //   248: aload 14
    //   250: invokevirtual 207	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   253: pop
    //   254: aload 5
    //   256: invokeinterface 210 1 0
    //   261: pop
    //   262: iinc 13 1
    //   265: goto -211 -> 54
    //   268: astore 7
    //   270: aconst_null
    //   271: astore 5
    //   273: ldc 85
    //   275: aload 7
    //   277: invokestatic 91	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   280: invokestatic 121	com/pay/common/tool/APLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   283: aload 5
    //   285: ifnull -212 -> 73
    //   288: aload 5
    //   290: invokeinterface 148 1 0
    //   295: return
    //   296: astore 8
    //   298: return
    //   299: astore 4
    //   301: aconst_null
    //   302: astore 5
    //   304: aload 5
    //   306: ifnull +10 -> 316
    //   309: aload 5
    //   311: invokeinterface 148 1 0
    //   316: aload 4
    //   318: athrow
    //   319: astore 6
    //   321: goto -5 -> 316
    //   324: astore 10
    //   326: return
    //   327: astore 4
    //   329: goto -25 -> 304
    //   332: astore 7
    //   334: goto -61 -> 273
    //
    // Exception table:
    //   from	to	target	type
    //   0	20	268	java/lang/Exception
    //   288	295	296	java/lang/Exception
    //   0	20	299	finally
    //   309	316	319	java/lang/Exception
    //   66	73	324	java/lang/Exception
    //   24	51	327	finally
    //   74	254	327	finally
    //   254	262	327	finally
    //   273	283	327	finally
    //   24	51	332	java/lang/Exception
    //   74	254	332	java/lang/Exception
    //   254	262	332	java/lang/Exception
  }

  // ERROR //
  public void insertIP(APIPState paramAPIPState, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: getfield 168	com/pay/http/APIPState:ip	Ljava/lang/String;
    //   5: aload_2
    //   6: invokevirtual 216	com/pay/http/APIPDatabase:isIPExist	(Ljava/lang/String;Ljava/lang/String;)Z
    //   9: ifeq +4 -> 13
    //   12: return
    //   13: aload_0
    //   14: monitorenter
    //   15: new 218	android/content/ContentValues
    //   18: dup
    //   19: invokespecial 219	android/content/ContentValues:<init>	()V
    //   22: astore_3
    //   23: aload_3
    //   24: ldc 158
    //   26: aload_1
    //   27: getfield 168	com/pay/http/APIPState:ip	Ljava/lang/String;
    //   30: invokevirtual 221	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   33: aload_3
    //   34: ldc 170
    //   36: aload_1
    //   37: getfield 177	com/pay/http/APIPState:accessTimes	I
    //   40: invokestatic 226	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   43: invokevirtual 229	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   46: aload_3
    //   47: ldc 179
    //   49: aload_1
    //   50: getfield 181	com/pay/http/APIPState:seqFailTimes	I
    //   53: invokestatic 226	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   56: invokevirtual 229	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   59: aload_3
    //   60: ldc 183
    //   62: aload_1
    //   63: getfield 185	com/pay/http/APIPState:succTimes	I
    //   66: invokestatic 226	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   69: invokevirtual 229	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   72: aload_3
    //   73: ldc 187
    //   75: aload_1
    //   76: getfield 189	com/pay/http/APIPState:failTimes	I
    //   79: invokestatic 226	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   82: invokevirtual 229	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   85: aload_3
    //   86: ldc 191
    //   88: aload_1
    //   89: getfield 193	com/pay/http/APIPState:ansTims	I
    //   92: invokestatic 226	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   95: invokevirtual 229	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   98: aload_3
    //   99: ldc 195
    //   101: aload_1
    //   102: getfield 197	com/pay/http/APIPState:ipEnv	Ljava/lang/String;
    //   105: invokevirtual 221	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   108: aload_3
    //   109: ldc 231
    //   111: aload_1
    //   112: getfield 233	com/pay/http/APIPState:province	Ljava/lang/String;
    //   115: invokevirtual 221	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   118: aload_3
    //   119: ldc 235
    //   121: aload_1
    //   122: getfield 237	com/pay/http/APIPState:city	Ljava/lang/String;
    //   125: invokevirtual 221	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   128: aload_0
    //   129: invokespecial 110	com/pay/http/APIPDatabase:a	()V
    //   132: aload_0
    //   133: getfield 30	com/pay/http/APIPDatabase:c	Landroid/database/sqlite/SQLiteDatabase;
    //   136: aload_2
    //   137: aconst_null
    //   138: aload_3
    //   139: invokevirtual 241	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   142: pop2
    //   143: aload_0
    //   144: monitorexit
    //   145: return
    //   146: astore 4
    //   148: aload_0
    //   149: monitorexit
    //   150: aload 4
    //   152: athrow
    //   153: astore 5
    //   155: ldc 85
    //   157: aload 5
    //   159: invokestatic 91	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   162: invokestatic 121	com/pay/common/tool/APLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   165: goto -22 -> 143
    //
    // Exception table:
    //   from	to	target	type
    //   15	128	146	finally
    //   128	143	146	finally
    //   143	145	146	finally
    //   155	165	146	finally
    //   128	143	153	android/database/SQLException
  }

  // ERROR //
  public boolean isIPExist(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: new 47	java/lang/StringBuilder
    //   3: dup
    //   4: ldc 243
    //   6: invokespecial 52	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   9: aload_1
    //   10: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   13: ldc 133
    //   15: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   21: astore_3
    //   22: aload_0
    //   23: invokespecial 110	com/pay/http/APIPDatabase:a	()V
    //   26: aload_0
    //   27: getfield 30	com/pay/http/APIPDatabase:c	Landroid/database/sqlite/SQLiteDatabase;
    //   30: aload_2
    //   31: aconst_null
    //   32: aload_3
    //   33: aconst_null
    //   34: aconst_null
    //   35: aconst_null
    //   36: aconst_null
    //   37: invokevirtual 142	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   40: astore 9
    //   42: aload 9
    //   44: astore 5
    //   46: aload 5
    //   48: invokeinterface 147 1 0
    //   53: istore 10
    //   55: iload 10
    //   57: ifle +66 -> 123
    //   60: aload 5
    //   62: ifnull +10 -> 72
    //   65: aload 5
    //   67: invokeinterface 148 1 0
    //   72: iconst_1
    //   73: ireturn
    //   74: astore 7
    //   76: aconst_null
    //   77: astore 5
    //   79: ldc 85
    //   81: aload 7
    //   83: invokestatic 91	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   86: invokestatic 121	com/pay/common/tool/APLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: aload 5
    //   91: ifnull +10 -> 101
    //   94: aload 5
    //   96: invokeinterface 148 1 0
    //   101: iconst_0
    //   102: ireturn
    //   103: astore 4
    //   105: aconst_null
    //   106: astore 5
    //   108: aload 5
    //   110: ifnull +10 -> 120
    //   113: aload 5
    //   115: invokeinterface 148 1 0
    //   120: aload 4
    //   122: athrow
    //   123: aload 5
    //   125: ifnull -24 -> 101
    //   128: aload 5
    //   130: invokeinterface 148 1 0
    //   135: goto -34 -> 101
    //   138: astore 11
    //   140: goto -39 -> 101
    //   143: astore 12
    //   145: goto -73 -> 72
    //   148: astore 8
    //   150: goto -49 -> 101
    //   153: astore 6
    //   155: goto -35 -> 120
    //   158: astore 4
    //   160: goto -52 -> 108
    //   163: astore 7
    //   165: goto -86 -> 79
    //
    // Exception table:
    //   from	to	target	type
    //   22	42	74	java/lang/Exception
    //   22	42	103	finally
    //   128	135	138	java/lang/Exception
    //   65	72	143	java/lang/Exception
    //   94	101	148	java/lang/Exception
    //   113	120	153	java/lang/Exception
    //   46	55	158	finally
    //   79	89	158	finally
    //   46	55	163	java/lang/Exception
  }

  public void updateIP(APIPState paramAPIPState, String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("accessTimes", Integer.valueOf(paramAPIPState.accessTimes));
    localContentValues.put("seqFailTimes", Integer.valueOf(paramAPIPState.seqFailTimes));
    localContentValues.put("succTimes", Integer.valueOf(paramAPIPState.succTimes));
    localContentValues.put("failTimes", Integer.valueOf(paramAPIPState.failTimes));
    localContentValues.put("ansTims", Integer.valueOf(paramAPIPState.ansTims));
    localContentValues.put("ipEnv", paramAPIPState.ipEnv);
    localContentValues.put("province", paramAPIPState.province);
    localContentValues.put("city", paramAPIPState.city);
    try
    {
      a();
      SQLiteDatabase localSQLiteDatabase = this.c;
      String[] arrayOfString = new String[1];
      arrayOfString[0] = paramAPIPState.ip;
      localSQLiteDatabase.update(paramString, localContentValues, "ip=?", arrayOfString);
      return;
    }
    catch (SQLException localSQLException)
    {
      APLog.w("APIPDatabase", String.valueOf(localSQLException));
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.APIPDatabase
 * JD-Core Version:    0.6.0
 */