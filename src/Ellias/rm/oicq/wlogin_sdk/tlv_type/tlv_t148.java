package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t148 extends tlv_t
{
  public tlv_t148()
  {
    this._cmd = 328;
  }

  public byte[] get_tlv148(byte[] paramArrayOfByte1, long paramLong1, long paramLong2, long paramLong3, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    byte[] arrayOfByte = new byte[2 + (2 + (4 + (4 + (4 + (2 + paramArrayOfByte1.length)))) + paramArrayOfByte2.length) + paramArrayOfByte3.length];
    util.int16_to_buf(arrayOfByte, 0, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, 0 + 2, paramArrayOfByte1.length);
    int i = 2 + paramArrayOfByte1.length;
    util.int64_to_buf32(arrayOfByte, i, paramLong1);
    int j = i + 4;
    util.int64_to_buf32(arrayOfByte, j, paramLong2);
    int k = j + 4;
    util.int64_to_buf32(arrayOfByte, k, paramLong3);
    int m = k + 4;
    util.int16_to_buf(arrayOfByte, m, paramArrayOfByte2.length);
    int n = m + 2;
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, n, paramArrayOfByte2.length);
    int i1 = n + paramArrayOfByte2.length;
    util.int16_to_buf(arrayOfByte, i1, paramArrayOfByte3.length);
    int i2 = i1 + 2;
    System.arraycopy(paramArrayOfByte3, 0, arrayOfByte, i2, paramArrayOfByte3.length);
    (i2 + paramArrayOfByte3.length);
    fill_head(this._cmd);
    fill_body(arrayOfByte, arrayOfByte.length);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t148
 * JD-Core Version:    0.6.0
 */