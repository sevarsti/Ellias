package com.tencent.component.net.http.request;

import android.content.Context;
import com.tencent.component.net.http.FluentCaseInsensitiveStringsMap;
import com.tencent.component.utils.HttpUtil;
import com.tencent.component.utils.HttpUtil.RequestOptions;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;

public class AsyncHttpPostRequest extends AsyncHttpRequestBase
{
  private HttpEntity a;
  private String b;

  public void a(String paramString)
  {
    this.b = paramString;
  }

  public void a(HttpEntity paramHttpEntity)
  {
    this.a = paramHttpEntity;
  }

  public HttpUriRequest generateHttpRequest(Context paramContext, HttpUtil.RequestOptions paramRequestOptions)
  {
    return HttpUtil.createHttpPost(paramContext, getUrl(), this.a, paramRequestOptions);
  }

  public FluentCaseInsensitiveStringsMap getHeaders()
  {
    return null;
  }

  public String getIdentifier()
  {
    return String.valueOf(getSeqNo());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.request.AsyncHttpPostRequest
 * JD-Core Version:    0.6.0
 */