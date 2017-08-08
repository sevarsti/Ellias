package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t166 extends tlv_t
{
  public tlv_t166()
  {
    this._cmd = 358;
  }

  public byte[] get_tlv166(int paramInt)
  {
    byte[] arrayOfByte = new byte[1];
    util.int8_to_buf(arrayOfByte, 0, paramInt);
    (0 + 1);
    fill_head(this._cmd);
    fill_body(arrayOfByte, arrayOfByte.length);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t166
 * JD-Core Version:    0.6.0
 */