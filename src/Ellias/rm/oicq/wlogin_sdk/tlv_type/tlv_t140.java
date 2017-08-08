package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t140 extends tlv_t
{
  int _info_len = 0;

  public tlv_t140()
  {
    this._cmd = 320;
  }

  public byte[] get_host()
  {
    byte[] arrayOfByte = new byte[this._info_len];
    System.arraycopy(this._buf, 2 + (2 + this._head_len), arrayOfByte, 0, this._info_len);
    return arrayOfByte;
  }

  public int get_port()
  {
    return util.buf_to_int16(this._buf, 2 + (2 + this._head_len) + this._info_len);
  }

  public Boolean verify()
  {
    if (this._body_len < 4)
      return Boolean.valueOf(false);
    this._info_len = util.buf_to_int16(this._buf, 2 + this._head_len);
    if (this._body_len < 2 + (2 + (4 + this._info_len)))
      return Boolean.valueOf(false);
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t140
 * JD-Core Version:    0.6.0
 */