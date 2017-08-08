package com.tencent.component.plugin.server;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.component.plugin.PluginInfo;
import com.tencent.component.plugin.PluginPlatformConfig;
import com.tencent.component.utils.ApkUtil.Certificates;
import com.tencent.component.utils.log.LogUtil;
import java.util.HashSet;

public class PluginValidator
{
  private static final String a = "PluginValidator";
  private static final int b = 0;
  private static final int c = 1;
  private static final int d = -1;
  private static final int e = -2;
  private static final int f = -3;
  private static volatile PluginValidator j;
  private final Context g;
  private volatile PackageInfo h;
  private volatile int[] i;

  private PluginValidator(Context paramContext)
  {
    this.g = paramContext.getApplicationContext();
  }

  private int a(c paramc)
  {
    return paramc.g().c();
  }

  private static int a(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    int i2;
    if (paramArrayOfInt1 == null)
      if (paramArrayOfInt2 == null)
        i2 = -3;
    boolean bool;
    do
    {
      return i2;
      return -1;
      if (paramArrayOfInt2 == null)
        return -2;
      HashSet localHashSet1 = new HashSet();
      int k = paramArrayOfInt1.length;
      for (int m = 0; m < k; m++)
        localHashSet1.add(Integer.valueOf(paramArrayOfInt1[m]));
      HashSet localHashSet2 = new HashSet();
      int n = paramArrayOfInt2.length;
      for (int i1 = 0; i1 < n; i1++)
        localHashSet2.add(Integer.valueOf(paramArrayOfInt2[i1]));
      bool = localHashSet1.equals(localHashSet2);
      i2 = 0;
    }
    while (bool);
    return 1;
  }

  private PackageInfo a()
  {
    if (this.h != null)
      return this.h;
    monitorenter;
    try
    {
      if (this.h != null)
      {
        PackageInfo localPackageInfo2 = this.h;
        return localPackageInfo2;
      }
    }
    finally
    {
      monitorexit;
    }
    try
    {
      this.h = this.g.getPackageManager().getPackageInfo(this.g.getPackageName(), 0);
      label59: PackageInfo localPackageInfo1 = this.h;
      monitorexit;
      return localPackageInfo1;
    }
    catch (Throwable localThrowable)
    {
      break label59;
    }
  }

  public static PluginValidator a(Context paramContext)
  {
    if (j != null)
      return j;
    monitorenter;
    try
    {
      if (j != null)
      {
        PluginValidator localPluginValidator2 = j;
        return localPluginValidator2;
      }
    }
    finally
    {
      monitorexit;
    }
    PluginValidator localPluginValidator1 = new PluginValidator(paramContext);
    j = localPluginValidator1;
    monitorexit;
    return localPluginValidator1;
  }

  private static String a(int[] paramArrayOfInt)
  {
    if ((paramArrayOfInt == null) || (paramArrayOfInt.length == 0))
      return null;
    StringBuilder localStringBuilder = new StringBuilder();
    int k = paramArrayOfInt.length;
    for (int m = 0; m < k; m++)
      localStringBuilder.append(paramArrayOfInt[m]).append(' ');
    return localStringBuilder.toString();
  }

  private static int[] a(Signature[] paramArrayOfSignature)
  {
    if (paramArrayOfSignature != null)
    {
      int[] arrayOfInt = new int[paramArrayOfSignature.length];
      for (int k = 0; k < paramArrayOfSignature.length; k++)
      {
        if (paramArrayOfSignature[k] == null)
          continue;
        arrayOfInt[k] = paramArrayOfSignature[k].hashCode();
      }
      return arrayOfInt;
    }
    return null;
  }

  @SuppressLint({"InlinedApi"})
  private int[] b(c paramc)
  {
    if (this.i != null)
      return this.i;
    monitorenter;
    try
    {
      if (this.i != null)
      {
        int[] arrayOfInt2 = this.i;
        return arrayOfInt2;
      }
    }
    finally
    {
      monitorexit;
    }
    try
    {
      this.i = paramc.g().b();
      Object localObject2;
      if (this.i == null)
      {
        if (Build.VERSION.SDK_INT < 8)
          break label106;
        localObject2 = this.g.getPackageCodePath();
        if (localObject2 != null)
          this.i = a(ApkUtil.Certificates.a((String)localObject2, (String[])ApkUtil.Certificates.a));
      }
      label95: int[] arrayOfInt1 = this.i;
      monitorexit;
      return arrayOfInt1;
      label106: PackageInfo localPackageInfo = a();
      if ((localPackageInfo != null) && (localPackageInfo.applicationInfo != null));
      for (String str = localPackageInfo.applicationInfo.sourceDir; ; str = null)
      {
        localObject2 = str;
        break;
      }
    }
    catch (Throwable localThrowable)
    {
      break label95;
    }
  }

  public void a(PluginInfo paramPluginInfo, c paramc)
  {
    if (paramPluginInfo == null)
      throw new PluginValidator.ValidateException("invalid parameter: null");
    if (TextUtils.isEmpty(paramPluginInfo.a))
      throw new PluginValidator.ValidateException("plugin " + paramPluginInfo + " has invalid target plugin: " + paramPluginInfo.a);
    if (TextUtils.isEmpty(paramPluginInfo.pluginId))
      throw new PluginValidator.ValidateException("plugin " + paramPluginInfo + " has invalid id: " + paramPluginInfo.pluginId);
    if (!paramPluginInfo.a())
    {
      if (paramPluginInfo.h == null)
        throw new PluginValidator.ValidateException("plugin " + paramPluginInfo + " has inconsistent signatures");
      if (a(a(paramPluginInfo.h), b(paramc)) != 0)
      {
        if (PluginConstant.c(this.g))
          throw new PluginValidator.ValidateException("plugin " + paramPluginInfo + " has mismatched signatures against platform" + ", plugin(" + a(a(paramPluginInfo.h)) + ")" + " platformArchive(" + a(b(paramc)) + ")");
        LogUtil.w("PluginValidator", "plugin " + paramPluginInfo + " has mismatched signatures against platform");
      }
    }
    int k = paramPluginInfo.minPlatformVersion;
    int m = paramPluginInfo.maxPlatformVersion;
    int n = a(paramc);
    if (((k > 0) && (k > n)) || ((m > 0) && (m < n)))
      throw new PluginValidator.ValidateException("plugin " + paramPluginInfo + " require pluginPlatform version: (min:" + k + ", max:" + m + ")" + ", current is " + n);
    int i1 = paramPluginInfo.minAndroidVersion;
    int i2 = paramPluginInfo.maxAndroidVersion;
    int i3 = Build.VERSION.SDK_INT;
    if (((i1 > 0) && (i1 > i3)) || ((i2 > 0) && (i2 < i3)))
      throw new PluginValidator.ValidateException("plugin " + paramPluginInfo + " require android version: (min:" + i1 + ", max:" + i2 + ")" + ", current is " + i3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.PluginValidator
 * JD-Core Version:    0.6.0
 */