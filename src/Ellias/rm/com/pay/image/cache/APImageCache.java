package com.pay.image.cache;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.LruCache;
import android.widget.ImageView;
import com.pay.tool.APAppDataInterface;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class APImageCache
{
  private static APImageCache a;
  private Queue b = new LinkedList();
  private Handler c = new c(this);
  public APDiskLruCache mDiskCache;
  public LruCache mMemoryCache;
  public Runnable runnable = new b(this);

  private APImageCache(Context paramContext)
  {
    int i = ((ActivityManager)paramContext.getSystemService("activity")).getMemoryClass();
    if (i > 32)
      i = 32;
    this.mMemoryCache = new d(this, i * 1048576 / 8);
    this.mDiskCache = APDiskLruCache.openCache(paramContext, APDiskLruCache.getDiskCacheDir(paramContext, APAppDataInterface.singleton().getOfferid()), 2097152L);
  }

  // ERROR //
  private static Bitmap a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: invokestatic 95	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   6: ifeq +5 -> 11
    //   9: aconst_null
    //   10: areturn
    //   11: new 97	java/net/URL
    //   14: dup
    //   15: aload_0
    //   16: invokespecial 100	java/net/URL:<init>	(Ljava/lang/String;)V
    //   19: invokevirtual 104	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   22: checkcast 106	java/net/HttpURLConnection
    //   25: astore 8
    //   27: aload 8
    //   29: iconst_1
    //   30: invokevirtual 110	java/net/HttpURLConnection:setDoInput	(Z)V
    //   33: aload 8
    //   35: invokevirtual 113	java/net/HttpURLConnection:connect	()V
    //   38: aload 8
    //   40: invokevirtual 117	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   43: astore 9
    //   45: aload 9
    //   47: astore 5
    //   49: aload 5
    //   51: ifnull +90 -> 141
    //   54: aload 8
    //   56: invokevirtual 120	java/net/HttpURLConnection:getResponseCode	()I
    //   59: sipush 200
    //   62: if_icmpne +79 -> 141
    //   65: aload 5
    //   67: invokestatic 126	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   70: astore 11
    //   72: aload 11
    //   74: astore 7
    //   76: aload 5
    //   78: invokevirtual 131	java/io/InputStream:close	()V
    //   81: aload 7
    //   83: areturn
    //   84: astore 4
    //   86: aconst_null
    //   87: astore 5
    //   89: aload 4
    //   91: invokevirtual 134	java/io/IOException:printStackTrace	()V
    //   94: aload 5
    //   96: invokevirtual 131	java/io/InputStream:close	()V
    //   99: aconst_null
    //   100: astore 7
    //   102: goto -21 -> 81
    //   105: astore 6
    //   107: aconst_null
    //   108: astore 7
    //   110: goto -29 -> 81
    //   113: astore_2
    //   114: aload_1
    //   115: invokevirtual 131	java/io/InputStream:close	()V
    //   118: aload_2
    //   119: athrow
    //   120: astore_3
    //   121: goto -3 -> 118
    //   124: astore 10
    //   126: goto -45 -> 81
    //   129: astore_2
    //   130: aload 5
    //   132: astore_1
    //   133: goto -19 -> 114
    //   136: astore 4
    //   138: goto -49 -> 89
    //   141: aconst_null
    //   142: astore 7
    //   144: goto -68 -> 76
    //
    // Exception table:
    //   from	to	target	type
    //   11	45	84	java/io/IOException
    //   94	99	105	java/lang/Exception
    //   11	45	113	finally
    //   114	118	120	java/lang/Exception
    //   76	81	124	java/lang/Exception
    //   54	72	129	finally
    //   89	94	129	finally
    //   54	72	136	java/io/IOException
  }

  private static void a(ImageView paramImageView, Bitmap paramBitmap, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      Drawable[] arrayOfDrawable = new Drawable[2];
      arrayOfDrawable[0] = new ColorDrawable(17170445);
      arrayOfDrawable[1] = new BitmapDrawable(paramBitmap);
      TransitionDrawable localTransitionDrawable = new TransitionDrawable(arrayOfDrawable);
      localTransitionDrawable.setCrossFadeEnabled(true);
      paramImageView.setImageDrawable(localTransitionDrawable);
      localTransitionDrawable.startTransition(300);
      return;
    }
    paramImageView.setImageBitmap(paramBitmap);
  }

  public static APImageCache getInstance(Context paramContext)
  {
    if (Looper.myLooper() != Looper.getMainLooper())
      throw new RuntimeException("Cannot instantiate outside UI thread.");
    if (a == null)
    {
      APImageCache localAPImageCache = new APImageCache(paramContext);
      a = localAPImageCache;
      localAPImageCache.intiThread();
    }
    return a;
  }

  public void displayImage(ImageView paramImageView, String paramString, int paramInt)
  {
    if (paramImageView == null);
    do
    {
      return;
      if (paramInt <= 0)
        continue;
      if (paramImageView.getBackground() == null)
        paramImageView.setBackgroundResource(paramInt);
      paramImageView.setImageDrawable(null);
    }
    while ((paramString == null) || (paramString.equals("")));
    Bitmap localBitmap1 = (Bitmap)this.mMemoryCache.get(paramString);
    if (localBitmap1 != null)
    {
      a(paramImageView, localBitmap1, false);
      return;
    }
    if (this.mDiskCache != null)
    {
      Bitmap localBitmap2 = this.mDiskCache.get(paramString);
      if (localBitmap2 != null)
      {
        if (this.mMemoryCache.get(paramString) == null)
          this.mMemoryCache.put(paramString, localBitmap2);
        a(paramImageView, localBitmap2, false);
        return;
      }
    }
    queueImage(new APImageCache.ImageRef(this, paramImageView, paramString, paramInt));
  }

  public void intiThread()
  {
    Executors.newSingleThreadExecutor().submit(this.runnable);
  }

  // ERROR //
  public void queueImage(APImageCache.ImageRef paramImageRef)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 26	com/pay/image/cache/APImageCache:b	Ljava/util/Queue;
    //   6: aload_1
    //   7: invokeinterface 256 2 0
    //   12: pop
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_0
    //   16: getfield 33	com/pay/image/cache/APImageCache:runnable	Ljava/lang/Runnable;
    //   19: astore 4
    //   21: aload 4
    //   23: monitorenter
    //   24: aload_0
    //   25: getfield 33	com/pay/image/cache/APImageCache:runnable	Ljava/lang/Runnable;
    //   28: invokevirtual 259	java/lang/Object:notify	()V
    //   31: aload 4
    //   33: monitorexit
    //   34: return
    //   35: astore_2
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_2
    //   39: athrow
    //   40: astore 5
    //   42: aload 4
    //   44: monitorexit
    //   45: aload 5
    //   47: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	15	35	finally
    //   24	34	40	finally
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.image.cache.APImageCache
 * JD-Core Version:    0.6.0
 */