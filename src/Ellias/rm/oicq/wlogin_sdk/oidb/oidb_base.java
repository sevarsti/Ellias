package oicq.wlogin_sdk.oidb;

import oicq.wlogin_sdk.tools.util;

public class oidb_base
{
  public int _body_len = 0;
  public int _cmd = 0;
  public int _pkg_len = 0;
  public int _result = 0;
  public int _role = 126;
  public int _trans_pkg_head_ext_len = 0;
  public int _trans_pkg_head_len = 127;
  public int _type = 0;
  public long _uin = 0L;

  public byte[] decode_response(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 4 + this._trans_pkg_head_len));
    int k;
    do
    {
      int j;
      do
      {
        do
        {
          int i;
          do
          {
            return null;
            util.LOGD("rsp len:" + paramArrayOfByte.length + " data:" + util.buf_to_string(paramArrayOfByte));
            i = util.buf_to_int16(paramArrayOfByte, 1 + this._trans_pkg_head_len);
          }
          while (paramArrayOfByte.length < 2 + (i + this._trans_pkg_head_len));
          this._pkg_len = paramArrayOfByte.length;
          this._trans_pkg_head_ext_len = i;
        }
        while ((util.buf_to_int8(paramArrayOfByte, 0) != 10) || (util.buf_to_int8(paramArrayOfByte, -1 + paramArrayOfByte.length) != 3) || (util.buf_to_int16(paramArrayOfByte, 1) != paramArrayOfByte.length));
        j = 1 + 4;
      }
      while (util.buf_to_int16(paramArrayOfByte, j) != this._cmd);
      k = j + 2;
    }
    while (util.buf_to_int32(paramArrayOfByte, k) != this._uin);
    this._result = util.buf_to_int8(paramArrayOfByte, k + 4);
    util.LOGD("result:" + this._result);
    int m = 1 + this._trans_pkg_head_len + this._trans_pkg_head_ext_len;
    byte[] arrayOfByte = new byte[-2 + (this._pkg_len - this._trans_pkg_head_len - this._trans_pkg_head_ext_len)];
    System.arraycopy(paramArrayOfByte, m, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }

  public byte[] encode_request(long paramLong, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null))
      return null;
    this._trans_pkg_head_ext_len = (15 + paramArrayOfByte2.length);
    this._pkg_len = (2 + this._trans_pkg_head_len + this._trans_pkg_head_ext_len + paramArrayOfByte1.length);
    byte[] arrayOfByte1 = new byte[this._pkg_len];
    util.int8_to_buf(arrayOfByte1, 0, 10);
    int i = 0 + 1;
    byte[] arrayOfByte2 = get_trans_pkg_head();
    System.arraycopy(arrayOfByte2, 0, arrayOfByte1, i, arrayOfByte2.length);
    int j = 1 + arrayOfByte2.length;
    byte[] arrayOfByte3 = get_trans_pkg_head_ext(paramLong, paramArrayOfByte2);
    System.arraycopy(arrayOfByte3, 0, arrayOfByte1, j, arrayOfByte3.length);
    int k = j + arrayOfByte3.length;
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte1, k, paramArrayOfByte1.length);
    int m = k + paramArrayOfByte1.length;
    util.int8_to_buf(arrayOfByte1, m, 3);
    (m + 1);
    return arrayOfByte1;
  }

  public int get_cmd()
  {
    return this._cmd;
  }

  public byte[] get_trans_pkg_head()
  {
    byte[] arrayOfByte = new byte[this._trans_pkg_head_len];
    util.int16_to_buf(arrayOfByte, 0, this._pkg_len);
    int i = 0 + 2;
    util.int16_to_buf(arrayOfByte, i, 5);
    int j = i + 2;
    util.int16_to_buf(arrayOfByte, j, this._cmd);
    int k = j + 2;
    util.int64_to_buf32(arrayOfByte, k, this._uin);
    int m = 2 + (1 + (k + 4));
    util.string_to_buf("mobile qq");
    int n = m + 50;
    util.int8_to_buf(arrayOfByte, n, this._type);
    (n + 64);
    return arrayOfByte;
  }

  public byte[] get_trans_pkg_head_ext(long paramLong, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[this._trans_pkg_head_ext_len];
    util.int16_to_buf(arrayOfByte, 0, this._trans_pkg_head_ext_len);
    int i = 0 + 2;
    util.int16_to_buf(arrayOfByte, i, 600);
    int j = i + 2;
    util.int64_to_buf32(arrayOfByte, j, paramLong);
    int k = j + 4;
    util.int8_to_buf(arrayOfByte, k, 8);
    int m = k + 1;
    util.int16_to_buf(arrayOfByte, m, paramArrayOfByte.length);
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, m + 2, paramArrayOfByte.length);
    (4 + (11 + paramArrayOfByte.length));
    return arrayOfByte;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.oidb.oidb_base
 * JD-Core Version:    0.6.0
 */