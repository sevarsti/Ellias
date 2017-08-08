package com.tencent.component.cache.common;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

class FastLruCache$1 extends LinkedHashMap
{
  protected boolean removeEldestEntry(Map.Entry paramEntry)
  {
    return size() > this.val$maxSize;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.common.FastLruCache.1
 * JD-Core Version:    0.6.0
 */