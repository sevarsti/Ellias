package oicq.wlogin_sdk.request;

import java.io.Serializable;
import java.util.TreeMap;
import oicq.wlogin_sdk.sharemem.WloginSigInfo;
import oicq.wlogin_sdk.sharemem.WloginSimpleInfo;
import oicq.wlogin_sdk.tools.util;

public class WloginAllSigInfo
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  public TreeMap<Long, WloginSigInfo> _tk_map = new TreeMap();
  public long _uin = 0L;
  public WloginSimpleInfo _useInfo = new WloginSimpleInfo();

  public WloginAllSigInfo get_clone()
  {
    try
    {
      WloginAllSigInfo localWloginAllSigInfo = (WloginAllSigInfo)clone();
      return localWloginAllSigInfo;
    }
    catch (Exception localException)
    {
      util.LOGD(localException.toString());
    }
    return null;
  }

  public int put_siginfo(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5, byte[] paramArrayOfByte6, byte[] paramArrayOfByte7, byte[] paramArrayOfByte8, byte[] paramArrayOfByte9, byte[] paramArrayOfByte10, byte[] paramArrayOfByte11, byte[] paramArrayOfByte12, byte[][] paramArrayOfByte, long[] paramArrayOfLong)
  {
    WloginSigInfo localWloginSigInfo = (WloginSigInfo)this._tk_map.get(new Long(paramLong1));
    if (localWloginSigInfo != null)
    {
      TreeMap localTreeMap2 = this._tk_map;
      Long localLong2 = new Long(paramLong1);
      localTreeMap2.put(localLong2, localWloginSigInfo.Set(paramLong2, paramLong3, paramLong4, paramLong5, paramArrayOfByte1, paramArrayOfByte2, paramArrayOfByte3, paramArrayOfByte4, paramArrayOfByte5, paramArrayOfByte6, paramArrayOfByte7, paramArrayOfByte8, paramArrayOfByte9, paramArrayOfByte10, paramArrayOfByte11, paramArrayOfByte12, paramArrayOfByte, paramArrayOfLong));
    }
    while (true)
    {
      return 0;
      TreeMap localTreeMap1 = this._tk_map;
      Long localLong1 = new Long(paramLong1);
      localTreeMap1.put(localLong1, new WloginSigInfo(paramLong2, paramLong3, paramLong4, paramLong5, paramArrayOfByte1, paramArrayOfByte2, paramArrayOfByte3, paramArrayOfByte4, paramArrayOfByte5, paramArrayOfByte6, paramArrayOfByte7, paramArrayOfByte8, paramArrayOfByte9, paramArrayOfByte10, paramArrayOfByte11, paramArrayOfByte12, paramArrayOfByte, paramArrayOfLong));
    }
  }

  public int put_siginfo(long paramLong1, long paramLong2, long paramLong3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    this._tk_map.put(new Long(paramLong1), new WloginSigInfo(paramLong2, paramLong3, paramArrayOfByte1, paramArrayOfByte2));
    return 0;
  }

  public int put_siginfo(long paramLong, WloginSigInfo paramWloginSigInfo)
  {
    WloginSigInfo localWloginSigInfo = (WloginSigInfo)this._tk_map.get(new Long(paramLong));
    if ((localWloginSigInfo == null) || (localWloginSigInfo.iSEarlyThan(paramWloginSigInfo._create_time)))
      this._tk_map.put(new Long(paramLong), paramWloginSigInfo);
    return 0;
  }

  public int put_simpleinfo(long paramLong, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[][] paramArrayOfByte)
  {
    if (this._useInfo == null)
      this._useInfo = new WloginSimpleInfo(paramLong, paramArrayOfByte1, paramArrayOfByte2, paramArrayOfByte3, paramArrayOfByte4, paramArrayOfByte);
    while (true)
    {
      return 0;
      this._useInfo.set_info(paramLong, paramArrayOfByte1, paramArrayOfByte2, paramArrayOfByte3, paramArrayOfByte4, paramArrayOfByte);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.WloginAllSigInfo
 * JD-Core Version:    0.6.0
 */