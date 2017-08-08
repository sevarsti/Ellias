package com.tencent.apkupdate.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.tencent.apkupdate.c.b;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public final class a
{
  private static String a = "10.0.0.172";
  private static int b = 80;
  private static String c = "10.0.0.200";
  private static HttpClient d = null;

  public static HttpClient a()
  {
    monitorenter;
    try
    {
      if (d == null)
      {
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(localBasicHttpParams, 1000L);
        ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 200);
        ConnManagerParams.setMaxConnectionsPerRoute(localBasicHttpParams, new ConnPerRouteBean(20));
        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 30000);
        HttpConnectionParams.setSoTimeout(localBasicHttpParams, 30000);
        HttpProtocolParams.setUseExpectContinue(localBasicHttpParams, true);
        HttpClientParams.setRedirecting(localBasicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 4096);
        SchemeRegistry localSchemeRegistry = new SchemeRegistry();
        localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        localSchemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        d = new DefaultHttpClient(new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
      }
      HttpClient localHttpClient1 = d;
      Context localContext = b.a().b();
      String str1;
      if (localContext == null)
      {
        str1 = "";
        if (!TextUtils.isEmpty(str1))
        {
          if ((!str1.equalsIgnoreCase("cmwap")) && (!str1.equalsIgnoreCase("3gwap")) && (!str1.equalsIgnoreCase("uniwap")))
            break label335;
          HttpHost localHttpHost1 = new HttpHost(a, b);
          localHttpClient1.getParams().setParameter("http.route.default-proxy", localHttpHost1);
        }
      }
      while (true)
      {
        HttpClient localHttpClient2 = d;
        return localHttpClient2;
        if (localContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == -1)
        {
          str1 = "";
          break;
        }
        NetworkInfo localNetworkInfo = ((ConnectivityManager)localContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (localNetworkInfo == null)
        {
          str1 = "";
          break;
        }
        if (localNetworkInfo.getType() == 1)
        {
          str1 = "wifi";
          break;
        }
        String str2 = localNetworkInfo.getExtraInfo();
        if (str2 == null)
        {
          str1 = "";
          break;
        }
        str1 = str2.toLowerCase();
        break;
        label335: if (!str1.equalsIgnoreCase("ctwap"))
          continue;
        HttpHost localHttpHost2 = new HttpHost(c, b);
        localHttpClient1.getParams().setParameter("http.route.default-proxy", localHttpHost2);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.b.a
 * JD-Core Version:    0.6.0
 */