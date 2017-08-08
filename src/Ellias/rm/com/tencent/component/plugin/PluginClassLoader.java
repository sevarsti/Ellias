package com.tencent.component.plugin;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.component.utils.FileUtil;
import com.tencent.component.utils.UniqueLock;
import com.tencent.component.utils.log.LogUtil;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

public final class PluginClassLoader
{
  private static final String a = "PluginClassLoader";
  private static final ConcurrentHashMap g = new ConcurrentHashMap();
  private static final UniqueLock h = new UniqueLock();
  private final ClassLoader b;
  private final ConcurrentHashMap c = new ConcurrentHashMap();
  private final File d;
  private final long e;
  private final long f;

  public PluginClassLoader(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Object localObject;
    File localFile;
    label47: long l2;
    if (TextUtils.isEmpty(paramString1))
    {
      localObject = paramContext.getClassLoader();
      this.b = ((ClassLoader)localObject);
      if (!TextUtils.isEmpty(paramString1))
        break label110;
      localFile = null;
      this.d = localFile;
      if (this.d != null)
        break label123;
      l2 = l1;
      label64: this.e = l2;
      if (this.d != null)
        break label135;
    }
    while (true)
    {
      this.f = l1;
      return;
      localObject = new i(paramString1, b(paramString2), b(paramString3), paramContext.getClassLoader());
      break;
      label110: localFile = new File(paramString1);
      break label47;
      label123: l2 = this.d.length();
      break label64;
      label135: l1 = this.d.lastModified();
    }
  }

  public static PluginClassLoader a(Context paramContext, PluginInfo paramPluginInfo)
  {
    String str1 = paramPluginInfo.pluginId;
    String str2 = paramPluginInfo.installPath;
    if (str1 == null);
    for (String str3 = ""; ; str3 = str1)
    {
      PluginClassLoader localPluginClassLoader1 = (PluginClassLoader)g.get(str3);
      if (localPluginClassLoader1 != null)
        return localPluginClassLoader1;
      Lock localLock = h.a(str2);
      localLock.lock();
      try
      {
        PluginClassLoader localPluginClassLoader2 = (PluginClassLoader)g.get(str3);
        if (localPluginClassLoader2 != null)
          return localPluginClassLoader2;
        PluginClassLoader localPluginClassLoader3 = new PluginClassLoader(paramContext.getApplicationContext(), str2, paramPluginInfo.dexOptimizeDir, paramPluginInfo.nativeLibraryDir);
        g.put(str3, localPluginClassLoader3);
        return localPluginClassLoader3;
      }
      finally
      {
        localLock.unlock();
      }
    }
  }

  public static void a(PluginInfo paramPluginInfo)
  {
    String str1 = paramPluginInfo.pluginId;
    String str2 = paramPluginInfo.installPath;
    if (str1 == null);
    do
      return;
    while (!g.containsKey(str1));
    Lock localLock = h.a(str2);
    localLock.lock();
    try
    {
      LogUtil.i("PluginClassLoader", "remove class loader :" + str1);
      g.remove(str1);
      return;
    }
    finally
    {
      localLock.unlock();
    }
    throw localObject;
  }

  private static boolean a(File paramFile)
  {
    return (paramFile != null) && (paramFile.isDirectory()) && (paramFile.exists());
  }

  private static String b(String paramString)
  {
    if (paramString != null)
      b(new File(paramString));
    return paramString;
  }

  private static boolean b(File paramFile)
  {
    if (paramFile == null)
      return false;
    if (!a(paramFile))
    {
      FileUtil.a(paramFile);
      return paramFile.mkdirs();
    }
    return true;
  }

  public Class a(String paramString)
  {
    Class localClass1;
    if (TextUtils.isEmpty(paramString))
      localClass1 = null;
    Class localClass2;
    do
    {
      do
      {
        return localClass1;
        localClass1 = (Class)this.c.get(paramString);
      }
      while (localClass1 != null);
      localClass2 = this.b.loadClass(paramString);
      localClass1 = (Class)this.c.putIfAbsent(paramString, localClass2);
    }
    while (localClass1 != null);
    return localClass2;
  }

  public boolean a()
  {
    int i = 1;
    if (this.d == null);
    while (true)
    {
      return i;
      boolean bool = this.d.exists();
      long l1 = this.d.length();
      long l2 = this.d.lastModified();
      if ((bool) && (l1 == this.e) && (l2 == this.f));
      while (i == 0)
      {
        LogUtil.e("PluginClassLoader", "plugin class loader not update to date (mFile.exists():" + bool + "| mFile.length():" + l1 + " |mInitFileSize:" + this.e + " |mFile.lastModified():" + l2 + " |mInitFileTime:" + this.f + ")");
        return i;
        i = 0;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginClassLoader
 * JD-Core Version:    0.6.0
 */