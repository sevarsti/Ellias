package oicq.wlogin_sdk.name_op;

import oicq.wlogin_sdk.tools.util;

public class name_unbind extends name_base
{
  public name_unbind()
  {
    this._cmd = 3;
  }

  public byte[] get_request(long paramLong1, long paramLong2, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    this._body_len = (13 + paramArrayOfByte2.length);
    byte[] arrayOfByte = new byte[this._body_len];
    util.int64_to_buf(arrayOfByte, 0, paramLong1);
    int i = 0 + 8;
    util.int64_to_buf32(arrayOfByte, i, paramLong2);
    int j = i + 4;
    util.int8_to_buf(arrayOfByte, j, paramArrayOfByte2.length);
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, j + 1, paramArrayOfByte2.length);
    (13 + paramArrayOfByte2.length);
    return get_request(arrayOfByte, paramArrayOfByte1);
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
 * Qualified Name:     oicq.wlogin_sdk.name_op.name_unbind
 * JD-Core Version:    0.6.0
 */