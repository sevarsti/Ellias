package oicq.wlogin_sdk.register;

import oicq.wlogin_sdk.tools.util;

public class reg_request_resend_msg extends reg_request
{
  public reg_request_resend_msg()
  {
    this._cmd = 4;
  }

  public byte[] get_request(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    this._body_len = (1 + paramArrayOfByte1.length);
    byte[] arrayOfByte = new byte[this._body_len];
    util.int8_to_buf(arrayOfByte, 0, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, 0 + 1, paramArrayOfByte1.length);
    (1 + paramArrayOfByte1.length);
    return get_request(arrayOfByte);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.register.reg_request_resend_msg
 * JD-Core Version:    0.6.0
 */