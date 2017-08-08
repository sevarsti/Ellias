package com.tencent.android.tpush.data;

import java.io.Serializable;

public class MessageId
  implements Serializable
{
  public static final short FLAG_ACK = 1;
  public static final short FLAG_UNACK = 0;
  private static final long serialVersionUID = 8708157897391765794L;
  public long accessId;
  public byte apn;
  public long busiMsgId = 0L;
  public String date = "";
  public long host;
  public long id;
  public short isAck;
  public byte isp;
  public long msgType = -1L;
  public long multiPkg = 0L;
  public byte pact;
  public String pkgName;
  public int port;
  public long pushTime;
  public long receivedTime;
  public String serviceHost;
  public long timestamp = 0L;

  public boolean a()
  {
    return this.isAck == 1;
  }

  public String toString()
  {
    return "MessageId [id=" + this.id + ", isAck=" + this.isAck + ", isp=" + this.isp + ", apn=" + this.apn + ", accessId=" + this.accessId + ", pushTime=" + this.pushTime + ", receivedTime=" + this.receivedTime + ", pact=" + this.pact + ", host=" + this.host + ", port=" + this.port + ", serviceHost=" + this.serviceHost + ", pkgName=" + this.pkgName + ", busiMsgId=" + this.busiMsgId + ", timestamp=" + this.timestamp + ", msgType=" + this.msgType + ", multiPkg=" + this.multiPkg + ", date=" + this.date + "]";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.data.MessageId
 * JD-Core Version:    0.6.0
 */