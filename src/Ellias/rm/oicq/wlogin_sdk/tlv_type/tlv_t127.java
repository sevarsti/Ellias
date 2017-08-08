package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t127 extends tlv_t
{
  int _t127_body_len = 0;
  int _version = 0;

  public tlv_t127()
  {
    this._cmd = 295;
  }

  public byte[] get_tlv127(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    this._t127_body_len = (2 + (4 + paramArrayOfByte1.length) + paramArrayOfByte2.length);
    byte[] arrayOfByte = new byte[this._t127_body_len];
    util.int16_to_buf(arrayOfByte, 0, this._version);
    int i = 0 + 2;
    util.int16_to_buf(arrayOfByte, i, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, i + 2, paramArrayOfByte1.length);
    int j = 4 + paramArrayOfByte1.length;
    util.int16_to_buf(arrayOfByte, j, paramArrayOfByte2.length);
    int k = j + 2;
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, k, paramArrayOfByte2.length);
    (k + paramArrayOfByte2.length);
    fill_head(this._cmd);
    fill_body(arrayOfByte, arrayOfByte.length);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t127
 * JD-Core Version:    0.6.0
 */