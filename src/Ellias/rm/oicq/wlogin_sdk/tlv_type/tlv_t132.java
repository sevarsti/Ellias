package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t132 extends tlv_t
{
  public int _token_len = 0;

  public tlv_t132()
  {
    this._cmd = 306;
  }

  public byte[] get_access_token()
  {
    byte[] arrayOfByte = new byte[this._token_len];
    System.arraycopy(this._buf, 2 + this._head_len, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }

  public Boolean verify()
  {
    if (this._body_len < 2)
      return Boolean.valueOf(false);
    this._token_len = util.buf_to_int16(this._buf, this._head_len);
    if (2 + this._token_len > this._body_len)
      return Boolean.valueOf(false);
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t132
 * JD-Core Version:    0.6.0
 */