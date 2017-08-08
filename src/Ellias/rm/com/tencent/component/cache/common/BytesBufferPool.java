package com.tencent.component.cache.common;

import java.util.ArrayList;

public class BytesBufferPool
{
  private static final int a = 4096;
  private final int b;
  private final int c;
  private final ArrayList d;

  public BytesBufferPool(int paramInt1, int paramInt2)
  {
    this.d = new ArrayList(paramInt1);
    this.b = paramInt1;
    this.c = paramInt2;
  }

  public BytesBufferPool.BytesBuffer a()
  {
    monitorenter;
    try
    {
      int i = this.d.size();
      if (i > 0);
      for (BytesBufferPool.BytesBuffer localBytesBuffer = (BytesBufferPool.BytesBuffer)this.d.remove(i - 1); ; localBytesBuffer = new BytesBufferPool.BytesBuffer(this.c, null))
        return localBytesBuffer;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(BytesBufferPool.BytesBuffer paramBytesBuffer)
  {
    monitorenter;
    try
    {
      int i = paramBytesBuffer.a.length;
      int j = this.c;
      if (i != j);
      while (true)
      {
        return;
        if (this.d.size() >= this.b)
          continue;
        paramBytesBuffer.b = 0;
        paramBytesBuffer.c = 0;
        this.d.add(paramBytesBuffer);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void b()
  {
    monitorenter;
    try
    {
      this.d.clear();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.common.BytesBufferPool
 * JD-Core Version:    0.6.0
 */