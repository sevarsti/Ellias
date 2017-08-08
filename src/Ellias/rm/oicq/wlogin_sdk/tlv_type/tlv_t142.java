package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t142 extends tlv_t
{
  int _t142_body_len = 0;
  int _version = 0;

  public tlv_t142()
  {
    this._cmd = 322;
  }

  public byte[] get_tlv142(byte[] paramArrayOfByte)
  {
    this._t142_body_len = (4 + paramArrayOfByte.length);
    byte[] arrayOfByte = new byte[this._t142_body_len];
    util.int16_to_buf(arrayOfByte, 0, this._version);
    int i = 0 + 2;
    util.int16_to_buf(arrayOfByte, i, paramArrayOfByte.length);
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, i + 2, paramArrayOfByte.length);
    (4 + paramArrayOfByte.length);
    fill_head(this._cmd);
    fill_body(arrayOfByte, arrayOfByte.length);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t142
 * JD-Core Version:    0.6.0
 */