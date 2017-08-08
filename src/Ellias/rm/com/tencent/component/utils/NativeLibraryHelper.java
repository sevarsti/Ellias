package com.tencent.component.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Build.VERSION;
import com.tencent.component.utils.log.LogUtil;
import java.io.File;

public class NativeLibraryHelper
{
  private static final String a = "NativeLibraryHelper";
  private static final String b = "lib/";
  private static final int c = 0;
  private static final String d = "/lib";
  private static final int e = 0;
  private static final String f = ".so";
  private static final int g = ".so".length();

  public static boolean a(File paramFile)
  {
    boolean bool = paramFile.exists();
    int i = 0;
    if (bool)
    {
      File[] arrayOfFile = paramFile.listFiles();
      i = 0;
      if (arrayOfFile != null)
      {
        int j = 0;
        if (i < arrayOfFile.length)
        {
          if (!arrayOfFile[i].delete())
            LogUtil.w("NativeLibraryHelper", "Could not delete native binary: " + arrayOfFile[i].getPath());
          while (true)
          {
            i++;
            break;
            j = 1;
          }
        }
        i = j;
      }
    }
    return i;
  }

  public static boolean a(File paramFile1, File paramFile2)
  {
    return a(paramFile1.getPath(), paramFile2.getPath());
  }

  public static boolean a(String paramString)
  {
    return a(new File(paramString));
  }

