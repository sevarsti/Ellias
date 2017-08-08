package com.tencent.apkupdate.logic.protocol.jce;

import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;

public final class ReportApkFileInfoResponse extends JceStruct
{
  public int ret = 0;

  public final void readFrom(JceInputStream paramJceInputStream)
  {
    this.ret = paramJceInputStream.read(this.ret, 0, true);
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.ret, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.logic.protocol.jce.ReportApkFileInfoResponse
 * JD-Core Version:    0.6.0
 */