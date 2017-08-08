package oicq.wlogin_sdk.push;

public class uin_appid
  implements Comparable
{
  public long _appid;
  public long _sub_appid;
  public long _uin;

  public uin_appid(long paramLong1, long paramLong2, long paramLong3)
  {
    this._uin = paramLong1;
    this._appid = paramLong2;
    this._sub_appid = paramLong3;
  }

  public int compareTo(Object paramObject)
  {
    uin_appid localuin_appid = (uin_appid)paramObject;
    if (this._uin > localuin_appid._uin);
    do
    {
      do
      {
        return 1;
        if (this._uin < localuin_appid._uin)
          return -1;
      }
      while (this._appid > localuin_appid._appid);
      if (this._appid < localuin_appid._appid)
        return -1;
    }
    while (this._sub_appid > localuin_appid._sub_appid);
    if (this._sub_appid < localuin_appid._sub_appid)
      return -1;
    return 0;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.push.uin_appid
 * JD-Core Version:    0.6.0
 */