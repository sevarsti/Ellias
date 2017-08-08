package com.tencent.component.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import com.tencent.component.utils.log.LogUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil
{
  public static final int a = 4096;
  public static final FileUtil.FileComparator b = new b();

  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    if (a(paramString2));
    String str1;
    while (true)
    {
      return;
      if (paramString1 == null)
        paramString1 = "";
      AssetManager localAssetManager = paramContext.getAssets();
      try
      {
        String[] arrayOfString2 = localAssetManager.list(paramString1);
        arrayOfString1 = arrayOfString2;
        if (arrayOfString1 == null)
          continue;
        if ((arrayOfString1.length == 0) && (paramString1.length() > 0))
          b(paramContext, paramString1, paramString2);
        int i = arrayOfString1.length;
        for (int j = 0; j < i; j++)
        {
          str1 = arrayOfString1[j];
          if (!a(str1))
            break label125;
        }
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        while (true)
        {
          if (paramString1.length() > 0)
            b(paramContext, paramString1, paramString2);
          arrayOfString1 = null;
        }
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          localIOException.printStackTrace();
          String[] arrayOfString1 = null;
        }
      }
    }
    label125: if (paramString1.length() == 0);
    for (String str2 = str1; ; str2 = paramString1 + File.separator + str1)
    {
      a(paramContext, str2, paramString2 + File.separator + str1);
      break;
    }
  }

  public static void a(File paramFile)
  {
    a(paramFile, false);
  }

  public static void a(File paramFile, boolean paramBoolean)
  {
    if ((paramFile == null) || (!paramFile.exists()));
    do
    {
      File[] arrayOfFile;
      do
      {
        return;
        if (paramFile.isFile())
        {
          paramFile.delete();
          return;
        }
        arrayOfFile = paramFile.listFiles();
      }
      while (arrayOfFile == null);
      int i = arrayOfFile.length;
      for (int j = 0; j < i; j++)
        a(arrayOfFile[j], paramBoolean);
    }
    while (paramBoolean);
    paramFile.delete();
  }

  public static void a(ZipOutputStream paramZipOutputStream, File paramFile, String paramString, byte[] paramArrayOfByte)
  {
    int i = 0;
    if ((paramZipOutputStream == null) || (paramFile == null))
      throw new IOException("I/O Object got NullPointerException");
    if (!paramFile.exists())
      throw new FileNotFoundException("Target File is missing");
    String str;
    if (TextUtils.isEmpty(paramString))
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
        IOUtils.a(localBufferedInputStream);
        throw localIOException1;
        str = paramString + File.separator + paramFile.getName();
        continue;
        IOUtils.a(localBufferedInputStream);
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

  public static boolean a(File paramFile1, File paramFile2)
  {
    return a(paramFile1, paramFile2, null);
  }

  public static boolean a(File paramFile1, File paramFile2, FileFilter paramFileFilter)
  {
    return a(paramFile1, paramFile2, paramFileFilter, b);
  }

  public static boolean a(File paramFile1, File paramFile2, FileFilter paramFileFilter, FileUtil.FileComparator paramFileComparator)
  {
    if ((paramFile1 == null) || (paramFile2 == null));
    File[] arrayOfFile;
    do
    {
      do
        return false;
      while (!paramFile1.exists());
      if (paramFile1.isFile())
        return b(paramFile1, paramFile2, paramFileFilter, paramFileComparator);
      arrayOfFile = paramFile1.listFiles();
    }
    while (arrayOfFile == null);
    int i = 1;
    int j = arrayOfFile.length;
    for (int k = 0; k < j; k++)
    {
      File localFile = arrayOfFile[k];
      if (a(localFile, new File(paramFile2, localFile.getName()), paramFileFilter))
        continue;
      i = 0;
    }
    return i;
  }

  private static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  public static boolean a(File[] paramArrayOfFile, File paramFile)
  {
    try
    {
      boolean bool = a(paramArrayOfFile, new FileOutputStream(paramFile));
      return bool;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      LogUtil.e("FileUtil", localFileNotFoundException.getMessage(), localFileNotFoundException);
    }
    return false;
  }

  // ERROR //
  public static boolean a(File[] paramArrayOfFile, FileOutputStream paramFileOutputStream)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +13 -> 14
    //   4: aload_0
    //   5: arraylength
    //   6: iconst_1
    //   7: if_icmplt +7 -> 14
    //   10: aload_1
    //   11: ifnonnull +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: sipush 4096
    //   19: newarray byte
    //   21: astore 8
    //   23: new 121	java/util/zip/ZipOutputStream
    //   26: dup
    //   27: new 175	java/io/BufferedOutputStream
    //   30: dup
    //   31: aload_1
    //   32: invokespecial 178	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   35: invokespecial 179	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   38: astore_3
    //   39: aload_0
    //   40: arraylength
    //   41: istore 10
    //   43: iconst_0
    //   44: istore 11
    //   46: iload 11
    //   48: iload 10
    //   50: if_icmpge +20 -> 70
    //   53: aload_3
    //   54: aload_0
    //   55: iload 11
    //   57: aaload
    //   58: aconst_null
    //   59: aload 8
    //   61: invokestatic 143	com/tencent/component/utils/FileUtil:a	(Ljava/util/zip/ZipOutputStream;Ljava/io/File;Ljava/lang/String;[B)V
    //   64: iinc 11 1
    //   67: goto -21 -> 46
    //   70: aload_3
    //   71: invokevirtual 182	java/util/zip/ZipOutputStream:flush	()V
    //   74: aload_3
    //   75: invokevirtual 185	java/util/zip/ZipOutputStream:closeEntry	()V
    //   78: aload_3
    //   79: invokestatic 138	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   82: pop
    //   83: iconst_1
    //   84: ireturn
    //   85: astore 5
    //   87: aconst_null
    //   88: astore 6
    //   90: aload 6
    //   92: invokestatic 138	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   95: pop
    //   96: iconst_0
    //   97: ireturn
    //   98: astore_2
    //   99: aconst_null
    //   100: astore_3
    //   101: aload_3
    //   102: invokestatic 138	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   105: pop
    //   106: aload_2
    //   107: athrow
    //   108: astore_2
    //   109: goto -8 -> 101
    //   112: astore 9
    //   114: aload_3
    //   115: astore 6
    //   117: goto -27 -> 90
    //
    // Exception table:
    //   from	to	target	type
    //   16	39	85	java/io/IOException
    //   16	39	98	finally
    //   39	43	108	finally
    //   53	64	108	finally
    //   70	78	108	finally
    //   39	43	112	java/io/IOException
    //   53	64	112	java/io/IOException
    //   70	78	112	java/io/IOException
  }

  // ERROR //
  private static void b(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_1
    //   3: invokestatic 27	com/tencent/component/utils/FileUtil:a	(Ljava/lang/String;)Z
    //   6: ifne +10 -> 16
    //   9: aload_2
    //   10: invokestatic 27	com/tencent/component/utils/FileUtil:a	(Ljava/lang/String;)Z
    //   13: ifeq +4 -> 17
    //   16: return
    //   17: aload_0
    //   18: invokevirtual 35	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   21: astore 4
    //   23: new 61	java/io/File
    //   26: dup
    //   27: aload_2
    //   28: invokespecial 188	java/io/File:<init>	(Ljava/lang/String;)V
    //   31: astore 5
    //   33: aload 5
    //   35: invokevirtual 79	java/io/File:exists	()Z
    //   38: istore 12
    //   40: iload 12
    //   42: ifeq +160 -> 202
    //   45: aload 4
    //   47: aload_1
    //   48: invokevirtual 192	android/content/res/AssetManager:openFd	(Ljava/lang/String;)Landroid/content/res/AssetFileDescriptor;
    //   51: astore 30
    //   53: aload 5
    //   55: invokevirtual 195	java/io/File:length	()J
    //   58: lstore 31
    //   60: aload 30
    //   62: invokevirtual 200	android/content/res/AssetFileDescriptor:getLength	()J
    //   65: lstore 33
    //   67: lload 31
    //   69: lload 33
    //   71: lcmp
    //   72: ifne +23 -> 95
    //   75: iconst_0
    //   76: ifeq +7 -> 83
    //   79: aconst_null
    //   80: invokevirtual 205	java/io/InputStream:close	()V
    //   83: iconst_0
    //   84: ifeq -68 -> 16
    //   87: aconst_null
    //   88: invokevirtual 208	java/io/OutputStream:close	()V
    //   91: return
    //   92: astore 36
    //   94: return
    //   95: aload 5
    //   97: invokevirtual 141	java/io/File:isDirectory	()Z
    //   100: istore 35
    //   102: iconst_0
    //   103: istore 22
    //   105: iload 35
    //   107: ifeq +8 -> 115
    //   110: aload 5
    //   112: invokestatic 210	com/tencent/component/utils/FileUtil:a	(Ljava/io/File;)V
    //   115: iload 22
    //   117: ifeq +85 -> 202
    //   120: aload 4
    //   122: aload_1
    //   123: invokevirtual 214	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   126: astore 23
    //   128: aload 5
    //   130: invokevirtual 195	java/io/File:length	()J
    //   133: lstore 26
    //   135: aload 23
    //   137: invokevirtual 217	java/io/InputStream:available	()I
    //   140: istore 28
    //   142: lload 26
    //   144: iload 28
    //   146: i2l
    //   147: lcmp
    //   148: ifne +36 -> 184
    //   151: aload 23
    //   153: invokevirtual 205	java/io/InputStream:close	()V
    //   156: iconst_0
    //   157: ifeq +7 -> 164
    //   160: aconst_null
    //   161: invokevirtual 205	java/io/InputStream:close	()V
    //   164: iconst_0
    //   165: ifeq -149 -> 16
    //   168: aconst_null
    //   169: invokevirtual 208	java/io/OutputStream:close	()V
    //   172: return
    //   173: astore 29
    //   175: return
    //   176: astore 21
    //   178: iconst_1
    //   179: istore 22
    //   181: goto -66 -> 115
    //   184: aload 5
    //   186: invokevirtual 141	java/io/File:isDirectory	()Z
    //   189: ifeq +8 -> 197
    //   192: aload 5
    //   194: invokestatic 210	com/tencent/component/utils/FileUtil:a	(Ljava/io/File;)V
    //   197: aload 23
    //   199: invokevirtual 205	java/io/InputStream:close	()V
    //   202: aload 5
    //   204: invokevirtual 221	java/io/File:getParentFile	()Ljava/io/File;
    //   207: astore 13
    //   209: aload 13
    //   211: invokevirtual 82	java/io/File:isFile	()Z
    //   214: ifeq +8 -> 222
    //   217: aload 13
    //   219: invokestatic 210	com/tencent/component/utils/FileUtil:a	(Ljava/io/File;)V
    //   222: aload 13
    //   224: invokevirtual 79	java/io/File:exists	()Z
    //   227: ifne +118 -> 345
    //   230: aload 13
    //   232: invokevirtual 224	java/io/File:mkdirs	()Z
    //   235: istore 19
    //   237: iload 19
    //   239: ifne +106 -> 345
    //   242: iconst_0
    //   243: ifeq +7 -> 250
    //   246: aconst_null
    //   247: invokevirtual 205	java/io/InputStream:close	()V
    //   250: iconst_0
    //   251: ifeq -235 -> 16
    //   254: aconst_null
    //   255: invokevirtual 208	java/io/OutputStream:close	()V
    //   258: return
    //   259: astore 20
    //   261: return
    //   262: astore 25
    //   264: aload 23
    //   266: invokevirtual 205	java/io/InputStream:close	()V
    //   269: goto -67 -> 202
    //   272: astore 9
    //   274: aconst_null
    //   275: astore 10
    //   277: aload 9
    //   279: invokevirtual 225	java/lang/Throwable:printStackTrace	()V
    //   282: aload 5
    //   284: invokestatic 210	com/tencent/component/utils/FileUtil:a	(Ljava/io/File;)V
    //   287: aload 10
    //   289: ifnull +8 -> 297
    //   292: aload 10
    //   294: invokevirtual 205	java/io/InputStream:close	()V
    //   297: aload_3
    //   298: ifnull -282 -> 16
    //   301: aload_3
    //   302: invokevirtual 208	java/io/OutputStream:close	()V
    //   305: return
    //   306: astore 11
    //   308: return
    //   309: astore 24
    //   311: aload 23
    //   313: invokevirtual 205	java/io/InputStream:close	()V
    //   316: aload 24
    //   318: athrow
    //   319: astore 6
    //   321: aconst_null
    //   322: astore 7
    //   324: aload 7
    //   326: ifnull +8 -> 334
    //   329: aload 7
    //   331: invokevirtual 205	java/io/InputStream:close	()V
    //   334: aload_3
    //   335: ifnull +7 -> 342
    //   338: aload_3
    //   339: invokevirtual 208	java/io/OutputStream:close	()V
    //   342: aload 6
    //   344: athrow
    //   345: aload 4
    //   347: aload_1
    //   348: invokevirtual 214	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   351: astore 14
    //   353: aload 14
    //   355: astore 7
    //   357: new 175	java/io/BufferedOutputStream
    //   360: dup
    //   361: new 158	java/io/FileOutputStream
    //   364: dup
    //   365: aload 5
    //   367: invokespecial 159	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   370: invokespecial 178	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   373: astore 15
    //   375: sipush 1024
    //   378: newarray byte
    //   380: astore 16
    //   382: aload 7
    //   384: aload 16
    //   386: invokevirtual 228	java/io/InputStream:read	([B)I
    //   389: istore 17
    //   391: iload 17
    //   393: ifle +28 -> 421
    //   396: aload 15
    //   398: aload 16
    //   400: iconst_0
    //   401: iload 17
    //   403: invokevirtual 229	java/io/OutputStream:write	([BII)V
    //   406: goto -24 -> 382
    //   409: astore 9
    //   411: aload 15
    //   413: astore_3
    //   414: aload 7
    //   416: astore 10
    //   418: goto -141 -> 277
    //   421: aload 7
    //   423: ifnull +8 -> 431
    //   426: aload 7
    //   428: invokevirtual 205	java/io/InputStream:close	()V
    //   431: aload 15
    //   433: ifnull -417 -> 16
    //   436: aload 15
    //   438: invokevirtual 208	java/io/OutputStream:close	()V
    //   441: return
    //   442: astore 18
    //   444: return
    //   445: astore 8
    //   447: goto -105 -> 342
    //   450: astore 6
    //   452: aconst_null
    //   453: astore_3
    //   454: goto -130 -> 324
    //   457: astore 6
    //   459: aload 15
    //   461: astore_3
    //   462: goto -138 -> 324
    //   465: astore 6
    //   467: aload 10
    //   469: astore 7
    //   471: goto -147 -> 324
    //   474: astore 9
    //   476: aload 7
    //   478: astore 10
    //   480: aconst_null
    //   481: astore_3
    //   482: goto -205 -> 277
    //
    // Exception table:
    //   from	to	target	type
    //   79	83	92	java/lang/Throwable
    //   87	91	92	java/lang/Throwable
    //   160	164	173	java/lang/Throwable
    //   168	172	173	java/lang/Throwable
    //   45	67	176	java/io/IOException
    //   95	102	176	java/io/IOException
    //   110	115	176	java/io/IOException
    //   246	250	259	java/lang/Throwable
    //   254	258	259	java/lang/Throwable
    //   128	142	262	java/io/IOException
    //   184	197	262	java/io/IOException
    //   33	40	272	java/lang/Throwable
    //   45	67	272	java/lang/Throwable
    //   95	102	272	java/lang/Throwable
    //   110	115	272	java/lang/Throwable
    //   120	128	272	java/lang/Throwable
    //   151	156	272	java/lang/Throwable
    //   197	202	272	java/lang/Throwable
    //   202	222	272	java/lang/Throwable
    //   222	237	272	java/lang/Throwable
    //   264	269	272	java/lang/Throwable
    //   311	319	272	java/lang/Throwable
    //   345	353	272	java/lang/Throwable
    //   292	297	306	java/lang/Throwable
    //   301	305	306	java/lang/Throwable
    //   128	142	309	finally
    //   184	197	309	finally
    //   33	40	319	finally
    //   45	67	319	finally
    //   95	102	319	finally
    //   110	115	319	finally
    //   120	128	319	finally
    //   151	156	319	finally
    //   197	202	319	finally
    //   202	222	319	finally
    //   222	237	319	finally
    //   264	269	319	finally
    //   311	319	319	finally
    //   345	353	319	finally
    //   375	382	409	java/lang/Throwable
    //   382	391	409	java/lang/Throwable
    //   396	406	409	java/lang/Throwable
    //   426	431	442	java/lang/Throwable
    //   436	441	442	java/lang/Throwable
    //   329	334	445	java/lang/Throwable
    //   338	342	445	java/lang/Throwable
    //   357	375	450	finally
    //   375	382	457	finally
    //   382	391	457	finally
    //   396	406	457	finally
    //   277	287	465	finally
    //   357	375	474	java/lang/Throwable
  }

  public static boolean b(File paramFile1, File paramFile2)
  {
    return a(new File[] { paramFile1 }, paramFile2);
  }

  // ERROR //
  private static boolean b(File paramFile1, File paramFile2, FileFilter paramFileFilter, FileUtil.FileComparator paramFileComparator)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: ifnull +7 -> 11
    //   7: aload_1
    //   8: ifnonnull +5 -> 13
    //   11: iconst_0
    //   12: ireturn
    //   13: aload_2
    //   14: ifnull +15 -> 29
    //   17: aload_2
    //   18: aload_0
    //   19: invokeinterface 237 2 0
    //   24: ifne +5 -> 29
    //   27: iconst_0
    //   28: ireturn
    //   29: aload_0
    //   30: invokevirtual 79	java/io/File:exists	()Z
    //   33: ifeq +14 -> 47
    //   36: aload_0
    //   37: invokevirtual 82	java/io/File:isFile	()Z
    //   40: istore 13
    //   42: iload 13
    //   44: ifne +21 -> 65
    //   47: iconst_0
    //   48: ifeq +7 -> 55
    //   51: aconst_null
    //   52: invokevirtual 240	java/nio/channels/FileChannel:close	()V
    //   55: iconst_0
    //   56: ifeq +7 -> 63
    //   59: aconst_null
    //   60: invokevirtual 240	java/nio/channels/FileChannel:close	()V
    //   63: iconst_0
    //   64: ireturn
    //   65: aload_1
    //   66: invokevirtual 79	java/io/File:exists	()Z
    //   69: ifeq +44 -> 113
    //   72: aload_3
    //   73: ifnull +36 -> 109
    //   76: aload_3
    //   77: aload_0
    //   78: aload_1
    //   79: invokeinterface 244 3 0
    //   84: istore 14
    //   86: iload 14
    //   88: ifeq +21 -> 109
    //   91: iconst_0
    //   92: ifeq +7 -> 99
    //   95: aconst_null
    //   96: invokevirtual 240	java/nio/channels/FileChannel:close	()V
    //   99: iconst_0
    //   100: ifeq +7 -> 107
    //   103: aconst_null
    //   104: invokevirtual 240	java/nio/channels/FileChannel:close	()V
    //   107: iconst_1
    //   108: ireturn
    //   109: aload_1
    //   110: invokestatic 210	com/tencent/component/utils/FileUtil:a	(Ljava/io/File;)V
    //   113: aload_1
    //   114: invokevirtual 221	java/io/File:getParentFile	()Ljava/io/File;
    //   117: astore 15
    //   119: aload 15
    //   121: invokevirtual 82	java/io/File:isFile	()Z
    //   124: ifeq +8 -> 132
    //   127: aload 15
    //   129: invokestatic 210	com/tencent/component/utils/FileUtil:a	(Ljava/io/File;)V
    //   132: aload 15
    //   134: invokevirtual 79	java/io/File:exists	()Z
    //   137: ifne +33 -> 170
    //   140: aload 15
    //   142: invokevirtual 224	java/io/File:mkdirs	()Z
    //   145: istore 23
    //   147: iload 23
    //   149: ifne +21 -> 170
    //   152: iconst_0
    //   153: ifeq +7 -> 160
    //   156: aconst_null
    //   157: invokevirtual 240	java/nio/channels/FileChannel:close	()V
    //   160: iconst_0
    //   161: ifeq +7 -> 168
    //   164: aconst_null
    //   165: invokevirtual 240	java/nio/channels/FileChannel:close	()V
    //   168: iconst_0
    //   169: ireturn
    //   170: new 111	java/io/FileInputStream
    //   173: dup
    //   174: aload_0
    //   175: invokespecial 113	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   178: invokevirtual 248	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   181: astore 16
    //   183: aload 16
    //   185: astore 6
    //   187: new 158	java/io/FileOutputStream
    //   190: dup
    //   191: aload_1
    //   192: invokespecial 159	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   195: invokevirtual 249	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   198: astore 17
    //   200: aload 17
    //   202: aload 6
    //   204: lconst_0
    //   205: aload 6
    //   207: invokevirtual 252	java/nio/channels/FileChannel:size	()J
    //   210: invokevirtual 256	java/nio/channels/FileChannel:transferFrom	(Ljava/nio/channels/ReadableByteChannel;JJ)J
    //   213: pop2
    //   214: aload 6
    //   216: ifnull +8 -> 224
    //   219: aload 6
    //   221: invokevirtual 240	java/nio/channels/FileChannel:close	()V
    //   224: aload 17
    //   226: ifnull +8 -> 234
    //   229: aload 17
    //   231: invokevirtual 240	java/nio/channels/FileChannel:close	()V
    //   234: iconst_1
    //   235: ireturn
    //   236: astore 8
    //   238: aconst_null
    //   239: astore 9
    //   241: aload 8
    //   243: invokevirtual 225	java/lang/Throwable:printStackTrace	()V
    //   246: aload_1
    //   247: invokestatic 210	com/tencent/component/utils/FileUtil:a	(Ljava/io/File;)V
    //   250: aload 4
    //   252: ifnull +8 -> 260
    //   255: aload 4
    //   257: invokevirtual 240	java/nio/channels/FileChannel:close	()V
    //   260: aload 9
    //   262: ifnull +8 -> 270
    //   265: aload 9
    //   267: invokevirtual 240	java/nio/channels/FileChannel:close	()V
    //   270: iconst_0
    //   271: ireturn
    //   272: astore 5
    //   274: aconst_null
    //   275: astore 6
    //   277: aload 6
    //   279: ifnull +8 -> 287
    //   282: aload 6
    //   284: invokevirtual 240	java/nio/channels/FileChannel:close	()V
    //   287: aload 4
    //   289: ifnull +8 -> 297
    //   292: aload 4
    //   294: invokevirtual 240	java/nio/channels/FileChannel:close	()V
    //   297: aload 5
    //   299: athrow
    //   300: astore 7
    //   302: goto -5 -> 297
    //   305: astore 5
    //   307: aconst_null
    //   308: astore 4
    //   310: goto -33 -> 277
    //   313: astore 19
    //   315: aload 17
    //   317: astore 4
    //   319: aload 19
    //   321: astore 5
    //   323: goto -46 -> 277
    //   326: astore 5
    //   328: aload 9
    //   330: astore 10
    //   332: aload 4
    //   334: astore 6
    //   336: aload 10
    //   338: astore 4
    //   340: goto -63 -> 277
    //   343: astore 11
    //   345: goto -75 -> 270
    //   348: astore 8
    //   350: aload 6
    //   352: astore 4
    //   354: aconst_null
    //   355: astore 9
    //   357: goto -116 -> 241
    //   360: astore 18
    //   362: aload 6
    //   364: astore 4
    //   366: aload 17
    //   368: astore 9
    //   370: aload 18
    //   372: astore 8
    //   374: goto -133 -> 241
    //   377: astore 22
    //   379: goto -145 -> 234
    //   382: astore 24
    //   384: goto -216 -> 168
    //   387: astore 25
    //   389: goto -282 -> 107
    //   392: astore 12
    //   394: goto -331 -> 63
    //
    // Exception table:
    //   from	to	target	type
    //   29	42	236	java/lang/Throwable
    //   65	72	236	java/lang/Throwable
    //   76	86	236	java/lang/Throwable
    //   109	113	236	java/lang/Throwable
    //   113	132	236	java/lang/Throwable
    //   132	147	236	java/lang/Throwable
    //   170	183	236	java/lang/Throwable
    //   29	42	272	finally
    //   65	72	272	finally
    //   76	86	272	finally
    //   109	113	272	finally
    //   113	132	272	finally
    //   132	147	272	finally
    //   170	183	272	finally
    //   282	287	300	java/lang/Throwable
    //   292	297	300	java/lang/Throwable
    //   187	200	305	finally
    //   200	214	313	finally
    //   241	250	326	finally
    //   255	260	343	java/lang/Throwable
    //   265	270	343	java/lang/Throwable
    //   187	200	348	java/lang/Throwable
    //   200	214	360	java/lang/Throwable
    //   219	224	377	java/lang/Throwable
    //   229	234	377	java/lang/Throwable
    //   156	160	382	java/lang/Throwable
    //   164	168	382	java/lang/Throwable
    //   95	99	387	java/lang/Throwable
    //   103	107	387	java/lang/Throwable
    //   51	55	392	java/lang/Throwable
    //   59	63	392	java/lang/Throwable
  }

  // ERROR //
  public static boolean c(File paramFile1, File paramFile2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: ifnull +19 -> 22
    //   6: aload_0
    //   7: invokevirtual 195	java/io/File:length	()J
    //   10: lconst_1
    //   11: lcmp
    //   12: iflt +10 -> 22
    //   15: aload_0
    //   16: invokevirtual 260	java/io/File:canRead	()Z
    //   19: ifne +5 -> 24
    //   22: iconst_0
    //   23: ireturn
    //   24: aload_1
    //   25: invokevirtual 79	java/io/File:exists	()Z
    //   28: ifne +8 -> 36
    //   31: aload_1
    //   32: invokevirtual 224	java/io/File:mkdirs	()Z
    //   35: pop
    //   36: sipush 8192
    //   39: newarray byte
    //   41: astore_3
    //   42: new 262	java/util/zip/ZipInputStream
    //   45: dup
    //   46: new 111	java/io/FileInputStream
    //   49: dup
    //   50: aload_0
    //   51: invokespecial 113	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   54: invokespecial 263	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   57: astore 4
    //   59: aload 4
    //   61: invokevirtual 267	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   64: astore 12
    //   66: aload 12
    //   68: ifnull +148 -> 216
    //   71: getstatic 273	java/lang/System:out	Ljava/io/PrintStream;
    //   74: aload 12
    //   76: invokevirtual 274	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   79: invokevirtual 279	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   82: aload 12
    //   84: invokevirtual 280	java/util/zip/ZipEntry:isDirectory	()Z
    //   87: ifeq +42 -> 129
    //   90: new 61	java/io/File
    //   93: dup
    //   94: aload_1
    //   95: aload 12
    //   97: invokevirtual 274	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   100: invokespecial 155	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   103: invokevirtual 224	java/io/File:mkdirs	()Z
    //   106: pop
    //   107: goto -48 -> 59
    //   110: astore 8
    //   112: aload 4
    //   114: astore 9
    //   116: aload_2
    //   117: invokestatic 138	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   120: pop
    //   121: aload 9
    //   123: invokestatic 138	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   126: pop
    //   127: iconst_0
    //   128: ireturn
    //   129: new 61	java/io/File
    //   132: dup
    //   133: aload_1
    //   134: aload 12
    //   136: invokevirtual 274	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   139: invokespecial 155	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   142: astore 13
    //   144: aload 13
    //   146: invokevirtual 221	java/io/File:getParentFile	()Ljava/io/File;
    //   149: invokevirtual 224	java/io/File:mkdirs	()Z
    //   152: pop
    //   153: new 175	java/io/BufferedOutputStream
    //   156: dup
    //   157: new 158	java/io/FileOutputStream
    //   160: dup
    //   161: aload 13
    //   163: invokespecial 159	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   166: invokespecial 178	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   169: astore 15
    //   171: aload 4
    //   173: aload_3
    //   174: iconst_0
    //   175: aload_3
    //   176: arraylength
    //   177: invokevirtual 281	java/util/zip/ZipInputStream:read	([BII)I
    //   180: istore 17
    //   182: iconst_m1
    //   183: iload 17
    //   185: if_icmpeq +15 -> 200
    //   188: aload 15
    //   190: aload_3
    //   191: iconst_0
    //   192: iload 17
    //   194: invokevirtual 282	java/io/BufferedOutputStream:write	([BII)V
    //   197: goto -26 -> 171
    //   200: aload 15
    //   202: invokevirtual 283	java/io/BufferedOutputStream:flush	()V
    //   205: aload 15
    //   207: invokevirtual 284	java/io/BufferedOutputStream:close	()V
    //   210: aload 15
    //   212: astore_2
    //   213: goto -154 -> 59
    //   216: aload 4
    //   218: invokevirtual 285	java/util/zip/ZipInputStream:closeEntry	()V
    //   221: aload 4
    //   223: invokevirtual 286	java/util/zip/ZipInputStream:close	()V
    //   226: aload_2
    //   227: invokestatic 138	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   230: pop
    //   231: aload 4
    //   233: invokestatic 138	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   236: pop
    //   237: iconst_1
    //   238: ireturn
    //   239: astore 5
    //   241: aconst_null
    //   242: astore 4
    //   244: aload_2
    //   245: invokestatic 138	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   248: pop
    //   249: aload 4
    //   251: invokestatic 138	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   254: pop
    //   255: aload 5
    //   257: athrow
    //   258: astore 5
    //   260: aload 15
    //   262: astore_2
    //   263: goto -19 -> 244
    //   266: astore 5
    //   268: goto -24 -> 244
    //   271: astore 21
    //   273: aconst_null
    //   274: astore_2
    //   275: aconst_null
    //   276: astore 9
    //   278: goto -162 -> 116
    //   281: astore 16
    //   283: aload 15
    //   285: astore_2
    //   286: aload 4
    //   288: astore 9
    //   290: goto -174 -> 116
    //
    // Exception table:
    //   from	to	target	type
    //   59	66	110	java/io/IOException
    //   71	107	110	java/io/IOException
    //   129	171	110	java/io/IOException
    //   216	226	110	java/io/IOException
    //   42	59	239	finally
    //   171	182	258	finally
    //   188	197	258	finally
    //   200	210	258	finally
    //   59	66	266	finally
    //   71	107	266	finally
    //   129	171	266	finally
    //   216	226	266	finally
    //   42	59	271	java/io/IOException
    //   171	182	281	java/io/IOException
    //   188	197	281	java/io/IOException
    //   200	210	281	java/io/IOException
  }

  // ERROR //
  public static boolean d(File paramFile1, File paramFile2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: ifnull +19 -> 22
    //   6: aload_0
    //   7: invokevirtual 195	java/io/File:length	()J
    //   10: lconst_1
    //   11: lcmp
    //   12: iflt +10 -> 22
    //   15: aload_0
    //   16: invokevirtual 260	java/io/File:canRead	()Z
    //   19: ifne +5 -> 24
    //   22: iconst_0
    //   23: ireturn
    //   24: aload_1
    //   25: invokevirtual 79	java/io/File:exists	()Z
    //   28: ifne +8 -> 36
    //   31: aload_1
    //   32: invokevirtual 224	java/io/File:mkdirs	()Z
    //   35: pop
    //   36: sipush 8192
    //   39: newarray byte
    //   41: astore_3
    //   42: new 289	java/util/jar/JarInputStream
    //   45: dup
    //   46: new 111	java/io/FileInputStream
    //   49: dup
    //   50: aload_0
    //   51: invokespecial 113	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   54: invokespecial 290	java/util/jar/JarInputStream:<init>	(Ljava/io/InputStream;)V
    //   57: astore 4
    //   59: aload 4
    //   61: invokevirtual 294	java/util/jar/JarInputStream:getNextJarEntry	()Ljava/util/jar/JarEntry;
    //   64: astore 12
    //   66: aload 12
    //   68: ifnull +135 -> 203
    //   71: getstatic 273	java/lang/System:out	Ljava/io/PrintStream;
    //   74: aload 12
    //   76: invokevirtual 297	java/util/jar/JarEntry:getName	()Ljava/lang/String;
    //   79: invokevirtual 279	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   82: aload 12
    //   84: invokevirtual 298	java/util/jar/JarEntry:isDirectory	()Z
    //   87: ifeq +42 -> 129
    //   90: new 61	java/io/File
    //   93: dup
    //   94: aload_1
    //   95: aload 12
    //   97: invokevirtual 297	java/util/jar/JarEntry:getName	()Ljava/lang/String;
    //   100: invokespecial 155	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   103: invokevirtual 224	java/io/File:mkdirs	()Z
    //   106: pop
    //   107: goto -48 -> 59
    //   110: astore 8
    //   112: aload 4
    //   114: astore 9
    //   116: aload_2
    //   117: invokestatic 138	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   120: pop
    //   121: aload 9
    //   123: invokestatic 138	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   126: pop
    //   127: iconst_0
    //   128: ireturn
    //   129: new 175	java/io/BufferedOutputStream
    //   132: dup
    //   133: new 158	java/io/FileOutputStream
    //   136: dup
    //   137: new 61	java/io/File
    //   140: dup
    //   141: aload_1
    //   142: aload 12
    //   144: invokevirtual 297	java/util/jar/JarEntry:getName	()Ljava/lang/String;
    //   147: invokespecial 155	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   150: invokespecial 159	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   153: invokespecial 178	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   156: astore 13
    //   158: aload 4
    //   160: aload_3
    //   161: iconst_0
    //   162: aload_3
    //   163: arraylength
    //   164: invokevirtual 299	java/util/jar/JarInputStream:read	([BII)I
    //   167: istore 15
    //   169: iconst_m1
    //   170: iload 15
    //   172: if_icmpeq +15 -> 187
    //   175: aload 13
    //   177: aload_3
    //   178: iconst_0
    //   179: iload 15
    //   181: invokevirtual 282	java/io/BufferedOutputStream:write	([BII)V
    //   184: goto -26 -> 158
    //   187: aload 13
    //   189: invokevirtual 283	java/io/BufferedOutputStream:flush	()V
    //   192: aload 13
    //   194: invokevirtual 284	java/io/BufferedOutputStream:close	()V
    //   197: aload 13
    //   199: astore_2
    //   200: goto -141 -> 59
    //   203: aload 4
    //   205: invokevirtual 300	java/util/jar/JarInputStream:closeEntry	()V
    //   208: aload 4
    //   210: invokevirtual 301	java/util/jar/JarInputStream:close	()V
    //   213: aload_2
    //   214: invokestatic 138	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   217: pop
    //   218: aload 4
    //   220: invokestatic 138	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   223: pop
    //   224: iconst_1
    //   225: ireturn
    //   226: astore 5
    //   228: aconst_null
    //   229: astore 4
    //   231: aload_2
    //   232: invokestatic 138	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   235: pop
    //   236: aload 4
    //   238: invokestatic 138	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   241: pop
    //   242: aload 5
    //   244: athrow
    //   245: astore 5
    //   247: aload 13
    //   249: astore_2
    //   250: goto -19 -> 231
    //   253: astore 5
    //   255: goto -24 -> 231
    //   258: astore 19
    //   260: aconst_null
    //   261: astore_2
    //   262: aconst_null
    //   263: astore 9
    //   265: goto -149 -> 116
    //   268: astore 14
    //   270: aload 13
    //   272: astore_2
    //   273: aload 4
    //   275: astore 9
    //   277: goto -161 -> 116
    //
    // Exception table:
    //   from	to	target	type
    //   59	66	110	java/io/IOException
    //   71	107	110	java/io/IOException
    //   129	158	110	java/io/IOException
    //   203	213	110	java/io/IOException
    //   42	59	226	finally
    //   158	169	245	finally
    //   175	184	245	finally
    //   187	197	245	finally
    //   59	66	253	finally
    //   71	107	253	finally
    //   129	158	253	finally
    //   203	213	253	finally
    //   42	59	258	java/io/IOException
    //   158	169	268	java/io/IOException
    //   175	184	268	java/io/IOException
    //   187	197	268	java/io/IOException
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.FileUtil
 * JD-Core Version:    0.6.0
 */