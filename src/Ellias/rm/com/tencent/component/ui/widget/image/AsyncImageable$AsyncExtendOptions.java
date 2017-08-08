package com.tencent.component.ui.widget.image;

import android.graphics.Bitmap.Config;
import android.view.animation.Animation;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.cache.file.FileCacheService;
import com.tencent.component.image.ImageLoader.Options;

public class AsyncImageable$AsyncExtendOptions
{
  int a = -1;
  int b = -1;
  boolean c = true;
  Bitmap.Config d = ImageLoader.Options.g;
  FileCacheService e = null;
  boolean f = true;
  Animation g = null;
  Animation h = null;
  boolean i = false;
  String[] j;

  @PluginApi(a=4)
  public Object obj;

  protected void a(int paramInt1, int paramInt2)
  {
  }

  final void a(ImageLoader.Options paramOptions)
  {
    paramOptions.i = this.a;
    paramOptions.j = this.b;
    paramOptions.m = this.c;
    paramOptions.o = this.d;
    paramOptions.u = this.e;
    paramOptions.l = this.f;
  }

  @PluginApi(a=4)
  public void setAnimation(Animation paramAnimation1, Animation paramAnimation2)
  {
    if ((this.g != paramAnimation1) || (this.h != paramAnimation2))
    {
      this.g = paramAnimation1;
      this.h = paramAnimation2;
    }
  }

  @PluginApi(a=4)
  public void setClipSize(int paramInt1, int paramInt2)
  {
    if ((this.a != paramInt1) || (this.b != paramInt2))
    {
      this.a = paramInt1;
      this.b = paramInt2;
      a(paramInt1, paramInt2);
    }
  }

  @PluginApi(a=4)
  public void setJustMemoryCache(boolean paramBoolean)
  {
    if (this.i != paramBoolean)
      this.i = paramBoolean;
  }

  @PluginApi(a=4)
  public void setPriority(boolean paramBoolean)
  {
    if (this.f != paramBoolean)
      this.f = paramBoolean;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.AsyncImageable.AsyncExtendOptions
 * JD-Core Version:    0.6.0
 */