package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_ta extends tlv_t
{
  int _msg_len = 0;

  public tlv_ta()
  {
    this._cmd = 10;
  }

  public byte[] get_msg()
  {
    byte[] arrayOfByte = new byte[this._msg_len];
    if (this._msg_len <= 0)
      return arrayOfByte;
    System.arraycopy(this._buf, 6 + this._head_len, arrayOfByte, 0, this._msg_len);
    return arrayOfByte;
  }

  public void get_tlv_ta(byte[] paramArrayOfByte, int paramInt)
  {
    set_buf(paramArrayOfByte, paramInt);
  }

  public Boolean verify()
  {
    if (this._body_len < 6)
      return Boolean.valueOf(false);
    int i = util.buf_to_int16(this._buf, 4 + this._head_len);
    if (i + 6 != this._body_len)
      return Boolean.valueOf(false);
    this._msg_len = i;
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_ta
 * JD-Core Version:    0.6.0
 */