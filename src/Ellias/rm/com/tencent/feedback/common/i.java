package com.tencent.feedback.common;

import android.os.Build;
import com.tencent.feedback.anr.a;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public final class i
{
  private static i b = null;
  private boolean a = false;

  protected i()
  {
    this.a = bool;
    String str = Build.TAGS;
    if ((str != null) && (str.contains("test-keys")))
      e.b("rqdp{  test-keys}", new Object[0]);
    while (true)
    {
      if ((!bool) && (!c()) && (!d()))
        this.a = false;
      return;
      bool = false;
    }
  }

  public static i a()
  {
    monitorenter;
    try
    {
      if (b == null)
        b = new i();
      i locali = b;
      return locali;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private static boolean c()
  {
    try
    {
      boolean bool = new File("/system/app/Superuser.apk").exists();
      int i = 0;
      if (bool)
      {
        e.b("rqdp{  super_apk}", new Object[0]);
        i = 1;
      }
      return i;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  private static boolean d()
  {
    ArrayList localArrayList = a.a(new String[] { "/system/bin/sh", "-c", "type su" });
    if ((localArrayList != null) && (localArrayList.size() > 0))
    {
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        e.b(str, new Object[0]);
        if (str.contains("not found"))
          return false;
      }
      e.b("rqdp{  sufile}", new Object[0]);
      return true;
    }
    e.b("rqdp{  no response}", new Object[0]);
    return false;
  }

  public final boolean b()
  {
    monitorenter;
    try
    {
      boolean bool = this.a;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.i
 * JD-Core Version:    0.6.0
 */