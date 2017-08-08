package com.tencent.component.ui.widget.image.processor;

import android.graphics.drawable.Drawable;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.ui.widget.drawable.RoundCornerDrawable;

@PluginApi(a=4)
public class OvalProcessor extends ImageProcessor
{
  @PluginApi(a=4)
  public Drawable process(Drawable paramDrawable)
  {
    RoundCornerDrawable localRoundCornerDrawable = new RoundCornerDrawable(paramDrawable);
    localRoundCornerDrawable.a(true);
    return localRoundCornerDrawable;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.processor.OvalProcessor
 * JD-Core Version:    0.6.0
 */