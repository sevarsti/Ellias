package com.tencent.tmassistantsdk.f.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.tmassistantsdk.f.c.g;
import com.tencent.tmassistantsdk.g.f;
import com.tencent.tmassistantsdk.g.l;

public abstract class c extends SQLiteOpenHelper
{
  public c(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, paramString, paramCursorFactory, paramInt);
  }

  private void a(SQLiteDatabase paramSQLiteDatabase)
  {
    l.b("sqliteHelper", "tables count:" + c().length);
    Class[] arrayOfClass = c();
    int i = arrayOfClass.length;
    int j = 0;
    while (true)
      if (j < i)
      {
        Class localClass = arrayOfClass[j];
        try
        {
          g localg = (g)localClass.newInstance();
          String str = localg.b();
          if ((str != null) && (str.length() > 0))
            paramSQLiteDatabase.execSQL(str);
          l.b("sqliteHelper", "sql=" + str);
          if (f.e("tmassistant_sdk.db"))
          {
            l.b("sqliteHelper", "dataMovement");
            localg.a(a.a().getReadableDatabase(), paramSQLiteDatabase);
          }
          j++;
        }
        catch (InstantiationException localInstantiationException)
        {
          while (true)
            localInstantiationException.printStackTrace();
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          while (true)
            localIllegalAccessException.printStackTrace();
        }
      }
    f.f("tmassistant_sdk.db");
  }

  private void b(SQLiteDatabase paramSQLiteDatabase)
  {
    Class[] arrayOfClass = c();
    int i = arrayOfClass.length;
    int j = 0;
    while (true)
      if (j < i)
      {
        Class localClass = arrayOfClass[j];
        try
        {
          paramSQLiteDatabase.delete(((g)localClass.newInstance()).a(), null, null);
          j++;
        }
        catch (InstantiationException localInstantiationException)
        {
          while (true)
            localInstantiationException.printStackTrace();
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          while (true)
            localIllegalAccessException.printStackTrace();
        }
      }
  }

  public abstract int b();

  public abstract Class[] c();

  // ERROR //
  public SQLiteDatabase getReadableDatabase()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 106	android/database/sqlite/SQLiteOpenHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   6: astore 4
    //   8: aload 4
    //   10: astore_2
    //   11: aload_2
    //   12: invokevirtual 110	android/database/sqlite/SQLiteDatabase:isDbLockedByCurrentThread	()Z
    //   15: ifne +10 -> 25
    //   18: aload_2
    //   19: invokevirtual 113	android/database/sqlite/SQLiteDatabase:isDbLockedByOtherThreads	()Z
    //   22: ifeq +27 -> 49
    //   25: ldc2_w 114
    //   28: invokestatic 121	android/os/SystemClock:sleep	(J)V
    //   31: goto -20 -> 11
    //   34: astore_3
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_3
    //   38: athrow
    //   39: astore_1
    //   40: aload_1
    //   41: invokevirtual 122	java/lang/Exception:printStackTrace	()V
    //   44: aconst_null
    //   45: astore_2
    //   46: goto -35 -> 11
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_2
    //   52: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   2	8	34	finally
    //   11	25	34	finally
    //   25	31	34	finally
    //   40	44	34	finally
    //   2	8	39	java/lang/Exception
  }

  // ERROR //
  public SQLiteDatabase getWritableDatabase()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 125	android/database/sqlite/SQLiteOpenHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   6: astore 4
    //   8: aload 4
    //   10: astore_2
    //   11: aload_2
    //   12: invokevirtual 110	android/database/sqlite/SQLiteDatabase:isDbLockedByCurrentThread	()Z
    //   15: ifne +10 -> 25
    //   18: aload_2
    //   19: invokevirtual 113	android/database/sqlite/SQLiteDatabase:isDbLockedByOtherThreads	()Z
    //   22: ifeq +27 -> 49
    //   25: ldc2_w 114
    //   28: invokestatic 121	android/os/SystemClock:sleep	(J)V
    //   31: goto -20 -> 11
    //   34: astore_3
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_3
    //   38: athrow
    //   39: astore_1
    //   40: aload_1
    //   41: invokevirtual 122	java/lang/Exception:printStackTrace	()V
    //   44: aconst_null
    //   45: astore_2
    //   46: goto -35 -> 11
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_2
    //   52: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   2	8	34	finally
    //   11	25	34	finally
    //   25	31	34	finally
    //   40	44	34	finally
    //   2	8	39	java/lang/Exception
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    a(paramSQLiteDatabase);
  }

  public void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    b(paramSQLiteDatabase);
    a(paramSQLiteDatabase);
  }

  public void onOpen(SQLiteDatabase paramSQLiteDatabase)
  {
    super.onOpen(paramSQLiteDatabase);
    int i = paramSQLiteDatabase.getVersion();
    l.b("sqliteHelper", " dbversion:" + i + " newVersion:" + b());
    if (i == 0);
    do
    {
      return;
      if (i >= b())
        continue;
      onUpgrade(paramSQLiteDatabase, i, b());
      return;
    }
    while (i <= b());
    onDowngrade(paramSQLiteDatabase, i, b());
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    while (paramInt1 < paramInt2)
    {
      Class[] arrayOfClass = c();
      int i = arrayOfClass.length;
      int j = 0;
      while (j < i)
      {
        Class localClass = arrayOfClass[j];
        try
        {
          String[] arrayOfString = ((g)localClass.newInstance()).a(paramInt1, paramInt1 + 1);
          l.b("sqliteHelper", " upgrade:" + arrayOfString);
          if (arrayOfString != null)
            for (int k = 0; k < arrayOfString.length; k++)
              paramSQLiteDatabase.execSQL(arrayOfString[k]);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          j++;
        }
      }
      paramInt1++;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.f.a.c
 * JD-Core Version:    0.6.0
 */