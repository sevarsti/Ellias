package com.tencent.qqgamemi.view.dragsortlist;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

class a extends GestureDetector.SimpleOnGestureListener
{
  a(DragSortController paramDragSortController)
  {
  }

  public final boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    int i;
    if ((DragSortController.a(this.a)) && (DragSortController.b(this.a)))
    {
      i = DragSortController.c(this.a).getWidth() / 5;
      if (paramFloat1 <= DragSortController.d(this.a))
        break label83;
      if (DragSortController.e(this.a) > -i)
        DragSortController.c(this.a).a(true, paramFloat1);
    }
    while (true)
    {
      DragSortController.a(this.a, false);
      return false;
      label83: if ((paramFloat1 >= -DragSortController.d(this.a)) || (DragSortController.e(this.a) >= i))
        continue;
      DragSortController.c(this.a).a(true, paramFloat1);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.a
 * JD-Core Version:    0.6.0
 */