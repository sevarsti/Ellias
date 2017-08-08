package com.tencent.msdk.communicator;

import android.os.Bundle;
import com.tencent.msdk.tea.TEACoding;

public class MHttpRequest
{
  private byte[] body;
  private HttpMethod method;
  private Bundle params = new Bundle();
  private TEACoding teaCode = new TEACoding("msdkmsdkmsdkmsdk".getBytes());
  private String url = "";

  public void addParam(String paramString1, String paramString2)
  {
    this.params.putString(paramString1, paramString2);
  }

  public byte[] getBody()
  {
    return this.body;
  }

  public HttpMethod getMethod()
  {
    return this.method;
  }

  public Bundle getParams()
  {
    return this.params;
  }

  public String getUrl()
  {
    return this.url;
  }

  public void setBody(String paramString)
  {
    if (HttpRequestManager.isEncode.booleanValue())
    {
      this.body = this.teaCode.encode(paramString.getBytes());
      return;
    }
    this.body = paramString.getBytes();
  }

  public void setBody(byte[] paramArrayOfByte)
  {
    this.body = paramArrayOfByte;
  }

  public void setMethod(HttpMethod paramHttpMethod)
  {
    this.method = paramHttpMethod;
  }

  public void setParams(Bundle paramBundle)
  {
    this.params = paramBundle;
  }

  public void setUrl(String paramString)
  {
    this.url = paramString;
  }

  public static enum HttpMethod
  {
    static
    {
      DELETE = new HttpMethod("DELETE", 3);
      HttpMethod[] arrayOfHttpMethod = new HttpMethod[4];
      arrayOfHttpMethod[0] = GET;
      arrayOfHttpMethod[1] = POST;
      arrayOfHttpMethod[2] = PUT;
      arrayOfHttpMethod[3] = DELETE;
      $VALUES = arrayOfHttpMethod;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.communicator.MHttpRequest
 * JD-Core Version:    0.6.0
 */