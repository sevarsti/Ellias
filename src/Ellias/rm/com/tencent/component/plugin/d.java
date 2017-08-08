package com.tencent.component.plugin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater.Factory;
import android.view.View;

class d
  implements LayoutInflater.Factory
{
  d(LayoutInflaterProxy paramLayoutInflaterProxy)
  {
  }

  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return LayoutInflaterProxy.a(this.a, null, paramString, paramContext, paramAttributeSet);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.d
 * JD-Core Version:    0.6.0
 */