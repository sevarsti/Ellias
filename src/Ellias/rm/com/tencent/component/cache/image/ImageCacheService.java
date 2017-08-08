package com.tencent.component.cache.image;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.cache.common.BlobCache;
import com.tencent.component.cache.common.BlobCache.LookupRequest;
import com.tencent.component.cache.common.BytesBufferPool;
import com.tencent.component.cache.common.BytesBufferPool.BytesBuffer;
import com.tencent.component.cache.common.FastLruCache;
import com.tencent.component.cache.common.LruCache;
import com.tencent.component.image.image.BitmapImage;
import com.tencent.component.image.image.Image;
import com.tencent.component.image.request.BitmapRequest;
import com.tencent.component.image.request.GifStreamRequest;
import com.tencent.component.image.request.ImageRequest;
import com.tencent.component.image.request.ImageRequest.Callback;
import com.tencent.component.image.request.VideoThumbnailRequest;
import com.tencent.component.media.MediaFile;
import com.tencent.component.media.MediaFile.MediaFileType;
import com.tencent.component.utils.DecodeUtils;
import com.tencent.component.utils.OOMHelper;
import com.tencent.component.utils.PlatformUtil;
import com.tencent.component.utils.ProcessUtils;
import com.tencent.component.utils.SecurityUtil;
import com.tencent.component.utils.collections.MultiHashMap;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.Future;
import com.tencent.component.utils.thread.ThreadPool;
import com.tencent.component.utils.thread.ThreadPool.Priority;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ImageCacheService
{
  private static volatile ImageCacheService H;
  static final ImageCache.Matcher a;
  private static final String b = "ImageCacheService";
  private static final int c = 1048576;
  private static final float d = 0.25F;
  private static final float e = 0.35F;
  private static final int f = 16;
  private static final String g = "img";
  private static final int h = 2500;
  private static final int i = 104857600;
  private static final int j = 1;
  private static final int k = 4;
  private static final int l = 204800;
  private static final String m = "image/gif";
  private static final float n = 1.2F;
  private static final float o = 0.0625F;
  private static final int p = 2097152;
  private static final float q = 0.2F;
  private static final int r = 10485760;
  private static final int s = 4;
  private static final int t = 1000;
  private static final BytesBufferPool u = new BytesBufferPool(4, 204800);
  private static final FastLruCache v = new FastLruCache(1000);
  private final ImageCache A;
  private final LruCache B = new LruCache(500);
  private final HashMap C = new HashMap();
  private final MultiHashMap D = new MultiHashMap();
  private final int E;
  private final int F;
  private final ImageRequest.Callback G = new e(this);
  private final Context w;
  private final ThreadPool x;
  private volatile BlobCache y;
  private final Object z = new Object();

  static
  {
    a = new h();
  }

  private ImageCacheService(Context paramContext)
  {
    this.w = paramContext.getApplicationContext();
    this.x = ThreadPool.getInstance();
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    if (PlatformUtil.version() >= 5);
    for (int i1 = localActivityManager.getMemoryClass(); ; i1 = 16)
    {
      if (i1 <= 0)
        i1 = 16;
      this.A = new ImageCache((int)(0.25F * (i1 * 1048576)), (int)(0.35F * (i1 * 1048576)));
      this.E = (int)Math.max(0.2F * (i1 * 1048576), 10485760.0F);
      this.F = (int)Math.max(0.0625F * (i1 * 1048576), 2097152.0F);
      return;
    }
  }

  public static ImageCacheService a(Context paramContext)
  {
    if (H != null)
      return H;
    monitorenter;
    try
    {
      if (H != null)
      {
        ImageCacheService localImageCacheService2 = H;
        return localImageCacheService2;
      }
    }
    finally
    {
      monitorexit;
    }
    ImageCacheService localImageCacheService1 = new ImageCacheService(paramContext);
    H = localImageCacheService1;
    monitorexit;
    return localImageCacheService1;
  }

  private Image a(Image paramImage, ImageEntry paramImageEntry)
  {
    if ((paramImage == null) || (paramImageEntry == null));
    while (true)
    {
      return paramImage;
      ImageProcessor localImageProcessor = paramImageEntry.e;
      if ((localImageProcessor == null) || (!(paramImage instanceof BitmapImage)))
        continue;
      Bitmap localBitmap1 = ((BitmapImage)paramImage).e();
      int i1 = localBitmap1.getWidth();
      int i2 = localBitmap1.getHeight();
      try
      {
        Bitmap localBitmap3 = localImageProcessor.a(localBitmap1, true);
        localBitmap2 = localBitmap3;
        if ((localBitmap2 == null) || (localBitmap2 == localBitmap1))
          continue;
        BitmapImage localBitmapImage = new BitmapImage(localBitmap2);
        localBitmapImage.f().a = i1;
        localBitmapImage.f().b = i2;
        return localBitmapImage;
      }
      catch (Throwable localThrowable)
      {
        while (true)
        {
          a(localThrowable);
          Bitmap localBitmap2 = null;
        }
      }
    }
  }

  private ImageRequest a(ImageEntry paramImageEntry)
  {
    if (g(paramImageEntry.a))
      return new VideoThumbnailRequest(this.w, paramImageEntry, this.G);
    if ((!paramImageEntry.c) && (f(paramImageEntry.a)))
      return new GifStreamRequest(paramImageEntry, this.G, paramImageEntry.d);
    return new BitmapRequest(paramImageEntry, this.G, paramImageEntry.d);
  }

  private MultiHashMap a(MultiHashMap paramMultiHashMap)
  {
    MultiHashMap localMultiHashMap = this.D;
    monitorenter;
    if (paramMultiHashMap != null);
    try
    {
      paramMultiHashMap.clear();
      if (this.D.isEmpty())
        return paramMultiHashMap;
      if (paramMultiHashMap == null)
        paramMultiHashMap = new MultiHashMap();
      paramMultiHashMap.putAll(this.D);
      this.D.clear();
      return paramMultiHashMap;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void a(ImageEntry paramImageEntry, ImageCacheService.ImageCacheListener paramImageCacheListener, Drawable paramDrawable)
  {
    if ((paramImageEntry != null) && (paramImageCacheListener != null))
      paramImageCacheListener.a(paramImageEntry.a, paramDrawable);
  }

  private void a(ImageEntry paramImageEntry, ImageCacheService.ImageCacheListener paramImageCacheListener, Throwable paramThrowable)
  {
    if ((paramImageEntry != null) && (paramImageCacheListener != null))
      paramImageCacheListener.a(paramImageEntry.a, paramThrowable);
  }

  private void a(ImageEntry paramImageEntry, Collection paramCollection)
  {
    if ((paramImageEntry != null) && (paramCollection != null))
    {
      String str = paramImageEntry.a;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        ImageCacheService.ImageCacheListener localImageCacheListener = (ImageCacheService.ImageCacheListener)localIterator.next();
        if (localImageCacheListener == null)
          continue;
        localImageCacheListener.a(str);
      }
    }
  }

  private void a(ImageEntry paramImageEntry, Collection paramCollection, Drawable paramDrawable)
  {
    if ((paramImageEntry != null) && (paramCollection != null))
    {
      String str = paramImageEntry.a;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        ImageCacheService.ImageCacheListener localImageCacheListener = (ImageCacheService.ImageCacheListener)localIterator.next();
        if (localImageCacheListener == null)
          continue;
        localImageCacheListener.a(str, paramDrawable);
      }
    }
  }

  private void a(ImageEntry paramImageEntry, Collection paramCollection, Throwable paramThrowable)
  {
    if ((paramImageEntry != null) && (paramCollection != null))
    {
      String str = paramImageEntry.a;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        ImageCacheService.ImageCacheListener localImageCacheListener = (ImageCacheService.ImageCacheListener)localIterator.next();
        if (localImageCacheListener == null)
          continue;
        localImageCacheListener.a(str, paramThrowable);
      }
    }
  }

  private void a(ImageEntry paramImageEntry, byte[] paramArrayOfByte)
  {
    if ((paramImageEntry == null) || (paramArrayOfByte == null));
    do
    {
      return;
      if (this.y != null)
        break;
      b(this.w);
    }
    while (this.y == null);
    byte[] arrayOfByte = paramImageEntry.a();
    long l1 = SecurityUtil.a(arrayOfByte);
    ByteBuffer localByteBuffer = ByteBuffer.allocate(arrayOfByte.length + paramArrayOfByte.length);
    localByteBuffer.put(arrayOfByte);
    localByteBuffer.put(paramArrayOfByte);
    try
    {
      synchronized (this.z)
      {
        this.y.a(l1, localByteBuffer.array());
        label89: return;
      }
    }
    catch (IOException localIOException)
    {
      break label89;
    }
  }

  private boolean a(int paramInt1, int paramInt2, float paramFloat, int paramInt3)
  {
    if (paramInt3 <= 0)
      return true;
    if (paramFloat < 1.0F)
      paramFloat = 1.0F;
    int i1 = Math.min(this.A.c(), paramInt3);
    if (4 * (int)(paramInt1 * paramInt2 / paramFloat) <= i1);
    for (int i2 = 1; ; i2 = 0)
      return i2;
  }

  private boolean a(ImageEntry paramImageEntry, BytesBufferPool.BytesBuffer paramBytesBuffer)
  {
    if (paramImageEntry == null);
    byte[] arrayOfByte;
    BlobCache.LookupRequest localLookupRequest;
    do
    {
      do
      {
        return false;
        if (this.y != null)
          break;
        b(this.w);
      }
      while (this.y == null);
      arrayOfByte = paramImageEntry.a();
      long l1 = SecurityUtil.a(arrayOfByte);
      try
      {
        localLookupRequest = new BlobCache.LookupRequest();
        localLookupRequest.a = l1;
        localLookupRequest.b = paramBytesBuffer.a;
        synchronized (this.z)
        {
          if (!this.y.a(localLookupRequest))
            return false;
        }
      }
      catch (IOException localIOException)
      {
        return false;
      }
      monitorexit;
    }
    while (!a(arrayOfByte, localLookupRequest.b));
    paramBytesBuffer.a = localLookupRequest.b;
    paramBytesBuffer.b = arrayOfByte.length;
    paramBytesBuffer.c = (localLookupRequest.c - paramBytesBuffer.b);
    return true;
  }

  private boolean a(ImageEntry paramImageEntry, ImageCacheService.ImageCacheListener paramImageCacheListener)
  {
    if (paramImageCacheListener == null)
      return false;
    synchronized (this.D)
    {
      int i1 = this.D.a(paramImageEntry);
      this.D.a(paramImageEntry, paramImageCacheListener);
      int i2 = 0;
      if (i1 == 0)
        i2 = 1;
      return i2;
    }
  }

  private boolean a(ImageEntry paramImageEntry, ImageCacheService.ImageCacheListener paramImageCacheListener, Collection paramCollection)
  {
    if (paramImageCacheListener == null)
      return false;
    synchronized (this.D)
    {
      int i1 = this.D.a(paramImageEntry);
      if (paramCollection != null)
        paramCollection.clear();
      if ((this.D.b(paramImageEntry, paramImageCacheListener)) && (paramCollection != null))
        paramCollection.add(paramImageCacheListener);
      int i2 = 0;
      if (i1 > 0)
      {
        int i3 = this.D.a(paramImageEntry);
        i2 = 0;
        if (i3 == 0)
          i2 = 1;
      }
      return i2;
    }
  }

  private boolean a(Throwable paramThrowable)
  {
    if (paramThrowable == null);
    do
    {
      return false;
      LogUtil.e("ImageCacheService", "handle exception, thread=" + Thread.currentThread().getId(), paramThrowable);
      OOMHelper.a(this.w, paramThrowable);
    }
    while (!(paramThrowable instanceof OutOfMemoryError));
    this.A.a();
    System.gc();
    System.gc();
    return true;
  }

  private static boolean a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int i1 = paramArrayOfByte1.length;
    if (paramArrayOfByte2.length < i1)
      return false;
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        break label33;
      if (paramArrayOfByte1[i2] != paramArrayOfByte2[i2])
        break;
    }
    label33: return true;
  }

  private int b(String paramString, ImageCacheService.Options paramOptions)
  {
    int i1 = 2147483647;
    int i2 = -1;
    int i3;
    label19: boolean bool;
    if (paramOptions == null)
    {
      i3 = i2;
      if (paramOptions != null)
        break label61;
      if (paramOptions != null)
        break label70;
      bool = false;
      label26: if (i3 <= 0)
        i3 = i1;
      if (i2 > 0)
        break label311;
    }
    while (true)
    {
      BitmapFactory.Options localOptions = h(paramString);
      if (localOptions == null)
      {
        return 1;
        i3 = paramOptions.i;
        break;
        label61: i2 = paramOptions.j;
        break label19;
        label70: bool = paramOptions.k;
        break label26;
      }
      int i4 = localOptions.outWidth;
      int i5 = localOptions.outHeight;
      float f3;
      float f4;
      if ((i3 < i4) || (i1 < i5))
        if (i3 * i5 > i1 * i4)
        {
          float f6 = i4 / i3;
          float f7 = i5 / i1;
          f3 = f6;
          f4 = f7;
          if (!bool)
            break label206;
          label148: if (f3 < 1.0F)
            f3 = 1.0F;
        }
      while (true)
      {
        int i6 = 0;
        while (true)
          if (f3 > 1 << i6)
          {
            i6++;
            continue;
            float f1 = i5 / i1;
            float f2 = i4 / i3;
            f3 = f1;
            f4 = f2;
            break;
            label206: f3 = (float)Math.sqrt(f3 * f4);
            break label148;
          }
        int i7;
        if (i6 > 0)
        {
          float f5 = 1 << i6;
          if ((f5 / f3 > 1.2F) && (a(i4, i5, f5, this.F)))
            i7 = i6 - 1;
        }
        while (true)
        {
          if (!a(i4, i5, 1 << i7, this.E))
          {
            i7++;
            continue;
          }
          return 1 << i7;
          i7 = i6;
        }
        f3 = 1.0F;
      }
      label311: i1 = i2;
    }
  }

  private static Drawable b(ImageEntry paramImageEntry, Image paramImage)
  {
    if (!d(paramImage))
      return null;
    return paramImage.a(paramImageEntry);
  }

  private Collection b(ImageEntry paramImageEntry, Collection paramCollection)
  {
    Collection localCollection;
    synchronized (this.D)
    {
      localCollection = (Collection)this.D.remove(paramImageEntry);
      if (paramCollection != null)
      {
        paramCollection.clear();
        if (localCollection != null)
          paramCollection.addAll(localCollection);
      }
      if (paramCollection != null)
        return paramCollection;
    }
    return localCollection;
  }

  private void b(Context paramContext)
  {
    monitorenter;
    try
    {
      BlobCache localBlobCache = this.y;
      if (localBlobCache != null);
      while (true)
      {
        return;
        String str = "img";
        if (!ProcessUtils.b(paramContext))
          str = str + "_" + SecurityUtil.a(ProcessUtils.a(paramContext));
        this.y = CacheManager.a(paramContext, str, 2500, 104857600, 1);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void b(Image paramImage)
  {
    if (!d(paramImage))
      return;
    k.a(this.w).a(paramImage);
  }

  private static j c(String paramString, ImageCacheService.Options paramOptions)
  {
    return new j(paramString, paramOptions);
  }

  private static void c(Image paramImage)
  {
    if ((paramImage != null) && (!paramImage.c()))
      paramImage.b();
  }

  private static boolean c(String paramString)
  {
    return !g(paramString);
  }

  private ImageEntry d(String paramString, ImageCacheService.Options paramOptions)
  {
    ImageProcessor localImageProcessor;
    boolean bool1;
    label15: label19: Bitmap.Config localConfig;
    label28: boolean bool2;
    if (paramOptions == null)
    {
      localImageProcessor = ImageCacheService.Options.h;
      if (paramOptions != null)
        break label96;
      bool1 = true;
      if (paramOptions != null)
        break label105;
      if (paramOptions != null)
        break label113;
      localConfig = ImageCacheService.Options.g;
      if (!bool1)
      {
        if (d(paramString))
          break label122;
        bool2 = true;
        label43: bool1 = bool2;
      }
      if (!c(paramString))
        break label128;
    }
    label128: for (int i1 = b(paramString, paramOptions); ; i1 = 1)
    {
      ImageEntry localImageEntry = new ImageEntry(paramString, i1, bool1, localConfig, localImageProcessor);
      localImageEntry.a(paramOptions);
      return localImageEntry;
      localImageProcessor = paramOptions.p;
      break;
      label96: bool1 = paramOptions.m;
      break label15;
      label105: break label19;
      label113: localConfig = paramOptions.o;
      break label28;
      label122: bool2 = false;
      break label43;
    }
  }

  private static boolean d(Image paramImage)
  {
    return (paramImage != null) && (!paramImage.c());
  }

  private static boolean d(String paramString)
  {
    return (g(paramString)) || (f(paramString));
  }

  private static boolean e(String paramString)
  {
    return f(paramString);
  }

  private static boolean f(String paramString)
  {
    BitmapFactory.Options localOptions = h(paramString);
    boolean bool = false;
    if (localOptions != null)
      bool = "image/gif".equalsIgnoreCase(localOptions.outMimeType);
    return bool;
  }

  private static boolean g(String paramString)
  {
    MediaFile.MediaFileType localMediaFileType = MediaFile.a(paramString);
    return (localMediaFileType != null) && (MediaFile.b(localMediaFileType.a));
  }

  private static BitmapFactory.Options h(String paramString)
  {
    i locali = new i(paramString);
    BitmapFactory.Options localOptions = (BitmapFactory.Options)v.b(locali);
    if (localOptions == null)
    {
      localOptions = new BitmapFactory.Options();
      localOptions.inPreferredConfig = ImageCacheService.Options.g;
    }
    try
    {
      DecodeUtils.b(paramString, localOptions);
      v.a(locali, localOptions);
      return localOptions;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }

  private static boolean i(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    File localFile;
    do
    {
      return false;
      localFile = new File(paramString);
    }
    while ((!localFile.isFile()) || (localFile.length() <= 0L));
    return true;
  }

  public Drawable a(String paramString, ImageCacheService.ImageCacheListener paramImageCacheListener)
  {
    return a(paramString, paramImageCacheListener, null);
  }

  public Drawable a(String paramString, ImageCacheService.ImageCacheListener paramImageCacheListener, ImageCacheService.Options paramOptions)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    j localj = c(paramString, paramOptions);
    ImageEntry localImageEntry = (ImageEntry)this.B.b(localj);
    if (localImageEntry != null)
    {
      Image localImage = this.A.a(localImageEntry);
      if (d(localImage))
        return b(localImageEntry, localImage);
      this.B.c(localj);
    }
    if (paramImageCacheListener == null)
      return null;
    boolean bool;
    ThreadPool localThreadPool;
    f localf;
    if (paramOptions == null)
    {
      bool = true;
      localThreadPool = ThreadPool.getInstance();
      localf = new f(this, localj, paramOptions, paramImageCacheListener, bool);
      if (!bool)
        break label137;
    }
    label137: for (ThreadPool.Priority localPriority = ThreadPool.Priority.HIGH; ; localPriority = ThreadPool.Priority.NORMAL)
    {
      localThreadPool.submit(localf, localPriority);
      return null;
      bool = paramOptions.l;
      break;
    }
  }

  public Drawable a(String paramString, ImageCacheService.Options paramOptions)
  {
    if (TextUtils.isEmpty(paramString));
    j localj;
    ImageEntry localImageEntry1;
    ImageEntry localImageEntry2;
    do
    {
      return null;
      localj = c(paramString, paramOptions);
      localImageEntry1 = (ImageEntry)this.B.b(localj);
      if (localImageEntry1 != null);
      for (localImageEntry2 = localImageEntry1; ; localImageEntry2 = d(paramString, paramOptions))
      {
        Image localImage1 = this.A.a(localImageEntry2);
        if (!d(localImage1))
          break;
        return b(localImageEntry2, localImage1);
      }
    }
    while (!i(paramString));
    if (localImageEntry1 == null)
      this.B.b(localj, localImageEntry2);
    ImageResult localImageResult = (ImageResult)a(localImageEntry2).run(ThreadPool.a);
    if (localImageResult == null);
    for (Image localImage2 = null; ; localImage2 = localImageResult.a())
    {
      if (d(localImage2))
        this.A.a(localImageEntry2, localImage2);
      return b(localImageEntry2, localImage2);
    }
  }

  public void a()
  {
    MultiHashMap localMultiHashMap = a(null);
    synchronized (this.C)
    {
      boolean bool = this.C.isEmpty();
      ArrayList localArrayList = null;
      if (!bool)
      {
        localArrayList = new ArrayList(this.C.values());
        this.C.clear();
      }
      if (localArrayList != null)
      {
        Iterator localIterator2 = localArrayList.iterator();
        while (localIterator2.hasNext())
        {
          Future localFuture = (Future)localIterator2.next();
          if (localFuture == null)
            continue;
          localFuture.cancel();
        }
      }
    }
    if (localMultiHashMap != null)
    {
      Iterator localIterator1 = localMultiHashMap.keySet().iterator();
      while (localIterator1.hasNext())
      {
        ImageEntry localImageEntry = (ImageEntry)localIterator1.next();
        if (localImageEntry == null)
          continue;
        a(localImageEntry, (Collection)localMultiHashMap.get(localImageEntry));
      }
    }
  }

  public void a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return;
    ImageEntry localImageEntry = d(paramString, null);
    this.A.a(localImageEntry, a);
  }

  public void b()
  {
    a();
    this.A.a();
  }

  public void b(String paramString, ImageCacheService.ImageCacheListener paramImageCacheListener, ImageCacheService.Options paramOptions)
  {
    if (TextUtils.isEmpty(paramString));
    ImageEntry localImageEntry1;
    do
    {
      return;
      j localj = c(paramString, paramOptions);
      localImageEntry1 = (ImageEntry)this.B.b(localj);
      if (localImageEntry1 != null)
        break;
    }
    while (!i(paramString));
    for (ImageEntry localImageEntry2 = d(paramString, paramOptions); ; localImageEntry2 = localImageEntry1)
    {
      ArrayList localArrayList = new ArrayList();
      if (a(localImageEntry2, paramImageCacheListener, localArrayList));
      synchronized (this.C)
      {
        Future localFuture = (Future)this.C.remove(localImageEntry2);
        if (localFuture != null)
          localFuture.cancel();
        a(localImageEntry2, localArrayList);
        return;
      }
    }
  }

  public int c()
  {
    return this.A.b();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.ImageCacheService
 * JD-Core Version:    0.6.0
 */