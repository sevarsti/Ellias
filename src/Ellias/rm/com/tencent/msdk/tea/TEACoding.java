package com.tencent.msdk.tea;

import com.tencent.msdk.tools.HexUtil;
import com.tencent.msdk.tools.Logger;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

public class TEACoding
{
  private static Random random = new Random();
  public static final String somStr = "msdkmsdkmsdkmsdk";
  private int contextStart;
  private int crypt;
  private boolean header;
  private byte[] key;
  private byte[] out;
  private int padding;
  private byte[] plain;
  private int pos;
  private int preCrypt;
  private byte[] prePlain;

  public TEACoding(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length != 16))
      throw new IllegalArgumentException("Key length must be 16!");
    this.header = true;
    this.key = paramArrayOfByte;
  }

  private byte[] decipher(byte[] paramArrayOfByte)
  {
    return decipher(paramArrayOfByte, 0);
  }

  private byte[] decipher(byte[] paramArrayOfByte, int paramInt)
  {
    long l1;
    long l2;
    long l3;
    long l4;
    long l5;
    long l6;
    long l7;
    long l8;
    int i;
    try
    {
      l1 = getUnsignedInt(paramArrayOfByte, paramInt, 4);
      l2 = getUnsignedInt(paramArrayOfByte, paramInt + 4, 4);
      l3 = getUnsignedInt(this.key, 0, 4);
      l4 = getUnsignedInt(this.key, 4, 4);
      l5 = getUnsignedInt(this.key, 8, 4);
      l6 = getUnsignedInt(this.key, 12, 4);
      l7 = 0xE3779B90 & 0xFFFFFFFF;
      l8 = 0x9E3779B9 & 0xFFFFFFFF;
      i = 16;
      break label145;
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(8);
      DataOutputStream localDataOutputStream = new DataOutputStream(localByteArrayOutputStream);
      localDataOutputStream.writeInt((int)l1);
      localDataOutputStream.writeInt((int)l2);
      localDataOutputStream.close();
      byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      return null;
    }
    while (true)
    {
      label145: int j = i - 1;
      if (i <= 0)
        break;
      l2 = 0xFFFFFFFF & l2 - (l5 + (l1 << 4) ^ l1 + l7 ^ l6 + (l1 >>> 5));
      l1 = 0xFFFFFFFF & l1 - (l3 + (l2 << 4) ^ l2 + l7 ^ l4 + (l2 >>> 5));
      l7 = 0xFFFFFFFF & l7 - l8;
      i = j;
    }
  }

  private byte[] decode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.preCrypt = 0;
    this.crypt = 0;
    byte[] arrayOfByte = new byte[paramInt1 + 8];
    if ((paramInt2 % 8 != 0) || (paramInt2 < 16));
    int i;
    do
    {
      do
      {
        return null;
        this.prePlain = decipher(paramArrayOfByte, paramInt1);
      }
      while (this.prePlain == null);
      this.pos = (0x7 & this.prePlain[0]);
      i = -10 + (paramInt2 - this.pos);
    }
    while (i < 0);
    for (int j = paramInt1; j < arrayOfByte.length; j++)
      arrayOfByte[j] = 0;
    this.out = new byte[i];
    this.preCrypt = 0;
    this.crypt = 8;
    this.contextStart = 8;
    this.pos = (1 + this.pos);
    this.padding = 1;
    while (this.padding <= 2)
    {
      if (this.pos < 8)
      {
        this.pos = (1 + this.pos);
        this.padding = (1 + this.padding);
      }
      if (this.pos != 8)
        continue;
      arrayOfByte = paramArrayOfByte;
      if (!decrypt8Bytes(paramArrayOfByte, paramInt1, paramInt2))
        return null;
    }
    int k = 0;
    while (i != 0)
    {
      if (this.pos < 8)
      {
        this.out[k] = (byte)(arrayOfByte[(paramInt1 + this.preCrypt + this.pos)] ^ this.prePlain[this.pos]);
        k++;
        i--;
        this.pos = (1 + this.pos);
      }
      if (this.pos != 8)
        continue;
      arrayOfByte = paramArrayOfByte;
      this.preCrypt = (-8 + this.crypt);
      if (!decrypt8Bytes(paramArrayOfByte, paramInt1, paramInt2))
        return null;
    }
    for (this.padding = 1; ; this.padding = (1 + this.padding))
    {
      if (this.padding >= 8)
        break label406;
      if (this.pos < 8)
      {
        if ((arrayOfByte[(paramInt1 + this.preCrypt + this.pos)] ^ this.prePlain[this.pos]) != 0)
          break;
        this.pos = (1 + this.pos);
      }
      if (this.pos != 8)
        continue;
      arrayOfByte = paramArrayOfByte;
      this.preCrypt = this.crypt;
      if (!decrypt8Bytes(paramArrayOfByte, paramInt1, paramInt2))
        break;
    }
    label406: return this.out;
  }

  private boolean decrypt8Bytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    for (this.pos = 0; this.pos < 8; this.pos = (1 + this.pos))
    {
      if (this.contextStart + this.pos >= paramInt2)
        return true;
      byte[] arrayOfByte = this.prePlain;
      int i = this.pos;
      arrayOfByte[i] = (byte)(arrayOfByte[i] ^ paramArrayOfByte[(paramInt1 + this.crypt + this.pos)]);
    }
    this.prePlain = decipher(this.prePlain);
    if (this.prePlain == null)
      return false;
    this.contextStart = (8 + this.contextStart);
    this.crypt = (8 + this.crypt);
    this.pos = 0;
    return true;
  }

  private byte[] encipher(byte[] paramArrayOfByte)
  {
    long l1;
    long l2;
    long l3;
    long l4;
    long l5;
    long l6;
    long l7;
    long l8;
    int i;
    try
    {
      l1 = getUnsignedInt(paramArrayOfByte, 0, 4);
      l2 = getUnsignedInt(paramArrayOfByte, 4, 4);
      l3 = getUnsignedInt(this.key, 0, 4);
      l4 = getUnsignedInt(this.key, 4, 4);
      l5 = getUnsignedInt(this.key, 8, 4);
      l6 = getUnsignedInt(this.key, 12, 4);
      l7 = 0L;
      l8 = 0x9E3779B9 & 0xFFFFFFFF;
      i = 16;
      break label135;
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(8);
      DataOutputStream localDataOutputStream = new DataOutputStream(localByteArrayOutputStream);
      localDataOutputStream.writeInt((int)l1);
      localDataOutputStream.writeInt((int)l2);
      localDataOutputStream.close();
      byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      return null;
    }
    while (true)
    {
      label135: int j = i - 1;
      if (i <= 0)
        break;
      l7 = 0xFFFFFFFF & l7 + l8;
      l1 = 0xFFFFFFFF & l1 + (l3 + (l2 << 4) ^ l2 + l7 ^ l4 + (l2 >>> 5));
      l2 = 0xFFFFFFFF & l2 + (l5 + (l1 << 4) ^ l1 + l7 ^ l6 + (l1 >>> 5));
      i = j;
    }
  }

  private byte[] encode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.plain = new byte[8];
    this.prePlain = new byte[8];
    this.pos = 1;
    this.padding = 0;
    this.preCrypt = 0;
    this.crypt = 0;
    this.header = true;
    this.pos = ((paramInt2 + 10) % 8);
    if (this.pos != 0)
      this.pos = (8 - this.pos);
    this.out = new byte[10 + (paramInt2 + this.pos)];
    this.plain[0] = (byte)(0xF8 & rand() | this.pos);
    for (int i = 1; i <= this.pos; i++)
      this.plain[i] = (byte)(0xFF & rand());
    this.pos = (1 + this.pos);
    for (int j = 0; j < 8; j++)
      this.prePlain[j] = 0;
    this.padding = 1;
    while (this.padding <= 2)
    {
      if (this.pos < 8)
      {
        byte[] arrayOfByte3 = this.plain;
        int i2 = this.pos;
        this.pos = (i2 + 1);
        arrayOfByte3[i2] = (byte)(0xFF & rand());
        this.padding = (1 + this.padding);
      }
      if (this.pos != 8)
        continue;
      encrypt8Bytes();
    }
    int k = paramInt1;
    int n;
    if (paramInt2 > 0)
    {
      if (this.pos >= 8)
        break label416;
      byte[] arrayOfByte2 = this.plain;
      int i1 = this.pos;
      this.pos = (i1 + 1);
      n = k + 1;
      arrayOfByte2[i1] = paramArrayOfByte[k];
      paramInt2--;
    }
    while (true)
    {
      if (this.pos == 8)
      {
        encrypt8Bytes();
        k = n;
        break;
        this.padding = 1;
        while (this.padding <= 7)
        {
          if (this.pos < 8)
          {
            byte[] arrayOfByte1 = this.plain;
            int m = this.pos;
            this.pos = (m + 1);
            arrayOfByte1[m] = 0;
            this.padding = (1 + this.padding);
          }
          if (this.pos != 8)
            continue;
          encrypt8Bytes();
        }
        return this.out;
      }
      k = n;
      break;
      label416: n = k;
    }
  }

  private void encrypt8Bytes()
  {
    this.pos = 0;
    if (this.pos < 8)
    {
      if (this.header)
      {
        byte[] arrayOfByte3 = this.plain;
        int k = this.pos;
        arrayOfByte3[k] = (byte)(arrayOfByte3[k] ^ this.prePlain[this.pos]);
      }
      while (true)
      {
        this.pos = (1 + this.pos);
        break;
        byte[] arrayOfByte2 = this.plain;
        int j = this.pos;
        arrayOfByte2[j] = (byte)(arrayOfByte2[j] ^ this.out[(this.preCrypt + this.pos)]);
      }
    }
    System.arraycopy(encipher(this.plain), 0, this.out, this.crypt, 8);
    for (this.pos = 0; this.pos < 8; this.pos = (1 + this.pos))
    {
      byte[] arrayOfByte1 = this.out;
      int i = this.crypt + this.pos;
      arrayOfByte1[i] = (byte)(arrayOfByte1[i] ^ this.prePlain[this.pos]);
    }
    System.arraycopy(this.plain, 0, this.prePlain, 0, 8);
    this.preCrypt = this.crypt;
    this.crypt = (8 + this.crypt);
    this.pos = 0;
    this.header = false;
  }

  public static long getUnsignedInt(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    long l = 0L;
    if (paramInt2 > 8);
    for (int i = paramInt1 + 8; ; i = paramInt1 + paramInt2)
      for (int j = paramInt1; j < i; j++)
        l = l << 8 | 0xFF & paramArrayOfByte[j];
    return 0xFFFFFFFF & l | l >>> 32;
  }

  private int rand()
  {
    return random.nextInt();
  }

  public static void test()
  {
    TEACoding localTEACoding = new TEACoding("msdkmsdkmsdkmsdk".getBytes());
    String str = localTEACoding.encode2HexStr("123456".getBytes());
    Logger.d("test +++++++++" + str);
    Logger.d("test +++++++++" + new String(localTEACoding.decodeFromHexStr(str)));
  }

  public byte[] decode(byte[] paramArrayOfByte)
  {
    return decode(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public byte[] decodeFromBase64Str(String paramString)
  {
    return decode(Base64.decode(paramString, 0));
  }

  public byte[] decodeFromHexStr(String paramString)
  {
    return decode(HexUtil.hexStr2Bytes(paramString));
  }

  public byte[] encode(byte[] paramArrayOfByte)
  {
    return encode(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public String encode2HexBase64(byte[] paramArrayOfByte)
  {
    return Base64.encodeToString(encode(paramArrayOfByte), 0);
  }

  public String encode2HexStr(byte[] paramArrayOfByte)
  {
    return HexUtil.bytes2HexStr(encode(paramArrayOfByte));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tea.TEACoding
 * JD-Core Version:    0.6.0
 */