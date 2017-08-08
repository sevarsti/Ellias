package com.tencent.component.db;

import android.content.Context;

public abstract interface ISQLiteOpenHelper
{
  public abstract ISQLiteDatabase a();

  public abstract void a(Context paramContext, String paramString, int paramInt);

  public abstract void a(Context paramContext, String paramString, int paramInt, EntityManager.UpdateListener paramUpdateListener);

  public abstract ISQLiteDatabase b();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.ISQLiteOpenHelper
 * JD-Core Version:    0.6.0
 */