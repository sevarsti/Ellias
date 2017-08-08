package com.tencent.component.net.http;

public class AsyncHttpResult$ContentTypeMismatchDescription extends AsyncHttpResult.FailDescription
{
  public AsyncHttpResult$ContentTypeMismatchDescription(String paramString)
  {
    super(5, paramString);
  }

  public String description()
  {
    return "ContentType invalid (" + this.extraInfo + ")";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.AsyncHttpResult.ContentTypeMismatchDescription
 * JD-Core Version:    0.6.0
 */