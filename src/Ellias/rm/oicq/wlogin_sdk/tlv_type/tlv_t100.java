package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t100 extends tlv_t
{
  int _db_buf_ver = 1;
  int _sso_ver = util.SSO_VERSION;
  int _t100_body_len = 22;

  public tlv_t100()
  {
    this._cmd = 256;
  }

  public byte[] get_tlv_100(long paramLong1, long paramLong2, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[this._t100_body_len];
    util.int16_to_buf(arrayOfByte, 0, this._db_buf_ver);
    int i = 0 + 2;
    util.int32_to_buf(arrayOfByte, i, this._sso_ver);
    int j = i + 4;
    util.int32_to_buf(arrayOfByte, j, (int)paramLong1);
    int k = j + 4;
    util.int32_to_buf(arrayOfByte, k, (int)paramLong2);
    int m = k + 4;
    util.int32_to_buf(arrayOfByte, m, paramInt1);
    int n = m + 4;
    util.int32_to_buf(arrayOfByte, n, paramInt2);
    (n + 4);
    fill_head(this._cmd);
    fill_body(arrayOfByte, this._t100_body_len);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t100
 * JD-Core Version:    0.6.0
 */