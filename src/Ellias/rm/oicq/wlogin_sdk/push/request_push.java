package oicq.wlogin_sdk.push;

import android.content.Context;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import oicq.wlogin_sdk.request.oicq_request;
import oicq.wlogin_sdk.request.request_global;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

public class request_push extends oicq_request
{
  static String _push_save_host;
  private static int _seq = 0;
  public Context _context;
  private push_service _owner;
  private push_info _pinfo;
  public int _push_comm_head_len = 51;

  static
  {
    _push_save_host = null;
  }

  public request_push(Context paramContext, push_info parampush_info, push_service parampush_service)
  {
    this._cmd = 2067;
    this._sub_cmd = 0;
    this._context = paramContext;
    this._pinfo = parampush_info;
    this._owner = parampush_service;
    this._g = new request_global(paramContext);
  }

  public static void save_push_host(String paramString)
  {
    _push_save_host = paramString;
  }

  public String get_host()
  {
    String[] arrayOfString = { "wtlogin-push.qq.com", "wtlogin-push1.qq.com" };
    return arrayOfString[java.lang.Math.abs(new java.util.Random().nextInt() % arrayOfString.length)];
  }

  public int get_port()
  {
    return 8081;
  }

  byte[] get_push_cld_head(int paramInt1, byte[] paramArrayOfByte, int paramInt2, long paramLong1, long paramLong2, boolean paramBoolean)
  {
    byte[] arrayOfByte;
    int m;
    int n;
    if (paramArrayOfByte == null)
    {
      arrayOfByte = new byte[18];
      util.int16_to_buf(arrayOfByte, 0, paramInt1 + arrayOfByte.length);
      int i = 0 + 2;
      util.int16_to_buf(arrayOfByte, i, paramInt2);
      int j = i + 2;
      util.int64_to_buf32(arrayOfByte, j, paramLong1);
      int k = j + 4;
      util.int64_to_buf32(arrayOfByte, k, paramLong2);
      m = k + 4;
      if (paramArrayOfByte == null)
        break label186;
      util.int16_to_buf(arrayOfByte, m, paramArrayOfByte.length);
      System.arraycopy(paramArrayOfByte, 0, arrayOfByte, m + 2, paramArrayOfByte.length);
      n = 14 + paramArrayOfByte.length;
      label103: if (!paramBoolean)
        break label203;
      util.int8_to_buf(arrayOfByte, n, 1);
    }
    for (int i1 = n + 1; ; i1 = n + 1)
    {
      util.int8_to_buf(arrayOfByte, i1, 0);
      int i2 = i1 + 1;
      util.int8_to_buf(arrayOfByte, i2, 0);
      int i3 = i2 + 1;
      util.int8_to_buf(arrayOfByte, i3, 0);
      (i3 + 1);
      return arrayOfByte;
      arrayOfByte = new byte[1 + (1 + (1 + (1 + (14 + paramArrayOfByte.length))))];
      break;
      label186: util.int16_to_buf(arrayOfByte, m, 0);
      n = m + 2;
      break label103;
      label203: util.int8_to_buf(arrayOfByte, n, 0);
    }
  }

