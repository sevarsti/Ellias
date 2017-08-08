package oicq.wlogin_sdk.register;

import android.os.Build.VERSION;
import oicq.wlogin_sdk.tools.util;

public class reg_request_submit_mobile extends reg_request
{
  public reg_request_submit_mobile()
  {
    this._cmd = 1;
  }

  public byte[] get_request(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2, int paramInt3, long paramLong, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5)
  {
    this._body_len = (1 + (1 + (4 + (4 + (1 + (1 + (1 + (1 + (1 + (1 + (1 + (1 + paramArrayOfByte1.length)) + Build.VERSION.RELEASE.length()) + paramArrayOfByte2.length) + paramArrayOfByte3.length)))))) + paramArrayOfByte5.length));
    byte[] arrayOfByte = new byte[this._body_len];
    util.int8_to_buf(arrayOfByte, 0, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, 0 + 1, paramArrayOfByte1.length);
    int i = 1 + paramArrayOfByte1.length;
    util.int8_to_buf(arrayOfByte, i, this._os_type);
    int j = i + 1;
    util.int8_to_buf(arrayOfByte, j, Build.VERSION.RELEASE.length());
    int k = j + 1;
    System.arraycopy(Build.VERSION.RELEASE.getBytes(), 0, arrayOfByte, k, Build.VERSION.RELEASE.length());
    int m = k + Build.VERSION.RELEASE.length();
    util.int8_to_buf(arrayOfByte, m, paramArrayOfByte2.length);
    int n = m + 1;
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, n, paramArrayOfByte2.length);
    int i1 = n + paramArrayOfByte2.length;
    util.int8_to_buf(arrayOfByte, i1, paramArrayOfByte3.length);
    int i2 = i1 + 1;
    System.arraycopy(paramArrayOfByte3, 0, arrayOfByte, i2, paramArrayOfByte3.length);
    int i3 = i2 + paramArrayOfByte3.length;
    util.int8_to_buf(arrayOfByte, i3, paramInt1);
    int i4 = i3 + 1;
    util.int8_to_buf(arrayOfByte, i4, paramInt2);
    int i5 = i4 + 1;
    util.int8_to_buf(arrayOfByte, i5, paramInt3);
    int i6 = i5 + 1;
    util.int64_to_buf32(arrayOfByte, i6, paramLong);
    int i7 = i6 + 4;
    if ((paramArrayOfByte4 != null) && (paramArrayOfByte4.length == 4))
      System.arraycopy(paramArrayOfByte4, 0, arrayOfByte, i7, 4);
    while (true)
    {
      int i8 = i7 + 4;
      util.int8_to_buf(arrayOfByte, i8, 0);
      (i8 + 1);
      return get_request(arrayOfByte);
      util.int32_to_buf(arrayOfByte, i7, 0);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.register.reg_request_submit_mobile
 * JD-Core Version:    0.6.0
 */