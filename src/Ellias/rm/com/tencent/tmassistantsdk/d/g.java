package com.tencent.tmassistantsdk.d;

import com.tencent.tmassistantsdk.downloadservice.c;
import com.tencent.tmassistantsdk.downloadservice.n;
import com.tencent.tmassistantsdk.g.l;

class g
  implements n
{
  g(f paramf)
  {
  }

  public void e()
  {
    l.b("LogReportManager", "onNetworkChanged,netState:" + c.b());
    Class[] arrayOfClass = f.e();
    int i = arrayOfClass.length;
    for (int j = 0; ; j++)
      if (j < i)
      {
        Class localClass = arrayOfClass[j];
        try
        {
          if (localClass.equals(b.class))
          {
            b.g().d();
            continue;
          }
          if (!localClass.equals(h.class))
            continue;
          h.g().d();
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
      else
      {
        if ((c.c()) && (c.b().equalsIgnoreCase("wifi")))
          this.a.c();
        return;
      }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.d.g
 * JD-Core Version:    0.6.0
 */