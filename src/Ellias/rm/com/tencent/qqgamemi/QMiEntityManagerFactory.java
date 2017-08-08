package com.tencent.qqgamemi;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.tencent.component.db.EntityManager;
import com.tencent.component.db.EntityManager.UpdateListener;
import com.tencent.component.db.EntityManagerFactory;
import com.tencent.component.db.ISQLiteDatabase;
import com.tencent.component.db.SdcardSQLiteOpenHelper;
import com.tencent.component.db.entity.TableEntity;
import com.tencent.component.utils.IOUtils;
import com.tencent.component.utils.log.LogUtil;
import java.util.concurrent.ConcurrentHashMap;

public class QMiEntityManagerFactory
{
  private static final String a = "QMiEntityManagerFactory";
  private static final int c = 2;
  private static final String d = "qmidb";
  private static volatile ConcurrentHashMap e = new ConcurrentHashMap();
  private EntityManager.UpdateListener b = new a(this);
  private Context f;
  private EntityManagerFactory g;

  private QMiEntityManagerFactory(Context paramContext, String paramString1, boolean paramBoolean, String paramString2)
  {
    this.f = paramContext.getApplicationContext();
    if (paramBoolean)
    {
      this.g = EntityManagerFactory.a(paramContext, 2, paramString1, SdcardSQLiteOpenHelper.a(paramString1, paramString2), this.b);
      return;
    }
    this.g = EntityManagerFactory.a(paramContext, 2, paramString1, null, this.b);
  }

  public static QMiEntityManagerFactory a(Context paramContext)
  {
    return a(paramContext, null);
  }

  public static QMiEntityManagerFactory a(Context paramContext, String paramString)
  {
    return a(paramContext, paramString, false, null);
  }

  public static QMiEntityManagerFactory a(Context paramContext, String paramString1, boolean paramBoolean, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1));
    QMiEntityManagerFactory localQMiEntityManagerFactory1;
    for (String str1 = "qmidb"; ; str1 = paramString1)
    {
      if (paramBoolean)
        str1 = "sdcard_" + str1;
      if (!TextUtils.isEmpty(paramString2));
      for (String str2 = str1 + "_" + paramString2.hashCode(); ; str2 = str1)
      {
        localQMiEntityManagerFactory1 = (QMiEntityManagerFactory)e.get(str2);
        if (localQMiEntityManagerFactory1 != null)
          break;
        synchronized (e)
        {
          QMiEntityManagerFactory localQMiEntityManagerFactory2 = (QMiEntityManagerFactory)e.get(str2);
          if (localQMiEntityManagerFactory2 == null)
          {
            localQMiEntityManagerFactory2 = new QMiEntityManagerFactory(paramContext, str2, paramBoolean, paramString2);
            e.put(str2, localQMiEntityManagerFactory2);
          }
          return localQMiEntityManagerFactory2;
        }
      }
    }
    return localQMiEntityManagerFactory1;
  }

  private static void b(ISQLiteDatabase paramISQLiteDatabase)
  {
    Cursor localCursor = null;
    if (paramISQLiteDatabase != null)
    {
      try
      {
        localCursor = paramISQLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type ='table'", null);
        if (localCursor != null)
          while (true)
          {
            boolean bool = localCursor.moveToNext();
            if (!bool)
              break;
            try
            {
              String str = localCursor.getString(0);
              paramISQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
              TableEntity.a(str);
            }
            catch (Throwable localThrowable)
            {
              LogUtil.e("QMiEntityManagerFactory", localThrowable.getMessage(), localThrowable);
            }
          }
      }
      finally
      {
        IOUtils.a(localCursor);
      }
      IOUtils.a(localCursor);
    }
  }

  private static void b(ISQLiteDatabase paramISQLiteDatabase, String paramString)
  {
    if ((paramISQLiteDatabase != null) && (!TextUtils.isEmpty(paramString)))
      paramISQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + paramString);
  }

  public EntityManager a(Class paramClass, String paramString)
  {
    return this.g.a(paramClass, paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.QMiEntityManagerFactory
 * JD-Core Version:    0.6.0
 */