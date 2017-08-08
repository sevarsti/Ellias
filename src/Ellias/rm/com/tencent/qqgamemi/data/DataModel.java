package com.tencent.qqgamemi.data;

import CobraHallQmiProto.TBodyGetUserInfoV2Resp;
import CobraHallQmiProto.TBodyQmiStartRsp;
import CobraHallQmiProto.TGameExtendInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.os.Handler;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.protocol.MsgHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataModel
{
  private static DataModel C;
  private static final String a = DataModel.class.getSimpleName();
  private static final Long b = Long.valueOf(86400000L);
  private static final Long c = b;
  private static final Long d = b;
  private static final Long e = b;
  private static final Long f = b;
  private static final int g = 86400;
  private static final int h = 600;
  private static final int i = 604800;
  private static int j = 0;
  private static final GameItem u = GameItem.makeOneGameItem("com.tencent.gamejoy", "手游宝", "http://www.tencent.com", "http://www.tencent.com", null, 3);
  private static final int v = 11;
  private static final int w = 1000;
  private static final int x = 1001;
  private static final int y = 1003;
  private static final int z = 2000;
  private Handler A = new a(this);
  private Context B;
  private PackageManager k = null;
  private GameItemProvider l = null;
  private List m = null;
  private List n = new ArrayList();
  private List o = new ArrayList();
  private String p;
  private StartItemProvider q = null;
  private List r = new ArrayList();
  private UserInfoProvider s = null;
  private List t = new ArrayList();

  static
  {
    C = null;
  }

  private DataModel(Context paramContext)
  {
    this.B = paramContext;
    this.k = paramContext.getPackageManager();
    this.q = new StartItemProvider();
    this.l = new GameItemProvider(paramContext);
    this.m = new ArrayList();
    this.s = new UserInfoProvider(paramContext);
    g();
  }

  public static DataModel a(Context paramContext)
  {
    if (C == null)
      C = new DataModel(paramContext);
    return C;
  }

  // ERROR //
  private GameItem a(TGameExtendInfo paramTGameExtendInfo)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 106	com/tencent/qqgamemi/data/DataModel:k	Landroid/content/pm/PackageManager;
    //   4: aload_1
    //   5: getfield 162	CobraHallQmiProto/TGameExtendInfo:gamePkgName	Ljava/lang/String;
    //   8: iconst_0
    //   9: invokevirtual 168	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   12: astore 10
    //   14: aload_0
    //   15: getfield 106	com/tencent/qqgamemi/data/DataModel:k	Landroid/content/pm/PackageManager;
    //   18: aload 10
    //   20: invokevirtual 172	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   23: invokevirtual 175	java/lang/Object:toString	()Ljava/lang/String;
    //   26: astore 11
    //   28: aload 11
    //   30: astore_3
    //   31: getstatic 64	com/tencent/qqgamemi/data/DataModel:a	Ljava/lang/String;
    //   34: new 177	java/lang/StringBuilder
    //   37: dup
    //   38: invokespecial 178	java/lang/StringBuilder:<init>	()V
    //   41: ldc 180
    //   43: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: aload_3
    //   47: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 185	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: invokestatic 190	com/tencent/qqgamemi/common/TLog:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   56: aload_1
    //   57: getfield 162	CobraHallQmiProto/TGameExtendInfo:gamePkgName	Ljava/lang/String;
    //   60: astore 4
    //   62: aload_1
    //   63: getfield 193	CobraHallQmiProto/TGameExtendInfo:walkthroughUrl	Ljava/lang/String;
    //   66: astore 5
    //   68: aload_1
    //   69: getfield 196	CobraHallQmiProto/TGameExtendInfo:bbsUrl	Ljava/lang/String;
    //   72: astore 6
    //   74: aload_1
    //   75: getfield 199	CobraHallQmiProto/TGameExtendInfo:xPos	I
    //   78: istore 7
    //   80: aconst_null
    //   81: astore 8
    //   83: iload 7
    //   85: iflt +34 -> 119
    //   88: aload_1
    //   89: getfield 202	CobraHallQmiProto/TGameExtendInfo:yPos	I
    //   92: istore 9
    //   94: aconst_null
    //   95: astore 8
    //   97: iload 9
    //   99: iflt +20 -> 119
    //   102: new 204	com/tencent/qqgamemi/data/Point
    //   105: dup
    //   106: aload_1
    //   107: getfield 199	CobraHallQmiProto/TGameExtendInfo:xPos	I
    //   110: aload_1
    //   111: getfield 202	CobraHallQmiProto/TGameExtendInfo:yPos	I
    //   114: invokespecial 207	com/tencent/qqgamemi/data/Point:<init>	(II)V
    //   117: astore 8
    //   119: aload 4
    //   121: aload_3
    //   122: aload 5
    //   124: aload 6
    //   126: aload 8
    //   128: aload_1
    //   129: getfield 210	CobraHallQmiProto/TGameExtendInfo:pluginShowType	I
    //   132: invokestatic 96	com/tencent/qqgamemi/data/GameItem:makeOneGameItem	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/tencent/qqgamemi/data/Point;I)Lcom/tencent/qqgamemi/data/GameItem;
    //   135: areturn
    //   136: astore_2
    //   137: aconst_null
    //   138: astore_3
    //   139: aload_2
    //   140: invokevirtual 213	java/lang/Exception:printStackTrace	()V
    //   143: goto -87 -> 56
    //   146: astore_2
    //   147: goto -8 -> 139
    //
    // Exception table:
    //   from	to	target	type
    //   0	28	136	java/lang/Exception
    //   31	56	146	java/lang/Exception
  }

  private void a(int paramInt)
  {
    if ((paramInt != j) && (paramInt >= 600) && (paramInt <= 604800))
    {
      j = paramInt;
      b(paramInt);
      TLog.c(a, "set NoGameTimeOut " + j);
    }
  }

  private void a(TBodyGetUserInfoV2Resp paramTBodyGetUserInfoV2Resp)
  {
    UserInfo localUserInfo1;
    if (paramTBodyGetUserInfoV2Resp != null)
    {
      localUserInfo1 = new UserInfo(paramTBodyGetUserInfoV2Resp);
      this.s.a(localUserInfo1);
    }
    for (UserInfo localUserInfo2 = localUserInfo1; ; localUserInfo2 = null)
    {
      Iterator localIterator = this.t.iterator();
      while (localIterator.hasNext())
        ((UserInfoCallBack)localIterator.next()).a(localUserInfo2);
      this.t.clear();
      return;
    }
  }

  private void a(TBodyQmiStartRsp paramTBodyQmiStartRsp)
  {
    StartItem localStartItem1;
    if (paramTBodyQmiStartRsp != null)
    {
      localStartItem1 = new StartItem(paramTBodyQmiStartRsp);
      this.q.a(localStartItem1);
    }
    for (StartItem localStartItem2 = localStartItem1; ; localStartItem2 = null)
    {
      Iterator localIterator = this.r.iterator();
      while (localIterator.hasNext())
        ((StartItemCallBack)localIterator.next()).a(localStartItem2);
      this.r.clear();
      return;
    }
  }

  private void b(int paramInt)
  {
    SharedPreferences.Editor localEditor = this.B.getSharedPreferences("NoGameTimeOut", 2).edit();
    localEditor.putInt("TimeOut", paramInt);
    localEditor.commit();
  }

  private void b(GameItem paramGameItem)
  {
    if ((paramGameItem != null) && (c(paramGameItem)))
    {
      TLog.b(a, "timeStamp is invalidate:" + paramGameItem);
      d(paramGameItem.packageName);
    }
  }

  private GameItem c(String paramString)
  {
    Iterator localIterator = this.m.iterator();
    while (localIterator.hasNext())
    {
      GameItem localGameItem = (GameItem)localIterator.next();
      if (!localGameItem.packageName.equals(paramString))
        continue;
      b(localGameItem);
      return localGameItem;
    }
    return null;
  }

  private boolean c(GameItem paramGameItem)
  {
    if (!paramGameItem.packageName.equals(this.p))
    {
      this.p = paramGameItem.packageName;
      if (paramGameItem.isGame())
      {
        TLog.b(a, paramGameItem.packageName + " is timeout");
        return true;
      }
    }
    Long localLong1 = Long.valueOf(System.currentTimeMillis());
    Long localLong2 = Long.valueOf(0L);
    if (paramGameItem.type == 0)
      localLong2 = Long.valueOf(1000L * j);
    while (localLong1.longValue() - paramGameItem.updateTimeStamp >= localLong2.longValue())
    {
      return true;
      if (paramGameItem.type == 2)
      {
        localLong2 = e;
        continue;
      }
      if (paramGameItem.type == 1)
      {
        localLong2 = d;
        continue;
      }
      if (paramGameItem.type != 3)
        continue;
      localLong2 = f;
    }
    return false;
  }

  private void d(GameItem paramGameItem)
  {
    if (paramGameItem != null)
      this.m.add(paramGameItem);
  }

  private void d(String paramString)
  {
    f(paramString);
  }

  private GameItem e(String paramString)
  {
    GameItem localGameItem = this.l.a(paramString);
    d(localGameItem);
    return localGameItem;
  }

  private void e(GameItem paramGameItem)
  {
    this.l.a(paramGameItem);
    e();
  }

  private void f(GameItem paramGameItem)
  {
    Iterator localIterator = this.m.iterator();
    while (localIterator.hasNext())
    {
      GameItem localGameItem = (GameItem)localIterator.next();
      if (!localGameItem.packageName.equals(paramGameItem.packageName))
        continue;
      this.m.remove(localGameItem);
    }
  }

  private void f(String paramString)
  {
    if (this.n.size() != 0)
      return;
    TLog.b(a, "MsgHandle.sendGameInfoReq:" + paramString);
    this.n.add(paramString);
    MsgHandle.a(this.A, 1001, this.n);
  }

  // ERROR //
  private GameItem g(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 106	com/tencent/qqgamemi/data/DataModel:k	Landroid/content/pm/PackageManager;
    //   4: aload_1
    //   5: iconst_0
    //   6: invokevirtual 168	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   9: astore 5
    //   11: aload_0
    //   12: getfield 106	com/tencent/qqgamemi/data/DataModel:k	Landroid/content/pm/PackageManager;
    //   15: aload 5
    //   17: invokevirtual 172	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   20: invokevirtual 175	java/lang/Object:toString	()Ljava/lang/String;
    //   23: astore 6
    //   25: aload 6
    //   27: astore_3
    //   28: getstatic 64	com/tencent/qqgamemi/data/DataModel:a	Ljava/lang/String;
    //   31: new 177	java/lang/StringBuilder
    //   34: dup
    //   35: invokespecial 178	java/lang/StringBuilder:<init>	()V
    //   38: ldc_w 374
    //   41: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: aload_3
    //   45: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: invokevirtual 185	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   51: invokestatic 190	com/tencent/qqgamemi/common/TLog:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   54: aload_1
    //   55: aload_3
    //   56: invokestatic 378	com/tencent/qqgamemi/data/GameItem:makeNotGameItem	(Ljava/lang/String;Ljava/lang/String;)Lcom/tencent/qqgamemi/data/GameItem;
    //   59: areturn
    //   60: astore_2
    //   61: aconst_null
    //   62: astore_3
    //   63: aload_2
    //   64: astore 4
    //   66: aload 4
    //   68: invokevirtual 213	java/lang/Exception:printStackTrace	()V
    //   71: goto -17 -> 54
    //   74: astore 4
    //   76: goto -10 -> 66
    //
    // Exception table:
    //   from	to	target	type
    //   0	25	60	java/lang/Exception
    //   28	54	74	java/lang/Exception
  }

  private void g()
  {
    j = this.B.getSharedPreferences("NoGameTimeOut", 0).getInt("TimeOut", 86400);
  }

  private void h()
  {
  }

  public GameItem a(String paramString)
  {
    GameItem localGameItem = c(paramString);
    if (localGameItem != null);
    do
    {
      return localGameItem;
      localGameItem = e(paramString);
    }
    while (localGameItem != null);
    f(paramString);
    return null;
  }

  public void a()
  {
    this.m.clear();
  }

  public void a(long paramLong, UserInfoCallBack paramUserInfoCallBack)
  {
    TLog.c("SYBACCOUNT", "requestUserInfo, uin = " + paramLong + ", callback=" + paramUserInfoCallBack);
    if ((paramUserInfoCallBack != null) && (paramLong != 0L))
    {
      UserInfo localUserInfo = this.s.a(paramLong);
      if (localUserInfo == null)
        break label90;
      paramUserInfoCallBack.a(localUserInfo);
    }
    while (true)
    {
      TLog.c("SYBACCOUNT", "requestUserInfo sendUserInfoReq!");
      MsgHandle.a(this.A, 1003);
      return;
      label90: this.t.add(paramUserInfoCallBack);
    }
  }

  public void a(GameItem paramGameItem)
  {
    this.l.a(paramGameItem);
  }

  public void a(StartItemCallBack paramStartItemCallBack)
  {
    StartItem localStartItem = this.q.a();
    int i1 = 0;
    if (localStartItem != null)
      i1 = localStartItem.timeStamp;
    this.r.add(paramStartItemCallBack);
    MsgHandle.a(this.A, 1000, i1);
  }

  public void a(String paramString, Point paramPoint)
  {
    this.l.a(paramString, paramPoint);
    GameItem localGameItem = c(paramString);
    if (localGameItem != null)
      localGameItem.movePoint = paramPoint;
  }

  public void a(ArrayList paramArrayList)
  {
    if (paramArrayList == null)
    {
      this.n.clear();
      return;
    }
    Iterator localIterator1 = paramArrayList.iterator();
    while (localIterator1.hasNext())
    {
      TGameExtendInfo localTGameExtendInfo = (TGameExtendInfo)localIterator1.next();
      TLog.b(a, "get a GameInfo from network:" + localTGameExtendInfo.gamePkgName);
      if (!this.n.contains(localTGameExtendInfo.gamePkgName))
        continue;
      GameItem localGameItem2 = a(localTGameExtendInfo);
      TLog.b(a, "get a new GameInfo:" + localGameItem2);
      GameItem localGameItem3 = this.l.a(localGameItem2.packageName);
      if ((localGameItem3 != null) && (localGameItem3.type != 0))
        localGameItem2.bSupport = localGameItem3.bSupport;
      e(localGameItem2);
      this.n.remove(localTGameExtendInfo.gamePkgName);
    }
    if (this.n.size() < 20)
    {
      Iterator localIterator3 = this.n.iterator();
      while (localIterator3.hasNext())
      {
        GameItem localGameItem1 = g((String)localIterator3.next());
        TLog.b(a, "get empty GameInfo from network:" + localGameItem1);
        e(localGameItem1);
      }
    }
    this.n.clear();
    Iterator localIterator2 = this.o.iterator();
    while (localIterator2.hasNext())
      ((GameItemCallBack)localIterator2.next()).a(c());
    this.o.clear();
  }

  public void a(ArrayList paramArrayList, GameItemCallBack paramGameItemCallBack)
  {
    if (this.n.size() != 0);
    do
    {
      return;
      TLog.b(a, "request " + paramArrayList.size() + " gameItem");
      this.n.addAll(paramArrayList);
      MsgHandle.a(this.A, 1001, this.n);
    }
    while (paramGameItemCallBack == null);
    this.o.add(paramGameItemCallBack);
  }

  public void b()
  {
    if (this.q.a() == null)
      h();
  }

  public void b(StartItemCallBack paramStartItemCallBack)
  {
    StartItem localStartItem = this.q.a();
    if (localStartItem != null)
    {
      paramStartItemCallBack.a(localStartItem);
      return;
    }
    a(paramStartItemCallBack);
  }

  public void b(String paramString)
  {
    Iterator localIterator = this.m.iterator();
    while (localIterator.hasNext())
    {
      GameItem localGameItem = (GameItem)localIterator.next();
      if (!localGameItem.packageName.equals(paramString))
        continue;
      localGameItem.bSupport = false;
      e(localGameItem);
    }
  }

  public List c()
  {
    return this.l.c();
  }

  public void d()
  {
    TLog.b(a, "onGetError");
    this.n.clear();
  }

  public void e()
  {
    this.m.clear();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.data.DataModel
 * JD-Core Version:    0.6.0
 */