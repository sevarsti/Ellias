package com.tencent.smtt.sdk;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

class DownLoadListenerAdapter
  implements com.tencent.smtt.export.external.interfaces.DownloadListener
{
  private DownloadListener mListener;
  private WebView mWebView;

  public DownLoadListenerAdapter(WebView paramWebView, DownloadListener paramDownloadListener, boolean paramBoolean)
  {
    this.mListener = paramDownloadListener;
    this.mWebView = paramWebView;
  }

  public void onDownloadStart(String paramString1, String paramString2, byte[] paramArrayOfByte, String paramString3, String paramString4, String paramString5, long paramLong, String paramString6, String paramString7)
  {
    if (this.mListener == null)
    {
      if (QbSdk.canOpenMimeFileType(this.mWebView.getContext(), paramString5))
      {
        Intent localIntent1 = new Intent("com.tencent.QQBrowser.action.sdk.document");
        localIntent1.setFlags(268435456);
        localIntent1.putExtra("key_reader_sdk_url", paramString1);
        localIntent1.putExtra("key_reader_sdk_type", 1);
        localIntent1.setData(Uri.parse(paramString1));
        this.mWebView.getContext().startActivity(localIntent1);
        return;
      }
      Intent localIntent2 = new Intent("com.tencent.QQBrowser.action.SHOWDOWNLOAD", Uri.parse(paramString1));
      localIntent2.addCategory("android.intent.category.DEFAULT");
      localIntent2.putExtra("method", paramString2);
      localIntent2.putExtra("postData", paramArrayOfByte);
      localIntent2.putExtra("userAgent", paramString3);
      localIntent2.putExtra("contentDisposition", paramString4);
      localIntent2.putExtra("mimetype", paramString5);
      localIntent2.putExtra("contentLength", paramLong);
      localIntent2.putExtra("referer", paramString6);
      localIntent2.putExtra("urlBeforeRedirect", paramString7);
      try
      {
        this.mWebView.getContext().startActivity(localIntent2);
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        localActivityNotFoundException.printStackTrace();
        return;
      }
    }
    this.mListener.onDownloadStart(paramString1, paramString3, paramString4, paramString5, paramLong);
  }

  public void onDownloadVideo(String paramString, long paramLong, int paramInt)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.DownLoadListenerAdapter
 * JD-Core Version:    0.6.0
 */