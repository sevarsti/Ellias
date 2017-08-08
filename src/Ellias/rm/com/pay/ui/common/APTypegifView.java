package com.pay.ui.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class APTypegifView extends View
  implements Runnable
{
  private static Bitmap b;
  private APTypegifOpenHelper a;

  public APTypegifView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public APTypegifView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  public void init()
  {
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.drawBitmap(b, 0.0F, 0.0F, new Paint());
    b = this.a.nextBitmap();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(this.a.getWidth(), this.a.getHeigh());
  }

  public void run()
  {
    while (true)
      try
      {
        Thread.sleep(200L);
        postInvalidate();
        continue;
      }
      catch (Exception localException)
      {
      }
  }

  public void setDelta(int paramInt)
  {
  }

  public void setSrc(int paramInt)
  {
    this.a = APTypegifOpenHelper.getTypeIntance();
    this.a.read(getResources().openRawResource(paramInt));
    b = this.a.getFrame(0);
    new Thread(this).start();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APTypegifView
 * JD-Core Version:    0.6.0
 */