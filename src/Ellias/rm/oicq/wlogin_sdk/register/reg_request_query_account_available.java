package oicq.wlogin_sdk.register;

import oicq.wlogin_sdk.tools.util;

public class reg_request_query_account_available extends reg_request
{
  public reg_request_query_account_available()
  {
    this._cmd = 7;
  }

  public byte[] get_request(int paramInt, byte[] paramArrayOfByte, long paramLong)
  {
    this._body_len = (4 + (2 + paramArrayOfByte.length));
    byte[] arrayOfByte = new byte[this._body_len];
    util.int8_to_buf(arrayOfByte, 0, paramInt);
    int i = 0 + 1;
    util.int8_to_buf(arrayOfByte, i, paramArrayOfByte.length);
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, i + 1, paramArrayOfByte.length);
    int j = 2 + paramArrayOfByte.length;
    util.int64_to_buf32(arrayOfByte, j, paramLong);
    (j + 4);
    return get_request(arrayOfByte);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.register.reg_request_query_account_available
 * JD-Core Version:    0.6.0
 */