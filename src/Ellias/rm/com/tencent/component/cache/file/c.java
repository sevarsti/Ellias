package com.tencent.component.cache.file;

import java.util.Comparator;

final class c
  implements Comparator
{
  public int a(d paramd1, d paramd2)
  {
    if (paramd1.c < paramd2.c)
      return -1;
    if (paramd1.c == paramd2.c)
      return 0;
    return 1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.file.c
 * JD-Core Version:    0.6.0
 */