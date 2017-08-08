package com.tencent.apkupdate.logic.protocol.jce;

import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import java.util.ArrayList;

public final class ReportApkFileInfoRequest extends JceStruct
{
  static ArrayList a;
  public ArrayList apkFileInfoList = null;

  public final void readFrom(JceInputStream paramJceInputStream)
  {
    if (a == null)
    {
      a = new ArrayList();
      ApkFileInfo localApkFileInfo = new ApkFileInfo();
      a.add(localApkFileInfo);
    }
    this.apkFileInfoList = ((ArrayList)paramJceInputStream.read(a, 0, true));
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.apkFileInfoList, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.logic.protocol.jce.ReportApkFileInfoRequest
 * JD-Core Version:    0.6.0
 */