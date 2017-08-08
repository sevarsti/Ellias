package com.tencent.apkupdate.logic.protocol;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Environment;
import android.util.Log;
import com.tencent.apkupdate.a.e;
import com.tencent.apkupdate.c.c;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class b
{
  private static b b = null;
  private Context a;
  private ExecutorService c;

  public static b a()
  {
    monitorenter;
    try
    {
      if (b == null)
        b = new b();
      b localb = b;
      return localb;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static String a(String paramString)
  {
    PackageManager localPackageManager = a().a.getPackageManager();
    try
    {
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramString, 0);
      if (localPackageInfo != null)
      {
        String str1 = localPackageInfo.applicationInfo.sourceDir;
        Log.i("ProtocolHelper", "packageName: " + paramString);
        Log.i("ProtocolHelper", "localPath: " + str1);
        long l1 = System.currentTimeMillis();
        StringBuilder localStringBuilder = new StringBuilder();
        Context localContext = a().a;
        Object localObject;
        if (("mounted".equals(Environment.getExternalStorageState())) && (Environment.getExternalStorageDirectory().canWrite()))
        {
          File localFile = new File(Environment.getExternalStorageDirectory().getPath() + "/mfcache");
          if (!localFile.exists())
            localFile.mkdirs();
          localObject = localFile.getAbsolutePath();
          if (!((String)localObject).endsWith("/"))
            localObject = (String)localObject + "/";
        }
        while (true)
        {
          String str3 = (String)localObject + paramString + ".cache";
          new e(str1, str3).a();
          String str4 = c.b(str3);
          long l2 = System.currentTimeMillis();
          Log.i("ProtocolHelper", "old_md5: cost=" + (l2 - l1) + "; md5=" + str4);
          if (str4 == null)
            break;
          return str4;
          localObject = localContext.getFilesDir().getAbsolutePath();
          if (((String)localObject).endsWith("/"))
            continue;
          String str2 = (String)localObject + "/";
          localObject = str2;
        }
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return (String)"";
  }

  public final void a(Context paramContext)
  {
    this.c = Executors.newFixedThreadPool(5);
    this.a = paramContext;
  }

  public final void a(Runnable paramRunnable)
  {
    Log.i("ProtocolHelper", "startNewTask");
    if ((this.c != null) && (!this.c.isShutdown()))
    {
      Log.i("ProtocolHelper", "threadPool.execute");
      this.c.execute(paramRunnable);
    }
  }

  public final Context b()
  {
    return this.a;
  }

  public final String b(String paramString)
  {
    try
    {
      PackageInfo localPackageInfo = this.a.getPackageManager().getPackageInfo(paramString, 64);
      if (localPackageInfo != null)
      {
        Signature[] arrayOfSignature = localPackageInfo.signatures;
        if ((arrayOfSignature != null) && (arrayOfSignature.length > 0))
        {
          String str = c.a(arrayOfSignature[(-1 + arrayOfSignature.length)].toCharsString());
          return str;
        }
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
  }

  public final void c()
  {
    Log.i("ProtocolHelper", "threadPool.shutdown()");
    if (this.c != null)
    {
      this.c.shutdown();
      this.c = null;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.logic.protocol.b
 * JD-Core Version:    0.6.0
 */