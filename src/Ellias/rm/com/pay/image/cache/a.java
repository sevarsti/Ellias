package com.pay.image.cache;

import java.io.File;
import java.io.FilenameFilter;

final class a
  implements FilenameFilter
{
  public final boolean accept(File paramFile, String paramString)
  {
    return paramString.startsWith("imgcache_");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.image.cache.a
 * JD-Core Version:    0.6.0
 */