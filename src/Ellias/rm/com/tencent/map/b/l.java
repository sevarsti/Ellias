package com.tencent.map.b;

import android.telephony.TelephonyManager;
import java.util.List;

final class l extends Thread
{
  l(m paramm)
  {
  }

  public final void run()
  {
    List localList;
    byte[] arrayOfByte;
    if (m.d(this.a) != null)
    {
      localList = m.d(this.a).getNeighboringCellInfo();
      arrayOfByte = m.e(this.a);
      monitorenter;
      if (localList == null);
    }
    try
    {
      m.f(this.a).clear();
      m.f(this.a).addAll(localList);
      monitorexit;
      m.a(this.a, false);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.map.b.l
 * JD-Core Version:    0.6.0
 */