package oicq.wlogin_sdk.register;

import oicq.wlogin_sdk.tools.util;

public class reg_request_submit_msg_chk extends reg_request
{
  public reg_request_submit_msg_chk()
  {
    this._cmd = 5;
  }

  public byte[] get_request(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte1 = get_encrypt_token(paramArrayOfByte1, paramArrayOfByte2);
    this._body_len = (1 + (1 + paramArrayOfByte1.length) + arrayOfByte1.length);
    byte[] arrayOfByte2 = new byte[this._body_len];
    util.int8_to_buf(arrayOfByte2, 0, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte2, 0 + 1, paramArrayOfByte1.length);
    int i = 1 + paramArrayOfByte1.length;
    util.int8_to_buf(arrayOfByte2, i, arrayOfByte1.length);
    int j = i + 1;
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, j, arrayOfByte1.length);
    (j + arrayOfByte1.length);
    return get_request(arrayOfByte2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.register.reg_request_submit_msg_chk
 * JD-Core Version:    0.6.0
 */