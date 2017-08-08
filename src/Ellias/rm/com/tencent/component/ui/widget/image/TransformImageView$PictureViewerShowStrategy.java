package com.tencent.component.ui.widget.image;

import android.graphics.Matrix;

public class TransformImageView$PictureViewerShowStrategy
  implements TransformImageView.InitialImageShowStrategy
{
  static final float a = 1.5F;
  static final float b = 2.0F;

  public Matrix a(TransformImageView paramTransformImageView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Matrix paramMatrix)
  {
    float f1 = paramInt1;
    float f2 = paramInt2;
    float f3 = f1 * 2.0F;
    float f4 = f2 * 2.0F;
    float f5;
    if (paramInt3 < paramInt4)
      f5 = paramInt3 / f1;
    while (true)
    {
      paramMatrix.reset();
      paramMatrix.postScale(f5, f5);
      if (f2 * f5 <= paramInt4)
        break;
      paramMatrix.postTranslate((paramInt3 - f5 * f1) / 2.0F, 0.0F);
      return paramMatrix;
      if ((f3 <= paramInt3) && (f4 <= paramInt4))
      {
        f5 = 1.5F;
        continue;
      }
      f5 = Math.min(paramInt3 / f1, paramInt4 / f2);
    }
    paramMatrix.postTranslate((paramInt3 - f1 * f5) / 2.0F, (paramInt4 - f5 * f2) / 2.0F);
    return paramMatrix;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.TransformImageView.PictureViewerShowStrategy
 * JD-Core Version:    0.6.0
 */