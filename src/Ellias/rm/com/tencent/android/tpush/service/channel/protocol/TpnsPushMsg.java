package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsPushMsg extends JceStruct
  implements Cloneable
{
  public long accessId = 0L;
  public String appPkgName = "";
  public long busiMsgId = 0L;
  public String content = "";
  public String date = "";
  public long msgId = 0L;
  public long multiPkg = 0L;
  public long timestamp = 0L;
  public String title = "";
  public long type = 0L;

  static
  {
    if (!TpnsPushMsg.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsPushMsg()
  {
  }

  public TpnsPushMsg(long paramLong1, long paramLong2, long paramLong3, String paramString1, String paramString2, long paramLong4, String paramString3, long paramLong5, long paramLong6, String paramString4)
  {
    this.msgId = paramLong1;
    this.accessId = paramLong2;
    this.busiMsgId = paramLong3;
    this.title = paramString1;
    this.content = paramString2;
    this.type = paramLong4;
    this.appPkgName = paramString3;
    this.timestamp = paramLong5;
    this.multiPkg = paramLong6;
    this.date = paramString4;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsPushMsg";
  }

  public Object clone()
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

  public void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.msgId, "msgId");
    localJceDisplayer.display(this.accessId, "accessId");
    localJceDisplayer.display(this.busiMsgId, "busiMsgId");
    localJceDisplayer.display(this.title, "title");
    localJceDisplayer.display(this.content, "content");
    localJceDisplayer.display(this.type, "type");
    localJceDisplayer.display(this.appPkgName, "appPkgName");
    localJceDisplayer.display(this.timestamp, "timestamp");
    localJceDisplayer.display(this.multiPkg, "multiPkg");
    localJceDisplayer.display(this.date, "date");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.msgId, true);
    localJceDisplayer.displaySimple(this.accessId, true);
    localJceDisplayer.displaySimple(this.busiMsgId, true);
    localJceDisplayer.displaySimple(this.title, true);
    localJceDisplayer.displaySimple(this.content, true);
    localJceDisplayer.displaySimple(this.type, true);
    localJceDisplayer.displaySimple(this.appPkgName, true);
    localJceDisplayer.displaySimple(this.timestamp, true);
    localJceDisplayer.displaySimple(this.multiPkg, true);
    localJceDisplayer.displaySimple(this.date, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TpnsPushMsg localTpnsPushMsg;
    do
    {
      return false;
      localTpnsPushMsg = (TpnsPushMsg)paramObject;
    }
    while ((!JceUtil.equals(this.msgId, localTpnsPushMsg.msgId)) || (!JceUtil.equals(this.accessId, localTpnsPushMsg.accessId)) || (!JceUtil.equals(this.busiMsgId, localTpnsPushMsg.busiMsgId)) || (!JceUtil.equals(this.title, localTpnsPushMsg.title)) || (!JceUtil.equals(this.content, localTpnsPushMsg.content)) || (!JceUtil.equals(this.type, localTpnsPushMsg.type)) || (!JceUtil.equals(this.appPkgName, localTpnsPushMsg.appPkgName)) || (!JceUtil.equals(this.timestamp, localTpnsPushMsg.timestamp)) || (!JceUtil.equals(this.multiPkg, localTpnsPushMsg.multiPkg)) || (!JceUtil.equals(this.date, localTpnsPushMsg.date)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsPushMsg";
  }

  public long getAccessId()
  {
    return this.accessId;
  }

  public String getAppPkgName()
  {
    return this.appPkgName;
  }

  public long getBusiMsgId()
  {
    return this.busiMsgId;
  }

  public String getContent()
  {
    return this.content;
  }

  public String getDate()
  {
    return this.date;
  }

  public long getMsgId()
  {
    return this.msgId;
  }

  public long getMultiPkg()
  {
    return this.multiPkg;
  }

  public long getTimestamp()
  {
    return this.timestamp;
  }

  public String getTitle()
  {
    return this.title;
  }

  public long getType()
  {
    return this.type;
  }

  public int hashCode()
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

  public void readFrom(JceInputStream paramJceInputStream)
  {
    this.msgId = paramJceInputStream.read(this.msgId, 0, true);
    this.accessId = paramJceInputStream.read(this.accessId, 1, true);
    this.busiMsgId = paramJceInputStream.read(this.busiMsgId, 2, true);
    this.title = paramJceInputStream.readString(3, true);
    this.content = paramJceInputStream.readString(4, true);
    this.type = paramJceInputStream.read(this.type, 5, true);
    this.appPkgName = paramJceInputStream.readString(6, false);
    this.timestamp = paramJceInputStream.read(this.timestamp, 7, false);
    this.multiPkg = paramJceInputStream.read(this.multiPkg, 8, false);
    this.date = paramJceInputStream.readString(9, false);
  }

  public void setAccessId(long paramLong)
  {
    this.accessId = paramLong;
  }

  public void setAppPkgName(String paramString)
  {
    this.appPkgName = paramString;
  }

  public void setBusiMsgId(long paramLong)
  {
    this.busiMsgId = paramLong;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public void setDate(String paramString)
  {
    this.date = paramString;
  }

  public void setMsgId(long paramLong)
  {
    this.msgId = paramLong;
  }

  public void setMultiPkg(long paramLong)
  {
    this.multiPkg = paramLong;
  }

  public void setTimestamp(long paramLong)
  {
    this.timestamp = paramLong;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public void setType(long paramLong)
  {
    this.type = paramLong;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.msgId, 0);
    paramJceOutputStream.write(this.accessId, 1);
    paramJceOutputStream.write(this.busiMsgId, 2);
    paramJceOutputStream.write(this.title, 3);
    paramJceOutputStream.write(this.content, 4);
    paramJceOutputStream.write(this.type, 5);
    if (this.appPkgName != null)
      paramJceOutputStream.write(this.appPkgName, 6);
    paramJceOutputStream.write(this.timestamp, 7);
    paramJceOutputStream.write(this.multiPkg, 8);
    if (this.date != null)
      paramJceOutputStream.write(this.date, 9);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsPushMsg
 * JD-Core Version:    0.6.0
 */