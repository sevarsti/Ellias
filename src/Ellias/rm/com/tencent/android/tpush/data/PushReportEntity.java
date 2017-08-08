package com.tencent.android.tpush.data;

import java.io.Serializable;

public class PushReportEntity
  implements Serializable
{
  private static final long serialVersionUID = 721994613426649085L;
  public int accId;
  public int apn;
  public int commandId;
  public String detail;
  public String deviceInfo;
  public int isp;
  public String locIp;
  public int pact;
  public String qua;
  public int result;
  public int resultCode;
  public int sdkVersion;
  public String seq;
  public String serviceHost;
  public int tmcost;
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.data.PushReportEntity
 * JD-Core Version:    0.6.0
 */