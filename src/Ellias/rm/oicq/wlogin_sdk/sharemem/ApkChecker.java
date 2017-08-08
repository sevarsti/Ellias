package oicq.wlogin_sdk.sharemem;

import android.content.Context;
import android.content.Intent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import oicq.wlogin_sdk.request.UinInfo;
import oicq.wlogin_sdk.request.WloginAllSigInfo;
import oicq.wlogin_sdk.request.account_sig_info_map;
import oicq.wlogin_sdk.request.request_app_signature;
import oicq.wlogin_sdk.request.request_global;
import oicq.wlogin_sdk.tools.util;

public class ApkChecker extends Thread
{
  request_app_signature mCheck;
  Context mContext;
  int mPid;
  sharemem_client mShare;

  public ApkChecker(Context paramContext, sharemem_client paramsharemem_client, request_app_signature paramrequest_app_signature, int paramInt)
  {
    this.mContext = paramContext;
    this.mShare = paramsharemem_client;
    this.mCheck = paramrequest_app_signature;
    this.mPid = paramInt;
  }

  public void run()
  {
    util.LOGI("ApkChecker Pid=" + this.mPid + " ...");
    if (this.mPid == 0)
    {
      util.LOGI("ApkChecker Pid=" + this.mPid + " return");
      return;
    }
    byte[] arrayOfByte1 = util.getPkgPublicKeyFromPid(this.mContext, this.mPid);
    WloginSigInfo localWloginSigInfo1 = request_global.get_siginfo(this.mContext);
    if (localWloginSigInfo1 == null)
    {
      util.LOGI("ApkChecker Pid=" + this.mPid + " do not find ticks");
      return;
    }
    this.mCheck._g._master_tgt_key = localWloginSigInfo1._TGTKey;
    String str1 = util.getPkgNameFromPid(this.mContext, this.mPid);
    byte[] arrayOfByte2 = util.get_apk_v(this.mContext, str1);
    int i = this.mCheck.make_request(localWloginSigInfo1._app_pri, localWloginSigInfo1._ret_appid, 1L, util.SSO_VERSION, 996082, localWloginSigInfo1._TGT, 66560, 116, null, str1.getBytes(), 0L, 1L, 1L, arrayOfByte2, arrayOfByte1, null);
    if (i == 0)
      this.mShare.set_checked(true);
    try
    {
      new Intent(sharemem_client.SHAREMEM_SET_UINFO_RECEIVED);
      TreeMap localTreeMap1 = account_sig_info_map.loadTKTreeMap(this.mContext, "tk_file");
      Iterator localIterator1;
      if (localTreeMap1 != null)
        localIterator1 = localTreeMap1.keySet().iterator();
      while (true)
      {
        if (!localIterator1.hasNext())
        {
          localTreeMap2 = account_sig_info_map.loadTKTreeMap(this.mContext, "name_file");
          if (localTreeMap2 != null)
          {
            localIterator2 = localTreeMap2.keySet().iterator();
            boolean bool = localIterator2.hasNext();
            if (bool)
              break;
          }
          util.LOGI("ApkChecker Pid=" + this.mPid + " ret=" + i);
          return;
        }
        Long localLong1 = (Long)localIterator1.next();
        WloginAllSigInfo localWloginAllSigInfo = (WloginAllSigInfo)localTreeMap1.get(localLong1);
        Iterator localIterator3 = localWloginAllSigInfo._tk_map.keySet().iterator();
        while (localIterator3.hasNext())
        {
          Long localLong2 = (Long)localIterator3.next();
          WloginSigInfo localWloginSigInfo2 = (WloginSigInfo)localWloginAllSigInfo._tk_map.get(localLong2);
          if (localWloginSigInfo2.iSExpireA2(request_global.get_cur_time()))
            continue;
          this.mShare.put_siginfo(localLong1.longValue(), localLong2.longValue(), localWloginAllSigInfo._useInfo.getWloginRemoteData(), localWloginSigInfo2.getWloginRemoteData());
        }
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        TreeMap localTreeMap2;
        Iterator localIterator2;
        StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
        localException.printStackTrace(localPrintWriter);
        localPrintWriter.flush();
        localStringWriter.flush();
        util.LOGW("exception:", localStringWriter.toString());
        continue;
        String str2 = (String)localIterator2.next();
        UinInfo localUinInfo = (UinInfo)localTreeMap2.get(str2);
        this.mShare.put_account(str2, localUinInfo._uin.longValue());
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.sharemem.ApkChecker
 * JD-Core Version:    0.6.0
 */