package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t4 extends tlv_t
{
  int _account_type = 0;
  int _t4_body_len = 6;

  public tlv_t4()
  {
    this._cmd = 4;
  }

  public byte[] get_tlv_4(int paramInt, byte[] paramArrayOfByte)
  {
    this._account_type = paramInt;
    this._t4_body_len = (4 + paramArrayOfByte.length);
    byte[] arrayOfByte = new byte[this._t4_body_len];
    util.int16_to_buf(arrayOfByte, 0, this._cmd);
    int i = 0 + 2;
    util.int16_to_buf(arrayOfByte, i, paramArrayOfByte.length);
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, i + 2, paramArrayOfByte.length);
    (4 + paramArrayOfByte.length);
    fill_head(this._cmd);
    fill_body(arrayOfByte, this._t4_body_len);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t4
 * JD-Core Version:    0.6.0
 */