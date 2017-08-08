package com.tencent.tmassistantsdk.protocol.jce;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class ReqHead extends JceStruct
  implements Cloneable
{
  static Terminal a;
  static Net b;
  public int assistantAPILevel = 0;
  public int assistantVersionCode = 0;
  public int cmdId = 0;
  public byte encryptWithPack = 0;
  public Net net = null;
  public String phoneGuid = "";
  public String qua = "";
  public int requestId = 0;
  public Terminal terminal = null;

  static
  {
    if (!ReqHead.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      c = bool;
      return;
    }
  }

  public ReqHead()
  {
  }

  public ReqHead(int paramInt1, int paramInt2, String paramString1, String paramString2, byte paramByte, Terminal paramTerminal, int paramInt3, int paramInt4, Net paramNet)
  {
    this.requestId = paramInt1;
    this.cmdId = paramInt2;
    this.phoneGuid = paramString1;
    this.qua = paramString2;
    this.encryptWithPack = paramByte;
    this.terminal = paramTerminal;
    this.assistantAPILevel = paramInt3;
    this.assistantVersionCode = paramInt4;
    this.net = paramNet;
  }

  public String className()
  {
    return "jce.ReqHead";
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
        bool = c;
        Object localObject1 = null;
      }
      while (bool);
    }
    throw new AssertionError();
  }

  public void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.requestId, "requestId");
    localJceDisplayer.display(this.cmdId, "cmdId");
    localJceDisplayer.display(this.phoneGuid, "phoneGuid");
    localJceDisplayer.display(this.qua, "qua");
    localJceDisplayer.display(this.encryptWithPack, "encryptWithPack");
    localJceDisplayer.display(this.terminal, "terminal");
    localJceDisplayer.display(this.assistantAPILevel, "assistantAPILevel");
    localJceDisplayer.display(this.assistantVersionCode, "assistantVersionCode");
    localJceDisplayer.display(this.net, "net");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.requestId, true);
    localJceDisplayer.displaySimple(this.cmdId, true);
    localJceDisplayer.displaySimple(this.phoneGuid, true);
    localJceDisplayer.displaySimple(this.qua, true);
    localJceDisplayer.displaySimple(this.encryptWithPack, true);
    localJceDisplayer.displaySimple(this.terminal, true);
    localJceDisplayer.displaySimple(this.assistantAPILevel, true);
    localJceDisplayer.displaySimple(this.assistantVersionCode, true);
    localJceDisplayer.displaySimple(this.net, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    ReqHead localReqHead;
    do
    {
      return false;
      localReqHead = (ReqHead)paramObject;
    }
    while ((!JceUtil.equals(this.requestId, localReqHead.requestId)) || (!JceUtil.equals(this.cmdId, localReqHead.cmdId)) || (!JceUtil.equals(this.phoneGuid, localReqHead.phoneGuid)) || (!JceUtil.equals(this.qua, localReqHead.qua)) || (!JceUtil.equals(this.encryptWithPack, localReqHead.encryptWithPack)) || (!JceUtil.equals(this.terminal, localReqHead.terminal)) || (!JceUtil.equals(this.assistantAPILevel, localReqHead.assistantAPILevel)) || (!JceUtil.equals(this.assistantVersionCode, localReqHead.assistantVersionCode)) || (!JceUtil.equals(this.net, localReqHead.net)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.tmassistantsdk.protocol.jce.ReqHead";
  }

  public int getAssistantAPILevel()
  {
    return this.assistantAPILevel;
  }

  public int getAssistantVersionCode()
  {
    return this.assistantVersionCode;
  }

  public int getCmdId()
  {
    return this.cmdId;
  }

  public byte getEncryptWithPack()
  {
    return this.encryptWithPack;
  }

  public Net getNet()
  {
    return this.net;
  }

  public String getPhoneGuid()
  {
    return this.phoneGuid;
  }

  public String getQua()
  {
    return this.qua;
  }

  public int getRequestId()
  {
    return this.requestId;
  }

  public Terminal getTerminal()
  {
    return this.terminal;
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
    this.requestId = paramJceInputStream.read(this.requestId, 0, true);
    this.cmdId = paramJceInputStream.read(this.cmdId, 1, true);
    this.phoneGuid = paramJceInputStream.readString(2, true);
    this.qua = paramJceInputStream.readString(3, true);
    this.encryptWithPack = paramJceInputStream.read(this.encryptWithPack, 4, false);
    if (a == null)
      a = new Terminal();
    this.terminal = ((Terminal)paramJceInputStream.read(a, 5, false));
    this.assistantAPILevel = paramJceInputStream.read(this.assistantAPILevel, 6, false);
    this.assistantVersionCode = paramJceInputStream.read(this.assistantVersionCode, 7, false);
    if (b == null)
      b = new Net();
    this.net = ((Net)paramJceInputStream.read(b, 8, false));
  }

  public void setAssistantAPILevel(int paramInt)
  {
    this.assistantAPILevel = paramInt;
  }

  public void setAssistantVersionCode(int paramInt)
  {
    this.assistantVersionCode = paramInt;
  }

  public void setCmdId(int paramInt)
  {
    this.cmdId = paramInt;
  }

  public void setEncryptWithPack(byte paramByte)
  {
    this.encryptWithPack = paramByte;
  }

  public void setNet(Net paramNet)
  {
    this.net = paramNet;
  }

  public void setPhoneGuid(String paramString)
  {
    this.phoneGuid = paramString;
  }

  public void setQua(String paramString)
  {
    this.qua = paramString;
  }

  public void setRequestId(int paramInt)
  {
    this.requestId = paramInt;
  }

  public void setTerminal(Terminal paramTerminal)
  {
    this.terminal = paramTerminal;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.requestId, 0);
    paramJceOutputStream.write(this.cmdId, 1);
    paramJceOutputStream.write(this.phoneGuid, 2);
    paramJceOutputStream.write(this.qua, 3);
    paramJceOutputStream.write(this.encryptWithPack, 4);
    if (this.terminal != null)
      paramJceOutputStream.write(this.terminal, 5);
    paramJceOutputStream.write(this.assistantAPILevel, 6);
    paramJceOutputStream.write(this.assistantVersionCode, 7);
    if (this.net != null)
      paramJceOutputStream.write(this.net, 8);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.protocol.jce.ReqHead
 * JD-Core Version:    0.6.0
 */