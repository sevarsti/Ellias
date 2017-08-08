package com.tencent.component.ui.widget.image.processor;

import android.graphics.drawable.Drawable;
import com.tencent.component.ui.widget.drawable.ScaleDrawable;
import com.tencent.component.ui.widget.drawable.ScaleDrawable.ScaleType;
import com.tencent.component.ui.widget.drawable.SpecifiedDrawable;

public class CropByPivotProcessor extends ImageProcessor
{
  private static final float c;
  protected float a = 0.0F;
  protected float b = 0.0F;

  public CropByPivotProcessor()
  {
  }

  public CropByPivotProcessor(float paramFloat1, float paramFloat2)
  {
    a(paramFloat1, paramFloat2);
  }

  public void a(float paramFloat1, float paramFloat2)
  {
    if ((this.a != paramFloat1) || (this.b != paramFloat2))
    {
      this.a = paramFloat1;
      this.b = paramFloat2;
    }
  }

  public Drawable process(Drawable paramDrawable)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    ScaleDrawable localScaleDrawable = new ScaleDrawable(paramDrawable, ScaleDrawable.ScaleType.CROP_BY_PIVOT);
    localScaleDrawable.a(this.a, this.b);
    return new SpecifiedDrawable(localScaleDrawable, i, j);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.processor.CropByPivotProcessor
 * JD-Core Version:    0.6.0
 */