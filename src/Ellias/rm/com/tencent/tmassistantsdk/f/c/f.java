package com.tencent.tmassistantsdk.f.c;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.tmassistantsdk.f.a.b;
import com.tencent.tmassistantsdk.f.a.c;
import java.util.HashMap;

public class f
  implements g
{
  private static int a(String paramString1, String paramString2, String paramString3, SQLiteDatabase paramSQLiteDatabase)
  {
    int i;
    if ((paramString1 == null) || (paramString2 == null))
      i = -1;
    while (true)
    {
      return i;
      try
      {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("settingField", paramString1);
        localContentValues.put("value", paramString2);
        localContentValues.put("type", paramString3);
        int j = paramSQLiteDatabase.update("settingInfo", localContentValues, "settingField = ?", new String[] { paramString1 });
        i = j;
        if (i <= 0)
          return 0;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return -2;
      }
      finally
      {
      }
    }
    throw localObject;
  }

  public static void a(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 != null) && (paramString2 != null));
    try
    {
      SQLiteDatabase localSQLiteDatabase = b.a().getWritableDatabase();
      if (a(paramString1, paramString2, paramString3, localSQLiteDatabase) <= 0)
      {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("settingField", paramString1);
        localContentValues.put("value", paramString2);
        localContentValues.put("type", paramString3);
        localSQLiteDatabase.insert("settingInfo", null, localContentValues);
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static HashMap c()
  {
    Cursor localCursor = null;
    HashMap localHashMap = new HashMap();
    while (true)
    {
      try
      {
        localCursor = b.a().getReadableDatabase().rawQuery("select * from settingInfo", null);
        if ((localCursor != null) && (localCursor.moveToFirst()))
        {
          localHashMap.put(localCursor.getString(localCursor.getColumnIndex("settingField")), localCursor.getString(localCursor.getColumnIndex("value")));
          boolean bool = localCursor.moveToNext();
          if (!bool)
            return localHashMap;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        if (localCursor == null)
          continue;
        continue;
      }
      finally
      {
        if (localCursor == null)
          continue;
        localCursor.close();
      }
      if (localCursor == null)
        continue;
    }
  }

  public String a()
  {
    return "settingInfo";
  }

  public void a(SQLiteDatabase paramSQLiteDatabase1, SQLiteDatabase paramSQLiteDatabase2)
  {
  }

  public String[] a(int paramInt1, int paramInt2)
  {
    return null;
  }

  public String b()
  {
    return "CREATE TABLE if not exists settingInfo( _id INTEGER PRIMARY KEY AUTOINCREMENT, settingField TEXT , value TEXT,type TEXT);";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.f.c.f
 * JD-Core Version:    0.6.0
 */