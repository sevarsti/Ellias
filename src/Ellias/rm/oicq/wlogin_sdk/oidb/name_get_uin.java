package oicq.wlogin_sdk.oidb;

import java.util.ArrayList;
import java.util.List;
import oicq.wlogin_sdk.tools.util;

public class name_get_uin extends oidb_base
{
  public List<NameUinInfo> nameinfo = new ArrayList();

  public name_get_uin(long paramLong)
  {
    this._uin = paramLong;
    this._type = 1;
    this._cmd = 1199;
  }

  public byte[] get_request(long paramLong, byte[] paramArrayOfByte, byte[][] paramArrayOfByte1)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte1 == null))
      return null;
    this._body_len = 6;
    int i = 0;
    byte[] arrayOfByte;
    int n;
    if (i >= paramArrayOfByte1.length)
    {
      arrayOfByte = new byte[this._body_len];
      util.int8_to_buf(arrayOfByte, 0, 79);
      int j = 0 + 1;
      util.int16_to_buf(arrayOfByte, j, -1);
      int k = j + 2;
      util.int16_to_buf(arrayOfByte, k, -1);
      int m = k + 2;
      util.int8_to_buf(arrayOfByte, m, paramArrayOfByte1.length);
      n = m + 1;
    }
    for (int i1 = 0; ; i1++)
    {
      if (i1 >= paramArrayOfByte1.length)
      {
        return encode_request(paramLong, arrayOfByte, paramArrayOfByte);
        this._body_len += 1 + paramArrayOfByte1[i].length;
        i++;
        break;
      }
      util.int8_to_buf(arrayOfByte, n, paramArrayOfByte1[i1].length);
      int i2 = n + 1;
      System.arraycopy(paramArrayOfByte1[i1], 0, arrayOfByte, i2, paramArrayOfByte1[i1].length);
      n = i2 + paramArrayOfByte1[i1].length;
    }
  }

  public int get_response(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte1 = decode_response(paramArrayOfByte);
    if (arrayOfByte1 == null)
      return -1009;
    if (this._result != 0)
      return this._result;
    int i = arrayOfByte1.length;
    if (3 > i)
      return -1009;
    if (util.buf_to_int8(arrayOfByte1, 0) != 79)
      return -1009;
    int j = 0 + 1;
    util.buf_to_int8(arrayOfByte1, j);
    int k = j + 1;
    int m = util.buf_to_int8(arrayOfByte1, k);
    int n = k + 1;
    this.nameinfo.clear();
    for (int i1 = 0; ; i1++)
    {
      if (i1 >= m)
        return 0;
      if (n + 13 > i)
        return -1009;
      long l = util.buf_to_int64(arrayOfByte1, n);
      int i2 = n + 8;
      int i3 = util.buf_to_int16(arrayOfByte1, i2);
      int i4 = i2 + 2;
      int i5 = util.buf_to_int16(arrayOfByte1, i4);
      int i6 = i4 + 2;
      int i7 = util.buf_to_int8(arrayOfByte1, i6);
      int i8 = i6 + 1;
      if (i8 + i7 > i)
        return -1009;
      byte[] arrayOfByte2 = new byte[i7];
      System.arraycopy(arrayOfByte1, i8, arrayOfByte2, 0, i7);
      n = i8 + i7;
      this.nameinfo.add(new NameUinInfo(l, i3, i5, new String(arrayOfByte2)));
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.oidb.name_get_uin
 * JD-Core Version:    0.6.0
 */