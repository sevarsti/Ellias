package com.tencent.tmassistantsdk.protocol.jce;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class Response extends JceStruct
  implements Cloneable
{
  static RspHead a;
  static byte[] b;
  public byte[] body = null;
  public RspHead head = null;

  static
  {
    if (!Response.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      c = bool;
      return;
    }
  }

  public Response()
  {
  }

  public Response(RspHead paramRspHead, byte[] paramArrayOfByte)
  {
    this.head = paramRspHead;
    this.body = paramArrayOfByte;
  }

  public String className()
  {
    return "jce.Response";
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
    localJceDisplayer.display(this.head, "head");
    localJceDisplayer.display(this.body, "body");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.head, true);
    localJceDisplayer.displaySimple(this.body, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    Response localResponse;
    do
    {
      return false;
      localResponse = (Response)paramObject;
    }
    while ((!JceUtil.equals(this.head, localResponse.head)) || (!JceUtil.equals(this.body, localResponse.body)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.tmassistantsdk.protocol.jce.Response";
  }

  public byte[] getBody()
  {
    return this.body;
  }

  public RspHead getHead()
  {
    return this.head;
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
    if (a == null)
      a = new RspHead();
    this.head = ((RspHead)paramJceInputStream.read(a, 0, true));
    if (b == null)
    {
      b = (byte[])new byte[1];
      ((byte[])b)[0] = 0;
    }
    this.body = ((byte[])paramJceInputStream.read(b, 1, true));
  }

  public void setBody(byte[] paramArrayOfByte)
  {
    this.body = paramArrayOfByte;
  }

  public void setHead(RspHead paramRspHead)
  {
    this.head = paramRspHead;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.head, 0);
    paramJceOutputStream.write(this.body, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.protocol.jce.Response
 * JD-Core Version:    0.6.0
 */