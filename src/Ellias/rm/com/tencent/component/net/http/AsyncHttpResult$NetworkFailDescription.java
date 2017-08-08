package com.tencent.component.net.http;

public class AsyncHttpResult$NetworkFailDescription extends AsyncHttpResult.FailDescription
{
  public static final int a = -10900;

  public AsyncHttpResult$NetworkFailDescription(int paramInt)
  {
    super(3, Integer.valueOf(paramInt));
  }

  public String description()
  {
    return "Http request failed (" + this.extraInfo + ")";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.AsyncHttpResult.NetworkFailDescription
 * JD-Core Version:    0.6.0
 */