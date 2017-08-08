package com.tencent.component.ui.widget.image;

import android.graphics.Bitmap;
import android.view.animation.Animation;

class e
  implements Runnable
{
  e(ExtendImageView paramExtendImageView, Bitmap paramBitmap, Animation paramAnimation)
  {
  }

  public void run()
  {
    ExtendImageView.a(this.c, this.a);
    if (this.b != null)
      ExtendImageView.a(this.c, this.b, null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.e
 * JD-Core Version:    0.6.0
 */