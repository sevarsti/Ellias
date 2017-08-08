package com.tencent.component.ui.widget.drawable;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

final class c extends ThreadLocal
{
  protected Paint a()
  {
    Paint localPaint = new Paint(6);
    localPaint.setAntiAlias(true);
    ColorMatrix localColorMatrix = new ColorMatrix();
    localColorMatrix.setSaturation(0.0F);
    localPaint.setColorFilter(new ColorMatrixColorFilter(localColorMatrix));
    return localPaint;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.c
 * JD-Core Version:    0.6.0
 */