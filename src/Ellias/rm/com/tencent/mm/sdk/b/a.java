package com.tencent.mm.sdk.b;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.Process;

public final class a
{
  private static int level = 6;
  private static a n;
  private static a o;
  private static final String p;

  static
  {
    b localb = new b();
    n = localb;
    o = localb;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("VERSION.RELEASE:[" + Build.VERSION.RELEASE);
    localStringBuilder.append("] VERSION.CODENAME:[" + Build.VERSION.CODENAME);
    localStringBuilder.append("] VERSION.INCREMENTAL:[" + Build.VERSION.INCREMENTAL);
    localStringBuilder.append("] BOARD:[" + Build.BOARD);
    localStringBuilder.append("] DEVICE:[" + Build.DEVICE);
    localStringBuilder.append("] DISPLAY:[" + Build.DISPLAY);
    localStringBuilder.append("] FINGERPRINT:[" + Build.FINGERPRINT);
    localStringBuilder.append("] HOST:[" + Build.HOST);
    localStringBuilder.append("] MANUFACTURER:[" + Build.MANUFACTURER);
    localStringBuilder.append("] MODEL:[" + Build.MODEL);
    localStringBuilder.append("] PRODUCT:[" + Build.PRODUCT);
    localStringBuilder.append("] TAGS:[" + Build.TAGS);
    localStringBuilder.append("] TYPE:[" + Build.TYPE);
    localStringBuilder.append("] USER:[" + Build.USER + "]");
    p = localStringBuilder.toString();
  }

  public static void a(String paramString1, String paramString2)
  {
    a(paramString1, paramString2, null);
  }

  public static void a(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    if ((o != null) && (o.b() <= 4))
      if (paramArrayOfObject != null)
        break label67;
    label67: for (String str = paramString2; ; str = String.format(paramString2, paramArrayOfObject))
    {
      if (str == null)
        str = "";
      a locala = o;
      Process.myPid();
      Thread.currentThread().getId();
      Looper.getMainLooper().getThread().getId();
      locala.f(paramString1, str);
      return;
    }
  }

  public static void b(String paramString1, String paramString2)
  {
    if ((o != null) && (o.b() <= 2))
    {
      if (paramString2 == null)
        paramString2 = "";
      a locala = o;
      Process.myPid();
      Thread.currentThread().getId();
      Looper.getMainLooper().getThread().getId();
      locala.d(paramString1, paramString2);
    }
  }

  public static void c(String paramString1, String paramString2)
  {
    if ((o != null) && (o.b() <= 1))
    {
      if (paramString2 == null)
        paramString2 = "";
      a locala = o;
      Process.myPid();
      Thread.currentThread().getId();
      Looper.getMainLooper().getThread().getId();
      locala.e(paramString1, paramString2);
    }
  }

  public static abstract interface a
  {
    public abstract int b();

    public abstract void d(String paramString1, String paramString2);

    public abstract void e(String paramString1, String paramString2);

    public abstract void f(String paramString1, String paramString2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mm.sdk.b.a
 * JD-Core Version:    0.6.0
 */