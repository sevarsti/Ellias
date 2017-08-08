package com.tencent.android.tpush.service.channel.security;

import B;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class a extends FilterInputStream
{
  private static final char[] a = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
  private static final int[] b = new int[''];
  private int c;
  private int d;

  static
  {
    for (int i = 0; i < 64; i++)
      b[a[i]] = i;
  }

  public a(InputStream paramInputStream)
  {
    super(paramInputStream);
  }

  public static byte[] a(String paramString)
  {
    Object localObject = new byte[0];
    try
    {
      byte[] arrayOfByte3 = paramString.getBytes("UTF-8");
      localObject = arrayOfByte3;
      locala = new a(new ByteArrayInputStream(localObject));
      localByteArrayOutputStream = new ByteArrayOutputStream((int)(0.67D * localObject.length));
    }
    catch (Exception localException)
    {
      try
      {
        a locala;
        ByteArrayOutputStream localByteArrayOutputStream;
        byte[] arrayOfByte1 = new byte[4096];
        while (true)
        {
          int i = locala.read(arrayOfByte1);
          if (i == -1)
            break;
          localByteArrayOutputStream.write(arrayOfByte1, 0, i);
        }
        locala.close();
        localByteArrayOutputStream.close();
        byte[] arrayOfByte2 = localByteArrayOutputStream.toByteArray();
        return arrayOfByte2;
        localException = localException;
      }
      catch (IOException localIOException)
      {
      }
    }
    return (B)null;
  }

  public int read()
  {
    int i = this.in.read();
    if (i == -1);
    int j;
    int k;
    do
    {
      do
      {
        return -1;
        if (Character.isWhitespace((char)i))
          break;
        this.c = (1 + this.c);
      }
      while (i == 61);
      j = b[i];
      k = (-1 + this.c) % 4;
      if (k == 0)
      {
        this.d = (j & 0x3F);
        return read();
      }
      if (k == 1)
      {
        int n = 0xFF & (this.d << 2) + (j >> 4);
        this.d = (j & 0xF);
        return n;
      }
      if (k != 2)
        continue;
      int m = 0xFF & (this.d << 4) + (j >> 2);
      this.d = (j & 0x3);
      return m;
    }
    while (k != 3);
    return 0xFF & j + (this.d << 6);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte.length < -1 + (paramInt2 + paramInt1))
      throw new IOException("The input buffer is too small: " + paramInt2 + " bytes requested starting at offset " + paramInt1 + " while the buffer " + " is only " + paramArrayOfByte.length + " bytes long.");
    for (int i = 0; ; i++)
    {
      int j;
      if (i < paramInt2)
      {
        j = read();
        if ((j == -1) && (i == 0))
          return -1;
        if (j != -1);
      }
      else
      {
        return i;
      }
      paramArrayOfByte[(paramInt1 + i)] = (byte)j;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.security.a
 * JD-Core Version:    0.6.0
 */