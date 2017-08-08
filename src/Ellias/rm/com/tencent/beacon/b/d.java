package com.tencent.beacon.b;

import android.content.Context;
import com.tencent.beacon.a.b.e.a;
import com.tencent.beacon.d.a;
import com.tencent.beacon.event.m;
import com.tencent.beacon.upload.f;
import com.tencent.beacon.upload.g;
import java.util.Arrays;
import java.util.List;

public final class d
  implements com.tencent.beacon.a.b.b
{
  private static d a = null;
  private Context b;
  private g c;
  private f d;

  private d(Context paramContext, g paramg)
  {
    this.b = paramContext.getApplicationContext();
    this.c = paramg;
    this.d = new e();
    if (this.c != null)
      this.c.a(105, this.d);
    com.tencent.beacon.a.b.c localc = com.tencent.beacon.a.b.c.a(this.b);
    localc.a(this);
    localc.a(2, this.c);
  }

  public static d a(Context paramContext, g paramg)
  {
    monitorenter;
    try
    {
      if ((a == null) && (!m.b))
      {
        a.e(" SpeedMonitorModule create instance", new Object[0]);
        a = new d(paramContext, paramg);
      }
      d locald = a;
      return locald;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static d d()
  {
    monitorenter;
    try
    {
      d locald = a;
      monitorexit;
      return locald;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static boolean e()
  {
    com.tencent.beacon.a.b.e locale = com.tencent.beacon.a.b.e.a();
    if (locale != null)
      return locale.c(2);
    return false;
  }

  public final void a()
  {
  }

  public final boolean a(b[] paramArrayOfb)
  {
    if ((paramArrayOfb == null) || (paramArrayOfb.length <= 0))
      return true;
    List localList = Arrays.asList(paramArrayOfb);
    com.tencent.beacon.a.c.a().a(new c(this.b, localList));
    return true;
  }

  public final void b()
  {
    com.tencent.beacon.a.b.e locale = com.tencent.beacon.a.b.c.a(this.b).b();
    if (locale == null);
    while (true)
    {
      return;
      e.a locala = locale.b(2);
      if ((!locala.a()) || (locala == null) || (locala.e() == null))
        continue;
      try
      {
        List localList = e.a(locala.e());
        if (localList == null)
          continue;
        a((b[])localList.toArray(new b[0]));
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }

  public final void c()
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.b.d
 * JD-Core Version:    0.6.0
 */