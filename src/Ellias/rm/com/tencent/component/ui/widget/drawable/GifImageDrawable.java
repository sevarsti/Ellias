package com.tencent.component.ui.widget.drawable;

import com.tencent.component.image.image.GifImage;
import com.tencent.component.image.image.GifImage.Frame;
import java.util.Iterator;
import java.util.List;

public class GifImageDrawable extends GifDrawable
{
  private final GifImage a;

  public GifImageDrawable(GifImage paramGifImage)
  {
    this.a = paramGifImage;
    b();
  }

  private void b()
  {
    List localList = this.a.f();
    if ((localList != null) && (localList.size() > 0))
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        GifImage.Frame localFrame = (GifImage.Frame)localIterator.next();
        if (localFrame == null)
          continue;
        a(localFrame.a, localFrame.b);
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.GifImageDrawable
 * JD-Core Version:    0.6.0
 */