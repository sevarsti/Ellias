package com.tencent.apkupdate.logic.protocol.jce;

import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import java.util.ArrayList;

public final class GetAppUpdateRequest extends JceStruct
{
  static ArrayList a;
  public ArrayList appInfoForUpdateList = null;
  public byte flag = 0;

  public final void readFrom(JceInputStream paramJceInputStream)
  {
    if (a == null)
    {
      a = new ArrayList();
      AppInfoForUpdate localAppInfoForUpdate = new AppInfoForUpdate();
      a.add(localAppInfoForUpdate);
    }
    this.appInfoForUpdateList = ((ArrayList)paramJceInputStream.read(a, 0, true));
    this.flag = paramJceInputStream.read(this.flag, 1, false);
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.appInfoForUpdateList, 0);
    paramJceOutputStream.write(this.flag, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.logic.protocol.jce.GetAppUpdateRequest
 * JD-Core Version:    0.6.0
 */