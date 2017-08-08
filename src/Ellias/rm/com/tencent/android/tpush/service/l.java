package com.tencent.android.tpush.service;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.util.DisplayMetrics;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.data.UnregisterInfo;
import com.tencent.android.tpush.logging.LogUtil;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.protocol.AppInfo;
import com.tencent.android.tpush.service.channel.protocol.DeviceInfo;
import com.tencent.android.tpush.service.channel.protocol.MutableInfo;
import com.tencent.android.tpush.service.channel.protocol.NetworkInfo;
import com.tencent.android.tpush.service.channel.protocol.TpnsConfigReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsGetApListReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClickReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsTokenTagReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsTriggerReportReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsTriggerReportRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq;
import com.tencent.android.tpush.service.channel.protocol.UnregInfo;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.report.ReportItem;
import com.tencent.android.tpush.service.report.e;
import com.tencent.mid.util.Util;
import java.util.ArrayList;
import org.json.JSONArray;

public class l
{
  private static l a = new l();
  private static JSONArray b = new JSONArray();
  private static final String c = com.tencent.android.tpush.encrypt.a.a("com.tencent.tpush.last_wifi_ts");
  private com.tencent.android.tpush.service.channel.n d = new m(this);

  public static byte a(boolean paramBoolean)
  {
    if (paramBoolean)
      return 1;
    return 0;
  }

  public static DeviceInfo a(Context paramContext)
  {
    DeviceInfo localDeviceInfo = new DeviceInfo();
    localDeviceInfo.apiLevel = ("" + c.b());
    localDeviceInfo.imei = c.c(paramContext);
    localDeviceInfo.model = c.d();
    localDeviceInfo.manu = Build.MANUFACTURER;
    localDeviceInfo.model = Build.MODEL;
    localDeviceInfo.network = Util.getLinkedWay(paramContext);
    localDeviceInfo.os = "android";
    DisplayMetrics localDisplayMetrics = Util.getDisplayMetrics(paramContext);
    localDeviceInfo.resolution = (localDisplayMetrics.widthPixels + "*" + localDisplayMetrics.heightPixels);
    localDeviceInfo.sdCard = Util.getExternalStorageInfo(paramContext);
    localDeviceInfo.sdDouble = Util.getSimOperator(paramContext);
    localDeviceInfo.sdkVersion = String.valueOf(2.3F);
    return localDeviceInfo;
  }

  public static l a()
  {
    return a;
  }

