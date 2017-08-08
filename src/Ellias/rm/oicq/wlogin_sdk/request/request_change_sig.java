package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.tlv_type.tlv_t100;
import oicq.wlogin_sdk.tlv_type.tlv_t108;
import oicq.wlogin_sdk.tlv_type.tlv_t109;
import oicq.wlogin_sdk.tlv_type.tlv_t10a;
import oicq.wlogin_sdk.tlv_type.tlv_t112;
import oicq.wlogin_sdk.tlv_type.tlv_t116;
import oicq.wlogin_sdk.tlv_type.tlv_t124;
import oicq.wlogin_sdk.tlv_type.tlv_t128;
import oicq.wlogin_sdk.tlv_type.tlv_t142;
import oicq.wlogin_sdk.tlv_type.tlv_t143;
import oicq.wlogin_sdk.tlv_type.tlv_t144;
import oicq.wlogin_sdk.tlv_type.tlv_t145;
import oicq.wlogin_sdk.tlv_type.tlv_t147;
import oicq.wlogin_sdk.tools.util;

public class request_change_sig extends oicq_request
{
  public request_change_sig(request_global paramrequest_global)
  {
    this._cmd = 2064;
    this._sub_cmd = 10;
    this._service_cmd = "wtlogin.exchange";
    this._g = paramrequest_global;
  }

