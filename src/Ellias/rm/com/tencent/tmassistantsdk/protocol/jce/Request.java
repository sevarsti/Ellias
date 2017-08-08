package com.tencent.tmassistantsdk.protocol.jce;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class Request extends JceStruct
  implements Cloneable
{
  static ReqHead a;
  static byte[] b;
  public byte[] body = null;
  public ReqHead head = null;

  static
  {
    if (!Request.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      c = bool;
      return;
    }
  }

  public Request()
  {
  }

  public Request(ReqHead paramReqHead, byte[] paramArrayOfByte)
  {
    this.head = paramReqHead;
    this.body = paramArrayOfByte;
  }

  public String className()
  {
    return "jce.Request";
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
    Request localRequest;
    do
    {
      return false;
      localRequest = (Request)paramObject;
    }
    while ((!JceUtil.equals(this.head, localRequest.head)) || (!JceUtil.equals(this.body, localRequest.body)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.tmassistantsdk.protocol.jce.Request";
  }

  public byte[] getBody()
  {
    return this.body;
  }

  public ReqHead getHead()
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
      a = new ReqHead();
    this.head = ((ReqHead)paramJceInputStream.read(a, 0, true));
    if (b == null)
    {
      b = (byte[])new byte[1];
      ((byte[])b)[0] = 0;
    }
    this.body = ((byte[])paramJceInputStream.read(b, 1, false));
  }

  public void setBody(byte[] paramArrayOfByte)
  {
    this.body = paramArrayOfByte;
  }

  public void setHead(ReqHead paramReqHead)
  {
    this.head = paramReqHead;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.head, 0);
    if (this.body != null)
      paramJceOutputStream.write(this.body, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.protocol.jce.Request
 * JD-Core Version:    0.6.0
 */