package com.tencent.apkupdate.logic.protocol.jce;

import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;

public final class Response extends JceStruct
{
  static RspHead a;
  static byte[] b;
  public byte[] body = null;
  public RspHead head = null;

  public final void readFrom(JceInputStream paramJceInputStream)
  {
    if (a == null)
      a = new RspHead();
    this.head = ((RspHead)paramJceInputStream.read(a, 0, true));
    if (b == null)
    {
      byte[] arrayOfByte = (byte[])new byte[1];
      b = arrayOfByte;
      ((byte[])arrayOfByte)[0] = 0;
    }
    this.body = ((byte[])paramJceInputStream.read(b, 1, true));
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.head, 0);
    paramJceOutputStream.write(this.body, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.logic.protocol.jce.Response
 * JD-Core Version:    0.6.0
 */