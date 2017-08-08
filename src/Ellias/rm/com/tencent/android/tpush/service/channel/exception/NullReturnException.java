package com.tencent.android.tpush.service.channel.exception;

public class NullReturnException extends Exception
{
  private static final long serialVersionUID = -2623309261327598087L;
  private int statusCode = -1;

  public NullReturnException(String paramString)
  {
    super(paramString);
  }

  public NullReturnException(String paramString, Exception paramException)
  {
    super(paramString, paramException);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.exception.NullReturnException
 * JD-Core Version:    0.6.0
 */