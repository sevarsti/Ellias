package strategy;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TxHostSource extends JceStruct
  implements Cloneable
{
  public String ip = "";
  public int port = 0;
  public String txHost = "";
  public String uri = "";

  static
  {
    if (!TxHostSource.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TxHostSource()
  {
    setIp(this.ip);
    setPort(this.port);
    setUri(this.uri);
    setTxHost(this.txHost);
  }

  public TxHostSource(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    setIp(paramString1);
    setPort(paramInt);
    setUri(paramString2);
    setTxHost(paramString3);
  }

  public final String className()
  {
    return "strategy.TxHostSource";
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
    localJceDisplayer.display(this.port, "port");
    localJceDisplayer.display(this.uri, "uri");
    localJceDisplayer.display(this.txHost, "txHost");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TxHostSource localTxHostSource;
    do
    {
      return false;
      localTxHostSource = (TxHostSource)paramObject;
    }
    while ((!JceUtil.equals(this.ip, localTxHostSource.ip)) || (!JceUtil.equals(this.port, localTxHostSource.port)) || (!JceUtil.equals(this.uri, localTxHostSource.uri)) || (!JceUtil.equals(this.txHost, localTxHostSource.txHost)));
    return true;
  }

  public final String fullClassName()
  {
    return "strategy.TxHostSource";
  }

  public final String getIp()
  {
    return this.ip;
  }

  public final int getPort()
  {
    return this.port;
  }

  public final String getTxHost()
  {
    return this.txHost;
  }

  public final String getUri()
  {
    return this.uri;
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
    this.port = paramJceInputStream.read(this.port, 1, true);
    this.uri = paramJceInputStream.readString(2, true);
    this.txHost = paramJceInputStream.readString(3, true);
  }

  public final void setIp(String paramString)
  {
    this.ip = paramString;
  }

  public final void setPort(int paramInt)
  {
    this.port = paramInt;
  }

  public final void setTxHost(String paramString)
  {
    this.txHost = paramString;
  }

  public final void setUri(String paramString)
  {
    this.uri = paramString;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.ip, 0);
    paramJceOutputStream.write(this.port, 1);
    paramJceOutputStream.write(this.uri, 2);
    paramJceOutputStream.write(this.txHost, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     strategy.TxHostSource
 * JD-Core Version:    0.6.0
 */