package com.tencent.msdk.db;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.msdk.Singleton;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.tools.Logger;

@TargetApi(14)
public class DbManager extends SQLiteOpenHelper
{
  public static final Singleton<DbManager> gDefault = new Singleton()
  {
    protected DbManager create()
    {
      return new DbManager(WeGame.getInstance().getActivity(), null);
    }
  };

  private DbManager(Context paramContext)
  {
    this(paramContext, "WEGAMEDB2", null, 12);
  }

  private DbManager(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, paramString, paramCursorFactory, paramInt);
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    try
    {
      paramSQLiteDatabase.execSQL(QQLoginModel.getCreateTblSql());
      paramSQLiteDatabase.execSQL(WxLoginModel.getCreateTblSql());
      paramSQLiteDatabase.execSQL(PermissionModel.getCreateTblSql());
      paramSQLiteDatabase.execSQL(AppDBModel.getCreateTblSql());
      paramSQLiteDatabase.execSQL(NoticeDBModel.getCreateTableSql());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  @SuppressLint({"Override"})
  public void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    Logger.d("onDowngrade");
    onUpgrade(paramSQLiteDatabase, paramInt1, paramInt2);
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    Logger.d("onUpgrade");
    try
    {
      paramSQLiteDatabase.execSQL(QQLoginModel.getDropTblSql());
      paramSQLiteDatabase.execSQL(QQLoginModel.getCreateTblSql());
      paramSQLiteDatabase.execSQL(WxLoginModel.getDropTblSql());
      paramSQLiteDatabase.execSQL(WxLoginModel.getCreateTblSql());
      paramSQLiteDatabase.execSQL(PermissionModel.getDropTblSql());
      paramSQLiteDatabase.execSQL(PermissionModel.getCreateTblSql());
      paramSQLiteDatabase.execSQL(AppDBModel.getDropTblSql());
      paramSQLiteDatabase.execSQL(AppDBModel.getCreateTblSql());
      paramSQLiteDatabase.execSQL(NoticeDBModel.getDropTableSql());
      paramSQLiteDatabase.execSQL(NoticeDBModel.getCreateTableSql());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.db.DbManager
 * JD-Core Version:    0.6.0
 */