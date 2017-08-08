package oicq.wlogin_sdk.request;

import B;
import J;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import oicq.wlogin_sdk.code2d.close_code;
import oicq.wlogin_sdk.code2d.code2d_base;
import oicq.wlogin_sdk.code2d.code2d_req_status;
import oicq.wlogin_sdk.code2d.verify_code;
import oicq.wlogin_sdk.name_op.name_bind;
import oicq.wlogin_sdk.name_op.name_query;
import oicq.wlogin_sdk.name_op.name_req_status;
import oicq.wlogin_sdk.name_op.name_unbind;
import oicq.wlogin_sdk.oidb.name_get_uin;
import oicq.wlogin_sdk.register.reg_request;
import oicq.wlogin_sdk.register.reg_request_get_account;
import oicq.wlogin_sdk.register.reg_request_query_account_available;
import oicq.wlogin_sdk.register.reg_request_query_msg_status;
import oicq.wlogin_sdk.register.reg_request_resend_msg;
import oicq.wlogin_sdk.register.reg_request_submit_checkimg;
import oicq.wlogin_sdk.register.reg_request_submit_mobile;
import oicq.wlogin_sdk.register.reg_request_submit_msg_chk;
import oicq.wlogin_sdk.register.reg_status;
import oicq.wlogin_sdk.report.report_t1;
import oicq.wlogin_sdk.report.report_t2;
import oicq.wlogin_sdk.sharemem.WloginLoginInfo;
import oicq.wlogin_sdk.sharemem.WloginRemoteData;
import oicq.wlogin_sdk.sharemem.WloginSigInfo;
import oicq.wlogin_sdk.sharemem.WloginSimpleInfo;
import oicq.wlogin_sdk.tlv_type.tlv_t105;
import oicq.wlogin_sdk.tlv_type.tlv_t129;
import oicq.wlogin_sdk.tlv_type.tlv_t150;
import oicq.wlogin_sdk.tlv_type.tlv_t165;
import oicq.wlogin_sdk.tools.ErrMsg;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

public class WtloginHelper extends WtloginListener
{
  private request_change_sig mChangeSig = new request_change_sig(this.mG);
  private int mChangeSigMiscBitmap = 628;
  private request_app_signature mCheckApk = new request_app_signature(this.mG);
  private request_check_sms mCheckSms = new request_check_sms(this.mG);
  private request_checkimage mCheckimage = new request_checkimage(this.mG);
  private Context mContext = null;
  private request_delay mDelay = new request_delay(this.mG);
  private request_flush_sms mFlushSms = new request_flush_sms(this.mG);
  private request_flushimage mFlushimage = new request_flushimage(this.mG);
  private request_global mG = new request_global(null);
  private request_getuin mGetuin = new request_getuin(this.mG);
  private HelperHandler mHelperHandler = newHelperHandler(this.mG, this);
  private WtloginListener mListener = null;
  private int mMainSigMap = 996082;
  private name_req_status mNameReqStatus = new name_req_status();
  private long mOpenAppid = 715019303L;
  private int mOpenMainSigMap = 16576;
  private request_ping mPing = new request_ping(this.mG);
  private reg_status mRegStatus = new reg_status();
  private request_fast_login mRemoteLogin = new request_fast_login(this.mG);
  private request_report_error mReportError = new request_report_error(this.mG);
  private int mSubSigMap = 66560;
  private int mTGTGTMiscBitmap = 892;
  private request_TGTGT mTgtgt = new request_TGTGT(this.mG);
  private request_transport mTransport = new request_transport(this.mG);

  public WtloginHelper(Context paramContext)
  {
    this.mContext = paramContext;
    this.mG.set_context(paramContext);
    RequestInit(0);
    util.LOGD("WtloginHelper create end");
  }

  public WtloginHelper(Context paramContext, int paramInt)
  {
    this.mContext = paramContext;
    this.mG.set_context(paramContext, paramInt);
    RequestInit(0);
    util.LOGD("WtloginHelper create end");
  }

