package com.tencent.component.utils;

import android.content.Context;
import android.content.res.Resources;
import com.tencent.component.ComponentContext;

public class ResourceUtil
{
  private static Context a;

  public static int a(String paramString)
  {
    return a(paramString, "layout");
  }

  private static int a(String paramString1, String paramString2)
  {
    int i = paramString1.lastIndexOf(".");
    if (i > 0)
      paramString1 = paramString1.substring(i + 1);
    Context localContext = a;
    if (localContext == null)
      localContext = ComponentContext.a();
    return localContext.getResources().getIdentifier(paramString1, paramString2, localContext.getPackageName());
  }

  public static void a(Context paramContext)
  {
    a = paramContext;
  }

  public static int b(String paramString)
  {
    return a(paramString, "string");
  }

  public static int c(String paramString)
  {
    return a(paramString, "drawable");
  }

  public static int d(String paramString)
  {
    return a(paramString, "style");
  }

  public static int e(String paramString)
  {
    return a(paramString, "styleable");
  }

  public static int f(String paramString)
  {
    return a(paramString, "id");
  }

  public static int g(String paramString)
  {
    return a(paramString, "color");
  }

  public static int h(String paramString)
  {
    return a(paramString, "attr");
  }

  public static int i(String paramString)
  {
    return a(paramString, "anim");
  }

  public static int j(String paramString)
  {
    return a(paramString, "dimen");
  }

  public static int k(String paramString)
  {
    return a(paramString, "integer");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.ResourceUtil
 * JD-Core Version:    0.6.0
 */