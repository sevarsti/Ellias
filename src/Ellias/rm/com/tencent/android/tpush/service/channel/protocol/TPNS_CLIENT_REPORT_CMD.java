package com.tencent.android.tpush.service.channel.protocol;

import java.io.Serializable;

public final class TPNS_CLIENT_REPORT_CMD
  implements Serializable
{
  public static final TPNS_CLIENT_REPORT_CMD CMD_REPORT_SPEED;
  public static final int _CMD_REPORT_SPEED;
  private static TPNS_CLIENT_REPORT_CMD[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!TPNS_CLIENT_REPORT_CMD.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new TPNS_CLIENT_REPORT_CMD[1];
      CMD_REPORT_SPEED = new TPNS_CLIENT_REPORT_CMD(0, 0, "CMD_REPORT_SPEED");
      return;
    }
  }

  private TPNS_CLIENT_REPORT_CMD(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public String toString()
  {
    return this.__T;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_REPORT_CMD
 * JD-Core Version:    0.6.0
 */