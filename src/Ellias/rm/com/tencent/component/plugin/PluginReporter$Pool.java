package com.tencent.component.plugin;

import java.util.ArrayList;

final class PluginReporter$Pool
{
  private final int a;
  private final ArrayList b;
  private final PluginReporter.Pool.Factory c;

  public PluginReporter$Pool(int paramInt, PluginReporter.Pool.Factory paramFactory)
  {
    this.a = paramInt;
    this.b = new ArrayList(paramInt);
    this.c = paramFactory;
  }

  private Object b()
  {
    return this.c.b();
  }

  public Object a()
  {
    monitorenter;
    try
    {
      int i = this.b.size();
      Object localObject2;
      if (i > 0)
        localObject2 = this.b.remove(i - 1);
      Object localObject4;
      for (Object localObject3 = localObject2; ; localObject3 = localObject4)
      {
        return localObject3;
        localObject4 = b();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public void a(Object paramObject)
  {
    monitorenter;
    try
    {
      if (this.b.size() < this.a)
        this.b.add(paramObject);
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
 * Qualified Name:     com.tencent.component.plugin.PluginReporter.Pool
 * JD-Core Version:    0.6.0
 */