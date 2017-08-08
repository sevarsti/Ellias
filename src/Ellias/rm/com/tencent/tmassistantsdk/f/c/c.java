package com.tencent.tmassistantsdk.f.c;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.tencent.tmassistantsdk.f.a.b;
import com.tencent.tmassistantsdk.f.b.a;

public class c
  implements g
{
  private static int a(a parama, SQLiteDatabase paramSQLiteDatabase)
  {
    int j;
    if (parama == null)
      j = -1;
    while (true)
    {
      return j;
      try
      {
        ContentValues localContentValues = new ContentValues();
        a(localContentValues, parama);
        String[] arrayOfString = new String[2];
        arrayOfString[0] = parama.a;
        arrayOfString[1] = parama.c;
        int i = paramSQLiteDatabase.update("clientinfo", localContentValues, "clientId = ? and taskUrl = ?", arrayOfString);
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

  private static a a(Cursor paramCursor)
  {
    a locala = new a();
    locala.a = paramCursor.getString(paramCursor.getColumnIndex("clientId"));
    locala.b = paramCursor.getInt(paramCursor.getColumnIndex("taskId"));
    locala.c = paramCursor.getString(paramCursor.getColumnIndex("taskUrl"));
    return locala;
  }

  private static void a(ContentValues paramContentValues, a parama)
  {
    if (parama != null)
    {
      paramContentValues.put("clientId", parama.a);
      paramContentValues.put("taskId", Integer.valueOf(parama.b));
      paramContentValues.put("taskUrl", parama.c);
    }
  }

  public static void a(a parama)
  {
    if (parama != null);
    try
    {
      SQLiteDatabase localSQLiteDatabase = b.a().getWritableDatabase();
      if (a(parama, localSQLiteDatabase) <= 0)
      {
        ContentValues localContentValues = new ContentValues();
        a(localContentValues, parama);
        localSQLiteDatabase.insert("clientinfo", null, localContentValues);
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
    if (!TextUtils.isEmpty(paramString));
    try
    {
      b.a().getWritableDatabase().delete("clientinfo", "taskUrl = ?", new String[] { paramString });
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static void a(String paramString1, String paramString2)
  {
    a locala = new a();
    locala.a = paramString1;
    locala.c = paramString2;
    a(locala);
  }

  public String a()
  {
    return "clientinfo";
  }

  public void a(SQLiteDatabase paramSQLiteDatabase1, SQLiteDatabase paramSQLiteDatabase2)
  {
    Cursor localCursor = null;
    if ((paramSQLiteDatabase2 != null) && (paramSQLiteDatabase1 != null))
      paramSQLiteDatabase2.beginTransaction();
    try
    {
      localCursor = paramSQLiteDatabase1.rawQuery("select * from clientinfo", null);
      if ((localCursor != null) && (localCursor.moveToFirst()))
      {
        boolean bool;
        do
        {
          ContentValues localContentValues = new ContentValues();
          a(localContentValues, a(localCursor));
          paramSQLiteDatabase2.insert("clientinfo", null, localContentValues);
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
    return null;
  }

  public String b()
  {
    return "CREATE TABLE if not exists clientinfo( _id INTEGER PRIMARY KEY AUTOINCREMENT, clientId TEXT , taskId INTEGER, taskUrl TEXT);";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.f.c.c
 * JD-Core Version:    0.6.0
 */