  byte[] get_push_common_head(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[this._push_comm_head_len];
    util.int16_to_buf(arrayOfByte, 0, paramInt1 + arrayOfByte.length);
    int i = 0 + 2;
    util.int16_to_buf(arrayOfByte, i, 1);
    int j = i + 2;
    util.int16_to_buf(arrayOfByte, j, paramInt2);
    int k = j + 2;
    util.int32_to_buf(arrayOfByte, k, paramInt3);
    int m = k + 4;
    util.int8_to_buf(arrayOfByte, m, 0);
    int n = m + 1;
    util.int64_to_buf32(arrayOfByte, n, paramLong1);
    int i1 = n + 4;
    int i3;
    if (paramLong2 == 0L)
    {
      util.int64_to_buf32(arrayOfByte, i1, 1L);
      int i2 = i1 + 4;
      util.int64_to_buf(arrayOfByte, i2, paramLong3);
      i3 = i2 + 8;
      if (paramArrayOfByte.length < 16)
        break label190;
      System.arraycopy(paramArrayOfByte, 0, arrayOfByte, i3, 16);
    }
    while (true)
    {
      int i4 = 7 + (i3 + 16);
      util.int8_to_buf(arrayOfByte, i4, 0);
      (i4 + 1);
      return arrayOfByte;
      util.int64_to_buf32(arrayOfByte, i1, paramLong2);
      break;
      label190: System.arraycopy(paramArrayOfByte, 0, arrayOfByte, i3, paramArrayOfByte.length);
    }
  }

  public byte[] get_request_body_hello(int paramInt, long paramLong)
  {
    byte[] arrayOfByte = new byte[6];
    util.int16_to_buf(arrayOfByte, 0, paramInt);
    int i = 0 + 2;
    util.int64_to_buf32(arrayOfByte, i, paramLong);
    (i + 4);
    return arrayOfByte;
  }

  public byte[] get_request_body_register(int paramInt)
  {
    int i = util.get_os_type(0);
    byte[] arrayOfByte1 = util.get_os_version();
    int j = util.SSO_VERSION;
    int k = util.get_network_type(this._context);
    byte[] arrayOfByte2 = new byte[0];
    byte[] arrayOfByte3 = util.get_apk_id(this._context);
    byte[] arrayOfByte4 = new byte[2 + (1 + (2 + (2 + (2 + (4 + (4 + arrayOfByte1.length)))) + arrayOfByte2.length)) + arrayOfByte3.length];
    util.int16_to_buf(arrayOfByte4, 0, i);
    int m = 0 + 2;
    util.int16_to_buf(arrayOfByte4, m, arrayOfByte1.length);
    System.arraycopy(arrayOfByte1, 0, arrayOfByte4, m + 2, arrayOfByte1.length);
    int n = 4 + arrayOfByte1.length;
    util.int32_to_buf(arrayOfByte4, n, j);
    int i1 = n + 4;
    util.int16_to_buf(arrayOfByte4, i1, k);
    int i2 = i1 + 2;
    util.int16_to_buf(arrayOfByte4, i2, 0);
    int i3 = i2 + 2;
    util.int16_to_buf(arrayOfByte4, i3, arrayOfByte2.length);
    int i4 = i3 + 2;
    System.arraycopy(arrayOfByte2, 0, arrayOfByte4, i4, arrayOfByte2.length);
    int i5 = i4 + arrayOfByte2.length;
    util.int8_to_buf(arrayOfByte4, i5, paramInt);
    int i6 = i5 + 1;
    util.int16_to_buf(arrayOfByte4, i6, arrayOfByte3.length);
    int i7 = i6 + 2;
    System.arraycopy(arrayOfByte3, 0, arrayOfByte4, i7, arrayOfByte3.length);
    (i7 + arrayOfByte3.length);
    return arrayOfByte4;
  }

  public byte[] get_request_body_unregister(int paramInt)
  {
    byte[] arrayOfByte = new byte[2];
    util.int16_to_buf(arrayOfByte, 0, paramInt);
    (0 + 2);
    return arrayOfByte;
  }

  public byte[] get_request_hello(long paramLong1, long paramLong2, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt)
  {
    byte[] arrayOfByte = new byte[8];
    arrayOfByte[0] = 2;
    util.int16_to_buf(arrayOfByte, 1, arrayOfByte.length);
    util.int32_to_buf(arrayOfByte, 3, paramInt);
    arrayOfByte[7] = 3;
    return arrayOfByte;
  }

