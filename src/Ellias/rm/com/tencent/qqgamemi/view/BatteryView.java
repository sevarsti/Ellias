package com.tencent.qqgamemi.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.common.TLog;

public class BatteryView extends ImageView
{
  private static final String a = "BatteryView";
  private static final int f = 2;
  private Rect b = new Rect();
  private Rect c;
  private Paint d = new Paint();
  private Rect e = new Rect();
  private int g = 0;
  private int h = 0;

  public BatteryView(Context paramContext)
  {
    super(paramContext);
    a();
  }

  public BatteryView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a();
  }

  public BatteryView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a();
  }

  private void a()
  {
    this.d.setColor(getResources().getColor(ResourceUtil.g("qmi_battery_info")));
  }

  private void b()
  {
    if (!this.e.isEmpty())
      this.e.right = (this.e.left + this.h * this.g / 100);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.b.isEmpty())
    {
      Drawable localDrawable = getBackground();
      localDrawable.getPadding(this.b);
      TLog.c("BatteryView", "padding:" + this.b);
      this.c = localDrawable.getBounds();
      TLog.c("BatteryView", "bound:" + this.c);
      this.e.left = (2 + this.b.left);
      this.e.top = (2 + this.b.top);
      this.e.right = (-2 + (this.c.right - this.b.right));
      this.e.bottom = (-2 + (this.c.bottom - this.b.bottom));
      this.g = (this.e.right - this.e.left);
      TLog.c("BatteryView", "battery_info_Rect:" + this.e);
    }
    b();
    paramCanvas.save();
    paramCanvas.drawRect(this.e, this.d);
    paramCanvas.restore();
  }

  public void setBatteryPercent(int paramInt)
  {
    TLog.c("BatteryView", "setBatteryPercent:" + paramInt);
    this.h = paramInt;
    invalidate();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.BatteryView
 * JD-Core Version:    0.6.0
 */