package com.tencent.component.net.http;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.TokenIterator;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.protocol.HttpContext;

public class CustomConnectionReuseStrategy extends DefaultConnectionReuseStrategy
{
  public boolean keepAlive(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpContext != null)
    {
      HttpRequest localHttpRequest = (HttpRequest)paramHttpContext.getAttribute("http.request");
      if (localHttpRequest != null)
      {
        HeaderIterator localHeaderIterator = localHttpRequest.headerIterator("Connection");
        if (localHeaderIterator.hasNext())
          try
          {
            TokenIterator localTokenIterator = createTokenIterator(localHeaderIterator);
            while (localTokenIterator.hasNext())
            {
              boolean bool = "Close".equalsIgnoreCase(localTokenIterator.nextToken());
              if (bool)
                return false;
            }
          }
          catch (ParseException localParseException)
          {
          }
      }
    }
    return super.keepAlive(paramHttpResponse, paramHttpContext);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.CustomConnectionReuseStrategy
 * JD-Core Version:    0.6.0
 */