  // ERROR //
  private static boolean a(String paramString, long paramLong1, long paramLong2, long paramLong3)
  {
    // Byte code:
    //   0: new 42	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 86	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore 7
    //   10: aload 7
    //   12: invokevirtual 96	java/io/File:length	()J
    //   15: lload_1
    //   16: lcmp
    //   17: ifeq +42 -> 59
    //   20: ldc 8
    //   22: new 55	java/lang/StringBuilder
    //   25: dup
    //   26: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   29: ldc 98
    //   31: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: aload 7
    //   36: invokevirtual 96	java/io/File:length	()J
    //   39: invokevirtual 101	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   42: ldc 103
    //   44: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: lload_1
    //   48: invokevirtual 101	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   51: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: invokestatic 106	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   57: iconst_1
    //   58: ireturn
    //   59: aload 7
    //   61: invokevirtual 109	java/io/File:lastModified	()J
    //   64: lload_3
    //   65: lcmp
    //   66: ifeq +42 -> 108
    //   69: ldc 8
    //   71: new 55	java/lang/StringBuilder
    //   74: dup
    //   75: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   78: ldc 111
    //   80: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: aload 7
    //   85: invokevirtual 109	java/io/File:lastModified	()J
    //   88: invokevirtual 101	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   91: ldc 103
    //   93: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: lload_3
    //   97: invokevirtual 101	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   100: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: invokestatic 106	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   106: iconst_1
    //   107: ireturn
    //   108: new 113	java/io/FileInputStream
    //   111: dup
    //   112: aload 7
    //   114: invokespecial 116	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   117: astore 8
    //   119: new 118	java/util/zip/CRC32
    //   122: dup
    //   123: invokespecial 119	java/util/zip/CRC32:<init>	()V
    //   126: astore 9
    //   128: sipush 8192
    //   131: newarray byte
    //   133: astore 16
    //   135: aload 8
    //   137: aload 16
    //   139: invokevirtual 123	java/io/FileInputStream:read	([B)I
    //   142: istore 17
    //   144: iload 17
    //   146: ifle +60 -> 206
    //   149: aload 9
    //   151: aload 16
    //   153: iconst_0
    //   154: iload 17
    //   156: invokevirtual 127	java/util/zip/CRC32:update	([BII)V
    //   159: goto -24 -> 135
    //   162: astore 14
    //   164: ldc 8
    //   166: new 55	java/lang/StringBuilder
    //   169: dup
    //   170: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   173: ldc 129
    //   175: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: aload_0
    //   179: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   185: aload 14
    //   187: invokestatic 132	com/tencent/component/utils/log/LogUtil:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   190: aload 8
    //   192: ifnull -135 -> 57
    //   195: aload 8
    //   197: invokevirtual 135	java/io/FileInputStream:close	()V
    //   200: iconst_1
    //   201: ireturn
    //   202: astore 15
    //   204: iconst_1
    //   205: ireturn
    //   206: aload 9
    //   208: invokevirtual 138	java/util/zip/CRC32:getValue	()J
    //   211: lstore 18
    //   213: ldc 8
    //   215: new 55	java/lang/StringBuilder
    //   218: dup
    //   219: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   222: aload_0
    //   223: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: ldc 140
    //   228: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: lload 18
    //   233: invokevirtual 101	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   236: ldc 142
    //   238: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   241: lload 5
    //   243: invokevirtual 101	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   246: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   249: invokestatic 106	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   252: lload 18
    //   254: lload 5
    //   256: lcmp
    //   257: ifeq +19 -> 276
    //   260: aload 8
    //   262: ifnull -205 -> 57
    //   265: aload 8
    //   267: invokevirtual 135	java/io/FileInputStream:close	()V
    //   270: iconst_1
    //   271: ireturn
    //   272: astore 21
    //   274: iconst_1
    //   275: ireturn
    //   276: aload 8
    //   278: ifnull +8 -> 286
    //   281: aload 8
    //   283: invokevirtual 135	java/io/FileInputStream:close	()V
    //   286: iconst_0
    //   287: ireturn
    //   288: astore 10
    //   290: aconst_null
    //   291: astore 8
    //   293: ldc 8
    //   295: new 55	java/lang/StringBuilder
    //   298: dup
    //   299: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   302: ldc 144
    //   304: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   307: aload_0
    //   308: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   311: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   314: aload 10
    //   316: invokestatic 132	com/tencent/component/utils/log/LogUtil:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   319: aload 8
    //   321: ifnull -264 -> 57
    //   324: aload 8
    //   326: invokevirtual 135	java/io/FileInputStream:close	()V
    //   329: iconst_1
    //   330: ireturn
    //   331: astore 13
    //   333: iconst_1
    //   334: ireturn
    //   335: astore 11
    //   337: aconst_null
    //   338: astore 8
    //   340: aload 8
    //   342: ifnull +8 -> 350
    //   345: aload 8
    //   347: invokevirtual 135	java/io/FileInputStream:close	()V
    //   350: aload 11
    //   352: athrow
    //   353: astore 20
    //   355: goto -69 -> 286
    //   358: astore 12
    //   360: goto -10 -> 350
    //   363: astore 11
    //   365: goto -25 -> 340
    //   368: astore 10
    //   370: goto -77 -> 293
    //   373: astore 14
    //   375: aconst_null
    //   376: astore 8
    //   378: goto -214 -> 164
    //
    // Exception table:
    //   from	to	target	type
    //   119	135	162	java/io/FileNotFoundException
    //   135	144	162	java/io/FileNotFoundException
    //   149	159	162	java/io/FileNotFoundException
    //   206	252	162	java/io/FileNotFoundException
    //   195	200	202	java/io/IOException
    //   265	270	272	java/io/IOException
    //   108	119	288	java/io/IOException
    //   324	329	331	java/io/IOException
    //   108	119	335	finally
    //   281	286	353	java/io/IOException
    //   345	350	358	java/io/IOException
    //   119	135	363	finally
    //   135	144	363	finally
    //   149	159	363	finally
    //   164	190	363	finally
    //   206	252	363	finally
    //   293	319	363	finally
    //   119	135	368	java/io/IOException
    //   135	144	368	java/io/IOException
    //   149	159	368	java/io/IOException
    //   206	252	368	java/io/IOException
    //   108	119	373	java/io/FileNotFoundException
  }

  @SuppressLint({"InlinedApi"})
  public static boolean a(String paramString1, String paramString2)
  {
    String str1 = Build.CPU_ABI;
    if (Build.VERSION.SDK_INT >= 8);
    for (String str2 = Build.CPU_ABI2; ; str2 = null)
      return a(paramString1, paramString2, str1, str2, PropertyUtils.b("ro.product.cpu.upgradeabi", "armeabi"));
  }

