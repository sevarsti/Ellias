package oicq.wlogin_sdk.request;

import B;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;
import oicq.wlogin_sdk.report.report_t1;
import oicq.wlogin_sdk.report.report_t3;
import oicq.wlogin_sdk.tlv_type.tlv_t102;
import oicq.wlogin_sdk.tlv_type.tlv_t103;
import oicq.wlogin_sdk.tlv_type.tlv_t104;
import oicq.wlogin_sdk.tlv_type.tlv_t105;
import oicq.wlogin_sdk.tlv_type.tlv_t106;
import oicq.wlogin_sdk.tlv_type.tlv_t108;
import oicq.wlogin_sdk.tlv_type.tlv_t10a;
import oicq.wlogin_sdk.tlv_type.tlv_t10b;
import oicq.wlogin_sdk.tlv_type.tlv_t10c;
import oicq.wlogin_sdk.tlv_type.tlv_t10d;
import oicq.wlogin_sdk.tlv_type.tlv_t10e;
import oicq.wlogin_sdk.tlv_type.tlv_t113;
import oicq.wlogin_sdk.tlv_type.tlv_t114;
import oicq.wlogin_sdk.tlv_type.tlv_t119;
import oicq.wlogin_sdk.tlv_type.tlv_t11a;
import oicq.wlogin_sdk.tlv_type.tlv_t11c;
import oicq.wlogin_sdk.tlv_type.tlv_t11d;
import oicq.wlogin_sdk.tlv_type.tlv_t11f;
import oicq.wlogin_sdk.tlv_type.tlv_t120;
import oicq.wlogin_sdk.tlv_type.tlv_t121;
import oicq.wlogin_sdk.tlv_type.tlv_t122;
import oicq.wlogin_sdk.tlv_type.tlv_t125;
import oicq.wlogin_sdk.tlv_type.tlv_t126;
import oicq.wlogin_sdk.tlv_type.tlv_t129;
import oicq.wlogin_sdk.tlv_type.tlv_t130;
import oicq.wlogin_sdk.tlv_type.tlv_t132;
import oicq.wlogin_sdk.tlv_type.tlv_t133;
import oicq.wlogin_sdk.tlv_type.tlv_t134;
import oicq.wlogin_sdk.tlv_type.tlv_t136;
import oicq.wlogin_sdk.tlv_type.tlv_t138;
import oicq.wlogin_sdk.tlv_type.tlv_t143;
import oicq.wlogin_sdk.tlv_type.tlv_t146;
import oicq.wlogin_sdk.tlv_type.tlv_t149;
import oicq.wlogin_sdk.tlv_type.tlv_t150;
import oicq.wlogin_sdk.tlv_type.tlv_t164;
import oicq.wlogin_sdk.tlv_type.tlv_t165;
import oicq.wlogin_sdk.tlv_type.tlv_t167;
import oicq.wlogin_sdk.tlv_type.tlv_t305;
import oicq.wlogin_sdk.tools.ErrMsg;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

public class oicq_request
{
  static byte[] _recv_ret_buf = new byte[10240];
  static String _save_host;
  static String[] _static_web_wlogin_ip;
  static String[] _static_wlogin_ip = { "183.60.18.138", "112.90.85.191", "112.90.85.193", "183.60.18.150", "120.196.212.233", "120.204.200.34", "27.115.124.244" };
  static int _test;
  static String _test_host;
  protected static int _test_push;
  protected static String _test_push_host;
  protected byte[] _buf = new byte[this._max];
  protected int _cmd = 0;
  protected int _default_client_seq = 0;
  protected int _default_client_version = 8001;
  protected int _default_ext_instance = 0;
  protected int _default_ext_no = 0;
  protected int _default_ext_retry = 0;
  protected int _default_ext_type = 0;
  protected int _default_ext_version = 3;
  protected int _default_ext_version1 = 0;
  public request_global _g;
  Date _get_ip_time = null;
  int _last_flowid = 0;
  int _max = 2048;
  int _msf_seq = 0;
  int _pos = 0;
  int _rep_body_len = 0;
  int _req_head_len = 27;
  byte _ret;
  protected int _rsp_body_len = 0;
  protected byte[] _rsp_buf = null;
  public int _rsp_head_len = 15;
  InetSocketAddress _server_ip = null;
  int _server_port = 0;
  protected String _service_cmd = "";
  protected int _sub_cmd = 0;
  protected int _transport_type = 0;

  static
  {
    _static_web_wlogin_ip = new String[] { "112.90.141.41", "112.90.141.48", "113.108.11.157", "113.108.11.159", "120.196.212.232" };
    _test = 0;
    _test_host = "";
    _save_host = "";
    _test_push = 0;
    _test_push_host = "";
  }

  public static void set_push_test(int paramInt, String paramString)
  {
    _test_push = paramInt;
    _test_push_host = paramString;
  }

  public static void set_test(int paramInt, String paramString)
  {
    _test = paramInt;
    _test_host = paramString;
  }