  public byte[] get_request_body(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2, byte[] paramArrayOfByte1, int paramInt3, int paramInt4, long[] paramArrayOfLong, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4)
  {
    int i = this._sub_cmd;
    tlv_t100 localtlv_t100 = new tlv_t100();
    tlv_t10a localtlv_t10a = new tlv_t10a();
    tlv_t116 localtlv_t116 = new tlv_t116();
    tlv_t108 localtlv_t108 = new tlv_t108();
    tlv_t109 localtlv_t109 = new tlv_t109();
    tlv_t124 localtlv_t124 = new tlv_t124();
    tlv_t128 localtlv_t128 = new tlv_t128();
    tlv_t143 localtlv_t143 = new tlv_t143();
    tlv_t112 localtlv_t112 = new tlv_t112();
    tlv_t144 localtlv_t144 = new tlv_t144();
    tlv_t145 localtlv_t145 = new tlv_t145();
    tlv_t147 localtlv_t147 = new tlv_t147();
    tlv_t142 localtlv_t142 = new tlv_t142();
    byte[] arrayOfByte1 = localtlv_t100.get_tlv_100(paramLong2, paramLong3, paramInt1, paramInt2);
    byte[] arrayOfByte2 = localtlv_t10a.get_tlv_10a(paramArrayOfByte1);
    byte[] arrayOfByte3 = localtlv_t116.get_tlv_116(paramInt3, paramInt4, paramArrayOfLong);
    byte[] arrayOfByte4 = localtlv_t145.get_tlv145(this._g._IMEI);
    byte[] arrayOfByte5 = localtlv_t142.get_tlv142(this._g._apk_id);
    int j = 0 + 4;
    byte[] arrayOfByte6 = new byte[0];
    byte[] arrayOfByte7 = new byte[0];
    new byte[0];
    new byte[0];
    byte[] arrayOfByte8 = new byte[0];
    byte[] arrayOfByte9 = new byte[0];
    new byte[0];
    byte[] arrayOfByte10 = new byte[0];
    if (paramArrayOfByte2.length > 0)
    {
      arrayOfByte6 = localtlv_t108.get_tlv_108(paramArrayOfByte2);
      j++;
    }
    if (paramArrayOfByte3.length > 0)
      arrayOfByte7 = localtlv_t109.get_tlv_109(paramArrayOfByte3);
    byte[] arrayOfByte11 = localtlv_t124.get_tlv124(util.get_os_type(), util.get_os_version(), this._g._network_type, this._g._sim_operator_name, new byte[0], this._g._apn);
    byte[] arrayOfByte12 = localtlv_t128.get_tlv128(this._g._new_install, this._g._read_guid, this._g._guid_chg, 0, this._g._device, this._g._IMEI);
    try
    {
      byte[] arrayOfByte18 = localtlv_t147.get_tlv147(paramLong2, this._g._apk_v, this._g._apk_sig);
      arrayOfByte13 = arrayOfByte18;
      byte[] arrayOfByte14 = this._g._master_tgt_key;
      arrayOfByte15 = localtlv_t144.get_tlv144(arrayOfByte7, arrayOfByte11, arrayOfByte12, arrayOfByte13, arrayOfByte14);
      if ((paramArrayOfByte4 != null) && (paramArrayOfByte4.length > 0))
      {
        int i9 = j + 1;
        byte[] arrayOfByte17 = localtlv_t143.get_tlv143(paramArrayOfByte4);
        k = i9 + 1;
        i = 11;
        arrayOfByte16 = new byte[arrayOfByte1.length + arrayOfByte2.length + arrayOfByte3.length + arrayOfByte6.length + arrayOfByte15.length + arrayOfByte17.length + arrayOfByte5.length];
        System.arraycopy(arrayOfByte1, 0, arrayOfByte16, 0, arrayOfByte1.length);
        int i10 = 0 + arrayOfByte1.length;
        System.arraycopy(arrayOfByte2, 0, arrayOfByte16, i10, arrayOfByte2.length);
        int i11 = i10 + arrayOfByte2.length;
        System.arraycopy(arrayOfByte3, 0, arrayOfByte16, i11, arrayOfByte3.length);
        int i12 = i11 + arrayOfByte3.length;
        int i13 = arrayOfByte6.length;
        System.arraycopy(arrayOfByte6, 0, arrayOfByte16, i12, i13);
        int i14 = i12 + arrayOfByte6.length;
        System.arraycopy(arrayOfByte15, 0, arrayOfByte16, i14, arrayOfByte15.length);
        int i15 = i14 + arrayOfByte15.length;
        System.arraycopy(arrayOfByte17, 0, arrayOfByte16, i15, arrayOfByte17.length);
        int i16 = i15 + arrayOfByte17.length;
        System.arraycopy(arrayOfByte5, 0, arrayOfByte16, i16, arrayOfByte5.length);
        (i16 + arrayOfByte5.length);
        return encrypt_body(arrayOfByte16, i, k);
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        byte[] arrayOfByte15;
        byte[] arrayOfByte13 = arrayOfByte10;
        continue;
        int k = j + 2;
        if ((this._g._name != null) && (!util.check_uin_account(this._g._name).booleanValue()))
        {
          arrayOfByte9 = localtlv_t112.get_tlv_112(this._g._name.getBytes());
          k++;
        }
        byte[] arrayOfByte16 = new byte[arrayOfByte1.length + arrayOfByte2.length + arrayOfByte3.length + arrayOfByte6.length + arrayOfByte15.length + arrayOfByte8.length + arrayOfByte9.length + arrayOfByte4.length + arrayOfByte5.length];
        System.arraycopy(arrayOfByte1, 0, arrayOfByte16, 0, arrayOfByte1.length);
        int m = 0 + arrayOfByte1.length;
        System.arraycopy(arrayOfByte2, 0, arrayOfByte16, m, arrayOfByte2.length);
        int n = m + arrayOfByte2.length;
        System.arraycopy(arrayOfByte3, 0, arrayOfByte16, n, arrayOfByte3.length);
        int i1 = n + arrayOfByte3.length;
        int i2 = arrayOfByte6.length;
        System.arraycopy(arrayOfByte6, 0, arrayOfByte16, i1, i2);
        int i3 = i1 + arrayOfByte6.length;
        System.arraycopy(arrayOfByte15, 0, arrayOfByte16, i3, arrayOfByte15.length);
        int i4 = i3 + arrayOfByte15.length;
        System.arraycopy(arrayOfByte8, 0, arrayOfByte16, i4, arrayOfByte8.length);
        int i5 = i4 + arrayOfByte8.length;
        int i6 = arrayOfByte9.length;
        System.arraycopy(arrayOfByte9, 0, arrayOfByte16, i5, i6);
        int i7 = i5 + arrayOfByte9.length;
        System.arraycopy(arrayOfByte4, 0, arrayOfByte16, i7, arrayOfByte4.length);
        int i8 = i7 + arrayOfByte4.length;
        System.arraycopy(arrayOfByte5, 0, arrayOfByte16, i8, arrayOfByte5.length);
        (i8 + arrayOfByte5.length);
      }
    }
  }

  public int make_request(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2, byte[] paramArrayOfByte1, int paramInt3, int paramInt4, long[] paramArrayOfLong, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    int i = this._g._app_client_version;
    this._g._uin = paramLong1;
    get_request(this._default_client_version, this._cmd, this._default_client_seq, paramLong1, this._default_ext_retry, this._default_ext_type, i, this._default_ext_instance, get_request_body(paramLong1, paramLong2, paramLong3, i, paramInt2, paramArrayOfByte1, paramInt3, paramInt4, paramArrayOfLong, this._g._ksid, this._g._IMEI, paramArrayOfByte2));
    int j = snd_rcv_req(String.valueOf(this._g._uin));
    if (j != 0)
      return j;
    return get_response(this._rsp_buf, this._rsp_buf.length);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.request_change_sig
 * JD-Core Version:    0.6.0
 */