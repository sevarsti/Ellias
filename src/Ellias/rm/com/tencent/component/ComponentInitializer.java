package com.tencent.component;

import android.content.Context;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.net.NetworkManager;

public class ComponentInitializer
{
  private static volatile boolean a;

  public static void a(Context paramContext)
  {
    if (!a)
    {
      ComponentContext.a(paramContext);
      NetworkManager.c(paramContext);
      CacheManager.b(paramContext);
      a = true;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ComponentInitializer
 * JD-Core Version:    0.6.0
 */