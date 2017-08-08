package com.tencent.component;

import android.content.Context;

public class ComponentContext
{
  private static Context a;

  public static Context a()
  {
    return a;
  }

  public static void a(Context paramContext)
  {
    a = paramContext.getApplicationContext();
  }

  public static final String b()
  {
    return a().getPackageName();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ComponentContext
 * JD-Core Version:    0.6.0
 */