package com.tencent.component.cache;

import com.tencent.component.cache.file.FileStorageHandler.Collector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

final class a
  implements FileStorageHandler.Collector
{
  public Collection a()
  {
    synchronized (CacheManager.b())
    {
      if (CacheManager.b().size() <= 0)
      {
        localArrayList = null;
        return localArrayList;
      }
      ArrayList localArrayList = new ArrayList(CacheManager.b().values());
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.a
 * JD-Core Version:    0.6.0
 */