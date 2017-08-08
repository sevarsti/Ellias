package oicq.wlogin_sdk.name_op;

import oicq.wlogin_sdk.tools.util;

public class name_query extends name_base
{
  public name_query()
  {
    this._cmd = 1;
  }

  public byte[] get_request(long paramLong1, long paramLong2, byte[] paramArrayOfByte, int[] paramArrayOfInt)
  {
    this._body_len = (14 + 2 * paramArrayOfInt.length);
    byte[] arrayOfByte = new byte[this._body_len];
    util.int64_to_buf(arrayOfByte, 0, paramLong1);
    int i = 0 + 8;
    util.int64_to_buf32(arrayOfByte, i, paramLong2);
    int j = i + 4;
    util.int16_to_buf(arrayOfByte, j, paramArrayOfInt.length);
    int k = j + 2;
    for (int m = 0; ; m++)
    {
      if (m >= paramArrayOfInt.length)
        return get_request(arrayOfByte, paramArrayOfByte);
      util.int16_to_buf(arrayOfByte, k, paramArrayOfInt[m]);
      k += 2;
    }
  }

  public int get_response(byte[] paramArrayOfByte, name_req_status paramname_req_status)
  {
    byte[] arrayOfByte1 = get_response(1, paramArrayOfByte, paramname_req_status);
    if (arrayOfByte1 == null);
    int i;
    int j;
    do
    {
      do
      {
        return -1009;
        if (paramname_req_status._ret_code != 0)
          return paramname_req_status._ret_code;
        i = arrayOfByte1.length;
      }
      while (i >= 0);
      paramname_req_status._uin = util.buf_to_int64(arrayOfByte1, 0);
      j = 4 + (0 + 8);
    }
    while (j >= i);
    int k = util.buf_to_int8(arrayOfByte1, j);
    int m = j + 1;
    paramname_req_status._mobile = new String[k];
    for (int n = 0; ; n++)
    {
      if (n >= k)
        return paramname_req_status._ret_code;
      if (m >= i)
        break;
      int i1 = util.buf_to_int8(arrayOfByte1, m);
      int i2 = m + 1;
      if (i2 >= i)
        break;
      byte[] arrayOfByte2 = new byte[i1];
      System.arraycopy(arrayOfByte1, i2, arrayOfByte2, 0, arrayOfByte2.length);
      m = i2 + arrayOfByte2.length;
      paramname_req_status._mobile[n] = new String(arrayOfByte2);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.name_op.name_query
 * JD-Core Version:    0.6.0
 */