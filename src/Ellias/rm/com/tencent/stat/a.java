package com.tencent.stat;

import android.content.Context;
import android.os.Environment;
import android.provider.Settings.System;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.d;
import com.tencent.stat.common.k;
import com.tencent.stat.common.p;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

public class a
{
  private static a b = null;
  private StatLogger a = k.b();
  private boolean c = false;
  private boolean d = false;
  private boolean e = false;
  private Context f = null;

  private a(Context paramContext)
  {
    this.f = paramContext.getApplicationContext();
    this.c = b(paramContext);
    this.d = d(paramContext);
    this.e = c(paramContext);
  }

  public static a a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (b == null)
        b = new a(paramContext);
      a locala = b;
      return locala;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private boolean b(Context paramContext)
  {
    if (!k.a(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE"))
    {
      this.a.e("Check permission failed: android.permission.WRITE_EXTERNAL_STORAGE");
      return false;
    }
    return true;
  }

  private boolean c(Context paramContext)
  {
    if (!k.a(paramContext, "android.permission.WRITE_SETTINGS"))
    {
      this.a.e("Check permission failed: android.permission.WRITE_SETTINGS");
      return false;
    }
    return true;
  }

  private boolean d(Context paramContext)
  {
    if (k.d() < 14)
      return b(paramContext);
    return true;
  }

  public boolean a(String paramString1, String paramString2)
  {
    p.b(this.f, paramString1, paramString2);
    return true;
  }

  public String b(String paramString1, String paramString2)
  {
    return p.a(this.f, paramString1, paramString2);
  }

  public boolean c(String paramString1, String paramString2)
  {
    if (!this.c)
      return false;
    try
    {
      d.a(Environment.getExternalStorageDirectory() + "/" + "Tencent/mta");
      File localFile = new File(Environment.getExternalStorageDirectory(), "Tencent/mta/.mid.txt");
      if (localFile != null)
      {
        BufferedWriter localBufferedWriter = new BufferedWriter(new FileWriter(localFile));
        localBufferedWriter.write(paramString1 + "," + paramString2);
        localBufferedWriter.write("\n");
        localBufferedWriter.close();
      }
      return true;
    }
    catch (Throwable localThrowable)
    {
      this.a.w(localThrowable);
    }
    return false;
  }

  public String d(String paramString1, String paramString2)
  {
    if (!this.c)
      return null;
    try
    {
      File localFile = new File(Environment.getExternalStorageDirectory(), "Tencent/mta/.mid.txt");
      if (localFile != null)
      {
        Iterator localIterator = d.a(localFile).iterator();
        String[] arrayOfString;
        do
        {
          if (!localIterator.hasNext())
            break;
          arrayOfString = ((String)localIterator.next()).split(",");
        }
        while ((arrayOfString.length != 2) || (!arrayOfString[0].equals(paramString1)));
        String str = arrayOfString[1];
        return str;
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      this.a.w("Tencent/mta/.mid.txt not found.");
      return null;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        this.a.w(localThrowable);
    }
  }

  public boolean e(String paramString1, String paramString2)
  {
    if (!this.e)
      return false;
    Settings.System.putString(this.f.getContentResolver(), paramString1, paramString2);
    return true;
  }

  public String f(String paramString1, String paramString2)
  {
    if (!this.e)
      return paramString2;
    return Settings.System.getString(this.f.getContentResolver(), paramString1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.a
 * JD-Core Version:    0.6.0
 */