package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t128 extends tlv_t
{
  int _t128_body_len = 0;

  public tlv_t128()
  {
    this._cmd = 296;
  }

  public byte[] get_tlv128(int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    this._t128_body_len = (2 + (2 + (11 + paramArrayOfByte1.length) + paramArrayOfByte2.length));
    byte[] arrayOfByte = new byte[this._t128_body_len];
    util.int16_to_buf(arrayOfByte, 0, 0);
    int i = 0 + 2;
    util.int8_to_buf(arrayOfByte, i, paramInt1);
    int j = i + 1;
    util.int8_to_buf(arrayOfByte, j, paramInt2);
    int k = j + 1;
    util.int8_to_buf(arrayOfByte, k, paramInt3);
    int m = k + 1;
    util.int32_to_buf(arrayOfByte, m, paramInt4);
    int n = m + 4;
    util.int16_to_buf(arrayOfByte, n, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, n + 2, paramArrayOfByte1.length);
    int i1 = 11 + paramArrayOfByte1.length;
    util.int16_to_buf(arrayOfByte, i1, paramArrayOfByte2.length);
    int i2 = i1 + 2;
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, i2, paramArrayOfByte2.length);
    int i3 = i2 + paramArrayOfByte2.length;
    util.int16_to_buf(arrayOfByte, i3, 0);
    (i3 + 2);
    fill_head(this._cmd);
    fill_body(arrayOfByte, this._t128_body_len);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t128
 * JD-Core Version:    0.6.0
 */