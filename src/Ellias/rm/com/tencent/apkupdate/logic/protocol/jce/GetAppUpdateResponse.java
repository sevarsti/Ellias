package com.tencent.apkupdate.logic.protocol.jce;

import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class GetAppUpdateResponse extends JceStruct
{
  static ArrayList a;
  static Map b;
  public Map appUpdateInfoGroup = null;
  public ArrayList appUpdateInfoList = null;
  public int ret = 0;

  public final void readFrom(JceInputStream paramJceInputStream)
  {
    this.ret = paramJceInputStream.read(this.ret, 0, true);
    if (a == null)
    {
      a = new ArrayList();
      AppUpdateInfo localAppUpdateInfo = new AppUpdateInfo();
      a.add(localAppUpdateInfo);
    }
    this.appUpdateInfoList = ((ArrayList)paramJceInputStream.read(a, 1, true));
    if (b == null)
    {
      b = new HashMap();
      Integer localInteger = Integer.valueOf(0);
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(new AppUpdateInfo());
      b.put(localInteger, localArrayList);
    }
    this.appUpdateInfoGroup = ((Map)paramJceInputStream.read(b, 2, false));
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.ret, 0);
    paramJceOutputStream.write(this.appUpdateInfoList, 1);
    if (this.appUpdateInfoGroup != null)
      paramJceOutputStream.write(this.appUpdateInfoGroup, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.logic.protocol.jce.GetAppUpdateResponse
 * JD-Core Version:    0.6.0
 */