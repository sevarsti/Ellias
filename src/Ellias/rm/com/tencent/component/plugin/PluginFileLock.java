package com.tencent.component.plugin;

import com.tencent.component.utils.UniqueReadWriteLock;
import java.util.concurrent.locks.Lock;

public final class PluginFileLock
{
  private static UniqueReadWriteLock a = new UniqueReadWriteLock();

  public static Lock a(String paramString)
  {
    return a.a(paramString);
  }

  public static Lock b(String paramString)
  {
    return a.b(paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginFileLock
 * JD-Core Version:    0.6.0
 */