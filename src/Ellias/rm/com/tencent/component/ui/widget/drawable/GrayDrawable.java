package com.tencent.component.ui.widget.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public class GrayDrawable extends DrawableContainer
{
  private static final int a = 6;
  private static final ThreadLocal b = new c();
  private Bitmap c;
  private d d;

  @PluginApi(a=6)
  public GrayDrawable(Drawable paramDrawable)
  {
    this.d = new d(paramDrawable, this);
    if ((paramDrawable instanceof ImageDrawable))
      this.c = ((ImageDrawable)paramDrawable).h();
    while (true)
    {
      a(this.d);
      return;
      if (!(paramDrawable instanceof BitmapDrawable))
        continue;
      this.c = ((BitmapDrawable)paramDrawable).getBitmap();
    }
  }

  private GrayDrawable(d paramd, Resources paramResources)
  {
    this.d = new d(paramd, this, paramResources);
    a(this.d);
  }

  public void draw(Canvas paramCanvas)
  {
    if (this.c == null)
    {
      super.draw(paramCanvas);
      return;
    }
    int i = paramCanvas.save();
    paramCanvas.drawBitmap(this.c, 0.0F, 0.0F, (Paint)b.get());
    paramCanvas.restoreToCount(i);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.GrayDrawable
 * JD-Core Version:    0.6.0
 */