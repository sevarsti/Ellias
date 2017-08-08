package com.tencent.component.ui.widget.image.processor;

import android.graphics.drawable.Drawable;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.ui.widget.drawable.RoundCornerDrawable;

public class RoundCornerProcessor extends ImageProcessor
{
  private float a;

  @PluginApi(a=4)
  public RoundCornerProcessor(float paramFloat)
  {
    this.a = paramFloat;
  }

  @PluginApi(a=4)
  public float getRadius()
  {
    return this.a;
  }

  public Drawable process(Drawable paramDrawable)
  {
    float f = this.a;
    if (f == 0.0F)
      return paramDrawable;
    return new RoundCornerDrawable(paramDrawable, f);
  }

  @PluginApi(a=4)
  public void setRadius(float paramFloat)
  {
    this.a = paramFloat;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.processor.RoundCornerProcessor
 * JD-Core Version:    0.6.0
 */