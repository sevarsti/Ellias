package com.tencent.tmassistantsdk.g;

import android.content.Context;
import java.lang.reflect.Field;

public class k
{
  protected Context a;
  protected final String b;

  public k(Context paramContext)
  {
    this.a = paramContext;
    this.b = (this.a.getPackageName() + ".R");
  }

  private int a(String paramString1, String paramString2)
  {
    if ((this.b == null) || (paramString1 == null) || (paramString2 == null))
      return 0;
    try
    {
      Class localClass = Class.forName(this.b + "$" + paramString1);
      int i = Integer.parseInt(localClass.getField(paramString2).get(localClass.newInstance()).toString());
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return -1;
  }

  public int a(String paramString)
  {
    return a("string", paramString);
  }

  public int b(String paramString)
  {
    return a("drawable", paramString);
  }

  public int c(String paramString)
  {
    return a("layout", paramString);
  }

  public int d(String paramString)
  {
    return a("id", paramString);
  }

  public int e(String paramString)
  {
    return a("style", paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.g.k
 * JD-Core Version:    0.6.0
 */