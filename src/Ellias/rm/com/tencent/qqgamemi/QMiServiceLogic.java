package com.tencent.qqgamemi;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.component.event.Event.EventRank;
import com.tencent.component.event.EventCenter;
import com.tencent.component.event.EventSource;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.business.AutoDownLoadGameJoy;
import com.tencent.qqgamemi.business.GameActionReportHelper;
import com.tencent.qqgamemi.business.MIUINotifyHelper;
import com.tencent.qqgamemi.business.NotificationHelper;
import com.tencent.qqgamemi.business.PluginUndealCountManager;
import com.tencent.qqgamemi.business.QMiEnvironmentHelper;
import com.tencent.qqgamemi.business.ReceiverRegisterHelper;
import com.tencent.qqgamemi.business.ShowPriorityHelper;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.data.DataModel;
import com.tencent.qqgamemi.data.GameItem;
import com.tencent.qqgamemi.login.QMiLoginManager;
import com.tencent.qqgamemi.plugin.QMiPluginManager;
import com.tencent.qqgamemi.protocol.QMiJceCommonData;
import com.tencent.qqgamemi.report.UserAccessStatics;

public class QMiServiceLogic
{
  public static final String a = "QMiService";
  public static GameItem b;
  private static final int c = 200;
  private static final int d = 1000;
  private static final int e = 230;
  private static final int f = 240;
  private static final int g = 210;
  private static final int h = 270;
  private static final int i = 271;
  private static final int j = 280;
  private static final int k = 290;
  private static final int l = 300;
  private static final int m = 301;
  private static final int n = 310;
  private static final int o = 311;
  private static final int p = 330;
  private static final int q = 340;
  private static final GameItem s = GameItem.makeNotGameItem("", "");
  private ShowPriorityHelper A;
  private AutoDownLoadGameJoy B;
  private Handler C = new c(this);
  private Context r;
  private GameItem t = s;
  private DataModel u = null;
  private QMiViewManager v = null;
  private NotificationHelper w;
  private GameActionReportHelper x;
  private MIUINotifyHelper y;
  private ReceiverRegisterHelper z;

  static
  {
    b = s;
  }

  public QMiServiceLogic(Context paramContext)
  {
    this.r = paramContext;
  }

  private void a(GameItem paramGameItem)
  {
    if (this.v.a(paramGameItem))
      if (b != null)
      {
        if (QMiConfig.b())
        {
          String str = this.r.getPackageName();
          QMiPluginManager.a().a(str);
        }
        EventCenter.getInstance().notify(new EventSource("QmiUI"), 2, Event.EventRank.NORMAL, new Object[0]);
        QMiPluginManager.a().d(b.packageName);
        UserAccessStatics.getInstance(this.r).addQMiAction(201, System.currentTimeMillis(), b.packageName, null);
        UserAccessStatics.getInstance(this.r).a();
      }
    while (true)
    {
      this.w.a();
      return;
      if ((b == null) || (this.t == null) || (this.t.packageName == null) || (this.t.packageName.equals(b.packageName)) || (!this.x.c()))
        continue;
      EventCenter.getInstance().notify(new EventSource("QmiUI"), 2, Event.EventRank.NORMAL, new Object[0]);
      QMiPluginManager.a().d(b.packageName);
    }
  }

  private void b(GameItem paramGameItem)
  {
    if ((b != null) && (!b.equals(paramGameItem)))
    {
      if ((paramGameItem.bSupport) && (!paramGameItem.equals(this.t)))
        QMiPluginManager.a().d();
      this.t = b;
      b = paramGameItem;
    }
  }

