package com.tencent.component.net.http.download;

import com.tencent.component.net.http.AsyncHttpResult;

public class DownloadResult extends AsyncHttpResult
{
  private String a;

  public DownloadResult(String paramString)
  {
    super(paramString);
  }

  public void a(String paramString)
  {
    this.a = paramString;
  }

  public String b()
  {
    return this.a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.download.DownloadResult
 * JD-Core Version:    0.6.0
 */