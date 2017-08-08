package com.tencent.component.ui.widget.adapter;

import android.database.DataSetObserver;

class e extends DataSetObserver
{
  e(MergeAdapter paramMergeAdapter)
  {
  }

  public void onChanged()
  {
    this.a.notifyDataSetChanged();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.adapter.e
 * JD-Core Version:    0.6.0
 */