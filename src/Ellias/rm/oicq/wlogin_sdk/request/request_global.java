package oicq.wlogin_sdk.request;

import B;
import android.content.Context;
import android.os.Build;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.List<Loicq.wlogin_sdk.sharemem.WloginLoginInfo;>;
import oicq.wlogin_sdk.push.push_client;
import oicq.wlogin_sdk.report.report_t;
import oicq.wlogin_sdk.report.report_t1;
import oicq.wlogin_sdk.sharemem.WloginLoginInfo;
import oicq.wlogin_sdk.sharemem.WloginRemoteData;
import oicq.wlogin_sdk.sharemem.WloginSigInfo;
import oicq.wlogin_sdk.sharemem.WloginSimpleInfo;
import oicq.wlogin_sdk.sharemem.sharemem_client;
import oicq.wlogin_sdk.tlv_type.tlv_t104;
import oicq.wlogin_sdk.tlv_type.tlv_t105;
import oicq.wlogin_sdk.tlv_type.tlv_t113;
import oicq.wlogin_sdk.tlv_type.tlv_t122;
import oicq.wlogin_sdk.tlv_type.tlv_t124;
import oicq.wlogin_sdk.tlv_type.tlv_t126;
import oicq.wlogin_sdk.tlv_type.tlv_t128;
import oicq.wlogin_sdk.tlv_type.tlv_t129;
import oicq.wlogin_sdk.tlv_type.tlv_t150;
import oicq.wlogin_sdk.tlv_type.tlv_t152;
import oicq.wlogin_sdk.tlv_type.tlv_t165;
import oicq.wlogin_sdk.tools.ErrMsg;
import oicq.wlogin_sdk.tools.util;

public class request_global
{
  public static byte[] _IMEI_KEY = new byte[0];
  static long _l_init_time = 0L;
  public static sharemem_client _sharemem = null;
  byte[] _IMEI = new byte[0];
  public SecureRandom _SR = new SecureRandom();
  public account_sig_info_map _account_sig_info_map = null;
  byte[] _apk_id = new byte[0];
  byte[] _apk_sig = new byte[0];
  byte[] _apk_v = new byte[0];
  byte[] _apn = new byte[0];
  public int _app_client_version = 0;
  public long _appid = 0L;
  public int _canceled = 0;
  public Context _context = null;
  byte[] _device = new byte[0];
  byte[] _encrypt_a1 = new byte[0];
  public int _getuin_need_image = 0;
  int _guid_chg = 0;
  public int _img_type = 1;
  byte[] _init_time = null;
  byte[] _ip_addr = null;
  int _isroot = 0;
  public byte[] _key_tgtgt = util.get_rand_16byte(this._SR);
  byte[] _ksid = new byte[0];
  ErrMsg _last_err_msg = new ErrMsg();
  String _last_login_account = new String();
  byte[] _mac = new byte[0];
  public int _main_sigmap = 0;
  public byte[] _master_tgt_key = null;
  public int _msf_transport_flag = 0;
  public String _name = "";
  int _network_type = 0;
  int _new_install = 0;
  int _ping_end_flag = 0;
  public push_client _push = null;
  public boolean _push_enable = false;
  byte[] _pwd_save = new byte[0];
  public byte[] _rand_key = new byte[16];
  int _read_guid = 0;
  public int _request_pending = 0;
  public int _ret = 0;
  report_t1 _rt1 = new report_t1();
  public boolean _share_tk_enable = false;
  byte[] _sim_operator_name = new byte[0];
  Socket _sk = null;
  public long _sub_appid = 0L;
  public long[] _sub_appid_list = new long[0];
  public tlv_t104 _t104 = new tlv_t104();
  public tlv_t105 _t105 = new tlv_t105();
  public tlv_t113 _t113 = new tlv_t113();
  public tlv_t122 _t122 = new tlv_t122();
  public tlv_t124 _t124 = new tlv_t124();
  public tlv_t126 _t126 = new tlv_t126();
  public tlv_t128 _t128 = new tlv_t128();
  public tlv_t129 _t129 = new tlv_t129();
  public tlv_t150 _t150 = null;
  public tlv_t152 _t152 = new tlv_t152();
  public tlv_t165 _t165 = new tlv_t165();
  long _time_difference = 0L;
  public int _time_out = 10000;
  public long _tk_time_out = -1L;
  public byte[] _tmp_pwd = new byte[16];
  public int _tmp_pwd_type = 0;
  public byte[] _token = new byte[0];
  Socket _transport_sk = null;
  public long _uin = 0L;
  Boolean isUploading = Boolean.valueOf(false);

