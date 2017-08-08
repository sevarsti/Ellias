package com.tencent.component.net.http;

import android.text.TextUtils;
import com.tencent.component.utils.log.LogUtil;
import org.apache.http.HttpResponse;

class h
  implements ContentHandler
{
  h(HttpTemplate paramHttpTemplate)
  {
  }

  public boolean a(AsyncHttpResult paramAsyncHttpResult, HttpResponse paramHttpResponse)
  {
    String str = paramAsyncHttpResult.getContent().type;
    if ((!TextUtils.isEmpty(str)) && (str.trim().toLowerCase().indexOf("text/html") != -1))
    {
      LogUtil.e("HttpTemplate", "Protocol response's content type mismatch , cotent-type :" + str);
      return false;
    }
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.h
 * JD-Core Version:    0.6.0
 */