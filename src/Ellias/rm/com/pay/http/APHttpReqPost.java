package com.pay.http;

import java.net.HttpURLConnection;

public abstract class APHttpReqPost extends APBaseHttpReq
{
  public APHttpReqPost()
  {
    this.httpParam.setReqWithHttp();
    this.httpParam.setSendWithPost();
  }

  protected void setBody()
  {
    super.setBody();
  }

  protected void setHeader()
  {
    try
    {
      this.httpURLConnection.setRequestMethod("POST");
      this.httpURLConnection.setRequestProperty("Charset", "UTF-8");
      this.httpURLConnection.setDoInput(true);
      this.httpURLConnection.setDoOutput(true);
      this.httpURLConnection.setRequestProperty("Content-Length", String.valueOf(this.httpParam.urlParams.getBytes().length));
      this.httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      return;
    }
    catch (Exception localException)
    {
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.APHttpReqPost
 * JD-Core Version:    0.6.0
 */