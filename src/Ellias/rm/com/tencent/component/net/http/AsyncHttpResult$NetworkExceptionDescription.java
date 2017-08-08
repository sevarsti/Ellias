package com.tencent.component.net.http;

public class AsyncHttpResult$NetworkExceptionDescription extends AsyncHttpResult.FailDescription
{
  public AsyncHttpResult$NetworkExceptionDescription(Throwable paramThrowable)
  {
    super(4, paramThrowable);
  }

  public String description()
  {
    if (this.extraInfo != null);
    for (String str = ((Throwable)this.extraInfo).getMessage(); ; str = "")
      return "NetworkException occurs (" + str + ")";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.AsyncHttpResult.NetworkExceptionDescription
 * JD-Core Version:    0.6.0
 */