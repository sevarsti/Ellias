package com.tencent.tmassistantsdk.downloadservice;

import android.text.TextUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class k
{
  public static HttpClient a()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 30000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 30000);
    HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 4096);
    HttpClientParams.setRedirecting(localBasicHttpParams, false);
    return new DefaultHttpClient(localBasicHttpParams);
  }

  public static void a(HttpClient paramHttpClient)
  {
    String str = c.b();
    if (!TextUtils.isEmpty(str))
    {
      if ((!str.equalsIgnoreCase("cmwap")) && (!str.equalsIgnoreCase("3gwap")) && (!str.equalsIgnoreCase("uniwap")))
        break label66;
      HttpHost localHttpHost1 = new HttpHost("10.0.0.172", 80);
      paramHttpClient.getParams().setParameter("http.route.default-proxy", localHttpHost1);
    }
    label66: 
    do
      return;
    while (!str.equalsIgnoreCase("ctwap"));
    HttpHost localHttpHost2 = new HttpHost("10.0.0.200", 80);
    paramHttpClient.getParams().setParameter("http.route.default-proxy", localHttpHost2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.k
 * JD-Core Version:    0.6.0
 */