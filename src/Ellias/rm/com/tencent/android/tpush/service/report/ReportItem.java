package com.tencent.android.tpush.service.report;

import android.content.Context;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.i;
import java.io.Serializable;

public class ReportItem
  implements Serializable
{
  public static final String ACCESS_ID = "accessid";
  public static final String APN = "apn";
  public static final String APP_ID = "appid";
  public static final String COMMAND_ID = "commandid";
  public static final String DETAIL = "detail";
  public static final String DEVICE_INFO = "deviceinfo";
  public static final String FREQUENCY = "frequency";
  public static final String ISP = "isp";
  public static final String LOC_IP = "locip";
  public static final String PACT = "pact";
  public static final String QUA = "qua";
  public static final String RESULT = "result";
  public static final String RESULT_CODE = "resultcode";
  public static final String SDK_VERSION = "sdkversion";
  public static final String SERVICE_HOST = "servicehost";
  public static final String STIME = "stime";
  public static final String TMCOST = "tmcost";
  private static final long serialVersionUID = 5639894120067283028L;
  public long accessId = 0L;
  public short apn;
  public String appId = "";
  public int commandId;
  public String detail = "";
  public String deviceInfo = "";
  public int frequency = 10;
  public int isp;
  public String locIp = "";
  public int pact;
  public String qua = "";
  public int result;
  public int resultCode;
  public float sdkVersion;
  public String serviceHost = "";
  public long stime;
  public long tmcost;

  public ReportItem()
  {
  }

  public ReportItem(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString1, long paramLong, String paramString2)
  {
    this.appId = "1000086";
    this.apn = (short)c.d(i.e());
    if ((this.apn == 1) || (this.apn == 0) || (this.apn == i))
    {
      this.isp = i;
      this.deviceInfo = (c.c(i.e()) + "_" + c.d() + "_" + c.c());
      if (i.e() == null)
        break label457;
    }
    label457: for (String str = i.e().getPackageName(); ; str = "")
    {
      this.serviceHost = str;
      this.sdkVersion = 2.3F;
      this.qua = CacheManager.getQua(i.e(), this.accessId);
      this.accessId = -1L;
      this.pact = paramInt1;
      this.result = paramInt2;
      this.resultCode = paramInt3;
      this.commandId = paramInt4;
      this.locIp = paramString1;
      this.tmcost = paramLong;
      this.detail = (paramString2 + "");
      TLog.i("ReportLogTag", "reportItem appId=" + this.appId + ",frequency=" + this.frequency + ",commandid=" + this.commandId + ",resultcode=" + this.resultCode + ",timecost=" + this.tmcost + ",isp=" + this.isp + ",apn=" + this.apn + ",accessId=" + this.accessId + ",pact=" + this.pact + ",result=" + this.result + ",sdkVersion=" + this.sdkVersion + ",pua=" + this.qua + ",locIp=" + this.locIp + ",deviceInfo" + this.deviceInfo + ",serviceHost=" + this.serviceHost);
      return;
      i = c.e(i.e());
      break;
    }
  }

  public ReportItem(long paramLong1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString1, long paramLong2, String paramString2)
  {
    this.appId = "1000086";
    this.isp = c.e(i.e());
    this.apn = (short)c.d(i.e());
    this.deviceInfo = (c.c(i.e()) + "_" + c.d() + "_" + c.c());
    if (i.e() != null);
    for (String str = i.e().getPackageName(); ; str = "")
    {
      this.serviceHost = str;
      this.stime = System.currentTimeMillis();
      this.sdkVersion = 2.3F;
      this.qua = CacheManager.getQua(i.e(), paramLong1);
      this.accessId = paramLong1;
      this.pact = paramInt1;
      this.result = paramInt2;
      this.resultCode = paramInt3;
      this.commandId = paramInt4;
      this.locIp = paramString1;
      this.tmcost = paramLong2;
      this.detail = (paramString2 + "");
      TLog.i("ReportLogTag", "reportItem appId=" + this.appId + ",frequency=" + this.frequency + ",commandid=" + this.commandId + ",resultcode=" + this.resultCode + ",timecost=" + this.tmcost + ",isp=" + this.isp + ",apn=" + this.apn + ",detail=" + this.detail + ",accessId=" + this.accessId + ",pact=" + this.pact + ",result=" + this.result + ",sdkVersion=" + this.sdkVersion + ",pua=" + this.qua + ",locIp=" + this.locIp + ",deviceInfo" + this.deviceInfo + ",serviceHost=" + this.serviceHost + ",stime=" + this.stime);
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.report.ReportItem
 * JD-Core Version:    0.6.0
 */