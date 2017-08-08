package com.tencent.component.net.download.multiplex.http;

import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpRequester$miTM
  implements TrustManager, X509TrustManager
{
  public boolean a(X509Certificate[] paramArrayOfX509Certificate)
  {
    return true;
  }

  public boolean b(X509Certificate[] paramArrayOfX509Certificate)
  {
    return true;
  }

  public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
  {
  }

  public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
  {
  }

  public X509Certificate[] getAcceptedIssuers()
  {
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.http.HttpRequester.miTM
 * JD-Core Version:    0.6.0
 */