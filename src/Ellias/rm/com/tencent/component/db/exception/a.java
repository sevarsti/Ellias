package com.tencent.component.db.exception;

import com.tencent.component.utils.ResourceUtil;

class a
  implements Runnable
{
  a(DbCacheExceptionHandler paramDbCacheExceptionHandler)
  {
  }

  public void run()
  {
    DbCacheExceptionHandler.a(this.a, ResourceUtil.b("appfw_access_db_error"));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.exception.a
 * JD-Core Version:    0.6.0
 */