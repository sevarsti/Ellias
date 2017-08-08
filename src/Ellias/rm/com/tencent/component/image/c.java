package com.tencent.component.image;

import android.graphics.drawable.Drawable;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.cache.file.FileCacheService;
import com.tencent.component.cache.image.ImageCacheService;
import com.tencent.component.net.http.AsyncHttpResult;
import com.tencent.component.net.http.AsyncHttpResult.Content;
import com.tencent.component.net.http.download.DownloadListener;
import com.tencent.component.net.http.download.DownloadResult;
import com.tencent.component.net.http.request.AsyncHttpRequest;
import com.tencent.component.utils.FileUtil;
import com.tencent.component.utils.log.LogUtil;
import java.io.File;

class c
  implements DownloadListener
{
  c(ImageLoader paramImageLoader, Request paramRequest)
  {
  }

  public void a(String paramString, long paramLong, float paramFloat)
  {
    ImageLoader.a(this.b, ImageLoader.e(this.b, this.a), paramFloat);
  }

  public void onRequestCanceled(AsyncHttpRequest paramAsyncHttpRequest)
  {
    ImageLoader.b(this.b, this.a);
  }

  public void onRequestEnqueque(AsyncHttpRequest paramAsyncHttpRequest)
  {
  }

  public void onRequestFailed(AsyncHttpRequest paramAsyncHttpRequest, AsyncHttpResult paramAsyncHttpResult)
  {
    ImageLoader.c(this.b, ImageLoader.b(this.b, this.a));
  }

  public void onRequestStart(AsyncHttpRequest paramAsyncHttpRequest)
  {
  }

  public void onRequestSuccess(AsyncHttpRequest paramAsyncHttpRequest, AsyncHttpResult paramAsyncHttpResult)
  {
    DownloadResult localDownloadResult = (DownloadResult)paramAsyncHttpResult;
    String str = this.a.i;
    if ((!ImageLoader.a(new File(str))) && (!CacheManager.b(str)) && (localDownloadResult.b() != null))
    {
      str = ImageLoader.a(this.b, this.a, false);
      if ((str != null) && (!ImageLoader.a(new File(str))))
        FileUtil.a(new File(localDownloadResult.b()), new File(str));
    }
    FileCacheService localFileCacheService = ImageLoader.d(this.b, this.a);
    localFileCacheService.c(this.a.e);
    boolean bool = localDownloadResult.getContent().noCache;
    this.a.g = str;
    this.a.f = new d(this, bool, localFileCacheService);
    if ((bool) && (this.a.d != null) && (this.a.d.n))
      this.a.d.n = false;
    Drawable localDrawable = ImageLoader.a(this.b).a(this.a.g, this.a.f, this.a.d);
    if (ImageLoader.a(localDrawable))
    {
      ImageLoader.a(this.b, ImageLoader.b(this.b, this.a), ImageLoader.a(localDrawable, this.a.d));
      if (bool)
        localFileCacheService.d(this.a.e);
      return;
    }
    LogUtil.i("ImageLoader", "download finish but drawable is invalid (url:" + paramAsyncHttpRequest.getUrl() + ")");
  }

  public void onRequestTimeout(AsyncHttpRequest paramAsyncHttpRequest)
  {
    ImageLoader.c(this.b, ImageLoader.b(this.b, this.a));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.c
 * JD-Core Version:    0.6.0
 */