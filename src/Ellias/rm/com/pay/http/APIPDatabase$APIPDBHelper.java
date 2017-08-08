package com.pay.http;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.pay.common.tool.APLog;

public class APIPDatabase$APIPDBHelper extends SQLiteOpenHelper
{
  public APIPDatabase$APIPDBHelper(APIPDatabase paramAPIPDatabase, Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, paramString, paramCursorFactory, paramInt);
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    APLog.i("APIPDatabase", "onCreate");
    APIPDatabase.a(this.a, paramSQLiteDatabase, "TencentUnipayIPTable");
    APIPDatabase.a(this.a, paramSQLiteDatabase, "TencentUnipayIPH5Table");
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    APLog.i("APIPDatabase", "onUpgrade");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.APIPDatabase.APIPDBHelper
 * JD-Core Version:    0.6.0
 */