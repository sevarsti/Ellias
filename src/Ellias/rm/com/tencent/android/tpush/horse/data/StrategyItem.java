package com.tencent.android.tpush.horse.data;

import android.text.TextUtils;
import java.io.Serializable;

public class StrategyItem
  implements Serializable
{
  private static final long serialVersionUID = 1692027785653072243L;
  private int protocolType;
  private String proxyIp;
  private int proxyPort;
  private int redirect;
  private String serverIp;
  private int serverPort;

  public StrategyItem(String paramString1, int paramInt1, String paramString2, int paramInt2, int paramInt3, int paramInt4)
  {
    this.serverIp = paramString1;
    this.serverPort = paramInt1;
    this.proxyIp = paramString2;
    this.proxyPort = paramInt2;
    this.protocolType = paramInt3;
    this.redirect = paramInt4;
  }

  public String a()
  {
    return this.serverIp;
  }

  public void a(int paramInt)
  {
    this.redirect = paramInt;
  }

  public boolean a(StrategyItem paramStrategyItem)
  {
    return (paramStrategyItem != null) && (this.serverIp.equals(paramStrategyItem.serverIp)) && (this.serverPort == paramStrategyItem.serverPort) && (this.proxyIp.equals(paramStrategyItem.proxyIp)) && (this.proxyPort == paramStrategyItem.proxyPort) && (this.protocolType == paramStrategyItem.protocolType);
  }

  public int b()
  {
    return this.serverPort;
  }

  public String c()
  {
    return this.proxyIp;
  }

  public int d()
  {
    return this.protocolType;
  }

  public int e()
  {
    return this.proxyPort;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof StrategyItem))
      return a((StrategyItem)paramObject);
    return super.equals(paramObject);
  }

  public int f()
  {
    return this.redirect;
  }

  public boolean g()
  {
    return (!TextUtils.isEmpty(this.serverIp)) && (this.serverPort != 0);
  }

  public boolean h()
  {
    return !TextUtils.isEmpty(this.proxyIp);
  }

  public int hashCode()
  {
    int i;
    int j;
    int k;
    if (this.serverIp == null)
    {
      i = 0;
      j = 31 * (31 * (i + 31) + this.serverPort);
      String str = this.proxyIp;
      k = 0;
      if (str != null)
        break label80;
    }
    while (true)
    {
      return 0x7FFFFFFF & 31 * (31 * (31 * (j + k) + this.proxyPort) + this.protocolType) + this.redirect;
      i = this.serverIp.hashCode();
      break;
      label80: k = this.proxyIp.hashCode();
    }
  }

  public boolean i()
  {
    return this.protocolType == 1;
  }

  public boolean j()
  {
    return this.redirect == 1;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("serverIp=").append(this.serverIp).append(",serverPort=").append(this.serverPort).append(", proxyIp=").append(this.proxyIp).append(",proxyPort=").append(this.proxyPort).append(", protocolType=");
    if (this.protocolType == 1);
    for (String str = "http"; ; str = "tcp")
      return str + ", redirect=" + this.redirect;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.data.StrategyItem
 * JD-Core Version:    0.6.0
 */