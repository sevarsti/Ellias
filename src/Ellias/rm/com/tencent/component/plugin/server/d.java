package com.tencent.component.plugin.server;

import java.io.File;
import java.io.FilenameFilter;

final class d
  implements FilenameFilter
{
  d(String paramString)
  {
  }

  public boolean accept(File paramFile, String paramString)
  {
    return paramString.contains(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.d
 * JD-Core Version:    0.6.0
 */