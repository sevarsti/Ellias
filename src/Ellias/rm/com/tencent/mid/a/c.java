package com.tencent.mid.a;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HttpContext;

final class c
  implements ConnectionKeepAliveStrategy
{
  public long getKeepAliveDuration(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    long l1 = 10L;
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    BasicHeaderElementIterator localBasicHeaderElementIterator = new BasicHeaderElementIterator(paramHttpResponse.headerIterator("Keep-Alive"));
    while (true)
    {
      String str2;
      if (localBasicHeaderElementIterator.hasNext())
      {
        HeaderElement localHeaderElement = localBasicHeaderElementIterator.nextElement();
        String str1 = localHeaderElement.getName();
        str2 = localHeaderElement.getValue();
        if ((str2 == null) || (!str1.equalsIgnoreCase("timeout")))
          continue;
      }
      try
      {
        long l2 = Long.parseLong(str2);
        if (l2 > 11L)
          l1 = l2 - l1;
        return l1 * 1000L;
        return 180000L;
      }
      catch (NumberFormatException localNumberFormatException)
      {
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.a.c
 * JD-Core Version:    0.6.0
 */