package com.tencent.android.tpush.service.channel.exception;

public class ChannelException extends Exception
{
  public int errorCode;

  public ChannelException(int paramInt, String paramString)
  {
    super(paramString);
    this.errorCode = paramInt;
  }

  public ChannelException(int paramInt, String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
    this.errorCode = paramInt;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.exception.ChannelException
 * JD-Core Version:    0.6.0
 */