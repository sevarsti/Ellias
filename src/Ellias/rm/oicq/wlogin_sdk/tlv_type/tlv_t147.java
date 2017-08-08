package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t147 extends tlv_t
{
  public tlv_t147()
  {
    this._cmd = 327;
  }

  public byte[] get_tlv147(long paramLong, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte = new byte[2 + (6 + paramArrayOfByte1.length) + paramArrayOfByte2.length];
    util.int64_to_buf32(arrayOfByte, 0, paramLong);
    int i = 0 + 4;
    util.int16_to_buf(arrayOfByte, i, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, i + 2, paramArrayOfByte1.length);
    int j = 6 + paramArrayOfByte1.length;
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
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t147
 * JD-Core Version:    0.6.0
 */