  private void f()
  {
    try
    {
      Class.forName(AsyncTask.class.getName());
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
  }

  private void g()
  {
    TLog.c("QMiService", "init");
    this.u = DataModel.a(this.r);
    this.v = new QMiViewManager(this.r);
    this.w = new NotificationHelper((Service)this.r);
    this.x = GameActionReportHelper.a();
    this.y = new MIUINotifyHelper(this.r);
    this.z = new ReceiverRegisterHelper(this.r);
    this.z.a();
    this.z.c();
    this.A = new ShowPriorityHelper(this.r);
    this.A.a();
    this.B = new AutoDownLoadGameJoy(this.r);
    PluginUndealCountManager.a().b();
  }

  private void h()
  {
    TLog.c("QMiService", "setUUID");
    this.u.b(new b(this));
  }

  private void i()
  {
    this.v.a();
    this.w.b();
  }

  private boolean j()
  {
    String str = QMiCommon.a(this.r);
    if (QMiConfig.b())
    {
      if ((!TextUtils.isEmpty(str)) && (!str.equals(this.r.getPackageName())))
      {
        b(s);
        this.x.a(s);
        return false;
      }
      if (DebugUtil.a())
        LogUtil.d("QMiService", "isSelfGame");
    }
    for (int i1 = 1; ; i1 = 0)
    {
      GameItem localGameItem = this.u.a(str);
      this.x.a(localGameItem);
      if (localGameItem != null)
      {
        if (localGameItem.equals(b))
        {
          if ((!localGameItem.bSupport) && (i1 == 0))
            break;
          return localGameItem.isShow();
        }
        if ((localGameItem.bSupport) || (i1 != 0))
        {
          b(localGameItem);
          return localGameItem.isShow();
        }
      }
      b(s);
      return false;
    }
  }

  private void k()
  {
    this.u.b(b.packageName);
  }

  public int a(Intent paramIntent, int paramInt1, int paramInt2)
  {
    a(paramIntent, paramInt2);
    if (QMiConfig.b())
      return 2;
    return 1;
  }

  public IBinder a(Intent paramIntent)
  {
    return null;
  }

  public void a()
  {
    TLog.c("QMiService", "onCreate");
    QMiJceCommonData.a(this.r);
    QMiPluginManager.a(this.r);
    g();
    h();
    QMiEnvironmentHelper.a(this.r);
    QMiLoginManager.a(this.r);
    if (QMiConfig.b())
    {
      QMiLoginManager.a();
      QMiLoginManager.b();
    }
    QMiCommon.showQMi(this.r);
    if (QMiConfig.b())
      this.B.a();
  }

  public void a(Intent paramIntent, int paramInt)
  {
    if (paramIntent == null);
    int i1;
    do
    {
      return;
      i1 = paramIntent.getIntExtra("operation", 0);
    }
    while ((this.A != null) && (this.A.d()) && (i1 != 211));
    switch (i1)
    {
    default:
      return;
    case 99:
      TLog.c("QMiService", "qmi operation init");
      a();
      return;
    case 100:
      TLog.c("QMiService", "running qmi");
      d();
      return;
    case 101:
      TLog.c("QMiService", "stop running qmi");
      e();
      return;
    case 102:
      TLog.c("QMiService", "qmi scroll hide");
      this.v.k();
      return;
    case 130:
      TLog.c("QMiService", "disable current game");
      this.C.sendEmptyMessage(230);
      return;
    case 140:
      TLog.c("QMiService", "update gameInfo");
      this.C.sendEmptyMessage(240);
      return;
    case 160:
      TLog.c("QMiService", "power on");
      this.C.sendEmptyMessage(270);
      return;
    case 161:
      TLog.c("QMiService", "power off");
      this.C.sendEmptyMessage(271);
      return;
    case 170:
      if (DebugUtil.a())
        TLog.c("QMiService", "alarm");
      this.C.removeMessages(280);
      this.C.sendEmptyMessage(280);
      return;
    case 180:
      TLog.c("QMiService", "configuration changed");
      this.C.sendEmptyMessage(210);
      return;
    case 190:
      TLog.c("QMiService", "check rom");
      this.C.sendEmptyMessage(290);
      return;
    case 200:
      TLog.c("QMiService", "login");
      Message localMessage3 = new Message();
      localMessage3.what = 300;
      localMessage3.obj = paramIntent;
      this.C.sendMessage(localMessage3);
      return;
    case 201:
      TLog.c("SYBACCOUNT", "logout");
      Message localMessage2 = new Message();
      localMessage2.what = 301;
      localMessage2.obj = paramIntent;
      this.C.sendMessage(localMessage2);
      return;
    case 210:
      TLog.c("QMiService", "hall install");
      this.C.sendEmptyMessage(310);
      return;
    case 211:
      TLog.c("QMiService", "hall uninstall");
      this.C.sendEmptyMessage(311);
      return;
    case 220:
      TLog.c("QMiService", "hall getqimei");
      Message localMessage1 = new Message();
      localMessage1.what = 330;
      localMessage1.obj = paramIntent;
      this.C.sendMessage(localMessage1);
      return;
    case 230:
    }
    TLog.c("QMiService", "msdk login suc");
    this.C.sendEmptyMessage(340);
  }

  public void b()
  {
    f();
    QMiConfig.a(this.r);
    QMiApplication.a(this.r);
  }

  public void c()
  {
    TLog.c("QMiService", "onDestroy");
    QMiWindowManager.g();
    this.w.b();
    this.z.b();
    this.u.a();
  }

  public void d()
  {
    this.C.removeMessages(200);
    this.C.sendEmptyMessage(200);
  }

  public void e()
  {
    this.C.removeMessages(200);
    i();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.QMiServiceLogic
 * JD-Core Version:    0.6.0
 */