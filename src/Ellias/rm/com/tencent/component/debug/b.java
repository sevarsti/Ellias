package com.tencent.component.debug;

import java.io.File;
import java.io.FileFilter;

final class b
  implements FileFilter
{
  public boolean accept(File paramFile)
  {
    if (!paramFile.isDirectory());
    do
      return false;
    while (FileTracerConfig.a(paramFile) <= 0L);
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.debug.b
 * JD-Core Version:    0.6.0
 */