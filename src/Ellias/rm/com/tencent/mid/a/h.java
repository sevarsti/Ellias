package com.tencent.mid.a;

import android.content.Context;
import com.tencent.mid.api.MidCallback;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.util.Util;
import java.util.ArrayList;
import java.util.Arrays;

public class h
  implements Runnable
{
  private Context a = null;
  private MidCallback b = null;
  private int c = 0;

  public h(Context paramContext, int paramInt, MidCallback paramMidCallback)
  {
    this.a = paramContext;
    this.c = paramInt;
    this.b = paramMidCallback;
  }

  private void a()
  {
    com.tencent.mid.b.g localg1 = com.tencent.mid.b.g.a(this.a);
    Integer[] arrayOfInteger1 = new Integer[1];
    arrayOfInteger1[0] = Integer.valueOf(1);
    MidEntity localMidEntity1 = localg1.a(new ArrayList(Arrays.asList(arrayOfInteger1)));
    com.tencent.mid.b.g localg2 = com.tencent.mid.b.g.a(this.a);
    Integer[] arrayOfInteger2 = new Integer[1];
    arrayOfInteger2[0] = Integer.valueOf(2);
    MidEntity localMidEntity2 = localg2.a(new ArrayList(Arrays.asList(arrayOfInteger2)));
    com.tencent.mid.b.g localg3 = com.tencent.mid.b.g.a(this.a);
    Integer[] arrayOfInteger3 = new Integer[1];
    arrayOfInteger3[0] = Integer.valueOf(4);
    MidEntity localMidEntity3 = localg3.a(new ArrayList(Arrays.asList(arrayOfInteger3)));
    if ((Util.equal(localMidEntity1, localMidEntity2)) && (Util.equal(localMidEntity1, localMidEntity3)))
    {
      Util.logInfo("local mid check passed.");
      return;
    }
    MidEntity localMidEntity4 = Util.getNewerMidEntity(Util.getNewerMidEntity(localMidEntity1, localMidEntity2), Util.getNewerMidEntity(localMidEntity1, localMidEntity3));
    Util.logInfo("local mid check failed, redress with mid:" + localMidEntity4.toString());
    com.tencent.mid.b.g.a(this.a).a(localMidEntity4);
  }

  private void b()
  {
    d.a(this.a).a(new g(this.a), new i(this));
  }

  private void c()
  {
    com.tencent.mid.b.a locala = com.tencent.mid.b.g.a(this.a).b();
    if (locala == null)
    {
      Util.logInfo("CheckEntity is null");
      return;
    }
    int i = 1 + locala.c();
    long l = Math.abs(System.currentTimeMillis() - locala.b());
    Util.logInfo("check entity: " + locala.toString() + ",duration:" + l);
    if (((i > locala.d()) && (l > a.a)) || (l > locala.a() * a.a))
    {
      a();
      b();
      return;
    }
    locala.b(i);
    com.tencent.mid.b.g.a(this.a).a(locala);
  }

  public void run()
  {
    Util.logInfo("request type:" + this.c);
    switch (this.c)
    {
    default:
      Util.logInfo("wrong type:" + this.c);
    case 1:
    case 2:
    }
    do
    {
      return;
      if (Util.isNetworkAvailable(this.a))
      {
        d.a(this.a).a(new g(this.a), this.b);
        return;
      }
      this.b.onFail(-10010, "network not available.");
      return;
    }
    while (!Util.isNetworkAvailable(this.a));
    c();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.a.h
 * JD-Core Version:    0.6.0
 */