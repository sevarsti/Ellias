package com.tencent.component.net.http;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

class a
  implements HttpRequestInterceptor
{
  a(AsyncHttpClient paramAsyncHttpClient)
  {
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    if (!paramHttpRequest.containsHeader("Accept-Encoding"))
      paramHttpRequest.addHeader("Accept-Encoding", "gzip");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.a
 * JD-Core Version:    0.6.0
 */