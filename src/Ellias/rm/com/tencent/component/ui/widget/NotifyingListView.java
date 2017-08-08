package com.tencent.component.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotifyingListView extends ListView
{
  private List a = Collections.synchronizedList(new ArrayList());
  private AbsListView.OnScrollListener b = new h(this);

  public NotifyingListView(Context paramContext)
  {
    super(paramContext);
  }

  public NotifyingListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public NotifyingListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
  {
    if (paramOnScrollListener != null)
    {
      if (!this.a.contains(paramOnScrollListener))
        this.a.add(paramOnScrollListener);
      super.setOnScrollListener(this.b);
      return;
    }
    this.a.clear();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.NotifyingListView
 * JD-Core Version:    0.6.0
 */