  private void a(int paramInt, String paramString, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "@@ loadConfiguraionFailHandler(" + paramInt + "," + paramString + ")");
    ReportItem localReportItem = new ReportItem(a(parama.b()), 1, paramInt, 3, parama.f(), parama.c(), paramString);
    e.a().a(localReportItem);
  }

  private void a(int paramInt, String paramString1, String paramString2, TpnsUnregisterReq paramTpnsUnregisterReq, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "@@ uninstallReportFailedHandler(" + paramInt + "," + paramString1 + "," + paramString2 + "," + paramTpnsUnregisterReq + ")");
    UnregisterInfo localUnregisterInfo = new UnregisterInfo();
    localUnregisterInfo.accessId = paramTpnsUnregisterReq.unregInfo.appInfo.accessId;
    localUnregisterInfo.accessKey = paramTpnsUnregisterReq.unregInfo.appInfo.accessKey;
    localUnregisterInfo.isUninstall = paramTpnsUnregisterReq.unregInfo.isUninstall;
    localUnregisterInfo.packName = paramString2;
    localUnregisterInfo.timestamp = paramTpnsUnregisterReq.unregInfo.timestamp;
    ReportItem localReportItem = new ReportItem(a(parama.b()), 1, paramInt, 2, parama.f(), parama.c(), paramString1 + "@" + paramString2);
    e.a().a(localReportItem);
  }

  private String b(Context paramContext)
  {
    JSONArray localJSONArray;
    long l1;
    if (paramContext != null)
    {
      StringBuilder localStringBuilder = new StringBuilder().append("getWifiList(").append(paramContext.getPackageName()).append("), lastWifiList size:");
      if (b == null);
      for (int i = 0; ; i = b.length())
      {
        TLog.v("XGService", i);
        localJSONArray = com.tencent.android.tpush.common.a.a(paramContext, 10);
        if ((localJSONArray == null) || (localJSONArray.length() <= 0))
          break label223;
        l1 = c.b(paramContext, c, 0L);
        if ((b == null) || (b.length() <= 0))
          break label226;
        if (!b.toString().equalsIgnoreCase(localJSONArray.toString()))
          break;
        return "";
      }
    }
    label223: label226: for (int j = Math.abs(b.length() - localJSONArray.length()); ; j = 0)
    {
      long l2 = System.currentTimeMillis();
      if (j >= 3);
      for (int k = 1; ; k = 0)
      {
        int m;
        if (k == 0)
        {
          boolean bool = Math.abs(l2 - l1) < 1800000L;
          m = 0;
          if (!bool);
        }
        else
        {
          m = 1;
        }
        if (m == 0)
          break;
        c.a(paramContext, c, l2);
        String str = localJSONArray.toString();
        b = localJSONArray;
        return str;
      }
      return "";
    }
  }

  public void a(long paramLong)
  {
    TLog.v("XGService", "@@ loadConfiguration(" + paramLong + ")");
    TpnsConfigReq localTpnsConfigReq = new TpnsConfigReq(paramLong);
    TLog.e("XGTcpSendPacks", "@@ =============loadConfiguration()================");
    com.tencent.android.tpush.service.channel.b.b().a(localTpnsConfigReq, new n(this));
  }

  public void a(long paramLong, String paramString1, int paramInt, String paramString2, com.tencent.android.tpush.service.channel.n paramn)
  {
    TLog.v("XGService", "@@ sendTag(" + paramLong + "," + paramString1 + ")");
    TpnsTokenTagReq localTpnsTokenTagReq = new TpnsTokenTagReq();
    localTpnsTokenTagReq.accessId = paramLong;
    localTpnsTokenTagReq.flag = paramInt;
    localTpnsTokenTagReq.tag = paramString2;
    TLog.e("XGTcpSendPacks", "@@ =============TpnsTokenTagReq()================");
    com.tencent.android.tpush.service.channel.b.b().a(localTpnsTokenTagReq, paramn);
  }

  public void a(long paramLong, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, String paramString5, com.tencent.android.tpush.service.channel.n paramn)
  {
    TLog.v("XGService", "@@ register(" + paramLong + "," + paramString2 + "," + paramString3 + "," + paramString4 + "," + paramInt + ")");
    TpnsRegisterReq localTpnsRegisterReq = new TpnsRegisterReq();
    localTpnsRegisterReq.accessId = paramLong;
    localTpnsRegisterReq.accessKey = paramString1;
    localTpnsRegisterReq.deviceId = paramString2;
    localTpnsRegisterReq.appCert = paramString5;
    localTpnsRegisterReq.account = paramString3;
    localTpnsRegisterReq.ticket = paramString4;
    localTpnsRegisterReq.ticketType = (short)paramInt;
    localTpnsRegisterReq.deviceInfo = a(i.e());
    localTpnsRegisterReq.token = CacheManager.getToken(i.e());
    localTpnsRegisterReq.version = 1;
    localTpnsRegisterReq.appVersion = c.i(i.e());
    TLog.e("XGTcpSendPacks", "@@ =============TpnsRegisterReq()================");
    com.tencent.android.tpush.service.channel.b.b().a(localTpnsRegisterReq, paramn);
  }

  public void a(JceStruct paramJceStruct, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "@@ onReceivedServicePush(" + paramJceStruct + "," + parama + ")");
    if (paramJceStruct == null);
    do
    {
      return;
      if (!(paramJceStruct instanceof TpnsPushClientReq))
        continue;
      TLog.i("XGService", ">> Notification");
      TpnsPushClientReq localTpnsPushClientReq = (TpnsPushClientReq)paramJceStruct;
      com.tencent.android.tpush.service.b.a.a().a(localTpnsPushClientReq.msgList, localTpnsPushClientReq.timeUs, parama);
      return;
    }
    while (!(paramJceStruct instanceof TpnsTriggerReportReq));
    TLog.i("XGService", ">> Message");
    TpnsTriggerReportReq localTpnsTriggerReportReq = (TpnsTriggerReportReq)paramJceStruct;
    LogUtil.upload(localTpnsTriggerReportReq.timeStart, localTpnsTriggerReportReq.timeEnd);
    TpnsTriggerReportRsp localTpnsTriggerReportRsp = new TpnsTriggerReportRsp();
    TLog.e("XGTcpSendPacks", "@@ =============TpnsTriggerReportReq()================");
    com.tencent.android.tpush.service.channel.b.b().a(localTpnsTriggerReportRsp, null);
  }

  public void a(String paramString)
  {
    TLog.v("XGService", "@@ uninstallReport(" + paramString + ")");
    com.tencent.android.tpush.data.b localb = CacheManager.getRegisterInfoByPkgName(paramString);
    if (localb != null)
    {
      TLog.i("XGService", ">>> uninstall report:" + localb.a + " " + localb.b + " " + localb.c + " " + paramString);
      TpnsUnregisterReq localTpnsUnregisterReq = new TpnsUnregisterReq();
      localTpnsUnregisterReq.unregInfo = new UnregInfo(new AppInfo(localb.a, localb.b, "", 0), 1, System.currentTimeMillis());
      CacheManager.UninstallInfoByPkgName(paramString);
      TLog.e("XGTcpSendPacks", "@@ =============TpnsUnregisterReq()================");
      com.tencent.android.tpush.service.channel.b.b().a(localTpnsUnregisterReq, new o(this, paramString));
    }
  }

  public void a(String paramString1, String paramString2, long paramLong, String paramString3, String paramString4, com.tencent.android.tpush.service.channel.n paramn)
  {
    TLog.v("XGService", "@@ register(" + paramString1 + "," + paramString2 + "," + paramLong + "," + paramString4 + ")");
    TpnsUnregisterReq localTpnsUnregisterReq = new TpnsUnregisterReq();
    Object localObject = "";
    try
    {
      TLog.v("XGService", ">> unregister report:" + paramLong + " " + paramString3 + " " + paramString1 + " " + paramString4);
      String str = TpnsSecurity.getEncryptAPKSignature(i.e().createPackageContext(paramString4, 0));
      localObject = str;
      localTpnsUnregisterReq.unregInfo = new UnregInfo(new AppInfo(paramLong, paramString3, (String)localObject, 0), 0, 0L);
      TLog.e("XGTcpSendPacks", "@@ =============TpnsUnregisterReq()================");
      com.tencent.android.tpush.service.channel.b.b().a(localTpnsUnregisterReq, paramn);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        TLog.e("XGService", ">> create context [for: " + paramString4 + "] fail.", localNameNotFoundException);
    }
    catch (Exception localException)
    {
      while (true)
        TLog.e("XGService", ">> create context [for: " + paramString4 + "] fail.", localException);
    }
  }

  public void a(ArrayList paramArrayList, com.tencent.android.tpush.service.channel.n paramn)
  {
    TLog.v("XGService", "@@ msgAck(" + paramArrayList + ")");
    if ((paramArrayList != null) && (paramArrayList.size() > 0))
    {
      TpnsPushVerifyReq localTpnsPushVerifyReq = new TpnsPushVerifyReq(paramArrayList);
      TLog.e("XGTcpSendPacks", "@@ =============msgAck()================");
      com.tencent.android.tpush.service.channel.b.b().a(localTpnsPushVerifyReq, paramn);
    }
  }

  public void a(boolean paramBoolean, long paramLong)
  {
    TLog.v("XGService", "@@ loadAppList(" + paramBoolean + "," + paramLong + ")");
    long l = CacheManager.getLastLoadIpTime(i.e());
    TLog.i("XGService", ">> load configure list, last time =" + l);
    if (paramBoolean)
    {
      TLog.i("XGService", ">> Http");
      if (System.currentTimeMillis() - l > com.tencent.android.tpush.service.a.a.n)
      {
        TLog.i("XGService", ">> load configure list, interval on expries.");
        if (com.tencent.android.tpush.service.a.a.a(i.e()) != paramLong)
          a().a(paramLong);
      }
    }
    while (System.currentTimeMillis() - l < com.tencent.android.tpush.service.a.a.n)
    {
      TLog.i("XGService", ">> load ap list, interval on ready.");
      return;
      TLog.i("XGService", ">> Tcp");
      if (com.tencent.android.tpush.service.a.a.a(i.e()) == paramLong)
        continue;
      a().a(paramLong);
    }
    TpnsGetApListReq localTpnsGetApListReq = new TpnsGetApListReq();
    NetworkInfo localNetworkInfo = new NetworkInfo();
    localNetworkInfo.network = c.d(i.e());
    localNetworkInfo.op = c.e(i.e());
    localTpnsGetApListReq.netInfo = localNetworkInfo;
    TLog.e("XGTcpSendPacks", "@@ =============loadAppList()================");
    com.tencent.android.tpush.service.channel.b.b().a(localTpnsGetApListReq, new p(this));
  }

  public com.tencent.android.tpush.service.channel.m b()
  {
    TLog.e("XGService", "@@ ==========getReconnPackage()==========");
    if (!c.e())
    {
      TLog.w("XGService", ">> no app registered!");
      return null;
    }
    TpnsReconnectReq localTpnsReconnectReq = new TpnsReconnectReq();
    localTpnsReconnectReq.deviceId = com.tencent.android.tpush.service.c.b.a();
    localTpnsReconnectReq.networkType = (short)c.d(i.e());
    localTpnsReconnectReq.token = CacheManager.getToken(i.e());
    localTpnsReconnectReq.unregInfoList = CacheManager.getUninstallAndUnregisterInfo();
    localTpnsReconnectReq.recvMsgList = com.tencent.android.tpush.service.b.a.a().a(i.e(), com.tencent.android.tpush.service.b.a.a().b(i.e()));
    localTpnsReconnectReq.msgClickList = com.tencent.android.tpush.service.b.a.a().a(i.e());
    TLog.v("XGService", ">> TpnsReconnectReq, deviceId=" + localTpnsReconnectReq.deviceId + ",networkType=" + localTpnsReconnectReq.networkType + ",token=" + localTpnsReconnectReq.token + ",unregInfoList=" + localTpnsReconnectReq.unregInfoList + ",recvMsgList=" + localTpnsReconnectReq.recvMsgList + "req.msgClickList=" + localTpnsReconnectReq.msgClickList);
    Context localContext = i.e();
    MutableInfo localMutableInfo = new MutableInfo();
    if ((localContext != null) && (Util.isNetworkAvailable(localContext)) && (Util.isWifiNet(localContext)))
    {
      localMutableInfo.bssid = Util.getWiFiBBSID(localContext);
      localMutableInfo.ssid = Util.getWiFiSSID(localContext);
    }
    localMutableInfo.mac = Util.getWifiMacAddress(localContext);
    try
    {
      localMutableInfo.wflist = b(localContext);
      localTpnsReconnectReq.mutableInfo = localMutableInfo;
      com.tencent.android.tpush.service.channel.m localm = new com.tencent.android.tpush.service.channel.m(localTpnsReconnectReq, this.d);
      TLog.e("XGService", "@@ ==========getReconnPackage()==========");
      return localm;
    }
    catch (Exception localException)
    {
      while (true)
        TLog.e("XGService", ">> getWifiList(" + localContext + ")" + localException);
    }
  }

  public void b(ArrayList paramArrayList, com.tencent.android.tpush.service.channel.n paramn)
  {
    TLog.v("XGService", "@@ sendPushClickResult(" + paramArrayList + ")");
    if ((paramArrayList == null) || (paramArrayList.size() == 0))
      return;
    TLog.i("XGService", "sendPushClickResult");
    TpnsPushClickReq localTpnsPushClickReq = new TpnsPushClickReq();
    localTpnsPushClickReq.msgClickList = paramArrayList;
    TLog.e("XGTcpSendPacks", "@@ =============TpnsPushClickReq()================");
    com.tencent.android.tpush.service.channel.b.b().a(localTpnsPushClickReq, paramn);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.l
 * JD-Core Version:    0.6.0
 */