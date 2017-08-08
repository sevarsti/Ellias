package com.pay.http;

import com.pay.common.tool.APLog;
import com.pay.tool.APAppDataInterface;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.conn.util.InetAddressUtils;

public class APBaseHttpParam
{
  public static final int CONNECT_TIMEOUT = 15000;
  public static final int READ_TIMEOUT = 15000;
  public static final int TRY_TIMES = 2;
  public long begTime = 0L;
  public int connectTimeout = 15000;
  public String defaultDomain = "";
  public String domain = "";
  public long endTime = 0L;
  public String port = "";
  public int reTryTimes = 2;
  public int readTimeout = 15000;
  public HashMap reqParam = new HashMap();
  public String reqType = "http://";
  public int requestTimes = 0;
  public String sendType = "GET";
  public String url;
  public String urlName = "";
  public String urlParams = "";

  public void constructParams()
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    Iterator localIterator;
    if (this.reqParam != null)
      localIterator = this.reqParam.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (localStringBuilder.length() > 0)
        {
          localStringBuilder.deleteCharAt(-1 + localStringBuilder.length());
          this.urlParams = localStringBuilder.toString();
        }
        APLog.i("APBaseHttpReq", "urlParams=" + this.urlParams);
        return;
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localStringBuilder.append((String)localEntry.getKey());
      localStringBuilder.append("=");
      localStringBuilder.append((String)localEntry.getValue());
      localStringBuilder.append("&");
    }
  }

  public void constructReTryUrl()
  {
    String str2;
    if (this.requestTimes < this.reTryTimes)
    {
      if (this.requestTimes != -1 + this.reTryTimes)
        break label126;
      this.domain = this.defaultDomain;
      str2 = "";
      if ((isIPAddress(this.domain)) && (this.port.length() != 0))
        str2 = ":" + this.port;
    }
    label126: String str1;
    for (this.url = (this.reqType + this.domain + str2 + this.urlName); ; this.url = (this.reqType + this.domain + str1 + this.urlName))
    {
      this.requestTimes = (1 + this.requestTimes);
      return;
      this.domain = APIPList.getInstance().getRandomIP(this.domain);
      str1 = "";
      if ((!isIPAddress(this.domain)) || (this.port.length() == 0))
        continue;
      str1 = ":" + this.port;
    }
  }

  public void constructUrl()
  {
    constructParams();
    if (this.sendType.equals("GET"))
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(this.url);
      if (!this.url.endsWith("?"))
        localStringBuffer.append("?");
      localStringBuffer.append(this.urlParams.toString());
      this.url = localStringBuffer.toString();
    }
  }

  public boolean isIPAddress(String paramString)
  {
    return (paramString != null) && ((InetAddressUtils.isIPv4Address(paramString)) || (InetAddressUtils.isIPv6Address(paramString)));
  }

  public void setReqWithHttp()
  {
    this.reqType = "http://";
  }

  public void setReqWithHttps()
  {
    this.reqType = "https://";
  }

  public void setSendWithGet()
  {
    this.sendType = "GET";
  }

  public void setSendWithPost()
  {
    this.sendType = "POST";
  }

  public void setUrl(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    String str1 = APAppDataInterface.singleton().getEnv();
    String str2 = "";
    if ((isIPAddress(this.domain)) && (this.port.length() != 0))
      str2 = ":" + this.port;
    if (str1.equals("custom"))
    {
      this.urlName = paramString1;
      this.defaultDomain = "api.unipay.qq.com";
      this.url = (this.reqType + this.domain + str2 + paramString1);
    }
    do
    {
      return;
      if (str1.equals("dev"))
      {
        this.urlName = paramString2;
        this.defaultDomain = "183.60.36.92";
        this.url = (this.reqType + this.domain + str2 + paramString2);
        return;
      }
      if (!str1.equals("test"))
        continue;
      this.urlName = paramString3;
      this.defaultDomain = "sandbox.api.unipay.qq.com";
      this.url = (this.reqType + this.domain + str2 + paramString3);
      return;
    }
    while (!str1.equals("release"));
    this.urlName = paramString4;
    this.defaultDomain = "api.unipay.qq.com";
    this.url = (this.reqType + this.domain + str2 + paramString4);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.APBaseHttpParam
 * JD-Core Version:    0.6.0
 */