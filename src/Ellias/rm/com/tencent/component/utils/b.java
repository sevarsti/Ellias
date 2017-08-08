package com.tencent.component.utils;

import java.io.File;

final class b
  implements FileUtil.FileComparator
{
  public boolean a(File paramFile1, File paramFile2)
  {
    return (paramFile1.length() == paramFile2.length()) && (paramFile1.lastModified() == paramFile2.lastModified());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.b
 * JD-Core Version:    0.6.0
 */