package com.tencent.component.net.download.multiplex.download.extension;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.component.ComponentContext;

public class DBHelper
{
  static DBHelper a;
  SQLiteDatabase b;

  public static DBHelper a()
  {
    if (a == null)
      a = new DBHelper();
    if (a.b == null)
      a.b = ComponentContext.a().openOrCreateDatabase("download.db", 0, null);
    return a;
  }

  public int a(String paramString, ContentValues paramContentValues)
  {
    return (int)this.b.insert(paramString, null, paramContentValues);
  }

  public int a(String paramString1, ContentValues paramContentValues, String paramString2, String[] paramArrayOfString)
  {
    return this.b.update(paramString1, paramContentValues, paramString2, paramArrayOfString);
  }

  public int a(String paramString1, String paramString2, String[] paramArrayOfString)
  {
    return this.b.delete(paramString1, paramString2, paramArrayOfString);
  }

  public Cursor a(String paramString1, String paramString2)
  {
    return this.b.query(paramString1, null, paramString2, null, null, null, null);
  }

  public Cursor a(String paramString1, String paramString2, String paramString3)
  {
    return this.b.query(paramString1, null, paramString2, null, null, null, paramString3);
  }

  public Cursor a(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3)
  {
    return this.b.query(paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, null, null, paramString3);
  }

  public boolean a(String paramString)
  {
    Cursor localCursor = this.b.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + paramString + "'", null);
    if (localCursor != null)
    {
      if (localCursor.getCount() > 0)
      {
        localCursor.close();
        return true;
      }
      localCursor.close();
    }
    return false;
  }

  public SQLiteDatabase b()
  {
    return this.b;
  }

  public void b(String paramString)
  {
    this.b.execSQL(paramString);
  }

  public void c()
  {
    if (this.b != null)
      this.b.close();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.extension.DBHelper
 * JD-Core Version:    0.6.0
 */