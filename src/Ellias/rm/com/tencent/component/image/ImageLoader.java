package com.tencent.component.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.webkit.URLUtil;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.cache.file.FileCacheService;
import com.tencent.component.cache.image.ImageCacheService;
import com.tencent.component.net.NetworkManager;
import com.tencent.component.net.http.download.DownloadRequest;
import com.tencent.component.net.http.download.Downloader;
import com.tencent.component.ui.widget.image.processor.ImageProcessor;
import com.tencent.component.utils.AssertUtil;
import com.tencent.component.utils.SecurityUtil;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.ThreadPool;
import com.tencent.component.utils.thread.ThreadPool.Priority;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ImageLoader
{
  private static final String a = "ImageLoader";
  private static final String b = "file://";
  private static volatile ImageLoader i;
  private final ImageCacheService c;
  private final FileCacheService d;
  private final Downloader e;
  private final Handler f = new Handler(Looper.getMainLooper());
  private final HashMap g = new HashMap();
  private ImageLoader.ReportHandler h;

  public ImageLoader(Context paramContext)
  {
    this.c = CacheManager.a(paramContext);
    this.d = CacheManager.d(paramContext);
    this.e = NetworkManager.d(paramContext);
  }

  private Drawable a(Request paramRequest, boolean paramBoolean)
  {
    paramRequest.g = e(paramRequest);
    if (paramBoolean);
    for (Drawable localDrawable1 = this.c.a(paramRequest.g, paramRequest.d); b(localDrawable1); localDrawable1 = this.c.a(paramRequest.g, null, paramRequest.d))
      return b(localDrawable1, paramRequest.d);
    if (CacheManager.a())
    {
      paramRequest.g = b(paramRequest, false);
      if (paramBoolean);
      for (Drawable localDrawable2 = this.c.a(paramRequest.g, paramRequest.d); b(localDrawable2); localDrawable2 = this.c.a(paramRequest.g, null, paramRequest.d))
        return b(localDrawable2, paramRequest.d);
    }
    return null;
  }

  public static ImageLoader a(Context paramContext)
  {
    if (i != null)
      return i;
    monitorenter;
    try
    {
      if (i != null)
      {
        ImageLoader localImageLoader2 = i;
        return localImageLoader2;
      }
    }
    finally
    {
      monitorexit;
    }
    ImageLoader localImageLoader1 = new ImageLoader(paramContext.getApplicationContext());
    i = localImageLoader1;
    monitorexit;
    return localImageLoader1;
  }

  private Request a(String paramString1, String paramString2, ImageLoader.ImageLoadListener paramImageLoadListener, ImageLoader.Options paramOptions)
  {
    String str1 = f(paramString2);
    String str2 = DownloadRequest.a(str1);
    if (h(str2))
      str2 = str1;
    Request localRequest = new Request(str1, paramString1, paramImageLoadListener, paramOptions);
    localRequest.e = g(str2);
    return localRequest;
  }

  private Collection a(Collection paramCollection)
  {
    HashMap localHashMap = this.g;
    monitorenter;
    if (paramCollection != null);
    while (true)
    {
      try
      {
        paramCollection.clear();
        if (this.g.isEmpty())
          return paramCollection;
        if (paramCollection == null)
        {
          paramCollection = new ArrayList(this.g.values());
          this.g.clear();
          return paramCollection;
        }
      }
      finally
      {
        monitorexit;
      }
      paramCollection.addAll(this.g.values());
    }
  }

  private void a(Request paramRequest)
  {
    boolean bool1 = true;
    String str1 = paramRequest.a;
    boolean bool2;
    label29: Drawable localDrawable;
    if (!h(str1))
    {
      bool2 = bool1;
      AssertUtil.a(bool2);
      if (paramRequest.d != null)
        break label117;
      File localFile = d(paramRequest);
      if (localFile == null)
        break label128;
      paramRequest.g = localFile.getAbsolutePath();
      paramRequest.f = new b(this, paramRequest);
      localDrawable = this.c.a(paramRequest.g, paramRequest.f, paramRequest.d);
    }
    while (true)
    {
      if (b(localDrawable))
        a(h(paramRequest), b(localDrawable, paramRequest.d));
      return;
      bool2 = false;
      break;
      label117: bool1 = paramRequest.d.l;
      break label29;
      label128: if (URLUtil.isNetworkUrl(str1))
      {
        paramRequest.i = e(paramRequest);
        paramRequest.h = new c(this, paramRequest);
        Downloader localDownloader = this.e;
        String str2 = paramRequest.i;
        if (bool1);
        for (ThreadPool.Priority localPriority = ThreadPool.Priority.HIGH; ; localPriority = ThreadPool.Priority.NORMAL)
        {
          localDownloader.a(str1, str2, localPriority, paramRequest.h);
          localDrawable = null;
          break;
        }
      }
      j(h(paramRequest));
      localDrawable = null;
    }
  }

  private void a(Request paramRequest, float paramFloat)
  {
    if (paramRequest == null)
      return;
    ImageLoader.Options localOptions = paramRequest.d;
    if (localOptions == null);
    for (boolean bool = false; !bool; bool = localOptions.r)
    {
      paramRequest.c.a(paramRequest.b, paramFloat, localOptions);
      return;
    }
    this.f.post(new h(this, paramRequest, paramFloat, localOptions));
  }

  private void a(Request paramRequest, Drawable paramDrawable)
  {
    if (paramRequest == null)
      return;
    ImageLoader.Options localOptions = paramRequest.d;
    if (localOptions == null);
    for (boolean bool = false; !bool; bool = localOptions.r)
    {
      paramRequest.c.a(paramRequest.b, paramDrawable, localOptions);
      return;
    }
    this.f.post(new g(this, paramRequest, paramDrawable, localOptions));
  }

  private void a(Request paramRequest, Result paramResult)
  {
    ImageLoader.ReportHandler localReportHandler = this.h;
    if (localReportHandler != null)
      localReportHandler.a(paramRequest, paramResult);
  }

  private static Drawable b(Drawable paramDrawable, ImageLoader.Options paramOptions)
  {
    if (paramOptions == null);
    for (ImageProcessor localImageProcessor = null; ; localImageProcessor = paramOptions.t)
    {
      if (localImageProcessor != null)
        paramDrawable = localImageProcessor.doProcess(paramDrawable);
      return paramDrawable;
    }
  }

  private String b(Request paramRequest, boolean paramBoolean)
  {
    String str = paramRequest.a;
    if (!h(str));
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      FileCacheService localFileCacheService = c(paramRequest);
      if (!URLUtil.isNetworkUrl(str))
        break;
      return localFileCacheService.a(paramRequest.e, paramBoolean);
    }
    return str;
  }

  private void b(Request paramRequest)
  {
    if (paramRequest == null);
    do
    {
      return;
      if (paramRequest.h == null)
        continue;
      LogUtil.i("ImageLoader", "cancel request " + paramRequest.a);
      this.e.a(paramRequest.a, paramRequest.i, paramRequest.h);
    }
    while (paramRequest.f == null);
    this.c.b(paramRequest.g, paramRequest.f, paramRequest.d);
  }

  private static boolean b(Drawable paramDrawable)
  {
    return paramDrawable != null;
  }

  private static boolean b(File paramFile)
  {
    return (paramFile != null) && (paramFile.isFile()) && (paramFile.length() > 0L);
  }

  private FileCacheService c(Request paramRequest)
  {
    if (paramRequest.d != null);
    for (FileCacheService localFileCacheService = paramRequest.d.u; localFileCacheService != null; localFileCacheService = null)
      return localFileCacheService;
    return this.d;
  }

  private File d(Request paramRequest)
  {
    String str = paramRequest.a;
    boolean bool;
    FileCacheService localFileCacheService;
    if (!h(str))
    {
      bool = true;
      AssertUtil.a(bool);
      localFileCacheService = c(paramRequest);
      if (!URLUtil.isNetworkUrl(str))
        break label59;
    }
    label59: for (File localFile = localFileCacheService.b(paramRequest.e); ; localFile = new File(str))
    {
      if (!b(localFile))
        break label72;
      return localFile;
      bool = false;
      break;
    }
    label72: return null;
  }

  private String e(Request paramRequest)
  {
    String str1 = paramRequest.a;
    boolean bool1;
    FileCacheService localFileCacheService;
    boolean bool2;
    if (!h(str1))
    {
      bool1 = true;
      AssertUtil.a(bool1);
      localFileCacheService = c(paramRequest);
      bool2 = URLUtil.isNetworkUrl(str1);
      if (!bool2)
        break label76;
    }
    label76: for (String str2 = localFileCacheService.a(paramRequest.e); ; str2 = str1)
    {
      if ((bool2) && (h(str2)))
        str2 = b(paramRequest, false);
      return str2;
      bool1 = false;
      break;
    }
  }

  private static boolean e(String paramString)
  {
    return (!h(paramString)) && (!h(f(paramString)));
  }

  private static String f(String paramString)
  {
    if (h(paramString))
      paramString = null;
    do
    {
      do
        return paramString;
      while (URLUtil.isNetworkUrl(paramString));
      if (!paramString.startsWith("file://"))
        continue;
      paramString = paramString.substring("file://".length());
    }
    while (paramString.startsWith(File.separator));
    return File.separator + paramString;
  }

  private String f(String paramString, ImageLoader.Options paramOptions)
  {
    String[] arrayOfString;
    int k;
    label17: String str;
    if (paramOptions == null)
    {
      arrayOfString = null;
      if (arrayOfString == null)
        break label71;
      int j = arrayOfString.length;
      k = 0;
      if (k >= j)
        break label71;
      str = arrayOfString[k];
      if (!h(str))
        break label52;
    }
    label52: 
    do
    {
      k++;
      break label17;
      arrayOfString = paramOptions.s;
      break;
    }
    while (d(a(null, str, null, null)) == null);
    paramString = str;
    label71: return paramString;
  }

  private boolean f(Request paramRequest)
  {
    if (paramRequest == null)
      return false;
    while (true)
    {
      Request localRequest;
      synchronized (this.g)
      {
        localRequest = (Request)this.g.get(paramRequest);
        if (localRequest == null)
        {
          this.g.put(paramRequest, paramRequest);
          break label60;
          return j;
        }
      }
      label60: 
      do
      {
        j = 0;
        break;
      }
      while (localRequest != null);
      int j = 1;
    }
  }

  private Request g(Request paramRequest)
  {
    if (paramRequest == null)
      return null;
    synchronized (this.g)
    {
      Request localRequest = (Request)this.g.get(paramRequest);
      return localRequest;
    }
  }

  private static String g(String paramString)
  {
    String str;
    if (h(paramString))
      str = null;
    do
    {
      return str;
      str = SecurityUtil.a(paramString);
    }
    while (!h(str));
    return String.valueOf(paramString.hashCode());
  }

  private Request h(Request paramRequest)
  {
    if (paramRequest == null)
      return null;
    synchronized (this.g)
    {
      Request localRequest = (Request)this.g.remove(paramRequest);
      return localRequest;
    }
  }

  private static boolean h(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  private void i(Request paramRequest)
  {
    if (paramRequest == null)
      return;
    ImageLoader.Options localOptions = paramRequest.d;
    if (localOptions == null);
    for (boolean bool = false; !bool; bool = localOptions.r)
    {
      paramRequest.c.a(paramRequest.b, localOptions);
      return;
    }
    this.f.post(new e(this, paramRequest, localOptions));
  }

  private void j(Request paramRequest)
  {
    if (paramRequest == null)
      return;
    ImageLoader.Options localOptions = paramRequest.d;
    if (localOptions == null);
    for (boolean bool = false; !bool; bool = localOptions.r)
    {
      paramRequest.c.b(paramRequest.b, localOptions);
      return;
    }
    this.f.post(new f(this, paramRequest, localOptions));
  }

  public Drawable a(String paramString)
  {
    return b(paramString, null);
  }

  public Drawable a(String paramString, ImageLoader.ImageLoadListener paramImageLoadListener)
  {
    return a(paramString, paramImageLoadListener, null);
  }

  public Drawable a(String paramString, ImageLoader.ImageLoadListener paramImageLoadListener, ImageLoader.Options paramOptions)
  {
    Drawable localDrawable1;
    if (paramImageLoadListener == null)
      localDrawable1 = a(paramString, paramOptions);
    boolean bool2;
    do
    {
      boolean bool1;
      do
      {
        return localDrawable1;
        bool1 = e(paramString);
        localDrawable1 = null;
      }
      while (!bool1);
      Request localRequest = a(paramString, paramString, paramImageLoadListener, paramOptions);
      Drawable localDrawable2 = a(localRequest, false);
      if (localDrawable2 != null)
        return localDrawable2;
      bool2 = f(localRequest);
      localDrawable1 = null;
    }
    while (!bool2);
    ThreadPool.getInstance().submit(new a(this, paramString, paramOptions, paramImageLoadListener));
    return null;
  }

  public Drawable a(String paramString, ImageLoader.Options paramOptions)
  {
    String[] arrayOfString;
    int k;
    label17: String str;
    if (paramOptions == null)
    {
      arrayOfString = null;
      if (arrayOfString == null)
        break label76;
      int j = arrayOfString.length;
      k = 0;
      if (k >= j)
        break label76;
      str = arrayOfString[k];
      if (e(str))
        break label52;
    }
    label52: Drawable localDrawable;
    do
    {
      k++;
      break label17;
      arrayOfString = paramOptions.s;
      break;
      localDrawable = a(a(paramString, str, null, paramOptions), false);
    }
    while (localDrawable == null);
    return localDrawable;
    label76: if (!e(paramString))
      return null;
    return a(a(paramString, paramString, null, paramOptions), false);
  }

  public void a()
  {
    Collection localCollection = a(null);
    if (localCollection != null)
    {
      LogUtil.i("ImageLoader", "cancel all request");
      Iterator localIterator = localCollection.iterator();
      while (localIterator.hasNext())
      {
        Request localRequest = (Request)localIterator.next();
        b(localRequest);
        i(localRequest);
      }
    }
  }

  public void a(ImageLoader.ReportHandler paramReportHandler)
  {
    this.h = paramReportHandler;
  }

  public Drawable b(String paramString, ImageLoader.Options paramOptions)
  {
    String str = f(paramString, paramOptions);
    if (!e(str))
      return null;
    return a(a(paramString, str, null, paramOptions), true);
  }

  public void b()
  {
    a();
    this.c.b();
  }

  public void b(String paramString)
  {
    c(paramString, null);
  }

  public void b(String paramString, ImageLoader.ImageLoadListener paramImageLoadListener, ImageLoader.Options paramOptions)
  {
    String str = f(paramString, paramOptions);
    if (!e(str))
      return;
    Request localRequest = h(a(paramString, str, paramImageLoadListener, paramOptions));
    b(localRequest);
    i(localRequest);
  }

  public File c(String paramString)
  {
    return d(paramString, null);
  }

  public void c(String paramString, ImageLoader.Options paramOptions)
  {
    if (!e(paramString))
      return;
    String str = e(a(null, paramString, null, paramOptions));
    this.c.a(str);
  }

  public File d(String paramString, ImageLoader.Options paramOptions)
  {
    if (!e(paramString))
      return null;
    return d(a(null, paramString, null, paramOptions));
  }

  public void d(String paramString)
  {
    e(paramString, null);
  }

  public void e(String paramString, ImageLoader.Options paramOptions)
  {
    if (!e(paramString));
    do
      return;
    while (!URLUtil.isNetworkUrl(paramString));
    Request localRequest = a(null, paramString, null, paramOptions);
    c(localRequest).d(localRequest.e);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.ImageLoader
 * JD-Core Version:    0.6.0
 */