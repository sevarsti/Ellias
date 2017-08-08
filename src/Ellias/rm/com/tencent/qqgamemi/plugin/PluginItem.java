package com.tencent.qqgamemi.plugin;

import CobraHallQmiProto.TPkgDownInfo;
import CobraHallQmiProto.TQmiUnitBaseInfo;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.tencent.component.db.annotation.Column;
import com.tencent.component.db.annotation.Id;
import com.tencent.component.db.annotation.Table;
import com.tencent.component.plugin.PluginInfo;
import com.tencent.qqgamemi.common.TLog;

@Table(name="PluginItemTable", version=10)
public class PluginItem
{
  private static final String MATCH_ALL = ".*";
  public static final int PLUGIN_STATUS_DISABLE = 8;
  public static final int PLUGIN_STATUS_DOWNLOADING = 2;
  public static final int PLUGIN_STATUS_DOWNLOAD_CANCEL = 4;
  public static final int PLUGIN_STATUS_DOWNLOAD_DONE = 5;
  public static final int PLUGIN_STATUS_DOWNLOAD_FAILED = 3;
  public static final int PLUGIN_STATUS_ENABLE = 7;
  public static final int PLUGIN_STATUS_INSTALLING = 6;
  public static final int PLUGIN_STATUS_ONLINE = 1;
  public static final int PLUGIN_STATUS_UNKNOW = 0;
  private static final String TAG = "PluginItem";
  public static final int UPGRADE_MODEL_AUTO = 1;
  public static final int UPGRADE_MODEL_MANUAL;

  @Column
  public String blackList = "";

  @Column
  public boolean hasOnlineInfo = false;

  @Column
  public String iconUrl = null;

  @Id(strategy=1)
  public String id = null;
  public boolean isHide = false;

  @Column
  public boolean isNew = false;
  public boolean isSurviveable = true;

  @Column
  public int lastVersion = 0;

  @Column
  public String name = null;

  @Column
  public boolean needUpdate = false;

  @Column
  public int pkgSize = 0;

  @Column
  public String pkgUrl = null;
  private PluginInfo pluginInfo = null;

  @Column
  public int status = 0;

  @Column
  public int upgradeModel = 0;

  @Column
  public int version = 0;

  @Column
  public String whiteList = "";

  private boolean checkUpdate()
  {
    return (this.lastVersion != 0) && (this.version != 0) && (this.lastVersion > this.version);
  }

  protected Object clone()
  {
    return super.clone();
  }

  public Drawable getIcon(Context paramContext)
  {
    PluginInfo localPluginInfo = this.pluginInfo;
    Drawable localDrawable = null;
    if (localPluginInfo != null)
      localDrawable = this.pluginInfo.a(QMiPluginManager.a().f());
    return localDrawable;
  }

  public String getName()
  {
    return this.name;
  }

  public PluginInfo getPluginInfo()
  {
    return this.pluginInfo;
  }

  public int getStatus()
  {
    return this.status;
  }

  public boolean isAutoUpgrade()
  {
    return this.upgradeModel == 1;
  }

  public boolean isLocal()
  {
    return (this.status == 7) || (this.status == 8);
  }

  public boolean isMatch(String paramString)
  {
    if ((TextUtils.isEmpty(this.whiteList)) && (TextUtils.isEmpty(this.blackList)));
    do
    {
      return true;
      if (!TextUtils.isEmpty(this.whiteList))
      {
        if ((!".*".equals(this.whiteList)) && (!this.whiteList.contains(paramString)))
          return false;
      }
      else
        return false;
    }
    while ((TextUtils.isEmpty(this.blackList)) || ((!".*".equals(this.blackList)) && (!this.blackList.contains(paramString))));
    return false;
  }

  public void launchPlugin(Context paramContext)
  {
    TLog.c("PluginItem", "launchPlugin:" + this.id);
    QMiPluginManager.a().b(this.id);
  }

  public void setIsNew(PluginItem paramPluginItem)
  {
    if (paramPluginItem == null)
      this.isNew = true;
    while (true)
    {
      if (this.isNew)
        TLog.c("PluginItem", "set new plugin:" + this);
      return;
      if (paramPluginItem.lastVersion < this.lastVersion)
      {
        this.isNew = true;
        continue;
      }
      this.isNew = paramPluginItem.isNew;
    }
  }

  public void setIsNew(boolean paramBoolean)
  {
    this.isNew = paramBoolean;
  }

  public void setPluginInfo(PluginInfo paramPluginInfo)
  {
    if (paramPluginInfo != null)
    {
      this.pluginInfo = paramPluginInfo;
      this.status = 8;
      this.id = paramPluginInfo.pluginId;
      this.name = paramPluginInfo.e;
      this.version = paramPluginInfo.version;
      this.needUpdate = checkUpdate();
      this.isSurviveable = paramPluginInfo.m;
    }
  }

  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }

  public void setTUnitBaseInfo(TQmiUnitBaseInfo paramTQmiUnitBaseInfo)
  {
    if (paramTQmiUnitBaseInfo != null)
    {
      this.id = paramTQmiUnitBaseInfo.runPkgName;
      this.name = paramTQmiUnitBaseInfo.pluginName;
      this.status = 1;
      this.hasOnlineInfo = true;
      this.iconUrl = paramTQmiUnitBaseInfo.iconUrl;
      this.pkgUrl = paramTQmiUnitBaseInfo.downInfo.downUrl;
      this.pkgSize = paramTQmiUnitBaseInfo.downInfo.pkgSize;
    }
    try
    {
      this.lastVersion = Integer.parseInt(paramTQmiUnitBaseInfo.upgradeVer);
      this.whiteList = paramTQmiUnitBaseInfo.matchGameList;
      if (this.whiteList != null)
        this.whiteList = this.whiteList.trim();
      this.blackList = paramTQmiUnitBaseInfo.blackGameList;
      if (this.blackList != null)
        this.blackList = this.blackList.trim();
      this.upgradeModel = paramTQmiUnitBaseInfo.updateModel;
      return;
    }
    catch (Exception localException)
    {
      while (true)
        this.lastVersion = -1;
    }
  }

  public String toString()
  {
    return "[" + this.name + "," + this.id + "," + this.status + ", isNew:" + this.isNew + ", status:" + this.status + ", whiteList:" + this.whiteList + ", blackList:" + this.blackList + ", isHide:" + this.isHide + ", upgradeModel:" + this.upgradeModel + ", version:" + this.version + ", lastVersion" + this.lastVersion + "]";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.PluginItem
 * JD-Core Version:    0.6.0
 */