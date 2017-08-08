package com.tencent.component.net.http;

public class AsyncHttpResult$NetworkUnavailableDescription extends AsyncHttpResult.FailDescription
{
  public AsyncHttpResult$NetworkUnavailableDescription()
  {
    super(6, "Network unavailable!");
  }

  public String description()
  {
    return (String)this.extraInfo;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.AsyncHttpResult.NetworkUnavailableDescription
 * JD-Core Version:    0.6.0
 */