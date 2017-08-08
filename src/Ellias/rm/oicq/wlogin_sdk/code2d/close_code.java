package oicq.wlogin_sdk.code2d;

import oicq.wlogin_sdk.tools.util;

public class close_code extends code2d_base
{
  public close_code()
  {
    this._cmd = 20;
  }

  public byte[] get_request(long paramLong1, long paramLong2, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt, byte[][] paramArrayOfByte)
  {
    int i = 0;
    byte[] arrayOfByte;
    int i5;
    int i6;
    int i7;
    if (paramArrayOfByte == null)
    {
      arrayOfByte = new byte[2 + (1 + (2 + (16 + paramArrayOfByte1.length) + paramArrayOfByte2.length))];
      int m = 0 + 2;
      util.int64_to_buf32(arrayOfByte, m, paramLong2);
      int n = m + 4;
      util.int64_to_buf(arrayOfByte, n, paramLong1);
      int i1 = n + 8;
      util.int16_to_buf(arrayOfByte, i1, paramArrayOfByte1.length);
      System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, i1 + 2, paramArrayOfByte1.length);
      int i2 = 16 + paramArrayOfByte1.length;
      util.int16_to_buf(arrayOfByte, i2, paramArrayOfByte2.length);
      int i3 = i2 + 2;
      System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, i3, paramArrayOfByte2.length);
      int i4 = i3 + paramArrayOfByte2.length;
      util.int8_to_buf(arrayOfByte, i4, 8);
      i5 = i4 + 1;
      if (paramArrayOfByte == null)
        break label281;
      util.int16_to_buf(arrayOfByte, i5, i);
      i6 = i5 + 2;
      i7 = 0;
      label170: if (i7 < i)
        break label245;
    }
    while (true)
    {
      return get_request(paramLong1, true, arrayOfByte);
      i = paramArrayOfByte.length;
      int j = 2 + (1 + (2 + (16 + paramArrayOfByte1.length) + paramArrayOfByte2.length));
      for (int k = 0; ; k++)
      {
        if (k >= i)
        {
          arrayOfByte = new byte[j];
          break;
        }
        j += paramArrayOfByte[k].length;
      }
      label245: System.arraycopy(paramArrayOfByte[i7], 0, arrayOfByte, i6, paramArrayOfByte[i7].length);
      i6 += paramArrayOfByte[i7].length;
      i7++;
      break label170;
      label281: util.int16_to_buf(arrayOfByte, i5, 0);
      (i5 + 2);
    }
  }

  public int get_response(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = get_response(paramArrayOfByte, 0);
    if ((arrayOfByte == null) || (arrayOfByte.length < 11))
      return -1009;
    int i = 0 + 2;
    _status._uin = util.buf_to_int64(arrayOfByte, i);
    int j = i + 8;
    _status._ret = (0xFF & util.buf_to_int8(arrayOfByte, j));
    int k = j + 1;
    if (_status._ret != 0)
    {
      int i2 = util.buf_to_int16(arrayOfByte, k);
      int i3 = k + 2;
      _status._msg = new byte[i2];
      System.arraycopy(arrayOfByte, i3, _status._msg, 0, i2);
      (i2 + 13);
      return _status._ret;
    }
    _status._time = (0xFFFFFFFF & util.buf_to_int32(arrayOfByte, k));
    int m = k + 4;
    int n = util.buf_to_int16(arrayOfByte, m);
    int i1 = m + 2;
    _status._app_name = new byte[n];
    System.arraycopy(arrayOfByte, i1, _status._app_name, 0, n);
    (n + 17);
    return _status._ret;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.code2d.close_code
 * JD-Core Version:    0.6.0
 */