package com.tencent.tmassistantsdk.downloadservice;

import java.util.Comparator;

class i
  implements Comparator
{
  i(h paramh)
  {
  }

  public int a(g paramg1, g paramg2)
  {
    if (paramg1.d() > paramg2.d())
      return 1;
    if (paramg1.d() == paramg2.d())
      return 0;
    return -1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.i
 * JD-Core Version:    0.6.0
 */