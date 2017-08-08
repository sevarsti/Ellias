package com.pay.http;

import android.content.Context;
import java.util.HashMap;
import org.apache.http.conn.util.InetAddressUtils;

public class APIPH5List
{
  private static APIPH5List a = null;

  private APIPH5List()
  {
    new HashMap();
    new HashMap();
  }

  public static APIPH5List getInstance()
  {
    if (a != null)
      return a;
    return null;
  }

  public static APIPH5List getInstance(Context paramContext)
  {
    if (a == null)
      a = new APIPH5List();
    return a;
  }

  public static void initIPList(Context paramContext)
  {
    a = null;
    a = new APIPH5List();
  }

  public static void release()
  {
    a = null;
  }

  public boolean checkIpInList(String paramString)
  {
    return false;
  }

  public void close()
  {
  }

  public String getRandomIP(String paramString)
  {
    return "";
  }

  public boolean isIPAddress(String paramString)
  {
    return (paramString != null) && ((InetAddressUtils.isIPv4Address(paramString)) || (InetAddressUtils.isIPv6Address(paramString)));
  }

  public void setDomain(String paramString)
  {
  }

  public void setIPState(String paramString, boolean paramBoolean)
  {
  }

  public void updateIPList(HashMap paramHashMap)
  {
  }

  public void updateToDB()
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.APIPH5List
 * JD-Core Version:    0.6.0
 */