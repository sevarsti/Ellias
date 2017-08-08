package com.tencent.component.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UniqueLock
{
  private final ConcurrentHashMap a = new ConcurrentHashMap();

  public Lock a(Object paramObject)
  {
    Lock localLock = (Lock)this.a.get(paramObject);
    if (localLock == null)
      synchronized (this.a)
      {
        Object localObject2 = (Lock)this.a.get(paramObject);
        if (localObject2 == null)
        {
          localObject2 = new ReentrantLock();
          this.a.put(paramObject, localObject2);
        }
        return localObject2;
      }
    return (Lock)localLock;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.UniqueLock
 * JD-Core Version:    0.6.0
 */