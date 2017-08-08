package com.tencent.component.ui.widget.adapter;

import android.widget.BaseAdapter;
import java.util.ArrayList;

class c
  implements Runnable
{
  c(MergeAdapter paramMergeAdapter, BaseAdapter paramBaseAdapter)
  {
  }

  public void run()
  {
    MergeAdapter.a(this.b).remove(this.a);
    this.b.notifyDataSetChanged();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.adapter.c
 * JD-Core Version:    0.6.0
 */