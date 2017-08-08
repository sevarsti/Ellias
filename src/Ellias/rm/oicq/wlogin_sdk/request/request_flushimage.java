package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.tlv_type.tlv_t104;
import oicq.wlogin_sdk.tlv_type.tlv_t105;
import oicq.wlogin_sdk.tlv_type.tlv_t165;
import oicq.wlogin_sdk.tools.util;

public class request_flushimage extends oicq_request
{
  public request_flushimage(request_global paramrequest_global)
  {
    this._cmd = 2064;
    this._sub_cmd = 3;
    this._service_cmd = "wtlogin.login";
    this._g = paramrequest_global;
  }

  public byte[] get_request_body(byte[] paramArrayOfByte)
  {
    return encrypt_body(new tlv_t104().get_tlv_104(paramArrayOfByte), this._sub_cmd, 1);
  }

  public int get_response_body(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    tlv_t104 localtlv_t104 = new tlv_t104();
    tlv_t105 localtlv_t105 = new tlv_t105();
    tlv_t165 localtlv_t165 = new tlv_t165();
    int i = get_response_ret_code(paramArrayOfByte, paramInt1 + 2);
    set_err_msg(null);
    util.LOGD(getClass().getName(), "type=" + i);
    int j = paramInt1 + 5;
    int k;
    switch (i)
    {
    case 3:
    case 4:
    default:
      get_err_msg(paramArrayOfByte, j, -1 + (this._pos - j));
      k = i;
    case 2:
      do
      {
        do
        {
          return k;
          k = localtlv_t104.get_tlv(paramArrayOfByte, j, this._pos - j);
        }
        while (k < 0);
        this._g._t104 = localtlv_t104;
        k = localtlv_t105.get_tlv(paramArrayOfByte, j, this._pos - j);
      }
      while (k < 0);
      this._g._t105 = localtlv_t105;
      if (localtlv_t165.get_tlv(paramArrayOfByte, j, this._pos - j) >= 0);
      for (this._g._t165 = localtlv_t165; ; this._g._t165 = new tlv_t165())
      {
        get_err_msg(paramArrayOfByte, j, -1 + (this._pos - j));
        return i;
      }
    case 5:
    }
    get_err_msg(paramArrayOfByte, j, -1 + (this._pos - j));
    return i;
  }

  public int make_request()
  {
    int i = this._g._app_client_version;
    get_request(this._default_client_version, this._cmd, this._default_client_seq, this._g._uin, this._default_ext_retry, this._default_ext_type, i, this._default_ext_instance, get_request_body(this._g._t104.get_data()));
    int j = snd_rcv_req(String.valueOf(this._g._uin));
    if (j != 0)
      return j;
    return get_response(this._rsp_buf, this._rsp_buf.length);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.request_flushimage
 * JD-Core Version:    0.6.0
 */