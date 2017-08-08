package com.tencent.component.debug;

import java.io.File;
import java.io.FileFilter;

class c
  implements FileFilter
{
  c(FileTracerConfig paramFileTracerConfig)
  {
  }

  public boolean accept(File paramFile)
  {
    if (!paramFile.getName().endsWith(this.a.j()));
    do
      return false;
    while (FileTracerConfig.e(paramFile) == -1);
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.debug.c
 * JD-Core Version:    0.6.0
 */