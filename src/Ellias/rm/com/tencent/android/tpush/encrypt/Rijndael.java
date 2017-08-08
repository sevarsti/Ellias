package com.tencent.android.tpush.encrypt;

import com.tencent.android.tpush.horse.Tools;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.i;

public class Rijndael
{
  public static String decrypt(String paramString)
  {
    if (!c.a(paramString))
    {
      localObject = TpnsSecurity.oiSymmetryDecrypt2(paramString);
      int i = 0;
      while (i < 3)
      {
        if (!"failed".equals(localObject))
          break label91;
        TLog.i("XGService", ">> decrypt content " + i + " times");
        String str = TpnsSecurity.oiSymmetryDecrypt2(paramString);
        i++;
        localObject = str;
      }
      Tools.clearOptKeyList(i.e());
      Tools.clearCacheServerItems(i.e());
      Tools.clearOptStrategyItem(i.e());
    }
    Object localObject = "";
    label91: return (String)localObject;
  }

  public static String encrypt(String paramString)
  {
    if (!c.a(paramString))
    {
      localObject = TpnsSecurity.oiSymmetryEncrypt2(paramString);
      int i = 0;
      while (i < 3)
      {
        if (!"failed".equals(localObject))
          break label73;
        TLog.i("XGService", ">> encrypt content " + i + " times");
        String str = TpnsSecurity.oiSymmetryEncrypt2(paramString);
        i++;
        localObject = str;
      }
    }
    Object localObject = "";
    label73: return (String)localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.encrypt.Rijndael
 * JD-Core Version:    0.6.0
 */