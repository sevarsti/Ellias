package oicq.wlogin_sdk.request;

import java.io.Serializable;

public class UinInfo
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public long _expire;
  public Long _uin;

  public UinInfo(long paramLong, Long paramLong1)
  {
    this._expire = paramLong;
    this._uin = paramLong1;
  }

  public UinInfo get_clone()
  {
    try
    {
      UinInfo localUinInfo = (UinInfo)clone();
      return localUinInfo;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  boolean iSExpire(long paramLong)
  {
    return paramLong > this._expire;
  }

  public void setExpireTime(long paramLong)
  {
    this._expire = paramLong;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.UinInfo
 * JD-Core Version:    0.6.0
 */