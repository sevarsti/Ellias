package com.tencent.component.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DefaultSQLiteDatabase
  implements ISQLiteDatabase
{
  private SQLiteDatabase a;

  public DefaultSQLiteDatabase(SQLiteDatabase paramSQLiteDatabase)
  {
    if (paramSQLiteDatabase == null)
      throw new IllegalArgumentException("SQLiteDatabase cannot be null!");
    this.a = paramSQLiteDatabase;
  }

  public void beginTransaction()
  {
    this.a.beginTransaction();
  }

  public void close()
  {
    this.a.close();
  }

  public int delete(String paramString1, String paramString2, String[] paramArrayOfString)
  {
    return this.a.delete(paramString1, paramString2, paramArrayOfString);
  }

  public void endTransaction()
  {
    this.a.endTransaction();
  }

  public void execSQL(String paramString)
  {
    this.a.execSQL(paramString);
  }

  public void execSQL(String paramString, Object[] paramArrayOfObject)
  {
    this.a.execSQL(paramString, paramArrayOfObject);
  }

  public String getPath()
  {
    return this.a.getPath();
  }

  public int getVersion()
  {
    return this.a.getVersion();
  }

  public boolean inTransaction()
  {
    return this.a.inTransaction();
  }

  public long insert(String paramString1, String paramString2, ContentValues paramContentValues)
  {
    return this.a.insert(paramString1, paramString2, paramContentValues);
  }

  public long insertOrThrow(String paramString1, String paramString2, ContentValues paramContentValues)
  {
    return this.a.insertOrThrow(paramString1, paramString2, paramContentValues);
  }

  public long insertWithOnConflict(String paramString1, String paramString2, ContentValues paramContentValues, int paramInt)
  {
    return this.a.insertWithOnConflict(paramString1, paramString2, paramContentValues, paramInt);
  }

  public boolean isOpen()
  {
    return this.a.isOpen();
  }

  public boolean isReadOnly()
  {
    return this.a.isReadOnly();
  }

  public boolean needUpgrade(int paramInt)
  {
    return this.a.needUpgrade(paramInt);
  }

  public Cursor query(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5)
  {
    return this.a.query(paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, paramString3, paramString4, paramString5);
  }

  public Cursor query(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    return this.a.query(paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, paramString3, paramString4, paramString5, paramString6);
  }

  public Cursor query(boolean paramBoolean, String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    return this.a.query(paramBoolean, paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, paramString3, paramString4, paramString5, paramString6);
  }

  public Cursor rawQuery(String paramString, String[] paramArrayOfString)
  {
    return this.a.rawQuery(paramString, paramArrayOfString);
  }

  public long replace(String paramString1, String paramString2, ContentValues paramContentValues)
  {
    return this.a.replace(paramString1, paramString2, paramContentValues);
  }

  public long replaceOrThrow(String paramString1, String paramString2, ContentValues paramContentValues)
  {
    return this.a.replaceOrThrow(paramString1, paramString2, paramContentValues);
  }

  public void setTransactionSuccessful()
  {
    this.a.setTransactionSuccessful();
  }

  public void setVersion(int paramInt)
  {
    this.a.setVersion(paramInt);
  }

  public int update(String paramString1, ContentValues paramContentValues, String paramString2, String[] paramArrayOfString)
  {
    return this.a.update(paramString1, paramContentValues, paramString2, paramArrayOfString);
  }

  public int updateWithOnConflict(String paramString1, ContentValues paramContentValues, String paramString2, String[] paramArrayOfString, int paramInt)
  {
    return this.a.updateWithOnConflict(paramString1, paramContentValues, paramString2, paramArrayOfString, paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.DefaultSQLiteDatabase
 * JD-Core Version:    0.6.0
 */