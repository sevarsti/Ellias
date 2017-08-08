package com.tencent.a.a;

import java.io.File;
import java.io.FileFilter;

class j
  implements FileFilter
{
  j(h paramh)
  {
  }

  public boolean accept(File paramFile)
  {
    if (!paramFile.getName().endsWith(this.a.j()));
    do
      return false;
    while (h.d(paramFile) == -1);
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.a.a.j
 * JD-Core Version:    0.6.0
 */