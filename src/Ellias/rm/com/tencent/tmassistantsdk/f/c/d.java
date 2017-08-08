package com.tencent.tmassistantsdk.f.c;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.tmassistantsdk.f.a.c;
import com.tencent.tmassistantsdk.g.l;
import java.util.ArrayList;
import java.util.Iterator;

public class d
  implements g
{
  private static int a(com.tencent.tmassistantsdk.downloadservice.d paramd, SQLiteDatabase paramSQLiteDatabase)
  {
    int j;
    if (paramd == null)
      j = -1;
    while (true)
    {
      return j;
      try
      {
        ContentValues localContentValues = new ContentValues();
        com.tencent.tmassistantsdk.downloadservice.d.a(localContentValues, paramd);
        String[] arrayOfString = new String[1];
        arrayOfString[0] = paramd.b;
        int i = paramSQLiteDatabase.update("downloadInfo", localContentValues, "taskUrl = ?", arrayOfString);
        j = i;
        if (j <= 0)
          return 0;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return -2;
  }

  public static void a(com.tencent.tmassistantsdk.downloadservice.d paramd)
  {
    if (paramd != null);
    try
    {
      SQLiteDatabase localSQLiteDatabase = com.tencent.tmassistantsdk.f.a.b.a().getWritableDatabase();
      if (a(paramd, localSQLiteDatabase) <= 0)
      {
        ContentValues localContentValues = new ContentValues();
        com.tencent.tmassistantsdk.downloadservice.d.a(localContentValues, paramd);
        localSQLiteDatabase.insert("downloadInfo", null, localContentValues);
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static void a(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0));
    try
    {
      com.tencent.tmassistantsdk.f.a.b.a().getWritableDatabase().delete("downloadInfo", "taskUrl = ?", new String[] { paramString });
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static void a(ArrayList paramArrayList)
  {
    SQLiteDatabase localSQLiteDatabase;
    if (paramArrayList != null)
      localSQLiteDatabase = com.tencent.tmassistantsdk.f.a.b.a().getWritableDatabase();
    try
    {
      localSQLiteDatabase.beginTransaction();
      Iterator localIterator = paramArrayList.iterator();
      while (localIterator.hasNext())
      {
        com.tencent.tmassistantsdk.downloadservice.d locald = (com.tencent.tmassistantsdk.downloadservice.d)localIterator.next();
        if (a(locald, localSQLiteDatabase) > 0)
          continue;
        ContentValues localContentValues = new ContentValues();
        com.tencent.tmassistantsdk.downloadservice.d.a(localContentValues, locald);
        localSQLiteDatabase.insert("downloadInfo", null, localContentValues);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      while (true)
      {
        return;
        localSQLiteDatabase.setTransactionSuccessful();
      }
    }
    finally
    {
      localSQLiteDatabase.endTransaction();
    }
    throw localObject;
  }

  // ERROR //
  public static com.tencent.tmassistantsdk.downloadservice.d b(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: ifnull +76 -> 79
    //   6: aload_0
    //   7: invokevirtual 64	java/lang/String:length	()I
    //   10: istore_2
    //   11: aconst_null
    //   12: astore_1
    //   13: iload_2
    //   14: ifle +65 -> 79
    //   17: invokestatic 47	com/tencent/tmassistantsdk/f/a/b:a	()Lcom/tencent/tmassistantsdk/f/a/c;
    //   20: invokevirtual 98	com/tencent/tmassistantsdk/f/a/c:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   23: ldc 100
    //   25: iconst_1
    //   26: anewarray 24	java/lang/String
    //   29: dup
    //   30: iconst_0
    //   31: aload_0
    //   32: aastore
    //   33: invokevirtual 104	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   36: astore 7
    //   38: aload 7
    //   40: astore 4
    //   42: aload 4
    //   44: ifnull +81 -> 125
    //   47: aload 4
    //   49: invokeinterface 109 1 0
    //   54: ifeq +71 -> 125
    //   57: aload 4
    //   59: invokestatic 112	com/tencent/tmassistantsdk/downloadservice/d:a	(Landroid/database/Cursor;)Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   62: astore 8
    //   64: aload 8
    //   66: astore_1
    //   67: aload 4
    //   69: ifnull +10 -> 79
    //   72: aload 4
    //   74: invokeinterface 115 1 0
    //   79: aload_1
    //   80: areturn
    //   81: astore 6
    //   83: aconst_null
    //   84: astore 4
    //   86: aload 6
    //   88: invokevirtual 41	java/lang/Exception:printStackTrace	()V
    //   91: aconst_null
    //   92: astore_1
    //   93: aload 4
    //   95: ifnull -16 -> 79
    //   98: aconst_null
    //   99: astore_1
    //   100: goto -28 -> 72
    //   103: astore_3
    //   104: aconst_null
    //   105: astore 4
    //   107: aload_3
    //   108: astore 5
    //   110: aload 4
    //   112: ifnull +10 -> 122
    //   115: aload 4
    //   117: invokeinterface 115 1 0
    //   122: aload 5
    //   124: athrow
    //   125: aconst_null
    //   126: astore_1
    //   127: aload 4
    //   129: ifnull -50 -> 79
    //   132: aconst_null
    //   133: astore_1
    //   134: goto -62 -> 72
    //   137: astore 5
    //   139: goto -29 -> 110
    //   142: astore 6
    //   144: goto -58 -> 86
    //
    // Exception table:
    //   from	to	target	type
    //   17	38	81	java/lang/Exception
    //   17	38	103	finally
    //   47	64	137	finally
    //   86	91	137	finally
    //   47	64	142	java/lang/Exception
  }

  public static ArrayList c()
  {
    Cursor localCursor = null;
    ArrayList localArrayList = new ArrayList();
    SQLiteDatabase localSQLiteDatabase = com.tencent.tmassistantsdk.f.a.b.a().getReadableDatabase();
    try
    {
      localCursor = localSQLiteDatabase.rawQuery("select * from downloadInfo", null);
      if ((localCursor != null) && (localCursor.moveToFirst()))
      {
        boolean bool;
        do
        {
          localArrayList.add(com.tencent.tmassistantsdk.downloadservice.d.a(localCursor));
          bool = localCursor.moveToNext();
        }
        while (bool);
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        if (localCursor == null)
          continue;
      }
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
    throw localObject;
  }

  public static ArrayList d()
  {
    Cursor localCursor = null;
    ArrayList localArrayList = new ArrayList();
    SQLiteDatabase localSQLiteDatabase = com.tencent.tmassistantsdk.f.a.b.a().getReadableDatabase();
    try
    {
      localCursor = localSQLiteDatabase.rawQuery("select a.taskUrl,a.status,b.clientId from downloadInfo as a left outer join clientinfo as b on a.taskUrl = b.taskUrl where b.clientId is not null and (a.status = 2 or a.status = 1)", null);
      if ((localCursor != null) && (localCursor.moveToFirst()))
      {
        boolean bool;
        do
        {
          String str1 = localCursor.getString(localCursor.getColumnIndex("clientId"));
          String str2 = localCursor.getString(localCursor.getColumnIndex("taskUrl"));
          int i = localCursor.getInt(localCursor.getColumnIndex("status"));
          com.tencent.tmassistantsdk.downloadservice.a.b localb = new com.tencent.tmassistantsdk.downloadservice.a.b(str1, str2);
          localb.c = i;
          localArrayList.add(localb);
          bool = localCursor.moveToNext();
        }
        while (bool);
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        if (localCursor == null)
          continue;
      }
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
    throw localObject;
  }

  public String a()
  {
    return "downloadInfo";
  }

  public void a(SQLiteDatabase paramSQLiteDatabase1, SQLiteDatabase paramSQLiteDatabase2)
  {
    Cursor localCursor = null;
    if ((paramSQLiteDatabase2 != null) && (paramSQLiteDatabase1 != null))
      paramSQLiteDatabase2.beginTransaction();
    try
    {
      localCursor = paramSQLiteDatabase1.rawQuery("select * from downloadInfo", null);
      if ((localCursor != null) && (localCursor.moveToFirst()))
      {
        l.b("downloadInfo", "start move data!");
        boolean bool;
        do
        {
          ContentValues localContentValues = new ContentValues();
          com.tencent.tmassistantsdk.downloadservice.d.a(localContentValues, com.tencent.tmassistantsdk.downloadservice.d.b(localCursor));
          paramSQLiteDatabase2.insert("downloadInfo", null, localContentValues);
          bool = localCursor.moveToNext();
        }
        while (bool);
      }
      if (localCursor != null)
        localCursor.close();
      paramSQLiteDatabase2.setTransactionSuccessful();
      paramSQLiteDatabase2.endTransaction();
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        l.b("downloadInfo", "move data exception!");
        if (localCursor == null)
          continue;
      }
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
    throw localObject;
  }

  public String[] a(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 1) && (paramInt2 == 2))
      return new String[] { "alter table downloadInfo add column headerParams TEXT;" };
    return new String[] { "alter table downloadInfo add column netType TEXT;", "alter table downloadInfo add column downloadFailedErrCode INTEGER;", "alter table downloadInfo add column downloadFailedTime INTEGER;" };
  }

  public String b()
  {
    return "CREATE TABLE if not exists downloadInfo( _id INTEGER PRIMARY KEY AUTOINCREMENT, taskId INTEGER , uId TEXT, taskUrl TEXT, finalUrl TEXT, fileName TEXT, contentType TEXT, redirectCnt INTEGER, retryCnt INTEGER, totalBytes INTEGER,status INTEGER,receivedBytes INTEGER,priority INTEGER,netType TEXT,downloadFailedErrCode INTEGER,downloadFailedTime INTEGER,headerParams TEXT);";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.f.c.d
 * JD-Core Version:    0.6.0
 */