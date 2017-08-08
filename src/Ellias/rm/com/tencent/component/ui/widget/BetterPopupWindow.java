package com.tencent.component.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.utils.ResourceUtil;

@PluginApi(a=7)
public class BetterPopupWindow
{
  private final PopupWindow a;

  @PluginApi(a=7)
  protected final View anchor;
  private View b;
  private Drawable c = null;
  private final WindowManager d;

  @PluginApi(a=7)
  public BetterPopupWindow(View paramView)
  {
    this.anchor = paramView;
    this.a = new PopupWindow(paramView.getContext());
    this.a.setTouchInterceptor(new a(this));
    this.d = ((WindowManager)this.anchor.getContext().getSystemService("window"));
    onCreate();
  }

  private void a()
  {
    if (this.b == null)
      throw new IllegalStateException("setContentView was not called with a view to display.");
    onShow();
    if (this.c == null)
      this.a.setBackgroundDrawable(new BitmapDrawable());
    while (true)
    {
      this.a.setWidth(-2);
      this.a.setHeight(-2);
      this.a.setTouchable(true);
      this.a.setFocusable(true);
      this.a.setOutsideTouchable(true);
      this.a.setContentView(this.b);
      return;
      this.a.setBackgroundDrawable(this.c);
    }
  }

  public View a(int paramInt)
  {
    View localView = this.a.getContentView();
    if (localView != null)
      return localView.findViewById(paramInt);
    return null;
  }

  @PluginApi(a=7)
  public void dismiss()
  {
    this.a.dismiss();
  }

  @PluginApi(a=7)
  protected void onCreate()
  {
  }

  @PluginApi(a=7)
  protected void onShow()
  {
  }

  @PluginApi(a=7)
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    this.c = paramDrawable;
  }

  @PluginApi(a=7)
  public void setContentView(int paramInt)
  {
    setContentView(((LayoutInflater)this.anchor.getContext().getSystemService("layout_inflater")).inflate(paramInt, null));
  }

  @PluginApi(a=7)
  public void setContentView(View paramView)
  {
    this.b = paramView;
    this.a.setContentView(paramView);
  }

  @PluginApi(a=7)
  public void setOnDismissListener(PopupWindow.OnDismissListener paramOnDismissListener)
  {
    this.a.setOnDismissListener(paramOnDismissListener);
  }

  @PluginApi(a=7)
  public void showFromBottom(int paramInt1, int paramInt2)
  {
    a();
    this.a.setAnimationStyle(ResourceUtil.d("Animations_GrowFromBottom"));
    this.a.showAsDropDown(this.anchor, paramInt1, paramInt2);
  }

  @PluginApi(a=7)
  public void showLikePopDownMenu()
  {
    showLikePopDownMenu(0, 0);
  }

  @PluginApi(a=7)
  public void showLikePopDownMenu(int paramInt1, int paramInt2)
  {
    a();
    this.a.setAnimationStyle(ResourceUtil.d("Animations_PopDownMenu"));
    this.a.showAsDropDown(this.anchor, paramInt1, paramInt2);
  }

  @PluginApi(a=7)
  public void showLikeQuickAction()
  {
    showLikeQuickAction(0, 0);
  }

  @PluginApi(a=7)
  public void showLikeQuickAction(int paramInt1, int paramInt2)
  {
    a();
    this.a.setAnimationStyle(ResourceUtil.d("Animations_GrowFromBottom"));
    int[] arrayOfInt = new int[2];
    this.anchor.getLocationOnScreen(arrayOfInt);
    Rect localRect = new Rect(arrayOfInt[0], arrayOfInt[1], arrayOfInt[0] + this.anchor.getWidth(), arrayOfInt[1] + this.anchor.getHeight());
    this.b.measure(-2, -2);
    int i = this.b.getMeasuredWidth();
    int j = this.b.getMeasuredHeight();
    int k = this.d.getDefaultDisplay().getWidth();
    this.d.getDefaultDisplay().getHeight();
    int m = paramInt1 + (k - i) / 2;
    int n = paramInt2 + (localRect.top - j);
    if (j > localRect.top)
    {
      n = paramInt2 + localRect.bottom;
      this.a.setAnimationStyle(ResourceUtil.d("Animations_GrowFromTop"));
    }
    this.a.showAtLocation(this.anchor, 0, m, n);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.BetterPopupWindow
 * JD-Core Version:    0.6.0
 */