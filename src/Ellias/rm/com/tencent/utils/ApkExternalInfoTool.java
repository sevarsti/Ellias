package com.tencent.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipException;

public final class ApkExternalInfoTool
{
  public static final String CHANNELID = "channelNo";
  private static final ZipLong a = new ZipLong(101010256L);
  private static final ZipShort b = new ZipShort(38651);

  private static byte[] a(RandomAccessFile paramRandomAccessFile)
    throws IOException
  {
    int i = 1;
    long l = paramRandomAccessFile.length() - 22L;
    paramRandomAccessFile.seek(l);
    byte[] arrayOfByte1 = a.getBytes();
    int j = paramRandomAccessFile.read();
    if (j != -1)
      if ((j != arrayOfByte1[0]) || (paramRandomAccessFile.read() != arrayOfByte1[i]) || (paramRandomAccessFile.read() != arrayOfByte1[2]) || (paramRandomAccessFile.read() != arrayOfByte1[3]));
    while (true)
    {
      if (i == 0)
      {
        throw new ZipException("archive is not a ZIP archive");
        l -= 1L;
        paramRandomAccessFile.seek(l);
        j = paramRandomAccessFile.read();
        break;
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
      i = 0;
    }
  }

  // ERROR //
  public static String read(File paramFile, String paramString)
    throws IOException
  {
    // Byte code:
    //   0: new 40	java/io/RandomAccessFile
    //   3: dup
    //   4: aload_0
    //   5: ldc 83
    //   7: invokespecial 86	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   10: astore_2
    //   11: aload_2
    //   12: invokestatic 88	com/tencent/utils/ApkExternalInfoTool:a	(Ljava/io/RandomAccessFile;)[B
    //   15: astore 4
    //   17: aload 4
    //   19: ifnonnull +17 -> 36
    //   22: aconst_null
    //   23: astore 5
    //   25: aload_2
    //   26: ifnull +7 -> 33
    //   29: aload_2
    //   30: invokevirtual 91	java/io/RandomAccessFile:close	()V
    //   33: aload 5
    //   35: areturn
    //   36: new 93	com/tencent/utils/ApkExternalInfoTool$ApkExternalInfo
    //   39: dup
    //   40: aconst_null
    //   41: invokespecial 96	com/tencent/utils/ApkExternalInfoTool$ApkExternalInfo:<init>	(Lcom/tencent/utils/ApkExternalInfoTool$1;)V
    //   44: astore 6
    //   46: aload 6
    //   48: aload 4
    //   50: invokevirtual 98	com/tencent/utils/ApkExternalInfoTool$ApkExternalInfo:a	([B)V
    //   53: aload 6
    //   55: getfield 101	com/tencent/utils/ApkExternalInfoTool$ApkExternalInfo:a	Ljava/util/Properties;
    //   58: aload_1
    //   59: invokevirtual 107	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   62: astore 7
    //   64: aload 7
    //   66: astore 5
    //   68: aload_2
    //   69: ifnull -36 -> 33
    //   72: aload_2
    //   73: invokevirtual 91	java/io/RandomAccessFile:close	()V
    //   76: aload 5
    //   78: areturn
    //   79: astore 8
    //   81: aconst_null
    //   82: astore_2
    //   83: aload 8
    //   85: astore_3
    //   86: aload_2
    //   87: ifnull +7 -> 94
    //   90: aload_2
    //   91: invokevirtual 91	java/io/RandomAccessFile:close	()V
    //   94: aload_3
    //   95: athrow
    //   96: astore_3
    //   97: goto -11 -> 86
    //
    // Exception table:
    //   from	to	target	type
    //   0	11	79	finally
    //   11	17	96	finally
    //   36	64	96	finally
  }

  public static String readChannelId(File paramFile)
    throws IOException
  {
    return read(paramFile, "channelNo");
  }

  public static void updateExternalInfo(File paramFile, String paramString1, String paramString2)
    throws IOException
  {
    try
    {
      localRandomAccessFile = new RandomAccessFile(paramFile, "rw");
    }
    finally
    {
      try
      {
        byte[] arrayOfByte1 = a(localRandomAccessFile);
        ApkExternalInfo localApkExternalInfo = new ApkExternalInfo(null);
        localApkExternalInfo.a(arrayOfByte1);
        localApkExternalInfo.a.setProperty(paramString1, paramString2);
        if (arrayOfByte1 == null);
        for (int i = 0; ; i = arrayOfByte1.length)
        {
          localRandomAccessFile.seek(localRandomAccessFile.length() - i - 2L);
          byte[] arrayOfByte2 = localApkExternalInfo.a();
          localRandomAccessFile.write(new ZipShort(arrayOfByte2.length).getBytes());
          localRandomAccessFile.write(arrayOfByte2);
          if (localRandomAccessFile != null)
            localRandomAccessFile.close();
          return;
        }
        localObject1 = finally;
        RandomAccessFile localRandomAccessFile = null;
        label120: if (localRandomAccessFile != null)
          localRandomAccessFile.close();
        throw localObject1;
      }
      finally
      {
        break label120;
      }
    }
  }

  private static class ApkExternalInfo
  {
    Properties a = new Properties();
    byte[] b;

    void a(byte[] paramArrayOfByte)
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
            i = ApkExternalInfoTool.a().getBytes().length;
            byte[] arrayOfByte1 = new byte[i];
            localByteBuffer.get(arrayOfByte1);
            if (ApkExternalInfoTool.a().equals(new ZipShort(arrayOfByte1)))
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
        this.a.load(new ByteArrayInputStream(arrayOfByte3));
        k = -2 + (paramArrayOfByte.length - i - j);
      }
      while (k <= 0);
      this.b = new byte[k];
      localByteBuffer.get(this.b);
    }

    byte[] a()
      throws IOException
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localByteArrayOutputStream.write(ApkExternalInfoTool.a().getBytes());
      Iterator localIterator = this.a.keySet().iterator();
      Object localObject;
      for (String str = ""; localIterator.hasNext(); str = str + localObject + "=" + this.a.getProperty((String)localObject) + "\r\n")
        localObject = localIterator.next();
      byte[] arrayOfByte = str.getBytes();
      localByteArrayOutputStream.write(new ZipShort(arrayOfByte.length).getBytes());
      localByteArrayOutputStream.write(arrayOfByte);
      if (this.b != null)
        localByteArrayOutputStream.write(this.b);
      return localByteArrayOutputStream.toByteArray();
    }

    public String toString()
    {
      return "ApkExternalInfo [p=" + this.a + ", otherData=" + Arrays.toString(this.b) + "]";
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.utils.ApkExternalInfoTool
 * JD-Core Version:    0.6.0
 */