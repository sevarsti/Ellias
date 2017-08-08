package com.pay.http;

import com.pay.tool.APAppDataInterface;

public class APIPState
{
  public int accessTimes = 0;
  public int ansTims = -1;
  public String city = "";
  public int failPercent = 0;
  public int failTimes = 0;
  public String ip = "";
  public String ipEnv = APAppDataInterface.singleton().getEnv();
  public String province = "";
  public int seqFailTimes = 0;
  public int succTimes = 0;

  public boolean equals(Object paramObject)
  {
    String str = ((APIPState)paramObject).ip;
    return this.ip.equals(str);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.APIPState
 * JD-Core Version:    0.6.0
 */