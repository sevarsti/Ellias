package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t153 extends tlv_t
{
  public tlv_t153()
  {
    this._cmd = 339;
  }

  public byte[] get_tlv153(int paramInt)
  {
    byte[] arrayOfByte = new byte[2];
    util.int16_to_buf(arrayOfByte, 0, paramInt);
    (0 + 2);
    fill_head(this._cmd);
    fill_body(arrayOfByte, arrayOfByte.length);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t153
 * JD-Core Version:    0.6.0
 */