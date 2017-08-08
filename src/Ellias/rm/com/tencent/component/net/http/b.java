package com.tencent.component.net.http;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

class b
  implements HttpResponseInterceptor
{
  b(AsyncHttpClient paramAsyncHttpClient)
  {
  }

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    if (localHttpEntity == null);
    while (true)
    {
      return;
      Header localHeader = localHttpEntity.getContentEncoding();
      if (localHeader == null)
        continue;
      HeaderElement[] arrayOfHeaderElement = localHeader.getElements();
      int i = arrayOfHeaderElement.length;
      for (int j = 0; j < i; j++)
      {
        if (!arrayOfHeaderElement[j].getName().equalsIgnoreCase("gzip"))
          continue;
        paramHttpResponse.setEntity(new g(localHttpEntity));
        return;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.b
 * JD-Core Version:    0.6.0
 */