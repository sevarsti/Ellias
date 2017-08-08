package com.tencent.connect.avatar;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class c
{
  public static int a(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat * paramContext.getResources().getDisplayMetrics().density);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.connect.avatar.c
 * JD-Core Version:    0.6.0
 */