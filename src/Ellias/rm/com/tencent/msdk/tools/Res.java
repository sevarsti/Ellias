package com.tencent.msdk.tools;

import android.content.Context;
import android.content.res.Resources;

public class Res
{
  private Context ctx;

  public Res(Context paramContext)
  {
    this.ctx = paramContext;
  }

  private int reflectResouce(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null) || (this.ctx == null))
    {
      Logger.d("type || name null");
      return -1;
    }
    try
    {
      int i = this.ctx.getResources().getIdentifier(paramString2, paramString1, this.ctx.getPackageName());
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Logger.d("getIdentifier exception");
    }
    return -1;
  }

  public int drawable(String paramString)
  {
    return reflectResouce("drawable", paramString);
  }

  public int id(String paramString)
  {
    return reflectResouce("id", paramString);
  }

  public int layout(String paramString)
  {
    return reflectResouce("layout", paramString);
  }

  public int string(String paramString)
  {
    return reflectResouce("string", paramString);
  }

  public int style(String paramString)
  {
    return reflectResouce("style", paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.Res
 * JD-Core Version:    0.6.0
 */