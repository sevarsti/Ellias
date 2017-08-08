package com.tencent.component.db;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=8)
public abstract interface EntityManager$UpdateListener
{
  @PluginApi(a=8)
  public abstract void onDatabaseDowngrade(ISQLiteDatabase paramISQLiteDatabase, int paramInt1, int paramInt2);

  @PluginApi(a=8)
  public abstract void onDatabaseUpgrade(ISQLiteDatabase paramISQLiteDatabase, int paramInt1, int paramInt2);

  @PluginApi(a=8)
  public abstract void onTableDowngrade(ISQLiteDatabase paramISQLiteDatabase, String paramString, int paramInt1, int paramInt2);

  @PluginApi(a=8)
  public abstract void onTableUpgrade(ISQLiteDatabase paramISQLiteDatabase, String paramString, int paramInt1, int paramInt2);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.EntityManager.UpdateListener
 * JD-Core Version:    0.6.0
 */