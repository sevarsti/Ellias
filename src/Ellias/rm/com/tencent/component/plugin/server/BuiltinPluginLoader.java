package com.tencent.component.plugin.server;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.cache.file.FileCacheService;
import com.tencent.component.plugin.PluginInfo;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.FileUtil;
import com.tencent.component.utils.UniqueLock;
import com.tencent.component.utils.log.LogUtil;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class BuiltinPluginLoader
{
  private static final String a = "BuiltinPluginLoader";
  private final c b;
  private final Context c;
  private final e d;
  private final g e;
  private final UniqueLock f = new UniqueLock();
  private final LinkedHashMap g = new LinkedHashMap();

  BuiltinPluginLoader(c paramc)
  {
    this.b = paramc;
    this.c = this.b.a();
    this.d = paramc.e();
    this.e = paramc.f();
    b();
  }

  private static File a(Context paramContext, boolean paramBoolean)
  {
    String str1 = UUID.randomUUID().toString();
    String str2 = CacheManager.a(paramContext, true).a(str1, paramBoolean);
    if (str2 != null)
      return new File(str2);
    return null;
  }

  private static Collection a(Context paramContext, String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      try
      {
        a locala = new a();
        XMLReader localXMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        localXMLReader.setContentHandler(locala);
        localXMLReader.parse(new InputSource(paramContext.getAssets().open(paramString)));
        Collection localCollection = locala.a();
        return localCollection;
      }
      catch (Throwable localThrowable)
      {
        LogUtil.i("BuiltinPluginLoader", "fail to parse xml " + paramString, localThrowable);
      }
    return null;
  }

  private void a(b paramb)
  {
    int i = 1;
    if ((paramb == null) || (!paramb.a()))
      return;
    Lock localLock = this.f.a(paramb.a);
    localLock.lock();
    try
    {
      boolean bool = paramb.e;
      if (bool)
        return;
      PluginInfo localPluginInfo = this.e.g(paramb.a);
      if ((!DebugUtil.a(this.c)) && (localPluginInfo != null) && (localPluginInfo.version >= paramb.d))
      {
        LogUtil.i("BuiltinPluginLoader", "plugin " + localPluginInfo + " is already up to date");
        return;
      }
      File localFile = b(this.c, paramb.b);
      if ((a(localFile)) && (this.d.a(localFile) == e.a));
      while (true)
      {
        if (i == 0)
          LogUtil.d("BuiltinPluginLoader", "fail to copy assets to tmp or perform install, record:" + paramb + " installed:" + localPluginInfo);
        paramb.e = true;
        return;
        i = 0;
      }
    }
    finally
    {
      localLock.unlock();
    }
    throw localObject;
  }

  private static boolean a(File paramFile)
  {
    return (paramFile != null) && (paramFile.isFile()) && (paramFile.length() > 0L);
  }

  private static File b(Context paramContext, String paramString)
  {
    if (paramString == null);
    File localFile2;
    do
    {
      do
      {
        return null;
        File localFile1 = a(paramContext, true);
        if (localFile1 != null)
        {
          if (localFile1.isDirectory())
            FileUtil.a(localFile1);
          FileUtil.a(paramContext, paramString, localFile1.getAbsolutePath());
          if (a(localFile1))
            return localFile1;
        }
        localFile2 = a(paramContext, false);
      }
      while (localFile2 == null);
      if (localFile2.isDirectory())
        FileUtil.a(localFile2);
      FileUtil.a(paramContext, paramString, localFile2.getAbsolutePath());
    }
    while (!a(localFile2));
    return localFile2;
  }

  private void b()
  {
    Collection localCollection = a(this.c, PluginConstant.a(this.b));
    if (localCollection != null)
    {
      Iterator localIterator = localCollection.iterator();
      while (localIterator.hasNext())
      {
        b localb = (b)localIterator.next();
        if (localb == null)
          continue;
        this.g.put(localb.a, localb);
      }
    }
  }

  final void a()
  {
    Collection localCollection = this.g.values();
    if (localCollection == null);
    while (true)
    {
      return;
      Iterator localIterator = localCollection.iterator();
      while (localIterator.hasNext())
        a((b)localIterator.next());
    }
  }

  final void a(String paramString)
  {
    b localb = (b)this.g.get(paramString);
    if (localb == null)
      return;
    a(localb);
  }

  final boolean a(PluginInfo paramPluginInfo)
  {
    if (paramPluginInfo == null)
      return false;
    b localb = (b)this.g.get(paramPluginInfo.pluginId);
    if ((localb != null) && (localb.d > paramPluginInfo.version));
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.BuiltinPluginLoader
 * JD-Core Version:    0.6.0
 */