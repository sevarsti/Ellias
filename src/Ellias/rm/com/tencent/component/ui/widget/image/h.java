package com.tencent.component.ui.widget.image;

import android.net.Uri;
import android.view.animation.Animation;

class h
  implements Runnable
{
  h(ExtendImageView paramExtendImageView, Uri paramUri, Animation paramAnimation)
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
 * Qualified Name:     com.tencent.component.ui.widget.image.h
 * JD-Core Version:    0.6.0
 */