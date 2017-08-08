package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

public class tlv_t144 extends tlv_t
{
  public int _t144_body_len = 0;

  public tlv_t144()
  {
    this._cmd = 324;
  }

  public byte[] get_tlv144(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4)
  {
    int i = 0;
    int j = 0;
    if (paramArrayOfByte1 != null)
    {
      int m = paramArrayOfByte1.length;
      i = 0;
      j = 0;
      if (m > 0)
      {
        i = 0 + paramArrayOfByte1.length;
        j = 0 + 1;
      }
    }
    if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length > 0))
    {
      i += paramArrayOfByte2.length;
      j++;
    }
    if ((paramArrayOfByte3 != null) && (paramArrayOfByte3.length > 0))
    {
      i += paramArrayOfByte3.length;
      j++;
    }
    byte[] arrayOfByte1 = new byte[i + 2];
    util.int16_to_buf(arrayOfByte1, 0, j);
    int k = 0 + 2;
    if ((paramArrayOfByte1 != null) && (paramArrayOfByte1.length > 0))
    {
      System.arraycopy(paramArrayOfByte1, 0, arrayOfByte1, k, paramArrayOfByte1.length);
      k = 2 + paramArrayOfByte1.length;
    }
    if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length > 0))
    {
      System.arraycopy(paramArrayOfByte2, 0, arrayOfByte1, k, paramArrayOfByte2.length);
      k += paramArrayOfByte2.length;
    }
    if ((paramArrayOfByte3 != null) && (paramArrayOfByte3.length > 0))
    {
      System.arraycopy(paramArrayOfByte3, 0, arrayOfByte1, k, paramArrayOfByte3.length);
      (k + paramArrayOfByte3.length);
    }
    byte[] arrayOfByte2 = cryptor.encrypt(arrayOfByte1, 0, arrayOfByte1.length, paramArrayOfByte4);
    this._t144_body_len = arrayOfByte2.length;
    fill_head(this._cmd);
    fill_body(arrayOfByte2, arrayOfByte2.length);
    set_length();
    return get_buf();
  }

  public byte[] get_tlv144(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5)
  {
    int i = 0;
    int j = 0;
    if (paramArrayOfByte1 != null)
    {
      int m = paramArrayOfByte1.length;
      i = 0;
      j = 0;
      if (m > 0)
      {
        i = 0 + paramArrayOfByte1.length;
        j = 0 + 1;
      }
    }
    if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length > 0))
    {
      i += paramArrayOfByte2.length;
      j++;
    }
    if ((paramArrayOfByte3 != null) && (paramArrayOfByte3.length > 0))
    {
      i += paramArrayOfByte3.length;
      j++;
    }
    if ((paramArrayOfByte4 != null) && (paramArrayOfByte4.length > 0))
    {
      i += paramArrayOfByte4.length;
      j++;
    }
    byte[] arrayOfByte1 = new byte[i + 2];
    util.int16_to_buf(arrayOfByte1, 0, j);
    int k = 0 + 2;
    if ((paramArrayOfByte1 != null) && (paramArrayOfByte1.length > 0))
    {
      System.arraycopy(paramArrayOfByte1, 0, arrayOfByte1, k, paramArrayOfByte1.length);
      k = 2 + paramArrayOfByte1.length;
    }
    if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length > 0))
    {
      System.arraycopy(paramArrayOfByte2, 0, arrayOfByte1, k, paramArrayOfByte2.length);
      k += paramArrayOfByte2.length;
    }
    if ((paramArrayOfByte3 != null) && (paramArrayOfByte3.length > 0))
    {
      System.arraycopy(paramArrayOfByte3, 0, arrayOfByte1, k, paramArrayOfByte3.length);
      k += paramArrayOfByte3.length;
    }
    if ((paramArrayOfByte4 != null) && (paramArrayOfByte4.length > 0))
    {
      System.arraycopy(paramArrayOfByte4, 0, arrayOfByte1, k, paramArrayOfByte4.length);
      (k + paramArrayOfByte4.length);
    }
    byte[] arrayOfByte2 = cryptor.encrypt(arrayOfByte1, 0, arrayOfByte1.length, paramArrayOfByte5);
    this._t144_body_len = arrayOfByte2.length;
    fill_head(this._cmd);
    fill_body(arrayOfByte2, arrayOfByte2.length);
    set_length();
    return get_buf();
  }

  public byte[] get_tlv144(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5, byte[] paramArrayOfByte6)
  {
    int i = 0;
    int j = 0;
    if (paramArrayOfByte1 != null)
    {
      int m = paramArrayOfByte1.length;
      i = 0;
      j = 0;
      if (m > 0)
      {
        i = 0 + paramArrayOfByte1.length;
        j = 0 + 1;
      }
    }
    if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length > 0))
    {
      i += paramArrayOfByte2.length;
      j++;
    }
    if ((paramArrayOfByte3 != null) && (paramArrayOfByte3.length > 0))
    {
      i += paramArrayOfByte3.length;
      j++;
    }
    if ((paramArrayOfByte4 != null) && (paramArrayOfByte4.length > 0))
    {
      i += paramArrayOfByte4.length;
      j++;
    }
    if ((paramArrayOfByte5 != null) && (paramArrayOfByte5.length > 0))
    {
      i += paramArrayOfByte5.length;
      j++;
    }
    byte[] arrayOfByte1 = new byte[i + 2];
    util.int16_to_buf(arrayOfByte1, 0, j);
    int k = 0 + 2;
    if ((paramArrayOfByte1 != null) && (paramArrayOfByte1.length > 0))
    {
      System.arraycopy(paramArrayOfByte1, 0, arrayOfByte1, k, paramArrayOfByte1.length);
      k = 2 + paramArrayOfByte1.length;
    }
    if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length > 0))
    {
      System.arraycopy(paramArrayOfByte2, 0, arrayOfByte1, k, paramArrayOfByte2.length);
      k += paramArrayOfByte2.length;
    }
    if ((paramArrayOfByte3 != null) && (paramArrayOfByte3.length > 0))
    {
      System.arraycopy(paramArrayOfByte3, 0, arrayOfByte1, k, paramArrayOfByte3.length);
      k += paramArrayOfByte3.length;
    }
    if ((paramArrayOfByte4 != null) && (paramArrayOfByte4.length > 0))
    {
      System.arraycopy(paramArrayOfByte4, 0, arrayOfByte1, k, paramArrayOfByte4.length);
      k += paramArrayOfByte4.length;
    }
    if ((paramArrayOfByte5 != null) && (paramArrayOfByte5.length > 0))
    {
      System.arraycopy(paramArrayOfByte5, 0, arrayOfByte1, k, paramArrayOfByte5.length);
      (k + paramArrayOfByte5.length);
    }
    byte[] arrayOfByte2 = cryptor.encrypt(arrayOfByte1, 0, arrayOfByte1.length, paramArrayOfByte6);
    this._t144_body_len = arrayOfByte2.length;
    fill_head(this._cmd);
    fill_body(arrayOfByte2, arrayOfByte2.length);
    set_length();
    return get_buf();
  }

  public byte[] get_tlv144(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5, byte[] paramArrayOfByte6, byte[] paramArrayOfByte7, byte[] paramArrayOfByte8)
  {
    int i = 0;
    int j = 0;
    if (paramArrayOfByte1 != null)
    {
      int m = paramArrayOfByte1.length;
      i = 0;
      j = 0;
      if (m > 0)
      {
        i = 0 + paramArrayOfByte1.length;
        j = 0 + 1;
      }
    }
    if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length > 0))
    {
      i += paramArrayOfByte2.length;
      j++;
    }
    if ((paramArrayOfByte3 != null) && (paramArrayOfByte3.length > 0))
    {
      i += paramArrayOfByte3.length;
      j++;
    }
    if ((paramArrayOfByte4 != null) && (paramArrayOfByte4.length > 0))
    {
      i += paramArrayOfByte4.length;
      j++;
    }
    if ((paramArrayOfByte5 != null) && (paramArrayOfByte5.length > 0))
    {
      i += paramArrayOfByte5.length;
      j++;
    }
    if ((paramArrayOfByte6 != null) && (paramArrayOfByte6.length > 0))
    {
      i += paramArrayOfByte6.length;
      j++;
    }
    if ((paramArrayOfByte7 != null) && (paramArrayOfByte7.length > 0))
    {
      i += paramArrayOfByte7.length;
      j++;
    }
    byte[] arrayOfByte1 = new byte[i + 2];
    util.int16_to_buf(arrayOfByte1, 0, j);
    int k = 0 + 2;
    if ((paramArrayOfByte1 != null) && (paramArrayOfByte1.length > 0))
    {
      System.arraycopy(paramArrayOfByte1, 0, arrayOfByte1, k, paramArrayOfByte1.length);
      k = 2 + paramArrayOfByte1.length;
    }
    if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length > 0))
    {
      System.arraycopy(paramArrayOfByte2, 0, arrayOfByte1, k, paramArrayOfByte2.length);
      k += paramArrayOfByte2.length;
    }
    if ((paramArrayOfByte3 != null) && (paramArrayOfByte3.length > 0))
    {
      System.arraycopy(paramArrayOfByte3, 0, arrayOfByte1, k, paramArrayOfByte3.length);
      k += paramArrayOfByte3.length;
    }
    if ((paramArrayOfByte4 != null) && (paramArrayOfByte4.length > 0))
    {
      System.arraycopy(paramArrayOfByte4, 0, arrayOfByte1, k, paramArrayOfByte4.length);
      k += paramArrayOfByte4.length;
    }
    if ((paramArrayOfByte5 != null) && (paramArrayOfByte5.length > 0))
    {
      System.arraycopy(paramArrayOfByte5, 0, arrayOfByte1, k, paramArrayOfByte5.length);
      k += paramArrayOfByte5.length;
    }
    if ((paramArrayOfByte6 != null) && (paramArrayOfByte6.length > 0))
    {
      System.arraycopy(paramArrayOfByte6, 0, arrayOfByte1, k, paramArrayOfByte6.length);
      k += paramArrayOfByte6.length;
    }
    if ((paramArrayOfByte7 != null) && (paramArrayOfByte7.length > 0))
    {
      System.arraycopy(paramArrayOfByte7, 0, arrayOfByte1, k, paramArrayOfByte7.length);
      (k + paramArrayOfByte7.length);
    }
    byte[] arrayOfByte2 = cryptor.encrypt(arrayOfByte1, 0, arrayOfByte1.length, paramArrayOfByte8);
    this._t144_body_len = arrayOfByte2.length;
    fill_head(this._cmd);
    fill_body(arrayOfByte2, arrayOfByte2.length);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t144
 * JD-Core Version:    0.6.0
 */