package com.tencent.android.tpush.service.channel.b;

import com.tencent.android.tpush.service.a.a;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;

public class f
  implements c
{
  private long a = 9223372036854775807L;
  private boolean b = false;
  protected TpnsSecurity j;

  public long a()
  {
    long l = this.a + a.b - System.currentTimeMillis();
    if (l < 0L)
      return 0L;
    return l;
  }

  public void a(TpnsSecurity paramTpnsSecurity)
  {
    this.j = paramTpnsSecurity;
  }

  public boolean b()
  {
    monitorenter;
    try
    {
      boolean bool = this.b;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void c()
  {
    if (this.a == 9223372036854775807L)
      this.a = System.currentTimeMillis();
  }

  public void d()
  {
    monitorenter;
    try
    {
      this.b = true;
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
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.b.f
 * JD-Core Version:    0.6.0
 */