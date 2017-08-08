package com.tencent.android.tpush.service.channel.exception;

public class HorseIgnoreException extends Exception
{
  private static final long serialVersionUID = 8810013461438153758L;

  public HorseIgnoreException(Exception paramException)
  {
    super(paramException);
  }

  public HorseIgnoreException(String paramString)
  {
    super(paramString);
  }

  public HorseIgnoreException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.exception.HorseIgnoreException
 * JD-Core Version:    0.6.0
 */