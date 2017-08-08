package com.tencent.android.tpush.service.channel.security;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class b extends FilterOutputStream
{
  private static final char[] a = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
  private int b;
  private int c;

  public b(OutputStream paramOutputStream)
  {
    super(paramOutputStream);
  }

  public static String a(byte[] paramArrayOfByte)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream((int)(1.37D * paramArrayOfByte.length));
    b localb = new b(localByteArrayOutputStream);
    try
    {
      localb.write(paramArrayOfByte);
      localb.close();
      localByteArrayOutputStream.close();
      String str = localByteArrayOutputStream.toString("UTF-8");
      return str;
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  public void close()
  {
    if (this.b % 3 == 1)
    {
      int j = 0x3F & this.c << 4;
      this.out.write(a[j]);
      this.out.write(61);
      this.out.write(61);
    }
    while (true)
    {
      super.close();
      return;
      if (this.b % 3 != 2)
        continue;
      int i = 0x3F & this.c << 2;
      this.out.write(a[i]);
      this.out.write(61);
    }
  }

  public void write(int paramInt)
  {
    if (paramInt < 0)
      paramInt += 256;
    if (this.b % 3 == 0)
    {
      int m = paramInt >> 2;
      this.c = (paramInt & 0x3);
      this.out.write(a[m]);
    }
    while (true)
    {
      this.b = (1 + this.b);
      if (this.b % 57 == 0)
        this.out.write(10);
      return;
      if (this.b % 3 == 1)
      {
        int k = 0x3F & (this.c << 4) + (paramInt >> 4);
        this.c = (paramInt & 0xF);
        this.out.write(a[k]);
        continue;
      }
      if (this.b % 3 != 2)
        continue;
      int i = 0x3F & (this.c << 2) + (paramInt >> 6);
      this.out.write(a[i]);
      int j = paramInt & 0x3F;
      this.out.write(a[j]);
      this.c = 0;
    }
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    for (int i = 0; i < paramInt2; i++)
      write(paramArrayOfByte[(paramInt1 + i)]);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.security.b
 * JD-Core Version:    0.6.0
 */