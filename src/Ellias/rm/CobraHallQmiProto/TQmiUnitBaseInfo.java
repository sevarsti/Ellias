package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TQmiUnitBaseInfo extends JceStruct
  implements Cloneable
{
  static TPkgDownInfo cache_downInfo;
  public int appType = 0;
  public String blackGameList = "";
  public TPkgDownInfo downInfo = null;
  public String iconUrl = "";
  public String matchGameList = "";
  public int pkgType = 0;
  public long pluginId = 0L;
  public String pluginName = "";
  public String runPkgName = "";
  public int showModel = 0;
  public int updateModel = 0;
  public int upgradeType = 0;
  public String upgradeVer = "";

  static
  {
    if (!TQmiUnitBaseInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TQmiUnitBaseInfo()
  {
  }

  public TQmiUnitBaseInfo(long paramLong, String paramString1, int paramInt1, String paramString2, int paramInt2, TPkgDownInfo paramTPkgDownInfo, String paramString3, String paramString4, int paramInt3, String paramString5, String paramString6, int paramInt4, int paramInt5)
  {
    this.pluginId = paramLong;
    this.pluginName = paramString1;
    this.upgradeType = paramInt1;
    this.upgradeVer = paramString2;
    this.pkgType = paramInt2;
    this.downInfo = paramTPkgDownInfo;
    this.iconUrl = paramString3;
    this.runPkgName = paramString4;
    this.appType = paramInt3;
    this.matchGameList = paramString5;
    this.blackGameList = paramString6;
    this.showModel = paramInt4;
    this.updateModel = paramInt5;
  }

  public String className()
  {
    return "CobraHallQmiProto.TQmiUnitBaseInfo";
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
    localJceDisplayer.display(this.pluginId, "pluginId");
    localJceDisplayer.display(this.pluginName, "pluginName");
    localJceDisplayer.display(this.upgradeType, "upgradeType");
    localJceDisplayer.display(this.upgradeVer, "upgradeVer");
    localJceDisplayer.display(this.pkgType, "pkgType");
    localJceDisplayer.display(this.downInfo, "downInfo");
    localJceDisplayer.display(this.iconUrl, "iconUrl");
    localJceDisplayer.display(this.runPkgName, "runPkgName");
    localJceDisplayer.display(this.appType, "appType");
    localJceDisplayer.display(this.matchGameList, "matchGameList");
    localJceDisplayer.display(this.blackGameList, "blackGameList");
    localJceDisplayer.display(this.showModel, "showModel");
    localJceDisplayer.display(this.updateModel, "updateModel");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.pluginId, true);
    localJceDisplayer.displaySimple(this.pluginName, true);
    localJceDisplayer.displaySimple(this.upgradeType, true);
    localJceDisplayer.displaySimple(this.upgradeVer, true);
    localJceDisplayer.displaySimple(this.pkgType, true);
    localJceDisplayer.displaySimple(this.downInfo, true);
    localJceDisplayer.displaySimple(this.iconUrl, true);
    localJceDisplayer.displaySimple(this.runPkgName, true);
    localJceDisplayer.displaySimple(this.appType, true);
    localJceDisplayer.displaySimple(this.matchGameList, true);
    localJceDisplayer.displaySimple(this.blackGameList, true);
    localJceDisplayer.displaySimple(this.showModel, true);
    localJceDisplayer.displaySimple(this.updateModel, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TQmiUnitBaseInfo localTQmiUnitBaseInfo;
    do
    {
      return false;
      localTQmiUnitBaseInfo = (TQmiUnitBaseInfo)paramObject;
    }
    while ((!JceUtil.equals(this.pluginId, localTQmiUnitBaseInfo.pluginId)) || (!JceUtil.equals(this.pluginName, localTQmiUnitBaseInfo.pluginName)) || (!JceUtil.equals(this.upgradeType, localTQmiUnitBaseInfo.upgradeType)) || (!JceUtil.equals(this.upgradeVer, localTQmiUnitBaseInfo.upgradeVer)) || (!JceUtil.equals(this.pkgType, localTQmiUnitBaseInfo.pkgType)) || (!JceUtil.equals(this.downInfo, localTQmiUnitBaseInfo.downInfo)) || (!JceUtil.equals(this.iconUrl, localTQmiUnitBaseInfo.iconUrl)) || (!JceUtil.equals(this.runPkgName, localTQmiUnitBaseInfo.runPkgName)) || (!JceUtil.equals(this.appType, localTQmiUnitBaseInfo.appType)) || (!JceUtil.equals(this.matchGameList, localTQmiUnitBaseInfo.matchGameList)) || (!JceUtil.equals(this.blackGameList, localTQmiUnitBaseInfo.blackGameList)) || (!JceUtil.equals(this.showModel, localTQmiUnitBaseInfo.showModel)) || (!JceUtil.equals(this.updateModel, localTQmiUnitBaseInfo.updateModel)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TQmiUnitBaseInfo";
  }

  public int getAppType()
  {
    return this.appType;
  }

  public String getBlackGameList()
  {
    return this.blackGameList;
  }

  public TPkgDownInfo getDownInfo()
  {
    return this.downInfo;
  }

  public String getIconUrl()
  {
    return this.iconUrl;
  }

  public String getMatchGameList()
  {
    return this.matchGameList;
  }

  public int getPkgType()
  {
    return this.pkgType;
  }

  public long getPluginId()
  {
    return this.pluginId;
  }

  public String getPluginName()
  {
    return this.pluginName;
  }

  public String getRunPkgName()
  {
    return this.runPkgName;
  }

  public int getShowModel()
  {
    return this.showModel;
  }

  public int getUpdateModel()
  {
    return this.updateModel;
  }

  public int getUpgradeType()
  {
    return this.upgradeType;
  }

  public String getUpgradeVer()
  {
    return this.upgradeVer;
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
    this.pluginId = paramJceInputStream.read(this.pluginId, 0, true);
    this.pluginName = paramJceInputStream.readString(1, true);
    this.upgradeType = paramJceInputStream.read(this.upgradeType, 2, true);
    this.upgradeVer = paramJceInputStream.readString(3, true);
    this.pkgType = paramJceInputStream.read(this.pkgType, 4, true);
    if (cache_downInfo == null)
      cache_downInfo = new TPkgDownInfo();
    this.downInfo = ((TPkgDownInfo)paramJceInputStream.read(cache_downInfo, 5, true));
    this.iconUrl = paramJceInputStream.readString(6, true);
    this.runPkgName = paramJceInputStream.readString(7, true);
    this.appType = paramJceInputStream.read(this.appType, 8, false);
    this.matchGameList = paramJceInputStream.readString(9, false);
    this.blackGameList = paramJceInputStream.readString(10, false);
    this.showModel = paramJceInputStream.read(this.showModel, 11, false);
    this.updateModel = paramJceInputStream.read(this.updateModel, 12, false);
  }

  public void setAppType(int paramInt)
  {
    this.appType = paramInt;
  }

  public void setBlackGameList(String paramString)
  {
    this.blackGameList = paramString;
  }

  public void setDownInfo(TPkgDownInfo paramTPkgDownInfo)
  {
    this.downInfo = paramTPkgDownInfo;
  }

  public void setIconUrl(String paramString)
  {
    this.iconUrl = paramString;
  }

  public void setMatchGameList(String paramString)
  {
    this.matchGameList = paramString;
  }

  public void setPkgType(int paramInt)
  {
    this.pkgType = paramInt;
  }

  public void setPluginId(long paramLong)
  {
    this.pluginId = paramLong;
  }

  public void setPluginName(String paramString)
  {
    this.pluginName = paramString;
  }

  public void setRunPkgName(String paramString)
  {
    this.runPkgName = paramString;
  }

  public void setShowModel(int paramInt)
  {
    this.showModel = paramInt;
  }

  public void setUpdateModel(int paramInt)
  {
    this.updateModel = paramInt;
  }

  public void setUpgradeType(int paramInt)
  {
    this.upgradeType = paramInt;
  }

  public void setUpgradeVer(String paramString)
  {
    this.upgradeVer = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.pluginId, 0);
    paramJceOutputStream.write(this.pluginName, 1);
    paramJceOutputStream.write(this.upgradeType, 2);
    paramJceOutputStream.write(this.upgradeVer, 3);
    paramJceOutputStream.write(this.pkgType, 4);
    paramJceOutputStream.write(this.downInfo, 5);
    paramJceOutputStream.write(this.iconUrl, 6);
    paramJceOutputStream.write(this.runPkgName, 7);
    paramJceOutputStream.write(this.appType, 8);
    if (this.matchGameList != null)
      paramJceOutputStream.write(this.matchGameList, 9);
    if (this.blackGameList != null)
      paramJceOutputStream.write(this.blackGameList, 10);
    paramJceOutputStream.write(this.showModel, 11);
    paramJceOutputStream.write(this.updateModel, 12);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TQmiUnitBaseInfo
 * JD-Core Version:    0.6.0
 */