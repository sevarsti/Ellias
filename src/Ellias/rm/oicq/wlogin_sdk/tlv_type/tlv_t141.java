package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t141 extends tlv_t
{
  int _version = 1;

  public tlv_t141()
  {
    this._cmd = 321;
  }

  public byte[] get_tlv_141(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2)
  {
    int i = 2 + (2 + (4 + paramArrayOfByte1.length)) + paramArrayOfByte2.length;
    byte[] arrayOfByte = new byte[i];
    util.int16_to_buf(arrayOfByte, 0, this._version);
    int j = 0 + 2;
    util.int16_to_buf(arrayOfByte, j, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, j + 2, paramArrayOfByte1.length);
    int k = 4 + paramArrayOfByte1.length;
    util.int16_to_buf(arrayOfByte, k, paramInt);
    int m = k + 2;
    util.int16_to_buf(arrayOfByte, m, paramArrayOfByte2.length);
    int n = m + 2;
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, n, paramArrayOfByte2.length);
    (n + paramArrayOfByte2.length);
    fill_head(this._cmd);
    fill_body(arrayOfByte, i);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t141
 * JD-Core Version:    0.6.0
 */