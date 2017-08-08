package com.tencent.component.net.http.download;

import com.tencent.component.net.http.AsyncRequestListener;

public abstract interface DownloadListener extends AsyncRequestListener
{
  public abstract void a(String paramString, long paramLong, float paramFloat);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.download.DownloadListener
 * JD-Core Version:    0.6.0
 */