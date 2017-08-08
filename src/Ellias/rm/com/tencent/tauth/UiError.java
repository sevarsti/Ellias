package com.tencent.tauth;

public class UiError
{
  public int errorCode;
  public String errorDetail;
  public String errorMessage;

  public UiError(int paramInt, String paramString1, String paramString2)
  {
    this.errorMessage = paramString1;
    this.errorCode = paramInt;
    this.errorDetail = paramString2;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tauth.UiError
 * JD-Core Version:    0.6.0
 */