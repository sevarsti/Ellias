package com.tencent.apkupdate.logic.protocol.jce;

import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import java.util.HashMap;
import java.util.Map;

public final class ApkFileInfo extends JceStruct
{
  static Map a;
  public long apkId = 0L;
  public Map fileCRC32 = null;
  public String manifestMd5 = "";
  public String packageName = "";

  public final void readFrom(JceInputStream paramJceInputStream)
  {
    this.apkId = paramJceInputStream.read(this.apkId, 0, true);
    this.manifestMd5 = paramJceInputStream.readString(1, true);
    this.packageName = paramJceInputStream.readString(2, true);
    if (a == null)
    {
      a = new HashMap();
      Long localLong = Long.valueOf(0L);
      a.put("", localLong);
    }
    this.fileCRC32 = ((Map)paramJceInputStream.read(a, 3, true));
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.apkId, 0);
    paramJceOutputStream.write(this.manifestMd5, 1);
    paramJceOutputStream.write(this.packageName, 2);
    paramJceOutputStream.write(this.fileCRC32, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.logic.protocol.jce.ApkFileInfo
 * JD-Core Version:    0.6.0
 */