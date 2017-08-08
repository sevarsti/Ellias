package com.tenpay.tenpayplugin;

import android.content.Context;
import android.content.res.Resources;

public class TenpayResourceUtil
{
  public static int getAnimId(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "anim", paramContext.getPackageName());
  }

  public static int getColorId(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "color", paramContext.getPackageName());
  }

  public static int getDrawableId(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "drawable", paramContext.getPackageName());
  }

  public static int getId(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "id", paramContext.getPackageName());
  }

  public static int getLayoutId(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "layout", paramContext.getPackageName());
  }

  public static String getString(Context paramContext, String paramString)
  {
    return paramContext.getResources().getString(getStringId(paramContext, paramString));
  }

  public static int getStringId(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "string", paramContext.getPackageName());
  }

  public static int getStyleId(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "style", paramContext.getPackageName());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayResourceUtil
 * JD-Core Version:    0.6.0
 */