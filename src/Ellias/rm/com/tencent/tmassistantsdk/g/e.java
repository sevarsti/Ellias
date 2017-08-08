package com.tencent.tmassistantsdk.g;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

public class e
{
  protected byte[] a;
  protected byte[] b;
  protected byte[] c;
  protected int d;
  protected int e;
  protected int f;
  protected int g;
  protected byte[] h;
  protected boolean i = true;
  protected int j;
  protected Random k = new Random();

  private void a()
  {
    this.f = 0;
    if (this.f < 8)
    {
      if (this.i)
      {
        byte[] arrayOfByte3 = this.a;
        int i1 = this.f;
        arrayOfByte3[i1] = (byte)(arrayOfByte3[i1] ^ this.b[this.f]);
      }
      while (true)
      {
        this.f = (1 + this.f);
        break;
        byte[] arrayOfByte2 = this.a;
        int n = this.f;
        arrayOfByte2[n] = (byte)(arrayOfByte2[n] ^ this.c[(this.e + this.f)]);
      }
    }
    System.arraycopy(a(this.a), 0, this.c, this.d, 8);
    for (this.f = 0; this.f < 8; this.f = (1 + this.f))
    {
      byte[] arrayOfByte1 = this.c;
      int m = this.d + this.f;
      arrayOfByte1[m] = (byte)(arrayOfByte1[m] ^ this.b[this.f]);
    }
    System.arraycopy(this.a, 0, this.b, 0, 8);
    this.e = this.d;
    this.d = (8 + this.d);
    this.f = 0;
    this.i = false;
  }

