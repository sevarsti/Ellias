package com.tencent.component.net.http.request;

import android.content.Context;
import com.tencent.component.net.http.FluentCaseInsensitiveStringsMap;
import com.tencent.component.utils.HttpUtil;
import com.tencent.component.utils.HttpUtil.RequestOptions;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class AsyncHttpGetRequest extends AsyncHttpRequestBase
{
  private String a;
  private Map b;

  public static String a(boolean paramBoolean, String paramString, Map paramMap)
  {
    if (paramBoolean);
    for (String str1 = paramString.replace(" ", "%20"); ; str1 = paramString)
    {
      if (paramMap != null)
      {
        LinkedList localLinkedList = new LinkedList();
        Iterator localIterator = paramMap.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          localLinkedList.add(new BasicNameValuePair((String)localEntry.getKey(), (String)localEntry.getValue()));
        }
        String str2 = URLEncodedUtils.format(localLinkedList, "UTF-8");
        if (!str1.contains("?"))
          return str1 + "?" + str2;
        return str1 + "&" + str2;
      }
      return str1;
    }
  }

  private void a()
  {
    this.a = a(true, getUrl(), this.b);
  }

  public void a(Map paramMap)
  {
    this.b = paramMap;
    a();
  }

  public void b(String paramString)
  {
    super.b(paramString);
    a();
  }

  public HttpUriRequest generateHttpRequest(Context paramContext, HttpUtil.RequestOptions paramRequestOptions)
  {
    return HttpUtil.createHttpGet(paramContext, this.a, paramRequestOptions);
  }

  public FluentCaseInsensitiveStringsMap getHeaders()
  {
    return null;
  }

  public String getIdentifier()
  {
    return this.a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.request.AsyncHttpGetRequest
 * JD-Core Version:    0.6.0
 */