  // ERROR //
  private static boolean a(String paramString1, String paramString2, String paramString3, String paramString4, d paramd)
  {
    // Byte code:
    //   0: new 175	java/util/zip/ZipFile
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 176	java/util/zip/ZipFile:<init>	(Ljava/lang/String;)V
    //   8: astore 5
    //   10: iconst_0
    //   11: istore 6
    //   13: iconst_0
    //   14: istore 7
    //   16: aload 5
    //   18: invokevirtual 180	java/util/zip/ZipFile:entries	()Ljava/util/Enumeration;
    //   21: astore 12
    //   23: aload 12
    //   25: invokeinterface 185 1 0
    //   30: ifeq +552 -> 582
    //   33: aload 12
    //   35: invokeinterface 189 1 0
    //   40: checkcast 191	java/util/zip/ZipEntry
    //   43: astore 14
    //   45: aload 14
    //   47: invokevirtual 194	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   50: astore 15
    //   52: aload 15
    //   54: ifnull -31 -> 23
    //   57: aload 15
    //   59: ldc 11
    //   61: invokevirtual 197	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   64: ifeq -41 -> 23
    //   67: aload 15
    //   69: invokevirtual 30	java/lang/String:length	()I
    //   72: iconst_1
    //   73: iconst_2
    //   74: getstatic 32	com/tencent/component/utils/NativeLibraryHelper:c	I
    //   77: iadd
    //   78: getstatic 34	com/tencent/component/utils/NativeLibraryHelper:e	I
    //   81: iadd
    //   82: iadd
    //   83: getstatic 36	com/tencent/component/utils/NativeLibraryHelper:g	I
    //   86: iadd
    //   87: if_icmplt -64 -> 23
    //   90: aload 15
    //   92: aload 15
    //   94: bipush 47
    //   96: invokevirtual 201	java/lang/String:lastIndexOf	(I)I
    //   99: invokevirtual 205	java/lang/String:substring	(I)Ljava/lang/String;
    //   102: astore 16
    //   104: aload 16
    //   106: ldc 21
    //   108: invokevirtual 208	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   111: ifeq -88 -> 23
    //   114: aload 16
    //   116: ldc 17
    //   118: invokevirtual 197	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   121: ifeq -98 -> 23
    //   124: aload 15
    //   126: getstatic 32	com/tencent/component/utils/NativeLibraryHelper:c	I
    //   129: aload_1
    //   130: iconst_0
    //   131: aload_1
    //   132: invokevirtual 30	java/lang/String:length	()I
    //   135: invokevirtual 212	java/lang/String:regionMatches	(ILjava/lang/String;II)Z
    //   138: ifeq +100 -> 238
    //   141: aload 15
    //   143: getstatic 32	com/tencent/component/utils/NativeLibraryHelper:c	I
    //   146: aload_1
    //   147: invokevirtual 30	java/lang/String:length	()I
    //   150: iadd
    //   151: invokevirtual 216	java/lang/String:charAt	(I)C
    //   154: bipush 47
    //   156: if_icmpne +82 -> 238
    //   159: iconst_1
    //   160: istore 6
    //   162: aload 5
    //   164: aload 14
    //   166: invokevirtual 220	java/util/zip/ZipFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   169: astore 17
    //   171: aload 4
    //   173: aload 17
    //   175: aload 14
    //   177: aload 16
    //   179: iconst_1
    //   180: invokevirtual 205	java/lang/String:substring	(I)Ljava/lang/String;
    //   183: invokeinterface 225 4 0
    //   188: ifne +366 -> 554
    //   191: ldc 8
    //   193: new 55	java/lang/StringBuilder
    //   196: dup
    //   197: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   200: ldc 227
    //   202: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: aload 15
    //   207: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   210: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   213: invokestatic 75	com/tencent/component/utils/log/LogUtil:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   216: aload 17
    //   218: ifnull +8 -> 226
    //   221: aload 17
    //   223: invokevirtual 230	java/io/InputStream:close	()V
    //   226: aload 5
    //   228: ifnull +8 -> 236
    //   231: aload 5
    //   233: invokevirtual 231	java/util/zip/ZipFile:close	()V
    //   236: iconst_0
    //   237: ireturn
    //   238: aload_2
    //   239: ifnull +157 -> 396
    //   242: aload 15
    //   244: getstatic 32	com/tencent/component/utils/NativeLibraryHelper:c	I
    //   247: aload_2
    //   248: iconst_0
    //   249: aload_2
    //   250: invokevirtual 30	java/lang/String:length	()I
    //   253: invokevirtual 212	java/lang/String:regionMatches	(ILjava/lang/String;II)Z
    //   256: ifeq +140 -> 396
    //   259: aload 15
    //   261: getstatic 32	com/tencent/component/utils/NativeLibraryHelper:c	I
    //   264: aload_2
    //   265: invokevirtual 30	java/lang/String:length	()I
    //   268: iadd
    //   269: invokevirtual 216	java/lang/String:charAt	(I)C
    //   272: bipush 47
    //   274: if_icmpne +122 -> 396
    //   277: iconst_1
    //   278: istore 7
    //   280: iload 6
    //   282: ifeq +72 -> 354
    //   285: ldc 8
    //   287: new 55	java/lang/StringBuilder
    //   290: dup
    //   291: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   294: ldc 233
    //   296: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   299: aload_2
    //   300: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   306: invokestatic 106	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   309: goto -286 -> 23
    //   312: astore 10
    //   314: ldc 8
    //   316: new 55	java/lang/StringBuilder
    //   319: dup
    //   320: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   323: ldc 235
    //   325: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: aload_0
    //   329: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   332: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   335: invokestatic 75	com/tencent/component/utils/log/LogUtil:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   338: aload 5
    //   340: ifnull -104 -> 236
    //   343: aload 5
    //   345: invokevirtual 231	java/util/zip/ZipFile:close	()V
    //   348: iconst_0
    //   349: ireturn
    //   350: astore 11
    //   352: iconst_0
    //   353: ireturn
    //   354: ldc 8
    //   356: new 55	java/lang/StringBuilder
    //   359: dup
    //   360: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   363: ldc 237
    //   365: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   368: aload_2
    //   369: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   375: invokestatic 106	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   378: goto -216 -> 162
    //   381: astore 8
    //   383: aload 5
    //   385: ifnull +8 -> 393
    //   388: aload 5
    //   390: invokevirtual 231	java/util/zip/ZipFile:close	()V
    //   393: aload 8
    //   395: athrow
    //   396: aload_3
    //   397: ifnull +102 -> 499
    //   400: aload 15
    //   402: getstatic 32	com/tencent/component/utils/NativeLibraryHelper:c	I
    //   405: aload_3
    //   406: iconst_0
    //   407: aload_3
    //   408: invokevirtual 30	java/lang/String:length	()I
    //   411: invokevirtual 212	java/lang/String:regionMatches	(ILjava/lang/String;II)Z
    //   414: ifeq +85 -> 499
    //   417: aload 15
    //   419: getstatic 32	com/tencent/component/utils/NativeLibraryHelper:c	I
    //   422: aload_3
    //   423: invokevirtual 30	java/lang/String:length	()I
    //   426: iadd
    //   427: invokevirtual 216	java/lang/String:charAt	(I)C
    //   430: bipush 47
    //   432: if_icmpne +67 -> 499
    //   435: iload 6
    //   437: ifne +8 -> 445
    //   440: iload 7
    //   442: ifeq +30 -> 472
    //   445: ldc 8
    //   447: new 55	java/lang/StringBuilder
    //   450: dup
    //   451: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   454: ldc 239
    //   456: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: aload_3
    //   460: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   463: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   466: invokestatic 106	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   469: goto -446 -> 23
    //   472: ldc 8
    //   474: new 55	java/lang/StringBuilder
    //   477: dup
    //   478: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   481: ldc 241
    //   483: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: aload_3
    //   487: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   490: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   493: invokestatic 106	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   496: goto -334 -> 162
    //   499: ldc 8
    //   501: new 55	java/lang/StringBuilder
    //   504: dup
    //   505: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   508: ldc 243
    //   510: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   513: aload 15
    //   515: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   518: ldc 245
    //   520: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: aload_1
    //   524: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   527: ldc 247
    //   529: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   532: aload_2
    //   533: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   536: ldc 249
    //   538: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   541: aload_3
    //   542: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   545: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   548: invokestatic 106	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   551: goto -528 -> 23
    //   554: aload 17
    //   556: ifnull -533 -> 23
    //   559: aload 17
    //   561: invokevirtual 230	java/io/InputStream:close	()V
    //   564: goto -541 -> 23
    //   567: astore 18
    //   569: aload 17
    //   571: ifnull +8 -> 579
    //   574: aload 17
    //   576: invokevirtual 230	java/io/InputStream:close	()V
    //   579: aload 18
    //   581: athrow
    //   582: aload 5
    //   584: ifnull +8 -> 592
    //   587: aload 5
    //   589: invokevirtual 231	java/util/zip/ZipFile:close	()V
    //   592: iconst_1
    //   593: ireturn
    //   594: astore 19
    //   596: iconst_0
    //   597: ireturn
    //   598: astore 13
    //   600: goto -8 -> 592
    //   603: astore 9
    //   605: goto -212 -> 393
    //   608: astore 21
    //   610: aload 21
    //   612: astore 8
    //   614: aconst_null
    //   615: astore 5
    //   617: goto -234 -> 383
    //   620: astore 20
    //   622: aconst_null
    //   623: astore 5
    //   625: goto -311 -> 314
    //
    // Exception table:
    //   from	to	target	type
    //   16	23	312	java/io/IOException
    //   23	52	312	java/io/IOException
    //   57	159	312	java/io/IOException
    //   162	171	312	java/io/IOException
    //   221	226	312	java/io/IOException
    //   242	277	312	java/io/IOException
    //   285	309	312	java/io/IOException
    //   354	378	312	java/io/IOException
    //   400	435	312	java/io/IOException
    //   445	469	312	java/io/IOException
    //   472	496	312	java/io/IOException
    //   499	551	312	java/io/IOException
    //   559	564	312	java/io/IOException
    //   574	579	312	java/io/IOException
    //   579	582	312	java/io/IOException
    //   343	348	350	java/io/IOException
    //   16	23	381	finally
    //   23	52	381	finally
    //   57	159	381	finally
    //   162	171	381	finally
    //   221	226	381	finally
    //   242	277	381	finally
    //   285	309	381	finally
    //   314	338	381	finally
    //   354	378	381	finally
    //   400	435	381	finally
    //   445	469	381	finally
    //   472	496	381	finally
    //   499	551	381	finally
    //   559	564	381	finally
    //   574	579	381	finally
    //   579	582	381	finally
    //   171	216	567	finally
    //   231	236	594	java/io/IOException
    //   587	592	598	java/io/IOException
    //   388	393	603	java/io/IOException
    //   0	10	608	finally
    //   0	10	620	java/io/IOException
  }

