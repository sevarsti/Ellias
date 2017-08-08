package oicq.wlogin_sdk.register;

import oicq.wlogin_sdk.tools.util;

public class reg_request_submit_checkimg extends reg_request
{
  public reg_request_submit_checkimg()
  {
    this._cmd = 2;
  }

  public byte[] get_request(int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    this._body_len = (2 + (1 + (2 + paramArrayOfByte1.length) + paramArrayOfByte2.length) + paramArrayOfByte3.length);
    byte[] arrayOfByte = new byte[this._body_len];
    util.int8_to_buf(arrayOfByte, 0, paramInt);
    int i = 0 + 1;
    util.int8_to_buf(arrayOfByte, i, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, i + 1, paramArrayOfByte1.length);
    int j = 2 + paramArrayOfByte1.length;
    util.int8_to_buf(arrayOfByte, j, paramArrayOfByte2.length);
    int k = j + 1;
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, k, paramArrayOfByte2.length);
    int m = k + paramArrayOfByte2.length;
    util.int16_to_buf(arrayOfByte, m, paramArrayOfByte3.length);
    int n = m + 2;
    System.arraycopy(paramArrayOfByte3, 0, arrayOfByte, n, paramArrayOfByte3.length);
    (n + paramArrayOfByte3.length);
    return get_request(arrayOfByte);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.register.reg_request_submit_checkimg
 * JD-Core Version:    0.6.0
 */