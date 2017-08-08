package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t105 extends tlv_t
{
  int _en_pos = 0;
  int _enlen = 0;
  int _pic_pos = 0;
  int _piclen = 0;

  public tlv_t105()
  {
    this._cmd = 261;
  }

  public byte[] get_pic()
  {
    byte[] arrayOfByte = new byte[this._piclen];
    if (this._piclen <= 0)
      return arrayOfByte;
    System.arraycopy(this._buf, this._pic_pos, arrayOfByte, 0, this._piclen);
    return arrayOfByte;
  }

  public byte[] get_sign()
  {
    byte[] arrayOfByte = new byte[this._enlen];
    if (this._enlen <= 0)
      return arrayOfByte;
    System.arraycopy(this._buf, this._en_pos, arrayOfByte, 0, this._enlen);
    return arrayOfByte;
  }

  public Boolean verify()
  {
    if (this._body_len < 2)
      return Boolean.valueOf(false);
    this._enlen = util.buf_to_int16(this._buf, this._head_len);
    if (this._body_len < 2 + (2 + this._enlen))
      return Boolean.valueOf(false);
    this._piclen = util.buf_to_int16(this._buf, 2 + this._head_len + this._enlen);
    if (this._body_len < 2 + (2 + this._enlen) + this._piclen)
      return Boolean.valueOf(false);
    this._en_pos = (2 + this._head_len);
    this._pic_pos = (2 + (2 + this._enlen) + this._head_len);
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t105
 * JD-Core Version:    0.6.0
 */