  private static boolean a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    return a(paramString1, paramString3, paramString4, paramString5, new c(paramString2));
  }

  private static boolean b(File paramFile)
  {
    if (!paramFile.exists())
      return paramFile.mkdirs();
    if (!paramFile.isDirectory())
    {
      FileUtil.a(paramFile);
      return paramFile.mkdirs();
    }
    return true;
  }

  // ERROR //
  private static boolean b(java.io.InputStream paramInputStream, java.util.zip.ZipEntry paramZipEntry, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: new 42	java/io/File
    //   3: dup
    //   4: aload_2
    //   5: invokespecial 86	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: invokestatic 266	com/tencent/component/utils/NativeLibraryHelper:b	(Ljava/io/File;)Z
    //   11: pop
    //   12: new 55	java/lang/StringBuilder
    //   15: dup
    //   16: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   19: aload_2
    //   20: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: getstatic 269	java/io/File:separator	Ljava/lang/String;
    //   26: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: aload_3
    //   30: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   36: astore 5
    //   38: aload 5
    //   40: aload_1
    //   41: invokevirtual 272	java/util/zip/ZipEntry:getSize	()J
    //   44: aload_1
    //   45: invokevirtual 275	java/util/zip/ZipEntry:getTime	()J
    //   48: aload_1
    //   49: invokevirtual 278	java/util/zip/ZipEntry:getCrc	()J
    //   52: invokestatic 280	com/tencent/component/utils/NativeLibraryHelper:a	(Ljava/lang/String;JJJ)Z
    //   55: ifne +5 -> 60
    //   58: iconst_1
    //   59: ireturn
    //   60: new 42	java/io/File
    //   63: dup
    //   64: aload 5
    //   66: invokespecial 86	java/io/File:<init>	(Ljava/lang/String;)V
    //   69: astore 6
    //   71: new 282	java/io/FileOutputStream
    //   74: dup
    //   75: aload 6
    //   77: invokespecial 283	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   80: astore 7
    //   82: sipush 1024
    //   85: newarray byte
    //   87: astore 12
    //   89: aload_0
    //   90: aload 12
    //   92: invokevirtual 284	java/io/InputStream:read	([B)I
    //   95: istore 13
    //   97: iload 13
    //   99: ifle +63 -> 162
    //   102: aload 7
    //   104: aload 12
    //   106: iconst_0
    //   107: iload 13
    //   109: invokevirtual 287	java/io/FileOutputStream:write	([BII)V
    //   112: goto -23 -> 89
    //   115: astore 10
    //   117: ldc 8
    //   119: new 55	java/lang/StringBuilder
    //   122: dup
    //   123: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   126: ldc_w 289
    //   129: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: aload 6
    //   134: invokevirtual 292	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   137: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   140: aload 10
    //   142: invokestatic 132	com/tencent/component/utils/log/LogUtil:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   145: aload 6
    //   147: invokestatic 264	com/tencent/component/utils/FileUtil:a	(Ljava/io/File;)V
    //   150: aload 7
    //   152: ifnull +8 -> 160
    //   155: aload 7
    //   157: invokevirtual 293	java/io/FileOutputStream:close	()V
    //   160: iconst_0
    //   161: ireturn
    //   162: aload 7
    //   164: ifnull +8 -> 172
    //   167: aload 7
    //   169: invokevirtual 293	java/io/FileOutputStream:close	()V
    //   172: aload 6
    //   174: aload_1
    //   175: invokevirtual 275	java/util/zip/ZipEntry:getTime	()J
    //   178: invokevirtual 297	java/io/File:setLastModified	(J)Z
    //   181: ifne +29 -> 210
    //   184: ldc 8
    //   186: new 55	java/lang/StringBuilder
    //   189: dup
    //   190: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   193: ldc_w 299
    //   196: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: aload 6
    //   201: invokevirtual 292	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   204: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   207: invokestatic 75	com/tencent/component/utils/log/LogUtil:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   210: iconst_1
    //   211: ireturn
    //   212: astore 8
    //   214: aconst_null
    //   215: astore 7
    //   217: aload 7
    //   219: ifnull +8 -> 227
    //   222: aload 7
    //   224: invokevirtual 293	java/io/FileOutputStream:close	()V
    //   227: aload 8
    //   229: athrow
    //   230: astore 14
    //   232: goto -60 -> 172
    //   235: astore 11
    //   237: goto -77 -> 160
    //   240: astore 9
    //   242: goto -15 -> 227
    //   245: astore 8
    //   247: goto -30 -> 217
    //   250: astore 10
    //   252: aconst_null
    //   253: astore 7
    //   255: goto -138 -> 117
    //
    // Exception table:
    //   from	to	target	type
    //   82	89	115	java/io/IOException
    //   89	97	115	java/io/IOException
    //   102	112	115	java/io/IOException
    //   71	82	212	finally
    //   167	172	230	java/io/IOException
    //   155	160	235	java/io/IOException
    //   222	227	240	java/io/IOException
    //   82	89	245	finally
    //   89	97	245	finally
    //   102	112	245	finally
    //   117	150	245	finally
    //   71	82	250	java/io/IOException
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.NativeLibraryHelper
 * JD-Core Version:    0.6.0
 */