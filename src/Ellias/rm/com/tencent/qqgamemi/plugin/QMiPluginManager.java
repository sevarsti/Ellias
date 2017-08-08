package com.tencent.qqgamemi.plugin;

import CobraHallQmiProto.TQmiUnitBaseInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.cache.sp.PreferenceUtil;
import com.tencent.component.net.download.multiplex.FileDownload;
import com.tencent.component.net.download.multiplex.download.DownloadTask;
import com.tencent.component.plugin.InstallPluginListener;
import com.tencent.component.plugin.Plugin;
import com.tencent.component.plugin.PluginManager;
import com.tencent.component.plugin.PluginManager.GetPluginListCallback;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.NetworkUtil;
import com.tencent.component.utils.SecurityUtil;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.ThreadPool;
import com.tencent.qqgamemi.QMiServiceLogic;
import com.tencent.qqgamemi.business.GameActionReportHelper;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.data.GameItem;
import com.tencent.qqgamemi.plugin.api.QMiApi;
import com.tencent.qqgamemi.plugin.ui.IFloatViewManager;
import com.tencent.qqgamemi.protocol.MsgHandle;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class QMiPluginManager
{
  public static final long a = 1000L;
  public static final long b = 60000L;
  public static final long c = 3600000L;
  private static final String d = QMiPluginManager.class.getSimpleName();
  private static final String e = "com.tencent.qqgamemi.forumplugin";
  private static final String f = "com.tencent.qqgamemi.raidersplugin";
  private static final Long g = Long.valueOf(86400000L);
  private static final Long h = g;
  private static final int i = 20;
  private static final Long j = Long.valueOf(2000L);
  private static final int k = 11;
  private static final int l = 1002;
  private static QMiPluginManager u = null;
  private static final String w = "last_plugin_download_";
  private static final long x = 7200000L;
  private Context m = null;
  private PluginManager n = null;
  private PluginItemProvider o = null;
  private boolean p = false;
  private boolean q = false;
  private List r = Collections.synchronizedList(new ArrayList());
  private List s = new ArrayList();
  private List t = new ArrayList();
  private PluginManager.GetPluginListCallback v = new e(this);
  private Handler y = new g(this);

  private QMiPluginManager(Context paramContext)
  {
    this.m = paramContext;
    this.n = PluginManager.a(paramContext, "qmi");
    this.n.a(new a(this));
    this.o = new PluginItemProvider(paramContext);
    GameActionReportHelper.a().a(new b(this));
  }

  private PluginItem a(List paramList, String paramString)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      PluginItem localPluginItem = (PluginItem)localIterator.next();
      if (localPluginItem.id.equals(paramString))
        return localPluginItem;
    }
    return null;
  }

  public static QMiPluginManager a()
  {
    return u;
  }

  public static void a(Context paramContext)
  {
    TLog.c(d, "init");
    if (u == null)
      u = new QMiPluginManager(paramContext);
  }

  private void a(PluginItem paramPluginItem)
  {
    DownloadTask localDownloadTask;
    if (paramPluginItem != null)
    {
      if (!NetworkUtil.b(this.m))
        break label437;
      if (l(paramPluginItem.id))
      {
        localDownloadTask = FileDownload.a(paramPluginItem.pkgUrl);
        if (localDownloadTask == null)
          break label228;
        switch (localDownloadTask.aD)
        {
        default:
          LogUtil.i(d, "plugin is downloading (" + paramPluginItem.id + ",v:" + paramPluginItem.version + ",lastV:" + paramPluginItem.lastVersion + "),current status is " + localDownloadTask.aD + " and url is " + paramPluginItem.pkgUrl);
        case 4:
        case 5:
        case 6:
        }
      }
    }
    label228: String str1;
    do
    {
      return;
      LogUtil.i(d, "resume download plugin(" + paramPluginItem.id + ",v:" + paramPluginItem.version + ",lastV:" + paramPluginItem.lastVersion + "),current status is " + localDownloadTask.aD + " and url is " + paramPluginItem.pkgUrl);
      FileDownload.a(localDownloadTask);
      return;
      LogUtil.i(d, "start download plugin(" + paramPluginItem.id + ",v:" + paramPluginItem.version + ",lastV:" + paramPluginItem.lastVersion + ") ,url is " + paramPluginItem.pkgUrl);
      str1 = CacheManager.a(this.m, "qmi-plugins", false);
    }
    while (TextUtils.isEmpty(str1));
    String str2 = SecurityUtil.a(paramPluginItem.pkgUrl);
    File localFile;
    try
    {
      localFile = new File(str1, str2);
      if (!localFile.exists())
      {
        FileDownload.a(paramPluginItem.pkgUrl, str1, SecurityUtil.a(paramPluginItem.pkgUrl), new h(paramPluginItem, this.m));
        return;
      }
    }
    catch (Exception localException)
    {
      TLog.c(d, localException.getMessage(), localException);
      return;
    }
    TLog.e(d, "plugin(" + paramPluginItem.id + ") file exist ,ingnore download request .(path:" + localFile.getAbsolutePath() + ")");
    return;
    label437: LogUtil.i(d, "wifi not connected ,ingnore plugin(" + paramPluginItem.id + ") auto download request.");
  }

  private void a(String paramString, long paramLong)
  {
    SharedPreferences.Editor localEditor = this.m.getSharedPreferences("pluginUpdateTimeStamp", 2).edit();
    localEditor.putLong(paramString, paramLong);
    localEditor.commit();
  }

  private void a(String paramString, List paramList)
  {
    TLog.c(d, "showPluginItem:" + paramString);
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      PluginItem localPluginItem = (PluginItem)localIterator.next();
      TLog.c(d, "" + localPluginItem);
    }
  }

  private boolean a(long paramLong)
  {
    return System.currentTimeMillis() - paramLong > h.longValue();
  }

  private static void b(String paramString, Context paramContext)
  {
    if ((!TextUtils.isEmpty(paramString)) && (paramContext != null))
    {
      SharedPreferences localSharedPreferences = PreferenceUtil.b(paramContext);
      if (localSharedPreferences != null)
      {
        long l1 = System.currentTimeMillis();
        localSharedPreferences.edit().putLong("last_plugin_download_" + paramString, l1).commit();
        LogUtil.i(d, "update plugin:" + paramString + " download time --> " + l1);
      }
    }
  }

  private void b(String paramString, List paramList)
  {
    TLog.b(d, "requestPluginItemsFromNetwork");
    MsgHandle.a(this.y, 1002, 1, 20, paramString, paramList);
  }

  private void b(List paramList, String paramString)
  {
    this.o.a(paramList, paramString);
    this.o.a(new PluginOnline(paramString, true));
    a(paramString, System.currentTimeMillis());
  }

  private void f(String paramString)
  {
    if (h(paramString))
    {
      LogUtil.d(d, "need update plugin online:" + paramString);
      g(paramString);
      return;
    }
    LogUtil.d(d, "not need to update plugin online:" + paramString);
  }

  private void g(String paramString)
  {
    this.n.a(new c(this, paramString));
  }

  private boolean h(String paramString)
  {
    if (DebugUtil.a());
    do
      return true;
    while (a(i(paramString)));
    return false;
  }

  private long i(String paramString)
  {
    return this.m.getSharedPreferences("pluginUpdateTimeStamp", 0).getLong(paramString, 0L);
  }

  private void j(String paramString)
  {
    if (o())
    {
      TLog.c(d, "need RequestPluginsFromNetwork sdk");
      this.n.a(new d(this, paramString));
      return;
    }
    TLog.c(d, "not need RequestPluginsFromNetwork sdk");
  }

  private PluginItem k(String paramString)
  {
    Iterator localIterator = this.r.iterator();
    while (localIterator.hasNext())
    {
      PluginItem localPluginItem = (PluginItem)localIterator.next();
      if (localPluginItem.id.equals(paramString))
        return localPluginItem;
    }
    return null;
  }

  private boolean l(String paramString)
  {
    Context localContext = this.m;
    boolean bool1 = TextUtils.isEmpty(paramString);
    boolean bool2 = false;
    if (!bool1)
    {
      bool2 = false;
      if (localContext != null)
      {
        SharedPreferences localSharedPreferences = PreferenceUtil.b(localContext);
        bool2 = false;
        if (localSharedPreferences != null)
        {
          long l1 = localSharedPreferences.getLong("last_plugin_download_" + paramString, 0L);
          long l2 = System.currentTimeMillis() - l1;
          boolean bool3 = l2 < 7200000L;
          bool2 = false;
          if (bool3)
            bool2 = true;
          LogUtil.i(d, "need download plugin result [pluginId:" + paramString + " |lastDownloadTime:" + l1 + " |interval:" + l2 + " | needDownload:" + bool2 + "]");
        }
      }
    }
    return bool2;
  }

  private List m(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return new ArrayList();
    List localList = this.o.b(paramString);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      PluginItem localPluginItem = (PluginItem)localIterator.next();
      TLog.c(d, "get a plugin from DB:" + localPluginItem);
    }
    return localList;
  }

  private boolean o()
  {
    return !this.q;
  }

  private List p()
  {
    return PluginOrderManager.a().b();
  }

  private void q()
  {
    ThreadPool.getInstance().submit(new f(this));
  }

  public void a(String paramString)
  {
    Message localMessage = new Message();
    localMessage.what = 11;
    localMessage.obj = paramString;
    this.y.sendMessage(localMessage);
  }

  public void a(String paramString, InstallPluginListener paramInstallPluginListener)
  {
    this.n.a(paramString, paramInstallPluginListener);
  }

  public void a(ArrayList paramArrayList, String paramString)
  {
    TLog.c(d, "onGetPluginsFromNetwork:" + paramString);
    if ((paramArrayList == null) || (TextUtils.isEmpty(paramString)))
    {
      Log.e(d, "onGetPluginsFromNetwork error");
      return;
    }
    TLog.c(d, "get " + paramArrayList.size() + " plugin, " + paramString);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      TQmiUnitBaseInfo localTQmiUnitBaseInfo = (TQmiUnitBaseInfo)localIterator.next();
      PluginItem localPluginItem1 = new PluginItem();
      localPluginItem1.setTUnitBaseInfo(localTQmiUnitBaseInfo);
      PluginItem localPluginItem2 = this.o.a(localPluginItem1.id, paramString);
      if (localPluginItem2 == null)
        localPluginItem2 = k(localPluginItem1.id);
      localPluginItem1.setIsNew(localPluginItem2);
      localArrayList.add(localPluginItem1);
    }
    b(localArrayList, paramString);
    g();
  }

  public void a(List paramList)
  {
    PluginOrderManager.a().a(paramList);
  }

  public void b(String paramString)
  {
    PluginItem localPluginItem = k(paramString);
    if ((localPluginItem != null) && (localPluginItem.status == 7))
    {
      this.n.i(paramString);
      return;
    }
    TLog.e(d, "plugin:" + paramString + " is not exist or disable");
  }

  public boolean b()
  {
    Iterator localIterator = this.n.b().iterator();
    while (localIterator.hasNext())
    {
      Plugin localPlugin = (Plugin)localIterator.next();
      if ((localPlugin == null) || (!(localPlugin instanceof QmiPlugin)))
        continue;
      IFloatViewManager localIFloatViewManager = ((QmiPlugin)localPlugin).getPluginFloatViewManager();
      if ((localIFloatViewManager != null) && (localIFloatViewManager.getVisibleFloatViewSize() != 0))
        return true;
    }
    return false;
  }

  public List c()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.n.b().iterator();
    while (localIterator.hasNext())
    {
      Plugin localPlugin = (Plugin)localIterator.next();
      if ((localPlugin == null) || (!(localPlugin instanceof QmiPlugin)))
        continue;
      IFloatViewManager localIFloatViewManager = ((QmiPlugin)localPlugin).getPluginFloatViewManager();
      if ((localIFloatViewManager == null) || (localIFloatViewManager.getVisibleFloatViewSize() == 0))
        continue;
      localArrayList.add(localPlugin);
    }
    return localArrayList;
  }

  public void c(String paramString)
  {
    this.n.e(paramString);
  }

  public void d()
  {
    LogUtil.d(d, "stopAllPlugin");
    this.n.c();
  }

  public void d(String paramString)
  {
    this.n.a(1, paramString);
  }

  public void e()
  {
    this.n.d();
  }

  public void e(String paramString)
  {
    this.n.h(paramString);
  }

  public PluginManager f()
  {
    return this.n;
  }

  public void g()
  {
    TLog.c(d, "updatePlugins");
    if (this.p)
      this.n.a(this.v);
  }

  public ArrayList h()
  {
    ArrayList localArrayList = new ArrayList(this.r);
    Iterator localIterator = localArrayList.iterator();
    label96: label100: 
    while (true)
    {
      PluginItem localPluginItem;
      if (localIterator.hasNext())
      {
        localPluginItem = (PluginItem)localIterator.next();
        localPluginItem.isHide = false;
        if ((QMiServiceLogic.b == null) || (TextUtils.isEmpty(QMiServiceLogic.b.packageName)))
          break label96;
      }
      for (boolean bool = this.o.a(QMiServiceLogic.b.packageName); ; bool = true)
      {
        if ((!bool) || (localPluginItem.hasOnlineInfo))
          break label100;
        localPluginItem.isHide = true;
        break;
        return localArrayList;
      }
    }
  }

  public List i()
  {
    ArrayList localArrayList = a().h();
    if (TextUtils.isEmpty(QMiApi.getInstance(this.m).getForumUrl()))
    {
      Iterator localIterator4 = localArrayList.iterator();
      while (localIterator4.hasNext())
      {
        PluginItem localPluginItem4 = (PluginItem)localIterator4.next();
        if (!localPluginItem4.id.equals("com.tencent.qqgamemi.forumplugin"))
          continue;
        localPluginItem4.isHide = true;
      }
    }
    if (TextUtils.isEmpty(QMiApi.getInstance(this.m).getRaidersUrl()))
    {
      Iterator localIterator3 = localArrayList.iterator();
      while (localIterator3.hasNext())
      {
        PluginItem localPluginItem3 = (PluginItem)localIterator3.next();
        if (!localPluginItem3.id.equals("com.tencent.qqgamemi.raidersplugin"))
          continue;
        localPluginItem3.isHide = true;
      }
    }
    String str = QMiCommon.a(this.m);
    Iterator localIterator1 = localArrayList.iterator();
    while (localIterator1.hasNext())
    {
      PluginItem localPluginItem2 = (PluginItem)localIterator1.next();
      if (localPluginItem2.isMatch(str))
        continue;
      TLog.c(d, "current game is " + str + ", it is not match with " + localPluginItem2);
      localPluginItem2.isHide = true;
    }
    Iterator localIterator2 = localArrayList.iterator();
    while (localIterator2.hasNext())
    {
      PluginItem localPluginItem1 = (PluginItem)localIterator2.next();
      if ((localPluginItem1 == null) || (localPluginItem1.isSurviveable))
        continue;
      TLog.c(d, "plugin( " + localPluginItem1 + ") is not surviveable.");
      localPluginItem1.isHide = true;
    }
    return localArrayList;
  }

  public boolean j()
  {
    Iterator localIterator = this.r.iterator();
    while (localIterator.hasNext())
      if (((PluginItem)localIterator.next()).isNew)
        return true;
    return false;
  }

  public List k()
  {
    return new ArrayList(this.s);
  }

  public void l()
  {
    if ((QMiServiceLogic.b != null) && (!TextUtils.isEmpty(QMiServiceLogic.b.packageName)))
    {
      String str = QMiServiceLogic.b.packageName;
      List localList = this.o.b(str);
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        PluginItem localPluginItem = (PluginItem)localIterator.next();
        if (!localPluginItem.isNew)
          continue;
        localPluginItem.isNew = false;
        localArrayList.add(localPluginItem);
      }
      TLog.c(d, "clearNewPlugin:" + localArrayList);
      this.o.a(localArrayList, QMiServiceLogic.b.packageName);
      return;
    }
    LogUtil.d(d, "clear new plugin error");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.QMiPluginManager
 * JD-Core Version:    0.6.0
 */