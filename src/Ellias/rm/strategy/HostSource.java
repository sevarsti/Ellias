package strategy;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class HostSource extends JceStruct
  implements Cloneable
{
  public String domain = "";
  public String ip = "";
  public int protocolType = 0;
  public String url = "";

  static
  {
    if (!HostSource.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public HostSource()
  {
    setIp(this.ip);
    setDomain(this.domain);
    setUrl(this.url);
    setProtocolType(this.protocolType);
  }

  public HostSource(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    setIp(paramString1);
    setDomain(paramString2);
    setUrl(paramString3);
    setProtocolType(paramInt);
  }

  public final String className()
  {
    return "strategy.HostSource";
  }

  public final Object clone()
  {
    try
    {
      Object localObject2 = super.clone();
      localObject1 = localObject2;
      return localObject1;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      boolean bool;
      do
      {
        bool = $assertionsDisabled;
        Object localObject1 = null;
      }
      while (bool);
    }
    throw new AssertionError();
  }

  public final void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.ip, "ip");
    localJceDisplayer.display(this.domain, "domain");
    localJceDisplayer.display(this.url, "url");
    localJceDisplayer.display(this.protocolType, "protocolType");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    HostSource localHostSource;
    do
    {
      return false;
      localHostSource = (HostSource)paramObject;
    }
    while ((!JceUtil.equals(this.ip, localHostSource.ip)) || (!JceUtil.equals(this.domain, localHostSource.domain)) || (!JceUtil.equals(this.url, localHostSource.url)) || (!JceUtil.equals(this.protocolType, localHostSource.protocolType)));
    return true;
  }

  public final String fullClassName()
  {
    return "strategy.HostSource";
  }

  public final String getDomain()
  {
    return this.domain;
  }

  public final String getIp()
  {
    return this.ip;
  }

  public final int getProtocolType()
  {
    return this.protocolType;
  }

  public final String getUrl()
  {
    return this.url;
  }

  public final int hashCode()
  {
    try
    {
      throw new Exception("Need define key first!");
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }

  public final void readFrom(JceInputStream paramJceInputStream)
  {
    this.ip = paramJceInputStream.readString(0, true);
    this.domain = paramJceInputStream.readString(1, true);
    this.url = paramJceInputStream.readString(2, true);
    this.protocolType = paramJceInputStream.read(this.protocolType, 3, false);
  }

  public final void setDomain(String paramString)
  {
    this.domain = paramString;
  }

  public final void setIp(String paramString)
  {
    this.ip = paramString;
  }

  public final void setProtocolType(int paramInt)
  {
    this.protocolType = paramInt;
  }

  public final void setUrl(String paramString)
  {
    this.url = paramString;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.ip, 0);
    paramJceOutputStream.write(this.domain, 1);
    paramJceOutputStream.write(this.url, 2);
    paramJceOutputStream.write(this.protocolType, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     strategy.HostSource
 * JD-Core Version:    0.6.0
 */