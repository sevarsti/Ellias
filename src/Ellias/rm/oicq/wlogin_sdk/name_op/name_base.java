package oicq.wlogin_sdk.name_op;

import oicq.wlogin_sdk.tools.util;

public class name_base
{
  public int _body_len = 0;
  public int _cmd = 0;
  public int _head_len = this._sms_head_len;
  public int _os_type = 5;
  public int _role = 16;
  public int _sms_head_len = 17;
  public int _sub_cmd = 0;
  public int _version = 0;
  public int _version1 = 0;

  public int get_cmd()
  {
    return this._cmd;
  }

  public byte[] get_request(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte = new byte[1 + this._head_len + paramArrayOfByte2.length + paramArrayOfByte1.length];
    util.int16_to_buf(arrayOfByte, 0, this._head_len + paramArrayOfByte2.length + paramArrayOfByte1.length);
    int i = 0 + 2;
    util.int16_to_buf(arrayOfByte, i, this._cmd);
    int j = i + 2;
    util.int16_to_buf(arrayOfByte, j, this._sub_cmd);
    int k = j + 2;
    util.int8_to_buf(arrayOfByte, k, this._version1);
    int m = k + 1;
    util.int8_to_buf(arrayOfByte, m, 0);
    int n = m + 1;
    util.int32_to_buf(arrayOfByte, n, 0);
    int i1 = n + 4;
    util.int16_to_buf(arrayOfByte, i1, 0);
    int i2 = i1 + 2;
    util.int8_to_buf(arrayOfByte, i2, 1);
    int i3 = i2 + 1;
    util.int16_to_buf(arrayOfByte, i3, paramArrayOfByte2.length);
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, i3 + 2, paramArrayOfByte2.length);
    int i4 = 17 + paramArrayOfByte2.length;
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, i4, paramArrayOfByte1.length);
    int i5 = i4 + paramArrayOfByte1.length;
    util.int8_to_buf(arrayOfByte, i5, 3);
    (i5 + 1);
    return arrayOfByte;
  }

  public byte[] get_response(int paramInt, byte[] paramArrayOfByte, name_req_status paramname_req_status)
  {
    int i = paramArrayOfByte.length;
    int j = util.buf_to_int16(paramArrayOfByte, 0);
    int k = 0 + 2;
    if (k >= i);
    int i3;
    do
    {
      do
      {
        int i1;
        do
        {
          int n;
          do
          {
            int m;
            do
            {
              return null;
              this._cmd = util.buf_to_int16(paramArrayOfByte, k);
              m = k + 2;
            }
            while (m >= i);
            this._sub_cmd = util.buf_to_int16(paramArrayOfByte, m);
            n = 1 + (m + 2);
          }
          while (n >= i);
          paramname_req_status._ret_code = util.buf_to_int8(paramArrayOfByte, n);
          i1 = 1 + (2 + (4 + (n + 1)));
        }
        while (i1 >= i);
        int i2 = util.buf_to_int16(paramArrayOfByte, i1);
        (i1 + 2);
        i3 = i2 + 17;
      }
      while (i3 >= i);
      this._sms_head_len = i3;
    }
    while (j + 1 != paramArrayOfByte.length);
    byte[] arrayOfByte = new byte[-1 + (paramArrayOfByte.length - i3)];
    System.arraycopy(paramArrayOfByte, i3, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.name_op.name_base
 * JD-Core Version:    0.6.0
 */