package com.tencent.component.net.download.multiplex.download;

import android.text.TextUtils;
import com.tencent.component.net.download.multiplex.download.extension.FileUtils;
import com.tencent.component.net.download.multiplex.http.Apn;
import com.tencent.component.net.download.multiplex.http.ContentType;
import com.tencent.component.net.download.multiplex.http.MttInputStream;
import com.tencent.component.net.download.multiplex.http.MttRequest;
import com.tencent.component.net.download.multiplex.http.MttResponse;
import com.tencent.component.net.download.multiplex.http.Requester;
import com.tencent.component.net.download.multiplex.http.RequesterFactory;
import com.tencent.component.net.download.multiplex.task.Task;
import com.tencent.component.utils.log.LogUtil;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class Downloader extends Task
{
  public static final int c = 1000;
  private static final String d = "Downloader";
  private static final int e = 5;
  protected long a;
  protected long b;
  private long f;
  private int g = -1;
  private boolean h = false;
  private DownloadTask i;
  private int j = 0;
  private int k = 0;
  private int l = 30000;
  private Thread m;
  private long n;
  private boolean o = true;
  private boolean p = false;
  private int q = -1;

  protected Downloader(DownloadTask paramDownloadTask, DownloadSections.DownloadSection paramDownloadSection, int paramInt)
  {
    q(true);
    this.i = paramDownloadTask;
    this.g = paramInt;
    this.a = paramDownloadSection.a;
    this.b = paramDownloadSection.a();
    this.f = paramDownloadSection.b;
    this.q = -1;
    this.ay = new MttRequest();
    this.ay.a(104);
    this.ay.a(paramDownloadTask.v());
    a(this.i);
  }

  public static Downloader a(DownloadTask paramDownloadTask, DownloadSections.DownloadSection paramDownloadSection, int paramInt)
  {
    return new Downloader(paramDownloadTask, paramDownloadSection, paramInt);
  }

  private void s()
  {
    this.j = 8;
    this.aD = 5;
    LogUtil.d("Downloader", "etag has changed!downloading from beginning again...");
    ax();
  }

  private void t()
  {
    this.aD = 6;
    ax();
  }

  public void R()
  {
    LogUtil.d("Downloader", "[Downloader] Cancel task.");
    if (!this.aE)
    {
      LogUtil.d("Downloader", "[Downloader] Cancel task implemented.");
      this.aE = true;
      if (!r());
    }
    else
    {
      return;
    }
    if ((this.aD == 1) || (this.aD == 2))
    {
      this.aD = 7;
      ax();
      return;
    }
    t();
  }

  public int a()
  {
    return this.j;
  }

  public void a(long paramLong)
  {
    this.a = paramLong;
  }

  protected void a(MttResponse paramMttResponse)
  {
  }

  public void a(boolean paramBoolean)
  {
    this.p = paramBoolean;
  }

  public int b()
  {
    return this.g;
  }

  public void b(long paramLong)
  {
    this.b = paramLong;
  }

  public void b(boolean paramBoolean)
  {
    this.o = paramBoolean;
  }

  public int c()
  {
    return this.q;
  }

  public void c(long paramLong)
  {
    this.f = paramLong;
  }

  public void c(boolean paramBoolean)
  {
    this.h = paramBoolean;
  }

  public long d()
  {
    return this.a;
  }

  public long e()
  {
    return this.b;
  }

  public long f()
  {
    return this.f;
  }

  public long g()
  {
    return this.b - this.f;
  }

  public int h()
  {
    return this.k;
  }

  public void i()
  {
    this.m = new j(this);
    this.m.setName("downloader:" + this.g);
    this.m.start();
  }

  public void j()
  {
    if ((this.i != null) && (this.m != null))
    {
      if (this.i.U())
        this.m.setPriority(5);
    }
    else
      return;
    this.m.setPriority(1);
  }

  protected void k()
  {
    if (this.i.U())
      Thread.currentThread().setPriority(5);
    while (true)
    {
      this.h = false;
      this.j = 0;
      this.aE = false;
      return;
      Thread.currentThread().setPriority(1);
    }
  }

  protected long l()
  {
    return -1L;
  }

  protected void m()
  {
    LogUtil.w("Benson", "[Downloader] Start to connnect [id:" + this.g + "] start pos:" + this.a + " end pos:" + this.b + " current pos:" + this.f);
    this.q = -1;
    if ((this.b > 0L) && (this.f > this.b))
    {
      this.h = true;
      this.aD = 3;
      ax();
      return;
    }
    k();
    this.aD = 1;
    ax();
    String str1 = this.i.J();
    if (!TextUtils.isEmpty(str1))
      this.ay.d(str1);
    if (!this.i.F())
      this.f = 0L;
    this.k = 0;
    MttResponse localMttResponse;
    int i2;
    int i4;
    int i5;
    label922: int i6;
    label998: int i8;
    int i9;
    label1192: int i7;
    while (true)
    {
      this.aA = RequesterFactory.a();
      this.aA.b(this.i.j());
      this.aA.a(this.l);
      try
      {
        LogUtil.d("Downloader", "[Downloader] Start downloading from  [" + this.f + "] to  [" + this.b + "]");
        if (!o())
        {
          if (this.b == -1L)
            this.ay.a("Range", "bytes=" + this.f + "-");
          while (true)
          {
            localMttResponse = this.aA.a(this.ay);
            b(localMttResponse);
            i2 = localMttResponse.c().intValue();
            this.q = i2;
            if (!this.aE)
              break;
            t();
            return;
            this.ay.a("Range", "bytes=" + this.f + "-" + this.b);
          }
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          localException.printStackTrace();
          if (!this.aE)
            break;
          t();
          return;
          this.ay.c("Range");
          this.f = 0L;
          LogUtil.d("Downloader", "[Downloader] start no range request");
        }
      }
      finally
      {
        LogUtil.w("Downloader", "[Downloader] close connect [id:" + this.g + "]");
        aw();
      }
      if ((i2 != 200) && (i2 != 206))
        break label1704;
      LogUtil.d("Downloader", "[Downloader] Connect Success !");
      LogUtil.d("Downloader", "content-type: " + localMttResponse.f().a() + "/" + localMttResponse.f().b());
      LogUtil.d("Downloader", "content-disposition: " + localMttResponse.r());
      a(localMttResponse);
      if (n())
      {
        LogUtil.d("Downloader", "[Downloader] first detect complete.");
        boolean bool2 = this.i.a(this, localMttResponse);
        if (!bool2)
        {
          LogUtil.w("Downloader", "[Downloader] close connect [id:" + this.g + "]");
          aw();
          return;
        }
      }
      else
      {
        String str2 = this.i.C();
        String str3 = localMttResponse.h();
        LogUtil.d("Downloader", "[Downloader] original etag:" + str2);
        LogUtil.d("Downloader", "[Downloader] new etag:" + str3);
        LogUtil.d("Downloader", "[Downloader] downloader id:" + this.g);
        if ((this.o) && (this.g == 0) && (!TextUtils.isEmpty(str2)) && (!TextUtils.isEmpty(str3)) && (!str2.equals(str3)))
        {
          s();
          LogUtil.w("Downloader", "[Downloader] close connect [id:" + this.g + "]");
          aw();
          return;
        }
      }
      try
      {
        MttInputStream localMttInputStream = localMttResponse.b();
        byte[] arrayOfByte;
        if (localMttInputStream != null)
        {
          arrayOfByte = new byte[8192];
          i4 = 0;
          i5 = 8192;
          if (!this.aE)
          {
            i6 = localMttInputStream.a(arrayOfByte, 0, i5);
            if (i6 > 0)
            {
              boolean bool1 = this.aE;
              if (bool1);
            }
          }
        }
        try
        {
          this.i.a(this.g, this.f, arrayOfByte, i6);
          if (this.b <= 0L)
            break label1298;
          if (this.f > this.b)
          {
            localMttInputStream.c();
            if (!this.aE)
              break;
            t();
            LogUtil.w("Downloader", "[Downloader] close connect [id:" + this.g + "]");
            aw();
            return;
          }
        }
        catch (IOException localIOException2)
        {
          this.j = 5;
          if (FileUtils.p() < 2024L)
            this.j = 2;
          this.aD = 5;
          LogUtil.e("Downloader", "[Downloader] Error status code:" + i2, localIOException2);
          ax();
          LogUtil.w("Downloader", "[Downloader] close connect [id:" + this.g + "]");
          aw();
          return;
        }
        if (this.f + i6 <= 1L + this.b)
          break label2344;
        i8 = 1;
        i9 = (int)(i6 - (this.f + i6 - this.b - 1L));
        this.f += i9;
        long l2 = this.b - this.f;
        if ((l2 <= 0L) || (l2 >= 8192L))
          break label2337;
        i7 = (int)l2;
        while (true)
        {
          label1236: this.i.a(this.g, this.f, i9);
          long l1 = System.currentTimeMillis();
          if ((this.aE) || (l1 - this.n <= 1000L))
            break;
          this.n = l1;
          this.aD = 2;
          ax();
          break;
          label1298: this.f += i6;
          i7 = i5;
          i8 = i4;
          i9 = i6;
        }
      }
      catch (IOException localIOException1)
      {
        localIOException1.printStackTrace();
        if ((((localIOException1 instanceof SocketTimeoutException)) || ((localIOException1 instanceof SocketException))) && (Apn.f()))
        {
          int i3 = this.k;
          if (i3 < 5)
          {
            LogUtil.w("Downloader", "[Downloader] close connect [id:" + this.g + "]");
            aw();
            continue;
          }
        }
        this.j = 3;
        if (FileUtils.p() < 2024L)
          this.j = 2;
        if (((localIOException1 instanceof IOException)) && (this.j == 3) && (Apn.f()) && (this.i.u() < 10))
          this.j = 7;
        this.aD = 5;
        LogUtil.e("Downloader", "[Downloader] Error status code:" + i2, localIOException1);
        ax();
        LogUtil.w("Downloader", "[Downloader] close connect [id:" + this.g + "]");
        aw();
        return;
      }
    }
    if (!this.i.N())
    {
      LogUtil.d("Downloader", "[Downloader] File not exist after downloading.");
      this.f = this.a;
      this.j = 4;
      this.aD = 5;
      ax();
      LogUtil.w("Downloader", "[Downloader] close connect [id:" + this.g + "]");
      aw();
      return;
    }
    this.h = true;
    LogUtil.i("Benson", "[Downloader] download complete, [id:" + this.g + "]");
    this.aD = 3;
    ax();
    while (true)
    {
      LogUtil.w("Downloader", "[Downloader] close connect [id:" + this.g + "]");
      aw();
      return;
      label1704: if ((i2 >= 300) && (i2 <= 307))
      {
        String str4 = localMttResponse.e();
        LogUtil.d("Downloader", "[Downloader] Download Task request old url:" + this.ay.a());
        this.ay.a(str4);
        LogUtil.d("Downloader", "[Downloader] Download Task 302,location:" + str4);
        LogUtil.d("Downloader", "[Downloader] Download Task request new url:" + this.ay.a());
        LogUtil.w("Downloader", "[Downloader] close connect [id:" + this.g + "]");
        aw();
        break;
      }
      if ((i2 != 416) || (this.b != this.f) || (this.b <= 0L))
        break label2371;
      this.aD = 3;
      label1898: ax();
    }
    while (true)
    {
      label1905: if (this.b == -1L)
      {
        this.j = 10;
        this.aD = 5;
        break label1898;
      }
      label2190: label2337: label2344: label2371: 
      do
      {
        LogUtil.d("Downloader", "[Downloader] download fail status code:" + i2);
        this.j = 6;
        this.aD = 5;
        break label1898;
        LogUtil.e("Downloader", "[Downloader] Download task " + this.k + " failed - ", localException);
        int i1 = this.k;
        if (i1 < 5)
        {
          try
          {
            Thread.sleep(5000L);
            if (this.aE)
            {
              t();
              LogUtil.w("Downloader", "[Downloader] close connect [id:" + this.g + "]");
              aw();
              return;
            }
          }
          catch (InterruptedException localInterruptedException)
          {
            while (true)
              LogUtil.e("Downloader", "[Downloader] Interrupted while sleeping to retry - ", localInterruptedException);
            this.k = (1 + this.k);
            if (Apn.f())
            {
              if ((this.i == null) || (!this.i.T()) || (this.k != 1) || (!(localException instanceof SocketTimeoutException)))
                break label2190;
              LogUtil.d("Downloader", "[Downloader] reset read timeout to 100000");
              this.l = 100000;
            }
          }
          while (true)
          {
            LogUtil.w("Downloader", "[Downloader] close connect [id:" + this.g + "]");
            aw();
            break;
            this.l = 30000;
          }
        }
        LogUtil.w("Downloader", "[Downloader] request file length error, change the request without range!");
        if ((this.b == -1L) && (Apn.f()) && (this.i != null) && (!this.i.T()) && (!this.p))
        {
          this.j = 10;
          this.p = true;
        }
        while (true)
        {
          this.aD = 5;
          ax();
          LogUtil.w("Downloader", "[Downloader] close connect [id:" + this.g + "]");
          aw();
          return;
          if (((localException instanceof IOException)) && (Apn.f()))
          {
            this.j = 7;
            continue;
          }
          this.j = 3;
        }
        i7 = i5;
        break label1236;
        i8 = i4;
        i9 = i6;
        break label1192;
        if (i8 != 0)
          break label998;
        i5 = i7;
        i4 = i8;
        break label922;
        if (i2 == 416)
          break label1905;
      }
      while (i2 != 406);
    }
  }

  protected boolean n()
  {
    return this.b == -1L;
  }

  protected boolean o()
  {
    return this.i.T();
  }

  public boolean p()
  {
    return this.aE;
  }

  public boolean q()
  {
    return this.h;
  }

  public boolean r()
  {
    return false;
  }

  public void run()
  {
    monitorenter;
    try
    {
      long l1 = l();
      if (l1 >= 0L)
      {
        this.i.a(this.g, this.f, l1);
        this.h = true;
        this.aD = 3;
        ax();
      }
      while (true)
      {
        return;
        m();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public String v()
  {
    return this.ay.a();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.Downloader
 * JD-Core Version:    0.6.0
 */