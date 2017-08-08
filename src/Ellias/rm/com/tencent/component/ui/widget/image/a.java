package com.tencent.component.ui.widget.image;

import android.graphics.drawable.Drawable;
import android.view.animation.Animation;

class a
  implements Runnable
{
  a(AsyncImageable.AsyncImageableImpl paramAsyncImageableImpl, Drawable paramDrawable, Animation paramAnimation)
  {
  }

  public void run()
  {
    AsyncImageable.AsyncImageableImpl.a(this.c).setImageDrawable(this.a);
    AsyncImageable.AsyncImageableImpl.a(AsyncImageable.AsyncImageableImpl.a(this.c), this.b, null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.a
 * JD-Core Version:    0.6.0
 */