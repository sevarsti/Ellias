package android.support.v4.widget;

import android.view.View.OnTouchListener;
import android.widget.PopupMenu;

class PopupMenuCompatKitKat
{
  public static View.OnTouchListener getDragToOpenListener(Object paramObject)
  {
    return ((PopupMenu)paramObject).getDragToOpenListener();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     android.support.v4.widget.PopupMenuCompatKitKat
 * JD-Core Version:    0.6.0
 */