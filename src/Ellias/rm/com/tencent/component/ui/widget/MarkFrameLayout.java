package com.tencent.component.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public class MarkFrameLayout extends FrameLayout
  implements Markable
{
  private Markable.Marker a;

  @PluginApi(a=6)
  public MarkFrameLayout(Context paramContext)
  {
    super(paramContext);
    a(null);
  }

  @PluginApi(a=6)
  public MarkFrameLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramAttributeSet);
  }

  @PluginApi(a=6)
  public MarkFrameLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramAttributeSet);
  }

  private void a(AttributeSet paramAttributeSet)
  {
    this.a = new Markable.Marker(this, paramAttributeSet);
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    this.a.a(paramCanvas);
  }

  public void getMarkerSize(int[] paramArrayOfInt)
  {
    this.a.getMarkerSize(paramArrayOfInt);
  }

  @PluginApi(a=6)
  public boolean isMarkerVisible()
  {
    return this.a.isMarkerVisible();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.a.a();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.a.a(paramMotionEvent))
      return true;
    return super.onTouchEvent(paramMotionEvent);
  }

  @PluginApi(a=6)
  public void setMarker(int paramInt)
  {
    this.a.setMarker(paramInt);
  }

  @PluginApi(a=6)
  public void setMarker(Drawable paramDrawable)
  {
    this.a.setMarker(paramDrawable);
  }

  @PluginApi(a=6)
  public void setMarkerPaddingOffset(int paramInt1, int paramInt2)
  {
    this.a.setMarkerPaddingOffset(paramInt1, paramInt2);
  }

  @PluginApi(a=6)
  public void setMarkerPosition(int paramInt)
  {
    this.a.setMarkerPosition(paramInt);
  }

  @PluginApi(a=6)
  public void setMarkerSize(int paramInt1, int paramInt2)
  {
    this.a.setMarkerSize(paramInt1, paramInt2);
  }

  @PluginApi(a=6)
  public void setMarkerVisible(boolean paramBoolean)
  {
    this.a.setMarkerVisible(paramBoolean);
  }

  @PluginApi(a=6)
  public void setMarkerVisibleWhenSelected(boolean paramBoolean)
  {
    this.a.setMarkerVisibleWhenSelected(paramBoolean);
  }

  @PluginApi(a=6)
  public void setOnMarkerClickListener(Markable.OnMarkerClickListener paramOnMarkerClickListener)
  {
    this.a.setOnMarkerClickListener(paramOnMarkerClickListener);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.MarkFrameLayout
 * JD-Core Version:    0.6.0
 */