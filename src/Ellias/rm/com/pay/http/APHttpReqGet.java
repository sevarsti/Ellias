package com.pay.http;

import java.net.HttpURLConnection;

public abstract class APHttpReqGet extends APBaseHttpReq
{
  public APHttpReqGet()
  {
    this.httpParam.setSendWithGet();
  }

  protected void setBody()
  {
    super.setBody();
  }

  public void setHeader()
  {
    try
    {
      this.httpURLConnection.setRequestMethod("GET");
      this.httpURLConnection.setDoInput(true);
      this.httpURLConnection.setRequestProperty("Connection", "close");
      this.httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      return;
    }
    catch (Exception localException)
    {
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.APHttpReqGet
 * JD-Core Version:    0.6.0
 */