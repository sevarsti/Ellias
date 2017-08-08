package com.tencent.android.tpush.service.channel.c;

import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class e
{
  static
  {
    if (!e.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      a = bool;
      return;
    }
  }

  public static int a(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt)
  {
    int i;
    if ((paramInputStream.available() == 0) && (paramArrayOfByte.length - paramInt > 0))
      i = 0;
    label67: 
    while (true)
    {
      return i;
      if (paramArrayOfByte.length - paramInt < paramInputStream.available());
      for (i = paramArrayOfByte.length - paramInt; ; i = paramInputStream.available())
      {
        if (i <= 0)
          break label67;
        i = paramInputStream.read(paramArrayOfByte, paramInt, i);
        if (i != -1)
          break;
        throw new IOException("the end of stream has been reached!");
      }
    }
  }

  public static int a(OutputStream paramOutputStream, int paramInt)
  {
    if ((!a) && ((paramInt < 0) || (paramInt > 255L)))
      throw new AssertionError();
    paramOutputStream.write((byte)(paramInt & 0xFF));
    return 1;
  }

  public static int a(OutputStream paramOutputStream, long paramLong)
  {
    if ((!a) && ((paramLong < 0L) || (paramLong > 4294967295L)))
      throw new AssertionError();
    byte[] arrayOfByte = new byte[4];
    arrayOfByte[0] = (byte)(int)(0xFF & paramLong >> 24);
    arrayOfByte[1] = (byte)(int)(0xFF & paramLong >> 16);
    arrayOfByte[2] = (byte)(int)(0xFF & paramLong >> 8);
    arrayOfByte[3] = (byte)(int)(paramLong & 0xFF);
    paramOutputStream.write(arrayOfByte);
    return 4;
  }

  public static int a(OutputStream paramOutputStream, byte[] paramArrayOfByte)
  {
    int i = 0;
    int j = 0;
    while (i < paramArrayOfByte.length)
    {
      paramOutputStream.write(paramArrayOfByte, i, 1);
      j++;
      i++;
    }
    return j;
  }

  public static short a(InputStream paramInputStream)
  {
    if (!a(paramInputStream, 1))
      throw new IORefusedException("inputstream cannot read 1 byte");
    byte[] arrayOfByte = new byte[1];
    if (paramInputStream.read(arrayOfByte) == -1)
      throw new IOException("the end of stream has been reached!");
    return (short)(0xFF & arrayOfByte[0]);
  }

  public static boolean a(InputStream paramInputStream, int paramInt)
  {
    return paramInputStream.available() >= paramInt;
  }

  public static int b(OutputStream paramOutputStream, int paramInt)
  {
    byte[] arrayOfByte = new byte[4];
    arrayOfByte[0] = (byte)(0xFF & paramInt >> 24);
    arrayOfByte[1] = (byte)(0xFF & paramInt >> 16);
    arrayOfByte[2] = (byte)(0xFF & paramInt >> 8);
    arrayOfByte[3] = (byte)(paramInt & 0xFF);
    paramOutputStream.write(arrayOfByte);
    return 4;
  }

  public static long b(InputStream paramInputStream)
  {
    if (!a(paramInputStream, 4))
      throw new IORefusedException("inputstream cannot read 4 byte");
    byte[] arrayOfByte = new byte[4];
    if (paramInputStream.read(arrayOfByte) == -1)
      throw new IOException("the end of stream has been reached!");
    return 0xFF & arrayOfByte[3] | (0xFF & arrayOfByte[2]) << 8 | (0xFF & arrayOfByte[1]) << 16 | (0xFF & arrayOfByte[0]) << 24;
  }

  public static int c(InputStream paramInputStream)
  {
    if (!a(paramInputStream, 4))
      throw new IORefusedException("inputstream cannot read 4 byte");
    byte[] arrayOfByte = new byte[4];
    if (paramInputStream.read(arrayOfByte) == -1)
      throw new IOException("the end of stream has been reached!");
    return 0xFF & arrayOfByte[3] | (0xFF & arrayOfByte[2]) << 8 | (0xFF & arrayOfByte[1]) << 16 | (0xFF & arrayOfByte[0]) << 24;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.c.e
 * JD-Core Version:    0.6.0
 */