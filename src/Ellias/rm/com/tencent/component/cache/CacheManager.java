package com.tencent.component.cache;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import com.tencent.component.cache.common.BlobCache;
import com.tencent.component.cache.file.FileCacheService;
import com.tencent.component.cache.file.FileStorageHandler;
import com.tencent.component.cache.file.FileStorageHandler.Collector;
import com.tencent.component.cache.image.ImageCacheService;
import com.tencent.component.cache.sp.PreferenceUtil;
import com.tencent.component.utils.AssertUtil;
import com.tencent.component.utils.FileUtil;
import com.tencent.component.utils.thread.ThreadPool;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class CacheManager
{
  public static final String a = "image";
  public static final int b = 3000;
  public static final int c = 800;
  public static final String d = "audio";
  public static final int e = 100;
  public static final int f = 100;
  public static final String g = "tmp";
  public static final int h = 500;
  public static final int i = 200;
  private static final Object j = new Object();
  private static final String k = "blob";
  private static final HashMap l = new HashMap();
  private static final String m = "_cache_file";
  private static final String n = "_version";
  private static final int o = 1;
  private static final boolean p;
  private static final HashMap q = new HashMap();
  private static volatile FileStorageHandler r;
  private static final FileStorageHandler.Collector s = new a();
  private static final Object t = new Object();
  private static volatile String u;
  private static BroadcastReceiver v = new c();
  private static volatile boolean w = false;

  public static BlobCache a(Context paramContext, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool1 = d(paramString);
    boolean bool2 = false;
    if (!bool1)
      bool2 = true;
    AssertUtil.a(bool2);
    Object localObject2;
    String str;
    synchronized (l)
    {
      localObject2 = (BlobCache)l.get(paramString);
      if (localObject2 == null)
      {
        str = b(paramContext, "blob");
        if (str == null);
      }
    }
    try
    {
      BlobCache localBlobCache = new BlobCache(str + File.separator + paramString, paramInt1, paramInt2, false, paramInt3);
      try
      {
        l.put(paramString, localBlobCache);
        localObject2 = localBlobCache;
        while (true)
        {
          monitorexit;
          return localObject2;
          label119: localObject3.printStackTrace();
        }
        localObject1 = finally;
        monitorexit;
        throw localObject1;
      }
      catch (IOException localIOException1)
      {
        while (true)
        {
          localObject2 = localBlobCache;
          Object localObject3 = localIOException1;
        }
      }
    }
    catch (IOException localIOException2)
    {
      break label119;
    }
  }

  public static FileCacheService a(Context paramContext, String paramString, int paramInt)
  {
    return a(paramContext, paramString, paramInt, 0);
  }

  public static FileCacheService a(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    return a(paramContext, paramString, paramInt1, paramInt2, false);
  }

  public static FileCacheService a(Context paramContext, String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (!d(paramString));
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      synchronized (q)
      {
        FileCacheService localFileCacheService = (FileCacheService)q.get(paramString);
        if (localFileCacheService == null)
        {
          localFileCacheService = new FileCacheService(paramContext, paramString, paramInt1, paramInt2, paramBoolean);
          q.put(paramString, localFileCacheService);
        }
        return localFileCacheService;
      }
    }
  }

  public static FileCacheService a(Context paramContext, boolean paramBoolean)
  {
    return a(paramContext, "tmp", 500, 200, paramBoolean);
  }

  public static ImageCacheService a(Context paramContext)
  {
    return ImageCacheService.a(paramContext);
  }

  public static String a(Context paramContext, String paramString)
  {
    return b(paramContext, paramString, false);
  }

  public static String a(Context paramContext, String paramString, boolean paramBoolean)
  {
    String str = b(paramContext, paramString, paramBoolean);
    if (str != null)
      return str;
    return d(paramContext, paramString, paramBoolean);
  }

  public static void a(Context paramContext, long paramLong)
  {
    synchronized (j)
    {
      j(paramContext);
      if (paramLong == 0L)
      {
        localSharedPreferences = PreferenceUtil.b(paramContext);
        localSharedPreferences.edit().clear().commit();
        return;
      }
      SharedPreferences localSharedPreferences = PreferenceUtil.b(paramContext, paramLong);
    }
  }

  public static boolean a()
  {
    String str = u;
    if (str == null)
    {
      str = Environment.getExternalStorageState();
      u = str;
    }
    return "mounted".equals(str);
  }

  public static boolean a(String paramString)
  {
    String str = Environment.getExternalStorageDirectory().getAbsolutePath();
    return (paramString != null) && (paramString.startsWith(str));
  }

  public static String b(Context paramContext, String paramString)
  {
    return c(paramContext, paramString, false);
  }

  public static String b(Context paramContext, String paramString, boolean paramBoolean)
  {
    String str = d(paramContext, paramBoolean);
    if (str == null)
      str = null;
    do
      return str;
    while (d(paramString));
    File localFile = new File(str + File.separator + paramString);
    if ((!localFile.exists()) || (!localFile.isDirectory()));
    synchronized (t)
    {
      if (!localFile.isDirectory())
      {
        FileUtil.a(localFile);
        localFile.mkdirs();
      }
      do
        return localFile.getAbsolutePath();
      while (localFile.exists());
      localFile.mkdirs();
    }
  }

  public static String b(Context paramContext, boolean paramBoolean)
  {
    if (!paramBoolean)
      return paramContext.getCacheDir().getAbsolutePath();
    return paramContext.getFilesDir().getAbsolutePath() + File.separator + "cache";
  }

  public static void b(Context paramContext)
  {
    i(paramContext);
    k(paramContext);
  }

  public static boolean b(String paramString)
  {
    String str = Environment.getDataDirectory().getAbsolutePath();
    return (paramString != null) && (paramString.startsWith(str));
  }

  public static FileStorageHandler c(Context paramContext)
  {
    if (r == null);
    synchronized (s)
    {
      if (r == null)
        r = new FileStorageHandler(paramContext, s);
      return r;
    }
  }

  public static String c(Context paramContext, String paramString)
  {
    return d(paramContext, paramString, false);
  }

  public static String c(Context paramContext, String paramString, boolean paramBoolean)
  {
    String str = e(paramContext, paramBoolean);
    if (str == null)
      str = null;
    do
      return str;
    while (d(paramString));
    File localFile = new File(str + File.separator + paramString);
    if ((!localFile.exists()) || (!localFile.isDirectory()));
    synchronized (t)
    {
      if (!localFile.isDirectory())
      {
        FileUtil.a(localFile);
        localFile.mkdirs();
      }
      do
        return localFile.getAbsolutePath();
      while (localFile.exists());
      localFile.mkdirs();
    }
  }

  public static String c(Context paramContext, boolean paramBoolean)
  {
    if (!paramBoolean)
      return paramContext.getCacheDir().getAbsolutePath();
    return paramContext.getFilesDir().getAbsolutePath() + File.separator;
  }

  public static FileCacheService d(Context paramContext)
  {
    return a(paramContext, "image", 3000, 800);
  }

  private static String d(Context paramContext, String paramString)
  {
    return a(paramContext, paramString, false);
  }

  public static String d(Context paramContext, String paramString, boolean paramBoolean)
  {
    String str = b(paramContext, paramBoolean);
    if (d(paramString))
      return str;
    File localFile = new File(str + File.separator + paramString);
    if ((!localFile.exists()) || (!localFile.isDirectory()));
    synchronized (t)
    {
      if (!localFile.isDirectory())
      {
        FileUtil.a(localFile);
        localFile.mkdirs();
      }
      do
        return localFile.getAbsolutePath();
      while (localFile.exists());
      localFile.mkdirs();
    }
  }

  private static String d(Context paramContext, boolean paramBoolean)
  {
    if (!a());
    while (true)
    {
      return null;
      if (!paramBoolean);
      for (File localFile = d.a(paramContext, false); localFile != null; localFile = d.a(paramContext, "cache", false))
        return localFile.getAbsolutePath();
    }
  }

  private static boolean d(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  public static FileCacheService e(Context paramContext)
  {
    return a(paramContext, "audio", 100, 100);
  }

  private static String e(Context paramContext, boolean paramBoolean)
  {
    if (!a());
    while (true)
    {
      return null;
      if (!paramBoolean);
      for (File localFile = d.a(paramContext, true); localFile != null; localFile = d.a(paramContext, "cache", true))
        return localFile.getAbsolutePath();
    }
  }

  public static FileCacheService f(Context paramContext)
  {
    return a(paramContext, false);
  }

  public static void g(Context paramContext)
  {
    synchronized (j)
    {
      a(paramContext, 0L);
      return;
    }
  }

  private static void i(Context paramContext)
  {
    synchronized (j)
    {
      Context localContext = paramContext.getApplicationContext();
      SharedPreferences localSharedPreferences = PreferenceUtil.a(localContext, "_cache_file");
      int i1 = localSharedPreferences.getInt("_version", -1);
      if ((i1 != -1) && (i1 != 1))
      {
        localSharedPreferences.edit().putInt("_version", 1).commit();
        ThreadPool.getInstance().submit(new b(localContext));
      }
      return;
    }
  }

  private static void j(Context paramContext)
  {
    String str1 = d(paramContext, false);
    if (str1 != null)
      FileUtil.a(new File(str1), true);
    String str2 = e(paramContext, false);
    if (str2 != null)
      FileUtil.a(new File(str2), true);
    String str3 = b(paramContext, false);
    if (str3 != null)
      FileUtil.a(new File(str3), true);
  }

  private static void k(Context paramContext)
  {
    if (!w)
    {
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
      localIntentFilter.addAction("android.intent.action.MEDIA_EJECT");
      localIntentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
      localIntentFilter.addAction("android.intent.action.MEDIA_REMOVED");
      localIntentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
      localIntentFilter.addDataScheme("file");
      paramContext.registerReceiver(v, localIntentFilter);
      w = true;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.CacheManager
 * JD-Core Version:    0.6.0
 */