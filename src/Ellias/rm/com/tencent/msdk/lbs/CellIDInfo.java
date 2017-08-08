package com.tencent.msdk.lbs;

class CellIDInfo
{
  public int cellId;
  public int locationAreaCode;
  public int mobileCountryCode;
  public int mobileNetworkCode;
  public String radioType;
  public int rssi;

  public String toString()
  {
    return "Cid = " + this.cellId + ", mcc = " + this.mobileCountryCode + ", mnc =" + this.mobileNetworkCode + ", lac = " + this.locationAreaCode + ", radioType = " + this.radioType + ", rssi = " + this.rssi;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.lbs.CellIDInfo
 * JD-Core Version:    0.6.0
 */