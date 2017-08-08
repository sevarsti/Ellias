package com.tencent.component.net.http.download;

import android.content.Context;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.cache.common.BytesBufferPool;
import com.tencent.component.cache.file.FileCacheService;
import com.tencent.component.net.http.AsyncHttpClient;
import com.tencent.component.net.http.AsyncHttpResult;
import com.tencent.component.net.http.AsyncHttpResult.Content;
import com.tencent.component.net.http.AsyncHttpResult.Status;
import com.tencent.component.net.http.AsyncResponseHandler;
import com.tencent.component.net.http.ContentHandler;
import com.tencent.component.net.http.request.AsyncHttpRequest;
import com.tencent.component.net.http.strategy.StrategyInfo;
import com.tencent.component.net.http.strategy.SwitchProxyRetryHandler;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.FileUtil;
import com.tencent.component.utils.HttpUtil;
import com.tencent.component.utils.HttpUtil.ClientOptions;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.ThreadPool;
import com.tencent.component.utils.thread.ThreadPool.JobContext;
import com.tencent.component.utils.thread.ThreadPool.Priority;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;

public class Downloader extends AsyncHttpClient
{
  private static final int a = 2;
  private static final int b = 0;
  private static final int c = 40;
  private static final int d = 3;
  private static final long e = 120L;
  private static final TimeUnit f = TimeUnit.SECONDS;
  private static final String g = "Downloader";
  private static final int h = 4;
  private static final int i = 8192;
  private static final BytesBufferPool l;
  private final FileCacheService j;
  private ContentHandler k;
  private AsyncResponseHandler m = new a(this);

  static
  {
    b = Math.min(1 + 2 * Runtime.getRuntime().availableProcessors(), 5);
    l = new BytesBufferPool(4, 8192);
  }

  public Downloader(Context paramContext)
  {
    super(paramContext);
    this.j = CacheManager.f(paramContext);
  }

  public static boolean a(File paramFile, boolean paramBoolean)
  {
    monitorenter;
    int n = 0;
    if (paramFile == null);
    while (true)
    {
      monitorexit;
      return n;
      try
      {
        File localFile = paramFile.getParentFile();
        if ((localFile.exists()) && (localFile.isFile()))
          FileUtil.a(localFile);
        if (!localFile.exists())
        {
          boolean bool2 = localFile.mkdirs();
          n = 0;
          if (!bool2)
            continue;
        }
        if ((paramBoolean) && (paramFile.exists()))
          FileUtil.a(paramFile);
        if (!paramFile.exists())
        {
          boolean bool1 = paramFile.createNewFile();
          n = 0;
          if (!bool1)
            continue;
        }
        n = 1;
        continue;
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject;
  }

  private DownloadRequest b(String paramString1, String paramString2, DownloadListener paramDownloadListener)
  {
    DownloadRequest localDownloadRequest = new DownloadRequest(paramString1, paramString2);
    localDownloadRequest.a(paramDownloadListener);
    return localDownloadRequest;
  }

  public void a(ContentHandler paramContentHandler)
  {
    this.k = paramContentHandler;
  }

  public void a(String paramString1, String paramString2, DownloadListener paramDownloadListener)
  {
    if (!DownloadRequest.a(paramString1, paramString2))
      return;
    cancel(b(paramString1, paramString2, paramDownloadListener));
  }

  public boolean a(String paramString1, String paramString2, ThreadPool.Priority paramPriority)
  {
    return a(paramString1, paramString2, paramPriority, null);
  }

  public boolean a(String paramString1, String paramString2, ThreadPool.Priority paramPriority, DownloadListener paramDownloadListener)
  {
    if (!DownloadRequest.a(paramString1, paramString2))
      return false;
    DownloadRequest localDownloadRequest = b(paramString1, paramString2, paramDownloadListener);
    localDownloadRequest.a(this.m);
    localDownloadRequest.a(new SwitchProxyRetryHandler());
    localDownloadRequest.a(paramPriority);
    sendRequest(localDownloadRequest);
    return true;
  }

  protected void handleReport(Context paramContext, ThreadPool.JobContext paramJobContext, StrategyInfo paramStrategyInfo, AsyncHttpRequest paramAsyncHttpRequest, AsyncHttpResult paramAsyncHttpResult)
  {
    if ((paramStrategyInfo != null) && (paramAsyncHttpResult.getStatus().isSucceed()))
      ProxyStatistics.d().a(paramContext, paramStrategyInfo.allowProxy, paramStrategyInfo.useConfigApn);
  }

  protected DefaultHttpClient obtainHttpClient()
  {
    HttpUtil.ClientOptions localClientOptions = new HttpUtil.ClientOptions();
    localClientOptions.a = true;
    localClientOptions.d = 40;
    localClientOptions.e = 3;
    localClientOptions.b = 120L;
    localClientOptions.c = f;
    return HttpUtil.a(localClientOptions);
  }

  protected void onHttpTaskFinish(Collection paramCollection, AsyncHttpRequest paramAsyncHttpRequest, AsyncHttpResult paramAsyncHttpResult)
  {
    if ((paramCollection != null) && (paramAsyncHttpResult != null))
    {
      ArrayList localArrayList = new ArrayList();
      DownloadResult localDownloadResult = (DownloadResult)paramAsyncHttpResult;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        AsyncHttpRequest localAsyncHttpRequest = (AsyncHttpRequest)localIterator.next();
        if (localAsyncHttpRequest == null)
          continue;
        DownloadRequest localDownloadRequest = (DownloadRequest)localAsyncHttpRequest;
        if ((localDownloadResult.b() == null) || (localArrayList.contains(localDownloadRequest.a())))
          continue;
        try
        {
          FileUtil.a(new File(localDownloadResult.b()), new File(localDownloadRequest.a()));
          localArrayList.add(localDownloadRequest.a());
        }
        catch (Throwable localThrowable)
        {
          LogUtil.i("Downloader", "copy file exception!!! " + paramAsyncHttpRequest.getUrl(), localThrowable);
        }
      }
      if (DebugUtil.a())
      {
        if (!paramAsyncHttpResult.getStatus().isSucceed())
          break label232;
        LogUtil.i("Downloader", "success to download :" + paramAsyncHttpRequest.getUrl() + " |nocache:" + paramAsyncHttpResult.getContent().noCache);
      }
    }
    return;
    label232: LogUtil.e("Downloader", "failed to download :" + paramAsyncHttpRequest.getUrl() + " |nocache:" + paramAsyncHttpResult.getContent().noCache);
  }

  protected void shutDownHttpClient(DefaultHttpClient paramDefaultHttpClient)
  {
    if (paramDefaultHttpClient != null)
      paramDefaultHttpClient.getConnectionManager().shutdown();
  }

  protected ThreadPool supplyThreadPool()
  {
    return new ThreadPool("download", 2, b);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.download.Downloader
 * JD-Core Version:    0.6.0
 */