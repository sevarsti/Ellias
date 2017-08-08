package com.tenpay.a.b;

import android.net.Proxy;
import android.os.Bundle;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

public abstract class a
{
  protected volatile boolean a = false;
  protected HttpHost b;

  public a()
  {
  }

  public a(boolean paramBoolean)
  {
    if (!paramBoolean)
      a();
  }

  public abstract Bundle a(String paramString);

  public void a()
  {
    String str = Proxy.getDefaultHost();
    int i = Proxy.getDefaultPort();
    if (str != null)
      this.b = new HttpHost(str, i);
  }

  protected void a(Bundle paramBundle)
  {
    paramBundle.putInt("op_code", 1);
  }

  protected void a(Bundle paramBundle, HttpEntity paramHttpEntity, boolean paramBoolean)
  {
    if (paramBoolean);
    Object localObject;
    try
    {
      localObject = new GZIPInputStream(paramHttpEntity.getContent());
      break label193;
      InputStream localInputStream = paramHttpEntity.getContent();
      localObject = localInputStream;
    }
    catch (IllegalStateException localIllegalStateException2)
    {
      localIllegalStateException2.printStackTrace();
      localObject = null;
    }
    catch (IOException localIOException1)
    {
      localIOException1.printStackTrace();
      localObject = null;
    }
    label193: 
    do
    {
      byte[] arrayOfByte = new byte[1024];
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      try
      {
        while (true)
        {
          int i;
          if (!this.a)
          {
            i = ((InputStream)localObject).read(arrayOfByte);
            if (i > 0);
          }
          else
          {
            if (!this.a)
              break;
            d(paramBundle);
            if (localObject != null);
            try
            {
              ((InputStream)localObject).close();
              if (localByteArrayOutputStream == null)
                return;
              localByteArrayOutputStream.close();
              return;
            }
            catch (IOException localIOException3)
            {
              localIOException3.printStackTrace();
              return;
            }
          }
          localByteArrayOutputStream.write(arrayOfByte, 0, i);
        }
      }
      catch (IllegalStateException localIllegalStateException1)
      {
        while (true)
          localIllegalStateException1.printStackTrace();
      }
      catch (IOException localIOException2)
      {
        while (true)
        {
          localIOException2.printStackTrace();
          continue;
          paramBundle.putByteArray("data", localByteArrayOutputStream.toByteArray());
          a(paramBundle);
        }
      }
    }
    while (localObject != null);
  }

  protected void a(Bundle paramBundle, HttpResponse paramHttpResponse)
  {
    Header[] arrayOfHeader = paramHttpResponse.getAllHeaders();
    int i = paramHttpResponse.getAllHeaders().length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      paramBundle.putString(arrayOfHeader[j].getName(), arrayOfHeader[j].getValue());
    }
  }

  public HttpClient b()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 20000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 60000);
    HttpClientParams.setRedirecting(localBasicHttpParams, true);
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      localSSLContext.init(null, null, new SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(localSSLContext.getSocketFactory());
    }
    catch (Exception localException1)
    {
      try
      {
        while (true)
        {
          SchemeRegistry localSchemeRegistry = new SchemeRegistry();
          SSLSocketFactory localSSLSocketFactory = SSLSocketFactory.getSocketFactory();
          localSSLSocketFactory.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
          localSchemeRegistry.register(new Scheme("https", localSSLSocketFactory, 443));
          localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
          return new DefaultHttpClient(new SingleClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
          localException1 = localException1;
          localException1.printStackTrace();
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
    return new DefaultHttpClient(localBasicHttpParams);
  }

  protected void b(Bundle paramBundle)
  {
    paramBundle.putInt("op_code", 2);
  }

  protected void b(Bundle paramBundle, HttpResponse paramHttpResponse)
  {
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    String str = paramBundle.getString("Content-Encoding");
    if ((str != null) && (str.indexOf("gzip") >= 0))
    {
      a(paramBundle, localHttpEntity, true);
      return;
    }
    a(paramBundle, localHttpEntity, false);
  }

  protected void c(Bundle paramBundle)
  {
    paramBundle.putInt("op_code", 3);
  }

  protected void d(Bundle paramBundle)
  {
    paramBundle.putInt("op_code", 4);
  }

  protected void e(Bundle paramBundle)
  {
    paramBundle.putInt("op_code", 5);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.a.b.a
 * JD-Core Version:    0.6.0
 */