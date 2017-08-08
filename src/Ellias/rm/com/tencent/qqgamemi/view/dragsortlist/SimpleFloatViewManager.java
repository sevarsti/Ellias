package com.tencent.qqgamemi.view.dragsortlist;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;

public class SimpleFloatViewManager
  implements DragSortListView.FloatViewManager
{
  private Bitmap a;
  private ImageView b;
  private int c = Color.rgb(198, 49, 48);
  private ListView d;

  public SimpleFloatViewManager(ListView paramListView)
  {
    this.d = paramListView;
  }

  public View a(int paramInt)
  {
    View localView = this.d.getChildAt(paramInt + this.d.getHeaderViewsCount() - this.d.getFirstVisiblePosition());
    if (localView == null)
      return null;
    localView.setPressed(false);
    localView.setDrawingCacheEnabled(true);
    this.a = Bitmap.createBitmap(localView.getDrawingCache());
    localView.setDrawingCacheEnabled(false);
    if (this.b == null)
      this.b = new ImageView(this.d.getContext());
    this.b.setBackgroundColor(this.c);
    this.b.setPadding(0, 0, 0, 0);
    this.b.setImageBitmap(this.a);
    this.b.setLayoutParams(new ViewGroup.LayoutParams(localView.getWidth(), localView.getHeight()));
    return this.b;
  }

  public void a(View paramView)
  {
    ((ImageView)paramView).setImageDrawable(null);
    this.a.recycle();
    this.a = null;
  }

  public void a(View paramView, Point paramPoint1, Point paramPoint2)
  {
  }

  public void g(int paramInt)
  {
    this.c = paramInt;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.SimpleFloatViewManager
 * JD-Core Version:    0.6.0
 */