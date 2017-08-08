package com.tencent.msdk.tools;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.ZipException;

public class ZipCommentReader
{
  private static final int CFD_LOCATOR_OFFSET = 16;
  protected static final ZipLong EOCD_SIG = new ZipLong(101010256L);
  private static final int MIN_EOCD_SIZE = 22;

  public static void main(String[] paramArrayOfString)
    throws Throwable
  {
    positionAtCentralDirectory("D:\\lab.zip");
  }

  public static String positionAtCentralDirectory(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString)))
      return "";
    try
    {
      RandomAccessFile localRandomAccessFile = new RandomAccessFile(new File(paramString), "r");
      long l;
      while (true)
      {
        try
        {
          l = localRandomAccessFile.length() - 22L;
          localRandomAccessFile.seek(l);
          byte[] arrayOfByte1 = EOCD_SIG.getBytes();
          i = localRandomAccessFile.read();
          int j = 0;
          if (i == -1)
            continue;
          if ((i != arrayOfByte1[0]) || (localRandomAccessFile.read() != arrayOfByte1[1]) || (localRandomAccessFile.read() != arrayOfByte1[2]) || (localRandomAccessFile.read() != arrayOfByte1[3]))
            break label148;
          j = 1;
          if (j != 0)
            break;
          localRandomAccessFile.close();
          throw new ZipException("archive is not a ZIP archive");
        }
        catch (IOException localIOException1)
        {
        }
        label136: localIOException1.printStackTrace();
        Logger.d("ZipCommentReader read file error");
        return "";
        label148: l -= 1L;
        localRandomAccessFile.seek(l);
        int i = localRandomAccessFile.read();
      }
      localRandomAccessFile.seek(4L + (16L + l));
      byte[] arrayOfByte2 = new byte[2];
      localRandomAccessFile.readFully(arrayOfByte2);
      byte[] arrayOfByte3 = new byte[new ZipShort(arrayOfByte2).getValue()];
      localRandomAccessFile.readFully(arrayOfByte3);
      if (localRandomAccessFile != null)
        localRandomAccessFile.close();
      String str = new String(arrayOfByte3);
      return str;
    }
    catch (IOException localIOException2)
    {
      break label136;
    }
  }

  public static final class ZipLong
    implements Cloneable
  {
    private long value;

    public ZipLong(long paramLong)
    {
      this.value = paramLong;
    }

    public ZipLong(byte[] paramArrayOfByte)
    {
      this(paramArrayOfByte, 0);
    }

    public ZipLong(byte[] paramArrayOfByte, int paramInt)
    {
      this.value = (0xFF000000 & paramArrayOfByte[(paramInt + 3)] << 24);
      this.value += (0xFF0000 & paramArrayOfByte[(paramInt + 2)] << 16);
      this.value += (0xFF00 & paramArrayOfByte[(paramInt + 1)] << 8);
      this.value += (0xFF & paramArrayOfByte[paramInt]);
    }

    public boolean equals(Object paramObject)
    {
      if ((paramObject == null) || (!(paramObject instanceof ZipLong)));
      do
        return false;
      while (this.value != ((ZipLong)paramObject).getValue());
      return true;
    }

    public byte[] getBytes()
    {
      byte[] arrayOfByte = new byte[4];
      arrayOfByte[0] = (byte)(int)(0xFF & this.value);
      arrayOfByte[1] = (byte)(int)((0xFF00 & this.value) >> 8);
      arrayOfByte[2] = (byte)(int)((0xFF0000 & this.value) >> 16);
      arrayOfByte[3] = (byte)(int)((0xFF000000 & this.value) >> 24);
      return arrayOfByte;
    }

    public long getValue()
    {
      return this.value;
    }

    public int hashCode()
    {
      return (int)this.value;
    }
  }

  public static final class ZipShort
    implements Cloneable
  {
    private int value;

    public ZipShort(int paramInt)
    {
      this.value = paramInt;
    }

    public ZipShort(byte[] paramArrayOfByte)
    {
      this(paramArrayOfByte, 0);
    }

    public ZipShort(byte[] paramArrayOfByte, int paramInt)
    {
      this.value = (0xFF00 & paramArrayOfByte[(paramInt + 1)] << 8);
      this.value += (0xFF & paramArrayOfByte[paramInt]);
    }

    public boolean equals(Object paramObject)
    {
      if ((paramObject == null) || (!(paramObject instanceof ZipShort)));
      do
        return false;
      while (this.value != ((ZipShort)paramObject).getValue());
      return true;
    }

    public byte[] getBytes()
    {
      byte[] arrayOfByte = new byte[2];
      arrayOfByte[0] = (byte)(0xFF & this.value);
      arrayOfByte[1] = (byte)((0xFF00 & this.value) >> 8);
      return arrayOfByte;
    }

    public int getValue()
    {
      return this.value;
    }

    public int hashCode()
    {
      return this.value;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.ZipCommentReader
 * JD-Core Version:    0.6.0
 */