package com.tencent.android.tpush.service.report;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.i;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class a
{
  public static c a(Context paramContext)
  {
    if (paramContext == null)
      return null;
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (localConnectivityManager == null)
      return null;
    NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
    if (localNetworkInfo == null)
      return null;
    if (localNetworkInfo.getType() == 0)
    {
      String str = a();
      int i = b();
      if ((!a(str)) && (i >= 0))
        return new c(str, i, null);
    }
    return null;
  }

  private static String a()
  {
    if (Build.VERSION.SDK_INT < 11)
    {
      if (i.e() != null)
      {
        String str = Proxy.getHost(i.e());
        if (a(str))
          str = Proxy.getDefaultHost();
        return str;
      }
      return Proxy.getDefaultHost();
    }
    return System.getProperty("http.proxyHost");
  }

  public static String a(Bundle paramBundle)
  {
    if (paramBundle == null)
      return "";
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramBundle.keySet().iterator();
    int i = 1;
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      Object localObject = paramBundle.get(str2);
      if ((localObject == null) || ((!(localObject instanceof String)) && (!(localObject instanceof String[]))))
        continue;
      label131: label173: label207: int j;
      if ((localObject instanceof String[]))
      {
        String[] arrayOfString;
        int k;
        if (i != 0)
        {
          i = 0;
          localStringBuilder.append(URLEncoder.encode(str2) + "=");
          arrayOfString = (String[])(String[])localObject;
          k = 0;
          if (k >= arrayOfString.length)
            break label207;
          if (k != 0)
            break label173;
          localStringBuilder.append(URLEncoder.encode(arrayOfString[k]));
        }
        while (true)
        {
          k++;
          break label131;
          localStringBuilder.append("&");
          break;
          localStringBuilder.append(URLEncoder.encode("," + arrayOfString[k]));
        }
        j = i;
        i = j;
        continue;
      }
      if (i != 0)
        i = 0;
      while (true)
      {
        localStringBuilder.append("" + URLEncoder.encode(str2) + "=" + URLEncoder.encode(paramBundle.getString(str2)));
        j = i;
        break;
        localStringBuilder.append("&");
      }
    }
    String str1 = localStringBuilder.toString();
    TLog.d("ReportLogTag", ">>> report data [" + str1 + "]");
    return str1;
  }

  public static HttpClient a(String paramString1, String paramString2)
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 30000);
    TLog.d("ReportLogTag", "Common_HttpConnectionTimeout" + 30000 + " socketTimeout:" + 30000 + " url:" + paramString2);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 30000);
    HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset(localBasicHttpParams, "UTF-8");
    HttpProtocolParams.setUserAgent(localBasicHttpParams, "AndroidSDK_" + Build.VERSION.SDK + "_" + Build.DEVICE + "_" + Build.VERSION.RELEASE);
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
    c localc = a(i.e());
    if (localc != null)
    {
      HttpHost localHttpHost = new HttpHost(localc.a, localc.b);
      localDefaultHttpClient.getParams().setParameter("http.route.default-proxy", localHttpHost);
    }
    return localDefaultHttpClient;
  }

  private static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  private static int b()
  {
    int i = -1;
    if (Build.VERSION.SDK_INT < 11)
      if (i.e() != null)
      {
        i = Proxy.getPort(i.e());
        if (i < 0)
          i = Proxy.getDefaultPort();
      }
    String str;
    do
    {
      return i;
      return Proxy.getDefaultPort();
      str = System.getProperty("http.proxyPort");
    }
    while (a(str));
    try
    {
      int j = Integer.parseInt(str);
      return j;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return i;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.report.a
 * JD-Core Version:    0.6.0
 */