package com.tencent.component.debug;

import java.io.File;
import java.util.Comparator;

class d
  implements Comparator
{
  d(FileTracerConfig paramFileTracerConfig)
  {
  }

  public int a(File paramFile1, File paramFile2)
  {
    return FileTracerConfig.e(paramFile1) - FileTracerConfig.e(paramFile2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.debug.d
 * JD-Core Version:    0.6.0
 */