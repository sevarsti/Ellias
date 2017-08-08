package com.tencent.android.tpush.data;

import java.io.Serializable;

public class ClientReportEntity
  implements Serializable
{
  public static final int COMMANDID_CONNECT = 1;
  public static final int COMMANDID_HEARTBEAT = 3;
  public static final int COMMANDID_LOAD_CONFIGURATION = 3;
  public static final int COMMANDID_REGISGER = 0;
  public static final int COMMANDID_UNREGISTER = 2;
  private static final long serialVersionUID = 7424062493011101717L;
  public int accId;
  public int apn;
  public int appId;
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
  public String serviceHost;
  public long stime;
  public int tmcost;
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.data.ClientReportEntity
 * JD-Core Version:    0.6.0
 */