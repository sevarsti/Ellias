package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t124 extends tlv_t
{
  int _t124_body_len = 0;

  public tlv_t124()
  {
    this._cmd = 292;
  }

  public byte[] get_tlv124(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5)
  {
    this._t124_body_len = (2 + (2 + (2 + (2 + (2 + (2 + paramArrayOfByte1.length) + paramArrayOfByte2.length)) + paramArrayOfByte3.length) + paramArrayOfByte4.length) + paramArrayOfByte5.length);
    byte[] arrayOfByte = new byte[this._t124_body_len];
    util.int16_to_buf(arrayOfByte, 0, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, 0 + 2, paramArrayOfByte1.length);
    int i = 2 + paramArrayOfByte1.length;
    util.int16_to_buf(arrayOfByte, i, paramArrayOfByte2.length);
    int j = i + 2;
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, j, paramArrayOfByte2.length);
    int k = j + paramArrayOfByte2.length;
    util.int16_to_buf(arrayOfByte, k, paramInt);
    int m = k + 2;
    util.int16_to_buf(arrayOfByte, m, paramArrayOfByte3.length);
    int n = m + 2;
    System.arraycopy(paramArrayOfByte3, 0, arrayOfByte, n, paramArrayOfByte3.length);
    int i1 = n + paramArrayOfByte3.length;
    util.int16_to_buf(arrayOfByte, i1, paramArrayOfByte4.length);
    int i2 = i1 + 2;
    System.arraycopy(paramArrayOfByte4, 0, arrayOfByte, i2, paramArrayOfByte4.length);
    int i3 = i2 + paramArrayOfByte4.length;
    util.int16_to_buf(arrayOfByte, i3, paramArrayOfByte5.length);
    int i4 = i3 + 2;
    System.arraycopy(paramArrayOfByte5, 0, arrayOfByte, i4, paramArrayOfByte5.length);
    (i4 + paramArrayOfByte5.length);
    fill_head(this._cmd);
    fill_body(arrayOfByte, this._t124_body_len);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t124
 * JD-Core Version:    0.6.0
 */