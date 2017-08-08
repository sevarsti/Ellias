package com.tencent.component.ui.widget.drawable;

import com.tencent.component.image.image.GifStreamImage;

public class GifStreamImageDrawable extends LightweightBitmapDrawable
{
  private final GifStreamImage a;
  private final b b = new b(this);

  public GifStreamImageDrawable(GifStreamImage paramGifStreamImage)
  {
    super(null, paramGifStreamImage.e(), paramGifStreamImage.f());
    this.a = paramGifStreamImage;
    if (isVisible())
      this.a.a(this.b);
  }

  public void finalize()
  {
    super.finalize();
    this.a.b(this.b);
    this.b.a();
  }

  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool = super.setVisible(paramBoolean1, paramBoolean2);
    if (bool)
    {
      if (paramBoolean1)
        this.a.a(this.b);
    }
    else
      return bool;
    this.a.b(this.b);
    return bool;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.GifStreamImageDrawable
 * JD-Core Version:    0.6.0
 */