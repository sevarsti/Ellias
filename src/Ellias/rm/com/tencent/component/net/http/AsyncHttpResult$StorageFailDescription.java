package com.tencent.component.net.http;

public class AsyncHttpResult$StorageFailDescription extends AsyncHttpResult.FailDescription
{
  public AsyncHttpResult$StorageFailDescription()
  {
    super(2, "storage is not enough!");
  }

  public String description()
  {
    return (String)this.extraInfo;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.AsyncHttpResult.StorageFailDescription
 * JD-Core Version:    0.6.0
 */