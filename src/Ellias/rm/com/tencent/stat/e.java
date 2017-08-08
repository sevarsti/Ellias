package com.tencent.stat;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

class e extends DefaultConnectionKeepAliveStrategy
{
  e(d paramd)
  {
  }

  public long getKeepAliveDuration(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    long l = super.getKeepAliveDuration(paramHttpResponse, paramHttpContext);
    if (l == -1L)
      l = 20000L;
    return l;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.e
 * JD-Core Version:    0.6.0
 */