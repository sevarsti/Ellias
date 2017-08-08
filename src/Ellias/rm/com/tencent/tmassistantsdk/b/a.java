package com.tencent.tmassistantsdk.b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import android.os.SystemClock;
import java.io.File;
import java.util.ArrayList;

public class a
{
  protected String a = "";

  public a()
  {
    if (("mounted".equals(Environment.getExternalStorageState())) && (Environment.getExternalStorageDirectory().canWrite()));
    for (int i = 1; ; i = 0)
    {
      if (i != 0)
      {
        File localFile1 = Environment.getExternalStorageDirectory();
        this.a = (localFile1.getPath() + "/tencent/assistant/");
        File localFile2 = new File(this.a);
        if (!localFile2.exists())
          localFile2.mkdirs();
        this.a += ".SystemConfig.db";
      }
      return;
    }
  }

  private void a(SQLiteDatabase paramSQLiteDatabase)
  {
    if ((paramSQLiteDatabase != null) && (paramSQLiteDatabase.isOpen()));
    try
    {
      paramSQLiteDatabase.close();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private SQLiteDatabase b()
  {
    Object localObject = null;
    int i = 0;
    while (true)
    {
      if (i < 20);
      try
      {
        SQLiteDatabase localSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(this.a, null);
        localObject = localSQLiteDatabase;
        label23: if (localObject == null)
        {
          SystemClock.sleep(50L);
          i++;
          continue;
        }
        b(localObject);
        return localObject;
      }
      catch (SQLiteException localSQLiteException)
      {
        break label23;
      }
    }
  }

  private void b(SQLiteDatabase paramSQLiteDatabase)
  {
    int i = paramSQLiteDatabase.getVersion();
    if (i != 1)
    {
      paramSQLiteDatabase.beginTransaction();
      if (i != 0);
    }
    try
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE if not exists channeldata( itemId INTEGER PRIMARY KEY AUTOINCREMENT, itemData BLOB);");
      paramSQLiteDatabase.setVersion(1);
      paramSQLiteDatabase.setTransactionSuccessful();
      return;
    }
    finally
    {
      paramSQLiteDatabase.endTransaction();
    }
    throw localObject;
  }

  // ERROR //
  private SQLiteDatabase c()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 34	java/io/File
    //   5: dup
    //   6: aload_0
    //   7: getfield 14	com/tencent/tmassistantsdk/b/a:a	Ljava/lang/String;
    //   10: invokespecial 56	java/io/File:<init>	(Ljava/lang/String;)V
    //   13: invokevirtual 59	java/io/File:exists	()Z
    //   16: istore_2
    //   17: aconst_null
    //   18: astore_3
    //   19: iload_2
    //   20: ifne +7 -> 27
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_3
    //   26: areturn
    //   27: iconst_0
    //   28: istore 4
    //   30: iload 4
    //   32: bipush 20
    //   34: if_icmpge -11 -> 23
    //   37: aload_0
    //   38: getfield 14	com/tencent/tmassistantsdk/b/a:a	Ljava/lang/String;
    //   41: aconst_null
    //   42: iconst_1
    //   43: invokestatic 120	android/database/sqlite/SQLiteDatabase:openDatabase	(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;
    //   46: astore 6
    //   48: aload 6
    //   50: astore_3
    //   51: aload_3
    //   52: ifnonnull -29 -> 23
    //   55: ldc2_w 84
    //   58: invokestatic 91	android/os/SystemClock:sleep	(J)V
    //   61: iinc 4 1
    //   64: goto -34 -> 30
    //   67: astore_1
    //   68: aload_0
    //   69: monitorexit
    //   70: aload_1
    //   71: athrow
    //   72: astore 5
    //   74: goto -23 -> 51
    //
    // Exception table:
    //   from	to	target	type
    //   2	17	67	finally
    //   37	48	67	finally
    //   55	61	67	finally
    //   37	48	72	android/database/sqlite/SQLiteException
  }

  public long a(c paramc)
  {
    if (paramc == null);
    byte[] arrayOfByte;
    SQLiteDatabase localSQLiteDatabase;
    do
    {
      do
      {
        return -1L;
        arrayOfByte = paramc.a();
      }
      while (arrayOfByte == null);
      localSQLiteDatabase = b();
    }
    while (localSQLiteDatabase == null);
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("itemData", arrayOfByte);
    long l = localSQLiteDatabase.insert("channeldata", "", localContentValues);
    a(localSQLiteDatabase);
    return l;
  }

  public ArrayList a()
  {
    SQLiteDatabase localSQLiteDatabase = c();
    ArrayList localArrayList = null;
    if (localSQLiteDatabase != null)
    {
      Cursor localCursor = localSQLiteDatabase.rawQuery("select * from channeldata", null);
      localArrayList = null;
      if (localCursor != null)
      {
        boolean bool = localCursor.moveToFirst();
        localArrayList = null;
        if (bool == true)
        {
          int i = localCursor.getColumnIndex("itemId");
          int j = localCursor.getColumnIndex("itemData");
          localArrayList = new ArrayList();
          do
          {
            int k = localCursor.getInt(i);
            c localc = c.a(localCursor.getBlob(j));
            if (localc == null)
              continue;
            localc.a = k;
            localArrayList.add(localc);
          }
          while (localCursor.moveToNext());
          a(localSQLiteDatabase);
        }
      }
      localCursor.close();
      a(localSQLiteDatabase);
    }
    return localArrayList;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.b.a
 * JD-Core Version:    0.6.0
 */