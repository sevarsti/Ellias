package com.tencent.android.tpush.logging.c;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class b
{
  public static void a(ZipOutputStream paramZipOutputStream, File paramFile, String paramString, byte[] paramArrayOfByte)
  {
    int i = 0;
    if ((paramZipOutputStream == null) || (paramFile == null))
      throw new IOException("I/O Object got NullPointerException");
    if (!paramFile.exists())
      throw new FileNotFoundException("Target File is missing");
    String str;
    if (d.b(paramString))
      str = paramFile.getName();
    while (true)
    {
      if (paramFile.isFile());
      try
      {
        localBufferedInputStream = new BufferedInputStream(new FileInputStream(paramFile));
        try
        {
          paramZipOutputStream.putNextEntry(new ZipEntry(str));
          while (true)
          {
            int k = localBufferedInputStream.read(paramArrayOfByte, 0, paramArrayOfByte.length);
            if (-1 == k)
              break;
            paramZipOutputStream.write(paramArrayOfByte, 0, k);
          }
        }
        catch (IOException localIOException1)
        {
        }
        a.a(localBufferedInputStream);
        throw localIOException1;
        str = paramString + File.separator + paramFile.getName();
        continue;
        a.a(localBufferedInputStream);
        while (true)
        {
          return;
          if (!paramFile.isDirectory())
            continue;
          File[] arrayOfFile = paramFile.listFiles();
          int j = arrayOfFile.length;
          while (i < j)
          {
            a(paramZipOutputStream, arrayOfFile[i], str, paramArrayOfByte);
            i++;
          }
        }
      }
      catch (IOException localIOException2)
      {
        while (true)
          BufferedInputStream localBufferedInputStream = null;
      }
    }
  }

  public static boolean a(File paramFile)
  {
    int i = 0;
    if (paramFile != null)
    {
      if (!paramFile.isFile())
        break label28;
      if (paramFile.delete())
        break label26;
      paramFile.deleteOnExit();
    }
    label26: label28: 
    do
    {
      return false;
      return true;
    }
    while (!paramFile.isDirectory());
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile != null)
    {
      int j = arrayOfFile.length;
      while (i < j)
      {
        a(arrayOfFile[i]);
        i++;
      }
    }
    return paramFile.delete();
  }

  // ERROR //
  public static boolean a(File[] paramArrayOfFile, File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: ifnull +13 -> 16
    //   6: aload_0
    //   7: arraylength
    //   8: iconst_1
    //   9: if_icmplt +7 -> 16
    //   12: aload_1
    //   13: ifnonnull +5 -> 18
    //   16: iconst_0
    //   17: ireturn
    //   18: sipush 4096
    //   21: newarray byte
    //   23: astore 8
    //   25: new 53	java/util/zip/ZipOutputStream
    //   28: dup
    //   29: new 107	java/io/BufferedOutputStream
    //   32: dup
    //   33: new 109	java/io/FileOutputStream
    //   36: dup
    //   37: aload_1
    //   38: iconst_0
    //   39: invokespecial 112	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   42: invokespecial 115	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   45: invokespecial 116	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   48: astore 9
    //   50: aload_0
    //   51: arraylength
    //   52: istore 11
    //   54: iconst_0
    //   55: istore 12
    //   57: iload 12
    //   59: iload 11
    //   61: if_icmpge +21 -> 82
    //   64: aload 9
    //   66: aload_0
    //   67: iload 12
    //   69: aaload
    //   70: aconst_null
    //   71: aload 8
    //   73: invokestatic 95	com/tencent/android/tpush/logging/c/b:a	(Ljava/util/zip/ZipOutputStream;Ljava/io/File;Ljava/lang/String;[B)V
    //   76: iinc 12 1
    //   79: goto -22 -> 57
    //   82: aload 9
    //   84: invokevirtual 119	java/util/zip/ZipOutputStream:flush	()V
    //   87: aload 9
    //   89: invokevirtual 122	java/util/zip/ZipOutputStream:closeEntry	()V
    //   92: aload 9
    //   94: invokestatic 70	com/tencent/android/tpush/logging/c/a:a	(Ljava/lang/Object;)Z
    //   97: pop
    //   98: iconst_1
    //   99: ireturn
    //   100: astore 5
    //   102: aconst_null
    //   103: astore 6
    //   105: aload 6
    //   107: invokestatic 70	com/tencent/android/tpush/logging/c/a:a	(Ljava/lang/Object;)Z
    //   110: pop
    //   111: iconst_0
    //   112: ireturn
    //   113: astore_3
    //   114: aload_2
    //   115: invokestatic 70	com/tencent/android/tpush/logging/c/a:a	(Ljava/lang/Object;)Z
    //   118: pop
    //   119: aload_3
    //   120: athrow
    //   121: astore_3
    //   122: aload 9
    //   124: astore_2
    //   125: goto -11 -> 114
    //   128: astore 10
    //   130: aload 9
    //   132: astore 6
    //   134: goto -29 -> 105
    //
    // Exception table:
    //   from	to	target	type
    //   18	50	100	java/io/IOException
    //   18	50	113	finally
    //   50	54	121	finally
    //   64	76	121	finally
    //   82	92	121	finally
    //   50	54	128	java/io/IOException
    //   64	76	128	java/io/IOException
    //   82	92	128	java/io/IOException
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.c.b
 * JD-Core Version:    0.6.0
 */