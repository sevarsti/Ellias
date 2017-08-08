package oicq.wlogin_sdk.code2d;

import java.lang.reflect.Array;
import oicq.wlogin_sdk.tools.util;

public class verify_code extends code2d_base
{
  public verify_code()
  {
    this._cmd = 19;
  }

  public byte[] get_request(long paramLong1, long paramLong2, boolean paramBoolean, byte[] paramArrayOfByte1, int[] paramArrayOfInt, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt)
  {
    byte[] arrayOfByte;
    int i1;
    int i2;
    label168: int i5;
    if (paramArrayOfInt == null)
    {
      arrayOfByte = new byte[2 + (1 + (2 + (1 + (16 + (2 + (16 + paramArrayOfByte1.length) + paramArrayOfByte2.length)))))];
      int i = 0 + 2;
      util.int64_to_buf32(arrayOfByte, i, paramLong2);
      int j = i + 4;
      util.int64_to_buf(arrayOfByte, j, paramLong1);
      int k = j + 8;
      util.int16_to_buf(arrayOfByte, k, paramArrayOfByte1.length);
      System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, k + 2, paramArrayOfByte1.length);
      int m = 16 + paramArrayOfByte1.length;
      util.int16_to_buf(arrayOfByte, m, paramArrayOfByte2.length);
      int n = m + 2;
      System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, n, paramArrayOfByte2.length);
      i1 = n + paramArrayOfByte2.length;
      if ((paramArrayOfByte3 == null) || (paramArrayOfByte3.length != 16))
        break label285;
      System.arraycopy(paramArrayOfByte3, 0, arrayOfByte, i1, 16);
      i2 = i1 + 16;
      util.int8_to_buf(arrayOfByte, i2, util.get_os_type(0));
      int i3 = i2 + 1;
      util.int16_to_buf(arrayOfByte, i3, paramInt);
      int i4 = i3 + 2;
      util.int8_to_buf(arrayOfByte, i4, 8);
      i5 = i4 + 1;
      if ((paramArrayOfInt != null) && (paramArrayOfInt.length != 0))
        break label295;
      util.int16_to_buf(arrayOfByte, i5, 0);
      (i5 + 2);
    }
    while (true)
    {
      return get_request(paramLong1, paramBoolean, arrayOfByte);
      arrayOfByte = new byte[2 + (1 + (2 + (1 + (16 + (2 + (16 + paramArrayOfByte1.length) + 2 * paramArrayOfInt.length + paramArrayOfByte2.length)))))];
      break;
      label285: i2 = i1 + 16;
      break label168;
      label295: util.int16_to_buf(arrayOfByte, i5, paramArrayOfInt.length);
      int i6 = i5 + 2;
      for (int i7 = 0; i7 < paramArrayOfInt.length; i7++)
      {
        util.int16_to_buf(arrayOfByte, i6, paramArrayOfInt[i7]);
        i6 += 2;
      }
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
      int i7 = util.buf_to_int16(arrayOfByte, k);
      int i8 = k + 2;
      _status._msg = new byte[i7];
      System.arraycopy(arrayOfByte, i8, _status._msg, 0, i7);
      (i7 + 13);
      return _status._ret;
    }
    _status._time = (0xFFFFFFFF & util.buf_to_int32(arrayOfByte, k));
    int m = k + 4;
    int n = util.buf_to_int16(arrayOfByte, m);
    int i1 = m + 2;
    _status._app_name = new byte[n];
    System.arraycopy(arrayOfByte, i1, _status._app_name, 0, n);
    int i2 = n + 17;
    int i3 = util.buf_to_int16(arrayOfByte, i2);
    int i4 = i2 + 2;
    code2d_req_status localcode2d_req_status = _status;
    int[] arrayOfInt = { i3, 0 };
    localcode2d_req_status._data = ((byte[][])Array.newInstance(Byte.TYPE, arrayOfInt));
    for (int i5 = 0; ; i5++)
    {
      if (i5 >= i3)
        return _status._ret;
      int i6 = util.buf_to_int16(arrayOfByte, i4 + 2);
      _status._data[i5] = new byte[i6 + 4];
      System.arraycopy(arrayOfByte, i4, _status._data[i5], 0, i6 + 4);
      i4 += i6 + 4;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.code2d.verify_code
 * JD-Core Version:    0.6.0
 */