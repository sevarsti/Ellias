package com.tencent.component.ui.widget.image.processor;

import android.graphics.drawable.Drawable;
import com.tencent.component.annotation.PluginApi;
import java.util.ArrayList;
import java.util.Iterator;

@PluginApi(a=4)
public class MergeProcessor extends ImageProcessor
{
  private final ArrayList a = new ArrayList();

  @PluginApi(a=4)
  public void addProcessor(int paramInt, ImageProcessor paramImageProcessor)
  {
    if (paramImageProcessor != null)
      this.a.add(paramInt, paramImageProcessor);
  }

  @PluginApi(a=4)
  public void addProcessor(ImageProcessor paramImageProcessor)
  {
    if (paramImageProcessor != null)
      this.a.add(paramImageProcessor);
  }

  @PluginApi(a=4)
  public ImageProcessor getProcessor(int paramInt)
  {
    return (ImageProcessor)this.a.get(paramInt);
  }

  @PluginApi(a=4)
  public int getProcessorCount()
  {
    return this.a.size();
  }

  public Drawable process(Drawable paramDrawable)
  {
    Iterator localIterator = this.a.iterator();
    while (localIterator.hasNext())
      paramDrawable = ((ImageProcessor)localIterator.next()).process(paramDrawable);
    return paramDrawable;
  }

  @PluginApi(a=4)
  public void removeProcessor(ImageProcessor paramImageProcessor)
  {
    if (paramImageProcessor != null)
      this.a.remove(paramImageProcessor);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.processor.MergeProcessor
 * JD-Core Version:    0.6.0
 */