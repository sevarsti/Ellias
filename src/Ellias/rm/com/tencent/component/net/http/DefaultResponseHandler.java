package com.tencent.component.net.http;

import com.tencent.component.net.http.request.AsyncHttpRequest;
import com.tencent.component.utils.thread.ThreadPool.JobContext;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.ByteArrayBuffer;

public class DefaultResponseHandler
  implements AsyncResponseHandler
{
  private static final int a = 4096;
  private ContentHandler b;

  private boolean a(AsyncHttpResult paramAsyncHttpResult, HttpResponse paramHttpResponse)
  {
    ContentHandler localContentHandler = this.b;
    if (localContentHandler != null)
      return localContentHandler.a(paramAsyncHttpResult, paramHttpResponse);
    return true;
  }

  public void a(ContentHandler paramContentHandler)
  {
    this.b = paramContentHandler;
  }

  byte[] a(HttpEntity paramHttpEntity)
  {
    byte[] arrayOfByte1 = null;
    if (paramHttpEntity != null)
    {
      InputStream localInputStream = paramHttpEntity.getContent();
      arrayOfByte1 = null;
      if (localInputStream != null)
      {
        long l = paramHttpEntity.getContentLength();
        if (l > 2147483647L)
          throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
        if (l < 0L)
          l = 4096L;
        ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer((int)l);
        try
        {
          byte[] arrayOfByte2 = new byte[4096];
          while (true)
          {
            int i = localInputStream.read(arrayOfByte2);
            if (i == -1)
              break;
            localByteArrayBuffer.append(arrayOfByte2, 0, i);
          }
        }
        finally
        {
          localInputStream.close();
        }
        localInputStream.close();
        arrayOfByte1 = localByteArrayBuffer.buffer();
      }
    }
    return arrayOfByte1;
  }

  public AsyncHttpResult generateAsyncHttpResult(String paramString)
  {
    return new AsyncHttpResult(paramString);
  }

  public boolean onResponseReceived(HttpResponse paramHttpResponse, AsyncHttpResult paramAsyncHttpResult, AsyncHttpRequest paramAsyncHttpRequest, ThreadPool.JobContext paramJobContext)
  {
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    long l1 = localHttpEntity.getContentLength();
    if (l1 == -1L)
    {
      Header[] arrayOfHeader = paramHttpResponse.getHeaders("Content-Length");
      if ((arrayOfHeader != null) && (arrayOfHeader.length > 0))
        l1 = Long.valueOf(arrayOfHeader[0].getValue()).longValue();
    }
    paramAsyncHttpResult.getContent().contentLength = l1;
    Header localHeader1 = localHttpEntity.getContentType();
    if (localHeader1 != null)
      paramAsyncHttpResult.getContent().type = localHeader1.getValue();
    Header localHeader2 = localHttpEntity.getContentEncoding();
    if (localHeader2 != null)
      paramAsyncHttpResult.getContent().encoding = localHeader2.getValue();
    if (!a(paramAsyncHttpResult, paramHttpResponse))
    {
      paramAsyncHttpResult.getStatus().a(new AsyncHttpResult.ContentTypeMismatchDescription(paramAsyncHttpResult.getContent().type));
      return false;
    }
    byte[] arrayOfByte = a(localHttpEntity);
    paramAsyncHttpResult.getContent().content = arrayOfByte;
    AsyncHttpResult.Content localContent = paramAsyncHttpResult.getContent();
    long l2;
    if (arrayOfByte != null)
      l2 = arrayOfByte.length;
    while (true)
    {
      localContent.receivedLength = l2;
      return true;
      l2 = 0L;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.DefaultResponseHandler
 * JD-Core Version:    0.6.0
 */