  public byte[] get_request_hello_body(long paramLong1, long paramLong2, long paramLong3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt, long paramLong4)
  {
    this._sub_cmd = 4;
    byte[] arrayOfByte1 = get_request_body_hello(paramInt, paramLong4);
    int i = arrayOfByte1.length;
    int j = this._sub_cmd;
    int k = _seq;
    _seq = k + 1;
    byte[] arrayOfByte2 = get_st_encrypt(paramArrayOfByte2, get_push_common_head(i, j, k, paramLong2, paramLong3, paramLong1, paramArrayOfByte3), arrayOfByte1);
    byte[] arrayOfByte3 = get_push_cld_head(arrayOfByte2.length, paramArrayOfByte1, this._sub_cmd, paramLong2, paramLong3, false);
    byte[] arrayOfByte4 = new byte[arrayOfByte3.length + arrayOfByte2.length];
    System.arraycopy(arrayOfByte3, 0, arrayOfByte4, 0, arrayOfByte3.length);
    System.arraycopy(arrayOfByte2, 0, arrayOfByte4, arrayOfByte3.length, arrayOfByte2.length);
    return encrypt_body(arrayOfByte4);
  }

  public void get_request_push(int paramInt1, long paramLong1, long paramLong2, long paramLong3, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    uin_app_info localuin_app_info = this._pinfo.get(new uin_appid(paramLong1, paramLong2, paramLong3));
    if (localuin_app_info == null);
    int j;
    byte[] arrayOfByte1;
    int i4;
    int i5;
    do
    {
      int n;
      int i1;
      int i2;
      do
      {
        int k;
        int m;
        do
        {
          do
            while (true)
            {
              return;
              if (paramLong1 == 0L)
                break;
              paramArrayOfByte = get_st_decrypt(localuin_app_info._st_key, paramArrayOfByte, paramInt2, paramInt3);
              if (paramArrayOfByte == null)
                continue;
              paramInt2 = 0;
            }
          while (paramInt3 < this._push_comm_head_len);
          int i = paramInt2 + 6;
          j = util.buf_to_int32(paramArrayOfByte, i);
          k = paramInt2 + this._push_comm_head_len;
          m = paramInt3 - this._push_comm_head_len;
        }
        while (m < 2);
        n = util.buf_to_int16(paramArrayOfByte, k);
        i1 = k + 2;
        i2 = m - 2;
      }
      while ((i2 < n) || (n < 0));
      arrayOfByte1 = new byte[n];
      int i3 = arrayOfByte1.length;
      System.arraycopy(paramArrayOfByte, i1, arrayOfByte1, 0, i3);
      i4 = i1 + arrayOfByte1.length;
      i5 = i2 - arrayOfByte1.length;
    }
    while (i5 < 2);
    int i6 = util.buf_to_int16(paramArrayOfByte, i4);
    int i7 = i4 + 2;
    int i8 = i5 - 2;
    push_msg[] arrayOfpush_msg = new push_msg[i6];
    for (int i9 = 0; ; i9++)
    {
      if (i9 >= i6)
      {
        if (!this._owner.pushCallback(paramLong1, paramLong2, paramLong3, arrayOfpush_msg))
          break;
        this._owner.sendRequest(get_response_push(paramLong1, paramLong2, paramLong3, j, localuin_app_info._st, localuin_app_info._st_key, localuin_app_info._guid, arrayOfpush_msg, arrayOfByte1));
        return;
      }
      arrayOfpush_msg[i9] = new push_msg();
      if (i8 < 8)
        break;
      arrayOfpush_msg[i9]._seq = util.buf_to_int64(paramArrayOfByte, i7);
      int i10 = i7 + 8;
      int i11 = i8 - 8;
      if (i11 < 22)
        break;
      int i12 = i10 + 8;
      int i13 = i11 - 8;
      int i14 = i12 + 4;
      int i15 = i13 - 4;
      int i16 = i14 + 4;
      int i17 = i15 - 4;
      int i18 = i16 + 4;
      int i19 = i17 - 4;
      int i20 = util.buf_to_int16(paramArrayOfByte, i18);
      int i21 = i18 + 2;
      int i22 = i19 - 2;
      if ((i22 < i20) || (i20 < 0))
        break;
      arrayOfpush_msg[i9]._status_title = new byte[i20];
      byte[] arrayOfByte2 = arrayOfpush_msg[i9]._status_title;
      System.arraycopy(paramArrayOfByte, i21, arrayOfByte2, 0, i20);
      int i23 = i21 + i20;
      int i24 = i22 - i20;
      if (i24 < 2)
        break;
      int i25 = util.buf_to_int16(paramArrayOfByte, i23);
      int i26 = i23 + 2;
      int i27 = i24 - 2;
      if ((i27 < i25) || (i25 < 0))
        break;
      arrayOfpush_msg[i9]._title = new byte[i25];
      byte[] arrayOfByte3 = arrayOfpush_msg[i9]._title;
      System.arraycopy(paramArrayOfByte, i26, arrayOfByte3, 0, i25);
      int i28 = i26 + i25;
      int i29 = i27 - i25;
      if (i29 < 2)
        break;
      int i30 = util.buf_to_int16(paramArrayOfByte, i28);
      int i31 = i28 + 2;
      int i32 = i29 - 2;
      if ((i32 < i30) || (i30 < 0))
        break;
      arrayOfpush_msg[i9]._msg = new byte[i30];
      byte[] arrayOfByte4 = arrayOfpush_msg[i9]._msg;
      System.arraycopy(paramArrayOfByte, i31, arrayOfByte4, 0, i30);
      int i33 = i31 + i30;
      int i34 = i32 - i30;
      if (i34 < 2)
        break;
      int i35 = util.buf_to_int16(paramArrayOfByte, i33);
      int i36 = i33 + 2;
      int i37 = i34 - 2;
      if ((i37 < i35) || (i35 < 0))
        break;
      arrayOfpush_msg[i9]._buf = new byte[i35];
      byte[] arrayOfByte5 = arrayOfpush_msg[i9]._buf;
      System.arraycopy(paramArrayOfByte, i36, arrayOfByte5, 0, i35);
      i7 = i36 + i35;
      i8 = i37 - i35;
    }
  }