  public byte[] decrypt_a1(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte1;
    if ((request_global._IMEI_KEY == null) || (request_global._IMEI_KEY.length <= 0))
    {
      arrayOfByte1 = cryptor.decrypt(paramArrayOfByte, 0, paramArrayOfByte.length, "%4;7t>;28<fc.5*6".getBytes());
      if (arrayOfByte1 == null)
        arrayOfByte1 = (byte[])paramArrayOfByte.clone();
      if ((arrayOfByte1 == null) || (arrayOfByte1.length < 16))
        return null;
    }
    else
    {
      byte[] arrayOfByte3 = new byte[16];
      if (request_global._IMEI_KEY.length > arrayOfByte3.length)
        System.arraycopy(request_global._IMEI_KEY, 0, arrayOfByte3, 0, arrayOfByte3.length);
      while (true)
      {
        arrayOfByte1 = cryptor.decrypt(paramArrayOfByte, 0, paramArrayOfByte.length, arrayOfByte3);
        if ((arrayOfByte1 != null) && (arrayOfByte1.length > 0))
          break;
        arrayOfByte1 = cryptor.decrypt(paramArrayOfByte, 0, paramArrayOfByte.length, "%4;7t>;28<fc.5*6".getBytes());
        break;
        System.arraycopy(request_global._IMEI_KEY, 0, arrayOfByte3, 0, request_global._IMEI_KEY.length);
        for (int j = request_global._IMEI_KEY.length; j < arrayOfByte3.length; j++)
          arrayOfByte3[j] = (byte)(j + 1);
      }
    }
    int i = -16 + arrayOfByte1.length;
    byte[] arrayOfByte2 = new byte[i];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, i);
    this._g._key_tgtgt = new byte[16];
    System.arraycopy(arrayOfByte1, i, this._g._key_tgtgt, 0, 16);
    util.LOGD("decrypt_a1:a1", util.buf_to_string(arrayOfByte2));
    util.LOGD("decrypt_a1:_key_tgtgt", util.buf_to_string(this._g._key_tgtgt));
    return arrayOfByte2;
  }

  public int decrypt_body(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte1 = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte1, paramInt1, arrayOfByte1, 0, paramInt2);
    util.LOGD("decrypt_body:" + util.buf_to_string(arrayOfByte1));
    byte[] arrayOfByte2 = cryptor.decrypt(paramArrayOfByte1, paramInt1, paramInt2, paramArrayOfByte2);
    if (arrayOfByte2 == null)
      return -1002;
    this._rsp_body_len = arrayOfByte2.length;
    if (2 + (arrayOfByte2.length + this._rsp_head_len) > this._max)
    {
      this._max = (2 + (arrayOfByte2.length + this._rsp_head_len));
      byte[] arrayOfByte3 = new byte[this._max];
      System.arraycopy(this._buf, 0, arrayOfByte3, 0, this._pos);
      this._buf = arrayOfByte3;
    }
    this._pos = 0;
    System.arraycopy(arrayOfByte2, 0, this._buf, paramInt1, arrayOfByte2.length);
    this._pos += 2 + this._rsp_head_len + arrayOfByte2.length;
    return 0;
  }

  public byte[] encrypt_a1(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[paramArrayOfByte.length + this._g._key_tgtgt.length];
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, paramArrayOfByte.length);
    System.arraycopy(this._g._key_tgtgt, 0, arrayOfByte, paramArrayOfByte.length, this._g._key_tgtgt.length);
    return arrayOfByte;
  }

  protected byte[] encrypt_body(byte[] paramArrayOfByte)
  {
    util.LOGD("encrypt_body key:" + util.buf_to_string(this._g._rand_key));
    byte[] arrayOfByte1 = cryptor.encrypt(paramArrayOfByte, 0, paramArrayOfByte.length, this._g._rand_key);
    byte[] arrayOfByte2 = new byte[arrayOfByte1.length + this._g._rand_key.length];
    System.arraycopy(this._g._rand_key, 0, arrayOfByte2, 0, this._g._rand_key.length);
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, this._g._rand_key.length, arrayOfByte1.length);
    return arrayOfByte2;
  }

  byte[] encrypt_body(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte1 = new byte[4 + paramArrayOfByte.length];
    util.int16_to_buf(arrayOfByte1, 0, paramInt1);
    util.int16_to_buf(arrayOfByte1, 2, paramInt2);
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte1, 4, paramArrayOfByte.length);
    util.LOGD("encrypt_body key:" + util.buf_to_string(this._g._rand_key));
    byte[] arrayOfByte2 = cryptor.encrypt(arrayOfByte1, 0, arrayOfByte1.length, this._g._rand_key);
    byte[] arrayOfByte3 = new byte[arrayOfByte2.length + this._g._rand_key.length];
    System.arraycopy(this._g._rand_key, 0, arrayOfByte3, 0, this._g._rand_key.length);
    System.arraycopy(arrayOfByte2, 0, arrayOfByte3, this._g._rand_key.length, arrayOfByte2.length);
    return arrayOfByte3;
  }

  public void fill(int paramInt1, int paramInt2, int paramInt3, long paramLong, int paramInt4, int paramInt5, int paramInt6, int paramInt7, byte[] paramArrayOfByte, int paramInt8)
  {
    fill_head(paramInt1, paramInt2, paramInt3, paramLong, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8);
    fill_body(paramArrayOfByte, paramInt8);
    fill_end();
  }

  public void fill_body(byte[] paramArrayOfByte, int paramInt)
  {
    if (1 + (paramInt + this._pos) > this._max)
    {
      this._max = (128 + (1 + (paramInt + this._pos)));
      byte[] arrayOfByte = new byte[this._max];
      System.arraycopy(this._buf, 0, arrayOfByte, 0, this._pos);
      this._buf = arrayOfByte;
    }
    System.arraycopy(paramArrayOfByte, 0, this._buf, this._pos, paramInt);
    this._pos = (paramInt + this._pos);
  }

  public void fill_end()
  {
    util.int8_to_buf(this._buf, this._pos, 3);
    this._pos = (1 + this._pos);
  }

  public void fill_head(int paramInt1, int paramInt2, int paramInt3, long paramLong, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    int i = 1 + this._default_client_seq;
    this._default_client_seq = i;
    this._pos = 0;
    util.int8_to_buf(this._buf, this._pos, 2);
    this._pos = (1 + this._pos);
    if (get_transport_type() == 0)
    {
      util.int16_to_buf(this._buf, this._pos, paramInt8 + (2 + this._req_head_len));
      this._pos = (2 + this._pos);
    }
    util.int16_to_buf(this._buf, this._pos, paramInt1);
    this._pos = (2 + this._pos);
    util.int16_to_buf(this._buf, this._pos, paramInt2);
    this._pos = (2 + this._pos);
    util.int16_to_buf(this._buf, this._pos, i);
    this._pos = (2 + this._pos);
    util.int32_to_buf(this._buf, this._pos, (int)paramLong);
    this._pos = (4 + this._pos);
    util.int8_to_buf(this._buf, this._pos, 3);
    this._pos = (1 + this._pos);
    util.int8_to_buf(this._buf, this._pos, 0);
    this._pos = (1 + this._pos);
    util.int8_to_buf(this._buf, this._pos, paramInt4);
    this._pos = (1 + this._pos);
    util.int32_to_buf(this._buf, this._pos, paramInt5);
    this._pos = (4 + this._pos);
    util.int32_to_buf(this._buf, this._pos, paramInt6);
    this._pos = (4 + this._pos);
    util.int32_to_buf(this._buf, this._pos, paramInt7);
    this._pos = (4 + this._pos);
  }

  public byte[] get_buf()
  {
    byte[] arrayOfByte = new byte[this._pos];
    System.arraycopy(this._buf, 0, arrayOfByte, 0, this._pos);
    return arrayOfByte;
  }

  public void get_err_msg(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    tlv_t146 localtlv_t146 = new tlv_t146();
    if (localtlv_t146.get_tlv(paramArrayOfByte, paramInt1, paramInt2) >= 0)
    {
      this._g._last_err_msg.setTitle(new String(localtlv_t146.get_title()));
      this._g._last_err_msg.setMessage(new String(localtlv_t146.get_msg()));
      this._g._last_err_msg.setOtherinfo(new String(localtlv_t146.get_errorinfo()));
      return;
    }
    this._g._last_err_msg.ClearMsg();
  }

  public String get_host(boolean paramBoolean)
  {
    String[] arrayOfString = new String[2];
    if (paramBoolean)
    {
      arrayOfString[0] = "wlogin.qq.com";
      arrayOfString[1] = "wlogin1.qq.com";
    }
    while (true)
    {
      return arrayOfString[java.lang.Math.abs(new java.util.Random().nextInt() % arrayOfString.length)];
      arrayOfString[0] = "wtlogin.qq.com";
      arrayOfString[1] = "wtlogin1.qq.com";
    }
  }

  public int get_last_flowid()
  {
    return this._last_flowid;
  }

  public int get_port(boolean paramBoolean)
  {
    if (paramBoolean);
    return 443;
  }

  public void get_request(int paramInt1, int paramInt2, int paramInt3, long paramLong, int paramInt4, int paramInt5, int paramInt6, int paramInt7, byte[] paramArrayOfByte)
  {
    fill(paramInt1, paramInt2, paramInt3, paramLong, paramInt4, paramInt5, paramInt6, paramInt7, paramArrayOfByte, paramArrayOfByte.length);
  }

  public int get_response(byte[] paramArrayOfByte, int paramInt)
  {
    if (paramInt <= 2 + this._rsp_head_len)
      return -1009;
    this._rsp_body_len = (-2 + (paramInt - this._rsp_head_len));
    set_buf(paramArrayOfByte, paramInt);
    util.LOGD("decrypt_body key:" + util.buf_to_string(this._g._rand_key));
    int i = decrypt_body(this._buf, 1 + this._rsp_head_len, this._rsp_body_len, this._g._rand_key);
    if (i < 0)
      return i;
    return get_response_body(this._buf, 1 + this._rsp_head_len, this._rsp_body_len);
  }

  public int get_response_body(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    long l1 = 2880L;
    long l2 = 2160000L;
    long l3 = 4294967295L;
    tlv_t104 localtlv_t104 = new tlv_t104();
    tlv_t105 localtlv_t105 = new tlv_t105();
    tlv_t113 localtlv_t113 = new tlv_t113();
    tlv_t119 localtlv_t119 = new tlv_t119();
    tlv_t10d localtlv_t10d = new tlv_t10d();
    tlv_t10e localtlv_t10e = new tlv_t10e();
    tlv_t10a localtlv_t10a = new tlv_t10a();
    tlv_t114 localtlv_t114 = new tlv_t114();
    tlv_t103 localtlv_t103 = new tlv_t103();
    tlv_t11a localtlv_t11a = new tlv_t11a();
    tlv_t102 localtlv_t102 = new tlv_t102();
    tlv_t10b localtlv_t10b = new tlv_t10b();
    tlv_t11c localtlv_t11c = new tlv_t11c();
    tlv_t11d localtlv_t11d = new tlv_t11d();
    tlv_t120 localtlv_t120 = new tlv_t120();
    tlv_t121 localtlv_t121 = new tlv_t121();
    tlv_t130 localtlv_t130 = new tlv_t130();
    tlv_t108 localtlv_t108 = new tlv_t108();
    tlv_t106 localtlv_t106 = new tlv_t106();
    tlv_t10c localtlv_t10c = new tlv_t10c();
    tlv_t125 localtlv_t125 = new tlv_t125();
    tlv_t122 localtlv_t122 = new tlv_t122();
    tlv_t126 localtlv_t126 = new tlv_t126();
    tlv_t129 localtlv_t129 = new tlv_t129();
    tlv_t11f localtlv_t11f = new tlv_t11f();
    tlv_t138 localtlv_t138 = new tlv_t138();
    tlv_t132 localtlv_t132 = new tlv_t132();
    tlv_t149 localtlv_t149 = new tlv_t149();
    tlv_t150 localtlv_t150 = new tlv_t150();
    tlv_t143 localtlv_t143 = new tlv_t143();
    tlv_t305 localtlv_t305 = new tlv_t305();
    tlv_t164 localtlv_t164 = new tlv_t164();
    tlv_t165 localtlv_t165 = new tlv_t165();
    tlv_t167 localtlv_t167 = new tlv_t167();
    tlv_t133 localtlv_t133 = new tlv_t133();
    tlv_t134 localtlv_t134 = new tlv_t134();
    tlv_t136 localtlv_t136 = new tlv_t136();
    int i;
    if ((this._cmd == 2064) && (this._sub_cmd == 9))
      i = 0;
    while (paramInt2 < 5)
    {
      return -1009;
      if ((this._cmd == 2064) && ((this._sub_cmd == 10) || (this._sub_cmd == 11)))
      {
        i = 1;
        continue;
      }
      if ((this._cmd == 2064) && (this._sub_cmd == 2))
      {
        i = 2;
        continue;
      }
      if ((this._cmd == 2064) && (this._sub_cmd == 4))
      {
        i = 3;
        continue;
      }
      if ((this._cmd == 2064) && (this._sub_cmd == 6))
      {
        i = 4;
        continue;
      }
      if ((this._cmd == 2064) && (this._sub_cmd == 7))
      {
        i = 5;
        continue;
      }
      if ((this._cmd == 2064) && (this._sub_cmd == 8))
      {
        i = 6;
        continue;
      }
      if ((this._cmd == 2064) && (this._sub_cmd == 13))
      {
        i = 7;
        continue;
      }
      return -1012;
    }
    int j = get_response_ret_code(paramArrayOfByte, paramInt1 + 2);
    set_err_msg(null);
    util.LOGD(getClass().getName(), "get_response_body type=" + j);
    int k = paramInt1 + 5;
    this._g._t150 = null;
    int m;
    switch (j)
    {
    default:
      get_err_msg(paramArrayOfByte, k, -1 + (this._pos - k));
      m = j;
    case 0:
    case 1:
    case 2:
    case 3:
    case 16:
    case 160:
    case 18:
    case 32:
    case 33:
    case 40:
    case 41:
    case 42:
    case 43:
    case 44:
    case 113:
    case 176:
    }
    while (true)
    {
      if ((i != 2) && (i != 4) && (i != 5) && (i != 6))
        set_last_flowid(i);
      return m;
      if ((i == 1) || ((get_last_flowid() == 1) && (i == 4)))
      {
        if (this._g._master_tgt_key == null)
          return -1006;
        if (localtlv_t150.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k)) >= 0)
          this._g._t150 = localtlv_t150;
        while (true)
        {
          m = localtlv_t119.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k), this._g._master_tgt_key);
          util.LOGD("decrypt key=", util.buf_to_string(this._g._master_tgt_key));
          if (m >= 0)
            break label1432;
          util.LOGD("119 can not decrypt");
          break;
        }
      }
      if (i == 2)
      {
        if (get_last_flowid() == 3)
        {
          if (localtlv_t113.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k)) >= 0)
          {
            this._g._t113 = localtlv_t113;
            this._g._uin = this._g._t113.get_uin();
            this._g.put_account(this._g._name, Long.valueOf(this._g._uin));
            label1025: if (localtlv_t104.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k)) < 0)
              break label1064;
            this._g._t104 = localtlv_t104;
          }
          label1064: 
          while (true)
          {
            m = j;
            break;
            break label1025;
          }
        }
        if (localtlv_t150.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k)) >= 0)
          this._g._t150 = localtlv_t150;
        while (true)
        {
          m = localtlv_t119.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k), this._g._key_tgtgt);
          break;
        }
      }
      if ((i == 3) || ((get_last_flowid() == 3) && (i == 4)))
      {
        m = localtlv_t113.get_tlv(paramArrayOfByte, k, this._pos - k);
        if (m < 0)
          continue;
        this._g._t113 = localtlv_t113;
        this._g._uin = this._g._t113.get_uin();
        this._g.put_account(this._g._name, Long.valueOf(this._g._uin));
        m = localtlv_t104.get_tlv(paramArrayOfByte, k, this._pos - k);
        if (m < 0)
          continue;
        this._g._t104 = localtlv_t104;
        m = j;
        continue;
      }
      if ((i == 7) || ((get_last_flowid() == 7) && (i == 4)))
      {
        if (this._g._master_tgt_key == null)
          return -1006;
        if (localtlv_t150.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k)) >= 0)
          this._g._t150 = localtlv_t150;
        while (true)
        {
          m = localtlv_t119.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k), this._g._master_tgt_key);
          util.LOGD("decrypt key=", util.buf_to_string(this._g._master_tgt_key));
          break;
        }
      }
      if (localtlv_t150.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k)) >= 0)
        this._g._t150 = localtlv_t150;
      while (true)
      {
        m = localtlv_t119.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k), this._g._key_tgtgt);
        break;
      }
      label1432: byte[] arrayOfByte1 = localtlv_t119.get_data();
      int n = arrayOfByte1.length;
      if (localtlv_t149.get_tlv(arrayOfByte1, 2, n) > 0)
        show_alert_dialog(localtlv_t149);
      if (localtlv_t130.get_tlv(arrayOfByte1, 2, n) > 0)
        this._g.set_time_ip(localtlv_t130.get_time(), localtlv_t130.get_ipaddr());
      localtlv_t10d.get_tlv(arrayOfByte1, 2, n);
      localtlv_t10e.get_tlv(arrayOfByte1, 2, n);
      localtlv_t10a.get_tlv(arrayOfByte1, 2, n);
      localtlv_t114.get_tlv(arrayOfByte1, 2, n);
      m = localtlv_t11a.get_tlv(arrayOfByte1, 2, n);
      if (m < 0)
        continue;
      int i1 = localtlv_t103.get_tlv(arrayOfByte1, 2, n);
      byte[] arrayOfByte2 = null;
      if (i1 >= 0)
        arrayOfByte2 = localtlv_t103.get_data();
      if (localtlv_t108.get_tlv(arrayOfByte1, 2, n) >= 0)
        util.set_ksid(this._g._context, localtlv_t108.get_data());
      int i2 = localtlv_t102.get_tlv(arrayOfByte1, 2, n);
      byte[] arrayOfByte3 = null;
      if (i2 >= 0)
        arrayOfByte3 = localtlv_t102.get_data();
      int i3 = localtlv_t10b.get_tlv(arrayOfByte1, 2, n);
      byte[] arrayOfByte4 = null;
      if (i3 >= 0)
        arrayOfByte4 = localtlv_t10b.get_data();
      int i4 = localtlv_t11c.get_tlv(arrayOfByte1, 2, n);
      util.LOGD("t11c ret=", new Integer(i4).toString());
      byte[] arrayOfByte5 = null;
      if (i4 >= 0)
        arrayOfByte5 = localtlv_t11c.get_data();
      util.LOGD("t11c ret=", util.buf_to_string(arrayOfByte5));
      int i5 = localtlv_t120.get_tlv(arrayOfByte1, 2, n);
      byte[] arrayOfByte6 = null;
      if (i5 >= 0)
        arrayOfByte6 = localtlv_t120.get_data();
      int i6 = localtlv_t121.get_tlv(arrayOfByte1, 2, n);
      byte[] arrayOfByte7 = null;
      if (i6 >= 0)
        arrayOfByte7 = localtlv_t121.get_data();
      int i7 = localtlv_t125.get_tlv(arrayOfByte1, 2, n);
      byte[] arrayOfByte8 = null;
      byte[] arrayOfByte9 = null;
      if (i7 >= 0)
      {
        arrayOfByte8 = localtlv_t125.get_openid();
        arrayOfByte9 = localtlv_t125.get_openkey();
      }
      int[] arrayOfInt1 = { 3, 0 };
      byte[][] arrayOfByte10 = (byte[][])Array.newInstance(Byte.TYPE, arrayOfInt1);
      if (localtlv_t167.get_tlv(arrayOfByte1, 2, n) >= 0)
      {
        arrayOfByte10[0] = localtlv_t167.get_img_type();
        arrayOfByte10[1] = localtlv_t167.get_img_format();
        arrayOfByte10[2] = localtlv_t167.get_img_url();
        util.LOGD("type:" + util.buf_to_string(arrayOfByte10[0]) + " format:" + util.buf_to_string(arrayOfByte10[1]) + " url:" + new String(arrayOfByte10[2]));
      }
      int[] arrayOfInt2 = { 8, 0 };
      byte[][] arrayOfByte11 = (byte[][])Array.newInstance(Byte.TYPE, arrayOfInt2);
      int i8 = 0;
      label1968: long[] arrayOfLong;
      int i11;
      if (i8 >= 8)
      {
        if (localtlv_t133.get_tlv(arrayOfByte1, 2, n) >= 0)
          arrayOfByte11[0] = localtlv_t133.get_data();
        if (localtlv_t134.get_tlv(arrayOfByte1, 2, n) >= 0)
          arrayOfByte11[1] = localtlv_t134.get_data();
        if (localtlv_t136.get_tlv(arrayOfByte1, 2, n) >= 0)
          arrayOfByte11[2] = localtlv_t136.get_data();
        int i9 = localtlv_t10c.get_tlv(arrayOfByte1, 2, n);
        if ((localtlv_t106.get_tlv(arrayOfByte1, 2, n) >= 0) && (i9 >= 0))
        {
          util.LOGD("update A1 from server:", util.buf_to_string(localtlv_t106.get_data()));
          util.LOGD("key:", util.buf_to_string(localtlv_t10c.get_data()));
          this._g._key_tgtgt = localtlv_t10c.get_data();
          this._g._encrypt_a1 = encrypt_a1(localtlv_t106.get_data());
          util.LOGD("key2:", util.buf_to_string(this._g._key_tgtgt));
          arrayOfByte11[3] = ((byte[])this._g._encrypt_a1.clone());
        }
        if (localtlv_t132.get_tlv(arrayOfByte1, 2, n) >= 0)
          arrayOfByte11[4] = localtlv_t132.get_access_token();
        if (localtlv_t143.get_tlv(arrayOfByte1, 2, n) >= 0)
          arrayOfByte11[5] = localtlv_t143.get_data();
        if (localtlv_t305.get_tlv(arrayOfByte1, 2, n) >= 0)
          arrayOfByte11[6] = localtlv_t305.get_data();
        if (localtlv_t164.get_tlv(arrayOfByte1, 2, n) >= 0)
          arrayOfByte11[7] = localtlv_t164.get_data();
        if (localtlv_t11f.get_tlv(arrayOfByte1, 2, n) >= 0)
        {
          if (this._g._tk_time_out != -1L)
            l1 = this._g._tk_time_out;
          l3 = 0xFFFFFFFF & localtlv_t11f.get_tk_pri();
          util.LOGD("tk_expire=" + new Long(l1).toString());
        }
        arrayOfLong = new long[7];
        int i10 = 2;
        i10 = localtlv_t138.get_tlv(arrayOfByte1, i10, n);
        if (i10 >= 0)
          break label2530;
        if (l2 < l1)
          l2 = l1;
        this._g.put_siginfo(this._g._uin, this._g._appid, l3, request_global.get_cur_time(), l1 + request_global.get_cur_time(), l2 + request_global.get_cur_time(), localtlv_t11a.get_face(), localtlv_t11a.get_age(), localtlv_t11a.get_gander(), localtlv_t11a.get_nick(), arrayOfByte10, localtlv_t10a.get_data(), localtlv_t10d.get_data(), localtlv_t114.get_data(), localtlv_t10e.get_data(), arrayOfByte2, arrayOfByte4, arrayOfByte3, arrayOfByte5, arrayOfByte6, arrayOfByte7, arrayOfByte8, arrayOfByte9, arrayOfByte11, arrayOfLong);
        if (i != 1)
          this._g.save_last_login_info(this._g._last_login_account);
        i11 = 2;
      }
      while (true)
      {
        i11 = localtlv_t11d.get_tlv(arrayOfByte1, i11, n);
        if (i11 < 0)
        {
          m = 0;
          break;
          arrayOfByte11[i8] = new byte[0];
          i8++;
          break label1968;
          label2530: if (localtlv_t138.get_a2_chg_time() != 0)
          {
            l2 = localtlv_t138.get_a2_chg_time();
            util.LOGD("a2_expire=" + new Long(l2).toString());
          }
          if (localtlv_t138.get_lskey_chg_time() != 0)
          {
            arrayOfLong[0] = localtlv_t138.get_lskey_chg_time();
            label2595: util.LOGD("lskey_expire=" + new Long(arrayOfLong[0]).toString());
            if (localtlv_t138.get_skey_chg_time() == 0)
              break label2949;
            arrayOfLong[1] = localtlv_t138.get_skey_chg_time();
            label2646: util.LOGD("skey_expire=" + new Long(arrayOfLong[1]).toString());
            if (localtlv_t138.get_vkey_chg_time() == 0)
              break label2959;
            arrayOfLong[2] = localtlv_t138.get_vkey_chg_time();
            label2697: util.LOGD("vkey_expire=" + new Long(arrayOfLong[2]).toString());
            if (localtlv_t138.get_a8_chg_time() == 0)
              break label2969;
            arrayOfLong[3] = localtlv_t138.get_a8_chg_time();
            label2748: util.LOGD("a8_expire=" + new Long(arrayOfLong[3]).toString());
            if (localtlv_t138.get_stweb_chg_time() == 0)
              break label2979;
            arrayOfLong[4] = localtlv_t138.get_stweb_chg_time();
            label2799: util.LOGD("stweb_expire=" + new Long(arrayOfLong[4]).toString());
            if (localtlv_t138.get_d2_chg_time() == 0)
              break label2989;
            arrayOfLong[5] = localtlv_t138.get_d2_chg_time();
            label2850: util.LOGD("d2_expire=" + new Long(arrayOfLong[5]).toString());
            if (localtlv_t138.get_sid_chg_time() == 0)
              break label2999;
            arrayOfLong[6] = localtlv_t138.get_sid_chg_time();
          }
          while (true)
          {
            util.LOGD("sid_expire=" + new Long(arrayOfLong[6]).toString());
            break;
            arrayOfLong[0] = 1641600L;
            break label2595;
            label2949: arrayOfLong[1] = 2880L;
            break label2646;
            label2959: arrayOfLong[2] = 1728000L;
            break label2697;
            label2969: arrayOfLong[3] = 72000L;
            break label2748;
            label2979: arrayOfLong[4] = 6000L;
            break label2799;
            label2989: arrayOfLong[5] = 1728000L;
            break label2850;
            label2999: arrayOfLong[6] = 1728000L;
          }
        }
        this._g.put_siginfo(this._g._uin, localtlv_t11d.get_appid(), request_global.get_cur_time(), l1 + request_global.get_cur_time(), localtlv_t11d.get_st(), localtlv_t11d.get_stkey());
      }
      get_err_msg(paramArrayOfByte, k, -1 + (this._pos - k));
      m = j;
      continue;
      m = localtlv_t104.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k));
      if (m < 0)
        continue;
      this._g._t104 = localtlv_t104;
      m = localtlv_t105.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k));
      if (m < 0)
        continue;
      this._g._t105 = localtlv_t105;
      if (localtlv_t165.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k)) >= 0);
      for (this._g._t165 = localtlv_t165; ; this._g._t165 = new tlv_t165())
      {
        if (i == 3)
          this._g._getuin_need_image = 1;
        get_err_msg(paramArrayOfByte, k, -1 + (this._pos - k));
        m = j;
        break;
      }
      m = localtlv_t104.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k));
      if (m < 0)
        continue;
      this._g._t104 = localtlv_t104;
      m = localtlv_t122.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k));
      if (m < 0)
        continue;
      this._g._t122 = localtlv_t122;
      get_err_msg(paramArrayOfByte, k, -1 + (this._pos - k));
      m = j;
      continue;
      m = localtlv_t130.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k));
      if (m < 0)
        continue;
      this._g.set_time_ip(localtlv_t130.get_time(), localtlv_t130.get_ipaddr());
      get_err_msg(paramArrayOfByte, k, -1 + (this._pos - k));
      m = j;
      continue;
      m = localtlv_t104.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k));
      if (m < 0)
        continue;
      this._g._t104 = localtlv_t104;
      m = localtlv_t126.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k));
      if (m < 0)
        continue;
      this._g._t126 = localtlv_t126;
      m = localtlv_t129.get_tlv(paramArrayOfByte, k, -1 + (this._pos - k));
      if (m < 0)
        continue;
      this._g._t129 = localtlv_t129;
      get_err_msg(paramArrayOfByte, k, -1 + (this._pos - k));
      m = j;
      continue;
      get_err_msg(paramArrayOfByte, k, -1 + (this._pos - k));
      m = j;
      continue;
      get_err_msg(paramArrayOfByte, k, -1 + (this._pos - k));
      m = j;
      this._g.remove_account(this._g._name);
    }
  }

  public int get_response_ret_code()
  {
    return this._ret;
  }

  public int get_response_ret_code(byte[] paramArrayOfByte, int paramInt)
  {
    this._ret = paramArrayOfByte[paramInt];
    this._g._ret = this._ret;
    return 0xFF & paramArrayOfByte[paramInt];
  }

  public int get_response_ret_tlv_num(byte[] paramArrayOfByte, int paramInt)
  {
    return (paramArrayOfByte[paramInt] << 8) + paramArrayOfByte[(paramInt + 1)];
  }

  public int get_ret_code()
  {
    return this._ret;
  }

  public int get_rsp_length(byte[] paramArrayOfByte)
  {
    if (get_transport_type() == 0)
      return util.buf_to_int16(paramArrayOfByte, 1);
    return 0;
  }

  public int get_sizeof()
  {
    return this._pos;
  }

  public Socket get_sk()
  {
    if (this._g._sk != null)
      util.LOGD("_sk", "_sk" + this._g._sk.toString());
    while (true)
    {
      return this._g._sk;
      util.LOGD("_sk", "_sknull");
    }
  }

  public String get_static_ip(boolean paramBoolean)
  {
    if (!paramBoolean)
      return _static_wlogin_ip[((int)(2147483647.0D * java.lang.Math.random()) % _static_wlogin_ip.length)];
    return _static_web_wlogin_ip[((int)(2147483647.0D * java.lang.Math.random()) % _static_web_wlogin_ip.length)];
  }

  public int get_transport_type()
  {
    return this._transport_type;
  }

  public String resolve_server_addr(int paramInt, boolean paramBoolean)
  {
    String str = "";
    int i = paramInt / 2;
    if ((_test != 0) && (_test_host != null) && (_test_host.length() > 0))
      str = _test_host;
    while (true)
    {
      _save_host = str;
      util.LOGD("resolve_server_addr OK", "host:" + str + " tryno:" + i);
      return str;
      if (i < 1)
      {
        if (paramBoolean)
          if (this._g._network_type == 1)
            str = new String(util.get_wap_server_host1(this._g._context));
        while (true)
        {
          if ((str != null) && (str.length() > 0))
            break label226;
          str = get_host(paramBoolean);
          break;
          if (this._g._network_type != 2)
            continue;
          str = new String(util.get_wap_server_host2(this._g._context));
          continue;
          if (this._g._network_type == 1)
          {
            str = new String(util.get_server_host1(this._g._context));
            continue;
          }
          if (this._g._network_type != 2)
            continue;
          str = new String(util.get_server_host2(this._g._context));
        }
        label226: continue;
      }
      if (i < 2)
      {
        str = get_host(paramBoolean);
        continue;
      }
      str = get_static_ip(paramBoolean);
    }
  }

  public void set_buf(byte[] paramArrayOfByte, int paramInt)
  {
    if (paramInt > this._max)
    {
      this._max = (paramInt + 128);
      this._buf = new byte[this._max];
    }
    this._pos = paramInt;
    System.arraycopy(paramArrayOfByte, 0, this._buf, 0, paramInt);
  }

  public void set_err_msg(ErrMsg paramErrMsg)
  {
    this._g._last_err_msg.ClearMsg();
    if (paramErrMsg != null);
    try
    {
      this._g._last_err_msg = ((ErrMsg)paramErrMsg.clone());
      return;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
      localCloneNotSupportedException.printStackTrace(localPrintWriter);
      localPrintWriter.flush();
      localStringWriter.flush();
      util.LOGW("exception", localStringWriter.toString());
      this._g._last_err_msg.ClearMsg();
    }
  }

  public int set_last_flowid(int paramInt)
  {
    this._last_flowid = paramInt;
    return paramInt;
  }

  public void set_sk(Socket paramSocket)
  {
    this._g._sk = paramSocket;
  }

  public void show_alert_dialog(tlv_t149 paramtlv_t149)
  {
    try
    {
      ErrMsg localErrMsg = new ErrMsg();
      if (paramtlv_t149 != null)
      {
        localErrMsg.setType(paramtlv_t149.get_type());
        localErrMsg.setTitle(new String(paramtlv_t149.get_title()));
        localErrMsg.setMessage(new String(paramtlv_t149.get_content()));
        localErrMsg.setOtherinfo(new String(paramtlv_t149.get_otherinfo()));
        new alert_thread(this._g._context, localErrMsg).start();
      }
      return;
    }
    catch (Exception localException)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
      localException.printStackTrace(localPrintWriter);
      localPrintWriter.flush();
      localStringWriter.flush();
      util.LOGW("exception", localStringWriter.toString());
    }
  }

  public int snd_rcv_req()
  {
    int i = get_transport_type();
    util.LOGD("snd_rcv_req", getClass().getName());
    int j;
    if (i == 1)
      j = snd_rcv_req_udp();
    while (true)
    {
      util.LOGD(getClass().getName() + "::snd_rcv_req", "ret=" + j + ", type=" + i);
      return j;
      if (i == 0)
      {
        j = snd_rcv_req_tcp();
        continue;
      }
      j = snd_rcv_req_tcp();
    }
  }

  public int snd_rcv_req(String paramString)
  {
    if (this._g.is_use_msf_transport())
      return snd_rcv_req_msf(paramString);
    return snd_rcv_req();
  }

  public int snd_rcv_req_msf(String paramString)
  {
    util.LOGI(getClass().getName() + ":snd_rcv_req_msf ...", this._g._context, this._g._uin, 0);
    int i = this._g._time_out;
    byte[] arrayOfByte1 = get_buf();
    long l = System.currentTimeMillis();
    try
    {
      WtloginMsfListener localWtloginMsfListener = new WtloginMsfListener(paramString, this._service_cmd, arrayOfByte1, i);
      Thread localThread = new Thread(localWtloginMsfListener);
      localThread.start();
      localThread.join(i);
      byte[] arrayOfByte2 = localWtloginMsfListener.getRetData();
      if (arrayOfByte2 == null)
        util.LOGI("recv data from server failed, ret=" + localWtloginMsfListener.getRet(), this._g._context, this._g._uin, 0);
      for (j = -1000; ; j = 0)
      {
        if (this._cmd != 2066)
        {
          localreport_t3 = new report_t3();
          localreport_t3._cmd = this._cmd;
          localreport_t3._sub = this._sub_cmd;
          localreport_t3._rst2 = j;
          localreport_t3._used = (int)(System.currentTimeMillis() - l);
          localreport_t3._try = 0;
          localreport_t3._host = "";
          localreport_t3._ip = "";
          localreport_t3._port = 0;
          localreport_t3._conn = 0;
          localreport_t3._net = 0;
          localreport_t3._str = "";
          if (j != 0)
            break;
          localreport_t3._slen = arrayOfByte1.length;
          localreport_t3._rlen = this._rsp_buf.length;
          localreport_t3._wap = 3;
          this._g._rt1.add_t3(localreport_t3);
        }
        util.LOGI(getClass().getName() + ":snd_rcv_req_msf ret=" + j, this._g._context, this._g._uin, 1);
        return j;
        this._rsp_buf = new byte[arrayOfByte2.length];
        System.arraycopy(arrayOfByte2, 0, this._rsp_buf, 0, arrayOfByte2.length);
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        report_t3 localreport_t3;
        StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
        localException.printStackTrace(localPrintWriter);
        localPrintWriter.flush();
        localStringWriter.flush();
        util.LOGW("exception", localStringWriter.toString(), this._g._context, this._g._uin);
        int j = -1000;
        continue;
        localreport_t3._slen = 0;
        localreport_t3._rlen = 0;
      }
    }
  }

  // ERROR //
  public int snd_rcv_req_tcp()
  {
    // Byte code:
    //   0: getstatic 51	oicq/wlogin_sdk/request/oicq_request:_recv_ret_buf	[B
    //   3: astore_1
    //   4: aload_1
    //   5: monitorenter
    //   6: new 202	java/lang/StringBuilder
    //   9: dup
    //   10: aload_0
    //   11: invokevirtual 463	java/lang/Object:getClass	()Ljava/lang/Class;
    //   14: invokevirtual 468	java/lang/Class:getName	()Ljava/lang/String;
    //   17: invokestatic 909	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   20: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   23: astore_2
    //   24: aload_2
    //   25: ldc_w 1028
    //   28: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   34: aload_0
    //   35: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   38: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   41: new 202	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   48: aload_0
    //   49: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   52: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   55: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   58: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   61: iconst_0
    //   62: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   65: aload_0
    //   66: invokevirtual 943	oicq/wlogin_sdk/request/oicq_request:get_buf	()[B
    //   69: astore 4
    //   71: lconst_0
    //   72: lstore 5
    //   74: aload_0
    //   75: invokevirtual 1030	oicq/wlogin_sdk/request/oicq_request:get_sk	()Ljava/net/Socket;
    //   78: astore 7
    //   80: iconst_0
    //   81: istore 8
    //   83: iconst_0
    //   84: istore 9
    //   86: iconst_0
    //   87: istore 10
    //   89: iconst_0
    //   90: istore 11
    //   92: iconst_0
    //   93: istore 12
    //   95: iconst_0
    //   96: istore 13
    //   98: goto +2657 -> 2755
    //   101: iload 43
    //   103: ifne +25 -> 128
    //   106: aload_0
    //   107: iload 8
    //   109: newarray byte
    //   111: putfield 136	oicq/wlogin_sdk/request/oicq_request:_rsp_buf	[B
    //   114: getstatic 51	oicq/wlogin_sdk/request/oicq_request:_recv_ret_buf	[B
    //   117: iconst_0
    //   118: aload_0
    //   119: getfield 136	oicq/wlogin_sdk/request/oicq_request:_rsp_buf	[B
    //   122: iconst_0
    //   123: iload 8
    //   125: invokestatic 179	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   128: iload 43
    //   130: ifne +184 -> 314
    //   133: aload_0
    //   134: getfield 138	oicq/wlogin_sdk/request/oicq_request:_cmd	I
    //   137: sipush 2066
    //   140: if_icmpeq +174 -> 314
    //   143: new 970	oicq/wlogin_sdk/report/report_t3
    //   146: dup
    //   147: invokespecial 971	oicq/wlogin_sdk/report/report_t3:<init>	()V
    //   150: astore 44
    //   152: aload 44
    //   154: aload_0
    //   155: getfield 138	oicq/wlogin_sdk/request/oicq_request:_cmd	I
    //   158: putfield 972	oicq/wlogin_sdk/report/report_t3:_cmd	I
    //   161: aload 44
    //   163: aload_0
    //   164: getfield 140	oicq/wlogin_sdk/request/oicq_request:_sub_cmd	I
    //   167: putfield 975	oicq/wlogin_sdk/report/report_t3:_sub	I
    //   170: aload 44
    //   172: iload 43
    //   174: putfield 978	oicq/wlogin_sdk/report/report_t3:_rst2	I
    //   177: aload 44
    //   179: invokestatic 946	java/lang/System:currentTimeMillis	()J
    //   182: lload 5
    //   184: lsub
    //   185: l2i
    //   186: putfield 981	oicq/wlogin_sdk/report/report_t3:_used	I
    //   189: aload 44
    //   191: iload 13
    //   193: putfield 984	oicq/wlogin_sdk/report/report_t3:_try	I
    //   196: aload 44
    //   198: getstatic 89	oicq/wlogin_sdk/request/oicq_request:_save_host	Ljava/lang/String;
    //   201: putfield 987	oicq/wlogin_sdk/report/report_t3:_host	Ljava/lang/String;
    //   204: aload 44
    //   206: getfield 987	oicq/wlogin_sdk/report/report_t3:_host	Ljava/lang/String;
    //   209: ifnonnull +10 -> 219
    //   212: aload 44
    //   214: ldc 85
    //   216: putfield 987	oicq/wlogin_sdk/report/report_t3:_host	Ljava/lang/String;
    //   219: aload_0
    //   220: getfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   223: ifnonnull +2479 -> 2702
    //   226: aload 44
    //   228: ldc 85
    //   230: putfield 990	oicq/wlogin_sdk/report/report_t3:_ip	Ljava/lang/String;
    //   233: aload 44
    //   235: aload_0
    //   236: getfield 132	oicq/wlogin_sdk/request/oicq_request:_server_port	I
    //   239: putfield 993	oicq/wlogin_sdk/report/report_t3:_port	I
    //   242: aload 44
    //   244: iload 9
    //   246: putfield 996	oicq/wlogin_sdk/report/report_t3:_conn	I
    //   249: aload 44
    //   251: aload_0
    //   252: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   255: getfield 832	oicq/wlogin_sdk/request/request_global:_network_type	I
    //   258: putfield 999	oicq/wlogin_sdk/report/report_t3:_net	I
    //   261: aload 44
    //   263: ldc 85
    //   265: putfield 1002	oicq/wlogin_sdk/report/report_t3:_str	Ljava/lang/String;
    //   268: aload 44
    //   270: aload 4
    //   272: arraylength
    //   273: putfield 1005	oicq/wlogin_sdk/report/report_t3:_slen	I
    //   276: aload 44
    //   278: aload_0
    //   279: getfield 136	oicq/wlogin_sdk/request/oicq_request:_rsp_buf	[B
    //   282: arraylength
    //   283: putfield 1008	oicq/wlogin_sdk/report/report_t3:_rlen	I
    //   286: iload 11
    //   288: ifeq +2441 -> 2729
    //   291: iload 10
    //   293: ifeq +2427 -> 2720
    //   296: aload 44
    //   298: iconst_2
    //   299: putfield 1011	oicq/wlogin_sdk/report/report_t3:_wap	I
    //   302: aload_0
    //   303: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   306: getfield 1015	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   309: aload 44
    //   311: invokevirtual 1021	oicq/wlogin_sdk/report/report_t1:add_t3	(Loicq/wlogin_sdk/report/report_t3;)V
    //   314: new 202	java/lang/StringBuilder
    //   317: dup
    //   318: aload_0
    //   319: invokevirtual 463	java/lang/Object:getClass	()Ljava/lang/Class;
    //   322: invokevirtual 468	java/lang/Class:getName	()Ljava/lang/String;
    //   325: invokestatic 909	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   328: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   331: astore 45
    //   333: aload 45
    //   335: ldc_w 1032
    //   338: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   341: iload 43
    //   343: invokevirtual 473	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   346: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   349: aload_0
    //   350: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   353: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   356: new 202	java/lang/StringBuilder
    //   359: dup
    //   360: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   363: aload_0
    //   364: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   367: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   370: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   373: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   376: iconst_1
    //   377: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   380: aload_1
    //   381: monitorexit
    //   382: iload 43
    //   384: ireturn
    //   385: iload 13
    //   387: ifeq +13 -> 400
    //   390: aload_0
    //   391: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   394: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   397: invokestatic 1036	oicq/wlogin_sdk/tools/util:chg_retry_type	(Landroid/content/Context;)V
    //   400: aload_0
    //   401: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   404: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   407: invokestatic 1040	oicq/wlogin_sdk/tools/util:is_wap_retry	(Landroid/content/Context;)Z
    //   410: istore 11
    //   412: aload_0
    //   413: aconst_null
    //   414: putfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   417: iload 13
    //   419: ifeq +180 -> 599
    //   422: aload_0
    //   423: getfield 138	oicq/wlogin_sdk/request/oicq_request:_cmd	I
    //   426: sipush 2066
    //   429: if_icmpeq +170 -> 599
    //   432: new 970	oicq/wlogin_sdk/report/report_t3
    //   435: dup
    //   436: invokespecial 971	oicq/wlogin_sdk/report/report_t3:<init>	()V
    //   439: astore 14
    //   441: aload 14
    //   443: aload_0
    //   444: getfield 138	oicq/wlogin_sdk/request/oicq_request:_cmd	I
    //   447: putfield 972	oicq/wlogin_sdk/report/report_t3:_cmd	I
    //   450: aload 14
    //   452: aload_0
    //   453: getfield 140	oicq/wlogin_sdk/request/oicq_request:_sub_cmd	I
    //   456: putfield 975	oicq/wlogin_sdk/report/report_t3:_sub	I
    //   459: aload 14
    //   461: iload 12
    //   463: putfield 978	oicq/wlogin_sdk/report/report_t3:_rst2	I
    //   466: aload 14
    //   468: invokestatic 946	java/lang/System:currentTimeMillis	()J
    //   471: lload 5
    //   473: lsub
    //   474: l2i
    //   475: putfield 981	oicq/wlogin_sdk/report/report_t3:_used	I
    //   478: aload 14
    //   480: iload 13
    //   482: putfield 984	oicq/wlogin_sdk/report/report_t3:_try	I
    //   485: aload 14
    //   487: getstatic 89	oicq/wlogin_sdk/request/oicq_request:_save_host	Ljava/lang/String;
    //   490: putfield 987	oicq/wlogin_sdk/report/report_t3:_host	Ljava/lang/String;
    //   493: aload 14
    //   495: getfield 987	oicq/wlogin_sdk/report/report_t3:_host	Ljava/lang/String;
    //   498: ifnonnull +10 -> 508
    //   501: aload 14
    //   503: ldc 85
    //   505: putfield 987	oicq/wlogin_sdk/report/report_t3:_host	Ljava/lang/String;
    //   508: aload_0
    //   509: getfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   512: ifnonnull +675 -> 1187
    //   515: aload 14
    //   517: ldc 85
    //   519: putfield 990	oicq/wlogin_sdk/report/report_t3:_ip	Ljava/lang/String;
    //   522: aload 14
    //   524: aload_0
    //   525: iload 11
    //   527: invokevirtual 1042	oicq/wlogin_sdk/request/oicq_request:get_port	(Z)I
    //   530: putfield 993	oicq/wlogin_sdk/report/report_t3:_port	I
    //   533: aload 14
    //   535: iload 9
    //   537: putfield 996	oicq/wlogin_sdk/report/report_t3:_conn	I
    //   540: aload 14
    //   542: aload_0
    //   543: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   546: getfield 832	oicq/wlogin_sdk/request/request_global:_network_type	I
    //   549: putfield 999	oicq/wlogin_sdk/report/report_t3:_net	I
    //   552: aload 14
    //   554: ldc 85
    //   556: putfield 1002	oicq/wlogin_sdk/report/report_t3:_str	Ljava/lang/String;
    //   559: aload 14
    //   561: iconst_0
    //   562: putfield 1005	oicq/wlogin_sdk/report/report_t3:_slen	I
    //   565: aload 14
    //   567: iconst_0
    //   568: putfield 1008	oicq/wlogin_sdk/report/report_t3:_rlen	I
    //   571: iload 11
    //   573: ifeq +646 -> 1219
    //   576: iload 10
    //   578: ifeq +632 -> 1210
    //   581: aload 14
    //   583: iconst_2
    //   584: putfield 1011	oicq/wlogin_sdk/report/report_t3:_wap	I
    //   587: aload_0
    //   588: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   591: getfield 1015	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   594: aload 14
    //   596: invokevirtual 1021	oicq/wlogin_sdk/report/report_t1:add_t3	(Loicq/wlogin_sdk/report/report_t3;)V
    //   599: invokestatic 946	java/lang/System:currentTimeMillis	()J
    //   602: lstore 5
    //   604: iload 11
    //   606: ifeq +1007 -> 1613
    //   609: new 202	java/lang/StringBuilder
    //   612: dup
    //   613: ldc_w 1044
    //   616: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   619: astore 15
    //   621: aload 15
    //   623: iload 13
    //   625: invokevirtual 473	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   628: ldc_w 1046
    //   631: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   634: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   637: aload_0
    //   638: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   641: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   644: new 202	java/lang/StringBuilder
    //   647: dup
    //   648: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   651: aload_0
    //   652: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   655: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   658: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   661: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   664: iconst_0
    //   665: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   668: ldc_w 1048
    //   671: invokestatic 217	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   674: aload_0
    //   675: iload 13
    //   677: iload 11
    //   679: invokevirtual 1050	oicq/wlogin_sdk/request/oicq_request:resolve_server_addr	(IZ)Ljava/lang/String;
    //   682: astore 16
    //   684: iconst_m1
    //   685: istore 17
    //   687: aload_0
    //   688: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   691: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   694: invokestatic 1053	oicq/wlogin_sdk/tools/util:is_wap_proxy_retry	(Landroid/content/Context;)Z
    //   697: istore 10
    //   699: aconst_null
    //   700: astore 21
    //   702: iload 10
    //   704: ifeq +109 -> 813
    //   707: invokestatic 1056	oicq/wlogin_sdk/tools/util:get_proxy_ip	()Ljava/lang/String;
    //   710: astore 21
    //   712: invokestatic 1059	oicq/wlogin_sdk/tools/util:get_proxy_port	()I
    //   715: istore 17
    //   717: aload 21
    //   719: ifnull +17 -> 736
    //   722: aload 21
    //   724: invokevirtual 823	java/lang/String:length	()I
    //   727: ifle +9 -> 736
    //   730: iload 17
    //   732: iconst_m1
    //   733: if_icmpne +80 -> 813
    //   736: iconst_0
    //   737: istore 10
    //   739: new 202	java/lang/StringBuilder
    //   742: dup
    //   743: ldc_w 1061
    //   746: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   749: astore 48
    //   751: aload 48
    //   753: aload 21
    //   755: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   758: ldc_w 1063
    //   761: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   764: iload 17
    //   766: invokevirtual 473	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   769: ldc_w 1065
    //   772: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   775: iconst_0
    //   776: invokevirtual 1068	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   779: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   782: aload_0
    //   783: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   786: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   789: new 202	java/lang/StringBuilder
    //   792: dup
    //   793: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   796: aload_0
    //   797: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   800: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   803: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   806: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   809: iconst_0
    //   810: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   813: new 202	java/lang/StringBuilder
    //   816: dup
    //   817: ldc_w 1070
    //   820: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   823: astore 22
    //   825: aload 22
    //   827: iload 10
    //   829: invokevirtual 1068	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   832: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   835: invokestatic 217	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   838: iload 10
    //   840: ifeq +388 -> 1228
    //   843: new 202	java/lang/StringBuilder
    //   846: dup
    //   847: ldc_w 1072
    //   850: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   853: astore 23
    //   855: new 1074	java/net/URL
    //   858: dup
    //   859: aload 23
    //   861: aload 21
    //   863: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   866: ldc_w 1076
    //   869: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   872: iload 17
    //   874: invokevirtual 473	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   877: ldc_w 1078
    //   880: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   883: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   886: invokespecial 1079	java/net/URL:<init>	(Ljava/lang/String;)V
    //   889: astore 24
    //   891: new 202	java/lang/StringBuilder
    //   894: dup
    //   895: ldc_w 1081
    //   898: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   901: astore 25
    //   903: aload 25
    //   905: aload 24
    //   907: invokevirtual 1084	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   910: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   913: invokestatic 217	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   916: new 202	java/lang/StringBuilder
    //   919: dup
    //   920: ldc_w 1086
    //   923: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   926: astore 26
    //   928: aload 26
    //   930: iload 10
    //   932: invokevirtual 1068	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   935: ldc_w 1088
    //   938: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   941: aload 24
    //   943: invokevirtual 1084	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   946: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   949: aload_0
    //   950: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   953: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   956: new 202	java/lang/StringBuilder
    //   959: dup
    //   960: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   963: aload_0
    //   964: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   967: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   970: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   973: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   976: iconst_0
    //   977: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   980: aload 24
    //   982: invokevirtual 1092	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   985: checkcast 1094	java/net/HttpURLConnection
    //   988: astore 27
    //   990: aload 27
    //   992: ldc_w 1096
    //   995: invokevirtual 1099	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   998: iload 10
    //   1000: ifeq +13 -> 1013
    //   1003: aload 27
    //   1005: ldc_w 1101
    //   1008: aload 16
    //   1010: invokevirtual 1104	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   1013: aload 27
    //   1015: ldc_w 1106
    //   1018: ldc_w 1108
    //   1021: invokevirtual 1104	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   1024: aload 27
    //   1026: ldc_w 1110
    //   1029: ldc_w 1112
    //   1032: invokevirtual 1104	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   1035: aload 27
    //   1037: ldc_w 1114
    //   1040: new 570	java/lang/Integer
    //   1043: dup
    //   1044: aload 4
    //   1046: arraylength
    //   1047: invokespecial 573	java/lang/Integer:<init>	(I)V
    //   1050: invokevirtual 574	java/lang/Integer:toString	()Ljava/lang/String;
    //   1053: invokevirtual 1104	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   1056: aload 27
    //   1058: aload_0
    //   1059: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1062: getfield 941	oicq/wlogin_sdk/request/request_global:_time_out	I
    //   1065: invokevirtual 1117	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   1068: aload 27
    //   1070: aload_0
    //   1071: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1074: getfield 941	oicq/wlogin_sdk/request/request_global:_time_out	I
    //   1077: invokevirtual 1120	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   1080: aload 27
    //   1082: iconst_1
    //   1083: invokevirtual 1124	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   1086: aload 27
    //   1088: iconst_1
    //   1089: invokevirtual 1127	java/net/HttpURLConnection:setDoInput	(Z)V
    //   1092: ldc_w 1129
    //   1095: aload_0
    //   1096: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1099: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   1102: new 202	java/lang/StringBuilder
    //   1105: dup
    //   1106: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   1109: aload_0
    //   1110: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1113: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1116: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1119: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1122: iconst_0
    //   1123: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   1126: aload 27
    //   1128: aload_0
    //   1129: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1132: getfield 941	oicq/wlogin_sdk/request/request_global:_time_out	I
    //   1135: i2l
    //   1136: invokestatic 1135	oicq/wlogin_sdk/request/http_connect_ontime:connect_ontime	(Ljava/net/HttpURLConnection;J)Z
    //   1139: ifne +129 -> 1268
    //   1142: ldc_w 1137
    //   1145: aload_0
    //   1146: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1149: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   1152: new 202	java/lang/StringBuilder
    //   1155: dup
    //   1156: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   1159: aload_0
    //   1160: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1163: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1166: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1169: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1172: iconst_1
    //   1173: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   1176: sipush -1000
    //   1179: istore 12
    //   1181: iinc 13 1
    //   1184: goto +1571 -> 2755
    //   1187: aload 14
    //   1189: aload_0
    //   1190: getfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   1193: invokevirtual 1143	java/net/InetSocketAddress:getAddress	()Ljava/net/InetAddress;
    //   1196: invokevirtual 1148	java/net/InetAddress:getHostAddress	()Ljava/lang/String;
    //   1199: putfield 990	oicq/wlogin_sdk/report/report_t3:_ip	Ljava/lang/String;
    //   1202: goto -680 -> 522
    //   1205: astore_3
    //   1206: aload_1
    //   1207: monitorexit
    //   1208: aload_3
    //   1209: athrow
    //   1210: aload 14
    //   1212: iconst_1
    //   1213: putfield 1011	oicq/wlogin_sdk/report/report_t3:_wap	I
    //   1216: goto -629 -> 587
    //   1219: aload 14
    //   1221: iconst_0
    //   1222: putfield 1011	oicq/wlogin_sdk/report/report_t3:_wap	I
    //   1225: goto -638 -> 587
    //   1228: new 202	java/lang/StringBuilder
    //   1231: dup
    //   1232: ldc_w 1072
    //   1235: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1238: astore 47
    //   1240: new 1074	java/net/URL
    //   1243: dup
    //   1244: aload 47
    //   1246: aload 16
    //   1248: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1251: ldc_w 1078
    //   1254: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1257: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1260: invokespecial 1079	java/net/URL:<init>	(Ljava/lang/String;)V
    //   1263: astore 24
    //   1265: goto -374 -> 891
    //   1268: ldc_w 1150
    //   1271: aload_0
    //   1272: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1275: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   1278: new 202	java/lang/StringBuilder
    //   1281: dup
    //   1282: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   1285: aload_0
    //   1286: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1289: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1292: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1295: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1298: iconst_0
    //   1299: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   1302: aload 27
    //   1304: invokevirtual 1154	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   1307: astore 28
    //   1309: aload 28
    //   1311: aload 4
    //   1313: iconst_0
    //   1314: aload 4
    //   1316: arraylength
    //   1317: invokevirtual 1159	java/io/OutputStream:write	([BII)V
    //   1320: aload 28
    //   1322: invokevirtual 1160	java/io/OutputStream:flush	()V
    //   1325: aload 27
    //   1327: invokevirtual 1163	java/net/HttpURLConnection:getResponseCode	()I
    //   1330: istore 29
    //   1332: new 202	java/lang/StringBuilder
    //   1335: dup
    //   1336: ldc_w 1165
    //   1339: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1342: astore 30
    //   1344: aload 30
    //   1346: iload 29
    //   1348: invokevirtual 473	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1351: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1354: aload_0
    //   1355: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1358: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   1361: new 202	java/lang/StringBuilder
    //   1364: dup
    //   1365: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   1368: aload_0
    //   1369: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1372: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1375: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1378: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1381: iconst_0
    //   1382: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   1385: sipush 200
    //   1388: iload 29
    //   1390: if_icmpeq +53 -> 1443
    //   1393: new 202	java/lang/StringBuilder
    //   1396: dup
    //   1397: ldc_w 1167
    //   1400: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1403: astore 31
    //   1405: aload 31
    //   1407: iload 29
    //   1409: invokevirtual 473	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1412: ldc_w 1169
    //   1415: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1418: aload 27
    //   1420: invokevirtual 1172	java/net/HttpURLConnection:getResponseMessage	()Ljava/lang/String;
    //   1423: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1426: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1429: invokestatic 217	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   1432: sipush -1000
    //   1435: istore 12
    //   1437: iinc 13 1
    //   1440: goto +1315 -> 2755
    //   1443: aload 27
    //   1445: invokevirtual 1176	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   1448: astore 32
    //   1450: aload 32
    //   1452: astore 33
    //   1454: aload 7
    //   1456: astore 34
    //   1458: ldc_w 1178
    //   1461: aload_0
    //   1462: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1465: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   1468: new 202	java/lang/StringBuilder
    //   1471: dup
    //   1472: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   1475: aload_0
    //   1476: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1479: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1482: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1485: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1488: iconst_0
    //   1489: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   1492: iconst_0
    //   1493: istore 39
    //   1495: iconst_0
    //   1496: istore 40
    //   1498: iload 40
    //   1500: iconst_1
    //   1501: aload_0
    //   1502: getfield 106	oicq/wlogin_sdk/request/oicq_request:_rsp_head_len	I
    //   1505: iadd
    //   1506: if_icmplt +862 -> 2368
    //   1509: iload 39
    //   1511: ifge +893 -> 2404
    //   1514: sipush -1000
    //   1517: istore 12
    //   1519: iinc 13 1
    //   1522: iload 11
    //   1524: ifne +1264 -> 2788
    //   1527: aload 34
    //   1529: invokevirtual 1181	java/net/Socket:close	()V
    //   1532: aload_0
    //   1533: aconst_null
    //   1534: putfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   1537: aconst_null
    //   1538: astore 34
    //   1540: aload_0
    //   1541: aconst_null
    //   1542: invokevirtual 1183	oicq/wlogin_sdk/request/oicq_request:set_sk	(Ljava/net/Socket;)V
    //   1545: aconst_null
    //   1546: astore 7
    //   1548: goto +1207 -> 2755
    //   1551: astore 18
    //   1553: new 854	java/io/StringWriter
    //   1556: dup
    //   1557: invokespecial 855	java/io/StringWriter:<init>	()V
    //   1560: astore 19
    //   1562: new 857	java/io/PrintWriter
    //   1565: dup
    //   1566: aload 19
    //   1568: iconst_1
    //   1569: invokespecial 860	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   1572: astore 20
    //   1574: aload 18
    //   1576: aload 20
    //   1578: invokevirtual 901	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   1581: aload 20
    //   1583: invokevirtual 867	java/io/PrintWriter:flush	()V
    //   1586: aload 19
    //   1588: invokevirtual 868	java/io/StringWriter:flush	()V
    //   1591: ldc_w 870
    //   1594: aload 19
    //   1596: invokevirtual 871	java/io/StringWriter:toString	()Ljava/lang/String;
    //   1599: invokestatic 874	oicq/wlogin_sdk/tools/util:LOGW	(Ljava/lang/String;Ljava/lang/String;)V
    //   1602: sipush -1000
    //   1605: istore 12
    //   1607: iinc 13 1
    //   1610: goto +1145 -> 2755
    //   1613: new 202	java/lang/StringBuilder
    //   1616: dup
    //   1617: ldc_w 1185
    //   1620: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1623: astore 49
    //   1625: aload 49
    //   1627: iload 13
    //   1629: invokevirtual 473	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1632: ldc_w 1046
    //   1635: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1638: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1641: aload_0
    //   1642: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1645: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   1648: new 202	java/lang/StringBuilder
    //   1651: dup
    //   1652: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   1655: aload_0
    //   1656: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1659: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1662: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1665: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1668: iconst_0
    //   1669: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   1672: ldc_w 1187
    //   1675: invokestatic 217	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   1678: aload 7
    //   1680: ifnonnull +344 -> 2024
    //   1683: ldc 85
    //   1685: astore 62
    //   1687: iconst_1
    //   1688: istore 9
    //   1690: aload_0
    //   1691: getfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   1694: ifnonnull +111 -> 1805
    //   1697: aload_0
    //   1698: iload 13
    //   1700: iload 11
    //   1702: invokevirtual 1050	oicq/wlogin_sdk/request/oicq_request:resolve_server_addr	(IZ)Ljava/lang/String;
    //   1705: astore 62
    //   1707: new 202	java/lang/StringBuilder
    //   1710: dup
    //   1711: ldc_w 1189
    //   1714: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1717: astore 65
    //   1719: aload 65
    //   1721: aload 62
    //   1723: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1726: ldc_w 1191
    //   1729: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1732: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1735: aload_0
    //   1736: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1739: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   1742: new 202	java/lang/StringBuilder
    //   1745: dup
    //   1746: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   1749: aload_0
    //   1750: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1753: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1756: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1759: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1762: iconst_0
    //   1763: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   1766: aload_0
    //   1767: aload_0
    //   1768: iload 11
    //   1770: invokevirtual 1042	oicq/wlogin_sdk/request/oicq_request:get_port	(Z)I
    //   1773: putfield 132	oicq/wlogin_sdk/request/oicq_request:_server_port	I
    //   1776: aload_0
    //   1777: getfield 132	oicq/wlogin_sdk/request/oicq_request:_server_port	I
    //   1780: istore 69
    //   1782: aload_0
    //   1783: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1786: getfield 941	oicq/wlogin_sdk/request/request_global:_time_out	I
    //   1789: i2l
    //   1790: lstore 70
    //   1792: aload_0
    //   1793: aload 62
    //   1795: iload 69
    //   1797: lload 70
    //   1799: invokestatic 1197	oicq/wlogin_sdk/request/DNS_resolver:get_DNS_resolver	(Ljava/lang/String;IJ)Ljava/net/InetSocketAddress;
    //   1802: putfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   1805: aload_0
    //   1806: getfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   1809: ifnonnull +140 -> 1949
    //   1812: new 202	java/lang/StringBuilder
    //   1815: dup
    //   1816: ldc_w 1189
    //   1819: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1822: astore 63
    //   1824: aload 63
    //   1826: aload 62
    //   1828: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1831: ldc_w 1199
    //   1834: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1837: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1840: aload_0
    //   1841: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1844: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   1847: new 202	java/lang/StringBuilder
    //   1850: dup
    //   1851: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   1854: aload_0
    //   1855: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1858: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   1861: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1864: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1867: iconst_1
    //   1868: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   1871: sipush -1007
    //   1874: istore 12
    //   1876: iinc 13 1
    //   1879: aload_0
    //   1880: aconst_null
    //   1881: putfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   1884: aload_0
    //   1885: aconst_null
    //   1886: invokevirtual 1183	oicq/wlogin_sdk/request/oicq_request:set_sk	(Ljava/net/Socket;)V
    //   1889: aconst_null
    //   1890: astore 7
    //   1892: goto +863 -> 2755
    //   1895: astore 66
    //   1897: new 854	java/io/StringWriter
    //   1900: dup
    //   1901: invokespecial 855	java/io/StringWriter:<init>	()V
    //   1904: astore 67
    //   1906: new 857	java/io/PrintWriter
    //   1909: dup
    //   1910: aload 67
    //   1912: iconst_1
    //   1913: invokespecial 860	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   1916: astore 68
    //   1918: aload 66
    //   1920: aload 68
    //   1922: invokevirtual 901	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   1925: aload 68
    //   1927: invokevirtual 867	java/io/PrintWriter:flush	()V
    //   1930: aload 67
    //   1932: invokevirtual 868	java/io/StringWriter:flush	()V
    //   1935: ldc_w 870
    //   1938: aload 67
    //   1940: invokevirtual 871	java/io/StringWriter:toString	()Ljava/lang/String;
    //   1943: invokestatic 874	oicq/wlogin_sdk/tools/util:LOGW	(Ljava/lang/String;Ljava/lang/String;)V
    //   1946: goto -141 -> 1805
    //   1949: new 202	java/lang/StringBuilder
    //   1952: dup
    //   1953: ldc_w 1189
    //   1956: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1959: astore 64
    //   1961: aload 64
    //   1963: aload 62
    //   1965: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1968: ldc_w 1201
    //   1971: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1974: aload_0
    //   1975: getfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   1978: invokevirtual 1202	java/net/InetSocketAddress:toString	()Ljava/lang/String;
    //   1981: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1984: ldc_w 1204
    //   1987: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1990: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1993: aload_0
    //   1994: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   1997: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   2000: new 202	java/lang/StringBuilder
    //   2003: dup
    //   2004: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   2007: aload_0
    //   2008: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   2011: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   2014: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2017: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2020: iconst_0
    //   2021: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   2024: aload 7
    //   2026: ifnonnull +722 -> 2748
    //   2029: new 202	java/lang/StringBuilder
    //   2032: dup
    //   2033: ldc_w 1206
    //   2036: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2039: astore 55
    //   2041: aload 55
    //   2043: aload_0
    //   2044: getfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   2047: invokevirtual 1084	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2050: ldc_w 1191
    //   2053: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2056: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2059: aload_0
    //   2060: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   2063: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   2066: new 202	java/lang/StringBuilder
    //   2069: dup
    //   2070: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   2073: aload_0
    //   2074: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   2077: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   2080: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2083: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2086: iconst_0
    //   2087: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   2090: new 808	java/net/Socket
    //   2093: dup
    //   2094: invokespecial 1207	java/net/Socket:<init>	()V
    //   2097: astore 34
    //   2099: aload_0
    //   2100: aload 34
    //   2102: invokevirtual 1183	oicq/wlogin_sdk/request/oicq_request:set_sk	(Ljava/net/Socket;)V
    //   2105: aload_0
    //   2106: getfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   2109: astore 57
    //   2111: aload_0
    //   2112: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   2115: getfield 941	oicq/wlogin_sdk/request/request_global:_time_out	I
    //   2118: istore 58
    //   2120: aload 34
    //   2122: aload 57
    //   2124: iload 58
    //   2126: invokevirtual 1211	java/net/Socket:connect	(Ljava/net/SocketAddress;I)V
    //   2129: aload_0
    //   2130: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   2133: getfield 941	oicq/wlogin_sdk/request/request_global:_time_out	I
    //   2136: istore 59
    //   2138: aload 34
    //   2140: iload 59
    //   2142: invokevirtual 1214	java/net/Socket:setSoTimeout	(I)V
    //   2145: getstatic 51	oicq/wlogin_sdk/request/oicq_request:_recv_ret_buf	[B
    //   2148: arraylength
    //   2149: istore 60
    //   2151: aload 34
    //   2153: iload 60
    //   2155: invokevirtual 1217	java/net/Socket:setReceiveBufferSize	(I)V
    //   2158: new 202	java/lang/StringBuilder
    //   2161: dup
    //   2162: ldc_w 1206
    //   2165: invokespecial 207	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2168: astore 61
    //   2170: aload 61
    //   2172: aload_0
    //   2173: getfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   2176: invokevirtual 1084	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2179: ldc_w 1219
    //   2182: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2185: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2188: aload_0
    //   2189: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   2192: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   2195: new 202	java/lang/StringBuilder
    //   2198: dup
    //   2199: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   2202: aload_0
    //   2203: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   2206: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   2209: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2212: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2215: iconst_0
    //   2216: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   2219: ldc_w 1221
    //   2222: aload_0
    //   2223: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   2226: getfield 556	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   2229: new 202	java/lang/StringBuilder
    //   2232: dup
    //   2233: invokespecial 931	java/lang/StringBuilder:<init>	()V
    //   2236: aload_0
    //   2237: getfield 181	oicq/wlogin_sdk/request/oicq_request:_g	Loicq/wlogin_sdk/request/request_global;
    //   2240: getfield 507	oicq/wlogin_sdk/request/request_global:_uin	J
    //   2243: invokevirtual 934	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2246: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2249: iconst_0
    //   2250: invokestatic 938	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   2253: aload 34
    //   2255: invokevirtual 1222	java/net/Socket:getOutputStream	()Ljava/io/OutputStream;
    //   2258: astore 53
    //   2260: aload 53
    //   2262: aload 4
    //   2264: iconst_0
    //   2265: aload 4
    //   2267: arraylength
    //   2268: invokevirtual 1159	java/io/OutputStream:write	([BII)V
    //   2271: aload 53
    //   2273: invokevirtual 1160	java/io/OutputStream:flush	()V
    //   2276: aload 34
    //   2278: invokevirtual 1223	java/net/Socket:getInputStream	()Ljava/io/InputStream;
    //   2281: astore 54
    //   2283: aload 54
    //   2285: astore 33
    //   2287: goto -829 -> 1458
    //   2290: astore 50
    //   2292: aload 7
    //   2294: pop
    //   2295: new 854	java/io/StringWriter
    //   2298: dup
    //   2299: invokespecial 855	java/io/StringWriter:<init>	()V
    //   2302: astore 51
    //   2304: new 857	java/io/PrintWriter
    //   2307: dup
    //   2308: aload 51
    //   2310: iconst_1
    //   2311: invokespecial 860	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   2314: astore 52
    //   2316: aload 50
    //   2318: aload 52
    //   2320: invokevirtual 901	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   2323: aload 52
    //   2325: invokevirtual 867	java/io/PrintWriter:flush	()V
    //   2328: aload 51
    //   2330: invokevirtual 868	java/io/StringWriter:flush	()V
    //   2333: ldc_w 870
    //   2336: aload 51
    //   2338: invokevirtual 871	java/io/StringWriter:toString	()Ljava/lang/String;
    //   2341: invokestatic 874	oicq/wlogin_sdk/tools/util:LOGW	(Ljava/lang/String;Ljava/lang/String;)V
    //   2344: sipush -1000
    //   2347: istore 12
    //   2349: iinc 13 1
    //   2352: aload_0
    //   2353: aconst_null
    //   2354: putfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   2357: aload_0
    //   2358: aconst_null
    //   2359: invokevirtual 1183	oicq/wlogin_sdk/request/oicq_request:set_sk	(Ljava/net/Socket;)V
    //   2362: aconst_null
    //   2363: astore 7
    //   2365: goto +390 -> 2755
    //   2368: aload 33
    //   2370: getstatic 51	oicq/wlogin_sdk/request/oicq_request:_recv_ret_buf	[B
    //   2373: iload 40
    //   2375: iconst_1
    //   2376: aload_0
    //   2377: getfield 106	oicq/wlogin_sdk/request/oicq_request:_rsp_head_len	I
    //   2380: iadd
    //   2381: iload 40
    //   2383: isub
    //   2384: invokevirtual 1228	java/io/InputStream:read	([BII)I
    //   2387: istore 39
    //   2389: iload 39
    //   2391: iflt -882 -> 1509
    //   2394: iload 40
    //   2396: iload 39
    //   2398: iadd
    //   2399: istore 40
    //   2401: goto -903 -> 1498
    //   2404: aload_0
    //   2405: getstatic 51	oicq/wlogin_sdk/request/oicq_request:_recv_ret_buf	[B
    //   2408: invokevirtual 1230	oicq/wlogin_sdk/request/oicq_request:get_rsp_length	([B)I
    //   2411: istore 8
    //   2413: iload 8
    //   2415: iconst_1
    //   2416: aload_0
    //   2417: getfield 106	oicq/wlogin_sdk/request/oicq_request:_rsp_head_len	I
    //   2420: iadd
    //   2421: if_icmpgt +40 -> 2461
    //   2424: sipush -1000
    //   2427: istore 12
    //   2429: iinc 13 1
    //   2432: iload 11
    //   2434: ifne +354 -> 2788
    //   2437: aload 34
    //   2439: invokevirtual 1181	java/net/Socket:close	()V
    //   2442: aload_0
    //   2443: aconst_null
    //   2444: putfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   2447: aconst_null
    //   2448: astore 34
    //   2450: aload_0
    //   2451: aconst_null
    //   2452: invokevirtual 1183	oicq/wlogin_sdk/request/oicq_request:set_sk	(Ljava/net/Socket;)V
    //   2455: aconst_null
    //   2456: astore 7
    //   2458: goto +297 -> 2755
    //   2461: iload 8
    //   2463: getstatic 51	oicq/wlogin_sdk/request/oicq_request:_recv_ret_buf	[B
    //   2466: arraylength
    //   2467: if_icmplt +40 -> 2507
    //   2470: sipush -1000
    //   2473: istore 12
    //   2475: iinc 13 1
    //   2478: iload 11
    //   2480: ifne +308 -> 2788
    //   2483: aload 34
    //   2485: invokevirtual 1181	java/net/Socket:close	()V
    //   2488: aload_0
    //   2489: aconst_null
    //   2490: putfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   2493: aconst_null
    //   2494: astore 34
    //   2496: aload_0
    //   2497: aconst_null
    //   2498: invokevirtual 1183	oicq/wlogin_sdk/request/oicq_request:set_sk	(Ljava/net/Socket;)V
    //   2501: aconst_null
    //   2502: astore 7
    //   2504: goto +251 -> 2755
    //   2507: iconst_1
    //   2508: aload_0
    //   2509: getfield 106	oicq/wlogin_sdk/request/oicq_request:_rsp_head_len	I
    //   2512: iadd
    //   2513: istore 41
    //   2515: iload 8
    //   2517: iload 41
    //   2519: isub
    //   2520: istore 42
    //   2522: goto +258 -> 2780
    //   2525: iload 39
    //   2527: iconst_m1
    //   2528: if_icmpne +237 -> 2765
    //   2531: sipush -1000
    //   2534: istore 12
    //   2536: iinc 13 1
    //   2539: iload 11
    //   2541: ifne +247 -> 2788
    //   2544: aload 34
    //   2546: invokevirtual 1181	java/net/Socket:close	()V
    //   2549: aload_0
    //   2550: aconst_null
    //   2551: putfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   2554: aconst_null
    //   2555: astore 34
    //   2557: aload_0
    //   2558: aconst_null
    //   2559: invokevirtual 1183	oicq/wlogin_sdk/request/oicq_request:set_sk	(Ljava/net/Socket;)V
    //   2562: aconst_null
    //   2563: astore 7
    //   2565: goto +190 -> 2755
    //   2568: aload 33
    //   2570: getstatic 51	oicq/wlogin_sdk/request/oicq_request:_recv_ret_buf	[B
    //   2573: iload 41
    //   2575: iload 42
    //   2577: invokevirtual 1228	java/io/InputStream:read	([BII)I
    //   2580: istore 46
    //   2582: iload 46
    //   2584: istore 39
    //   2586: iload 39
    //   2588: iconst_m1
    //   2589: if_icmpeq -64 -> 2525
    //   2592: iload 41
    //   2594: iload 39
    //   2596: iadd
    //   2597: istore 41
    //   2599: iload 42
    //   2601: iload 39
    //   2603: isub
    //   2604: istore 42
    //   2606: goto +174 -> 2780
    //   2609: astore 35
    //   2611: new 854	java/io/StringWriter
    //   2614: dup
    //   2615: invokespecial 855	java/io/StringWriter:<init>	()V
    //   2618: astore 36
    //   2620: new 857	java/io/PrintWriter
    //   2623: dup
    //   2624: aload 36
    //   2626: iconst_1
    //   2627: invokespecial 860	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   2630: astore 37
    //   2632: aload 35
    //   2634: aload 37
    //   2636: invokevirtual 901	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   2639: aload 37
    //   2641: invokevirtual 867	java/io/PrintWriter:flush	()V
    //   2644: aload 36
    //   2646: invokevirtual 868	java/io/StringWriter:flush	()V
    //   2649: ldc_w 870
    //   2652: aload 36
    //   2654: invokevirtual 871	java/io/StringWriter:toString	()Ljava/lang/String;
    //   2657: invokestatic 874	oicq/wlogin_sdk/tools/util:LOGW	(Ljava/lang/String;Ljava/lang/String;)V
    //   2660: sipush -1000
    //   2663: istore 12
    //   2665: iinc 13 1
    //   2668: iload 11
    //   2670: ifne +118 -> 2788
    //   2673: aload 34
    //   2675: invokevirtual 1233	java/net/Socket:isConnected	()Z
    //   2678: ifeq +8 -> 2686
    //   2681: aload 34
    //   2683: invokevirtual 1181	java/net/Socket:close	()V
    //   2686: aload_0
    //   2687: aconst_null
    //   2688: putfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   2691: aload_0
    //   2692: aconst_null
    //   2693: invokevirtual 1183	oicq/wlogin_sdk/request/oicq_request:set_sk	(Ljava/net/Socket;)V
    //   2696: aconst_null
    //   2697: astore 34
    //   2699: goto +89 -> 2788
    //   2702: aload 44
    //   2704: aload_0
    //   2705: getfield 130	oicq/wlogin_sdk/request/oicq_request:_server_ip	Ljava/net/InetSocketAddress;
    //   2708: invokevirtual 1143	java/net/InetSocketAddress:getAddress	()Ljava/net/InetAddress;
    //   2711: invokevirtual 1148	java/net/InetAddress:getHostAddress	()Ljava/lang/String;
    //   2714: putfield 990	oicq/wlogin_sdk/report/report_t3:_ip	Ljava/lang/String;
    //   2717: goto -2484 -> 233
    //   2720: aload 44
    //   2722: iconst_1
    //   2723: putfield 1011	oicq/wlogin_sdk/report/report_t3:_wap	I
    //   2726: goto -2424 -> 302
    //   2729: aload 44
    //   2731: iconst_0
    //   2732: putfield 1011	oicq/wlogin_sdk/report/report_t3:_wap	I
    //   2735: goto -2433 -> 302
    //   2738: astore 38
    //   2740: goto -54 -> 2686
    //   2743: astore 50
    //   2745: goto -450 -> 2295
    //   2748: aload 7
    //   2750: astore 34
    //   2752: goto -533 -> 2219
    //   2755: iload 13
    //   2757: bipush 6
    //   2759: if_icmplt -2374 -> 385
    //   2762: aload 7
    //   2764: pop
    //   2765: iload 13
    //   2767: bipush 6
    //   2769: if_icmplt +26 -> 2795
    //   2772: sipush -1000
    //   2775: istore 43
    //   2777: goto -2676 -> 101
    //   2780: iload 42
    //   2782: ifgt -214 -> 2568
    //   2785: goto -260 -> 2525
    //   2788: aload 34
    //   2790: astore 7
    //   2792: goto -37 -> 2755
    //   2795: iconst_0
    //   2796: istore 43
    //   2798: goto -2697 -> 101
    //
    // Exception table:
    //   from	to	target	type
    //   6	71	1205	finally
    //   74	80	1205	finally
    //   106	128	1205	finally
    //   133	219	1205	finally
    //   219	233	1205	finally
    //   233	286	1205	finally
    //   296	302	1205	finally
    //   302	314	1205	finally
    //   314	382	1205	finally
    //   390	400	1205	finally
    //   400	417	1205	finally
    //   422	508	1205	finally
    //   508	522	1205	finally
    //   522	571	1205	finally
    //   581	587	1205	finally
    //   587	599	1205	finally
    //   599	604	1205	finally
    //   609	684	1205	finally
    //   687	699	1205	finally
    //   707	717	1205	finally
    //   722	730	1205	finally
    //   739	813	1205	finally
    //   813	838	1205	finally
    //   843	891	1205	finally
    //   891	998	1205	finally
    //   1003	1013	1205	finally
    //   1013	1176	1205	finally
    //   1187	1202	1205	finally
    //   1206	1208	1205	finally
    //   1210	1216	1205	finally
    //   1219	1225	1205	finally
    //   1228	1265	1205	finally
    //   1268	1385	1205	finally
    //   1393	1432	1205	finally
    //   1443	1450	1205	finally
    //   1458	1492	1205	finally
    //   1498	1509	1205	finally
    //   1527	1537	1205	finally
    //   1540	1545	1205	finally
    //   1553	1602	1205	finally
    //   1613	1678	1205	finally
    //   1690	1766	1205	finally
    //   1766	1805	1205	finally
    //   1805	1871	1205	finally
    //   1879	1889	1205	finally
    //   1897	1946	1205	finally
    //   1949	2024	1205	finally
    //   2029	2099	1205	finally
    //   2099	2219	1205	finally
    //   2219	2283	1205	finally
    //   2295	2344	1205	finally
    //   2352	2362	1205	finally
    //   2368	2389	1205	finally
    //   2404	2424	1205	finally
    //   2437	2447	1205	finally
    //   2450	2455	1205	finally
    //   2461	2470	1205	finally
    //   2483	2493	1205	finally
    //   2496	2501	1205	finally
    //   2507	2515	1205	finally
    //   2544	2554	1205	finally
    //   2557	2562	1205	finally
    //   2568	2582	1205	finally
    //   2611	2660	1205	finally
    //   2673	2686	1205	finally
    //   2686	2696	1205	finally
    //   2702	2717	1205	finally
    //   2720	2726	1205	finally
    //   2729	2735	1205	finally
    //   687	699	1551	java/lang/Exception
    //   707	717	1551	java/lang/Exception
    //   722	730	1551	java/lang/Exception
    //   739	813	1551	java/lang/Exception
    //   813	838	1551	java/lang/Exception
    //   843	891	1551	java/lang/Exception
    //   891	998	1551	java/lang/Exception
    //   1003	1013	1551	java/lang/Exception
    //   1013	1176	1551	java/lang/Exception
    //   1228	1265	1551	java/lang/Exception
    //   1268	1385	1551	java/lang/Exception
    //   1393	1432	1551	java/lang/Exception
    //   1443	1450	1551	java/lang/Exception
    //   1766	1805	1895	java/lang/Exception
    //   2029	2099	2290	java/lang/Exception
    //   1458	1492	2609	java/lang/Exception
    //   1498	1509	2609	java/lang/Exception
    //   1527	1537	2609	java/lang/Exception
    //   1540	1545	2609	java/lang/Exception
    //   2368	2389	2609	java/lang/Exception
    //   2404	2424	2609	java/lang/Exception
    //   2437	2447	2609	java/lang/Exception
    //   2450	2455	2609	java/lang/Exception
    //   2461	2470	2609	java/lang/Exception
    //   2483	2493	2609	java/lang/Exception
    //   2496	2501	2609	java/lang/Exception
    //   2507	2515	2609	java/lang/Exception
    //   2544	2554	2609	java/lang/Exception
    //   2557	2562	2609	java/lang/Exception
    //   2568	2582	2609	java/lang/Exception
    //   2673	2686	2738	java/lang/Exception
    //   2099	2219	2743	java/lang/Exception
    //   2219	2283	2743	java/lang/Exception
  }

  public int snd_rcv_req_udp()
  {
    return -1000;
  }

  public String toString()
  {
    String str = "";
    for (int i = 0; ; i++)
    {
      if (i >= this._pos)
        return str;
      str = new StringBuilder(String.valueOf(str)).append(Integer.toHexString(0xF & this._buf[i] >> 4)).toString() + Integer.toHexString(0xF & this._buf[i]);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.oicq_request
 * JD-Core Version:    0.6.0
 */