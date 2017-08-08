package com.tencent.component.net.download.multiplex.download;

import com.tencent.component.net.download.multiplex.download.extension.FileUtils;
import java.io.File;

public class DownloadInfo
{
  public static final int a = 1;
  public static final int b = 2;
  public static final int c = 3;
  public DownloadInfo.DownloadTaskConfirmObserver A;
  public byte B;
  public boolean C;
  public boolean d = false;
  public byte e;
  public int f;
  public String g;
  public String h;
  public long i;
  public String j;
  public String k;
  public boolean l;
  public int m;
  public boolean n;
  public boolean o;
  public boolean p;
  public boolean q;
  public long r;
  public int s;
  public String t = "";
  public String u = "";
  public DownloadManager.OnDownloadFeedbackListener v;
  public String w;
  public boolean x;
  public long y;
  public int z;

  public DownloadInfo()
  {
    this.g = null;
    this.h = null;
    this.i = 0L;
    this.j = null;
    this.k = null;
    this.l = false;
    this.n = true;
    this.o = true;
    this.p = false;
    this.q = true;
    this.x = false;
    this.y = 0L;
    this.z = 99;
    this.B = 0;
    this.C = false;
  }

  public DownloadInfo(String paramString1, String paramString2, long paramLong, String paramString3, String paramString4)
  {
    this.g = paramString1;
    this.h = paramString2;
    this.i = paramLong;
    this.j = paramString3;
    if (paramString4 == null);
    for (this.k = FileUtils.k().getAbsolutePath(); ; this.k = paramString4)
    {
      this.z = 99;
      this.B = 0;
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.DownloadInfo
 * JD-Core Version:    0.6.0
 */