  public request_global(Context paramContext)
  {
  }

  private void SetIMEI(byte[] paramArrayOfByte)
  {
    this._IMEI = new byte[paramArrayOfByte.length];
    System.arraycopy(paramArrayOfByte, 0, this._IMEI, 0, paramArrayOfByte.length);
    byte[] arrayOfByte = new byte[1 + paramArrayOfByte.length];
    arrayOfByte[0] = 35;
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 1, paramArrayOfByte.length);
    this._key_tgtgt = util.get_rand_16byte(this._SR, arrayOfByte);
  }

  public static long get_cur_time()
  {
    return System.currentTimeMillis() / 1000L;
  }

  public static long get_server_cur_time()
  {
    return System.currentTimeMillis() / 1000L + _l_init_time;
  }

  public static WloginSigInfo get_siginfo(Context paramContext)
  {
    monitorenter;
    try
    {
      WloginSigInfo localWloginSigInfo = account_sig_info_map.get_siginfo(paramContext, get_cur_time());
      monitorexit;
      return localWloginSigInfo;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private byte[] get_stsig(long paramLong1, long paramLong2)
  {
    monitorenter;
    try
    {
      util.LOGD("get_stsig", "uin=" + paramLong1 + "appid=" + paramLong2);
      byte[] arrayOfByte = this._account_sig_info_map.get_stsig(paramLong1, paramLong2);
      if (arrayOfByte != null);
      monitorexit;
      return arrayOfByte;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public String app_version_to_string()
  {
    if ((0xFF000000 & this._app_client_version) != 0)
    {
      Object[] arrayOfObject4 = new Object[4];
      arrayOfObject4[0] = Integer.valueOf(0xFF & this._app_client_version >> 24);
      arrayOfObject4[1] = Integer.valueOf(0xFF & this._app_client_version >> 16);
      arrayOfObject4[2] = Integer.valueOf(0xFF & this._app_client_version >> 8);
      arrayOfObject4[3] = Integer.valueOf(0xFF & this._app_client_version >> 0);
      return String.format("%d.%d.%d.%d", arrayOfObject4);
    }
    if ((0xFF0000 & this._app_client_version) != 0)
    {
      Object[] arrayOfObject3 = new Object[3];
      arrayOfObject3[0] = Integer.valueOf(0xFF & this._app_client_version >> 16);
      arrayOfObject3[1] = Integer.valueOf(0xFF & this._app_client_version >> 8);
      arrayOfObject3[2] = Integer.valueOf(0xFF & this._app_client_version >> 0);
      return String.format("%d.%d.%d", arrayOfObject3);
    }
    if ((0xFF00 & this._app_client_version) != 0)
    {
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = Integer.valueOf(0xFF & this._app_client_version >> 8);
      arrayOfObject2[1] = Integer.valueOf(0xFF & this._app_client_version >> 0);
      return String.format("%d.%d", arrayOfObject2);
    }
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Integer.valueOf(0xFF & this._app_client_version >> 0);
    return String.format("%d", arrayOfObject1);
  }

  public void clear_account(String paramString)
  {
    monitorenter;
    try
    {
      this._account_sig_info_map.clear_account(paramString);
      if (_sharemem != null)
        is_share_tk_enable();
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

  public void clear_request_pending()
  {
    close_connect();
    this._request_pending = 0;
  }

  public void clear_sig(Long paramLong)
  {
    monitorenter;
    try
    {
      this._account_sig_info_map.clear_sig(paramLong);
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

  public void clear_sig(Long paramLong1, Long paramLong2)
  {
    monitorenter;
    try
    {
      this._account_sig_info_map.clear_sig(paramLong1, paramLong2);
      if (_sharemem != null)
        is_share_tk_enable();
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

  public void clear_sig_session()
  {
    this._t104 = new tlv_t104();
  }

  public void clear_sig_test(Long paramLong)
  {
    monitorenter;
    try
    {
      this._account_sig_info_map.clear_sig_test(paramLong);
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

  public void clear_time_ip()
  {
    this._init_time = null;
    this._ip_addr = null;
    this._time_difference = 0L;
    _l_init_time = 0L;
  }

  public void clear_tmp_pwd()
  {
    if (this._tmp_pwd == null);
    while (true)
    {
      return;
      for (int i = 0; i < this._tmp_pwd.length; i++)
        this._tmp_pwd[i] = 0;
    }
  }

  public void close_connect()
  {
    util.LOGD("close_connect", "close_connect");
    if (this._sk != null);
    try
    {
      util.LOGD("close_connect", this._sk.toString());
      this._sk.close();
      this._sk = null;
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
        localException.printStackTrace(localPrintWriter);
        localPrintWriter.flush();
        localStringWriter.flush();
        util.LOGW("exception", localStringWriter.toString());
      }
    }
  }

  public void close_transport_connect()
  {
    util.LOGD("close_transport_connect", "close_transport_connect");
    if (this._transport_sk != null);
    try
    {
      util.LOGD("close_transport_connect", this._transport_sk.toString());
      this._transport_sk.close();
      this._transport_sk = null;
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
        localException.printStackTrace(localPrintWriter);
        localPrintWriter.flush();
        localStringWriter.flush();
        util.LOGW("exception", localStringWriter.toString());
      }
    }
  }

  public Long get_account(String paramString)
  {
    monitorenter;
    try
    {
      Long localLong = this._account_sig_info_map.get_account(paramString);
      if (localLong != null);
      monitorexit;
      return localLong;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public List<WloginLoginInfo> get_all_logined_account()
  {
    monitorenter;
    try
    {
      new ArrayList();
      List localList = this._account_sig_info_map.get_all_logined_account(true);
      monitorexit;
      return localList;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public List<WloginLoginInfo> get_all_logined_account_remote(long paramLong, long[] paramArrayOfLong)
  {
    Object localObject = new ArrayList();
    if ((_sharemem != null) && (is_share_tk_enable()))
      localObject = _sharemem.get_all_logined_account(paramLong, paramArrayOfLong);
    return (List<WloginLoginInfo>)localObject;
  }

  public WloginLastLoginInfo get_last_login_info()
  {
    return this._account_sig_info_map.get_last_login_info();
  }

  public int get_ping_end_flag()
  {
    return this._ping_end_flag;
  }

  public int get_ret_code()
  {
    return this._ret;
  }

  public WloginSigInfo get_siginfo(long paramLong1, long paramLong2)
  {
    monitorenter;
    try
    {
      util.LOGD("get_siginfo", "uin=" + paramLong1 + "appid=" + paramLong2);
      WloginSigInfo localWloginSigInfo = this._account_sig_info_map.get_siginfo(paramLong1, paramLong2);
      if (localWloginSigInfo != null);
      monitorexit;
      return localWloginSigInfo;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public WloginSigInfo get_siginfo_by_pri(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    monitorenter;
    try
    {
      util.LOGD("get_stsig", "uin=" + paramLong1 + "appid=" + paramLong2);
      WloginSigInfo localWloginSigInfo = this._account_sig_info_map.get_siginfo_by_pri(paramLong1, paramLong2, paramLong3, paramLong4);
      if (localWloginSigInfo != null);
      monitorexit;
      return localWloginSigInfo;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int get_siginfo_remote(String paramString, long paramLong1, long paramLong2, long paramLong3, long paramLong4, int paramInt1, int paramInt2, int paramInt3, int paramInt4, long[] paramArrayOfLong, byte[] paramArrayOfByte1, long paramLong5, long paramLong6, long paramLong7, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, List<WloginRemoteData> paramList)
  {
    sharemem_client localsharemem_client = _sharemem;
    int i = 0;
    if (localsharemem_client != null)
    {
      boolean bool = is_share_tk_enable();
      i = 0;
      if (bool)
        i = _sharemem.get_siginfo_remote(paramString, paramLong1, paramLong2, paramLong3, paramLong4, paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfLong, paramArrayOfByte1, paramLong5, paramLong6, paramLong7, paramArrayOfByte2, paramArrayOfByte3, paramArrayOfByte4, paramList);
    }
    return i;
  }

  public WloginSimpleInfo get_simpleinfo(long paramLong)
  {
    monitorenter;
    try
    {
      WloginSimpleInfo localWloginSimpleInfo = this._account_sig_info_map.get_simpleinfo(paramLong);
      if (localWloginSimpleInfo != null);
      monitorexit;
      return localWloginSimpleInfo;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void init()
  {
    int i = util.get_saved_network_type(this._context);
    byte[] arrayOfByte1 = util.get_saved_imei(this._context);
    byte[] arrayOfByte2;
    label79: String str;
    label192: int j;
    if ((arrayOfByte1 == null) || (arrayOfByte1.length <= 0))
    {
      arrayOfByte2 = util.get_IMEI(this._context);
      if ((arrayOfByte2 == null) || (arrayOfByte2.length <= 0))
      {
        arrayOfByte2 = new String("%4;7t>;28<fc.5*6").getBytes();
        this._read_guid = 0;
        util.save_imei(this._context, arrayOfByte2);
        this._new_install = 1;
        this._guid_chg = 1;
        SetIMEI(arrayOfByte2);
        _IMEI_KEY = (byte[])this._IMEI.clone();
        this._sim_operator_name = util.get_sim_operator_name(this._context);
        this._network_type = util.get_network_type(this._context);
        if (i != this._network_type)
        {
          util.set_net_retry_type(this._context, 0);
          util.save_network_type(this._context, this._network_type);
        }
        this._apn = util.get_apn_string(this._context).getBytes();
        this._ksid = util.get_ksid(this._context);
        if ((this._ksid != null) && (this._ksid.length > 0))
          break label366;
        util.LOGD("ksid=null");
        this._apk_id = util.get_apk_id(this._context);
        this._apk_v = util.get_apk_v(this._context, new String(this._apk_id));
        this._apk_sig = util.getPkgPublicKeyFromApkName(this._context, this._context.getPackageName());
        str = Build.MODEL;
        if (str != null)
          break label395;
        this._device = new byte[0];
        label260: if ((util.isFileExist("/system/bin/su")) || (util.isFileExist("/system/xbin/su")) || (util.isFileExist("/sbin/su")))
          break label407;
        j = 0;
        label290: if (j == 0)
          break label413;
      }
    }
    label395: label407: label413: for (int k = 1; ; k = 0)
    {
      this._isroot = k;
      this._rt1 = report_t.read_fromfile(this._context);
      if (this._rt1 == null)
        this._rt1 = new report_t1();
      set_ping_end_flag();
      return;
      this._read_guid = 1;
      break;
      arrayOfByte2 = arrayOfByte1;
      this._read_guid = 1;
      this._new_install = 0;
      this._guid_chg = 0;
      break label79;
      label366: util.LOGD("ksid=" + util.buf_to_string(this._ksid));
      break label192;
      this._device = str.getBytes();
      break label260;
      j = 1;
      break label290;
    }
  }

  public boolean init_service(Context paramContext, boolean paramBoolean1, boolean paramBoolean2, WtloginHelper.HelperHandler paramHelperHandler, int paramInt, request_app_signature paramrequest_app_signature)
  {
    this._push_enable = paramBoolean1;
    this._share_tk_enable = paramBoolean2;
    if (is_push_enable())
    {
      if (this._push == null)
        this._push = new push_client();
      this._push.init(paramContext, paramHelperHandler, paramInt);
    }
    if (is_share_tk_enable())
    {
      if (_sharemem == null)
        _sharemem = new sharemem_client();
      _sharemem.init(paramContext, paramrequest_app_signature);
    }
    return true;
  }

  boolean is_push_enable()
  {
    return this._push_enable;
  }

  boolean is_share_tk_enable()
  {
    return this._share_tk_enable;
  }

  boolean is_use_msf_transport()
  {
    return this._msf_transport_flag != 0;
  }

  public void put_account(String paramString, Long paramLong)
  {
    monitorenter;
    try
    {
      this._account_sig_info_map.put_account(paramString, paramLong);
      if ((_sharemem != null) && (is_share_tk_enable()))
        _sharemem.put_account(paramString, paramLong.longValue());
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

  public int put_siginfo(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[][] paramArrayOfByte5, byte[] paramArrayOfByte6, byte[] paramArrayOfByte7, byte[] paramArrayOfByte8, byte[] paramArrayOfByte9, byte[] paramArrayOfByte10, byte[] paramArrayOfByte11, byte[] paramArrayOfByte12, byte[] paramArrayOfByte13, byte[] paramArrayOfByte14, byte[] paramArrayOfByte15, byte[] paramArrayOfByte16, byte[] paramArrayOfByte17, byte[][] paramArrayOfByte18, long[] paramArrayOfLong)
  {
    monitorenter;
    try
    {
      util.LOGD("put a2 sig:" + paramLong1 + "," + paramLong2 + "," + paramLong3 + "," + paramLong4 + "," + paramLong5 + "," + paramLong6 + "," + util.buf_len(paramArrayOfByte1) + "," + util.buf_len(paramArrayOfByte2) + "," + util.buf_len(paramArrayOfByte3) + "," + util.buf_len(paramArrayOfByte4) + "," + util.buf_len(paramArrayOfByte6) + "," + util.buf_len(paramArrayOfByte7) + "," + util.buf_len(paramArrayOfByte8) + "," + util.buf_len(paramArrayOfByte9) + "," + util.buf_len(paramArrayOfByte10) + "," + util.buf_len(paramArrayOfByte11) + "," + util.buf_len(paramArrayOfByte12) + "," + util.buf_len(paramArrayOfByte13) + "," + util.buf_len(paramArrayOfByte14) + "," + util.buf_len(paramArrayOfByte15) + "," + util.buf_len(paramArrayOfByte16) + "," + util.buf_len(paramArrayOfByte17));
      Object localObject2;
      if (paramArrayOfByte18 != null)
        localObject2 = "";
      for (int j = 0; ; j++)
      {
        int k = paramArrayOfByte18.length;
        if (j >= k)
        {
          String str1 = " " + paramLong1;
          util.LOGD((String)localObject2, str1);
          int i = this._account_sig_info_map.put_siginfo(paramLong1, paramLong2, paramLong3, paramLong4, paramLong5, paramLong6, paramArrayOfByte1, paramArrayOfByte2, paramArrayOfByte3, paramArrayOfByte4, paramArrayOfByte5, paramArrayOfByte6, paramArrayOfByte7, paramArrayOfByte8, paramArrayOfByte9, paramArrayOfByte10, paramArrayOfByte11, paramArrayOfByte12, paramArrayOfByte13, paramArrayOfByte14, paramArrayOfByte15, paramArrayOfByte16, paramArrayOfByte17, paramArrayOfByte18, paramArrayOfLong);
          if ((_sharemem != null) && (is_share_tk_enable()))
            _sharemem.put_siginfo(paramLong1, paramLong2, paramLong3, paramLong4, paramLong5, paramLong6, paramArrayOfByte1, paramArrayOfByte2, paramArrayOfByte3, paramArrayOfByte4, paramArrayOfByte5, paramArrayOfByte6, paramArrayOfByte7, paramArrayOfByte8, paramArrayOfByte9, paramArrayOfByte10, paramArrayOfByte11, paramArrayOfByte12, paramArrayOfByte13, paramArrayOfByte14, paramArrayOfByte15, paramArrayOfByte16, paramArrayOfByte17, paramArrayOfByte18);
          return i;
        }
        String str2 = localObject2 + "reserve[i]:" + util.buf_len(paramArrayOfByte18[j]);
        localObject2 = str2;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public int put_siginfo(long paramLong1, long paramLong2, long paramLong3, long paramLong4, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    monitorenter;
    try
    {
      util.LOGD("put st sig:" + paramLong1 + "," + paramLong2 + "," + paramLong3 + "," + paramLong4 + "," + "," + util.buf_len(paramArrayOfByte1) + "," + util.buf_len(paramArrayOfByte2));
      int i = this._account_sig_info_map.put_siginfo(paramLong1, paramLong2, paramLong3, paramLong4, paramArrayOfByte1, paramArrayOfByte2);
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

  public int register_push(long paramLong1, long paramLong2, long paramLong3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3)
  {
    monitorenter;
    try
    {
      if ((this._push != null) && (is_push_enable()))
      {
        int j = this._push.register(paramLong1, paramLong2, paramLong3, paramString1, paramString2, paramString3, paramArrayOfByte1, paramArrayOfByte2, paramInt1, this._IMEI, paramInt2);
        i = j;
        return i;
      }
      int i = -1011;
    }
    finally
    {
      monitorexit;
    }
  }

  public void remove_account(String paramString)
  {
    monitorenter;
    try
    {
      this._account_sig_info_map.remove_account(paramString);
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

  public void remove_last_login_info(String paramString)
  {
    this._account_sig_info_map.remove_last_login_info(paramString);
  }

  public void save_last_login_info(String paramString)
  {
    this._account_sig_info_map.save_last_login_info(paramString);
  }

  public void set_context(Context paramContext)
  {
    this._context = paramContext;
    this._account_sig_info_map = new account_sig_info_map(paramContext);
    byte[] arrayOfByte = new byte[16];
    this._SR.nextBytes(arrayOfByte);
    System.arraycopy(arrayOfByte, 0, this._rand_key, 0, arrayOfByte.length);
  }

  public void set_context(Context paramContext, int paramInt)
  {
    this._context = paramContext;
    this._account_sig_info_map = new account_sig_info_map(null);
    byte[] arrayOfByte = new byte[16];
    this._SR.nextBytes(arrayOfByte);
    System.arraycopy(arrayOfByte, 0, this._rand_key, 0, arrayOfByte.length);
  }

  public void set_ping_end_flag()
  {
    this._ping_end_flag = 1;
  }

  public int set_push_test(int paramInt, String paramString)
  {
    monitorenter;
    try
    {
      if ((this._push != null) && (is_push_enable()))
      {
        int j = this._push.set_push_test(paramInt, paramString);
        i = j;
        return i;
      }
      int i = -1011;
    }
    finally
    {
      monitorexit;
    }
  }

  public void set_request_pending()
  {
    this._request_pending = 1;
  }

  public int set_share_test(int paramInt, String paramString)
  {
    monitorenter;
    try
    {
      if ((_sharemem != null) && (is_share_tk_enable()))
      {
        int j = _sharemem.set_share_test(paramInt, paramString);
        i = j;
        return i;
      }
      int i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  public void set_time_ip(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    this._init_time = paramArrayOfByte1;
    this._time_difference = ((0xFFFFFFFF & util.buf_to_int32(paramArrayOfByte1, 0)) - System.currentTimeMillis() / 1000L);
    _l_init_time = this._time_difference;
    this._ip_addr = paramArrayOfByte2;
  }

  public void stop_service(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramBoolean1) && (this._push != null))
      this._push.deinit();
    if ((paramBoolean2) && (_sharemem != null))
      _sharemem.deinit();
  }

  public boolean test_request_pending()
  {
    return this._request_pending != 0;
  }

  public int unregister_push(long paramLong1, long paramLong2, long paramLong3, int paramInt)
  {
    monitorenter;
    try
    {
      if ((this._push != null) && (is_push_enable()))
      {
        int j = this._push.un_register(paramLong1, paramLong2, paramLong3, paramInt);
        i = j;
        return i;
      }
      int i = -1011;
    }
    finally
    {
      monitorexit;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.request_global
 * JD-Core Version:    0.6.0
 */