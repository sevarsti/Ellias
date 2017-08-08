package com.tencent.component.ui.widget;

import android.graphics.drawable.Drawable;
import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public abstract interface Markable
{

  @PluginApi(a=6)
  public static final int MARK_BOTTOM_CENTER = 9;

  @PluginApi(a=6)
  public static final int MARK_CENTER = 1;

  @PluginApi(a=6)
  public static final int MARK_LEFT_BOTTOM = 5;

  @PluginApi(a=6)
  public static final int MARK_LEFT_CENTER = 6;

  @PluginApi(a=6)
  public static final int MARK_LEFT_TOP = 2;

  @PluginApi(a=6)
  public static final int MARK_RIGHT_BOTTOM = 4;

  @PluginApi(a=6)
  public static final int MARK_RIGHT_CENTER = 7;

  @PluginApi(a=6)
  public static final int MARK_RIGHT_TOP = 3;

  @PluginApi(a=6)
  public static final int MARK_TOP_CENTER = 8;

  @PluginApi(a=6)
  public abstract void getMarkerSize(int[] paramArrayOfInt);

  @PluginApi(a=6)
  public abstract boolean isMarkerVisible();

  @PluginApi(a=6)
  public abstract void setMarker(int paramInt);

  @PluginApi(a=6)
  public abstract void setMarker(Drawable paramDrawable);

  @PluginApi(a=6)
  public abstract void setMarkerPaddingOffset(int paramInt1, int paramInt2);

  @PluginApi(a=6)
  public abstract void setMarkerPosition(int paramInt);

  @PluginApi(a=6)
  public abstract void setMarkerSize(int paramInt1, int paramInt2);

  @PluginApi(a=6)
  public abstract void setMarkerVisible(boolean paramBoolean);

  @PluginApi(a=6)
  public abstract void setMarkerVisibleWhenSelected(boolean paramBoolean);

  @PluginApi(a=6)
  public abstract void setOnMarkerClickListener(Markable.OnMarkerClickListener paramOnMarkerClickListener);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.Markable
 * JD-Core Version:    0.6.0
 */