  private Boolean ClearUserLoginDataInMem(String paramString, long paramLong)
  {
    int i = 1;
    if ((paramString == null) || (paramString.length() <= 0))
      return Boolean.valueOf(true);
    monitorenter;
    while (true)
    {
      try
      {
        if (!util.check_uin_account(paramString).booleanValue())
        {
          localObject2 = this.mG.get_account(paramString);
          if (localObject2 != null)
            continue;
          i = 0;
          if (i != 1)
            continue;
          this.mG.clear_sig((Long)localObject2, Long.valueOf(paramLong));
          return Boolean.valueOf(true);
          this.mG.clear_account(paramString);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      Long localLong = Long.valueOf(Long.parseLong(paramString));
      Object localObject2 = localLong;
    }
  }

  private WloginSigInfo FindUserSig(long paramLong1, long paramLong2)
  {
    return this.mG.get_siginfo(paramLong1, paramLong2);
  }

  private WloginSigInfo FindUserSig(String paramString, long paramLong)
  {
    Long localLong;
    if (!util.check_uin_account(paramString).booleanValue())
    {
      localLong = this.mG.get_account(paramString);
      i = 0;
      if (localLong == null);
    }
    for (int i = 1; i == 0; i = 1)
    {
      return null;
      localLong = Long.valueOf(Long.parseLong(paramString));
    }
    return this.mG.get_siginfo(localLong.longValue(), paramLong);
  }

  private int RequestInit(int paramInt)
  {
    monitorenter;
    try
    {
      int i = util.get_saved_network_type(this.mContext);
      String str = Build.VERSION.RELEASE;
      if (str == null)
        str = "";
      this.mG.init();
      util.LOGI("WtloginHelper init ok: android version:" + str + " saved_network_type:" + i + " network_type:" + this.mG._network_type + " release time:" + util.get_release_time(), this.mContext, "", 1);
      return 0;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private int RequestReport(int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, long paramLong1, long paramLong2)
  {
    if (paramInt == 0)
    {
      new HelperThread(this, this.mHelperHandler, paramArrayOfByte1, paramArrayOfByte2, paramLong1, paramLong2).RunReq(util.ASYN_REPORT);
      return -1001;
    }
    if (this.mG.is_use_msf_transport());
    try
    {
      Thread.sleep(10000L);
      label49: synchronized (this.mTransport)
      {
        int i = this.mTransport.make_request(paramLong1, null, paramArrayOfByte1, paramArrayOfByte2, paramLong2);
        this.mG.close_transport_connect();
        return i;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      break label49;
    }
  }

  private int RequestReportError(int paramInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, long paramLong1, long paramLong2, int paramInt2)
  {
    if (paramInt1 == 0)
    {
      new HelperThread(this, this.mHelperHandler, paramArrayOfByte1, paramArrayOfByte2, paramLong1, paramLong2, paramInt2).RunReq(util.ASYN_REPORT_ERROR);
      return -1001;
    }
    return this.mReportError.make_request(paramLong1, null, paramArrayOfByte1, paramArrayOfByte2, paramLong2, paramInt2);
  }

  private HelperHandler newHelperHandler(request_global paramrequest_global, WtloginHelper paramWtloginHelper)
  {
    if (Looper.myLooper() == null)
      return null;
    return new HelperHandler(paramrequest_global, paramWtloginHelper);
  }

  public int BindMobileByUin(String paramString1, long paramLong, String paramString2, String paramString3)
  {
    WloginSimpleInfo localWloginSimpleInfo = new WloginSimpleInfo();
    if (!GetBasicUserInfo(paramString1, localWloginSimpleInfo).booleanValue())
      return -1003;
    long l = localWloginSimpleInfo._uin;
    WloginSigInfo localWloginSigInfo = FindUserSig(l, paramLong);
    if (localWloginSigInfo == null)
      return -1004;
    name_bind localname_bind = new name_bind();
    TransReqContext localTransReqContext = new TransReqContext();
    localTransReqContext.set_name_func_req();
    localTransReqContext.set_subcmd(localname_bind.get_cmd());
    byte[] arrayOfByte1 = localWloginSigInfo._sKey;
    byte[] arrayOfByte2 = paramString2.getBytes();
    byte[] arrayOfByte3 = paramString3.getBytes();
    int[] arrayOfInt1 = new int[0];
    int[] arrayOfInt2 = { 0, 0 };
    localTransReqContext._body = localname_bind.get_request(l, paramLong, arrayOfByte1, arrayOfByte2, arrayOfByte3, arrayOfInt1, (byte[][])Array.newInstance(Byte.TYPE, arrayOfInt2));
    return RequestTransport(0, 1, paramString1, paramLong, localname_bind._role, localTransReqContext);
  }

  public void CancelRequest()
  {
    this.mG._canceled = 1;
  }

  public int CheckPictureAndGetSt(String paramString, byte[] paramArrayOfByte, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    return CheckPictureAndGetSt(paramString, paramArrayOfByte, paramWUserSigInfo, null, paramInt);
  }

  public int CheckPictureAndGetSt(String paramString, byte[] paramArrayOfByte, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte1, int paramInt)
  {
    if (paramInt == 0)
    {
      new HelperThread(this, this.mHelperHandler, paramString, paramArrayOfByte, paramWUserSigInfo, paramArrayOfByte1).RunReq(util.ASYN_CHECK_IMAGE);
      return -1001;
    }
    util.LOGI("user:" + paramString + " CheckPictureAndGetSt ...", this.mG._context, paramString, 0);
    monitorenter;
    try
    {
      int i = this.mCheckimage.make_request(paramArrayOfByte);
      int m;
      if (i != 0)
        m = i;
      while (true)
      {
        if (m == 0)
        {
          this.mG._rt1.commit_t2(this.mG._uin, this.mG._name, util.format_ret_code(m, this.mG._last_err_msg.getMessage()), m);
          if ((paramWUserSigInfo._userStSig != null) && (paramWUserSigInfo._userStSig.length != 0))
            RequestReport(0, paramWUserSigInfo._userStSig, paramWUserSigInfo._userSt_Key, this.mG._uin, this.mG._appid);
          label178: if ((this.mG._t150 != null) && (this.mG._t150.get_bitmap() != 0))
            RequestReportError(0, paramWUserSigInfo._userStSig, paramWUserSigInfo._userSt_Key, this.mG._uin, this.mG._appid, 1);
          new delete_expire_log(this.mG._context).start();
          monitorexit;
          this.mG.close_connect();
          util.LOGI("user:" + paramString + " CheckPictureAndGetSt ret=" + m, this.mG._context, this.mG._uin, 1);
          return m;
        }
        while (true)
        {
          Long localLong;
          int j;
          int k;
          try
          {
            if (util.check_uin_account(paramString).booleanValue())
              continue;
            localLong = this.mG.get_account(paramString);
            j = 0;
            if (localLong == null)
              break label1104;
            j = 1;
            break label1104;
            localLong = Long.valueOf(Long.parseLong(paramString));
            j = 1;
            break label1104;
            this.mG._name = paramString;
            this.mG._uin = localLong.longValue();
            WloginSigInfo localWloginSigInfo1 = this.mG.get_siginfo(localLong.longValue(), this.mG._appid);
            if (localWloginSigInfo1 == null)
              break label579;
            if (this.mG._tmp_pwd_type == 0)
              continue;
            paramWUserSigInfo.get_clone(localWloginSigInfo1);
            if ((this.mG._sub_appid_list == null) || (paramArrayOfByte1 == null) || (2 * this.mG._sub_appid_list.length != paramArrayOfByte1.length))
              break label1117;
            k = 0;
            if (this.mG._sub_appid_list == null)
              break label1117;
            int n = this.mG._sub_appid_list.length;
            if (k < n)
              break label509;
            break label1117;
            paramWUserSigInfo.get_clone(localWloginSigInfo1);
            continue;
          }
          finally
          {
          }
          monitorexit;
          throw localObject1;
          label509: WloginSigInfo localWloginSigInfo2 = this.mG.get_siginfo(localLong.longValue(), this.mG._sub_appid_list[k]);
          if (localWloginSigInfo2 != null)
          {
            paramArrayOfByte1[(k * 2)] = ((byte[])localWloginSigInfo2._userSt_Key.clone());
            paramArrayOfByte1[(1 + k * 2)] = ((byte[])localWloginSigInfo2._userStSig.clone());
            break label1123;
            label579: byte[] arrayOfByte1;
            label606: int i4;
            if ((paramWUserSigInfo._in_ksid != null) && (paramWUserSigInfo._in_ksid.length > 0))
            {
              arrayOfByte1 = (byte[])paramWUserSigInfo._in_ksid.clone();
              if (this.mG._tmp_pwd_type == 0)
                break label763;
              i4 = this.mTgtgt.make_request(this.mG._appid, 1, this.mG._uin, 0, this.mG._ip_addr, this.mG._tmp_pwd, this.mTGTGTMiscBitmap, this.mSubSigMap, this.mG._sub_appid_list, this.mG._main_sigmap, this.mG._sub_appid, 1, 0, 1, 102400, 1, arrayOfByte1);
            }
            WloginSigInfo localWloginSigInfo3;
            label763: int i1;
            for (m = i4; ; m = i1)
            {
              if (m != -1000)
                this.mG.clear_tmp_pwd();
              if (m != 0)
                break;
              localWloginSigInfo3 = this.mG.get_siginfo(localLong.longValue(), this.mG._appid);
              if (localWloginSigInfo3 != null)
                break label872;
              m = -1004;
              break;
              arrayOfByte1 = this.mG._ksid;
              break label606;
              byte[] arrayOfByte2 = new byte[4];
              util.int64_to_buf32(arrayOfByte2, 0, System.currentTimeMillis() / 1000L + request_global._l_init_time);
              i1 = this.mTgtgt.make_request(this.mG._appid, 1, this.mG._uin, 0, this.mG._ip_addr, arrayOfByte2, 0, this.mG._tmp_pwd, this.mTGTGTMiscBitmap, this.mSubSigMap, this.mG._sub_appid_list, this.mG._main_sigmap, this.mG._sub_appid, 1, 0, 1, 102400, 1, arrayOfByte1);
            }
            label872: if (this.mG._tmp_pwd_type != 0)
              paramWUserSigInfo.get_clone(localWloginSigInfo3);
            while (true)
            {
              if ((this.mG._sub_appid_list == null) || (paramArrayOfByte1 == null) || (2 * this.mG._sub_appid_list.length != paramArrayOfByte1.length))
                break label1129;
              i2 = 0;
              if (this.mG._sub_appid_list == null)
                break label1129;
              int i3 = this.mG._sub_appid_list.length;
              if (i2 < i3)
                break;
              break label1129;
              paramWUserSigInfo.get_clone(localWloginSigInfo3);
            }
            WloginSigInfo localWloginSigInfo4 = this.mG.get_siginfo(localLong.longValue(), this.mG._sub_appid_list[i2]);
            if (localWloginSigInfo4 == null)
              break label1140;
            paramArrayOfByte1[(i2 * 2)] = ((byte[])localWloginSigInfo4._userSt_Key.clone());
            paramArrayOfByte1[(1 + i2 * 2)] = ((byte[])localWloginSigInfo4._userStSig.clone());
            break label1140;
            this.mG._rt1.commit_t2(this.mG._uin, this.mG._name, util.format_ret_code(m, this.mG._last_err_msg.getMessage()), m);
            RequestReportError(0, paramWUserSigInfo._userStSig, paramWUserSigInfo._userSt_Key, this.mG._uin, this.mG._appid, 0);
            break label178;
            label1104: if (j != 0)
              continue;
            m = -1003;
            break;
            label1117: m = 0;
            break;
          }
          label1123: k++;
        }
        label1129: m = 0;
      }
    }
    finally
    {
      while (true)
      {
        int i2;
        continue;
        label1140: i2++;
      }
    }
  }

  public int CheckSMSGetSt(String paramString, byte[] paramArrayOfByte, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    return CheckSMSGetSt(paramString, paramArrayOfByte, paramWUserSigInfo, null, paramInt);
  }

  public int CheckSMSGetSt(String paramString, byte[] paramArrayOfByte, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte1, int paramInt)
  {
    if (paramInt == 0)
    {
      new HelperThread(this, this.mHelperHandler, paramString, paramArrayOfByte, paramWUserSigInfo, paramArrayOfByte1).RunReq(util.ASYN_CHECK_SMS);
      return -1001;
    }
    monitorenter;
    while (true)
    {
      Long localLong;
      int j;
      int k;
      try
      {
        i = this.mCheckSms.make_request(paramArrayOfByte);
        if (i == 0)
          continue;
        monitorexit;
        this.mG.close_connect();
        return i;
        if (util.check_uin_account(paramString).booleanValue())
          continue;
        localLong = this.mG.get_account(paramString);
        j = 0;
        if (localLong == null)
          break label322;
        j = 1;
        break label322;
        localLong = Long.valueOf(Long.parseLong(paramString));
        j = 1;
        break label322;
        this.mG._uin = localLong.longValue();
        WloginSigInfo localWloginSigInfo1 = this.mG.get_siginfo(localLong.longValue(), this.mG._appid);
        if (localWloginSigInfo1 == null)
          break label314;
        if (this.mG._tmp_pwd_type == 0)
          continue;
        paramWUserSigInfo.get_clone(localWloginSigInfo1);
        if ((this.mG._sub_appid_list == null) || (paramArrayOfByte1 == null) || (2 * this.mG._sub_appid_list.length != paramArrayOfByte1.length))
          break label335;
        k = 0;
        if (this.mG._sub_appid_list == null)
          break label335;
        if (k >= this.mG._sub_appid_list.length)
        {
          break label335;
          paramWUserSigInfo.get_clone(localWloginSigInfo1);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      WloginSigInfo localWloginSigInfo2 = this.mG.get_siginfo(localLong.longValue(), this.mG._sub_appid_list[k]);
      if (localWloginSigInfo2 != null)
      {
        paramArrayOfByte1[(k * 2)] = ((byte[])localWloginSigInfo2._userSt_Key.clone());
        paramArrayOfByte1[(1 + k * 2)] = ((byte[])localWloginSigInfo2._userStSig.clone());
      }
      k++;
      continue;
      label314: int i = -1004;
      continue;
      label322: if (j != 0)
        continue;
      i = -1003;
      continue;
      label335: i = 0;
    }
  }

  public Boolean ClearUserLoginData(String paramString, long paramLong)
  {
    util.LOGI("user:" + paramString + " ClearUserLoginData ...", this.mG._context, paramString, 1);
    this.mG.remove_last_login_info(paramString);
    return ClearUserLoginDataInMem(paramString, paramLong);
  }

  public Boolean ClearUserLoginDataTest(String paramString)
  {
    int i = 1;
    if ((paramString == null) || (paramString.length() <= 0))
      return Boolean.valueOf(true);
    monitorenter;
    while (true)
    {
      try
      {
        if (!util.check_uin_account(paramString).booleanValue())
        {
          localObject2 = this.mG.get_account(paramString);
          if (localObject2 != null)
            continue;
          i = 0;
          if (i != 1)
            continue;
          this.mG.clear_sig_test((Long)localObject2);
          return Boolean.valueOf(true);
          this.mG.clear_account(paramString);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      Long localLong = Long.valueOf(Long.parseLong(paramString));
      Object localObject2 = localLong;
    }
  }

  public int CloseCode(String paramString, long paramLong, byte[] paramArrayOfByte, int paramInt, byte[][] paramArrayOfByte1)
  {
    WloginSimpleInfo localWloginSimpleInfo = new WloginSimpleInfo();
    if (!GetBasicUserInfo(paramString, localWloginSimpleInfo).booleanValue())
      return -1003;
    long l = localWloginSimpleInfo._uin;
    WloginSigInfo localWloginSigInfo = FindUserSig(l, paramLong);
    if (localWloginSigInfo == null)
      return -1004;
    close_code localclose_code = new close_code();
    TransReqContext localTransReqContext = new TransReqContext();
    localTransReqContext.set_code2d_func_req();
    localTransReqContext.set_subcmd(localclose_code.get_cmd());
    localTransReqContext._body = localclose_code.get_request(l, paramLong, paramArrayOfByte, localWloginSigInfo._TGT, this.mG._IMEI, paramInt, paramArrayOfByte1);
    return RequestTransport(0, 1, paramString, paramLong, localclose_code._role, localTransReqContext);
  }

  public byte[] GetA1ByAccount(String paramString, long paramLong)
  {
    Long localLong;
    WloginSigInfo localWloginSigInfo;
    if (!util.check_uin_account(paramString).booleanValue())
    {
      localLong = this.mG.get_account(paramString);
      if (localLong != null)
        break label106;
      localWloginSigInfo = null;
    }
    while ((localWloginSigInfo == null) || (localWloginSigInfo._en_A1 == null) || (localWloginSigInfo._en_A1.length <= 0))
    {
      util.LOGI("userAccount:" + paramString + " dwAppid:" + paramLong + " GetA1ByAccount return: null", this.mG._context, paramString, 1);
      return null;
      localLong = Long.valueOf(Long.parseLong(paramString));
      label106: localWloginSigInfo = this.mG.get_siginfo(localLong.longValue(), paramLong);
      if (localWloginSigInfo != null)
        continue;
    }
    util.LOGI("userAccount:" + paramString + " dwAppid:" + paramLong + " GetA1ByAccount return: not null", this.mG._context, paramString, 1);
    return (byte[])localWloginSigInfo._en_A1.clone();
  }

  public byte[] GetA2A2KeyBuf(String paramString, long paramLong)
  {
    WUserSigInfo localWUserSigInfo = GetLocalSig(paramString, paramLong);
    if ((localWUserSigInfo._A2 == null) || (localWUserSigInfo._A2.length <= 0) || (localWUserSigInfo._A2_Key == null) || (localWUserSigInfo._A2_Key.length <= 0));
    do
      return null;
    while ((request_global._IMEI_KEY == null) || (request_global._IMEI_KEY.length <= 0));
    byte[] arrayOfByte = new byte[2 + (2 + (8 + (2 + paramString.getBytes().length)) + localWUserSigInfo._A2.length) + localWUserSigInfo._A2_Key.length];
    util.int16_to_buf(arrayOfByte, 0, paramString.getBytes().length);
    int i = 0 + 2;
    System.arraycopy(paramString.getBytes(), 0, arrayOfByte, i, paramString.getBytes().length);
    int j = 2 + paramString.getBytes().length;
    util.int64_to_buf(arrayOfByte, j, paramLong);
    int k = j + 8;
    util.int16_to_buf(arrayOfByte, k, localWUserSigInfo._A2.length);
    int m = k + 2;
    System.arraycopy(localWUserSigInfo._A2, 0, arrayOfByte, m, localWUserSigInfo._A2.length);
    int n = m + localWUserSigInfo._A2.length;
    util.int16_to_buf(arrayOfByte, n, localWUserSigInfo._A2_Key.length);
    int i1 = n + 2;
    System.arraycopy(localWUserSigInfo._A2_Key, 0, arrayOfByte, i1, localWUserSigInfo._A2_Key.length);
    (i1 + localWUserSigInfo._A2_Key.length);
    return cryptor.encrypt(arrayOfByte, 0, arrayOfByte.length, request_global._IMEI_KEY);
  }

  public List<WloginLoginInfo> GetAllLoginInfo()
  {
    return this.mG.get_all_logined_account();
  }

  public long GetAppidFromUrl(String paramString)
  {
    if (paramString == null);
    int i;
    do
    {
      return -1L;
      i = paramString.indexOf("f=");
    }
    while ((i == -1) || (i + 2 >= paramString.length()));
    int j = i + 2;
    String str = "";
    while (true)
    {
      if (j >= paramString.length());
      try
      {
        do
        {
          long l = Long.parseLong(str);
          return l;
        }
        while (paramString.charAt(j) == '&');
        str = str + paramString.charAt(j);
        j++;
      }
      catch (Exception localException)
      {
      }
    }
    return -1L;
  }

  public Boolean GetBasicUserInfo(String paramString, WloginSimpleInfo paramWloginSimpleInfo)
  {
    int i = 1;
    Long localLong;
    WloginSimpleInfo localWloginSimpleInfo;
    if (!util.check_uin_account(paramString).booleanValue())
    {
      localLong = this.mG.get_account(paramString);
      if (localLong == null)
        i = 0;
      if (i == 1)
      {
        localWloginSimpleInfo = this.mG.get_simpleinfo(localLong.longValue());
        if (localWloginSimpleInfo != null)
          break label81;
        i = 0;
      }
      label55: if (i != 1)
        break label172;
    }
    label172: for (boolean bool = true; ; bool = false)
    {
      return Boolean.valueOf(bool);
      localLong = Long.valueOf(Long.parseLong(paramString));
      break;
      label81: util.LOGD(getClass().getName() + "found:" + localWloginSimpleInfo._uin);
      paramWloginSimpleInfo.get_clone(new WloginSimpleInfo(localWloginSimpleInfo._uin, localWloginSimpleInfo._face, localWloginSimpleInfo._age, localWloginSimpleInfo._gander, localWloginSimpleInfo._nick, localWloginSimpleInfo._img_type, localWloginSimpleInfo._img_format, localWloginSimpleInfo._img_url));
      break label55;
    }
  }

  public ErrMsg GetLastErrMsg()
  {
    if (this.mG._last_err_msg != null)
      try
      {
        ErrMsg localErrMsg = (ErrMsg)this.mG._last_err_msg.clone();
        return localErrMsg;
      }
      catch (CloneNotSupportedException localCloneNotSupportedException)
      {
        StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
        localCloneNotSupportedException.printStackTrace(localPrintWriter);
        localPrintWriter.flush();
        localStringWriter.flush();
        util.LOGW("exception", localStringWriter.toString());
        return new ErrMsg();
      }
    return new ErrMsg();
  }

  public WloginLastLoginInfo GetLastLoginInfo()
  {
    Object localObject = null;
    List localList = this.mG.get_all_logined_account();
    if (localList == null)
      return null;
    Iterator localIterator = localList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (localObject == null)
          break;
        if ((localObject.mAccount == null) || (localObject.mAccount.length() <= 0))
          break label105;
        return new WloginLastLoginInfo(localObject.mAccount);
      }
      WloginLoginInfo localWloginLoginInfo = (WloginLoginInfo)localIterator.next();
      if (localObject == null)
      {
        localObject = localWloginLoginInfo;
        continue;
      }
      if (localWloginLoginInfo.mCreateTime <= localObject.mCreateTime)
        continue;
      localObject = localWloginLoginInfo;
    }
    label105: return new WloginLastLoginInfo(new Long(localObject.mUin).toString());
  }

  public WUserSigInfo GetLocalSig(String paramString, long paramLong)
  {
    Long localLong;
    if (!util.check_uin_account(paramString).booleanValue())
    {
      util.LOGD("GetLocalSig:" + paramString + ":name");
      localLong = this.mG.get_account(paramString);
      if (localLong != null)
        break label88;
    }
    label88: WloginSigInfo localWloginSigInfo;
    do
    {
      return null;
      util.LOGD("GetLocalSig:" + paramString + ":num");
      localLong = Long.valueOf(Long.parseLong(paramString));
      localWloginSigInfo = this.mG.get_siginfo(localLong.longValue(), paramLong);
    }
    while (localWloginSigInfo == null);
    WUserSigInfo localWUserSigInfo = new WUserSigInfo();
    localWUserSigInfo.get_clone(localWloginSigInfo);
    return localWUserSigInfo;
  }

  public int GetMobileByUin(String paramString, long paramLong)
  {
    WloginSimpleInfo localWloginSimpleInfo = new WloginSimpleInfo();
    if (!GetBasicUserInfo(paramString, localWloginSimpleInfo).booleanValue())
      return -1003;
    long l = localWloginSimpleInfo._uin;
    WloginSigInfo localWloginSigInfo = FindUserSig(l, paramLong);
    if (localWloginSigInfo == null)
      return -1004;
    name_query localname_query = new name_query();
    TransReqContext localTransReqContext = new TransReqContext();
    localTransReqContext.set_name_func_req();
    localTransReqContext.set_subcmd(localname_query.get_cmd());
    localTransReqContext._body = localname_query.get_request(l, paramLong, localWloginSigInfo._sKey, new int[0]);
    return RequestTransport(0, 1, paramString, paramLong, localname_query._role, localTransReqContext);
  }

  public int GetOpenKeyWithPasswd(String paramString1, long paramLong, String paramString2, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    return GetStWithPasswd(paramString1, this.mOpenAppid, this.mOpenMainSigMap, paramLong, null, false, paramString2, paramWUserSigInfo, null, paramInt);
  }

  public int GetOpenKeyWithoutPasswd(String paramString, long paramLong1, long paramLong2, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    return GetStWithoutPasswd(paramString, this.mOpenAppid, this.mOpenAppid, -1L, this.mOpenMainSigMap, paramLong2, null, paramWUserSigInfo, null, null, paramInt);
  }

  public byte[] GetPictureData(String paramString)
  {
    monitorenter;
    try
    {
      byte[] arrayOfByte = this.mG._t105.get_pic();
      return arrayOfByte;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public byte[] GetPicturePrompt(String paramString)
  {
    monitorenter;
    try
    {
      byte[] arrayOfByte = this.mG._t165.get_data();
      return arrayOfByte;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public List<WloginLoginInfo> GetRemoteLoginedInfo(long paramLong, long[] paramArrayOfLong)
  {
    return this.mG.get_all_logined_account_remote(paramLong, paramArrayOfLong);
  }

  public int GetRemoteStWithoutPasswd(String paramString, long paramLong1, long paramLong2, long paramLong3, int paramInt1, long paramLong4, long[] paramArrayOfLong, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte1, byte[][] paramArrayOfByte2, int paramInt2)
  {
    int i = 0;
    new Long(0L);
    if (paramInt2 == 0)
    {
      new HelperThread(this, this.mHelperHandler, paramString, paramLong1, paramLong2, paramLong3, paramInt1, paramLong4, paramArrayOfLong, paramWUserSigInfo, paramArrayOfByte1, paramArrayOfByte2).RunReq(util.ASYN_GET_REMOTE_ST_WITHOUT_PWD);
      return -1001;
    }
    util.LOGI("wtlogin login with GetRemoteStWithoutPasswd:user:" + paramString + " dwSrcAppid:" + paramLong1 + " dwDstAppid:" + paramLong2 + " dwDstAppPri:" + paramLong3 + " dwMainSigMap:" + paramInt1 + " dwSubDstAppid:" + paramLong4 + " ...", this.mG._context, paramString, 0);
    if (this.mG.get_ping_end_flag() != 0)
      monitorenter;
    try
    {
      int j = util.get_saved_network_type(this.mContext);
      this.mG._network_type = util.get_network_type(this.mContext);
      if (j != this.mG._network_type)
      {
        util.set_net_retry_type(this.mContext, 0);
        util.save_network_type(this.mContext, this.mG._network_type);
      }
      this.mG._apn = util.get_apn_string(this.mContext).getBytes();
      this.mG._name = paramString;
      this.mG._uin = 0L;
      this.mG._appid = paramLong2;
      this.mG._sub_appid = paramLong4;
      this.mG._main_sigmap = paramInt1;
      this.mG._sub_appid_list = null;
      this.mG._encrypt_a1 = new byte[0];
      if (paramArrayOfLong != null)
        this.mG._sub_appid_list = ((long[])paramArrayOfLong.clone());
      this.mG._rt1.add_t2(new report_t2("exchg", new String(this.mG._sim_operator_name), System.currentTimeMillis(), paramLong1, paramLong4, paramArrayOfLong));
      byte[] arrayOfByte1 = null;
      if (paramArrayOfByte2 != null)
      {
        int k = paramArrayOfByte2.length;
        arrayOfByte1 = null;
        if (k > 0)
          arrayOfByte1 = paramArrayOfByte2[0];
      }
      byte[] arrayOfByte2 = util.get_apk_id(this.mG._context);
      localArrayList = new ArrayList();
      int m = this.mG.get_siginfo_remote(paramString, this.mG._uin, paramLong1, paramLong2, 1L, util.SSO_VERSION, this.mMainSigMap, this.mSubSigMap, this.mChangeSigMiscBitmap, paramArrayOfLong, arrayOfByte2, util.SSO_VERSION, paramLong2, 1L, util.get_apk_v(this.mG._context, new String(arrayOfByte2)), util.getPkgPublicKeyFromApkName(this.mG._context, new String(arrayOfByte2)), arrayOfByte1, localArrayList);
      i = m;
      if (i == 0)
        if (localArrayList == null)
        {
          n = -1019;
          label510: if (1 != 0)
            if (n != 0)
              break label1455;
        }
    }
    finally
    {
      try
      {
        label1003: label1272: int i5;
        while (true)
        {
          ArrayList localArrayList;
          this.mG._rt1.commit_t2(this.mG._uin, this.mG._name, util.format_ret_code(n, this.mG._last_err_msg.getMessage()), n);
          if ((paramWUserSigInfo._userStSig != null) && (paramWUserSigInfo._userStSig.length != 0))
            RequestReport(0, paramWUserSigInfo._userStSig, paramWUserSigInfo._userSt_Key, this.mG._uin, this.mG._appid);
          while (true)
          {
            while (true)
            {
              if ((this.mG._t150 != null) && (this.mG._t150.get_bitmap() != 0))
                RequestReportError(0, paramWUserSigInfo._userStSig, paramWUserSigInfo._userSt_Key, this.mG._uin, this.mG._appid, 1);
              new delete_expire_log(this.mG._context).start();
              monitorexit;
              this.mG.close_connect();
              util.LOGI("wtlogin login with GetRemoteStWithoutPasswd:user:" + paramString + " dwSrcAppid:" + paramLong1 + " dwDstAppid:" + paramLong2 + " dwDstAppPri:" + paramLong3 + " dwMainSigMap:" + paramInt1 + " dwSubDstAppid:" + paramLong4 + " ret=" + n, this.mG._context, this.mG._uin, 1);
              return n;
              try
              {
                Thread.currentThread();
                Thread.sleep(20L);
              }
              catch (Exception localException)
              {
              }
            }
            break;
            int i1 = 3;
            if (paramArrayOfLong != null)
              i1 += paramArrayOfLong.length;
            if (localArrayList.size() < i1)
            {
              n = -1019;
              break label510;
            }
            Long localLong;
            if (!util.check_uin_account(paramString).booleanValue())
            {
              WloginRemoteData localWloginRemoteData3 = (WloginRemoteData)localArrayList.get(0);
              if (localWloginRemoteData3 == null)
              {
                n = -1019;
                break label510;
              }
              List localList1 = localWloginRemoteData3.getLongData();
              List localList2 = localWloginRemoteData3.getByteData();
              if ((localList1 == null) || (localList2 == null) || (localList1.size() != 1) || (localList2.size() != 1))
                break label1550;
              localLong = (Long)localList1.get(0);
              this.mG.put_account(paramString, localLong);
            }
            WloginRemoteData localWloginRemoteData1;
            while (true)
            {
              localWloginRemoteData1 = (WloginRemoteData)localArrayList.get(1);
              if (localWloginRemoteData1 != null)
                break label1003;
              n = -1019;
              break;
              localLong = Long.valueOf(Long.parseLong(paramString));
            }
            WloginSimpleInfo localWloginSimpleInfo = WloginSimpleInfo.getWloginSimpleInfo(localWloginRemoteData1);
            if (localWloginSimpleInfo == null)
            {
              n = -1019;
              break label510;
            }
            WloginRemoteData localWloginRemoteData2 = (WloginRemoteData)localArrayList.get(2);
            if (localWloginRemoteData2 == null)
            {
              n = -1019;
              break label510;
            }
            WloginSigInfo localWloginSigInfo1 = WloginSigInfo.getWloginSigInfo(localWloginRemoteData2);
            if (localWloginSigInfo1 == null)
            {
              n = -1019;
              break label510;
            }
            this.mG.put_siginfo(localLong.longValue(), paramLong2, localWloginSigInfo1._app_pri, localWloginSigInfo1._create_time, localWloginSigInfo1._expire_time, localWloginSigInfo1._A2_expire_time, localWloginSimpleInfo._face, localWloginSimpleInfo._age, localWloginSimpleInfo._gander, localWloginSimpleInfo._nick, localWloginSimpleInfo.getReserveData(), localWloginSigInfo1._TGT, localWloginSigInfo1._TGTKey, localWloginSigInfo1._userStSig, localWloginSigInfo1._userSt_Key, localWloginSigInfo1._userStWebSig, localWloginSigInfo1._userA5, localWloginSigInfo1._userA8, localWloginSigInfo1._lsKey, localWloginSigInfo1._sKey, localWloginSigInfo1._userSig64, localWloginSigInfo1._openid, localWloginSigInfo1._openkey, localWloginSigInfo1.getReserveData(), null);
            for (int i2 = 3; ; i2++)
            {
              int i3 = localArrayList.size();
              if (i2 >= i3);
              WloginSigInfo localWloginSigInfo2;
              for (n = i; ; n = -1019)
              {
                int i4 = localArrayList.size();
                if (i2 >= i4)
                  break label1313;
                n = -1019;
                break;
                localWloginSigInfo2 = WloginSigInfo.getWloginSigInfo((WloginRemoteData)localArrayList.get(i2));
                if (localWloginSigInfo2 != null)
                  break label1272;
              }
              this.mG.put_siginfo(localLong.longValue(), paramLong2, localWloginSigInfo2._create_time, localWloginSigInfo2._expire_time, localWloginSigInfo2._userStSig, localWloginSigInfo2._userSt_Key);
            }
            label1313: WloginSigInfo localWloginSigInfo3 = this.mG.get_siginfo(localLong.longValue(), paramLong2);
            if (localWloginSigInfo3 == null)
            {
              n = -1004;
              break label510;
            }
            paramWUserSigInfo.get_clone(localWloginSigInfo3);
            if ((paramArrayOfLong == null) || (paramArrayOfByte1 == null) || (2 * paramArrayOfLong.length != paramArrayOfByte1.length))
              break label510;
            i5 = 0;
            if (paramArrayOfLong == null)
              break label510;
            int i6 = paramArrayOfLong.length;
            if (i5 >= i6)
              break label510;
            WloginSigInfo localWloginSigInfo4 = this.mG.get_siginfo(localLong.longValue(), paramArrayOfLong[i5]);
            if (localWloginSigInfo4 == null)
              break label1558;
            paramArrayOfByte1[(i5 * 2)] = ((byte[])localWloginSigInfo4._userSt_Key.clone());
            paramArrayOfByte1[(1 + i5 * 2)] = ((byte[])localWloginSigInfo4._userStSig.clone());
            break label1558;
            label1455: this.mG._rt1.commit_t2(this.mG._uin, this.mG._name, util.format_ret_code(n, this.mG._last_err_msg.getMessage()), n);
            RequestReportError(0, paramWUserSigInfo._userStSig, paramWUserSigInfo._userSt_Key, this.mG._uin, this.mG._appid, 0);
          }
          while (true)
          {
            label1530: monitorexit;
            Object localObject1;
            throw localObject1;
            localObject2 = finally;
          }
          int n = i;
          continue;
          label1550: n = -1019;
        }
        label1558: i5++;
      }
      finally
      {
        break label1530;
      }
    }
  }

  public int GetSMSTime(SMSTime paramSMSTime)
  {
    paramSMSTime._timeout = this.mG._t129.get_timeout();
    paramSMSTime._nexttime = this.mG._t129.get_nexttime();
    return 0;
  }

  // ERROR //
  public int GetStWithPasswd(String paramString1, long paramLong1, int paramInt1, long paramLong2, long[] paramArrayOfLong, boolean paramBoolean, String paramString2, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte, int paramInt2)
  {
    // Byte code:
    //   0: iload 4
    //   2: sipush 192
    //   5: ior
    //   6: istore 13
    //   8: iload 12
    //   10: ifne +42 -> 52
    //   13: new 288	oicq/wlogin_sdk/request/WtloginHelper$HelperThread
    //   16: dup
    //   17: aload_0
    //   18: aload_0
    //   19: aload_0
    //   20: getfield 134	oicq/wlogin_sdk/request/WtloginHelper:mHelperHandler	Loicq/wlogin_sdk/request/WtloginHelper$HelperHandler;
    //   23: aload_1
    //   24: lload_2
    //   25: iload 13
    //   27: lload 5
    //   29: aload 7
    //   31: iload 8
    //   33: aload 9
    //   35: aload 10
    //   37: aload 11
    //   39: invokespecial 1027	oicq/wlogin_sdk/request/WtloginHelper$HelperThread:<init>	(Loicq/wlogin_sdk/request/WtloginHelper;Loicq/wlogin_sdk/request/WtloginHelper;Landroid/os/Handler;Ljava/lang/String;JIJ[JZLjava/lang/String;Loicq/wlogin_sdk/request/WUserSigInfo;[[B)V
    //   42: getstatic 1030	oicq/wlogin_sdk/tools/util:ASYN_GET_ST_WITH_PWD	I
    //   45: invokevirtual 298	oicq/wlogin_sdk/request/WtloginHelper$HelperThread:RunReq	(I)V
    //   48: sipush -1001
    //   51: ireturn
    //   52: iconst_0
    //   53: istore 14
    //   55: iconst_1
    //   56: istore 15
    //   58: new 251	java/lang/StringBuilder
    //   61: dup
    //   62: ldc_w 1032
    //   65: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   68: aload_1
    //   69: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: ldc_w 614
    //   75: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: lload_2
    //   79: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   82: ldc_w 853
    //   85: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: iload 13
    //   90: invokevirtual 264	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   93: ldc_w 855
    //   96: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: lload 5
    //   101: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   104: ldc_w 857
    //   107: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   113: aload_0
    //   114: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   117: getfield 441	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   120: aload_1
    //   121: iconst_0
    //   122: invokestatic 282	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   125: aload 9
    //   127: ifnull +23 -> 150
    //   130: aload 9
    //   132: invokevirtual 190	java/lang/String:length	()I
    //   135: bipush 16
    //   137: if_icmple +13 -> 150
    //   140: aload 9
    //   142: iconst_0
    //   143: bipush 16
    //   145: invokevirtual 1036	java/lang/String:substring	(II)Ljava/lang/String;
    //   148: astore 9
    //   150: aload_0
    //   151: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   154: invokevirtual 860	oicq/wlogin_sdk/request/request_global:get_ping_end_flag	()I
    //   157: ifeq +532 -> 689
    //   160: aload_0
    //   161: monitorenter
    //   162: aload_0
    //   163: getfield 136	oicq/wlogin_sdk/request/WtloginHelper:mContext	Landroid/content/Context;
    //   166: invokestatic 238	oicq/wlogin_sdk/tools/util:get_saved_network_type	(Landroid/content/Context;)I
    //   169: istore 20
    //   171: aload_0
    //   172: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   175: aload_0
    //   176: getfield 136	oicq/wlogin_sdk/request/WtloginHelper:mContext	Landroid/content/Context;
    //   179: invokestatic 863	oicq/wlogin_sdk/tools/util:get_network_type	(Landroid/content/Context;)I
    //   182: putfield 269	oicq/wlogin_sdk/request/request_global:_network_type	I
    //   185: iload 20
    //   187: aload_0
    //   188: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   191: getfield 269	oicq/wlogin_sdk/request/request_global:_network_type	I
    //   194: if_icmpeq +25 -> 219
    //   197: aload_0
    //   198: getfield 136	oicq/wlogin_sdk/request/WtloginHelper:mContext	Landroid/content/Context;
    //   201: iconst_0
    //   202: invokestatic 866	oicq/wlogin_sdk/tools/util:set_net_retry_type	(Landroid/content/Context;I)V
    //   205: aload_0
    //   206: getfield 136	oicq/wlogin_sdk/request/WtloginHelper:mContext	Landroid/content/Context;
    //   209: aload_0
    //   210: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   213: getfield 269	oicq/wlogin_sdk/request/request_global:_network_type	I
    //   216: invokestatic 869	oicq/wlogin_sdk/tools/util:save_network_type	(Landroid/content/Context;I)V
    //   219: aload_0
    //   220: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   223: aload_0
    //   224: getfield 136	oicq/wlogin_sdk/request/WtloginHelper:mContext	Landroid/content/Context;
    //   227: invokestatic 873	oicq/wlogin_sdk/tools/util:get_apn_string	(Landroid/content/Context;)Ljava/lang/String;
    //   230: invokevirtual 391	java/lang/String:getBytes	()[B
    //   233: putfield 876	oicq/wlogin_sdk/request/request_global:_apn	[B
    //   236: aload_0
    //   237: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   240: lload_2
    //   241: putfield 482	oicq/wlogin_sdk/request/request_global:_appid	J
    //   244: aload_0
    //   245: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   248: aload_1
    //   249: putfield 452	oicq/wlogin_sdk/request/request_global:_name	Ljava/lang/String;
    //   252: aload_0
    //   253: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   256: lconst_0
    //   257: putfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   260: aload_0
    //   261: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   264: aconst_null
    //   265: putfield 517	oicq/wlogin_sdk/request/request_global:_sub_appid_list	[J
    //   268: aload_0
    //   269: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   272: lload 5
    //   274: putfield 539	oicq/wlogin_sdk/request/request_global:_sub_appid	J
    //   277: aload_0
    //   278: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   281: iload 13
    //   283: putfield 536	oicq/wlogin_sdk/request/request_global:_main_sigmap	I
    //   286: aload_0
    //   287: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   290: iconst_0
    //   291: putfield 1039	oicq/wlogin_sdk/request/request_global:_getuin_need_image	I
    //   294: aload_0
    //   295: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   298: aload_1
    //   299: putfield 1042	oicq/wlogin_sdk/request/request_global:_last_login_account	Ljava/lang/String;
    //   302: aload_0
    //   303: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   306: invokevirtual 1045	oicq/wlogin_sdk/request/request_global:clear_sig_session	()V
    //   309: aload 7
    //   311: ifnull +18 -> 329
    //   314: aload_0
    //   315: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   318: aload 7
    //   320: invokevirtual 881	[J:clone	()Ljava/lang/Object;
    //   323: checkcast 880	[J
    //   326: putfield 517	oicq/wlogin_sdk/request/request_global:_sub_appid_list	[J
    //   329: aload_0
    //   330: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   333: getfield 448	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   336: new 883	oicq/wlogin_sdk/report/report_t2
    //   339: dup
    //   340: ldc_w 1047
    //   343: new 186	java/lang/String
    //   346: dup
    //   347: aload_0
    //   348: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   351: getfield 888	oicq/wlogin_sdk/request/request_global:_sim_operator_name	[B
    //   354: invokespecial 891	java/lang/String:<init>	([B)V
    //   357: invokestatic 554	java/lang/System:currentTimeMillis	()J
    //   360: lload_2
    //   361: lload 5
    //   363: aload 7
    //   365: invokespecial 894	oicq/wlogin_sdk/report/report_t2:<init>	(Ljava/lang/String;Ljava/lang/String;JJJ[J)V
    //   368: invokevirtual 898	oicq/wlogin_sdk/report/report_t1:add_t2	(Loicq/wlogin_sdk/report/report_t2;)V
    //   371: aload_0
    //   372: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   375: getfield 1050	oicq/wlogin_sdk/request/request_global:_init_time	[B
    //   378: ifnonnull +329 -> 707
    //   381: aload_0
    //   382: getfield 68	oicq/wlogin_sdk/request/WtloginHelper:mPing	Loicq/wlogin_sdk/request/request_ping;
    //   385: lconst_0
    //   386: lload_2
    //   387: invokevirtual 1053	oicq/wlogin_sdk/request/request_ping:make_request	(JJ)I
    //   390: istore 38
    //   392: iload 38
    //   394: istore 23
    //   396: new 251	java/lang/StringBuilder
    //   399: dup
    //   400: ldc_w 1055
    //   403: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   406: iload 23
    //   408: invokevirtual 264	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   411: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   414: invokestatic 179	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   417: iload 23
    //   419: ifeq +1262 -> 1681
    //   422: iload 23
    //   424: ifne +1182 -> 1606
    //   427: aload_0
    //   428: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   431: getfield 448	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   434: aload_0
    //   435: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   438: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   441: aload_0
    //   442: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   445: getfield 452	oicq/wlogin_sdk/request/request_global:_name	Ljava/lang/String;
    //   448: iload 23
    //   450: aload_0
    //   451: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   454: getfield 456	oicq/wlogin_sdk/request/request_global:_last_err_msg	Loicq/wlogin_sdk/tools/ErrMsg;
    //   457: invokevirtual 461	oicq/wlogin_sdk/tools/ErrMsg:getMessage	()Ljava/lang/String;
    //   460: invokestatic 465	oicq/wlogin_sdk/tools/util:format_ret_code	(ILjava/lang/String;)I
    //   463: iload 23
    //   465: invokevirtual 471	oicq/wlogin_sdk/report/report_t1:commit_t2	(JLjava/lang/String;II)V
    //   468: aload 10
    //   470: getfield 476	oicq/wlogin_sdk/request/WUserSigInfo:_userStSig	[B
    //   473: ifnull +42 -> 515
    //   476: aload 10
    //   478: getfield 476	oicq/wlogin_sdk/request/WUserSigInfo:_userStSig	[B
    //   481: arraylength
    //   482: ifeq +33 -> 515
    //   485: aload_0
    //   486: iconst_0
    //   487: aload 10
    //   489: getfield 476	oicq/wlogin_sdk/request/WUserSigInfo:_userStSig	[B
    //   492: aload 10
    //   494: getfield 479	oicq/wlogin_sdk/request/WUserSigInfo:_userSt_Key	[B
    //   497: aload_0
    //   498: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   501: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   504: aload_0
    //   505: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   508: getfield 482	oicq/wlogin_sdk/request/request_global:_appid	J
    //   511: invokespecial 337	oicq/wlogin_sdk/request/WtloginHelper:RequestReport	(I[B[BJJ)I
    //   514: pop
    //   515: aload_0
    //   516: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   519: getfield 486	oicq/wlogin_sdk/request/request_global:_t150	Loicq/wlogin_sdk/tlv_type/tlv_t150;
    //   522: ifnull +47 -> 569
    //   525: aload_0
    //   526: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   529: getfield 486	oicq/wlogin_sdk/request/request_global:_t150	Loicq/wlogin_sdk/tlv_type/tlv_t150;
    //   532: invokevirtual 491	oicq/wlogin_sdk/tlv_type/tlv_t150:get_bitmap	()I
    //   535: ifeq +34 -> 569
    //   538: aload_0
    //   539: iconst_0
    //   540: aload 10
    //   542: getfield 476	oicq/wlogin_sdk/request/WUserSigInfo:_userStSig	[B
    //   545: aload 10
    //   547: getfield 479	oicq/wlogin_sdk/request/WUserSigInfo:_userSt_Key	[B
    //   550: aload_0
    //   551: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   554: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   557: aload_0
    //   558: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   561: getfield 482	oicq/wlogin_sdk/request/request_global:_appid	J
    //   564: iconst_1
    //   565: invokespecial 341	oicq/wlogin_sdk/request/WtloginHelper:RequestReportError	(I[B[BJJI)I
    //   568: pop
    //   569: new 493	oicq/wlogin_sdk/request/delete_expire_log
    //   572: dup
    //   573: aload_0
    //   574: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   577: getfield 441	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   580: invokespecial 494	oicq/wlogin_sdk/request/delete_expire_log:<init>	(Landroid/content/Context;)V
    //   583: invokevirtual 497	oicq/wlogin_sdk/request/delete_expire_log:start	()V
    //   586: aload_0
    //   587: monitorexit
    //   588: aload_0
    //   589: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   592: invokevirtual 500	oicq/wlogin_sdk/request/request_global:close_connect	()V
    //   595: new 251	java/lang/StringBuilder
    //   598: dup
    //   599: ldc_w 1032
    //   602: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   605: aload_1
    //   606: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   609: ldc_w 614
    //   612: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   615: lload_2
    //   616: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   619: ldc_w 853
    //   622: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   625: iload 13
    //   627: invokevirtual 264	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   630: ldc_w 855
    //   633: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   636: lload 5
    //   638: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   641: ldc_w 921
    //   644: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   647: iload 23
    //   649: invokevirtual 264	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   652: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   655: aload_0
    //   656: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   659: getfield 441	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   662: new 251	java/lang/StringBuilder
    //   665: dup
    //   666: invokespecial 503	java/lang/StringBuilder:<init>	()V
    //   669: aload_0
    //   670: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   673: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   676: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   679: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   682: iconst_1
    //   683: invokestatic 282	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   686: iload 23
    //   688: ireturn
    //   689: invokestatic 925	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   692: pop
    //   693: ldc2_w 926
    //   696: invokestatic 309	java/lang/Thread:sleep	(J)V
    //   699: goto -549 -> 150
    //   702: astore 16
    //   704: goto -554 -> 150
    //   707: ldc_w 1057
    //   710: invokestatic 179	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   713: aload_1
    //   714: invokestatic 200	oicq/wlogin_sdk/tools/util:check_uin_account	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   717: invokevirtual 204	java/lang/Boolean:booleanValue	()Z
    //   720: ifne +95 -> 815
    //   723: aload_0
    //   724: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   727: aload_1
    //   728: invokevirtual 208	oicq/wlogin_sdk/request/request_global:get_account	(Ljava/lang/String;)Ljava/lang/Long;
    //   731: astore 21
    //   733: aload 21
    //   735: ifnonnull +6 -> 741
    //   738: iconst_0
    //   739: istore 15
    //   741: aload 9
    //   743: ifnull +109 -> 852
    //   746: aload 9
    //   748: invokevirtual 190	java/lang/String:length	()I
    //   751: istore 22
    //   753: iload 22
    //   755: ifle +97 -> 852
    //   758: iload 8
    //   760: ifeq +67 -> 827
    //   763: aload_0
    //   764: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   767: aload 9
    //   769: ldc_w 1059
    //   772: invokevirtual 1061	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   775: invokevirtual 523	[B:clone	()Ljava/lang/Object;
    //   778: checkcast 519	[B
    //   781: putfield 533	oicq/wlogin_sdk/request/request_global:_tmp_pwd	[B
    //   784: aload_0
    //   785: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   788: iconst_0
    //   789: putfield 509	oicq/wlogin_sdk/request/request_global:_tmp_pwd_type	I
    //   792: iload 15
    //   794: ifne +265 -> 1059
    //   797: aload_1
    //   798: invokevirtual 190	java/lang/String:length	()I
    //   801: getstatic 1064	oicq/wlogin_sdk/tools/util:MAX_NAME_LEN	I
    //   804: if_icmple +167 -> 971
    //   807: sipush -1008
    //   810: istore 23
    //   812: goto -390 -> 422
    //   815: aload_1
    //   816: invokestatic 224	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   819: invokestatic 213	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   822: astore 21
    //   824: goto -83 -> 741
    //   827: aload_0
    //   828: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   831: aload 9
    //   833: invokestatic 1069	oicq/wlogin_sdk/tools/MD5:toMD5Byte	(Ljava/lang/String;)[B
    //   836: putfield 533	oicq/wlogin_sdk/request/request_global:_tmp_pwd	[B
    //   839: goto -55 -> 784
    //   842: astore 18
    //   844: iload 14
    //   846: pop
    //   847: aload_0
    //   848: monitorexit
    //   849: aload 18
    //   851: athrow
    //   852: aload_0
    //   853: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   856: aload_0
    //   857: aload_1
    //   858: lload_2
    //   859: invokevirtual 1071	oicq/wlogin_sdk/request/WtloginHelper:GetA1ByAccount	(Ljava/lang/String;J)[B
    //   862: putfield 533	oicq/wlogin_sdk/request/request_global:_tmp_pwd	[B
    //   865: aload_0
    //   866: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   869: getfield 533	oicq/wlogin_sdk/request/request_global:_tmp_pwd	[B
    //   872: ifnull +14 -> 886
    //   875: aload_0
    //   876: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   879: getfield 533	oicq/wlogin_sdk/request/request_global:_tmp_pwd	[B
    //   882: arraylength
    //   883: ifgt +38 -> 921
    //   886: aload 10
    //   888: getfield 1074	oicq/wlogin_sdk/request/WUserSigInfo:_userPasswdSig	[B
    //   891: ifnull +56 -> 947
    //   894: aload 10
    //   896: getfield 1074	oicq/wlogin_sdk/request/WUserSigInfo:_userPasswdSig	[B
    //   899: arraylength
    //   900: ifle +47 -> 947
    //   903: aload_0
    //   904: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   907: aload 10
    //   909: getfield 1074	oicq/wlogin_sdk/request/WUserSigInfo:_userPasswdSig	[B
    //   912: invokevirtual 523	[B:clone	()Ljava/lang/Object;
    //   915: checkcast 519	[B
    //   918: putfield 533	oicq/wlogin_sdk/request/request_global:_tmp_pwd	[B
    //   921: aload_0
    //   922: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   925: getfield 533	oicq/wlogin_sdk/request/request_global:_tmp_pwd	[B
    //   928: ifnull +770 -> 1698
    //   931: aload_0
    //   932: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   935: getfield 533	oicq/wlogin_sdk/request/request_global:_tmp_pwd	[B
    //   938: arraylength
    //   939: bipush 16
    //   941: if_icmpge +19 -> 960
    //   944: goto +754 -> 1698
    //   947: aload_0
    //   948: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   951: iconst_0
    //   952: newarray byte
    //   954: putfield 533	oicq/wlogin_sdk/request/request_global:_tmp_pwd	[B
    //   957: goto -36 -> 921
    //   960: aload_0
    //   961: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   964: iconst_1
    //   965: putfield 509	oicq/wlogin_sdk/request/request_global:_tmp_pwd_type	I
    //   968: goto -176 -> 792
    //   971: aload_0
    //   972: getfield 88	oicq/wlogin_sdk/request/WtloginHelper:mGetuin	Loicq/wlogin_sdk/request/request_getuin;
    //   975: lload_2
    //   976: lload 5
    //   978: iconst_1
    //   979: iload 13
    //   981: aload_1
    //   982: invokevirtual 391	java/lang/String:getBytes	()[B
    //   985: iconst_0
    //   986: iconst_0
    //   987: iconst_0
    //   988: iconst_1
    //   989: aload_0
    //   990: getfield 156	oicq/wlogin_sdk/request/WtloginHelper:mTGTGTMiscBitmap	I
    //   993: aload_0
    //   994: getfield 154	oicq/wlogin_sdk/request/WtloginHelper:mSubSigMap	I
    //   997: aload 7
    //   999: invokevirtual 1077	oicq/wlogin_sdk/request/request_getuin:make_request	(JJII[BIIIIII[J)I
    //   1002: istore 27
    //   1004: iload 27
    //   1006: istore 23
    //   1008: iload 23
    //   1010: iconst_3
    //   1011: if_icmpne +12 -> 1023
    //   1014: aload_0
    //   1015: getfield 113	oicq/wlogin_sdk/request/WtloginHelper:mDelay	Loicq/wlogin_sdk/request/request_delay;
    //   1018: invokevirtual 1079	oicq/wlogin_sdk/request/request_delay:make_request	()I
    //   1021: istore 23
    //   1023: iload 23
    //   1025: ifne -603 -> 422
    //   1028: aload_0
    //   1029: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1032: aload_1
    //   1033: invokevirtual 208	oicq/wlogin_sdk/request/request_global:get_account	(Ljava/lang/String;)Ljava/lang/Long;
    //   1036: astore 28
    //   1038: aload 28
    //   1040: astore 21
    //   1042: aload 21
    //   1044: ifnonnull +11 -> 1055
    //   1047: sipush -1003
    //   1050: istore 23
    //   1052: goto -630 -> 422
    //   1055: iload 23
    //   1057: istore 14
    //   1059: aload_0
    //   1060: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1063: aload 21
    //   1065: invokevirtual 234	java/lang/Long:longValue	()J
    //   1068: putfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1071: new 251	java/lang/StringBuilder
    //   1074: dup
    //   1075: ldc_w 1081
    //   1078: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1081: new 210	java/lang/Long
    //   1084: dup
    //   1085: aload_0
    //   1086: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1089: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1092: invokespecial 780	java/lang/Long:<init>	(J)V
    //   1095: invokevirtual 781	java/lang/Long:toString	()Ljava/lang/String;
    //   1098: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1101: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1104: invokestatic 179	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   1107: aload 10
    //   1109: getfield 527	oicq/wlogin_sdk/request/WUserSigInfo:_in_ksid	[B
    //   1112: ifnull +197 -> 1309
    //   1115: aload 10
    //   1117: getfield 527	oicq/wlogin_sdk/request/WUserSigInfo:_in_ksid	[B
    //   1120: arraylength
    //   1121: ifle +188 -> 1309
    //   1124: aload 10
    //   1126: getfield 527	oicq/wlogin_sdk/request/WUserSigInfo:_in_ksid	[B
    //   1129: invokevirtual 523	[B:clone	()Ljava/lang/Object;
    //   1132: checkcast 519	[B
    //   1135: astore 29
    //   1137: aload_0
    //   1138: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1141: getfield 509	oicq/wlogin_sdk/request/request_global:_tmp_pwd_type	I
    //   1144: ifeq +177 -> 1321
    //   1147: new 251	java/lang/StringBuilder
    //   1150: dup
    //   1151: ldc_w 436
    //   1154: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1157: aload_1
    //   1158: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1161: ldc_w 1083
    //   1164: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1167: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1170: aload_0
    //   1171: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1174: getfield 441	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   1177: new 251	java/lang/StringBuilder
    //   1180: dup
    //   1181: invokespecial 503	java/lang/StringBuilder:<init>	()V
    //   1184: aload_0
    //   1185: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1188: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1191: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1194: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1197: iconst_0
    //   1198: invokestatic 282	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   1201: aload_0
    //   1202: getfield 73	oicq/wlogin_sdk/request/WtloginHelper:mTgtgt	Loicq/wlogin_sdk/request/request_TGTGT;
    //   1205: lload_2
    //   1206: iconst_1
    //   1207: aload_0
    //   1208: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1211: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1214: iconst_0
    //   1215: aload_0
    //   1216: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1219: getfield 530	oicq/wlogin_sdk/request/request_global:_ip_addr	[B
    //   1222: aload_0
    //   1223: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1226: getfield 533	oicq/wlogin_sdk/request/request_global:_tmp_pwd	[B
    //   1229: aload_0
    //   1230: getfield 156	oicq/wlogin_sdk/request/WtloginHelper:mTGTGTMiscBitmap	I
    //   1233: aload_0
    //   1234: getfield 154	oicq/wlogin_sdk/request/WtloginHelper:mSubSigMap	I
    //   1237: aload 7
    //   1239: iload 13
    //   1241: lload 5
    //   1243: iconst_1
    //   1244: iconst_0
    //   1245: iconst_1
    //   1246: ldc_w 540
    //   1249: iconst_1
    //   1250: aload 29
    //   1252: invokevirtual 543	oicq/wlogin_sdk/request/request_TGTGT:make_request	(JIJI[B[BII[JIJIIIII[B)I
    //   1255: istore 36
    //   1257: iload 36
    //   1259: istore 23
    //   1261: iload 23
    //   1263: iconst_3
    //   1264: if_icmpne +12 -> 1276
    //   1267: aload_0
    //   1268: getfield 113	oicq/wlogin_sdk/request/WtloginHelper:mDelay	Loicq/wlogin_sdk/request/request_delay;
    //   1271: invokevirtual 1079	oicq/wlogin_sdk/request/request_delay:make_request	()I
    //   1274: istore 23
    //   1276: iload 23
    //   1278: ifne -856 -> 422
    //   1281: aload_0
    //   1282: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1285: aload 21
    //   1287: invokevirtual 234	java/lang/Long:longValue	()J
    //   1290: lload_2
    //   1291: invokevirtual 229	oicq/wlogin_sdk/request/request_global:get_siginfo	(JJ)Loicq/wlogin_sdk/sharemem/WloginSigInfo;
    //   1294: astore 32
    //   1296: aload 32
    //   1298: ifnonnull +165 -> 1463
    //   1301: sipush -1004
    //   1304: istore 23
    //   1306: goto -884 -> 422
    //   1309: aload_0
    //   1310: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1313: getfield 549	oicq/wlogin_sdk/request/request_global:_ksid	[B
    //   1316: astore 29
    //   1318: goto -181 -> 1137
    //   1321: new 251	java/lang/StringBuilder
    //   1324: dup
    //   1325: ldc_w 436
    //   1328: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1331: aload_1
    //   1332: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1335: ldc_w 1085
    //   1338: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1341: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1344: aload_0
    //   1345: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1348: getfield 441	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   1351: new 251	java/lang/StringBuilder
    //   1354: dup
    //   1355: invokespecial 503	java/lang/StringBuilder:<init>	()V
    //   1358: aload_0
    //   1359: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1362: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1365: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1368: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1371: iconst_0
    //   1372: invokestatic 282	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   1375: iconst_4
    //   1376: newarray byte
    //   1378: astore 30
    //   1380: aload 30
    //   1382: iconst_0
    //   1383: invokestatic 554	java/lang/System:currentTimeMillis	()J
    //   1386: ldc2_w 555
    //   1389: ldiv
    //   1390: getstatic 559	oicq/wlogin_sdk/request/request_global:_l_init_time	J
    //   1393: ladd
    //   1394: invokestatic 563	oicq/wlogin_sdk/tools/util:int64_to_buf32	([BIJ)V
    //   1397: aload_0
    //   1398: getfield 73	oicq/wlogin_sdk/request/WtloginHelper:mTgtgt	Loicq/wlogin_sdk/request/request_TGTGT;
    //   1401: lload_2
    //   1402: iconst_1
    //   1403: aload_0
    //   1404: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1407: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1410: iconst_0
    //   1411: aload_0
    //   1412: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1415: getfield 530	oicq/wlogin_sdk/request/request_global:_ip_addr	[B
    //   1418: aload 30
    //   1420: iconst_0
    //   1421: aload_0
    //   1422: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1425: getfield 533	oicq/wlogin_sdk/request/request_global:_tmp_pwd	[B
    //   1428: aload_0
    //   1429: getfield 156	oicq/wlogin_sdk/request/WtloginHelper:mTGTGTMiscBitmap	I
    //   1432: aload_0
    //   1433: getfield 154	oicq/wlogin_sdk/request/WtloginHelper:mSubSigMap	I
    //   1436: aload 7
    //   1438: iload 13
    //   1440: lload 5
    //   1442: iconst_1
    //   1443: iconst_0
    //   1444: iconst_1
    //   1445: ldc_w 540
    //   1448: iconst_1
    //   1449: aload 29
    //   1451: invokevirtual 566	oicq/wlogin_sdk/request/request_TGTGT:make_request	(JIJI[B[BI[BII[JIJIIIII[B)I
    //   1454: istore 31
    //   1456: iload 31
    //   1458: istore 23
    //   1460: goto -199 -> 1261
    //   1463: aload_0
    //   1464: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1467: getfield 509	oicq/wlogin_sdk/request/request_global:_tmp_pwd_type	I
    //   1470: ifeq +61 -> 1531
    //   1473: aload 10
    //   1475: aload 32
    //   1477: invokevirtual 513	oicq/wlogin_sdk/request/WUserSigInfo:get_clone	(Loicq/wlogin_sdk/sharemem/WloginSigInfo;)V
    //   1480: aload 7
    //   1482: ifnull +39 -> 1521
    //   1485: aload 11
    //   1487: ifnull +34 -> 1521
    //   1490: iconst_2
    //   1491: aload 7
    //   1493: arraylength
    //   1494: imul
    //   1495: aload 11
    //   1497: arraylength
    //   1498: if_icmpne +23 -> 1521
    //   1501: iconst_0
    //   1502: istore 33
    //   1504: aload 7
    //   1506: ifnull +15 -> 1521
    //   1509: aload 7
    //   1511: arraylength
    //   1512: istore 34
    //   1514: iload 33
    //   1516: iload 34
    //   1518: if_icmplt +23 -> 1541
    //   1521: aload_0
    //   1522: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1525: invokevirtual 546	oicq/wlogin_sdk/request/request_global:clear_tmp_pwd	()V
    //   1528: goto -1106 -> 422
    //   1531: aload 10
    //   1533: aload 32
    //   1535: invokevirtual 513	oicq/wlogin_sdk/request/WUserSigInfo:get_clone	(Loicq/wlogin_sdk/sharemem/WloginSigInfo;)V
    //   1538: goto -58 -> 1480
    //   1541: aload_0
    //   1542: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1545: aload 21
    //   1547: invokevirtual 234	java/lang/Long:longValue	()J
    //   1550: aload 7
    //   1552: iload 33
    //   1554: laload
    //   1555: invokevirtual 229	oicq/wlogin_sdk/request/request_global:get_siginfo	(JJ)Loicq/wlogin_sdk/sharemem/WloginSigInfo;
    //   1558: astore 35
    //   1560: aload 35
    //   1562: ifnull +149 -> 1711
    //   1565: aload 11
    //   1567: iload 33
    //   1569: iconst_2
    //   1570: imul
    //   1571: aload 35
    //   1573: getfield 518	oicq/wlogin_sdk/sharemem/WloginSigInfo:_userSt_Key	[B
    //   1576: invokevirtual 523	[B:clone	()Ljava/lang/Object;
    //   1579: checkcast 519	[B
    //   1582: aastore
    //   1583: aload 11
    //   1585: iconst_1
    //   1586: iload 33
    //   1588: iconst_2
    //   1589: imul
    //   1590: iadd
    //   1591: aload 35
    //   1593: getfield 524	oicq/wlogin_sdk/sharemem/WloginSigInfo:_userStSig	[B
    //   1596: invokevirtual 523	[B:clone	()Ljava/lang/Object;
    //   1599: checkcast 519	[B
    //   1602: aastore
    //   1603: goto +108 -> 1711
    //   1606: aload_0
    //   1607: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1610: getfield 448	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   1613: aload_0
    //   1614: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1617: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1620: aload_0
    //   1621: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1624: getfield 452	oicq/wlogin_sdk/request/request_global:_name	Ljava/lang/String;
    //   1627: iload 23
    //   1629: aload_0
    //   1630: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1633: getfield 456	oicq/wlogin_sdk/request/request_global:_last_err_msg	Loicq/wlogin_sdk/tools/ErrMsg;
    //   1636: invokevirtual 461	oicq/wlogin_sdk/tools/ErrMsg:getMessage	()Ljava/lang/String;
    //   1639: invokestatic 465	oicq/wlogin_sdk/tools/util:format_ret_code	(ILjava/lang/String;)I
    //   1642: iload 23
    //   1644: invokevirtual 471	oicq/wlogin_sdk/report/report_t1:commit_t2	(JLjava/lang/String;II)V
    //   1647: aload_0
    //   1648: iconst_0
    //   1649: aload 10
    //   1651: getfield 476	oicq/wlogin_sdk/request/WUserSigInfo:_userStSig	[B
    //   1654: aload 10
    //   1656: getfield 479	oicq/wlogin_sdk/request/WUserSigInfo:_userSt_Key	[B
    //   1659: aload_0
    //   1660: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1663: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1666: aload_0
    //   1667: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1670: getfield 482	oicq/wlogin_sdk/request/request_global:_appid	J
    //   1673: iconst_0
    //   1674: invokespecial 341	oicq/wlogin_sdk/request/WtloginHelper:RequestReportError	(I[B[BJJI)I
    //   1677: pop
    //   1678: goto -1163 -> 515
    //   1681: iload 23
    //   1683: istore 14
    //   1685: goto -972 -> 713
    //   1688: astore 37
    //   1690: sipush -1013
    //   1693: istore 23
    //   1695: goto -1273 -> 422
    //   1698: sipush -1016
    //   1701: istore 23
    //   1703: goto -1281 -> 422
    //   1706: astore 18
    //   1708: goto -861 -> 847
    //   1711: iinc 33 1
    //   1714: goto -210 -> 1504
    //
    // Exception table:
    //   from	to	target	type
    //   689	699	702	java/lang/Exception
    //   162	219	842	finally
    //   219	309	842	finally
    //   314	329	842	finally
    //   329	392	842	finally
    //   707	713	842	finally
    //   713	733	842	finally
    //   746	753	842	finally
    //   763	784	842	finally
    //   784	792	842	finally
    //   797	807	842	finally
    //   815	824	842	finally
    //   827	839	842	finally
    //   852	886	842	finally
    //   886	921	842	finally
    //   921	944	842	finally
    //   947	957	842	finally
    //   960	968	842	finally
    //   971	1004	842	finally
    //   1059	1137	842	finally
    //   1137	1257	842	finally
    //   1309	1318	842	finally
    //   1321	1456	842	finally
    //   763	784	1688	java/lang/Exception
    //   396	417	1706	finally
    //   427	515	1706	finally
    //   515	569	1706	finally
    //   569	588	1706	finally
    //   847	849	1706	finally
    //   1014	1023	1706	finally
    //   1028	1038	1706	finally
    //   1267	1276	1706	finally
    //   1281	1296	1706	finally
    //   1463	1480	1706	finally
    //   1490	1501	1706	finally
    //   1509	1514	1706	finally
    //   1521	1528	1706	finally
    //   1531	1538	1706	finally
    //   1541	1560	1706	finally
    //   1565	1603	1706	finally
    //   1606	1678	1706	finally
  }

  public int GetStWithPasswd(String paramString1, long paramLong, String paramString2, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    return GetStWithPasswd(paramString1, paramLong, this.mMainSigMap, 1L, null, false, paramString2, paramWUserSigInfo, null, paramInt);
  }

  public int GetStWithPasswdMd5(String paramString1, long paramLong, String paramString2, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    return GetStWithPasswd(paramString1, paramLong, this.mMainSigMap, 1L, null, true, paramString2, paramWUserSigInfo, null, paramInt);
  }

  // ERROR //
  public int GetStWithoutPasswd(String paramString, long paramLong1, long paramLong2, long paramLong3, int paramInt1, long paramLong4, long[] paramArrayOfLong, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte1, byte[][] paramArrayOfByte2, int paramInt2)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 16
    //   3: iload 8
    //   5: sipush 192
    //   8: ior
    //   9: istore 17
    //   11: iload 15
    //   13: ifne +44 -> 57
    //   16: new 288	oicq/wlogin_sdk/request/WtloginHelper$HelperThread
    //   19: dup
    //   20: aload_0
    //   21: aload_0
    //   22: aload_0
    //   23: getfield 134	oicq/wlogin_sdk/request/WtloginHelper:mHelperHandler	Loicq/wlogin_sdk/request/WtloginHelper$HelperHandler;
    //   26: aload_1
    //   27: lload_2
    //   28: lload 4
    //   30: lload 6
    //   32: iload 17
    //   34: lload 9
    //   36: aload 11
    //   38: aload 12
    //   40: aload 13
    //   42: aload 14
    //   44: invokespecial 840	oicq/wlogin_sdk/request/WtloginHelper$HelperThread:<init>	(Loicq/wlogin_sdk/request/WtloginHelper;Loicq/wlogin_sdk/request/WtloginHelper;Landroid/os/Handler;Ljava/lang/String;JJJIJ[JLoicq/wlogin_sdk/request/WUserSigInfo;[[B[[B)V
    //   47: getstatic 1089	oicq/wlogin_sdk/tools/util:ASYN_GET_ST_WITHOUT_PWD	I
    //   50: invokevirtual 298	oicq/wlogin_sdk/request/WtloginHelper$HelperThread:RunReq	(I)V
    //   53: sipush -1001
    //   56: ireturn
    //   57: new 251	java/lang/StringBuilder
    //   60: dup
    //   61: ldc_w 1091
    //   64: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   67: aload_1
    //   68: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: ldc_w 847
    //   74: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: lload_2
    //   78: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   81: ldc_w 849
    //   84: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: lload 4
    //   89: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   92: ldc_w 851
    //   95: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: lload 6
    //   100: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   103: ldc_w 853
    //   106: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: iload 17
    //   111: invokevirtual 264	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   114: ldc_w 855
    //   117: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: lload 9
    //   122: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   125: ldc_w 857
    //   128: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: aload_0
    //   135: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   138: getfield 441	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   141: aload_1
    //   142: iconst_0
    //   143: invokestatic 282	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   146: aload_0
    //   147: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   150: invokevirtual 860	oicq/wlogin_sdk/request/request_global:get_ping_end_flag	()I
    //   153: ifeq +558 -> 711
    //   156: aload_0
    //   157: monitorenter
    //   158: iconst_0
    //   159: istore 20
    //   161: aload_0
    //   162: getfield 136	oicq/wlogin_sdk/request/WtloginHelper:mContext	Landroid/content/Context;
    //   165: invokestatic 238	oicq/wlogin_sdk/tools/util:get_saved_network_type	(Landroid/content/Context;)I
    //   168: istore 23
    //   170: aload_0
    //   171: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   174: aload_0
    //   175: getfield 136	oicq/wlogin_sdk/request/WtloginHelper:mContext	Landroid/content/Context;
    //   178: invokestatic 863	oicq/wlogin_sdk/tools/util:get_network_type	(Landroid/content/Context;)I
    //   181: putfield 269	oicq/wlogin_sdk/request/request_global:_network_type	I
    //   184: iload 23
    //   186: aload_0
    //   187: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   190: getfield 269	oicq/wlogin_sdk/request/request_global:_network_type	I
    //   193: if_icmpeq +25 -> 218
    //   196: aload_0
    //   197: getfield 136	oicq/wlogin_sdk/request/WtloginHelper:mContext	Landroid/content/Context;
    //   200: iconst_0
    //   201: invokestatic 866	oicq/wlogin_sdk/tools/util:set_net_retry_type	(Landroid/content/Context;I)V
    //   204: aload_0
    //   205: getfield 136	oicq/wlogin_sdk/request/WtloginHelper:mContext	Landroid/content/Context;
    //   208: aload_0
    //   209: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   212: getfield 269	oicq/wlogin_sdk/request/request_global:_network_type	I
    //   215: invokestatic 869	oicq/wlogin_sdk/tools/util:save_network_type	(Landroid/content/Context;I)V
    //   218: aload_0
    //   219: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   222: aload_0
    //   223: getfield 136	oicq/wlogin_sdk/request/WtloginHelper:mContext	Landroid/content/Context;
    //   226: invokestatic 873	oicq/wlogin_sdk/tools/util:get_apn_string	(Landroid/content/Context;)Ljava/lang/String;
    //   229: invokevirtual 391	java/lang/String:getBytes	()[B
    //   232: putfield 876	oicq/wlogin_sdk/request/request_global:_apn	[B
    //   235: aload_0
    //   236: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   239: aload_1
    //   240: putfield 452	oicq/wlogin_sdk/request/request_global:_name	Ljava/lang/String;
    //   243: aload_0
    //   244: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   247: lconst_0
    //   248: putfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   251: aload_0
    //   252: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   255: lload 4
    //   257: putfield 482	oicq/wlogin_sdk/request/request_global:_appid	J
    //   260: aload_0
    //   261: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   264: lload 9
    //   266: putfield 539	oicq/wlogin_sdk/request/request_global:_sub_appid	J
    //   269: aload_0
    //   270: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   273: iload 17
    //   275: putfield 536	oicq/wlogin_sdk/request/request_global:_main_sigmap	I
    //   278: aload_0
    //   279: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   282: aconst_null
    //   283: putfield 517	oicq/wlogin_sdk/request/request_global:_sub_appid_list	[J
    //   286: aload_0
    //   287: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   290: iconst_0
    //   291: newarray byte
    //   293: putfield 879	oicq/wlogin_sdk/request/request_global:_encrypt_a1	[B
    //   296: aload 11
    //   298: ifnull +18 -> 316
    //   301: aload_0
    //   302: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   305: aload 11
    //   307: invokevirtual 881	[J:clone	()Ljava/lang/Object;
    //   310: checkcast 880	[J
    //   313: putfield 517	oicq/wlogin_sdk/request/request_global:_sub_appid_list	[J
    //   316: aload_1
    //   317: invokestatic 200	oicq/wlogin_sdk/tools/util:check_uin_account	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   320: invokevirtual 204	java/lang/Boolean:booleanValue	()Z
    //   323: istore 24
    //   325: iconst_0
    //   326: istore 16
    //   328: iload 24
    //   330: ifne +399 -> 729
    //   333: new 251	java/lang/StringBuilder
    //   336: dup
    //   337: ldc_w 1093
    //   340: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   343: aload_1
    //   344: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   347: ldc_w 785
    //   350: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   353: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   356: invokestatic 179	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   359: aload_0
    //   360: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   363: aload_1
    //   364: invokevirtual 208	oicq/wlogin_sdk/request/request_global:get_account	(Ljava/lang/String;)Ljava/lang/Long;
    //   367: astore 25
    //   369: iconst_0
    //   370: istore 16
    //   372: aload 25
    //   374: ifnonnull +390 -> 764
    //   377: new 251	java/lang/StringBuilder
    //   380: dup
    //   381: ldc_w 436
    //   384: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   387: aload_1
    //   388: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   391: ldc_w 1095
    //   394: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   397: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   400: aload_0
    //   401: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   404: getfield 441	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   407: aload_1
    //   408: iconst_0
    //   409: invokestatic 282	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   412: sipush -1003
    //   415: istore 26
    //   417: iload 20
    //   419: ifeq +96 -> 515
    //   422: iload 26
    //   424: ifne +1802 -> 2226
    //   427: aload_0
    //   428: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   431: getfield 448	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   434: aload_0
    //   435: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   438: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   441: aload_0
    //   442: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   445: getfield 452	oicq/wlogin_sdk/request/request_global:_name	Ljava/lang/String;
    //   448: iload 26
    //   450: aload_0
    //   451: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   454: getfield 456	oicq/wlogin_sdk/request/request_global:_last_err_msg	Loicq/wlogin_sdk/tools/ErrMsg;
    //   457: invokevirtual 461	oicq/wlogin_sdk/tools/ErrMsg:getMessage	()Ljava/lang/String;
    //   460: invokestatic 465	oicq/wlogin_sdk/tools/util:format_ret_code	(ILjava/lang/String;)I
    //   463: iload 26
    //   465: invokevirtual 471	oicq/wlogin_sdk/report/report_t1:commit_t2	(JLjava/lang/String;II)V
    //   468: aload 12
    //   470: getfield 476	oicq/wlogin_sdk/request/WUserSigInfo:_userStSig	[B
    //   473: ifnull +42 -> 515
    //   476: aload 12
    //   478: getfield 476	oicq/wlogin_sdk/request/WUserSigInfo:_userStSig	[B
    //   481: arraylength
    //   482: ifeq +33 -> 515
    //   485: aload_0
    //   486: iconst_0
    //   487: aload 12
    //   489: getfield 476	oicq/wlogin_sdk/request/WUserSigInfo:_userStSig	[B
    //   492: aload 12
    //   494: getfield 479	oicq/wlogin_sdk/request/WUserSigInfo:_userSt_Key	[B
    //   497: aload_0
    //   498: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   501: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   504: aload_0
    //   505: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   508: getfield 482	oicq/wlogin_sdk/request/request_global:_appid	J
    //   511: invokespecial 337	oicq/wlogin_sdk/request/WtloginHelper:RequestReport	(I[B[BJJ)I
    //   514: pop
    //   515: aload_0
    //   516: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   519: getfield 486	oicq/wlogin_sdk/request/request_global:_t150	Loicq/wlogin_sdk/tlv_type/tlv_t150;
    //   522: ifnull +47 -> 569
    //   525: aload_0
    //   526: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   529: getfield 486	oicq/wlogin_sdk/request/request_global:_t150	Loicq/wlogin_sdk/tlv_type/tlv_t150;
    //   532: invokevirtual 491	oicq/wlogin_sdk/tlv_type/tlv_t150:get_bitmap	()I
    //   535: ifeq +34 -> 569
    //   538: aload_0
    //   539: iconst_0
    //   540: aload 12
    //   542: getfield 476	oicq/wlogin_sdk/request/WUserSigInfo:_userStSig	[B
    //   545: aload 12
    //   547: getfield 479	oicq/wlogin_sdk/request/WUserSigInfo:_userSt_Key	[B
    //   550: aload_0
    //   551: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   554: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   557: aload_0
    //   558: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   561: getfield 482	oicq/wlogin_sdk/request/request_global:_appid	J
    //   564: iconst_1
    //   565: invokespecial 341	oicq/wlogin_sdk/request/WtloginHelper:RequestReportError	(I[B[BJJI)I
    //   568: pop
    //   569: new 493	oicq/wlogin_sdk/request/delete_expire_log
    //   572: dup
    //   573: aload_0
    //   574: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   577: getfield 441	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   580: invokespecial 494	oicq/wlogin_sdk/request/delete_expire_log:<init>	(Landroid/content/Context;)V
    //   583: invokevirtual 497	oicq/wlogin_sdk/request/delete_expire_log:start	()V
    //   586: aload_0
    //   587: monitorexit
    //   588: aload_0
    //   589: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   592: invokevirtual 500	oicq/wlogin_sdk/request/request_global:close_connect	()V
    //   595: new 251	java/lang/StringBuilder
    //   598: dup
    //   599: ldc_w 1091
    //   602: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   605: aload_1
    //   606: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   609: ldc_w 847
    //   612: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   615: lload_2
    //   616: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   619: ldc_w 849
    //   622: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   625: lload 4
    //   627: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   630: ldc_w 851
    //   633: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   636: lload 6
    //   638: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   641: ldc_w 853
    //   644: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   647: iload 17
    //   649: invokevirtual 264	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   652: ldc_w 855
    //   655: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   658: lload 9
    //   660: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   663: ldc_w 921
    //   666: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   669: iload 26
    //   671: invokevirtual 264	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   674: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   677: aload_0
    //   678: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   681: getfield 441	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   684: new 251	java/lang/StringBuilder
    //   687: dup
    //   688: invokespecial 503	java/lang/StringBuilder:<init>	()V
    //   691: aload_0
    //   692: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   695: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   698: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   701: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   704: iconst_1
    //   705: invokestatic 282	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   708: iload 26
    //   710: ireturn
    //   711: invokestatic 925	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   714: pop
    //   715: ldc2_w 926
    //   718: invokestatic 309	java/lang/Thread:sleep	(J)V
    //   721: goto -575 -> 146
    //   724: astore 18
    //   726: goto -580 -> 146
    //   729: new 251	java/lang/StringBuilder
    //   732: dup
    //   733: ldc_w 1093
    //   736: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   739: aload_1
    //   740: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   743: ldc_w 787
    //   746: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   749: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   752: invokestatic 179	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   755: aload_1
    //   756: invokestatic 224	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   759: invokestatic 213	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   762: astore 25
    //   764: aload_0
    //   765: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   768: aload 25
    //   770: invokevirtual 234	java/lang/Long:longValue	()J
    //   773: putfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   776: aload 14
    //   778: ifnull +347 -> 1125
    //   781: aload 14
    //   783: arraylength
    //   784: iconst_4
    //   785: if_icmpne +340 -> 1125
    //   788: aload 14
    //   790: iconst_0
    //   791: aaload
    //   792: ifnull +333 -> 1125
    //   795: aload 14
    //   797: iconst_0
    //   798: aaload
    //   799: arraylength
    //   800: iconst_1
    //   801: if_icmpne +324 -> 1125
    //   804: aload 14
    //   806: iconst_0
    //   807: aaload
    //   808: iconst_0
    //   809: baload
    //   810: iconst_1
    //   811: if_icmpne +314 -> 1125
    //   814: iconst_1
    //   815: istore 20
    //   817: aload_0
    //   818: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   821: getfield 448	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   824: new 883	oicq/wlogin_sdk/report/report_t2
    //   827: dup
    //   828: ldc_w 885
    //   831: new 186	java/lang/String
    //   834: dup
    //   835: aload_0
    //   836: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   839: getfield 888	oicq/wlogin_sdk/request/request_global:_sim_operator_name	[B
    //   842: invokespecial 891	java/lang/String:<init>	([B)V
    //   845: invokestatic 554	java/lang/System:currentTimeMillis	()J
    //   848: lload_2
    //   849: lload 9
    //   851: aload 11
    //   853: invokespecial 894	oicq/wlogin_sdk/report/report_t2:<init>	(Ljava/lang/String;Ljava/lang/String;JJJ[J)V
    //   856: invokevirtual 898	oicq/wlogin_sdk/report/report_t1:add_t2	(Loicq/wlogin_sdk/report/report_t2;)V
    //   859: new 251	java/lang/StringBuilder
    //   862: dup
    //   863: ldc_w 436
    //   866: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   869: aload_1
    //   870: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   873: ldc_w 1097
    //   876: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   879: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   882: aload_0
    //   883: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   886: getfield 441	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   889: new 251	java/lang/StringBuilder
    //   892: dup
    //   893: invokespecial 503	java/lang/StringBuilder:<init>	()V
    //   896: aload_0
    //   897: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   900: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   903: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   906: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   909: iconst_0
    //   910: invokestatic 282	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   913: aload 14
    //   915: iconst_1
    //   916: aaload
    //   917: ifnull +1389 -> 2306
    //   920: aload 14
    //   922: iconst_2
    //   923: aaload
    //   924: ifnull +1382 -> 2306
    //   927: aload 14
    //   929: iconst_3
    //   930: aaload
    //   931: ifnonnull +6 -> 937
    //   934: goto +1372 -> 2306
    //   937: aload_0
    //   938: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   941: getfield 1050	oicq/wlogin_sdk/request/request_global:_init_time	[B
    //   944: ifnonnull +159 -> 1103
    //   947: aload_0
    //   948: getfield 68	oicq/wlogin_sdk/request/WtloginHelper:mPing	Loicq/wlogin_sdk/request/request_ping;
    //   951: lconst_0
    //   952: lload 4
    //   954: invokevirtual 1053	oicq/wlogin_sdk/request/request_ping:make_request	(JJ)I
    //   957: istore 47
    //   959: iload 47
    //   961: istore 26
    //   963: new 251	java/lang/StringBuilder
    //   966: dup
    //   967: ldc_w 1055
    //   970: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   973: iload 26
    //   975: invokevirtual 264	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   978: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   981: invokestatic 179	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   984: iload 26
    //   986: ifne -569 -> 417
    //   989: iload 26
    //   991: istore 16
    //   993: aload_0
    //   994: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   997: aload 14
    //   999: iconst_3
    //   1000: aaload
    //   1001: invokestatic 1100	oicq/wlogin_sdk/tools/MD5:toMD5Byte	([B)[B
    //   1004: putfield 1103	oicq/wlogin_sdk/request/request_global:_master_tgt_key	[B
    //   1007: aload_0
    //   1008: getfield 93	oicq/wlogin_sdk/request/WtloginHelper:mChangeSig	Loicq/wlogin_sdk/request/request_change_sig;
    //   1011: aload 25
    //   1013: invokevirtual 234	java/lang/Long:longValue	()J
    //   1016: lload 4
    //   1018: lload 9
    //   1020: iconst_1
    //   1021: iload 17
    //   1023: aload 14
    //   1025: iconst_1
    //   1026: aaload
    //   1027: aload_0
    //   1028: getfield 158	oicq/wlogin_sdk/request/WtloginHelper:mChangeSigMiscBitmap	I
    //   1031: aload_0
    //   1032: getfield 154	oicq/wlogin_sdk/request/WtloginHelper:mSubSigMap	I
    //   1035: aload 11
    //   1037: aload 14
    //   1039: iconst_2
    //   1040: aaload
    //   1041: aload 14
    //   1043: iconst_3
    //   1044: aaload
    //   1045: invokevirtual 1106	oicq/wlogin_sdk/request/request_change_sig:make_request	(JJJII[BII[J[B[B)I
    //   1048: istore 46
    //   1050: iload 46
    //   1052: istore 26
    //   1054: iload 26
    //   1056: iconst_3
    //   1057: if_icmpne +12 -> 1069
    //   1060: aload_0
    //   1061: getfield 113	oicq/wlogin_sdk/request/WtloginHelper:mDelay	Loicq/wlogin_sdk/request/request_delay;
    //   1064: invokevirtual 1079	oicq/wlogin_sdk/request/request_delay:make_request	()I
    //   1067: istore 26
    //   1069: iload 26
    //   1071: ifne -654 -> 417
    //   1074: aload_0
    //   1075: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1078: aload 25
    //   1080: invokevirtual 234	java/lang/Long:longValue	()J
    //   1083: lload 4
    //   1085: invokevirtual 229	oicq/wlogin_sdk/request/request_global:get_siginfo	(JJ)Loicq/wlogin_sdk/sharemem/WloginSigInfo;
    //   1088: astore 34
    //   1090: aload 34
    //   1092: ifnonnull +1021 -> 2113
    //   1095: sipush -1004
    //   1098: istore 26
    //   1100: goto -683 -> 417
    //   1103: ldc_w 1057
    //   1106: invokestatic 179	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   1109: iconst_0
    //   1110: istore 16
    //   1112: goto -119 -> 993
    //   1115: astore 21
    //   1117: iload 16
    //   1119: pop
    //   1120: aload_0
    //   1121: monitorexit
    //   1122: aload 21
    //   1124: athrow
    //   1125: aload 14
    //   1127: ifnull +294 -> 1421
    //   1130: aload 14
    //   1132: arraylength
    //   1133: iconst_3
    //   1134: if_icmpne +287 -> 1421
    //   1137: aload 14
    //   1139: iconst_0
    //   1140: aaload
    //   1141: ifnull +280 -> 1421
    //   1144: aload 14
    //   1146: iconst_0
    //   1147: aaload
    //   1148: arraylength
    //   1149: iconst_1
    //   1150: if_icmpne +271 -> 1421
    //   1153: aload 14
    //   1155: iconst_0
    //   1156: aaload
    //   1157: iconst_0
    //   1158: baload
    //   1159: iconst_2
    //   1160: if_icmpne +261 -> 1421
    //   1163: iconst_1
    //   1164: istore 20
    //   1166: aload_0
    //   1167: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1170: getfield 448	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   1173: new 883	oicq/wlogin_sdk/report/report_t2
    //   1176: dup
    //   1177: ldc_w 885
    //   1180: new 186	java/lang/String
    //   1183: dup
    //   1184: aload_0
    //   1185: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1188: getfield 888	oicq/wlogin_sdk/request/request_global:_sim_operator_name	[B
    //   1191: invokespecial 891	java/lang/String:<init>	([B)V
    //   1194: invokestatic 554	java/lang/System:currentTimeMillis	()J
    //   1197: lload_2
    //   1198: lload 9
    //   1200: aload 11
    //   1202: invokespecial 894	oicq/wlogin_sdk/report/report_t2:<init>	(Ljava/lang/String;Ljava/lang/String;JJJ[J)V
    //   1205: invokevirtual 898	oicq/wlogin_sdk/report/report_t1:add_t2	(Loicq/wlogin_sdk/report/report_t2;)V
    //   1208: new 251	java/lang/StringBuilder
    //   1211: dup
    //   1212: ldc_w 436
    //   1215: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1218: aload_1
    //   1219: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1222: ldc_w 1108
    //   1225: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1228: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1231: aload_0
    //   1232: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1235: getfield 441	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   1238: new 251	java/lang/StringBuilder
    //   1241: dup
    //   1242: invokespecial 503	java/lang/StringBuilder:<init>	()V
    //   1245: aload_0
    //   1246: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1249: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1252: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1255: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1258: iconst_0
    //   1259: invokestatic 282	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   1262: aload 14
    //   1264: iconst_1
    //   1265: aaload
    //   1266: ifnull +1048 -> 2314
    //   1269: aload 14
    //   1271: iconst_2
    //   1272: aaload
    //   1273: ifnonnull +6 -> 1279
    //   1276: goto +1038 -> 2314
    //   1279: aload_0
    //   1280: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1283: getfield 1050	oicq/wlogin_sdk/request/request_global:_init_time	[B
    //   1286: ifnonnull +123 -> 1409
    //   1289: aload_0
    //   1290: getfield 68	oicq/wlogin_sdk/request/WtloginHelper:mPing	Loicq/wlogin_sdk/request/request_ping;
    //   1293: lconst_0
    //   1294: lload 4
    //   1296: invokevirtual 1053	oicq/wlogin_sdk/request/request_ping:make_request	(JJ)I
    //   1299: istore 45
    //   1301: iload 45
    //   1303: istore 26
    //   1305: new 251	java/lang/StringBuilder
    //   1308: dup
    //   1309: ldc_w 1055
    //   1312: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1315: iload 26
    //   1317: invokevirtual 264	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1320: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1323: invokestatic 179	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   1326: iload 26
    //   1328: ifne -911 -> 417
    //   1331: iload 26
    //   1333: istore 16
    //   1335: aload_0
    //   1336: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1339: aload 14
    //   1341: iconst_2
    //   1342: aaload
    //   1343: putfield 1103	oicq/wlogin_sdk/request/request_global:_master_tgt_key	[B
    //   1346: aload_0
    //   1347: getfield 93	oicq/wlogin_sdk/request/WtloginHelper:mChangeSig	Loicq/wlogin_sdk/request/request_change_sig;
    //   1350: aload 25
    //   1352: invokevirtual 234	java/lang/Long:longValue	()J
    //   1355: lload 4
    //   1357: lload 9
    //   1359: iconst_1
    //   1360: iload 17
    //   1362: aload 14
    //   1364: iconst_1
    //   1365: aaload
    //   1366: aload_0
    //   1367: getfield 158	oicq/wlogin_sdk/request/WtloginHelper:mChangeSigMiscBitmap	I
    //   1370: aload_0
    //   1371: getfield 154	oicq/wlogin_sdk/request/WtloginHelper:mSubSigMap	I
    //   1374: aload 11
    //   1376: aconst_null
    //   1377: aconst_null
    //   1378: invokevirtual 1106	oicq/wlogin_sdk/request/request_change_sig:make_request	(JJJII[BII[J[B[B)I
    //   1381: istore 43
    //   1383: iload 43
    //   1385: istore 26
    //   1387: iload 26
    //   1389: iconst_3
    //   1390: if_icmpne -321 -> 1069
    //   1393: aload_0
    //   1394: getfield 113	oicq/wlogin_sdk/request/WtloginHelper:mDelay	Loicq/wlogin_sdk/request/request_delay;
    //   1397: invokevirtual 1079	oicq/wlogin_sdk/request/request_delay:make_request	()I
    //   1400: istore 44
    //   1402: iload 44
    //   1404: istore 26
    //   1406: goto -337 -> 1069
    //   1409: ldc_w 1057
    //   1412: invokestatic 179	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   1415: iconst_0
    //   1416: istore 16
    //   1418: goto -83 -> 1335
    //   1421: new 251	java/lang/StringBuilder
    //   1424: dup
    //   1425: ldc_w 436
    //   1428: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1431: aload_1
    //   1432: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1435: ldc_w 1110
    //   1438: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1441: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1444: aload_0
    //   1445: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1448: getfield 441	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   1451: new 251	java/lang/StringBuilder
    //   1454: dup
    //   1455: invokespecial 503	java/lang/StringBuilder:<init>	()V
    //   1458: aload_0
    //   1459: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1462: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1465: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1468: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1471: iconst_0
    //   1472: invokestatic 282	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   1475: aload_0
    //   1476: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1479: aload 25
    //   1481: invokevirtual 234	java/lang/Long:longValue	()J
    //   1484: lload_2
    //   1485: invokevirtual 229	oicq/wlogin_sdk/request/request_global:get_siginfo	(JJ)Loicq/wlogin_sdk/sharemem/WloginSigInfo;
    //   1488: astore 30
    //   1490: iconst_0
    //   1491: istore 16
    //   1493: aload 30
    //   1495: ifnull +14 -> 1509
    //   1498: aload 30
    //   1500: invokestatic 1113	oicq/wlogin_sdk/request/request_global:get_cur_time	()J
    //   1503: invokevirtual 1117	oicq/wlogin_sdk/sharemem/WloginSigInfo:iSExpireA2	(J)Z
    //   1506: ifeq +289 -> 1795
    //   1509: iconst_1
    //   1510: istore 20
    //   1512: aload_0
    //   1513: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1516: getfield 448	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   1519: new 883	oicq/wlogin_sdk/report/report_t2
    //   1522: dup
    //   1523: ldc_w 885
    //   1526: new 186	java/lang/String
    //   1529: dup
    //   1530: aload_0
    //   1531: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1534: getfield 888	oicq/wlogin_sdk/request/request_global:_sim_operator_name	[B
    //   1537: invokespecial 891	java/lang/String:<init>	([B)V
    //   1540: invokestatic 554	java/lang/System:currentTimeMillis	()J
    //   1543: lload_2
    //   1544: lload 9
    //   1546: aload 11
    //   1548: invokespecial 894	oicq/wlogin_sdk/report/report_t2:<init>	(Ljava/lang/String;Ljava/lang/String;JJJ[J)V
    //   1551: invokevirtual 898	oicq/wlogin_sdk/report/report_t1:add_t2	(Loicq/wlogin_sdk/report/report_t2;)V
    //   1554: new 251	java/lang/StringBuilder
    //   1557: dup
    //   1558: ldc_w 436
    //   1561: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1564: aload_1
    //   1565: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1568: ldc_w 1119
    //   1571: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1574: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1577: aload_0
    //   1578: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1581: getfield 441	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   1584: new 251	java/lang/StringBuilder
    //   1587: dup
    //   1588: invokespecial 503	java/lang/StringBuilder:<init>	()V
    //   1591: aload_0
    //   1592: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1595: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1598: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1601: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1604: iconst_0
    //   1605: invokestatic 282	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   1608: aload_0
    //   1609: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1612: aload 25
    //   1614: invokevirtual 234	java/lang/Long:longValue	()J
    //   1617: lload 4
    //   1619: lload 6
    //   1621: invokestatic 1113	oicq/wlogin_sdk/request/request_global:get_cur_time	()J
    //   1624: invokevirtual 1123	oicq/wlogin_sdk/request/request_global:get_siginfo_by_pri	(JJJJ)Loicq/wlogin_sdk/sharemem/WloginSigInfo;
    //   1627: astore 31
    //   1629: iconst_0
    //   1630: istore 16
    //   1632: aload 31
    //   1634: ifnull +688 -> 2322
    //   1637: aload 31
    //   1639: invokestatic 1113	oicq/wlogin_sdk/request/request_global:get_cur_time	()J
    //   1642: invokevirtual 1117	oicq/wlogin_sdk/sharemem/WloginSigInfo:iSExpireA2	(J)Z
    //   1645: ifeq +6 -> 1651
    //   1648: goto +674 -> 2322
    //   1651: aload_0
    //   1652: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1655: getfield 1050	oicq/wlogin_sdk/request/request_global:_init_time	[B
    //   1658: ifnonnull +125 -> 1783
    //   1661: aload_0
    //   1662: getfield 68	oicq/wlogin_sdk/request/WtloginHelper:mPing	Loicq/wlogin_sdk/request/request_ping;
    //   1665: lconst_0
    //   1666: lload 4
    //   1668: invokevirtual 1053	oicq/wlogin_sdk/request/request_ping:make_request	(JJ)I
    //   1671: istore 38
    //   1673: iload 38
    //   1675: istore 26
    //   1677: new 251	java/lang/StringBuilder
    //   1680: dup
    //   1681: ldc_w 1055
    //   1684: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1687: iload 26
    //   1689: invokevirtual 264	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1692: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1695: invokestatic 179	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   1698: iload 26
    //   1700: ifne -1283 -> 417
    //   1703: iload 26
    //   1705: istore 16
    //   1707: aload_0
    //   1708: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1711: aload 31
    //   1713: getfield 973	oicq/wlogin_sdk/sharemem/WloginSigInfo:_TGTKey	[B
    //   1716: putfield 1103	oicq/wlogin_sdk/request/request_global:_master_tgt_key	[B
    //   1719: aload_0
    //   1720: getfield 93	oicq/wlogin_sdk/request/WtloginHelper:mChangeSig	Loicq/wlogin_sdk/request/request_change_sig;
    //   1723: aload 25
    //   1725: invokevirtual 234	java/lang/Long:longValue	()J
    //   1728: lload 4
    //   1730: lload 9
    //   1732: iconst_1
    //   1733: iload 17
    //   1735: aload 31
    //   1737: getfield 598	oicq/wlogin_sdk/sharemem/WloginSigInfo:_TGT	[B
    //   1740: aload_0
    //   1741: getfield 158	oicq/wlogin_sdk/request/WtloginHelper:mChangeSigMiscBitmap	I
    //   1744: aload_0
    //   1745: getfield 154	oicq/wlogin_sdk/request/WtloginHelper:mSubSigMap	I
    //   1748: aload 11
    //   1750: aconst_null
    //   1751: aconst_null
    //   1752: invokevirtual 1106	oicq/wlogin_sdk/request/request_change_sig:make_request	(JJJII[BII[J[B[B)I
    //   1755: istore 32
    //   1757: iload 32
    //   1759: istore 26
    //   1761: iload 26
    //   1763: iconst_3
    //   1764: if_icmpne -695 -> 1069
    //   1767: aload_0
    //   1768: getfield 113	oicq/wlogin_sdk/request/WtloginHelper:mDelay	Loicq/wlogin_sdk/request/request_delay;
    //   1771: invokevirtual 1079	oicq/wlogin_sdk/request/request_delay:make_request	()I
    //   1774: istore 33
    //   1776: iload 33
    //   1778: istore 26
    //   1780: goto -711 -> 1069
    //   1783: ldc_w 1057
    //   1786: invokestatic 179	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   1789: iconst_0
    //   1790: istore 16
    //   1792: goto -85 -> 1707
    //   1795: new 251	java/lang/StringBuilder
    //   1798: dup
    //   1799: ldc_w 436
    //   1802: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1805: aload_1
    //   1806: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1809: ldc_w 1125
    //   1812: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1815: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1818: aload_0
    //   1819: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1822: getfield 441	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   1825: new 251	java/lang/StringBuilder
    //   1828: dup
    //   1829: invokespecial 503	java/lang/StringBuilder:<init>	()V
    //   1832: aload_0
    //   1833: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1836: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1839: invokevirtual 506	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1842: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1845: iconst_0
    //   1846: invokestatic 282	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   1849: aload_0
    //   1850: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1853: aload 25
    //   1855: invokevirtual 234	java/lang/Long:longValue	()J
    //   1858: lload 4
    //   1860: invokevirtual 229	oicq/wlogin_sdk/request/request_global:get_siginfo	(JJ)Loicq/wlogin_sdk/sharemem/WloginSigInfo;
    //   1863: astore 39
    //   1865: iconst_0
    //   1866: istore 16
    //   1868: aload 39
    //   1870: ifnull +45 -> 1915
    //   1873: aload 39
    //   1875: invokestatic 1113	oicq/wlogin_sdk/request/request_global:get_cur_time	()J
    //   1878: invokevirtual 1128	oicq/wlogin_sdk/sharemem/WloginSigInfo:iSExpire	(J)Z
    //   1881: ifne +34 -> 1915
    //   1884: aload 14
    //   1886: ifnull +218 -> 2104
    //   1889: aload 14
    //   1891: arraylength
    //   1892: iconst_1
    //   1893: if_icmpne +211 -> 2104
    //   1896: aload 14
    //   1898: iconst_0
    //   1899: aaload
    //   1900: arraylength
    //   1901: iconst_1
    //   1902: if_icmpne +202 -> 2104
    //   1905: aload 14
    //   1907: iconst_0
    //   1908: aaload
    //   1909: iconst_0
    //   1910: baload
    //   1911: iconst_1
    //   1912: if_icmpne +192 -> 2104
    //   1915: iconst_1
    //   1916: istore 20
    //   1918: aload_0
    //   1919: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1922: getfield 448	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   1925: new 883	oicq/wlogin_sdk/report/report_t2
    //   1928: dup
    //   1929: ldc_w 885
    //   1932: new 186	java/lang/String
    //   1935: dup
    //   1936: aload_0
    //   1937: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1940: getfield 888	oicq/wlogin_sdk/request/request_global:_sim_operator_name	[B
    //   1943: invokespecial 891	java/lang/String:<init>	([B)V
    //   1946: invokestatic 554	java/lang/System:currentTimeMillis	()J
    //   1949: lload_2
    //   1950: lload 9
    //   1952: aload 11
    //   1954: invokespecial 894	oicq/wlogin_sdk/report/report_t2:<init>	(Ljava/lang/String;Ljava/lang/String;JJJ[J)V
    //   1957: invokevirtual 898	oicq/wlogin_sdk/report/report_t1:add_t2	(Loicq/wlogin_sdk/report/report_t2;)V
    //   1960: aload_0
    //   1961: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   1964: getfield 1050	oicq/wlogin_sdk/request/request_global:_init_time	[B
    //   1967: ifnonnull +125 -> 2092
    //   1970: aload_0
    //   1971: getfield 68	oicq/wlogin_sdk/request/WtloginHelper:mPing	Loicq/wlogin_sdk/request/request_ping;
    //   1974: lconst_0
    //   1975: lload 4
    //   1977: invokevirtual 1053	oicq/wlogin_sdk/request/request_ping:make_request	(JJ)I
    //   1980: istore 42
    //   1982: iload 42
    //   1984: istore 26
    //   1986: new 251	java/lang/StringBuilder
    //   1989: dup
    //   1990: ldc_w 1055
    //   1993: invokespecial 255	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1996: iload 26
    //   1998: invokevirtual 264	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2001: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2004: invokestatic 179	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   2007: iload 26
    //   2009: ifne -1592 -> 417
    //   2012: iload 26
    //   2014: istore 16
    //   2016: aload_0
    //   2017: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   2020: aload 30
    //   2022: getfield 973	oicq/wlogin_sdk/sharemem/WloginSigInfo:_TGTKey	[B
    //   2025: putfield 1103	oicq/wlogin_sdk/request/request_global:_master_tgt_key	[B
    //   2028: aload_0
    //   2029: getfield 93	oicq/wlogin_sdk/request/WtloginHelper:mChangeSig	Loicq/wlogin_sdk/request/request_change_sig;
    //   2032: aload 25
    //   2034: invokevirtual 234	java/lang/Long:longValue	()J
    //   2037: lload 4
    //   2039: lload 9
    //   2041: iconst_1
    //   2042: iload 17
    //   2044: aload 30
    //   2046: getfield 598	oicq/wlogin_sdk/sharemem/WloginSigInfo:_TGT	[B
    //   2049: aload_0
    //   2050: getfield 158	oicq/wlogin_sdk/request/WtloginHelper:mChangeSigMiscBitmap	I
    //   2053: aload_0
    //   2054: getfield 154	oicq/wlogin_sdk/request/WtloginHelper:mSubSigMap	I
    //   2057: aload 11
    //   2059: aconst_null
    //   2060: aconst_null
    //   2061: invokevirtual 1106	oicq/wlogin_sdk/request/request_change_sig:make_request	(JJJII[BII[J[B[B)I
    //   2064: istore 40
    //   2066: iload 40
    //   2068: istore 26
    //   2070: iload 26
    //   2072: iconst_3
    //   2073: if_icmpne -1004 -> 1069
    //   2076: aload_0
    //   2077: getfield 113	oicq/wlogin_sdk/request/WtloginHelper:mDelay	Loicq/wlogin_sdk/request/request_delay;
    //   2080: invokevirtual 1079	oicq/wlogin_sdk/request/request_delay:make_request	()I
    //   2083: istore 41
    //   2085: iload 41
    //   2087: istore 26
    //   2089: goto -1020 -> 1069
    //   2092: ldc_w 1057
    //   2095: invokestatic 179	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   2098: iconst_0
    //   2099: istore 16
    //   2101: goto -85 -> 2016
    //   2104: iconst_0
    //   2105: istore 26
    //   2107: iconst_0
    //   2108: istore 20
    //   2110: goto -1041 -> 1069
    //   2113: aload 12
    //   2115: aload 34
    //   2117: invokevirtual 513	oicq/wlogin_sdk/request/WUserSigInfo:get_clone	(Loicq/wlogin_sdk/sharemem/WloginSigInfo;)V
    //   2120: aload 11
    //   2122: ifnull -1705 -> 417
    //   2125: aload 13
    //   2127: ifnull -1710 -> 417
    //   2130: iconst_2
    //   2131: aload 11
    //   2133: arraylength
    //   2134: imul
    //   2135: aload 13
    //   2137: arraylength
    //   2138: if_icmpne -1721 -> 417
    //   2141: iconst_0
    //   2142: istore 35
    //   2144: aload 11
    //   2146: ifnull -1729 -> 417
    //   2149: aload 11
    //   2151: arraylength
    //   2152: istore 36
    //   2154: iload 35
    //   2156: iload 36
    //   2158: if_icmpge -1741 -> 417
    //   2161: aload_0
    //   2162: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   2165: aload 25
    //   2167: invokevirtual 234	java/lang/Long:longValue	()J
    //   2170: aload 11
    //   2172: iload 35
    //   2174: laload
    //   2175: invokevirtual 229	oicq/wlogin_sdk/request/request_global:get_siginfo	(JJ)Loicq/wlogin_sdk/sharemem/WloginSigInfo;
    //   2178: astore 37
    //   2180: aload 37
    //   2182: ifnull +148 -> 2330
    //   2185: aload 13
    //   2187: iload 35
    //   2189: iconst_2
    //   2190: imul
    //   2191: aload 37
    //   2193: getfield 518	oicq/wlogin_sdk/sharemem/WloginSigInfo:_userSt_Key	[B
    //   2196: invokevirtual 523	[B:clone	()Ljava/lang/Object;
    //   2199: checkcast 519	[B
    //   2202: aastore
    //   2203: aload 13
    //   2205: iconst_1
    //   2206: iload 35
    //   2208: iconst_2
    //   2209: imul
    //   2210: iadd
    //   2211: aload 37
    //   2213: getfield 524	oicq/wlogin_sdk/sharemem/WloginSigInfo:_userStSig	[B
    //   2216: invokevirtual 523	[B:clone	()Ljava/lang/Object;
    //   2219: checkcast 519	[B
    //   2222: aastore
    //   2223: goto +107 -> 2330
    //   2226: aload_0
    //   2227: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   2230: getfield 448	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   2233: aload_0
    //   2234: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   2237: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   2240: aload_0
    //   2241: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   2244: getfield 452	oicq/wlogin_sdk/request/request_global:_name	Ljava/lang/String;
    //   2247: iload 26
    //   2249: aload_0
    //   2250: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   2253: getfield 456	oicq/wlogin_sdk/request/request_global:_last_err_msg	Loicq/wlogin_sdk/tools/ErrMsg;
    //   2256: invokevirtual 461	oicq/wlogin_sdk/tools/ErrMsg:getMessage	()Ljava/lang/String;
    //   2259: invokestatic 465	oicq/wlogin_sdk/tools/util:format_ret_code	(ILjava/lang/String;)I
    //   2262: iload 26
    //   2264: invokevirtual 471	oicq/wlogin_sdk/report/report_t1:commit_t2	(JLjava/lang/String;II)V
    //   2267: aload_0
    //   2268: iconst_0
    //   2269: aload 12
    //   2271: getfield 476	oicq/wlogin_sdk/request/WUserSigInfo:_userStSig	[B
    //   2274: aload 12
    //   2276: getfield 479	oicq/wlogin_sdk/request/WUserSigInfo:_userSt_Key	[B
    //   2279: aload_0
    //   2280: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   2283: getfield 449	oicq/wlogin_sdk/request/request_global:_uin	J
    //   2286: aload_0
    //   2287: getfield 61	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/request_global;
    //   2290: getfield 482	oicq/wlogin_sdk/request/request_global:_appid	J
    //   2293: iconst_0
    //   2294: invokespecial 341	oicq/wlogin_sdk/request/WtloginHelper:RequestReportError	(I[B[BJJI)I
    //   2297: pop
    //   2298: goto -1783 -> 515
    //   2301: astore 21
    //   2303: goto -1183 -> 1120
    //   2306: sipush -1004
    //   2309: istore 26
    //   2311: goto -1894 -> 417
    //   2314: sipush -1004
    //   2317: istore 26
    //   2319: goto -1902 -> 417
    //   2322: sipush -1004
    //   2325: istore 26
    //   2327: goto -1910 -> 417
    //   2330: iinc 35 1
    //   2333: goto -189 -> 2144
    //
    // Exception table:
    //   from	to	target	type
    //   711	721	724	java/lang/Exception
    //   161	218	1115	finally
    //   218	296	1115	finally
    //   301	316	1115	finally
    //   316	325	1115	finally
    //   333	369	1115	finally
    //   377	412	1115	finally
    //   729	764	1115	finally
    //   764	776	1115	finally
    //   781	814	1115	finally
    //   817	934	1115	finally
    //   937	959	1115	finally
    //   993	1050	1115	finally
    //   1103	1109	1115	finally
    //   1130	1163	1115	finally
    //   1166	1276	1115	finally
    //   1279	1301	1115	finally
    //   1335	1383	1115	finally
    //   1409	1415	1115	finally
    //   1421	1490	1115	finally
    //   1498	1509	1115	finally
    //   1512	1629	1115	finally
    //   1637	1648	1115	finally
    //   1651	1673	1115	finally
    //   1707	1757	1115	finally
    //   1783	1789	1115	finally
    //   1795	1865	1115	finally
    //   1873	1884	1115	finally
    //   1889	1915	1115	finally
    //   1918	1982	1115	finally
    //   2016	2066	1115	finally
    //   2092	2098	1115	finally
    //   427	515	2301	finally
    //   515	569	2301	finally
    //   569	588	2301	finally
    //   963	984	2301	finally
    //   1060	1069	2301	finally
    //   1074	1090	2301	finally
    //   1120	1122	2301	finally
    //   1305	1326	2301	finally
    //   1393	1402	2301	finally
    //   1677	1698	2301	finally
    //   1767	1776	2301	finally
    //   1986	2007	2301	finally
    //   2076	2085	2301	finally
    //   2113	2120	2301	finally
    //   2130	2141	2301	finally
    //   2149	2154	2301	finally
    //   2161	2180	2301	finally
    //   2185	2223	2301	finally
    //   2226	2298	2301	finally
  }

  public int GetStWithoutPasswd(String paramString, long paramLong1, long paramLong2, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    return GetStWithoutPasswd(paramString, paramLong1, paramLong2, -1L, this.mMainSigMap, 1L, null, paramWUserSigInfo, null, null, paramInt);
  }

  public int GetStWithoutPasswd(String paramString, long paramLong1, long paramLong2, WUserSigInfo paramWUserSigInfo, int paramInt1, int paramInt2)
  {
    if (paramInt1 == 0)
      return GetStWithoutPasswd(paramString, paramLong1, paramLong2, -1L, this.mMainSigMap, 1L, null, paramWUserSigInfo, null, null, paramInt2);
    byte[][] arrayOfByte = new byte[1][];
    arrayOfByte[0] = new byte[1];
    arrayOfByte[0][0] = 1;
    return GetStWithoutPasswd(paramString, paramLong1, paramLong2, -1L, this.mMainSigMap, 1L, null, paramWUserSigInfo, null, arrayOfByte, paramInt2);
  }

  public int GetStWithoutPasswd(String paramString, long paramLong1, long paramLong2, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    byte[][] arrayOfByte = new byte[3][];
    arrayOfByte[0] = new byte[1];
    arrayOfByte[0][0] = 2;
    arrayOfByte[1] = paramArrayOfByte1;
    arrayOfByte[2] = paramArrayOfByte2;
    return GetStWithoutPasswd(paramString, paramLong1, paramLong2, -1L, this.mMainSigMap, 1L, null, paramWUserSigInfo, null, arrayOfByte, paramInt);
  }

  public int GetStWithoutPasswd(byte[] paramArrayOfByte, long paramLong, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
      return -1017;
    byte[] arrayOfByte1 = cryptor.decrypt(paramArrayOfByte, 0, paramArrayOfByte.length, request_global._IMEI_KEY);
    if ((arrayOfByte1 == null) || (arrayOfByte1.length <= 0))
      return -1017;
    if (2 > arrayOfByte1.length)
      return -1017;
    int i = util.buf_to_int16(arrayOfByte1, 0);
    int j = 0 + 2;
    if ((i <= 0) || (i + 2 > arrayOfByte1.length))
      return -1017;
    String str = new String(arrayOfByte1, j, i);
    int k = i + 2;
    if (k + 8 > arrayOfByte1.length)
      return -1017;
    long l = util.buf_to_int64(arrayOfByte1, k);
    int m = k + 8;
    if (m + 2 > arrayOfByte1.length)
      return -1017;
    int n = util.buf_to_int16(arrayOfByte1, m);
    int i1 = m + 2;
    if ((n <= 0) || (i1 + n > arrayOfByte1.length))
      return -1017;
    byte[] arrayOfByte2 = new byte[n];
    System.arraycopy(arrayOfByte1, i1, arrayOfByte2, 0, arrayOfByte2.length);
    int i2 = i1 + n;
    if (i2 + 2 > arrayOfByte1.length)
      return -1017;
    int i3 = util.buf_to_int16(arrayOfByte1, i2);
    int i4 = i2 + 2;
    if ((i3 <= 0) || (i4 + i3 > arrayOfByte1.length))
      return -1017;
    byte[] arrayOfByte3 = new byte[i3];
    System.arraycopy(arrayOfByte1, i4, arrayOfByte3, 0, arrayOfByte3.length);
    (i4 + i3);
    byte[][] arrayOfByte = new byte[3][];
    arrayOfByte[0] = new byte[1];
    arrayOfByte[0][0] = 2;
    arrayOfByte[1] = arrayOfByte2;
    arrayOfByte[2] = arrayOfByte3;
    return GetStWithoutPasswd(str, l, paramLong, -1L, this.mMainSigMap, 1L, null, paramWUserSigInfo, null, arrayOfByte, paramInt);
  }

  public long GetTimeDifference()
  {
    return this.mG._time_difference;
  }

  public boolean InitPushService(int paramInt)
  {
    monitorenter;
    try
    {
      boolean bool = this.mG.init_service(this.mContext, true, false, this.mHelperHandler, paramInt, this.mCheckApk);
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean InitShareService()
  {
    monitorenter;
    try
    {
      boolean bool = this.mG.init_service(this.mContext, false, true, this.mHelperHandler, 0, this.mCheckApk);
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean IsLocalTkValid(String paramString, long paramLong)
  {
    Long localLong;
    boolean bool1;
    if (!util.check_uin_account(paramString).booleanValue())
    {
      util.LOGD("GetLocalSig:" + paramString + ":name");
      localLong = this.mG.get_account(paramString);
      bool1 = false;
      if (localLong != null)
        break label156;
    }
    while (true)
    {
      util.LOGI("userAccount:" + paramString + " dwAppid:" + paramLong + " IsLocalTkValid return:" + bool1, this.mG._context, localLong, 1);
      return bool1;
      util.LOGD("GetLocalSig:" + paramString + ":num");
      localLong = Long.valueOf(Long.parseLong(paramString));
      label156: WloginSigInfo localWloginSigInfo = this.mG.get_siginfo(localLong.longValue(), paramLong);
      bool1 = false;
      if (localWloginSigInfo == null)
        continue;
      boolean bool2 = localWloginSigInfo.iSExpire(request_global.get_cur_time());
      bool1 = false;
      if (bool2)
        continue;
      bool1 = true;
    }
  }

  public Boolean IsNeedLoginWithPasswd(String paramString, long paramLong)
  {
    return IsNeedLoginWithPasswd(paramString, paramLong, -1L);
  }

  public Boolean IsNeedLoginWithPasswd(String paramString, long paramLong1, long paramLong2)
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (util.check_uin_account(paramString).booleanValue())
          continue;
        Long localLong = this.mG.get_account(paramString);
        if (localLong != null)
          continue;
        i = 0 + 1;
        bool2 = true;
        monitorexit;
        util.LOGI("user:" + paramString + " appid:" + paramLong1 + " dwAppPri:" + paramLong2 + " need password:" + bool2 + " flag=" + i, this.mG._context, paramString, 1);
        return Boolean.valueOf(bool2);
        localLong = Long.valueOf(Long.parseLong(paramString));
        WloginSigInfo localWloginSigInfo1 = this.mG.get_siginfo(localLong.longValue(), paramLong1);
        if ((localWloginSigInfo1 == null) || (localWloginSigInfo1._TGT == null) || (localWloginSigInfo1._TGT.length == 0) || (localWloginSigInfo1.iSExpireA2(request_global.get_cur_time())))
          continue;
        i = 0 + 1;
        bool2 = false;
        continue;
        if (localWloginSigInfo1 != null)
          continue;
        StringBuilder localStringBuilder1 = new StringBuilder("user:").append(paramString).append(" appid:").append(paramLong1).append(" dwAppPri:").append(paramLong2).append("local search failed info==null:");
        if (localWloginSigInfo1 != null)
          break label485;
        bool1 = true;
        util.LOGI(bool1, this.mG._context, paramString, 0);
        WloginSigInfo localWloginSigInfo2 = this.mG.get_siginfo_by_pri(localLong.longValue(), paramLong1, paramLong2, request_global.get_cur_time());
        if ((localWloginSigInfo2 == null) || (localWloginSigInfo2._TGT == null) || (localWloginSigInfo2._TGT.length == 0))
          break label474;
        boolean bool3 = localWloginSigInfo2.iSExpireA2(request_global.get_cur_time());
        i = 0;
        bool2 = false;
        if (!bool3)
          continue;
        break label474;
        StringBuilder localStringBuilder2 = new StringBuilder("user:").append(paramString).append(" appid:").append(paramLong1).append(" dwAppPri:").append(paramLong2).append("local search failed info==null:");
        if (localWloginSigInfo1 == null)
        {
          bool4 = true;
          StringBuilder localStringBuilder3 = localStringBuilder2.append(bool4).append(" info._TGT==null:");
          if (localWloginSigInfo1._TGT != null)
            break label468;
          bool5 = true;
          util.LOGI(bool5 + " current:" + request_global.get_cur_time() + " a2 expire:" + localWloginSigInfo1._A2_expire_time, this.mG._context, paramString, 0);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      boolean bool4 = false;
      continue;
      label468: boolean bool5 = false;
      continue;
      label474: int i = 0 + 1;
      boolean bool2 = true;
      continue;
      label485: boolean bool1 = false;
    }
  }

  public boolean IsWtLoginUrl(String paramString)
  {
    if (paramString == null);
    String str;
    do
    {
      int i;
      do
      {
        return false;
        i = paramString.indexOf("?k=");
      }
      while ((i == -1) || (32 + (i + 3) > paramString.length()));
      int j = i + 3;
      str = paramString.substring(j, j + 32);
    }
    while (util.base64_decode_url(str.getBytes(), str.length()) == null);
    return true;
  }

  public int NameGetUin(String paramString, long paramLong, byte[][] paramArrayOfByte, WUserSigInfo paramWUserSigInfo)
  {
    WloginSimpleInfo localWloginSimpleInfo = new WloginSimpleInfo();
    if (!GetBasicUserInfo(paramString, localWloginSimpleInfo).booleanValue())
      return -1003;
    long l = localWloginSimpleInfo._uin;
    WloginSigInfo localWloginSigInfo = FindUserSig(l, paramLong);
    if (localWloginSigInfo == null)
      return -1004;
    name_get_uin localname_get_uin = new name_get_uin(l);
    TransReqContext localTransReqContext = new TransReqContext();
    localTransReqContext.set_uin(l);
    localTransReqContext.set_oidb_func_req();
    localTransReqContext.set_subcmd(localname_get_uin.get_cmd());
    localTransReqContext._body = localname_get_uin.get_request(paramLong, localWloginSigInfo._TGT, paramArrayOfByte);
    if (localTransReqContext._body == null)
      return -1017;
    return RequestTransport(0, 1, paramString, paramLong, localname_get_uin._role, localTransReqContext, paramWUserSigInfo);
  }

  public void OnOidbRequest(String paramString, long paramLong1, long paramLong2, TransReqContext paramTransReqContext, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    util.LOGD("OnOidbRequest cmd = 0x" + Integer.toHexString(paramTransReqContext.get_subcmd()) + " ret=" + paramInt);
    if (paramTransReqContext.get_subcmd() == 1199)
    {
      if (paramInt != 0)
      {
        if (this.mListener != null)
        {
          this.mListener.OnNameGetUin(paramString, new ArrayList(), paramWUserSigInfo, paramInt);
          return;
        }
        OnNameGetUin(paramString, new ArrayList(), paramWUserSigInfo, paramInt);
        return;
      }
      name_get_uin localname_get_uin = new name_get_uin(paramTransReqContext.get_uin());
      int i = localname_get_uin.get_response(paramTransReqContext.get_body());
      if (this.mListener != null)
      {
        this.mListener.OnNameGetUin(paramString, localname_get_uin.nameinfo, paramWUserSigInfo, i);
        return;
      }
      OnNameGetUin(paramString, localname_get_uin.nameinfo, paramWUserSigInfo, i);
      return;
    }
    OnException(new Exception("unhandle name operation cmd"), util.ASYN_TRANSPORT);
    util.LOGW("OnRequestName unhandle cmd", "", this.mG._context, paramString);
  }

  public void OnRequestCode2d(String paramString, long paramLong1, long paramLong2, TransReqContext paramTransReqContext, int paramInt)
  {
    code2d_req_status localcode2d_req_status = code2d_base._status;
    if (paramTransReqContext.get_subcmd() == 19)
    {
      if (paramInt != 0)
      {
        if (this.mListener != null)
        {
          this.mListener.OnVerifyCode(paramString, localcode2d_req_status._app_name, localcode2d_req_status._time, localcode2d_req_status._data, localcode2d_req_status._msg, paramInt);
          return;
        }
        OnVerifyCode(paramString, localcode2d_req_status._app_name, localcode2d_req_status._time, localcode2d_req_status._data, localcode2d_req_status._msg, paramInt);
        return;
      }
      localcode2d_req_status._ret = new verify_code().get_response(paramTransReqContext.get_body());
      if (this.mListener != null)
      {
        this.mListener.OnVerifyCode(paramString, localcode2d_req_status._app_name, localcode2d_req_status._time, localcode2d_req_status._data, localcode2d_req_status._msg, localcode2d_req_status._ret);
        return;
      }
      OnVerifyCode(paramString, localcode2d_req_status._app_name, localcode2d_req_status._time, localcode2d_req_status._data, localcode2d_req_status._msg, localcode2d_req_status._ret);
      return;
    }
    if (paramTransReqContext.get_subcmd() == 20)
    {
      if (paramInt != 0)
      {
        if (this.mListener != null)
        {
          this.mListener.OnCloseCode(paramString, localcode2d_req_status._app_name, localcode2d_req_status._time, localcode2d_req_status._msg, paramInt);
          return;
        }
        OnCloseCode(paramString, localcode2d_req_status._app_name, localcode2d_req_status._time, localcode2d_req_status._msg, paramInt);
        return;
      }
      localcode2d_req_status._ret = new close_code().get_response(paramTransReqContext.get_body());
      if (this.mListener != null)
      {
        this.mListener.OnCloseCode(paramString, localcode2d_req_status._app_name, localcode2d_req_status._time, localcode2d_req_status._msg, localcode2d_req_status._ret);
        return;
      }
      OnCloseCode(paramString, localcode2d_req_status._app_name, localcode2d_req_status._time, localcode2d_req_status._msg, localcode2d_req_status._ret);
      return;
    }
    OnException(new Exception("unhandle name operation cmd"), util.ASYN_TRANSPORT);
    util.LOGW("OnRequestName unhandle cmd", "", this.mG._context, paramString);
  }

  public void OnRequestName(String paramString, long paramLong1, long paramLong2, TransReqContext paramTransReqContext, int paramInt)
  {
    name_req_status localname_req_status = this.mNameReqStatus;
    if (paramTransReqContext.get_subcmd() == 1)
    {
      if (paramInt != 0)
      {
        if (this.mListener != null)
        {
          this.mListener.OnGetMobileByUin(paramInt, localname_req_status._mobile);
          return;
        }
        OnGetMobileByUin(paramInt, localname_req_status._mobile);
        return;
      }
      new name_query().get_response(paramTransReqContext.get_body(), localname_req_status);
      if (this.mListener != null)
      {
        this.mListener.OnGetMobileByUin(localname_req_status._ret_code, localname_req_status._mobile);
        return;
      }
      OnGetMobileByUin(localname_req_status._ret_code, localname_req_status._mobile);
      return;
    }
    if (paramTransReqContext.get_subcmd() == 2)
    {
      if (paramInt != 0)
      {
        if (this.mListener != null)
        {
          this.mListener.OnBindMobileByUin(paramInt);
          return;
        }
        OnBindMobileByUin(paramInt);
        return;
      }
      new name_bind().get_response(paramTransReqContext.get_body(), localname_req_status);
      if (this.mListener != null)
      {
        this.mListener.OnBindMobileByUin(localname_req_status._ret_code);
        return;
      }
      OnBindMobileByUin(localname_req_status._ret_code);
      return;
    }
    if (paramTransReqContext.get_subcmd() == 3)
    {
      if (paramInt != 0)
      {
        if (this.mListener != null)
        {
          this.mListener.OnUnbindMobileByUin(paramInt);
          return;
        }
        OnUnbindMobileByUin(paramInt);
        return;
      }
      new name_unbind().get_response(paramTransReqContext.get_body(), localname_req_status);
      if (this.mListener != null)
      {
        this.mListener.OnUnbindMobileByUin(localname_req_status._ret_code);
        return;
      }
      OnUnbindMobileByUin(localname_req_status._ret_code);
      return;
    }
    OnException(new Exception("unhandle name operation cmd"), util.ASYN_TRANSPORT);
    util.LOGW("OnRequestName unhandle cmd", "", this.mG._context, paramString);
  }

  public void OnRequestRegister(String paramString, long paramLong1, long paramLong2, TransReqContext paramTransReqContext, int paramInt)
  {
    if (paramInt != 0)
    {
      if (this.mListener != null)
      {
        this.mListener.OnRegError(paramInt);
        return;
      }
      OnRegError(paramInt);
      return;
    }
    reg_status localreg_status = this.mRegStatus;
    if (paramTransReqContext.get_subcmd() == 1)
    {
      int i1 = reg_request.parse_checkvalid_rsp(paramTransReqContext.get_body(), localreg_status);
      if (i1 != 0)
      {
        if (this.mListener != null)
        {
          this.mListener.OnRegError(i1);
          return;
        }
        OnRegError(i1);
        return;
      }
      if (localreg_status.sec_ctrl_code == 0)
      {
        if (localreg_status.return_msg == null);
        for (String str = ""; this.mListener != null; str = new String(localreg_status.return_msg))
        {
          this.mListener.OnRegCheckDownloadMsg(localreg_status.telnum_verify_result, str);
          return;
        }
        OnRegCheckDownloadMsg(localreg_status.telnum_verify_result, str);
        return;
      }
      if (localreg_status.sec_ctrl_code == 2)
      {
        if (this.mListener != null)
        {
          this.mListener.OnRegCheckUploadMsg(new String(localreg_status.recvnum), new String(localreg_status.sendmsg));
          return;
        }
        OnRegCheckUploadMsg(new String(localreg_status.recvnum), new String(localreg_status.sendmsg));
        return;
      }
      if (localreg_status.sec_ctrl_code == 3)
      {
        if (this.mListener != null)
        {
          this.mListener.OnRegCheckValidUrl(new String(localreg_status.url));
          return;
        }
        OnRegCheckValidUrl(new String(localreg_status.url));
        return;
      }
      util.LOGW("OnRequestRegister unhandle return code while cmd>=1 && cmd <= 4, code:", new Integer(localreg_status.sec_ctrl_code).toString(), this.mG._context, paramString);
      if (this.mListener != null)
      {
        this.mListener.OnRegError(localreg_status.sec_ctrl_code);
        return;
      }
      OnRegError(localreg_status.sec_ctrl_code);
      return;
    }
    if (paramTransReqContext.get_subcmd() == 3)
    {
      int n = reg_request.parse_checkvalid_rsp(paramTransReqContext.get_body(), localreg_status);
      if (n != 0)
      {
        if (this.mListener != null)
        {
          this.mListener.OnRegError(n);
          return;
        }
        OnRegError(n);
        return;
      }
      if (localreg_status.sec_ctrl_code == 0)
      {
        if (this.mListener != null)
        {
          this.mListener.OnRegQueryClientSendedMsgStatus(localreg_status.sec_ctrl_code, localreg_status.next_check_time, localreg_status.total_time_out);
          return;
        }
        OnRegQueryClientSendedMsgStatus(localreg_status.sec_ctrl_code, localreg_status.next_check_time, localreg_status.total_time_out);
        return;
      }
      if (localreg_status.sec_ctrl_code == 3)
      {
        if (this.mListener != null)
        {
          this.mListener.OnRegCheckValidUrl(new String(localreg_status.url));
          return;
        }
        OnRegCheckValidUrl(new String(localreg_status.url));
        return;
      }
      if (localreg_status.sec_ctrl_code == 4)
      {
        if (this.mListener != null)
        {
          this.mListener.OnRegQueryClientSendedMsgStatus(localreg_status.sec_ctrl_code, localreg_status.next_check_time, localreg_status.total_time_out);
          return;
        }
        OnRegQueryClientSendedMsgStatus(localreg_status.sec_ctrl_code, localreg_status.next_check_time, localreg_status.total_time_out);
        return;
      }
      util.LOGW("OnRequestRegister unhandle return code while cmd>=1 && cmd <= 4, code:", new Integer(localreg_status.sec_ctrl_code).toString(), this.mG._context, paramString);
      if (this.mListener != null)
      {
        this.mListener.OnRegError(localreg_status.sec_ctrl_code);
        return;
      }
      OnRegError(localreg_status.sec_ctrl_code);
      return;
    }
    if (paramTransReqContext.get_subcmd() == 4)
    {
      int m = reg_request.parse_checkvalid_rsp(paramTransReqContext.get_body(), localreg_status);
      if (m != 0)
      {
        if (this.mListener != null)
        {
          this.mListener.OnRegError(m);
          return;
        }
        OnRegError(m);
        return;
      }
      if (localreg_status.sec_ctrl_code == 0)
      {
        if (this.mListener != null)
        {
          this.mListener.OnRegRequestServerResendMsg(localreg_status.sec_ctrl_code, localreg_status.next_check_time, localreg_status.total_time_out);
          return;
        }
        OnRegRequestServerResendMsg(localreg_status.sec_ctrl_code, localreg_status.next_check_time, localreg_status.total_time_out);
        return;
      }
      if (localreg_status.sec_ctrl_code == 3)
      {
        if (this.mListener != null)
        {
          this.mListener.OnRegCheckValidUrl(new String(localreg_status.url));
          return;
        }
        OnRegCheckValidUrl(new String(localreg_status.url));
        return;
      }
      if (localreg_status.sec_ctrl_code == 5)
      {
        if (this.mListener != null)
        {
          this.mListener.OnRegRequestServerResendMsg(localreg_status.sec_ctrl_code, localreg_status.next_check_time, localreg_status.total_time_out);
          return;
        }
        OnRegRequestServerResendMsg(localreg_status.sec_ctrl_code, localreg_status.next_check_time, localreg_status.total_time_out);
        return;
      }
      util.LOGW("OnRequestRegister unhandle return code while cmd>=1 && cmd <= 4, code:", new Integer(localreg_status.sec_ctrl_code).toString(), this.mG._context, paramString);
      if (this.mListener != null)
      {
        this.mListener.OnRegError(localreg_status.sec_ctrl_code);
        return;
      }
      OnRegError(localreg_status.sec_ctrl_code);
      return;
    }
    if (paramTransReqContext.get_subcmd() == 5)
    {
      int k = reg_request.parse_0x5_rsp(paramTransReqContext.get_body(), localreg_status);
      if (k != 0)
      {
        if (this.mListener != null)
        {
          this.mListener.OnRegError(k);
          return;
        }
        OnRegError(k);
        return;
      }
      if (this.mListener != null)
      {
        this.mListener.OnRegSubmitMsgChk(localreg_status.sec_ctrl_code);
        return;
      }
      OnRegSubmitMsgChk(localreg_status.sec_ctrl_code);
      return;
    }
    if (paramTransReqContext.get_subcmd() == 6)
    {
      int j = reg_request.parse_0x6_rsp(paramTransReqContext.get_body(), localreg_status);
      if (j != 0)
      {
        if (this.mListener != null)
        {
          this.mListener.OnRegError(j);
          return;
        }
        OnRegError(j);
        return;
      }
      if (this.mListener != null)
      {
        this.mListener.OnRegGetAccount(localreg_status.sec_ctrl_code, localreg_status.uin, localreg_status.supersig);
        return;
      }
      OnRegGetAccount(localreg_status.sec_ctrl_code, localreg_status.uin, localreg_status.supersig);
      return;
    }
    if (paramTransReqContext.get_subcmd() == 7)
    {
      int i = reg_request.parse_0x7_rsp(paramTransReqContext.get_body(), localreg_status);
      if (i != 0)
      {
        if (this.mListener != null)
        {
          this.mListener.OnRegError(i);
          return;
        }
        OnRegError(i);
        return;
      }
      if (this.mListener != null)
      {
        this.mListener.OnRegQueryAccount(localreg_status.sec_ctrl_code, localreg_status.promptinfo);
        return;
      }
      OnRegQueryAccount(localreg_status.sec_ctrl_code, localreg_status.promptinfo);
      return;
    }
    if (this.mListener != null)
      this.mListener.OnRegError(-1010);
    while (true)
    {
      util.LOGW("OnRequestRegister unhandle cmd", "", this.mG._context, paramString);
      return;
      OnRegError(-1010);
    }
  }

  public int RefreshPictureData(String paramString, int paramInt)
  {
    if (paramInt == 0)
    {
      new HelperThread(this, this.mHelperHandler, paramString).RunReq(util.ASYN_REFLUSH_IMAGE);
      return -1001;
    }
    util.LOGI("user:" + paramString + " RefreshPictureData ...", this.mG._context, this.mG._uin, 0);
    monitorenter;
    try
    {
      int i = this.mFlushimage.make_request();
      if (i == 2)
        i = 0;
      monitorexit;
      util.LOGI("user:" + paramString + " RefreshPictureData ret=" + i, this.mG._context, this.mG._uin, 1);
      return i;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int RefreshPictureData(String paramString, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    if (paramInt == 0)
    {
      new HelperThread(this, this.mHelperHandler, paramString, paramWUserSigInfo).RunReq(util.ASYN_NEW_REFLUSH_IMAGE);
      return -1001;
    }
    util.LOGI("user:" + paramString + " RefreshPictureData ...", this.mG._context, this.mG._uin, 0);
    monitorenter;
    try
    {
      int i = this.mFlushimage.make_request();
      if (i == 2)
        i = 0;
      monitorexit;
      util.LOGI("user:" + paramString + " RefreshPictureData ret=" + i, this.mG._context, this.mG._uin, 1);
      return i;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int RefreshSMSData(String paramString, int paramInt)
  {
    if (paramInt == 0)
    {
      new HelperThread(this, this.mHelperHandler).RunReq(util.ASYN_REFLUSH_SMS);
      return -1001;
    }
    monitorenter;
    try
    {
      int i = this.mFlushSms.make_request();
      if (i == 160)
        i = 0;
      monitorexit;
      this.mG.close_connect();
      return i;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int RegGetAccount(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    reg_request_get_account localreg_request_get_account = new reg_request_get_account();
    TransReqContext localTransReqContext = new TransReqContext();
    reg_status localreg_status = this.mRegStatus;
    localreg_status.msgchk = paramString1.getBytes();
    localTransReqContext.set_register_req();
    localTransReqContext.set_subcmd(localreg_request_get_account.get_cmd());
    localTransReqContext._body = localreg_request_get_account.get_request(localreg_status.token, paramString1.getBytes(), paramString3.getBytes(), paramInt, localreg_status.name.getBytes(), paramString2.getBytes());
    return RequestTransport(0, 0, null, 0L, localreg_status.role, localTransReqContext);
  }

  public int RegQueryAccount(int paramInt, byte[] paramArrayOfByte, long paramLong)
  {
    this.mRegStatus = new reg_status();
    this.mRegStatus.name = new String(paramArrayOfByte);
    reg_request_query_account_available localreg_request_query_account_available = new reg_request_query_account_available();
    TransReqContext localTransReqContext = new TransReqContext();
    reg_status localreg_status = this.mRegStatus;
    localTransReqContext.set_register_req();
    localTransReqContext.set_subcmd(localreg_request_query_account_available.get_cmd());
    localTransReqContext._body = localreg_request_query_account_available.get_request(paramInt, paramArrayOfByte, paramLong);
    return RequestTransport(0, 0, null, 0L, localreg_status.role, localTransReqContext);
  }

  public int RegQueryClientSendedMsgStatus()
  {
    reg_request_query_msg_status localreg_request_query_msg_status = new reg_request_query_msg_status();
    TransReqContext localTransReqContext = new TransReqContext();
    reg_status localreg_status = this.mRegStatus;
    localTransReqContext.set_register_req();
    localTransReqContext.set_subcmd(localreg_request_query_msg_status.get_cmd());
    localTransReqContext._body = localreg_request_query_msg_status.get_request(localreg_status.token, this.mRegStatus.sendmsg);
    return RequestTransport(0, 0, null, 0L, localreg_status.role, localTransReqContext);
  }

  public int RegRefreshImg()
  {
    reg_request_submit_checkimg localreg_request_submit_checkimg = new reg_request_submit_checkimg();
    TransReqContext localTransReqContext = new TransReqContext();
    reg_status localreg_status = this.mRegStatus;
    localTransReqContext.set_register_req();
    localTransReqContext.set_subcmd(localreg_request_submit_checkimg.get_cmd());
    localTransReqContext._body = localreg_request_submit_checkimg.get_request(1, localreg_status.token, new byte[0], new byte[0]);
    return RequestTransport(0, 0, null, 0L, localreg_status.role, localTransReqContext);
  }

  public int RegRequestServerResendMsg()
  {
    reg_request_resend_msg localreg_request_resend_msg = new reg_request_resend_msg();
    TransReqContext localTransReqContext = new TransReqContext();
    reg_status localreg_status = this.mRegStatus;
    localTransReqContext.set_register_req();
    localTransReqContext.set_subcmd(localreg_request_resend_msg.get_cmd());
    localTransReqContext._body = localreg_request_resend_msg.get_request(localreg_status.token, this.mRegStatus.mobile.getBytes());
    return RequestTransport(0, 0, null, 0L, localreg_status.role, localTransReqContext);
  }

  public int RegSubmitImg(byte[] paramArrayOfByte)
  {
    reg_request_submit_checkimg localreg_request_submit_checkimg = new reg_request_submit_checkimg();
    TransReqContext localTransReqContext = new TransReqContext();
    reg_status localreg_status = this.mRegStatus;
    localTransReqContext.set_register_req();
    localTransReqContext.set_subcmd(localreg_request_submit_checkimg.get_cmd());
    localTransReqContext._body = localreg_request_submit_checkimg.get_request(0, localreg_status.token, paramArrayOfByte, localreg_status.sigbuf);
    return RequestTransport(0, 0, null, 0L, localreg_status.role, localTransReqContext);
  }

  public int RegSubmitMobile(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2, int paramInt3, long paramLong)
  {
    this.mRegStatus.mobile = new String(paramArrayOfByte1);
    reg_request_submit_mobile localreg_request_submit_mobile = new reg_request_submit_mobile();
    TransReqContext localTransReqContext = new TransReqContext();
    reg_status localreg_status = this.mRegStatus;
    localreg_status.tel = paramArrayOfByte1;
    localreg_status.appid = paramLong;
    localTransReqContext.set_register_req();
    localTransReqContext.set_subcmd(localreg_request_submit_mobile.get_cmd());
    localTransReqContext._body = localreg_request_submit_mobile.get_request(paramArrayOfByte1, paramArrayOfByte2, paramArrayOfByte3, paramInt1, paramInt2, paramInt3, paramLong, null, util.get_IMEI(this.mContext));
    return RequestTransport(0, 0, null, 0L, localreg_status.role, localTransReqContext);
  }

  public int RegSubmitMsgChk(String paramString)
  {
    reg_request_submit_msg_chk localreg_request_submit_msg_chk = new reg_request_submit_msg_chk();
    TransReqContext localTransReqContext = new TransReqContext();
    reg_status localreg_status = this.mRegStatus;
    localTransReqContext.set_register_req();
    localTransReqContext.set_subcmd(localreg_request_submit_msg_chk.get_cmd());
    localTransReqContext._body = localreg_request_submit_msg_chk.get_request(localreg_status.token, paramString.getBytes());
    return RequestTransport(0, 0, null, 0L, localreg_status.role, localTransReqContext);
  }

  public int RegisterBroadcastPush(long paramLong1, long paramLong2, int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3)
  {
    monitorenter;
    try
    {
      int i = this.mG.register_push(0L, paramLong1, paramLong2, null, null, paramInt1, paramInt2, paramString1, paramString2, paramString3);
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int RegisterPush(String paramString1, long paramLong1, long paramLong2, int paramInt1, int paramInt2, String paramString2, String paramString3, String paramString4)
  {
    monitorenter;
    try
    {
      WloginSimpleInfo localWloginSimpleInfo = new WloginSimpleInfo();
      boolean bool = GetBasicUserInfo(paramString1, localWloginSimpleInfo).booleanValue();
      int j;
      if (!bool)
        j = -1003;
      while (true)
      {
        return j;
        long l = localWloginSimpleInfo._uin;
        WloginSigInfo localWloginSigInfo = FindUserSig(l, paramLong1);
        if (localWloginSigInfo == null)
        {
          j = -1004;
          continue;
        }
        int i = this.mG.register_push(l, paramLong1, paramLong2, localWloginSigInfo._userStSig, localWloginSigInfo._userSt_Key, paramInt1, paramInt2, paramString2, paramString3, paramString4);
        j = i;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int RequestTransport(int paramInt1, int paramInt2, String paramString, long paramLong1, long paramLong2, TransReqContext paramTransReqContext)
  {
    if (paramInt1 == 0)
    {
      new HelperThread(this, this.mHelperHandler, paramInt2, paramString, paramLong1, paramLong2, paramTransReqContext).RunReq(util.ASYN_TRANSPORT);
      return -1001;
    }
    util.LOGI("user:" + paramString + " encrypt:" + paramInt2 + " appid:" + paramLong1 + " role:" + paramLong2 + " RequestTransport ...", this.mG._context, paramString, 0);
    request_transport localrequest_transport = this.mTransport;
    monitorenter;
    if (paramInt2 != 0);
    while (true)
    {
      try
      {
        WloginSimpleInfo localWloginSimpleInfo2 = new WloginSimpleInfo();
        if (paramString != null)
        {
          if (GetBasicUserInfo(paramString, localWloginSimpleInfo2).booleanValue())
            continue;
          break label356;
          this.mG.close_transport_connect();
          monitorexit;
          util.LOGI("user:" + paramString + " encrypt:" + paramInt2 + " appid:" + paramLong1 + " role:" + paramLong2 + " RequestTransport ret=" + i, this.mG._context, paramString, 1);
          return i;
          WloginSigInfo localWloginSigInfo = this.mG.get_siginfo(localWloginSimpleInfo2._uin, paramLong1);
          if (localWloginSigInfo != null)
            continue;
          i = -1004;
          continue;
          i = this.mTransport.make_request(localWloginSimpleInfo2._uin, paramTransReqContext, localWloginSigInfo._userStSig, localWloginSigInfo._userSt_Key, paramLong1, paramLong2);
          continue;
          WloginSimpleInfo localWloginSimpleInfo1 = new WloginSimpleInfo();
          if ((paramString != null) && (GetBasicUserInfo(paramString, localWloginSimpleInfo1).booleanValue()))
            continue;
          localWloginSimpleInfo1._uin = 0L;
          i = this.mTransport.make_request(localWloginSimpleInfo1._uin, paramTransReqContext, null, null, paramLong1, paramLong2);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      label356: int i = -1003;
    }
  }

  public int RequestTransport(int paramInt1, int paramInt2, String paramString, long paramLong1, long paramLong2, TransReqContext paramTransReqContext, WUserSigInfo paramWUserSigInfo)
  {
    if (paramInt1 == 0)
    {
      new HelperThread(this, this.mHelperHandler, paramInt2, paramString, paramLong1, paramLong2, paramTransReqContext, paramWUserSigInfo).RunReq(util.ASYN_NEW_TRANSPORT);
      return -1001;
    }
    util.LOGI("user:" + paramString + " encrypt:" + paramInt2 + " appid:" + paramLong1 + " role:" + paramLong2 + " RequestTransport ...", this.mG._context, paramString, 0);
    request_transport localrequest_transport = this.mTransport;
    monitorenter;
    if (paramInt2 != 0);
    while (true)
    {
      try
      {
        WloginSimpleInfo localWloginSimpleInfo2 = new WloginSimpleInfo();
        if (paramString != null)
        {
          if (GetBasicUserInfo(paramString, localWloginSimpleInfo2).booleanValue())
            continue;
          break label358;
          this.mG.close_transport_connect();
          monitorexit;
          util.LOGI("user:" + paramString + " encrypt:" + paramInt2 + " appid:" + paramLong1 + " role:" + paramLong2 + " RequestTransport ret=" + i, this.mG._context, paramString, 1);
          return i;
          WloginSigInfo localWloginSigInfo = this.mG.get_siginfo(localWloginSimpleInfo2._uin, paramLong1);
          if (localWloginSigInfo != null)
            continue;
          i = -1004;
          continue;
          i = this.mTransport.make_request(localWloginSimpleInfo2._uin, paramTransReqContext, localWloginSigInfo._userStSig, localWloginSigInfo._userSt_Key, paramLong1, paramLong2);
          continue;
          WloginSimpleInfo localWloginSimpleInfo1 = new WloginSimpleInfo();
          if ((paramString != null) && (GetBasicUserInfo(paramString, localWloginSimpleInfo1).booleanValue()))
            continue;
          localWloginSimpleInfo1._uin = 0L;
          i = this.mTransport.make_request(localWloginSimpleInfo1._uin, paramTransReqContext, null, null, paramLong1, paramLong2);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      label358: int i = -1003;
    }
  }

  public int RequestTransportMsf(int paramInt1, int paramInt2, String paramString, long paramLong1, long paramLong2, TransReqContext paramTransReqContext)
  {
    if (paramInt1 == 0)
    {
      new HelperThread(this, this.mHelperHandler, paramInt2, paramString, paramLong1, paramLong2, paramTransReqContext).RunReq(util.ASYN_TRANSPORT_MSF);
      return -1001;
    }
    util.LOGI("user:" + paramString + " encrypt:" + paramInt2 + " appid:" + paramLong1 + " role:" + paramLong2 + " RequestTransportMsf ...", this.mG._context, paramString, 0);
    request_transport localrequest_transport = this.mTransport;
    monitorenter;
    if (paramInt2 != 0);
    while (true)
    {
      try
      {
        WloginSimpleInfo localWloginSimpleInfo2 = new WloginSimpleInfo();
        if (paramString != null)
        {
          if (GetBasicUserInfo(paramString, localWloginSimpleInfo2).booleanValue())
            continue;
          break label435;
          this.mG.close_transport_connect();
          monitorexit;
          util.LOGI("user:" + paramString + " encrypt:" + paramInt2 + " appid:" + paramLong1 + " role:" + paramLong2 + " RequestTransportMsf ret=" + i, this.mG._context, paramString, 1);
          return i;
          WloginSigInfo localWloginSigInfo2 = this.mG.get_siginfo(localWloginSimpleInfo2._uin, paramLong1);
          if (localWloginSigInfo2 != null)
            continue;
          i = -1004;
          continue;
          i = this.mTransport.make_request_msf(localWloginSimpleInfo2._uin, paramTransReqContext, localWloginSigInfo2._userStSig, localWloginSigInfo2._userSt_Key, localWloginSigInfo2._TGT, paramLong1, paramLong2);
          continue;
          if ((!util.check_uin_account(paramString).booleanValue()) || (Long.parseLong(paramString) != 0L))
            continue;
          i = this.mTransport.make_request_msf(0L, paramTransReqContext, null, null, new byte[0], paramLong1, paramLong2);
          continue;
          WloginSimpleInfo localWloginSimpleInfo1 = new WloginSimpleInfo();
          if ((paramString == null) || (!GetBasicUserInfo(paramString, localWloginSimpleInfo1).booleanValue()))
            break label443;
          WloginSigInfo localWloginSigInfo1 = this.mG.get_siginfo(localWloginSimpleInfo1._uin, paramLong1);
          if (localWloginSigInfo1 != null)
            continue;
          i = -1004;
          continue;
          i = this.mTransport.make_request_msf(localWloginSimpleInfo1._uin, paramTransReqContext, null, null, localWloginSigInfo1._TGT, paramLong1, paramLong2);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      label435: int i = -1003;
      continue;
      label443: i = -1003;
    }
  }

  public void ResetThisHelper()
  {
    CancelRequest();
    this.mG = new request_global(null);
    this.mPing = new request_ping(this.mG);
    this.mTgtgt = new request_TGTGT(this.mG);
    this.mCheckimage = new request_checkimage(this.mG);
    this.mFlushimage = new request_flushimage(this.mG);
    this.mGetuin = new request_getuin(this.mG);
    this.mChangeSig = new request_change_sig(this.mG);
    this.mTransport = new request_transport(this.mG);
    this.mFlushSms = new request_flush_sms(this.mG);
    this.mCheckSms = new request_check_sms(this.mG);
    this.mDelay = new request_delay(this.mG);
    this.mRemoteLogin = new request_fast_login(this.mG);
    this.mCheckApk = new request_app_signature(this.mG);
    this.mReportError = new request_report_error(this.mG);
    this.mHelperHandler = newHelperHandler(this.mG, this);
    this.mRegStatus = new reg_status();
    this.mNameReqStatus = new name_req_status();
    this.mMainSigMap = 996082;
    this.mSubSigMap = 66560;
    this.mTGTGTMiscBitmap = 892;
    this.mChangeSigMiscBitmap = 628;
    this.mOpenAppid = 715019303L;
    this.mOpenMainSigMap = 16576;
    this.mG.set_context(this.mContext);
    RequestInit(0);
  }

  public void SetAppClientVersion(int paramInt)
  {
    this.mG._app_client_version = paramInt;
  }

  public void SetImgType(int paramInt)
  {
    this.mG._img_type = paramInt;
    this.mTGTGTMiscBitmap = (0x80 | this.mTGTGTMiscBitmap);
  }

  public void SetListener(WtloginListener paramWtloginListener)
  {
    this.mListener = paramWtloginListener;
  }

  public void SetMsfTransportFlag(int paramInt)
  {
    this.mG._msf_transport_flag = paramInt;
    this.mG._ip_addr = new byte[4];
    this.mG._init_time = new byte[0];
    request_global._l_init_time = 0L;
  }

  public void SetOpenSigMap(int paramInt)
  {
    this.mOpenMainSigMap = paramInt;
  }

  public void SetPushTestHost(int paramInt, String paramString)
  {
    this.mG.set_push_test(paramInt, paramString);
  }

  public void SetShareTestHost(int paramInt, String paramString)
  {
    this.mG.set_share_test(paramInt, paramString);
  }

  public void SetSigMap(int paramInt)
  {
    this.mMainSigMap = (paramInt | 0xC0);
  }

  public void SetTestHost(int paramInt, String paramString)
  {
    oicq_request.set_test(paramInt, paramString);
  }

  public void SetTimeOut(int paramInt)
  {
    this.mG._time_out = paramInt;
  }

  public void SetTkTimeOut(long paramLong)
  {
    this.mG._tk_time_out = paramLong;
  }

  public void StopPushService()
  {
    monitorenter;
    try
    {
      this.mG.stop_service(true, false);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void StopShareService()
  {
    monitorenter;
    try
    {
      this.mG.stop_service(false, true);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int StubGetRemoteStWithoutPasswd(String paramString, long paramLong1, long paramLong2, long paramLong3, long paramLong4, int paramInt1, int paramInt2, int paramInt3, int paramInt4, long[] paramArrayOfLong, byte[] paramArrayOfByte1, long paramLong5, long paramLong6, long paramLong7, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, List<WloginRemoteData> paramList, account_sig_info_map paramaccount_sig_info_map)
  {
    util.LOGI("wtlogin login with StubGetRemoteStWithoutPasswd:user:" + paramString + " dwSrcAppid:" + paramLong2 + " dwDstAppid:" + paramLong3 + " dwDstAppPri:" + 0 + " dwMainSigMap:" + paramInt3 + " dwSubDstAppid:" + paramLong4 + " ...", this.mG._context, paramString, 0);
    if (this.mG.get_ping_end_flag() != 0)
      monitorenter;
    while (true)
    {
      label427: label457: int k;
      try
      {
        while (true)
        {
          int i = util.get_saved_network_type(this.mContext);
          this.mG._network_type = util.get_network_type(this.mContext);
          if (i != this.mG._network_type)
          {
            util.set_net_retry_type(this.mContext, 0);
            util.save_network_type(this.mContext, this.mG._network_type);
          }
          this.mG._apn = util.get_apn_string(this.mContext).getBytes();
          this.mG._name = paramString;
          this.mG._uin = 0L;
          this.mG._appid = paramLong3;
          this.mG._sub_appid = paramLong4;
          this.mG._main_sigmap = paramInt3;
          this.mG._sub_appid_list = null;
          this.mG._encrypt_a1 = new byte[0];
          if (paramArrayOfLong != null)
            this.mG._sub_appid_list = ((long[])paramArrayOfLong.clone());
          if (util.check_uin_account(paramString).booleanValue())
            break label457;
          localLong = paramaccount_sig_info_map.get_account(paramString);
          if (localLong != null)
            break label427;
          j = -1003;
          monitorexit;
          this.mG.close_connect();
          util.LOGI("wtlogin login with StubGetRemoteStWithoutPasswd:user:" + paramString + " dwSrcAppid:" + paramLong2 + " dwDstAppid:" + paramLong3 + " dwDstAppPri:" + 0 + " dwMainSigMap:" + paramInt3 + " dwSubDstAppid:" + paramLong4 + " ret=" + j, this.mG._context, this.mG._uin, 1);
          return j;
          try
          {
            Thread.currentThread();
            Thread.sleep(20L);
          }
          catch (Exception localException)
          {
          }
        }
        break;
        String str = paramString;
        WloginSigInfo localWloginSigInfo1 = paramaccount_sig_info_map.get_siginfo(localLong.longValue(), paramLong2);
        if (localWloginSigInfo1 != null)
          continue;
        j = -1004;
        continue;
        Long localLong = Long.valueOf(Long.parseLong(paramString));
        str = "";
        continue;
        this.mG._master_tgt_key = localWloginSigInfo1._TGTKey;
        j = this.mRemoteLogin.make_request(localLong.longValue(), paramLong3, paramLong4, paramInt1, paramInt2, localWloginSigInfo1._TGT, paramInt3, paramInt4, paramArrayOfLong, paramLong2, paramArrayOfByte1, paramLong5, paramLong6, paramLong7, paramArrayOfByte2, paramArrayOfByte3, paramArrayOfByte4, str.getBytes());
        if (j != 0)
          continue;
        WloginSigInfo localWloginSigInfo2 = this.mG.get_siginfo(localLong.longValue(), paramLong3);
        WloginSimpleInfo localWloginSimpleInfo = this.mG.get_simpleinfo(localLong.longValue());
        if ((localWloginSigInfo2 != null) && (localWloginSimpleInfo != null))
        {
          WloginRemoteData localWloginRemoteData = new WloginRemoteData();
          localWloginRemoteData.getLongData().add(localLong);
          localWloginRemoteData.getByteData().add(paramString.getBytes());
          paramList.add(localWloginRemoteData);
          paramList.add(localWloginSimpleInfo.getWloginRemoteData());
          paramList.add(localWloginSigInfo2.getWloginRemoteData());
          if (paramArrayOfLong == null)
            continue;
          k = 0;
          int m = paramArrayOfLong.length;
          if (k >= m)
            continue;
          WloginSigInfo localWloginSigInfo3 = this.mG.get_siginfo(localLong.longValue(), paramArrayOfLong[k]);
          if (localWloginSigInfo3 == null)
            break label738;
          paramList.add(localWloginSigInfo3.getWloginRemoteData());
        }
      }
      finally
      {
        monitorexit;
      }
      int j = -1004;
      continue;
      label738: k++;
    }
  }

  public int UnRegisterBroadcastPush(long paramLong1, long paramLong2, int paramInt)
  {
    monitorenter;
    try
    {
      int i = this.mG.unregister_push(0L, paramLong1, paramLong2, paramInt);
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int UnRegisterPush(String paramString, long paramLong1, long paramLong2, int paramInt)
  {
    monitorenter;
    try
    {
      WloginSimpleInfo localWloginSimpleInfo = new WloginSimpleInfo();
      boolean bool = GetBasicUserInfo(paramString, localWloginSimpleInfo).booleanValue();
      int i;
      if (!bool)
        i = -1003;
      while (true)
      {
        return i;
        long l = localWloginSimpleInfo._uin;
        if (FindUserSig(l, paramLong1) == null)
        {
          i = -1004;
          continue;
        }
        int j = this.mG.unregister_push(l, paramLong1, paramLong2, paramInt);
        i = j;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int UnbindMobileByUin(String paramString1, long paramLong, String paramString2)
  {
    WloginSimpleInfo localWloginSimpleInfo = new WloginSimpleInfo();
    if (!GetBasicUserInfo(paramString1, localWloginSimpleInfo).booleanValue())
      return -1003;
    long l = localWloginSimpleInfo._uin;
    WloginSigInfo localWloginSigInfo = FindUserSig(l, paramLong);
    if (localWloginSigInfo == null)
      return -1004;
    name_unbind localname_unbind = new name_unbind();
    TransReqContext localTransReqContext = new TransReqContext();
    localTransReqContext.set_name_func_req();
    localTransReqContext.set_subcmd(localname_unbind.get_cmd());
    localTransReqContext._body = localname_unbind.get_request(l, paramLong, localWloginSigInfo._sKey, paramString2.getBytes());
    return RequestTransport(0, 1, paramString1, paramLong, localname_unbind._role, localTransReqContext);
  }

  public int VerifyCode(String paramString, long paramLong, boolean paramBoolean, byte[] paramArrayOfByte, int[] paramArrayOfInt, int paramInt)
  {
    WloginSimpleInfo localWloginSimpleInfo = new WloginSimpleInfo();
    if (!GetBasicUserInfo(paramString, localWloginSimpleInfo).booleanValue())
      return -1003;
    long l = localWloginSimpleInfo._uin;
    WloginSigInfo localWloginSigInfo = FindUserSig(l, paramLong);
    if (localWloginSigInfo == null)
      return -1004;
    verify_code localverify_code = new verify_code();
    TransReqContext localTransReqContext = new TransReqContext();
    localTransReqContext.set_code2d_func_req();
    localTransReqContext.set_subcmd(localverify_code.get_cmd());
    localTransReqContext._body = localverify_code.get_request(l, paramLong, paramBoolean, paramArrayOfByte, paramArrayOfInt, localWloginSigInfo._TGT, this.mG._IMEI, paramInt);
    return RequestTransport(0, 1, paramString, paramLong, localverify_code._role, localTransReqContext);
  }

  public class HelperHandler extends Handler
  {
    long mDwAppid;
    long mDwDstAppPri;
    long mDwDstAppid;
    long[] mDwDstSubAppidList;
    int mDwMainSigMap;
    long[] mDwSubAppidList;
    long mDwSubDstAppid;
    int mEncrypt;
    request_global mG;
    WtloginHelper mHelper;
    byte[] mPictureData;
    boolean mPwdMd5;
    TransReqContext mReqContext;
    long mRole;
    byte[][] mST;
    long mUIN;
    String mUserAccount;
    byte[] mUserInput;
    String mUserPasswd;
    WUserSigInfo mUserSigInfo;

    public HelperHandler(request_global paramWtloginHelper, WtloginHelper arg3)
    {
      this.mG = paramWtloginHelper;
      Object localObject;
      this.mHelper = localObject;
    }

    public void handleMessage(Message paramMessage)
    {
      int i = paramMessage.what;
      byte[] arrayOfByte = paramMessage.getData().getByteArray("param");
      util.LOGD(getClass().getName() + "handleMessage ReqType=" + i);
      if (this.mG._canceled != 0);
      while (true)
      {
        return;
        try
        {
          int j = util.ASYN_EXCEPTION;
          if (i == j)
          {
            try
            {
              localException2 = new Exception(new String(arrayOfByte));
              if (this.mHelper.mListener == null)
                break label171;
              this.mHelper.mListener.OnException(localException2, paramMessage.arg1);
              return;
            }
            catch (Exception localException3)
            {
              if (this.mHelper.mListener == null)
                break label185;
            }
            this.mHelper.mListener.OnException(localException3, paramMessage.arg1);
            return;
          }
        }
        catch (Exception localException1)
        {
          Exception localException2;
          if (this.mHelper.mListener != null)
          {
            this.mHelper.mListener.OnException(localException1, paramMessage.arg1);
            return;
            label171: this.mHelper.OnException(localException2, paramMessage.arg1);
            return;
            label185: this.mHelper.OnException(localException3, paramMessage.arg1);
            return;
            ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
            ObjectInputStream localObjectInputStream = new ObjectInputStream(localByteArrayInputStream);
            if (i == util.ASYN_GET_ST_WITH_PWD)
            {
              this.mUserAccount = ((String)localObjectInputStream.readObject());
              this.mDwAppid = ((Long)localObjectInputStream.readObject()).longValue();
              this.mDwMainSigMap = ((Integer)localObjectInputStream.readObject()).intValue();
              this.mDwSubDstAppid = ((Long)localObjectInputStream.readObject()).longValue();
              this.mDwSubAppidList = ((long[])localObjectInputStream.readObject());
              this.mPwdMd5 = ((Boolean)localObjectInputStream.readObject()).booleanValue();
              this.mUserPasswd = ((String)localObjectInputStream.readObject());
              this.mUserSigInfo = ((WUserSigInfo)localObjectInputStream.readObject());
              this.mST = ((byte[][])localObjectInputStream.readObject());
              int i10 = ((Integer)localObjectInputStream.readObject()).intValue();
              if (this.mDwSubAppidList == null)
              {
                if (this.mHelper.mListener != null)
                {
                  this.mHelper.mListener.OnGetStWithPasswd(this.mUserAccount, this.mDwAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mUserPasswd, this.mUserSigInfo, i10);
                  return;
                }
                this.mHelper.OnGetStWithPasswd(this.mUserAccount, this.mDwAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mUserPasswd, this.mUserSigInfo, i10);
                return;
              }
              if (this.mHelper.mListener != null)
              {
                this.mHelper.mListener.OnGetStWithPasswd(this.mUserAccount, this.mDwAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwSubAppidList, this.mUserPasswd, this.mUserSigInfo, this.mST, i10);
                return;
              }
              this.mHelper.OnGetStWithPasswd(this.mUserAccount, this.mDwAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwSubAppidList, this.mUserPasswd, this.mUserSigInfo, this.mST, i10);
              return;
            }
            if (i == util.ASYN_REFLUSH_IMAGE)
            {
              this.mUserAccount = ((String)localObjectInputStream.readObject());
              this.mPictureData = this.mHelper.GetPictureData(this.mUserAccount);
              int i9 = ((Integer)localObjectInputStream.readObject()).intValue();
              if (this.mHelper.mListener != null)
              {
                this.mHelper.mListener.OnRefreshPictureData(this.mUserAccount, this.mPictureData, i9);
                return;
              }
              this.mHelper.OnRefreshPictureData(this.mUserAccount, this.mPictureData, i9);
              return;
            }
            if (i == util.ASYN_NEW_REFLUSH_IMAGE)
            {
              this.mUserAccount = ((String)localObjectInputStream.readObject());
              this.mUserSigInfo = ((WUserSigInfo)localObjectInputStream.readObject());
              this.mPictureData = this.mHelper.GetPictureData(this.mUserAccount);
              int i8 = ((Integer)localObjectInputStream.readObject()).intValue();
              if (this.mHelper.mListener != null)
              {
                this.mHelper.mListener.OnRefreshPictureData(this.mUserAccount, this.mUserSigInfo, this.mPictureData, i8);
                return;
              }
              this.mHelper.OnRefreshPictureData(this.mUserAccount, this.mUserSigInfo, this.mPictureData, i8);
              return;
            }
            if (i == util.ASYN_REFLUSH_SMS)
            {
              this.mUserAccount = ((String)localObjectInputStream.readObject());
              this.mPictureData = this.mHelper.GetPictureData(this.mUserAccount);
              int i7 = ((Integer)localObjectInputStream.readObject()).intValue();
              if (this.mHelper.mListener != null)
              {
                this.mHelper.mListener.OnRefreshSMSData(this.mUserAccount, this.mPictureData, i7);
                return;
              }
              this.mHelper.OnRefreshSMSData(this.mUserAccount, this.mPictureData, i7);
              return;
            }
            if (i == util.ASYN_CHECK_IMAGE)
            {
              this.mUserAccount = ((String)localObjectInputStream.readObject());
              this.mUserInput = ((byte[])localObjectInputStream.readObject());
              this.mUserSigInfo = ((WUserSigInfo)localObjectInputStream.readObject());
              this.mST = ((byte[][])localObjectInputStream.readObject());
              int i6 = ((Integer)localObjectInputStream.readObject()).intValue();
              if (this.mST == null)
              {
                if (this.mHelper.mListener != null)
                {
                  this.mHelper.mListener.OnCheckPictureAndGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, i6);
                  return;
                }
                this.mHelper.OnCheckPictureAndGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, i6);
                return;
              }
              if (this.mHelper.mListener != null)
              {
                this.mHelper.mListener.OnCheckPictureAndGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, this.mST, i6);
                return;
              }
              this.mHelper.OnCheckPictureAndGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, this.mST, i6);
              return;
            }
            if (i == util.ASYN_CHECK_SMS)
            {
              this.mUserAccount = ((String)localObjectInputStream.readObject());
              this.mUserInput = ((byte[])localObjectInputStream.readObject());
              this.mUserSigInfo = ((WUserSigInfo)localObjectInputStream.readObject());
              this.mST = ((byte[][])localObjectInputStream.readObject());
              int i5 = ((Integer)localObjectInputStream.readObject()).intValue();
              if (this.mST == null)
              {
                if (this.mHelper.mListener != null)
                {
                  this.mHelper.mListener.OnCheckSMSGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, i5);
                  return;
                }
                this.mHelper.OnCheckSMSGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, i5);
                return;
              }
              if (this.mHelper.mListener != null)
              {
                this.mHelper.mListener.OnCheckSMSGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, this.mST, i5);
                return;
              }
              this.mHelper.OnCheckSMSGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, this.mST, i5);
              return;
            }
            if (i == util.ASYN_GET_ST_WITHOUT_PWD)
            {
              this.mUserAccount = ((String)localObjectInputStream.readObject());
              this.mDwAppid = ((Long)localObjectInputStream.readObject()).longValue();
              this.mDwDstAppid = ((Long)localObjectInputStream.readObject()).longValue();
              this.mDwDstAppPri = ((Long)localObjectInputStream.readObject()).longValue();
              this.mDwMainSigMap = ((Integer)localObjectInputStream.readObject()).intValue();
              this.mDwSubDstAppid = ((Long)localObjectInputStream.readObject()).longValue();
              this.mDwDstSubAppidList = ((long[])localObjectInputStream.readObject());
              this.mUserSigInfo = ((WUserSigInfo)localObjectInputStream.readObject());
              this.mST = ((byte[][])localObjectInputStream.readObject());
              int i4 = ((Integer)localObjectInputStream.readObject()).intValue();
              if (this.mDwDstSubAppidList == null)
              {
                if (this.mHelper.mListener != null)
                {
                  this.mHelper.mListener.OnGetStWithoutPasswd(this.mUserAccount, this.mDwAppid, this.mDwDstAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mUserSigInfo, i4);
                  return;
                }
                this.mHelper.OnGetStWithoutPasswd(this.mUserAccount, this.mDwAppid, this.mDwDstAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mUserSigInfo, i4);
                return;
              }
              if (this.mHelper.mListener != null)
              {
                this.mHelper.mListener.OnGetStWithoutPasswd(this.mUserAccount, this.mDwAppid, this.mDwDstAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwDstSubAppidList, this.mUserSigInfo, this.mST, i4);
                return;
              }
              this.mHelper.OnGetStWithoutPasswd(this.mUserAccount, this.mDwAppid, this.mDwDstAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwDstSubAppidList, this.mUserSigInfo, this.mST, i4);
              return;
            }
            if (i == util.ASYN_GET_REMOTE_ST_WITHOUT_PWD)
            {
              this.mUserAccount = ((String)localObjectInputStream.readObject());
              this.mDwAppid = ((Long)localObjectInputStream.readObject()).longValue();
              this.mDwDstAppid = ((Long)localObjectInputStream.readObject()).longValue();
              this.mDwDstAppPri = ((Long)localObjectInputStream.readObject()).longValue();
              this.mDwMainSigMap = ((Integer)localObjectInputStream.readObject()).intValue();
              this.mDwSubDstAppid = ((Long)localObjectInputStream.readObject()).longValue();
              this.mDwDstSubAppidList = ((long[])localObjectInputStream.readObject());
              this.mUserSigInfo = ((WUserSigInfo)localObjectInputStream.readObject());
              this.mST = ((byte[][])localObjectInputStream.readObject());
              int i3 = ((Integer)localObjectInputStream.readObject()).intValue();
              if (this.mHelper.mListener != null)
              {
                this.mHelper.mListener.OnGetRemoteStWithoutPasswd(this.mUserAccount, this.mDwAppid, this.mDwDstAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwDstSubAppidList, this.mUserSigInfo, this.mST, i3);
                return;
              }
              this.mHelper.OnGetRemoteStWithoutPasswd(this.mUserAccount, this.mDwAppid, this.mDwDstAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwDstSubAppidList, this.mUserSigInfo, this.mST, i3);
              return;
            }
            if (i == util.ASYN_PING)
            {
              int i2 = ((Integer)localObjectInputStream.readObject()).intValue();
              if (this.mHelper.mListener != null)
              {
                this.mHelper.mListener.OnInit(i2);
                return;
              }
              this.mHelper.OnInit(i2);
              return;
            }
            if (i == util.ASYN_REPORT)
              continue;
            if (i == util.ASYN_TRANSPORT)
            {
              this.mEncrypt = ((Integer)localObjectInputStream.readObject()).intValue();
              this.mUserAccount = ((String)localObjectInputStream.readObject());
              this.mDwAppid = ((Long)localObjectInputStream.readObject()).longValue();
              this.mRole = ((Long)localObjectInputStream.readObject()).longValue();
              this.mReqContext = ((TransReqContext)localObjectInputStream.readObject());
              int i1 = ((Integer)localObjectInputStream.readObject()).intValue();
              if (this.mReqContext.is_register_req())
              {
                this.mHelper.OnRequestRegister(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, i1);
                return;
              }
              if (this.mReqContext.is_name_func_req())
              {
                this.mHelper.OnRequestName(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, i1);
                return;
              }
              if (this.mReqContext.is_code2d_func_req())
              {
                this.mHelper.OnRequestCode2d(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, i1);
                return;
              }
              if (this.mHelper.mListener != null)
              {
                this.mHelper.mListener.OnRequestTransport(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, i1);
                return;
              }
              this.mHelper.OnRequestTransport(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, i1);
              return;
            }
            if (i == util.ASYN_NEW_TRANSPORT)
            {
              this.mEncrypt = ((Integer)localObjectInputStream.readObject()).intValue();
              this.mUserAccount = ((String)localObjectInputStream.readObject());
              this.mDwAppid = ((Long)localObjectInputStream.readObject()).longValue();
              this.mRole = ((Long)localObjectInputStream.readObject()).longValue();
              this.mReqContext = ((TransReqContext)localObjectInputStream.readObject());
              this.mUserSigInfo = ((WUserSigInfo)localObjectInputStream.readObject());
              int n = ((Integer)localObjectInputStream.readObject()).intValue();
              if (this.mReqContext.is_oidb_func_req())
              {
                this.mHelper.OnOidbRequest(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, this.mUserSigInfo, n);
                return;
              }
              if (this.mHelper.mListener != null)
              {
                this.mHelper.mListener.OnRequestTransport(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, this.mUserSigInfo, n);
                return;
              }
              this.mHelper.OnRequestTransport(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, this.mUserSigInfo, n);
              return;
            }
            if (i == util.ASYN_TRANSPORT_MSF)
            {
              this.mEncrypt = ((Integer)localObjectInputStream.readObject()).intValue();
              this.mUserAccount = ((String)localObjectInputStream.readObject());
              this.mDwAppid = ((Long)localObjectInputStream.readObject()).longValue();
              this.mRole = ((Long)localObjectInputStream.readObject()).longValue();
              this.mReqContext = ((TransReqContext)localObjectInputStream.readObject());
              int m = ((Integer)localObjectInputStream.readObject()).intValue();
              if (this.mHelper.mListener != null)
              {
                this.mHelper.mListener.OnRequestTransport(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, m);
                return;
              }
              this.mHelper.OnRequestTransport(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, m);
              return;
            }
            if (i == util.ASYN_TRANSPORT_PUSH)
              continue;
            if (i == util.ASYN_PUSH_CONNECTED)
            {
              if (this.mHelper.mListener != null)
              {
                this.mHelper.mListener.OnPushConnected();
                return;
              }
              this.mHelper.OnPushConnected();
              return;
            }
            if (i == util.ASYN_PUSH_DISCONNECTED)
            {
              if (this.mHelper.mListener != null)
              {
                this.mHelper.mListener.OnPushDisConnected();
                return;
              }
              this.mHelper.OnPushDisConnected();
              return;
            }
            int k = util.ASYN_REPORT_ERROR;
            if (i == k)
              return;
          }
          this.mHelper.OnException(localException1, paramMessage.arg1);
        }
      }
    }
  }

  public class HelperThread extends Thread
  {
    long mDwAppid;
    long mDwDstAppPri;
    long mDwDstAppid;
    long[] mDwDstSubAppidList;
    int mDwMainSigMap;
    long[] mDwSubAppidList;
    long mDwSubDstAppid;
    int mEncrypt;
    Handler mHandler;
    WtloginHelper mHelper;
    byte[] mPictureData;
    boolean mPwdMd5;
    int mReportErrType;
    TransReqContext mReqContext;
    int mReqType;
    byte[][] mReserve;
    long mRole;
    byte[][] mST;
    byte[] mST1;
    byte[] mST1Key;
    long mUIN;
    String mUserAccount;
    byte[] mUserInput;
    String mUserPasswd;
    WUserSigInfo mUserSigInfo;

    HelperThread(WtloginHelper paramHandler, Handler arg3)
    {
      this.mHelper = paramHandler;
      Object localObject;
      this.mHandler = localObject;
    }

    HelperThread(WtloginHelper paramHandler, Handler paramInt, int paramString, String paramLong1, long arg6, long arg8, TransReqContext arg10)
    {
      this.mHelper = paramHandler;
      this.mHandler = paramInt;
      this.mEncrypt = paramString;
      this.mUserAccount = paramLong1;
      this.mDwAppid = ???;
      Object localObject1;
      this.mRole = localObject1;
      Object localObject2;
      this.mReqContext = localObject2;
    }

    HelperThread(WtloginHelper paramHandler, Handler paramInt, int paramString, String paramLong1, long arg6, long arg8, TransReqContext paramWUserSigInfo, WUserSigInfo arg11)
    {
      this.mHelper = paramHandler;
      this.mHandler = paramInt;
      this.mEncrypt = paramString;
      this.mUserAccount = paramLong1;
      this.mDwAppid = ???;
      this.mRole = ???;
      this.mReqContext = paramWUserSigInfo;
      Object localObject;
      this.mUserSigInfo = localObject;
    }

    HelperThread(WtloginHelper paramHandler, Handler paramString, String arg4)
    {
      this.mHelper = paramHandler;
      this.mHandler = paramString;
      Object localObject;
      this.mUserAccount = localObject;
    }

    HelperThread(WtloginHelper paramHandler, Handler paramString1, String paramLong1, long arg5, int paramLong2, long arg8, long[] paramBoolean, boolean paramString2, String paramWUserSigInfo, WUserSigInfo paramArrayOfByte, byte[][] arg14)
    {
      this.mHelper = paramHandler;
      this.mHandler = paramString1;
      this.mUserAccount = paramLong1;
      this.mDwAppid = ???;
      this.mDwMainSigMap = paramLong2;
      this.mDwSubDstAppid = ???;
      this.mDwSubAppidList = paramBoolean;
      this.mPwdMd5 = paramString2;
      this.mUserPasswd = paramWUserSigInfo;
      this.mUserSigInfo = paramArrayOfByte;
      Object localObject;
      this.mST = localObject;
    }

    HelperThread(WtloginHelper paramHandler, Handler paramString, String paramLong1, long arg5, long arg7, long arg9, int paramLong4, long arg12, long[] paramWUserSigInfo, WUserSigInfo paramArrayOfByte1, byte[][] paramArrayOfByte2, byte[][] arg17)
    {
      this.mHelper = paramHandler;
      this.mHandler = paramString;
      this.mUserAccount = paramLong1;
      this.mDwAppid = ???;
      this.mDwDstAppid = ???;
      this.mDwDstAppPri = ???;
      this.mDwMainSigMap = paramLong4;
      this.mDwSubDstAppid = ???;
      this.mDwDstSubAppidList = paramWUserSigInfo;
      this.mUserSigInfo = paramArrayOfByte1;
      this.mST = paramArrayOfByte2;
      Object localObject;
      this.mReserve = localObject;
    }

    HelperThread(WtloginHelper paramHandler, Handler paramString, String paramWUserSigInfo, WUserSigInfo arg5)
    {
      this.mHelper = paramHandler;
      this.mHandler = paramString;
      this.mUserAccount = paramWUserSigInfo;
      Object localObject;
      this.mUserSigInfo = localObject;
    }

    HelperThread(WtloginHelper paramHandler, Handler paramString, String paramArrayOfByte, byte[] paramWUserSigInfo, WUserSigInfo paramArrayOfByte1, byte[][] arg7)
    {
      this.mHelper = paramHandler;
      this.mHandler = paramString;
      this.mUserAccount = paramArrayOfByte;
      this.mUserInput = paramWUserSigInfo;
      this.mUserSigInfo = paramArrayOfByte1;
      Object localObject;
      this.mST = localObject;
    }

    HelperThread(WtloginHelper paramHandler, Handler paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramLong1, long arg6, long arg8)
    {
      this.mHelper = paramHandler;
      this.mHandler = paramArrayOfByte1;
      this.mST1 = paramArrayOfByte2;
      this.mST1Key = paramLong1;
      this.mUIN = ???;
      Object localObject;
      this.mDwAppid = localObject;
    }

    HelperThread(WtloginHelper paramHandler, Handler paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramLong1, long arg6, long arg8, int arg10)
    {
      this.mHelper = paramHandler;
      this.mHandler = paramArrayOfByte1;
      this.mST1 = paramArrayOfByte2;
      this.mST1Key = paramLong1;
      this.mUIN = ???;
      Object localObject;
      this.mDwAppid = localObject;
      int i;
      this.mReportErrType = i;
    }

    public void RunReq(int paramInt)
    {
      this.mReqType = paramInt;
      start();
    }

    public void run()
    {
      int i = this.mHelper.mG._canceled;
      try
      {
        localByteArrayOutputStream = new ByteArrayOutputStream();
        localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
        if (this.mReqType == util.ASYN_GET_ST_WITH_PWD)
        {
          i10 = this.mHelper.GetStWithPasswd(this.mUserAccount, this.mDwAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwSubAppidList, this.mPwdMd5, this.mUserPasswd, this.mUserSigInfo, this.mST, 1);
          localObjectOutputStream.writeObject(this.mUserAccount);
          localObjectOutputStream.writeObject(new Long(this.mDwAppid));
          localObjectOutputStream.writeObject(new Integer(this.mDwMainSigMap));
          localObjectOutputStream.writeObject(new Long(this.mDwSubDstAppid));
          localObjectOutputStream.writeObject(this.mDwSubAppidList);
          localObjectOutputStream.writeObject(new Boolean(this.mPwdMd5));
          localObjectOutputStream.writeObject(this.mUserPasswd);
          localObjectOutputStream.writeObject(this.mUserSigInfo);
          localObjectOutputStream.writeObject(this.mST);
          localObjectOutputStream.writeObject(new Integer(i10));
          util.LOGD(getClass().getName() + "run ReqType=" + this.mReqType + "ret=" + i10);
          if (this.mHandler == null)
          {
            if (i != 0)
              return;
            if (this.mDwSubAppidList == null)
              if (this.mHelper.mListener != null)
              {
                this.mHelper.mListener.OnGetStWithPasswd(this.mUserAccount, this.mDwAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mUserPasswd, this.mUserSigInfo, i10);
                return;
              }
          }
        }
      }
      catch (Exception localException1)
      {
        ByteArrayOutputStream localByteArrayOutputStream;
        ObjectOutputStream localObjectOutputStream;
        int i10;
        String str = new String("async thread exception:");
        if (this.mHandler == null)
        {
          if (i == 0)
          {
            Exception localException2;
            try
            {
              localException2 = new Exception(str);
              if (this.mHelper.mListener == null)
                break label3728;
              this.mHelper.mListener.OnException(localException2, this.mReqType);
              return;
            }
            catch (Exception localException3)
            {
              if (this.mHelper.mListener == null)
                break label3742;
            }
            this.mHelper.mListener.OnException(localException3, this.mReqType);
            return;
            this.mHelper.OnGetStWithPasswd(this.mUserAccount, this.mDwAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mUserPasswd, this.mUserSigInfo, i10);
            return;
            if (this.mHelper.mListener != null)
            {
              this.mHelper.mListener.OnGetStWithPasswd(this.mUserAccount, this.mDwAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwSubAppidList, this.mUserPasswd, this.mUserSigInfo, this.mST, i10);
              return;
            }
            this.mHelper.OnGetStWithPasswd(this.mUserAccount, this.mDwAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwSubAppidList, this.mUserPasswd, this.mUserSigInfo, this.mST, i10);
            return;
            sendMsg(this.mReqType, this.mReqType, localByteArrayOutputStream.toByteArray());
            return;
            if (this.mReqType == util.ASYN_REFLUSH_IMAGE)
            {
              int i9 = this.mHelper.RefreshPictureData(this.mUserAccount, 1);
              localObjectOutputStream.writeObject(this.mUserAccount);
              localObjectOutputStream.writeObject(new Integer(i9));
              util.LOGD(getClass().getName() + "run ReqType=" + this.mReqType + "ret=" + i9);
              if (this.mHandler == null)
              {
                if (i == 0)
                {
                  this.mPictureData = this.mHelper.GetPictureData(this.mUserAccount);
                  if (this.mHelper.mListener != null)
                  {
                    this.mHelper.mListener.OnRefreshPictureData(this.mUserAccount, this.mPictureData, i9);
                    return;
                  }
                  this.mHelper.OnRefreshPictureData(this.mUserAccount, this.mPictureData, i9);
                  return;
                }
              }
              else
              {
                sendMsg(this.mReqType, this.mReqType, localByteArrayOutputStream.toByteArray());
                return;
              }
            }
            else if (this.mReqType == util.ASYN_NEW_REFLUSH_IMAGE)
            {
              int i8 = this.mHelper.RefreshPictureData(this.mUserAccount, this.mUserSigInfo, 1);
              localObjectOutputStream.writeObject(this.mUserAccount);
              localObjectOutputStream.writeObject(this.mUserSigInfo);
              localObjectOutputStream.writeObject(new Integer(i8));
              util.LOGD(getClass().getName() + "run ReqType=" + this.mReqType + "ret=" + i8);
              if (this.mHandler == null)
              {
                if (i == 0)
                {
                  this.mPictureData = this.mHelper.GetPictureData(this.mUserAccount);
                  if (this.mHelper.mListener != null)
                  {
                    this.mHelper.mListener.OnRefreshPictureData(this.mUserAccount, this.mUserSigInfo, this.mPictureData, i8);
                    return;
                  }
                  this.mHelper.OnRefreshPictureData(this.mUserAccount, this.mUserSigInfo, this.mPictureData, i8);
                  return;
                }
              }
              else
              {
                sendMsg(this.mReqType, this.mReqType, localByteArrayOutputStream.toByteArray());
                return;
              }
            }
            else if (this.mReqType == util.ASYN_REFLUSH_SMS)
            {
              int i7 = this.mHelper.RefreshSMSData(this.mUserAccount, 1);
              localObjectOutputStream.writeObject(this.mUserAccount);
              localObjectOutputStream.writeObject(new Integer(i7));
              util.LOGD(getClass().getName() + "run ReqType=" + this.mReqType + "ret=" + i7);
              if (this.mHandler == null)
              {
                if (i == 0)
                {
                  this.mPictureData = this.mHelper.GetPictureData(this.mUserAccount);
                  if (this.mHelper.mListener != null)
                  {
                    this.mHelper.mListener.OnRefreshSMSData(this.mUserAccount, this.mPictureData, i7);
                    return;
                  }
                  this.mHelper.OnRefreshSMSData(this.mUserAccount, this.mPictureData, i7);
                  return;
                }
              }
              else
              {
                sendMsg(this.mReqType, this.mReqType, localByteArrayOutputStream.toByteArray());
                return;
              }
            }
            else if (this.mReqType == util.ASYN_CHECK_IMAGE)
            {
              int i6 = this.mHelper.CheckPictureAndGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, this.mST, 1);
              localObjectOutputStream.writeObject(this.mUserAccount);
              localObjectOutputStream.writeObject(this.mUserInput);
              localObjectOutputStream.writeObject(this.mUserSigInfo);
              localObjectOutputStream.writeObject(this.mST);
              localObjectOutputStream.writeObject(new Integer(i6));
              util.LOGD(getClass().getName() + "run ReqType=" + this.mReqType + "ret=" + i6);
              if (this.mHandler == null)
              {
                if (i == 0)
                {
                  if (this.mST == null)
                  {
                    if (this.mHelper.mListener != null)
                    {
                      this.mHelper.mListener.OnCheckPictureAndGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, i6);
                      return;
                    }
                    this.mHelper.OnCheckPictureAndGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, i6);
                    return;
                  }
                  if (this.mHelper.mListener != null)
                  {
                    this.mHelper.mListener.OnCheckPictureAndGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, this.mST, i6);
                    return;
                  }
                  this.mHelper.OnCheckPictureAndGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, this.mST, i6);
                  return;
                }
              }
              else
              {
                sendMsg(this.mReqType, this.mReqType, localByteArrayOutputStream.toByteArray());
                return;
              }
            }
            else if (this.mReqType == util.ASYN_CHECK_SMS)
            {
              int i5 = this.mHelper.CheckSMSGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, this.mST, 1);
              localObjectOutputStream.writeObject(this.mUserAccount);
              localObjectOutputStream.writeObject(this.mUserInput);
              localObjectOutputStream.writeObject(this.mUserSigInfo);
              localObjectOutputStream.writeObject(this.mST);
              localObjectOutputStream.writeObject(new Integer(i5));
              util.LOGD(getClass().getName() + "run ReqType=" + this.mReqType + "ret=" + i5);
              if (this.mHandler == null)
              {
                if (i == 0)
                {
                  if (this.mST == null)
                  {
                    if (this.mHelper.mListener != null)
                    {
                      this.mHelper.mListener.OnCheckSMSGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, i5);
                      return;
                    }
                    this.mHelper.OnCheckSMSGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, i5);
                    return;
                  }
                  if (this.mHelper.mListener != null)
                  {
                    this.mHelper.mListener.OnCheckSMSGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, this.mST, i5);
                    return;
                  }
                  this.mHelper.OnCheckSMSGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, this.mST, i5);
                  return;
                }
              }
              else
              {
                sendMsg(this.mReqType, this.mReqType, localByteArrayOutputStream.toByteArray());
                return;
              }
            }
            else if (this.mReqType == util.ASYN_GET_ST_WITHOUT_PWD)
            {
              int i4 = this.mHelper.GetStWithoutPasswd(this.mUserAccount, this.mDwAppid, this.mDwDstAppid, this.mDwDstAppPri, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwDstSubAppidList, this.mUserSigInfo, this.mST, this.mReserve, 1);
              localObjectOutputStream.writeObject(this.mUserAccount);
              localObjectOutputStream.writeObject(new Long(this.mDwAppid));
              localObjectOutputStream.writeObject(new Long(this.mDwDstAppid));
              localObjectOutputStream.writeObject(new Long(this.mDwDstAppPri));
              localObjectOutputStream.writeObject(new Integer(this.mDwMainSigMap));
              localObjectOutputStream.writeObject(new Long(this.mDwSubDstAppid));
              localObjectOutputStream.writeObject(this.mDwDstSubAppidList);
              localObjectOutputStream.writeObject(this.mUserSigInfo);
              localObjectOutputStream.writeObject(this.mST);
              localObjectOutputStream.writeObject(new Integer(i4));
              util.LOGD(getClass().getName() + "run ReqType=" + this.mReqType + "ret=" + i4);
              if (this.mHandler == null)
              {
                if (i == 0)
                {
                  if (this.mDwDstSubAppidList == null)
                  {
                    if (this.mHelper.mListener != null)
                    {
                      this.mHelper.mListener.OnGetStWithoutPasswd(this.mUserAccount, this.mDwAppid, this.mDwDstAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mUserSigInfo, i4);
                      return;
                    }
                    this.mHelper.OnGetStWithoutPasswd(this.mUserAccount, this.mDwAppid, this.mDwDstAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mUserSigInfo, i4);
                    return;
                  }
                  if (this.mHelper.mListener != null)
                  {
                    this.mHelper.mListener.OnGetStWithoutPasswd(this.mUserAccount, this.mDwAppid, this.mDwDstAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwDstSubAppidList, this.mUserSigInfo, this.mST, i4);
                    return;
                  }
                  this.mHelper.OnGetStWithoutPasswd(this.mUserAccount, this.mDwAppid, this.mDwDstAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwDstSubAppidList, this.mUserSigInfo, this.mST, i4);
                  return;
                }
              }
              else
              {
                sendMsg(this.mReqType, this.mReqType, localByteArrayOutputStream.toByteArray());
                return;
              }
            }
            else if (this.mReqType == util.ASYN_GET_REMOTE_ST_WITHOUT_PWD)
            {
              int i3 = this.mHelper.GetRemoteStWithoutPasswd(this.mUserAccount, this.mDwAppid, this.mDwDstAppid, this.mDwDstAppPri, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwDstSubAppidList, this.mUserSigInfo, this.mST, this.mReserve, 1);
              localObjectOutputStream.writeObject(this.mUserAccount);
              localObjectOutputStream.writeObject(new Long(this.mDwAppid));
              localObjectOutputStream.writeObject(new Long(this.mDwDstAppid));
              localObjectOutputStream.writeObject(new Long(this.mDwDstAppPri));
              localObjectOutputStream.writeObject(new Integer(this.mDwMainSigMap));
              localObjectOutputStream.writeObject(new Long(this.mDwSubDstAppid));
              localObjectOutputStream.writeObject(this.mDwDstSubAppidList);
              localObjectOutputStream.writeObject(this.mUserSigInfo);
              localObjectOutputStream.writeObject(this.mST);
              localObjectOutputStream.writeObject(new Integer(i3));
              util.LOGD(getClass().getName() + "run ReqType=" + this.mReqType + "ret=" + i3);
              if (this.mHandler == null)
              {
                if (i == 0)
                {
                  if (this.mHelper.mListener != null)
                  {
                    this.mHelper.mListener.OnGetRemoteStWithoutPasswd(this.mUserAccount, this.mDwAppid, this.mDwDstAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwDstSubAppidList, this.mUserSigInfo, this.mST, i3);
                    return;
                  }
                  this.mHelper.OnGetRemoteStWithoutPasswd(this.mUserAccount, this.mDwAppid, this.mDwDstAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwDstSubAppidList, this.mUserSigInfo, this.mST, i3);
                  return;
                }
              }
              else
              {
                sendMsg(this.mReqType, this.mReqType, localByteArrayOutputStream.toByteArray());
                return;
              }
            }
            else if (this.mReqType == util.ASYN_PING)
            {
              int i2 = this.mHelper.RequestInit(1);
              localObjectOutputStream.writeObject(new Integer(i2));
              util.LOGD(getClass().getName() + "run ReqType=" + this.mReqType + "ret=" + i2);
              if (this.mHandler == null)
              {
                if (i == 0)
                {
                  if (this.mHelper.mListener != null)
                  {
                    this.mHelper.mListener.OnInit(i2);
                    return;
                  }
                  this.mHelper.OnInit(i2);
                  return;
                }
              }
              else
              {
                sendMsg(this.mReqType, this.mReqType, localByteArrayOutputStream.toByteArray());
                return;
              }
            }
            else
            {
              if (this.mReqType == util.ASYN_REPORT)
              {
                int i1 = this.mHelper.RequestReport(1, this.mST1, this.mST1Key, this.mUIN, this.mDwAppid);
                util.LOGD(getClass().getName() + "run ReqType=" + this.mReqType + "ret=" + i1);
                return;
              }
              if (this.mReqType == util.ASYN_TRANSPORT)
              {
                int n = this.mHelper.RequestTransport(1, this.mEncrypt, this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext);
                localObjectOutputStream.writeObject(new Integer(this.mEncrypt));
                localObjectOutputStream.writeObject(this.mUserAccount);
                localObjectOutputStream.writeObject(new Long(this.mDwAppid));
                localObjectOutputStream.writeObject(new Long(this.mRole));
                localObjectOutputStream.writeObject(this.mReqContext);
                localObjectOutputStream.writeObject(new Integer(n));
                util.LOGD(getClass().getName() + "run ReqType=" + this.mReqType + "ret=" + n);
                if (this.mHandler == null)
                {
                  if (i == 0)
                  {
                    if (this.mReqContext.is_register_req())
                    {
                      this.mHelper.OnRequestRegister(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, n);
                      return;
                    }
                    if (this.mReqContext.is_name_func_req())
                    {
                      this.mHelper.OnRequestName(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, n);
                      return;
                    }
                    if (this.mReqContext.is_code2d_func_req())
                    {
                      this.mHelper.OnRequestCode2d(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, n);
                      return;
                    }
                    if (this.mHelper.mListener != null)
                    {
                      this.mHelper.mListener.OnRequestTransport(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, n);
                      return;
                    }
                    this.mHelper.OnRequestTransport(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, n);
                    return;
                  }
                }
                else
                {
                  sendMsg(this.mReqType, this.mReqType, localByteArrayOutputStream.toByteArray());
                  return;
                }
              }
              else if (this.mReqType == util.ASYN_NEW_TRANSPORT)
              {
                int m = this.mHelper.RequestTransport(1, this.mEncrypt, this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, this.mUserSigInfo);
                localObjectOutputStream.writeObject(new Integer(this.mEncrypt));
                localObjectOutputStream.writeObject(this.mUserAccount);
                localObjectOutputStream.writeObject(new Long(this.mDwAppid));
                localObjectOutputStream.writeObject(new Long(this.mRole));
                localObjectOutputStream.writeObject(this.mReqContext);
                localObjectOutputStream.writeObject(this.mUserSigInfo);
                localObjectOutputStream.writeObject(new Integer(m));
                util.LOGD(getClass().getName() + "run ReqType=" + this.mReqType + "ret=" + m);
                if (this.mHandler == null)
                {
                  if (i == 0)
                  {
                    if (this.mReqContext.is_oidb_func_req())
                    {
                      this.mHelper.OnOidbRequest(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, this.mUserSigInfo, m);
                      return;
                    }
                    if (this.mHelper.mListener != null)
                    {
                      this.mHelper.mListener.OnRequestTransport(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, this.mUserSigInfo, m);
                      return;
                    }
                    this.mHelper.OnRequestTransport(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, this.mUserSigInfo, m);
                    return;
                  }
                }
                else
                {
                  sendMsg(this.mReqType, this.mReqType, localByteArrayOutputStream.toByteArray());
                  return;
                }
              }
              else if (this.mReqType == util.ASYN_TRANSPORT_MSF)
              {
                int k = this.mHelper.RequestTransportMsf(1, this.mEncrypt, this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext);
                localObjectOutputStream.writeObject(new Integer(this.mEncrypt));
                localObjectOutputStream.writeObject(this.mUserAccount);
                localObjectOutputStream.writeObject(new Long(this.mDwAppid));
                localObjectOutputStream.writeObject(new Long(this.mRole));
                localObjectOutputStream.writeObject(this.mReqContext);
                localObjectOutputStream.writeObject(new Integer(k));
                util.LOGD(getClass().getName() + "run ReqType=" + this.mReqType + "ret=" + k);
                if (this.mHandler == null)
                {
                  if (i == 0)
                  {
                    if (this.mHelper.mListener != null)
                    {
                      this.mHelper.mListener.OnRequestTransport(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, k);
                      return;
                    }
                    this.mHelper.OnRequestTransport(this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, k);
                    return;
                  }
                }
                else
                {
                  sendMsg(this.mReqType, this.mReqType, localByteArrayOutputStream.toByteArray());
                  return;
                }
              }
              else
              {
                if (this.mReqType == util.ASYN_REPORT_ERROR)
                {
                  int j = this.mHelper.RequestReportError(1, this.mST1, this.mST1Key, this.mUIN, this.mDwAppid, this.mReportErrType);
                  util.LOGD(getClass().getName() + "run ReqType=" + this.mReqType + "ret=" + j);
                  return;
                }
                util.LOGD(getClass().getName() + "run unknown");
                return;
                label3728: this.mHelper.OnException(localException2, this.mReqType);
                return;
                label3742: this.mHelper.OnException(localException3, this.mReqType);
                return;
              }
            }
          }
        }
        else
        {
          sendMsg(util.ASYN_EXCEPTION, this.mReqType, str.getBytes());
          StringWriter localStringWriter = new StringWriter();
          PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
          localException1.printStackTrace(localPrintWriter);
          localPrintWriter.flush();
          localStringWriter.flush();
          util.LOGW("exception", localStringWriter.toString());
        }
      }
    }

    public void sendMsg(int paramInt1, int paramInt2, byte[] paramArrayOfByte)
    {
      Message localMessage = new Message();
      localMessage.what = paramInt1;
      localMessage.arg1 = paramInt2;
      Bundle localBundle = new Bundle();
      localBundle.putByteArray("param", paramArrayOfByte);
      localMessage.setData(localBundle);
      this.mHandler.sendMessage(localMessage);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.WtloginHelper
 * JD-Core Version:    0.6.0
 */