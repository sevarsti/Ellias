package com.pay.http;

import com.pay.common.tool.APLog;
import java.net.HttpURLConnection;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public abstract class APHttpsReqPost extends APBaseHttpReq
{
  public APHttpsReqPost()
  {
    this.httpParam.setReqWithHttps();
    this.httpParam.setSendWithPost();
  }

  protected void preCreateConnection()
  {
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      if (isIPAddress(this.httpParam.domain))
      {
        APLog.i("APHttpsReqPost", "ssl check init");
        TrustManager[] arrayOfTrustManager = new TrustManager[1];
        arrayOfTrustManager[0] = new d();
        localSSLContext.init(null, arrayOfTrustManager, new SecureRandom());
        HttpsURLConnection.setDefaultHostnameVerifier(new c(this));
      }
      while (true)
      {
        HttpsURLConnection.setDefaultSSLSocketFactory(localSSLContext.getSocketFactory());
        super.preCreateConnection();
        return;
        APLog.i("APHttpsReqPost", "ssl system check init");
        localSSLContext.init(null, null, new SecureRandom());
      }
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  protected void setBody()
  {
    super.setBody();
  }

  public void setHeader()
  {
    try
    {
      this.httpURLConnection.setDoInput(true);
      this.httpURLConnection.setDoOutput(true);
      this.httpURLConnection.setRequestMethod("POST");
      this.httpURLConnection.setRequestProperty("Charset", "UTF-8");
      this.httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      return;
    }
    catch (Exception localException)
    {
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.APHttpsReqPost
 * JD-Core Version:    0.6.0
 */