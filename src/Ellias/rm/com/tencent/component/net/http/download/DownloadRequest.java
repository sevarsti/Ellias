package com.tencent.component.net.http.download;

import android.text.TextUtils;
import android.webkit.URLUtil;
import com.tencent.component.net.http.request.AsyncHttpGetRequest;
import com.tencent.component.utils.AssertUtil;

public class DownloadRequest extends AsyncHttpGetRequest
{
  private final String a;
  private final String b;

  public DownloadRequest(String paramString1, String paramString2)
  {
    AssertUtil.a(a(paramString1, paramString2));
    this.a = paramString2;
    this.b = paramString1;
    b(paramString1);
  }

  public static String a(String paramString)
  {
    return paramString;
  }

  public static boolean a(String paramString1, String paramString2)
  {
    return (c(paramString1)) && (!TextUtils.isEmpty(paramString2));
  }

  private static boolean c(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (URLUtil.isNetworkUrl(paramString));
  }

  public String a()
  {
    return this.a;
  }

  public String getIdentifier()
  {
    return a(this.b);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.download.DownloadRequest
 * JD-Core Version:    0.6.0
 */