  public byte[] get_request_register(long paramLong1, long paramLong2, long paramLong3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt, byte[] paramArrayOfByte3, boolean paramBoolean)
  {
    byte[] arrayOfByte = get_request_register_body(paramLong1, paramLong2, paramLong3, paramArrayOfByte1, paramArrayOfByte2, paramInt, paramArrayOfByte3, paramBoolean);
    get_request(this._default_client_version, this._cmd, this._default_client_seq, paramLong1, this._default_ext_retry, this._default_ext_type, 0, this._default_ext_instance, arrayOfByte);
    return get_buf();
  }

  public byte[] get_request_register_body(long paramLong1, long paramLong2, long paramLong3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt, byte[] paramArrayOfByte3, boolean paramBoolean)
  {
    byte[] arrayOfByte5;
    if (paramLong1 != 0L)
    {
      this._sub_cmd = 1;
      byte[] arrayOfByte6 = get_request_body_register(paramInt);
      int i2 = arrayOfByte6.length;
      int i3 = this._sub_cmd;
      int i4 = _seq;
      _seq = i4 + 1;
      byte[] arrayOfByte7 = get_st_encrypt(paramArrayOfByte2, get_push_common_head(i2, i3, i4, paramLong2, paramLong3, paramLong1, paramArrayOfByte3), arrayOfByte6);
      byte[] arrayOfByte8 = get_push_cld_head(arrayOfByte7.length, paramArrayOfByte1, this._sub_cmd, paramLong2, paramLong3, paramBoolean);
      arrayOfByte5 = new byte[arrayOfByte8.length + arrayOfByte7.length];
      int i5 = arrayOfByte8.length;
      System.arraycopy(arrayOfByte8, 0, arrayOfByte5, 0, i5);
      int i6 = arrayOfByte8.length;
      int i7 = arrayOfByte7.length;
      System.arraycopy(arrayOfByte7, 0, arrayOfByte5, i6, i7);
    }
    while (true)
    {
      return encrypt_body(arrayOfByte5);
      this._sub_cmd = 81;
      byte[] arrayOfByte1 = get_request_body_register(paramInt);
      int i = arrayOfByte1.length;
      int j = this._sub_cmd;
      int k = _seq;
      _seq = k + 1;
      byte[] arrayOfByte2 = get_push_common_head(i, j, k, paramLong2, paramLong3, paramLong1, paramArrayOfByte3);
      byte[] arrayOfByte3 = new byte[arrayOfByte1.length + arrayOfByte2.length];
      System.arraycopy(arrayOfByte2, 0, arrayOfByte3, 0, arrayOfByte2.length);
      System.arraycopy(arrayOfByte1, 0, arrayOfByte3, arrayOfByte2.length, arrayOfByte1.length);
      byte[] arrayOfByte4 = get_push_cld_head(arrayOfByte3.length, paramArrayOfByte1, this._sub_cmd, paramLong2, paramLong3, paramBoolean);
      arrayOfByte5 = new byte[arrayOfByte4.length + arrayOfByte3.length];
      int m = arrayOfByte4.length;
      System.arraycopy(arrayOfByte4, 0, arrayOfByte5, 0, m);
      int n = arrayOfByte4.length;
      int i1 = arrayOfByte3.length;
      System.arraycopy(arrayOfByte3, 0, arrayOfByte5, n, i1);
    }
  }

