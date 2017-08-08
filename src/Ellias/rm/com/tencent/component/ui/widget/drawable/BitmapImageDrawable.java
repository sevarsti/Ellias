package com.tencent.component.ui.widget.drawable;

import com.tencent.component.image.image.BitmapImage;
import com.tencent.component.image.image.Image.MetaInfo;

public class BitmapImageDrawable extends ImageDrawable
{
  private final BitmapImage a;

  public BitmapImageDrawable(BitmapImage paramBitmapImage)
  {
    this(paramBitmapImage, -1, -1);
  }

  public BitmapImageDrawable(BitmapImage paramBitmapImage, int paramInt1, int paramInt2)
  {
    super(paramBitmapImage.e(), paramInt1, paramInt2);
    this.a = paramBitmapImage;
    i();
  }

  private void i()
  {
    a(this.a.f().a);
    b(this.a.f().b);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.BitmapImageDrawable
 * JD-Core Version:    0.6.0
 */