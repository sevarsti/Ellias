package oicq.wlogin_sdk.register;

import oicq.wlogin_sdk.tools.MD5;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

public class reg_request_get_account extends reg_request
{
  public reg_request_get_account()
  {
    this._cmd = 6;
  }

  public byte[] get_encrypt_tocken(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5)
  {
    if (paramArrayOfByte5 == null)
      paramArrayOfByte5 = new byte[0];
    byte[] arrayOfByte1 = new byte[2 + (2 + (2 + (1 + (1 + (1 + (1 + paramArrayOfByte1.length + paramArrayOfByte3.length))))) + paramArrayOfByte4.length) + paramArrayOfByte5.length];
    util.int8_to_buf(arrayOfByte1, 0, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte1, 0 + 1, paramArrayOfByte1.length);
    int i = 1 + paramArrayOfByte1.length;
    util.int8_to_buf(arrayOfByte1, i, paramArrayOfByte3.length);
    int j = i + 1;
    System.arraycopy(paramArrayOfByte3, 0, arrayOfByte1, j, paramArrayOfByte3.length);
    int k = j + paramArrayOfByte3.length;
    util.int8_to_buf(arrayOfByte1, k, paramInt);
    int m = k + 1;
    util.int8_to_buf(arrayOfByte1, m, 2);
    int n = m + 1;
    util.int8_to_buf(arrayOfByte1, n, 1);
    int i1 = n + 1;
    util.int8_to_buf(arrayOfByte1, i1, paramArrayOfByte4.length);
    int i2 = i1 + 1;
    System.arraycopy(paramArrayOfByte4, 0, arrayOfByte1, i2, paramArrayOfByte4.length);
    int i3 = i2 + paramArrayOfByte4.length;
    util.int8_to_buf(arrayOfByte1, i3, 5);
    int i4 = i3 + 1;
    util.int8_to_buf(arrayOfByte1, i4, paramArrayOfByte5.length);
    int i5 = i4 + 1;
    System.arraycopy(paramArrayOfByte5, 0, arrayOfByte1, i5, paramArrayOfByte5.length);
    (i5 + paramArrayOfByte5.length);
    if ((paramArrayOfByte2 == null) || (paramArrayOfByte2.length <= 0));
    for (byte[] arrayOfByte2 = reg_status.STATIC_KEY.getBytes(); ; arrayOfByte2 = MD5.toMD5Byte(paramArrayOfByte2))
      return cryptor.encrypt(arrayOfByte1, 0, arrayOfByte1.length, arrayOfByte2);
  }

  public byte[] get_request(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5)
  {
    byte[] arrayOfByte1 = get_encrypt_tocken(paramArrayOfByte1, paramArrayOfByte2, paramArrayOfByte3, paramInt, paramArrayOfByte4, paramArrayOfByte5);
    this._body_len = (1 + (1 + paramArrayOfByte1.length) + arrayOfByte1.length);
    byte[] arrayOfByte2 = new byte[this._body_len];
    util.int8_to_buf(arrayOfByte2, 0, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte2, 0 + 1, paramArrayOfByte1.length);
    int i = 1 + paramArrayOfByte1.length;
    util.int8_to_buf(arrayOfByte2, i, arrayOfByte1.length);
    int j = i + 1;
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, j, arrayOfByte1.length);
    (j + arrayOfByte1.length);
    return get_request(arrayOfByte2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.register.reg_request_get_account
 * JD-Core Version:    0.6.0
 */