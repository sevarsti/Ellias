package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.tlv_type.tlv_t104;

public class request_flush_sms extends oicq_request
{
  public request_flush_sms(request_global paramrequest_global)
  {
    this._cmd = 2064;
    this._sub_cmd = 8;
    this._g = paramrequest_global;
  }

  public byte[] get_request_body(byte[] paramArrayOfByte)
  {
    return encrypt_body(new tlv_t104().get_tlv_104(paramArrayOfByte), this._sub_cmd, 1);
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
 * Qualified Name:     oicq.wlogin_sdk.request.request_flush_sms
 * JD-Core Version:    0.6.0
 */