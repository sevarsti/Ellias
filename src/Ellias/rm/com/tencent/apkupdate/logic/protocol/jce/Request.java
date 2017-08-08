package com.tencent.apkupdate.logic.protocol.jce;

import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;

public final class Request extends JceStruct
{
  static ReqHead a;
  static byte[] b;
  public byte[] body = null;
  public ReqHead head = null;

  public final void readFrom(JceInputStream paramJceInputStream)
  {
    if (a == null)
      a = new ReqHead();
    this.head = ((ReqHead)paramJceInputStream.read(a, 0, true));
    if (b == null)
    {
      byte[] arrayOfByte = (byte[])new byte[1];
      b = arrayOfByte;
      ((byte[])arrayOfByte)[0] = 0;
    }
    this.body = ((byte[])paramJceInputStream.read(b, 1, false));
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.head, 0);
    if (this.body != null)
      paramJceOutputStream.write(this.body, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.logic.protocol.jce.Request
 * JD-Core Version:    0.6.0
 */