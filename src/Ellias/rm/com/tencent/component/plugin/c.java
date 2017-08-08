package com.tencent.component.plugin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater.Factory2;
import android.view.View;

class c
  implements LayoutInflater.Factory2
{
  c(LayoutInflaterProxy paramLayoutInflaterProxy)
  {
  }

  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return LayoutInflaterProxy.a(this.a, paramView, paramString, paramContext, paramAttributeSet);
  }

  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.c
 * JD-Core Version:    0.6.0
 */