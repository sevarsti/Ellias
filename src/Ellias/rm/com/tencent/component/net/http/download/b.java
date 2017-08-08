package com.tencent.component.net.http.download;

import android.text.TextUtils;
import com.tencent.component.net.http.AsyncHttpResult;
import com.tencent.component.net.http.AsyncHttpResult.Content;
import com.tencent.component.net.http.ContentHandler;
import com.tencent.component.utils.StringUtils;
import com.tencent.component.utils.log.LogUtil;
import org.apache.http.HttpResponse;

class b
  implements ContentHandler
{
  b(ImageDownloader paramImageDownloader)
  {
  }

  public boolean a(AsyncHttpResult paramAsyncHttpResult, HttpResponse paramHttpResponse)
  {
    String str1 = paramAsyncHttpResult.getContent().type;
    if (TextUtils.isEmpty(str1))
      return false;
    String str2 = str1.trim();
    if ((StringUtils.b(str2, "image")) || ("application/octet-stream".equalsIgnoreCase(str2)))
      return true;
    LogUtil.e("ImageDownloader", "Content type mismatch , cotent-type :" + str2);
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.download.b
 * JD-Core Version:    0.6.0
 */