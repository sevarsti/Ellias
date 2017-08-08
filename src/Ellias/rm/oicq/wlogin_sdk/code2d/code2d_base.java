package oicq.wlogin_sdk.code2d;

import oicq.wlogin_sdk.tools.util;

public class code2d_base
{
  public static int _seq = 0;
  public static code2d_req_status _status;
  public static int _version = 32;
  public int _cmd = 0;
  public int _head_len = 43;
  public int _role = 114;
  public int _sub_cmd = 0;

  static
  {
    _status = new code2d_req_status();
  }

  public int get_cmd()
  {
    return this._cmd;
  }

  public byte[] get_request(long paramLong, boolean paramBoolean, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[1 + (this._head_len + paramArrayOfByte.length)];
    util.int8_to_buf(arrayOfByte, 0, 2);
    int i = 0 + 1;
    util.int16_to_buf(arrayOfByte, i, arrayOfByte.length);
    int j = i + 2;
    util.int16_to_buf(arrayOfByte, j, this._cmd);
    int k = 1 + (1 + (2 + (4 + (2 + (4 + (2 + (4 + (2 + (j + 2)))))))));
    if (paramBoolean)
      util.int16_to_buf(arrayOfByte, k, 0);
    while (true)
    {
      int m = k + 2;
      util.int16_to_buf(arrayOfByte, m, _version);
      int n = m + 2;
      int i1 = _seq;
      _seq = i1 + 1;
      util.int32_to_buf(arrayOfByte, n, i1);
      int i2 = n + 4;
      util.int64_to_buf(arrayOfByte, i2, paramLong);
      System.arraycopy(paramArrayOfByte, 0, arrayOfByte, i2 + 8, paramArrayOfByte.length);
      int i3 = 43 + paramArrayOfByte.length;
      util.int8_to_buf(arrayOfByte, i3, 3);
      (i3 + 1);
      return arrayOfByte;
      util.int16_to_buf(arrayOfByte, k, 1);
    }
  }

  public byte[] get_response(byte[] paramArrayOfByte, int paramInt)
  {
    if (paramArrayOfByte.length <= this._head_len)
      return null;
    byte[] arrayOfByte = new byte[paramArrayOfByte.length - this._head_len];
    System.arraycopy(paramArrayOfByte, this._head_len, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.code2d.code2d_base
 * JD-Core Version:    0.6.0
 */