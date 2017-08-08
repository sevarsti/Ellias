package com.tencent.component.db.exception;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=4)
public class DBException extends RuntimeException
{
  private static final long serialVersionUID = 1L;

  @PluginApi(a=4)
  public DBException()
  {
  }

  @PluginApi(a=4)
  public DBException(String paramString)
  {
    super(paramString);
  }

  @PluginApi(a=4)
  public DBException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.exception.DBException
 * JD-Core Version:    0.6.0
 */