  public byte[] get_request_unregister(long paramLong1, long paramLong2, long paramLong3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt, byte[] paramArrayOfByte3)
  {
    byte[] arrayOfByte = get_request_unregister_body(paramLong1, paramLong2, paramLong3, paramArrayOfByte1, paramArrayOfByte2, paramInt, paramArrayOfByte3);
    get_request(this._default_client_version, this._cmd, this._default_client_seq, paramLong1, this._default_ext_retry, this._default_ext_type, 0, this._default_ext_instance, arrayOfByte);
    return get_buf();
  }

  public byte[] get_request_unregister_body(long paramLong1, long paramLong2, long paramLong3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt, byte[] paramArrayOfByte3)
  {
    byte[] arrayOfByte5;
    if (paramLong1 != 0L)
    {
      this._sub_cmd = 2;
      byte[] arrayOfByte6 = get_request_body_unregister(paramInt);
      int i2 = arrayOfByte6.length;
      int i3 = this._sub_cmd;
      int i4 = _seq;
      _seq = i4 + 1;
      byte[] arrayOfByte7 = get_st_encrypt(paramArrayOfByte2, get_push_common_head(i2, i3, i4, paramLong2, paramLong3, paramLong1, paramArrayOfByte3), arrayOfByte6);
      byte[] arrayOfByte8 = get_push_cld_head(arrayOfByte7.length, paramArrayOfByte1, this._sub_cmd, paramLong2, paramLong3, false);
      arrayOfByte5 = new byte[arrayOfByte8.length + arrayOfByte7.length];
      int i5 = arrayOfByte8.length;
      System.arraycopy(arrayOfByte8, 0, arrayOfByte5, 0, i5);
      int i6 = arrayOfByte8.length;
      int i7 = arrayOfByte7.length;
      System.arraycopy(arrayOfByte7, 0, arrayOfByte5, i6, i7);
    }
    while (true)
    {
      return encrypt_body(arrayOfByte5);
      this._sub_cmd = 82;
      byte[] arrayOfByte1 = get_request_body_unregister(paramInt);
      int i = arrayOfByte1.length;
      int j = this._sub_cmd;
      int k = _seq;
      _seq = k + 1;
      byte[] arrayOfByte2 = get_push_common_head(i, j, k, paramLong2, paramLong3, paramLong1, paramArrayOfByte3);
      byte[] arrayOfByte3 = new byte[arrayOfByte2.length + arrayOfByte1.length];
      System.arraycopy(arrayOfByte2, 0, arrayOfByte3, 0, arrayOfByte2.length);
      System.arraycopy(arrayOfByte1, 0, arrayOfByte3, arrayOfByte2.length, arrayOfByte1.length);
      byte[] arrayOfByte4 = get_push_cld_head(arrayOfByte3.length, paramArrayOfByte1, this._sub_cmd, paramLong2, paramLong3, false);
      arrayOfByte5 = new byte[arrayOfByte4.length + arrayOfByte3.length];
      int m = arrayOfByte4.length;
      System.arraycopy(arrayOfByte4, 0, arrayOfByte5, 0, m);
      int n = arrayOfByte4.length;
      int i1 = arrayOfByte3.length;
      System.arraycopy(arrayOfByte3, 0, arrayOfByte5, n, i1);
    }
  }

