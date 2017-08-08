package com.tencent.beacon.a;

import android.content.Context;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class b
  implements Runnable
{
  private Context a;
  private int b;
  private int c;
  private Runnable d;

  public b(Context paramContext, int paramInt1, int paramInt2, Runnable paramRunnable)
  {
    this.a = paramContext.getApplicationContext();
    this.b = paramInt1;
    this.c = paramInt2;
    this.d = paramRunnable;
  }

  public final void run()
  {
    long l;
    boolean bool;
    com.tencent.beacon.a.a.e locale1;
    com.tencent.beacon.a.a.e locale2;
    if (this.b > 0)
    {
      l = new Date().getTime();
      bool = a.g(this.a);
      locale1 = com.tencent.beacon.applog.a.c(this.a);
      if (locale1 != null)
        break label277;
      locale2 = new com.tencent.beacon.a.a.e();
      locale2.c(l);
      locale2.d(l);
      locale2.b(0L);
      locale2.a(0L);
    }
    label277: for (com.tencent.beacon.a.a.e locale3 = locale2; ; locale3 = locale1)
    {
      locale3.a(locale3.a() + this.b / 60);
      if (bool)
        locale3.b(locale3.b() + this.b / 60);
      locale3.d(l);
      Context localContext = this.a;
      if ((localContext == null) || (locale3 == null));
      while (true)
      {
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = Long.valueOf(locale3.a());
        arrayOfObject[1] = Long.valueOf(locale3.b());
        arrayOfObject[2] = Integer.valueOf(this.b);
        com.tencent.beacon.d.a.e(" used:%d  seen:%d  next:%d", arrayOfObject);
        if (locale3.a() >= this.c)
          c.a().a(this.d);
        return;
        ArrayList localArrayList = new ArrayList();
        com.tencent.beacon.a.a.a locala = new com.tencent.beacon.a.a.a(8, 0, locale3.d(), e.a(locale3));
        locala.a(locale3.e());
        localArrayList.add(locala);
        com.tencent.beacon.a.a.a.b(localContext, localArrayList);
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.a.b
 * JD-Core Version:    0.6.0
 */