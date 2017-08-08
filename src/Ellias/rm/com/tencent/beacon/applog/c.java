package com.tencent.beacon.applog;

import android.content.Context;
import com.tencent.beacon.c.a.b;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class c extends com.tencent.beacon.upload.a
{
  private Context d = null;
  private b e = null;
  private d f = null;
  private boolean g = true;

  public c(Context paramContext, d paramd, boolean paramBoolean)
  {
    super(paramContext, 1, 5);
    this.d = paramContext;
    this.f = paramd;
    this.g = paramBoolean;
  }

  private void a(b paramb)
  {
    monitorenter;
    try
    {
      this.e = null;
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

  public final b a()
  {
    int i = 1;
    monitorenter;
    while (true)
    {
      try
      {
        com.tencent.beacon.d.a.b("AppLogUploadDatas.getUploadRequestPackage() start}", new Object[0]);
        if (this.e == null)
          continue;
        b localb = this.e;
        return localb;
        try
        {
          com.tencent.beacon.a.b.e locale = com.tencent.beacon.a.b.c.a(this.c).b();
          String str = "*^@K#K@!";
          if (locale != null)
          {
            i = locale.f();
            j = locale.g();
            str = locale.h();
            byte[] arrayOfByte = com.tencent.beacon.a.e.a(this.f.i().getBytes("UTF8"), j, i, str);
            if (arrayOfByte != null)
              continue;
            com.tencent.beacon.d.a.d("encodeDatasByZipAndEncry failed!", new Object[0]);
            localb = null;
            continue;
            com.tencent.beacon.a.d locald = com.tencent.beacon.a.d.m();
            this.e = com.tencent.beacon.a.e.a(this.a, locald, arrayOfByte, j, i);
            localb = this.e;
          }
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      int j = i;
    }
  }

  public final void a(boolean paramBoolean)
  {
    a(null);
    if (this.g)
      if ((!paramBoolean) && (this.f != null));
    do
    {
      return;
      if ((this.f.h() == null) || ("".equals(this.f.h())))
        this.f.d(this.d.getFilesDir().toString() + "/log" + System.currentTimeMillis());
      if (a.a(this.f.i(), this.f.h()))
      {
        Context localContext = this.d;
        d locald = this.f;
        ArrayList localArrayList2 = new ArrayList();
        localArrayList2.add(locald);
        if (a.a(localContext, localArrayList2) == -1);
      }
      this.e = null;
      this.f = null;
      return;
    }
    while ((!paramBoolean) || (this.f == null));
    File localFile = new File(this.f.h());
    if (localFile.exists())
      localFile.delete();
    ArrayList localArrayList1 = new ArrayList();
    localArrayList1.add(this.f);
    a.b(this.d, localArrayList1);
    this.e = null;
    this.f = null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.applog.c
 * JD-Core Version:    0.6.0
 */