  public int get_response(byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte = new byte[16];
    if (paramInt <= 2 + (16 + this._rsp_head_len))
      return -1009;
    long l = 0xFFFFFFFF & util.buf_to_int32(paramArrayOfByte, 9);
    int i = 0xFFFF & util.buf_to_int16(paramArrayOfByte, 5);
    this._rsp_body_len = (-2 + (paramInt - this._rsp_head_len - 16));
    set_buf(paramArrayOfByte, paramInt);
    System.arraycopy(paramArrayOfByte, 1 + this._rsp_head_len, arrayOfByte, 0, 16);
    int j = decrypt_body(this._buf, 16 + (1 + this._rsp_head_len), this._rsp_body_len, arrayOfByte);
    if (j < 0)
      return j;
    return get_response_body(l, i, this._buf, 16 + (1 + this._rsp_head_len), this._rsp_body_len);
  }

  public int get_response_body(long paramLong, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    if (paramInt3 < 16)
      return -1009;
    int i = util.buf_to_int16(paramArrayOfByte, paramInt2);
    int j = paramInt2 + 2;
    int k = i - 2;
    this._sub_cmd = util.buf_to_int16(paramArrayOfByte, j);
    int m = j + 2;
    int n = k - 2;
    long l1 = 0xFFFFFFFF & util.buf_to_int32(paramArrayOfByte, m);
    int i1 = m + 4;
    int i2 = n - 4;
    long l2 = 0xFFFFFFFF & util.buf_to_int32(paramArrayOfByte, i1);
    int i3 = i1 + 4;
    int i4 = i2 - 4;
    int i5 = get_response_ret_code(paramArrayOfByte, i3);
    int i6 = i3 + 1;
    int i7 = i4 - 1;
    int i8 = i6 + 2;
    int i9 = i7 - 2;
    int i10 = util.buf_to_int8(paramArrayOfByte, i8);
    int i11 = i8 + 1;
    int i12 = i9 - 1;
    if (i12 < i10)
      return -1009;
    int i13 = i11 + i10;
    int i14 = i12 - i10;
    set_err_msg(null);
    util.LOGD(getClass().getName(), "type=" + i5);
    int i15 = i5;
    if ((this._sub_cmd == 1) || (this._sub_cmd == 81))
    {
      get_response_register(i15, paramLong, l1, l2, paramArrayOfByte, i13, i14);
      if (i15 == 1)
        i15 = -1100;
    }
    while (true)
    {
      return i15;
      if ((this._sub_cmd == 2) || (this._sub_cmd == 82))
      {
        get_response_unregister(i15, paramLong, l1);
        continue;
      }
      if (this._sub_cmd == 4)
      {
        get_response_hello(i15, paramLong, l1);
        continue;
      }
      if ((this._sub_cmd != 512) && (this._sub_cmd != 592))
        continue;
      get_request_push(i15, paramLong, l1, l2, paramArrayOfByte, i13, i14);
    }
  }

