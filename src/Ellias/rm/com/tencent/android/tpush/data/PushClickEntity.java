package com.tencent.android.tpush.data;

import java.io.Serializable;

public class PushClickEntity
  implements Serializable
{
  private static final long serialVersionUID = -166678396447407161L;
  public long accessId = 0L;
  public int action = 0;
  public long broadcastId = 0L;
  public long clickTime = 0L;
  public long msgId = 0L;
  public String pkgName = "";
  public long timestamp = 0L;
  public long type = 1L;

  public PushClickEntity()
  {
  }

  public PushClickEntity(long paramLong1, long paramLong2, long paramLong3, long paramLong4, String paramString, long paramLong5, long paramLong6, int paramInt)
  {
    this.msgId = paramLong1;
    this.accessId = paramLong2;
    this.broadcastId = paramLong3;
    this.timestamp = paramLong4;
    this.pkgName = paramString;
    this.type = paramLong5;
    this.clickTime = paramLong6;
    this.action = paramInt;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.data.PushClickEntity
 * JD-Core Version:    0.6.0
 */