  private boolean a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    for (this.f = 0; this.f < 8; this.f = (1 + this.f))
    {
      if (this.j + this.f >= paramInt2)
        return true;
      byte[] arrayOfByte = this.b;
      int m = this.f;
      arrayOfByte[m] = (byte)(arrayOfByte[m] ^ paramArrayOfByte[(paramInt1 + this.d + this.f)]);
    }
    this.b = b(this.b);
    if (this.b == null)
      return false;
    this.j = (8 + this.j);
    this.d = (8 + this.d);
    this.f = 0;
    return true;
  }

  private byte[] a(byte[] paramArrayOfByte)
  {
    int m = 16;
    long l1;
    long l2;
    long l3;
    long l4;
    long l5;
    long l6;
    long l7;
    long l8;
    try
    {
      l1 = b(paramArrayOfByte, 0, 4);
      l2 = b(paramArrayOfByte, 4, 4);
      l3 = b(this.h, 0, 4);
      l4 = b(this.h, 4, 4);
      l5 = b(this.h, 8, 4);
      l6 = b(this.h, 12, 4);
      l7 = 0L;
      l8 = 0x9E3779B9 & 0xFFFFFFFF;
      break label136;
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
      label136: int n = m - 1;
      if (m <= 0)
        break;
      l7 = 0xFFFFFFFF & l7 + l8;
      l1 = 0xFFFFFFFF & l1 + (l3 + (l2 << 4) ^ l2 + l7 ^ l4 + (l2 >>> 5));
      l2 = 0xFFFFFFFF & l2 + (l5 + (l1 << 4) ^ l1 + l7 ^ l6 + (l1 >>> 5));
      m = n;
    }
  }

  private byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    int m = 16;
    long l1;
    long l2;
    long l3;
    long l4;
    long l5;
    long l6;
    long l7;
    long l8;
    try
    {
      l1 = b(paramArrayOfByte, paramInt, 4);
      l2 = b(paramArrayOfByte, paramInt + 4, 4);
      l3 = b(this.h, 0, 4);
      l4 = b(this.h, 4, 4);
      l5 = b(this.h, 8, 4);
      l6 = b(this.h, 12, 4);
      l7 = 0xE3779B90 & 0xFFFFFFFF;
      l8 = 0x9E3779B9 & 0xFFFFFFFF;
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
      label145: int n = m - 1;
      if (m <= 0)
        break;
      l2 = 0xFFFFFFFF & l2 - (l5 + (l1 << 4) ^ l1 + l7 ^ l6 + (l1 >>> 5));
      l1 = 0xFFFFFFFF & l1 - (l3 + (l2 << 4) ^ l2 + l7 ^ l4 + (l2 >>> 5));
      l7 = 0xFFFFFFFF & l7 - l8;
      m = n;
    }
  }

  private int b()
  {
    return this.k.nextInt();
  }

  private static long b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    long l = 0L;
    int m;
    if (paramInt2 > 8)
      m = paramInt1 + 8;
    while (paramInt1 < m)
    {
      l = l << 8 | 0xFF & paramArrayOfByte[paramInt1];
      paramInt1++;
      continue;
      m = paramInt1 + paramInt2;
    }
    return 0xFFFFFFFF & l | l >>> 32;
  }

  private byte[] b(byte[] paramArrayOfByte)
  {
    return a(paramArrayOfByte, 0);
  }

  public byte[] a(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2)
  {
    this.e = 0;
    this.d = 0;
    this.h = paramArrayOfByte2;
    byte[] arrayOfByte1 = new byte[paramInt1 + 8];
    if ((paramInt2 % 8 != 0) || (paramInt2 < 16))
      return null;
    this.b = a(paramArrayOfByte1, paramInt1);
    this.f = (0x7 & this.b[0]);
    int m = -10 + (paramInt2 - this.f);
    if (m < 0)
      return null;
    for (int n = paramInt1; n < arrayOfByte1.length; n++)
      arrayOfByte1[n] = 0;
    this.c = new byte[m];
    this.e = 0;
    this.d = 8;
    this.j = 8;
    this.f = (1 + this.f);
    this.g = 1;
    byte[] arrayOfByte2 = arrayOfByte1;
    while (true)
    {
      if (this.g <= 2)
      {
        if (this.f < 8)
        {
          this.f = (1 + this.f);
          this.g = (1 + this.g);
        }
        if (this.f != 8)
          continue;
        if (!a(paramArrayOfByte1, paramInt1, paramInt2))
          return null;
      }
      else
      {
        int i1 = m;
        byte[] arrayOfByte3 = arrayOfByte2;
        int i2 = 0;
        byte[] arrayOfByte4 = arrayOfByte3;
        while (true)
        {
          if (i1 != 0)
          {
            if (this.f < 8)
            {
              this.c[i2] = (byte)(arrayOfByte4[(paramInt1 + this.e + this.f)] ^ this.b[this.f]);
              i2++;
              i1--;
              this.f = (1 + this.f);
            }
            if (this.f != 8)
              continue;
            this.e = (-8 + this.d);
            if (!a(paramArrayOfByte1, paramInt1, paramInt2))
              return null;
          }
          else
          {
            this.g = 1;
            byte[] arrayOfByte5 = arrayOfByte4;
            while (this.g < 8)
            {
              if (this.f < 8)
              {
                if ((arrayOfByte5[(paramInt1 + this.e + this.f)] ^ this.b[this.f]) != 0)
                  return null;
                this.f = (1 + this.f);
              }
              if (this.f == 8)
              {
                this.e = this.d;
                if (!a(paramArrayOfByte1, paramInt1, paramInt2))
                  return null;
                arrayOfByte5 = paramArrayOfByte1;
              }
              this.g = (1 + this.g);
            }
            return this.c;
          }
          arrayOfByte4 = paramArrayOfByte1;
        }
      }
      arrayOfByte2 = paramArrayOfByte1;
    }
  }

  public byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    return a(paramArrayOfByte1, 0, paramArrayOfByte1.length, paramArrayOfByte2);
  }

  public byte[] b(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2)
  {
    this.a = new byte[8];
    this.b = new byte[8];
    this.f = 1;
    this.g = 0;
    this.e = 0;
    this.d = 0;
    this.h = paramArrayOfByte2;
    this.i = true;
    this.f = ((paramInt2 + 10) % 8);
    if (this.f != 0)
      this.f = (8 - this.f);
    this.c = new byte[10 + (paramInt2 + this.f)];
    this.a[0] = (byte)(0xF8 & b() | this.f);
    for (int m = 1; m <= this.f; m++)
      this.a[m] = (byte)(0xFF & b());
    this.f = (1 + this.f);
    for (int n = 0; n < 8; n++)
      this.b[n] = 0;
    this.g = 1;
    while (this.g <= 2)
    {
      if (this.f < 8)
      {
        byte[] arrayOfByte3 = this.a;
        int i7 = this.f;
        this.f = (i7 + 1);
        arrayOfByte3[i7] = (byte)(0xFF & b());
        this.g = (1 + this.g);
      }
      if (this.f != 8)
        continue;
      a();
    }
    int i1 = paramInt1;
    int i2 = paramInt2;
    int i4;
    if (i2 > 0)
    {
      if (this.f >= 8)
        break label437;
      byte[] arrayOfByte2 = this.a;
      int i6 = this.f;
      this.f = (i6 + 1);
      i4 = i1 + 1;
      arrayOfByte2[i6] = paramArrayOfByte1[i1];
    }
    for (int i5 = i2 - 1; ; i5 = i2)
    {
      if (this.f == 8)
      {
        a();
        i2 = i5;
        i1 = i4;
        break;
        this.g = 1;
        while (this.g <= 7)
        {
          if (this.f < 8)
          {
            byte[] arrayOfByte1 = this.a;
            int i3 = this.f;
            this.f = (i3 + 1);
            arrayOfByte1[i3] = 0;
            this.g = (1 + this.g);
          }
          if (this.f != 8)
            continue;
          a();
        }
        return this.c;
      }
      i2 = i5;
      i1 = i4;
      break;
      label437: i4 = i1;
    }
  }

  public byte[] b(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    return b(paramArrayOfByte1, 0, paramArrayOfByte1.length, paramArrayOfByte2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.g.e
 * JD-Core Version:    0.6.0
 */