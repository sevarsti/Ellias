package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.tlv_type.tlv_t100;
import oicq.wlogin_sdk.tlv_type.tlv_t108;
import oicq.wlogin_sdk.tlv_type.tlv_t109;
import oicq.wlogin_sdk.tlv_type.tlv_t10a;
import oicq.wlogin_sdk.tlv_type.tlv_t116;
import oicq.wlogin_sdk.tlv_type.tlv_t124;
import oicq.wlogin_sdk.tlv_type.tlv_t128;
import oicq.wlogin_sdk.tlv_type.tlv_t142;
import oicq.wlogin_sdk.tlv_type.tlv_t144;
import oicq.wlogin_sdk.tlv_type.tlv_t147;
import oicq.wlogin_sdk.tlv_type.tlv_t148;
import oicq.wlogin_sdk.tlv_type.tlv_t151;
import oicq.wlogin_sdk.tlv_type.tlv_t152;
import oicq.wlogin_sdk.tlv_type.tlv_t153;
import oicq.wlogin_sdk.tools.util;

public class request_app_signature extends oicq_request
{
  public request_app_signature(request_global paramrequest_global)
  {
    this._cmd = 2064;
    this._sub_cmd = 12;
    this._service_cmd = "wtlogin.fastlogin";
    this._g = paramrequest_global;
  }

