package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.tlv_type.tlv_t1;
import oicq.wlogin_sdk.tlv_type.tlv_t100;
import oicq.wlogin_sdk.tlv_type.tlv_t104;
import oicq.wlogin_sdk.tlv_type.tlv_t106;
import oicq.wlogin_sdk.tlv_type.tlv_t107;
import oicq.wlogin_sdk.tlv_type.tlv_t108;
import oicq.wlogin_sdk.tlv_type.tlv_t109;
import oicq.wlogin_sdk.tlv_type.tlv_t112;
import oicq.wlogin_sdk.tlv_type.tlv_t116;
import oicq.wlogin_sdk.tlv_type.tlv_t124;
import oicq.wlogin_sdk.tlv_type.tlv_t128;
import oicq.wlogin_sdk.tlv_type.tlv_t142;
import oicq.wlogin_sdk.tlv_type.tlv_t144;
import oicq.wlogin_sdk.tlv_type.tlv_t145;
import oicq.wlogin_sdk.tlv_type.tlv_t147;
import oicq.wlogin_sdk.tlv_type.tlv_t166;
import oicq.wlogin_sdk.tlv_type.tlv_t18;
import oicq.wlogin_sdk.tools.util;

public class request_TGTGT extends oicq_request
{
  int _response_ret_pos = 0;

  public request_TGTGT(request_global paramrequest_global)
  {
    this._cmd = 2064;
    this._sub_cmd = 9;
    this._service_cmd = "wtlogin.login";
    this._g = paramrequest_global;
  }

