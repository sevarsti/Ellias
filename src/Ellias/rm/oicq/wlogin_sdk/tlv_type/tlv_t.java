package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

public class tlv_t
{
  int _body_len = 0;
  byte[] _buf = new byte[this._max];
  int _cmd = 0;
  int _head_len = 4;
  int _max = 128;
  int _pos = 0;
  int _type = 0;

  public void fill_body(byte[] paramArrayOfByte, int paramInt)
  {
    if (paramInt > this._max - this._head_len)
    {
      this._max = (64 + (paramInt + this._head_len));
      byte[] arrayOfByte = new byte[this._max];
      System.arraycopy(this._buf, 0, arrayOfByte, 0, this._pos);
      this._buf = arrayOfByte;
    }
    this._body_len = paramInt;
    System.arraycopy(paramArrayOfByte, 0, this._buf, this._pos, paramInt);
    this._pos = (paramInt + this._pos);
  }

  public void fill_head(int paramInt)
  {
    util.int16_to_buf(this._buf, this._pos, paramInt);
    this._pos = (2 + this._pos);
    util.int16_to_buf(this._buf, this._pos, 0);
    this._pos = (2 + this._pos);
  }

  public byte[] get_buf()
  {
    byte[] arrayOfByte = new byte[this._pos];
    System.arraycopy(this._buf, 0, arrayOfByte, 0, this._pos);
    return arrayOfByte;
  }

  public byte[] get_data()
  {
    byte[] arrayOfByte = new byte[this._body_len];
    System.arraycopy(this._buf, this._head_len, arrayOfByte, 0, this._body_len);
    return arrayOfByte;
  }

  public int get_data_len()
  {
    return this._body_len;
  }

  public int get_sizeof()
  {
    return this._pos;
  }

  int get_tlv(byte[] paramArrayOfByte, int paramInt)
  {
    if (this._head_len >= paramInt);
    do
    {
      return -1;
      this._body_len = util.buf_to_int16(paramArrayOfByte, 2);
    }
    while (this._head_len + this._body_len > paramInt);
    set_buf(paramArrayOfByte, this._head_len + this._body_len);
    if (!verify().booleanValue())
      return -1005;
    return 0;
  }

  public int get_tlv(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = search_tlv(paramArrayOfByte, paramInt1, paramInt2, this._cmd);
    if (i < 0);
    int j;
    do
    {
      do
      {
        return -1;
        j = paramInt2 - (i - paramInt1);
      }
      while (this._head_len >= j);
      this._body_len = util.buf_to_int16(paramArrayOfByte, i + 2);
    }
    while (this._head_len + this._body_len > j);
    set_buf(paramArrayOfByte, i, this._head_len + this._body_len);
    if (!verify().booleanValue())
      return -1005;
    return i + this._head_len + this._body_len;
  }

  public int get_tlv(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2)
  {
    int i = search_tlv(paramArrayOfByte1, paramInt1, paramInt2, this._cmd);
    if (i < 0)
      return -1;
    int j = paramInt2 - (i - paramInt1);
    byte[] arrayOfByte = new byte[j];
    System.arraycopy(paramArrayOfByte1, i, arrayOfByte, 0, j);
    return get_tlv(arrayOfByte, j, paramArrayOfByte2);
  }

  int get_tlv(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2)
  {
    if (this._head_len >= paramInt);
    do
    {
      return -1;
      this._body_len = util.buf_to_int16(paramArrayOfByte1, 2);
    }
    while (this._head_len + this._body_len > paramInt);
    byte[] arrayOfByte = cryptor.decrypt(paramArrayOfByte1, this._head_len, this._body_len, paramArrayOfByte2);
    if (arrayOfByte == null)
      return -1015;
    if (this._head_len + arrayOfByte.length > this._max)
    {
      this._max = (this._head_len + arrayOfByte.length);
      this._buf = new byte[this._max];
    }
    this._pos = 0;
    System.arraycopy(paramArrayOfByte1, 0, this._buf, 0, this._head_len);
    this._pos += this._head_len;
    System.arraycopy(arrayOfByte, 0, this._buf, this._pos, arrayOfByte.length);
    this._pos += arrayOfByte.length;
    this._body_len = arrayOfByte.length;
    if (!verify().booleanValue())
      return -1005;
    return 0;
  }

  public int get_type()
  {
    return this._cmd;
  }

  int search_tlv(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramArrayOfByte.length;
    while (true)
    {
      if (paramInt1 >= i);
      int j;
      do
      {
        do
          return -1;
        while (paramInt1 + 2 > i);
        if (util.buf_to_int16(paramArrayOfByte, paramInt1) == paramInt3)
          return paramInt1;
        j = paramInt1 + 2;
      }
      while (j + 2 > i);
      paramInt1 = j + (2 + util.buf_to_int16(paramArrayOfByte, j));
    }
  }

  public void set_buf(byte[] paramArrayOfByte, int paramInt)
  {
    if (paramInt > this._max)
    {
      this._max = (paramInt + 128);
      this._buf = new byte[this._max];
    }
    this._pos = paramInt;
    System.arraycopy(paramArrayOfByte, 0, this._buf, 0, paramInt);
    this._cmd = util.buf_to_int16(paramArrayOfByte, 0);
    this._body_len = (paramInt - this._head_len);
  }

  public void set_buf(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramInt2 > this._max)
    {
      this._max = (paramInt2 + 128);
      this._buf = new byte[this._max];
    }
    this._pos = paramInt2;
    System.arraycopy(paramArrayOfByte, paramInt1, this._buf, 0, paramInt2);
    this._cmd = util.buf_to_int16(paramArrayOfByte, paramInt1);
    this._body_len = (paramInt2 - this._head_len);
  }

  public void set_buf(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt2 > this._max)
    {
      this._max = (paramInt2 + 128);
      this._buf = new byte[this._max];
    }
    this._pos = paramInt2;
    System.arraycopy(paramArrayOfByte, paramInt1, this._buf, 0, paramInt2);
    this._cmd = paramInt3;
    this._body_len = paramInt4;
  }

  public void set_data(byte[] paramArrayOfByte, int paramInt)
  {
    if (paramInt + this._head_len > this._max)
    {
      this._max = (128 + (paramInt + this._head_len));
      byte[] arrayOfByte = new byte[this._max];
      System.arraycopy(this._buf, 0, arrayOfByte, 0, this._head_len);
      this._buf = arrayOfByte;
    }
    this._pos = (paramInt + this._head_len);
    System.arraycopy(paramArrayOfByte, 0, this._buf, this._head_len, paramInt);
    this._body_len = paramInt;
    util.int16_to_buf(this._buf, 0, this._cmd);
    util.int16_to_buf(this._buf, 2, this._body_len);
  }

  public void set_length()
  {
    util.int16_to_buf(this._buf, 2, this._pos - this._head_len);
  }

  public String toString()
  {
    String str = "";
    for (int i = 0; ; i++)
    {
      if (i >= this._pos)
        return str;
      str = new StringBuilder(String.valueOf(str)).append(Integer.toHexString(0xF & this._buf[i] >> 4)).toString() + Integer.toHexString(0xF & this._buf[i]);
    }
  }

  public Boolean verify()
  {
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t
 * JD-Core Version:    0.6.0
 */