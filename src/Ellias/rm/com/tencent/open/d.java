package com.tencent.open;

import android.content.Context;
import android.location.Location;
import com.tencent.map.a.a.a;

public class d
{
  private f a;

  public void a(Context paramContext, a parama)
  {
    this.a = new f(parama);
    a.a().a(paramContext, this.a);
  }

  public boolean a()
  {
    return a.a().a("OpenSdk", "WQMPF-XMH66-ISQXP-OIGMM-BNL7M");
  }

  public void b()
  {
    a.a().b();
    this.a = null;
  }

  public static abstract interface a
  {
    public abstract void onLocationUpdate(Location paramLocation);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.d
 * JD-Core Version:    0.6.0
 */