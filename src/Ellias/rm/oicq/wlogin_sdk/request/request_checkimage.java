package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.tlv_type.tlv_t104;
import oicq.wlogin_sdk.tlv_type.tlv_t105;
import oicq.wlogin_sdk.tlv_type.tlv_t2;

public class request_checkimage extends oicq_request
{
  public request_checkimage(request_global paramrequest_global)
  {
    this._cmd = 2064;
    this._sub_cmd = 2;
    this._service_cmd = "wtlogin.login";
    this._g = paramrequest_global;
  }

  public byte[] get_request_body(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    tlv_t104 localtlv_t104 = new tlv_t104();
    tlv_t2 localtlv_t2 = new tlv_t2();
    byte[] arrayOfByte1 = localtlv_t104.get_tlv_104(paramArrayOfByte1);
    byte[] arrayOfByte2 = localtlv_t2.get_tlv_2(paramArrayOfByte2, paramArrayOfByte3);
    byte[] arrayOfByte3 = new byte[arrayOfByte1.length + arrayOfByte2.length];
    int i = paramArrayOfByte1.length;
    int j = 0;
    int k = 0;
    if (i > 0)
    {
      System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 0, arrayOfByte1.length);
      j = 0 + arrayOfByte1.length;
      k = 0 + 1;
    }
    System.arraycopy(arrayOfByte2, 0, arrayOfByte3, j, arrayOfByte2.length);
    (j + arrayOfByte2.length);
    int m = k + 1;
    return encrypt_body(arrayOfByte3, this._sub_cmd, m);
  }

  public int make_request(byte[] paramArrayOfByte)
  {
    int i = this._g._app_client_version;
    get_request(this._default_client_version, this._cmd, this._default_client_seq, this._g._uin, this._default_ext_retry, this._default_ext_type, i, this._default_ext_instance, get_request_body(this._g._t104.get_data(), paramArrayOfByte, this._g._t105.get_sign()));
    int j = snd_rcv_req(String.valueOf(this._g._uin));
    if (j != 0)
      return j;
    return get_response(this._rsp_buf, this._rsp_buf.length);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.request_checkimage
 * JD-Core Version:    0.6.0
 */