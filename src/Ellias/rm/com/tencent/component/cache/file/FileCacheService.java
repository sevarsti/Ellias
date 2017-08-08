package com.tencent.component.cache.file;

import android.content.Context;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.utils.FileUtil;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.ThreadPool;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class FileCacheService
{
  private static final String a = "FileCacheService";
  private static final String b = "file";
  private static final ThreadLocal i = new a();
  private static Comparator j = new c();
  private final Context c;
  private final String d;
  private final boolean e;
  private final FileCache f;
  private final FileCache g;
  private boolean h = false;

  public FileCacheService(Context paramContext, String paramString, int paramInt)
  {
    this(paramContext, paramString, paramInt, false);
  }

  public FileCacheService(Context paramContext, String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (e(paramString))
      throw new NullPointerException("file cache: name can NOT be empty!");
    this.c = paramContext.getApplicationContext();
    this.d = ("file" + File.separator + paramString);
    this.e = paramBoolean;
    if (paramInt2 < 0)
      paramInt2 = 0;
    if (paramInt1 > 0);
    while (true)
    {
      this.f = new FileCache(paramInt1);
      this.g = new FileCache(paramInt2);
      d();
      return;
      paramInt1 = 2147483647;
    }
  }

  public FileCacheService(Context paramContext, String paramString, int paramInt, boolean paramBoolean)
  {
    this(paramContext, paramString, paramInt, 0, paramBoolean);
  }

  private static boolean a(File paramFile)
  {
    return (paramFile != null) && (paramFile.exists()) && (paramFile.isFile());
  }

  private boolean c(String paramString, boolean paramBoolean)
  {
    FileCache localFileCache = e(paramBoolean);
    String str = a(paramString, paramBoolean);
    if (str == null);
    File localFile;
    while (true)
    {
      return false;
      localFile = new File(str);
      if (a(localFile))
        break;
      if (!localFile.isDirectory())
        continue;
      FileUtil.a(localFile);
      return false;
    }
    localFileCache.b(paramString, localFile.getAbsolutePath());
    g(paramBoolean);
    return true;
  }

  private File d(String paramString, boolean paramBoolean)
  {
    String str = a(paramString, paramBoolean);
    if (str == null)
      return null;
    try
    {
      File localFile = new File(str);
      FileUtil.a(localFile);
      localFile.createNewFile();
      return localFile;
    }
    catch (IOException localIOException)
    {
      LogUtil.i("FileCacheService", "fail to create file " + str, localIOException);
    }
    return null;
  }

  private void d()
  {
    ThreadPool.getInstance().submit(new b(this));
  }

  private void d(boolean paramBoolean)
  {
    int k = 0;
    monitorenter;
    while (true)
    {
      FileCache localFileCache;
      d locald;
      try
      {
        String str = f(paramBoolean);
        localFileCache = e(paramBoolean);
        boolean bool = e(str);
        if (bool)
          return;
        String[] arrayOfString = new File(str).list();
        if ((arrayOfString == null) || (arrayOfString.length == 0))
          continue;
        d[] arrayOfd = new d[arrayOfString.length];
        int m = 0;
        if (m >= arrayOfd.length)
          continue;
        arrayOfd[m] = new d(str, arrayOfString[m]);
        m++;
        continue;
        Arrays.sort(arrayOfd, j);
        int n = arrayOfd.length;
        if (k >= n)
          continue;
        locald = arrayOfd[k];
        if (locald == null)
          break label190;
        if (!locald.d)
        {
          if (locald.a == null)
            break label190;
          FileUtil.a(new File(locald.a));
        }
      }
      finally
      {
        monitorexit;
      }
      localFileCache.b(locald.b, locald.a);
      label190: k++;
    }
  }

  private FileCache e(boolean paramBoolean)
  {
    if (paramBoolean)
      return this.f;
    return this.g;
  }

  private static boolean e(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  private String f(boolean paramBoolean)
  {
    if (paramBoolean)
      return CacheManager.b(this.c, this.d, this.e);
    return CacheManager.d(this.c, this.d, this.e);
  }

  private void g(boolean paramBoolean)
  {
    FileStorageHandler localFileStorageHandler = CacheManager.c(this.c);
    if (localFileStorageHandler != null)
      if (!paramBoolean)
        break label26;
    label26: for (FileStorageHandler.Mode localMode = FileStorageHandler.Mode.EXTERNAL; ; localMode = FileStorageHandler.Mode.INTERNAL)
    {
      localFileStorageHandler.a(localMode);
      return;
    }
  }

  public Context a()
  {
    return this.c;
  }

  public String a(String paramString)
  {
    return a(paramString, CacheManager.a());
  }

  public String a(String paramString, boolean paramBoolean)
  {
    if (e(paramString));
    String str;
    do
    {
      return null;
      str = f(paramBoolean);
    }
    while (str == null);
    StringBuilder localStringBuilder = (StringBuilder)i.get();
    localStringBuilder.delete(0, localStringBuilder.length());
    localStringBuilder.append(str).append(File.separatorChar).append(paramString);
    return localStringBuilder.toString();
  }

  public void a(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      e(paramBoolean).a();
      String str = f(paramBoolean);
      if (str != null)
        FileUtil.a(new File(str), true);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(boolean paramBoolean, int paramInt)
  {
    monitorenter;
    try
    {
      e(paramBoolean).a(paramInt);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int b(boolean paramBoolean)
  {
    if (paramBoolean)
      return this.f.b();
    return this.g.b();
  }

  public File b(String paramString)
  {
    return b(paramString, false);
  }

  public File b(String paramString, boolean paramBoolean)
  {
    if (e(paramString))
      return null;
    boolean bool = CacheManager.a();
    String str1 = (String)e(bool).b(paramString);
    if ((str1 == null) && (!this.h));
    for (String str2 = a(paramString, bool); ; str2 = str1)
    {
      if (str2 == null);
      for (File localFile1 = null; a(localFile1); localFile1 = new File(str2))
        return localFile1;
      String str3;
      if (bool)
      {
        str3 = (String)e(false).b(paramString);
        if ((str3 != null) || (this.h))
          break label209;
      }
      label209: for (String str4 = a(paramString, false); ; str4 = str3)
      {
        if (str4 == null);
        for (File localFile4 = null; a(localFile4); localFile4 = new File(str4))
          return localFile4;
        if (!paramBoolean)
          break;
        File localFile2 = d(paramString, bool);
        if (a(localFile2))
        {
          c(paramString);
          return localFile2;
        }
        if (!bool)
          break;
        File localFile3 = d(paramString, false);
        if (!a(localFile3))
          break;
        c(paramString);
        return localFile3;
      }
    }
  }

  public void b()
  {
    monitorenter;
    try
    {
      a(false);
      a(true);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int c(boolean paramBoolean)
  {
    if (paramBoolean)
      return this.f.c();
    return this.g.c();
  }

  public boolean c()
  {
    return this.e;
  }

  public boolean c(String paramString)
  {
    boolean bool2;
    if (e(paramString))
      bool2 = false;
    boolean bool1;
    do
    {
      return bool2;
      bool1 = CacheManager.a();
      bool2 = c(paramString, bool1);
    }
    while ((bool2) || (!bool1));
    return c(paramString, false);
  }

  public void d(String paramString)
  {
    if (e(paramString));
    String str2;
    do
    {
      return;
      e(false).c(paramString);
      e(true).c(paramString);
      String str1 = a(paramString, false);
      str2 = a(paramString, true);
      if (str1 == null)
        continue;
      FileUtil.a(new File(str1));
    }
    while (str2 == null);
    FileUtil.a(new File(str2));
  }

  public String toString()
  {
    return "FileCache#" + this.d + "#capacity=" + c(true) + "," + c(false) + "#size=" + b(true) + "," + b(false);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.file.FileCacheService
 * JD-Core Version:    0.6.0
 */