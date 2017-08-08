package com.pay.image.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import com.pay.common.tool.APLog;
import com.pay.common.tool.APMD5;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class APDiskLruCache
{
  private static long d = 5242880L;
  private static final FilenameFilter h = new a();
  private final File a;
  private int b = 0;
  private int c = 0;
  private Bitmap.CompressFormat e = Bitmap.CompressFormat.PNG;
  private int f = 70;
  private final Map g = Collections.synchronizedMap(new LinkedHashMap(32, 0.75F, true));

  private APDiskLruCache(File paramFile, long paramLong)
  {
    this.a = paramFile;
    d = paramLong;
  }

  private void a()
  {
    for (int i = 0; ; i++)
    {
      if (((this.b <= 64) && (this.c <= d)) || (i >= 4))
        return;
      Map.Entry localEntry = (Map.Entry)this.g.entrySet().iterator().next();
      File localFile = new File((String)localEntry.getValue());
      long l = localFile.length();
      this.g.remove(localEntry.getKey());
      localFile.delete();
      this.b = this.g.size();
      this.c = (int)(this.c - l);
    }
  }

  private static void a(File paramFile)
  {
    File[] arrayOfFile = paramFile.listFiles(h);
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfFile.length)
        return;
      arrayOfFile[i].delete();
    }
  }

  private void a(String paramString1, String paramString2)
  {
    this.g.put(paramString1, paramString2);
    this.b = this.g.size();
    this.c = (int)(this.c + new File(paramString2).length());
  }

  // ERROR //
  private boolean a(Bitmap paramBitmap, String paramString)
  {
    // Byte code:
    //   0: new 124	java/io/BufferedOutputStream
    //   3: dup
    //   4: new 126	java/io/FileOutputStream
    //   7: dup
    //   8: aload_2
    //   9: invokespecial 127	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   12: sipush 4096
    //   15: invokespecial 130	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   18: astore_3
    //   19: aload_1
    //   20: aload_0
    //   21: getfield 44	com/pay/image/cache/APDiskLruCache:e	Landroid/graphics/Bitmap$CompressFormat;
    //   24: aload_0
    //   25: getfield 46	com/pay/image/cache/APDiskLruCache:f	I
    //   28: aload_3
    //   29: invokevirtual 136	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   32: istore 5
    //   34: aload_3
    //   35: invokevirtual 141	java/io/OutputStream:close	()V
    //   38: iload 5
    //   40: ireturn
    //   41: astore 4
    //   43: aconst_null
    //   44: astore_3
    //   45: aload_3
    //   46: ifnull +7 -> 53
    //   49: aload_3
    //   50: invokevirtual 141	java/io/OutputStream:close	()V
    //   53: aload 4
    //   55: athrow
    //   56: astore 4
    //   58: goto -13 -> 45
    //
    // Exception table:
    //   from	to	target	type
    //   0	19	41	finally
    //   19	34	56	finally
  }

  public static void clearCache(Context paramContext, String paramString)
  {
    a(getDiskCacheDir(paramContext, paramString));
  }

  public static File getDiskCacheDir(Context paramContext, String paramString)
  {
    String str = paramContext.getCacheDir().getPath();
    APLog.i("getDiskCacheDir", "path:" + str);
    return new File(str + File.separator + paramString);
  }

  public static String getFilePath(File paramFile, String paramString)
  {
    try
    {
      String str = paramFile.getAbsolutePath() + File.separator + "imgcache_" + APMD5.toMd5(paramString.getBytes());
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public static String getSDCardDir()
  {
    if (Environment.getExternalStorageState().equals("mounted"));
    for (File localFile = Environment.getExternalStorageDirectory(); ; localFile = null)
    {
      String str = null;
      if (localFile != null)
        str = localFile.toString();
      return str;
    }
  }

  public static APDiskLruCache openCache(Context paramContext, File paramFile)
  {
    if (!paramFile.exists())
      paramFile.mkdir();
    if ((paramFile.isDirectory()) && (paramFile.canWrite()))
      return new APDiskLruCache(paramFile, d);
    return null;
  }

  public static APDiskLruCache openCache(Context paramContext, File paramFile, long paramLong)
  {
    d = paramLong;
    return openCache(paramContext, paramFile);
  }

  public void clearCache()
  {
    a(this.a);
  }

  public boolean containsKey(String paramString)
  {
    if (this.g.containsKey(paramString))
      return true;
    String str = getFilePath(this.a, paramString);
    if (new File(str).exists())
    {
      a(paramString, str);
      return true;
    }
    return false;
  }

  public Bitmap get(String paramString)
  {
    try
    {
      synchronized (this.g)
      {
        String str1 = (String)this.g.get(paramString);
        if (str1 != null)
        {
          Bitmap localBitmap1 = BitmapFactory.decodeFile(str1);
          return localBitmap1;
        }
        String str2 = getFilePath(this.a, paramString);
        APLog.i("APDiskLruCache", "get existingFile :" + str2);
        if (new File(str2).exists())
        {
          a(paramString, str2);
          Bitmap localBitmap2 = BitmapFactory.decodeFile(str2);
          return localBitmap2;
        }
      }
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      localOutOfMemoryError.printStackTrace();
      monitorexit;
      return null;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public String getFilePath(String paramString)
  {
    return getFilePath(this.a, paramString);
  }

  public void put(String paramString, Bitmap paramBitmap)
  {
    synchronized (this.g)
    {
      Object localObject2 = this.g.get(paramString);
      if (localObject2 != null);
    }
    try
    {
      String str = getFilePath(this.a, paramString);
      APLog.i("APDiskLruCache", "filePath:" + str);
      if (a(paramBitmap, str))
      {
        a(paramString, str);
        a();
      }
      label79: monitorexit;
      return;
      localObject1 = finally;
      monitorexit;
      throw localObject1;
    }
    catch (IOException localIOException)
    {
      break label79;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      break label79;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.image.cache.APDiskLruCache
 * JD-Core Version:    0.6.0
 */