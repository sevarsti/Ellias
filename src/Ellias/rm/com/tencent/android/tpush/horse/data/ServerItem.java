package com.tencent.android.tpush.horse.data;

import com.tencent.android.tpush.service.c.c;
import java.io.Serializable;

public class ServerItem
  implements Serializable
{
  private static final long serialVersionUID = -6609283086276910655L;
  private long serverIpLong;
  private String serverIpStr;
  private int serverPort;
  private int spType;

  public ServerItem(long paramLong, int paramInt1, int paramInt2)
  {
    this.serverIpLong = paramLong;
    this.serverIpStr = c.a(paramLong);
    this.serverPort = paramInt1;
    this.spType = paramInt2;
  }

  public ServerItem(String paramString, int paramInt1, int paramInt2)
  {
    this.serverIpStr = paramString;
    this.serverIpLong = c.b(this.serverIpStr);
    this.serverPort = paramInt1;
    this.spType = paramInt2;
  }

  private boolean a(ServerItem paramServerItem)
  {
    return (paramServerItem != null) && (paramServerItem.a().equals(this.serverIpStr)) && (paramServerItem.b() == this.serverPort) && (paramServerItem.c() == this.spType);
  }

  public String a()
  {
    return this.serverIpStr;
  }

  public int b()
  {
    return this.serverPort;
  }

  public int c()
  {
    return this.spType;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof ServerItem))
      return a((ServerItem)paramObject);
    return super.equals(paramObject);
  }

  public int hashCode()
  {
    if (this.serverIpStr != null);
    for (int i = this.serverIpStr.hashCode(); ; i = 0)
      return 0x7FFFFFFF & 31 * (31 * (i + 31) + this.serverPort) + this.spType;
  }

  public String toString()
  {
    return this.serverIpStr + ":" + this.serverPort;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.data.ServerItem
 * JD-Core Version:    0.6.0
 */