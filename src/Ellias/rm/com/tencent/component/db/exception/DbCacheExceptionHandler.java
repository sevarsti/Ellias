package com.tencent.component.db.exception;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteException;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.tencent.component.plugin.PluginContextWrapper;
import com.tencent.component.utils.PlatformUtil;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.component.utils.log.LogUtil;

public class DbCacheExceptionHandler
{
  private static final String a = "DbCacheExceptionHandler";
  private static final boolean b = true;
  private static final Handler c = new Handler(Looper.getMainLooper());
  private volatile Context d;

  public static DbCacheExceptionHandler a()
  {
    return b.a;
  }

  private void a(int paramInt)
  {
    Context localContext = PluginContextWrapper.a(this.d);
    if (localContext != null)
      if (paramInt == 0)
        break label35;
    label35: for (String str = localContext.getResources().getString(paramInt); ; str = null)
    {
      Toast.makeText(localContext, str, 1).show();
      return;
    }
  }

  private void a(CharSequence paramCharSequence)
  {
    Context localContext = PluginContextWrapper.a(this.d);
    if (localContext != null)
      Toast.makeText(localContext, paramCharSequence, 1).show();
  }

  private static void a(Runnable paramRunnable)
  {
    if (c())
    {
      paramRunnable.run();
      return;
    }
    c.post(paramRunnable);
  }

  private void b()
  {
    if (c())
    {
      a(ResourceUtil.b("appfw_access_db_error"));
      return;
    }
    a(new a(this));
  }

  private static boolean c()
  {
    return Thread.currentThread() == Looper.getMainLooper().getThread();
  }

  public void a(Context paramContext)
  {
    if (paramContext != null);
    for (Context localContext = paramContext.getApplicationContext(); ; localContext = null)
    {
      this.d = localContext;
      return;
    }
  }

  public void a(Throwable paramThrowable)
  {
    if (paramThrowable == null)
      return;
    LogUtil.e("DbCacheExceptionHandler", "handle exception", paramThrowable);
    if (((paramThrowable instanceof SQLiteDiskIOException)) || ((paramThrowable instanceof SQLiteDatabaseCorruptException)) || ((PlatformUtil.version() >= 11) && (paramThrowable.getClass().getSimpleName().equals("SQLiteCantOpenDatabaseException"))) || ((PlatformUtil.version() >= 11) && (paramThrowable.getClass().getSimpleName().equals("SQLiteAccessPermException"))) || (((paramThrowable instanceof SQLiteException)) && (paramThrowable.getMessage().contains("no such table"))))
    {
      b();
      return;
    }
    if ((paramThrowable instanceof RuntimeException))
      throw ((RuntimeException)paramThrowable);
    throw new DbCacheExceptionHandler.DbCacheError(paramThrowable);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.exception.DbCacheExceptionHandler
 * JD-Core Version:    0.6.0
 */