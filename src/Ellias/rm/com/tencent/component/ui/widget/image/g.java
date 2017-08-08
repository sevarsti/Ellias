package com.tencent.component.ui.widget.image;

import android.view.animation.Animation;

class g
  implements Runnable
{
  g(ExtendImageView paramExtendImageView, int paramInt, Animation paramAnimation)
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
 * Qualified Name:     com.tencent.component.ui.widget.image.g
 * JD-Core Version:    0.6.0
 */