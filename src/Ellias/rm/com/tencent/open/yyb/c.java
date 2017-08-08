package com.tencent.open.yyb;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
import com.tencent.a.a.d;
import com.tencent.connect.auth.QQToken;

class c
  implements DownloadListener
{
  c(AppbarActivity paramAppbarActivity)
  {
  }

  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    d.b("openSDK_LOG", "-->(AppbarActivity)onDownloadStart : url = " + paramString1);
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString1));
    try
    {
      this.a.startActivity(localIntent);
      QQToken localQQToken = AppbarActivity.access$500(this.a);
      if (localQQToken != null)
        b.a(localQQToken.getAppId(), "200", "SDK.APPBAR.HOME ACTION");
      return;
    }
    catch (Exception localException)
    {
      while (true)
        d.b("openSDK_LOG", "-->(AppbarActivity)onDownloadStart : activity aciton_view not found.");
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.yyb.c
 * JD-Core Version:    0.6.0
 */