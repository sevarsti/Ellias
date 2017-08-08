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
import oicq.wlogin_sdk.tools.util;

public class request_check_apkmd5 extends oicq_request
{
  public request_check_apkmd5(request_global paramrequest_global)
  {
    this._cmd = 2064;
    this._sub_cmd = 12;
    this._g = paramrequest_global;
  }

  public byte[] get_request_body(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, int paramInt1, int paramInt2, long[] paramArrayOfLong, long paramLong1, long paramLong2, long paramLong3, int paramInt3, int paramInt4, long paramLong4, byte[] paramArrayOfByte5, byte[] paramArrayOfByte6, byte[] paramArrayOfByte7, long paramLong5, long paramLong6, long paramLong7, byte[] paramArrayOfByte8, byte[] paramArrayOfByte9, byte[] paramArrayOfByte10)
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
    byte[] arrayOfByte1 = localtlv_t10a.get_tlv_10a(paramArrayOfByte1);
    byte[] arrayOfByte2 = localtlv_t100.get_tlv_100(paramLong2, paramLong3, paramInt3, paramInt4);
    byte[] arrayOfByte3 = localtlv_t116.get_tlv_116(paramInt1, paramInt2, paramArrayOfLong);
    int i = 0 + 3;
    byte[] arrayOfByte4 = new byte[0];
    byte[] arrayOfByte5 = new byte[0];
    new byte[0];
    new byte[0];
    new byte[0];
    new byte[0];
    new byte[0];
    new byte[0];
    if (paramArrayOfByte3.length > 0)
      arrayOfByte5 = localtlv_t109.get_tlv_109(paramArrayOfByte3);
    if (paramArrayOfByte4.length > 0)
    {
      arrayOfByte4 = localtlv_t108.get_tlv_108(paramArrayOfByte4);
      i++;
    }
    byte[] arrayOfByte6 = localtlv_t124.get_tlv124(util.get_os_type(), util.get_os_version(), this._g._network_type, this._g._sim_operator_name, new byte[0], this._g._apn);
    byte[] arrayOfByte7 = localtlv_t128.get_tlv128(this._g._new_install, this._g._read_guid, this._g._guid_chg, 0, this._g._device, this._g._IMEI);
    byte[] arrayOfByte8 = localtlv_t147.get_tlv147(paramLong4, paramArrayOfByte5, paramArrayOfByte6);
    byte[] arrayOfByte9 = localtlv_t148.get_tlv148(paramArrayOfByte7, paramLong5, paramLong6, paramLong7, paramArrayOfByte8, paramArrayOfByte9);
    byte[] arrayOfByte10 = localtlv_t144.get_tlv144(arrayOfByte5, arrayOfByte6, arrayOfByte7, arrayOfByte8, arrayOfByte9, paramArrayOfByte2);
    int j = i + 1;
    byte[] arrayOfByte11 = localtlv_t142.get_tlv142(paramArrayOfByte10);
    int k = j + 1;
    return encrypt_body(new byte[arrayOfByte1.length + arrayOfByte10.length + arrayOfByte2.length + arrayOfByte4.length + arrayOfByte3.length + arrayOfByte11.length], this._sub_cmd, k);
  }

  public int make_request(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, int paramInt1, int paramInt2, long[] paramArrayOfLong, long paramLong1, long paramLong2, long paramLong3, int paramInt3, int paramInt4, long paramLong4, byte[] paramArrayOfByte5, byte[] paramArrayOfByte6, byte[] paramArrayOfByte7, long paramLong5, long paramLong6, long paramLong7, byte[] paramArrayOfByte8, byte[] paramArrayOfByte9, byte[] paramArrayOfByte10)
  {
    int i = this._g._app_client_version;
    this._g._uin = paramLong1;
    get_request(this._default_client_version, this._cmd, this._default_client_seq, paramLong1, this._default_ext_retry, this._default_ext_type, i, this._default_ext_instance, get_request_body(paramArrayOfByte1, paramArrayOfByte2, paramArrayOfByte3, paramArrayOfByte4, paramInt1, paramInt2, paramArrayOfLong, paramLong1, paramLong2, paramLong3, paramInt3, paramInt4, paramLong4, paramArrayOfByte5, paramArrayOfByte6, paramArrayOfByte7, paramLong5, paramLong6, paramLong7, paramArrayOfByte8, paramArrayOfByte9, paramArrayOfByte10));
    int j = snd_rcv_req();
    if (j != 0)
      return j;
    return get_response(this._rsp_buf, this._rsp_buf.length);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.request_check_apkmd5
 * JD-Core Version:    0.6.0
 */