  public byte[] get_request_body(long paramLong1, int paramInt1, long paramLong2, int paramInt2, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt3, int paramInt4, long[] paramArrayOfLong, int paramInt5, long paramLong3, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5, byte[] paramArrayOfByte6)
  {
    tlv_t18 localtlv_t18 = new tlv_t18();
    tlv_t1 localtlv_t1 = new tlv_t1();
    tlv_t106 localtlv_t106 = new tlv_t106();
    tlv_t116 localtlv_t116 = new tlv_t116();
    tlv_t100 localtlv_t100 = new tlv_t100();
    tlv_t107 localtlv_t107 = new tlv_t107();
    tlv_t108 localtlv_t108 = new tlv_t108();
    tlv_t109 localtlv_t109 = new tlv_t109();
    tlv_t104 localtlv_t104 = new tlv_t104();
    tlv_t124 localtlv_t124 = new tlv_t124();
    tlv_t128 localtlv_t128 = new tlv_t128();
    tlv_t142 localtlv_t142 = new tlv_t142();
    tlv_t112 localtlv_t112 = new tlv_t112();
    tlv_t144 localtlv_t144 = new tlv_t144();
    tlv_t145 localtlv_t145 = new tlv_t145();
    tlv_t147 localtlv_t147 = new tlv_t147();
    tlv_t166 localtlv_t166 = new tlv_t166();
    byte[] arrayOfByte1 = localtlv_t18.get_tlv_18(paramLong1, paramInt1, paramLong2, paramInt2);
    byte[] arrayOfByte2 = localtlv_t1.get_tlv_1(paramLong2, paramArrayOfByte1);
    this._g._encrypt_a1 = encrypt_a1(paramArrayOfByte2);
    localtlv_t106.set_data(paramArrayOfByte2, paramArrayOfByte2.length);
    byte[] arrayOfByte3 = localtlv_t106.get_buf();
    util.LOGD("req2 a1:", util.buf_to_string(arrayOfByte3));
    byte[] arrayOfByte4 = localtlv_t100.get_tlv_100(paramLong1, paramLong3, paramInt1, paramInt5);
    byte[] arrayOfByte5 = localtlv_t107.get_tlv_107(paramInt7, paramInt8, paramInt9, paramInt10);
    byte[] arrayOfByte6 = localtlv_t116.get_tlv_116(paramInt3, paramInt4, paramArrayOfLong);
    byte[] arrayOfByte7 = localtlv_t145.get_tlv145(this._g._IMEI);
    int i = 0 + 7;
    byte[] arrayOfByte8 = new byte[0];
    byte[] arrayOfByte9 = new byte[0];
    byte[] arrayOfByte10 = new byte[0];
    new byte[0];
    new byte[0];
    new byte[0];
    byte[] arrayOfByte11 = new byte[0];
    new byte[0];
    byte[] arrayOfByte12 = new byte[0];
    byte[] arrayOfByte13 = new byte[0];
    if (paramArrayOfByte3.length > 0)
    {
      arrayOfByte8 = localtlv_t108.get_tlv_108(paramArrayOfByte3);
      i++;
    }
    if ((paramInt3 & 0x80) != 0)
    {
      arrayOfByte13 = localtlv_t166.get_tlv166(this._g._img_type);
      i++;
    }
    if (paramArrayOfByte4.length > 0)
      arrayOfByte9 = localtlv_t109.get_tlv_109(paramArrayOfByte4);
    byte[] arrayOfByte14 = localtlv_t124.get_tlv124(util.get_os_type(), util.get_os_version(), this._g._network_type, this._g._sim_operator_name, new byte[0], this._g._apn);
    byte[] arrayOfByte15 = localtlv_t128.get_tlv128(this._g._new_install, this._g._read_guid, this._g._guid_chg, 0, this._g._device, this._g._IMEI);
    try
    {
      byte[] arrayOfByte21 = localtlv_t147.get_tlv147(paramLong1, this._g._apk_v, this._g._apk_sig);
      arrayOfByte16 = arrayOfByte21;
      byte[] arrayOfByte17 = this._g._key_tgtgt;
      byte[] arrayOfByte18 = localtlv_t144.get_tlv144(arrayOfByte9, arrayOfByte14, arrayOfByte15, arrayOfByte16, arrayOfByte17);
      int j = i + 1;
      byte[] arrayOfByte19 = localtlv_t142.get_tlv142(paramArrayOfByte6);
      int k = j + 1;
      if ((this._g._name != null) && (!util.check_uin_account(this._g._name).booleanValue()))
      {
        arrayOfByte11 = localtlv_t112.get_tlv_112(this._g._name.getBytes());
        k++;
      }
      if (paramArrayOfByte5.length > 0)
      {
        arrayOfByte10 = localtlv_t104.get_tlv_104(paramArrayOfByte5);
        k++;
      }
      byte[] arrayOfByte20 = new byte[arrayOfByte1.length + arrayOfByte2.length + arrayOfByte3.length + arrayOfByte6.length + arrayOfByte4.length + arrayOfByte5.length + arrayOfByte8.length + arrayOfByte18.length + arrayOfByte10.length + arrayOfByte19.length + arrayOfByte11.length + arrayOfByte7.length + arrayOfByte13.length];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte20, 0, arrayOfByte1.length);
      int m = 0 + arrayOfByte1.length;
      System.arraycopy(arrayOfByte2, 0, arrayOfByte20, m, arrayOfByte2.length);
      int n = m + arrayOfByte2.length;
      System.arraycopy(arrayOfByte3, 0, arrayOfByte20, n, arrayOfByte3.length);
      int i1 = n + arrayOfByte3.length;
      System.arraycopy(arrayOfByte6, 0, arrayOfByte20, i1, arrayOfByte6.length);
      int i2 = i1 + arrayOfByte6.length;
      System.arraycopy(arrayOfByte4, 0, arrayOfByte20, i2, arrayOfByte4.length);
      int i3 = i2 + arrayOfByte4.length;
      System.arraycopy(arrayOfByte5, 0, arrayOfByte20, i3, arrayOfByte5.length);
      int i4 = i3 + arrayOfByte5.length;
      int i5 = arrayOfByte8.length;
      System.arraycopy(arrayOfByte8, 0, arrayOfByte20, i4, i5);
      int i6 = i4 + arrayOfByte8.length;
      System.arraycopy(arrayOfByte18, 0, arrayOfByte20, i6, arrayOfByte18.length);
      int i7 = i6 + arrayOfByte18.length;
      System.arraycopy(arrayOfByte19, 0, arrayOfByte20, i7, arrayOfByte19.length);
      int i8 = i7 + arrayOfByte19.length;
      int i9 = arrayOfByte11.length;
      System.arraycopy(arrayOfByte11, 0, arrayOfByte20, i8, i9);
      int i10 = i8 + arrayOfByte11.length;
      int i11 = arrayOfByte10.length;
      System.arraycopy(arrayOfByte10, 0, arrayOfByte20, i10, i11);
      int i12 = i10 + arrayOfByte10.length;
      System.arraycopy(arrayOfByte7, 0, arrayOfByte20, i12, arrayOfByte7.length);
      int i13 = i12 + arrayOfByte7.length;
      int i14 = arrayOfByte13.length;
      System.arraycopy(arrayOfByte13, 0, arrayOfByte20, i13, i14);
      (i13 + arrayOfByte13.length);
      return encrypt_body(arrayOfByte20, this._sub_cmd, k);
    }
    catch (Exception localException)
    {
      while (true)
        byte[] arrayOfByte16 = arrayOfByte12;
    }
  }

  public byte[] get_request_body(long paramLong1, int paramInt1, long paramLong2, int paramInt2, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt3, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, int paramInt4, int paramInt5, long[] paramArrayOfLong, int paramInt6, long paramLong3, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, byte[] paramArrayOfByte5, byte[] paramArrayOfByte6, byte[] paramArrayOfByte7, byte[] paramArrayOfByte8)
  {
    tlv_t18 localtlv_t18 = new tlv_t18();
    tlv_t1 localtlv_t1 = new tlv_t1();
    tlv_t106 localtlv_t106 = new tlv_t106();
    tlv_t116 localtlv_t116 = new tlv_t116();
    tlv_t100 localtlv_t100 = new tlv_t100();
    tlv_t107 localtlv_t107 = new tlv_t107();
    tlv_t108 localtlv_t108 = new tlv_t108();
    tlv_t109 localtlv_t109 = new tlv_t109();
    tlv_t104 localtlv_t104 = new tlv_t104();
    tlv_t124 localtlv_t124 = new tlv_t124();
    tlv_t128 localtlv_t128 = new tlv_t128();
    tlv_t142 localtlv_t142 = new tlv_t142();
    tlv_t112 localtlv_t112 = new tlv_t112();
    tlv_t144 localtlv_t144 = new tlv_t144();
    tlv_t145 localtlv_t145 = new tlv_t145();
    tlv_t147 localtlv_t147 = new tlv_t147();
    tlv_t166 localtlv_t166 = new tlv_t166();
    byte[] arrayOfByte1 = localtlv_t18.get_tlv_18(paramLong1, paramInt1, paramLong2, paramInt2);
    byte[] arrayOfByte2 = localtlv_t1.get_tlv_1(paramLong2, paramArrayOfByte1);
    byte[] arrayOfByte3 = localtlv_t106.get_tlv_106(paramLong1, paramInt1, paramLong2, paramArrayOfByte2, paramArrayOfByte1, 1, paramArrayOfByte3, paramArrayOfByte4, this._g._read_guid, this._g._IMEI);
    util.LOGD("req1 a1:", util.buf_to_string(arrayOfByte3));
    this._g._encrypt_a1 = encrypt_a1(localtlv_t106.get_data());
    byte[] arrayOfByte4 = localtlv_t100.get_tlv_100(paramLong1, paramLong3, paramInt1, paramInt6);
    byte[] arrayOfByte5 = localtlv_t107.get_tlv_107(paramInt8, paramInt9, paramInt10, paramInt11);
    byte[] arrayOfByte6 = localtlv_t116.get_tlv_116(paramInt4, paramInt5, paramArrayOfLong);
    byte[] arrayOfByte7 = localtlv_t145.get_tlv145(this._g._IMEI);
    int i = 0 + 7;
    byte[] arrayOfByte8 = new byte[0];
    byte[] arrayOfByte9 = new byte[0];
    byte[] arrayOfByte10 = new byte[0];
    new byte[0];
    new byte[0];
    new byte[0];
    byte[] arrayOfByte11 = new byte[0];
    new byte[0];
    byte[] arrayOfByte12 = new byte[0];
    byte[] arrayOfByte13 = new byte[0];
    if (paramArrayOfByte5.length > 0)
    {
      arrayOfByte8 = localtlv_t108.get_tlv_108(paramArrayOfByte5);
      i++;
    }
    if ((paramInt4 & 0x80) != 0)
    {
      arrayOfByte13 = localtlv_t166.get_tlv166(this._g._img_type);
      i++;
    }
    if (paramArrayOfByte6.length > 0)
      arrayOfByte9 = localtlv_t109.get_tlv_109(paramArrayOfByte6);
    byte[] arrayOfByte14 = localtlv_t124.get_tlv124(util.get_os_type(), util.get_os_version(), this._g._network_type, this._g._sim_operator_name, new byte[0], this._g._apn);
    byte[] arrayOfByte15 = localtlv_t128.get_tlv128(this._g._new_install, this._g._read_guid, this._g._guid_chg, 0, this._g._device, this._g._IMEI);
    try
    {
      byte[] arrayOfByte21 = localtlv_t147.get_tlv147(paramLong1, this._g._apk_v, this._g._apk_sig);
      arrayOfByte16 = arrayOfByte21;
      byte[] arrayOfByte17 = this._g._key_tgtgt;
      byte[] arrayOfByte18 = localtlv_t144.get_tlv144(arrayOfByte9, arrayOfByte14, arrayOfByte15, arrayOfByte16, arrayOfByte17);
      int j = i + 1;
      byte[] arrayOfByte19 = localtlv_t142.get_tlv142(paramArrayOfByte8);
      int k = j + 1;
      if ((this._g._name != null) && (!util.check_uin_account(this._g._name).booleanValue()))
      {
        arrayOfByte11 = localtlv_t112.get_tlv_112(this._g._name.getBytes());
        k++;
      }
      if (paramArrayOfByte7.length > 0)
      {
        arrayOfByte10 = localtlv_t104.get_tlv_104(paramArrayOfByte7);
        k++;
      }
      byte[] arrayOfByte20 = new byte[arrayOfByte1.length + arrayOfByte2.length + arrayOfByte3.length + arrayOfByte6.length + arrayOfByte4.length + arrayOfByte5.length + arrayOfByte8.length + arrayOfByte18.length + arrayOfByte10.length + arrayOfByte19.length + arrayOfByte11.length + arrayOfByte7.length + arrayOfByte13.length];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte20, 0, arrayOfByte1.length);
      int m = 0 + arrayOfByte1.length;
      System.arraycopy(arrayOfByte2, 0, arrayOfByte20, m, arrayOfByte2.length);
      int n = m + arrayOfByte2.length;
      System.arraycopy(arrayOfByte3, 0, arrayOfByte20, n, arrayOfByte3.length);
      int i1 = n + arrayOfByte3.length;
      System.arraycopy(arrayOfByte6, 0, arrayOfByte20, i1, arrayOfByte6.length);
      int i2 = i1 + arrayOfByte6.length;
      System.arraycopy(arrayOfByte4, 0, arrayOfByte20, i2, arrayOfByte4.length);
      int i3 = i2 + arrayOfByte4.length;
      System.arraycopy(arrayOfByte5, 0, arrayOfByte20, i3, arrayOfByte5.length);
      int i4 = i3 + arrayOfByte5.length;
      int i5 = arrayOfByte8.length;
      System.arraycopy(arrayOfByte8, 0, arrayOfByte20, i4, i5);
      int i6 = i4 + arrayOfByte8.length;
      System.arraycopy(arrayOfByte18, 0, arrayOfByte20, i6, arrayOfByte18.length);
      int i7 = i6 + arrayOfByte18.length;
      System.arraycopy(arrayOfByte19, 0, arrayOfByte20, i7, arrayOfByte19.length);
      int i8 = i7 + arrayOfByte19.length;
      int i9 = arrayOfByte11.length;
      System.arraycopy(arrayOfByte11, 0, arrayOfByte20, i8, i9);
      int i10 = i8 + arrayOfByte11.length;
      int i11 = arrayOfByte10.length;
      System.arraycopy(arrayOfByte10, 0, arrayOfByte20, i10, i11);
      int i12 = i10 + arrayOfByte10.length;
      System.arraycopy(arrayOfByte7, 0, arrayOfByte20, i12, arrayOfByte7.length);
      int i13 = i12 + arrayOfByte7.length;
      int i14 = arrayOfByte13.length;
      System.arraycopy(arrayOfByte13, 0, arrayOfByte20, i13, i14);
      (i13 + arrayOfByte13.length);
      util.LOGD(getClass().getName(), util.buf_to_string(arrayOfByte20));
      util.LOGD("uin=", new Long(paramLong2).toString());
      return encrypt_body(arrayOfByte20, this._sub_cmd, k);
    }
    catch (Exception localException)
    {
      while (true)
        byte[] arrayOfByte16 = arrayOfByte12;
    }
  }

  public int make_request(long paramLong1, int paramInt1, long paramLong2, int paramInt2, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt3, int paramInt4, long[] paramArrayOfLong, int paramInt5, long paramLong3, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, byte[] paramArrayOfByte3)
  {
    int i = this._g._app_client_version;
    this._g._uin = paramLong2;
    byte[] arrayOfByte = decrypt_a1(paramArrayOfByte2);
    if (arrayOfByte == null)
      return -1014;
    get_request(this._default_client_version, this._cmd, this._default_client_seq, paramLong2, this._default_ext_retry, this._default_ext_type, i, this._default_ext_instance, get_request_body(paramLong1, i, paramLong2, paramInt2, paramArrayOfByte1, arrayOfByte, paramInt3, paramInt4, paramArrayOfLong, paramInt5, paramLong3, i, paramInt7, paramInt8, paramInt9, paramInt10, paramArrayOfByte3, this._g._IMEI, this._g._t104.get_data(), this._g._apk_id));
    int j = snd_rcv_req(String.valueOf(this._g._uin));
    if (j != 0)
      return j;
    return get_response(this._rsp_buf, this._rsp_buf.length);
  }

  public int make_request(long paramLong1, int paramInt1, long paramLong2, int paramInt2, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt3, byte[] paramArrayOfByte3, int paramInt4, int paramInt5, long[] paramArrayOfLong, int paramInt6, long paramLong3, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, byte[] paramArrayOfByte4)
  {
    int i = this._g._app_client_version;
    this._g._uin = paramLong2;
    util.LOGD("IMEI", util.buf_to_string(this._g._IMEI));
    byte[] arrayOfByte = get_request_body(paramLong1, i, paramLong2, paramInt2, paramArrayOfByte1, paramArrayOfByte2, paramInt3, paramArrayOfByte3, this._g._key_tgtgt, paramInt4, paramInt5, paramArrayOfLong, paramInt6, paramLong3, i, paramInt8, paramInt9, paramInt10, paramInt11, paramArrayOfByte4, this._g._IMEI, this._g._t104.get_data(), this._g._apk_id);
    get_request(this._default_client_version, this._cmd, this._default_client_seq, paramLong2, this._default_ext_retry, this._default_ext_type, i, this._default_ext_instance, arrayOfByte);
    int j = snd_rcv_req(String.valueOf(this._g._uin));
    if (j != 0)
      return j;
    return get_response(this._rsp_buf, this._rsp_buf.length);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.request_TGTGT
 * JD-Core Version:    0.6.0
 */