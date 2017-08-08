package com.tencent.component.plugin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

final class LayoutInflaterProxy extends LayoutInflater
{
  private LayoutInflaterProxy.InflaterImpl a;

  protected LayoutInflaterProxy(Context paramContext)
  {
    this(paramContext, null);
  }

  protected LayoutInflaterProxy(Context paramContext, LayoutInflaterProxy.InflaterImpl paramInflaterImpl)
  {
    super(paramContext);
    a(paramInflaterImpl);
    a();
  }

  private View a(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    LayoutInflaterProxy.InflaterImpl localInflaterImpl = this.a;
    if (localInflaterImpl == null)
      return null;
    try
    {
      View localView = localInflaterImpl.a(paramView, paramString, paramContext, paramAttributeSet);
      return localView;
    }
    catch (Throwable localThrowable)
    {
    }
    return null;
  }

  @SuppressLint({"InlinedApi", "NewApi"})
  private void a()
  {
    boolean bool = b();
    int i = 0;
    if (bool);
    try
    {
      setFactory2(new c(this));
      i = 1;
      if (i == 0)
        setFactory(new d(this));
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        i = 0;
    }
  }

  private static boolean b()
  {
    return Build.VERSION.SDK_INT >= 11;
  }

  public void a(LayoutInflaterProxy.InflaterImpl paramInflaterImpl)
  {
    this.a = paramInflaterImpl;
  }

  public LayoutInflater cloneInContext(Context paramContext)
  {
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.LayoutInflaterProxy
 * JD-Core Version:    0.6.0
 */