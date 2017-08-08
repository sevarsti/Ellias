package oicq.wlogin_sdk.name_op;

import oicq.wlogin_sdk.tools.util;

public class name_bind extends name_base
{
  public name_bind()
  {
    this._cmd = 2;
  }

  public byte[] get_request(long paramLong1, long paramLong2, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int[] paramArrayOfInt, byte[][] paramArrayOfByte)
  {
    this._body_len = (2 + (1 + (13 + paramArrayOfByte2.length) + paramArrayOfByte3.length));
    int i = 0;
    byte[] arrayOfByte;
    int i2;
    if (i >= paramArrayOfByte.length)
    {
      arrayOfByte = new byte[this._body_len];
      util.int64_to_buf(arrayOfByte, 0, paramLong1);
      int j = 0 + 8;
      util.int64_to_buf32(arrayOfByte, j, paramLong2);
      int k = j + 4;
      util.int8_to_buf(arrayOfByte, k, paramArrayOfByte2.length);
      System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, k + 1, paramArrayOfByte2.length);
      int m = 13 + paramArrayOfByte2.length;
      util.int8_to_buf(arrayOfByte, m, paramArrayOfByte3.length);
      int n = m + 1;
      System.arraycopy(paramArrayOfByte3, 0, arrayOfByte, n, paramArrayOfByte3.length);
      int i1 = n + paramArrayOfByte3.length;
      util.int16_to_buf(arrayOfByte, i1, paramArrayOfByte.length);
      i2 = i1 + 2;
    }
    for (int i3 = 0; ; i3++)
    {
      if (i3 >= paramArrayOfByte.length)
      {
        return get_request(arrayOfByte, paramArrayOfByte1);
        this._body_len += 4 + paramArrayOfByte[i].length;
        i++;
        break;
      }
      util.int16_to_buf(arrayOfByte, i2, paramArrayOfInt[i3]);
      int i4 = i2 + 2;
      util.int16_to_buf(arrayOfByte, i4, paramArrayOfByte[i3].length);
      int i5 = i4 + 2;
      System.arraycopy(paramArrayOfByte[i3], 0, arrayOfByte, i5, paramArrayOfByte[i3].length);
      i2 = i5 + paramArrayOfByte[i3].length;
    }
  }

  public int get_response(byte[] paramArrayOfByte, name_req_status paramname_req_status)
  {
    if (get_response(1, paramArrayOfByte, paramname_req_status) == null)
      return -1009;
    if (paramname_req_status._ret_code != 0)
      return paramname_req_status._ret_code;
    return paramname_req_status._ret_code;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.name_op.name_bind
 * JD-Core Version:    0.6.0
 */