package com.tencent.component.ui.widget.image;

import android.graphics.drawable.Drawable;
import android.view.animation.Animation;

class f
  implements Runnable
{
  f(ExtendImageView paramExtendImageView, Drawable paramDrawable, Animation paramAnimation)
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
 * Qualified Name:     com.tencent.component.ui.widget.image.f
 * JD-Core Version:    0.6.0
 */