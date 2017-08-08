package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t1 extends tlv_t
{
  byte[] IP_KEY = new byte[2];
  int _ip_len = 4;
  int _ip_pos = 14;
  int _ip_ver = 1;
  int _t1_body_len = 20;

  public tlv_t1()
  {
    this._cmd = 1;
  }

  public byte[] get_ip()
  {
    byte[] arrayOfByte = new byte[this._ip_len];
    System.arraycopy(this._buf, this._ip_pos, arrayOfByte, 0, this._ip_len);
    return arrayOfByte;
  }

  public byte[] get_tlv_1(long paramLong, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[this._t1_body_len];
    util.int16_to_buf(arrayOfByte, 0, this._ip_ver);
    int i = 0 + 2;
    util.int32_to_buf(arrayOfByte, i, util.get_rand_32());
    int j = i + 4;
    util.int32_to_buf(arrayOfByte, j, (int)paramLong);
    int k = j + 4;
    util.int64_to_buf32(arrayOfByte, k, util.get_server_cur_time());
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, k + 4, paramArrayOfByte.length);
    int m = 14 + paramArrayOfByte.length;
    util.int16_to_buf(arrayOfByte, m, 0);
    (m + 2);
    fill_head(this._cmd);
    fill_body(arrayOfByte, this._t1_body_len);
    set_length();
    return get_buf();
  }

  public Boolean verify()
  {
    if (this._body_len < 20)
      return Boolean.valueOf(false);
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t1
 * JD-Core Version:    0.6.0
 */