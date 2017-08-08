package com.tencent.component.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import com.tencent.component.app.ExceptionManager;
import com.tencent.component.utils.log.LogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ImageUtil
{
  private static final String a = "ImageUtil";

  // ERROR //
  public static final Bitmap a(android.content.Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 21	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   4: invokevirtual 27	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   7: aload_1
    //   8: invokevirtual 33	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   11: astore 9
    //   13: aload 9
    //   15: astore_3
    //   16: aload_3
    //   17: invokestatic 39	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   20: astore 11
    //   22: aload 11
    //   24: astore 7
    //   26: aload_3
    //   27: ifnull +7 -> 34
    //   30: aload_3
    //   31: invokevirtual 44	java/io/InputStream:close	()V
    //   34: aload 7
    //   36: areturn
    //   37: astore 6
    //   39: aconst_null
    //   40: astore_3
    //   41: aconst_null
    //   42: astore 7
    //   44: aload_3
    //   45: ifnull -11 -> 34
    //   48: aload_3
    //   49: invokevirtual 44	java/io/InputStream:close	()V
    //   52: aconst_null
    //   53: areturn
    //   54: astore 8
    //   56: aconst_null
    //   57: areturn
    //   58: astore_2
    //   59: aconst_null
    //   60: astore_3
    //   61: aload_2
    //   62: astore 4
    //   64: aload_3
    //   65: ifnull +7 -> 72
    //   68: aload_3
    //   69: invokevirtual 44	java/io/InputStream:close	()V
    //   72: aload 4
    //   74: athrow
    //   75: astore 12
    //   77: aload 7
    //   79: areturn
    //   80: astore 5
    //   82: goto -10 -> 72
    //   85: astore 4
    //   87: goto -23 -> 64
    //   90: astore 10
    //   92: goto -51 -> 41
    //
    // Exception table:
    //   from	to	target	type
    //   0	13	37	java/lang/Exception
    //   48	52	54	java/lang/Exception
    //   0	13	58	finally
    //   30	34	75	java/lang/Exception
    //   68	72	80	java/lang/Exception
    //   16	22	85	finally
    //   16	22	90	java/lang/Exception
  }

  private static Bitmap a(Bitmap paramBitmap, int paramInt1, int paramInt2, Matrix paramMatrix, boolean paramBoolean)
  {
    try
    {
      Bitmap localBitmap = Bitmap.createBitmap(paramBitmap, 0, 0, paramInt1, paramInt2, paramMatrix, true);
      return localBitmap;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      LogUtil.e("ImageUtil", "catch out of mem Matrix " + paramBoolean, localOutOfMemoryError);
      ExceptionManager.a().a(localOutOfMemoryError);
      if (paramBoolean)
        return a(paramBitmap, paramInt1, paramInt2, paramMatrix, false);
    }
    return null;
  }

  private static Bitmap a(BitmapFactory.Options paramOptions, String paramString)
  {
    int i = 0;
    Object localObject1 = null;
    while (true)
    {
      if ((i > 0) && (paramOptions.inSampleSize > 7))
        return localObject1;
      try
      {
        localObject1 = BitmapFactory.decodeFile(paramString, paramOptions);
        LogUtil.i("QZoneUpload", "options.inSampleSize ： " + paramOptions.inSampleSize);
        return localObject1;
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        Object localObject2 = localObject1;
        LogUtil.e("ImageUtil", "catch out of mem Option small options", localOutOfMemoryError);
        ExceptionManager.a().a(localOutOfMemoryError);
        paramOptions.inSampleSize = (1 + paramOptions.inSampleSize);
        i++;
        localObject1 = localObject2;
      }
    }
  }

  private static Bitmap a(BitmapFactory.Options paramOptions, String paramString, boolean paramBoolean)
  {
    try
    {
      Bitmap localBitmap2 = BitmapFactory.decodeFile(paramString, paramOptions);
      localBitmap1 = localBitmap2;
      return localBitmap1;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      do
      {
        LogUtil.e("ImageUtil", "catch out of mem Option " + paramBoolean, localOutOfMemoryError);
        ExceptionManager.a().a(localOutOfMemoryError);
        Bitmap localBitmap1 = null;
      }
      while (!paramBoolean);
    }
    return a(paramOptions, paramString, false);
  }

  public static Bitmap a(String paramString, int paramInt1, int paramInt2)
  {
    Bitmap localBitmap1;
    if (!new File(paramString).exists())
      localBitmap1 = null;
    int m;
    int n;
    float f1;
    float f2;
    do
    {
      return localBitmap1;
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inDither = true;
      localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, localOptions);
      localOptions.inJustDecodeBounds = false;
      int i = localOptions.outWidth;
      int j = localOptions.outHeight;
      int k = Math.min(i / paramInt1, j / paramInt2);
      if (k < 1)
        k = 1;
      localOptions.inSampleSize = k;
      localBitmap1 = a(localOptions, paramString, true);
      if (localBitmap1 == null)
      {
        localOptions.inSampleSize = (1 + localOptions.inSampleSize);
        localBitmap1 = a(localOptions, paramString);
      }
      if (localBitmap1 == null)
        return null;
      m = localBitmap1.getWidth();
      n = localBitmap1.getHeight();
      f1 = paramInt1 / m;
      f2 = paramInt2 / n;
    }
    while ((f1 > 1.0F) && (f2 > 1.0F));
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(f1, f2);
    Bitmap localBitmap2 = a(localBitmap1, m, n, localMatrix, true);
    if (localBitmap2 != localBitmap1)
      localBitmap1.recycle();
    return localBitmap2;
  }

  public static BitmapFactory.Options a(InputStream paramInputStream)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(paramInputStream, null, localOptions);
    return localOptions;
  }

  // ERROR //
  public static String a(android.content.Context paramContext, android.net.Uri paramUri)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_1
    //   7: invokevirtual 184	android/net/Uri:getScheme	()Ljava/lang/String;
    //   10: astore_2
    //   11: ldc 186
    //   13: aload_2
    //   14: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   17: ifeq +8 -> 25
    //   20: aload_1
    //   21: invokevirtual 195	android/net/Uri:getPath	()Ljava/lang/String;
    //   24: areturn
    //   25: ldc 197
    //   27: aload_2
    //   28: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   31: ifeq +126 -> 157
    //   34: iconst_1
    //   35: anewarray 188	java/lang/String
    //   38: dup
    //   39: iconst_0
    //   40: ldc 199
    //   42: aastore
    //   43: astore_3
    //   44: aload_0
    //   45: invokevirtual 203	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   48: aload_1
    //   49: aload_3
    //   50: aconst_null
    //   51: aconst_null
    //   52: aconst_null
    //   53: invokevirtual 209	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   56: astore 9
    //   58: aload 9
    //   60: astore 5
    //   62: aload 5
    //   64: ifnull +118 -> 182
    //   67: aload 5
    //   69: invokeinterface 214 1 0
    //   74: ifeq +108 -> 182
    //   77: aload 5
    //   79: aload 5
    //   81: ldc 199
    //   83: invokeinterface 218 2 0
    //   88: invokeinterface 222 2 0
    //   93: astore 11
    //   95: aload 11
    //   97: astore 8
    //   99: aload 5
    //   101: ifnull +10 -> 111
    //   104: aload 5
    //   106: invokeinterface 223 1 0
    //   111: aload 8
    //   113: areturn
    //   114: astore 6
    //   116: aconst_null
    //   117: astore 7
    //   119: aload 7
    //   121: ifnull +55 -> 176
    //   124: aload 7
    //   126: invokeinterface 223 1 0
    //   131: aconst_null
    //   132: astore 8
    //   134: goto -23 -> 111
    //   137: astore 4
    //   139: aconst_null
    //   140: astore 5
    //   142: aload 5
    //   144: ifnull +10 -> 154
    //   147: aload 5
    //   149: invokeinterface 223 1 0
    //   154: aload 4
    //   156: athrow
    //   157: aload_1
    //   158: invokevirtual 224	android/net/Uri:toString	()Ljava/lang/String;
    //   161: areturn
    //   162: astore 4
    //   164: goto -22 -> 142
    //   167: astore 10
    //   169: aload 5
    //   171: astore 7
    //   173: goto -54 -> 119
    //   176: aconst_null
    //   177: astore 8
    //   179: goto -68 -> 111
    //   182: aconst_null
    //   183: astore 8
    //   185: goto -86 -> 99
    //
    // Exception table:
    //   from	to	target	type
    //   44	58	114	java/lang/Throwable
    //   44	58	137	finally
    //   67	95	162	finally
    //   67	95	167	java/lang/Throwable
  }

  public static String a(String paramString)
  {
    if (paramString == null);
    do
      return null;
    while (!new File(paramString).exists());
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    return localOptions.outMimeType;
  }

  // ERROR //
  public static boolean a(Bitmap paramBitmap, String paramString, int paramInt)
  {
    // Byte code:
    //   0: new 117	java/io/File
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 120	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore_3
    //   9: aconst_null
    //   10: astore 4
    //   12: new 231	java/io/BufferedOutputStream
    //   15: dup
    //   16: new 233	java/io/FileOutputStream
    //   19: dup
    //   20: aload_3
    //   21: invokespecial 236	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   24: invokespecial 239	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   27: astore 5
    //   29: aload_0
    //   30: getstatic 245	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   33: iload_2
    //   34: aload 5
    //   36: invokevirtual 249	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   39: ifeq +24 -> 63
    //   42: aload 5
    //   44: invokevirtual 252	java/io/BufferedOutputStream:flush	()V
    //   47: iconst_1
    //   48: istore 10
    //   50: aload 5
    //   52: ifnull +8 -> 60
    //   55: aload 5
    //   57: invokevirtual 253	java/io/BufferedOutputStream:close	()V
    //   60: iload 10
    //   62: ireturn
    //   63: iconst_0
    //   64: istore 10
    //   66: goto -16 -> 50
    //   69: astore 11
    //   71: iconst_0
    //   72: ireturn
    //   73: astore 13
    //   75: aload 4
    //   77: ifnull +55 -> 132
    //   80: aload 4
    //   82: invokevirtual 253	java/io/BufferedOutputStream:close	()V
    //   85: iconst_0
    //   86: ireturn
    //   87: astore 7
    //   89: iconst_0
    //   90: ireturn
    //   91: astore 12
    //   93: aconst_null
    //   94: astore 5
    //   96: aload 12
    //   98: astore 8
    //   100: aload 5
    //   102: ifnull +8 -> 110
    //   105: aload 5
    //   107: invokevirtual 253	java/io/BufferedOutputStream:close	()V
    //   110: aload 8
    //   112: athrow
    //   113: astore 9
    //   115: goto -5 -> 110
    //   118: astore 8
    //   120: goto -20 -> 100
    //   123: astore 6
    //   125: aload 5
    //   127: astore 4
    //   129: goto -54 -> 75
    //   132: iconst_0
    //   133: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   55	60	69	java/lang/Exception
    //   12	29	73	java/lang/Exception
    //   80	85	87	java/lang/Exception
    //   12	29	91	finally
    //   105	110	113	java/lang/Exception
    //   29	47	118	finally
    //   29	47	123	java/lang/Exception
  }

  public static Bitmap b(String paramString, int paramInt1, int paramInt2)
  {
    int i = 1;
    File localFile = new File(paramString);
    BitmapFactory.Options localOptions2;
    int j;
    float f1;
    float f2;
    if ((paramInt1 > 0) && (paramInt2 > 0))
    {
      localOptions2 = new BitmapFactory.Options();
      FileInputStream localFileInputStream2 = new FileInputStream(localFile);
      if ((paramInt1 > 0) || (paramInt2 > 0))
      {
        localOptions2.inJustDecodeBounds = i;
        BitmapFactory.decodeStream(localFileInputStream2, null, localOptions2);
        localFileInputStream2.close();
        j = localOptions2.outWidth;
        int k = localOptions2.outHeight;
        if ((j > paramInt1) && (k > paramInt2))
        {
          f1 = j / k;
          f2 = paramInt1 / paramInt2;
          if (f1 <= f2)
            break label168;
          i = k / paramInt2;
        }
      }
      localOptions2.inJustDecodeBounds = false;
      localOptions2.inSampleSize = i;
    }
    for (BitmapFactory.Options localOptions1 = localOptions2; ; localOptions1 = null)
    {
      FileInputStream localFileInputStream1 = new FileInputStream(localFile);
      Bitmap localBitmap = BitmapFactory.decodeStream(localFileInputStream1, null, localOptions1);
      localFileInputStream1.close();
      return localBitmap;
      label168: if (f1 >= f2)
        break;
      i = j / paramInt1;
      break;
    }
  }

  public static String b(String paramString)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    if ((localOptions.outWidth > 0) && (localOptions.outHeight > 0) && (localOptions.outMimeType == null))
      return "webp";
    if (localOptions.outMimeType != null)
    {
      int i = localOptions.outMimeType.indexOf('/');
      if (i != -1)
        return localOptions.outMimeType.substring(i + 1);
    }
    return "png";
  }

  public static ImageUtil.Size c(String paramString)
  {
    if (!new File(paramString).exists())
      return null;
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inJustDecodeBounds = false;
    return new ImageUtil.Size(localOptions.outWidth, localOptions.outHeight);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.ImageUtil
 * JD-Core Version:    0.6.0
 */