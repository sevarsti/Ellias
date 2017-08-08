package com.tencent.android.tpush.data;

import java.io.Serializable;

public class UnregisterInfo
  implements Serializable
{
  public static final byte TYPE_UNINSTALL = 1;
  public static final byte TYPE_UNREGISTER = 0;
  private static final long serialVersionUID = -2293449011577410421L;
  public long accessId;
  public String accessKey;
  public String appCert;
  public byte isUninstall;
  public String option;
  public String packName;
  public long timestamp;
  public String token;

  public String toString()
  {
    return "------------UnregisterInfo----------------\naccessId=" + this.accessId + "\n" + "accesskey=" + this.accessKey + "\n" + "token=" + this.token + "\n" + "isUninstall=" + this.isUninstall + "\n" + "packName=" + this.packName + "\n" + "--------------UnregisterInfo End------------";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.data.UnregisterInfo
 * JD-Core Version:    0.6.0
 */