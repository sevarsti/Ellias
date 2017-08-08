package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.MD5;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

public class tlv_t106 extends tlv_t
{
  int _SSoVer = 1;
  int _TGTGTVer = 1;
  int _t106_body_len = 69;

  public tlv_t106()
  {
    this._cmd = 262;
    if (util.SSO_VERSION <= 2)
    {
      this._TGTGTVer = 1;
      this._t106_body_len = 69;
      return;
    }
    this._TGTGTVer = 2;
    this._t106_body_len = 90;
  }

  public byte[] get_tlv_106(long paramLong1, int paramInt1, long paramLong2, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, int paramInt3, byte[] paramArrayOfByte5)
  {
    if (util.SSO_VERSION <= 2)
    {
      byte[] arrayOfByte6 = new byte[this._t106_body_len];
      util.int16_to_buf(arrayOfByte6, 0, this._TGTGTVer);
      int i8 = 0 + 2;
      util.int32_to_buf(arrayOfByte6, i8, util.get_rand_32());
      int i9 = i8 + 4;
      util.int32_to_buf(arrayOfByte6, i9, this._SSoVer);
      int i10 = i9 + 4;
      util.int32_to_buf(arrayOfByte6, i10, (int)paramLong1);
      int i11 = i10 + 4;
      util.int32_to_buf(arrayOfByte6, i11, paramInt1);
      int i12 = i11 + 4;
      util.int64_to_buf(arrayOfByte6, i12, paramLong2);
      System.arraycopy(paramArrayOfByte1, 0, arrayOfByte6, i12 + 8, paramArrayOfByte1.length);
      int i13 = 26 + paramArrayOfByte1.length;
      System.arraycopy(paramArrayOfByte2, 0, arrayOfByte6, i13, paramArrayOfByte2.length);
      int i14 = i13 + paramArrayOfByte2.length;
      util.int8_to_buf(arrayOfByte6, i14, paramInt2);
      int i15 = i14 + 1;
      System.arraycopy(paramArrayOfByte3, 0, arrayOfByte6, i15, paramArrayOfByte3.length);
      int i16 = i15 + paramArrayOfByte3.length;
      System.arraycopy(paramArrayOfByte4, 0, arrayOfByte6, i16, paramArrayOfByte4.length);
      (i16 + paramArrayOfByte4.length);
      byte[] arrayOfByte7 = new byte[24];
      System.arraycopy(paramArrayOfByte3, 0, arrayOfByte7, 0, paramArrayOfByte3.length);
      util.int64_to_buf(arrayOfByte7, 16, paramLong2);
      byte[] arrayOfByte8 = MD5.toMD5Byte(arrayOfByte7);
      byte[] arrayOfByte9 = cryptor.encrypt(arrayOfByte6, 0, arrayOfByte6.length, arrayOfByte8);
      this._t106_body_len = arrayOfByte9.length;
      fill_head(this._cmd);
      fill_body(arrayOfByte9, this._t106_body_len);
      set_length();
      return get_buf();
    }
    byte[] arrayOfByte1 = new byte[this._t106_body_len];
    util.int16_to_buf(arrayOfByte1, 0, this._TGTGTVer);
    int i = 0 + 2;
    util.int32_to_buf(arrayOfByte1, i, util.get_rand_32());
    int j = i + 4;
    util.int32_to_buf(arrayOfByte1, j, this._SSoVer);
    int k = j + 4;
    util.int32_to_buf(arrayOfByte1, k, (int)paramLong1);
    int m = k + 4;
    util.int32_to_buf(arrayOfByte1, m, paramInt1);
    int n = m + 4;
    util.int64_to_buf(arrayOfByte1, n, paramLong2);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte1, n + 8, paramArrayOfByte1.length);
    int i1 = 26 + paramArrayOfByte1.length;
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte1, i1, paramArrayOfByte2.length);
    int i2 = i1 + paramArrayOfByte2.length;
    util.int8_to_buf(arrayOfByte1, i2, paramInt2);
    int i3 = i2 + 1;
    System.arraycopy(paramArrayOfByte3, 0, arrayOfByte1, i3, paramArrayOfByte3.length);
    int i4 = i3 + paramArrayOfByte3.length;
    System.arraycopy(paramArrayOfByte4, 0, arrayOfByte1, i4, paramArrayOfByte4.length);
    int i5 = i4 + paramArrayOfByte4.length;
    util.int32_to_buf(arrayOfByte1, i5, 0);
    int i6 = i5 + 4;
    util.int8_to_buf(arrayOfByte1, i6, paramInt3);
    int i7 = i6 + 1;
    if ((paramArrayOfByte5 == null) || (paramArrayOfByte5.length <= 0))
    {
      byte[] arrayOfByte2 = new byte[16];
      util.int32_to_buf(arrayOfByte2, 0, util.get_rand_32());
      util.int32_to_buf(arrayOfByte2, 4, util.get_rand_32());
      util.int32_to_buf(arrayOfByte2, 8, util.get_rand_32());
      util.int32_to_buf(arrayOfByte2, 12, util.get_rand_32());
      (i7 + arrayOfByte2.length);
    }
    while (true)
    {
      byte[] arrayOfByte3 = new byte[24];
      System.arraycopy(paramArrayOfByte3, 0, arrayOfByte3, 0, paramArrayOfByte3.length);
      util.int64_to_buf(arrayOfByte3, 16, paramLong2);
      byte[] arrayOfByte4 = MD5.toMD5Byte(arrayOfByte3);
      byte[] arrayOfByte5 = cryptor.encrypt(arrayOfByte1, 0, arrayOfByte1.length, arrayOfByte4);
      this._t106_body_len = arrayOfByte5.length;
      fill_head(this._cmd);
      fill_body(arrayOfByte5, this._t106_body_len);
      set_length();
      return get_buf();
      System.arraycopy(paramArrayOfByte5, 0, arrayOfByte1, i7, paramArrayOfByte5.length);
      (i7 + paramArrayOfByte5.length);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t106
 * JD-Core Version:    0.6.0
 */