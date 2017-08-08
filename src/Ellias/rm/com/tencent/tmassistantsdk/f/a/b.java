package com.tencent.tmassistantsdk.f.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.tencent.tmassistantsdk.f.c.d;
import com.tencent.tmassistantsdk.f.c.e;
import com.tencent.tmassistantsdk.f.c.h;
import com.tencent.tmassistantsdk.f.c.i;

public class b extends c
{
  protected static c a = null;
  private static final Class[] b = { d.class, com.tencent.tmassistantsdk.f.c.f.class, com.tencent.tmassistantsdk.f.c.c.class, h.class, e.class, i.class };

  public b(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, paramString, paramCursorFactory, paramInt);
  }

  public static c a()
  {
    monitorenter;
    try
    {
      if (a == null)
      {
        Context localContext = com.tencent.tmassistantsdk.g.f.a().b();
        if (localContext != null)
          a = new b(localContext, "tmassistant_sdk_v2.db", null, 2);
      }
      c localc = a;
      return localc;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int b()
  {
    return 2;
  }

  public Class[] c()
  {
    return b;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.f.a.b
 * JD-Core Version:    0.6.0
 */