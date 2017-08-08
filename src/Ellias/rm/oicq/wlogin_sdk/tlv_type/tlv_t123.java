package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t123 extends tlv_t
{
  int _lg = 0;
  int _ln = 0;
  int _ls = 0;
  int _ly = 0;
  int _t123_body_len = 0;

  public tlv_t123()
  {
    this._cmd = 291;
  }

  public byte[] get_tlv123(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, long paramLong)
  {
    this._t123_body_len = (2 + (4 + (2 + (2 + (2 + (2 + paramArrayOfByte1.length + paramArrayOfByte2.length) + paramArrayOfByte3.length) + paramArrayOfByte4.length))));
    byte[] arrayOfByte = new byte[this._t123_body_len];
    util.int16_to_buf(arrayOfByte, 0, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, 0 + 2, paramArrayOfByte1.length);
    int i = 2 + paramArrayOfByte1.length;
    util.int16_to_buf(arrayOfByte, i, paramArrayOfByte2.length);
    int j = i + 2;
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, j, paramArrayOfByte2.length);
    int k = j + paramArrayOfByte2.length;
    util.int16_to_buf(arrayOfByte, k, paramArrayOfByte3.length);
    int m = k + 2;
    System.arraycopy(paramArrayOfByte3, 0, arrayOfByte, m, paramArrayOfByte3.length);
    int n = m + paramArrayOfByte3.length;
    util.int16_to_buf(arrayOfByte, n, paramArrayOfByte4.length);
    int i1 = n + 2;
    System.arraycopy(paramArrayOfByte4, 0, arrayOfByte, i1, paramArrayOfByte4.length);
    int i2 = i1 + paramArrayOfByte4.length;
    util.int64_to_buf32(arrayOfByte, i2, paramLong);
    int i3 = i2 + 4;
    util.int16_to_buf(arrayOfByte, i3, 0);
    (i3 + 2);
    fill_head(this._cmd);
    fill_body(arrayOfByte, this._t123_body_len);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t123
 * JD-Core Version:    0.6.0
 */