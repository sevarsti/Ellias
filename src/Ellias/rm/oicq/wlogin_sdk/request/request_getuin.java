package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.tlv_type.tlv_t100;
import oicq.wlogin_sdk.tlv_type.tlv_t107;
import oicq.wlogin_sdk.tlv_type.tlv_t108;
import oicq.wlogin_sdk.tlv_type.tlv_t109;
import oicq.wlogin_sdk.tlv_type.tlv_t112;
import oicq.wlogin_sdk.tlv_type.tlv_t116;
import oicq.wlogin_sdk.tlv_type.tlv_t124;
import oicq.wlogin_sdk.tlv_type.tlv_t128;
import oicq.wlogin_sdk.tools.util;

public class request_getuin extends oicq_request
{
  public request_getuin(request_global paramrequest_global)
  {
    this._cmd = 2064;
    this._sub_cmd = 4;
    this._service_cmd = "wtlogin.name2uin";
    this._g = paramrequest_global;
  }

  public byte[] get_request_body(long paramLong1, long paramLong2, int paramInt1, int paramInt2, byte[] paramArrayOfByte1, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long[] paramArrayOfLong, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    tlv_t100 localtlv_t100 = new tlv_t100();
    tlv_t112 localtlv_t112 = new tlv_t112();
    tlv_t107 localtlv_t107 = new tlv_t107();
    tlv_t108 localtlv_t108 = new tlv_t108();
    tlv_t109 localtlv_t109 = new tlv_t109();
    tlv_t124 localtlv_t124 = new tlv_t124();
    tlv_t128 localtlv_t128 = new tlv_t128();
    tlv_t116 localtlv_t116 = new tlv_t116();
    new byte[0];
    new byte[0];
    byte[] arrayOfByte1 = localtlv_t100.get_tlv_100(paramLong1, paramLong2, paramInt1, paramInt2);
    byte[] arrayOfByte2 = localtlv_t112.get_tlv_112(paramArrayOfByte1);
    byte[] arrayOfByte3 = localtlv_t107.get_tlv_107(paramInt3, paramInt4, paramInt5, paramInt6);
    byte[] arrayOfByte4 = localtlv_t108.get_tlv_108(paramArrayOfByte2);
    byte[] arrayOfByte5 = localtlv_t109.get_tlv_109(paramArrayOfByte3);
    byte[] arrayOfByte6 = localtlv_t116.get_tlv_116(paramInt7, paramInt8, paramArrayOfLong);
    byte[] arrayOfByte7 = localtlv_t124.get_tlv124(util.get_os_type(), util.get_os_version(), this._g._network_type, this._g._sim_operator_name, new byte[0], this._g._apn);
    byte[] arrayOfByte8 = localtlv_t128.get_tlv128(this._g._new_install, this._g._read_guid, this._g._guid_chg, 0, this._g._device, this._g._IMEI);
    int i;
    byte[] arrayOfByte9;
    int m;
    int n;
    if (util.SSO_VERSION <= 2)
    {
      i = arrayOfByte1.length + arrayOfByte2.length + arrayOfByte3.length + arrayOfByte4.length + arrayOfByte5.length + arrayOfByte6.length;
      arrayOfByte9 = new byte[i];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte9, 0, arrayOfByte1.length);
      int j = 0 + arrayOfByte1.length;
      System.arraycopy(arrayOfByte2, 0, arrayOfByte9, j, arrayOfByte2.length);
      int k = j + arrayOfByte2.length;
      System.arraycopy(arrayOfByte3, 0, arrayOfByte9, k, arrayOfByte3.length);
      m = k + arrayOfByte3.length;
      n = 0 + 3;
      if (paramArrayOfByte2.length > 0)
      {
        System.arraycopy(arrayOfByte4, 0, arrayOfByte9, m, arrayOfByte4.length);
        m += arrayOfByte4.length;
        n++;
      }
      if (util.SSO_VERSION > 2)
        break label470;
      if (paramArrayOfByte3.length > 0)
      {
        System.arraycopy(arrayOfByte5, 0, arrayOfByte9, m, arrayOfByte5.length);
        m += arrayOfByte5.length;
        n++;
      }
    }
    while (true)
    {
      System.arraycopy(arrayOfByte6, 0, arrayOfByte9, m, arrayOfByte6.length);
      (m + arrayOfByte6.length);
      int i2 = n + 1;
      return encrypt_body(arrayOfByte9, this._sub_cmd, i2);
      i = arrayOfByte1.length + arrayOfByte2.length + arrayOfByte3.length + arrayOfByte4.length + arrayOfByte7.length + arrayOfByte8.length + arrayOfByte6.length;
      break;
      label470: System.arraycopy(arrayOfByte7, 0, arrayOfByte9, m, arrayOfByte7.length);
      int i1 = m + arrayOfByte7.length;
      System.arraycopy(arrayOfByte8, 0, arrayOfByte9, i1, arrayOfByte8.length);
      m = i1 + arrayOfByte8.length;
      n = 1 + (n + 1);
    }
  }

  public int make_request(long paramLong1, long paramLong2, int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long[] paramArrayOfLong)
  {
    int i = this._g._app_client_version;
    this._g._name = new String(paramArrayOfByte);
    get_request(this._default_client_version, this._cmd, this._default_client_seq, 0L, this._default_ext_retry, this._default_ext_type, i, this._default_ext_instance, get_request_body(paramLong1, paramLong2, i, paramInt2, paramArrayOfByte, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramArrayOfLong, this._g._ksid, this._g._IMEI));
    int j = snd_rcv_req(this._g._name);
    if (j != 0)
      return j;
    return get_response(this._rsp_buf, this._rsp_buf.length);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.request_getuin
 * JD-Core Version:    0.6.0
 */