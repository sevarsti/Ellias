package com.tencent.component.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UniqueReadWriteLock
{
  private final ConcurrentHashMap a = new ConcurrentHashMap();

  private ReadWriteLock c(Object paramObject)
  {
    ReadWriteLock localReadWriteLock = (ReadWriteLock)this.a.get(paramObject);
    if (localReadWriteLock == null)
      synchronized (this.a)
      {
        Object localObject2 = (ReadWriteLock)this.a.get(paramObject);
        if (localObject2 == null)
        {
          localObject2 = new ReentrantReadWriteLock();
          this.a.put(paramObject, localObject2);
        }
        return localObject2;
      }
    return (ReadWriteLock)localReadWriteLock;
  }

  public Lock a(Object paramObject)
  {
    return c(paramObject).readLock();
  }

  public Lock b(Object paramObject)
  {
    return c(paramObject).writeLock();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.UniqueReadWriteLock
 * JD-Core Version:    0.6.0
 */