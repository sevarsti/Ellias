package com.tencent.msdk.pf;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Properties;
import java.util.zip.ZipException;

public final class ApkExternalInfoTool
{
  private static final int CFD_LOCATOR_OFFSET = 16;
  private static final String CHANNELID = "channelId";
  protected static final ZipLong EOCD_SIG = new ZipLong(101010256L);
  private static final int MIN_EOCD_SIZE = 22;
  private static ZipShort protoHead = new ZipShort(38650);

  // ERROR //
  public static String read(File paramFile, String paramString)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 47	java/io/RandomAccessFile
    //   5: dup
    //   6: aload_0
    //   7: ldc 49
    //   9: invokespecial 52	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   12: astore_3
    //   13: aload_3
    //   14: invokestatic 56	com/tencent/msdk/pf/ApkExternalInfoTool:readComment	(Ljava/io/RandomAccessFile;)[B
    //   17: astore 5
    //   19: aload 5
    //   21: ifnonnull +17 -> 38
    //   24: aconst_null
    //   25: astore 6
    //   27: aload_3
    //   28: ifnull +7 -> 35
    //   31: aload_3
    //   32: invokevirtual 59	java/io/RandomAccessFile:close	()V
    //   35: aload 6
    //   37: areturn
    //   38: new 61	com/tencent/msdk/pf/ApkExternalInfoTool$ApkExternalInfo
    //   41: dup
    //   42: aconst_null
    //   43: invokespecial 64	com/tencent/msdk/pf/ApkExternalInfoTool$ApkExternalInfo:<init>	(Lcom/tencent/msdk/pf/ApkExternalInfoTool$1;)V
    //   46: astore 7
    //   48: aload 7
    //   50: aload 5
    //   52: invokevirtual 68	com/tencent/msdk/pf/ApkExternalInfoTool$ApkExternalInfo:decode	([B)V
    //   55: aload 7
    //   57: getfield 72	com/tencent/msdk/pf/ApkExternalInfoTool$ApkExternalInfo:p	Ljava/util/Properties;
    //   60: aload_1
    //   61: invokevirtual 78	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   64: astore 8
    //   66: aload 8
    //   68: astore 6
    //   70: aload_3
    //   71: ifnull -36 -> 35
    //   74: aload_3
    //   75: invokevirtual 59	java/io/RandomAccessFile:close	()V
    //   78: aload 6
    //   80: areturn
    //   81: astore 4
    //   83: aload_2
    //   84: ifnull +7 -> 91
    //   87: aload_2
    //   88: invokevirtual 59	java/io/RandomAccessFile:close	()V
    //   91: aload 4
    //   93: athrow
    //   94: astore 4
    //   96: aload_3
    //   97: astore_2
    //   98: goto -15 -> 83
    //
    // Exception table:
    //   from	to	target	type
    //   2	13	81	finally
    //   13	19	94	finally
    //   38	66	94	finally
  }

  public static String readChannelId(File paramFile)
    throws IOException
  {
    return read(paramFile, "channelId");
  }

  private static byte[] readComment(RandomAccessFile paramRandomAccessFile)
    throws IOException
  {
    long l = paramRandomAccessFile.length() - 22L;
    paramRandomAccessFile.seek(l);
    byte[] arrayOfByte1 = EOCD_SIG.getBytes();
    for (int i = paramRandomAccessFile.read(); ; i = paramRandomAccessFile.read())
    {
      int j = 0;
      if (i != -1)
      {
        if ((i == arrayOfByte1[0]) && (paramRandomAccessFile.read() == arrayOfByte1[1]) && (paramRandomAccessFile.read() == arrayOfByte1[2]) && (paramRandomAccessFile.read() == arrayOfByte1[3]))
          j = 1;
      }
      else
      {
        if (j != 0)
          break;
        throw new ZipException("archive is not a ZIP archive");
      }
      l -= 1L;
      paramRandomAccessFile.seek(l);
    }
    paramRandomAccessFile.seek(4L + (16L + l));
    byte[] arrayOfByte2 = new byte[2];
    paramRandomAccessFile.readFully(arrayOfByte2);
    int k = new ZipShort(arrayOfByte2).getValue();
    if (k == 0)
      return null;
    byte[] arrayOfByte3 = new byte[k];
    paramRandomAccessFile.read(arrayOfByte3);
    return arrayOfByte3;
  }

  private static class ApkExternalInfo
  {
    byte[] otherData;
    Properties p = new Properties();

    void decode(byte[] paramArrayOfByte)
      throws IOException
    {
      if (paramArrayOfByte == null);
      ByteBuffer localByteBuffer;
      int k;
      do
      {
        int i;
        int j;
        do
        {
          do
          {
            return;
            localByteBuffer = ByteBuffer.wrap(paramArrayOfByte);
            i = ApkExternalInfoTool.protoHead.getBytes().length;
            byte[] arrayOfByte1 = new byte[i];
            localByteBuffer.get(arrayOfByte1);
            if (ApkExternalInfoTool.protoHead.equals(new ZipShort(arrayOfByte1)))
              continue;
            throw new ProtocolException("unknow protocl [" + Arrays.toString(paramArrayOfByte) + "]");
          }
          while (paramArrayOfByte.length - i <= 2);
          byte[] arrayOfByte2 = new byte[2];
          localByteBuffer.get(arrayOfByte2);
          j = new ZipShort(arrayOfByte2).getValue();
        }
        while (-2 + (paramArrayOfByte.length - i) < j);
        byte[] arrayOfByte3 = new byte[j];
        localByteBuffer.get(arrayOfByte3);
        this.p.load(new ByteArrayInputStream(arrayOfByte3));
        k = -2 + (paramArrayOfByte.length - i - j);
      }
      while (k <= 0);
      this.otherData = new byte[k];
      localByteBuffer.get(this.otherData);
    }

    public String toString()
    {
      return "ApkExternalInfo [p=" + this.p + ", otherData=" + Arrays.toString(this.otherData) + "]";
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.pf.ApkExternalInfoTool
 * JD-Core Version:    0.6.0
 */