package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.tlv_type.tlv_t140;
import oicq.wlogin_sdk.tlv_type.tlv_t141;
import oicq.wlogin_sdk.tlv_type.tlv_t17;
import oicq.wlogin_sdk.tlv_type.tlv_t18;
import oicq.wlogin_sdk.tools.util;

public class request_ping extends oicq_request
{
  public request_ping(request_global paramrequest_global)
  {
    this._cmd = 2065;
    this._sub_cmd = 0;
    this._g = paramrequest_global;
  }

  public byte[] get_request_body(long paramLong1, int paramInt1, long paramLong2, int paramInt2)
  {
    tlv_t18 localtlv_t18 = new tlv_t18();
    tlv_t141 localtlv_t141 = new tlv_t141();
    byte[] arrayOfByte1 = localtlv_t18.get_tlv_18(paramLong1, paramInt1, paramLong2, paramInt2);
    byte[] arrayOfByte2 = localtlv_t141.get_tlv_141(this._g._sim_operator_name, this._g._network_type, this._g._apn);
    byte[] arrayOfByte3 = new byte[arrayOfByte1.length + arrayOfByte2.length];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 0, arrayOfByte1.length);
    int i = 0 + 1;
    int j = 0 + arrayOfByte1.length;
    System.arraycopy(arrayOfByte2, 0, arrayOfByte3, j, arrayOfByte2.length);
    int k = i + 1;
    (j + arrayOfByte2.length);
    return encrypt_body(arrayOfByte3, this._sub_cmd, k);
  }

  public int get_response_body(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    tlv_t17 localtlv_t17 = new tlv_t17();
    tlv_t140 localtlv_t140 = new tlv_t140();
    if (paramInt2 < 5)
      return -1;
    int i = get_response_ret_code(paramArrayOfByte, paramInt1);
    set_err_msg(null);
    util.LOGD(getClass().getName(), "type=" + i);
    int j = paramInt1 + 5;
    int k;
    switch (i)
    {
    default:
      get_err_msg(paramArrayOfByte, j, -1 + (this._pos - j));
      k = i;
    case 0:
    }
    do
    {
      return k;
      k = localtlv_t17.get_tlv(paramArrayOfByte, j, -1 + (this._pos - j));
    }
    while (k < 0);
    this._g.set_time_ip(localtlv_t17.get_time(), localtlv_t17.get_ipaddr());
    if (localtlv_t140.get_tlv(paramArrayOfByte, j, -1 + (this._pos - j)) >= 0)
    {
      if (!util.is_wap_retry(this._g._context))
        break label331;
      if (this._g._network_type != 1)
        break label302;
      util.set_wap_server_host1(this._g._context, localtlv_t140.get_host());
    }
    while (true)
    {
      util.LOGD("set server info", "networktype:" + new Integer(this._g._network_type).toString() + "host:" + new String(localtlv_t140.get_host()) + "port:" + new Integer(localtlv_t140.get_port()).toString());
      k = i;
      break;
      label302: if (this._g._network_type != 2)
        continue;
      util.set_wap_server_host2(this._g._context, localtlv_t140.get_host());
      continue;
      label331: if (this._g._network_type == 1)
      {
        util.set_server_host1(this._g._context, localtlv_t140.get_host());
        continue;
      }
      if (this._g._network_type != 2)
        continue;
      util.set_server_host2(this._g._context, localtlv_t140.get_host());
    }
  }

  public int make_request(long paramLong1, long paramLong2)
  {
    int i = this._g._app_client_version;
    byte[] arrayOfByte = get_request_body(paramLong2, i, paramLong1, 0);
    get_request(this._default_client_version, this._cmd, this._default_client_seq, paramLong1, this._default_ext_retry, this._default_ext_type, i, this._default_ext_instance, arrayOfByte);
    int j = snd_rcv_req(String.valueOf(paramLong1));
    if (j != 0)
      return j;
    return get_response(this._rsp_buf, this._rsp_buf.length);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.request_ping
 * JD-Core Version:    0.6.0
 */