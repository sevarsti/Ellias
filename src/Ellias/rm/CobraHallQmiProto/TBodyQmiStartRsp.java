package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TBodyQmiStartRsp extends JceStruct
  implements Cloneable
{
  static TPkgDownInfo cache_downInfo;
  public TPkgDownInfo downInfo = null;
  public int svrTimestamp = 0;
  public String upgradeVer = "";
  public String uuid = "";

  static
  {
    if (!TBodyQmiStartRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyQmiStartRsp()
  {
  }

  public TBodyQmiStartRsp(TPkgDownInfo paramTPkgDownInfo, String paramString1, int paramInt, String paramString2)
  {
    this.downInfo = paramTPkgDownInfo;
    this.uuid = paramString1;
    this.svrTimestamp = paramInt;
    this.upgradeVer = paramString2;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyQmiStartRsp";
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
        bool = $assertionsDisabled;
        Object localObject1 = null;
      }
      while (bool);
    }
    throw new AssertionError();
  }

  public void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.downInfo, "downInfo");
    localJceDisplayer.display(this.uuid, "uuid");
    localJceDisplayer.display(this.svrTimestamp, "svrTimestamp");
    localJceDisplayer.display(this.upgradeVer, "upgradeVer");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.downInfo, true);
    localJceDisplayer.displaySimple(this.uuid, true);
    localJceDisplayer.displaySimple(this.svrTimestamp, true);
    localJceDisplayer.displaySimple(this.upgradeVer, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TBodyQmiStartRsp localTBodyQmiStartRsp;
    do
    {
      return false;
      localTBodyQmiStartRsp = (TBodyQmiStartRsp)paramObject;
    }
    while ((!JceUtil.equals(this.downInfo, localTBodyQmiStartRsp.downInfo)) || (!JceUtil.equals(this.uuid, localTBodyQmiStartRsp.uuid)) || (!JceUtil.equals(this.svrTimestamp, localTBodyQmiStartRsp.svrTimestamp)) || (!JceUtil.equals(this.upgradeVer, localTBodyQmiStartRsp.upgradeVer)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyQmiStartRsp";
  }

  public TPkgDownInfo getDownInfo()
  {
    return this.downInfo;
  }

  public int getSvrTimestamp()
  {
    return this.svrTimestamp;
  }

  public String getUpgradeVer()
  {
    return this.upgradeVer;
  }

  public String getUuid()
  {
    return this.uuid;
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
    if (cache_downInfo == null)
      cache_downInfo = new TPkgDownInfo();
    this.downInfo = ((TPkgDownInfo)paramJceInputStream.read(cache_downInfo, 0, true));
    this.uuid = paramJceInputStream.readString(1, true);
    this.svrTimestamp = paramJceInputStream.read(this.svrTimestamp, 2, false);
    this.upgradeVer = paramJceInputStream.readString(3, true);
  }

  public void setDownInfo(TPkgDownInfo paramTPkgDownInfo)
  {
    this.downInfo = paramTPkgDownInfo;
  }

  public void setSvrTimestamp(int paramInt)
  {
    this.svrTimestamp = paramInt;
  }

  public void setUpgradeVer(String paramString)
  {
    this.upgradeVer = paramString;
  }

  public void setUuid(String paramString)
  {
    this.uuid = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.downInfo, 0);
    paramJceOutputStream.write(this.uuid, 1);
    paramJceOutputStream.write(this.svrTimestamp, 2);
    paramJceOutputStream.write(this.upgradeVer, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyQmiStartRsp
 * JD-Core Version:    0.6.0
 */