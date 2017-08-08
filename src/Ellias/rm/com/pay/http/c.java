package com.pay.http;

import com.pay.common.tool.APLog;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

final class c
  implements HostnameVerifier
{
  c(APHttpsReqPost paramAPHttpsReqPost)
  {
  }

  public final boolean verify(String paramString, SSLSession paramSSLSession)
  {
    if (APIPList.getInstance().checkIpInList(paramString))
    {
      APLog.i("APHttpsRePost", "hostName:" + paramString + " verify hostName true");
      return true;
    }
    APLog.i("APHttpsRePost", "hostName:" + paramString + " verify hostName false");
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.c
 * JD-Core Version:    0.6.0
 */