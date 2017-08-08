package com.tencent.component.net.http.download;

import android.os.StatFs;
import android.text.TextUtils;
import com.tencent.component.cache.file.FileCacheService;
import com.tencent.component.net.http.AsyncHttpResult;
import com.tencent.component.net.http.AsyncHttpResult.Content;
import com.tencent.component.net.http.AsyncHttpResult.ContentTypeMismatchDescription;
import com.tencent.component.net.http.AsyncHttpResult.Status;
import com.tencent.component.net.http.AsyncResponseHandler;
import com.tencent.component.net.http.ContentHandler;
import com.tencent.component.net.http.request.AsyncHttpRequest;
import com.tencent.component.utils.thread.ThreadPool.JobContext;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

class a
  implements AsyncResponseHandler
{
  a(Downloader paramDownloader)
  {
  }

  private String a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return UUID.randomUUID().toString();
    return String.valueOf(paramString.hashCode());
  }

  private String a(String paramString, boolean paramBoolean)
  {
    return Downloader.a(this.a).a(paramString, paramBoolean);
  }

  private void a(Collection paramCollection, long paramLong, float paramFloat)
  {
    if (paramCollection == null);
    while (true)
    {
      return;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        AsyncHttpRequest localAsyncHttpRequest = (AsyncHttpRequest)localIterator.next();
        if ((localAsyncHttpRequest == null) || (localAsyncHttpRequest.isCanceled()))
          continue;
        DownloadListener localDownloadListener = (DownloadListener)localAsyncHttpRequest.getRequestListener();
        if (localDownloadListener == null)
          continue;
        localDownloadListener.a(localAsyncHttpRequest.getUrl(), paramLong, paramFloat);
      }
    }
  }

  private boolean a(AsyncHttpResult paramAsyncHttpResult, HttpResponse paramHttpResponse)
  {
    ContentHandler localContentHandler = Downloader.b(this.a);
    if (localContentHandler != null)
      return localContentHandler.a(paramAsyncHttpResult, paramHttpResponse);
    return true;
  }

  private boolean a(String paramString, long paramLong)
  {
    int i = 1;
    if (TextUtils.isEmpty(paramString));
    while (true)
    {
      return false;
      try
      {
        File localFile = new File(paramString);
        if (!Downloader.a(localFile, true))
          continue;
        Downloader.a(this.a).c(localFile.getName());
        if (paramLong <= 0L)
          return i;
        while (!localFile.exists())
          localFile = localFile.getParentFile();
        StatFs localStatFs = new StatFs(localFile.getAbsolutePath());
        long l = localStatFs.getAvailableBlocks();
        int j = localStatFs.getBlockSize();
        if (l * j > paramLong);
        while (true)
        {
          return i;
          i = 0;
        }
      }
      catch (IOException localIOException)
      {
      }
    }
    return false;
  }

  private boolean a(HttpResponse paramHttpResponse, AsyncHttpResult paramAsyncHttpResult, ThreadPool.JobContext paramJobContext)
  {
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    long l = localHttpEntity.getContentLength();
    if (l == -1L)
    {
      Header[] arrayOfHeader = paramHttpResponse.getHeaders("Content-Length");
      if ((arrayOfHeader != null) && (arrayOfHeader.length > 0))
        l = Long.valueOf(arrayOfHeader[0].getValue()).longValue();
    }
    paramAsyncHttpResult.getContent().contentLength = l;
    Header localHeader1 = localHttpEntity.getContentType();
    if (localHeader1 != null)
      paramAsyncHttpResult.getContent().type = localHeader1.getValue();
    Header localHeader2 = localHttpEntity.getContentEncoding();
    if (localHeader2 != null)
      paramAsyncHttpResult.getContent().encoding = localHeader2.getValue();
    if (paramJobContext.isCancelled())
      return false;
    HeaderIterator localHeaderIterator = paramHttpResponse.headerIterator("Cache-Control");
    while ((localHeaderIterator != null) && (localHeaderIterator.hasNext()))
    {
      Header localHeader3 = localHeaderIterator.nextHeader();
      if ((localHeader3 == null) || (!"no-cache".equalsIgnoreCase(localHeader3.getValue())))
        continue;
      paramAsyncHttpResult.getContent().noCache = true;
    }
    if (paramJobContext.isCancelled())
      return false;
    if (!a(paramAsyncHttpResult, paramHttpResponse))
    {
      paramAsyncHttpResult.getStatus().a(new AsyncHttpResult.ContentTypeMismatchDescription(paramAsyncHttpResult.getContent().type));
      return false;
    }
    return true;
  }

  // ERROR //
  private boolean a(HttpResponse paramHttpResponse, AsyncHttpRequest paramAsyncHttpRequest, AsyncHttpResult paramAsyncHttpResult, ThreadPool.JobContext paramJobContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_3
    //   3: aload 4
    //   5: invokespecial 234	com/tencent/component/net/http/download/a:a	(Lorg/apache/http/HttpResponse;Lcom/tencent/component/net/http/AsyncHttpResult;Lcom/tencent/component/utils/thread/ThreadPool$JobContext;)Z
    //   8: ifne +5 -> 13
    //   11: iconst_0
    //   12: ireturn
    //   13: aload_3
    //   14: invokevirtual 223	com/tencent/component/net/http/AsyncHttpResult:getStatus	()Lcom/tencent/component/net/http/AsyncHttpResult$Status;
    //   17: getfield 237	com/tencent/component/net/http/AsyncHttpResult$Status:a	I
    //   20: istore 5
    //   22: aload_3
    //   23: invokevirtual 172	com/tencent/component/net/http/AsyncHttpResult:getContent	()Lcom/tencent/component/net/http/AsyncHttpResult$Content;
    //   26: getfield 178	com/tencent/component/net/http/AsyncHttpResult$Content:contentLength	J
    //   29: lstore 6
    //   31: aload_3
    //   32: checkcast 239	com/tencent/component/net/http/download/DownloadResult
    //   35: astore 8
    //   37: aload_0
    //   38: aload_2
    //   39: invokeinterface 83 1 0
    //   44: invokespecial 241	com/tencent/component/net/http/download/a:a	(Ljava/lang/String;)Ljava/lang/String;
    //   47: astore 9
    //   49: aload_0
    //   50: aload 9
    //   52: invokespecial 243	com/tencent/component/net/http/download/a:b	(Ljava/lang/String;)Ljava/lang/String;
    //   55: astore 10
    //   57: aload_0
    //   58: aload 9
    //   60: iconst_0
    //   61: invokespecial 244	com/tencent/component/net/http/download/a:a	(Ljava/lang/String;Z)Ljava/lang/String;
    //   64: astore 11
    //   66: aload_0
    //   67: aload 10
    //   69: lload 6
    //   71: invokespecial 246	com/tencent/component/net/http/download/a:a	(Ljava/lang/String;J)Z
    //   74: ifeq +102 -> 176
    //   77: aload 8
    //   79: aload 10
    //   81: invokevirtual 248	com/tencent/component/net/http/download/DownloadResult:a	(Ljava/lang/String;)V
    //   84: iload 5
    //   86: sipush 200
    //   89: if_icmpne +18 -> 107
    //   92: new 100	java/io/File
    //   95: dup
    //   96: aload 8
    //   98: invokevirtual 250	com/tencent/component/net/http/download/DownloadResult:b	()Ljava/lang/String;
    //   101: invokespecial 103	java/io/File:<init>	(Ljava/lang/String;)V
    //   104: invokestatic 255	com/tencent/component/utils/FileUtil:a	(Ljava/io/File;)V
    //   107: invokestatic 258	com/tencent/component/net/http/download/Downloader:b	()Lcom/tencent/component/cache/common/BytesBufferPool;
    //   110: invokevirtual 263	com/tencent/component/cache/common/BytesBufferPool:a	()Lcom/tencent/component/cache/common/BytesBufferPool$BytesBuffer;
    //   113: astore 12
    //   115: new 100	java/io/File
    //   118: dup
    //   119: aload 8
    //   121: invokevirtual 250	com/tencent/component/net/http/download/DownloadResult:b	()Ljava/lang/String;
    //   124: invokespecial 103	java/io/File:<init>	(Ljava/lang/String;)V
    //   127: astore 13
    //   129: aload 13
    //   131: iconst_0
    //   132: invokestatic 106	com/tencent/component/net/http/download/Downloader:a	(Ljava/io/File;Z)Z
    //   135: pop
    //   136: aload 4
    //   138: invokeinterface 197 1 0
    //   143: istore 18
    //   145: iload 18
    //   147: ifeq +76 -> 223
    //   150: iconst_0
    //   151: ifeq +7 -> 158
    //   154: aconst_null
    //   155: invokevirtual 268	java/io/InputStream:close	()V
    //   158: iconst_0
    //   159: ifeq +7 -> 166
    //   162: aconst_null
    //   163: invokevirtual 271	java/io/OutputStream:close	()V
    //   166: invokestatic 258	com/tencent/component/net/http/download/Downloader:b	()Lcom/tencent/component/cache/common/BytesBufferPool;
    //   169: aload 12
    //   171: invokevirtual 274	com/tencent/component/cache/common/BytesBufferPool:a	(Lcom/tencent/component/cache/common/BytesBufferPool$BytesBuffer;)V
    //   174: iconst_0
    //   175: ireturn
    //   176: aload 10
    //   178: aload 11
    //   180: invokestatic 278	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   183: ifne +24 -> 207
    //   186: aload_0
    //   187: aload 11
    //   189: lload 6
    //   191: invokespecial 246	com/tencent/component/net/http/download/a:a	(Ljava/lang/String;J)Z
    //   194: ifeq +13 -> 207
    //   197: aload 8
    //   199: aload 11
    //   201: invokevirtual 248	com/tencent/component/net/http/download/DownloadResult:a	(Ljava/lang/String;)V
    //   204: goto -120 -> 84
    //   207: aload_3
    //   208: invokevirtual 223	com/tencent/component/net/http/AsyncHttpResult:getStatus	()Lcom/tencent/component/net/http/AsyncHttpResult$Status;
    //   211: new 280	com/tencent/component/net/http/AsyncHttpResult$StorageFailDescription
    //   214: dup
    //   215: invokespecial 281	com/tencent/component/net/http/AsyncHttpResult$StorageFailDescription:<init>	()V
    //   218: invokevirtual 231	com/tencent/component/net/http/AsyncHttpResult$Status:a	(Lcom/tencent/component/net/http/AsyncHttpResult$FailDescription;)V
    //   221: iconst_0
    //   222: ireturn
    //   223: aload_1
    //   224: invokeinterface 139 1 0
    //   229: invokeinterface 284 1 0
    //   234: astore 19
    //   236: new 286	java/io/FileOutputStream
    //   239: dup
    //   240: aload 13
    //   242: iconst_0
    //   243: invokespecial 289	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   246: astore 15
    //   248: new 291	java/util/ArrayList
    //   251: dup
    //   252: invokespecial 292	java/util/ArrayList:<init>	()V
    //   255: astore 20
    //   257: lconst_0
    //   258: lstore 21
    //   260: aload 13
    //   262: invokevirtual 295	java/io/File:length	()J
    //   265: lstore 23
    //   267: aload 19
    //   269: aload 12
    //   271: getfield 300	com/tencent/component/cache/common/BytesBufferPool$BytesBuffer:a	[B
    //   274: iconst_0
    //   275: aload 12
    //   277: getfield 300	com/tencent/component/cache/common/BytesBufferPool$BytesBuffer:a	[B
    //   280: arraylength
    //   281: invokevirtual 304	java/io/InputStream:read	([BII)I
    //   284: istore 25
    //   286: iload 25
    //   288: ifle +118 -> 406
    //   291: aload 15
    //   293: aload 12
    //   295: getfield 300	com/tencent/component/cache/common/BytesBufferPool$BytesBuffer:a	[B
    //   298: iconst_0
    //   299: iload 25
    //   301: invokevirtual 308	java/io/OutputStream:write	([BII)V
    //   304: lload 21
    //   306: iload 25
    //   308: i2l
    //   309: ladd
    //   310: lstore 21
    //   312: aload_3
    //   313: invokevirtual 172	com/tencent/component/net/http/AsyncHttpResult:getContent	()Lcom/tencent/component/net/http/AsyncHttpResult$Content;
    //   316: lload 21
    //   318: putfield 311	com/tencent/component/net/http/AsyncHttpResult$Content:receivedLength	J
    //   321: lload 6
    //   323: lconst_0
    //   324: lcmp
    //   325: ifle -58 -> 267
    //   328: aload_0
    //   329: aload_0
    //   330: getfield 12	com/tencent/component/net/http/download/a:a	Lcom/tencent/component/net/http/download/Downloader;
    //   333: aload_2
    //   334: invokeinterface 314 1 0
    //   339: iconst_0
    //   340: aload 20
    //   342: invokestatic 317	com/tencent/component/net/http/download/Downloader:a	(Lcom/tencent/component/net/http/download/Downloader;Ljava/lang/String;ZLjava/util/Collection;)Ljava/util/Collection;
    //   345: lload 6
    //   347: lload 23
    //   349: ladd
    //   350: lload 21
    //   352: lload 23
    //   354: ladd
    //   355: l2f
    //   356: lload 6
    //   358: lload 23
    //   360: ladd
    //   361: l2f
    //   362: fdiv
    //   363: invokespecial 319	com/tencent/component/net/http/download/a:a	(Ljava/util/Collection;JF)V
    //   366: goto -99 -> 267
    //   369: astore 14
    //   371: aload 19
    //   373: astore 16
    //   375: aload 16
    //   377: ifnull +8 -> 385
    //   380: aload 16
    //   382: invokevirtual 268	java/io/InputStream:close	()V
    //   385: aload 15
    //   387: ifnull +8 -> 395
    //   390: aload 15
    //   392: invokevirtual 271	java/io/OutputStream:close	()V
    //   395: invokestatic 258	com/tencent/component/net/http/download/Downloader:b	()Lcom/tencent/component/cache/common/BytesBufferPool;
    //   398: aload 12
    //   400: invokevirtual 274	com/tencent/component/cache/common/BytesBufferPool:a	(Lcom/tencent/component/cache/common/BytesBufferPool$BytesBuffer;)V
    //   403: aload 14
    //   405: athrow
    //   406: lload 6
    //   408: lconst_0
    //   409: lcmp
    //   410: ifgt +26 -> 436
    //   413: aload_0
    //   414: aload_0
    //   415: getfield 12	com/tencent/component/net/http/download/a:a	Lcom/tencent/component/net/http/download/Downloader;
    //   418: aload_2
    //   419: invokeinterface 314 1 0
    //   424: iconst_0
    //   425: aload 20
    //   427: invokestatic 321	com/tencent/component/net/http/download/Downloader:b	(Lcom/tencent/component/net/http/download/Downloader;Ljava/lang/String;ZLjava/util/Collection;)Ljava/util/Collection;
    //   430: lload 21
    //   432: fconst_1
    //   433: invokespecial 319	com/tencent/component/net/http/download/a:a	(Ljava/util/Collection;JF)V
    //   436: aload_3
    //   437: invokevirtual 172	com/tencent/component/net/http/AsyncHttpResult:getContent	()Lcom/tencent/component/net/http/AsyncHttpResult$Content;
    //   440: lload 21
    //   442: putfield 311	com/tencent/component/net/http/AsyncHttpResult$Content:receivedLength	J
    //   445: aload 19
    //   447: ifnull +8 -> 455
    //   450: aload 19
    //   452: invokevirtual 268	java/io/InputStream:close	()V
    //   455: aload 15
    //   457: ifnull +8 -> 465
    //   460: aload 15
    //   462: invokevirtual 271	java/io/OutputStream:close	()V
    //   465: invokestatic 258	com/tencent/component/net/http/download/Downloader:b	()Lcom/tencent/component/cache/common/BytesBufferPool;
    //   468: aload 12
    //   470: invokevirtual 274	com/tencent/component/cache/common/BytesBufferPool:a	(Lcom/tencent/component/cache/common/BytesBufferPool$BytesBuffer;)V
    //   473: iconst_1
    //   474: ireturn
    //   475: astore 14
    //   477: aconst_null
    //   478: astore 15
    //   480: aconst_null
    //   481: astore 16
    //   483: goto -108 -> 375
    //   486: astore 14
    //   488: aload 19
    //   490: astore 16
    //   492: aconst_null
    //   493: astore 15
    //   495: goto -120 -> 375
    //
    // Exception table:
    //   from	to	target	type
    //   248	257	369	finally
    //   260	267	369	finally
    //   267	286	369	finally
    //   291	304	369	finally
    //   312	321	369	finally
    //   328	366	369	finally
    //   413	436	369	finally
    //   436	445	369	finally
    //   115	145	475	finally
    //   223	236	475	finally
    //   236	248	486	finally
  }

  private String b(String paramString)
  {
    return Downloader.a(this.a).a(paramString);
  }

  public AsyncHttpResult generateAsyncHttpResult(String paramString)
  {
    return new DownloadResult(paramString);
  }

  public boolean onResponseReceived(HttpResponse paramHttpResponse, AsyncHttpResult paramAsyncHttpResult, AsyncHttpRequest paramAsyncHttpRequest, ThreadPool.JobContext paramJobContext)
  {
    return a(paramHttpResponse, paramAsyncHttpRequest, paramAsyncHttpResult, paramJobContext);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.download.a
 * JD-Core Version:    0.6.0
 */