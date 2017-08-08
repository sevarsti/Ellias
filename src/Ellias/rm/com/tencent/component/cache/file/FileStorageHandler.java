package com.tencent.component.cache.file;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.Future;
import com.tencent.component.utils.thread.ThreadPool;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class FileStorageHandler
{
  private static final String a = "FileStorageHandler";
  private static final long b = 1048576L;
  private static final int c = 3;
  private static final long d = 10485760L;
  private static final int e = 2;
  private static final float f = 0.1F;
  private static final float g = 0.05F;
  private static final float h = 0.02F;
  private static final float i = 0.1F;
  private static final int j = 60000;
  private static final int k = 1800000;
  private static final int l = 6;
  private static final Handler m = new Handler(Looper.getMainLooper());
  private final Context n;
  private final FileStorageHandler.Collector o;
  private final AtomicInteger p = new AtomicInteger(0);
  private final AtomicInteger q = new AtomicInteger(0);
  private Future r;
  private long s;
  private int t;

  public FileStorageHandler(Context paramContext, FileStorageHandler.Collector paramCollector)
  {
    this.n = paramContext.getApplicationContext();
    this.o = paramCollector;
  }

  private int a(int paramInt1, int paramInt2)
  {
    if (paramInt1 <= 0)
      return paramInt1;
    float f1;
    if (paramInt2 / paramInt1 < 0.12F)
      f1 = 0.05F;
    while (true)
    {
      return (int)(f1 * paramInt1);
      f1 = 0.1F;
    }
  }

  private void a(long paramLong1, long paramLong2, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((!paramBoolean2) && (this.q.getAndIncrement() < 2))
      return;
    this.q.set(0);
    a(paramLong1, paramLong2, paramBoolean1);
  }

  private void a(Context paramContext)
  {
    if (paramContext == null);
    do
      return;
    while (!b());
    m.post(new f(this, paramContext));
  }

  private void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramBoolean1) && (!CacheManager.a()));
    long l1;
    long l2;
    do
    {
      File localFile;
      while (true)
      {
        return;
        if (paramBoolean1);
        for (localFile = Environment.getExternalStorageDirectory(); localFile != null; localFile = Environment.getDataDirectory())
          while (!localFile.exists())
            localFile = localFile.getParentFile();
      }
      StatFs localStatFs = new StatFs(localFile.getAbsolutePath());
      l1 = localStatFs.getBlockCount() * localStatFs.getBlockSize();
      l2 = localStatFs.getAvailableBlocks() * localStatFs.getBlockSize();
    }
    while (l2 >= 10485760L);
    a(l1, l2, paramBoolean1, paramBoolean2);
  }

  private boolean b()
  {
    long l1 = ()(1800000.0F * (1.0F - 1.0F / (1.0F + this.t / 6.0F)));
    long l2 = System.currentTimeMillis();
    if (l2 - this.s >= l1);
    for (int i1 = 1; ; i1 = 0)
    {
      if (i1 != 0)
      {
        if (this.t < 2147483647)
          this.t = (1 + this.t);
        this.s = l2;
      }
      return i1;
    }
  }

  public void a()
  {
    this.s = 0L;
    this.t = 0;
  }

  protected void a(long paramLong1, long paramLong2, boolean paramBoolean)
  {
    LogUtil.w("FileStorageHandler", "low storage: totalSize=" + paramLong1 + ", availableSize=" + paramLong2 + ", external=" + paramBoolean);
    monitorenter;
    try
    {
      if ((this.r != null) && (!this.r.isDone()))
        return;
      Context localContext = this.n;
      this.r = ThreadPool.getInstance().submit(new e(this, paramBoolean, localContext));
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(FileStorageHandler.Mode paramMode)
  {
    a(paramMode, false);
  }

  public void a(FileStorageHandler.Mode paramMode, boolean paramBoolean)
  {
    if (paramMode == null);
    do
      return;
    while ((!paramBoolean) && (this.p.getAndIncrement() < 3));
    this.p.set(0);
    switch (g.a[paramMode.ordinal()])
    {
    default:
      return;
    case 1:
      a(true, paramBoolean);
      return;
    case 2:
      a(false, paramBoolean);
      return;
    case 3:
    }
    a(true, paramBoolean);
    a(false, paramBoolean);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.file.FileStorageHandler
 * JD-Core Version:    0.6.0
 */