  public byte[] get_request_body(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2, byte[] paramArrayOfByte1, int paramInt3, int paramInt4, long[] paramArrayOfLong, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, long paramLong4, long paramLong5, long paramLong6, byte[] paramArrayOfByte5, byte[] paramArrayOfByte6, byte[] paramArrayOfByte7)
  {
    tlv_t10a localtlv_t10a = new tlv_t10a();
    tlv_t144 localtlv_t144 = new tlv_t144();
    tlv_t100 localtlv_t100 = new tlv_t100();
    tlv_t108 localtlv_t108 = new tlv_t108();
    tlv_t116 localtlv_t116 = new tlv_t116();
    tlv_t142 localtlv_t142 = new tlv_t142();
    tlv_t109 localtlv_t109 = new tlv_t109();
    tlv_t124 localtlv_t124 = new tlv_t124();
    tlv_t128 localtlv_t128 = new tlv_t128();
    tlv_t147 localtlv_t147 = new tlv_t147();
    tlv_t148 localtlv_t148 = new tlv_t148();
    tlv_t151 localtlv_t151 = new tlv_t151();
    tlv_t153 localtlv_t153 = new tlv_t153();
    byte[] arrayOfByte1 = localtlv_t10a.get_tlv_10a(paramArrayOfByte1);
    byte[] arrayOfByte2 = localtlv_t100.get_tlv_100(paramLong2, paramLong3, paramInt1, paramInt2);
    byte[] arrayOfByte3 = localtlv_t116.get_tlv_116(paramInt3, paramInt4, paramArrayOfLong);
    byte[] arrayOfByte4 = localtlv_t142.get_tlv142(this._g._apk_id);
    int i = 0 + 4;
    byte[] arrayOfByte5 = new byte[0];
    byte[] arrayOfByte6 = new byte[0];
    new byte[0];
    new byte[0];
    new byte[0];
    new byte[0];
    byte[] arrayOfByte7 = new byte[0];
    new byte[0];
    new byte[0];
    if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length > 0))
    {
      arrayOfByte5 = localtlv_t108.get_tlv_108(paramArrayOfByte2);
      i++;
    }
    if ((paramArrayOfByte3 != null) && (paramArrayOfByte3.length > 0))
      arrayOfByte6 = localtlv_t109.get_tlv_109(paramArrayOfByte3);
    if ((paramArrayOfByte7 != null) && (paramArrayOfByte7.length > 0))
      arrayOfByte7 = localtlv_t151.get_tlv151(paramArrayOfByte7);
    byte[] arrayOfByte8 = localtlv_t124.get_tlv124(util.get_os_type(), util.get_os_version(), this._g._network_type, this._g._sim_operator_name, new byte[0], this._g._apn);
    byte[] arrayOfByte9 = localtlv_t128.get_tlv128(this._g._new_install, this._g._read_guid, this._g._guid_chg, 0, this._g._device, this._g._IMEI);
    byte[] arrayOfByte10 = localtlv_t147.get_tlv147(paramLong2, this._g._apk_v, this._g._apk_sig);
    byte[] arrayOfByte11 = localtlv_t148.get_tlv148(paramArrayOfByte4, paramLong4, paramLong5, paramLong6, paramArrayOfByte5, paramArrayOfByte6);
    byte[] arrayOfByte12 = localtlv_t153.get_tlv153(this._g._isroot);
    byte[] arrayOfByte13 = this._g._master_tgt_key;
    byte[] arrayOfByte14 = localtlv_t144.get_tlv144(arrayOfByte6, arrayOfByte8, arrayOfByte9, arrayOfByte10, arrayOfByte11, arrayOfByte7, arrayOfByte12, arrayOfByte13);
    int j = i + 1;
    byte[] arrayOfByte15 = new byte[arrayOfByte1.length + arrayOfByte2.length + arrayOfByte5.length + arrayOfByte3.length + arrayOfByte4.length + arrayOfByte14.length];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte15, 0, arrayOfByte1.length);
    int k = 0 + arrayOfByte1.length;
    System.arraycopy(arrayOfByte2, 0, arrayOfByte15, k, arrayOfByte2.length);
    int m = k + arrayOfByte2.length;
    int n = arrayOfByte5.length;
    System.arraycopy(arrayOfByte5, 0, arrayOfByte15, m, n);
    int i1 = m + arrayOfByte5.length;
    System.arraycopy(arrayOfByte3, 0, arrayOfByte15, i1, arrayOfByte3.length);
    int i2 = i1 + arrayOfByte3.length;
    System.arraycopy(arrayOfByte4, 0, arrayOfByte15, i2, arrayOfByte4.length);
    int i3 = i2 + arrayOfByte4.length;
    System.arraycopy(arrayOfByte14, 0, arrayOfByte15, i3, arrayOfByte14.length);
    (i3 + arrayOfByte14.length);
    return encrypt_body(arrayOfByte15, this._sub_cmd, j);
  }

  public int get_response_body(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    tlv_t152 localtlv_t152 = new tlv_t152();
    int i = get_response_ret_code(paramArrayOfByte, paramInt1 + 2);
    set_err_msg(null);
    util.LOGD(getClass().getName(), "type=" + i);
    int j = paramInt1 + 5;
    switch (i)
    {
    default:
      get_err_msg(paramArrayOfByte, j, -1 + (this._pos - j));
      return i;
    case 0:
    }
    if (localtlv_t152.get_tlv(paramArrayOfByte, j, this._pos - j) >= 0);
    for (this._g._t152 = localtlv_t152; ; this._g._t152 = new tlv_t152())
      return i;
  }

  public int make_request(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2, byte[] paramArrayOfByte1, int paramInt3, int paramInt4, long[] paramArrayOfLong, byte[] paramArrayOfByte2, long paramLong4, long paramLong5, long paramLong6, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5)
  {
    int i = this._g._app_client_version;
    this._g._uin = paramLong1;
    byte[] arrayOfByte = get_request_body(paramLong1, paramLong2, paramLong3, i, paramInt2, paramArrayOfByte1, paramInt3, paramInt4, paramArrayOfLong, this._g._ksid, this._g._IMEI, paramArrayOfByte2, paramLong4, paramLong5, paramLong6, paramArrayOfByte3, paramArrayOfByte4, paramArrayOfByte5);
    get_request(this._default_client_version, this._cmd, this._default_client_seq, paramLong1, this._default_ext_retry, this._default_ext_type, i, this._default_ext_instance, arrayOfByte);
    int j = snd_rcv_req(String.valueOf(this._g._uin));
    if (j != 0)
      return j;
    return get_response(this._rsp_buf, this._rsp_buf.length);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.request_app_signature
 * JD-Core Version:    0.6.0
 */