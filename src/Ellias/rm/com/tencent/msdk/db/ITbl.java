package com.tencent.msdk.db;

import java.util.ArrayList;

public abstract interface ITbl
{
  public abstract boolean create();

  public abstract int delete();

  public abstract int deleteAll();

  public abstract BaseUserInfo find();

  public abstract ArrayList<BaseUserInfo> findAll();

  public abstract String getTableName();

  public abstract boolean isExisted();

  public abstract boolean save();

  public abstract int update();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.db.ITbl
 * JD-Core Version:    0.6.0
 */