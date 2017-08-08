package oicq.wlogin_sdk.sharemem;

import java.util.TreeMap;

public class ApkCheckInfo
{
  public int STAT_CHECK_FAIL = 3;
  public int STAT_CHECK_SUCCESS = 2;
  public int STAT_INIT = 1;
  public int STAT_UNINIT = 0;
  TreeMap<String, MD5Info> _map = new TreeMap();

  public void addApkInfo(String paramString1, String paramString2)
  {
    MD5Info localMD5Info = new MD5Info(paramString2, this.STAT_INIT);
    this._map.put(paramString1, localMD5Info);
  }

  public void setApkInfoStat(String paramString1, String paramString2, int paramInt)
  {
    MD5Info localMD5Info = (MD5Info)this._map.get(paramString1);
    if (localMD5Info._smd5.equals(paramString2))
      localMD5Info._stat = paramInt;
  }

  public class MD5Info
  {
    public String _smd5 = "";
    public int _stat = ApkCheckInfo.this.STAT_UNINIT;

    public MD5Info(String paramInt, int arg3)
    {
      this._smd5 = paramInt;
      int i;
      this._stat = i;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.sharemem.ApkCheckInfo
 * JD-Core Version:    0.6.0
 */