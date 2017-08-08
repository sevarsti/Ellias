package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t107 extends tlv_t
{
  int _t107_body_len = 6;

  public tlv_t107()
  {
    this._cmd = 263;
  }

  public byte[] get_tlv_107(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    byte[] arrayOfByte = new byte[this._t107_body_len];
    util.int16_to_buf(arrayOfByte, 0, paramInt1);
    int i = 0 + 2;
    util.int8_to_buf(arrayOfByte, i, paramInt2);
    int j = i + 1;
    util.int16_to_buf(arrayOfByte, j, paramInt3);
    int k = j + 2;
    util.int8_to_buf(arrayOfByte, k, paramInt4);
    (k + 1);
    fill_head(this._cmd);
    fill_body(arrayOfByte, this._t107_body_len);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t107
 * JD-Core Version:    0.6.0
 */