package com.tencent.component.db;

import android.content.ContentValues;
import android.database.Cursor;
import com.tencent.component.annotation.PluginApi;

@PluginApi(a=8)
public abstract interface ISQLiteDatabase
{
  @PluginApi(a=8)
  public abstract void beginTransaction();

  @PluginApi(a=8)
  public abstract void close();

  @PluginApi(a=8)
  public abstract int delete(String paramString1, String paramString2, String[] paramArrayOfString);

  @PluginApi(a=8)
  public abstract void endTransaction();

  @PluginApi(a=8)
  public abstract void execSQL(String paramString);

  @PluginApi(a=8)
  public abstract void execSQL(String paramString, Object[] paramArrayOfObject);

  @PluginApi(a=8)
  public abstract String getPath();

  @PluginApi(a=8)
  public abstract int getVersion();

  @PluginApi(a=8)
  public abstract boolean inTransaction();

  @PluginApi(a=8)
  public abstract long insert(String paramString1, String paramString2, ContentValues paramContentValues);

  @PluginApi(a=8)
  public abstract long insertOrThrow(String paramString1, String paramString2, ContentValues paramContentValues);

  @PluginApi(a=8)
  public abstract long insertWithOnConflict(String paramString1, String paramString2, ContentValues paramContentValues, int paramInt);

  @PluginApi(a=8)
  public abstract boolean isOpen();

  @PluginApi(a=8)
  public abstract boolean isReadOnly();

  @PluginApi(a=8)
  public abstract boolean needUpgrade(int paramInt);

  @PluginApi(a=8)
  public abstract Cursor query(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5);

  @PluginApi(a=8)
  public abstract Cursor query(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6);

  @PluginApi(a=8)
  public abstract Cursor query(boolean paramBoolean, String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6);

  @PluginApi(a=8)
  public abstract Cursor rawQuery(String paramString, String[] paramArrayOfString);

  @PluginApi(a=8)
  public abstract long replace(String paramString1, String paramString2, ContentValues paramContentValues);

  @PluginApi(a=8)
  public abstract long replaceOrThrow(String paramString1, String paramString2, ContentValues paramContentValues);

  @PluginApi(a=8)
  public abstract void setTransactionSuccessful();

  @PluginApi(a=8)
  public abstract void setVersion(int paramInt);

  @PluginApi(a=8)
  public abstract int update(String paramString1, ContentValues paramContentValues, String paramString2, String[] paramArrayOfString);

  @PluginApi(a=8)
  public abstract int updateWithOnConflict(String paramString1, ContentValues paramContentValues, String paramString2, String[] paramArrayOfString, int paramInt);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.ISQLiteDatabase
 * JD-Core Version:    0.6.0
 */