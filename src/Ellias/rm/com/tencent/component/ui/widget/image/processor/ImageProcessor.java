package com.tencent.component.ui.widget.image.processor;

import android.graphics.drawable.Drawable;
import com.tencent.component.annotation.PluginApi;

@PluginApi(a=4)
public abstract class ImageProcessor
{
  private int a = -1;

  public void a(int paramInt)
  {
    this.a = paramInt;
  }

  @PluginApi(a=4)
  public final Drawable doProcess(Drawable paramDrawable)
  {
    if (paramDrawable == null)
      paramDrawable = null;
    Drawable localDrawable;
    do
    {
      return paramDrawable;
      localDrawable = process(paramDrawable);
      if ((this.a <= 0) || (localDrawable == null))
        continue;
      localDrawable.setAlpha(this.a);
    }
    while (localDrawable == null);
    return localDrawable;
  }

  @PluginApi(a=4)
  public abstract Drawable process(Drawable paramDrawable);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.processor.ImageProcessor
 * JD-Core Version:    0.6.0
 */