package com.tencent.qqgamemi.view.dragsortlist;

import android.content.Context;
import android.view.View;
import android.widget.Checkable;

public class DragSortItemViewCheckable extends DragSortItemView
  implements Checkable
{
  public DragSortItemViewCheckable(Context paramContext)
  {
    super(paramContext);
  }

  public boolean isChecked()
  {
    View localView = getChildAt(0);
    if ((localView instanceof Checkable))
      return ((Checkable)localView).isChecked();
    return false;
  }

  public void setChecked(boolean paramBoolean)
  {
    View localView = getChildAt(0);
    if ((localView instanceof Checkable))
      ((Checkable)localView).setChecked(paramBoolean);
  }

  public void toggle()
  {
    View localView = getChildAt(0);
    if ((localView instanceof Checkable))
      ((Checkable)localView).toggle();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.DragSortItemViewCheckable
 * JD-Core Version:    0.6.0
 */