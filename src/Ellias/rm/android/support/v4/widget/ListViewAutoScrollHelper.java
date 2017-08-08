package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

public class ListViewAutoScrollHelper extends AutoScrollHelper
{
  private final ListView mTarget;

  public ListViewAutoScrollHelper(ListView paramListView)
  {
    super(paramListView);
    this.mTarget = paramListView;
  }

  public boolean canTargetScrollHorizontally(int paramInt)
  {
    return false;
  }

  public boolean canTargetScrollVertically(int paramInt)
  {
    ListView localListView = this.mTarget;
    int i = localListView.getCount();
    int j = localListView.getChildCount();
    int k = localListView.getFirstVisiblePosition();
    int m = k + j;
    if (paramInt > 0)
    {
      if ((m < i) || (localListView.getChildAt(j - 1).getBottom() > localListView.getHeight()));
    }
    else
      do
        return false;
      while ((paramInt >= 0) || ((k <= 0) && (localListView.getChildAt(0).getTop() >= 0)));
    return true;
  }

  public void scrollTargetBy(int paramInt1, int paramInt2)
  {
    ListView localListView = this.mTarget;
    int i = localListView.getFirstVisiblePosition();
    if (i == -1);
    View localView;
    do
    {
      return;
      localView = localListView.getChildAt(0);
    }
    while (localView == null);
    localListView.setSelectionFromTop(i, localView.getTop() - paramInt2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     android.support.v4.widget.ListViewAutoScrollHelper
 * JD-Core Version:    0.6.0
 */