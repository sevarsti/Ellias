package com.tencent.qqgamemi;

import com.tencent.component.db.EntityManager.UpdateListener;
import com.tencent.component.db.ISQLiteDatabase;
import com.tencent.component.utils.log.LogUtil;

class a
  implements EntityManager.UpdateListener
{
  a(QMiEntityManagerFactory paramQMiEntityManagerFactory)
  {
  }

  public void onDatabaseDowngrade(ISQLiteDatabase paramISQLiteDatabase, int paramInt1, int paramInt2)
  {
    LogUtil.i("QMiEntityManagerFactory", "onDatabaseDowngrade(" + paramInt1 + " --> " + paramInt2 + ")");
    QMiEntityManagerFactory.a(paramISQLiteDatabase);
  }

  public void onDatabaseUpgrade(ISQLiteDatabase paramISQLiteDatabase, int paramInt1, int paramInt2)
  {
    LogUtil.i("QMiEntityManagerFactory", "onDatabaseUpgrade(" + paramInt1 + " --> " + paramInt2 + ")");
    QMiEntityManagerFactory.a(paramISQLiteDatabase);
  }

  public void onTableDowngrade(ISQLiteDatabase paramISQLiteDatabase, String paramString, int paramInt1, int paramInt2)
  {
    LogUtil.i("QMiEntityManagerFactory", "onTableDowngrade(" + paramInt1 + " --> " + paramInt2 + ",tableName:" + paramString + ")");
    QMiEntityManagerFactory.a(paramISQLiteDatabase, paramString);
  }

  public void onTableUpgrade(ISQLiteDatabase paramISQLiteDatabase, String paramString, int paramInt1, int paramInt2)
  {
    LogUtil.i("QMiEntityManagerFactory", "onTableUpgrade(" + paramInt1 + " --> " + paramInt2 + ",tableName:" + paramString + ")");
    QMiEntityManagerFactory.a(paramISQLiteDatabase, paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.a
 * JD-Core Version:    0.6.0
 */