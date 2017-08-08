package com.tencent.component.ui.widget.image.processor;

import android.graphics.drawable.Drawable;
import com.tencent.component.ui.widget.drawable.ScaleDrawable;
import com.tencent.component.ui.widget.drawable.ScaleDrawable.ScaleType;
import com.tencent.component.ui.widget.drawable.SpecifiedDrawable;

public class SpecifiedSizeProcessor extends CropByPivotProcessor
{
  private final int c;
  private final int d;

  public SpecifiedSizeProcessor(int paramInt1, int paramInt2)
  {
    this.c = paramInt1;
    this.d = paramInt2;
  }

  public SpecifiedSizeProcessor(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2)
  {
    this(paramInt1, paramInt2);
    a(paramFloat1, paramFloat2);
  }

  public int a()
  {
    return this.c;
  }

  public int b()
  {
    return this.d;
  }

  public Drawable process(Drawable paramDrawable)
  {
    if ((this.c <= 0) || (this.d <= 0));
    int i;
    int j;
    do
    {
      return paramDrawable;
      i = paramDrawable.getIntrinsicWidth();
      j = paramDrawable.getIntrinsicHeight();
    }
    while ((i == this.c) && (j == this.d));
    ScaleDrawable localScaleDrawable = new ScaleDrawable(paramDrawable, ScaleDrawable.ScaleType.CROP_BY_PIVOT);
    localScaleDrawable.a(this.a, this.b);
    return new SpecifiedDrawable(localScaleDrawable, this.c, this.d);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.processor.SpecifiedSizeProcessor
 * JD-Core Version:    0.6.0
 */