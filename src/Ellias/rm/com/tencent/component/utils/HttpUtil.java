package com.tencent.component.utils;

import android.content.Context;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.utils.log.LogUtil;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultHttpRoutePlanner;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

@PluginApi(a=8)
public class HttpUtil
{
  private static final String a = "HttpUtil";
  private static final int b = 30000;
  private static final int c = 45000;
  private static final HttpUtil.ClientOptions d = new HttpUtil.ClientOptions();

  private static String a(String paramString)
  {
    if (paramString != null);
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      String str = paramString.trim().replace(" ", "");
      int i = str.indexOf('#');
      if (i > 0)
        str = str.substring(0, i);
      return str;
    }
  }

  public static HttpClient a()
  {
    return a(null);
  }

  public static DefaultHttpClient a(HttpUtil.ClientOptions paramClientOptions)
  {
    if (paramClientOptions == null);
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setStaleCheckingEnabled(localBasicHttpParams, false);
    ConnManagerParams.setTimeout(localBasicHttpParams, 30000L);
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 30000);
    HttpConnectionParams.setTcpNoDelay(localBasicHttpParams, true);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 45000);
    HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 8192);
    HttpProtocolParams.setUseExpectContinue(localBasicHttpParams, false);
    HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setUserAgent(localBasicHttpParams, "HttpClient");
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    try
    {
      localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
      localSchemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
      localDefaultHttpClient.setRoutePlanner(new DefaultHttpRoutePlanner(localDefaultHttpClient.getConnectionManager().getSchemeRegistry()));
      return localDefaultHttpClient;
    }
    catch (Exception localException)
    {
      while (true)
        LogUtil.i("HttpUtil", "http register Scheme exception", localException);
    }
  }

  private static void a(Context paramContext, HttpRequest paramHttpRequest, HttpUtil.RequestOptions paramRequestOptions)
  {
    boolean bool1;
    if (paramRequestOptions != null)
    {
      bool1 = paramRequestOptions.allowProxy;
      if (paramRequestOptions == null)
        break label130;
    }
    label130: for (boolean bool2 = paramRequestOptions.apnProxy; ; bool2 = false)
    {
      if ((bool1) && (NetworkUtil.c(paramContext)))
      {
        NetworkUtil.NetworkProxy localNetworkProxy = NetworkUtil.a(paramContext, bool2);
        if (localNetworkProxy != null)
        {
          HttpHost localHttpHost = new HttpHost(localNetworkProxy.a, localNetworkProxy.b);
          paramHttpRequest.getParams().setParameter("http.route.default-proxy", localHttpHost);
          LogUtil.d("HttpUtil", "use proxy[host:" + localNetworkProxy.a + ",port:" + localNetworkProxy.b + "]");
        }
      }
      return;
      bool1 = true;
      break;
    }
  }

  public static void a(HttpRequest paramHttpRequest, boolean paramBoolean)
  {
    boolean bool;
    if (paramHttpRequest != null)
    {
      bool = true;
      AssertUtil.a(bool);
      if (!paramBoolean)
        break label32;
    }
    label32: for (String str = "Keep-Alive"; ; str = "Close")
    {
      paramHttpRequest.setHeader("Connection", str);
      return;
      bool = false;
      break;
    }
  }

  public static boolean a(HttpRequest paramHttpRequest)
  {
    if (paramHttpRequest != null);
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      Object localObject = paramHttpRequest.getParams().getParameter("http.route.default-proxy");
      if ((localObject == null) || (!(localObject instanceof HttpHost)))
        break;
      return true;
    }
    return false;
  }

  private static String b(String paramString)
  {
    if (paramString != null);
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      return new URL(paramString).getAuthority();
    }
  }

  public static HttpContext b()
  {
    return new BasicHttpContext();
  }

  @PluginApi(a=8)
  public static HttpGet createHttpGet(Context paramContext, String paramString)
  {
    return createHttpGet(paramContext, paramString, null);
  }

  @PluginApi(a=8)
  public static HttpGet createHttpGet(Context paramContext, String paramString, HttpUtil.RequestOptions paramRequestOptions)
  {
    String str1 = a(paramString);
    String str2 = b(str1);
    HttpGet localHttpGet = new HttpGet(str1);
    localHttpGet.addHeader("x-online-host", str2);
    localHttpGet.addHeader("Host", str2);
    a(paramContext, localHttpGet, paramRequestOptions);
    return localHttpGet;
  }

  @PluginApi(a=8)
  public static HttpGet createHttpGet(Context paramContext, String paramString1, String paramString2, HttpUtil.RequestOptions paramRequestOptions)
  {
    String str1 = a(paramString1);
    String str2 = a(paramString2);
    String str3 = b(str1);
    HttpGet localHttpGet = new HttpGet(str2);
    localHttpGet.addHeader("x-online-host", str3);
    localHttpGet.addHeader("Host", str3);
    a(paramContext, localHttpGet, paramRequestOptions);
    return localHttpGet;
  }

  @PluginApi(a=8)
  public static HttpPost createHttpPost(Context paramContext, String paramString, HttpEntity paramHttpEntity)
  {
    return createHttpPost(paramContext, paramString, paramHttpEntity, null);
  }

  @PluginApi(a=8)
  public static HttpPost createHttpPost(Context paramContext, String paramString, HttpEntity paramHttpEntity, HttpUtil.RequestOptions paramRequestOptions)
  {
    String str1 = a(paramString);
    String str2 = b(str1);
    HttpPost localHttpPost = new HttpPost(str1);
    localHttpPost.addHeader("Host", str2);
    localHttpPost.addHeader("x-online-host", str2);
    if ((paramHttpEntity instanceof ByteArrayEntity))
      localHttpPost.addHeader("Content-Type", "application/octet-stream");
    localHttpPost.setEntity(paramHttpEntity);
    a(paramContext, localHttpPost, paramRequestOptions);
    return localHttpPost;
  }

  @PluginApi(a=8)
  public static HttpResponse executeHttpGet(Context paramContext, String paramString)
  {
    return executeHttpGet(paramContext, paramString, null);
  }

  @PluginApi(a=8)
  public static HttpResponse executeHttpGet(Context paramContext, String paramString, HttpUtil.RequestOptions paramRequestOptions)
  {
    return a().execute(createHttpGet(paramContext, paramString, paramRequestOptions));
  }

  @PluginApi(a=8)
  public static HttpResponse executeHttpPost(Context paramContext, String paramString, HttpEntity paramHttpEntity)
  {
    return executeHttpPost(paramContext, paramString, paramHttpEntity, null);
  }

  @PluginApi(a=8)
  public static HttpResponse executeHttpPost(Context paramContext, String paramString, HttpEntity paramHttpEntity, HttpUtil.RequestOptions paramRequestOptions)
  {
    return a().execute(createHttpPost(paramContext, paramString, paramHttpEntity, paramRequestOptions));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.HttpUtil
 * JD-Core Version:    0.6.0
 */