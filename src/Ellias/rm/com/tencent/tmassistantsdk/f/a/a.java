package com.tencent.tmassistantsdk.f.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.tencent.tmassistantsdk.f.c.d;
import com.tencent.tmassistantsdk.f.c.e;
import com.tencent.tmassistantsdk.f.c.h;

public class a extends c
{
  protected static c a = null;
  private static final Class[] b = { d.class, com.tencent.tmassistantsdk.f.c.f.class, com.tencent.tmassistantsdk.f.c.c.class, h.class, e.class };

  public a(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, "tmassistant_sdk.db", paramCursorFactory, paramInt);
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
          a = new a(localContext, "tmassistant_sdk.db", null, 1);
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
    return 1;
  }

  public Class[] c()
  {
    return b;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.f.a.a
 * JD-Core Version:    0.6.0
 */