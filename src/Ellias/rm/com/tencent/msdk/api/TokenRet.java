package com.tencent.msdk.api;

public class TokenRet
{
  public long expiration = 0L;
  public int type = 0;
  public String value = "";

  public TokenRet()
  {
  }

  public TokenRet(int paramInt, String paramString, long paramLong)
  {
    this.type = paramInt;
    this.value = paramString;
    this.expiration = paramLong;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.api.TokenRet
 * JD-Core Version:    0.6.0
 */