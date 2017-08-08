package com.tencent.android.tpush.a;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.common.c;

public class f
{
  private static f a = null;
  private Context b = null;

  public static f a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (a == null)
      {
        a = new f();
        a.b = paramContext.getApplicationContext();
      }
      f localf = a;
      return localf;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(Intent paramIntent)
  {
    c.a().a(new g(this, this.b, paramIntent, null));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.a.f
 * JD-Core Version:    0.6.0
 */