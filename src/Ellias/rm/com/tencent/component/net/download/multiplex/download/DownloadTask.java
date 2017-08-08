package com.tencent.component.net.download.multiplex.download;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import com.tencent.component.net.download.multiplex.download.extension.FileUtils;
import com.tencent.component.net.download.multiplex.http.Apn;
import com.tencent.component.net.download.multiplex.http.ContentType;
import com.tencent.component.net.download.multiplex.http.MttRequest;
import com.tencent.component.net.download.multiplex.http.MttResponse;
import com.tencent.component.net.download.multiplex.task.Task;
import com.tencent.component.net.download.multiplex.task.TaskObserver;
import com.tencent.component.utils.log.LogUtil;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadTask extends Task
  implements TaskObserver
{
  public static final int A = 9;
  public static final int B = 10;
  public static final int C = 11;
  public static final int D = 12;
  public static final int E = 101;
  public static final String a = ".qbdltmp";
  private static final String aI = "DownloadTask";
  private static final Pattern aJ = Pattern.compile("[^\\d]*(\\d+)\\-(\\d+)\\/(\\d+|\\*)");
  private static long aK = 0L;
  public static final int b = -1;
  public static final long c = -1L;
  public static final int d = 0;
  public static final int e = 1;
  public static final int f = 2;
  public static final int g = 4;
  public static final int h = 8;
  public static final int i = 16;
  public static final int j = 32;
  public static final int k = 64;
  public static final int l = 128;
  public static final int m = 256;
  public static final int n = 512;
  public static final int o = 1024;
  public static final int p = 2048;
  public static final int q = 4096;
  public static final int r = 0;
  public static final int s = 1;
  public static final int t = 2;
  public static final int u = 3;
  public static final int v = 4;
  public static final int w = 5;
  public static final int x = 6;
  public static final int y = 7;
  public static final int z = 8;
  protected int F;
  protected String G = "";
  protected String H;
  protected long I;
  protected long J;
  protected String K;
  protected boolean L;
  protected int M = 0;
  protected List N = new ArrayList();
  protected int O = 0;
  protected RandomAccessFile P;
  protected DownloadSections Q = new DownloadSections();
  protected long R;
  protected long S;
  private final Object aL = new Object();
  private boolean aM = false;
  private String aN;
  private Bitmap aO;
  private Queue aP;
  private long aQ;
  private long aR;
  private String aS;
  private int aT;
  private boolean aU = false;
  private boolean aV = false;
  private boolean aW = false;
  private boolean aX = false;
  private boolean aY = false;
  private boolean aZ = true;
  private boolean ba = false;
  private boolean bb = false;
  private String bc;
  private String bd = "";
  private String be = "";
  private boolean bf = true;
  private String bg;
  private boolean bh = false;
  private long bi = 0L;
  private int bj = Apn.a();
  private final Object bk = new Object();
  private final Object bl = new Object();
  private int bm = 0;
  private boolean bn = false;
  private boolean bo = false;

  public DownloadTask(int paramInt1, byte paramByte, String paramString1, String paramString2, String paramString3, long paramLong1, long paramLong2, boolean paramBoolean1, String paramString4, int paramInt2, boolean paramBoolean2, long paramLong3, String paramString5)
  {
    this.aB = 3;
    boolean bool1;
    boolean bool2;
    label208: boolean bool3;
    label241: boolean bool4;
    if ((paramInt2 & 0x40) == 64)
    {
      bool1 = true;
      this.bf = bool1;
      if ((paramInt2 & 0x80) != 128)
        break label548;
      bool2 = true;
      this.bh = bool2;
      this.F = paramInt1;
      this.aD = paramByte;
      this.bg = paramString5;
      if ((paramInt2 & 0x20) <= 0)
        break label554;
      bool3 = true;
      this.bb = bool3;
      if ((paramInt2 & 0x1000) <= 0)
        break label560;
      bool4 = true;
      label259: this.bo = bool4;
      this.ay = new MttRequest();
      this.ay.a(104);
      this.K = paramString1;
      this.ay.a(this.K);
      if (!TextUtils.isEmpty(paramString3))
        break label566;
      this.H = FileUtils.a(paramString2);
      label318: this.G = paramString2;
      if ((al()) && (paramString2 != null) && (!paramString2.endsWith(".m3u8")))
        this.G = (paramString2 + ".m3u8");
      this.I = paramLong2;
      this.J = paramLong1;
      LogUtil.d("DownloadTask", "[DownloadTask]DownloadTask construct, mFileSize=" + this.I + ",mDownloadedSize=" + this.J);
      this.aR = paramLong3;
      this.L = paramBoolean1;
      if (paramBoolean2)
        f(2);
      this.aP = new LinkedList();
      this.aS = paramString4;
      d(paramInt2);
      if ((0x4 & this.aT) == 0)
        break label580;
    }
    label548: label554: label560: label566: label580: for (boolean bool5 = true; ; bool5 = false)
    {
      this.aU = bool5;
      this.aO = FileUtils.a(this.G, this.H);
      if ((0x800 & K()) == 2048)
        o();
      if ((this.aD != 3) && ((0x10 & K()) == 16));
      return;
      bool1 = false;
      break;
      bool2 = false;
      break label208;
      bool3 = false;
      break label241;
      bool4 = false;
      break label259;
      this.H = paramString3;
      this.aM = true;
      break label318;
    }
  }

  public DownloadTask(int paramInt1, String paramString1, String paramString2, String paramString3, long paramLong1, long paramLong2, boolean paramBoolean1, String paramString4, int paramInt2, boolean paramBoolean2)
  {
    this(paramInt1, 0, paramString1, paramString2, paramString3, paramLong1, paramLong2, paramBoolean1, paramString4, paramInt2, paramBoolean2, 0L, null);
  }

  public DownloadTask(String paramString)
  {
    this(paramString, null, -1L, null);
  }

  public DownloadTask(String paramString1, String paramString2)
  {
    this(-1, 0, paramString1, null, paramString2, 0L, -1L, true, null, 0, false, 0L, null);
  }

  public DownloadTask(String paramString1, String paramString2, long paramLong, String paramString3)
  {
    this(-1, paramString1, paramString2, null, 0L, paramLong, true, paramString3, 0, false);
  }

  public DownloadTask(String paramString1, String paramString2, String paramString3)
  {
    this(-1, paramString1, paramString2, paramString3, 0L, 0L, true, null, 0, false);
  }

  public DownloadTask(String paramString1, String paramString2, String paramString3, long paramLong, String paramString4, boolean paramBoolean)
  {
    this(-1, paramString1, paramString2, paramString3, 0L, paramLong, true, paramString4, 0, false);
    this.aU = paramBoolean;
    if (this.aU)
      this.aT = (0x4 | this.aT);
  }

  private void a(PackageInfo paramPackageInfo, Context paramContext)
  {
    if (paramPackageInfo == null)
      return;
    this.bg = paramPackageInfo.packageName;
    int i1 = paramPackageInfo.versionCode;
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      PackageInfo localPackageInfo2 = localPackageManager.getPackageInfo(paramPackageInfo.packageName, 0);
      bool1 = true;
      localPackageInfo1 = localPackageInfo2;
      if ((bool1) && (i1 > 0))
      {
        int i2 = localPackageInfo1.versionCode;
        bool2 = false;
        if (i1 != i2)
        {
          if (bool2);
          a(bool2);
          return;
        }
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
      {
        bool1 = false;
        localPackageInfo1 = null;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        boolean bool1 = false;
        PackageInfo localPackageInfo1 = null;
        continue;
        boolean bool2 = bool1;
      }
    }
  }

  private void a(Downloader paramDownloader1, Downloader paramDownloader2)
  {
    if ((paramDownloader1 == null) || (paramDownloader2 == null))
      return;
    LogUtil.i("DownloadTask", "[DownloadTask], lauch downloader[" + paramDownloader2.b() + "] to help download[" + paramDownloader1.b() + "] handle not finish part!");
    if ((paramDownloader1.an() == 5) && (paramDownloader2.an() != 5))
    {
      paramDownloader1.c(false);
      paramDownloader1.i();
      return;
    }
    LogUtil.d("DownloadTask", "[DownloadTask]Downloader " + paramDownloader2.b() + " had ended;");
    LogUtil.d("DownloadTask", "[DownloadTask]get new task from downloader " + paramDownloader1.b());
    long l1 = paramDownloader1.f();
    long l2 = paramDownloader1.e();
    long l3 = (l1 + l2) / 2L;
    paramDownloader1.b(l3);
    DownloadSections.DownloadSection localDownloadSection1;
    synchronized (this.bk)
    {
      localDownloadSection1 = this.Q.a(paramDownloader1.b());
      if (localDownloadSection1 == null)
        return;
    }
    localDownloadSection1.a(l3);
    paramDownloader2.a(l3 + 1L);
    paramDownloader2.c(l3 + 1L);
    paramDownloader2.b(l2);
    DownloadSections.DownloadSection localDownloadSection2 = this.Q.a(paramDownloader2.b());
    localDownloadSection2.a = (l3 + 1L);
    localDownloadSection2.b = (l3 + 1L);
    localDownloadSection2.a(l2);
    monitorexit;
    paramDownloader2.c(false);
    paramDownloader2.b(false);
    paramDownloader2.i();
  }

  private void aC()
  {
    if (this.N != null)
    {
      int i1 = this.N.size();
      for (int i2 = 0; i2 < i1; i2++)
      {
        Downloader localDownloader = (Downloader)this.N.get(i2);
        if (localDownloader == null)
          continue;
        localDownloader.j();
      }
    }
  }

  private void aD()
  {
    LogUtil.d("Benson", "[DownloadTask]resume unfinished task");
    o(false);
    try
    {
      if (!ae())
        return;
      if (this.aE)
      {
        S();
        return;
      }
    }
    catch (IOException localIOException)
    {
      this.M = 5;
      if (FileUtils.p() < 2024L)
        this.M = 2;
      this.aD = 5;
      ax();
      return;
    }
    aJ();
  }

  private void aE()
  {
  }

  private void aF()
  {
  }

  private void aG()
  {
    if ((0x100 & this.aT) == 0)
      return;
    this.aT = (0xFFFFFEFF & this.aT);
    this.aT = (0x400 | this.aT);
    this.aD = 5;
    this.M = 12;
    ax();
  }

  private void aH()
  {
    synchronized (this.bk)
    {
      this.Q.a(this.H, this.G, this.O);
      if (this.Q.c() >= 0L)
        this.J = this.Q.c();
      return;
    }
  }

  private boolean aI()
  {
    boolean bool = this.Q.a(this.H, this.G, this.I, this.O);
    if (bool)
      this.J = this.Q.c();
    return bool;
  }

  private void aJ()
  {
    this.N.clear();
    if (!this.L)
    {
      n(false);
      return;
    }
    if (af())
      ad();
    while (true)
    {
      this.R = this.J;
      this.S = System.currentTimeMillis();
      return;
      aH();
      if (af())
      {
        ad();
        continue;
      }
      n(false);
    }
  }

  private float aK()
  {
    long l1 = this.J - this.R;
    long l2 = System.currentTimeMillis() - this.S;
    return 1000.0F * (float)l1 / (float)l2;
  }

  private long aL()
  {
    long l1 = ()aK();
    if (l1 == 0L)
      l1 = aK;
    return l1;
  }

  private void aM()
  {
    this.J = 0L;
    this.N.clear();
    this.Q.a(true);
  }

  private void aN()
  {
    LogUtil.d("DownloadTask", "[DownloadTask]handleResumeWithoutRange");
    I();
    this.ba = true;
    this.O = 1;
    c(true);
    this.aD = 2;
    W();
  }

  private void c(Downloader paramDownloader)
  {
    LogUtil.d("DownloadTask", "[DownloadTask]handleResumeWithOneDownloader");
    if (this.N.size() == this.O)
      for (int i1 = 0; i1 < this.O; i1++)
      {
        Downloader localDownloader = (Downloader)this.N.get(i1);
        if ((localDownloader == null) || (localDownloader.q()) || (localDownloader == paramDownloader))
          continue;
        localDownloader.R();
      }
    I();
    this.ba = true;
    this.aD = 2;
    aD();
  }

  private void d(Downloader paramDownloader)
  {
    LogUtil.d("DownloadTask", "[DownloadTask]handleTaskFailed");
    this.aD = 5;
    I();
    this.M = paramDownloader.a();
    if (this.M == 4)
      aM();
    if (this.N.size() == this.O)
      for (int i1 = 0; i1 < this.O; i1++)
      {
        Downloader localDownloader = (Downloader)this.N.get(i1);
        if ((localDownloader == null) || (localDownloader.q()) || (localDownloader == paramDownloader))
          continue;
        localDownloader.R();
      }
    ai();
    ax();
  }

  public static boolean e(int paramInt)
  {
    return (paramInt & 0x200) != 0;
  }

  private void f(Task paramTask)
  {
  }

  public float A()
  {
    while (true)
    {
      DownloadSpeedData localDownloadSpeedData;
      long l1;
      long l2;
      synchronized (this.aP)
      {
        localDownloadSpeedData = (DownloadSpeedData)this.aP.peek();
        l1 = System.currentTimeMillis();
        l2 = this.J;
        if (localDownloadSpeedData == null)
          break label134;
        if (localDownloadSpeedData.b == l1)
        {
          f1 = 0.0F;
          if (f1 != 0.0F)
            break label123;
          this.bm = (1 + this.bm);
          float f2 = aK();
          if ((this.bm >= 3) || (f1 >= f2))
            break label131;
          return f2;
        }
      }
      float f1 = 1000.0F * (float)(l2 - localDownloadSpeedData.a) / (float)(l1 - localDownloadSpeedData.b);
      continue;
      label123: this.bm = 0;
      continue;
      label131: return f1;
      label134: f1 = 0.0F;
    }
  }

  public void B()
  {
    synchronized (this.aP)
    {
      if (this.aP.size() > 3)
        this.aP.poll();
      this.aP.offer(new DownloadSpeedData(this.J, System.currentTimeMillis()));
      return;
    }
  }

  public String C()
  {
    return this.bc;
  }

  public int D()
  {
    return this.O;
  }

  public int E()
  {
    return this.F;
  }

  public boolean F()
  {
    return this.L;
  }

  public long G()
  {
    return this.aQ;
  }

  public long H()
  {
    return this.aR;
  }

  public void I()
  {
    this.aR += System.currentTimeMillis() - this.S;
  }

  public String J()
  {
    return this.aS;
  }

  public int K()
  {
    return this.aT;
  }

  public boolean L()
  {
    return this.aV;
  }

  public boolean M()
  {
    return this.aW;
  }

  public boolean N()
  {
    boolean bool;
    if ((TextUtils.isEmpty(this.G)) || (TextUtils.isEmpty(this.H)))
      bool = false;
    do
    {
      return bool;
      bool = new File(this.H, this.G).exists();
    }
    while (bool);
    return new File(this.H, this.G + ".qbdltmp").exists();
  }

  public String O()
  {
    return "";
  }

  public void P()
  {
    this.aE = false;
    synchronized (this.aP)
    {
      this.aP.clear();
      this.M = 0;
      this.aD = 0;
      ax();
      return;
    }
  }

  protected void Q()
  {
    aM();
    this.M = 4;
    this.aD = 5;
    ax();
  }

  public void R()
  {
    LogUtil.d("Benson", "[DownloadTask] Cancel task. task id : " + this.F);
    this.aW = true;
    I();
    if ((this.J > 0L) && (!N()))
    {
      LogUtil.d("DownloadTask", "[DownloadTask] File not exist after downloading.");
      int i3 = this.N.size();
      int i4 = this.O;
      int i5 = 0;
      while (true)
        if ((i3 == i4) && (i5 < this.O))
          try
          {
            Downloader localDownloader2 = (Downloader)this.N.get(i5);
            if (localDownloader2 != null)
              localDownloader2.R();
            i5++;
          }
          catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
          {
            while (true)
              localIndexOutOfBoundsException.printStackTrace();
          }
      Q();
    }
    do
      return;
    while (this.aE);
    LogUtil.d("DownloadTask", "[DownloadTask] Cancel task implemented.");
    this.aE = true;
    if ((this.aD == 1) || (this.aD == 2))
    {
      this.aD = 7;
      if (this.N.size() == this.O)
      {
        int i2 = 0;
        i1 = 1;
        while (i2 < this.O)
        {
          Downloader localDownloader1 = (Downloader)this.N.get(i2);
          if ((localDownloader1 != null) && (!localDownloader1.q()))
          {
            localDownloader1.R();
            if (!localDownloader1.q())
              i1 = 0;
          }
          i2++;
        }
      }
      int i1 = 1;
      if (i1 != 0)
        this.aD = 6;
      ax();
      return;
    }
    LogUtil.d("DownloadTask", "[DownloadTask]Cancel taskCanceled");
    S();
  }

  protected void S()
  {
    this.aD = 6;
    ax();
  }

  public boolean T()
  {
    return this.bh;
  }

  public boolean U()
  {
    return this.aY;
  }

  protected boolean V()
  {
    return (this.I > 0L) && (this.J > 0L) && (!TextUtils.isEmpty(this.G)) && (FileUtils.e(this.G));
  }

  protected void W()
  {
    Downloader localDownloader = Downloader.a(this, this.Q.b(0), 0);
    this.N.add(0, localDownloader);
    if (this.aE)
    {
      LogUtil.d("Benson", "[DownloadTask] [Task id:" + this.F + "] mCanceled==true, cancel it first");
      S();
      return;
    }
    localDownloader.c(false);
    LogUtil.d("Benson", "[DownloadTask] [Task id:" + this.F + "]: 开始启动 Downloader : " + localDownloader.b());
    localDownloader.i();
  }

  protected boolean X()
  {
    File localFile = new File(this.H, this.G + ".qbdltmp");
    if ((localFile != null) && (localFile.exists()))
      return localFile.renameTo(new File(this.H, this.G));
    return true;
  }

  public void Y()
  {
    LogUtil.d("DownloadTask", "[DownloadTask]finishDownloadTask");
    I();
    if ((this.I > 0L) && (this.J < this.I))
    {
      LogUtil.d("DownloadTask", "[DownloadTask]finishDownloadTask ERRORCODE_UNKNOWN, mDownloadedSize=" + this.J + ", mFileSize=" + this.I);
      this.M = 101;
      this.aD = 5;
    }
    do
    {
      synchronized (this.bk)
      {
        this.J = 0L;
        this.Q.a(true);
        ax();
        return;
      }
      ai();
      if (!X())
      {
        LogUtil.d("DownloadTask", "[DownloadTask]finishDownloadTask ERROR_RENAME_FAILED");
        this.M = 9;
        this.aD = 5;
        ax();
        return;
      }
      if (!N())
      {
        LogUtil.d("DownloadTask", "[DownloadTask]File not exist after downloading.");
        Q();
        return;
      }
      ac();
      if (this.I <= 0L)
        this.I = this.J;
      LogUtil.d("DownloadTask", "[DownloadTask] file size:" + this.I + ",downloadedSize:" + this.J);
      LogUtil.d("DownloadTask", "[DownloadTask] cost time:" + this.aR / 1000L + "s");
      LogUtil.d("DownloadTask", "[DownloadTask] retry times:" + m());
      LogUtil.d("DownloadTask", "[DownloadTask] speed:" + 1000.0F * ((float)this.I / 1024.0F) / (float)this.aR + "KB/S");
    }
    while (((0x100 & K()) == 256) || ((0x800 & K()) == 2048));
    if (s())
      o();
    this.aD = 3;
    ax();
  }

  public void Z()
  {
  }

  public String a()
  {
    return this.bg;
  }

  public void a(int paramInt)
  {
    this.M = paramInt;
    this.aD = 5;
    ax();
  }

  public void a(int paramInt, long paramLong1, long paramLong2)
  {
    synchronized (this.bk)
    {
      DownloadSections.DownloadSection localDownloadSection = this.Q.a(paramInt);
      if (localDownloadSection == null)
        return;
      localDownloadSection.b = paramLong1;
      this.J = (paramLong2 + this.J);
      if ((this.I > 0L) && (this.J > this.I))
        this.J = this.I;
      return;
    }
  }

  public void a(int paramInt, long paramLong1, byte[] paramArrayOfByte, long paramLong2)
  {
    synchronized (this.bl)
    {
      if (this.P != null)
      {
        this.P.seek(paramLong1);
        this.P.write(paramArrayOfByte, 0, (int)paramLong2);
      }
      return;
    }
  }

  public void a(long paramLong)
  {
    this.bi = paramLong;
  }

  protected void a(Downloader paramDownloader)
  {
    b(paramDownloader);
    int i1 = this.N.size();
    if (i1 > this.O)
      for (int i11 = i1 - 1; i11 >= this.O; i11--)
      {
        if ((i11 < 0) || (i11 >= this.N.size()))
          continue;
        Downloader localDownloader2 = (Downloader)this.N.remove(i11);
        if (localDownloader2 == null)
          continue;
        localDownloader2.R();
      }
    int i8;
    int i9;
    int i10;
    if (this.N.size() == this.O)
    {
      i8 = 0;
      i9 = 1;
      i10 = 1;
      if (i8 < this.O)
      {
        Downloader localDownloader1 = (Downloader)this.N.get(i8);
        if (localDownloader1 != null)
          if (!localDownloader1.q())
          {
            if (localDownloader1.an() != 7)
              break label472;
            i9 = 0;
            i10 = 0;
          }
      }
    }
    while (true)
    {
      i8++;
      break;
      i9 = 0;
      continue;
      int i2 = i10;
      int i3 = i9;
      Object localObject1;
      int i4;
      int i5;
      label216: Object localObject2;
      if (i3 == 0)
      {
        localObject1 = null;
        if (this.N.size() == this.O)
        {
          i4 = 1;
          i5 = 0;
          if (i5 >= this.O)
            break label372;
          localObject2 = (Downloader)this.N.get(i5);
          if ((localObject2 == null) || (((Downloader)localObject2).r()))
            break label461;
          int i7 = ((Downloader)localObject2).an();
          if ((i7 != 5) && (i7 != 3))
            i4 = 0;
          if (i7 != 5)
            break label461;
        }
      }
      for (int i6 = i4; ; i6 = i4)
      {
        i5++;
        i4 = i6;
        localObject1 = localObject2;
        break label216;
        LogUtil.d("DownloadTask", "[DownloadTask]mDownloaders.size() != mMaxDownloaderNum = " + this.O);
        LogUtil.d("DownloadTask", "[DownloadTask]mDownloaders.size() = " + this.N.size());
        i2 = 1;
        i3 = 0;
        break;
        i4 = 1;
        label372: if ((i4 != 0) && (localObject1 != null))
          d(localObject1);
        do
        {
          return;
          LogUtil.d("DownloadTask", "[DownloadTask] task file:" + q() + " finished downloader id:" + paramDownloader.b());
          if ((i2 == 0) || (!this.aE))
            continue;
          this.aE = false;
          S();
          return;
        }
        while (i3 == 0);
        Y();
        return;
        label461: localObject2 = localObject1;
      }
      label472: i9 = 0;
    }
  }

  public void a(Task paramTask)
  {
    monitorenter;
    monitorexit;
  }

  public void a(File paramFile)
  {
    this.Q.a(paramFile);
  }

  public void a(String paramString)
  {
    this.bg = paramString;
  }

  protected void a(String paramString, Downloader paramDownloader)
  {
    LogUtil.d("DownloadTask", "[DownloadTask]handleTaskRestart");
    this.K = paramString;
    this.ay.a(paramString);
    if (this.N.size() == this.O)
      for (int i1 = 0; i1 < this.O; i1++)
      {
        Downloader localDownloader = (Downloader)this.N.get(i1);
        if ((localDownloader == null) || (localDownloader.q()) || (localDownloader == paramDownloader))
          continue;
        localDownloader.R();
      }
    this.R = 0L;
    this.J = 0L;
    this.bc = null;
    this.S = System.currentTimeMillis();
    this.aD = 2;
    this.Q.a(true);
    W();
  }

  public void a(boolean paramBoolean)
  {
    this.bf = paramBoolean;
    if (paramBoolean)
    {
      this.aT = (0x40 | this.aT);
      return;
    }
    this.aT = (0xFFFFFFBF & this.aT);
  }

  public boolean a(Downloader paramDownloader, MttResponse paramMttResponse)
  {
    LogUtil.d("DownloadTask", "[DownloadTask][" + this.F + "]: 侦查到HTTP Header信息");
    this.L = false;
    String str = paramMttResponse.q();
    if (str != null)
    {
      Matcher localMatcher = aJ.matcher(str);
      if (localMatcher.find())
      {
        long l1 = Long.parseLong(localMatcher.group(1));
        LogUtil.d("DownloadTask", "[DownloadTask]**** Start range - " + l1);
        LogUtil.d("DownloadTask", "[DownloadTask]**** Start currentPos - 1");
        if (l1 != 0L)
          break label474;
      }
    }
    label474: for (boolean bool2 = true; ; bool2 = false)
    {
      this.L = bool2;
      m(this.bh);
      this.bc = paramMttResponse.h();
      d(paramMttResponse.g());
      o(true);
      if (((this.bh) || (!FileUtils.e(this.G))) && (!this.aM))
        this.H = FileUtils.a(this.G);
      LogUtil.d("DownloadTask", "[DownloadTask][" + this.F + "]: 侦查后文件名:" + this.G + ",目录=" + this.H);
      synchronized (this.aL)
      {
        if (this.aZ)
          this.G = FileUtils.c(this.H, this.G);
        if (this.aO != null)
          FileUtils.a(this.G, this.H, this.aO);
        boolean bool1 = ae();
        if (!bool1)
          return false;
      }
      LogUtil.d("DownloadTask", "[DownloadTask]content-type: " + paramMttResponse.f().a());
      LogUtil.d("DownloadTask", "[DownloadTask]content-value:" + paramMttResponse.f().b());
      LogUtil.d("DownloadTask", "[DownloadTask]content-disposition: " + paramMttResponse.r());
      if (this.aE)
      {
        S();
        return false;
      }
      if ((!this.aZ) && (aI()))
      {
        ad();
        return false;
      }
      this.Q.b(this.H, this.G);
      this.Q.a(true);
      n(true);
      return true;
    }
  }

  public int aa()
  {
    return 0;
  }

  public void ab()
  {
    if (!this.L)
      return;
    synchronized (this.bk)
    {
      this.Q.a(this.J, this.O);
      return;
    }
  }

  public void ac()
  {
    this.Q.a();
  }

  protected void ad()
  {
    LogUtil.d("DownloadTask", "[DownloadTask]restoring downloaders from data");
    this.N.clear();
    int i1 = 0;
    int i2 = 1;
    DownloadSections.DownloadSection localDownloadSection;
    Downloader localDownloader;
    if (i1 < this.O)
    {
      localDownloadSection = this.Q.a(i1);
      this.N.add(i1, Downloader.a(this, localDownloadSection, i1));
      localDownloader = (Downloader)this.N.get(i1);
      if ((!localDownloadSection.b()) && (!localDownloadSection.c()))
        if (this.aE)
          S();
    }
    do
    {
      return;
      LogUtil.d("DownloadTask", "[DownloadTask]new thread with startPos:" + localDownloadSection);
      localDownloader.c(false);
      localDownloader.i();
      i2 = 0;
      while (true)
      {
        i1++;
        break;
        localDownloader.c(true);
      }
    }
    while (i2 == 0);
    Y();
  }

  protected boolean ae()
  {
    try
    {
      new File(this.H).mkdirs();
      File localFile1 = new File(this.H, this.G + ".qbdltmp");
      if ((this.J > 0L) && (!localFile1.exists()))
        localFile1 = new File(this.H, this.G);
      if ((this.J > 0L) && (!N()))
      {
        LogUtil.d("DownloadTask", "[DownloadTask]File not exist after downloading.");
        Q();
        File localFile2 = new File(this.H, "." + this.G + ".dltmp");
        if ((localFile2 != null) && (localFile2.exists()))
        {
          localFile2.delete();
          return false;
        }
      }
      else
      {
        if ((localFile1 != null) && (!localFile1.exists()))
          localFile1.createNewFile();
        this.P = new RandomAccessFile(localFile1, "rw");
        return true;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.M = 5;
      if (FileUtils.p() < 2024L)
        this.M = 2;
      this.aD = 5;
      ax();
    }
    return false;
  }

  protected boolean af()
  {
    boolean bool = this.Q.b(this.I, this.O);
    if (!bool)
      LogUtil.d("DownloadTask", "[DownloadTask]invalid section data");
    return bool;
  }

  public void ag()
  {
    Iterator localIterator = this.N.iterator();
    Downloader localDownloader;
    do
    {
      if (!localIterator.hasNext())
        break;
      localDownloader = (Downloader)localIterator.next();
    }
    while ((localDownloader == null) || (localDownloader.an() != 7));
    for (int i1 = 0; ; i1 = 1)
    {
      if ((i1 != 0) && (this.aD == 7))
        this.aD = 6;
      return;
    }
  }

  public boolean ah()
  {
    String str = Environment.getDataDirectory().getAbsolutePath();
    return (!TextUtils.isEmpty(this.H)) && (this.H.startsWith(str));
  }

  public void ai()
  {
    if (this.P != null);
    try
    {
      if ((this.I > 0L) && (this.P.length() > this.I))
        this.P.setLength(this.I);
      this.P.close();
      this.P = null;
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public String aj()
  {
    return this.be;
  }

  public String ak()
  {
    return this.bd;
  }

  public boolean al()
  {
    return false;
  }

  public int am()
  {
    return this.M;
  }

  public long b()
  {
    return this.bi;
  }

  public void b(int paramInt)
  {
    this.O = paramInt;
  }

  public void b(long paramLong)
  {
    this.J = paramLong;
  }

  protected void b(Downloader paramDownloader)
  {
    int i1;
    int i2;
    if ((paramDownloader != null) && (paramDownloader.q()))
    {
      i1 = paramDownloader.b();
      i2 = this.N.size();
    }
    for (int i3 = 0; ; i3++)
    {
      if (i3 < this.O)
      {
        if ((i3 == i1) || (i3 >= i2) || (i1 >= i2) || (((Downloader)this.N.get(i3)).g() <= aL()))
          continue;
        a((Downloader)this.N.get(i3), (Downloader)this.N.get(i1));
      }
      return;
    }
  }

  public void b(Task paramTask)
  {
    monitorenter;
    monitorexit;
  }

  public void b(String paramString)
  {
    this.aN = paramString;
  }

  public void b(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.aT = (0x400 | this.aT);
      return;
    }
    this.aT = (0xFFFFFBFF & this.aT);
  }

  public void c(int paramInt)
  {
    this.F = paramInt;
  }

  public void c(long paramLong)
  {
    this.aQ = paramLong;
  }

  public void c(Task paramTask)
  {
    monitorenter;
    try
    {
      if (this.aD != 5)
      {
        if ((this.aD != 7) && (this.aG != null) && (this.aG.size() != 0))
          break label48;
        ((Downloader)paramTask).R();
      }
      while (true)
      {
        return;
        label48: if (this.aE)
          continue;
        this.aD = 2;
        ax();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void c(String paramString)
  {
    this.G = paramString;
  }

  public void c(boolean paramBoolean)
  {
    this.bh = paramBoolean;
    if (paramBoolean)
    {
      this.aT = (0x80 | this.aT);
      return;
    }
    this.aT = (0xFFFFFF7F & this.aT);
  }

  public boolean c()
  {
    return this.bb;
  }

  public void d(int paramInt)
  {
    this.aT = paramInt;
  }

  protected void d(long paramLong)
  {
    this.I = paramLong;
  }

  public void d(Task paramTask)
  {
    monitorenter;
    try
    {
      LogUtil.d("DownloadTask", "[DownloadTask] completed flow:" + paramTask.ar());
      f(paramTask);
      a((Downloader)paramTask);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void d(String paramString)
  {
    this.H = paramString;
  }

  public void d(boolean paramBoolean)
  {
    this.bb = paramBoolean;
    if (paramBoolean)
    {
      this.aT = (0x20 | this.aT);
      return;
    }
    this.aT = (0xFFFFFFDF & this.aT);
  }

  public boolean d()
  {
    return this.bo;
  }

  public void e(Task paramTask)
  {
    monitorenter;
    while (true)
    {
      Downloader localDownloader1;
      int i1;
      boolean bool2;
      try
      {
        LogUtil.d("DownloadTask", "[DownloadTask] onTaskFailed id : " + this.F);
        LogUtil.d("PV", "DownloadTask onTaskFailed flow:" + paramTask.ar());
        f(paramTask);
        localDownloader1 = (Downloader)paramTask;
        if ((localDownloader1.an() != 5) || (this.aD == 5))
          break label538;
        this.R = this.J;
        this.S = System.currentTimeMillis();
        bool1 = true;
        if (this.N.size() != this.O)
          break label553;
        i1 = 0;
        i2 = 0;
        bool2 = bool1;
        if (i1 >= this.O)
          break label569;
        Downloader localDownloader2 = (Downloader)this.N.get(i1);
        if ((localDownloader2 == null) || (localDownloader2 == localDownloader1) || (localDownloader2.q()) || (localDownloader2.r()) || (localDownloader2.an() == 5) || (localDownloader2.an() == 3))
          continue;
        LogUtil.d("DownloadTask", "[DownloadTask]downloader:" + localDownloader2 + " d status:" + localDownloader2.an());
        bool2 = false;
        if ((localDownloader2 == null) || (localDownloader2.an() != 5))
          break label546;
        i3 = i2 + 1;
        break label559;
        LogUtil.d("DownloadTask", "[DownloadTask]downloader:" + localDownloader1 + " downloader real failed:" + bool1);
        LogUtil.d("DownloadTask", "[DownloadTask]downloader:" + localDownloader1 + " downloader status failed error:" + localDownloader1.an());
        LogUtil.d("DownloadTask", "[DownloadTask]downloader:" + localDownloader1 + " downloader failed error:" + localDownloader1.a());
        if (!bool1)
          continue;
        LogUtil.d("DownloadTask", "[DownloadTask]real failed with error code:" + localDownloader1.a());
        if ((i2 >= 2) && (localDownloader1.a() == 7))
        {
          LogUtil.d("DownloadTask", "[DownloadTask]failedNum=" + i2 + ",errcode=" + 7);
          if (this.bj != Apn.c())
            continue;
          c(localDownloader1);
          return;
          d(localDownloader1);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      if (localDownloader1.a() == 10)
      {
        aN();
        continue;
      }
      if (localDownloader1.a() == 8)
      {
        a(this.K, localDownloader1);
        continue;
      }
      d(localDownloader1);
      continue;
      label538: a(localDownloader1);
      continue;
      label546: int i3 = i2;
      break label559;
      label553: int i2 = 0;
      continue;
      label559: i1++;
      i2 = i3;
      continue;
      label569: boolean bool1 = bool2;
    }
  }

  // ERROR //
  public void e(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 202	com/tencent/component/net/download/multiplex/download/DownloadTask:bl	Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: new 544	java/io/File
    //   10: dup
    //   11: aload_0
    //   12: getfield 251	com/tencent/component/net/download/multiplex/download/DownloadTask:H	Ljava/lang/String;
    //   15: new 265	java/lang/StringBuilder
    //   18: dup
    //   19: invokespecial 266	java/lang/StringBuilder:<init>	()V
    //   22: aload_0
    //   23: getfield 154	com/tencent/component/net/download/multiplex/download/DownloadTask:G	Ljava/lang/String;
    //   26: invokevirtual 270	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: ldc 21
    //   31: invokevirtual 270	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   37: invokespecial 546	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   40: astore_3
    //   41: new 544	java/io/File
    //   44: dup
    //   45: aload_0
    //   46: getfield 251	com/tencent/component/net/download/multiplex/download/DownloadTask:H	Ljava/lang/String;
    //   49: new 265	java/lang/StringBuilder
    //   52: dup
    //   53: invokespecial 266	java/lang/StringBuilder:<init>	()V
    //   56: aload_1
    //   57: invokevirtual 270	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: ldc 21
    //   62: invokevirtual 270	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   68: invokespecial 546	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   71: astore 4
    //   73: aload_3
    //   74: aload 4
    //   76: invokevirtual 592	java/io/File:renameTo	(Ljava/io/File;)Z
    //   79: pop
    //   80: aload_3
    //   81: ifnull +21 -> 102
    //   84: aload 4
    //   86: invokevirtual 549	java/io/File:exists	()Z
    //   89: istore 7
    //   91: iload 7
    //   93: ifne +9 -> 102
    //   96: aload 4
    //   98: invokevirtual 776	java/io/File:createNewFile	()Z
    //   101: pop
    //   102: aload_0
    //   103: new 637	java/io/RandomAccessFile
    //   106: dup
    //   107: aload 4
    //   109: ldc_w 778
    //   112: invokespecial 781	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   115: putfield 635	com/tencent/component/net/download/multiplex/download/DownloadTask:P	Ljava/io/RandomAccessFile;
    //   118: aload_2
    //   119: monitorexit
    //   120: aload_0
    //   121: getfield 200	com/tencent/component/net/download/multiplex/download/DownloadTask:bk	Ljava/lang/Object;
    //   124: astore 9
    //   126: aload 9
    //   128: monitorenter
    //   129: new 544	java/io/File
    //   132: dup
    //   133: aload_0
    //   134: getfield 251	com/tencent/component/net/download/multiplex/download/DownloadTask:H	Ljava/lang/String;
    //   137: new 265	java/lang/StringBuilder
    //   140: dup
    //   141: invokespecial 266	java/lang/StringBuilder:<init>	()V
    //   144: ldc_w 768
    //   147: invokevirtual 270	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: aload_0
    //   151: getfield 154	com/tencent/component/net/download/multiplex/download/DownloadTask:G	Ljava/lang/String;
    //   154: invokevirtual 270	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: ldc_w 770
    //   160: invokevirtual 270	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   166: invokespecial 546	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   169: new 544	java/io/File
    //   172: dup
    //   173: aload_0
    //   174: getfield 251	com/tencent/component/net/download/multiplex/download/DownloadTask:H	Ljava/lang/String;
    //   177: new 265	java/lang/StringBuilder
    //   180: dup
    //   181: invokespecial 266	java/lang/StringBuilder:<init>	()V
    //   184: ldc_w 768
    //   187: invokevirtual 270	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: aload_1
    //   191: invokevirtual 270	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: ldc_w 770
    //   197: invokevirtual 270	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   203: invokespecial 546	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   206: invokevirtual 592	java/io/File:renameTo	(Ljava/io/File;)Z
    //   209: pop
    //   210: aload_0
    //   211: getfield 169	com/tencent/component/net/download/multiplex/download/DownloadTask:Q	Lcom/tencent/component/net/download/multiplex/download/DownloadSections;
    //   214: ifnull +15 -> 229
    //   217: aload_0
    //   218: getfield 169	com/tencent/component/net/download/multiplex/download/DownloadTask:Q	Lcom/tencent/component/net/download/multiplex/download/DownloadSections;
    //   221: aload_0
    //   222: getfield 251	com/tencent/component/net/download/multiplex/download/DownloadTask:H	Ljava/lang/String;
    //   225: aload_1
    //   226: invokevirtual 741	com/tencent/component/net/download/multiplex/download/DownloadSections:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   229: aload 9
    //   231: monitorexit
    //   232: aload_0
    //   233: aload_1
    //   234: putfield 154	com/tencent/component/net/download/multiplex/download/DownloadTask:G	Ljava/lang/String;
    //   237: return
    //   238: astore 12
    //   240: aload 12
    //   242: invokevirtual 823	java/io/IOException:printStackTrace	()V
    //   245: goto -143 -> 102
    //   248: astore 5
    //   250: aload_2
    //   251: monitorexit
    //   252: aload 5
    //   254: athrow
    //   255: astore 8
    //   257: aload 8
    //   259: invokevirtual 880	java/io/FileNotFoundException:printStackTrace	()V
    //   262: goto -144 -> 118
    //   265: astore 10
    //   267: aload 9
    //   269: monitorexit
    //   270: aload 10
    //   272: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   96	102	238	java/io/IOException
    //   7	80	248	finally
    //   84	91	248	finally
    //   96	102	248	finally
    //   102	118	248	finally
    //   118	120	248	finally
    //   240	245	248	finally
    //   250	252	248	finally
    //   257	262	248	finally
    //   102	118	255	java/io/FileNotFoundException
    //   129	229	265	finally
    //   229	232	265	finally
    //   267	270	265	finally
  }

  public void e(boolean paramBoolean)
  {
    this.bo = paramBoolean;
    if (paramBoolean)
    {
      this.aT = (0x1000 | this.aT);
      return;
    }
    this.aT = (0xFFFFEFFF & this.aT);
  }

  public boolean e()
  {
    return this.bf;
  }

  public int f()
  {
    if ((this.N != null) && (this.N.size() > 1))
    {
      Iterator localIterator = this.N.iterator();
      while (localIterator.hasNext())
      {
        Downloader localDownloader = (Downloader)localIterator.next();
        if (localDownloader.an() == 5)
          return localDownloader.c();
      }
    }
    return -1;
  }

  public void f(String paramString)
  {
    this.bc = paramString;
  }

  public void f(boolean paramBoolean)
  {
    this.aZ = paramBoolean;
  }

  public void g(Task paramTask)
  {
  }

  public void g(String paramString)
  {
    this.aS = paramString;
  }

  public void g(boolean paramBoolean)
  {
    this.aX = paramBoolean;
  }

  public boolean g()
  {
    return this.bn;
  }

  public void h(String paramString)
  {
    this.be = paramString;
  }

  public void h(boolean paramBoolean)
  {
  }

  public boolean h()
  {
    return (0x400 & this.aT) > 0;
  }

  public int i()
  {
    return this.N.size();
  }

  public void i(String paramString)
  {
    this.bd = paramString;
  }

  public void i(boolean paramBoolean)
  {
    this.aV = paramBoolean;
  }

  public void j(boolean paramBoolean)
  {
    this.aW = paramBoolean;
  }

  public boolean j()
  {
    return this.aU;
  }

  public void k(boolean paramBoolean)
  {
    this.aE = paramBoolean;
  }

  public boolean k()
  {
    return this.aZ;
  }

  public String l()
  {
    return this.aN;
  }

  public void l(boolean paramBoolean)
  {
    this.aY = paramBoolean;
    aC();
  }

  public int m()
  {
    int i1 = this.N.size();
    int i2 = 0;
    int i3 = 0;
    while (i2 < i1)
    {
      i3 += ((Downloader)this.N.get(i2)).h();
      i2++;
    }
    return i3;
  }

  protected void m(boolean paramBoolean)
  {
    if (paramBoolean)
      this.L = false;
  }

  protected void n(boolean paramBoolean)
  {
    long l1 = this.I / this.O;
    if (!paramBoolean)
      this.Q.a(false);
    this.J = 0L;
    int i1 = 0;
    long l2;
    long l3;
    DownloadSections.DownloadSection localDownloadSection2;
    Downloader localDownloader2;
    if (i1 < this.O)
    {
      l2 = l1 * i1;
      l3 = l1 * (i1 + 1) - 1L;
      if (i1 == -1 + this.O)
        l3 = this.I - 1L;
      if ((i1 == 0) && (paramBoolean))
      {
        if (this.Q.b() <= 0)
          this.Q.b(0);
        localDownloadSection2 = this.Q.a(0);
        localDownloadSection2.a = l2;
        localDownloadSection2.a(l3);
        localDownloadSection2.b = l2;
        if (this.N.size() > 0)
        {
          localDownloader2 = (Downloader)this.N.get(0);
          label163: this.N.get(0);
          localDownloader2.a(l2);
          localDownloader2.b(l3);
          localDownloader2.c(l2);
        }
      }
    }
    while (true)
    {
      i1++;
      break;
      localDownloader2 = Downloader.a(this, localDownloadSection2, 0);
      this.N.add(0, localDownloader2);
      break label163;
      DownloadSections.DownloadSection localDownloadSection1 = this.Q.b(i1);
      localDownloadSection1.a = l2;
      localDownloadSection1.a(l3);
      localDownloadSection1.b = l2;
      LogUtil.d("DownloadTask", "[DownloadTask]new thread with startPos:" + l2 + " endPos:" + l3);
      if ((i1 >= 0) && (i1 < this.N.size()))
        this.N.remove(i1);
      this.N.add(i1, Downloader.a(this, localDownloadSection1, i1));
      if (this.aE)
      {
        S();
        return;
      }
      Downloader localDownloader1 = (Downloader)this.N.get(i1);
      localDownloader1.c(false);
      localDownloader1.i();
    }
  }

  public boolean n()
  {
    return this.aX;
  }

  public void o()
  {
    new h(this).start();
  }

  protected void o(boolean paramBoolean)
  {
    if (this.O == 0)
    {
      if ((!this.L) || (this.ba))
        break label104;
      if (this.I >= aK)
        break label73;
      this.O = 1;
    }
    while (true)
    {
      if (Apn.l())
        this.O = 1;
      if (this.ba)
        this.O = 1;
      if (!this.L)
        this.O = 1;
      return;
      label73: if (this.I < 2L * aK)
      {
        this.O = 2;
        continue;
      }
      this.O = 3;
      continue;
      label104: this.O = 1;
    }
  }

  public Bitmap p()
  {
    return this.aO;
  }

  public String q()
  {
    return this.G;
  }

  public String r()
  {
    return this.G;
  }

  public void run()
  {
    monitorenter;
    while (true)
    {
      try
      {
        LogUtil.d("Benson", "============= [DownloadTask] Start [id:" + this.F + "] to download =============");
        if (!this.aY)
          continue;
        Thread.currentThread().setPriority(5);
        this.bn = false;
        this.aD = 1;
        ax();
        this.R = this.J;
        this.S = System.currentTimeMillis();
        this.bj = Apn.c();
        LogUtil.d("Benson", "[DownloadTask] [Task id:" + this.F + "]: 开始运行");
        if (V())
        {
          LogUtil.d("Benson", "[DownloadTask] [Task id:" + this.F + "]: 开始恢复下载");
          aD();
          return;
          Thread.currentThread().setPriority(1);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      LogUtil.d("Benson", "[DownloadTask] [Task id:" + this.F + "]: 开始侦查下载");
      W();
    }
  }

  public boolean s()
  {
    return (!TextUtils.isEmpty(this.G)) && (this.G.toLowerCase().endsWith(".apk"));
  }

  public String t()
  {
    return this.H;
  }

  public int u()
  {
    if (this.I != 0L)
      return (int)(100L * this.J / this.I);
    return 0;
  }

  public String v()
  {
    return this.K;
  }

  public long w()
  {
    return this.J;
  }

  public long x()
  {
    return this.I;
  }

  public void y()
  {
    this.aD = 1;
    ax();
  }

  public void z()
  {
    this.aD = 6;
    ax();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.DownloadTask
 * JD-Core Version:    0.6.0
 */