package com.tencent.component.ui.widget.image;

import android.graphics.drawable.Drawable;
import com.tencent.component.image.ImageLoader.ImageLoadListener;
import com.tencent.component.image.ImageLoader.Options;
import com.tencent.component.utils.log.LogUtil;

class k
  implements ImageLoader.ImageLoadListener
{
  k(GridIconImageView paramGridIconImageView)
  {
  }

  public void a(String paramString, float paramFloat, ImageLoader.Options paramOptions)
  {
  }

  public void a(String paramString, Drawable paramDrawable, ImageLoader.Options paramOptions)
  {
    LogUtil.d(GridIconImageView.a(), "onImageLoaded:" + paramString);
    GridIconImageView.a(this.a, paramDrawable, paramString);
  }

  public void a(String paramString, ImageLoader.Options paramOptions)
  {
  }

  public void b(String paramString, ImageLoader.Options paramOptions)
  {
    LogUtil.d(GridIconImageView.a(), "onImageFailed:" + paramString);
    GridIconImageView.a(this.a, null, paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.k
 * JD-Core Version:    0.6.0
 */