  public byte[] get_response_body_push(push_msg[] paramArrayOfpush_msg, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[2 + (4 + (2 + paramArrayOfByte.length)) + 20 * paramArrayOfpush_msg.length];
    util.int16_to_buf(arrayOfByte, 0, paramArrayOfByte.length);
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0 + 2, paramArrayOfByte.length);
    int i = 2 + paramArrayOfByte.length;
    util.int64_to_buf32(arrayOfByte, i, System.currentTimeMillis() / 1000L);
    int j = i + 4;
    util.int16_to_buf(arrayOfByte, j, paramArrayOfpush_msg.length);
    int k = j + 2;
    for (int m = 0; ; m++)
    {
      if (m >= paramArrayOfpush_msg.length)
        return arrayOfByte;
      util.int64_to_buf(arrayOfByte, k, paramArrayOfpush_msg[m]._seq);
      int n = k + 8;
      util.int32_to_buf(arrayOfByte, n, 0);
      k = 8 + (n + 4);
    }
  }

  public void get_response_hello(int paramInt, long paramLong1, long paramLong2)
  {
  }

  public byte[] get_response_push(long paramLong1, long paramLong2, long paramLong3, int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, push_msg[] paramArrayOfpush_msg, byte[] paramArrayOfByte4)
  {
    byte[] arrayOfByte = get_response_push_body(paramLong1, paramLong2, paramLong3, paramInt, paramArrayOfByte1, paramArrayOfByte2, paramArrayOfByte3, paramArrayOfpush_msg, paramArrayOfByte4);
    get_request(this._default_client_version, this._cmd, this._default_client_seq, paramLong1, this._default_ext_retry, this._default_ext_type, 0, this._default_ext_instance, arrayOfByte);
    return get_buf();
  }

  public byte[] get_response_push_body(long paramLong1, long paramLong2, long paramLong3, int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, push_msg[] paramArrayOfpush_msg, byte[] paramArrayOfByte4)
  {
    byte[] arrayOfByte5;
    if (paramLong1 != 0L)
    {
      this._sub_cmd = 512;
      byte[] arrayOfByte6 = get_response_body_push(paramArrayOfpush_msg, paramArrayOfByte4);
      byte[] arrayOfByte7 = get_st_encrypt(paramArrayOfByte2, get_push_common_head(arrayOfByte6.length, this._sub_cmd, paramInt, paramLong2, paramLong3, paramLong1, paramArrayOfByte3), arrayOfByte6);
      byte[] arrayOfByte8 = get_push_cld_head(arrayOfByte7.length, paramArrayOfByte1, this._sub_cmd, paramLong2, paramLong3, false);
      arrayOfByte5 = new byte[arrayOfByte8.length + arrayOfByte7.length];
      int m = arrayOfByte8.length;
      System.arraycopy(arrayOfByte8, 0, arrayOfByte5, 0, m);
      int n = arrayOfByte8.length;
      int i1 = arrayOfByte7.length;
      System.arraycopy(arrayOfByte7, 0, arrayOfByte5, n, i1);
    }
    while (true)
    {
      return encrypt_body(arrayOfByte5);
      this._sub_cmd = 592;
      byte[] arrayOfByte1 = get_response_body_push(paramArrayOfpush_msg, paramArrayOfByte4);
      byte[] arrayOfByte2 = get_push_common_head(arrayOfByte1.length, this._sub_cmd, paramInt, paramLong2, paramLong3, paramLong1, paramArrayOfByte3);
      byte[] arrayOfByte3 = new byte[arrayOfByte2.length + arrayOfByte1.length];
      System.arraycopy(arrayOfByte2, 0, arrayOfByte3, 0, arrayOfByte2.length);
      System.arraycopy(arrayOfByte1, 0, arrayOfByte3, arrayOfByte2.length, arrayOfByte1.length);
      byte[] arrayOfByte4 = get_push_cld_head(arrayOfByte3.length, paramArrayOfByte1, this._sub_cmd, paramLong2, paramLong3, false);
      arrayOfByte5 = new byte[arrayOfByte4.length + arrayOfByte3.length];
      int i = arrayOfByte4.length;
      System.arraycopy(arrayOfByte4, 0, arrayOfByte5, 0, i);
      int j = arrayOfByte4.length;
      int k = arrayOfByte3.length;
      System.arraycopy(arrayOfByte3, 0, arrayOfByte5, j, k);
    }
  }

  public void get_response_register(int paramInt1, long paramLong1, long paramLong2, long paramLong3, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    if (paramInt1 == 0)
      this._pinfo.set_reg(paramLong1, paramLong2, paramLong3, true);
    int k;
    int m;
    int n;
    do
    {
      do
        return;
      while ((paramInt1 != 1) || (paramInt3 < 8));
      int i = paramInt2 + 2;
      int j = paramInt3 - 2;
      k = util.buf_to_int16(paramArrayOfByte, i);
      m = i + 2;
      n = j - 2;
    }
    while ((n < k) || (k < 0));
    byte[] arrayOfByte = new byte[k];
    System.arraycopy(paramArrayOfByte, m, arrayOfByte, 0, k);
    (m + k);
    (n - k);
    save_push_host(new String(arrayOfByte));
  }

  public void get_response_unregister(int paramInt, long paramLong1, long paramLong2)
  {
  }

  byte[] get_st_decrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte2, paramInt1, arrayOfByte, 0, paramInt2);
    return cryptor.decrypt(arrayOfByte, 0, arrayOfByte.length, paramArrayOfByte1);
  }

  byte[] get_st_encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    byte[] arrayOfByte = new byte[paramArrayOfByte2.length + paramArrayOfByte3.length];
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, 0, paramArrayOfByte2.length);
    System.arraycopy(paramArrayOfByte3, 0, arrayOfByte, paramArrayOfByte2.length, paramArrayOfByte3.length);
    return cryptor.encrypt(arrayOfByte, 0, arrayOfByte.length, paramArrayOfByte1);
  }

  public InetAddress resolve_server_addr(int paramInt)
  {
    String str;
    int i;
    if ((_test_push != 0) && (_test_push_host != null) && (_test_push_host.length() > 0))
      if ((_push_save_host != null) && (_push_save_host.length() > 0))
      {
        str = _push_save_host;
        i = 0;
      }
    Object localObject;
    while (true)
    {
      localObject = null;
      if (i >= 3);
      while (true)
      {
        if (i < 3)
          break label219;
        util.LOGD("resolve_server_addr failed", "tryno:" + paramInt + " ret:" + -1007);
        return null;
        str = _test_push_host;
        break;
        if (paramInt < 1)
        {
          if ((_push_save_host == null) || (_push_save_host.length() <= 0))
            return null;
          str = _push_save_host;
          break;
        }
        if (paramInt < 2)
        {
          str = get_host();
          break;
        }
        str = get_host();
        break;
        try
        {
          InetAddress localInetAddress = InetAddress.getByName(str);
          localObject = localInetAddress;
        }
        catch (Exception localException)
        {
          StringWriter localStringWriter = new StringWriter();
          PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
          localException.printStackTrace(localPrintWriter);
          localPrintWriter.flush();
          localStringWriter.flush();
          util.LOGD("exception", localStringWriter.toString());
          i++;
        }
      }
    }
    label219: util.LOGD("resolve_server_addr OK", "host:" + str + " tryno:" + paramInt);
    _push_save_host = str;
    return localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.push.request_push
 * JD-Core Version:    0.6.0
 */