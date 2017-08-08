package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t18 extends tlv_t
{
  int _ping_version = 1;
  int _sso_version = 1536;
  int _t18_body_len = 22;

  public tlv_t18()
  {
    this._cmd = 24;
  }

  public byte[] get_tlv_18(long paramLong1, int paramInt1, long paramLong2, int paramInt2)
  {
    byte[] arrayOfByte = new byte[this._t18_body_len];
    util.int16_to_buf(arrayOfByte, 0, this._ping_version);
    int i = 0 + 2;
    util.int32_to_buf(arrayOfByte, i, this._sso_version);
    int j = i + 4;
    util.int32_to_buf(arrayOfByte, j, (int)paramLong1);
    int k = j + 4;
    util.int32_to_buf(arrayOfByte, k, paramInt1);
    int m = k + 4;
    util.int32_to_buf(arrayOfByte, m, (int)paramLong2);
    int n = m + 4;
    util.int16_to_buf(arrayOfByte, n, paramInt2);
    int i1 = n + 2;
    util.int16_to_buf(arrayOfByte, i1, 0);
    (i1 + 2);
    fill_head(this._cmd);
    fill_body(arrayOfByte, this._t18_body_len);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t18
 * JD-Core Version:    0.6.0
 */