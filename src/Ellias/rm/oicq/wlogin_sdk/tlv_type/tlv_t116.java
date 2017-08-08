package oicq.wlogin_sdk.tlv_type;

import J;
import oicq.wlogin_sdk.tools.util;

public class tlv_t116 extends tlv_t
{
  int _t116_body_len = 0;
  int _ver = 0;

  public tlv_t116()
  {
    this._cmd = 278;
  }

  public byte[] get_tlv_116(int paramInt1, int paramInt2, long[] paramArrayOfLong)
  {
    long[] arrayOfLong;
    byte[] arrayOfByte;
    int m;
    if (paramArrayOfLong == null)
    {
      arrayOfLong = new long[0];
      this._t116_body_len = (10 + 4 * arrayOfLong.length);
      arrayOfByte = new byte[this._t116_body_len];
      util.int8_to_buf(arrayOfByte, 0, this._ver);
      int i = 0 + 1;
      util.int32_to_buf(arrayOfByte, i, paramInt1);
      int j = i + 4;
      util.int32_to_buf(arrayOfByte, j, paramInt2);
      int k = j + 4;
      util.int8_to_buf(arrayOfByte, k, arrayOfLong.length);
      m = k + 1;
    }
    for (int n = 0; ; n++)
    {
      if (n >= arrayOfLong.length)
      {
        fill_head(this._cmd);
        fill_body(arrayOfByte, this._t116_body_len);
        set_length();
        return get_buf();
        arrayOfLong = (long[])paramArrayOfLong.clone();
        break;
      }
      util.int32_to_buf(arrayOfByte, m, (int)arrayOfLong[n]);
      m += 4;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t116
 * JD-Core Version:    0.6.0
 */