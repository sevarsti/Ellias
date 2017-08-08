package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t11d extends tlv_t
{
  int _st_len = 0;

  public tlv_t11d()
  {
    this._cmd = 285;
  }

  public long get_appid()
  {
    return 0xFFFFFFFF & util.buf_to_int32(this._buf, this._head_len);
  }

  public byte[] get_st()
  {
    byte[] arrayOfByte = new byte[this._st_len];
    System.arraycopy(this._buf, 2 + (16 + (4 + this._head_len)), arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }

  public byte[] get_stkey()
  {
    byte[] arrayOfByte = new byte[16];
    System.arraycopy(this._buf, 4 + this._head_len, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }

  public Boolean verify()
  {
    if (this._body_len < 22)
      return Boolean.valueOf(false);
    this._st_len = util.buf_to_int16(this._buf, 16 + (4 + this._head_len));
    this._st_len = (0xFFFF & this._st_len);
    if (this._body_len < 22 + this._st_len)
      return Boolean.valueOf(false);
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t11d
 * JD-Core Version:    0.6.0
 */