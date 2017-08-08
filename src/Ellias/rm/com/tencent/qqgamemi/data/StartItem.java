package com.tencent.qqgamemi.data;

import CobraHallQmiProto.TBodyQmiStartRsp;
import CobraHallQmiProto.TPkgDownInfo;
import java.io.Serializable;

public class StartItem
  implements Serializable
{
  private static final String TAG = "StartItem";
  public int pkgSize;
  public String pkgUrl;
  public String pkgVersion;
  public int timeStamp;
  public String uuid;

  public StartItem(TBodyQmiStartRsp paramTBodyQmiStartRsp)
  {
    this.uuid = paramTBodyQmiStartRsp.getUuid();
    this.timeStamp = paramTBodyQmiStartRsp.getSvrTimestamp();
    this.pkgVersion = paramTBodyQmiStartRsp.getUpgradeVer();
    this.pkgUrl = paramTBodyQmiStartRsp.downInfo.getDownUrl();
    this.pkgSize = paramTBodyQmiStartRsp.downInfo.getPkgSize();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.data.StartItem
 * JD-Core Version:    0.6.0
 */