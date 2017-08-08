package com.tencent.android.tpush.logging.a;

import java.io.File;
import java.io.FileFilter;

final class c
  implements FileFilter
{
  public boolean accept(File paramFile)
  {
    if (!paramFile.isDirectory());
    do
      return false;
    while (b.a(paramFile) <= 0L);
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.a.c
 * JD-Core Version:    0.6.0
 */