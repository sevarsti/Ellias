package com.tencent.android.tpush.service.c;

import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.i;

public class b
{
  public static String a()
  {
    TLog.v("XGService", "@@ getDeviceId()");
    if (i.e() != null)
      try
      {
        String str2 = TpnsSecurity.getBusinessDeviceId(i.e());
        str1 = str2;
        if ((str1 == null) || (str1.trim().length() == 0))
          str1 = "";
        TLog.i("XGService", ">>> getDeviceId():" + str1);
        return str1;
      }
      catch (Exception localException)
      {
        while (true)
        {
          TLog.e("ServiceLogTag", ">>get deviceid err", localException);
          String str1 = null;
        }
      }
    TLog.i("XGService", ">>> getDeviceId() > context == null");
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.c.b
 * JD-Core Version:    0.6.0
 */