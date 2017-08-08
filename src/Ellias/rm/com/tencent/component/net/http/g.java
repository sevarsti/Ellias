package com.tencent.component.net.http;

import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

class g extends HttpEntityWrapper
{
  public g(HttpEntity paramHttpEntity)
  {
    super(paramHttpEntity);
  }

  public InputStream getContent()
  {
    return new GZIPInputStream(this.wrappedEntity.getContent());
  }

  public long getContentLength()
  {
    return -1L;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.g
 * JD-Core Version:    0.6.0
 */